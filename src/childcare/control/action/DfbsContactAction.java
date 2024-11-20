package childcare.control.action;

import childcare.control.form.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import childcare.data.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import childcare.to.*;
import main.data.*;
import main.to.*;
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
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_CONTACT);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
            ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        
        if (method == null) 
        { DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          if (selectedOwner.getOwnerId() == 0)
          { setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("newContact");
          }
          else
          {setOptions(request,dfbsUtilityDAO);
          DfbsContact contact = cDAO.selectContact(selectedOwner.getOwnerId());
          contactForm.setProperties(contact);
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateContact");
          }
        } 
        else if (method.equals("newContact"))
        {   setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("newContact");
        }
       
        else if (method.equals("viewContacts"))
        {  String ownerId = request.getParameter("ownerId");
           String renewStatus = request.getParameter("renewStatus");
           DfbsOwner owner =  oDAO.selectOwner(Integer.parseInt(ownerId));
           owner.setContacts(cDAO.selectContactList(owner.getOwnerId()));
            session.setAttribute("OWNER_SELECTED",owner);
            session.setAttribute("RENEW_RM",renewStatus);
           return mapping.findForward("viewContacts");
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
          redirectUrl.append(request.getContextPath()).append("/childCare/childCare.do");
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
          DfbsOwner owner = cart.getOwner(selectedOwner.getOwnerKey());
          setOptions(request,dfbsUtilityDAO); 
          owner.addContact(contact);
          session.setAttribute("OWNER_SELECTED",owner);
          return mapping.findForward("viewContacts");
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
           String contactKey = request.getParameter("contactKey");
          DfbsContact contact = selectedOwner.getContact(contactKey);
          contactForm.setProperties(contact);
           setFilterOptions(request,dfbsUtilityDAO);
           setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editContact");
        } 
          else if (method.equals("renewContact")) 
        {  DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_RENEW");
           String contactKey = request.getParameter("contactKey");
          DfbsContact contact = selectedOwner.getContact(contactKey);
          contactForm.setProperties(contact);
           setFilterOptions(request,dfbsUtilityDAO);
           setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("renewContactForm");
        } 
        else if (method.equals("renewContactVerified")) 
        { DfbsContact updatedContact = contactForm.getDfbsContact();
          DfbsContactForm errorForm = new DfbsContactForm();
          if (validate(updatedContact,errorForm,session) ) {
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_RENEW");
          DfbsContact contact = selectedOwner.getContact(Integer.toString(contactForm.getPersonId()));
          contactForm.setUpdatedProperties(updatedContact,contact);  
          contact.setRenewStatus("Y");
          setOptions(request,dfbsUtilityDAO);
          String uploadFlag =oDAO.ownerRenewStatus(selectedOwner);
          if (uploadFlag.equals("Y"))
          {
            session.setAttribute("UPLOAD_FLAG","Y");
          }
          return mapping.findForward("renewPermit");
          } 
        
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERSON_ERROR",errorForm);
           contactForm.setProperties(updatedContact);
           return mapping.findForward("renewContactForm");
          }
          } 
         else if (method.equals("saveEditContact")) 
        { DfbsContact updatedContact = contactForm.getDfbsContact();
          DfbsContactForm errorForm = new DfbsContactForm();
          if (validate(updatedContact,errorForm,session) ) {
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsContact contact = selectedOwner.getContact(Integer.toString(contactForm.getPersonId()));
          contactForm.setUpdatedProperties(updatedContact,contact);          
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("viewContacts");
          } 
        
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERSON_ERROR",errorForm);
           contactForm.setProperties(updatedContact);
           return mapping.findForward("updateContact");
          }
          } 
            else if (method.equals("editContactsComplete")) 
        {   
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/childCare/childcare.do?method=addPermitForm");
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
    List charNav = uDAO.getCharacterNavs(DfbsChildcareSQL.SELECT_FIRST_LETTER_OPTIONS);
    List counties = uDAO.getOptions(DfbsChildcareSQL.SELECT_COUNTY_OPTIONS);
    List cities = uDAO.getOptions(DfbsChildcareSQL.SELECT_CITIES_OPTIONS);
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
       contact.getPersonEmail().indexOf('@') <=0 || contact.getPersonEmail().indexOf('.') <= 0 ) 
    {
      errorForm.setPersonEmail("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPersonEmail("");
    }
    
    }
  /*   if(contact.getPersonAddress1() == null || contact.getPersonAddress1().trim().equals("") ) 
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
    }*/
    
    
    
    return noError;
  }
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsChildcareSQL.SELECT_STATE_OPTIONS);
    List states2 = uDAO.getOptions(DfbsChildcareSQL.SELECT_STATE_OPTIONS2);
     List counties =  uDAO.getOptions(DfbsChildcareSQL.SELECT_COUNTY_LIST_OPTIONS);
     List fds =  uDAO.getOptions(DfbsChildcareSQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("CHILDCARE_STATE_OPTIONS",states2);
      request.setAttribute("CHILDCARE_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
     
     
    
    
 } 
}
