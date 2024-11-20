package elevator.control.action;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import elevator.to.*;
import elevator.control.form.*;
import elevator.data.*;
import hs.control.ControlAction;

import hs.data.system.HsUtilityDAO;

import hs.util.HsConstant;

import hs.util.HsMailer;

import java.io.IOException;

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

public class elevCheckoutAction extends ControlAction{    
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
      ServletContext context = this.getServlet().getServletConfig().getServletContext();
      DfbsDataMap dmap2 = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      elevatorOwnerDAO oDAO = ( elevatorOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_OWNER);
      elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
      elevApplicationDAO aDAO = (elevApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_APPLICATION);  
      String method = request.getParameter("method");
        ShoppingCartForm cartForm = (ShoppingCartForm) form;
       
          HttpSession session = request.getSession();
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            DfbsOwner owner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");  
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
      ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
            
           if (method.equals("checkout")) 
          { 
               processCheckout(context,request,response,cart); 
          
          return mapping.findForward("start");
          } 
      if (method.equals("check")) 
      { int receiptId = Integer.parseInt(request.getParameter("receiptId"));
        applicationSpecificProcessing(cart,receiptId,oDAO,eDAO,aDAO,contacts,request);
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
  private static void processCheckout(ServletContext context,
   HttpServletRequest request, HttpServletResponse response,ShoppingCart cart) throws Exception
  { 
   
    HttpSession session = request.getSession();
    OrderInfo order = new OrderInfo();
   Address userAddress = new Address();
   userAddress.setAddress1("IdhsFinesAddress1");
    userAddress.setAddress2("IdhsFinesAddress2");
    userAddress.setCity("IdhsFinesCity");
    userAddress.setState("IN");
    userAddress.setZipCode("12345");
    ItemInfo[] itemsInfo = new ItemInfo[cart.getTotalPermits()];
    Map ownermap = cart.getOwnerMap();
    Set ownerkeys = ownermap.keySet();
    Iterator i = ownerkeys.iterator();
      while(i.hasNext())
      {  
        String ownerkey = (String) i.next();
        DfbsOwner owner = (DfbsOwner)  ownermap.get(ownerkey); 
          Map elevAppMap =owner.getElevatorAppMap();
          Set appkeys = elevAppMap.keySet();
          Iterator j = appkeys.iterator();    
             int k = -1;
            while(j.hasNext())
            {  
              StringBuffer sku = new StringBuffer("sku:");
              String appkey = (String) j.next();
              elevatorApplication eleApp = (elevatorApplication)  elevAppMap.get(appkey); 
              ItemInfo item = new ItemInfo();
              sku.append(eleApp.getStateNumber());
              item.setSku(sku.toString());
              StringBuffer sb = new StringBuffer();
              sb.append(eleApp.getStateNumber());
              sb.append(" (");
              sb.append(eleApp.getApplicationDateString());
              sb.append(":"); 
              sb.append(eleApp.getApplicationType());  
              sb.append(")");
              item.setDescription(sb.toString());
              item.setQuantity(new Integer(1));
              item.setUnitCost(new BigDecimal(eleApp.getAppFee()));
              itemsInfo[++k] = item;
                
            }
      }
    order.setItemsInfo(itemsInfo);
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
    redirectUrl.append(request.getContextPath()).append("/elevator/completeCheckout.do?method=completeCheckout");
    order.setRedirectUrl(redirectUrl.toString());
    order.setApplicationName(context.getInitParameter("application_name_elevators"));
     CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
    ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
    ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
    ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
    String token = checkoutService.createOrder(order);
    cart.setCheckoutId(token);
    session.setAttribute("IDHS_ELEVATOR_CHECKOUT_TOKEN",token); 
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
            //  sendEmailInsp(request,elevAppl,contacts,owner,cart);
            
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
              String[] bccTo = {"nnimmagadda@dhs.in.gov"};
             
              StringBuffer sb = new StringBuffer();
              sb.append(owner.getOwnerDBA()+ ": submitted following online applications.").append("\n\r");  
              sb.append(app.getStateNumber() + ": applied for alteration/installation permit.").append("\n\r");
              sb.append("$"+app.getAppFee() + " paid and online reference number is "+cart.getReceiptId()).append("\n\r");
              sb.append("Use this link to view information:").append("\n\r");
              sb.append("https://oas.dhs.in.gov/dfbs/elevator/start.do").append("\n\r");
                   StringBuffer sub = new StringBuffer();
              sub.append(" From IDHS  Elevator online transaction alert New online system test !!IGNORE!!");
              
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


