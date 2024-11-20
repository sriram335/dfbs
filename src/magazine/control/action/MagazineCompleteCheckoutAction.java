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


 
public class MagazineCompleteCheckoutAction  extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        DfbsDataMap dmap2 = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
        MagazineUsersDAO uDAO = ( MagazineUsersDAO) dmapNew.getHsDAO(DfbsDataMap2.MAG_USER);
          
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          MagazineUsers magUser = (MagazineUsers) session.getAttribute("MAGAZINE_USER");
        if(cart == null ) 
        {
          throw new Exception("NO_SHOPPING_CART_IN_SESSION");
        }
        if (method == null)
        {
         if (cart.getTotalPermits() > 0)
        {
        completeCheckout(context,request,response,cart,contacts,session,oDAO,pDAO,magUser,uDAO);
          return mapping.findForward("start");
        }
        else
        {
        
        return null;
        }
        }
        else if(method.equals("test")) {
          cart.setPaymentType("CC");
          applicationSpecificProcessing(pDAO,oDAO,cart,19999000,session,request,contacts,magUser,uDAO);
          sendEmailCheckOut(19999000,Double.toString(cart.getAmount()),contacts,session);
          session.setAttribute("SHOPPING_CART",null);
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
  
 
 
 private static void completeCheckout(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        ShoppingCart cart,ApplicationContacts contacts,HttpSession session,
        DfbsOwnerDAO oDAO,DfbsPermitDAO pDAO,MagazineUsers magUser,MagazineUsersDAO uDAO) throws Exception
 {
   
   String checkoutToken = (String)  
   session.getAttribute("MAGAZINE_CHECKOUT_TOKEN");
   if(checkoutToken == null || ! checkoutToken.equals(cart.getCheckoutId())) 
   {
     throw new Exception("ERROR_MAGAZINE_CHECKOUT_TOKEN");
   }
   
  
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

	  OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
	    
	 
    StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
    
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
    
     
      
        
        
      int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
       cart.setReceiptId(receiptId);
       cart.setPaymentType("CC");
       applicationSpecificProcessing(pDAO,oDAO,cart,receiptId,session,request,contacts,magUser,uDAO);
       sendEmailCheckOut(receiptId,Double.toString(cart.getAmount()),contacts,session);
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

  
  
  
 private static void applicationSpecificProcessing(DfbsPermitDAO pDAO,DfbsOwnerDAO oDAO,
       ShoppingCart cart, int receiptId,HttpSession session,HttpServletRequest request,
            ApplicationContacts contacts,MagazineUsers magUser,MagazineUsersDAO uDAO) throws Exception 
  
       {  
       List owners =cart.getOwnerList();
      Iterator j = owners.iterator();
      String ownerEmail ="";
      while(j.hasNext())
    {  
      DfbsOwner owner = (DfbsOwner)  j.next();
      if (owner.getOwnerEmail() !=null && owner.getOwnerEmail().length() >5)
      { 
        ownerEmail=owner.getOwnerEmail();
      }
      if(owner.isNew())
      {
      oDAO.insertOwner(owner);
       }
      else
      { 
      oDAO.createBackUpData(owner.getOwnerId(),"xx");
       oDAO.updateOwner(owner);
      }
       int personId = uDAO.addNewMagUser(owner.getOwnerId());
        magUser= uDAO.selectMagUser(personId);
        sendEmailNewUser(magUser,"New",ownerEmail,contacts);
      // contacts removed as per Donna Tolan 10/2011
      /*     Map mapContact = owner.getContactsMap();
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
            {
             oDAO.updateContact(contact);
            }
            if (contact.getPersonEmail()!=null && contact.getPersonEmail().length() >5)
            {
              contactEmail=contact.getPersonEmail();
            } 
               if (magUser ==null || magUser.getUserLoginId()==null) {
                  
                int personId = uDAO.addNewMagUser(contact.getPersonId());
                magUser= uDAO.selectMagUser(personId);
                 sendEmailNewUser(magUser,"New",contactEmail,contacts);
               }
           } */
   //permits
      List permits = owner.getPermits();
      Iterator i = permits.iterator();
    
    
        while(i.hasNext())
        {
         DfbsPermit permit = (DfbsPermit)  i.next();
          if(permit.isNew())
           {
            pDAO.insertPermit(permit,owner.getOwnerId(),"New");
             pDAO.insertPermitTransaction(permit,owner,cart,receiptId);
         }
            else
            {
             oDAO.createBackUpData(0,permit.getMagIdNumber());
             permit.setMagExpStatus("");
             permit.setMagIssueDate(null);
             permit.setMagOnlineStatus("New");
             pDAO.updatePermit(permit);
              pDAO.insertPermitTransaction(permit,owner,cart,receiptId);
            }
            String inspEmail=pDAO.findEmail(permit.getMagCounty());
             sendEmailInsp(request,permit,contacts,inspEmail); 
          } 
      
     
       session.setAttribute("OWNER_EMAIL",ownerEmail);
     //  session.setAttribute("CONTACT_EMAIL",contactEmail);
      sendEmailCheckOut(receiptId,Double.toString(cart.getAmount()),contacts,session);
      
    }  
    
  }
  
       
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/magazine/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
  private static void sendEmailCheckOut(int receiptId,String amount,ApplicationContacts contacts,HttpSession session) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String ownerEmail =(String) session.getAttribute("OWNER_EMAIL");
           String contactEmail =(String) session.getAttribute("CONTACT_EMAIL");
              String emailCheck ="Y"  ;
           String[] emailTo ={contacts.getContact1Email()};
            
           if (contactEmail !=null &&contactEmail.length() >5)
           {
             String[] emailTo1 = {contactEmail};
             emailTo = emailTo1;
           } 
           if (ownerEmail !=null &&ownerEmail.length() >5)
           {
             String[] emailTo2 = {ownerEmail};
             emailTo = emailTo2;
           }
            
          if ((ownerEmail !=null &&ownerEmail.length() >5 )&&(contactEmail !=null &&contactEmail.length() >5))
           {
             String[] emailTo3 = {ownerEmail,contactEmail};
             emailTo = emailTo3;
           }
             if ((ownerEmail ==null || ownerEmail.length() <5 )&&(contactEmail ==null || contactEmail.length() <5))
          {
              emailCheck ="N";
           }  
            String[] bccTo = {"nnimmagadda@dhs.in.gov",contacts.getContact1Email()};
          //  String[] emailTo = {ownerEmail,contactEmail,contacts.getContact1Email(),contacts.getContact2Email()};
           //String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           
            StringBuffer sb = new StringBuffer("\r\nRegulated Explosive Magazine Permit online transaction #");
            sb.append(" Thank you for using IDHS online application.\n\rYour transaction confirmation number is:" );
            sb.append(receiptId);
            sb.append(" \n\rAmount paid in the transaction$:" );
            sb.append(amount);
            sb.append("\n\rYou have successfully submitted your application.if you need additional information about this transaction ");
            sb.append(" contact this office via email to " + contacts.getContact1Email());
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Regulated Explosive Magazine Permit Online Transaction  ");
          
            if(emailCheck.equals("Y"))
              {
              mail.createMail("magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
              }
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            String ownerEmail =(String) session.getAttribute("OWNER_EMAIL");
            String contactEmail =(String) session.getAttribute("CONTACT_EMAIL");
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Regulated Explosive Magazine Permit Online Transaction error  ",ownerEmail +":owner:contact:"+contactEmail+":dhs:"+contacts.getContact1Email());
            mail1.sendAndClose();    
           }
  }
  
private static void sendEmailInsp(HttpServletRequest request,DfbsPermit permit,
                          ApplicationContacts contacts,String inspEmail) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
         // String[] emailTo ={"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
         
            StringBuffer sb = new StringBuffer();
            sb.append(" Identification Number: "+ permit.getMagIdNumber() + " is ready for inspection").append("\n\r");
            sb.append("Contact Person: "+permit.getMagContactPerson() + ", Telephone: "+ permit.getMagPhone() + " Email : "+permit.getMagEmail()).append("\n\r");
            sb.append(" to view details log on https://oas.dhs.in.gov/dfbs/main/main.do?method=backToMain \n\r");
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_magazine_list_by_county.rdf&p_id="+permit.getMagIdNumber()).append("\n\r");
            StringBuffer sub = new StringBuffer();
            sub.append(" From explosive magazine application received ");
           
          
            mail.createMail(contacts.getContact1Email()+",",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire explosive application received  ","magazineCompleteCheckoutAction");
            mail1.sendAndClose(); 
            }
  }

private static void sendEmailNewUser(MagazineUsers magUser,String userType,String userEmail,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {userEmail,contacts.getContact1Email()};
           //String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n This is to confirm that, we have approved new user for your company.");
            sb.append("\r\n Indiana Department of Homeland Security(IDHS) informs you that the following user is added as additional user for your company.");
            sb.append("\r\n This user will be able to use this user name and password to add/update his/her company information/magazine(s) information , print permit(s) along with other features in IDHS online Magazine application at our web site on behalf of your company.");
            sb.append("\r\n name=");
            sb.append(magUser.getUserLastName());
            sb.append("\r\n user Login ID=");
             sb.append(magUser.getUserLoginId()).append(".");
            if ( userType.equals("New"))
            {
              sb.append("\r\n password=");
             sb.append(magUser.getUserPassword());
               sb.append("\r\n If you have questions or concerns about this email, please contact "+contacts.getContact1Email()+", with details.Do not reply to this message.");
            }
             if ( userType.equals("Old"))
            {
              sb.append("\r\n If this user does not belong to your organization and he is not a authorized user, please contact "+contacts.getContact1Name()+" at "+contacts.getContact1Email()+"/"+contacts.getContact1Phone()+" or "+contacts.getContact1Name()+" at " +contacts.getContact2Email()+"/"+contacts.getContact1Phone() +" with details.Do not reply to this message.");
             
            }
            
            
            StringBuffer sub = new StringBuffer();
            sub.append("IDHS Magazine Application new user request processed. ");
          
          
          
            mail.createMail("magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","MagUserAction");
            mail1.sendAndClose();
            }
  }

 
}
  


