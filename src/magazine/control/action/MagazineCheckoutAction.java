package magazine.control.action;
import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;
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



public class MagazineCheckoutAction  extends ControlAction
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
         DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
      //  DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_CONTACT);
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
         String fileStatus ="Y";
          DfbsOwner errorOwner=null;
          DfbsPermit errorPermit=null;
         Map mapOwner = cart.getOwnerMap();
         Set ownerkeys = mapOwner.keySet();
         Iterator m = ownerkeys.iterator();
         String ownerLetter ="";
          while(m.hasNext())
          {  String keyOwner = (String) m.next();
             DfbsOwner owner = (DfbsOwner) mapOwner.get(keyOwner); 
            ownerLetter= owner.getOwnerLastName();
            
           if (owner.getFileListCount()==0) {
             fileStatus = "N";
           }
           Map mapPermit = owner.getPermitsMap();
            Set permitkeys = mapPermit.keySet();
            Iterator n = permitkeys.iterator();
           while(n.hasNext())
          { 
          String keyPermit = (String) n.next();
          DfbsPermit permit = (DfbsPermit) mapPermit.get(keyPermit); 
         // sendEmailInsp(request,"",permit,contacts);
          }
          }
          session.setAttribute("FILE_UPLOAD_STATUS",fileStatus);
          if(fileStatus.equals("Y"))
          {
          processCheckout(context,request,response,cart);
          }
          else
         {      
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do?method=filter&filter=byLetter&letter="+ownerLetter.substring(0,1));
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
         
        }
        return null;
        }
         else if(method.equals("check"))
       { ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
         int receiptId = Integer.parseInt(request.getParameter("receiptId"));
         cart.setReceiptId(receiptId);
         cart.setPaymentType("check");
         applicationSpecificProcessing(pDAO,oDAO, cart,receiptId,request,contacts);
         setOptions(request,dfbsUtilityDAO);
         ShoppingCart cartNew = new ShoppingCart(); 
         session.setAttribute("SHOPPING_CART",cartNew);
         shopCartForm.setReceiptId(0);
         return mapping.findForward("start");
       } 
         else if(method.equals("testEmail"))
       { 
       String ownerEmail ="";
       String contactEmail ="";
       ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
       List owners =cart.getOwnerList();
      Iterator j = owners.iterator();
      while(j.hasNext())
    {
      DfbsOwner owner = (DfbsOwner)  j.next();
      ownerEmail=owner.getOwnerEmail();
      Map mapContact = owner.getContactsMap();
          Set contactkeys = mapContact.keySet();
          Iterator k = contactkeys.iterator();
          while(k.hasNext())
           { 
            String key = (String) k.next();
           DfbsContact contact = (DfbsContact) mapContact.get(key); 
            contactEmail=contact.getPersonEmail();
           }
       } 
        session.setAttribute("OWNER_EMAIL",ownerEmail);
       session.setAttribute("CONTACT_EMAIL",contactEmail);
      sendEmailCheckOut(123,Double.toString(cart.getAmount()),contacts,session);
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
        Set permitkeys = mapPermit.keySet();
        Iterator n = permitkeys.iterator();
         while(n.hasNext())
         { StringBuffer sku = new StringBuffer("sku:");
         String keyPermit = (String) n.next();
         DfbsPermit permit = (DfbsPermit) mapPermit.get(keyPermit); 
         ItemInfo item = new ItemInfo();
         sku.append("permit");
         item.setSku(sku.toString());
         StringBuffer sb = new StringBuffer();
        // sb.append(owner.getPermitsCount());
         sb.append(" permit");
         sb.append(" ");
         sb.append(permit.getMagPermitKey().toUpperCase());
         item.setDescription(sb.toString());
         item.setQuantity(new Integer(1));
         item.setUnitCost(new BigDecimal(permit.getAmount()));
         itemsInfo[++j] = item;
       
        }
    
    
   }
	cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/magazine/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
   cart.setApplicationName(context.getInitParameter("application_name_magazine"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(cart);
   
	 session.setAttribute("MAGAZINE_CHECKOUT_TOKEN", token);
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
  redirectUrl.append(request.getContextPath()).append("/magazine/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ;

 } 
 
 private static void applicationSpecificProcessing(DfbsPermitDAO pDAO,DfbsOwnerDAO oDAO,
      ShoppingCart cart, int receiptId,HttpServletRequest request,ApplicationContacts contacts) throws Exception {
    
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
      oDAO.createBackUpData(owner.getOwnerId(),"xx");
       oDAO.updateOwner(owner);
      }
   // contacts
   
   /*
          Map mapContact = owner.getContactsMap();
          Set contactkeys = mapContact.keySet();
          Iterator k = contactkeys.iterator();
          while(k.hasNext())
           { 
           String key = (String) k.next();
           DfbsContact contact = (DfbsContact) mapContact.get(key); 
            if(contact.isNew())
           { 
            oDAO.insertContact(contact,owner.getOwnerId());
            }
            else
            {oDAO.updateContact(contact);
            }
           }
   */
   
   //permits
      List permits = owner.getPermits();
      Iterator i = permits.iterator();
    
        while(i.hasNext())
        {
         DfbsPermit permit = (DfbsPermit)  i.next();
          if(permit.isNew())
           {
            pDAO.insertPermit(permit,owner.getOwnerId(),"");
            if (receiptId >0 )
            {
             pDAO.insertPermitTransaction(permit,owner,cart,owner.getOwnerId());
            }
            }
            else
            {oDAO.createBackUpData(0,permit.getMagIdNumber());
             permit.setMagExpStatus("");
             permit.setMagIssueDate(null);
             pDAO.updatePermit(permit);
              if (receiptId >0 )
            {
             pDAO.insertPermitTransaction(permit,owner,cart,owner.getOwnerId());
            }
             }
              if (receiptId >0 )
            {
             String inspEmail=pDAO.findEmail(permit.getMagCounty());
             sendEmailInsp(request,inspEmail,permit,contacts);
            }
          } 
      
    
    }  
        
    
  }
  
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS);
    List states2 = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS2);
    List mags =  uDAO.getOptions(DfbsSQL.SELECT_MAGAZINE_TYPE_OPTIONS);
     List counties =  uDAO.getOptions(DfbsSQL.SELECT_COUNTY_LIST_OPTIONS);
     List fds =  uDAO.getOptions(DfbsSQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("MAGAZINE_STATE_OPTIONS",states2);
     request.setAttribute("MAGAZINE_TYPE_OPTIONS",mags);
     request.setAttribute("MAGAZINE_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
     
     
    
    
 }  
 
 private static void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsPermit permit,
                          ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
         //  String[] emailTo = {contacts.getContact1Email(),inspEmail,contacts.getContact2Email()};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           
          
            StringBuffer sb = new StringBuffer();
            sb.append( " Identification Number: "+ permit.getMagIdNumber() + " is ready for inspection").append("\n\r");
            sb.append("Contact Person: "+permit.getMagContactPerson() + ", Telephone: "+ permit.getMagPhone() + "Email : "+permit.getMagEmail()).append("\n\r");
            sb.append(" to view details log on https://oas.dhs.in.gov/dfbs/main/main.do?method=backToMain \n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append(" From explosive magazine pre-checkout ");
          
            mail.createMail(contacts.getContact1Email()+",",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From explosive magazine pre-chekout ","magazineCheckoutAction");
            mail1.sendAndClose();
            }
  }
  
   private static void sendEmailCheckOut(int receiptId,String amount,ApplicationContacts contacts,HttpSession session) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String ownerEmail =(String) session.getAttribute("OWNER_EMAIL");
            String contactEmail =(String) session.getAttribute("CONTACT_EMAIL");
            //String[] emailTo = {ownerEmail,contactEmail,contacts.getContact1Email(),contacts.getContact2Email()};
             String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo =  {"nnimmagadda@dhs.in.gov"}  ;    
          
            StringBuffer sb = new StringBuffer("\r\nRegulated Explosive Magazine Permit online transaction #");
            sb.append(" Thank you for using IDHS online application.\n\rYour transaction confirmation number is:" );
            sb.append(receiptId);
            sb.append(" \n\rAmount paid in the transaction$:" );
            sb.append(amount);
            sb.append("\n\rYou have successfully submitted your application.if you need additional information about this transaction ");
            sb.append(" contact this office via email to " + contacts.getContact1Email());
            
            
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Regulated Explosive Magazine Permit Online Transaction  ");
          
           
            mail.createMail("magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
           mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Regulated Explosive Magazine Permit Online Transaction error  ","magazineCompleteCheckoutAction");
           mail1.sendAndClose();           
         }
  }

}  



