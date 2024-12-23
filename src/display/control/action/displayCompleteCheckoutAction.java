package display.control.action;

import display.control.*;
import display.to.*;
import display.data.*;
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
import display.control.form.*;
import main.to.*;
import main.data.*;

 
public class displayCompleteCheckoutAction  extends ControlAction
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
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_CONTACT);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        if(cart == null ) 
        {
          throw new Exception("NO_SHOPPING_CART_IN_SESSION");
        }
         if (cart.getTotalPermits() > 0)
        {
        completeCheckout(context,request,response,cart,contacts,session);
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
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
       return mapping.findForward("error");
      
    }
        
      
        
      
  }
  
 
 
 private static void completeCheckout(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        ShoppingCart cart,ApplicationContacts contacts,HttpSession session) throws Exception
 {
   
   String checkoutToken = (String)  
   session.getAttribute("DISPLAY_CHECKOUT_TOKEN");
   if(checkoutToken == null || ! checkoutToken.equals(cart.getCheckoutId())) 
   {
     throw new Exception("ERROR_DISPLAY_CHECKOUT_TOKEN");
   }
   
  
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

	  OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
	    
	 
    StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
    homeUrl.append("<a href='");
    homeUrl.append(request.getContextPath()).append("/display/display.do'>");
    homeUrl.append("Back to Radio Active Waste Transport Permits </a>");
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
    
     
      
      DfbsDataMap dmap2 = (DfbsDataMap)
      context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_CONTACT); 
      int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
       cart.setReceiptId(receiptId);
       cart.setPaymentType("CC");
       applicationSpecificProcessing(pDAO,oDAO,cart,cDAO,receiptId,session,request,contacts);
     //  sendEmailCheckOut(receiptId,Double.toString(cart.getAmount()),contacts,session);
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
		  return;
		}
      
    // https://test.secure.in.gov/apps/kwikekard/checkout/servlet/receipt 

	}

  
  
  
 private static void applicationSpecificProcessing(DfbsDisplayDAO pDAO,DfbsOwnerDAO oDAO,
       ShoppingCart cart,DfbsContactDAO cDAO, int receiptId,HttpSession session,HttpServletRequest request,ApplicationContacts contacts) throws Exception 
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
   //displays
      List displays = owner.getPermits();
      Iterator i = displays.iterator();
    
    
        while(i.hasNext())
        {
         DfbsDisplay display = (DfbsDisplay)  i.next();
         
            pDAO.insertPermit(display,owner.getOwnerId(),"New");
            sendEmailCheckOut(receiptId,Double.toString(cart.getAmount()),contacts,session,display);
             pDAO.insertPermitTransaction(display,owner,cart,receiptId);
             
      // display dates
                Map mapDates = display.getDisplayDatesMap();
                Set datekeys = mapDates.keySet();
                Iterator d = datekeys.iterator();
                while(d.hasNext())
                 {  String key = (String) d.next();
                    DisplayDates displayDate = (DisplayDates) mapDates.get(key); 
                      displayDate.setDisplayId(display.getDisplayIdNumber());
                    pDAO.insertPermitDate(displayDate);
                 }
           
            sendEmailInsp(request,display,contacts); 
          } 
      
       session.setAttribute("OWNER_EMAIL",ownerEmail);
       session.setAttribute("CONTACT_EMAIL",contactEmail);
     //  sendEmailCheckOut(receiptId,Double.toString(cart.getAmount()),contacts,session);
       
    }  
    
  }
  
       
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/display/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
 
  
  private static void sendEmailCheckOut(int receiptId,String amount,ApplicationContacts contacts,HttpSession session,DfbsDisplay display) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String ownerEmail =(String) session.getAttribute("OWNER_EMAIL");
            String contactEmail =(String) session.getAttribute("CONTACT_EMAIL");
            
             String[] emailTo ={"nnimmagadda@dhs.in.gov"};
           if (contactEmail !=null &&contactEmail.length() >5)
           {
             String[] emailTo1 = {contacts.getContact1Email(),contacts.getContact2Email(),contactEmail};
             emailTo = emailTo1;
           }
           if (ownerEmail !=null &&ownerEmail.length() >5)
           {
             String[] emailTo2 = {contacts.getContact1Email(),contacts.getContact2Email(),ownerEmail};
             emailTo = emailTo2;
           }
          if ((ownerEmail !=null &&ownerEmail.length() >5 )&&(contactEmail !=null &&contactEmail.length() >5))
           {
             String[] emailTo3 = {contacts.getContact1Email(),contacts.getContact2Email(),ownerEmail,contactEmail};
             emailTo = emailTo3;
           }
             if ((ownerEmail ==null || ownerEmail.length() <5 )&&(contactEmail ==null || contactEmail.length() >5))
           {
             String[] emailTo4 = {contacts.getContact1Email(),contacts.getContact2Email()};
             emailTo = emailTo4;
           }  
            String[] bccTo =  {"nnimmagadda@dhs.in.gov"}  ;    
          
            StringBuffer sb = new StringBuffer("\r\nFire Display Permit online transaction for:"+display.getDisplayIdNumber());
            sb.append(" Thank you for using IDHS online application.\n\rYour transaction confirmation number is:" );
            sb.append(receiptId);
            sb.append(" \n\rAmount paid in the transaction$:" );
            sb.append(amount);
            sb.append("\n\rYou have successfully submitted your application.if you need additional information about this transaction ");
            sb.append(" contact this office via email to " + contacts.getContact1Email());
            
            
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Fire Display Permit Online Transaction  ");
          
           
            mail.createMail("display_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("display_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Fire Display Permit Online Transaction error  ","displayCompleteCheckoutAction");
            mail1.sendAndClose();            }
  }
  

 
private static void sendEmailInsp(HttpServletRequest request,DfbsDisplay display,
                          ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
           String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
          // String[] emailTo ={"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
           
            StringBuffer sb = new StringBuffer();
            sb.append( display.getDisplayIdNumber() + " is ready for inspection").append("\n\r");
            sb.append("County :"+ display.getDisplayCounty()).append("\n\r");
            sb.append("Use this link to approve display:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/display/start.do").append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            StringBuffer sub = new StringBuffer();
            sub.append(" From fire display inspection ready alert ");
          
            mail.createMail(contacts.getContact1Email()+",",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("display_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire display inspection ready alert error  ",display.getDisplayIdNumber()+":displayCompleteCheckoutAction");
            mail1.sendAndClose(); 
            }
  }


 
}
  



