package aepermits.control.action;
import aepermits.control.*;
import aepermits.control.form.*;
import aepermits.to.*;
import aepermits.data.*;

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
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import main.data.*;
import main.to.*;

import util.logging.DHSLogLevel;
import util.logging.Log;

public class DfbsEntrApplicationAction extends ControlAction
{
    private static final String CLASS_NAME="DfbsEntrApplicationAction";
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      String METHOD_NAME="executeControl";  
      try {
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsEntrOwnerDAO oDAO = (DfbsEntrOwnerDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_OWNER);
        DfbsEntrPermitDAO pDAO = (DfbsEntrPermitDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_PERMIT);
        
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
         ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
        
        if (method.equals("startApplication")) 
        {
          String ownerId = request.getParameter("ownerId");
          DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
          if(ownerId == null && owner ==null) 
          { 
            session.setAttribute("DFBS_OWNER",new DfbsOwner());
            session.setAttribute("DFBS_OWNER_APPLICATION",new DfbsOwner());
            request.setAttribute("DFBS_APPLICATION","NEW");
            return mapping.findForward("intro");
          } 
          else 
          { 
           DfbsEntrPermit permit =pDAO.selectPermitUpdate(request.getParameter("permitNumber"));
            ownerForm.setProperties(owner);
            session.setAttribute("DFBS_OWNER_APPLICATION",owner);
            session.setAttribute("PERMIT_SELECTED",permit);  
            request.setAttribute("DFBS_APPLICATION","RENEW");
              
            return mapping.findForward("intro");
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
          return mapping.findForward("intro");
        }
        else if(method.equals("saveOwner")) 
        {
           DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
           DfbsOwnerForm errorForm = new DfbsOwnerForm();
           if (validate(updatedOwner,errorForm,security) ) {
               Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "updatedOwner.:"+updatedOwner);   
              updatedOwner.setPermitsMap(ownerApplication.getPermitsMap());
              session.setAttribute("DFBS_OWNER_APPLICATION",updatedOwner);
              this.saveToken(request);
              ownerForm.setProperties(updatedOwner);
              setOptions(request,dfbsUtilityDAO);
              request.setAttribute("DFBS_SAVE","OWNER");
             DfbsEntrPermit permit = (DfbsEntrPermit) session.getAttribute("PERMIT_SELECTED");
             if (permit !=null && permit.getPermitNumber() !=null) {
               ownerApplication.addPermit(permit);
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
               redirectUrl.append(request.getContextPath()).append("/aepermits/permit.do?method=editPermitForm&key="+permit.getPermitNumber());
               response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;
             }
              else
             { 
                 return mapping.findForward("step2");}
           } 
           else 
           {
             request.setAttribute("DFBS_OWNER_ERROR",errorForm);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("step1");
           }
        }  
        else if(method.equals("step1")) 
        { ownerApplication.setApplicationStage("step1");
          ownerForm.setProperties(ownerApplication);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("step1");
        } 
      
        else if(method.equals("step2")) 
        { ownerApplication.setApplicationStage("step2");
           return mapping.findForward("step2");
        } 
        else if(method.equals("step3")) 
        {  ownerApplication.setApplicationStage("step3");
           pDAO.computeFees(owner,ownerApplication);
            DfbsOwnerForm ownerErrorForm = new DfbsOwnerForm();
          if(validateApplication(oDAO,owner,ownerApplication,ownerErrorForm,session,security)) 
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
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
       return mapping.findForward("error");
      
    }
   
  }
  
  protected static boolean validateApplication(DfbsEntrOwnerDAO oDAO,DfbsOwner owner,
                    DfbsOwner ownerApp,DfbsOwnerForm ownerErrorForm,HttpSession session,ApplicationSecurity security) throws Exception
  {
    boolean noError2 = true;
    boolean noError1= true;
    String onlySpecialFlag="True" ;   
    Map permitsErrorMap = new HashMap();
    
    Map mapPermit = ownerApp.getPermitsMap();
    Set permitkeys = mapPermit.keySet();
    Iterator i = permitkeys.iterator();
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsEntrPermit permit = (DfbsEntrPermit) mapPermit.get(key); 
     DfbsEntrPermitForm errorForm = new DfbsEntrPermitForm();
     if( permit.getStatus().trim().equals("EXPIRED") || permit.getStatus().trim().equals("EXPIRES SOON")||permit.getStatus().trim().equals("NEW"))
     {onlySpecialFlag="False"; 
     noError2 = DfbsEntrPermitAction.validate(permit,errorForm,oDAO,session);
     }
     else
     {
       noError2 = true;
     }
      
     permitsErrorMap.put(key,errorForm);
     // for specials map check
         Map specialErrorMap = new HashMap();
        
        Map mapSpecial = permit.getSpecialMap();
        Set specialkeys = mapSpecial.keySet();
        Iterator j = specialkeys.iterator();
        while(j.hasNext())
        {   String keySpecial = (String) j.next();
            DfbsEntrSpecial special = (DfbsEntrSpecial) mapSpecial.get(keySpecial); 
            String fileName=oDAO.getUploadFileName(special.getApplicationKey(),"AESpecial");
             if(fileName.equals("NoName") ) 
            {         
              special.setNoMap("ERROR");
              session.setAttribute("FILE_UPLOAD","FALSE");
            } 
             else 
            { session.setAttribute("FILE_UPLOAD","TRUE");          
              special.setNoMap("uploaded:" + fileName);
              
            } 
        }
    }
    if (onlySpecialFlag.equals("False"))
    {
     noError1 = DfbsEntrApplicationAction.validate(ownerApp,ownerErrorForm,security);
    if(ownerApp.getPermitsMapCount() == 0) 
     {
       noError1 = false;
     }}
    ownerErrorForm.setPermitsMap(permitsErrorMap);
    session.setAttribute("ONLY_SPECIAL_FLAG",onlySpecialFlag);
  return (noError1 && noError2) ? true : false ;
 
  }
  
  
  protected static boolean validate(DfbsOwner owner,DfbsOwnerForm errorForm,ApplicationSecurity security) 
  {
    boolean noError = true;
    
    if(owner.isNew() == true)
    {
    
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
    }
    
    if(owner.getPhoneNumber() == null || owner.getPhoneNumber().trim().equals("") ||
       owner.getPhoneNumber().indexOf('-') > 0 ||owner.getPhoneNumber().indexOf(')') > 0 ||
       owner.getPhoneNumber().indexOf('(') > 0) 
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
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("DFBS_STATE_OPTIONS",states);
    //request.setAttribute("DFBS_CITIES_OPTIONS",cities);
 }
 
 
 
 


 
}
  



