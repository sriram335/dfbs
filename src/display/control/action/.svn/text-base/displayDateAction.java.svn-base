package display.control.action;

import display.control.*;
import display.to.*;
import display.data.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
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
import hs.report.pdf.*;
import main.to.*;
import main.data.*;

public class displayDateAction extends ControlAction
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
        
        
        DisplayDatesForm displayDateForm = (DisplayDatesForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
         DfbsDisplay display = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
        if (method.equals("addDate")) 
        {  displayDateForm.setSaveType("New");
          
          /*  String ownerKey= request.getParameter("ownerKey");
            String displayKey= request.getParameter("displayKey");
            ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
            DfbsOwner owner = cart.getOwner(ownerKey);
            session.setAttribute("OWNER_SELECTED",owner);
            DfbsDisplay editPermit = owner.getPermit(displayKey);
            session.setAttribute("PERMIT_SELECTED",editPermit);*/
           displayDateForm.setDisplayId(display.getDisplayPermitKey()); 
           return mapping.findForward("newDate");
          
        } 
       
         else if (method.equals("editDate")) 
        {
        String applicationKey= request.getParameter("applicationKey");
        DisplayDates displayDate = display.getDisplayDate(applicationKey);
        displayDateForm.setProperties(displayDate);
        displayDateForm.setSaveType("Update");
         session.setAttribute("DISPLAY_DATE_SELECTED",displayDate);
        return mapping.findForward("newDate");
        }
       
         else if (method.equals("saveDate")) 
        {
        DisplayDates displayDate = displayDateForm.getDisplayDates();
       if (displayDate.getDisplayDate() == null || displayDate.getDisplayTime() == null || displayDate.getDisplayTime().equals("") )
         {
         displayDateForm.setProperties(displayDate);
          displayDateForm.setSaveType("Update");
         session.setAttribute("DISPLAY_DATE_SELECTED",displayDate);
          session.setAttribute("DISPLAY_DATE_ERROR","Y");
        return mapping.findForward("newDate");
       }
      else
       { session.setAttribute("DISPLAY_DATE_ERROR","N");
           if (displayDate.getSaveType().equals("New") &&displayDateForm.getSaveDateType().equals("Save Date"))
            {
            display.addDate(displayDate);
              ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
              DfbsOwner owner = cart.getOwner(selectedOwner.getOwnerKey());
               DfbsDisplay editPermit = owner.getPermit(display.getDisplayPermitKey());
               editPermit.setDisplayDatesMap(display.getDisplayDatesMap());
            display.setDisplayDateError(true); 
            }
            if (displayDate.getSaveType().equals("Update") && displayDateForm.getSaveDateType().equals("Save Date"))
            {
            DisplayDates displayDateOld = (DisplayDates) session.getAttribute("DISPLAY_DATE_SELECTED");
            displayDateForm.setUpdatedProperties(displayDate,displayDateOld);
            }
        if (displayDateForm.getSaveDateType().equals("Add Dates Complete"))
        {
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do?method=backToPermit");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        }
        else {
            return mapping.findForward("newDate");
        }
          
       }
      
        } 
         
       
        else if (method.equals("removeDate")) 
        {
        String applicationKey= request.getParameter("applicationKey");
        display.removePermit(applicationKey);
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do?method=backToPermit&ownerKey="+selectedOwner.getOwnerKey()+"&displayKey="+display.getDisplayPermitKey());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        }
  // internal use
  
   if (method.equals("addIntDate")) 
        {  displayDateForm.setSaveType("New");
           displayDateForm.setDisplayId(display.getDisplayPermitKey());
           return mapping.findForward("newIntDate");
          
        } 
       
         else if (method.equals("editIntDate")) 
        {
        String dispalyDateId= request.getParameter("displayDateId");
        DisplayDates displayDate = pDAO.selectDisplayDates(Integer.parseInt(dispalyDateId));
        displayDateForm.setProperties(displayDate);
        displayDateForm.setSaveType("Update");
         session.setAttribute("DISPLAY_DATE_SELECTED",displayDate);
        return mapping.findForward("newIntDate");
        }
       
         else if (method.equals("saveIntDate")) 
        {
        DisplayDates displayDate = displayDateForm.getDisplayDates();
          if (displayDate.getSaveType().equals("New") || displayDate.getDisplayDateId()==0)
        {
        displayDate.setDisplayId(display.getDisplayIdNumber());
        pDAO.insertPermitDate(displayDate);
        }
        if (displayDate.getSaveType().equals("Update"))
        {
        pDAO.updateDisplayDate(displayDate);
        }
        
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do?method=update&displayKey="+display.getDisplayIdNumber());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
         
       
        else if (method.equals("removeIntDate")) 
        {
       String dispalyDateId= request.getParameter("displayDateId");
        pDAO.deleteDisplayDate(Integer.parseInt(dispalyDateId));
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do?method=update&displayKey="+display.getDisplayIdNumber());
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
}
