package magazine.control.action;
import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;
import main.to.*;
import main.data.*;

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

public class DfbsOwnerAction extends ControlAction
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
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        if (method == null) 
        {  
          setList(request,oDAO,ownerForm, pDAO);
          setFilterOptions(request,dfbsUtilityDAO);
         setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("ownerListDisplayAll");
        } 
         else if (method.equals("filterByMagUser")) 
        {setList(request,oDAO,ownerForm, pDAO);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("ownerListDisplayAll");
        } 
        
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,ownerForm, pDAO);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("ownerListDisplayAll");
        } 
         else if (method.equals("updateOwner")) 
        {
          int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          DfbsOwner owner = oDAO.selectOwner(ownerId);
          setOptions(request,dfbsUtilityDAO);
          ownerForm.setProperties(owner);
          return mapping.findForward("updateSaveOwner");
        } 
         else if (method.equals("updateSaveOwner")) 
        {
          DfbsOwner owner = ownerForm.getDfbsOwner();
          DfbsOwnerForm errorForm = new DfbsOwnerForm();
          if (validate(owner,errorForm,session) ) {
              oDAO.updateOwner(owner);
              setList(request,oDAO,ownerForm, pDAO);
              setFilterOptions(request,dfbsUtilityDAO);
              return mapping.findForward("ownerListDisplayAll");
          }
          else {
              setOptions(request,dfbsUtilityDAO);
              request.setAttribute("OWNER_ERROR",errorForm);
             return mapping.findForward("updateSaveOwner");
          }
        } 
         else if (method.equals("newPermit")) 
        {
          DfbsOwner owner = ownerForm.getDfbsOwner();
          DfbsOwnerForm errorForm = new DfbsOwnerForm();
          if (validate(owner,errorForm,session) ) {
          session.setAttribute("OWNER_SELECTED",owner);
          setOptions(request,dfbsUtilityDAO);
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
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
          setList(request,oDAO,ownerForm, pDAO);
          return mapping.findForward("ownerListDisplayAll");
        } 
    
         else if (method.equals("renew")) 
        { int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          DfbsOwner owner = oDAO.selectOwner(ownerId);
           String permitNumber =request.getParameter("idNumber");
           DfbsPermit permit = new DfbsPermit();
           permit.setMagIdNumber(permitNumber);
           session.setAttribute("PERMIT_SELECTED",permit);
           ownerForm.setProperties(owner);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("OWNER_SELECTED",owner);
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
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
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
             List permitsRenewList=(pDAO.selectPermits(owner.getOwnerId(),"All",null));
              session.setAttribute("PERMIT_RENEW_LIST",new HsListWrapper(permitsRenewList));
          setOptions(request,dfbsUtilityDAO);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
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
        
        else if (method.equals("sendEmail")) 
        {
          sendEmail(request);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          return mapping.findForward("sendEmailForm");
        }
        else if (method.equals("sendEmailForm")) 
        {
          return mapping.findForward("sendEmailForm");
        }
        
       
         else if (method.equals("editOwner")) 
        {  String ownerId = request.getParameter("key");
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
         DfbsOwner owner =cart.getOwner(updatedOwner.getOwnerKey());
          ownerForm.setUpdatedProperties(updatedOwner,owner);
           session.setAttribute("OWNER_SELECTED",owner);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
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
           cart.removeOwner(ownerKey);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/permit.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
          
        else if (method.equals("renewDirect")) 
        { int ownerId = Integer.parseInt(request.getParameter("ownerId"));
         DfbsOwner selectedOwner = oDAO.selectOwner(ownerId);
        //  permit.setFileList(pDAO.selectFileList(permit)); 
          String permitNumber =request.getParameter("idNumber");
          DfbsPermit permit = pDAO.selectPermit(permitNumber,"Old");
          session.setAttribute("OWNER_SELECTED",selectedOwner);
          session.setAttribute("PERMIT_SELECTED",permit);
          String permitNew="";
          String ownerCartCheck ="N";
          Map mapOwner = cart.getOwnerMap();
          Set ownerkeys = mapOwner.keySet();
          Iterator i = ownerkeys.iterator();
           while(i.hasNext())
           { 
           String key = (String) i.next();
           DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
           if (owner.getOwnerId()==selectedOwner.getOwnerId())
           {
             session.setAttribute("IN_MAP_OWNER",owner);
             ownerCartCheck ="Y";
           }
           }
          if (ownerCartCheck =="Y")
          { 
            DfbsOwner inMapOwner = (DfbsOwner) session.getAttribute("IN_MAP_OWNER");
            inMapOwner.addPermit(permit);
            if (permit.getMagIdNumber().equals(""))
            { permitNew="Y";
            }
            else
            { permitNew="N";
            }
            pDAO.computeFees(cart,permitNew);
            setList(request,oDAO,ownerForm, pDAO);
            setFilterOptions(request,dfbsUtilityDAO);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("ownerListDisplayAll");
          }
          else
          {
          selectedOwner.addPermit(permit);
          selectedOwner.setFileList(oDAO.selectFileListNew(selectedOwner));
          cart.addOwner(selectedOwner);
          if (permit.getMagIdNumber().equals(""))
            { permitNew="Y";
            }
            else
            { permitNew="N";
            }
             pDAO.computeFees(cart,permitNew);
            setList(request,oDAO,ownerForm, pDAO);
            setFilterOptions(request,dfbsUtilityDAO);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("ownerListDisplayAll");
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
  
 private static void setList(HttpServletRequest request,DfbsOwnerDAO fwDAO,DfbsOwnerForm ownerForm,DfbsPermitDAO pDAO) throws Exception
  {
  
     HttpSession session = request.getSession();
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("DFBS_OWNER_LIST_FILTER");
     MagazineUsers magUser = (MagazineUsers)session.getAttribute("MAGAZINE_USER");
     if (magUser != null) { 
      list = fwDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_MAG_USER,magUser.getUserLoginId(),"All","All",pDAO);
     }
     else
     {
    // filterSession.setValue(filterForm.getCounty());
     if(filterSession == null ) {       
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
        list = fwDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),"All","All",pDAO);
     } 
     else if(filterSession.getType().equals("byOwnerId")) {       
        String ownerId= request.getParameter("ownerId");
         list = fwDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_OWNER_ID,ownerId,"All","All",pDAO);
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),"All","All",pDAO);
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_COUNTY,filterSession.getValue(),"byCounty",ownerForm.getCounty(),pDAO);
        ownerForm.setCounty(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_CITY,filterSession.getValue(),"byCity",ownerForm.getCity(),pDAO);
        ownerForm.setCity(filterSession.getValue());
     } 
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
            sub.append(" From explosive magazine Online enquiry - ").append(subject);
          
          
          
            mail.createMail("fire_magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From explosive magazine error email Online enquiry error ","DfbsOwnerAction");
            mail1.sendAndClose();            
          }
  }
  
 protected static boolean validate(DfbsOwner owner,DfbsOwnerForm errorForm,HttpSession session) 
  {
    boolean noError = true;
    ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
  
    
    if(owner.getOwnerLastName() == null || owner.getOwnerLastName().trim().equals("") ) 
    {
      errorForm.setOwnerLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerLastName("");
    }
     
    if (security !=null && (security.getGroupLevelAll() !=null || security.getGroupLevelFire().equals("DBA")))
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
    if(owner.getOwnerFirstName() == null || owner.getOwnerFirstName().trim().equals("") ) 
    {
      errorForm.setOwnerFirstName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerFirstName("");
    }
     
    if (security == null)
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
   
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    List counties =  uDAO.getOptions(DfbsSQL.SELECT_COUNTY_LIST_OPTIONS);
     List inspType = uDAO.getOptions(DfbsSQL.SELECT_INSPECTION_TYPE);
    List inspectors = uDAO.getOptions(DfbsSQL.SELECT_INSPECTORS);
    List inspOptions = uDAO.getOptions(DfbsSQL.SELECT_INSP_OPTIONS);
    List alaramOptions = uDAO.getOptions(DfbsSQL.SELECT_ALARAM_OPTIONS);
    request.setAttribute("INSPECTION_TYPE_OPTIONS",inspType);
    request.setAttribute("INSPECTORS_OPTIONS",inspectors);
    request.setAttribute("ALARAM_OPTIONS",alaramOptions);
    request.setAttribute("COMPLIED_OPTIONS",inspOptions);
    request.setAttribute("MAGAZINE_COUNTY_OPTIONS",counties); 
 } 
 
 
}
