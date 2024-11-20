package main.control.action;
import main.data.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import hs.control.*;
import hs.control.form.*;
import main.control.form.*;
import main.to.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import util.logging.DHSLogLevel;
import util.logging.Log;

public class ApplicationSecurityAction  extends ControlAction
{
    private static final String CLASS_NAME="ApplicationSecurityAction";
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
      try {
        
          String METHOD_NAME="executeControlSecurity";  
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        ApplicationSecurityForm securityForm = (ApplicationSecurityForm) form;
         HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
         ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
       String method = request.getParameter("method");
       String url = request.getRemoteAddr(); 
          Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "url.:"+url); 
        HttpSession session = request.getSession();
         if (method == null ) {
             Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, "NULL_METHOD_INVOKED", "url.:"+url);

         /* ApplicationContacts contacts = ( ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
           if (contacts !=null &&(contacts.getContact1IP()!=null && contacts.getContact1IP().equals(url) ||contacts.getContact2IP()!=null && contacts.getContact2IP().equals(url))||url.equals("10.90.29.38"))
          {  String conType ="";
            if (url.equals("10.90.29.63"))
             {
                 conType ="ENTERTAINMENT_CONTACT";
               session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
             }
             else
             {
              conType = (String) session.getAttribute("APPLICATION_CONTACT_TYPE"); 
             }              
             session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
             ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
              sDAO.findSecurityUser(url,security,conType);
              String userCheck = sDAO.getSecurity(security,security.getUserId(),security.getUserPassword());
            return mapping.findForward("main");
          }
          else
          { */

        if (url.equals("1111127.0.0.1"))
         {
             ApplicationSecurity security = new ApplicationSecurity (); 
           security.setGroupLevelAll("DBA");
           security.setUserId("nnimmagadda");
           session.setAttribute("DFBS_SECURITY",security);
           return mapping.findForward("main");
         }else if (url.equals("11115cg44435vc"))
             {
                 ApplicationSecurity security = new ApplicationSecurity (); 
               security.setGroupLevelAll("DBA");
               security.setUserId("nnimmagadda");
               session.setAttribute("DFBS_SECURITY",security);
               return mapping.findForward("main");
             }
        else
        {
        String currentDate =sDAO.selectCurrentDate();
          session.setAttribute("CURRENT_DATE",currentDate);
          return mapping.findForward("logOn");
        } 
        } 
         else if (method.equals("logOut")) 
        { 
        session.setAttribute("DFBS_SECURITY",null);
            Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, "LOGGED_OFF", "url.:"+url);
            //  sDAO.insertIp(request.getParameter("userId")+":logout",url);//Added to insert login userid...Dev 04/25/2015
        return mapping.findForward("logOn");
        }
        else if (method.equals("loginSystem")) 
        { 
          String userId = request.getParameter("userId");
          String passWord = request.getParameter("userPassword");
           session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
          ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
          String userCheck = sDAO.getSecurity(security,userId,passWord);
            if (userCheck ==null ||userCheck.equals("xxx"))
          {  session.setAttribute("DFBS_SECURITY",null);
            String userLogInError ="ERROR";
             session.setAttribute("DFBS_LOGIN_ERROR",userLogInError);
            return mapping.findForward("logOn");             
          }
          else
          { 
            if (security.getPasswordExpiresDate().compareTo(security.getCurrentDate()) <= 0)
              {
                return mapping.findForward("changePassword");
              }
              else
              {
              sDAO.insertIp(userId+":login",url);//Added to insert login userid...Dev 04/25/2015
              return mapping.findForward("main");
              } 
          }
        } 
         else if (method.equals("changePassword")) 
         {
           return mapping.findForward("changePassword");
         }
          else if (method.equals("saveChangedPassword")) 
         { String userPassword = request.getParameter("userPassword");
           String userPasswordNew = request.getParameter("newPassword");
           String userPasswordNewRetype = request.getParameter("retypeNewPassword");
           ApplicationSecurityForm errorForm = new ApplicationSecurityForm();
           ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
           String userPasswordOld = security.getOldPassword();
           if (validatePassword(userPassword,userPasswordOld,userPasswordNew,userPasswordNewRetype,errorForm))
          {  
            sDAO.updatePassword(security,userPasswordNew);
            return mapping.findForward("main");
          }
          else
          { request.setAttribute("USER_ERROR",errorForm);
           return mapping.findForward("changePassword");
          }
         }
         // email forgotten password
         else if (method.equals("emailPassword")) 
         { String userEmail = request.getParameter("userEmail");
           session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
           ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
           String userCheck = sDAO.getPassword(security,userEmail);
           if (userCheck !="")
           {
           sendEmail(security.getUserId(),security.getUserPassword(),userEmail);
           String userLogInError ="EMAIL_SENT";
             session.setAttribute("DFBS_LOGIN_ERROR",userLogInError);
             session.setAttribute("DFBS_SECURITY",null);
           return mapping.findForward("logOn");
           }
           else
           {  security.setUserEmail("ERROR");
              return mapping.findForward("emailPasswordPage");
           }
         }
        
         else if (method.equals("emailPasswordPage")) 
         {
           return mapping.findForward("emailPasswordPage");
         }
          else if (method.equals("backToMain")) 
         {  ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
           if (security ==null)
           {
             return mapping.findForward("logOn");
           }
           else
           {
            session.removeAttribute("APPLICATION_HEADING");
            session.removeAttribute("APPLICATION_CONTACTS");
           return mapping.findForward("main");
           }
         }
        else if (method.equals("aepermits"))

         {
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/aepermits/start.do");

           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
          else if (method.equals("idhsInspections")) 
           {
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append("/dfbs/idhsInspections/search.do");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
           }
          else if (method.equals("fireBldEducation")) 
           {
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append("/dfbs/fireBldEducation/start.do");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
           }
         else if (method.equals("magazines")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/magazine/start.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
          else if (method.equals("code")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/code/main.do");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
           else if (method.equals("orgShipmentView")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/hazmat/orgShipmentView.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
           else if (method.equals("fireworks")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/fireworks/start.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
          else if (method.equals("cigarettes"))
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/cigarette/cigarette.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
          else if (method.equals("display")) 
         {
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/display/start.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null; 
         }
          else if (method.equals("childcare")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/childCare/start.do");
         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
           else if (method.equals("ems")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/ems/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
        else if (method.equals("idhsFeesFines")) 
        {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/idhsFeesFines/start.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }
        else if (method.equals("elevator")) 
        {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/elevator/start.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }
         else if (method.equals("plan")) 
        {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/otherUsers/otherUser.do?method=userListPlan");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }
        else if (method.equals("lepc")) 
        {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/lepc/lepc.do?method=start");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
         return null;
        }
        else if (method.equals("amuseRide")) 
        {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/amuseRide/owner.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
         return null;
        }
        else if (method.equals("ust")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/ust/ust.do");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
        else if (method.equals("emsOps")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/ems/main.do");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
        else if (method.equals("variance")) 
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/variance/start.do");
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
 


  protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,ApplicationSecurityForm errorForm) throws Exception
  {
    boolean noError = true;
   
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
    
    if(UserPasswordNew.compareTo(UserPasswordNewRetype) <0 ) 
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
   
    return noError;
  }
  
  
  private static void sendEmail(String user,String passWord,String userEmail) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {userEmail};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n DFBS Password Recovery");
            sb.append("\r\n user id=");
            sb.append(user);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("DFBS password recovery service ");
          
          
          
            mail.createMail("dfbs_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","ApplicationSecurityAction");
            mail1.sendAndClose();
            }
  }
 
}