package fireworks.control.action;

import fireworks.control.form.*;
import fireworks.to.*;
import fireworks.data.*;
import fireworks.report.*;

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
import main.to.*;
import hs.util.*;
import main.data.*;
import idhsInspections.data.*;
import hs.data.system.*;


public class FireworksCompleteCheckoutAction  extends ControlAction
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
        DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
        DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
        if(owner == null || ownerApplication == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
        
        
        completeCheckout(context,request,response,ownerApplication);
        return null;
        
    
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
 
 
 private static void completeCheckout(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        DfbsOwner owner) throws Exception
 {
   HttpSession session = request.getSession();
   
   String checkoutToken = (String)  
   session.getAttribute("DFBS_FIREWORKS_CHECKOUT_TOKEN");
   if(checkoutToken == null || ! checkoutToken.equals(owner.getCheckoutId())) 
   {
     throw new Exception("ERROR_OWNER_CHECKOUT_TOKEN");
   }
   
   
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
        // ((Stub)checkoutService)._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, context.getInitParameter("checkout_url"));
	 ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

	  OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
	    
	  
    StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
    homeUrl.append("<a href='");
    homeUrl.append(request.getContextPath()).append("/fireworks/main.do'>");
    homeUrl.append("Back to DHS Fireworks</a>");
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
    
      
      
      DfbsDataMap dmap2 = (DfbsDataMap)
      context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
      DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
      DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
      int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
      applicationSpecificProcessing(oDAO,pDAO,owner,request,session,receiptId);
     // logReceiptId(pDAO,owner,receiptId);
      
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

   private static void completeCheckoutTest(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        DfbsOwner owner) throws Exception
 {
   HttpSession session = request.getSession();
 
   DfbsDataMap dmap2 = (DfbsDataMap)
   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      
        
   DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
   DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
   applicationSpecificProcessing(oDAO,pDAO,owner,request,session,0);
   
   session.setAttribute("DFBS_OWNER",null);
   session.setAttribute("DFBS_OWNER_APPLICATION",null);
   
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
    redirectUrl.append(request.getContextPath()).append("/fireworks/main.do");
    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
	  return ;

	}
  
  
  private static void applicationSpecificProcessing(DfbsFireworksOwnerDAO oDAO,DfbsFireworksPermitDAO pDAO,DfbsOwner owner,
                                                     HttpServletRequest request,HttpSession session,int receiptId) throws Exception {
    
   
    boolean isNewOwner = owner.isNew();
    ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
    String permitYear =(String) session.getAttribute("PERMIT_YEAR");
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
    {System.out.println(owner.getOwnerId());
      DfbsFireworksPermit permit = (DfbsFireworksPermit) i.next();
      
      if(isNewOwner) {
        permit.setOwnerId(owner.getOwnerId());
      } 
      
      if(permit.isNew()) {System.out.println(permit.getApplicationKey());
        pDAO.insertPermit(permit,permitYear);
        System.out.println("out");
         permit.setReceiptId(receiptId);
      permit.setOwnerName(owner.getOwnerName());
      pDAO.insertPermitTransaction(permit);
      } 
      else 
      {
        pDAO.updatePermit(permit,permitYear);
         permit.setReceiptId(receiptId);
      permit.setOwnerName(owner.getOwnerName());
      pDAO.insertPermitTransaction(permit);
      }
      
           sendEmailContacts(request,permit,contacts,receiptId);
         
    }
  }
  
  
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/fireworks/application/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
  private static void logReceiptId(DfbsFireworksPermitDAO pDAO,DfbsOwner owner,int receiptId) throws Exception {
    
    
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    
    while(i.hasNext())
    {
      DfbsFireworksPermit permit = (DfbsFireworksPermit) i.next();
      permit.setReceiptId(receiptId);
      permit.setOwnerName(owner.getOwnerName());
      pDAO.insertPermitTransaction(permit);
    }
  
    
	}
  
 
  

   private static void sendEmailContacts(HttpServletRequest request,DfbsFireworksPermit permit,
                          ApplicationContacts contacts,int receiptId) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),"fireworks@dor.in.gov"};
          //    String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
        //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          //  String currentDate = iDAO.selectCurrentDate();
          //  int charPosition =inspEmail.indexOf("@");
           // String userId= inspEmail.substring(0,charPosition);
           // int inspectorId =iDAO.selectInspector(inspEmail);
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("Payment receipt number : " + receiptId).append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Contact Name :"+ permit.getContactPerson()+" Phone Number= " +permit.getPhoneNumber()).append("\n\r");
            sb.append("Ready for inspection date :"+ permit.getInspectDateString()+" Facility open date= " +permit.getOpenDateString()+" Hours of operation="+permit.getHoursOfOperation()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do").append("\n\r");
            sb.append("For details click this link: https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_fireworks_list_tobe_inspected.rdf&p_id="+permit.getPermitNumber()).append("\n\r");;
            sb.append(" or this link  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_compliance_list_tobe_inspected.rdf&p_id="+permit.getPermitNumber()).append("\n\r");;
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            StringBuffer sub = new StringBuffer();
            sub.append(" From fire fireworks inspection ready alert ");
           
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            mail1.createMail("fireworks_online@dhs.in.gov",emailTo,bccTo," From fire fireworks inspection ready alert error email ",permit.getPermitNumber()+"HsFireworksCompleteCheckoutAction ");
            mail1.sendAndClose();             }
  }

 

 
}
  



