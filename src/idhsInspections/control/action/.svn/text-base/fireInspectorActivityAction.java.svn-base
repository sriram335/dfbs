package idhsInspections.control.action;
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

import main.data.DfbsDataMap;

import org.apache.struts.upload.FormFile;
public class fireInspectorActivityAction extends ControlAction{
        public ActionForward executeControl(ActionMapping mapping,
         ActionForm form,HttpServletRequest request, HttpServletResponse response) 
         throws IOException, ServletException
         {
             try {
               
               
               ServletContext context = 
               this.getServlet().getServletConfig().getServletContext();
              
               
               DfbsDataMap dmap2 = (DfbsDataMap)
               context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
               
               
               inspectorActivityForm inspActivityForm = (inspectorActivityForm) form;
              
               HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
               fireInspActivityDAO iDAO = (fireInspActivityDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSP_ACTIVITY);
                 fireInspectionDAO fiDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
               idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION);
               fireViolationDAO vDAO = (fireViolationDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_VIOLATION);
               ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
               String method = request.getParameter("method");
               HttpSession session = request.getSession();
               HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
                 ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
                 if (method==null) {return mapping.findForward("inspectionStart"); 
                 }
              
                 else if (method.equals("updateInspActivity")) 
                 {int inspActivityId = Integer.parseInt(request.getParameter("inspActivityId"));
                   fireInspectorActivity inspActivity =iDAO.selectInspActivity(inspActivityId);
                   inspActivityForm.setProperties(inspActivity);
                   session.setAttribute("INSP_ACTIVITY_SELECTED",inspActivity);
                   setOptions(request,dfbsUtilityDAO);
                  return mapping.findForward("updateInspActivity");
                 }
               else if (method.equals("deleteInspActivity")) 
               {int inspActivityId = Integer.parseInt(request.getParameter("inspActivityId"));
                 iDAO.deleteInspActivity(inspActivityId);
                 setOptions(request,dfbsUtilityDAO);
                 return mapping.findForward("start");
               }
                 else if (method.equals("saveUpdateInspActivity")) 
                 {    fireInspectorActivity inspActivity =  inspActivityForm.getInspActivity();
                 inspectorActivityForm errorForm = new inspectorActivityForm();
                 if (validate(inspActivity,errorForm) ) 
                 {
                 iDAO.updateInspActivity(inspActivity);
                     String inspectorIdString= (String) session.getAttribute("INSPECTOR_CURRENT"); 
                     String currentDate= fiDAO.selectCurrentDate();
                     List activityList =  iDAO.selectInspeActivities(Integer.parseInt(inspectorIdString),currentDate,currentDate);
                     session.setAttribute("ACTIVITIES_SELECTED",activityList);
                   setOptions(request,dfbsUtilityDAO);
                   return mapping.findForward("start");
                 } 
                 else {  request.setAttribute("INSP_ACTIVITY_ERROR",errorForm);
                     }
                 inspActivityForm.setProperties(inspActivity);
                     session.setAttribute("INSP_ACTIVITY_SELECTED",inspActivity);
                   setOptions(request,dfbsUtilityDAO);
                  return mapping.findForward("updateInspActivity");
                 }
                 else if (method.equals("saveInsertInspActivity")) 
                 {    fireInspectorActivity inspActivity =  inspActivityForm.getInspActivity();
                     inspectorActivityForm errorForm = new inspectorActivityForm();
                     if (validate(inspActivity,errorForm) )  
                 { String inspectorIdString= (String) session.getAttribute("INSPECTOR_CURRENT"); 
                 iDAO.insertInspActivity(inspActivity,Integer.parseInt(inspectorIdString));
                     inspActivityForm.setProperties(inspActivity);
                     session.setAttribute("INSP_ACTIVITY_SELECTED",inspActivity);
                   setOptions(request,dfbsUtilityDAO);
                     String currentDate= fiDAO.selectCurrentDate();
                     List activityList =  iDAO.selectInspeActivities(Integer.parseInt(inspectorIdString),currentDate,currentDate);
                     session.setAttribute("ACTIVITIES_SELECTED",activityList);
                   return mapping.findForward("start");
                 }
                 else { setOptions(request,dfbsUtilityDAO);
                     request.setAttribute("INSP_ACTIVITY_ERROR",errorForm);
                     return mapping.findForward("newInspActivity");
                 }
                 }
                else if (method.equals("newInspActivity")) 
               { setOptions(request,dfbsUtilityDAO); 
                   String inspectorIdString= (String) session.getAttribute("INSPECTOR_CURRENT"); 
                   String currentDate= fiDAO.selectCurrentDate();
                 List activityList =  iDAO.selectInspeActivities(Integer.parseInt(inspectorIdString),currentDate,currentDate);
                   session.setAttribute("ACTIVITIES_SELECTED",activityList);
                return mapping.findForward("newInspActivity");
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
     
     List idhsInspActivityOptions = uDAO.getOptions(InspectionsSQL.IDHS_INSP_ACTIVITY_OPTIONS);
     
      request.setAttribute("IDHS_INSP_ACTIVITY_OPTIONS",idhsInspActivityOptions); 
      
      List otherCounties = uDAO.getOptions(InspectionsSQL.SELECT_COUNTY_OPTIONS);
      
      request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
      
      List inspFor = uDAO.getOptions(InspectionsSQL.SELECT_SEARCH_FOR_OPTIONS);
      
      request.setAttribute("SEARCH_FOR_OPTIONS",inspFor);
        List inspectors = uDAO.getOptions(InspectionsSQL.SELECT_INSPECTORS);
        
        request.setAttribute("INSPECTORS_OPTIONS",inspectors);
      
    }
    protected static boolean validate(fireInspectorActivity inspActivity,inspectorActivityForm errorForm) throws Exception
     {
       boolean noError = true;
           
       if(inspActivity.getActivityType() == null || inspActivity.getActivityType().trim().equals("")  ) 
       { 
         errorForm.setActivityType("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setActivityType("");
       }
      
       
       
      
      
     
       return noError;
     } 
}
