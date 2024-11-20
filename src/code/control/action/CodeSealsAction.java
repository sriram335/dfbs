package code.control.action;

import code.to.*;
import code.control.form.*;
import code.data.* ;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
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
public class CodeSealsAction extends ControlAction
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
        
        SealOrderForm sealForm = (SealOrderForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CodeFacilityDAO fDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
      
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
         if  (method.equals("processSeals"))
          
        {
          String orderId = request.getParameter("orderId");
          SealOrder seal = fDAO.selectOrderSeals(Integer.parseInt(orderId)); 
          session.setAttribute("SEAL_ORDER",seal);
          sealForm.setProperties(seal);
          setOptions(request,dfbsUtilityDAO);
          List sealUsage=fDAO.selectCodeSealUsage(Integer.parseInt(orderId));
           request.setAttribute("SEAL_USAGE",new HsListWrapper(sealUsage));
          return mapping.findForward("processSeals");
        }
        if  (method.equals("createSealRecord"))
          
        {
          CodeFacility facility = (CodeFacility) session.getAttribute("FACILITY");
          SealOrder sealOld =(SealOrder) session.getAttribute("SEAL_ORDER");
          SealOrder seal = sealForm.getsealOrder();
          fDAO.updateSealOrder(seal);
          setOptions(request,dfbsUtilityDAO);
          if (sealOld.getMsealsFrom() ==0 && seal.getMsealsFrom() >0 && sealOld.getMsealsTo()==0 && seal.getMsealsTo()>0)
          {    int i=seal.getMsealsFrom();
              while (i<=seal.getMsealsTo())
              {String sealNumber = "M"+Integer.toString(i);
               String sealDate =seal.getOrderDateString();
              fDAO.createSealUsageRecord(sealNumber,seal,manufacturer,facility);
              i=i+1;
              }
          }
           if (sealOld.getPsealsFrom() ==0 && seal.getPsealsFrom() >0 && sealOld.getPsealsTo()==0 && seal.getPsealsTo()>0)
          {    int i=seal.getPsealsFrom();
              while (i<=seal.getPsealsTo())
              {String sealNumber = "P"+Integer.toString(i);
               String sealDate =seal.getOrderDateString();
              fDAO.createSealUsageRecord(sealNumber,seal,manufacturer,facility);
              i=i+1;
              }
          }
          return mapping.findForward("processSeals");
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
    List company = uDAO.getOptions(CodeSQL.SELECT_INSP_COMPANY_OPTIONS);
    request.setAttribute("CODE_COMPANY_OPTIONS",company);
 
 
    List inspector = uDAO.getOptions(CodeSQL.SELECT_INSPECTOR_OPTIONS);
    request.setAttribute("CODE_INSPECTOR_OPTIONS",inspector);
    
    
 }
}
