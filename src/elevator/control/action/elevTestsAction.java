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

import hs.report.pdf.*;

import main.to.ApplicationSecurity;

public class elevTestsAction extends ControlAction {
    
  public ActionForward executeControl(ActionMapping mapping,
   ActionForm form,HttpServletRequest request, HttpServletResponse response) 
   throws IOException, ServletException
   {
       try {
         ServletContext context =     this.getServlet().getServletConfig().getServletContext();
         
         DfbsDataMap dmap = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
         DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
         elevTestsForm elevTestForm = (elevTestsForm) form;
         
         HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap.getHsDAO(DfbsDataMap.UTILITY);
         elevInspectionDAO iDAO = (elevInspectionDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_INSPECTION);
         elevTestsDAO tDAO = (elevTestsDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_TESTS);  
         elevatorDAO oDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
         String method = request.getParameter("method");
         HttpSession session = request.getSession();
           ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
         elevInspection inspection =  (elevInspection) session.getAttribute("ELEVATOR_INSPECTION_SELECTED");
           if (method.equals("addTestsList")) 
                   {  
                     List updateTestList =tDAO.selectElevTestsList(inspection.getInspectionId());
                     if (updateTestList.size() > 0) {
                       session.setAttribute("ELEV_UPDATE_TESTS",new HsListWrapper(updateTestList));
                        return mapping.findForward("elevUpdateTests");
                     }
                     else
                     {List testList =tDAO.selectElevStandrdTestsList();
                     session.setAttribute("ELEV_TESTS",new HsListWrapper(testList));
                      return mapping.findForward("elevNewTests");
                     }
                   }
                   else if (method.equals("saveTestsList")) 
                   {List rule13List =tDAO.selectElevStandrdTestsList();
                      Iterator i = rule13List.iterator();
                    int x =1;
                       while(i.hasNext())
                       { x=x+1;
                       elevTests elevTest = (elevTests) i.next();
                        StringBuffer sb1 = new StringBuffer("");
                        sb1.append(Integer.toString(elevTest.getTestId()));
                        String status = request.getParameter(sb1.toString());
                         StringBuffer sb2 = new StringBuffer("");
                        sb2.append((elevTest.getTestValue()));
                      
                        String testValue = request.getParameter(sb2.toString());
                         elevTest.setInspectionId(inspection.getInspectionId());
                         elevTest.setTestStatus(status);
                         elevTest.setTestValue(testValue); 
                          tDAO.insertElevTests(elevTest);    
                           
                      } 
                       StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                       redirectUrl.append("/dfbs/elevator/inspection.do?method=elevUpdateInspection&inspectionId="+inspection.getInspectionId());
                       response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                     return null;

                   }
                  else if (method.equals("updateTest")) 
                       {  int testId = Integer.parseInt (request.getParameter("testId"));
                         elevTests elevTest = tDAO.selectElevTest(testId);
                         elevTestForm.setProperties(elevTest);
                         session.setAttribute("UPDATE_TEST_SELECTED",elevTest);
                           return mapping.findForward("updateTest");
                       }
               else if (method.equals("updateSaveTest")) 
                    {  
                      elevTests elevTest = elevTestForm.getElevTests();
                      tDAO.updateElevTests(elevTest);
                      elevTestForm.setProperties(elevTest);
                         return mapping.findForward("updateTest");
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
}
