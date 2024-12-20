package aepermits.control.action;

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

public class DfbsEntrCompleteCheckoutAction  extends ControlAction
{
    private static final String CLASS_NAME="DfbsEntrCompleteCheckoutAction";
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      String METHOD_NAME="executeControl"; 
      try {
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
          Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "1.:"+context);  
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
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
         ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS"); 
        if(owner == null || ownerApplication == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "ownerApplication.:"+ownerApplication);   
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "bef.:"+ownerApplication.getTotalPermitsAmount());
         if (ownerApplication.getTotalPermitsAmount() > 0)
        {
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "if.:"+ownerApplication.getTotalPermitsAmount());
        completeCheckout(context,request,response,ownerApplication,session,pDAO,oDAO,sDAO);
        return null;
        }
        else
        {
       Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "else.:");
        completeCheckoutNoFee(context,request,response,ownerApplication,session,pDAO,oDAO,sDAO);
        return null;
        }
    
             
      } 
    catch (Exception e) 
    {
       saveError(request,e);
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "Excep.:"+e.toString());
       return mapping.findForward("error");
      
    }
        
      
        
      
  }
  
 
 
 private static void completeCheckout(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        DfbsOwner owner,HttpSession session,DfbsEntrPermitDAO pDAO,DfbsEntrOwnerDAO oDAO,DfbsEntrSpecialDAO sDAO) throws Exception
 {
	    String METHOD_NAME="completeCheckout"; 
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "starting.:"); 
   String checkoutToken = (String)session.getAttribute("DFBS_FIREWORKS_CHECKOUT_TOKEN");
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "checkoutToken.:"+checkoutToken);
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "owner.getCheckoutId().:"+owner.getCheckoutId());
   if(checkoutToken == null || ! checkoutToken.equals(owner.getCheckoutId())) 
   {
       Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "checkoutToken-error.:");
     throw new Exception("ERROR_OWNER_CHECKOUT_TOKEN");
   }
   
   
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "checkoutService.:"+checkoutService);
	  OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "orderInfo.:"+orderInfo);    
	 
    StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "homeUrl.:"+homeUrl);
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "request.getContextPath().:"+request.getContextPath()); 
    homeUrl.append("<a href='");
	  //  String realPath =  context.getInitParameter("realPath");
	 //   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, "request.getContextPath()", "realPath.:"+realPath); 
    //homeUrl.append(request.getContextPath()).append("/dfbs/aepermits/main.do'>");
    homeUrl.append("/dfbs/aepermits/main.do'>");
    homeUrl.append("Back to DHS AE Permits </a>");
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
    
     
      
      DfbsDataMap dmap2 = (DfbsDataMap)
      context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
     
       int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
      applicationSpecificProcessing(oDAO,pDAO,sDAO, owner,request,session,receiptId);
      logReceiptId(pDAO,owner,receiptId);  
    //  sendEmailCheckOut(receiptId,owner.getTotalPermitsAmountString());
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
		  return;
		} else {
        sb.append(url.getProtocol()).append("://").append(url.getHost());
        sb.append("/apps/kwikekard/checkout/servlet/receipt");
        response.sendRedirect(sb.toString());
		  return;
		}
      
      

	}

   private static void completeCheckoutTest(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        DfbsOwner owner,HttpSession session,DfbsEntrPermitDAO pDAO,DfbsEntrOwnerDAO oDAO,DfbsEntrSpecialDAO sDAO) throws Exception
 {
 
   DfbsDataMap dmap2 = (DfbsDataMap)
   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
  
   applicationSpecificProcessing(oDAO,pDAO,sDAO,owner,request,session,0);
   
   session.setAttribute("DFBS_OWNER",null);
   session.setAttribute("DFBS_OWNER_APPLICATION",null);
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
    redirectUrl.append(request.getContextPath()).append("/aepermits/main.do");
    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
	  return;
	}
  
  
  private static void applicationSpecificProcessing(DfbsEntrOwnerDAO oDAO,DfbsEntrPermitDAO pDAO,
       DfbsEntrSpecialDAO sDAO, DfbsOwner owner,HttpServletRequest request,HttpSession session,int receiptId
       ) throws Exception {
      String onlySpecialFlag= (String)  session.getAttribute("ONLY_SPECIAL_FLAG");
   ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
    boolean isNewOwner = owner.isNew();
    
    if(isNewOwner) {
      oDAO.insertOwner(owner);
    } 
    else
    {
      if (onlySpecialFlag.equals("False"))
      {
      oDAO.updateOwner(owner);
      }
    }
    
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
      permit.setReceiptId(receiptId);
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
          if (onlySpecialFlag.equals("False"))
              {permit.setStatus("NEW");
               pDAO.updatePermit(permit);} 
         List specials = permit.getSpecials();
        Iterator j = specials.iterator();
         while(j.hasNext())
         {
          DfbsEntrSpecial special = (DfbsEntrSpecial) j.next();
          sDAO.insertPermit(special,permit.getPermitNumber());
          String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
          if (inspEmail.trim().length() > 5)
          {
           sendEmailInspSpecial(request,inspEmail,special,contacts,pDAO,owner,permit);
          }
         }
      }
      
        String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
        if (inspEmail.trim().length() > 5)
        {
         sendEmailInsp(request,inspEmail,permit,contacts,pDAO,owner);
        }
    }
  
       }
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/aepermits/application/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
  private static void logReceiptId(DfbsEntrPermitDAO pDAO,DfbsOwner owner,int receiptId) throws Exception {
    
    
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsEntrPermit permit = (DfbsEntrPermit) i.next();
      permit.setReceiptId(receiptId);
      permit.setOwnerName(owner.getOwnerName());
      pDAO.insertPermitTransaction(permit);
       List specials = permit.getSpecials();
        Iterator j = specials.iterator();
         while(j.hasNext())
         {DfbsEntrSpecial special = (DfbsEntrSpecial) j.next();
         pDAO.insertPermitTransaction(special,permit);
         }
    }
  
    
	}
  
 
 private static void completeCheckoutNoFee(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        DfbsOwner owner,HttpSession session,DfbsEntrPermitDAO pDAO,DfbsEntrOwnerDAO oDAO,DfbsEntrSpecialDAO sDAO) throws Exception
 {
	    String METHOD_NAME="completeCheckoutNoFee"; 
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "owner.:"+owner);
   DfbsDataMap dmap2 = (DfbsDataMap)
   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
	  ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");

  
   applicationSpecificProcessing(oDAO,pDAO,sDAO,owner,request,session,0);
   //sendEmailNoFee(owner.getOwnerId(),owner.getOwnerName());
   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "owner-email.:"+owner.getOwnerEmail());
   if (owner.getOwnerEmail().trim().length() > 5)
   { sendEmailNoFeeOwner(owner.getOwnerId(),owner.getOwnerName(),owner.getOwnerEmail(),contacts);
   }
 
   session.setAttribute("DFBS_OWNER",null);
   session.setAttribute("DFBS_OWNER_APPLICATION",null);
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
	    redirectUrl.append("/dfbs/aepermits/noFee.jsp'>");
    //redirectUrl.append(request.getContextPath()).append("/aepermits/noFee.jsp");
    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
	  return;

	}

 private static void sendEmailNoFee(int ownId, String OwnDBName,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
              String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\nEntertainment permit online transaction #");
            sb.append(ownId);
            sb.append(" :");
            sb.append(OwnDBName);
            sb.append(" : No fee for owner filed");
           
            
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Entertainment Permits Online Transaction  ");
          
          
          
            mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail = new HsMailer();
            mail.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov","error email owner check out",Integer.toString(ownId));
            mail.sendAndClose();
            }
  }

  private static void sendEmailNoFeeOwner(int ownId, String OwnDBName, String OwnerEmailNoFee,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
              String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),OwnerEmailNoFee};
         // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n");
            sb.append(OwnDBName);
            sb.append(" ,\n\r");
            sb.append("You have successfully submitted your application.You have applied for a fee exempt AE permit.");
            sb.append("Your application will not be processed untill we receive the copy of IRS fee exempt letter.");
            sb.append("(If you have already submitted the required documentation for the current year, ");
            sb.append("you need not submit it again.)You can mail the copy of IRS letter to the following ");
            sb.append("address or you can fax it to 317-233-0307. Or if you have an electronic copy of the ");
            sb.append("document you can email it to: "+ contacts.getContact1Email() +" or "+ contacts.getContact2Email()+".");
            sb.append(" If we have any questions regarding the application we will contact you at the email /");
            sb.append("phone number provided by you in the application. ");
            sb.append("\n\r");
            sb.append("Mailing Address");
            sb.append("\n\r");
            sb.append("Department of Fire and Building Services Permit Division");
            sb.append("\n\r");
            sb.append("State Fire Marshal Office ");
            sb.append("\n\r");
            sb.append("402 West Washington St, # E241");
            sb.append("\n\r");
            sb.append("Indianapolis, IN 46204");
             sb.append("\n\r");
            sb.append("1.If you are claiming fee exempt status, you must provide a valid 501C. If you have questions contact "+ contacts.getContact1Name()+": "+ contacts.getContact1Phone()+" or " +contacts.getContact2Name()+": "+ contacts.getContact2Phone()+" with details after submitting the application. ");
            sb.append("You contact us via email or by phone.");
            
                   
            
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Entertainment Permits Confirmation Email  ");
          
          
          
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail(contacts.getContact1Email(),"nnimmagadda@dhs.in.gov","error email owner no fee check out",Integer.toString(ownId));
            mail.sendAndClose();
            }
  }  
 
private static void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsEntrPermit permit,
                          ApplicationContacts contacts,DfbsEntrPermitDAO pDAO,DfbsOwner owner) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
                
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append(owner.getTotalPermitsAmountString() + "paid and online reference number is "+permit.getReceiptId()).append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/aepermits/start.do").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/aepermits/permit.do?method=updatePermit&permitNumber="+permit.getPermitNumber()).append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            sb.append("payment made by credit card, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+permit.getPermitNumber()).append("\n\r");
                StringBuffer sub = new StringBuffer();
            sub.append(" Application for a Annual filed online with FM office ");
            
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
               String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,"error email inspector check out",inspEmail+permit.getPermitNumber());
            mail.sendAndClose();
            }
  }
 
   private static void sendEmailInspSpecial(HttpServletRequest request,String inspEmail,DfbsEntrSpecial special,
                          ApplicationContacts contacts,DfbsEntrPermitDAO pDAO,DfbsOwner owner,DfbsEntrPermit permit) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
              String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
         //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer();
            sb.append(special.getPermitNumber() +": " +special.getEventName() +": is ready for inspection").append("\n\r");
            sb.append(owner.getTotalPermitsAmountString() + "paid and online reference number is "+permit.getReceiptId()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/aepermits/start.do").append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            sb.append("payment made by check, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+special.getPermitNumber()).append("\n\r");
             
            StringBuffer sub = new StringBuffer();
            sub.append(" Application for a Special filed online with FM office ");
           
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail1 = new HsMailer();
              String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
              String[] bccTo = {"nnimmagadda@dhs.in.gov"};
             mail1.createMail(contacts.getContact1Email(),emailTo,bccTo," From fire entertainment inspection ready alert error email ","DfbsEntrCheckoutAction "+inspEmail+permit.getPermitNumber());
           mail1.sendAndClose();     
           }
  }
}
  



