package code.control.action;
import code.to.*;
import code.control.form.*;
import code.data.* ;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.data.* ;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

public class CodePersonAction extends ControlAction
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
        
        CodePersonForm personForm = (CodePersonForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CodePersonDAO personDAO = (CodePersonDAO) dmap2.getHsDAO(DfbsDataMap.PERSON);
        CodeFacilityDAO fDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
        CodeManufacturerDAO mDAO = (CodeManufacturerDAO) dmap2.getHsDAO(DfbsDataMap.MANUFACTURER);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
         if  (method.equals("newContactPerson")) 
        {
          return mapping.findForward("ContactPersonForm");
        }
        else if (method.equals("editPerson")) 
        {  
          String personId = request.getParameter("applicationKey");
          String personType = request.getParameter("personType");
          String facilityIdString = request.getParameter("facilityId");
         
          
          if (personType.equals("M")){
          CodePerson person = manufacturer.getPerson(personId);
          personForm.setProperties(person);
          }
          if (personType.equals("F")){ 
          String facId = request.getParameter("facilityId");
          CodeFacility facility = manufacturer.getFacility(facId); 
          CodePerson person = facility.getPerson(personId);
          personForm.setProperties(person);
          }
          return mapping.findForward("editPerson");
        }
         else if (method.equals("savePerson")) 
        { 
        
         String facilityIdString = request.getParameter("codePersonCompanyId");
        
          CodePerson person = personForm.getCodePerson();
          CodePersonForm errorForm = new CodePersonForm();
          if (validate(person,errorForm) ) {
             if (personForm.getCodePersonType().equals("F")){
            String key = request.getParameter("applicationKey");
            String facId = request.getParameter("codePersonCompanyId");
             CodeFacility facility = manufacturer.getFacility(facilityIdString); 
            facility.removePerson(key);
            facility.addPerson(person);
             return mapping.findForward("viewFacility");
             }
              if (personForm.getCodePersonType().equals("M")){
              if (person.getCodePersonId()==0) {  
            String key = request.getParameter("applicationKey");
             manufacturer.removePerson(key); }
            manufacturer.addPerson(person);
            
             return mapping.findForward("backtoview");
             }
          }
          else
          {
           request.setAttribute("CODE_PERSON_ERROR",errorForm);
           return mapping.findForward("editPerson");
          }
        }
        else if (method.equals("newPerson")) 
        {  
           return mapping.findForward("editPerson");
        }
        else if (method.equals("backtoview")) 
        {  
           return mapping.findForward("backtoview");
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
   protected static boolean validate(CodePerson person, CodePersonForm errorForm) throws Exception
  {
    boolean noError = true;
    
     
    if(person.getCodePersonFirstName() == null || person.getCodePersonFirstName().trim().equals("") ) 
    {
      errorForm.setCodePersonFirstName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCodePersonFirstName("");
    }
    if(person.getCodePersonLastName() == null || person.getCodePersonLastName().trim().equals("") ) 
    {
      errorForm.setCodePersonLastName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCodePersonLastName("");
    }
    
    if(person.getCodePersonTitle() == null || person.getCodePersonTitle().trim().equals("") ) 
    {
      errorForm.setCodePersonTitle("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setCodePersonTitle("");
    }
    
    
    if(person.getCodePersonTelephone() == null || person.getCodePersonTelephone().trim().equals("") ) 
    {
      errorForm.setCodePersonTelephone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCodePersonTelephone("");
    }
    return noError;
    
  }
  
 
 
}