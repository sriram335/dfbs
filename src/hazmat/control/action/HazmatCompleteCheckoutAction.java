package hazmat.control.action;
import hazmat.control.form.*;
import hazmat.to.*;
import hazmat.data.*;
import hazmat.report.*;
import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;

import main.to.*;
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
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;


 
public class HazmatCompleteCheckoutAction  extends ControlAction
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
        HazmatCarrierDAO cDAO = (HazmatCarrierDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_CARRIER);
        HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
        HazmatShipmentDAO sDAO = (HazmatShipmentDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_SHIPMENT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
        if(permit == null ) 
        {
          throw new Exception("NO_ORGANIZATION_IN_SESSION");
        }
         if (permit.getTotalShipments() > 0)
        {
        completeCheckout(context,request,response,permit,contacts);
        return null;
        }
        else
        {
        
        return null;
        }
    
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
 
 
 private static void completeCheckout(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        HazmatPermit permit,ApplicationContacts contacts) throws Exception
 {
   HttpSession session = request.getSession();
   
   String checkoutToken = (String)  
   session.getAttribute("HAZMAT_CHECKOUT_TOKEN");
   if(checkoutToken == null || ! checkoutToken.equals(permit.getCheckoutId())) 
   {
     throw new Exception("ERROR_HAZMAT_CHECKOUT_TOKEN");
   }
   
   
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

	  OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
	    
	 
    StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
    homeUrl.append("<a href='");
    homeUrl.append(request.getContextPath()).append("/hazmat/hazmat.do'>");
    homeUrl.append("Back to Hazardous Material Transport Permits </a>");
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
    
     
      
      DfbsDataMap dmap2 = (DfbsDataMap)
      context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
        HazmatCarrierDAO cDAO = (HazmatCarrierDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_CARRIER);
        HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
        HazmatShipmentDAO sDAO = (HazmatShipmentDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_SHIPMENT);
      int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
      permit.setReceiptId(receiptId);
       permit.setPaymentType("CC");
      applicationSpecificProcessing(oDAO,cDAO,sDAO, permit,receiptId);
     
       sendEmailCheckOut(receiptId,Double.toString(permit.getAmount()),permit.getOrgName(),permit.getOrgEmail(),contacts);
     
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
      
    // https://test.secure.in.gov/apps/kwikekard/checkout/servlet/receipt 

	}

  
  
  
  private static void applicationSpecificProcessing(HazmatPermitDAO oDAO,HazmatCarrierDAO cDAO,
       HazmatShipmentDAO sDAO, HazmatPermit permit, int receiptId) throws Exception {
    
    
      oDAO.insertPermit(permit);
   
    
    List carriers = permit.getCarriers();
    Iterator i = carriers.iterator();
    
    
    while(i.hasNext())
    {
      HazmatCarrier carrier = (HazmatCarrier)  i.next();
      
        cDAO.insertCarrier(carrier,permit.getOrgId());
        List shipments = carrier.getShipments();
        Iterator j = shipments.iterator();
         while(j.hasNext())
         { HazmatShipment shipment = (HazmatShipment) j.next();
          sDAO.insertShipment(shipment,receiptId,carrier.getCarrierId());
          sDAO.insertPermitTransaction(shipment,permit,receiptId);
         }
      } 
      
    
       
        
    
  }
  
       
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/hazmat/application/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
 
  
  private static void sendEmailCheckOut(int receiptId,String amount,String orgName,String orgEmail,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),orgEmail};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("Hazardous Material Transport permit online transaction #");
            sb.append("Company Name: " +orgName);
            sb.append("\n\r");
            sb.append(" Thank you for using IDHS online application.Your transaction confirmation number is:" );
            sb.append(receiptId);
            sb.append(" : ");
            sb.append("\n\r");
            sb.append(" Amount paid in the transaction$:" );
            sb.append(amount);
            sb.append("\n\r");
            sb.append(" You have successfully submitted your application.If for some reason you have failed ");
            sb.append(" to print the permits use the email link given below to print them.  ");
            sb.append(" For all other concerns email  "+contacts.getContact1Email()+" or "+contacts.getContact2Email()+" with the confirmation number listed in the mail.");
             sb.append("\n\r");
            sb.append(" Due to the nature of the permit, permits will be mailed to the ");
            sb.append(" organization address listed in the application or can be emailed to the email address ");
            sb.append(" submitted in the online application ONLY. ");
             sb.append("\n\r");
             sb.append(" https://oas.dhs.in.gov/dfbs/hazmat/hazmat.do?method=printAllPermits&receiptId="+receiptId+"&orgEmail="+orgEmail);
            StringBuffer sub = new StringBuffer();
            sub.append(" Hazardous Material Transport permit Online Transaction  ");
          
          
          
            mail.createMail("hazmat_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail1 = new HsMailer();
            mail1.createMail("hazmat_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Hazardous Material Transport permit Online Transaction error email ","hazmatCompleteCheckoutAction");
            mail1.sendAndClose();
            }
  }
  

  private static void completeCheckoutTest(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        HazmatPermit permit,ApplicationContacts contacts) throws Exception
 {
   HttpSession session = request.getSession();
 
   DfbsDataMap dmap2 = (DfbsDataMap)
   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
   
        
        HazmatCarrierDAO cDAO = (HazmatCarrierDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_CARRIER);
        HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
        HazmatShipmentDAO sDAO = (HazmatShipmentDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_SHIPMENT);
      int receiptId = 100;
      applicationSpecificProcessing(oDAO,cDAO,sDAO, permit,receiptId);
       sendEmailCheckOut(receiptId,Double.toString(permit.getAmount()),permit.getOrgName(),permit.getOrgEmail(),contacts);    
   
   
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
    redirectUrl.append(request.getContextPath()).append("/hazmat/hazmat.do");
    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
	  return ;


	}  
 



 
}
  



