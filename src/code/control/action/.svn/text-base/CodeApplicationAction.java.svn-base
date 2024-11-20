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
public class CodeApplicationAction extends ControlAction
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
        
        CodeManufacturerForm manufacturerForm = (CodeManufacturerForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CodeManufacturerDAO mDAO = (CodeManufacturerDAO) dmap2.getHsDAO(DfbsDataMap.MANUFACTURER);
        CodeFacilityDAO fDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
        
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
           
        if(method.equals("editManufacturer")) 
        {  
            int manufacturerNameId = Integer.parseInt(request.getParameter("manufacturerNameId"));
            manufacturer = CodeAction.setView(request,mDAO,manufacturerForm,manufacturerNameId,fDAO);
            manufacturerForm.setProperties(manufacturer);
            CodeManufacturer newManufacturer = manufacturerForm.getCodeManufacturer();
             session.setAttribute("MANUFACTURER",newManufacturer);
             setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("editManufacturer");
        } 
      
       
         if(method.equals("saveManufacturer")) 
        {  
         CodeManufacturer updatedManufacturer = manufacturerForm.getCodeManufacturer();
         manufacturerForm.setProperties(updatedManufacturer);
          return mapping.findForward("view");
          
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
  
 
  
 

 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(CodeSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("DFBS_STATE_OPTIONS",states);
    //request.setAttribute("DFBS_CITIES_OPTIONS",cities);
 }
 
 
 



 
}