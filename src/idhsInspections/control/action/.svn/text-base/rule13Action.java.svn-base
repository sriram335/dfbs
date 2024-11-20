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

import org.apache.struts.upload.FormFile;
public class rule13Action extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           
           ServletContext context = 
           this.getServlet().getServletConfig().getServletContext();
          
           
           DfbsDataMap dmap2 = (DfbsDataMap)
           context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           
           
           rule13Form ruleForm = (rule13Form) form;
          
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           fireInspectionDAO iDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
           idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION);
           rule13DAO rDAO = (rule13DAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION_RULE13);
           ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
           String method = request.getParameter("method");
           HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
             ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
             if (method.equals("addRuleList")) 
                     {  
                        
                        List ruleList =rDAO.selectRule13sInfo();
                        session.setAttribute("RULE13",new HsListWrapper(ruleList));
                        return mapping.findForward("addRuleList");
                     }
                     else if (method.equals("saveList")) 
                     {List rule13List =rDAO.selectRule13sInfo();
                        Iterator i = rule13List.iterator();
                         fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                         while(i.hasNext())
                        {
                          rule13 rule = (rule13) i.next();
                          StringBuffer sb = new StringBuffer("");
                          sb.append(Integer.toString(rule.getRule13SeqId()));
                          String param = request.getParameter(sb.toString());
                          if(param != null ) 
                          {   rule.setInspectionId(inspection.getInspId());
                              rule.setRule13Remarks(param);
                              rDAO.insertRule13(rule,Integer.parseInt(inspection.getInspFacId()));
                          }
                        }
                         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                         redirectUrl.append("/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId="+inspection.getInspFacId());
                         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;

                     }
                    
             if (method.equals("updateRuleList")) 
                     {  
                         fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                        List ruleList =rDAO.selectRule13s(inspection.getInspId());
                        session.setAttribute("RULE13_UPDATE",new HsListWrapper(ruleList));
                        return mapping.findForward("updateRuleList");
                     }
             if (method.equals("editRule13")) 
                     {   String responseId = request.getParameter("responseId");
                         rule13 rule=rDAO.selectRule13(Integer.parseInt(responseId));
                        ruleForm.setProperties(rule);
                           return mapping.findForward("editRule13");
                     }
                     
             if (method.equals("editSaveRule")) 
                     {  
                         rule13 rule= ruleForm.getRule13();
                         rDAO.updateRule13(rule);
                         fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                         List ruleList =rDAO.selectRule13s(inspection.getInspId());
                         session.setAttribute("RULE13_UPDATE",new HsListWrapper(ruleList));
                         return mapping.findForward("updateRuleList");
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
}
