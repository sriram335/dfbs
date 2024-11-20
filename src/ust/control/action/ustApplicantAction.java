package ust.control.action;


import aepermits.data.DfbsEntrSQL;


import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import main.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import main.data.*;
import org.apache.struts.upload.FormFile;
import ust.to.*;
import ust.control.form.*;
import ust.data.*;
public class ustApplicantAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           ServletContext context =  this.getServlet().getServletConfig().getServletContext();
           DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
           ustApplicantForm ustForm = (ustApplicantForm) form;
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
             ustDAO uDAO = ( ustDAO) dmapNew.getHsDAO(DfbsDataMap2.UST_APPLICANT);
           String method = request.getParameter("method");
            HttpSession session = request.getSession();
           ApplicationContacts contacts = sDAO.setContacts("UST_CONTACT1","UST_CONTACT2");
           session.setAttribute("APPLICATION_CONTACTS",contacts);
            if (method == null) 
         
           { 
             ShoppingCart cart = new ShoppingCart();
             session.setAttribute("SHOPPING_CART_UST",cart);
            setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("start");
           } 
           else if (method.equals("renewUst")) 
           {      setOptions(request,dfbsUtilityDAO);
                setList(request,uDAO);
                 return mapping.findForward("renewUst");
           }
           else if (method.equals("filter")) 
           {  setOptions(request,dfbsUtilityDAO);
             setFilterSession(request);
             setList(request,uDAO);
             return mapping.findForward("renewUst");
           } 
           else if (method.equals("appRenewal")) 
           {  int ustId = Integer.parseInt(request.getParameter("ustId"));
                ustApplicant ust = uDAO.selectust(ustId);
                ustForm.setProperties(ust);
             ust.setFileList(uDAO.selectFileList(Integer.toString(ust.getUstId())));
             session.setAttribute("UST_OLD_APPLICANT",ust);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("appRenewal");
           } 
           else if (method.equals("newApp")) 
           {  setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("appRenewal");
           } 
           else if (method.equals("saveUst")) 
           { 
             ustApplicant ust = ustForm.getUstApplicant();
             ustApplicantForm errorForm = new ustApplicantForm();
             if (validate(ust,errorForm,uDAO,session) ) {
             session.setAttribute("UST_APPLICANT",ust);
             setOptions(request,dfbsUtilityDAO);
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append("/dfbs/ust/ustCert.do");
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
             }
             else {
               request.setAttribute("APPLICATION_ERROR",errorForm);
               setOptions(request,dfbsUtilityDAO);
               return mapping.findForward("appRenewal");
             }
           } 
           
           else if (method.equals("downLoadUst")) 
           { String fileName = request.getParameter("fileName");
           String fileType = request.getParameter("fileType");
           int fileId = Integer.parseInt(request.getParameter("fileId"));
           doGetFile(request,response,uDAO,fileName,fileType,fileId);
           return null;
               }
           else if (method.equals("sendCertification")) 
           {    String ustId = request.getParameter("ustId");
              String userEmail = request.getParameter("userEmail");
                 this.sendCertification(Integer.parseInt(ustId),userEmail,contacts);
                 StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                 redirectUrl.append("/dfbs/ust/ust.do");
                 response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                 return null;
            }
           
             else if (method.equals("goToUpload")) 
           { 
             ustApplicant ust = (ustApplicant) session.getAttribute("UST_APPLICANT");
             if (ust.getUstId()==0) {
               ustForm.setUstId(100);
               ust.setFileList(uDAO.selectFileList("100"));  
             }
             else
             {
              ustForm.setUstId(ust.getUstId());
              ust.setFileList(uDAO.selectFileList(Integer.toString(ust.getUstId()))); 
             }
            return mapping.findForward("goToUpload");
           }  
             // file upload upload feature
             else if (method.equals("uploadFile")) 
            { 
            final FormFile oneFile = ustForm.getCaseFile();
              if(oneFile != null && oneFile.getFileSize()>0 )
                  { String ustId = request.getParameter("ustId"); 
                   ustApplicant ust = (ustApplicant) session.getAttribute("UST_APPLICANT");
                  uDAO.uploadFile(oneFile,ustId);  
                   ust.setFileList(uDAO.selectFileList(ustId)); 
                 }
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append("/dfbs/ust/ustCert.do");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
            }
           throw new Exception("HS_ERROR_METHOD_INVALID");
                
         } 
       catch (Exception e) 
       {
          saveError(request,e);
         request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
          return mapping.findForward("error");
         
       }
           
         
           
         
     }
  public void doGetFile( HttpServletRequest request,HttpServletResponse response,ustDAO uDAO,String fileName,String fileType,int fileId) throws Exception 
  { try {
   response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
   if(fileId >0) {
     java.io.OutputStream out = response.getOutputStream(); 
   int lenFile = uDAO.downLoad(out,fileId);
   out.flush(); 
   out.close();
   return; 
   }
  else {
     response.sendRedirect("goToUpload");
     return;
   }
  } 
           catch(Exception e) 
           {
             throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
           }
  } 
  
  protected static void setFilterSession(HttpServletRequest request) throws Exception
   {
     HttpSession session = request.getSession();
     String filter = request.getParameter("filter");
     
     HsFilter filterSession = new HsFilter();
     filterSession.setType(filter);
     
      if(filter == null ) {
       filterSession.setType("byLetter");
       filterSession.setValue("A");
     } 
        String letter = request.getParameter("letter");
        filterSession.setValue(letter);
      
      session.setAttribute("UST_LIST_FILTER",filterSession);
     
   }
   
  private static void setList(HttpServletRequest request,ustDAO uDAO) throws Exception
   {
   
      HttpSession session = request.getSession();
      
      List list = null;
      
      HsFilter filterSession = (HsFilter) session.getAttribute("UST_LIST_FILTER");
      
      
      if(filterSession == null) {
         filterSession = new HsFilter();
         filterSession.setType("byLetter");
         filterSession.setValue("A");
         session.setAttribute("UST_LIST_FILTER",filterSession);
         list = uDAO.selectUstList(ustSQL.SELECT_UST_LIST,filterSession.getValue());
      } 
      else if(filterSession.getType().equals("byLetter")) 
      {
        list = uDAO.selectUstList(ustSQL.SELECT_UST_LIST,filterSession.getValue());
      } 
      request.setAttribute("UST_LIST",new HsListWrapper(list));
      
   }
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
  {
       List charNav = uDAO.getCharacterNavs(ustSQL.SELECT_FIRST_LETTER_OPTIONS_UST );
      request.setAttribute("UST_FIRST_LETTERS_OPTIONS",charNav);
    List states = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("UST_STATE_OPTIONS",states);
    
  }
  protected static boolean validate(ustApplicant ust, ustApplicantForm  errorForm,ustDAO uDAO ,HttpSession session) throws Exception
  {
   boolean noError = true;
 
   if(ust.getPersonLastName() == null || ust.getPersonLastName().trim().equals("")  ) 
   { 
     errorForm.setPersonLastName("ERROR");
     noError = false;
   } 
   else 
   {
     errorForm.setPersonLastName("");
   }
    if(ust.getCompanyName() == null || ust.getCompanyName().trim().equals("")  ) 
    { 
      errorForm.setCompanyName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyName("");
    }
    if(ust.getAddressFlag() ==null || ust.getAddressFlag().trim().equals("") ) 
   { 
     errorForm.setAddressFlag("ERROR");
     noError = false;
   } 
   else 
   {
     errorForm.setAddressFlag("");
   }
    if(ust.getPersonAddress1() == null || ust.getPersonAddress1().trim().equals("") ) 
    {
      errorForm.setPersonAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonAddress1("");
    }
   if(ust.getPersonCity() == null || ust.getPersonCity().trim().equals("") ) 
   {
     errorForm.setPersonCity("ERROR");
     noError = false;
   } 
   else 
   {
     errorForm.setPersonCity("");
   }
    if(ust.getPersonZip() == null || ust.getPersonZip().trim().equals("") ) 
   { 
     errorForm.setPersonZip("ERROR");
     noError = false;
   }
   
   else 
   {
     errorForm.setPersonZip("");
   }
   
   
   
   if(ust.getPersonPhoneOffice() == null || ust.getPersonPhoneOffice().trim().equals("") ) 
   { 
     errorForm.setPersonPhoneOffice("ERROR");
     noError = false;
   } 
   else 
   {
     errorForm.setPersonPhoneOffice("");
   }
   
   if(ust.getPersonEmail() == null || ust.getPersonEmail().trim().equals("")  ) 
   {
     errorForm.setPersonEmail("ERROR");
     noError = false;
   } 
   else 
   {
     errorForm.setPersonEmail("");
   }
   
       return noError;
  
  
  }
  private static void sendCertification(int ustId ,String userEmail, ApplicationContacts contacts) throws Exception
  {
         try {
            
           HsMailer mail = new HsMailer();
             String[] emailTo = {userEmail};
        //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           String[] bccTo = {contacts.getContact1Email(),contacts.getContact2Email()};
           StringBuffer sb = new StringBuffer();
           sb.append(" This is to inform you that after reviewing your application, we have approved your ").append("\n\r");
           sb.append(" application for UST Certification for state of Indiana.").append("\n\r");
           sb.append("Use this link print certification.Use Internet Explorer as the browser").append("\n\r");
           sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=ust_certification_laser.rdf&p_ust_id="+ustId).append("\n\r");
             sb.append("Use this link to verify status or renew certification or print certification at any future date.").append("\n\r");
             sb.append("https://oas.dhs.in.gov/dfbs/ust/ust.do").append("\n\r"); 
           StringBuffer sub = new StringBuffer();
           sub.append(" Your UST Certification is approved by Fire Marshal office, IDHS, State of Indiana ");
          
           mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
          mail.sendAndClose();
           } 
           catch(Exception e) 
           {
             HsMailer mail1 = new HsMailer();
             String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
             String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            mail1.createMail(contacts.getContact1Email(),emailTo,bccTo," From ust certification "," ust email cert: "+ustId);
          mail1.sendAndClose();     
          }
  }
  
}
