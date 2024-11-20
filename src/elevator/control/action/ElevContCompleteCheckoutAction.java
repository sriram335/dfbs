package elevator.control.action;
import aepermits.data.DfbsEntrPermitDAO;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import elevator.to.*;
import elevator.control.form.*;

import hs.control.ControlAction;

import hs.data.system.HsUtilityDAO;

import hs.util.HsConstant;

import java.io.IOException;
import elevator.data.*;

import hs.util.BufferedHttpServletResponse;

import hs.util.HsMailer;

import java.util.Iterator;
import java.util.Map;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import main.data.DfbsDataMap;

import main.data.DfbsDataMap2;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import main.data.*;
import main.to.*;
public class ElevContCompleteCheckoutAction extends ControlAction{    
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context = this.getServlet().getServletConfig().getServletContext();
            DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
            elevServContractorDAO cDAO = (elevServContractorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_SERVICE_CONTRACTOR);
            String method = request.getParameter("method");
       
          HttpSession session = request.getSession();
            ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            elevServiceContractor cont = (elevServiceContractor) session.getAttribute("SERVICE_CONTRACTOR_SELECTED");
           if (method.equals("completeCheckout")) 
          { 
            completeCheckout(context,request,response,session,cDAO,cont); 
          return mapping.findForward("start");
          }   
           else if (method.equals("completeCheckoutTest")) 
            {applicationSpecificProcessing(99999,cDAO,contacts,request,cont);
            return mapping.findForward("start");
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
    
    
      private static void completeCheckout(ServletContext context,
         HttpServletRequest request,HttpServletResponse response,
            HttpSession session,elevServContractorDAO cDAO,elevServiceContractor cont ) throws Exception
      { 
         ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        String checkoutToken = (String)  session.getAttribute("IDHS_ELEVATOR_CONT_CHECKOUT_TOKEN");
        if(checkoutToken == null || ! checkoutToken.equals(cont.getCheckoutId())) 
        {
          throw new Exception("ERROR_IDHS_ELEVATOR_CHECKOUT_TOKEN");
        }
        CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
        ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
        ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
        ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
        ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
        OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
         StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
         homeUrl.append("<a href='");
         homeUrl.append(request.getContextPath()).append("/elevator/servContractor.do'>");
         homeUrl.append("Back to IDHS Service Contractor Application </a>");
         if(!orderInfo.isAuthorized()) {
         
           throw new ServletException(checkoutToken + " has not been authorized.");
         
         } else if(!orderInfo.isCaptured()) {
            int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
            cont.setReceiptId(receiptId);
            applicationSpecificProcessing(receiptId,cDAO,contacts,request,cont);
            sendEmailReceipt(request,contacts,cont);
            session.setAttribute("SERVICE_CONTRACTOR_SELECTED",null);
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
           return ;

         }
        
       }
      private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
          request.getRequestDispatcher("/elevator/receiptCont.jsp").include(request,bufferedResponse);
         return new String(bufferedResponse.getBuffer());
      }
        private static void applicationSpecificProcessing(int receiptId,elevServContractorDAO cDAO ,
                                                          ApplicationContacts contacts,HttpServletRequest request,elevServiceContractor cont) throws Exception {
          if (cont.getContId()==0) {
             cDAO.insertElevContractor(cont);
            cDAO.insertPermitTransaction(cont);
          }
          else {
            cDAO.updateElevServiceContRenewal(cont);
            cDAO.insertPermitTransaction(cont);
          }
          
          
          }
        private static void sendEmailReceipt(HttpServletRequest request, 
                                  ApplicationContacts contacts,elevServiceContractor cont) throws Exception
          {
                  try {
                     
                    HsMailer mail = new HsMailer();
                    String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),cont.getContEmail()};
                   //  String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
                    String[] bccTo = {"nnimmagadda@dhs.in.gov"};
                   
                    StringBuffer sb = new StringBuffer();
                    sb.append(cont.getContLastName()+" "+cont.getContFirstName()+ ": submitted following online application.").append("\n\r");  
                    sb.append(cont.getLicNumber() + ": Elevator Service Contractor permit.").append("\n\r");
                    sb.append(cont.getContFee() + " paid and online reference number is "+cont.getReceiptId()).append("\n\r");
                    sb.append("Use this link to view information:").append("\n\r");
                    sb.append("https://oas.dhs.in.gov/dfbs/elevator/servContractor.do").append("\n\r");
                         StringBuffer sub = new StringBuffer();
                    sub.append(" From IDHS  Elevator Service Contractor online transaction alert ");
                    
                    mail.createMail("elevators_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                   mail.sendAndClose();
                    } 
                    catch(Exception e) 
                    {
                      HsMailer mail = new HsMailer();
                    mail.createMail("elevators_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email elevator check out"+cont.getLicNumber() );
                    mail.sendAndClose();
                    }
          }
        
      
      }



