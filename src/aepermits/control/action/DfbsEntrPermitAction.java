package aepermits.control.action;
import aepermits.control.*;
import aepermits.control.form.*;
import aepermits.to.*;
import aepermits.data.*;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;



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
import main.data.*;
import main.to.*;

public class DfbsEntrPermitAction extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        DfbsEntrPermitForm permitForm = (DfbsEntrPermitForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsEntrOwnerDAO oDAO = (DfbsEntrOwnerDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_OWNER);
        DfbsEntrPermitDAO pDAO = (DfbsEntrPermitDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_PERMIT);
         DfbsEntrSpecialDAO sDAO = (DfbsEntrSpecialDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_SPECIAL_PERMIT);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        
        //at this point owner must be in session
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
        ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
        if(method ==null &&(owner == null || ownerApplication == null ))
          { 
          
            throw new Exception("NO_OWNER_IN_SESSION");
          
        }
          if (method.equals("viewPermit"))
              {
                setOptions(request,dfbsUtilityDAO);
              }
          else if (method.equals("deleteFile")) 
             { 
              int fileId = Integer.parseInt(request.getParameter("fileId"));
              oDAO.deleteFile(fileId);
                return mapping.findForward("viewPermit");
             } 
            else if (method.equals("emailOwners")) 
              {  
              List ownerList = pDAO.selectOwnersListEmail();
                 Iterator i = ownerList.iterator();
             while(i.hasNext())
              {
                DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
                String emailContact = pDAO.selectContactEmail(permit.getOwnerId()); 
                 String emailOwner = pDAO.selectOwnerEmail(permit.getOwnerId()); 
                 if (emailOwner !=null || emailContact !=null)
                 {
                  sendEmailReminder(permit,emailContact,emailOwner,contacts);
                  pDAO.updatePermitEmailDate(permit);
                 }
              }
                return mapping.findForward("start");
              } 
         
       if (method.equals("addPermitForm")) 
        { 
          setOptions(request,dfbsUtilityDAO);
         request.setAttribute("DFBS_PERMIT",new DfbsEntrPermit() );
          return mapping.findForward("addPermitForm");
        } 
        else if (method.equals("editPermitForm")) 
        {
          String key = request.getParameter("key");
          if(key!=null)
          { 
          DfbsEntrPermit permit = ownerApplication.getPermit(key);
          Map specialMap =permit.getSpecialMap();
          session.setAttribute("PERMIT_SELECTED_SPECIAL_MAP",specialMap);
          permitForm.setProperties(permit);
          permitForm.setNotes(null);
          permitForm.setApplicationKey(key);
          request.setAttribute("DFBS_PERMIT",permit);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editPermitForm");
          }
          else {
            setOptions(request,dfbsUtilityDAO);
            request.setAttribute("DFBS_PERMIT",new DfbsEntrPermit() );
            return mapping.findForward("addPermitForm");
          }
          
          
        } 
         else if (method.equals("addPermit")) 
        { DfbsEntrPermit permit = permitForm.getDfbsEntrPermit();
          permit.setStatus("NEW");
          DfbsEntrPermitForm errorForm = new DfbsEntrPermitForm();
          
          if (validate(permit,errorForm,oDAO,session) ) {
          
             ownerApplication.addPermit(permit);
             return mapping.findForward("step2");
          } 
          else 
          {
          
            request.setAttribute("DFBS_PERMIT_ERROR",errorForm);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("addPermitForm");
          }
          
        } 
        else if (method.equals("editPermit")) 
        {  
          DfbsEntrPermit permit = permitForm.getDfbsEntrPermit();
          DfbsEntrPermitForm errorForm = new DfbsEntrPermitForm();
            
          if (validate(permit,errorForm,oDAO,session) ) {
            permit.setRenewStatus("Renewed");
            String key = request.getParameter("applicationKey");
            Map specialMap=(Map) session.getAttribute("PERMIT_SELECTED_SPECIAL_MAP");
            permit.setSpecialMap(specialMap);
            ownerApplication.removePermit(key);
            ownerApplication.addPermit(permit);
            permit.setFileList(oDAO.selectFileList(permit.getApplicationKey(),"AEPermit")); 
           session.setAttribute("PERMIT_SELECTED",permit);
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/aepermits/main.do?method=goToUpload&idNumber="+permit.getPermitNumber()+"&idType=AEPermit");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          } 
          else 
          {
           request.setAttribute("DFBS_PERMIT_ERROR",errorForm);
            request.setAttribute("DFBS_PERMIT",permit);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("editPermitForm");
          }
          
        } 
        else if (method.equals("addSelectedPermits")) 
        {
          addPermitsToRenew(request,owner,ownerApplication,pDAO);
          return mapping.findForward("step2");
        }
        else if (method.equals("removePermit")) 
        {  String key = request.getParameter("key");
          ownerApplication.removePermit(key);
          return mapping.findForward("step2");
        } 
         else if (method.equals("viewPermit")) 
        {
         DfbsEntrPermit permit =pDAO.selectPermit(request.getParameter("permitNumber"));
         permit.setFileList(oDAO.viewFileList(request.getParameter("permitNumber"),"AEPermit"));
          permitForm.setProperties(permit);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("VIEW_PERMIT_SELECTED",permit);
          return mapping.findForward("viewPermit");
        } 
          else if (method.equals("updatePermit")) 
        {
         DfbsEntrPermit permit =pDAO.selectPermitUpdate(request.getParameter("permitNumber"));
         permit.setFileList(oDAO.viewFileList(request.getParameter("permitNumber"),"AEPermit"));
          permitForm.setProperties(permit);
           session.setAttribute("UPDATE_PERMIT",permit);
           session.setAttribute("PERMIT_INSP_EMAIL","N");
           session.setAttribute("PERMIT_EMAIL_SENT","N");
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updatePermit");
        }  
           else if (method.equals("saveUpdatePermit")) 
        {
        DfbsEntrPermit permit = permitForm.getDfbsEntrPermit();
       pDAO.updatePermit(permit);
           session.setAttribute("UPDATE_PERMIT",permit);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updatePermit");
        } 
         else if (method.equals("emailInspector")) 
        {
        DfbsEntrPermit permit = (DfbsEntrPermit) session.getAttribute("UPDATE_PERMIT");
         String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
        if (inspEmail.trim().length() > 5)
        {permit.setStatus("APPROVED");
        // pDAO.updatePermit(permit);
         sendEmailInsp(request,inspEmail,permit,owner,pDAO,contacts);
        }permitForm.setProperties(permit);
         setOptions(request,dfbsUtilityDAO);
         session.setAttribute("UPDATE_PERMIT",permit);
         session.setAttribute("PERMIT_INSP_EMAIL","Y");
        return mapping.findForward("updatePermit");
        }
         else if (method.equals("emailPerson")) 
        {
        DfbsEntrPermit permit = (DfbsEntrPermit) session.getAttribute("UPDATE_PERMIT");
        String emailTo = request.getParameter("emailTo");
        permitForm.setProperties(permit);
        setOptions(request,dfbsUtilityDAO);
        session.setAttribute("UPDATE_PERMIT",permit);
        session.setAttribute("PERMIT_EMAIL_SENT","Y");
        if (emailTo.trim().length() > 5)
        {
          String redirectUrl = "https://oasdev.dhs.in.gov/reports/rwservlet?dfbsmailipdf&report=&report=fire_ae_denial_letter.rdf&from="+security.getUserId()+"@dhs.in.gov"+
          "&p_id="+permit.getPermitNumber()+
          "&desname="+emailTo+","+security.getUserId()+"@dhs.in.gov"+"&subject=Your application for " +permit.getPermitNumber()+" information."+"&target=_blank";
          response.sendRedirect(response.encodeRedirectURL(redirectUrl));
          return null;
        }
        return mapping.findForward("updatePermit");
        }
        else if (method.equals("emailDenial")) 
        {
        DfbsEntrPermit permit = (DfbsEntrPermit) session.getAttribute("UPDATE_PERMIT");
        String emailTo = request.getParameter("emailTo");
        if (emailTo.trim().length() > 5)
        {
        sendEmailDenial(request,emailTo,permit,owner,pDAO,contacts);
        }permitForm.setProperties(permit);
        setOptions(request,dfbsUtilityDAO);
        session.setAttribute("UPDATE_PERMIT",permit);
        session.setAttribute("PERMIT_EMAIL_SENT","Y");
        return mapping.findForward("updatePermit");
        }
         else if (method.equals("showNewPermits")) 
        {
      //  DfbsEntrPermit permit = (DfbsEntrPermit) session.getAttribute("UPDATE_PERMIT");
        List newPermits = pDAO.selectNewPermits(oDAO);
        List newSpecials = sDAO.selectNewSpecials();
        session.setAttribute("PERMITS_NEW",newPermits);
        session.setAttribute("SPECIALS_NEW",newSpecials);
       //  permitForm.setProperties(permit);
         setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("updatePermit");
        }
         else if (method.equals("emailBatchList")) 
         {  
              List ownerList = pDAO.selectOwnersListEmail();
                 Iterator i = ownerList.iterator();
             while(i.hasNext())
              {
                DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
                 String emailContact = pDAO.selectContactEmail(permit.getOwnerId()); 
                 String emailOwner = pDAO.selectOwnerEmail(permit.getOwnerId()); 
                 if (emailOwner !=null || emailContact !=null)
                 { 
                  sendEmailReminder(permit,emailContact,emailOwner,contacts);
                  pDAO.updatePermitEmailDate(permit);
                  }
              }
                return mapping.findForward("start");
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
  
   protected static boolean validate(DfbsEntrPermit permit,DfbsEntrPermitForm errorForm,DfbsEntrOwnerDAO oDAO,HttpSession session) throws Exception
  {
    boolean noError = true;
   if(permit.isNew() == false) {
    if(permit.getSpecialsCount() == 0 && (permit.getStatus().trim().equals("VALID") || permit.getStatus().trim().equals("PENDING") )) 
    {
       noError = false;
    } }
   
    if(permit.getStreet1() == null || permit.getStreet1().trim().equals("")  ) 
    { 
      errorForm.setStreet1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setStreet1("");
    }
     if(permit.getNotes() !=null && permit.getNotes().length() >1000  ) 
    { 
      errorForm.setNotes("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setNotes("");
    }
    if(permit.getCity() == null || permit.getCity().trim().equals("") ) 
    {
      errorForm.setCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCity("");
    }
     if(permit.getState() == null || permit.getState().trim().equals("") ) 
    { 
      errorForm.setState("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setState("");
    }
    
    
    
    if(permit.getZip() == null || permit.getZip().trim().equals("") ) 
    { 
      errorForm.setZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setZip("");
    }
    
    if((permit.getCounty() == null || permit.getCounty().trim().equals("") || permit.getCounty().trim().equals("-----")) ) 
    {
      errorForm.setCounty("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCounty("");
    }
     if(( permit.getContactPerson() == null || permit.getContactPerson().trim().equals("") )
     && ((permit.getStatus().trim().equals("EXPIRED")) ||(permit.getStatus().trim().equals("EXPIRES SOON")) ))
    {
      errorForm.setContactPerson("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContactPerson("");
    }
    if ((permit.getPhoneNumber() == null || permit.getPhoneNumber().trim().equals("") ||
       permit.getPhoneNumber().indexOf('-') > 0 || permit.getPhoneNumber().indexOf(')') > 0 ||
       permit.getPhoneNumber().indexOf('(') > 0)  
    && ((permit.getStatus().trim().equals("EXPIRED")) ||(permit.getStatus().trim().equals("EXPIRES SOON"))))
    { 
      errorForm.setPhoneNumber("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPhoneNumber("");
    }
   
      if(permit.getActualLocation() == null || permit.getActualLocation().trim().equals("") ) 
    { 
      errorForm.setActualLocation("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setActualLocation("");
    }
    
    if(permit.getFacilityDescription() == null || permit.getFacilityDescription().trim().equals("") ) 
    {
      errorForm.setFacilityDescription("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setFacilityDescription("");
    }
  
    if(permit.getIntendedOccupantLoad() == null || permit.getIntendedOccupantLoad().trim().equals("") ) 
    { 
      errorForm.setIntendedOccupantLoad("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setIntendedOccupantLoad("");
    }
    
     if(permit.getFeeExempt() == null || permit.getFeeExempt().trim().equals("") ) 
    { 
      errorForm.setFeeExempt("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setFeeExempt("");
    } 
    // to verify file upload
     String fileName=oDAO.getUploadFileName(permit.getApplicationKey(),"AEPermit");
     if(fileName.equals("NoName") &&(permit.getStatus().trim().equals("EXPIRED")  ||
     permit.getStatus().trim().equals("EXPIRES SOON")|| permit.getStatus().trim().equals("NEW"))) 
    { 
      errorForm.setNoMap("ERROR");
      permit.setNoMap("ERROR");
        session.setAttribute("FILE_UPLOAD","FALSE");
    } 
    else
    {
      errorForm.setNoMap("");
      session.setAttribute("FILE_UPLOAD","TRUE");   
      permit.setNoMap("uploaded:" + fileName);
    }  
        return noError;
  
  
  }
 
  
  private static void addPermitsToRenew(HttpServletRequest request,DfbsOwner owner,DfbsOwner ownerApplication,DfbsEntrPermitDAO pDAO) throws Exception
  {
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    while(i.hasNext())
    {
      DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
      StringBuffer sb = new StringBuffer("PERMIT");
      sb.append(permit.getPermitNumber());
      String param = request.getParameter(sb.toString());
      if(param != null) 
      {
       if( permit.getStatus().equals("EXPIRES SOON")||permit.getStatus().equals("EXPIRED"))
       {
       permit.setRenewStatus("Renew");
       }
        ownerApplication.addPermit(permit);
        pDAO.computeFees(owner,ownerApplication);
      }
    }
    
  }
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("DFBS_STATE_OPTIONS",states);
 
 
    List counties = uDAO.getOptions(DfbsEntrSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
    
    List occupancy = uDAO.getOptions(DfbsEntrSQL.SELECT_OCCUPANCY_OPTIONS);
    request.setAttribute("DFBS_OCCUPANCY_OPTIONS",occupancy);
 }
 
  private static void sendEmailReminder( DfbsEntrPermit permit,String contactEmail,String ownerEmail,ApplicationContacts contacts) throws Exception
  {
          try {
         
            HsMailer mail = new HsMailer();
             String[] emailTo = {"cclouse@dhs.in.gov"};
          if (contactEmail != null && contactEmail.length() >5)
           {
             String[] emailTo1 = {contactEmail,"cclouse@dhs.in.gov"};
              emailTo =emailTo1;
              if (ownerEmail != null && ownerEmail.length() >5)
              {
                String[] emailTo2 = {contactEmail,ownerEmail};
                emailTo =emailTo2;
              }
           }
           else
           {   
         if (ownerEmail != null && ownerEmail.length() >5)
              { 
                String[] emailTo2 = {ownerEmail,"cclouse@dhs.in.gov"};
                emailTo =emailTo2;
               }
           } 
               
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"cclouse@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("This is a email reminder for renewal of your IDHS Annual Entertainment Permit.");
            sb.append(" Your Annual permit is going to expire shortly. The details of the permit are:  ");
            sb.append("\n\r");
            sb.append(" Identification Number: "+permit.getPermitNumber());
             sb.append("\n\r");
            sb.append(" Name "+permit.getActualLocation());
            sb.append("\n\r");
            sb.append(" Address "+permit.getStreet1());
            sb.append("\n\r");
             sb.append(" City: "+permit.getCity());
             sb.append(" State: "+permit.getState());
            sb.append(" Zip: "+permit.getZip());
            sb.append("\n\r");
            sb.append(" County: "+permit.getCounty());
              sb.append("\n\r");
            sb.append(" Phone: "+permit.getPhoneNumber());
            sb.append(" Contact: "+permit.getContactPerson());
            sb.append("\n\r");
            sb.append("Visit IDHS website at https://oas.dhs.in.gov/dfbs/aepermits/start.do to renew your permit or apply for new AE permits");
            sb.append("\n\r");
             sb.append(" If you have questions or concerns about the email contact cclouse@dhs.in.gov");
           sb.append("\n\r");
             StringBuffer sub = new StringBuffer(" Fire Entertainment Permit Renewal Reminder ");
            mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            
            } 
            catch(Exception e) 
            {
              HsMailer mail1 = new HsMailer();
            mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," permit renewal email  error","dfbspermitAction"+permit.getPermitNumber()+":"+contactEmail+":"+ownerEmail);
            mail1.sendAndClose();
            }
  }
 
 private static void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsEntrPermit permit,
                          DfbsOwner owner,DfbsEntrPermitDAO pDAO,ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String inspSupEmail =pDAO.findInspSupEmail(inspEmail);
              String[] emailTo = {inspEmail,inspSupEmail,contacts.getContact1Email(),contacts.getContact2Email()};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
           //String[] emailTo ={"nnimmagadda@dhs.in.gov"};
        //   String currentYear = pDAO.selectCurrentYear();
         //  int charPosition =inspEmail.indexOf("@");
         //  String userId= inspEmail.substring(0,charPosition);
          // int inspectorId =pDAO.selectInspector(inspEmail);
          
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/aepermits/start.do").append("\n\r");
             sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
              sb.append("payment made by credit card, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+permit.getPermitNumber()).append("\n\r");
             StringBuffer sub = new StringBuffer();
            sub.append(" From fire entertainment inspection ready alert ");
            
            mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email inspector check out",inspEmail);
            mail.sendAndClose();
            }
  }
  private static void sendEmailDenial(HttpServletRequest request,String inspEmail,DfbsEntrPermit permit,
                           DfbsOwner owner,DfbsEntrPermitDAO pDAO,ApplicationContacts contacts) throws Exception
   {
           try {
              
             HsMailer mail = new HsMailer();
             String[] emailTo = {inspEmail,contacts.getContact1Email(),contacts.getContact2Email()};
             String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            //String[] emailTo ={"nnimmagadda@dhs.in.gov"};
          
             StringBuffer sb = new StringBuffer();
             sb.append(permit.getPermitNumber() + ": is denied for the follwing reason(s)").append("\n\r");
             sb.append("Reasons :"+ permit.getNotes()).append("\n\r");
             
             StringBuffer sub = new StringBuffer();
             sub.append(" From fire entertainment permit application is denied ");
             
             mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
             mail.sendAndClose();
             } 
             catch(Exception e) 
             {
               HsMailer mail = new HsMailer();
             mail.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email inspector check out",inspEmail);
             mail.sendAndClose();
             }
   }
 private static void sendBatchEmailInsp(HttpServletRequest request,String inspEmail,DfbsEntrPermit permit,
                          DfbsEntrPermitDAO pDAO,ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {inspEmail,contacts.getContact1Email(),contacts.getContact2Email()};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          // String[] emailTo ={"wnguyen@iot.in.gov"};
           String currentYear = pDAO.selectCurrentYear();
           int charPosition =inspEmail.indexOf("@");
           String userId= inspEmail.substring(0,charPosition);
           int inspectorId =pDAO.selectInspector(inspEmail);
          
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/aepermits/start.do").append("\n\r");
             sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
              sb.append("payment made by credit card, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+permit.getPermitNumber()).append("\n\r");
             StringBuffer sub = new StringBuffer();
            sub.append(" TEST! TEST! TEST!");
            
            mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email inspector check out",inspEmail);
            mail.sendAndClose();
            }
  }
 

 
}
  


