package cigarette.control.action;
import cigarette.control.form.*;
import cigarette.to.*;
import cigarette.data.*;
import main.data.*;
import main.to.*;

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



public class CigaretteCheckoutAction  extends ControlAction
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
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           CigaretteApplicationDAO aDAO = (CigaretteApplicationDAO) dmap2.getHsDAO(DfbsDataMap.CIG_APPLICATION);
        CigaretteCompanyDAO cDAO = (CigaretteCompanyDAO) dmap2.getHsDAO(DfbsDataMap.CIG_COMPANY);
         CigaretteBrandDAO bDAO = (CigaretteBrandDAO) dmap2.getHsDAO(DfbsDataMap.CIG_BRAND);
        CigaretteUsersDAO uDAO = (CigaretteUsersDAO) dmap2.getHsDAO(DfbsDataMap.CIG_USER);
          
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          ApplicationContacts contacts = sDAO.setContacts("CIGARETTE_CONTACT1","CIGARETTE_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
        String appType = (String) session.getAttribute("APPLICATION_TYPE");
        if (method == null)
        {
        CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
        CigaretteAgent agent = (CigaretteAgent) session.getAttribute("CIGARETTE_AGENT");
        if(company == null) 
        {
          throw new Exception("NO_COMPANY_IN_SESSION");
        
        }
        if (company.getTotalBrands() > 0 )
         {
          processCheckout(context,request,response,company);
          return null;
         }
         else
         { 
         
          return null;
         }
        }
         else if(method.equals("check"))
       { CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
        CigaretteAgent agent = (CigaretteAgent) session.getAttribute("CIGARETTE_AGENT");
         int receiptId = Integer.parseInt(request.getParameter("receiptId"));
          company.setReceiptId(receiptId);
          company.setPaymentType("check");
         applicationSpecificProcessing(cDAO,aDAO,bDAO, company,receiptId,agent,uDAO,contacts);
          sendEmailCheckOut(receiptId,Double.toString(company.getAmount()),session,company,contacts);
          return mapping.findForward("cigaretteApp");
       }
        else if(method.equals("noPayment"))
       { CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
        CigaretteAgent agent = (CigaretteAgent) session.getAttribute("CIGARETTE_AGENT");
         company.setReceiptId(1);
          company.setPaymentType("");
          CigaretteApplication application = (CigaretteApplication) session.getAttribute("CIGARETTE_APPLICATION");
          if (application.getAppId()==0)
          { 
         applicationSpecificProcessing(cDAO,aDAO,bDAO, company,company.getReceiptId(),agent,uDAO,contacts);
          sendEmailNoFee(0,Double.toString(company.getAmount()),session,company,contacts);
          }
          return mapping.findForward("cigaretteApp");
       }
       
        
       else
         { 
         
          return null;
         }
      } 
      
    catch (Exception e) 
    {
       saveError(request,e);
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
       return mapping.findForward("error");
      
    }
  }
  
  
 
 private static void processCheckout(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        CigaretteCompany company) throws Exception
 {
   HttpSession session = request.getSession();
	 
   OrderInfo cart = new OrderInfo();
   
   Address userAddress = new Address();
   userAddress.setAddress1(company.getCompanyAddress1());
   userAddress.setAddress2(company.getCompanyAddress2());
   userAddress.setCity(company.getCompanyCity());
   userAddress.setState(company.getCompanyState());
	 userAddress.setZipCode(company.getCompanyZip());
   
   
   ItemInfo[] itemsInfo = new ItemInfo[company.getApplicationsCount()];
   List applications = company.getApplications();
   Iterator i = applications.iterator();
   int j = -1;
   StringBuffer sku = new StringBuffer("sku:");
   while(i.hasNext())
   {
    CigaretteApplication application = (CigaretteApplication)  i.next();
     
     ItemInfo item = new ItemInfo();
      sku.append("application");
    
     item.setSku(sku.toString());
     StringBuffer sb = new StringBuffer();
     sb.append(company.getTotalBrands());
     sb.append(" cigarette brand family ");
     sb.append(" ");
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(application.getAmount()));
     itemsInfo[++j] = item;
    
   }
	 cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/cigarette/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
   cart.setApplicationName(context.getInitParameter("application_name_cigarette"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(cart);
  session.setAttribute("CIGARETTE_CHECKOUT_TOKEN", token);
   company.setCheckoutId(token);
   URL endpointUrl = new URL((String)((Stub)checkoutService)._getProperty(Stub.ENDPOINT_ADDRESS_PROPERTY));

   StringBuffer sb = new StringBuffer();
	 if(endpointUrl.getPort() != -1) {
      sb.append(endpointUrl.getProtocol()).append("://").append(endpointUrl.getHost());
      sb.append(":").append(endpointUrl.getPort());
      sb.append("/apps/kwikekard/checkout/servlet/beginSession?token=");
      sb.append(token);
    	response.sendRedirect(response.encodeRedirectURL(sb.toString()));
		  return ;
     
		} else {
      sb.append(endpointUrl.getProtocol()).append("://").append(endpointUrl.getHost());
      sb.append("/apps/kwikekard/checkout/servlet/beginSession?token=");
      sb.append(token);
     	response.sendRedirect(response.encodeRedirectURL(sb.toString()));
		  return ;

		}
  
  
  
  
 }
 
 private static void processCheckoutTest(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        CigaretteCompany company) throws Exception
 {
   HttpSession session = request.getSession();
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/cigarette/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ;
 
 }
 
 private static void applicationSpecificProcessing(CigaretteCompanyDAO cDAO,CigaretteApplicationDAO aDAO,
       CigaretteBrandDAO bDAO, CigaretteCompany company, int receiptId,CigaretteAgent agent,CigaretteUsersDAO uDAO,ApplicationContacts contacts) throws Exception {
    
      
      cDAO.updateCompany(company);
      if( agent !=null)
      {
          if (agent.getAgentId()== 0)
          {
          cDAO.insertAgent(agent,company);
          }
          else
          {
          cDAO.updateAgent(agent);
          }
      }
    List applications = company.getApplications();
    Iterator i = applications.iterator();
    while(i.hasNext())
    {
      CigaretteApplication application = (CigaretteApplication)  i.next();
        aDAO.insertApplication(company.getCompanyId(),receiptId,application);
        if (application.getAppType().equals("Initial"))
        {
        int userId = aDAO.addNewUser(application.getAppId()); 
         CigaretteUsers cigUserNew = uDAO.selectCigUser(userId);
         sendEmailNewUser(cigUserNew,"New",cigUserNew.getUserLoginId(),contacts);
        }
        List brands = application.getBrands();
        Iterator j = brands.iterator();
         while(j.hasNext())
         { CigaretteBrand brand = (CigaretteBrand) j.next();
          bDAO.insertBrand(brand,application.getAppId());
         }
        if (application.getAmount() >0)
        {
         bDAO.insertPermitTransaction(application,company,receiptId);
        }
      } 
    
  } 
  
 private static void sendEmailCheckOut(int receiptId,String amount,HttpSession session,CigaretteCompany company,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            CigaretteApplication application = (CigaretteApplication) session.getAttribute("CIGARETTE_APPLICATION");
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),application.getEmail()};    
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("Cigarette company online transaction # for "+company.getCompanyName());
            sb.append(". Thank you for using IDHS Cigarette online application.Your transaction confirmation number is:" );
            sb.append(receiptId);
            sb.append(" : ");
            sb.append("\n\r");
            sb.append(" Amount paid in the transaction$:" );
            sb.append(amount);
            sb.append("\n\r");
            sb.append(" You have successfully submitted your application.Please print the final application ");
            sb.append(" from the web site and sign the application and  mail the signed copy of the application");
            sb.append(" to the address on the application. Once we receive the signed copy of the application we will mail you the permit.  ");
            sb.append(" After our approval you can print copy of the permit from the same online application.");
             sb.append(" If you have not printed the application already use the link below to print it.");
            sb.append(" https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_application.rdf&p_application_id=" +application.getAppId());
            StringBuffer sub = new StringBuffer();
            sub.append(" Cigarette permit Online Check Transaction  ");
          
          
          
            mail.createMail("cigarette_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From cigarette check out fee error email ","cigarette check out fee");
            mail1.sendAndClose();;
            }
  }
   private static void sendEmailNoFee(int receiptId,String amount,HttpSession session,CigaretteCompany company,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            CigaretteApplication application = (CigaretteApplication) session.getAttribute("CIGARETTE_APPLICATION");
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("Cigarette company online transaction # for "+company.getCompanyName());
            sb.append(". Thank you for using IDHS Cigarette online application." );
            sb.append("\n\r");
            sb.append(" Amount due for this transaction is $:" );
            sb.append(amount);
            sb.append(" You have successfully submitted your application. Please print the final application ");
            sb.append(" from the web site and sign the application and  mail the signed copy of the application");
            sb.append(" to the address on the application. Once we receive the signed copy of the application, the check, and testing lab reports and the markings selected by the manufacturer, we will mail you the permit.  ");
            sb.append(" After our approval you can print copy of the permit from the same online application.");
             sb.append(" If you have not printed the application already use the link below to print it.");
            sb.append(" https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_application.rdf&p_application_id=" +application.getAppId());
            
            
            
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Cigarette permit Online Check Transaction  ");
          
          
          
            mail.createMail("cigarette_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From cigarette check out no fee error email ","cigarette check out no fee");
            mail1.sendAndClose();
            }
  }
  private static void sendEmailNewUser(CigaretteUsers cigUser,String userType,String userEmail,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {userEmail};
        //   String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n This is to confirm that, we have received a request for new user for your company.");
            sb.append("\r\n Indiana Department of Homeland Security(IDHS) informs you that the following user is added as additional user for your company.");
            sb.append("\r\n This user will be able to submit supplemental applications for IDHS online FSC cigarette application at our web site on behalf of your company.");
            sb.append("\r\n user name=");
            sb.append(cigUser.getUserLastName());
             sb.append("\r\n user email=");
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
 
}
  


