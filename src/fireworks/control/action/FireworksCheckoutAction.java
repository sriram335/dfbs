package fireworks.control.action;
import fireworks.control.form.*;
import fireworks.to.*;
import fireworks.data.*;
import fireworks.report.*;

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
import main.to.*;
import hs.util.*;
import main.data.*;
import idhsInspections.data.*;
import hs.data.system.*;


public class FireworksCheckoutAction extends ControlAction
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
        DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
        DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        
        
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
        if(owner == null || ownerApplication == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
        if(method == null)
        {
        processCheckout(context,request,response,ownerApplication);
        return null;
        }
          else if(method.equals("check"))
       {  
            
          String checkNumber  = request.getParameter("checkNumber");
           applicationSpecificProcessing(oDAO,pDAO, ownerApplication,request,session,checkNumber);
             
       if (ownerApplication.getTotalPermitsAmount() > 0)
         {
           logReceiptId(pDAO,ownerApplication,ownerApplication.getOwnerId());
           
         }
      session.setAttribute("DFBS_OWNER",null);
      session.setAttribute("DFBS_OWNER_APPLICATION",null);
          
       }
      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/fireworks/main.do");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
  }
  
  
 
 private static void processCheckout(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        DfbsOwner owner) throws Exception
 {
   HttpSession session = request.getSession();
	 
   
   OrderInfo cart = new OrderInfo();
   
   Address userAddress = new Address();
   userAddress.setAddress1(owner.getStreet1());
   userAddress.setAddress2(owner.getStreet2());
   userAddress.setCity(owner.getCity());
   userAddress.setState(owner.getState());
	 userAddress.setZipCode(owner.getZip());
   
   
   ItemInfo[] itemsInfo = new ItemInfo[owner.getPermitsCount()];
   List permits = owner.getPermits();
   
   Iterator i = permits.iterator();
   int j = -1;
   
   while(i.hasNext())
   {StringBuffer sku = new StringBuffer("sku:");
     DfbsFireworksPermit permit = (DfbsFireworksPermit)  i.next();
     
     ItemInfo item = new ItemInfo();
     if(permit.getPermitNumber() == null) {
      sku.append("new");
     } 
     else 
     {
      item.setSku(permit.getPermitNumber());
     }
     item.setSku(sku.toString());
     StringBuffer sb = new StringBuffer();
     if(permit.isNew()) {
      sb.append("NEW");
     } 
     else 
     {
      sb.append(permit.getPermitNumber());
     }
     sb.append(" (");
     sb.append(permit.getPermitTypeString().toUpperCase());
     sb.append(")");
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(permit.getAmount()));
     itemsInfo[++j] = item;
     
   }
	 cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/fireworks/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
  
   cart.setApplicationName(context.getInitParameter("application_name_fireworks"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
       //  ((Stub)checkoutService)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, context.getInitParameter("checkout_url"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

   String token = checkoutService.createOrder(cart);
   
	 session.setAttribute("DFBS_FIREWORKS_CHECKOUT_TOKEN", token);
   owner.setCheckoutId(token);

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
		  return;

		}
  
  
  
  
 }
 
 private static void processCheckoutTest(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        DfbsOwner owner) throws Exception
 {
   HttpSession session = request.getSession();
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/fireworks/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ;

 }
 
 private static void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsFireworksPermit permit,
                          ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),inspEmail,contacts.getContact2Email()};
           //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          //  String currentDate = iDAO.selectCurrentDate();
           // int charPosition =inspEmail.indexOf("@");
          //  String userId= inspEmail.substring(0,charPosition);
         //   int inspectorId =iDAO.selectInspector(inspEmail);
           
          
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do").append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            sb.append("payment made by check, may take couple of business days for posting.").append("\n\r"); 
            StringBuffer sub = new StringBuffer();
            sub.append(" From fire fireworks inspection ready alert ");
           
           mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("fireworks_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire entertainment inspection ready alert error email ","HsFireworksCheckoutAction "+inspEmail);
            mail1.sendAndClose();  
           }
  }
  private static void applicationSpecificProcessing(DfbsFireworksOwnerDAO oDAO,DfbsFireworksPermitDAO pDAO,DfbsOwner owner,
                      HttpServletRequest request,HttpSession session, String checkNumber) throws Exception {
    
   ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
   String permitYear =(String) session.getAttribute("PERMIT_YEAR");
    boolean isNewOwner = owner.isNew();
    if(isNewOwner) {
      oDAO.insertOwner(owner);
    } 
    else
    {
      oDAO.updateOwner(owner);
    }
    
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsFireworksPermit permit = (DfbsFireworksPermit) i.next();
     
      if(isNewOwner) {
        permit.setOwnerId(owner.getOwnerId());
      } 
      
      if(permit.isNew()) {
        pDAO.insertPermit(permit,permitYear);
        
      } 
      else 
      {
        pDAO.updatePermit(permit,permitYear);
      }
      String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
     //     if ( checkNumber != null &&owner.getTotalPermitsAmount() > 0 && inspEmail.trim().length() > 5)
       //   {
        //   sendEmailInsp(request,inspEmail,permit,contacts);
       //   }
    }
  }
   private static void logReceiptId(DfbsFireworksPermitDAO pDAO,DfbsOwner owner,int receiptId) throws Exception {
    
    
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsFireworksPermit permit = (DfbsFireworksPermit) i.next();
      permit.setReceiptId(receiptId);
      permit.setOwnerName(owner.getOwnerName());
      pDAO.insertPermitTransaction(permit);
    }
  
    
	}
}
  



