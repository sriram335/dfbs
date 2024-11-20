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
public class lepcChemicalAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcChemicalForm chemForm = (lepcChemicalForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
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
      else if (method.equals("addChemical")) 
      { String exerciseId = request.getParameter("exerciseId");
        List chemList = cDAO.selectChemicalList();
        session.setAttribute("STANDARD_CHEMICAL_LIST",chemList);
        session.setAttribute("CHEM_EXERCISE_ID",exerciseId);
        chemForm.setExerciseId(Integer.parseInt(exerciseId)); 
        setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("chemical"); 
      } 
        else if (method.equals("addChemicalFromList")) 
        { String casNumber = request.getParameter("casNumber");
          lepcChemList chem = cDAO.selectChemicalListByCas(casNumber);
          String exerciseId = (String) session.getAttribute("CHEM_EXERCISE_ID");
          chemForm.setExerciseId(Integer.parseInt(exerciseId)); 
          chemForm.setChemName(chem.getChemCas()+" : "+chem.getChemName()+" : "+chem.getChemicalName());
          if (chem.getChemCerlaRq() !=null)
          {
          chemForm.setRqForChem(chem.getChemCerlaRq());
          chemForm.setIsChemCercla("Yes");
            chemForm.setIsChemEHS("No");
          }
          if (chem.getChemEhsRq() !=null) {
            chemForm.setRqForChem(chem.getChemEhsRq());
            chemForm.setIsChemEHS("Yes");
          }
          if (chemForm.getIsChemCercla() ==null) {
            chemForm.setIsChemCercla("No");
          }
          session.setAttribute("CHEMICAL_QUANTITY",chemForm.getRqForChem());
          setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("chemical"); 
        } 
        else if (method.equals("saveChemical")) 
        { 
           lepcChemical chem= chemForm.getChemical();
           String chemQuantity = (String) session.getAttribute("CHEMICAL_QUANTITY");
           if (chemForm.getQuantityReleased()==null || chemForm.getQuantityReleased().equals("")|| (Integer.parseInt(chemQuantity) > Integer.parseInt(chemForm.getQuantityReleased()))) {
             session.setAttribute("CHEMICAL_QUANTITY_CHECK","Y" );
             return mapping.findForward("chemical"); 
           }
           else
           { session.setAttribute("CHEMICAL_QUANTITY_CHECK","N" );
            if (chem.getChemicalId()== 0) {
            cDAO.insertChemical(chem);
          }
          else {
            cDAO.updateChemical(chem);
          }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/exercise.do?method=updateExercise&exerciseId="+chem.getExerciseId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
           }   
        } 
        else if (method.equals("updateChemical")) 
        { 
          String chemicalId =request.getParameter("chemicalId");
          lepcChemical chem= cDAO.selectChemical(Integer.parseInt(chemicalId));
          chemForm.setProperties(chem);
          setOptions(request,dfbsUtilityDAO);
           session.setAttribute("CHEMICAL_UPDATE",chem);
         return mapping.findForward("chemical"); 
        } 
        else if (method.equals("deleteChemical")) 
        { 
          String chemicalId =request.getParameter("chemicalId");
          lepcChemical chem= cDAO.selectChemical(Integer.parseInt(chemicalId));
          cDAO.deleteChemical(Integer.parseInt(chemicalId));
          setOptions(request,dfbsUtilityDAO);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/exercise.do?method=updateExercise&exerciseId="+chem.getExerciseId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
        } 
        
      throw new Exception("LEPC Chemical Action"); 
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
