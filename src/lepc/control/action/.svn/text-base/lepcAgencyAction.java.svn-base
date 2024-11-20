package lepc.control.action;
import hs.control.ControlAction;

import hs.data.system.HsUtilityDAO;

import hs.util.HsConstant;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lepc.control.form.*;
import childcare.control.form.*;
import lepc.data.*;
import lepc.to.*;
import childcare.to.*;
import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;

import main.to.ApplicationSecurity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import otherUsers.to.otherUsers;
public class lepcAgencyAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcAgencyForm agencyForm = (lepcAgencyForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
      lepcAgencyDAO aDAO = (lepcAgencyDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_AGENCY);
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
      else if (method.equals("addAgency")) 
      { String exerciseId = request.getParameter("exerciseId");
        agencyForm.setExerciseId(Integer.parseInt(exerciseId)); 
        setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("agency"); 
      } 
        else if (method.equals("saveAgency")) 
        { 
           lepcAgency agency= agencyForm.getAgency();
           if (agencyForm.getAgencyOther().length()>3) {
             agency.setAgencyName(agencyForm.getAgencyOther());
           }
          if (agency.getAgencyId()== 0) {
            aDAO.insertAgency(agency);
          }
          else {
            aDAO.updateAgency(agency);
          }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/exercise.do?method=updateExercise&exerciseId="+agency.getExerciseId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
        } 
        else if (method.equals("updateAgency")) 
        { 
          String agencyId =request.getParameter("agencyId");
          lepcAgency agency= aDAO.selectAgency(Integer.parseInt(agencyId));
          
          agencyForm.setProperties(agency);
          setOptions(request,dfbsUtilityDAO);
          
           session.setAttribute("AGENCY_UPDATE",agency);
         return mapping.findForward("agency"); 
        } 
        
      throw new Exception("LEPC Agency Action"); 
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
   List agencyTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_AGENCY_TYPE);
   request.setAttribute("LEPC_AGENCY_TYPE_OPTIONS",agencyTypes);
  
  }
}
