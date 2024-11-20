package childcare.control.action;

import childcare.control.form.*;
import childcare.to.*;

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
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
import main.data.*;
import main.to.*;
public class ChildcareAction extends ControlAction
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
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          
        if (method == null) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        } 
        
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,oDAO,ownerForm);
          return mapping.findForward("main");
        } 
        else if (method.equals("refreshView")) 
        {
          String id = (String )request.getAttribute("DFBS_OWNER_REFRESH");
          this.setView(request,oDAO,ownerForm,Integer.parseInt(id),session);
          return mapping.findForward("view");
        }
        else if (method.equals("view")) 
        {
          String id = request.getParameter("ownerId");
          this.setView(request,oDAO,ownerForm,Integer.parseInt(id),session);
          return mapping.findForward("view");
        }
         else if (method.equals("viewFees")) 
        { String permitNumber = request.getParameter("permitNumber");
          DfbsChildcarePermit permit=new DfbsChildcarePermit();
          permit.setPermitNumber(permitNumber);
          permit.setPermitFees(pDAO.selectPermitFees(permit.getPermitNumber()));
          session.setAttribute("PERMIT_FEE",permit);
           return mapping.findForward("view");
        }
        
        else if (method.equals("sendEmail")) 
        {
          sendEmail(request,oDAO);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          return mapping.findForward("sendEmailForm");
        }
        else if (method.equals("sendEmailForm")) 
        {
          return mapping.findForward("sendEmailForm");
        }
       
     
        
        throw new Exception("HS_ERROR_METHOD_INVALID1");
             
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
    HttpSession session = request.getSession();
    String structureType = (String) session.getAttribute("STRUCTURE_TYPE");
    List charNav = uDAO.getCharacterNavs(DfbsChildcareSQL.SELECT_FIRST_LETTER_OPTIONS,structureType);
    List otherCounties = uDAO.getOptions(DfbsChildcareSQL.SELECT_COUNTIES_OPTIONS);
    List cities = uDAO.getOptions(DfbsChildcareSQL.SELECT_CITIES_OPTIONS);
     request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
    request.setAttribute("DFBS_CITIES_OPTIONS",cities);
   
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
  
 private static void setList(HttpServletRequest request,DfbsChildcareOwnerDAO fwDAO,DfbsOwnerForm ownerForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("DFBS_OWNER_LIST_FILTER");
     String structureType = (String) session.getAttribute("STRUCTURE_TYPE");
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
        list = fwDAO.selectOwnersList(DfbsChildcareSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),structureType);
       } 
     else if(filterSession.getType().equals("byLetter")) 
     { 
        list = fwDAO.selectOwnersList(DfbsChildcareSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue(),structureType);
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
          list = fwDAO.selectOwnersList(DfbsChildcareSQL.SELECT_OWNERS_BY_COUNTY,filterSession.getValue(),structureType);
        ownerForm.setCounty(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsChildcareSQL.SELECT_OWNERS_BY_CITY,filterSession.getValue(),structureType);
        ownerForm.setCity(filterSession.getValue());
     } 
    
    
     request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
     
  }
  protected static DfbsOwner setView(HttpServletRequest request,
    DfbsChildcareOwnerDAO fwDAO,
      DfbsOwnerForm ownerForm,int id,HttpSession session) throws Exception
  {
   
   DfbsOwner owner = fwDAO.selectOwner(id);
    session.setAttribute("OWNER_SELECTED",owner);
    
    ownerForm.setProperties(owner);
    session.setAttribute("PERMIT_FEE",null);
         
    
    return owner;
  }
  
 private void sendEmail(HttpServletRequest request,DfbsChildcareOwnerDAO oDAO) throws Exception
  {
          try {
           
           
            HsMailer mail = new HsMailer();
            String[] emailTo = {"cclouse@dhs.in.gov"};
          //  String[] emailTo = {"nnimmaga"};
           String[] bccTo = {"nnimmaga"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
          
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append(" From Daycare Online enquiry - ").append(subject);
          
          
          
            mail.createMail("childCare_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("childCare_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Childcare Online enquiry error email ","HsChildcareAction");
            mail1.sendAndClose();
          
            }
  }
  
 

 
 
}
