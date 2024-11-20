package FireBldEducation.control.action;
import main.data.*;
import FireBldEducation.control.form.*;
import main.control.form.*;
import main.to.*;
import FireBldEducation.to.*;
import FireBldEducation.data.*;
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



public class FireBldEducationUserAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
           
          
          DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
            
          FireBldEducationUserForm userForm = (FireBldEducationUserForm) form;
          FireBldEducationUserDAO userDAO = (FireBldEducationUserDAO) dmapNew.getHsDAO(DfbsDataMap2.FIRE_BLDG_USER);
            HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
            FireBldEducationCourseDAO courseDAO = (FireBldEducationCourseDAO) dmapNew.getHsDAO(DfbsDataMap2.FIRE_BLDG_COURSE);
            
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
          
          if (method == null ) 
          {ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
                      if (security !=null)
                      {
            List userList=userDAO.selectUserList(FireBldEducationSQL.SELECT_CODE_USERS);
              session.setAttribute("USER_LIST",userList);
            return mapping.findForward("userList");
              }
              else {
                  return mapping.findForward("courseAdmin");
              }
          } 
          else if (method.equals("userList") ) 
          {ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
                      if (security !=null)
                      {
              List userList=userDAO.selectUserList(FireBldEducationSQL.SELECT_CODE_USERS);
              request.setAttribute("USER_LIST",new HsListWrapper(userList));
              setFilterOptions(request,dfbsUtilityDAO);
               return mapping.findForward("userList");
              }
              else {
                  return mapping.findForward("courseAdmin");
              }
          } 
            else if (method.equals("userToRegister") ) 
            {   String userLastName = request.getParameter("userLastName");
                if (userLastName.length()>=3  )
                {
                List userList=userDAO.selectUserListByLastName(userLastName);
                session.setAttribute("USER_TO_REGISTER_LIST",new HsListWrapper(userList));
                setFilterOptions(request,dfbsUtilityDAO);
                    request.setAttribute("USER_TO_REGISTER_ERROR",null);
                }
                else {
                    request.setAttribute("USER_TO_REGISTER_ERROR","ERROR");
                }
                FireBldEducationCourse course = (FireBldEducationCourse) session.getAttribute("UPDATE_COURSE");
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                 redirectUrl.append(request.getContextPath()).append("/fireBldEducation/course.do?method=updateCourse&courseId="+course.getCourseId());
                 response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;

            } 
            else if (method.equals("userIdLookup") ) 
            {   String userLastName = request.getParameter("userLastName");
                String userFirstName = request.getParameter("userFirstName");
                if (userLastName.length()>=3 && userFirstName.length()>=3 )
                {
                List userList=userDAO.selectUserListByName(userLastName,userFirstName);
                request.setAttribute("USER_VIEW_LIST",new HsListWrapper(userList));
                setFilterOptions(request,dfbsUtilityDAO);
                    request.setAttribute("USER_VIEW_LIST_ERROR",null);
                 return mapping.findForward("userIdLookup");
                }
                else {
                    request.setAttribute("USER_VIEW_LIST_ERROR","ERROR");
                }
                    return mapping.findForward("userIdLookup");
               
            } 
            else if (method.equals("forgotUserId") ) 
            {     setFilterOptions(request,dfbsUtilityDAO);  
            return mapping.findForward("userIdLookup");
            } 
            else if (method.equals("classList") ) 
            {    String userId = request.getParameter("userPersonId");
                 List classList=userDAO.selectClassList(Integer.parseInt(userId));
                 request.setAttribute("CLASS_LIST",new HsListWrapper(classList));
                 return mapping.findForward("classList");
            } 
            else if (method.equals("filter")) 
                   {
                     setFilterSession(request,userForm);
                     setFilterOptions(request,dfbsUtilityDAO);
                    setList(request,userDAO,userForm);
                     return mapping.findForward("userList");
                   } 
            else if (method.equals("addNewUser") ) 
            {    
                setOptions(request,dfbsUtilityDAO);
                return mapping.findForward("addNewUser");
             
            } 
           
            else if (method.equals("updateUser") ) 
            {   String userId = request.getParameter("userPersonId");
           FireBldEducationUser user = userDAO.selectUser(Integer.parseInt(userId));
           userForm.setProperties(user);
                session.setAttribute("VIEW_USER",user);
              List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
              request.setAttribute("COURSE_LIST_REGISTER",new HsListWrapper(courseList));
                setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("updateUser");
            } 
            else if (method.equals("updateSaveUser") ) 
            {                   FireBldEducationUser user =  userForm.getCodeUser();
                          FireBldEducationUserForm errorForm = new FireBldEducationUserForm();
                        
                           if (validateUser(user,errorForm,userDAO))
                           {   userDAO.updateUser(user);
                                FireBldEducationUser viewUser = userDAO.selectUser((user.getUserId()));
                                
                               session.setAttribute("VIEW_USER",viewUser);
                               return mapping.findForward("manageUser");
                             
                           }
                            else {setOptions(request,dfbsUtilityDAO);
                                return mapping.findForward("updateUser");
                            }
            } 
            else if (method.equals("saveNewUser") ) 
            {
                FireBldEducationUser user =  userForm.getCodeUser();
                          FireBldEducationUserForm errorForm = new FireBldEducationUserForm();
                        
                           if (validateUser(user,errorForm,userDAO))
                           {   
                          int userCheck =userDAO.selectUserCheck(user.getUserId());
                          if(userCheck ==0)
                          {     request.setAttribute("USER_EXIST","");
                               userDAO.insertUser(user);
                                session.setAttribute("VIEW_USER",user);
                                return mapping.findForward("manageUser");
                             
                          }
                          else
                          {
                               setOptions(request,dfbsUtilityDAO);
                               request.setAttribute("USER_EXIST","TRUE");
                               return mapping.findForward("addNewUser");
                           }
                           }
                            else {setOptions(request,dfbsUtilityDAO);
                                request.setAttribute("USER_ERROR",errorForm);
                                return mapping.findForward("addNewUser");
                            }
            } 
            else if (method.equals("userAdmin") ) 
            {
              
              return mapping.findForward("userAdmin");
            } 
            else if (method.equals("manageUser") ) 
            {     
                FireBldEducationUser codeEduUser = (FireBldEducationUser) session.getAttribute("CODE_EDU_USER");
                FireBldEducationUser viewUser = userDAO.selectUser((codeEduUser.getUserId()));
                session.setAttribute("VIEW_USER",viewUser);
               return mapping.findForward("manageUser");
            } 
            else if (method.equals("backToManageUser") ) 
            {    
                
                return mapping.findForward("manageUser");
            } 
           
            else if (method.equals("registerCourse")) 
                     {    FireBldEducationCourse course = (FireBldEducationCourse) session.getAttribute("UPDATE_COURSE");
                        String userId = request.getParameter("userId");
                        FireBldEducationUser viewUser = userDAO.selectUser((userId));
                        courseDAO.registerCourse(viewUser.getUserPersonId(),course.getCourseId());
                        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                         redirectUrl.append(request.getContextPath()).append("/FireBldEducation/course.do?method=updateCourse&courseId="+course.getCourseId());
                         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null;

                    } 
            else if (method.equals("logOn") ) 
            {    
                
                return mapping.findForward("logOn");
            } 
            else if (method.equals("logOut") ) 
            {    session.setAttribute("VIEW_USER",null);
                session.setAttribute("CODE_EDU_USER",null);
              session.setAttribute("SECURITY_SUPER_USER",null);
                return mapping.findForward("start");
            } 
            else if (method.equals("loginSystem")) 
            { 
              String userId = request.getParameter("userId");
              String passWord = request.getParameter("userPassword");
                session.setAttribute("DFBS_SECURITY",null);
                session.setAttribute("SECURITY_SUPER_USER",null);
              session.setAttribute("CODE_EDU_USER",new FireBldEducationUser ());
              FireBldEducationUser codeEduUser = (FireBldEducationUser) session.getAttribute("CODE_EDU_USER");
              codeEduUser = userDAO.selectUser(codeEduUser,userId,passWord);
                
              if (codeEduUser.getUserPassword() != null )
              {   session.setAttribute("VIEW_USER",codeEduUser);
                List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                request.setAttribute("COURSE_LIST_REGISTER",new HsListWrapper(courseList));
                  return mapping.findForward("manageUser");
              }
              else
              {codeEduUser.setUserId("ERROR");
                return mapping.findForward("logOn");
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
                 FireBldEducationUser codeEduUser = (FireBldEducationUser) session.getAttribute("CODE_EDU_USER");
               String userPasswordOld = codeEduUser.getUserPassword();
                 if (validatePassword(userPassword,userPasswordOld,userPasswordNew,userPasswordNewRetype,userForm))
              {  
                userDAO.updatePassword(codeEduUser,userPasswordNew);
                  userForm.setProperties(codeEduUser);
                       session.setAttribute("VIEW_USER",codeEduUser);
                       setOptions(request,dfbsUtilityDAO);
                     return mapping.findForward("updateUser");
              }
              else
              { request.setAttribute("USER_ERROR",userForm);
               return mapping.findForward("changePassword");
              }
             }
             // email forgotten password
             else if (method.equals("emailPassword")) 
             { 
              String userEmail = request.getParameter("userId");
                 session.setAttribute("CODE_EDU_USER",new FireBldEducationUser ());
                 FireBldEducationUser codeEduUser = (FireBldEducationUser) session.getAttribute("CODE_EDU_USER");
               codeEduUser = userDAO.selectUser(userEmail);
               
               if (codeEduUser !=null && codeEduUser.getUserPassword() !=null )
               {
               sendEmail(codeEduUser.getUserPassword(),userEmail);
               String pwdStatus="EMAIL_SENT";
               session.setAttribute("PASSWORD_STATUS", pwdStatus); 
               return mapping.findForward("logOn");
               }
               else
               {   String pwdStatus="ERROR";
                   session.setAttribute("PASSWORD_STATUS", pwdStatus);
                  return mapping.findForward("emailPasswordPage");
               }
             }
            
             else if (method.equals("emailPasswordPage")) 
             {
               return mapping.findForward("emailPasswordPage");
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
    protected static boolean validateUser(FireBldEducationUser user ,FireBldEducationUserForm errorForm,FireBldEducationUserDAO cDAO) throws Exception
     {
       boolean noError = true;
       
      
       
        if(user.getUserLastName() == null || user.getUserLastName().trim().equals("")  )
       {
       errorForm.setUserLastName("ERROR");
        noError = false;
         }
       else
       {   
           errorForm.setUserLastName("");
          
       }
         if(user.getUserFirstName() == null || user.getUserFirstName().trim().equals("")  )
         {
         errorForm.setUserFirstName("ERROR");
         noError = false;
          }
         else
         {
            errorForm.setUserFirstName("");
           
         }
         if(user.getUserId() == null || user.getUserId().trim().equals("") ||user.getUserId().toUpperCase().indexOf("@") < 0 )
         {
         errorForm.setUserId("ERROR");
         noError = false;
          }
         else
         {        errorForm.setUserId("");
                 }
         if(user.getUserPassword() == null || user.getUserPassword().trim().equals("")) { 
         errorForm.setUserPassword("ERROR");
         noError = false;
          }
         else
         {
            errorForm.setUserPassword("");
           
         }
         if((user.getUserAddress1() == null || user.getUserAddress1().trim().equals("")))  
         {
         errorForm.setUserAddress1("ERROR");
         noError = false;
          }
         else
         {
            errorForm.setUserAddress1("");
           
         }
         if((user.getUserCity() == null || user.getUserCity().trim().equals("")))  
         {
         errorForm.setUserCity("ERROR");
         noError = false;
          }
         else
         {
            errorForm.setUserCity("");
           
         }
         if((user.getUserZip() == null || user.getUserZip().trim().equals("")))  
         {
         errorForm.setUserZip("ERROR");
         noError = false;
          }
         else
         {
            errorForm.setUserZip("");
           
         }
       if((user.getUserPhone() == null || user.getUserPhone().trim().equals("")))  
       {
       errorForm.setUserPhone("ERROR");
       noError = false;
        }
       else
       {
          errorForm.setUserPhone("");
         
       }
       if((user.getUserPsid() == null || user.getUserPsid().trim().equals("")))  
       {
       errorForm.setUserPsid("ERROR");
       noError = false;
        }
       else
       {
          errorForm.setUserPsid("");
         
       }
       if((user.getUserType() == null || user.getUserType().trim().equals("")))  
       {
       errorForm.setUserType("ERROR");
       noError = false;
        }
       else
       {
          errorForm.setUserType("");
         
       }
       return noError;
     }
    protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
    {
      
      
        
      List county = uDAO.getOptions(FireBldEducationSQL.SELECT_COUNTY_LIST_OPTIONS);
       request.setAttribute("USER_COUNTY_OPTIONS",county);
      List state = uDAO.getOptions(FireBldEducationSQL.SELECT_STATE_OPTIONS2);
       request.setAttribute("USER_STATE_OPTIONS",state);
        List userType = uDAO.getOptions(FireBldEducationSQL.SELECT_USER_LIST_OPTIONS);
         request.setAttribute("USER_TYPE_OPTIONS",userType);
      
    }
    private static void setList(HttpServletRequest request,FireBldEducationUserDAO userDAO,FireBldEducationUserForm userForm) throws Exception
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
           
           list = userDAO.selectUserList(FireBldEducationSQL.SELECT_USER_BY_LETTER ,filterSession.getValue());
        } 
        else if(filterSession.getType().equals("byLetter")) 
        { 
           list = userDAO.selectUserList(FireBldEducationSQL.SELECT_USER_BY_LETTER,filterSession.getValue());
        } 
       
       
       
       
        request.setAttribute("USER_LIST",new HsListWrapper(list));
        
     }
           
       protected static void setFilterSession(HttpServletRequest request,FireBldEducationUserForm userForm) throws Exception
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
      List charNav = uDAO.getCharacterNavs(FireBldEducationSQL.SELECT_USER_FIRST_LETTER_OPTIONS);
      
      
      request.setAttribute("USER_FIRST_LETTERS_OPTIONS",charNav);
      
     
    }     
    protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,FireBldEducationUserForm errorForm) throws Exception
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
      
      if(UserPasswordNew.compareTo(UserPasswordNewRetype) <0   ) 
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
    
    private static void sendEmail(String passWord,String userEmail) throws Exception
    {
            try {
              HsMailer mail = new HsMailer();
              
              String[] emailTo = {userEmail};
             // String[] bccTo = {"nnimmagadda@dhs.in.gov"};
              StringBuffer sb = new StringBuffer("\r\n DFBS Password Recovery");
              sb.append("\r\n user id= ");
              sb.append(userEmail);
              sb.append("\r\n password= ");
              sb.append(passWord);
              
              StringBuffer sub = new StringBuffer();
              sub.append("Fire and Building Education password recovery service ");
             mail.createMail("FireBldEducation_online@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
              mail.sendAndClose();
              } 
              catch(Exception e) 
              {
                 HsMailer mail1 = new HsMailer();
              mail1.createMail("FireBldEducation_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," FireBldEducation password recovery service error email ","FireBldEducationUserAction");
              mail1.sendAndClose();
              }
    }  
}
