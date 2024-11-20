package display.control.action;

import display.control.*;
import display.to.*;
import display.data.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import display.control.form.*;
import hs.control.form.*;

import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import main.to.*;
import main.data.*;
import hs.report.pdf.*;


public class DfbsStartAction extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        
        DfbsStartForm startForm = (DfbsStartForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
         String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
      
        if (method == null) 
        {   ShoppingCart cart = new ShoppingCart(); 
            session.setAttribute("SHOPPING_CART",cart);
             String conType ="CHILDCARE_CONTACT";
            session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
            String appLocation ="/dfbs/display/start.do";
             session.setAttribute("APPLICATION_LOCATION",appLocation); 
             ApplicationContacts contacts = sDAO.setContacts("DISPLAY_CONTACT1","DISPLAY_CONTACT2");
             session.setAttribute("APPLICATION_CONTACTS",contacts);
            String appHeading ="Supervised Fireworks Display";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
          FileNames names= sDAO.selectAppStatus("FIRE_DISPLAY_MAINT_FLAG");
            session.setAttribute("FIRE_DISPLAY_APP_STATUS",names.getFileName());
            session.setAttribute("FIRE_DISPLAY_APP_MESSAGE",names.getFileType());
            return mapping.findForward("start");
        } 
        else if (method.equals("backToStart")) 
        {
        
          return mapping.findForward("start");
        } 
        
       
        else if (method.equals("renewBy")) 
        {
        
          return mapping.findForward("renewBy");
        } 
         else if (method.equals("newPermitNewOwner")) 
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newOwner");
        } 
         else if (method.equals("newPermitOldOwner")) 
        {
        
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/ownerListDisplay.do");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewByOwner")) 
        {
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/ownerListDisplay.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewByStreetNumber")) 
        {
        String streetNumber=startForm.getStreetNumber();
         if (streetNumber.equals(""))
          {
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setStreetNumber("");
            request.setAttribute("START_FORM_ERROR",errorForm);
            return mapping.findForward("renewBy");
          }
          else
          {
           List list = null;
          list = oDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_STREET,streetNumber,"byStreet",streetNumber);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByStreetNumber");
          }
        } 
        
        else if (method.equals("renewByPermitNumber")) 
        { String displayNumber=startForm.getPermitNumber();
          if (displayNumber.trim().equals(""))
          {
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setPermitNumber("ERROR");
            request.setAttribute("START_FORM_ERROR",errorForm);
            return mapping.findForward("renewBy");
          }
          else
          {
          List list = null;
          list = oDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_PERMIT,displayNumber,"byPermit",displayNumber);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          }
        } 
        else if (method.equals("renewByIdNumber")) 
        { String displayNumber=startForm.getIdNumber();
          if (displayNumber.trim().equals(""))
          {
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setIdNumber("ERROR");
            request.setAttribute("START_FORM_ERROR",errorForm);
            return mapping.findForward("renewBy");
          }
          else
          {
          List list = null;
          list = oDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_ID,displayNumber,"byMagId",displayNumber);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          }
        } 
             else if (method.equals("viewFees")) 
        { String displayIdNumber = request.getParameter("displayIdNumber");
          DfbsDisplay display=new DfbsDisplay();
          display.setDisplayIdNumber(displayIdNumber);
          display.setDisplayFees(pDAO.selectPermitFees(display.getDisplayIdNumber()));
          session.setAttribute("PERMIT_FEE",display);
           List list = null;
          list = oDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_ID,display.getDisplayIdNumber(),"byMagId",display.getDisplayIdNumber());
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
           return mapping.findForward("renewByPermitNumber");
        }
        
       
        else if (method.equals("reset")) 
        {
          
           session.setAttribute("SHOPPING_CART",null);
          return mapping.findForward("start");
        }
         else if (method.equals("lookupNew")) 
        {
         
          List list = null;
          list = oDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_STATUS,"NEW","byStatus","xx");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByStreetNumber");
         
        } 
          else if (method.equals("logOn")) 
        {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/main/main.do");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
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
    List states = uDAO.getOptions(DfbsDisplaySQL.SELECT_STATE_OPTIONS);
     request.setAttribute("OWNER_STATE_OPTIONS",states);
 
 
   
 }
 
}
