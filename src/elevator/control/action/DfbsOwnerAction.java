package elevator.control.action;




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
import elevator.data.*;
import elevator.control.form.*;

import elevator.to.*;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationSecurity;

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
        elevatorOwnerDAO oDAO = ( elevatorOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_OWNER);
        elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method == null) 
        { setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("selectOwner");
        } 
        if (method.equals("ownerName") ) 
        {  String ownerName = request.getParameter("ownerDBA");
         List ownList = oDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_LIKE,ownerName,"All","All");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(ownList));
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("selectOwner");
        }   
        if (method.equals("ownerStreet") ) 
        {  String ownerStreet = request.getParameter("ownerDBA");
         List ownList = oDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_ADDRESS_LIKE,ownerStreet,"All","All");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(ownList));
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("selectOwner");
        }
        if (method.equals("elevStateNumber") ) 
        {  String stateNumber = request.getParameter("ownerDBA");
         List ownList = oDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_STATE_NUMBER,stateNumber,"All","All");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(ownList));
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("selectOwner");
        }
        if (method.equals("elevStreetNumber") ) 
        {  String elevStreet = request.getParameter("ownerDBA");
         List ownList = oDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_ELEV_STREET_NUMBER,elevStreet,"All","All");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(ownList));
           setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("selectOwner");
        }
        
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("selectOwner");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,ownerForm);
          setList(request,oDAO,ownerForm);
           setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("selectOwner");
        } 
        else if (method.equals("view")) 
        {String ownerId = request.getParameter("ownerId");
          DfbsOwner owner = oDAO.selectOwner(Integer.parseInt(ownerId));
          ownerForm.setProperties(owner);
          session.setAttribute("OWNER_SELECTED",owner);
          return mapping.findForward("viewOwner");
        }   
        else if (method.equals("newInstallation")) 
        { StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
         redirectUrl.append(request.getContextPath()).append("/elevator/elevator.do?method=newElevator");
         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
        }   
        
         else if (method.equals("editOwner")) 
        {  String ownerId = request.getParameter("key");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner inMapOwner =cart.getOwner(ownerId);
          session.setAttribute("OWNER_SELECTED",inMapOwner);
           ownerForm.setProperties(inMapOwner);
           setOptions(request,dfbsUtilityDAO);
          setFilterOptions(request,dfbsUtilityDAO);
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
            setFilterOptions(request,dfbsUtilityDAO);
           request.setAttribute("OWNER_ERROR",errorForm);
           session.setAttribute("OWNER_SELECTED",updatedOwner);
           return mapping.findForward("editOwner");
          }
          } 
        else if (method.equals("newOwner")) 
        {  setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("newOwner");
        }
        else if (method.equals("saveNewOwner")) 
        { DfbsOwner newOwner = ownerForm.getDfbsOwner();
         DfbsOwnerForm errorForm = new DfbsOwnerForm();
         if (validate(newOwner,errorForm,session) ) {
          session.setAttribute("OWNER_SELECTED",newOwner);
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
         redirectUrl.append(request.getContextPath()).append("/elevator/elevator.do?method=newElevator");
         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
        } 
         else 
         {setOptions(request,dfbsUtilityDAO);
           setFilterOptions(request,dfbsUtilityDAO);
          request.setAttribute("OWNER_ERROR",errorForm);
          session.setAttribute("OWNER_SELECTED",newOwner);
          return mapping.findForward("newOwner");
         }
         } 
           else if (method.equals("removeOwner")) 
        {  String ownerKey= request.getParameter("ownerKey");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          cart.removeOwner(ownerKey);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/elevator/start.do");
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
    List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS);
   List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS);
     List counties = uDAO.getOptions(elevatorSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
  //  request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
   request.setAttribute("DFBS_STATE_OPTIONS",states);
    
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
  
 private static void setList(HttpServletRequest request,elevatorOwnerDAO  fwDAO,DfbsOwnerForm ownerForm) throws Exception
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
        list = fwDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),"All","All");
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),"All","All");
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = fwDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_BY_COUNTY,filterSession.getValue(),"byCounty",ownerForm.getCounty());
        ownerForm.setCounty(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectOwnersList(elevatorSQL.SELECT_OWNERS_BY_CITY,filterSession.getValue(),"byCity",ownerForm.getCity());
        ownerForm.setCity(filterSession.getValue());
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
    
    if(owner.getOwnerDBA() == null || owner.getOwnerDBA().trim().equals("") ) 
    {
      errorForm.setOwnerDBA("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerDBA("");
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
   
    
    
    
   
     
   
    
    
   owner.setOwnerError(noError);
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS);
    List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
   request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    
    
 } 
 
 
}