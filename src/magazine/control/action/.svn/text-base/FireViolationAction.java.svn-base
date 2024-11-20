package magazine.control.action;
import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;
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



public class FireViolationAction extends ControlAction
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
        
        
        FireViolationForm violationForm = (FireViolationForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
     //   FireInspectionDAO iDAO = (FireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_MAG_INSPECTION);
      //  FireViolationDAO vDAO = (FireViolationDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_MAG_VIOLATION);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method.equals("violationList")) 
        {  
           int inspectionId = Integer.parseInt(request.getParameter("inspectionId"));
           FireInspection inspection = oDAO.selectInspection(inspectionId);
           inspection.setViolations(oDAO.selectViolations(inspection.getInspId()));
           session.setAttribute("INSPECTION_VIOLATIONS",inspection);
           return mapping.findForward("violationList");
        }
        else if (method.equals("updateViolation")) 
        {int violationId = Integer.parseInt(request.getParameter("violationId"));
          FireViolation violation=oDAO.selectViolation(violationId);
          violationForm.setProperties(violation);
          session.setAttribute("VIOLATION_SELECTED",violation);
         return mapping.findForward("updateViolation");
        }
         else if (method.equals("newViolation")) 
        { 
          return mapping.findForward("newViolation");
        }
         else if (method.equals("violationLookup")) 
        {String stdViolation = request.getParameter("stdViolation");
         List list = oDAO.selectStdViolations(stdViolation);
         request.setAttribute("STANDARD_VIOLATION",new HsListWrapper(list));
         return mapping.findForward("newViolation");
        }
        
        else if (method.equals("addStdViolation")) 
        {String stdVioCode = request.getParameter("stdVioCode");
         String stdVioDescription = oDAO.selectStdVioDescription(stdVioCode);
         violationForm.setVioCode(stdVioCode);
         violationForm.setVioDescription(stdVioDescription);
        return mapping.findForward("newViolation");
        }
        else if (method.equals("saveViolation")) 
        { FireInspection inspection = (FireInspection) session.getAttribute("INSPECTION_SELECTED");
          int inspectionId = inspection.getInspId();
          FireViolation violation = violationForm.getFireViolation();
          violation.setInspectionId(inspectionId); 
          FireViolationForm errorForm = new FireViolationForm();
          if (validate(violation,errorForm) ) 
          {
          if (violation.isNew())
          {setOptions(request,dfbsUtilityDAO);
            oDAO.insertViolation(violation);
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/inspection.do?method=backToInspection");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          }
          else
          {
            oDAO.updateViolation(violation);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/inspection.do?method=backToInspection");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          }
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("VIOLATION_ERROR",errorForm);
            violationForm.setProperties(violation);
            if (violation.isNew())
            {
             return mapping.findForward("newViolation");
            }
            else
            {
              return mapping.findForward("updateViolation");
            }
           
          }
        
        
        } 
        else if (method.equals("backToInspection")) 
        { 
           return mapping.findForward("updateInspection");
        }
      
        
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 

 
 protected static boolean validate(FireViolation violation,FireViolationForm errorForm) throws Exception
  {
    boolean noError = true;
   
   
    if(violation.getVioDescription() == null || violation.getVioDescription().trim().equals("")  ) 
    { 
      errorForm.setVioDescription("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setVioDescription("");
    }
    
    
    if(violation.getVioCorDateString() == null || violation.getVioCorDateString().trim().equals("") ) 
    { 
      errorForm.setVioCorDate("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setVioCorDate("");
    }
    
    
 /*   
    if(violation.getVioCompDateString() == null || violation.getVioCompDateString().trim().equals("") ) 
    { 
      errorForm.setVioCompDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setVioCompDate("");
    }
    */
    
      if (violation.getVioCode() == null || violation.getVioCode().trim().equals("") )
    { 
      errorForm.setVioCode("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setVioCode("");
    }
    return noError;
  } 
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List inspType = uDAO.getOptions(DfbsSQL.SELECT_INSPECTION_TYPE);
    List inspectors = uDAO.getOptions(DfbsSQL.SELECT_INSPECTORS);
    List inspOptions = uDAO.getOptions(DfbsSQL.SELECT_INSP_OPTIONS);
    List alaramOptions = uDAO.getOptions(DfbsSQL.SELECT_ALARAM_OPTIONS);
    request.setAttribute("INSPECTION_TYPE_OPTIONS",inspType);
    request.setAttribute("INSPECTORS_OPTIONS",inspectors);
    request.setAttribute("ALARAM_OPTIONS",alaramOptions);
    request.setAttribute("COMPLIED_OPTIONS",inspOptions);
     
    
    
 } 
  
}
