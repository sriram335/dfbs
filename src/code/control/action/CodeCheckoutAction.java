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

public class CodeCheckoutAction  extends ControlAction
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
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        
        
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
       
        if(manufacturer == null ) 
        {
          throw new Exception("NO_MANUFACTURER_IN_SESSION");
        }
        if(method == null)
        {
         Map releasesMap = manufacturer.getDesignReleaseMap();
        manufacturer.setFileStatus("YES");
       Set releasekeys = releasesMap.keySet();
       Iterator l = releasekeys.iterator();
       while(l.hasNext()){
       String keyr = (String) l.next();
        CodeDesignRelease release = (CodeDesignRelease) releasesMap.get(keyr);
        if (release.getFileStatus().equals("NO"))
        {
          manufacturer.setFileStatus("NO");
        }
        
       }
        if (manufacturer.getFileStatus().equals("YES"))
        {
        processCheckout(context,request,response,manufacturer);
        return null;
        }
        else
        {
               return mapping.findForward("view");
        }
        }
         else if(method.equals("check"))
       {  String checkNumber  = request.getParameter("checkNumber");
          applicationSpecificProcessing(mDAO, manufacturer, manufacturer.getManufacturerNameId());
         manufacturer.setFileStatus("CHECKED OUT");
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/code/main.do?method=view&manufacturerNameId="+manufacturer.getManufacturerNameId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
       }
         return(null);
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
        CodeManufacturer manufacturer) throws Exception
 {
   HttpSession session = request.getSession();
	 
   
   OrderInfo cart = new OrderInfo();
   
   Address userAddress = new Address();
   userAddress.setAddress1(manufacturer.getManufacturerAddress1());
   userAddress.setAddress2(manufacturer.getManufacturerAddress2());
   userAddress.setCity(manufacturer.getManufacturerCity());
   userAddress.setState(manufacturer.getManufacturerState());
	 userAddress.setZipCode(manufacturer.getManufacturerZip());
   int itemsCount;
  if(manufacturer.getTotalAmountSeals() > 0  && manufacturer.getTotalAmountReleases() > 0)
    {
      itemsCount=2;
    }
    else
    {
       itemsCount=1;
    }
   
   ItemInfo[] itemsInfo = new ItemInfo[itemsCount];
   int j = -1;
   StringBuffer sku = new StringBuffer("sku:");
     if(manufacturer.getTotalAmountSeals() > 0 ) 
     {
     ItemInfo item = new ItemInfo();
     sku.append("Seals");
     item.setSku(sku.toString());
     StringBuffer sb = new StringBuffer();
     sb.append(" (");
     sb.append(manufacturer.getManufacturerName().toUpperCase());
     sb.append(")");
     sb.append(manufacturer.getTotalSealsCount());
     sb.append(" seals purchased");
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(manufacturer.getTotalAmountSeals()));
     itemsInfo[++j] = item;
     }
     if(manufacturer.getTotalAmountReleases() > 0 ) 
     {
     ItemInfo item = new ItemInfo();
     sku.append("Release(s)");
     item.setSku(sku.toString());
     StringBuffer sb = new StringBuffer();
     sb.append(" (");
     sb.append(manufacturer.getManufacturerName().toUpperCase());
     sb.append(")-");
     sb.append(manufacturer.getDesignReleaseCounter());
     sb.append(" releases applied");
     item.setDescription(sb.toString());
     item.setQuantity(new Integer(1));
     item.setUnitCost(new BigDecimal(manufacturer.getTotalAmountReleases()));
     itemsInfo[++j] = item;
     }
	 cart.setItemsInfo(itemsInfo);
   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/code/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
  
   cart.setApplicationName(context.getInitParameter("application_name_code"));


	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);

   String token = checkoutService.createOrder(cart);
   session.setAttribute("AI_DHS_HOUSING_CHECKOUT_TOKEN", token);
   manufacturer.setCheckoutId(token);

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
		  return ;

		}
  
  
  
  
 }
 

  private static void applicationSpecificProcessing
      (CodeManufacturerDAO mDAO,CodeManufacturer manufacturer, int receiptId) throws Exception {
    
   
    
      mDAO.saveRecords(manufacturer,receiptId,null);
    }

 
}
  



