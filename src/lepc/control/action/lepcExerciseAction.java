package lepc.control.action;
import lepc.control.form.*;
import lepc.to.*;
import lepc.data.*;
import main.data.*;
import main.to.*;
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

import org.apache.struts.upload.FormFile;

import otherUsers.data.otherUsersSQL;

import otherUsers.to.otherUsers;

public class lepcExerciseAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcExerciseForm exerForm = (lepcExerciseForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
      fiscalReportDAO fDAO = (fiscalReportDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_FISCAL_REPORT);
      lepcExerciseDAO eDAO = (lepcExerciseDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_EXERCISE);
      lepcAgencyDAO aDAO = (lepcAgencyDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_AGENCY);
      lepcChemicalDAO cDAO = (lepcChemicalDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_CHEMICAL);
      String method = request.getParameter("method");
      HttpSession session = request.getSession();
      ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
      ApplicationContacts contacts = sDAO.setContacts("LEPC_CONTACT1","LEPC_CONTACT2");      
      otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");    
      if (method == null ||((otherUser ==null || otherUser.getUserLoginId().length() < 3 )&&
      (security ==null || security.getUserId().length() < 3))) 
      {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      else if (method.equals("approve")) 
      { String exerciseId =request.getParameter("exerciseId");
        lepcExercise exercise = eDAO.selectExercise(Integer.parseInt(exerciseId));
        exercise.setExerciseStatus("Approved");
        eDAO.updateExercise(exercise);
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+exercise.getLepcId());
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null; 
      } 
      else if (method.equals("addExercise")) 
      { String lepcId =request.getParameter("lepcId");
       exerForm.setReportType("Exercise");
       lepcExercise exercise = new lepcExercise();
       exercise.setReportType("Exercise");
        session.setAttribute("LEPC_EXERCISE", exercise);
        setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("exercise"); 
      } 
      else if (method.equals("addProposal")) 
      { String lepcId =request.getParameter("lepcId");
        lepcExercise exercise = new lepcExercise();
        exercise.setReportType("Proposal");
         session.setAttribute("LEPC_EXERCISE", exercise);
        exerForm.setReportType("Proposal"); 
        setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("exercise"); 
      } 
          
      else if (method.equals("updateExercise")) 
      { String exerciseId =request.getParameter("exerciseId");
        lepcExercise exercise = eDAO.selectExercise(Integer.parseInt(exerciseId));
        exerForm.setProperties(exercise);
        session.setAttribute("LEPC_EXERCISE",exercise); 
        List agencies = aDAO.selectAgencies(Integer.parseInt(exerciseId));
        session.setAttribute("AGENCY_LIST",agencies); 
        List chemicals = cDAO.selectChemicalByExercise(Integer.parseInt(exerciseId));
        session.setAttribute("CHEMICAL_LIST",chemicals); 
        setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("exercise"); 
      } 
          
      else if (method.equals("saveExercise")) 
      { lepcExercise exercise = exerForm.getExercise();
         if (exercise.getExerciseId()==0) {
          String currentDate =(String) session.getAttribute("CURRENT_DATE");
          exercise.setReportDateString(currentDate);
          String userId = otherUser.getUserLoginId();
                 /*   if (userId == null) {
                        userId ="nnimmagadda@dhs.in.gov";
                    }*/
                    eDAO.insertExecise(exercise, userId);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/exercise.do?method=updateExercise&exerciseId="+exercise.getExerciseId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
        }
        else {
          eDAO.updateExercise(exercise);
        }
        session.setAttribute("LEPC_EXERCISE",exercise); 
         setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("exercise"); 
      } 
    throw new Exception("LEPC Start Action"); 
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
   List subTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_EXER_SUB_TYPE);
   request.setAttribute("LEPC_EXER_SUB_TYPE",subTypes);
   
    List exerciseTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_EXER_TYPE);
    request.setAttribute("LEPC_EXER_TYPE",exerciseTypes);
    List proposalTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_PROP_TYPE);
    request.setAttribute("LEPC_PROP_TYPE",proposalTypes);
    
    List incidentTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_INCIDENT_TYPE_LIST);
    request.setAttribute("LEPC_INCIDENT_TYPE",incidentTypes);
    
    List counties = uDAO.getOptions(otherUsersSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
  
  }
}
