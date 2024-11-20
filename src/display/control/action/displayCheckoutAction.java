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
import display.control.form.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import main.to.*;
import main.data.*;



public class displayCheckoutAction  extends ControlAction
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
        ShoppingCartForm shopCartForm = (ShoppingCartForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
         DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_CONTACT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        if (method == null)
        {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        if(cart == null) 
        {
          throw new Exception("Shopping Cart not available");
        
        }
        if (cart.getTotalPermits() > 0 )
         {
        if (validateApplication(oDAO,pDAO,cDAO,cart,session))
         {
          processCheckout(context,request,response,cart);
          return null;
         }
         else
         {session.setAttribute("APPLICATION_ERROR","true");
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do?method=backToPermit");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
         }
         else
         { 
          return null;
         }
        }
         else if(method.equals("check"))
       { ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
         int receiptId = Integer.parseInt(request.getParameter("receiptId"));
         cart.setReceiptId(receiptId);
         cart.setPaymentType("check");
         applicationSpecificProcessing(pDAO,oDAO,cDAO, cart,receiptId,request,contacts);
         setOptions(request,dfbsUtilityDAO);
         ShoppingCart cartNew = new ShoppingCart(); 
         session.setAttribute("SHOPPING_CART",cartNew);
         shopCartForm.setReceiptId(0);
         return mapping.findForward("start");
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
        Map mapPermit = owner.getPermitsMap();
        Set displaykeys = mapPermit.keySet();
        Iterator n = displaykeys.iterator();
        
        
         while(n.hasNext())
         { StringBuffer sku = new StringBuffer("sku:");
         String keyPermit = (String) n.next();
         DfbsDisplay display = (DfbsDisplay) mapPermit.get(keyPermit); 
         ItemInfo item = new ItemInfo();
         sku.append("display");
         item.setSku(sku.toString());
         StringBuffer sb = new StringBuffer();
         sb.append(" display");
         sb.append(" ");
         sb.append(display.getDisplayPermitKey().toUpperCase());
         item.setDescription(sb.toString());
         item.setQuantity(new Integer(1));
         item.setUnitCost(new BigDecimal(display.getAmount()));
         itemsInfo[++j] = item;
       
        }
    
    
   }
	cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/display/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
   cart.setApplicationName(context.getInitParameter("application_name_display"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(cart);
   
	 session.setAttribute("DISPLAY_CHECKOUT_TOKEN", token);
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
		  return ;
		}
  
  
  
  
 }
 
 private static void processCheckoutTest(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        ShoppingCart shopCart) throws Exception
 {
   HttpSession session = request.getSession();
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/display/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return;
 } 
 
 private static void applicationSpecificProcessing(DfbsDisplayDAO pDAO,DfbsOwnerDAO oDAO,
       DfbsContactDAO cDAO,ShoppingCart cart, int receiptId,HttpServletRequest request,ApplicationContacts contacts) throws Exception {
    
      List owners =cart.getOwnerList();
      Iterator j = owners.iterator();
      while(j.hasNext())
    {
      DfbsOwner owner = (DfbsOwner)  j.next();
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
           }
   
   
   //displays
      List displays = owner.getPermits();
      Iterator i = displays.iterator();
    
        while(i.hasNext())
        {
         DfbsDisplay display = (DfbsDisplay)  i.next();
            pDAO.insertPermit(display,owner.getOwnerId(),"");
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
            if (receiptId >0 )
            {
             pDAO.insertPermitTransaction(display,owner,cart,receiptId);
            }
            
            
           //  String inspEmail=pDAO.findEmail(display.getDisplayCounty());
             sendEmailInsp(request,display,contacts);
           
          } 
      
   
    }  
        
    
  }
  
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsDisplaySQL.SELECT_STATE_OPTIONS);
    List states2 = uDAO.getOptions(DfbsDisplaySQL.SELECT_STATE_OPTIONS2);
     List counties =  uDAO.getOptions(DfbsDisplaySQL.SELECT_COUNTY_LIST_OPTIONS);
     List fds =  uDAO.getOptions(DfbsDisplaySQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("DISPLAY_STATE_OPTIONS",states2);
     request.setAttribute("DISPLAY_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
     
     
    
    
 }  
 
 private static void sendEmailInsp(HttpServletRequest request,DfbsDisplay display,
                          ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
           String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
           // String[]  emailTo ={"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
           
            StringBuffer sb = new StringBuffer();
            sb.append( display.getDisplayIdNumber() + " is ready for inspection").append("\n\r");
            sb.append("County :"+ display.getDisplayCounty()).append("\n\r");
            sb.append("Use this link to approve display:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/display/display.do?method=update&displayKey="+display.getDisplayIdNumber()).append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            StringBuffer sub = new StringBuffer();
            sub.append(" From fire display inspection ready alert ");
           
            mail.createMail(contacts.getContact1Email()+",",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("display_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire display inspection ready alert error  ","displayCompleteCheckoutAction");
            mail1.sendAndClose(); 
            }
  }
  
  protected static boolean validateApplication(DfbsOwnerDAO oDAO,DfbsDisplayDAO pDAO,
                     DfbsContactDAO cDAO,ShoppingCart cart, HttpSession session) throws Exception
  {
     Map permitsErrorMap = new HashMap();
     Map mapOwners = cart.getOwnerMap();
     Set ownerkeys = mapOwners.keySet();
    Iterator k = ownerkeys.iterator();
     boolean noErrorDisplay =true;
     boolean noErrorO =true;
     boolean noErrorc =true;
     boolean noErrorUpload =true;
    while(k.hasNext())
    {String keyO = (String) k.next();
    DfbsOwner owner =(DfbsOwner) mapOwners.get(keyO);
    DfbsOwnerForm errorFormOwner = new DfbsOwnerForm();
    noErrorO = DfbsOwnerAction.validate(owner,errorFormOwner,session);
    owner.setOwnerError(noErrorO);
    DfbsContact contact = cDAO.selectContact(owner.getOwnerId());
    DfbsContactForm errorFormContact = new DfbsContactForm();
    noErrorc = DfbsContactAction.validate(contact,errorFormContact,session);
    contact.setContactError(noErrorc); 
    Map mapPermit = owner.getPermitsMap();
    Set permitkeys = mapPermit.keySet();
    Iterator i = permitkeys.iterator();
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsDisplay display = (DfbsDisplay) mapPermit.get(key); 
     DfbsDisplayForm errorFormDisplay = new DfbsDisplayForm();
     boolean noErrorPermitDate=true;
     boolean noErrorp = DfbsPermitAction.validate(display,errorFormDisplay,session,pDAO);
     display.setDisplayError(noErrorp);
     if (pDAO.CountFileList(display.getDisplayPermitKey()) == 0)
     {
        display.setDisplayUploadError(false);
         noErrorUpload = false;
     }
     if (noErrorp == false)
     {
       noErrorDisplay = false;
     }
     if (display.getDisplayDateError()==false)
     {
       noErrorDisplay = false;
       noErrorPermitDate =false;
     }
     
   }
    }
   
  return (noErrorO &&  noErrorDisplay && noErrorUpload) ? true : false ;
 
   
  }
}
  


