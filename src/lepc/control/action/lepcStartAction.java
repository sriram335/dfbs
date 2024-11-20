package lepc.control.action;

import aepermits.to.DfbsEntrSpecial;

import lepc.control.form.*;
import lepc.to.*;
import childcare.to.*;
import lepc.data.*;
import main.data.*;
import main.to.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import org.apache.struts.upload.FormFile;

import otherUsers.to.otherUsers;




public class lepcStartAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcForm lForm = (lepcForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
      fiscalReportDAO fDAO = (fiscalReportDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_FISCAL_REPORT);
      lepcExerciseDAO eDAO = (lepcExerciseDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_EXERCISE);
      String method = request.getParameter("method");
      HttpSession session = request.getSession();
      ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
      ApplicationContacts contacts = sDAO.setContacts("LEPC_CONTACT1","LEPC_CONTACT2");      
      otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
      
      if (method == null ||((otherUser ==null || otherUser.getUserLoginId().length() < 3 )&&
      (security ==null || security.getUserId().length() < 3)))
      {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      else if (method.equals("start")) 
      { 
        String countyName= (String) session.getAttribute("LEPC_COUNTY_SELECTED");
        if (countyName != null && security ==null) 
        {
            List yearList =lDAO.selectYearList(countyName);
             session.setAttribute("LEPC_YEAR_LIST",yearList);
                List docListEthics =lDAO.selectLepcDocListCurrent(2013,"Ethics");
                 session.setAttribute("LEPC_DOC_LIST_ETHICS",docListEthics);
            return mapping.findForward("lepcYear");                              
              }
        else
        {
        List countyList =lDAO.selectCountyList();
       session.setAttribute("LEPC_COUNTY_LIST",countyList);
          List docListEthics =lDAO.selectLepcDocListCurrent(2013,"Ethics");
           session.setAttribute("LEPC_DOC_LIST_ETHICS",docListEthics);
       return mapping.findForward("start"); 
        }
      } 
      
      else if (method.equals("viewAllCounties")) 
      {setOptions(request,dfbsUtilityDAO);   
       return mapping.findForward("viewAllCounties");  
      }  
      else if (method.equals("searchAllCounties")) 
      {setOptions(request,dfbsUtilityDAO);   
        String lepcYear = request.getParameter("lepcYear");
        String lepcDocType = request.getParameter("lepcDocType");
        String lepcStatus = request.getParameter("lepcStatus");
        List countiesList = lDAO.selectLepcCountyYear(lepcYear,lepcDocType,lepcStatus);
        request.setAttribute("VIEW_LEPC_COUNTY_LIST",countiesList); 
        session.setAttribute("SESSION_DOC_VIEW",lepcDocType);
       return mapping.findForward("viewAllCounties");  
      }  
      else if (method.equals("getEmails")) 
      {  String lepcYear = request.getParameter("lepcYear"); 
        String docType = request.getParameter("lepcDocType"); 
        String docStatus = request.getParameter("lepcStatus"); 
      String emailsList = lDAO.selectEmails(docType,docStatus,lepcYear);
      lForm.setLepcNotes(emailsList);
        setOptions(request,dfbsUtilityDAO);   
        return mapping.findForward("viewAllCounties");  
      }
      
      else if (method.equals("goToCounty")) 
      {   String countyName = request.getParameter("countyName");
        session.setAttribute("LEPC_COUNTY_SELECTED",countyName); 
       List yearList =lDAO.selectYearList(countyName); 
       session.setAttribute("LEPC_YEAR_LIST",yearList); 
      //  List docListTier =lDAO.selectLepcDocListTier2(countyName); 
        // session.setAttribute("LEPC_DOC_LIST_TIER2",docListTier);  
       return mapping.findForward("lepcYear");  
      }  
      else if (method.equals("addLepcYear")) 
      {    
            return mapping.findForward("addLepcYear"); 
      }  
      else if (method.equals("approveLepc")) 
      { LepcYear lYear = (LepcYear) session.getAttribute("LEPC_SELECTED");
        List lepcContacts =lDAO.selectLepcContacts(lYear.getLepcCounty());
        lYear.setLepcStatus("Approved");
        lYear.setLepcApprovedBy(security.getUserId());
        lDAO.updateLepcYear(lYear);
        Iterator j = lepcContacts.iterator();
         while(j.hasNext())
         { DfbsContact contact = (DfbsContact) j.next();
          this.sendApprovalEmail(contact.getPersonEmail(), " LEPC Submission", " Approved", lYear.getLepcYearString());
         }
        return mapping.findForward("lepcDocList"); 
      }  
      else if (method.equals("updateLepcNotes")) 
      { LepcYear lYear = (LepcYear) session.getAttribute("LEPC_SELECTED");
        if (lYear.getLepcNotes() !=null)
        {
        lForm.setLepcNotes(lYear.getLepcNotes());
        }
        return mapping.findForward("lepcNotes"); 
      }   
      else if (method.equals("updateSaveLepcNotes")) 
      { LepcYear lYear = (LepcYear) session.getAttribute("LEPC_SELECTED");
        lYear.setLepcNotes(lForm.getLepcNotes());
        lDAO.updateLepcYearNotes(lYear);
        return mapping.findForward("lepcDocList"); 
      }   
      else if (method.equals("saveLepcYear")) 
      { 
        String lepcYear = request.getParameter("lepcYear");
        String countyName = (String) session.getAttribute("LEPC_COUNTY_SELECTED");
        LepcYear lYearCheck = lDAO.selectLepcYear(lepcYear, countyName);
        if (lYearCheck !=null && lYearCheck.getLepcStatus() !=null) {
          session.setAttribute("LEPC_YEAR_CHECK", "Y");
          return mapping.findForward("addLepcYear"); 
        }
        else
        { session.setAttribute("LEPC_YEAR_CHECK", "N");
        LepcYear lYear =new LepcYear();
        lYear.setLepcYearString("01/01/"+lepcYear);
        lYear.setLepcStatus("Pending");
        lYear.setLepcCounty(countyName);
        lDAO.insertLepc(lYear);
        List yearList =lDAO.selectYearList(countyName);
        session.setAttribute("LEPC_YEAR_LIST",yearList);
       return mapping.findForward("lepcYear"); 
        }
      }   
      else if (method.equals("viewLepcYear")) 
      {   String countyName = (String) session.getAttribute("LEPC_COUNTY_SELECTED");
          int lepcId =Integer.parseInt(request.getParameter("lepcId"));
          LepcYear lYear = lDAO.selectLepcYear(lepcId);
        session.setAttribute("LEPC_SELECTED",lYear);
        session.setAttribute("LEPC_SELECTED_CURRENT_YEAR",lYear.getLepcYearString().substring(6));
        lepcRoster roster= rDAO.selectRosterByLepc(lepcId);
        session.setAttribute("LEPC_DOC_ROSTER",roster);
        List exerciseList= eDAO.selectExerciseByLepc(lepcId,"Exercise");
        session.setAttribute("LEPC_EXERCISE_LIST",exerciseList);
        List proposalList= eDAO.selectExerciseByLepc(lepcId,"Proposal");
        session.setAttribute("LEPC_PROPOSAL_LIST",proposalList);
        fiscalReport fisReport= fDAO.selectFiscalReportByLepc((lepcId));
        session.setAttribute("LEPC_FISCAL_REPORT",fisReport);
        session.setAttribute("LEPC_SELECTED_ID",request.getParameter("lepcId"));
        List docList =lDAO.selectLepcDocList();
         session.setAttribute("LEPC_DOC_LIST",docList);
         List docListLegal =lDAO.selectLepcDocListCurrent(lepcId,"Legal Notice");
          session.setAttribute("LEPC_DOC_LIST_LEGAL",docListLegal);
        List docListFiscal =lDAO.selectLepcDocListCurrent(lepcId,"County Auditor Report");
         session.setAttribute("LEPC_DOC_LIST_FISCAL",docListFiscal);
        List docListPlan =lDAO.selectLepcDocListCurrent(lepcId,"Plan Updates");
         session.setAttribute("LEPC_DOC_LIST_PLAN",docListPlan);
        List docListBylaws =lDAO.selectLepcDocListCurrent(lepcId,"Bylaws");
         session.setAttribute("LEPC_DOC_LIST_BYLAWS",docListBylaws);
        List docListMeetMins =lDAO.selectLepcDocListCurrent(lepcId,"Meeting Minutes and Sign-in");
         session.setAttribute("LEPC_DOC_LIST_MINUTES",docListMeetMins); 
          return mapping.findForward("lepcDocList"); 
      }
      else if (method.equals("Approve")) 
      {  
        LepcYear lYear = (LepcYear) session.getAttribute("LEPC_SELECTED");
        List lepcContacts =lDAO.selectLepcContacts(lYear.getLepcCounty());
          String docId =request.getParameter("docId");
        String docName =request.getParameter("docName");
        String  approvalType =request.getParameter("approvalType");
        if (approvalType ==null ||approvalType.length() ==0 ) {
          lDAO.updateDfbsDocuments(Integer.parseInt(docId),"");
          Iterator j = lepcContacts.iterator();
           while(j.hasNext())
           { DfbsContact contact = (DfbsContact) j.next();
            this.sendApprovalEmail(contact.getPersonEmail(), docName, "Approved", lYear.getLepcYearString());
           }
        }
        else {
          lDAO.updateDfbsDocuments(Integer.parseInt(docId),approvalType);
          Iterator j = lepcContacts.iterator();
           while(j.hasNext())
           { DfbsContact contact = (DfbsContact) j.next();
            this.sendApprovalEmail(contact.getPersonEmail(), docName, approvalType, lYear.getLepcYearString());
           }  
        }
        
        String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      } 
      else if (method.equals("emailNotes")) 
      {    String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
            LepcYear lYear = (LepcYear) session.getAttribute("LEPC_SELECTED");
            List lepcContacts =lDAO.selectLepcContacts(lYear.getLepcCounty());
           setOptions(request,dfbsUtilityDAO);
        Iterator j = lepcContacts.iterator();
         while(j.hasNext())
         { DfbsContact contact = (DfbsContact) j.next();
          this.sendEmailNotes(contact.getPersonEmail(),  lYear.getLepcNotes());
         }  
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;  
      }   
      else if (method.equals("goToUpload")) 
      {    String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
           String docType = request.getParameter("docType");
           lForm.setLepcId(Integer.parseInt(lepcId));
           if (docType.equals("5")) {
             lForm.setLepcDocType("Bylaws");
           }
        if (docType.equals("1")) {
          lForm.setLepcDocType("Legal Notice");
        }
        if (docType.equals("3")) {
          lForm.setLepcDocType("Fiscal Report");
        }
        if (docType.equals("4")) {
          lForm.setLepcDocType("Plan Review");
        }
        if (docType.equals("8")) {
          lForm.setLepcDocType("Meeting Minutes");
        }
        session.setAttribute("LEPC_DOC_UPLOAD_TYPE", lForm.getLepcDocType());
           setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("uploadFile"); 
      }    
      else if (method.equals("goToUploadEthics")) 
      {     String docType = "Ethics";
           lForm.setLepcId(Integer.parseInt("2013"));
           session.setAttribute("LEPC_DOC_UPLOAD_TYPE", docType);
           setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("uploadFileEthics"); 
      } 
      else if (method.equals("deleteFile")) 
      {   String fileId =request.getParameter("fileId");
        lDAO.deleteFile(Integer.parseInt(fileId)); 
        String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");  
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;  
      }    
      else if (method.equals("deleteFileEthics")) 
      {   String fileId =request.getParameter("fileId");
        lDAO.deleteFile(Integer.parseInt(fileId)); 
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/start.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;  
      }    
      else if (method.equals("downLoadFile")) 
      { 
      String fileName = request.getParameter("fileName");
      String fileType = request.getParameter("fileType");
      int fileId = Integer.parseInt(request.getParameter("fileId"));
      if (security !=null ||  otherUser != null)
      { 
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else
      {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      }
      else if (method.equals("uploadFile")) 
      {
        String lepcId = request.getParameter("lepcId");
      final FormFile oneFile = lForm.getCaseFile();
       if(oneFile != null && oneFile.getFileSize()>0 )
           {  
             String lepcDocType = request.getParameter("lepcDocType"); 
             session.setAttribute("LEPC_DOC_TYPE_ERROR","");
             String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                     if (lepcDocType ==null || lepcDocType.equals("XX")) {
                        lForm.setCaseFile(oneFile);
                       session.setAttribute("LEPC_DOC_TYPE_ERROR","ERROR");
                       setOptions(request,dfbsUtilityDAO);
                       return mapping.findForward("uploadFile"); 
                     }
                     else
                     {
                         if (security !=null)
                                {
                                lDAO.uploadFile(oneFile,lepcId,security.getUserId(),lepcDocType);  
                                }
                              else {
                                 lDAO.uploadFile(oneFile,lepcId,otherUser.getUserLoginId(),lepcDocType);  
                                   this.sendSubmissionEmail( oneFile.getFileName(),lepcDocType,otherUser.getUserCounty());
                                 }
                          session.setAttribute("LEPC_DOC_TYPE_ERROR","");
                     }
             }
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      else if (method.equals("uploadFileEthics")) 
      {final FormFile oneFile = lForm.getCaseFile();
        String lepcId = request.getParameter("lepcId"); 
          lDAO.uploadFile(oneFile,lepcId,"Brad","Ethics");  
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      else if (method.equals("viewTutorial")) 
      { 
      String fileName = "lepc online reporting tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79431;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewExRepTutorial")) 
      { 
      String fileName = "Exercise report tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79405;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewByLawTutorial")) 
      { 
      String fileName = "bylaws document tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79407;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewPlanTutorial")) 
      { 
      String fileName = "Plan document upload tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79408;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewLLTutorial")) 
      { 
      String fileName = "legal notice and meeting schedules tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79430;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewSignTutorial")) 
      { 
      String fileName = "minutes and sign-in sheet tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79404;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewExProTutorial")) 
      { 
      String fileName = "exercise proposal tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79406;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewFiscalTutorial")) 
      { 
      String fileName = "Fiscal tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79429;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
      else if (method.equals("viewRosterTutorial")) 
      { 
      String fileName = "Roster tutorial.ppt";
      String fileType = "MASTER";
      int fileId = 79432;
      doGetFile(request,response,lDAO,fileName,fileType,fileId);
      return null;
      }
     
      
      throw new Exception("LEPC Start Action"); 
    }
  
  catch (Exception e) 
  {
     saveError(request,e);
    request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
     return mapping.findForward("error");
    
  }
}
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
  {
   List planTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_DOC_TYPE_LIST);
   request.setAttribute("LEPC_DOC_TYPE_OPTIONS",planTypes);
    List lepcYears = uDAO.getOptions(lepcSQL.SELECT_LEPC_YEAR);
    request.setAttribute("LEPC_YEAR_OPTIONS",lepcYears);
    List counties = uDAO.getOptions(lepcSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("LEPC_COUNTY_OPTIONS",counties);
    List lepcGroups = uDAO.getOptions(lepcSQL.SELECT_LEPC_GROUP_OPTIONS);
    request.setAttribute("LEPC_GROUP_OPTIONS",lepcGroups);
    
  }
  public void doGetFile( HttpServletRequest request,HttpServletResponse response,lepcDAO lDAO,String fileName,String fileType,int fileId) throws Exception 
  { try {
   response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
   if(fileId >0) {
     java.io.OutputStream out = response.getOutputStream(); 
   int lenFile = lDAO.downLoad(out,fileId);
   out.flush(); 
   out.close();
   return; 
   }
  else {
     response.sendRedirect("goToUpload");
     return ;
   }
  } 
           catch(Exception e) 
           {
             throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
           }
  }
  private static void sendApprovalEmail(String contactEmail, String docName, String appType, String lepcYear) throws Exception
  {
         try {
           HsMailer mail = new HsMailer();
           
           String[] emailTo = {contactEmail};
          // String[] emailTo = {"iewusi@dhs,in.gov"};
           String[] bccTo = {"mroe@dhs.IN.gov","KBuster@dhs.in.gov"};
           StringBuffer sb = new StringBuffer("\r\n This is a system generated alert about LEPC approval.");
            sb.append("\n \n This is to confirm that "+docName+" document is "+appType + " for the "+lepcYear+" year.");
            sb.append("\n \n If you have questions or concerns about this email, please contact Ian Ewusi at iewusi@dhs,in.gov.");
            StringBuffer sub = new StringBuffer();
           sub.append(" LEPC approval information ");
            mail.createMail("mroe@dhs.IN.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
           } 
           catch(Exception e) 
           {
              HsMailer mail1 = new HsMailer();
           mail1.createMail("ierd_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
           mail1.sendAndClose();
           }
}
  private static void sendSubmissionEmail( String docName, String appType,String county) throws Exception
  {
         try {
           HsMailer mail = new HsMailer();
           
           String[] emailTo = {"mroe@dhs.IN.gov"};
          // String[] emailTo = {"iewusi@dhs.in.gov"};
           String[] bccTo = {"mroe@dhs.IN.gov","KBuster@dhs.in.gov"};
           StringBuffer sb = new StringBuffer("\r\n This is a system generated alert about LEPC submission.");
             sb.append("\n \n This is to confirm that "+appType+" document with file name "+docName + " for county "+county+" is submitted.");
            StringBuffer sub = new StringBuffer();
           sub.append(" LEPC approval information ");
            mail.createMail("mroe@dhs.IN.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
           } 
           catch(Exception e) 
           {
              HsMailer mail1 = new HsMailer();
           mail1.createMail("ierd_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
           mail1.sendAndClose();
           }
  }
  private static void sendEmailNotes(String contactEmail, String notes) throws Exception
  {
         try {
           HsMailer mail = new HsMailer();
           
           String[] emailTo = {contactEmail};
          // String[] emailTo = {"iewusi@dhs.in.gov"};
           String[] bccTo = {"mroe@dhs.IN.gov","KBuster@dhs.in.gov"};
           StringBuffer sb = new StringBuffer("\r\n This is a system generated alert about LEPC. IERC has added the following notes to your LEPC:"+notes);
            sb.append("\n \n If you have questions or concerns about this email, please contact Ian Ewusi at iewusi@dhs.in.gov.");
            StringBuffer sub = new StringBuffer();
           sub.append(" LEPC notes email ");
            mail.createMail("mroe@dhs.IN.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
           } 
           catch(Exception e) 
           {
              HsMailer mail1 = new HsMailer();
           mail1.createMail("ierc_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
           mail1.sendAndClose();
           }
  }
}