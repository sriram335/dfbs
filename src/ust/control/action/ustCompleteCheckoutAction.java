package ust.control.action;



import ai.kwikekard.checkout.service2.client.*;


import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import ust.to.*;
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
import ust.data.*;
import main.data.*;
import main.to.*;

import ust.control.form.ustCertificationForm;

public class ustCompleteCheckoutAction extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        ServletContext context =  this.getServlet().getServletConfig().getServletContext();
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        ustCertificationForm certForm = (ustCertificationForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          ustDAO uDAO = ( ustDAO) dmapNew.getHsDAO(DfbsDataMap2.UST_APPLICANT);
        ustCertificationDAO certDAO = ( ustCertificationDAO) dmapNew.getHsDAO(DfbsDataMap2.UST_CERTIFICATION);
         String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART_UST");
        ApplicationContacts contacts = ( ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        ustApplicant ust = (ustApplicant) session.getAttribute("UST_APPLICANT");
        ustApplicant ustOld = (ustApplicant) session.getAttribute("UST_OLD_APPLICANT");
        ustCertification cert = (ustCertification) session.getAttribute("UST_CERT");
          if (method == null)
          {
              if(cart.getAmount() == 0) 
              {
                throw new Exception("NO_OWNER_IN_SESSION");
              }
               if (cart.getAmount() > 0)
              { 
              completeCheckout(context,request,response,cart,contacts,session,ust,uDAO,ustOld,certDAO,cert,sDAO);
                session.setAttribute("UST_APPLICANT",null);
                session.setAttribute("UST_CERT",null); 
                session.setAttribute("SHOPPING_CART_UST",null);
              return null;
              }
              else
              {
              return null;
              }
          
          }
        else if (method.equals("check")) 
        { 
          applicationSpecificProcessing(ust,cart,11992288,session,request,contacts,uDAO,ustOld,certDAO,cert); 
          return null;
        }
        throw new Exception("Check out error");  
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
        ShoppingCart cart,ApplicationContacts contacts,HttpSession session,
                                      ustApplicant ust,ustDAO uDAO,ustApplicant ustOld,
       ustCertificationDAO certDAO, ustCertification cert,ApplicationSecurityDAO sDAO) throws Exception
 {
	  
   String checkoutToken = (String)  
   session.getAttribute("UST_CERT_CHECKOUT_TOKEN");
	 if(checkoutToken == null || ! checkoutToken.equals(cart.getCheckoutId())) 
   {
     throw new Exception("ERROR_UST_CERT_CHECKOUT_TOKEN");
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
      applicationSpecificProcessing(ust,cart,receiptId,session,request,contacts,uDAO,ustOld,certDAO,cert);
       session.setAttribute("UST_APPLICANT",null);
      session.setAttribute("UST_CERT",null);
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


  private static void applicationSpecificProcessing(ustApplicant ust,
       ShoppingCart cart, int receiptId,HttpSession session,HttpServletRequest request,
                                                    ApplicationContacts contacts,ustDAO uDAO,ustApplicant ustOld,
       ustCertificationDAO certDAO, ustCertification cert) throws Exception 
       {
      if (ust.getUstId() ==0)
      {
       uDAO.insertUst(ust)  ; 
       cert.setUstId(ust.getUstId());
       if (cert.getLicState().equals("0")) {
         cert.setLicState("IN");
       }
       
       certDAO.insertUst(cert);
       logReceiptId(ust,receiptId,uDAO,cart,cert);
        sendConfirmation(ust,contacts,cart,receiptId); 
      }
      else {
          ust.setComments("Old First Name:"+ustOld.getPersonFirstName()+" Old Last Name:"+ustOld.getPersonLastName()+" "+ust.getComments());
        uDAO.updateUst(ust);
      //    certDAO.updateUst(cert);
        logReceiptId(ust,receiptId,uDAO,cart,cert);
       sendConfirmation(ust,contacts,cart,receiptId); 
      }
    
  }
  
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/ust/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
  private static void logReceiptId(ustApplicant ust,int receiptId,ustDAO uDAO, ShoppingCart cart,ustCertification cert) throws Exception {
    
      uDAO.insertPermitTransaction(ust,receiptId,cart,cert);
   
	}
  private static void sendConfirmation(ustApplicant ust,
                         ApplicationContacts contacts,ShoppingCart cart, int receiptId) throws Exception
  {
         try {
            
           HsMailer mail = new HsMailer();
             String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),ust.getPersonEmail()};
        //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           String[] bccTo = {"nnimmagadda@dhs.in.gov"};
           StringBuffer sb = new StringBuffer();
           sb.append(ust.getPersonLastName() +" has applied for UST certification/ renewal.").append("\n\r");
           sb.append(cart.getAmount() + " paid and online reference number is "+receiptId).append("\n\r");
           sb.append("Use this link to verify status and print certification after approval.").append("\n\r");
           sb.append("https://oas.dhs.in.gov/dfbs/ust/ust.do").append("\n\r");
            
           StringBuffer sub = new StringBuffer();
           sub.append(" Application for a UST Certification / Renewal filed online with FM office ");
          
           mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
          mail.sendAndClose();
           } 
           catch(Exception e) 
           {
             HsMailer mail1 = new HsMailer();
             String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
             String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            mail1.createMail(contacts.getContact1Email(),emailTo,bccTo," From ust renewal alert error email ","DfbsEntrCheckoutAction "+ust.getPersonEmail()+":"+ust.getPersonLastName());
          mail1.sendAndClose();     
          }
  }
}
  



