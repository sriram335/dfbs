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
public class elevCompleteCheckoutAction extends ControlAction{    
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context = this.getServlet().getServletConfig().getServletContext();
          DfbsDataMap dmap2 = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          elevatorOwnerDAO oDAO = ( elevatorOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_OWNER);
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
          elevApplicationDAO aDAO = (elevApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_APPLICATION);  
          String method = request.getParameter("method");
            ShoppingCartForm cartForm = (ShoppingCartForm) form;
      
          HttpSession session = request.getSession();
            ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            DfbsOwner owner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");  
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           if (method.equals("completeCheckout")) 
          { 
            completeCheckout(context,request,response,cart,session,oDAO,eDAO,aDAO); 
          return mapping.findForward("start");
          }   
           else if (method.equals("completeCheckoutTest")) 
            {applicationSpecificProcessing(cart,99999,oDAO,eDAO,aDAO,contacts,request);
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
            ShoppingCart cart,HttpSession session,elevatorOwnerDAO oDAO,elevatorDAO eDAO,elevApplicationDAO aDAO) throws Exception
      { 
         ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        String checkoutToken = (String)  session.getAttribute("IDHS_ELEVATOR_CHECKOUT_TOKEN");
        if(checkoutToken == null || ! checkoutToken.equals(cart.getCheckoutId())) 
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
         homeUrl.append(request.getContextPath()).append("/elevator/start.do'>");
         homeUrl.append("Back to IDHS Elevator Application </a>");
         if(!orderInfo.isAuthorized()) {
         
           throw new ServletException(checkoutToken + " has not been authorized.");
         
         } else if(!orderInfo.isCaptured()) {
            int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
            cart.setReceiptId(receiptId);
            cart.setPaymentType("CC");
            applicationSpecificProcessing(cart,receiptId,oDAO,eDAO,aDAO,contacts,request);
            session.setAttribute("SHOPPING_CART",null);
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
          request.getRequestDispatcher("/elevator/receipt.jsp").include(request,bufferedResponse);
         return new String(bufferedResponse.getBuffer());
      }
        private static void applicationSpecificProcessing(ShoppingCart cart,int receiptId,elevatorOwnerDAO oDAO,elevatorDAO eDAO,
                                                          elevApplicationDAO aDAO,ApplicationContacts contacts,HttpServletRequest request) throws Exception {
          Map mapOwner = cart.getOwnerMap();
          Set ownerkeys = mapOwner.keySet();
          Iterator i = ownerkeys.iterator();
          String appStateNumber="";
          while(i.hasNext())
          {
             String key = (String) i.next();
             DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
            if (owner.isNew()) {
              oDAO.insertOwner(owner);
            }
            else {
              oDAO.updateOwner(owner);
            }
            Map mapElevators = owner.getElevatorsMap();
            Set elevkeys = mapElevators.keySet();
            Iterator k = elevkeys.iterator();
              while(k.hasNext())
              {
                String elevkey = (String) k.next();
               elevator elev = (elevator) mapElevators.get(elevkey);
                if (elev.isNew()||elev.getStateNumber().equals("New")) {
                                   elev.setOwnerId(owner.getOwnerId());
                                    eDAO.insertElevator(elev);
                  
                }
                else {
                  eDAO.updateElevator(elev);
                }
              // add apps
            Map mapApps = elev.getElevatorAppMap();
            Set appkeys = mapApps.keySet();
            Iterator j = appkeys.iterator();
              while(j.hasNext())
              {
                String appkey = (String) j.next();
                 elevatorApplication elevAppl = (elevatorApplication) mapApps.get(appkey);
                 elevAppl.setStateNumber(elev.getStateNumber());
                  aDAO.insertElevatorApplication(elevAppl);
                  aDAO.insertElevatorTransaction(elevAppl,cart,owner);
                  sendEmailInsp(request,elevAppl,contacts,owner,cart);
                 sendEmailOwner(request,elevAppl,contacts,owner,cart,receiptId);
              }
              }
              
          }
        }
        private static void sendEmailInsp(HttpServletRequest request, elevatorApplication app,
                                  ApplicationContacts contacts,DfbsOwner owner,ShoppingCart cart) throws Exception
          {
                  try {
                     
                    HsMailer mail = new HsMailer();
                    String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
                   //  String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
                    String[] bccTo = {"nnimmagadda@dhs.in.gov"};
                   
                    StringBuffer sb = new StringBuffer();
                    sb.append(owner.getOwnerDBA()+ ": submitted following online applications.").append("\n\r");  
                    sb.append(app.getStateNumber() + ": applied for alteration/installation permit.").append("\n\r");
                    sb.append(app.getAppFee() + " paid and online reference number is "+cart.getReceiptId()).append("\n\r");
                    sb.append("Use this link to view information:").append("\n\r");
                    sb.append("https://oas.dhs.in.gov/dfbs/elevator/start.do").append("\n\r");
                         StringBuffer sub = new StringBuffer();
                    sub.append(" From IDHS  Elevator online transaction alert New online system ");
                    
                    mail.createMail("elevators_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                   mail.sendAndClose();
                    } 
                    catch(Exception e) 
                    {
                      HsMailer mail = new HsMailer();
                    mail.createMail("elevators_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email elevator check out"+app.getStateNumber());
                    mail.sendAndClose();
                    }
          }
         private static void sendEmailOwner(HttpServletRequest request, elevatorApplication app,
                                  ApplicationContacts contacts,DfbsOwner owner,ShoppingCart cart,int receiptId) throws Exception
          {
                  try {
                     
                    HsMailer mail = new HsMailer();
                    String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),app.getAppliedEmail(),app.getOwnerEmail()};
                  //  String[] emailTo =   {"nnimmagadda@dhs.in.gov"};
                    String[] bccTo = {"nnimmagadda@dhs.in.gov"};
                   
                    StringBuffer sb = new StringBuffer();
                    sb.append("This is to confirm we received  a online application on behalf of "+owner.getOwnerDBA()+ " and ");  
                    sb.append(app.getAppliedBy()+ " / " +app.getAppliedFirm() +" has applied for alteration/installation permit.").append("\n\r");
                    sb.append(" Online application is received for elevator with State number:"+ app.getStateNumber() +" and a fee of $"+app.getAppFee() + ".00 is paid and online reference number is "+receiptId).append("\n\r");
                    sb.append("Important Note: Please note that the application process is not complete, please use the link provided below to print the "+
                              "affirmation of the owner and affiramtion of the contractor.1) Upload elevator floor plans 2) Please print and sign the documents and either fax it to 317 -232-6609"+
                              " or scan and upload the signed document at our website https://oas.dhs.in.gov/dfbs/elevator/start.do").append("\n\r");
                    sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_alt_inst_application.rdf&p_application_id=" +app.getApplicationId()).append("\n\r");
                    sb.append(" Please do not reply to this message. If you have question(s) about this information or you have not authorized the firm to represent you please contact:");
                    sb.append(contacts.getContact1Name() + " at "+ contacts.getContact1Email()+" or "+contacts.getContact2Name() + " at "+contacts.getContact2Email()).append("\n\r");
                    
                         StringBuffer sub = new StringBuffer();
                    sub.append(" From IDHS  Elevator online application alert New online system. ");
                    
                    mail.createMail("elevators_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                   mail.sendAndClose();
                    } 
                    catch(Exception e) 
                    {
                      HsMailer mail = new HsMailer();
                    mail.createMail("elevators_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email elevator check out"+app.getStateNumber());
                    mail.sendAndClose();
                    }
          }
      
      }


