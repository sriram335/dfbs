package childcare.control.action;

import childcare.control.form.*;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import childcare.to.*;


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
import childcare.data.*;
import main.data.*;
import main.to.*;
public class ChildcareApplicationAction extends ControlAction
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
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_CONTACT);
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
       
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        
        if (method.equals("startApplication")) 
        {
          String ownerId = request.getParameter("ownerId");
           String permitNumber = request.getParameter("permitNumber");
          if(ownerId == null) 
          {
           setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("newOwner");
          } 
          else 
          {            
             DfbsOwner owner = ChildcareAction.setView(request,oDAO,ownerForm,Integer.parseInt(ownerId),session);
            ownerForm.setProperties(owner);
             setOptions(request,dfbsUtilityDAO);
             owner.setOwnerKey(Integer.toString(owner.getOwnerId()));
             session.setAttribute("OWNER_SELECTED",owner);
               session.setAttribute("PERMIT_KEY",permitNumber);
            return mapping.findForward("renewOwner");
          }
        } 
        else if (method.equals("startOverForm")) 
        {
          return mapping.findForward("startOverForm");
        }
        
         else if (method.equals("renewOwner")) 
          {            
             DfbsOwner owner = ( DfbsOwner) session.getAttribute("OWNER_RENEW");
            ownerForm.setProperties(owner);
             setOptions(request,dfbsUtilityDAO);
             owner.setOwnerKey(Integer.toString(owner.getOwnerId()));
             session.setAttribute("OWNER_SELECTED",owner);
            return mapping.findForward("renewOwnerForm");
          }
          else if (method.equals("renewOwnerVerified")) 
        {
           DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
           DfbsOwnerForm errorForm = new DfbsOwnerForm();
           if (validate(updatedOwner,errorForm) ) {
             DfbsOwner owner = ( DfbsOwner) session.getAttribute("OWNER_RENEW");
             updatedOwner.setContactsMap(owner.getContactsMap());
             updatedOwner.setChildCaresMap(owner.getChildCaresMap());
              updatedOwner.setRenewStatus("Y");
              session.setAttribute("OWNER_RENEW",updatedOwner);
              return mapping.findForward("renewPermit");
          
           } 
           else 
           {
             request.setAttribute("OWNER_ERROR",errorForm);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("renewOwner");
           }
        }  
        else if (method.equals("startOver")) 
        {
          session.setAttribute("OWNER_SELECTED",null);
          return mapping.findForward("main");
        }
        
        
       
        else if(method.equals("saveOwner")) 
        {
           DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
           DfbsOwnerForm errorForm = new DfbsOwnerForm();
           if (validate(updatedOwner,errorForm) ) {
              cart.addOwner(updatedOwner);
              session.setAttribute("OWNER_SELECTED",updatedOwner);
              updatedOwner.setContacts(cDAO.selectContactList(updatedOwner.getOwnerId()));
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             session.setAttribute("RENEW_RM","true");
            redirectUrl.append(request.getContextPath()).append("/childCare/contact.do?method=viewContacts&ownerId="+updatedOwner.getOwnerId()+"&renewStatus=true");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
           } 
           else 
           {
             request.setAttribute("OWNER_ERROR",errorForm);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("renewOwner");
           }
        }  
         else if(method.equals("saveNewOwner")) 
        {
           DfbsOwner newOwner = ownerForm.getDfbsOwner();
            DfbsOwnerForm errorForm = new DfbsOwnerForm();
           if (validate(newOwner,errorForm) ) {
              cart.addOwner(newOwner);
              session.setAttribute("OWNER_SELECTED",newOwner);
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             
            redirectUrl.append(request.getContextPath()).append("/childCare/contact.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
           } 
           
           else 
           {
             request.setAttribute("OWNER_ERROR",errorForm);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("newOwner");
           }
        }  
         else if(method.equals("removeOwner")) 
                {
          String ownerKey = request.getParameter("ownerKey");
          DfbsOwner selectedOwner=cart.getOwner(ownerKey); 
          cart.removeOwner(ownerKey);
          pDAO.computeFees(cart);
          if( cart.getOwnerMapCount() !=0)   
          {
            return mapping.findForward("renewPermit"); 
          }
          else
          {
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
           redirectUrl.append(request.getContextPath()).append("/childCare/start.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          
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
  
  protected static boolean validateApplication(DfbsChildcareOwnerDAO oDAO,DfbsOwner owner,DfbsOwner ownerApp,DfbsOwnerForm ownerErrorForm,HttpSession session) throws Exception
  {
   boolean noError1 = ChildcareApplicationAction.validate(ownerApp,ownerErrorForm);
    boolean noError2 = true;
   if(ownerApp.getChildCaresMapCount() == 0) 
    {
      noError1 = false;
    }
    
    Map permitsErrorMap = new HashMap();
    
    Map mapPermit = ownerApp.getChildCaresMap();
    Set permitkeys = mapPermit.keySet();
    Iterator i = permitkeys.iterator();
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsChildcarePermit permit = (DfbsChildcarePermit) mapPermit.get(key); 
     DfbsChildcarePermitForm errorForm = new DfbsChildcarePermitForm();
     noError2 = ChildcarePermitAction.validate(permit,errorForm,oDAO,session);
     permitsErrorMap.put(key,errorForm);
         
    }
   // owner.setChildcaresMap(ChildCaresErrorMap);
  return (noError1 && noError2) ? true : false ;
 
  }
  
  
  protected static boolean validate(DfbsOwner owner,DfbsOwnerForm errorForm) 
  {
    boolean noError = true;
    
    if(owner.isNew() == true)
    {
   
    if(owner.getOwnerLastName() == null || owner.getOwnerLastName().trim().equals("")) 
    {
      errorForm.setOwnerLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerLastName("");
    }
    if(owner.getOwnerFirstName() == null || owner.getOwnerFirstName().trim().equals("")) 
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
    
    if(owner.getOwnerStateId() == 0  ) 
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
  
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsChildcareSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    //request.setAttribute("DFBS_CITIES_OPTIONS",cities);
 }
 
 
 
 


 
}
  



