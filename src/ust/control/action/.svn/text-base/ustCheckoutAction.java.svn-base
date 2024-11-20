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

public class ustCheckoutAction extends ControlAction
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
        if (method == null)
        {
        if(cart.getAmount() == 0) 
        {
          throw new Exception("Shopping Cart amount zero");
        }
        if (cart.getAmount() > 0 )
         {
          processCheckout(context,request,response,cart,ust);
          return null;
         }
         else
         { 
          return null;
         }
        }
       return mapping.findForward("ustCert");
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
        ShoppingCart shopCart, ustApplicant ust) throws Exception
 {
	HttpSession session = request.getSession();
	 OrderInfo order = new OrderInfo();
   ItemInfo[] itemsInfo = new ItemInfo[1];
         int j = -1;
    Address userAddress = new Address();
     userAddress.setAddress1(ust.getPersonAddress1());
     userAddress.setAddress2(ust.getPersonAddress2());
     userAddress.setCity(ust.getPersonCity());
     userAddress.setState(ust.getPersonState());
     userAddress.setZipCode(ust.getPersonZip());
        StringBuffer sku = new StringBuffer("sku:");
          ItemInfo item = new ItemInfo();
         sku.append("UST");
         item.setSku(sku.toString());
         StringBuffer stb = new StringBuffer();
         stb.append(" UST Certification for : ");
         stb.append(ust.getPersonLastName()+","+ust.getPersonFirstName());
         item.setDescription(stb.toString());
         item.setQuantity(new Integer(1));
         item.setUnitCost(new BigDecimal(shopCart.getAmount()));
         itemsInfo[++j] = item;       
  
	order.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/ust/completeCheckOut.do");
   order.setRedirectUrl(redirectUrl.toString());
    order.setApplicationName(context.getInitParameter("application_name_idhsFines"));
    CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(order);
   
	 session.setAttribute("UST_CERT_CHECKOUT_TOKEN", token);
   shopCart.setCheckoutId(token);
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
 
  
}
  




