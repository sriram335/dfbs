package idhsFines.control.action;


import aepermits.to.DfbsEntrPermit;

import ai.kwikekard.checkout.service2.client.Address;
import ai.kwikekard.checkout.service2.client.CheckoutService2SoapBindingStub;
import ai.kwikekard.checkout.service2.client.CheckoutService2_PortType;
import ai.kwikekard.checkout.service2.client.CheckoutService2_ServiceLocator;
import ai.kwikekard.checkout.service2.client.ItemInfo;
import ai.kwikekard.checkout.service2.client.OrderInfo;

import ai.kwikekard.checkout.service2.client.TransactionInfo;

import main.to.*;


import idhsFines.control.form.*;

import idhsFines.to.ShoppingCart;



import main.data.*;
import idhsFines.to.*;
import idhsFines.data.*;
import main.control.form.*;
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
import hs.report.pdf.*;

import java.math.BigDecimal;

import java.net.URL;

import java.sql.Connection;

import javax.xml.rpc.Stub;

import main.to.ApplicationContacts;
import main.to.feeDetails;


public class fineStartAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
      try {
        
        
        ServletContext context =    this.getServlet().getServletConfig().getServletContext();
         DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
         DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY); 
        finesDataDAO fDAO = (finesDataDAO) dmapNew.getHsDAO(DfbsDataMap2.IDHS_FINES);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
         idhsFinesForm fineForm = (idhsFinesForm) form;
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
         
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART_FEE");System.out.println("1");
          
        if (method == null ) 
        {   ShoppingCart cartNew = new ShoppingCart();
          session.setAttribute("SHOPPING_CART_FEE",cartNew);
          session.setAttribute("SEARCH_FOR_FINE",null);  
          appContacts contact =fDAO.selectAppContacts();
          session.setAttribute("FEE_APP_CONTACTS",contact);  
           FileNames names= sDAO.selectAppStatus("FEE_FINES_MAINT_FLAG");
            session.setAttribute("FEE_FINES_APP_STATUS",names.getFileName());
            session.setAttribute("FEE_FINES_APP_MESSAGE",names.getFileType());
          return mapping.findForward("start");
        } 
        else if (method.equals("varianceCompApplication")) 
        { 
          ShoppingCart cartNew = new ShoppingCart();
         session.setAttribute("SHOPPING_CART_FEE",cartNew);
         session.setAttribute("SEARCH_FOR_FINE",null);  
         appContacts contact =fDAO.selectAppContacts();
         session.setAttribute("FEE_APP_CONTACTS",contact);  
         FileNames names= sDAO.selectAppStatus("FEE_FINES_MAINT_FLAG");
         session.setAttribute("FEE_FINES_APP_STATUS",names.getFileName());
         session.setAttribute("FEE_FINES_APP_MESSAGE",names.getFileType());
          session.setAttribute("EMAIL_APP_CONTACT",contact.getVarContactEmail());
          String applicationType =context.getInitParameter("application_name_variance");
          session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
          session.setAttribute("SEARCH_FOR_FINE","Variance Application");
          session.setAttribute("SEARCH_DIVISION","8");
        String stateNumber = request.getParameter("stateNumber");
         String division = (String) session.getAttribute("SEARCH_DIVISION");
        List feeDueList = fDAO.showDues(stateNumber, "0", division, cartNew);
        session.setAttribute("FEE_DUE_LIST",feeDueList);
        session.setAttribute("SEARCH_STATE_NUMBER",stateNumber);
        session.setAttribute("SEARCH_OWNER_ID","0");
        return mapping.findForward("start");
        }
         else if (method.equals("search")) 
         {  
           String searchFor = fineForm.getSearchFor();
             if (searchFor.equals("Pay Fines")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
                String applicationType =context.getInitParameter("application_name_idhsFines");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType);  
                session.setAttribute("SEARCH_FOR_FINE","IDHS Fines");  
                session.setAttribute("SEARCH_DIVISION","6");
              }
           if (searchFor.equals("Fireworks")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
              String applicationType =context.getInitParameter("application_name_fireworks");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType);  
              session.setAttribute("SEARCH_FOR_FINE","Fireworks");  
              session.setAttribute("SEARCH_DIVISION","6");
            }
             if (searchFor.equals("Plan Review")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getPlanContactEmail());
                String applicationType =context.getInitParameter("application_name_planReview");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType);  
                session.setAttribute("SEARCH_FOR_FINE","Plan Review");  
                session.setAttribute("SEARCH_DIVISION","4");
              }
           if (searchFor.equals("AE Permits")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
              String applicationType =context.getInitParameter("application_name_aepermits");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","AE Permits");  
              session.setAttribute("SEARCH_DIVISION","6");
            }
           if (searchFor.equals("Registered Ministries")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
              String applicationType =context.getInitParameter("application_name_childcare");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Registered Ministries");  
              session.setAttribute("SEARCH_DIVISION","6");
            }
           if (searchFor.equals("Magazines")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
              String applicationType =context.getInitParameter("application_name_magazine");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Magazines");  
              session.setAttribute("SEARCH_DIVISION","6");
            }
           if (searchFor.equals("Fire Safe Cigarettes")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
              String applicationType =context.getInitParameter("application_name_cigarette");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Fire Safe Cigarettes");  
              session.setAttribute("SEARCH_DIVISION","6");
            }
           if (searchFor.equals("Supervised Fireworks Public Displays")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
              String applicationType =context.getInitParameter("application_name_display");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Supervised Fireworks Public Displays");
              session.setAttribute("SEARCH_DIVISION","6");
            }
        
           if (searchFor.equals("Purchase Seals")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getCodeContactEmail());
              String applicationType =context.getInitParameter("application_name_code");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Purchase Seals");  
              session.setAttribute("SEARCH_DIVISION","13");
            }
           if (searchFor.equals("Industrial Building CDR")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getCodeContactEmail());
              String applicationType =context.getInitParameter("application_name_code");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Industrial Building CDR"); 
              session.setAttribute("SEARCH_DIVISION","13");
            }
           if (searchFor.equals("Elevators")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getElevContactEmail());
              String applicationType =context.getInitParameter("application_name_elevators");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Elevators"); 
              session.setAttribute("SEARCH_DIVISION","3");
            }
           if (searchFor.equals("Boiler and Pressure Vessels")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getBpvContactEmail());
              String applicationType =context.getInitParameter("application_name_bpv");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Boiler and Pressure Vessels");  
              session.setAttribute("SEARCH_DIVISION","11");
            }
           if (searchFor.equals("Amusement Rides")) {
              appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
              session.setAttribute("EMAIL_APP_CONTACT",contact.getRidesContactEmail());
              String applicationType =context.getInitParameter("application_name_rides");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Amusement Rides");  
              session.setAttribute("SEARCH_DIVISION","1");
            } 
             if (searchFor.equals("Variance Application")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getVarContactEmail());
                String applicationType =context.getInitParameter("application_name_variance");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
               session.setAttribute("SEARCH_FOR_FINE","Variance Application");  
                session.setAttribute("SEARCH_DIVISION","8");
              } 
             if (searchFor.equals("Blaster Certificate")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
                String applicationType =context.getInitParameter("application_name_blaster");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
               session.setAttribute("SEARCH_FOR_FINE","Blaster Certificate");  
                session.setAttribute("SEARCH_DIVISION","5");
              } 
            
             if (searchFor.equals("Plan Review")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getCodeContactEmail());
                String applicationType =context.getInitParameter("application_name_planReview");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
               session.setAttribute("SEARCH_FOR_FINE","Plan Review");  
                session.setAttribute("SEARCH_DIVISION","4");
              } 
             if (searchFor.equals("Hazmat Transport Permit")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getHazmatContactEmail());
                String applicationType =context.getInitParameter("application_name_hazmat");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
               session.setAttribute("SEARCH_FOR_FINE","Hazmat Transport Permit");  
                session.setAttribute("SEARCH_DIVISION","15");
              } 
             if (searchFor.equals("UST Certification")) {
                appContacts contact = (appContacts) session.getAttribute("FEE_APP_CONTACTS");
                session.setAttribute("EMAIL_APP_CONTACT",contact.getCodeContactEmail());
                String applicationType =context.getInitParameter("application_name_idhsFines");
                session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
               session.setAttribute("SEARCH_FOR_FINE","UST Certification");  
                session.setAttribute("SEARCH_DIVISION","6");
              } 
             return mapping.findForward("start");
           }
          else if (method.equals("payBlaster")) {
           ShoppingCart cartNew = new ShoppingCart();
           session.setAttribute("SHOPPING_CART_FEE",cartNew);
           ShoppingCart blasterCart = (ShoppingCart) session.getAttribute("SHOPPING_CART_FEE");
           session.setAttribute("SEARCH_FOR_FINE",null);  
           appContacts contact =fDAO.selectAppContacts();
           session.setAttribute("FEE_APP_CONTACTS",contact);  
           String PSID = request.getParameter("PSID");
           String lastName = request.getParameter("lastName");
          int blasterFeeCheck = fDAO.selectBlasterFeeExisting(PSID,"Blaster");
          if (blasterFeeCheck ==0){
            fDAO.insertPermitTransaction(PSID,lastName,"Blaster");}
            session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
           String applicationType =context.getInitParameter("application_name_blaster");
           session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
          session.setAttribute("SEARCH_FOR_FINE","Blaster Certificate");  
           session.setAttribute("SEARCH_DIVISION","5");
           List feeDueList = fDAO.showDues(PSID, "0", "5", blasterCart);
           session.setAttribute("FEE_DUE_LIST",feeDueList);
           session.setAttribute("SEARCH_STATE_NUMBER",PSID);
           session.setAttribute("SEARCH_OWNER_ID","5");
           return mapping.findForward("start"); 
         } 
        else if (method.equals("payOperator")) {
         ShoppingCart cartNew = new ShoppingCart();
         session.setAttribute("SHOPPING_CART_FEE",cartNew);
         ShoppingCart operatorCart = (ShoppingCart) session.getAttribute("SHOPPING_CART_FEE");
         session.setAttribute("SEARCH_FOR_FINE",null);  
         appContacts contact =fDAO.selectAppContacts();
         session.setAttribute("FEE_APP_CONTACTS",contact);  
         String PSID = request.getParameter("PSID");
         String lastName = request.getParameter("lastName");
        int operatorFeeCheck = fDAO.selectBlasterFeeExisting(PSID,"Operator");
        if (operatorFeeCheck ==0){
          fDAO.insertPermitTransaction(PSID,lastName,"Operator");}
          session.setAttribute("EMAIL_APP_CONTACT",contact.getFireContactEmail());
         String applicationType =context.getInitParameter("application_name_blaster");
         session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
        session.setAttribute("SEARCH_FOR_FINE","Blaster Certificate");  
         session.setAttribute("SEARCH_DIVISION","5");
         List feeDueList = fDAO.showDues(PSID, "0", "5", operatorCart);
         session.setAttribute("FEE_DUE_LIST",feeDueList);
         session.setAttribute("SEARCH_STATE_NUMBER",PSID);
         session.setAttribute("SEARCH_OWNER_ID","5");
         return mapping.findForward("start"); 
        }
           else if (method.equals("searchById")) 
           { 
           String stateNumber = request.getParameter("stateNumber");
           String ownerId = request.getParameter("ownerId");
            String division = (String) session.getAttribute("SEARCH_DIVISION");
           List feeDueList = fDAO.showDues(stateNumber, ownerId, division, cart);
           session.setAttribute("FEE_DUE_LIST",feeDueList);
           session.setAttribute("SEARCH_STATE_NUMBER",stateNumber);
           session.setAttribute("SEARCH_OWNER_ID",ownerId);
           return mapping.findForward("start");
         }
        else if (method.equals("searchByIdElevator")) 
        {   appContacts contact =fDAO.selectAppContacts();
             session.setAttribute("FEE_APP_CONTACTS",contact);  
              session.setAttribute("EMAIL_APP_CONTACT",contact.getElevContactEmail());
              String applicationType =context.getInitParameter("application_name_elevators");
              session.setAttribute("SEARCH_APPLICATION_TYPE",applicationType); 
             session.setAttribute("SEARCH_FOR_FINE","Elevators"); 
              session.setAttribute("SEARCH_DIVISION","3"); 
          ShoppingCart cartNew = new ShoppingCart();
          session.setAttribute("SHOPPING_CART_FEE",cartNew);
         String stateNumber = request.getParameter("stateNumber");
        String ownerId = request.getParameter("ownerId");
         String division = (String) session.getAttribute("SEARCH_DIVISION");
        List feeDueList = fDAO.showDues(stateNumber, ownerId, division, cartNew);
        session.setAttribute("FEE_DUE_LIST",feeDueList);
        session.setAttribute("SEARCH_STATE_NUMBER",stateNumber);
        session.setAttribute("SEARCH_OWNER_ID",ownerId);
        return mapping.findForward("start");
        }
        else if (method.equals("addToCart")) 
        { 
        String transactionId = request.getParameter("transactionId");
        feeDetails feeDet =fDAO.selectFeeDetails(transactionId);
        cart.addFeeId(feeDet);
        String division = (String) session.getAttribute("SEARCH_DIVISION");
        String  stateNumber = (String) session.getAttribute("SEARCH_STATE_NUMBER");
        String ownerId = (String) session.getAttribute("SEARCH_OWNER_ID");
        List feeDueList = fDAO.showDues(stateNumber, ownerId, division, cart);
        session.setAttribute("FEE_DUE_LIST",feeDueList);
        return mapping.findForward("start");
        }
        else if (method.equals("addSelectToCart")) 
        { 
          List fees = (List) session.getAttribute("FEE_DUE_LIST");
              Iterator i = fees.iterator();
              while(i.hasNext())
              { feeDetails feeDet = (feeDetails) i.next();
               String payFlag = request.getParameter(feeDet.getTransactionId());
              if (payFlag !=null)  
              {cart.addFeeId(feeDet);  
            } }
         String division = (String) session.getAttribute("SEARCH_DIVISION");
        String  stateNumber = (String) session.getAttribute("SEARCH_STATE_NUMBER");
        String ownerId = (String) session.getAttribute("SEARCH_OWNER_ID");
        List feeDueList = fDAO.showDues(stateNumber, ownerId, division, cart);
        session.setAttribute("FEE_DUE_LIST",feeDueList);
        return mapping.findForward("start");
        }
        else if (method.equals("removeTransaction")) 
        { 
        String transactionId = request.getParameter("transactionId");
        cart.removeFeeId(transactionId);
        String division = (String) session.getAttribute("SEARCH_DIVISION");
        String  stateNumber = (String) session.getAttribute("SEARCH_STATE_NUMBER");
        String ownerId = (String) session.getAttribute("SEARCH_OWNER_ID");
        List feeDueList = fDAO.showDues(stateNumber, ownerId, division, cart);
        session.setAttribute("FEE_DUE_LIST",feeDueList);
        return mapping.findForward("start");
        }     
        else if (method.equals("otherFees")) 
        { 
          session.setAttribute("SEARCH_FOR_FINE",null);  
        return mapping.findForward("start");
        }  
        else if (method.equals("checkout")) 
        { 
            String applicationType =(String) session.getAttribute("SEARCH_APPLICATION_TYPE");
          processCheckout(context,request,response,cart,applicationType); 
       
        return mapping.findForward("start");
        } 
        else if (method.equals("completeCheckout")) 
        { 
          completeCheckout(context,request,response,cart,session,fDAO); 
        return mapping.findForward("start");
        }   
        else if (method.equals("completeCheckoutTest")) 
        { applicationSpecificProcessing(cart,12345,fDAO,"15");
        return mapping.findForward("start");
        }   
          
       
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
        request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
         return mapping.findForward("error");
        
      }
      
      }
  private static void processCheckout(ServletContext context,
   HttpServletRequest request, HttpServletResponse response,ShoppingCart cart, String applicationType) throws Exception
  { 
   
      try{ HttpSession session = request.getSession();
    OrderInfo order = new OrderInfo();
   Address userAddress = new Address();
   userAddress.setAddress1("IdhsFinesAddress1");
    userAddress.setAddress2("IdhsFinesAddress2");
    userAddress.setCity("IdhsFinesCity");
    userAddress.setState("IN");
    userAddress.setZipCode("12345");
   // new for transaction id
  // TransactionInfo[] transInfo = new TransactionInfo[1];  
    //   TransactionInfo transaction = new TransactionInfo();     
   //   transaction.setId(1234599);     
  // end transaction id
   
    ItemInfo[] itemsInfo = new ItemInfo[cart.getFeeIdCounter()];
    Map feeIdmap = cart.getFeeIdMap();
    Set feeIdkeys = feeIdmap.keySet();
    Iterator i = feeIdkeys.iterator();
    int j = -1;
    while(i.hasNext())
    {  
        StringBuffer sku = new StringBuffer("sku:");
      String key = (String) i.next();
      feeDetails feeDet = (feeDetails)  feeIdmap.get(key); 
      ItemInfo item = new ItemInfo();
      sku.append(feeDet.getUniqueNumber());
      item.setSku(sku.toString());
      StringBuffer sb = new StringBuffer();
      sb.append(feeDet.getUniqueNumber());
      sb.append(" (");
      sb.append(feeDet.getPostDate());
      sb.append(")");
      item.setDescription(sb.toString());
      item.setQuantity(new Integer(1));
      item.setUnitCost(new BigDecimal(feeDet.getDue()));
      item.setId(1234599+j);
      
       itemsInfo[++j] = item;
        
    }
    order.setItemsInfo(itemsInfo);
   // transaction.setItemsInfo(itemsInfo);
   // transInfo[0]=transaction;
   // order.setTransactionsInfo(transInfo);  
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
    redirectUrl.append(request.getContextPath()).append("/idhsFeesFines/start.do?method=completeCheckout");
    order.setRedirectUrl(redirectUrl.toString());
    order.setApplicationName(applicationType);
     CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
    ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
    ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
    ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
    String token = checkoutService.createOrder(order);
    cart.setCheckoutId(token);
    session.setAttribute("IDHS_FEES_FINES_CHECKOUT_TOKEN",token); 
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
    finally 
    {
     try {
        
      } catch (Exception e) { }
    }
    
  }
  private static void completeCheckout(ServletContext context,
     HttpServletRequest request,HttpServletResponse response,
        ShoppingCart cart,HttpSession session,finesDataDAO fDAO) throws Exception
  {
    String checkoutToken = (String)  session.getAttribute("IDHS_FEES_FINES_CHECKOUT_TOKEN");
    if(checkoutToken == null || ! checkoutToken.equals(cart.getCheckoutId())) 
    {
      throw new Exception("ERROR_IDHS_FEES_FINES_CHECKOUT_TOKEN");
    }
    CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
    ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
    ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
    ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
    ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
    OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
     StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
     homeUrl.append("<a href='");
     homeUrl.append(request.getContextPath()).append("/idhsFeesFines/start.do'>");
     homeUrl.append("Back to IDHS Payments </a>");
     if(!orderInfo.isAuthorized()) {
     
       throw new ServletException(checkoutToken + " has not been authorized.");
     
     } else if(!orderInfo.isCaptured()) {
        int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
        cart.setReceiptId(receiptId);
        cart.setPaymentType("CC");
          String division = (String) session.getAttribute("SEARCH_DIVISION");
          if (division.equals("6") ||division.equals("11")||division.equals("8")||division.equals("13") 
              ||division.equals("4")||division.equals("15"))
          { sendEmailAppContact(request,session,cart); 
          }
        applicationSpecificProcessing(cart,receiptId,fDAO,division);
        session.setAttribute("SHOPPING_CART_FEE",null);
        session.setAttribute("SEARCH_FOR_FINE",null);  
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
      request.getRequestDispatcher("/idhsFeesFines/receipt.jsp").include(request,bufferedResponse);
     return new String(bufferedResponse.getBuffer());
  }
  private static void applicationSpecificProcessing(ShoppingCart cart,int receiptId, finesDataDAO fDAO,String division) throws Exception {
    Map feeIdmap = cart.getFeeIdMap();
    Set feeIdkeys = feeIdmap.keySet();
    Iterator i = feeIdkeys.iterator();
    while(i.hasNext())
    { 
      String key = (String) i.next();
      feeDetails feeDet = (feeDetails)  feeIdmap.get(key); 
      fDAO.updateFeeRecord(feeDet,receiptId,division);
      
    }
  }
  private static void sendEmailAppContact(HttpServletRequest request,HttpSession session,ShoppingCart cart) throws Exception
    {
            try {
               
              HsMailer mail = new HsMailer();
                String appType= (String) session.getAttribute("SEARCH_FOR_FINE"); 
                String appContactEmail= (String) session.getAttribute("EMAIL_APP_CONTACT");
                String[] emailTo = {appContactEmail};
               StringBuffer sb = new StringBuffer();
              sb.append("Online  payment made for "+appType +" received.").append("\n\r");
               
              sb.append(cart.getAmount() + " is paid by online reference number : "+cart.getReceiptId()).append("\n\r");
              StringBuffer sub = new StringBuffer();
              sub.append(" Online fee payment alert ");
              
              mail.createMail("online_fee_payment@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
             mail.sendAndClose();
              } 
              catch(Exception e) 
              {
                HsMailer mail = new HsMailer();
              mail.createMail("online_fee_payment","nnimmagadda@dhs.in.gov","error online_fee_payment ",Integer.toString(cart.getReceiptId()));
              mail.sendAndClose();
              }
    }
   

}
