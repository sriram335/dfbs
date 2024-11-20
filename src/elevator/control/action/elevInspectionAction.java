package elevator.control.action;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import hs.control.form.*;
import elevator.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import main.data.*;
import elevator.data.*;

import elevator.to.*;

import hs.data.system.*;


import main.to.ApplicationSecurity;
public class elevInspectionAction extends ControlAction{    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context =     this.getServlet().getServletConfig().getServletContext();
         
          DfbsDataMap dmap = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          elevInspectionForm elevInspForm = (elevInspectionForm) form;
         
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap.getHsDAO(DfbsDataMap.UTILITY);
          elevInspectionDAO iDAO = (elevInspectionDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_INSPECTION);
          elevatorDAO oDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
          HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            DfbsOwner owner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");
          if (method == null) 
          { 
            return mapping.findForward("ownerListDisplayAll");
          } 
          if (method.equals("inspectionList"))
             {       
                     return mapping.findForward("inspectionList"); 
                     
              }
         if (method.equals("newElevInspection"))
            {         setOptions(request,dfbsUtilityDAO);
                    elevInspForm.setStateNumber(elev.getStateNumber());
                    return mapping.findForward("elevNewInspection"); 
                    
             }
          if (method.equals("newElevInspection"))
             {         setOptions(request,dfbsUtilityDAO);
                     elevInspForm.setStateNumber(elev.getStateNumber());
                     return mapping.findForward("elevNewInspection"); 
                     
              }
          if (method.equals("elevUpdateInspection"))
             {         setOptions(request,dfbsUtilityDAO);
                       int inspectionId = Integer.parseInt(request.getParameter("inspectionId"));
                elevInspection elevInsp = iDAO.selectElevInspection(inspectionId);
                elevInspForm.setProperties(elevInsp);
                session.setAttribute("ELEVATOR_INSPECTION_SELECTED",elevInsp);
                      return mapping.findForward("elevUpdateInspection"); 
                     
              }
          if (method.equals("saveInsertInspection")) {
            elevInspection elevInsp = elevInspForm.getElevInspection();
            String sessionId=session.getId();
            elevInsp.setComments(sessionId);
            elevInspectionForm errorForm = new elevInspectionForm();
            if (validate(elevInsp,errorForm) ) 
            { setOptions(request,dfbsUtilityDAO);
            iDAO.insertElevInspection(elevInsp);
            elevInspForm.setProperties(elevInsp);
              session.setAttribute("ELEVATOR_INSPECTION_SELECTED",elevInsp);
              return mapping.findForward("elevUpdateInspection");
            }
            else
            { setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("elevNewInspection");
            }
          }
          if (method.equals("saveUpdateInspection")) {
            elevInspection elevInsp = elevInspForm.getElevInspection();
             elevInspectionForm errorForm = new elevInspectionForm();
            if (validate(elevInsp,errorForm) ) 
            { setOptions(request,dfbsUtilityDAO);
              
              
            iDAO.updateElevInspection(elevInsp);
            elevInspForm.setProperties(elevInsp);
              session.setAttribute("ELEVATOR_INSPECTION_SELECTED",elevInsp);
              return mapping.findForward("elevUpdateInspection");
            }
            else
            { setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("elevUpdateInspection");
            }
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
     List yesNoOptions = uDAO.getOptions(elevatorSQL.ELEV_YES_NO_OPTIONS);
     List inspCategory = uDAO.getOptions(elevatorSQL.ELEV_INSP_CATEGORY_OPTIONS);
    List inspType = uDAO.getOptions(elevatorSQL.ELEV_INSP_TYPE_OPTIONS);
    List inspContractors = uDAO.getOptions(elevatorSQL.ELEV_CONTACTORS_OPTIONS);
    List inspSerContractors = uDAO.getOptions(elevatorSQL.ELEV_CONTACTOR_OPTIONS);
    List inspMechannic = uDAO.getOptions(elevatorSQL.ELEV_MECHANIC_OPTIONS);
    List inspStatus = uDAO.getOptions(elevatorSQL.ELEV_INSP_STATUS_OPTIONS);
    List inspectors = uDAO.getOptions(elevatorSQL.SELECT_INSPECTORS);
    
     request.setAttribute("ELEV_YN_OPTIONS",yesNoOptions);
     request.setAttribute("ELEV_INSP_TYPE_OPTIONS",inspType);
    request.setAttribute("ELEV_INSP_STATUS_OPTIONS",inspStatus);
    request.setAttribute("ELEV_INSP_CAT_OPTIONS",inspCategory);
    request.setAttribute("ELEV_CON_COMPANY_OPTIONS",inspContractors );
    request.setAttribute("ELEV_CONTRACTOR_OPTIONS",inspSerContractors);
    request.setAttribute("ELEV_MECHANIC_OPTIONS",inspMechannic);
    request.setAttribute("INSPECTORS_OPTIONS",inspectors);
  }   
  protected static boolean validate(elevInspection inspection,elevInspectionForm errorForm) throws Exception
   {
     boolean noError = true;
         
     if(inspection.getInspectionDateString() == null || inspection.getInspectionDateString().trim().equals("")  ) 
     { 
       errorForm.setInspectionDate("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setInspectionDate("");
     }
     if(inspection.getInspCategory() == null || inspection.getInspCategory().trim().equals("")  ) 
     { 
       errorForm.setInspCategory("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setInspCategory("");
     }
      
     
     if(inspection.getInspectionStatus() == null || inspection.getInspectionStatus().trim().equals("")  ) 
     { 
       errorForm.setInspectionStatus("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setInspectionStatus("");
     }
    
     if(inspection.getInspectionType() == null || inspection.getInspectionType().trim().equals("")  ) 
     { 
       errorForm.setInspectionType("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setInspectionType("");
     }
    
     return noError;
   } 
}