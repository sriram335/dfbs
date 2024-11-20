package cigarette.control.action;

import cigarette.control.form.*;
import cigarette.to.*;
import cigarette.data.*;
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


public class CigaretteUsersAction extends ControlAction
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
        
        
        CigaretteUsersForm cigUserForm = (CigaretteUsersForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
         CigaretteUsersDAO uDAO = (CigaretteUsersDAO) dmap2.getHsDAO(DfbsDataMap.CIG_USER);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          ApplicationContacts contacts = sDAO.setContacts("CIGARETTE_CONTACT1","CIGARETTE_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
           
         if (method == null ) 
        {
          return mapping.findForward("loginUser");
        } 
         else if (method.equals("userList")) 
         {
          setList(request,uDAO,cigUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        } 
         else if (method.equals("userAuthList")) 
         {
         int companyId = Integer.parseInt(request.getParameter("companyId"));
          setAuthList(request,uDAO,companyId);
          return mapping.findForward("userList");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,uDAO,cigUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,cigUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         setList(request,uDAO,cigUserForm);
          return mapping.findForward("userList");
        } 
        else if (method.equals("loginSystem")) 
        { 
          String userLoginId = request.getParameter("userLoginId");
          String passWord = request.getParameter("userPassword");
          session.setAttribute("CIGARETTE_USER",new CigaretteUsers ());
          CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
          CigaretteUsers cigUser = uDAO.selectCigUser(userLoginId,passWord,company.getCompanyId());
                  
          if (cigUser.getUserLoginId() !=null && cigUser.getUserLoginId().length() >0)
          {
          session.setAttribute("CIGARETTE_USER",cigUser);
              if (cigUser.getUserPasswordCheck() >0)
              { 
              String appType = (String) session.getAttribute("APPLICATION_TYPE");
              if (appType.equals("3YearRenewal")) {
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/cigarette/application.do?method=add3YearRenewal");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;
              }
              else  
              {session.setAttribute("USER_ERROR","");
              return mapping.findForward("cigaretteApp"); 
              }
              }
              else
              { 
                return mapping.findForward("updatePassword"); 
              }
              
          }
          else
          { session.setAttribute("USER_ERROR","user id or password does not match, try again or contact "+contacts.getContact1Email()+" with details");
            return mapping.findForward("loginUser");
          }
        } 
        else if (method.equals("addNewUser")) 
        {
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          cigUserForm.setCompanyId(companyId);
          cigUserForm.setUserStatus("A");
          session.setAttribute("CIGARETTE_INSERT_USER_STATUS","");
          return mapping.findForward("addNewUser"); 
        }
        else if (method.equals("saveNewUser")) 
        {
          CigaretteUsers cigUser = cigUserForm.getCigaretteUsers();
          uDAO.insertCigUser(cigUser);
           session.setAttribute("CIGARETTE_INSERT_USER_STATUS","Y");
          return mapping.findForward("addNewUser"); 
        }
        else if (method.equals("updateUser")) 
        {
           int userId = Integer.parseInt(request.getParameter("userId"));
           
          CigaretteUsers cigUser = uDAO.selectCigUser(userId);
          cigUserForm.setProperties(cigUser);
          session.setAttribute("CIGARETTE_UPDATE_USER_STATUS","");
          return mapping.findForward("updateUser"); 
        }
        else if (method.equals("saveUpdateUser")) 
        {
          CigaretteUsers cigUser = cigUserForm.getCigaretteUsers();
          uDAO.updateCigUser(cigUser);
          session.setAttribute("CIGARETTE_UPDATE_USER_STATUS","Y");
          return mapping.findForward("updateUser"); 
        }
       
          else if (method.equals("saveChangedPassword")) 
         { 
           CigaretteUsers cigUser = (CigaretteUsers) session.getAttribute("CIGARETTE_USER");
           String userPassword = request.getParameter("userPassword");
           String userPasswordNew = request.getParameter("newPassword");
           String userPasswordNewRetype = request.getParameter("retypeNewPassword");
           CigaretteUsersForm errorForm = new CigaretteUsersForm();
           String userPasswordOld = cigUser.getUserPassword();
           if (validatePassword(userPassword,userPasswordOld,userPasswordNew,userPasswordNewRetype,errorForm))
          {  
             cigUser.setUserPassword(userPasswordNew);
             uDAO.updateCigUserPassword(cigUser);
             String appType= (String) session.getAttribute("APPLICATION_TYPE");
             if (appType !=null && appType.equals("Supplemental"))
             {
               return mapping.findForward("cigaretteApp");
             }
             else if (appType !=null && appType.equals("3YearRenewal"))
             {
                 StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/cigarette/application.do?method=add3YearRenewal");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;
             }
             else
             { 
              return mapping.findForward("manageAccounts");
             }  
            }
          else
          {  request.setAttribute("CIG_USER_ERROR",errorForm);
           return mapping.findForward("updatePassword");
          }
         }
         // email forgotten password
         else if (method.equals("emailPassword")) 
         { String userEmail = request.getParameter("userLoginId");
           CigaretteUsers cigUser= uDAO.emailCigUserPassword(userEmail);
           if (cigUser !=null && cigUser.getUserPassword()!=null && cigUser.getUserPassword().length()>3)
           {
           sendEmail(cigUser.getUserPassword(),cigUser.getUserLoginId());
           session.setAttribute("USER_ERROR","Password sent to email address!");
           return mapping.findForward("loginUser");
           
           }
           else
           {  session.setAttribute("USER_ERROR","No user match for the email found contact "+contacts.getContact1Email()+" with details !");
              return mapping.findForward("loginUser");
           }
         }
        
         else if (method.equals("emailUserInfo")) 
        {
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          List oldUserList = uDAO.selectUserList(companyId);
          Iterator i = oldUserList.iterator();
           int userId = Integer.parseInt(request.getParameter("userId"));
         CigaretteUsers cigUserNew = uDAO.selectCigUser(userId);   
        while(i.hasNext())
        {
        CigaretteUsers cigUserOld = (CigaretteUsers)  i.next();
        sendEmailNewUser(cigUserNew,"Old",cigUserOld.getUserLoginId(),contacts);
        }
        sendEmailNewUser(cigUserNew,"New",cigUserNew.getUserLoginId(),contacts);
          session.setAttribute("CIGARETTE_UPDATE_USER_STATUS","Y");
          return mapping.findForward("applicationsView"); 
        }
        else if (method.equals("userAccounts")) 
        {
           return mapping.findForward("userAccounts"); 
        }
       
      
         else if (method.equals("manageAccounts")) 
        { 
          String userLoginId = request.getParameter("userLoginId");
          String passWord = request.getParameter("userPassword");
          session.setAttribute("CIGARETTE_USER",new CigaretteUsers ());
          CigaretteUsers cigUser = uDAO.selectCigUser(userLoginId,passWord);
          if (cigUser.getUserLoginId() !=null && cigUser.getUserLoginId().length() >0)
          {
          
          session.setAttribute("CIGARETTE_USER",cigUser);
              if (cigUser.getUserPasswordCheck() >0)
              {
              session.setAttribute("USER_ERROR","");
              return mapping.findForward("manageAccounts"); 
              }
              else
              { 
                return mapping.findForward("updatePassword"); 
              }
              
          }
          else
          { session.setAttribute("USER_ERROR","user id or password does not match, try again or contact "+contacts.getContact1Email()+" with details");
            return mapping.findForward("userAccounts");
          }
        } 
          else if (method.equals("updatePassword")) 
        {
           return mapping.findForward("updatePassword"); 
        }
          else if (method.equals("dataExtract")) 
        {
         CigaretteUsers cigUser = (CigaretteUsers) session.getAttribute("CIGARETTE_USER");
          StringBuffer redirectUrl = new StringBuffer("https://oasdev.dhs.in.gov/reports/rwservlet?");
          redirectUrl.append("dfbsepnopen&report=cigarette_data_extract.rdf&desformat=delimiteddata&desname="+cigUser.getUserLoginId()+"&destype=mail&subject=email extract attched&from=cigarette_online@dhs.in.gov");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        }
        else if (method.equals("emailUsersAll")) 
        {
          List list = null;
          list = uDAO.selectUserList();
          Iterator i = list.iterator();
          while(i.hasNext())
          {
            CigaretteUsers cigUser = (CigaretteUsers)  i.next();
            sendEmailNewUserAll(cigUser,contacts);
          }
          return mapping.findForward("cigaretteApp"); 
        }
         else if (method.equals("emailUserExpiredApplication")) 
        {
          List emailList = uDAO.selectEmailsForExpiration();
          Iterator i = emailList.iterator();
          
          while(i.hasNext())
          { 
            CigaretteApplication application = (CigaretteApplication)  i.next();
             // System.out.println(application.getEmail());
            // sendEmailApplicationExpire(application.getEmail(),contacts,application.getAppType(),application.getAppDateString());
              uDAO.updateApplication(application.getAppId());
          }
          return mapping.findForward("cigaretteApp"); 
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
        
   protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,CigaretteUsersForm errorForm) throws Exception
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
          
          
            StringBuffer sb = new StringBuffer("\r\n Cigarette Application Password Recovery");
            sb.append("\r\n user id=");
            sb.append(userEmail);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("Cigarette Application password recovery service ");
          
          
          
            mail.createMail("cigarette_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","CigUserAction"+userEmail);
            mail1.sendAndClose();
            }
  }
 

   private static void setList(HttpServletRequest request,CigaretteUsersDAO uDAO,CigaretteUsersForm userForm) throws Exception
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
        
        list = uDAO.selectUserList(CigarettePermitSQL.SELECT_USER_BY_LETTER ,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     { 
        list = uDAO.selectUserList(CigarettePermitSQL.SELECT_USER_BY_LETTER,filterSession.getValue());
     } 
    
    
    
    
     request.setAttribute("USER_LIST",new HsListWrapper(list));
     
  }
        
    protected static void setFilterSession(HttpServletRequest request,CigaretteUsersForm userForm) throws Exception
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
    List charNav = uDAO.getCharacterNavs(CigarettePermitSQL.SELECT_USER_FIRST_LETTER_OPTIONS);
    
    
    request.setAttribute("USER_FIRST_LETTERS_OPTIONS",charNav);
    
   
 }
  private static void setAuthList(HttpServletRequest request,CigaretteUsersDAO uDAO,int companyId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     list = uDAO.selectUserList(companyId);
     
    
    
    
    
     request.setAttribute("USER_LIST",new HsListWrapper(list));
     
  }
   private static void sendEmailNewUser(CigaretteUsers cigUser,String userType,String userEmail,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {userEmail};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov","pbright@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n This is to confirm that, we have received a request for new user for your company.");
            sb.append("\r\n Indiana Department of Homeland Security(IDHS) informs you that the following user is added as additional user for your company.");
            sb.append("\r\n This user will be able to submit supplemental applications for IDHS online FSC cigarette application at our web site on behalf of your company.");
            sb.append("\r\n user last name=");
            sb.append(cigUser.getUserLastName());
             sb.append("\r\n user First name=");
             sb.append(cigUser.getUserFirstName()).append(".");
              sb.append("\r\n user Id= ");
             sb.append(cigUser.getUserLoginId()).append(".");
            if ( userType.equals("New"))
            {
              sb.append("\r\n password=");
             sb.append(cigUser.getUserPassword()).append(".");
               sb.append("\r\n If you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.Do not reply to this message.");
            }
             if ( userType.equals("Old"))
            {
              sb.append("\r\n If this user does not belong to your organization and he is not a authorized user, please contact "+contacts.getContact1Email()+" with details.Do not reply to this message.");
             
            }
            
            
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS Cigarette Application new user request processed. ");
          
          
          
            mail.createMail("cigarette_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","CigUserAction");
            mail1.sendAndClose();
            }
  }
  private static void sendEmailNewUserAll(CigaretteUsers cigUser,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {cigUser.getUserLoginId()};
          // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov","pbright@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n This Indiana Department of Homeland Security(IDHS) email is sent to confirm that we have designated you as the authorized user for the IDHS online FSC cigarette application for your company.");
            sb.append("\r\n As a user, you will be able to apply for supplemental applications at our web site on behalf of your company.");
            sb.append("\r\n With this change, only designated users will be able to apply for supplemental and 3-year re-certification FSC applications.");
            sb.append("\r\n If you would like to have additional users designated for your company please contact "+contacts.getContact1Email()+", with your last name, first name, email, phone number and the company name.");
            sb.append("\r\n You can use the 'Manage User Accounts' option on the online application to log in to your account, and then you will be able to change your password or retrive your password, and get a data extract of your company data.");
            sb.append("\r\n user name = ");
            sb.append(cigUser.getUserLastName());
           sb.append("\r\n user id = ");
            sb.append(cigUser.getUserLoginId()).append(".");
            sb.append("\r\n password = ");
            sb.append(cigUser.getUserPassword()).append(".");
            sb.append("\r\n If you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.Please do not reply to this message.");
           
            
            
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS Cigarette Application authorized user added. ");
          
          
          
            mail.createMail("cigarette_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","CigUserAction");
            mail1.sendAndClose();
            }
  }
   private static void sendEmailApplicationExpire(String userEmail,ApplicationContacts contacts,String companyName,String expDate) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
           String[] emailTo = {userEmail};
          // String[] emailTo = {"nnimmagadda@dhs.in.gov"};   
            String[] bccTo = {"nnimmagadda@dhs.in.gov","pbright@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("Cigarette Application - 3 year renewal reminder for: "+companyName+".");
            sb.append("\nThis is to remind you that your Fire Safe Cigarette Brands approved about 3 years ago by IDHS,State of Indiana, USA.,");
            sb.append("are due for renewal. Please visit our web site at https://oas.dhs.in.gov/dfbs/cigarette/cigarette.do and start 3 Year Re-certification process. ").append("\n\r");
            sb.append("This is a FINAL REMINDER. Your previous 3 Year approval expires on:"+expDate+".");
            sb.append("\nIF WE DO NOT RECEIVE YOUR APPLICATION ATLEAST 10 DAYS BEFORE "+expDate +" DATE, YOU WILL NOT BE ABLE TO SELL CIGARETTES IN STATE OF INDIANA AFTER :"+expDate+".");
            sb.append("\nIf you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.");
            
           
            StringBuffer sub = new StringBuffer();
            sub.append("Cigarette Application - 3 year renewal FINAL reminder! ");
          
          
          
            mail.createMail("pbright@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","CigUserAction"+userEmail);
            mail1.sendAndClose();
            }
  }
 
  }
 


  
  
  
 
 
 

