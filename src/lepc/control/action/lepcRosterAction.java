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

import lepc.data.*;
import lepc.to.*;
import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;

import main.to.ApplicationSecurity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import otherUsers.to.otherUsers;

public class lepcRosterAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcRosterForm rosterForm = (lepcRosterForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
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
      else if (method.equals("addRoster")) 
      { String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
        lepcRoster roster= rDAO.selectRosterByLepc(Integer.parseInt(lepcId));
        if (roster !=null) {
          List repList= rDAO.selectRepPersons(roster.getRosterId()); 
          session.setAttribute("ROSTER_REP_LIST",repList);
          List repListAdmin= rDAO.selectRepPersonsAdmin(roster.getRosterId()); 
          session.setAttribute("ROSTER_REP_LIST_ADMIN",repListAdmin);
          rosterForm.setProperties(roster); 
          String rosterCheck =rDAO.rosterCheck(roster.getRosterId());
          session.setAttribute("ROSTER_REP_CHECK",rosterCheck);
          session.setAttribute("LEPC_DOC_ROSTER",roster);
        }
        else
        {
        session.setAttribute("ROSTER_REP_LIST",null);
        session.setAttribute("ROSTER_REP_LIST_ADMIN",null);
        session.setAttribute("LEPC_DOC_ROSTER",null);
        }
       return mapping.findForward("addRoster"); 
        
      } 
        else if (method.equals("updateRoster")) 
        { 
          String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
          lepcRoster roster= rDAO.selectRosterByLepc(Integer.parseInt(lepcId));
          List repList= rDAO.selectRepPersons(roster.getRosterId()); 
          String rosterCheck =rDAO.rosterCheck(roster.getRosterId());
          session.setAttribute("ROSTER_REP_CHECK",rosterCheck);
          session.setAttribute("ROSTER_REP_LIST",repList);
          List repListAdmin= rDAO.selectRepPersonsAdmin(roster.getRosterId()); 
          session.setAttribute("ROSTER_REP_LIST_ADMIN",repListAdmin);
          rosterForm.setProperties(roster); 
          session.setAttribute("LEPC_DOC_ROSTER",roster);
         return mapping.findForward("addRoster"); 
        } 
        else if (method.equals("copyRoster")) 
        { 
          String county =(String) session.getAttribute("LEPC_COUNTY_SELECTED");
          String rosterId =(String) request.getParameter("rosterId");;
          String lepcId =(String) request.getParameter("lepcId");;                                                           
          rDAO.copyRoster(lepcId,rosterId,county); 
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
        } 
        else if (method.equals("approve")) 
        { 
          String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
          String rosterId =(String) request.getParameter("rosterId");
          String appDate =(String) request.getParameter("rosterAddress2");
          lepcRoster roster= rDAO.selectRoster(Integer.parseInt(rosterId));
          roster.setRosterStatus("Approved");
          rDAO.updateRoster(roster);
          rDAO.updateRosterPersons(roster,appDate);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
        } 
        else if (method.equals("saveRoster")) 
        { 
          lepcRoster roster= rosterForm.getRoster();
          String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
          roster.setLepcId(Integer.parseInt(lepcId));
          if (roster.getRosterId()== 0) {
            rDAO.insertRoster(roster,otherUser.getUserLoginId());
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster&lepcId="+lepcId);
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null; 
          }
          else { 
            if (roster.getRosterAddBy()==null || roster.getRosterAddBy().length()==0) {
              roster.setRosterAddBy(otherUser.getUserLoginId());
            }
            if (roster.getRosterDateString()==null || roster.getRosterDateString().length()==0) {
              String currentDate =sDAO.selectCurrentDate();
              roster.setRosterDateString(currentDate);
            }
            rDAO.updateRoster(roster);
          }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
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
}
