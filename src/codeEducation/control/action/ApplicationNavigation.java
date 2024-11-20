package codeEducation.control.action;
import main.data.*;
import main.to.*;
import codeEducation.to.*;
import codeEducation.data.*;
import codeEducation.control.form.*;
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


public class ApplicationNavigation  extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
         
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          CodeEducationUserDAO userDAO = (CodeEducationUserDAO) dmap2.getHsDAO(DfbsDataMap.CODE_USER);    
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
           ApplicationContacts contacts = sDAO.setContacts("CODE_EDU_CONTACT1","CODE_EDU_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
          
        if (method == null ) 
        {   String conType ="CODE_EDU_CONTACT";
            session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
            String appLocation ="/dfbs/codeEducation/start.do";
             session.setAttribute("APPLICATION_LOCATION",appLocation); 
             String appHeading ="Code Education Online Application (2010.2 version)";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
            if (security !=null)
         {
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/codeEducation/course.do?method=courseAdmin");
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;

         }
         else {
             return mapping.findForward("start");
         }
          
        } 
       
         
          else if (method.equals("loginIDHS")) 
          {  
              return mapping.findForward("loginIDHS");
           
          } 
          else if (method.equals("loginState")) 
          { 
            
              return mapping.findForward("loginState");
           
          } 
          else if (method.equals("loginOthers")) 
          { 
            
              return mapping.findForward("loginOthers");
           
          } 
          else if (method.equals("registerState")) 
          { 
             
              return mapping.findForward("registerState");
           
          } 
          else if (method.equals("registerIDHS")) 
          {             String oldPwdSQL = CodeEducationSQL.CODE_EDU_CHANGE_PASSWORD_DFBS;
            session.setAttribute("DFBS_SECURITY_OLD_PASSWORD",oldPwdSQL);
              userDAO.updateUserPasswords();
              return mapping.findForward("registerIDHS");
           
          } 
          else if (method.equals("registerOthers")) 
          { 
            
              return mapping.findForward("registerOthers");
           
          } 
          else if (method.equals("addNewUser")) 
          { 
            
              return mapping.findForward("addNewUser");
           
          } 
        
          else if (method.equals("courseAdmin")) 
          {  
             String url = request.getRemoteAddr();
             //  ||url.equals("10.90.27.52")   
        if ((contacts.getContact1IP() !=null && contacts.getContact1IP().equals(url)) ||(contacts.getContact2IP() !=null && contacts.getContact2IP().equals(url))||url.equals("10.90.29.38"))
        {  session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
                 ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
                 sDAO.findSecurityUser(url,security,"CODE_EDU_CONTACT");
                 StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                  redirectUrl.append(request.getContextPath()).append("/codeEducation/course.do?method=courseAdmin");
                  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;

              }
              else
              { 
                  return mapping.findForward("login");
              }
           
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
         //   mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","ApplicationSecurityAction");
            mail1.sendAndClose();
            }
  }
 

   
        
      
  }
 


  
  
  
 
 
 
