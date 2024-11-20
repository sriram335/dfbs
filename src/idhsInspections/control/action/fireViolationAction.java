package idhsInspections.control.action;


import fireworks.to.DfbsFireworksPermit;

import idhsInspections.control.*;
import idhsInspections.control.form.*;
import idhsInspections.to.*;
import idhsInspections.data.*;
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
import main.to.*;
import hs.util.*;
import hs.data.*;
import main.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import org.apache.struts.upload.FormFile;

public class fireViolationAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           
           ServletContext context = 
           this.getServlet().getServletConfig().getServletContext();
          
           
           DfbsDataMap dmap2 = (DfbsDataMap)
           context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           
           
           fireViolationForm violationForm = (fireViolationForm) form;
          
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           fireInspectionDAO iDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
           idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION);
           fireViolationDAO vDAO = (fireViolationDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_VIOLATION);
           ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
           String method = request.getParameter("method");
           HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
             ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
             if (method.equals("violationList")) 
                     {  
                        int inspectionId = Integer.parseInt(request.getParameter("inspectionId"));
                         fireInspection inspection = iDAO.selectInspection(inspectionId);
                        inspection.setViolations(vDAO.selectIdhsViolations(inspection.getInspId()));
                        session.setAttribute("INSPECTION_VIOLATIONS",inspection);
                        return mapping.findForward("violationList");
                     }
                     else if (method.equals("updateIdhsViolation")) 
                     {int violationId = Integer.parseInt(request.getParameter("violationId"));
                       fireViolation violation=vDAO.selectIdhsViolation(violationId);
                       violationForm.setProperties(violation);
                       session.setAttribute("VIOLATION_SELECTED",violation);
                       fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                       List violations =  inspection.getViolations();
                         Iterator i = violations.iterator();
                         while(i.hasNext())
                         {
                           fireViolation violationForMap = (fireViolation) i.next();
                           inspection.addViolation(violationForMap);                              
                         }
                      return mapping.findForward("updateIdhsViolation");
                     }
                     else if (method.equals("saveAllViolations")) 
                     {
                      String vioType =  request.getParameter("vioType");
                         fireViolation violationForMap=violationForm.getfireViolation();
                         fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                          inspection.removeViolation(violationForMap.getVioId());
                         inspection.addViolation(violationForMap);
                         if(vioType.equals("Next")) {                          
                             fireViolation nextViolation=vDAO.selectIdhsViolationMax(violationForMap.getVioId(),violationForMap.getInspectionId());
                                if (nextViolation.getVioDescription() !=null){
                                 violationForm.setProperties(nextViolation);
                                 session.setAttribute("VIOLATION_SELECTED",nextViolation); 
                                 request.setAttribute("VIO_NAVIGATION","");}
                                else {
                                  request.setAttribute("VIO_NAVIGATION","Reached Last Violation");
                                }
                                   return mapping.findForward("updateIdhsViolation");
                                
                        }
                         if(vioType.equals("Previous")) {                             
                             fireViolation nextViolation=vDAO.selectIdhsViolationMin(violationForMap.getVioId(),violationForMap.getInspectionId());
                             if (nextViolation.getVioDescription() !=null){
                                  violationForm.setProperties(nextViolation);
                                 session.setAttribute("VIOLATION_SELECTED",nextViolation); 
                                  request.setAttribute("VIO_NAVIGATION","");}
                               else {
                                 request.setAttribute("VIO_NAVIGATION","Reached First Violation");
                               }
                                 return mapping.findForward("updateIdhsViolation");
                         }
                         if(vioType.equals("Save")) {    
                                 Map violationsMap = inspection.getViolationMap(); 
                                 Set violationkeys = violationsMap.keySet();
                                 Iterator i = violationkeys.iterator();
                             while(i.hasNext())
                                 { 
                                  String key = (String) i.next();
                                  fireViolation violation = (fireViolation) violationsMap.get(key); 
                                   violation.setVioDateString(iDAO.formatDfbsDate(violation.getVioDateString()));
                                  violation.setVioCompDateString(iDAO.formatDfbsDate(violation.getVioCompDateString()));
                                  vDAO.updateViolation(violation);
                                 }
                             return mapping.findForward("updateIdhsViolation");
                             
                         }
                         if(vioType.equals("Back To Inspection")) { 
                             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                             redirectUrl.append("/dfbs/idhsInspections/idhsInspection.do?method=backToInspection");
                              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                           return null;

                         }
                     }
                     
                     else if (method.equals("deleteIdhsViolation")) 
                     {int violationId = Integer.parseInt(request.getParameter("violationId"));
                        vDAO.deleteViolation(violationId);
                         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                         redirectUrl.append("/dfbs/idhsInspections/idhsInspection.do?method=backToInspection");
                         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;

                     }
                     else if (method.equals("newIdhsViolation")) 
                     { setOptions(request,dfbsUtilityDAO);
                       String year = request.getParameter("year");
                         List vioCode= vDAO.selectStdVioCode(year);
                          request.setAttribute("VIO_CODE_LIST",new HsListWrapper(vioCode));  
                       return mapping.findForward("newIdhsViolation");
                     }
                     else if (method.equals("addVioCode")) 
                     {String vioCode = request.getParameter("vioCode");
                         violationForm.setVioCode(vioCode);
                         setOptions(request,dfbsUtilityDAO);
                         List vioCodeList= vDAO.selectStdVioCode(vioCode.substring(0,4));
                          request.setAttribute("VIO_CODE_LIST",new HsListWrapper(vioCodeList));  
                        return mapping.findForward("newIdhsViolation");
                     }
                    
                     else if (method.equals("saveIdhsViolation")) 
                     { fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                       int inspectionId = inspection.getInspId();
                       fireViolation violation = violationForm.getfireViolation();
                       violation.setInspectionId(inspectionId); 
                       fireViolationForm errorForm = new fireViolationForm();
                       if (validate(violation,errorForm) ) 
                       {
                       if (violation.isNew())
                       {violation.setVioDateString(iDAO.formatDfbsDate(violation.getVioDateString()));
                         violation.setVioCompDateString(iDAO.formatDfbsDate(violation.getVioCompDateString()));
                         vDAO.insertIdhsViolation(violation,inspection);
                         violationForm.setVioId(violation.getVioId());
                        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                       redirectUrl.append(request.getContextPath()).append("/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId="+violation.getInspectionId());
                       response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));		
                       return null;
                       }
                       else
                       { violation.setVioDateString(iDAO.formatDfbsDate(violation.getVioDateString()));
                         violation.setVioCompDateString(iDAO.formatDfbsDate(violation.getVioCompDateString()));
                         vDAO.updateViolation(violation);
                       StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                       redirectUrl.append(request.getContextPath()).append("/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId="+violation.getInspectionId());
                       response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                         return null;
                        } } 
                       else 
                       { 
                        setOptions(request,dfbsUtilityDAO);
                        request.setAttribute("VIOLATION_ERROR",errorForm);
                         violationForm.setProperties(violation);
                         if (violation.isNew())
                         {
                          return mapping.findForward("newIdhsViolation");
                         }
                         else
                         {
                           return mapping.findForward("updateIdhsViolation");
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
         request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
          return mapping.findForward("error");
         
       }
           
         
           
         
     }
    

    
    protected static boolean validate(fireViolation violation,fireViolationForm errorForm) throws Exception
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
   List inspType = uDAO.getOptions(InspectionsSQL.SELECT_INSPECTION_TYPE);
   List inspectors = uDAO.getOptions(InspectionsSQL.SELECT_INSPECTORS);
   List inspOptions = uDAO.getOptions(InspectionsSQL.SELECT_INSP_OPTIONS);
   List alaramOptions = uDAO.getOptions(InspectionsSQL.SELECT_ALARAM_OPTIONS);
   List idhsMaintInspOptions = uDAO.getOptions(InspectionsSQL.IDHS_MAINT_INSP_EXCEL_TYPE_OPTIONS);
    List idhsInspOptions = uDAO.getOptions(InspectionsSQL.IDHS_EXCEL_INSP_TYPE_OPTIONS);
   List idhsInspActivityOptions = uDAO.getOptions(InspectionsSQL.IDHS_INSP_ACTIVITY_OPTIONS);
    List idhsStPrefixOptions = uDAO.getOptions(InspectionsSQL.IDHS_ST_PREFIX_OPTIONS);
    List idhsStSuffixOptions = uDAO.getOptions(InspectionsSQL.IDHS_ST_SUFFIX_OPTIONS);
    List idhsRGOptions = uDAO.getOptions(InspectionsSQL.IDHS_RED_GREEN_OPTIONS);
    List idhSYesNoptions = uDAO.getOptions(InspectionsSQL.IDHS_YES_NO_OPTIONS);
    List countyOptions2 = uDAO.getOptions(InspectionsSQL.SELECT_COUNTY_OPTIONS2);
    List inspStatusOptions = uDAO.getOptions(InspectionsSQL.IDHS_INSP_STATUS_OPTIONS);
   request.setAttribute("INSPECTION_TYPE_OPTIONS",inspType);
   request.setAttribute("INSPECTORS_OPTIONS",inspectors);
   request.setAttribute("ALARAM_OPTIONS",alaramOptions);
   request.setAttribute("COMPLIED_OPTIONS",inspOptions);
    request.setAttribute("IDHS_MAINT_INSP_TYPE_OPTIONS",idhsMaintInspOptions); 
    request.setAttribute("IDHS_INSP_TYPE_OPTIONS",idhsInspOptions); 
    request.setAttribute("IDHS_INSP_ACTIVITY_OPTIONS",idhsInspActivityOptions); 
    request.setAttribute("IDHS_ST_PREFIX_OPTIONS",idhsStPrefixOptions); 
    request.setAttribute("IDHS_ST_SUFFIX_OPTIONS",idhsStSuffixOptions); 
    request.setAttribute("IDHS_RG_OPTIONS",idhsRGOptions); 
    request.setAttribute("IDHS_YES_NO_OPTIONS",idhSYesNoptions); 
    request.setAttribute("IDHS_COUNTY_OPTIONS2",countyOptions2);
    request.setAttribute("IDHS_INSP_STATUS_OPTIONS",inspStatusOptions);
    
  }

}
