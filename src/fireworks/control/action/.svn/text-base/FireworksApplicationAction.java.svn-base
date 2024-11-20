package fireworks.control.action;

import fireworks.control.form.*;
import fireworks.to.*;
import fireworks.data.*;
import fireworks.report.*;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;



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
import main.to.*;
import hs.util.*;
import main.data.*;
import hs.data.system.*;


public class FireworksApplicationAction extends ControlAction
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
        DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
        DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
        
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
        if (method.equals("startApplication")) 
        {
          String ownerId = request.getParameter("ownerId");
          if(ownerId == null) 
          {
            session.setAttribute("DFBS_OWNER",new DfbsOwner());
            session.setAttribute("DFBS_OWNER_APPLICATION",new DfbsOwner());
            request.setAttribute("DFBS_APPLICATION","NEW");
              setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("step1");
          } 
          else 
          {
            DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
            owner = FireworksAction.setView(request,oDAO,ownerForm,ownerId);
            ownerForm.setProperties(owner);
            DfbsOwner newOwner = ownerForm.getDfbsOwner();
            session.setAttribute("DFBS_OWNER_APPLICATION",newOwner);
            request.setAttribute("DFBS_APPLICATION","RENEW");
              setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("step1");
          }
        } 
        else if (method.equals("startOverForm")) 
        {
          return mapping.findForward("startOverForm");
        }
        else if (method.equals("startOver")) 
        {
          session.setAttribute("DFBS_OWNER",null);
          session.setAttribute("DFBS_OWNER_APPLICATION",null);
          return mapping.findForward("main");
        }
        
        //CHECK IF owner and ownerApplication is valid
        //at this point owner must be in session
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
        if(owner == null || ownerApplication == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
        if (method.equals("intro")) 
        {
          return mapping.findForward("step1");
        }
        else if(method.equals("saveOwner")) 
        { 
            
           DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
           DfbsOwnerForm errorForm = new DfbsOwnerForm();
           if (validate(updatedOwner,errorForm,security) ) {
              updatedOwner.setPermitsMap(ownerApplication.getPermitsMap());
              session.setAttribute("DFBS_OWNER_APPLICATION",updatedOwner);
              this.saveToken(request);
              ownerForm.setProperties(updatedOwner);
              setOptions(request,dfbsUtilityDAO);
              request.setAttribute("DFBS_SAVE","OWNER");
              return mapping.findForward("step2");
           } 
           else 
           {
             request.setAttribute("DFBS_OWNER_ERROR",errorForm);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("step1");
           }
        }  
        else if(method.equals("step1")) 
        {
          ownerForm.setProperties(ownerApplication);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("step1");
        } 
        else if(method.equals("step2")) 
        {
          return mapping.findForward("step2");
        } 
        else if(method.equals("step3")) 
        { String permitYear =(String) session.getAttribute("PERMIT_YEAR");
          int wholesaleCountDb = oDAO.selectCount(DfbsFireworksSQL.SELECT_WHOLESALE_COUNT,
          owner.getOwnerId(), permitYear);
          int retailTentCountDb = oDAO.selectCount(DfbsFireworksSQL.SELECT_RETAIL_TENT_COUNT,
          owner.getOwnerId(), permitYear);
          int retailClassCountDb = oDAO.selectCount(DfbsFireworksSQL.SELECT_RETAIL_CLASS_COUNT,
          owner.getOwnerId(), permitYear);
          int retailStandCountDb = oDAO.selectCount(DfbsFireworksSQL.SELECT_RETAIL_STAND_COUNT,
          owner.getOwnerId(), permitYear);
        
          owner.setRetailTentPermitsCount(retailStandCountDb);
          owner.setRetailTentPermitsCount(retailTentCountDb);
          owner.setRetailClassPermitsCount(retailClassCountDb);
          owner.setWholesalePermitsCount(wholesaleCountDb);
        
          pDAO.computeFees(owner,ownerApplication);
          
          DfbsOwnerForm ownerErrorForm = new DfbsOwnerForm();
          if(validateApplication(oDAO,owner,ownerApplication,ownerErrorForm,session,pDAO,security)) 
          {
              request.setAttribute("DFBS_APPLICATION_ERROR",null);  
          } 
          else 
          {
              request.setAttribute("DFBS_APPLICATION_ERROR",ownerErrorForm);
          }
          
          return mapping.findForward("step3");
        }        
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
   
  }
  
  protected static boolean validateApplication(DfbsFireworksOwnerDAO oDAO,DfbsOwner owner,DfbsOwner ownerApp,DfbsOwnerForm ownerErrorForm,HttpSession session,DfbsFireworksPermitDAO pDAO,ApplicationSecurity security) throws Exception
  {
    boolean noError1 = FireworksApplicationAction.validate(ownerApp,ownerErrorForm,security);
    boolean noError2 = true;
    
    if(ownerApp.getPermitsMapCount() == 0) 
    {
      noError1 = false;
    }
    
    Map permitsErrorMap = new HashMap();
    
    Map mapPermit = ownerApp.getPermitsMap();
    Set permitkeys = mapPermit.keySet();
    Iterator i = permitkeys.iterator();
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsFireworksPermit permit = (DfbsFireworksPermit) mapPermit.get(key); 
     DfbsFireworksPermitForm errorForm = new DfbsFireworksPermitForm();
     noError2 = FireworksPermitAction.validate(permit,errorForm,pDAO,session);
     permitsErrorMap.put(key,errorForm);
     String fileUploadStatus = (String) session.getAttribute("FILE_UPLOAD");
     if (fileUploadStatus.equals("FALSE"))
     {
       noError1 =false;
     }
    }
    ownerErrorForm.setPermitsMap(permitsErrorMap);
 
    
    if(owner.getWholesalePermitsCount() + ownerApp.getWholesalePermitsCount() > 1) 
    {
      noError1 = false;
      ownerErrorForm.setWholesalePermitCount(-1);
    }
 
   return (noError1 && noError2) ? true : false ;
 
  }
  
  
  protected static boolean validate(DfbsOwner owner,DfbsOwnerForm errorForm,ApplicationSecurity security ) 
  {
    boolean noError = true;
    
    
    
    if(owner.getOwnerName() == null || owner.getOwnerName().trim().equals("") 
    || owner.getOwnerName().equals("-----")
    ) 
    {
      errorForm.setOwnerName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerName("");
    }
    
    if(owner.getStreet1() == null || owner.getStreet1().trim().equals("") ) 
    {
      errorForm.setStreet1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setStreet1("");
    }
    
    if(owner.getCity() == null || owner.getCity().trim().equals("") ) 
    {
      errorForm.setCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCity("");
    }
    
    if(owner.getStateId() == 0  ) 
    {
      errorForm.setStateId(-1);
      noError = false;
    } 
    else 
    {
      errorForm.setState("");
    }
    
    if(owner.getZip() == null || owner.getZip().trim().equals("") ) 
    {
      errorForm.setZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setZip("");
    }
    
    if(owner.getPhoneNumber() == null || owner.getPhoneNumber().trim().equals("") ) 
    {
      errorForm.setPhoneNumber("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPhoneNumber("");
    }
    
    if(owner.getFirstName() == null || owner.getFirstName().trim().equals("") ) 
    {
      errorForm.setFirstName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setFirstName("");
    }
    
    if(owner.getLastName() == null || owner.getLastName().trim().equals("") ) 
    {
      errorForm.setLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setLastName("");
    }
    
    if(owner.getContactPhoneNumber() == null || owner.getContactPhoneNumber().trim().equals("") ) 
    {
      errorForm.setContactPhoneNumber("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContactPhoneNumber("");
    }
    if (security == null || security.getUserId()==null)
    {
      if(owner.getOwnerEmail() == null || owner.getOwnerEmail().trim().equals("") || owner.getOwnerEmail().indexOf("@") < 0 ) 
      {
        errorForm.setOwnerEmail("ERROR");
        noError = false;
      } 
      else 
      {
        errorForm.setOwnerEmail("");
      }
    }
   
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsFireworksSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("DFBS_STATE_OPTIONS",states);
    //request.setAttribute("DFBS_CITIES_OPTIONS",cities);
 }
 
 
 
 


 
}
  


