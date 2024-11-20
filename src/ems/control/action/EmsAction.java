package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
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
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;

import main.data.*;
import main.to.*;
import main.to.ApplicationContacts;

import org.apache.struts.upload.FormFile;

import otherUsers.to.otherUsers;

public class EmsAction extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        EmsSecurityForm EmsForm = (EmsSecurityForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        EmsSecurityDetailDAO edDAO = (EmsSecurityDetailDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY_DETAIL);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
         if (method == null) 
      
        {  ApplicationContacts contacts = sDAO.setContacts("EMS_CONTACT1","EMS_CONTACT2");
      
       session.setAttribute("APPLICATION_CONTACTS",contacts); 
       String conType ="EMS_CONTACT";
       session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
         String appLocation ="/dfbs/ems/main.do";
         session.setAttribute("APPLICATION_LOCATION",appLocation); 
        String appHeading ="IDHS EMS Online Application";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
         ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
         if(security !=null && (security.getGroupLevelAll() !=null ||security.getGroupLevelEms().equals("USER")||security.getGroupLevelEms().equals("SUPERVISOR")))
         {
              return mapping.findForward("authUser");
         }
         return mapping.findForward("main");
        } 
         else if (method.equals("authUser")) 
        {
          return mapping.findForward("authUser");
        } 
         else if (method.equals("logOut")) 
        {
           session.setAttribute("OTHER_USER",null);
          return mapping.findForward("main");
        } 
         else if (method.equals("advanceSearch")) 
        {
          
          return mapping.findForward("advanceSearch");
        } 
        else if (method.equals("publicAccess")) 
        {
          
          return mapping.findForward("authUser");
        } 
      
        else if (method.equals("verifySecurity")) 
        { 
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
        } 
       
         else if (method.equals("newUser")) 
        {
          
          return mapping.findForward("newUserType");
        }   
         else if (method.equals("newUserMD")) 
        {
          session.setAttribute("NEW_USER_ACCOUNT_TYPE","USER");
          return mapping.findForward("newUser");
        }  
         else if (method.equals("newUserInst")) 
        {
          session.setAttribute("NEW_USER_ACCOUNT_TYPE","COURSE_USER");
          return mapping.findForward("newUser");
        }   
         else if (method.equals("approveUsers")) 
        {
          
          return mapping.findForward("newUser");
        }  
       
         else if (method.equals("emailPasswordPage")) 
         {
           return mapping.findForward("emailPasswordPage");
         }
         // password change
         else if (method.equals("changePassword")) 
         {
           return mapping.findForward("changePassword");
         }
         
        
          else if (method.equals("emailNewUser")) 
         { String userId = request.getParameter("userId");
           String password = request.getParameter("password");
           if (password !="")
           {
           sendEmailNewAccount(userId,password);
           EmsSecurity security = (EmsSecurity) session.getAttribute("EMS_EDIT_USER");
          EmsForm.setProperties(security);
           return mapping.findForward("updateUsers");
           }
          
         }
          
       
        else if (method.equals("updateUsers")) 
        {
          
          return mapping.findForward("updateUsers");
        }  
        
        // file upload download feature
         else if (method.equals("goToUpload")) 
         { String idNumber = request.getParameter("idNumber"); 
           String idType = request.getParameter("idType"); 
           if (idType.equals("Institution"))
                  {EmsInstitution institution = (EmsInstitution) session.getAttribute("INSTITUTION");
                    institution.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
            if (idType.equals("Provider"))
                  { 
                   EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
                    provider.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
            if (idType.equals("Hospital"))
                  {EmsHospital hospital = (EmsHospital) session.getAttribute("HOSPITAL");
                    hospital.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
           if (idType.equals("Vehicle"))
                  {EmsVehicle vehicle = (EmsVehicle) session.getAttribute("VEHICLE");
                    vehicle.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
          if (idType.equals("Course"))
                  {EmsCourse course = (EmsCourse) session.getAttribute("COURSE"); System.out.println("in course");
                    course.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); System.out.println("out course");
                  }
           if (idType.equals("Person"))
                  {EmsInstructors instructor = (EmsInstructors) session.getAttribute("EMS_INSTRUCTOR");
                    instructor.setFileList(eDAO.selectFileList(idNumber,idType,null,null));  
                  }System.out.println("5");
         return mapping.findForward("goToUpload");
         }
         
           else if (method.equals("downLoadFile")) 
         { 
          String fileName = request.getParameter("fileName");
          String fileType = request.getParameter("fileType");
          int fileId = Integer.parseInt(request.getParameter("fileId"));
          doGetFile(request,response,eDAO,fileName,fileType,fileId);
           return mapping.findForward("goToUpload");
         }
          else if (method.equals("uploadFile")) 
         { 
         
         final FormFile oneFile = EmsForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {    
            String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
              {
                 String idNumber = request.getParameter("idNumber"); 
                 String idType = request.getParameter("idType"); 
                  eDAO.uploadFile(oneFile,idNumber,otherUser.getUserLoginId(),idType);   
                 if (idType.equals("Institution"))
                  {EmsInstitution institution = (EmsInstitution) session.getAttribute("INSTITUTION");
                    institution.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
            if (idType.equals("Provider"))
                  {EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
                  String renewFlag =(String) session.getAttribute("RENEW_PROVIDER");
                  if (renewFlag !=null)
                  {
                  session.setAttribute("RENEW_PROVIDER","Complete");
                session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER",provider.getProviderName());
                session.setAttribute("RENEW_PROVIDER_UPDATE","Completed");
                 session.setAttribute("RENEW_PROVIDER_RENEW_INFORMATION","Complete");
                  session.setAttribute("RENEW_PROVIDER_VEHICLE_INFORMATION","Complete");
                 session.setAttribute("RENEW_PROVIDER_FILE_UPLOAD","Complete"); 
                  }
                    provider.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
            if (idType.equals("Hospital"))
                  {EmsHospital hospital = (EmsHospital) session.getAttribute("HOSPITAL");
                    hospital.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
           if (idType.equals("Vehicle"))
                  {EmsVehicle vehicle = (EmsVehicle) session.getAttribute("VEHICLE");
                    vehicle.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
          if (idType.equals("Course"))
                  {EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
                    course.setFileList(eDAO.selectFileList(idNumber,idType,otherUser.getUserGroup(),otherUser.getUserGroup())); 
                     session.setAttribute("NEW_COURSE_FILE_UPLOAD","Uploaded");
                     session.setAttribute("NEW_COURSE","Completed");
                    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                     redirectUrl.append(request.getContextPath()).append("/ems/course.do?method=courseDetail&courseId=" +course.getCourseId());
                     response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                     return(null);
                  }
                   if (idType.equals("Person"))
                  {EmsInstructors instructor = (EmsInstructors) session.getAttribute("EMS_INSTRUCTOR");
                    instructor.setFileList(eDAO.selectFileList(idNumber,idType,null,null)); 
                  }
                session.setAttribute("FILE_EXTENSION","");   
            }
             else
            {
              session.setAttribute("FILE_EXTENSION","ERROR");
            }
            }
           
           return mapping.findForward("goToUpload");
         }
    // add user capabilities USER_SEC_FLAG
    
        else if (method.equals("showEmsUserList")) 
         {  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do?method=userListEms");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
          }
    else if (method.equals("goProviderUser")) 
     { session.setAttribute("USER_SEC_FLAG","Yes");
      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
      redirectUrl.append(request.getContextPath()).append("/ems/provider.do?method=providerList");
      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;
      }
      else if (method.equals("goHospitalUser")) 
     { session.setAttribute("USER_SEC_FLAG","Yes");
      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
      redirectUrl.append(request.getContextPath()).append("/ems/hospital.do?method=hospitalList");
      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;
      }
      else if (method.equals("goInstitutionUser")) 
     { session.setAttribute("USER_SEC_FLAG","Yes");
      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
      redirectUrl.append(request.getContextPath()).append("/ems/institution.do?method=institutionList");
      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;
      }
   
     
        else if (method.equals("logOn")) 
        {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/idhs/dfbs/main/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
    protected static boolean validate(EmsSecurity security,EmsSecurityForm errorForm) throws Exception
  {
    boolean noError = true;
    
   
    
    if(security.getUserFirstName() == null || security.getUserFirstName().trim().equals("") ) 
    { System.out.println("1");
      errorForm.setUserFirstName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setUserFirstName("");
    }
   
    if(security.getUserLastName() == null || security.getUserLastName().trim().equals("") ) 
    { 
      errorForm.setUserLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setUserLastName("");
    }
   if(security.getUserPhone() == null || security.getUserPhone().trim().equals("") ) 
    { 
      errorForm.setUserPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setUserPhone("");
    }
    if(security.getUserId() == null || security.getUserId().trim().equals("") ) 
    { 
      errorForm.setUserId("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setUserId("");
    }
    if(security.getUserTitle() == null || security.getUserTitle().trim().equals("") ) 
    { 
      errorForm.setUserTitle("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setUserTitle("");
    }
    // for administrator
    if (security.getCurrentUserLevel() != null)
    {
    if (security.getCurrentUserLevel().trim().equals("ADMINISTRATOR"))
      {
        if(security.getUserLevel() == null || security.getUserLevel().trim().equals("") )
        { 
          errorForm.setUserLevel("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setUserLevel("");
        }
        if(security.getUserStatus() == null || security.getUserStatus().trim().equals("") )
        { 
          errorForm.setUserStatus("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setUserStatus("");
        }
      }
    }
    return noError;
  }
  
 
  private static void sendEmail(String email,String passWord) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {email};
            String[] bccTo = {"nnimmagadda@dhs.in.gov","jfox@dhs.in.gov","jabraham@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS Password Recovery");
            sb.append("\r\n user id=");
            sb.append(email);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS password recovery service ");
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS password recovery service error email ","EmsAction");
            mail1.sendAndClose();            }
  }
  
 protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,EmsSecurityForm errorForm) throws Exception
  {
    boolean noError = true;
    /* string comparision
     * if (string1.equals(string2))
   if (string1.compareTo(string2) < 0)
   if (string1.equalsIgnoreCase(string2))
     */
    if(UserPassword.compareTo(UserPasswordOld) <0 )
    {
    errorForm.setUserPassword("ERROR_NOTOLDPASSWORD");
     noError = false;
      }
    else
    {  if(UserPassword.equals(UserPasswordNew) ) 
      {
      errorForm.setUserPassword("ERROR_SAMEPASSWORD");
      noError = false;
       }   
      else
      { 
        errorForm.setUserPassword("");
      } 
    }
    
    if(UserPasswordNew != UserPasswordNewRetype ) 
    { 
      errorForm.setRetypeNewPassword("ERROR_NOTEQUAL");
      noError = false;
    } 
     else
    {
      errorForm.setRetypeNewPassword("");
    }
    if(UserPasswordNew.length() < 6 ) 
    { 
      errorForm.setNewPassword("ERROR_LENGTH");
      noError = false;
    } 
     else
    {
      errorForm.setNewPassword("");
    }
   System.out.println("in validate password3");
    return noError;
  }
  
 

  
  protected static void setFilterSession(HttpServletRequest request,EmsSecurityForm filterForm) throws Exception
  {
    HttpSession session = request.getSession();
    String filter = request.getParameter("filter");
    
    HsFilter filterSession = new HsFilter();
    filterSession.setType(filter);
    
     if(filter == null ) {
      filterSession.setType("byLetter");
      filterSession.setValue("R");
    } 
    else if(filter.equals("byLetter"))  
    { 
       String letter = request.getParameter("letter");
       filterSession.setValue(letter);
     }
     
     session.setAttribute("USER_LIST_FILTER",filterSession);
    
  }
  
 

  private static void sendEmailNewAccount(String email,String passWord) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {email};
            String[] bccTo = {"nnimmagadda@dhs.in.gov","jfox@dhs.in.gov","rachelmiller@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS New Account Information");
            sb.append("\r\n user id= ");
            sb.append(email);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS new account set up service ");
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
             HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS new account set up service error email ","EmsAction");
            mail1.sendAndClose();            }
  }
  
 public void doGetFile(HttpServletRequest request, HttpServletResponse response,EmsSecurityDAO oDAO,String fileName,String fileType,int fileId) throws Exception 
 {  
   try {response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
    java.io.OutputStream out = response.getOutputStream(); 
    int lenFile = oDAO.downLoad(out,fileId);
     out.flush(); 
    out.close();
   } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
            }
    

} 
 private static void NewAccountRequestReceived(String email) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
          String[] emailTo = {email,"jfox@dhs.in.gov","rachelmiller@dhs.in.gov"};
          // String[] emailTo = {"rstump@dhs.in.gov","ssteinhilber@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS New Account Requested");
            sb.append("\r\n user id="+ email);
           
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS new account requested ");
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
             HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS new account requested error email ","EmsAction");
            mail1.sendAndClose();
            }
  }
  
 
}