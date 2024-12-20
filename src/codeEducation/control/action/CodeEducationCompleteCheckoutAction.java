package codeEducation.control.action;
import main.data.*;
import codeEducation.control.form.*;
import main.to.*;
import codeEducation.to.*;
import codeEducation.data.*;
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
public class CodeEducationCompleteCheckoutAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
      ActionForm form,HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException
      {
          try {
            ServletContext context = 
            this.getServlet().getServletConfig().getServletContext();
           
            DfbsDataMap dmap2 = (DfbsDataMap)
            context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
            
            HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
              CodeEducationUserDAO userDAO = (CodeEducationUserDAO) dmap2.getHsDAO(DfbsDataMap.CODE_USER);
              CodeEducationCourseDAO courseDAO = (CodeEducationCourseDAO) dmap2.getHsDAO(DfbsDataMap.CODE_COURSE);
            ApplicationSecurityDAO seDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
            String method = request.getParameter("method");
            
            HttpSession session = request.getSession();
            
              ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS"); 
              CodeEducationUser viewUser = (CodeEducationUser) session.getAttribute("VIEW_USER");
            if(viewUser == null ) 
            {
              throw new Exception("NO_USER_IN_SESSION");
            }
            
            completeCheckout(context,request,response,viewUser,session,userDAO,courseDAO,contacts);
            return null;
                 
          } 
          
        catch (Exception e) 
        {
           saveError(request,e);
          request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
           return mapping.findForward("error");
          
        }
          
      }
    private static void completeCheckout(ServletContext context,
       HttpServletRequest request,HttpServletResponse response,
           CodeEducationUser viewUser,HttpSession session,CodeEducationUserDAO userDAO,
           CodeEducationCourseDAO courseDAO,ApplicationContacts contacts) throws Exception
    {
      
      String checkoutToken = (String)  
      session.getAttribute("CODE_EDUCATION_CHECKOUT_TOKEN");
      if(checkoutToken == null || ! checkoutToken.equals(viewUser.getCheckoutId())) 
      {
        throw new Exception("ERROR_CODE_EDU_CHECKOUT_TOKEN");
      }
      
      
            CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
            ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
            ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
            ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
            ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

             OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
               
       
       
             if(!orderInfo.isAuthorized()) {
       
         throw new ServletException(checkoutToken + " has not been authorized.");
             
       } else if(!orderInfo.isCaptured()) {
       
        
         
         DfbsDataMap dmap2 = (DfbsDataMap)
         context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
         
           
        
           
        
         int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
         applicationSpecificProcessing(userDAO,courseDAO,request,session,viewUser,receiptId,contacts);
         
         
         
         session.setAttribute("VIEW_USER",null);
         
        
             }

             URL url = new URL((String)((Stub)checkoutService)._getProperty(Stub.ENDPOINT_ADDRESS_PROPERTY));
       StringBuffer sb = new StringBuffer();
       if(url.getPort() != -1) {
         sb.append(url.getProtocol()).append("://").append(url.getHost());
         sb.append(":").append(url.getPort());
         sb.append("/apps/kwikekard/checkout/servlet/receipt");
         response.sendRedirect(sb.toString());
                     return ;

                   } else {
           sb.append(url.getProtocol()).append("://").append(url.getHost());
           sb.append("/apps/kwikekard/checkout/servlet/receipt");
           response.sendRedirect(sb.toString());
                     return ;

                   }
         
         
           }
    private static void applicationSpecificProcessing(CodeEducationUserDAO userDAO,CodeEducationCourseDAO courseDAO,
           HttpServletRequest request,HttpSession session,CodeEducationUser viewUser, int receiptId,ApplicationContacts contacts) throws Exception {
        
       if (viewUser.getUserPersonId()==0) {
           userDAO.insertUser(viewUser);
       }
        else {
            userDAO.updateUser(viewUser);
        }
        List permits = viewUser.getCourses();
        Iterator i = permits.iterator();
        
        
        while(i.hasNext())
        {
          CodeEducationCourse course = (CodeEducationCourse) i.next();
          courseDAO.registerCourse(viewUser.getUserPersonId(),course.getCourseId());
          logReceiptId(courseDAO,viewUser,course,receiptId);
          sendEmailRegistration(viewUser.getUserId(),contacts, course,receiptId); 
      }
      
           }
    private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
         request.getRequestDispatcher("/codeEducation/user/receipt.jsp").include(request,bufferedResponse);
              return new String(bufferedResponse.getBuffer());
           }
    private static void logReceiptId(CodeEducationCourseDAO courseDAO,CodeEducationUser viewUser,CodeEducationCourse course,int receiptId) throws Exception {
       
       
      
         courseDAO.insertCourseTransaction(course,viewUser,receiptId);
       
           }
           
    private static void sendEmailRegistration(String userId,ApplicationContacts contacts,CodeEducationCourse course ,int receiptId) throws Exception
     {
             try {
               HsMailer mail = new HsMailer();
               String[] emailTo = {userId, contacts.getContact1Email()};
             //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
               String[] bccTo = {"nnimmagadda@dhs.in.gov"};
             
             
                   StringBuffer sb = new StringBuffer("\r\n");
                   sb.append(" This is to confirm that you are registered for the course" +course.getCourseName()+".");
                   sb.append(" The course is scheduled on date " +course.getStartDateString()+" at "+course.getClassLocation()+".");
                   sb.append(" Couse Description: "+ course.getCourseDescription());
                   sb.append(" \r\n If you  have any questions regarding this email or the course you can contact ");
                   sb.append(contacts.getContact2Email() +" by email or call "+ contacts.getContact2Phone());
                   sb.append(". To cancel or  register log back  into the system at https://oas.dhs.in.gov/dfbs/codeEducation/start.do.");
                   sb.append(" \r\n Your online payment reference number is: " +receiptId);               
               
               
               StringBuffer sub = new StringBuffer();
               sub.append(" Code Education Class Registration Confirmation Email  ");
             
             
             
               mail.createMail("codeEducation_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
               mail.sendAndClose();
               } 
               catch(Exception e) 
               {
                 HsMailer mail = new HsMailer();
               mail.createMail("codeEducation_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email class registration",userId);
               mail.sendAndClose();
               }
     }  
     
 
}
