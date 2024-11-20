package hazmat.control.action;

import hazmat.control.form.*;
import hazmat.to.*;
import hazmat.data.*;
import hazmat.report.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.to.*;
import main.data.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;


public class HazmatShipmentAction  extends ControlAction
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
        
        
        HazmatShipmentForm shipmentForm = (HazmatShipmentForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        HazmatCarrierDAO cDAO = (HazmatCarrierDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_CARRIER);
        HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
        HazmatShipmentDAO sDAO = (HazmatShipmentDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_SHIPMENT);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method == null ) 
        {
          
          return mapping.findForward("shipment");
        } 
        
         else if(method.equals("saveShipment")) 
        { setOptions(request,dfbsUtilityDAO);
           HazmatShipment shipment = shipmentForm.getHazmatShipment();
           HazmatShipmentForm errorForm = new HazmatShipmentForm();
           if (validate(shipment,errorForm) ) {
              session.setAttribute("HAZMAT_SHIPMENT",shipment);
              HazmatCarrier carrier = (HazmatCarrier) session.getAttribute("HAZMAT_CARRIER"); 
              HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG"); 
              carrier.addShipment(shipment); 
              sDAO.computeFees(permit); 
              return mapping.findForward("hazmatApp");
           } 
           else 
           {  
             setOptions(request,dfbsUtilityDAO);
             request.setAttribute("HAZMAT_SHIPMENT_ERROR",errorForm);
             return mapping.findForward("addShipment");
           }
        }  
        else if (method.equals("addShipment")) 
        { setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("addShipment");
        } 
        else if (method.equals("editShipment")) 
        { setOptions(request,dfbsUtilityDAO);
          String key = request.getParameter("key");
          HazmatCarrier carrier = (HazmatCarrier) session.getAttribute("HAZMAT_CARRIER");
          HazmatShipment shipment = carrier.getShipment(key);
          shipmentForm.setProperties(shipment);
          carrier.removeShipment(key);
          return mapping.findForward("editShipment");
        } 
        else if (method.equals("removeShipment")) 
        { 
          String key = request.getParameter("key");
           
           HazmatCarrier carrier = (HazmatCarrier) session.getAttribute("HAZMAT_CARRIER");
           HazmatShipment shipment = carrier.getShipment(key);
          carrier.removeShipment(key);
          HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
           sDAO.computeFees(permit);
          return mapping.findForward("hazmatApp");
        }     
        else if (method.equals("verifyPermit")) 
        {  String permitNumber = request.getParameter("shipPermitNumber");
           String issueDate = request.getParameter("shipIssueDate");
           HazmatPermit org = new HazmatPermit();
           HazmatCarrier carrier = new HazmatCarrier();
          org.setOrgName("NO DATA");
          HazmatShipment shipment = sDAO.findShipment(permitNumber,issueDate,org,carrier);
          session.setAttribute("HAZMAT_ORGANIZATION_VERIFY",org);
          session.setAttribute("HAZMAT_CARRIER_VERIFY",carrier);
          session.setAttribute("HAZMAT_SHIPMENT_VERIFY",shipment);
          return mapping.findForward("verify");
        }     
        
        else if (method.equals("verify")) 
        { 
        
          return mapping.findForward("verify");
        }  
        
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 


  
  protected static boolean validate(HazmatShipment shipment,HazmatShipmentForm errorForm) throws Exception
  {
    boolean noError = true;
   
   
    if(shipment.getShipDestination() == null || shipment.getShipDestination().trim().equals("")  ) 
    { 
      errorForm.setShipDestination("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipDestination("");
    }
    if(shipment.getShipOrigin() == null || shipment.getShipOrigin().trim().equals("")  ) 
    { 
      errorForm.setShipOrigin("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipOrigin("");
    }
     if(shipment.getShipDate() == null || shipment.getShipDateString().trim().equals("")  ) 
    { 
      errorForm.setShipDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipDate("");
    }
    if(shipment.getShipAmount() == null || shipment.getShipAmount().trim().equals("")  ) 
    { 
      errorForm.setShipAmount("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipAmount("");
    }
     if(shipment.getShipRadLevel() == null || shipment.getShipRadLevel().trim().equals("")  ) 
    { 
      errorForm.setShipRadLevel("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipRadLevel("");
    }
    
     if(shipment.getShipMaterialType() == null || shipment.getShipMaterialType().trim().equals("")  ) 
    { 
      errorForm.setShipMaterialType("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipMaterialType("");
    }
     if(shipment.getShipActIsotope() == null || shipment.getShipActIsotope().trim().equals("")  ) 
    { 
      errorForm.setShipActIsotope("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipActIsotope("");
    }
      if(shipment.getShipIsotope() == null || shipment.getShipIsotope().trim().equals("")  ) 
    { 
      errorForm.setShipIsotope("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipIsotope("");
    }
       if(shipment.getShipVicvsa() == null || shipment.getShipVicvsa().trim().equals("")  ) 
    { 
      errorForm.setShipVicvsa("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipVicvsa("");
    }
      if(shipment.getShipRoute() == null || shipment.getShipRoute().trim().equals("")  ) 
    { 
      errorForm.setShipRoute("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShipRoute("");
    }
    if(shipment.getShipmentType() == null || shipment.getShipmentType().trim().equals("")  ) 
    {
    errorForm.setShipmentType("ERROR");
    noError = false;
    }
    else
    {
    errorForm.setShipmentType("");
    }
    if(shipment.getShipmentType().equals("RAIL") && shipment.getNumberOfCasks() ==0  ) 
    {
    errorForm.setNumberOfCasks(-1);
    noError = false;
    }
    else
    {
    errorForm.setNumberOfCasks(0);
    }
    return noError;
  }
  
 
  
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List radLevels = uDAO.getOptions(HazmatPermitSQL.SELECT_RAD_LEVEL_OPTIONS);
    request.setAttribute("SHIPMENT_RAD_LEVEL_OPTIONS",radLevels);
 
    List states = uDAO.getOptions(HazmatPermitSQL.SELECT_STATE_OPTIONS);
    request.setAttribute("SHIPMENT_CVSA_STATE_OPTIONS",states);
    
     List cvsaYN = uDAO.getOptions(HazmatPermitSQL.SHIPMENT_CVSA_YN_OPTIONS);
    request.setAttribute("SHIPMENT_CVSA_YN_OPTIONS",cvsaYN);
    
   List shipmentTypes = uDAO.getOptions(HazmatPermitSQL.SELECT_SHIPMENT_TYPE_OPTIONS);
   request.setAttribute("SHIPMENT_TYPE_OPTIONS",shipmentTypes);
   
 }
 
 
}
