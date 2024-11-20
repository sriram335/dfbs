package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import main.to.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import main.data.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;

import otherUsers.to.otherUsers;


public class EmsInstitutionAction  extends ControlAction
{
 
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        EmsInstitutionForm institutionForm = (EmsInstitutionForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsInstitutionDAO IDAO = (EmsInstitutionDAO) dmapNew.getHsDAO(DfbsDataMap2.INSTITUTION);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
         if (method.equals("institutionList"))
        { setList(request,IDAO,institutionForm,"xx");
          setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("institutionList");
        } 
         if (method.equals("addNewCourse"))
        { String courseId = (String) session.getAttribute("NEW_COURSE_ID");
          if(courseId !=null &&(courseId.equals("150013") || courseId.equals("150017")))
          {
            setList(request,IDAO,institutionForm,courseId);
          }
          else
          {
            setList(request,IDAO,institutionForm,"xx");
          }
          setFilterOptions(request,dfbsUtilityDAO);
          session.setAttribute("NEW_COURSE","In Process");
          session.setAttribute("NEW_COURSE_INSTITUTION","Not Selected");
           return mapping.findForward("institutionList");
        } 
        else if (method.equals("refresh")) 
        {
          String courseId = (String) session.getAttribute("NEW_COURSE_ID");
          if(courseId !=null &&(courseId.equals("150013") || courseId.equals("150017")))
          {
            setList(request,IDAO,institutionForm,courseId);
          }
          else
          {
            setList(request,IDAO,institutionForm,"xx");
          }
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("institutionList");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,institutionForm);
          setFilterOptions(request,dfbsUtilityDAO);
          String courseId = (String) session.getAttribute("NEW_COURSE_ID");
          if(courseId !=null &&(courseId.equals("150013") || courseId.equals("150017")))
          {
            setList(request,IDAO,institutionForm,courseId);
          }
          else
          {
            setList(request,IDAO,institutionForm,"xx");
          }
          return mapping.findForward("institutionList");
        } 
      
        else if (method.equals("institutionDetail")) 
        {
          int id = Integer.parseInt(request.getParameter("institutionId"));
          this.setView(request,IDAO,institutionForm,id);
          setOptions(request,dfbsUtilityDAO);
          EmsInstitution institution = (EmsInstitution) session.getAttribute("INSTITUTION");
          if (otherUser !=null)
          {
          institution.setFileList(eDAO.selectFileList(request.getParameter("institutionId"),"Institution",null,null)); 
         
           int InstSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),id,"Institution");
          if (InstSecurityCheck >0 )
          {
            session.setAttribute("INSTITUTION_SECURITY_FLAG","Y");
          }
          else
          {
             session.setAttribute("INSTITUTION_SECURITY_FLAG","N");
          }
           }
          return mapping.findForward("institutionDetail");
        }
        
        else if (method.equals("addProvider")) 
        {
          int providerId = Integer.parseInt(request.getParameter("providerId"));
          EmsInstitution institution =(EmsInstitution) session.getAttribute("INSTITUTION");
          IDAO.addProvider(providerId,institution.getInstitutionId());
          this.setView(request,IDAO,institutionForm,institution.getInstitutionId());
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("institutionDetail");
        }
          else if (method.equals("deleteProvider")) 
        {
          int providerId = Integer.parseInt(request.getParameter("providerId"));
          EmsInstitution institution =(EmsInstitution) session.getAttribute("INSTITUTION");
          IDAO.deleteProvider(providerId,institution.getInstitutionId());
          this.setView(request,IDAO,institutionForm,institution.getInstitutionId());
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("institutionDetail");
        }   
        
         else if (method.equals("requestRenewal")) 
        {  EmsInstitution institution =(EmsInstitution) session.getAttribute("INSTITUTION");
           sendEmailRequestRenewal(institution,otherUser);
           this.setView(request,IDAO,institutionForm,institution.getInstitutionId());
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("institutionDetail");
        }
         if(method.equals("saveInstitution")) 
         
       { 
          EmsInstitution institution =  institutionForm.getEmsInstitution();
          EmsInstitutionForm errorForm = new EmsInstitutionForm();
           if (validateInstitution(institution,errorForm))
          { 
             IDAO.updateInstitution(institution);
             setList(request,IDAO,institutionForm,"xx");
             setFilterOptions(request,dfbsUtilityDAO);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("institutionList");
          }
          else
          { request.setAttribute("USER_ERROR",errorForm);
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("institutionDetail");
          }
        }
           if(method.equals("personList")) 
        {  
                   return mapping.findForward("personList");
         }    
          else if (method.equals("startOverForm")) 
        {
          return mapping.findForward("startOverForm");
        }
        else if (method.equals("startOver")) 
        {
          session.setAttribute("INSTITUTION",null);
           setList(request,IDAO,institutionForm,"xx");
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
    List charNav = uDAO.getCharacterNavs(EmsSQL.SELECT_FIRST_LETTER_OPTIONS_INST);
    List county = uDAO.getOptions(EmsSQL.SELECT_COUNTY_OPTIONS_INST);
    List cities = uDAO.getOptions(EmsSQL.SELECT_CITIES_OPTIONS_INST);
    
    request.setAttribute("INSTITUTION_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("INSTITUTION_COUNTY_OPTIONS",county);
    request.setAttribute("INSTITUTION_CITIES_OPTIONS",cities);
   
 }
 protected static void setFilterSession(HttpServletRequest request,EmsInstitutionForm filterForm) throws Exception
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
       filterSession.setValue(filterForm.getInstitutionCounty());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(filterForm.getInstitutionCity());
     }
     session.setAttribute("INSTITUTION_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,EmsInstitutionDAO eIDAO,EmsInstitutionForm institutionForm,String courseId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("INSTITUTION_LIST_FILTER");
     String listSql = " order by 2";
     if (courseId.equals("150013"))
     {
      listSql = " and institution_type ='2' and institytion_accredidated='Yes' order by 2";
     }
      if (courseId.equals("150017"))
     {
      listSql = " and institution_type ='2' order by 2";
     }
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("INSTITUTION_LIST_FILTER",filterSession);
        list = eIDAO.selectInstitutionList(EmsSQL.SELECT_INSTITUTION_BY_LETTER + listSql,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = eIDAO.selectInstitutionList(EmsSQL.SELECT_INSTITUTION_BY_LETTER + listSql,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = eIDAO.selectInstitutionList(EmsSQL.SELECT_INSTITUTION_BY_COUNTY + listSql,filterSession.getValue());
        institutionForm.setInstitutionState(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = eIDAO.selectInstitutionList(EmsSQL.SELECT_INSTITUTION_BY_CITY + listSql,filterSession.getValue());
        institutionForm.setInstitutionCity(filterSession.getValue());
     } 
    
    
     request.setAttribute("INSTITUTION_LIST",new HsListWrapper(list));
     
  }
  protected static EmsInstitution setView(HttpServletRequest request,
    EmsInstitutionDAO rDAO,
      EmsInstitutionForm institutionForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsInstitution institution = rDAO.selectInstitution(id);
    
    session.setAttribute("INSTITUTION",institution);
    
    institutionForm.setProperties(institution);
    return institution; 
   
  }
  
 private void sendEmailRequestRenewal(EmsInstitution institution,otherUsers otherUser) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"rmiller2@dhs.in.gov","rstump@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            
          
            StringBuffer sb = new StringBuffer();
            sb.append("Name: "+institution.getInstitutionName() +", Certification number:" +institution.getInstitutionCertNumber());
            sb.append("\n\r Institution renewal request submitted by : " +otherUser.getUserLoginId());
            sb.append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append("Request for renewal of Institution" +institution.getInstitutionName());
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail1 = new HsMailer();
              mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Institution renewal error email ","EmsCourseAction");
              mail1.sendAndClose(); 
            }
  }
protected static boolean validateInstitution(EmsInstitution institution ,EmsInstitutionForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(institution.getInstitutionName() == null || institution.getInstitutionName().trim().equals("")  )
    {
    errorForm.setInstitutionName("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setInstitutionName("");
       
    }
    
 
    if(institution.getInstitutionAddress1() == null || institution.getInstitutionAddress1().trim().equals("") ) 
    { 
      errorForm.setInstitutionAddress1("ERROR_NOTEQUAL");
      noError = false;
    } 
     else
    {
      errorForm.setInstitutionAddress1("");
    }
    if(institution.getInstitutionAddress1() == null || institution.getInstitutionAddress1().trim().equals("")) 
    { 
      errorForm.setInstitutionAddress1("ERROR_LENGTH");
      noError = false;
    } 
     else
    {
      errorForm.setInstitutionAddress1("");
    }
    return noError;
  }
    
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
    
     List stateList = uDAO.getOptions(EmsSQL.SELECT_STATE_LIST_OPTIONS);
     List countyList = uDAO.getOptions(EmsSQL.SELECT_COUNTY_LIST_OPTIONS);
     request.setAttribute("INSTITUTION_STATE_LIST_OPTIONS",stateList);
     request.setAttribute("INSTITUTION_COUNTY_LIST_OPTIONS",countyList);
    
 }  
 
 
}