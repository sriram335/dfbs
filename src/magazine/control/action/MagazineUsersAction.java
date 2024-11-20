package magazine.control.action;




import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
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

public class MagazineUsersAction extends ControlAction
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
         DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        MagazineUsersForm magUserForm = (MagazineUsersForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          MagazineUsersDAO mDAO = ( MagazineUsersDAO) dmapNew.getHsDAO(DfbsDataMap2.MAG_USER);
          ApplicationContacts contacts = sDAO.setContacts("MAGAZINE_CONTACT1","MAGAZINE_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
         if (method == null ) 
        {
          return mapping.findForward("loginUser");
        } 
         else if (method.equals("userList")) 
         {
          setList(request,mDAO,magUserForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        } 
         else if (method.equals("userAuthList")) 
         {
         int companyId = Integer.parseInt(request.getParameter("companyId"));
          setAuthList(request,mDAO,companyId);
          return mapping.findForward("userList");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,mDAO,magUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("userList");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,magUserForm);
          setFilterOptions(request,dfbsUtilityDAO);
         setList(request,mDAO,magUserForm);
          return mapping.findForward("userList");
        } 
        else if (method.equals("loginSystem")) 
        { 
          String userLoginId = request.getParameter("userLoginId");
          String passWord = request.getParameter("userPassword");
          session.setAttribute("MAGAZINE_USER",new MagazineUsers ());
          MagazineUsers magUser = mDAO.selectMagazineUser(userLoginId,passWord);
                  
          if (magUser.getUserLoginId() !=null && magUser.getUserLoginId().length() >0)
          {
          session.setAttribute("MAGAZINE_USER",magUser);
              if (magUser.getUserPasswordCheck() >0)
              {
              session.setAttribute("USER_ERROR","");
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do?method=filterByMagUser");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
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
          int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          magUserForm.setOwnerId(ownerId);
          session.setAttribute("MAGAZINE_INSERT_USER_STATUS","");
          return mapping.findForward("addNewUser"); 
        }
        else if (method.equals("saveNewUser")) 
        {
          MagazineUsers magUser = magUserForm.getMagazineUsers();
          mDAO.insertMagUser(magUser);
           session.setAttribute("MAGAZINE_INSERT_USER_STATUS","Y");
          return mapping.findForward("addNewUser"); 
        }
        else if (method.equals("updateUser")) 
        {
           int userId = Integer.parseInt(request.getParameter("userId"));
           
          MagazineUsers magUser = mDAO.selectMagUser(userId);
          magUserForm.setProperties(magUser);
          session.setAttribute("MAGAZINE_UPDATE_USER_STATUS","");
          return mapping.findForward("updateUser"); 
        }
        else if (method.equals("saveUpdateUser")) 
        {
          MagazineUsers magUser = magUserForm.getMagazineUsers();
          mDAO.updateMagUser(magUser);
          session.setAttribute("MAGAZINE_UPDATE_USER_STATUS","Y");
          return mapping.findForward("updateUser"); 
        }
       
          else if (method.equals("saveChangedPassword")) 
         { 
           MagazineUsers magUser = (MagazineUsers) session.getAttribute("MAGAZINE_USER");
           String userPassword = request.getParameter("userPassword");
           String userPasswordNew = request.getParameter("newPassword");
           String userPasswordNewRetype = request.getParameter("retypeNewPassword");
           MagazineUsersForm errorForm = new MagazineUsersForm();
           String userPasswordOld = magUser.getUserPassword();
           if (validatePassword(userPassword,userPasswordOld,userPasswordNew,userPasswordNewRetype,errorForm))
          {  
              session.setAttribute("USER_ERROR","");
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do?method=filterByMagUser");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
            }
          else
          {  request.setAttribute("MAG_USER_ERROR",errorForm);
           return mapping.findForward("updatePassword");
          }
         }
         // email forgotten password
         else if (method.equals("emailPassword")) 
         { String userEmail = request.getParameter("userLoginId");
           MagazineUsers magUser= mDAO.emailMagUserPassword(userEmail);
           if (magUser !=null && magUser.getUserPassword() !=null && magUser.getUserPassword().length() >3)
           {
           sendEmail(magUser.getUserPassword(),magUser.getUserLoginId());
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
          int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          List oldUserList = mDAO.selectUserList(ownerId);
          Iterator i = oldUserList.iterator();
           int userId = Integer.parseInt(request.getParameter("userId"));
         MagazineUsers magUserNew = mDAO.selectMagUser(userId);   
        while(i.hasNext())
        {
        MagazineUsers magUserOld = (MagazineUsers)  i.next();
        sendEmailNewUser(magUserNew,"Old",magUserOld.getUserLoginId(),contacts);
        }
        sendEmailNewUser(magUserNew,"New",magUserNew.getUserLoginId(),contacts);
          session.setAttribute("MAGAZINE_UPDATE_USER_STATUS","Y");
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
          session.setAttribute("MAGAZINE_USER",new MagazineUsers ());
          MagazineUsers magUser = mDAO.selectMagUser(userLoginId,passWord);
          if (magUser.getUserLoginId() !=null && magUser.getUserLoginId().length() >0)
          {
          
          session.setAttribute("MAGAZINE_USER",magUser);
              if (magUser.getUserPasswordCheck() >0)
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
         MagazineUsers magUser = (MagazineUsers) session.getAttribute("MAGAZINE_USER");
          StringBuffer redirectUrl = new StringBuffer("https://oasdev.dhs.in.gov/reports/rwservlet?");
          redirectUrl.append("dfbsepnopen&report=magazine_data_extract.rdf&desformat=delimiteddata&desname="+magUser.getUserLoginId()+"&destype=mail&subject=email extract attched&from=magazine_online@dhs.in.gov");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        }
        else if (method.equals("emailUsersAll")) 
        {
          List list = null;
          list = mDAO.selectUserList();
          Iterator i = list.iterator();
          while(i.hasNext())
          {
            MagazineUsers magUser = (MagazineUsers)  i.next();
            sendEmailNewUserAll(magUser,contacts);
          }
          return mapping.findForward("magazineApp"); 
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
        
   protected static boolean validatePassword(String UserPassword,String UserPasswordOld,String UserPasswordNew,String UserPasswordNewRetype,MagazineUsersForm errorForm) throws Exception
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
          
          
            StringBuffer sb = new StringBuffer("\r\n Magazine Application Password Recovery");
            sb.append("\r\n user id=");
            sb.append(userEmail);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("Magazine Application password recovery service ");
          
          
          
            mail.createMail("magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS password recovery service error email ","MagUserAction"+userEmail);
            mail1.sendAndClose();
            }
  }
 

   private static void setList(HttpServletRequest request,MagazineUsersDAO mDAO,MagazineUsersForm userForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("USER_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("R");
        session.setAttribute("USER_LIST_FILTER",filterSession);
        
        list = mDAO.selectUserList(DfbsSQL.SELECT_USER_BY_LETTER ,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     { 
        list = mDAO.selectUserList(DfbsSQL.SELECT_USER_BY_LETTER,filterSession.getValue());
     } 
    
    
    
    
     request.setAttribute("USER_LIST",new HsListWrapper(list));
     
  }
        
    protected static void setFilterSession(HttpServletRequest request,MagazineUsersForm userForm) throws Exception
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
   protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO mDAO) throws Exception 
 {
    List charNav = mDAO.getCharacterNavs(DfbsSQL.SELECT_USER_FIRST_LETTER_OPTIONS);
    
    
    request.setAttribute("USER_FIRST_LETTERS_OPTIONS",charNav);
    
   
 }
  private static void setAuthList(HttpServletRequest request,MagazineUsersDAO mDAO,int companyId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     list = mDAO.selectUserList(companyId);
     
    
    
    
    
     request.setAttribute("USER_LIST",new HsListWrapper(list));
     
  }
   private static void sendEmailNewUser(MagazineUsers magUser,String userType,String userEmail,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {userEmail};
           //String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n This is to confirm that, we have received a request for new user for your company.");
            sb.append("\r\n Indiana Department of Homeland Security(IDHS) informs you that the following user is added as additional user for your company.");
            sb.append("\r\n This user will be able to submit supplemental applications for IDHS online FSC magazine application at our web site on behalf of your company.");
            sb.append("\r\n user last name=");
            sb.append(magUser.getUserLastName());
             sb.append("\r\n user First name=");
             sb.append(magUser.getUserFirstName()).append(".");
              sb.append("\r\n user Id= ");
             sb.append(magUser.getUserLoginId()).append(".");
            if ( userType.equals("New"))
            {
              sb.append("\r\n password=");
             sb.append(magUser.getUserPassword()).append(".");
               sb.append("\r\n If you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.Do not reply to this message.");
            }
             if ( userType.equals("Old"))
            {
              sb.append("\r\n If this user does not belong to your organization and he is not a authorized user, please contact "+contacts.getContact1Email()+" with details.Do not reply to this message.");
             
            }
            
            
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS Magazine Application new user request processed. ");
          
          
          
            mail.createMail("magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","MagUserAction");
            mail1.sendAndClose();
            }
  }
  private static void sendEmailNewUserAll(MagazineUsers magUser,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {magUser.getUserLoginId()};
          // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n This Indiana Department of Homeland Security(IDHS) email is sent to confirm that we have designated you as the authorized user for the IDHS online FSC magazine application for your company.");
            sb.append("\r\n As a user, you will be able to apply for supplemental applications at our web site on behalf of your company.");
            sb.append("\r\n With this change, only designated users will be able to apply for supplemental and 3-year re-certification FSC applications.");
            sb.append("\r\n If you would like to have additional users designated for your company please contact "+contacts.getContact1Email()+", with your last name, first name, email, phone number and the company name.");
            sb.append("\r\n You can use the 'Manage User Accounts' option on the online application to log in to your account, and then you will be able to change your password or retrive your password, and get a data extract of your company data.");
            sb.append("\r\n user name = ");
            sb.append(magUser.getUserLastName());
           sb.append("\r\n user id = ");
            sb.append(magUser.getUserLoginId()).append(".");
            sb.append("\r\n password = ");
            sb.append(magUser.getUserPassword()).append(".");
            sb.append("\r\n If you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.Please do not reply to this message.");
           
            
            
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS Magazine Application authorized user added. ");
          
          
          
            mail.createMail("magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","MagUserAction");
            mail1.sendAndClose();
            }
  }
  }
 


  
  
  
 
 
 

