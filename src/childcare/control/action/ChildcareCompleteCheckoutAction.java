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
public class ChildcareCompleteCheckoutAction  extends ControlAction
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
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
         ApplicationSecurityDAO seDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
          ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        if(cart == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
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
   session.getAttribute("CHILDCARE_CHECKOUT_TOKEN");
   if(checkoutToken == null || ! checkoutToken.equals(cart.getCheckoutId())) 
   {
     throw new Exception("ERROR_OWNER_CHECKOUT_TOKEN");
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
    
     
      
      DfbsDataMap dmap2 = (DfbsDataMap)
      context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
      DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_CONTACT); 
      int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
       cart.setReceiptId(receiptId);
       cart.setPaymentType("CC");
        
      applicationSpecificProcessing(pDAO,oDAO,cart,cDAO,receiptId,session,request,contacts);
     
      
      session.setAttribute("DFBS_OWNER",null);
      session.setAttribute("DFBS_OWNER_APPLICATION",null);
     
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


  
  
  private static void applicationSpecificProcessing(DfbsChildcarePermitDAO pDAO,DfbsChildcareOwnerDAO oDAO,
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
   //permits
      List permits = owner.getChildCares();
      Iterator i = permits.iterator();
    
    
        while(i.hasNext())
        {
         DfbsChildcarePermit permit = (DfbsChildcarePermit)  i.next();
          if(permit.isNew())
           {
            pDAO.insertPermit(permit,owner.getOwnerId(),"New");
            permit.setReceiptId(receiptId);
             pDAO.insertPermitTransaction(permit,owner);
             
         }
            else
            {
             pDAO.updatePermit(permit);
             permit.setReceiptId(receiptId);
              pDAO.insertPermitTransaction(permit,owner);
            }
            String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
             int rmNewStatus= pDAO.childCareNewStatus(permit.getPermitNumber());
            String rmNewStatusString="OLD";
            if (rmNewStatus == 0)
            {
              rmNewStatusString="NEW";
            }
             sendEmailInsp(request,permit,contacts,receiptId,cart.getAmount(),session,inspEmail,rmNewStatusString); 
            
          } 
      
       session.setAttribute("OWNER_EMAIL",ownerEmail);
       session.setAttribute("CONTACT_EMAIL",contactEmail);
       
    }  
    
  }
  
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/childCare/application/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
  private static void logReceiptId(DfbsChildcarePermitDAO pDAO,DfbsOwner owner,int receiptId) throws Exception {
    
    
    List permits = owner.getChildCares();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsChildcarePermit permit = (DfbsChildcarePermit) i.next();
      permit.setReceiptId(receiptId);
      pDAO.insertPermitTransaction(permit,owner);
    }
  
    
	}
  
  
 

 
 
private static void sendEmailInsp(HttpServletRequest request,DfbsChildcarePermit permit,
                          ApplicationContacts contacts, int receiptId,double amount,HttpSession session, String inspEmail,String rmNewStatus) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
           String contactEmail =(String) session.getAttribute("CONTACT_EMAIL");
           String[] emailTo ={"nnimmagadda@dhs.in.gov"};
           if (contactEmail !=null &&contactEmail.length() >5)
           {// insp email removed as per Jeff Short on 10/13/2010
             String[] emailTo1 = {contacts.getContact1Email(),contacts.getContact2Email(),contactEmail};
             emailTo = emailTo1;
           }
           else
           {
             String[] emailTo2 = {contacts.getContact1Email(),contacts.getContact2Email()};
             emailTo = emailTo2;
           }  
            String[] bccTo = {"nnimmagadda@dhs.in.gov","cclouse@dhs.in.gov"};
            
           
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Registered Ministries permit online transaction #");
            sb.append(receiptId);
            sb.append(" : ");
            sb.append(amount);
            sb.append(". Use this link to log in to the system").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/childCare/start.do").append("\n\r"); 
             sb.append("Use this link view details of the registered ministry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_day_care_tobe_inspected.rdf&p_id=" + permit.getPermitNumber()).append("\n\r");
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
            
            mail.createMail("childCare_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail("childCare_online@dhs.in.gov","nnimmagadda@dhs.in.gov",contacts.getContact1Email(),"error email inspector check out "+permit.getPermitNumber());
            mail.sendAndClose();
            }
  }
 
  
}
  



