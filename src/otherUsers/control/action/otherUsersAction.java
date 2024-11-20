package otherUsers.control.action;


import otherUsers.control.form.*;
import otherUsers.to.*;
import otherUsers.data.*;
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

public class otherUsersAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context =   this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        otherUsersForm otherUserForm = (otherUsersForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        otherUsersDAO uDAO = (otherUsersDAO) dmapNew.getHsDAO(DfbsDataMap2.OTHER_USER);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        ApplicationContacts contacts = sDAO.setContacts("FIRE_OTHER_CONTACT1","FIRE_OTHER_CONTACT2");  
         if (method == null ) 
        {
          return mapping.findForward("loginUser");
        } 
        else if (method.equals("logOutLepc")) 
        {  session.setAttribute("OTHER_USER",null);
          return mapping.findForward("loginUser");
        }
         
         else if (method.equals("selectCounty")) 
        { setOptions(request,dfbsUtilityDAO,"");
          session.setAttribute("START_STATUS","");
          return mapping.findForward("start"); 
        }
           else if (method.equals("selectSaveCounty")) 
        { 
          otherUsers otherUser = new otherUsers();
          otherUser.setUserCounty(otherUserForm.getUserCounty());
          setOptions(request,dfbsUtilityDAO,otherUserForm.getUserCounty());
          session.setAttribute("START_OTHER_USER",otherUser);
          session.setAttribute("START_STATUS","COUNTY");
           session.setAttribute("START_COUNTY",otherUserForm.getUserCounty());
          return mapping.findForward("start"); 
        }
             else if (method.equals("validateUserApp")) 
        { 
          String fcEmail = request.getParameter("userLoginId");
          String fdId = request.getParameter("FDId");
          String accKey = request.getParameter("userLastName");
          int verifyAccess = uDAO.validateNewUserRequest(fdId, fcEmail, accKey);
          if (verifyAccess >0) {
          String userCounty = (String)   session.getAttribute("START_COUNTY");                 
          setOptions(request,dfbsUtilityDAO,userCounty);
          otherUserForm.setUserLoginId("");
          otherUserForm.setUserLastName("");
          otherUserForm.setUserCounty(userCounty);
          otherUserForm.setFDId(fdId);
           session.setAttribute("NEW_USER","Y");
          return mapping.findForward("addNewUser"); 
          }
          else {
              String userCounty = (String)   session.getAttribute("START_COUNTY");
            otherUsers otherUser = new otherUsers();
          setOptions(request,dfbsUtilityDAO,userCounty);
          session.setAttribute("START_OTHER_USER",otherUser);
          session.setAttribute("START_STATUS","COUNTY");
           return mapping.findForward("start"); 
          }
         
        }
         else if (method.equals("addNewUser")) 
        { otherUsers otherUser = (otherUsers) session.getAttribute("START_OTHER_USER");
          setOptions(request,dfbsUtilityDAO,otherUserForm.getUserCounty());
          otherUserForm.setUserCounty(otherUser.getUserCounty());
          return mapping.findForward("addNewUser"); 
        }
        else if (method.equals("addNewPlanUser")) 
        { setOptions(request,dfbsUtilityDAO,null);
         return mapping.findForward("addNewPlanUser"); 
        }
        else if (method.equals("addNewEmsUser")) 
        { setOptions(request,dfbsUtilityDAO,null);
          String detNumber = request.getParameter("detNumber");
          String personType = request.getParameter("personType");
          session.setAttribute("EMS_USER_DET_NUMBER",detNumber);
          session.setAttribute("EMS_USER_PERSON_TYPE",personType);
         return mapping.findForward("addNewEmsUser"); 
         
        }
        else if (method.equals("saveEmsUser")) 
        { 
          otherUsers otherUser = otherUserForm.getOtherUsers();
          if (otherUser.getUserId()==0)
          {
          otherUser.setUserPassword(otherUser.getUserFirstName().substring(0, 2)+otherUser.getUserLastName().substring(0,3)+"1");
           String detNumber =(String) session.getAttribute("EMS_USER_DET_NUMBER");
            String personType =(String) session.getAttribute("EMS_USER_PERSON_TYPE");
          uDAO.insertOtherUser(otherUser,"A","EMS_OPS",Integer.parseInt(detNumber), personType);
            NewEmsAccountRequestReceived(otherUser);
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/ems/main.do");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null; 
          }
          else {
            uDAO.updateOtherUser(otherUser);
            setListLepc(request,uDAO,otherUserForm);
             setFilterOptions(request,dfbsUtilityDAO);
            return mapping.findForward("userListEms");
          }
         
        }
        else if (method.equals("updateEmsUser")) 
        {  int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
          otherUserForm.setProperties(otherUser);
         setOptions(request,dfbsUtilityDAO,"");
         return mapping.findForward("addNewEmsUser"); 
        }
        else if (method.equals("updatePlanUser")) 
        {  int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
          otherUserForm.setProperties(otherUser);
         setOptions(request,dfbsUtilityDAO,"");
         return mapping.findForward("addNewPlanUser"); 
        }
         
        else if (method.equals("addNewLepcUser")) 
        {  setOptions(request,dfbsUtilityDAO,"");
          otherUserForm.setUserStatus("A");
         return mapping.findForward("lepcUser"); 
        }
        else if (method.equals("updateLepcUser")) 
        {  int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
          otherUserForm.setProperties(otherUser);
         setOptions(request,dfbsUtilityDAO,"");
         return mapping.findForward("lepcUser"); 
        }
        else if (method.equals("saveLepcUser")) 
        { 
          otherUsers otherUser = otherUserForm.getOtherUsers();
          if (otherUser.getUserId()==0)
          {
          otherUser.setUserPassword(otherUser.getUserFirstName().substring(0, 2)+otherUser.getUserLastName().substring(0,3)+"1");
          uDAO.insertOtherUser(otherUser,"A","LEPC",0,null);
          }
          else {
            uDAO.updateOtherUser(otherUser);
          }
          setListLepc(request,uDAO,otherUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userListLepc");
        }
        else if (method.equals("saveNewUser")) 
        {
          otherUsers otherUser = otherUserForm.getOtherUsers();
          int userCheck = uDAO.selectOtherUserLoginId(otherUser.getUserLoginId());
           if (userCheck ==0 )
          {
             session.setAttribute("INSERT_OTHER_USER_STATUS","Y");
             List authUsersList = uDAO.selectUserListByFD(otherUser.getUserCounty(),otherUser.getFDId());
             if (authUsersList.size()>2) {
               uDAO.insertOtherUser(otherUser,"","planReview",0,null);
               session.setAttribute("INSERT_OTHER_USER_WARNING","Y");
               sendEmailNewUser(otherUser,authUsersList.size(),contacts);
            }
             else
              {otherUser.setUserPassword(otherUser.getUserFirstName().substring(0, 3)+otherUser.getUserLastName().substring(0,3)+"1");
                uDAO.insertOtherUser(otherUser,"A","planReview",0,null);
                 sendEmailNewUseAccountInfo(otherUser,contacts,"New",null);
                 String fireCheifEmail=uDAO.selectChiefEmail(otherUser.getFDId());
                 if (fireCheifEmail.toUpperCase().equals(otherUser.getUserLoginId().toUpperCase()))
                 {
                 }
                else
                 {
                 sendEmailNewUseAccountInfo(otherUser,contacts,"old",fireCheifEmail);
                 }
               session.setAttribute("INSERT_OTHER_USER_WARNING","N");
             }
             session.setAttribute("AUTH_USER_LIST",authUsersList);
              setOptions(request,dfbsUtilityDAO,otherUserForm.getUserCounty());
            return mapping.findForward("addNewUser"); 
          }
          else {
             session.setAttribute("INSERT_OTHER_USER_WARNING","E");
            return mapping.findForward("addNewUser"); 
          }
        }
        else if (method.equals("savePlanUser")) 
        {  otherUsers otherUser = otherUserForm.getOtherUsers();
           if (otherUser.getUserId() >0) {
                 uDAO.updateOtherUser(otherUser);   
                  }
          else {otherUser.setUserStatus("A");
          otherUser.setUserPassword(otherUser.getUserFirstName().substring(0, 3)+otherUser.getUserLastName().substring(0,3)+"1");
          uDAO.insertOtherUser(otherUser,"","planReview",0,null);
                                        }
          setListPlan(request,uDAO,otherUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userListPlan");
        }
         else if (method.equals("authUserList")) 
        { int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
          List authUsersList = uDAO.selectUserListByFD(otherUser.getUserCounty(),otherUser.getFDId());
           session.setAttribute("AUTH_USER_LIST",authUsersList);
          return mapping.findForward("addNewUser"); 
        } 
         else if (method.equals("loginSystem")) 
        { 
          String userLoginId = request.getParameter("userLoginId");
          String passWord = request.getParameter("userPassword");
         session.setAttribute("OTHER_USER",new otherUsers ());
          otherUsers otherUser = uDAO.selectLoginOtherUser(userLoginId,passWord);
           List authUsersList = uDAO.selectUserListByFD(otherUser.getUserCounty(),otherUser.getFDId());
           session.setAttribute("AUTH_USER_LIST",authUsersList);
          if (otherUser.getUserLoginId() !=null && otherUser.getUserLoginId().length() >0)
          {  session.setAttribute("OTHER_USER",otherUser);
              if (otherUser.getUserPasswordCheck() >0)
              {
                    if (otherUser.getUserGroup().equals("planReview"))
                    { 
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/planReview/upload.do?method=plansForOthers&idNumber=0");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null;
                    }
                    else if (otherUser.getUserGroup().equals("fireworks"))
                    { 
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/fireworks/main.do?method=showCounty&recFlag=PENDING");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null; 
                    }
                    else if (otherUser.getUserGroup().equals("LEPC"))
                    {  
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=goToCounty&countyName="+otherUser.getUserCounty());
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null; 
                    }
                      
                    else if (otherUser.getUserGroup().equals("EMS_OPS"))
                    {  
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/ems/main.do?method=authUser");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null; 
                    }
                    else if (otherUser.getUserGroup().equals("FSSA"))
                    {  
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/idhsInspections/search.do?method=searchFssa");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null; 
                    }
                    else {
                      return mapping.findForward("loginUser");
                    }
              }
              else {
                 return mapping.findForward("updatePassword"); 
              }
                
           }
            else
            { session.setAttribute("USER_ERROR","user id or password does not match, try again or contact nnimmagadda@dhs.in.gov with details");
              return mapping.findForward("loginUser");
            } 
         } 
         else if (method.equals("userList")) 
         {
          setList(request,uDAO,otherUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        } 
        else if (method.equals("userListPlan")) 
        {
         setListPlan(request,uDAO,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         return mapping.findForward("userListPlan");
        }
        else if (method.equals("userListLepc")) 
        {
         setListLepc(request,uDAO,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         return mapping.findForward("userListLepc");
        }
        else if (method.equals("userListEms")) 
        {
         setListEms(request,uDAO,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         return mapping.findForward("userListEms");
        }
        
        else if (method.equals("emailLepcUsersInfo")) 
        {
            int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
          ApplicationContacts lepcContacts = sDAO.setContacts("LEPC_CONTACT1","LEPC_CONTACT2");  
           sendEmailNewLepcUser(otherUser,lepcContacts);
          setListLepc(request,uDAO,otherUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userListLepc");
        }
        else if (method.equals("emailEmsUsersInfo")) 
        {
            int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
           sendEmailNewEmsAccount(otherUser.getUserLoginId(),otherUser.getUserPassword());
          setListEms(request,uDAO,otherUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userListEms");
        }
        else if (method.equals("userListLepcCounty")) 
        { String county =request.getParameter("countyName");
         List userList = uDAO.selectUserListByCounty(county);
          session.setAttribute("COUNTY_USER_LIST",userList);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=start");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
        }
        else if (method.equals("dropUserLEPC")) 
        { String county =request.getParameter("countyName");
          String userId=request.getParameter("userId");
          uDAO.deleteLepc(Integer.parseInt(userId));
         List userList = uDAO.selectUserListByCounty(county);
          session.setAttribute("COUNTY_USER_LIST",userList);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=start");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
        }
        
            
        else if (method.equals("refresh")) 
        {
          setList(request,uDAO,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         setList(request,uDAO,otherUserForm);
          return mapping.findForward("userList");
        } 
        else if (method.equals("filterLepc")) 
        {
          setFilterSession(request,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         setListLepc(request,uDAO,otherUserForm);
          return mapping.findForward("userListLepc");
        } 
        else if (method.equals("filterPlan")) 
        {
          setFilterSession(request,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         setListPlan(request,uDAO,otherUserForm);
          return mapping.findForward("userListPlan");
        } 
        else if (method.equals("filterEms")) 
        {
          setFilterSession(request,otherUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         setListEms(request,uDAO,otherUserForm);
          return mapping.findForward("userListEms");
        } 
        else if (method.equals("updateUser")) 
        {
            int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
          otherUserForm.setProperties(otherUser);
          setOptions(request,dfbsUtilityDAO,otherUser.getUserCounty());
          session.setAttribute("OTHER_UPDATE_USER_STATUS","");
          return mapping.findForward("updateUser"); 
        }
        else if (method.equals("saveUpdateUser")) 
        {
          otherUsers otherUser = otherUserForm.getOtherUsers();
          uDAO.updateOtherUser(otherUser);
          
          setOptions(request,dfbsUtilityDAO,otherUserForm.getUserCounty());
          session.setAttribute("OTHER_UPDATE_USER_STATUS","Y");
          return mapping.findForward("updateUser"); 
        }
       
          else if (method.equals("saveChangedPassword")) 
         { 
           otherUsers otherUser = (otherUsers) session.getAttribute("OTHER_USER");
           String userPassword = request.getParameter("userPassword");
           String userPasswordNew = request.getParameter("newPassword");
           String userPasswordNewRetype = request.getParameter("retypeNewPassword");
           otherUsersForm errorForm = new otherUsersForm();
           String userPasswordOld = otherUser.getUserPassword();
           if (validatePassword(userPassword,userPasswordOld,userPasswordNew,userPasswordNewRetype,errorForm))
          {  
             otherUser.setUserPassword(userPasswordNew);
             uDAO.updateOtherUserPassword(otherUser);
                  if (otherUser.getUserGroup().equals("planReview"))
                    { 
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/planReview/upload.do?method=plansForOthers");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null;
                    }
                    else if (otherUser.getUserGroup().equals("fireworks"))
                    { 
                      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/fireworks/main.do?method=showCounty&recFlag=PENDING");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null; 
                    }
                else if (otherUser.getUserGroup().equals("LEPC"))
                { 
                  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                  redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=goToCounty&countyName="+otherUser.getUserCounty());
                  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                  return null; 
                }
              else if (otherUser.getUserGroup().equals("EMS_OPS"))
              {  
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/ems/main.do?method=authUser");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null; 
              }
            }
          else
          {  request.setAttribute("OTHER_USER_ERROR",errorForm);
           return mapping.findForward("updatePassword");
          }
         }
         // email forgotten password
         else if (method.equals("emailPassword")) 
         { String userEmail = request.getParameter("userLoginId");
           otherUsers otherUser= uDAO.emailOtherUserPassword(userEmail);
           if (otherUser !=null && otherUser.getUserPassword()!=null && otherUser.getUserPassword().length()>3)
           {
           sendEmail(otherUser.getUserPassword(),otherUser.getUserLoginId());
           session.setAttribute("USER_ERROR","Password sent to email address!");
           return mapping.findForward("loginUser");
           
           }
           else
           {  session.setAttribute("USER_ERROR","No user match for the email found contact nnimmagadda@dhs.in.gov with details !");
              return mapping.findForward("loginUser");
           }
         }
      
        else if (method.equals("userAccounts")) 
        {
           return mapping.findForward("userAccounts"); 
        }
       
      
          else if (method.equals("updatePassword")) 
        {
           return mapping.findForward("updatePassword"); 
        }
     
        else if (method.equals("emailUsersInfo")) 
        {
            int userId = Integer.parseInt(request.getParameter("userId"));
          otherUsers otherUser = uDAO.selectOtherUser(userId);
           String fireCheifEmail=uDAO.selectChiefEmail(otherUser.getFDId());
           sendEmailNewUseAccountInfo(otherUser,contacts,"New",null);
             if (fireCheifEmail !=null && fireCheifEmail != otherUser.getUserLoginId())
             {
           sendEmailNewUseAccountInfo(otherUser,contacts,"old",fireCheifEmail);
             }
           setList(request,uDAO,otherUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        }
          else if (method.equals("emailFireChiefs")) 
        {
            List fireChiefsEmails= uDAO.selectFireChiefsEmail();
           Iterator j = fireChiefsEmails.iterator();
           while(j.hasNext())
         {
          otherUsers otherUser = (otherUsers) j.next();
          String fireChiefEmail=otherUser.getUserLoginId();
        //  if (fireChiefEmail.trim().length() > 5)
        //  {
         //  sendEmailFireChief(otherUser);
        //  }
           
         }
          return mapping.findForward("userList");
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
        
   protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,otherUsersForm errorForm) throws Exception
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
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("\r\n other Application Password Recovery");
            sb.append("\r\n user id=");
            sb.append(userEmail);
            sb.append("\r\n password= ");
            sb.append(passWord);
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS other user's password recovery service.");
            mail.createMail("idhs_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("idhs_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","OtherUserAction"+userEmail);
            mail1.sendAndClose();
            }
  }
 
 private static void sendEmailFireChief(otherUsers otherUser) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
           String[] emailTo = {otherUser.getUserLoginId()};
             
            // String[] emailTo = {"nnimmagadda@dhs.in.gov"}; 
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("We are in the process of rolling out our IDHS plan review access for the Fire Departments.");
            sb.append("\n \nThis is how the program works. We have decided to give access to Fire and Life Safety plans of all Buildings to the Fire Departments.  This access is restricted to the Fire Department’s county. So you will be able to access all the plans (if available in the database) "+
                      "of "+otherUser.getUserFirstName()+" for the county. We are going to grant access for 2 users for each fire department. These two users could be Fire Chief and one other persons working in that fire department. Or if the Fire Chief is busy we can grant access to 2 other persons working in that fire department.");
            sb.append("\n \nTo keep up with the security of the application we have designed a portal through which you can apply for access to the plan review process."+
                      " Please  visit IDHS web site at https://oas.dhs.in.gov/dfbs/otherUsers/otherUser.do?method=selectCounty ");
            sb.append("\n \n Step 1. Select the County you are working for: "+otherUser.getUserFirstName()+ ", click Next.");
            sb.append("\n \n Step 2.Select your Fire Department. "+otherUser.getUserLastName()+" ");
            sb.append("\n \n Step 3. Fire Chief Email :"+ otherUser.getUserLoginId()+" ");
            sb.append("\n \n Step 4.Security key: "+ otherUser.getUserPassword()+" ");
             sb.append("\n \nThen click validate, if the information is correct, it will let you apply for user access. After applying for user access IDHS will contact you with your account information. If you have questions about this email please contact acronnon@dhs.in.gov. Do not reply to this message.");
            StringBuffer sub = new StringBuffer();
            sub.append("Indiana Department of Homeland Security Plan Review Plans Access.");
            mail.createMail("idhs_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("idhs_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","OtherUserAction"+otherUser.getUserLoginId());
            mail1.sendAndClose();
            }
  }
   private static void setList(HttpServletRequest request,otherUsersDAO uDAO,otherUsersForm userForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     List list = null;
     HsFilter filterSession = (HsFilter) session.getAttribute("USER_LIST_FILTER");
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("R");
        session.setAttribute("USER_LIST_FILTER",filterSession);
        list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER ,filterSession.getValue());
       
     } 
     else if(filterSession.getType().equals("byLetter")) 
     { 
        list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER,filterSession.getValue());
     } 
       request.setAttribute("USER_LIST",new HsListWrapper(list));
     
  }
  private static void setListLepc(HttpServletRequest request,otherUsersDAO uDAO,otherUsersForm userForm) throws Exception
  {
  
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    List list = null;
    HsFilter filterSession = (HsFilter) session.getAttribute("USER_LIST_FILTER");
    if(filterSession == null) {
       filterSession = new HsFilter();
       filterSession.setType("byLetter");
       filterSession.setValue("R");
       session.setAttribute("USER_LIST_FILTER",filterSession);
       list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER_LEPC ,filterSession.getValue());
    } 
    else if(filterSession.getType().equals("byLetter")) 
    { 
       list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER_LEPC,filterSession.getValue());
    } 
      request.setAttribute("USER_LIST",new HsListWrapper(list));
    
  }     
    protected static void setFilterSession(HttpServletRequest request,otherUsersForm userForm) throws Exception
  {
    HttpSession session = request.getSession();
    String filter = request.getParameter("filter");
    
    HsFilter filterSession = new HsFilter();
    filterSession.setType(filter);
    
     if(filter == null ) {
      filterSession.setType("byLetter");
      filterSession.setValue("A");
    } 
    else if(filter.equals("byLetter"))  
    { 
       String letter = request.getParameter("letter");
       filterSession.setValue(letter);
     }
    
     session.setAttribute("USER_LIST_FILTER",filterSession);
    
  }   
   protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List charNav = uDAO.getCharacterNavs(otherUsersSQL.SELECT_USER_FIRST_LETTER_OPTIONS);
   List charNavLepc = uDAO.getCharacterNavs(otherUsersSQL.SELECT_USER_FIRST_LETTER_OPTIONS_LEPC);
   List charNavEms = uDAO.getCharacterNavs(otherUsersSQL.SELECT_USER_FIRST_LETTER_OPTIONS_EMS);
   List charNavPlan = uDAO.getCharacterNavs(otherUsersSQL.SELECT_USER_FIRST_LETTER_OPTIONS_PLAN);
    request.setAttribute("USER_FIRST_LETTERS_OPTIONS",charNav);
   request.setAttribute("USER_FIRST_LETTERS_OPTIONS_LEPC",charNavLepc);
   request.setAttribute("USER_FIRST_LETTERS_OPTIONS_PLAN",charNavPlan);
   request.setAttribute("USER_FIRST_LETTERS_OPTIONS_EMS",charNavEms);
    
   
 }
 
   private static void sendEmailNewUser(otherUsers otherUser,int authUserCheck,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {otherUser.getUserLoginId(),contacts.getContact1Email(),contacts.getContact2Email()};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("\r\n This is to confirm that we have received a request for a new user for the IDHS Plan Review Process.");
            sb.append("\n Indiana Department of Homeland Security(IDHS) will contact you in about a week's time about your request.");
            if (authUserCheck >2) {
             sb.append("\n ***** This user can only be created when another authorized user for this Fire Department is inactivated. *****."); 
            }
            sb.append("\n \n User last name=");
            sb.append(otherUser.getUserLastName()+".");
             sb.append("\n \n User first name=");
             sb.append(otherUser.getUserFirstName());
              sb.append("\n \n user Id= ");
             sb.append(otherUser.getUserLoginId());
             StringBuffer sub = new StringBuffer();
            sub.append("IDHS  new user request.");
             mail.createMail("idhs_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("idhs_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
            mail1.sendAndClose();
            }
  }
  private static void sendEmailNewUseAccountInfo(otherUsers otherUser,ApplicationContacts contacts,String userType,String fireCheiefEmail) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
              String[] emailFinal ={"nnimmagadda@dhs.in.gov"}; 
             if ( userType.equals("New"))
              {
              String[] emailTo={otherUser.getUserLoginId()};
              emailFinal=emailTo;
              }
              else {
                 String[] emailTo={fireCheiefEmail};
                   emailFinal=emailTo;
              } 
          // String[] emailFinal = {"nnimmagadda@dhs.in.gov"};
           String[] bccTo = {"nnimmagadda@dhs.in.gov"}; 
          //  String[] bccTo = {contacts.getContact1Email(),contacts.getContact2Email(),"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("\n This Indiana Department of Homeland Security(IDHS) email is sent to confirm that we have designated this user as the authorized user for the IDHS Plan Review Plans.");
            sb.append(" This user will be able to view Fire and Life safety plans of the projects in your county on behalf of your fire department.");
             sb.append("\n \n You can use https://oas.dhs.in.gov/dfbs/otherUsers/otherUser.do to log in to the IDHS system.");
            sb.append("\n \n user name = ");
            sb.append(otherUser.getUserLastName()+", "+otherUser.getUserFirstName());
           sb.append("\n \n user id = ");
            sb.append(otherUser.getUserLoginId()).append(".");
              if ( userType.equals("New"))
              {
              sb.append("\n \n password = ");
             sb.append(otherUser.getUserPassword()).append(".");
            }
             if ( userType.equals("old"))
            {
              sb.append("\n \n If this user does not belong to your organization and he is not a authorized user, please contact "+contacts.getContact1Email()+" with details.Do not reply to this message.");
            }
            sb.append("\n \n If you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.Please do not reply to this message.");
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS other Application authorized user added. ");
              mail.createMail("IDHS_otherUser_online@dhs.in.gov",emailFinal,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("IDHS_otherUser_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
            mail1.sendAndClose();
            }
  }
 
         protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO,String county) throws Exception 
 {
    List counties = uDAO.getOptions(otherUsersSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
    if (county!=null &&county.length()>3)
    {
     List fdList = uDAO.getOptions(otherUsersSQL.SELECT_FD_OTHERS +" where county="+"'"+county+"'");
    request.setAttribute("DFBS_FD_OPTIONS",fdList);
    }
   List firedeptList = uDAO.getOptions(otherUsersSQL.SELECT_FD_OTHERS );
   request.setAttribute("DFBS_FIRE_DEPT_OPTIONS",firedeptList);
 }
         
  private static void sendEmailNewLepcUser(otherUsers otherUser,ApplicationContacts contacts) throws Exception
  {
         try {
           HsMailer mail = new HsMailer();
           
           String[] emailTo = {otherUser.getUserLoginId()};
          // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           String[] bccTo = {"nnimmagadda@dhs.in.gov","mroe@dhs.IN.gov","KBuster@dhs.in.gov"};
           StringBuffer sb = new StringBuffer("\r\n You have been designated as the "+otherUser.getUserCounty()+" county contact for the LEPC online submissions.");
             sb.append("\n \n Please use the information below to log in and begin your submission at http://www.in.gov/dhs/3793.htm");
             sb.append("\n \n User last name=");
           sb.append(otherUser.getUserLastName()+".");
            sb.append("\n \n User first name=");
            sb.append(otherUser.getUserFirstName());
             sb.append("\n \n user Id= ");
            sb.append(otherUser.getUserLoginId());
             sb.append("\n \n password = ");
             sb.append(otherUser.getUserPassword());
             sb.append("\n \n Please contact Ian Ewusi at iewusi@dhs.in.gov if you have questions or concerns.");
            StringBuffer sub = new StringBuffer();
           sub.append("IDHS LEPC Online Reporting New User Info. ");
            mail.createMail("mroe@dhs.IN.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
           } 
           catch(Exception e) 
           {
              HsMailer mail1 = new HsMailer();
           mail1.createMail("idhs_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
           mail1.sendAndClose();
           }
  }
  private static void setListPlan(HttpServletRequest request,otherUsersDAO uDAO,otherUsersForm userForm) throws Exception
  {
  
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    List list = null;
    HsFilter filterSession = (HsFilter) session.getAttribute("USER_LIST_FILTER");
    if(filterSession == null) {
       filterSession = new HsFilter();
       filterSession.setType("byLetter");
       filterSession.setValue("R");
       session.setAttribute("USER_LIST_FILTER",filterSession);
       list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER_PLAN ,filterSession.getValue());
    } 
    else if(filterSession.getType().equals("byLetter")) 
    { 
       list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER_PLAN,filterSession.getValue());
    } 
      request.setAttribute("USER_LIST_PLAN",new HsListWrapper(list));
    
  }     
  private static void setListEms(HttpServletRequest request,otherUsersDAO uDAO,otherUsersForm userForm) throws Exception
  {
  
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    List list = null;
    HsFilter filterSession = (HsFilter) session.getAttribute("USER_LIST_FILTER");
    if(filterSession == null) {
       filterSession = new HsFilter();
       filterSession.setType("byLetter");
       filterSession.setValue("R");
       session.setAttribute("USER_LIST_FILTER",filterSession);
       list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER_EMS ,filterSession.getValue());
    } 
    else if(filterSession.getType().equals("byLetter")) 
    { 
       list = uDAO.selectUserList(otherUsersSQL.SELECT_USER_BY_LETTER_EMS,filterSession.getValue());
    } 
      request.setAttribute("USER_LIST_EMS",new HsListWrapper(list));
    
  }     
  private static void sendEmailNewEmsAccount(String email,String passWord) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
           // String[] emailTo = {email};
           String[] emailTo =   {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS New User Account Information");
            sb.append("\n\n user id= ");
            sb.append(email);
            sb.append("\n\n password= ");
              sb.append(passWord);
             sb.append("\n \n use this IDHS web site to access the application http://127.0.0.1:7101/dfbs/otherUsers/otherUser.do");
              sb.append("\n \n If you have questions or concerns about this email please contact rachelmiller@dhs.in.gov");
            
            
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
  private static void NewEmsAccountRequestReceived(otherUsers emsUser) throws Exception
   {
           try {
             HsMailer mail = new HsMailer();
             
          // String[] emailTo = {email,"jfox@dhs.in.gov","rachelmiller@dhs.in.gov"};
            String[] emailTo = {"rstump@dhs.in.gov","chilton@dhs.in.gov",emsUser.getUserLoginId()};
          // String[] emailTo =   {"nnimmagadda@dhs.in.gov"};
             String[] bccTo = {"nnimmagadda@dhs.in.gov"};
           
           
             StringBuffer sb = new StringBuffer("\r\n This email is to confirm that IDHS has received your EMS New Account Request.");
             sb.append("\n \n After verification and authentication of your request, IDHS will create a account and forward to the details by email.");
             sb.append("\n \n If you have questions or concerns about this email please contact chilton@dhs.in.gov.");
             sb.append("\n\n user id requested for person:"+ emsUser.getUserFirstName()+" "+emsUser.getUserLastName());
               sb.append("\r\n user email:"+ emsUser.getUserLoginId());
             
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