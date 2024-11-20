package display.control.action;

import display.control.*;
import display.to.*;
import display.data.*;
import display.control.form.*;

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
import main.to.*;
import main.data.*;
import hs.report.pdf.*;

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
        
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method == null) 
        {  
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("ownerListDisplayAll");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("ownerListDisplayAll");
        } 
         else if (method.equals("newPermit")) 
        {
          DfbsOwner owner = ownerForm.getDfbsOwner();
          DfbsOwnerForm errorForm = new DfbsOwnerForm();
          if (validate(owner,errorForm,session) ) {
          session.setAttribute("OWNER_SELECTED",owner);
          setOptions(request,dfbsUtilityDAO);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/contact.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          else 
          {setOptions(request,dfbsUtilityDAO);
           request.setAttribute("OWNER_ERROR",errorForm);
           session.setAttribute("OWNER_SELECTED",owner);
           return mapping.findForward("newOwner");
          }
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,oDAO,ownerForm);
          return mapping.findForward("ownerListDisplayAll");
        } 
         else if (method.equals("renew")) 
        { int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          DfbsOwner owner = oDAO.selectOwner(ownerId);
           String displayNumber =request.getParameter("idNumber");
           DfbsDisplay display = new DfbsDisplay();
           display.setDisplayIdNumber(displayNumber);
           session.setAttribute("PERMIT_SELECTED",display);
           ownerForm.setProperties(owner);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("OWNER_SELECTED",owner);
           session.setAttribute("OWNER_DIAPLY_LIST",owner);
            ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          Map mapOwner = cart.getOwnerMap();
          Set ownerkeys = mapOwner.keySet();
          Iterator i = ownerkeys.iterator();
          int inMapOwnerId=0;
           while(i.hasNext())
           { 
           String key = (String) i.next();
           DfbsOwner inMapOwner = (DfbsOwner) mapOwner.get(key); 
           if (owner.getOwnerId()==inMapOwner.getOwnerId())
           {  inMapOwnerId=inMapOwner.getOwnerId();
           }
           }
          if (inMapOwnerId >0 )
          {
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString())); 
            return null;

          }
          else
          {
          return mapping.findForward("renew");
          }
        } 
         else if (method.equals("renewPermit")) 
        { DfbsOwner owner = ownerForm.getDfbsOwner();
          DfbsOwnerForm errorForm = new DfbsOwnerForm();
          if (validate(owner,errorForm,session) ) {
           session.setAttribute("OWNER_SELECTED",owner);
          setOptions(request,dfbsUtilityDAO);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/contact.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          else 
          {setOptions(request,dfbsUtilityDAO);
           request.setAttribute("OWNER_ERROR",errorForm);
           session.setAttribute("OWNER_SELECTED",owner);
           return mapping.findForward("renew");
          }
          } 
        
     
      
        
       
         else if (method.equals("editOwner")) 
        {  String ownerId = request.getParameter("key");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner inMapOwner =cart.getOwner(ownerId);
          session.setAttribute("OWNER_SELECTED",inMapOwner);
           ownerForm.setProperties(inMapOwner);
           setOptions(request,dfbsUtilityDAO);
           
          return mapping.findForward("editOwner");
        } 
         else if (method.equals("saveEditOwner")) 
        { DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
          DfbsOwnerForm errorForm = new DfbsOwnerForm();
          if (validate(updatedOwner,errorForm,session) ) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
         DfbsOwner owner =cart.getOwner(updatedOwner.getOwnerKey());
          ownerForm.setUpdatedProperties(updatedOwner,owner);
           session.setAttribute("OWNER_SELECTED",owner);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/contact.do?method=editContact");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;           
          } 
          else 
          {setOptions(request,dfbsUtilityDAO);
           request.setAttribute("OWNER_ERROR",errorForm);
           session.setAttribute("OWNER_SELECTED",updatedOwner);
           return mapping.findForward("editOwner");
          }
          } 
           else if (method.equals("removeOwner")) 
        {  String ownerKey= request.getParameter("key");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           cart.removeOwner(ownerKey);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/display.do");
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
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {   
    List charNav = uDAO.getCharacterNavs(DfbsDisplaySQL.SELECT_FIRST_LETTER_OPTIONS);
    List counties = uDAO.getOptions(DfbsDisplaySQL.SELECT_COUNTY_OPTIONS);
    List cities = uDAO.getOptions(DfbsDisplaySQL.SELECT_CITIES_OPTIONS);
    request.setAttribute("OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("OWNER_COUNTY_OPTIONS",counties);
    request.setAttribute("OWNER_CITIES_OPTIONS",cities);
   
 }
 protected static void setFilterSession(HttpServletRequest request,DfbsOwnerForm filterForm) throws Exception
  {
    HttpSession session = request.getSession();
    String filter = request.getParameter("filter");
    
    HsFilter filterSession = new HsFilter();
    filterSession.setType(filter);
    
     if(filter == null ) { 
      filterSession.setType("byLetter");
      filterSession.setValue("A");
    } 
    else if(filter.equals("byLetter"))  
    { 
       String letter = request.getParameter("letter");
       filterSession.setValue(letter);
     }
     else if(filter.equals("byCounty"))  
    { 
       filterSession.setValue(filterForm.getCounty());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(filterForm.getCity());
     }
     session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,DfbsOwnerDAO fwDAO,DfbsOwnerForm ownerForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("DFBS_OWNER_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
        list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),"All","All");
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),"All","All");
     } 
     else if(filterSession.getType().equals("byCounty")) 
     { 
        list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_COUNTY,filterSession.getValue(),"byCounty",ownerForm.getCounty());
        ownerForm.setCounty(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     { 
        list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_CITY,filterSession.getValue(),"byCity",ownerForm.getCity());
        ownerForm.setCity(filterSession.getValue());
     } 
    else if(filterSession.getType().equals("renewByStreetNumber")) 
    { 
       list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_FAC_ADDRESS1,ownerForm.getOwnerAddress1(),"byStreet",ownerForm.getOwnerAddress1());
       //ownerForm.setCity(filterSession.getValue());
    } 
    else if(filterSession.getType().equals("renewByPermitNumber")) 
    {
       list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_PERMIT_NUMBER,ownerForm.getOwnerAddress2(),"byPermit",ownerForm.getOwnerAddress2());
       //ownerForm.setCity(filterSession.getValue());
    } 
    else if(filterSession.getType().equals("renewByOwnerName")) 
    {
       list = fwDAO.selectOwnersList(DfbsDisplaySQL.SELECT_OWNERS_BY_OWNER_NAME,ownerForm.getOwnerDBA(),"byOwnerName",ownerForm.getOwnerDBA());
       //ownerForm.setCity(filterSession.getValue());
    } 
     request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
     
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
    
    if(owner.getOwnerLastName() == null || owner.getOwnerLastName().trim().equals("") ) 
    {
      errorForm.setOwnerLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerLastName("");
    }
     
    if (security !=null && (security.getGroupLevelAll()!=null ||security.getGroupLevelFire()!=null))
    { 
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
      errorForm.setOwnerState("");
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
   
     
   
    
  } 
    if(security ==null) 
    {
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
   
    
    
   owner.setOwnerError(noError);
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsDisplaySQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    
     List inspType = uDAO.getOptions(DfbsDisplaySQL.SELECT_INSPECTION_TYPE);
    List inspectors = uDAO.getOptions(DfbsDisplaySQL.SELECT_INSPECTORS);
    List inspOptions = uDAO.getOptions(DfbsDisplaySQL.SELECT_INSP_OPTIONS);
    List alaramOptions = uDAO.getOptions(DfbsDisplaySQL.SELECT_ALARAM_OPTIONS);
    request.setAttribute("INSPECTION_TYPE_OPTIONS",inspType);
    request.setAttribute("INSPECTORS_OPTIONS",inspectors);
    request.setAttribute("ALARAM_OPTIONS",alaramOptions);
    request.setAttribute("COMPLIED_OPTIONS",inspOptions);
    
 } 
 
 
}
