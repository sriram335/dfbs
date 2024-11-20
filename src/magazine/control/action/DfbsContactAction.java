package magazine.control.action;

import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;

import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.to.*;
import main.data.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;


public class DfbsContactAction extends ControlAction
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
        DfbsContactForm contactForm = (DfbsContactForm) form;
         HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
      //  DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_CONTACT);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method == null) 
        { DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          if (selectedOwner.getOwnerId() == 0)
          { setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("newContact");
          }
          else
          {setOptions(request,dfbsUtilityDAO);
          DfbsContact contact = oDAO.selectContact(selectedOwner.getOwnerId());
          contactForm.setProperties(contact);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateContact");
          }
        } 
        else if (method == "newContact")
        {   setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("newContact");
        }
        else if (method.equals("updateContact")) 
        {
          int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          setOptions(request,dfbsUtilityDAO);
          DfbsContact contact = oDAO.selectContact(ownerId);
          contactForm.setProperties(contact);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateSaveContact");
        } 
         else if (method.equals("updateSaveContact")) 
        {
          DfbsContact contact = contactForm.getDfbsContact();
          DfbsContactForm errorForm = new DfbsContactForm();
          if (validate(contact,errorForm,session) ) {
              oDAO.updateContact(contact); 
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          else {
              setOptions(request,dfbsUtilityDAO);
              request.setAttribute("PERSON_ERROR",errorForm);
             return mapping.findForward("updateSaveContact");
          }
        } 
          else if (method.equals("renewPermit")) 
        { 
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsContact contact = contactForm.getDfbsContact();
          DfbsContactForm errorForm = new DfbsContactForm();
          if (validate(contact,errorForm,session) ) {
           
          selectedOwner.addContact(contact);
         // return mapping.findForward("updateContact");
         setOptions(request,dfbsUtilityDAO); 
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERSON_ERROR",errorForm);
           contactForm.setProperties(contact);
           return mapping.findForward("updateContact");
          }
          } 
          else if (method.equals("newPermit")) 
        { 
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsContact contact = contactForm.getDfbsContact();
          DfbsContactForm errorForm = new DfbsContactForm();
          if (validate(contact,errorForm,session) ) {
          setOptions(request,dfbsUtilityDAO); 
          selectedOwner.addContact(contact);
         // return mapping.findForward("updateContact");
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          else 
          {setOptions(request,dfbsUtilityDAO);
           request.setAttribute("PERSON_ERROR",errorForm);
           contactForm.setProperties(contact);
           return mapping.findForward("newContact");
          }
          } 
          else if (method.equals("editContact")) 
        {  DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
            Map mapContact = selectedOwner.getContactsMap();
          Set contactkeys = mapContact.keySet();
          Iterator i = contactkeys.iterator();
           while(i.hasNext())
           { 
           String key = (String) i.next();
           DfbsContact inMapContact = (DfbsContact) mapContact.get(key); 
           contactForm.setProperties(inMapContact);
           }
           setFilterOptions(request,dfbsUtilityDAO);
           setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editContact");
        } 
         else if (method.equals("saveEditContact")) 
        { DfbsContact contact = contactForm.getDfbsContact();
          DfbsContactForm errorForm = new DfbsContactForm();
          if (validate(contact,errorForm,session) ) {
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          Map mapOwner = cart.getOwnerMap();
          Set ownerkeys = mapOwner.keySet();
          Iterator i = ownerkeys.iterator();
          int inMapOwnerId=0;
           while(i.hasNext())
           { 
           String key = (String) i.next();
           DfbsOwner inMapOwner = (DfbsOwner) mapOwner.get(key); 
           if (selectedOwner.getOwnerId()==inMapOwner.getOwnerId())
           {inMapOwner.removeContact(contact.getContactKey());
            inMapOwner.addContact(contact);
           }
           }
           setOptions(request,dfbsUtilityDAO);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do?method=permitInCart");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERSON_ERROR",errorForm);
           contactForm.setProperties(contact);
           return mapping.findForward("updateContact");
          }
          } 
        
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {   
    List charNav = uDAO.getCharacterNavs(DfbsSQL.SELECT_FIRST_LETTER_OPTIONS);
    List counties = uDAO.getOptions(DfbsSQL.SELECT_COUNTY_OPTIONS);
    List cities = uDAO.getOptions(DfbsSQL.SELECT_CITIES_OPTIONS);
    request.setAttribute("OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("OWNER_COUNTY_OPTIONS",counties);
    request.setAttribute("OWNER_CITIES_OPTIONS",cities);
   
 }
 
 protected static boolean validate(DfbsContact contact,DfbsContactForm errorForm,HttpSession session) throws Exception
  {
    boolean noError = true;
    ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
   //if(contact.isNew() == false) {
    if ((contact.getPersonPhone() == null || contact.getPersonPhone().trim().equals("") ||
       contact.getPersonPhone().indexOf('-') > 0 || contact.getPersonPhone().indexOf(')') > 0 ||
       contact.getPersonPhone().indexOf('(') > 0) )
    { 
      errorForm.setPersonPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonPhone("");
    }
  //  }
   
    if(contact.getPersonLastName() == null || contact.getPersonLastName().trim().equals("")  ) 
    { 
      errorForm.setPersonLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonLastName("");
    }
    if(contact.getPersonFirstName() == null || contact.getPersonFirstName().trim().equals("") ) 
    { 
      errorForm.setPersonFirstName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonFirstName("");
    }
    
   
   
    if (security == null)
    {
     if(contact.getPersonEmail() == null || contact.getPersonEmail().trim().equals("") ||
     contact.getPersonEmail().indexOf('@') <=0 || contact.getPersonEmail().indexOf('.') <= 0) 
    { 
      errorForm.setPersonEmail("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonEmail("");
    }
    }
   /*  if(contact.getPersonAddress1() == null || contact.getPersonAddress1().trim().equals("") ) 
    { 
      errorForm.setPersonAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonAddress1("");
    }
     if(contact.getPersonCity() == null || contact.getPersonCity().trim().equals("") ) 
    { 
      errorForm.setPersonCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonCity("");
    }
     if(contact.getPersonStateId() == 0  ) 
    { 
      errorForm.setPersonStateId(contact.getPersonStateId());
      noError = false;
    } 
    else 
    {
      errorForm.setPersonStateId(0);
    }
    if(contact.getPersonZip() == null || contact.getPersonZip().trim().equals("") ) 
    { 
      errorForm.setPersonZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonZip("");
    }
    */
    
    
    return noError;
  }
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS);
    List states2 = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS2);
    List mags =  uDAO.getOptions(DfbsSQL.SELECT_MAGAZINE_TYPE_OPTIONS);
     List counties =  uDAO.getOptions(DfbsSQL.SELECT_COUNTY_LIST_OPTIONS);
     List fds =  uDAO.getOptions(DfbsSQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("MAGAZINE_STATE_OPTIONS",states2);
     request.setAttribute("MAGAZINE_TYPE_OPTIONS",mags);
     request.setAttribute("MAGAZINE_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
     
     
    
    
 } 
}