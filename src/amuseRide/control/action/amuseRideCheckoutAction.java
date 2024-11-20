package amuseRide.control.action;



import amuseRide.control.form.*;
import amuseRide.to.*;
import amuseRide.data.*;

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
import main.data.*;
import main.to.*;
public class amuseRideCheckoutAction extends ControlAction
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
        ShoppingCartForm cartForm = (ShoppingCartForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        amuseOwnerDAO oDAO = ( amuseOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_OWNER);
        amuseRideDAO rDAO = (amuseRideDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_RIDE);
        insuranceDAO iDAO = (insuranceDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_INSURANCE);  
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        DfbsOwner owner = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
        ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS"); 
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
         if (method == null)
        {
        if(owner == null ) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
         if (cart.getOwnerMapCount()> 0)
         {oDAO.calculateAmuseFee(cart);
          processCheckout(context,request,response,cart,session);
          return null;
         }
        }
        else if(method.equals("check"))
       {  int receiptId  = cartForm.getReceiptId();
           applicationSpecificProcessing(oDAO,rDAO,iDAO, session,cart,receiptId);
      session.setAttribute("DFBS_AMUSE_OWNER_SELECTED",null);
       }
     
       return mapping.findForward("start");
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
        ShoppingCart shopCart,HttpSession session) throws Exception
 {
	 
   Map ownerMap = shopCart.getOwnerMap();
   Set ownerkeys = ownerMap.keySet();
   Iterator k = ownerkeys.iterator();
   while(k.hasNext())
   {
   String key = (String) k.next();
   DfbsOwner owner= (DfbsOwner) ownerMap.get(key); 
    OrderInfo cart = new OrderInfo();
   Address userAddress = new Address();
   userAddress.setAddress1(owner.getOwnerAddress1());
   userAddress.setAddress2(owner.getOwnerAddress2());
   userAddress.setCity(owner.getOwnerCity());
   userAddress.setState(owner.getOwnerState());
	 userAddress.setZipCode(owner.getOwnerZip());
   ItemInfo[] itemsInfo = new ItemInfo[shopCart.getTotalPermits()];
     Map ridesMap = owner.getRidesMap();
     Set rideskeys = ridesMap.keySet();
     Iterator l = rideskeys.iterator();
     
   int j = -1;
    while(l.hasNext())
   {   String keyRide = (String) l.next();
        StringBuffer sku = new StringBuffer("sku:");
     amuseRide ride = (amuseRide)  ridesMap.get(keyRide);
      ItemInfo item = new ItemInfo();
     if(ride.getPermitNumber() == null) {
      sku.append("new");
     } 
     else 
     {
      sku.append(ride.getPermitNumber());
     }
     item.setSku(sku.toString());
     StringBuffer sb = new StringBuffer();
     if(ride.isNew()) {
      sb.append("NEW");
     } 
     else 
     {
      sb.append(ride.getPermitNumber());
     }
     sb.append(" (Amuse Ride Annual");
     sb.append("):");
      sb.append(" ");
     sb.append(owner.getOwnerDBA().toUpperCase());
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(ride.getAppFee()));
     itemsInfo[++j] = item;
    
   }
   cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/amuseRide/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   cart.setApplicationName(context.getInitParameter("application_name_rides"));
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
   ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(cart);
   session.setAttribute("DFBS_AMUSE_RIDE_CHECKOUT_TOKEN", token);
   shopCart.setCheckoutId(token);
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
  
 }
 private static void applicationSpecificProcessing(amuseOwnerDAO oDAO,amuseRideDAO rDAO,
       insuranceDAO iDAO,HttpSession session, ShoppingCart cart,int receiptId) throws Exception {
    
   ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
         Map ownerMap = cart.getOwnerMap();
         Set ownerkeys = ownerMap.keySet();
         Iterator k = ownerkeys.iterator();
         while(k.hasNext())
         {
         String key = (String) k.next();
         DfbsOwner owner= (DfbsOwner) ownerMap.get(key); 
         if(owner.getOwnerId() == 0) {
         oDAO.insertOwner(owner);
         } 
         else 
         {
           oDAO.updateOwner(owner);
         }   
            
           Map ridesMap = owner.getRidesMap();
           Set rideskeys = ridesMap.keySet();
           Iterator l = rideskeys.iterator();
           while(l.hasNext())
         {   String keyRide = (String) l.next();
           amuseRide ride = (amuseRide)  ridesMap.get(keyRide);
           if(ride.getPermitNumber() == null) {
           rDAO.insertRide(ride, owner.getOwnerId());
             logReceiptId(rDAO,ride,owner,receiptId);
             sendEmailInsp(owner,contacts,ride,cart);
           } 
           else 
           {
             rDAO.updateRide(ride);
             logReceiptId(rDAO,ride,owner,receiptId);
             sendEmailInsp(owner,contacts,ride,cart);
           }
         }
         Map insMap = owner.getInsMap();
         Set inskeys = insMap.keySet();
         Iterator m = inskeys.iterator();
         while(m.hasNext())
         {   String keyIns = (String) m.next();
         insurance ins = (insurance)  insMap.get(keyIns);
         if(ins.getInsuranceId() == 0) {
         ins.setOwnerId(Integer.toString(owner.getOwnerId()));
         iDAO.insertInsurance(ins, owner.getOwnerId()); 
         } 
         else 
         {
           iDAO.updateInsurance(ins);
         }
         }
         
  
       }
 
       }
  private static void logReceiptId(amuseRideDAO rDAO,amuseRide ride,DfbsOwner owner,int receiptId) throws Exception {
    
    
   rDAO.insertPermitTransaction(ride,receiptId,owner);
    
	}
  
  private static void sendEmailInsp( DfbsOwner owner,
                            ApplicationContacts contacts,amuseRide ride,ShoppingCart cart) throws Exception
    {
            try {
               
              HsMailer mail = new HsMailer();
              String[] emailTo;
                String[] emailTo1 ={owner.getOwnerEmail(),contacts.getContact1Email(),contacts.getContact2Email()};
                String[] emailTo2 = {contacts.getContact1Email(),contacts.getContact2Email()};
              if (owner.getOwnerEmail() !=null)
              {
              emailTo = emailTo1;
              }
              else {
                emailTo = emailTo2;
              } 
             //  String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
              String[] bccTo = {"nnimmagadda@dhs.in.gov"};
             
              StringBuffer sb = new StringBuffer();
              sb.append(owner.getOwnerDBA()+ ": submitted following online applications.").append("\n\r");  
              sb.append("Serial Number of the ride applied for New / Renewal of Amusement ride is:"+ride.getSerialNumber() ).append("\n\r");
              sb.append("$"+ride.getAppFee() + " is paid for this transaction and online reference number is "+cart.getReceiptId()).append("\n\r");
              sb.append("Use this link to view information:").append("\n\r");
              sb.append("https://oas.dhs.in.gov/dfbs/amuseRide/owner.do").append("\n\r");
                   StringBuffer sub = new StringBuffer();
              sub.append(" From IDHS  Amusement online transaction alert for online system ");
              
              mail.createMail("rides_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
             mail.sendAndClose();
              } 
              catch(Exception e) 
              {
                HsMailer mail = new HsMailer();
              mail.createMail("rides_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email ride check out"+ride.getSerialNumber());
              mail.sendAndClose();
              }
    }
 
  
}
  



