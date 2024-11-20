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

public class HazmatCarrierAction  extends ControlAction

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
        
        
        HazmatCarrierForm carrierForm = (HazmatCarrierForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        HazmatCarrierDAO cDAO = (HazmatCarrierDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_CARRIER);
        HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
         HazmatShipmentDAO sDAO = (HazmatShipmentDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_SHIPMENT);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method == null ) 
        {
          
          return mapping.findForward("carrier");
        } 
        
         else if(method.equals("saveCarrier")) 
        {
           HazmatCarrier carrier = carrierForm.getHazmatCarrier();
           
           HazmatCarrierForm errorForm = new HazmatCarrierForm();
           if (validate(carrier,errorForm) ) 
             {
             HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
              permit.addCarrier(carrier);
              session.setAttribute("HAZMAT_CARRIER",carrier);
               return mapping.findForward("hazmatApp");
             } 
             else 
             { 
               request.setAttribute("HAZMAT_CARRIER_ERROR",errorForm);
               return mapping.findForward("addCarrier");
             }
        }  
         else if(method.equals("saveEditedCarrier")) 
        {
           HazmatCarrier updatedCarrier = carrierForm.getHazmatCarrier();
           
           HazmatCarrierForm errorForm = new HazmatCarrierForm();
           if (validate(updatedCarrier,errorForm) ) 
             {
                  HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
              HazmatCarrier carrier = (HazmatCarrier) session.getAttribute("HAZMAT_EDIT_CARRIER");
              carrierForm.setUpdatedProperties(updatedCarrier,carrier);
              session.setAttribute("HAZMAT_CARRIER",carrier);
               session.setAttribute("HAZMAT_EDIT_CARRIER",null);
              return mapping.findForward("hazmatApp");
             } 
             else 
             {
               request.setAttribute("HAZMAT_CARRIER_ERROR",errorForm);
               return mapping.findForward("editCarrier");
             }
        }  
        else if (method.equals("addCarrier")) 
        {
          return mapping.findForward("addCarrier");
        } 
        else if (method.equals("editCarrier")) 
        {
          String key = request.getParameter("key");
          HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
          HazmatCarrier carrier = permit.getCarrier(key);
          session.setAttribute("HAZMAT_EDIT_CARRIER",carrier);
         // permit.removeCarrier(key);
          carrierForm.setProperties(carrier);
          return mapping.findForward("editCarrier");
        } 
        else if (method.equals("removeCarrier")) 
        { 
          String key = request.getParameter("key");
           HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
           HazmatCarrier carrier = permit.getCarrier(key);
          permit.removeCarrier(key);
           sDAO.computeFees(permit);
          return mapping.findForward("hazmatApp");
        }     
        
        
       
        
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 


  protected static boolean validate(HazmatCarrier carrier,HazmatCarrierForm errorForm) throws Exception
  {
    boolean noError = true;
   
   
    if(carrier.getCarrierName() == null || carrier.getCarrierName().trim().equals("")  ) 
    { 
      errorForm.setCarrierName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCarrierName("");
    }
   
     if(carrier.getCarrierContact() == null || carrier.getCarrierContact().trim().equals("")  ) 
    { 
      errorForm.setCarrierContact("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCarrierContact("");
    }
     if(carrier.getCarrierTitle() == null || carrier.getCarrierTitle().trim().equals("")  ) 
    { 
      errorForm.setCarrierTitle("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCarrierTitle("");
    }
   
     if(carrier.getCarrierPhone() == null || carrier.getCarrierPhone().trim().equals("")  ) 
    { 
      errorForm.setCarrierPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCarrierPhone("");
    }
    
    
    return noError;
  }
  
 
  
 
 
 
}
