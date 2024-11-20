package planReview.control.action;



import planReview.control.form.*;
import planReview.to.*;
import planReview.data.*;
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

import magazine.data.DfbsSQL;

public class PlanLBOUsersAction  extends ControlAction
   {
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
         DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        PlanLBOUsersForm planUserForm = (PlanLBOUsersForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          PlanLBOUsersDAO pDAO = ( PlanLBOUsersDAO) dmapNew.getHsDAO(DfbsDataMap2.PLAN_LBO_USER);
         if (method == null ) 
        {
          return mapping.findForward("loginUser");
        } 
         
       
        else if (method.equals("loginSystem")) 
        { 
          
          String userLoginId = request.getParameter("userLoginId");
          String passWord = request.getParameter("userPassword");
          session.setAttribute("PLAN_LBO_USER",new PlanLBOUsers ());
          PlanLBOUsers planUser = pDAO.selectPlanLBOUser(userLoginId,passWord);
          if (planUser.getUserLoginId() !=null && planUser.getUserLoginId().length() >0)
          {
          session.setAttribute("PLAN_LBO_USER",planUser);
              if (planUser.getUserPasswordCheck() >0)
              {
              session.setAttribute("USER_ERROR","");
              List projectList =pDAO.selectProjectList(planUser.getUserLoginId(), 90, 0);
              session.setAttribute("PLAN_REVIEW_LBO_PLANS",projectList);
              return mapping.findForward("viewPlans"); 
              }
              else
              { 
                return mapping.findForward("updatePassword"); 
              }
              
          }
          else
          { session.setAttribute("USER_ERROR","user id or password does not match, try again or contact nnimmagadda@dhs.in.gov with details");
            return mapping.findForward("loginUser");
          }
        } 
          else if (method.equals("saveChangedPassword")) 
         { 
           PlanLBOUsers planUser = (PlanLBOUsers) session.getAttribute("PLAN_LBO_USER");
           String userPassword = request.getParameter("userPassword");
           String userPasswordNew = request.getParameter("newPassword");
           String userPasswordNewRetype = request.getParameter("retypeNewPassword");
           PlanLBOUsersForm errorForm = new PlanLBOUsersForm();
           String userPasswordOld = planUser.getUserPassword();
           if (validatePassword(userPassword,userPasswordOld,userPasswordNew,userPasswordNewRetype,errorForm))
          {  
              session.setAttribute("USER_ERROR","");
              planUser.setUserPassword(userPasswordNew);
              pDAO.updateplanUserPassword(planUser);
             List projectList =pDAO.selectProjectList(planUser.getUserLoginId(), 90, 0);
              session.setAttribute("PLAN_REVIEW_LBO_PLANS",projectList);
              return mapping.findForward("viewPlans"); 
            }
          else
          {  request.setAttribute("MAG_USER_ERROR",errorForm);
           return mapping.findForward("updatePassword");
          }
         }
         // email forgotten password
         else if (method.equals("emailPassword")) 
         { String userEmail = request.getParameter("userLoginId");
           PlanLBOUsers planUser= pDAO.emailPlanUserPassword(userEmail);
           if (planUser !=null && planUser.getUserPassword() !=null && planUser.getUserPassword().length() >0 )
           {
           sendEmail(planUser.getUserPassword(),planUser.getUserLoginId());
           session.setAttribute("USER_ERROR","Password sent to email address!");
           return mapping.findForward("loginUser");
           
           }
           else
           {  session.setAttribute("USER_ERROR","No user match for the email found contact nnimmagadda@dhs.in.gov with details !");
              return mapping.findForward("loginUser");
           }
         }
           else if (method.equals("searchLBOProject")) 
            { 
             int idNumber = Integer.parseInt(request.getParameter("idNumber"));
             PlanLBOUsers planUser = (PlanLBOUsers) session.getAttribute("PLAN_LBO_USER");
             List projectList =pDAO.selectProjectListProject(planUser.getUserLoginId(),idNumber );
              session.setAttribute("PLAN_REVIEW_LBO_PLANS",projectList);
              return mapping.findForward("viewPlans"); 
            }
          else if (method.equals("startOver")) 
            { 
             PlanLBOUsers planUser = (PlanLBOUsers) session.getAttribute("PLAN_LBO_USER");
              List projectList =pDAO.selectProjectList(planUser.getUserLoginId(), 90, 0);
              session.setAttribute("PLAN_REVIEW_LBO_PLANS",projectList);
              return mapping.findForward("viewPlans"); 
            }
          else if (method.equals("updatePassword")) 
        {
           return mapping.findForward("updatePassword"); 
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
        
   protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,PlanLBOUsersForm errorForm) throws Exception
  {
    boolean noError = true;
    errorForm.setUserPassword("");
     if(UserPassword.toUpperCase().compareTo(UserPasswordOld.toUpperCase()) <0 )
    {
    errorForm.setUserPassword("ERROR_NOTOLDPASSWORD");
     noError = false;
      }
     if(UserPassword.toUpperCase().compareTo(UserPasswordNew.toUpperCase()) == 0 ) 
      {
      errorForm.setUserPassword("ERROR_SAMEPASSWORD");
      noError = false;
       }   
     
    
    if(UserPasswordNew.toUpperCase().compareTo(UserPasswordNewRetype.toUpperCase()) <0  ) 
    { 
      errorForm.setUserPassword("ERROR_NOTEQUAL");
      noError = false;
    } 
    
    if(UserPasswordNew.length() < 6 ) 
    { 
      errorForm.setUserPassword("ERROR_LENGTH");
      noError = false;
    } 
    
    return noError;
  }
  
  
  private static void sendEmail(String passWord,String userEmail) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {userEmail};
           // String[] emailTo ={"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n Plan LBO User Password Recovery");
            sb.append("\r\n user id=");
            sb.append(userEmail);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("Plan LBO User password recovery service ");
          
          
          
            mail.createMail("planReview_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("planReview_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Plan Review User password recovery service error email ","PlanLBOUserAction"+userEmail);
            mail1.sendAndClose();
            }
  }
 

  }
 


  
  
  
 
 
 

