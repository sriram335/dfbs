package childcare.control.action;

import childcare.control.form.*;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import childcare.to.*;


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
import childcare.data.*;
import main.data.*;
import main.to.*;
public class ChildcareCheckoutAction extends ControlAction
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
        
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_CONTACT); 
         String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        ApplicationContacts contacts = ( ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        if (method == null)
        {
        if(cart == null) 
        {
          throw new Exception("Shopping Cart not available");
        }
        if (cart.getTotalPermits() > 0 )
         {
          processCheckout(context,request,response,cart);
          return null;
         }
         else
         { 
          return null;
         }
        }
        else if(method.equals("check"))
       {  String receiptId  = request.getParameter("receiptId");
             applicationSpecificProcessing(pDAO,oDAO,cart,cDAO,Integer.parseInt(receiptId),session,request,contacts);
            session.setAttribute("DFBS_OWNER",null);
          session.setAttribute("SHOPPING_CART",null);
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
        ShoppingCart shopCart) throws Exception
 {
	HttpSession session = request.getSession();
	 OrderInfo cart = new OrderInfo();
   ItemInfo[] itemsInfo = new ItemInfo[shopCart.getTotalPermits()];
         int j = -1;
   Map mapOwner = shopCart.getOwnerMap();
    Set ownerkeys = mapOwner.keySet();
    Iterator m = ownerkeys.iterator();
     while(m.hasNext())
     { 
     String keyOwner = (String) m.next();
     DfbsOwner owner = (DfbsOwner) mapOwner.get(keyOwner); 
     Address userAddress = new Address();
     userAddress.setAddress1(owner.getOwnerAddress1());
     userAddress.setAddress2(owner.getOwnerAddress2());
     userAddress.setCity(owner.getOwnerCity());
     userAddress.setState(owner.getOwnerState());
     userAddress.setZipCode(owner.getOwnerZip());
        Map mapPermit = owner.getChildCaresMap();
        Set permitkeys = mapPermit.keySet();
        Iterator n = permitkeys.iterator();
        
        
         while(n.hasNext())
         { StringBuffer sku = new StringBuffer("sku:");
         String keyPermit = (String) n.next();
         DfbsChildcarePermit permit = (DfbsChildcarePermit) mapPermit.get(keyPermit); 
         ItemInfo item = new ItemInfo();
         sku.append("permit");
         item.setSku(sku.toString());
         StringBuffer sb = new StringBuffer();
         sb.append(" permit");
         sb.append(" ");
         sb.append(permit.getApplicationKey().toUpperCase());
         item.setDescription(sb.toString());
         item.setQuantity(new Integer(1));
         item.setUnitCost(new BigDecimal(permit.getAmount()));
         itemsInfo[++j] = item;
       
        }
    
    
   }
	cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/childCare/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
    cart.setApplicationName(context.getInitParameter("application_name_childcare"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(cart);
   
	 session.setAttribute("CHILDCARE_CHECKOUT_TOKEN", token);
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
 
 
 private static void processCheckoutTest(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        DfbsOwner owner,HttpSession session) throws Exception
 {
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/childCare/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ;
 }
 
 private static void processCheckoutNoFee(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        DfbsOwner owner,HttpSession session) throws Exception
 {
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/childCare/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ; 
 }
  private static void applicationSpecificProcessing(DfbsChildcarePermitDAO pDAO,DfbsChildcareOwnerDAO oDAO,
       ShoppingCart cart,DfbsContactDAO cDAO, int receiptId,HttpSession session,
       HttpServletRequest request,ApplicationContacts contacts) throws Exception 
       { 
    
   
      List owners =cart.getOwnerList();
      Iterator j = owners.iterator();
      String ownerEmail ="";
      String contactEmail ="";
      while(j.hasNext())
    { 
      DfbsOwner owner = (DfbsOwner)  j.next();
      if (owner.getOwnerEmail().length() >5)
      {
        ownerEmail=owner.getOwnerEmail();
      }
      if(owner.isNew())
      {
      oDAO.insertOwner(owner);
      }
      else
      {
       oDAO.updateOwner(owner);
      }
      // contacts
   
     //
          Map mapContact = owner.getContactsMap();
          Set contactkeys = mapContact.keySet();
          Iterator k = contactkeys.iterator();
          while(k.hasNext())
           { 
           String key = (String) k.next();
           DfbsContact contact = (DfbsContact) mapContact.get(key); 
            if(contact.isNew())
           { 
            cDAO.insertContact(contact,owner.getOwnerId());
            }
            else
            {
             cDAO.updateContact(contact);
               
            }
            if (contact.getPersonEmail().length() >5)
            {
              contactEmail=contact.getPersonEmail();
            }
           }
   //permits
      List permits = owner.getChildCares();
      Iterator i = permits.iterator();
    
    
        while(i.hasNext())
        {
         DfbsChildcarePermit permit = (DfbsChildcarePermit)  i.next();
            int facilityCount=  pDAO.selectFacilityNewOld(permit.getPermitNumber());
          if(facilityCount ==0)
           {
            pDAO.insertPermit(permit,owner.getOwnerId(),"New");
            permit.setReceiptId(receiptId);
             pDAO.insertPermitTransaction(permit,owner);
            }
            else
            {
             pDAO.updatePermit(permit);
             permit.setReceiptId(receiptId);
             if( receiptId >0)
             {
              pDAO.insertPermitTransaction(permit,owner);
             }
            }
            String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
            int rmNewStatus= pDAO.childCareNewStatus(permit.getPermitNumber());
            String rmNewStatusString="OLD";
            if (rmNewStatus == 0)
            {
              rmNewStatusString="NEW";
            }
           sendEmailInsp(request,inspEmail,permit,contacts,rmNewStatusString,pDAO); 
          } 
      
       session.setAttribute("OWNER_EMAIL",ownerEmail);
       session.setAttribute("CONTACT_EMAIL",contactEmail);
     //  sendEmailCheckOut(receiptId,Double.toString(cart.getAmount()),contacts,session);
       
    }  
    
  }
  
  
  
   private static void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsChildcarePermit permit,
                          ApplicationContacts contacts,String rmNewStatus,DfbsChildcarePermitDAO pDAO) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          //  String[] emailTo ={"nnimmagadda@dhs.in.gov"};
           String currentYear = pDAO.selectCurrentYear();
           int charPosition =inspEmail.indexOf("@");
           String userId= inspEmail.substring(0,charPosition);
           int inspectorId =pDAO.selectInspector(inspEmail);
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do?method=backToMain").append("\n\r");
             sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            if (rmNewStatus.equals("NEW"))
            {
               sb.append("This is a new facility, immediate inspection is requested.").append("\n\r");
            }
            
            StringBuffer sub = new StringBuffer();
           
            if (rmNewStatus.equals("NEW"))
            {
               sub.append("!!NEW FACILITY!!");
            }
             sub.append(" From fire registered ministry inspection ready alert ");
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail("childCare_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email inspector check out",inspEmail);
            mail.sendAndClose();
            }
  }
 
  
  
 
}
  



