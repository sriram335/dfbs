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
import java.util.List;
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
public class ElevContCheckoutAction extends ControlAction{    
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
      ServletContext context = this.getServlet().getServletConfig().getServletContext();
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      DfbsDataMap dmap = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      elevServContractorDAO cDAO = (elevServContractorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_SERVICE_CONTRACTOR);
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap.getHsDAO(DfbsDataMap.UTILITY);
      String method = request.getParameter("method");
      ShoppingCartForm cartForm = (ShoppingCartForm) form;
          HttpSession session = request.getSession();
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
      elevServiceContractor cont = (elevServiceContractor) session.getAttribute("SERVICE_CONTRACTOR_SELECTED");
      ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
      System.out.println(cartForm.getAffirmation());
           if (method.equals("checkout")  && cartForm.getAffirmation()!=null) 
          { 
           processCheckout(context,request,response,cont); 
          return mapping.findForward("selectContractor");
          } 
      if (method.equals("checkout")  && cartForm.getAffirmation()==null) 
      {   session.setAttribute("IDHS_ELEV_CONT_AFFIRMATION","Y");      
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/elevator/servContractor.do?method=backToContractor");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;
        }
      if (method.equals("check")) 
      { 
          int receiptId = Integer.parseInt(request.getParameter("receiptId"));
        cont.setReceiptId(receiptId);
        applicationSpecificProcessing(receiptId,cDAO,contacts,request,cont);
      return mapping.findForward("selectContractor");
      }
      return mapping.findForward("selectContractor");
    }
      catch (Exception e) 
      {
         saveError(request,e);
        request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
         return mapping.findForward("error");
        
      }
    }
  private static void processCheckout(ServletContext context,
   HttpServletRequest request, HttpServletResponse response,elevServiceContractor cont) throws Exception
  { 
   
    HttpSession session = request.getSession();
     OrderInfo order = new OrderInfo();
     ItemInfo[] itemsInfo = new ItemInfo[1];
           int j = -1;
      Address userAddress = new Address();
       userAddress.setAddress1(cont.getContAddress1());
       userAddress.setAddress2(cont.getContAddress2());
       userAddress.setCity(cont.getContCity());
       userAddress.setState(cont.getContState());
       userAddress.setZipCode(cont.getContZip());
          StringBuffer sku = new StringBuffer("sku:");
            ItemInfo item = new ItemInfo();
           sku.append("ServiceContractor");
           item.setSku(sku.toString());
           StringBuffer stb = new StringBuffer();
           stb.append(" Elev Service Contractor Certification for : ");
           stb.append(cont.getContLastName()+","+cont.getContFirstName());
           item.setDescription(stb.toString());
           item.setQuantity(new Integer(1));
           item.setUnitCost(new BigDecimal(cont.getContFee()));
           itemsInfo[++j] = item;       
    
    
    order.setItemsInfo(itemsInfo);
    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
    redirectUrl.append(request.getContextPath()).append("/elevator/completeCheckoutCont.do?method=completeCheckout");
    order.setRedirectUrl(redirectUrl.toString());
    order.setApplicationName(context.getInitParameter("application_name_elevators"));
     CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
    ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
    ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
    ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
    String token = checkoutService.createOrder(order);
    cont.setCheckoutId(token);
    session.setAttribute("IDHS_ELEVATOR_CONT_CHECKOUT_TOKEN",token); 
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
    private static void applicationSpecificProcessing(int receiptId,elevServContractorDAO cDAO,ApplicationContacts contacts,
                                                      HttpServletRequest request,elevServiceContractor cont) throws Exception {
     
            if (cont.getContId()==0) {
               cDAO.insertElevContractor(cont);
              cDAO.insertPermitTransaction(cont);
            }
            else {
              cDAO.updateElevServiceContRenewal(cont);
              cDAO.insertPermitTransaction(cont);
            }
           
       
          }
    protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
    {   
       List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS_CONT);
      List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS2);
       request.setAttribute("CONTRACTOR_FIRST_LETTERS_OPTIONS",charNav);
      request.setAttribute("DFBS_STATE_OPTIONS",states);
       
    } 
    protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
    {
       List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS);
       List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS_CONT);
      List conType = uDAO.getOptions(elevatorSQL.SELECT_CON_TYPE_OPTIONS);
      List conParentList = uDAO.getOptions(elevatorSQL. ELEV_CONTACTORS_OPTIONS);
      List conStatusList = uDAO.getOptions(elevatorSQL. SELECT_CON_STATUS_OPTIONS);
       request.setAttribute("CONTRACTOR_STATE_OPTIONS",states);
      request.setAttribute("CONTRACTOR_FIRST_LETTERS_OPTIONS",charNav);
      request.setAttribute("CON_TYPE_OPTIONS",conType);
      request.setAttribute("CON_PARENT_OPTIONS",conParentList); 
      request.setAttribute("CON_STATUS_OPTIONS",conStatusList);  
    } 
 
  }


