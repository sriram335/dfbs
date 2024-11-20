package idhsInspections.control.action;


import childcare.control.action.ChildcareAction;
import childcare.control.action.ChildcareApplicationAction;
import childcare.control.form.DfbsChildcarePermitForm;
import childcare.control.form.DfbsOwnerForm;
import childcare.to.DfbsOwner;
import childcare.data.*;

import childcare.to.DfbsChildcarePermit;

import idhsInspections.control.*;
import idhsInspections.control.form.*;
import idhsInspections.to.*;
import idhsInspections.data.*;
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
import hs.data.*;
import main.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import main.data.DfbsDataMap;
public class dfbsOwnerAction extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        ServletContext context =  this.getServlet().getServletConfig().getServletContext();
       
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        fireInspectionDAO iDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER); 
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
         fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
       
        if (method.equals("addNewOwner")) 
        {
          /*  
            ownerForm.setOwnerLastName(inspection.getFacilityContact());
            ownerForm.setOwnerAddress1(inspection.getFacilityStreetPrefix()+" "+inspection.getFacilitySteetNumber()+" "+
                                       inspection.getFacilityStreetName()+" "+inspection.getFacilityStreetSuffix());
            ownerForm.setOwnerAddress2(inspection.getFacilityAddress2());
            ownerForm.setOwnerCity(inspection.getFacilityCity());
            ownerForm.setOwnerZip(inspection.getFacilityZip());
            ownerForm.setOwnerPhone(inspection.getFacilityPhone());
            ownerForm.setOwnerEmail(inspection.getFacilityEmail());
            ownerForm.setOwnerStateId(15);
           */ 
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("updateOwner");
          }
        else if(method.equals("lookUpOwners")) 
               {String lastName=request.getParameter("ownerLastName");
              List ownerList=  iDAO.selectOwnersList(InspectionsSQL.SELECT_OWNERS, lastName);
             request.setAttribute("INSPECTIONS_OWNER_LIST",new HsListWrapper(ownerList));
        setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("updateOwner");         
          } 
         else if(method.equals("newSaveOwner")) 
                {            
            DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
              DfbsOwnerForm errorForm = new DfbsOwnerForm();
             if (validate(updatedOwner,errorForm) ) {
             iDAO.createAEandBUdata(updatedOwner,inspection,"New");  }
             else
             {   request.setAttribute("OWNER_ERROR",errorForm);
                 setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("updateOwner");       } 
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append("/dfbs/idhsInspections/inspection.do?method=updateIdhsInspection&inspectionId="+inspection.getInspId());
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString())); 
             return(null);
           } 
        else if(method.equals("attachOwner")) 
               {int ownerId= Integer.parseInt(request.getParameter("ownerId"));
            DfbsOwner updatedOwner = iDAO.selectOwner(ownerId);
                iDAO.createAEandBUdata(updatedOwner,inspection,"Old");  
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append("/dfbs/idhsInspections/inspection.do?method=updateIdhsInspection&inspectionId="+inspection.getInspId());
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));   
        return(null);
          } 
        else if(method.equals("updateOwner")) 
               {int ownerId= Integer.parseInt(request.getParameter("ownerId"));
            DfbsOwner updatedOwner = iDAO.selectOwner(ownerId);
            ownerForm.setProperties(updatedOwner);
            return mapping.findForward("updateOwner");
          } 
        else if(method.equals("updateSaveOwner")) 
               {int ownerId= Integer.parseInt(request.getParameter("ownerId"));
            DfbsOwner updatedOwner = iDAO.selectOwner(ownerId);
            ownerForm.setProperties(updatedOwner);
            return mapping.findForward("updateOwner");
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
    
   /* if(owner.getOwnerPhone() == null || owner.getOwnerPhone().trim().equals("") ||
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
  */
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsChildcareSQL.SELECT_STATE_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    //request.setAttribute("DFBS_CITIES_OPTIONS",cities);
 }
 
 
 
 


 
}
