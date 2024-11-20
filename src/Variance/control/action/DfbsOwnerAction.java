package Variance.control.action;


import hs.control.ControlAction;
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
import Variance.data.*;
import Variance.control.form.*;

import Variance.to.*;

import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;
import main.to.FileNames;

import org.apache.struts.upload.FormFile;

public class DfbsOwnerAction extends ControlAction
{
 
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = ( DfbsOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_OWNER);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        varianceApplicationDAO aDAO = (varianceApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_APPLICATION); 
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method.equals("editOwner")) 
        {  String ownerId = request.getParameter("key");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner inMapOwner =cart.getOwner(ownerId);
          session.setAttribute("VARIANCE_OWNER_SELECTED",inMapOwner);
          ownerForm.setProperties(inMapOwner);
           setOptions(request,dfbsUtilityDAO);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editOwner");
        } 
        if (method.equals("viewOwner")) 
        {  int ownerId = Integer.parseInt(request.getParameter("ownerId"));
           DfbsOwner owner =oDAO.selectOwner(ownerId);
          session.setAttribute("VIEW_OWNER_SELECTED",owner);
          ownerForm.setProperties(owner);
           setOptions(request,dfbsUtilityDAO);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateOwner");
        } 
        if (method.equals("updateOwner")) 
        {  DfbsOwner owner = ownerForm.getDfbsOwner();
           oDAO.updateOwner(owner);
           setOptions(request,dfbsUtilityDAO);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateOwner");
        } 
        else if (method.equals("addNewOwner")) 
        {  
          DfbsOwner newOwner = ( DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
          if (newOwner !=null) {
            ownerForm.setProperties(newOwner);
          }
          session.setAttribute("NEW_VARIANCE_STATUS", "START_OWNER"); 
            setFilterOptions(request,dfbsUtilityDAO);
           setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("newOwner");
        }
        else if (method.equals("saveNewOwner")) 
        {
            DfbsOwner newOwner = ownerForm.getDfbsOwner();
         DfbsOwnerForm errorForm = new DfbsOwnerForm();
         if (validate(newOwner,errorForm,session) ) { 
            session.setAttribute("VARIANCE_OWNER_SELECTED", newOwner); 
            session.setAttribute("NEW_VARIANCE_STATUS", "END_OWNER"); 
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server")); 
            redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=subQuestion"); 
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
          } 
          ownerForm.setProperties(newOwner);
          request.setAttribute("OWNER_ERROR",errorForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newOwner");
           
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
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {   
   List states = uDAO.getOptions(varianceSQL.SELECT_STATE_OPTIONS);
   request.setAttribute("DFBS_STATE_OPTIONS",states);
    
 }

 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"cclouse@dhs.in.gov"};
           String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
          
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append(" From Fire display Online enquiry - ").append(subject);
          
          
          
            mail.createMail("fire_display_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("display_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Fire display error emailOnline enquiry error ","DfbsOwnerAction");
            mail1.sendAndClose();             }
  }
  
 protected static boolean validate(DfbsOwner owner,DfbsOwnerForm errorForm,HttpSession session) 
  {
    boolean noError = true;
     ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
  
  if (owner.getOwnerId() == 0)
  {
    
   /* if(owner.getOwnerDBA() == null || owner.getOwnerDBA().trim().equals("") ) 
    {
      errorForm.setOwnerDBA("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerDBA("");
    } */
    if(owner.getOwnerLastName() == null || owner.getOwnerLastName().trim().equals("") ) 
    {
      errorForm.setOwnerLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerLastName("");
    }   
    if(owner.getOwnerFirstName() == null || owner.getOwnerFirstName().trim().equals("") ) 
    {
      errorForm.setOwnerFirstName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerFirstName("");
    }
   
    if(owner.getOwnerAddress1() == null || owner.getOwnerAddress1().trim().equals("") ) 
    {
      errorForm.setOwnerAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerAddress1("");
    }
    
    if(owner.getOwnerCity() == null || owner.getOwnerCity().trim().equals("") ) 
    {
      errorForm.setOwnerCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerCity("");
    }
    if(owner.getOwnerStateId() ==  0  ) 
    {
      errorForm.setOwnerStateId(0);
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerStateId(owner.getOwnerStateId());
    }
    
    if(owner.getOwnerZip() == null || owner.getOwnerZip().trim().equals("") ) 
    {
      errorForm.setOwnerZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerZip("");
    }
    
    
  } 
   if(owner.getOwnerEmail() == null || owner.getOwnerEmail().trim().equals("") ||
       owner.getOwnerEmail().indexOf('@') <=0 || owner.getOwnerEmail().indexOf('.') <= 0 ) 
    {
      errorForm.setOwnerEmail("ERROR");
      noError = false;
    } 
    else 
    { 
      errorForm.setOwnerEmail("");
    }
    
    if(owner.getOwnerPhone() == null || owner.getOwnerPhone().trim().equals("") ||
       owner.getOwnerPhone().indexOf('-') > 0 ||owner.getOwnerPhone().indexOf(')') > 0 ||
       owner.getOwnerPhone().indexOf('(') > 0) 
    {
      errorForm.setOwnerPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerPhone("");
    }
   
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(varianceSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    
    
 } 
 
}