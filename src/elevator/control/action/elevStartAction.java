package elevator.control.action;

import elevator.control.form.DfbsOwnerForm;

import elevator.data.elevatorDAO;
import elevator.to.*;
import hs.control.ControlAction;

import hs.data.system.HsUtilityDAO;

import hs.to.HsUser;

import hs.util.HsConstant;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;

import main.to.FileNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class elevStartAction extends ControlAction {
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
         
          DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY); 
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
         
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
          session.setAttribute("SESSION_ID",session.getId());
         
          ApplicationContacts contacts = sDAO.setContacts("ELEVATOR_CONTACT1","ELEVATOR_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
          if (method == null) 
          { 
            ShoppingCart cart = new ShoppingCart(); 
            session.setAttribute("SHOPPING_CART",cart);
            session.setAttribute("ELEVATOR_ONLINE_APP_STATUS",null);
             FileNames names= sDAO.selectAppStatus("ELEV_PERMITS_MAINT_FLAG");
            session.setAttribute("ELEV_PERMITS_APP_STATUS",names.getFileName());
            session.setAttribute("ELEV_PERMITS_APP_MESSAGE",names.getFileType());
            return mapping.findForward("start");
          } 
          if (method.equals("search") ) 
          {  
            return mapping.findForward("search");
          }   
         
          if (method.equals("installationPermitOldOwner") ) 
          {  session.setAttribute("ELEVATOR_ONLINE_APP_STATUS","installationPermitOldOwner");
            return mapping.findForward("installationPermitOldOwner");
          } 
          if (method.equals("alterationPermit") ) 
          { session.setAttribute("ELEVATOR_ONLINE_APP_STATUS","alterationPermit"); 
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/elevator/owner.do");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          if (method.equals("submitInspections") ) 
          { session.setAttribute("ELEVATOR_ONLINE_APP_STATUS","submitInspections"); 
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/elevator/owner.do");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          if (method.equals("payInvoice") ) 
          {  session.setAttribute("ELEVATOR_ONLINE_APP_STATUS","payInvoice");
            return mapping.findForward("payInvoice");
          } 
          if (method.equals("contractorLicense") ) 
          {  session.setAttribute("ELEVATOR_ONLINE_APP_STATUS","contractorLicense");
            return mapping.findForward("contractorLicense");
          } 
          if (method.equals("backToStart") ) 
          {  return mapping.findForward("start");
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
