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



public class HazmatCheckoutAction  extends ControlAction
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
        
       
        if (method == null)
        {
        HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
        if(permit == null) 
        {
          throw new Exception("NO_ORGANIZATION_IN_SESSION");
        
        }
        if (permit.getTotalShipments() > 0 )
         {
          processCheckout(context,request,response,permit);
          return null;
         }
         else
         { 
         
          return null;
         }
        }
         else if(method.equals("check"))
       { HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
         int receiptId = Integer.parseInt(request.getParameter("receiptId"));
          permit.setReceiptId(receiptId);
          permit.setPaymentType("check");
         applicationSpecificProcessing(oDAO,cDAO,sDAO, permit,receiptId);
          return mapping.findForward("hazmatApp");
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
  
  
 
 private static void processCheckout(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        HazmatPermit permit) throws Exception
 {
   HttpSession session = request.getSession();
	 
   OrderInfo cart = new OrderInfo();
   
   Address userAddress = new Address();
   userAddress.setAddress1(permit.getOrgAddress1());
   userAddress.setAddress2(permit.getOrgAddress2());
   userAddress.setCity(permit.getOrgCity());
   userAddress.setState(permit.getOrgState());
	 userAddress.setZipCode(permit.getOrgZip());
   
   
   ItemInfo[] itemsInfo = new ItemInfo[permit.getCarriersCount()];
   List carriers = permit.getCarriers();
   Iterator i = carriers.iterator();
   int j = -1;
   StringBuffer sku = new StringBuffer("sku:");
   while(i.hasNext())
   {
    HazmatCarrier carrier = (HazmatCarrier)  i.next();
     
     ItemInfo item = new ItemInfo();
      sku.append("carrier");
    
     item.setSku(sku.toString());
     StringBuffer sb = new StringBuffer();
     sb.append(carrier.getShipmentsCount());
     sb.append(" (hazmat permits)");
     sb.append(" ");
     sb.append(carrier.getCarrierName().toUpperCase());
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(carrier.getAmount()));
     itemsInfo[++j] = item;
   
   }
	 cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/hazmat/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
  
   cart.setApplicationName(context.getInitParameter("application_name_hazmat"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

   String token = checkoutService.createOrder(cart);
   
	 session.setAttribute("HAZMAT_CHECKOUT_TOKEN", token);
   permit.setCheckoutId(token);
   
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
        HazmatPermit permit) throws Exception
 {
   HttpSession session = request.getSession();
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/hazmat/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ;

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
  
 
}
  



