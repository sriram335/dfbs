package codeEducation.control.action;
import main.data.*;
import codeEducation.control.form.*;
import main.to.*;
import codeEducation.to.*;
import codeEducation.data.*;
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
import hs.report.pdf.*;
public class CodeEducationPlanAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
            
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
            
          
          DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          
          CodeEduPlanForm planForm = (CodeEduPlanForm) form;
          CodeEducationPlanDAO planDAO = (CodeEducationPlanDAO) dmap2.getHsDAO(DfbsDataMap.CODE_EDU_PLAN);
            HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
          HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          if (method == null ) 
          { String codePersonId = request.getParameter("codePersonId");
            List planList=planDAO.selectPlanList(CodeEducationSQL.SELECT_CODE_EDU_PLANS,Integer.parseInt(codePersonId));
              session.setAttribute("PLAN_LIST",planList);
            return mapping.findForward("planList");
          } 
          else if (method.equals("planList") ) 
          {   String codePersonId = request.getParameter("codePersonId");
              List planList=planDAO.selectPlanList(CodeEducationSQL.SELECT_CODE_EDU_PLANS,Integer.parseInt(codePersonId));
              request.setAttribute("PLAN_LIST",new HsListWrapper(planList));
               return mapping.findForward("planList");
          } 
           
             
            else if (method.equals("addNewPlan") ) 
            { String codeEduType = request.getParameter("codeEduType");
                CodeEducationUser viewUser = ( CodeEducationUser) session.getAttribute("VIEW_USER");
              planForm.setEduType(codeEduType);
              planForm.setCodePersonId(viewUser.getUserPersonId());
             session.setAttribute("PLAN_TYPE",codeEduType);
                List planList=planDAO.selectPlanList(CodeEducationSQL.SELECT_CODE_EDU_PLANS,viewUser.getUserPersonId());
                request.setAttribute("PLAN_LIST",new HsListWrapper(planList));
              return mapping.findForward("addNewPlan");
            } 
            else if (method.equals("updatePlan") ) 
            {   String planId = request.getParameter("planId");
           CodeEducationPlan plan = planDAO.selectPlan(Integer.parseInt(planId));
           planForm.setProperties(plan);
                session.setAttribute("UPDATE_PLAN_SELECTED",plan);
              return mapping.findForward("updatePlan");
            } 
            else if (method.equals("updateSavePlan") ) 
            {                   CodeEducationPlan plan =  planForm.getCodePlan();
                          CodeEduPlanForm errorForm = new CodeEduPlanForm();
                        
                           if (validatePlan(plan,errorForm,planDAO))
                           {
                               planDAO.updatePlan(plan);
                               CodeEducationUser codeEduUser = (CodeEducationUser) session.getAttribute("VIEW_USER");
                               codeEduUser.setPlans(planDAO.selectPlanList(CodeEducationSQL.SELECT_CODE_EDU_PLANS,codeEduUser.getUserPersonId()));
                             //  List planList=planDAO.selectPlanList(CodeEducationSQL.SELECT_CODE_EDU_PLANS,plan.getCodePersonId());
                              // request.setAttribute("PLAN_LIST",new HsListWrapper(planList));
                               return mapping.findForward("manageUser");
                           }
                            else {
                                return mapping.findForward("updatePlan");
                            }
            } 
            else if (method.equals("savePlan") ) 
            {
                CodeEducationPlan plan =  planForm.getCodePlan();
                          CodeEduPlanForm errorForm = new CodeEduPlanForm();
                        
                           if (validatePlan(plan,errorForm,planDAO))
                           {
                               planDAO.insertPlan(plan);
                               CodeEducationUser codeEduUser = (CodeEducationUser) session.getAttribute("VIEW_USER");
                               codeEduUser.setPlans(planDAO.selectPlanList(CodeEducationSQL.SELECT_CODE_EDU_PLANS,codeEduUser.getUserPersonId()));
                               return mapping.findForward("manageUser");
                           }
                            else {request.setAttribute("PLAN_ERROR",errorForm);
                                return mapping.findForward("addNewPlan");
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
    protected static boolean validatePlan(CodeEducationPlan plan ,CodeEduPlanForm errorForm,CodeEducationPlanDAO cDAO) throws Exception
     {
       boolean noError = true;
       
      
       
        if(plan.getEduDescription() == null || plan.getEduDescription().trim().equals("")  )
       {
       errorForm.setEduDescription("ERROR");
        noError = false;
         }
       else
       {  
           errorForm.setEduDescription("");
          
       }
         
       
       return noError;
     }
}