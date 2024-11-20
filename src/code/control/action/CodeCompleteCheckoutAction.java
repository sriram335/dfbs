package code.control.action;
import code.to.*;
import code.control.form.*;
import code.data.* ;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;

import main.data.* ;
import main.to.* ;

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



public class CodeCompleteCheckoutAction extends ControlAction
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
        CodeManufacturerDAO mDAO = (CodeManufacturerDAO) dmap2.getHsDAO(DfbsDataMap.MANUFACTURER);
        CodeFacilityDAO fDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
        CodeDesignReleaseDAO rDAO = (CodeDesignReleaseDAO) dmap2.getHsDAO(DfbsDataMap.RELEASE);
        CodePersonDAO personDAO = (CodePersonDAO) dmap2.getHsDAO(DfbsDataMap.PERSON);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationContacts contacts = sDAO.setContacts("CODE_CONTACT1","CODE_CONTACT2");
               session.setAttribute("APPLICATION_CONTACTS",contacts);
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
        if(manufacturer == null ) 
        {
          throw new Exception("NO_MANUFACTURER_IN_SESSION");
        }
        
        
       
        completeCheckout(context,request,response,manufacturer,contacts);
        
        return null;
        
    
             
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
        CodeManufacturer manufacturer,ApplicationContacts contacts) throws Exception
 {
   HttpSession session = request.getSession();
   
   String checkoutToken = (String)  
   session.getAttribute("AI_DHS_HOUSING_CHECKOUT_TOKEN");
   if(checkoutToken == null || ! checkoutToken.equals(manufacturer.getCheckoutId())) 
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
    homeUrl.append("<a href='");
    homeUrl.append(request.getContextPath()).append("/code/main.do'>");
    homeUrl.append("Back to DHS Code Enforcement</a>");
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
    
      
      
      DfbsDataMap dmap2 = (DfbsDataMap)
      context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      CodeManufacturerDAO mDAO = (CodeManufacturerDAO) dmap2.getHsDAO(DfbsDataMap.MANUFACTURER);
      int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
	    String sealDetails = (String) session.getAttribute("SEAL_PURCHASE_DETAILS");
       applicationSpecificProcessing(mDAO, manufacturer, receiptId,sealDetails);
      // logReceiptId(fDAO,manufacturer,receiptId);
     sendEmailCheckOut(receiptId,manufacturer,contacts);
      
      session.setAttribute("MANUFACTURER",null);
     
     
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


  
  
  private static void applicationSpecificProcessing
      (CodeManufacturerDAO mDAO,CodeManufacturer manufacturer, int receiptId,String sealDetails) throws Exception {
    
      mDAO.saveRecords(manufacturer,receiptId,sealDetails);
    }
    
   
  
  
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/code/application/receipt.jsp").include(request,bufferedResponse);
	   return new String(bufferedResponse.getBuffer());
	}
  
 
  private static void sendEmailCheckOut(int receiptId,CodeManufacturer manufacturer,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
             String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
           //  String[] emailTo = {"kcompton@dhs.in.gov"};
            String[] bccTo = {"tbradshaw@dhs.in.gov","nnimmagadda@dhs.in.gov","gkelley@dhs.in.gov","rmays@dhs.in.gov"};
          
            StringBuffer sb = new StringBuffer("\r\nCode enforcement online transaction ");
            sb.append("\n User:");
            sb.append(manufacturer.getManufacturerName());
            sb.append("\n  fee details receipt #"); 
            sb.append(receiptId);
            sb.append(" : ");
            if (manufacturer.getGrandTotalString() !=null)
            {
            sb.append(manufacturer.getGrandTotalString());
            }
            sb.append("\r\n  login to web site to approve the cdr "); 
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do");
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Code enforcement Online Transaction ");
          
            mail.createMail("code_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
 HsMailer mail1 = new HsMailer();
            mail1.createMail("codeEnforcement_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Code enforcement Online Transaction error email "+manufacturer.getManufacturerName()+":"+receiptId+":"+manufacturer.getGrandTotalString(),"CodeCompleteCheckoutAction");
            mail1.sendAndClose();           
            }
  }
    
  

 

 
}
  



