package code.control.action;

import code.to.*;
import code.control.form.*;
import code.data.* ;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.data.* ;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
public class CodeFacilityAction extends ControlAction
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
        
        CodeFacilityForm facilityForm = (CodeFacilityForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CodeFacilityDAO fDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
        CodeManufacturerDAO oDAO = (CodeManufacturerDAO) dmap2.getHsDAO(DfbsDataMap.MANUFACTURER);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
         if  (method.equals("viewFacility"))
          
        {
          int id = Integer.parseInt(request.getParameter("facilityId"));
          String facId = request.getParameter("facilityId");
          CodeFacility facility = manufacturer.getFacility(facId); 
           session.setAttribute("FACILITY",facility);
           this.setViewFacility(request,fDAO,facilityForm,id);
          
          return mapping.findForward("viewFacility");
        }
         else if (method.equals("buySeals")) 
        {
      
          return mapping.findForward("buySeals");
        }
         else if (method.equals("postSeals")) 
        {
          String facId = request.getParameter("facilityId");
          int pseals = Integer.parseInt(request.getParameter("pseals"));
          int mseals = Integer.parseInt(request.getParameter("mseals"));
         
          CodeFacility facility = manufacturer.getFacility(facId); 
          facility.setMseals(mseals);
          facility.setPseals(pseals);
          String sealDetails = (String) session.getAttribute("SEAL_PURCHASE_DETAILS");
          if (sealDetails == null) {
            sealDetails ="";
          }
           sealDetails = sealDetails+","+facility.getFacilityName()+": M seals"+mseals+": P seals"+pseals;
          session.setAttribute("SEAL_PURCHASE_DETAILS",sealDetails);
          fDAO.computeFees(manufacturer);
          return mapping.findForward("backtoview");
        } 
        else if (method.equals("backtoview")) 
        {
          
          return mapping.findForward("backtoview");
        }
        else if (method.equals("sealOrders")) 
        {
          String facId = request.getParameter("facilityId");
          CodeFacility facility = fDAO.selectFacility(Integer.parseInt(facId)); 
          session.setAttribute("FACILITY",facility);
          List facOrders=fDAO.selectFacilityOrders(facility);
          request.setAttribute("SEAL_ORDER_LIST",new HsListWrapper(facOrders));
          return mapping.findForward("sealOrders");
        } 
         else if (method.equals("sealOrders")) 
        {
          String facId = request.getParameter("facilityId");
          CodeFacility facility = fDAO.selectFacility(Integer.parseInt(facId)); 
          List facOrders=fDAO.selectFacilityOrders(facility);
          request.setAttribute("SEAL_ORDER_LIST",new HsListWrapper(facOrders));
          return mapping.findForward("sealOrders");
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
 
  
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"rpickard@dhs.in.gov","cclouse@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
          
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append("* From codeEnforcement Online * - ").append(subject);
          
          
          
            mail.createMail("codeEnforcement_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
           HsMailer mail1 = new HsMailer();
            mail1.createMail("codeEnforcement_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From codeEnforcement Online error email ","CodeFacilityAction");
            mail1.sendAndClose();  
           }
  }
  

  protected static CodeFacility setViewFacility(HttpServletRequest request,
    CodeFacilityDAO fDAO,
      CodeFacilityForm facilityForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
   
    CodeFacility facility = fDAO.selectFacility(id);
    
    session.setAttribute("FACILITY",facility);
    
    facilityForm.setProperties(facility);
    
    return facility;
  }
 
}
