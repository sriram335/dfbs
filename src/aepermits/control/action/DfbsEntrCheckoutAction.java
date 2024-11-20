package aepermits.control.action;

import aepermits.control.*;
import aepermits.control.form.*;
import aepermits.to.*;
import aepermits.data.*;

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

import util.logging.DHSLogLevel;
import util.logging.Log;

public class DfbsEntrCheckoutAction extends ControlAction
{
  private static final String CLASS_NAME="DfbsEntrCheckoutAction";
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      String METHOD_NAME="executeControl"; 
      try {
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsEntrOwnerDAO oDAO = (DfbsEntrOwnerDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_OWNER);
        DfbsEntrPermitDAO pDAO = (DfbsEntrPermitDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_PERMIT);
        DfbsEntrSpecialDAO sDAO = (DfbsEntrSpecialDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_SPECIAL_PERMIT);
         ApplicationSecurityDAO seDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
                
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
          Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "owner.:"+owner);
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
          Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "ownerApplication.:"+ownerApplication);
         ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS"); 
         if (method == null)
        {
        if(owner == null || ownerApplication == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
        if (ownerApplication.getTotalPermitsAmount() > 0)
         {
             Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "if.:"+ownerApplication);
          processCheckout(context,request,response,ownerApplication,session,pDAO);
          return null;
         }
         else
         { 
             Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "else.:"+ownerApplication);
          processCheckoutNoFee(context,request,response,ownerApplication,session,pDAO);
          return null;
         }
        }
        else if(method.equals("check"))
       {  String checkNumber  = request.getParameter("checkNumber");
           applicationSpecificProcessing(oDAO,pDAO,sDAO, ownerApplication,request,session,checkNumber);
       if (ownerApplication.getTotalPermitsAmount() > 0)
         {
           logReceiptId(pDAO,ownerApplication,ownerApplication.getOwnerId());
         }
      session.setAttribute("DFBS_OWNER",null);
      session.setAttribute("DFBS_OWNER_APPLICATION",null);
      
       }
       else if(method.equals("test"))
       { DfbsEntrPermit permit =pDAO.selectPermit("AE8323772");
       String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
      // sendEmailInsp(request,inspEmail,permit,contacts,pDAO);
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
        DfbsOwner owner,HttpSession session,DfbsEntrPermitDAO pDAO) throws Exception
 {
	 
   OrderInfo cart = new OrderInfo();
   
   Address userAddress = new Address();
   userAddress.setAddress1(owner.getStreet1());
   userAddress.setAddress2(owner.getStreet2());
   userAddress.setCity(owner.getCity());
   userAddress.setState(owner.getState());
	 userAddress.setZipCode(owner.getZip());
   ItemInfo[] itemsInfo = new ItemInfo[owner.getCartPermitCount()];
   List permits = owner.getPermits();
   Iterator i = permits.iterator();
   int j = -1;
   
    while(i.hasNext())
   {StringBuffer sku = new StringBuffer("sku:");
     DfbsEntrPermit permit = (DfbsEntrPermit)  i.next();
    //  if (permit.getPermitTotalAmount() >0)
   //  {
     ItemInfo item = new ItemInfo();
     if(permit.getPermitNumber() == null) {
      sku.append("new");
     } 
     else 
     {
      sku.append(permit.getPermitNumber());
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
    
     sb.append(" (AE permit Annual");
     sb.append("):");
      sb.append(" ");
     sb.append(owner.getOwnerName().toUpperCase());
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(permit.getPermitTotalAmount()));
     itemsInfo[++j] = item;
    // }
      List specials = permit.getSpecials();
      Iterator k = specials.iterator();
       while(k.hasNext())
       { DfbsEntrSpecial special = (DfbsEntrSpecial)  k.next();
          if (special.getAmount() >0)
         {
          ItemInfo itemSpecial = new ItemInfo();
     if(permit.getPermitNumber() == null) {
      sku.append("new");
     } 
     else 
     {
      sku.append(permit.getPermitNumber());
     }
     itemSpecial.setSku(sku.toString());
     StringBuffer sbSpecial = new StringBuffer();
     if(permit.isNew()) {
      sbSpecial.append("NEW");
     } 
     else 
     {
      sbSpecial.append(permit.getPermitNumber());
     }
         sbSpecial.append(" (AE Special");
         sbSpecial.append("):");
          sbSpecial.append(" ");
         sbSpecial.append(owner.getOwnerName().toUpperCase());
         itemSpecial.setDescription(sbSpecial.toString());
         itemSpecial.setQuantity(new Integer(1));
         itemSpecial.setUnitCost(new BigDecimal(special.getAmount()));
         itemsInfo[++j] = itemSpecial;
         }
       }
   }
   cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/aepermits/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   cart.setApplicationName(context.getInitParameter("application_name_aepermits"));

	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
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
        DfbsOwner owner,HttpSession session,DfbsEntrPermitDAO pDAO) throws Exception
 {
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/aepermits/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
   return ;
 }
 
 private static void processCheckoutNoFee(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        DfbsOwner owner,HttpSession session,DfbsEntrPermitDAO pDAO) throws Exception
 {
	
  
  
  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
  redirectUrl.append(request.getContextPath()).append("/aepermits/completeCheckout.do");
  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
  return;
 }
 private static void applicationSpecificProcessing(DfbsEntrOwnerDAO oDAO,DfbsEntrPermitDAO pDAO,
       DfbsEntrSpecialDAO sDAO, DfbsOwner owner,HttpServletRequest request,HttpSession session, String checkNumber) throws Exception {
    
   ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
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
      DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
      
      if(isNewOwner) {
        permit.setOwnerId(owner.getOwnerId());
      } 
      
      if(permit.isNew()) {
        pDAO.countyCode(permit,permit.getCounty());
        pDAO.insertPermit(permit);
        List specials = permit.getSpecials();
        Iterator j = specials.iterator();
         while(j.hasNext())
         { DfbsEntrSpecial special = (DfbsEntrSpecial) j.next();
          sDAO.insertPermit(special,permit.getPermitNumber());
         }
      } 
      else 
      { 
        if(permit.getStatus().trim().equals("EXPIRED") || permit.getStatus().trim().equals("EXPIRES SOON")) {
        pDAO.updatePermit(permit);} 
         List specials = permit.getSpecials();
        Iterator j = specials.iterator();
         while(j.hasNext())
         {
          DfbsEntrSpecial special = (DfbsEntrSpecial) j.next();
          sDAO.insertPermit(special,permit.getPermitNumber());
          String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
          if ( checkNumber != null  && inspEmail.trim().length() > 5)
          {
           sendEmailInspSpecial(request,inspEmail,special,contacts,pDAO);
          }
           
         }
    }
      
        String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
       if (checkNumber != null  && inspEmail.trim().length() > 5)
        {
         sendEmailInsp(request,inspEmail,permit,contacts,pDAO);
        }
        
       
        
    
  }
  
       }
 
  
  private static void logReceiptId(DfbsEntrPermitDAO pDAO,DfbsOwner owner,int receiptId) throws Exception {
    
    
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
      permit.setReceiptId(receiptId);
      permit.setOwnerName(owner.getOwnerName());
      if (permit.getPermitTotalAmount() >0)
      {
      pDAO.insertPermitTransaction(permit);
      }
      List specials = permit.getSpecials();
        Iterator j = specials.iterator();
         while(j.hasNext())
         {DfbsEntrSpecial special = (DfbsEntrSpecial) j.next();
         pDAO.insertPermitTransaction(special,permit);
         }
    }
  
    
	}
  
   private static void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsEntrPermit permit,
                          ApplicationContacts contacts,DfbsEntrPermitDAO pDAO) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
           String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            // String[] emailTo ={"nnimmagadda@dhs.in.gov"};
         
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do?method=backToMain").append("\n\r");
             sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
              sb.append("payment made by credit card, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+permit.getPermitNumber()).append("\n\r");
              
            StringBuffer sub = new StringBuffer();
            sub.append(" From fire entertainment inspection ready alert ");
             mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email inspector check out",inspEmail);
            mail.sendAndClose();
            }
  }
 
  private static void sendEmailInspSpecial(HttpServletRequest request,String inspEmail,DfbsEntrSpecial special,
                          ApplicationContacts contacts,DfbsEntrPermitDAO pDAO) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
              String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            StringBuffer sb = new StringBuffer();
            sb.append(special.getPermitNumber() +" " + special.getEventName() +": is ready for inspection").append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do?method=backToMain").append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            sb.append("payment made by check, may take couple of business days for posting.").append("\n\r"); 
              sb.append("payment made by check, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+special.getPermitNumber()).append("\n\r");
             
            StringBuffer sub = new StringBuffer();
            sub.append(" From fire entertainment inspection ready alert ");
           
            mail.createMail(contacts.getContact1Email()+",",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
 HsMailer mail1 = new HsMailer();
            mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire entertainment inspection ready alert error email ","DfbsEntrCheckoutAction "+inspEmail);
            mail1.sendAndClose();    
           }
  }
  
 
}
  


