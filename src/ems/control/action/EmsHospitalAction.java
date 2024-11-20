package ems.control.action;

import ems.control.form.EmsHospitalForm;

import main.to.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;

import hs.control.ControlAction;

import main.data.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;

import otherUsers.to.otherUsers;


public class EmsHospitalAction extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        EmsHospitalForm hospitalForm = (EmsHospitalForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsHospitalDAO hDAO = (EmsHospitalDAO) dmapNew.getHsDAO(DfbsDataMap2.HOSPITAL);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
         if (method.equals("hospitalList"))
        { setList(request,hDAO,hospitalForm);
          setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("hospitalList");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,hDAO,hospitalForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("hospitalList");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,hospitalForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,hDAO,hospitalForm);
          return mapping.findForward("hospitalList");
        } 
      
        else if (method.equals("hospitalDetail")) 
        {
          int id = Integer.parseInt(request.getParameter("hospitalId"));
          this.setView(request,hDAO,hospitalForm,id);
          setOptions(request,dfbsUtilityDAO);
          EmsHospital hospital = (EmsHospital) session.getAttribute("HOSPITAL");
            if (otherUser !=null)
          {
           hospital.setFileList(eDAO.selectFileList(request.getParameter("hospitalId"),"Hospital",null,null)); 
          
           int HospSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),id,"Hospital");
          if (HospSecurityCheck >0)
          {
            session.setAttribute("HOSPITAL_SECURITY_FLAG","Y");
          }
          else
          {
             session.setAttribute("HOSPITAL_SECURITY_FLAG","N");
          }
          }
          return mapping.findForward("hospitalDetail");
        }
        
         else if (method.equals("requestRenewal")) 
        {  EmsHospital hospital = (EmsHospital) session.getAttribute("HOSPITAL");
           sendEmailRequestRenewal(hospital,otherUser);
          this.setView(request,hDAO,hospitalForm,hospital.getHospitalId());
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("hospitalDetail");
        }
                 
         if(method.equals("saveHospital")) 
         
       { 
          EmsHospital hospital =  hospitalForm.getEmsHospital();
          EmsHospitalForm errorForm = new EmsHospitalForm();
           if (validateHospital(hospital,errorForm))
          { 
             hDAO.updateHospital(hospital);
             setList(request,hDAO,hospitalForm);
             setFilterOptions(request,dfbsUtilityDAO);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("hospitalList");
          }
          else
          { request.setAttribute("USER_ERROR",errorForm);
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("hospitalDetail");
          }
        }
           if(method.equals("personList")) 
        {   System.out.println(" go person list");
                   return mapping.findForward("personList");
         }    
          else if (method.equals("startOverForm")) 
        {
          return mapping.findForward("startOverForm");
        }
        else if (method.equals("startOver")) 
        {
          session.setAttribute("HOSPITAL",null);
           setList(request,hDAO,hospitalForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
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
    List charNav = uDAO.getCharacterNavs(EmsSQL.SELECT_FIRST_LETTER_OPTIONS_HOSP);
    List county = uDAO.getOptions(EmsSQL.SELECT_COUNTY_OPTIONS_HOSP);
    List cities = uDAO.getOptions(EmsSQL.SELECT_CITIES_OPTIONS_HOSP);
    
    request.setAttribute("HOSPITAL_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("HOSPITAL_COUNTY_OPTIONS",county);
    request.setAttribute("HOSPITAL_CITIES_OPTIONS",cities);
   
 }
 protected static void setFilterSession(HttpServletRequest request,EmsHospitalForm filterForm) throws Exception
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
     session.setAttribute("HOSPITAL_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,EmsHospitalDAO ehDAO,EmsHospitalForm hospitalForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("HOSPITAL_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("HOSPITAL_LIST_FILTER",filterSession);
        list = ehDAO.selectHospitalList(EmsSQL.SELECT_HOSPITAL_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = ehDAO.selectHospitalList(EmsSQL.SELECT_HOSPITAL_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = ehDAO.selectHospitalList(EmsSQL.SELECT_HOSPITAL_BY_COUNTY,filterSession.getValue());
        hospitalForm.setState(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = ehDAO.selectHospitalList(EmsSQL.SELECT_HOSPITAL_BY_CITY,filterSession.getValue());
        hospitalForm.setCity(filterSession.getValue());
     } 
    
    
     request.setAttribute("HOSPITAL_LIST",new HsListWrapper(list));
     
  }
  protected static EmsHospital setView(HttpServletRequest request,
    EmsHospitalDAO rDAO,
      EmsHospitalForm hospitalForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsHospital hospital = rDAO.selectHospital(id);
    
    session.setAttribute("HOSPITAL",hospital);
    
    hospitalForm.setProperties(hospital);
    return hospital; 
   
  }
  
 private void sendEmailRequestRenewal(EmsHospital hospital,otherUsers otherUser) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"rmiller2@dhs.in.gov","rstump@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            
          
            StringBuffer sb = new StringBuffer();
            sb.append("Name: "+hospital.getHospitalName() +", Certification number:" +hospital.getCertNumber());
            sb.append("\n\r Hospital renewal request submitted by : " +otherUser.getUserLoginId());
            sb.append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append("Request for renewal of Hospital" +hospital.getHospitalName());
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Hospital renewal error email ","EmsCourseAction");
            mail1.sendAndClose();             }
  }
protected static boolean validateHospital(EmsHospital hospital ,EmsHospitalForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(hospital.getHospitalName() == null || hospital.getHospitalName().trim().equals("")  )
    {
    errorForm.setHospitalName("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setHospitalName("");
       
    }
    
 
    if(hospital.getAddress1() == null || hospital.getAddress1().trim().equals("") ) 
    { 
      errorForm.setAddress1("ERROR_NOTEQUAL");
      noError = false;
    } 
     else
    {
      errorForm.setAddress1("");
    }
    if(hospital.getAddress1() == null || hospital.getAddress1().trim().equals("")) 
    { 
      errorForm.setAddress1("ERROR_LENGTH");
      noError = false;
    } 
     else
    {
      errorForm.setAddress1("");
    }
   
    return noError;
  }
    
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
    
     List stateList = uDAO.getOptions(EmsSQL.SELECT_STATE_LIST_OPTIONS);
     List countyList = uDAO.getOptions(EmsSQL.SELECT_COUNTY_LIST_OPTIONS);
     request.setAttribute("HOSPITAL_STATE_LIST_OPTIONS",stateList);
     request.setAttribute("HOSPITAL_COUNTY_LIST_OPTIONS",countyList);
    
 }  
 
private void sendEmailRenewalApproved(HttpServletRequest request,EmsHospital hospital,EmsSecurity security) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"abiggs@dhs.in.gov","rstump@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: "+hospital.getHospitalName() +"certification number:" +hospital.getCertNumber());
            sb.append("\n\r Hospital renewal request approved by : " +security.getCurrentUser());
            sb.append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append("Request for renewal of Hospital" +hospital.getHospitalName());
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  } 
}