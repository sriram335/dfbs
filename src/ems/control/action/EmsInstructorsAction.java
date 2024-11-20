package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import otherUsers.to.*;
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


public class EmsInstructorsAction  extends ControlAction
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
          EmsInstructorsForm instructorForm = (EmsInstructorsForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsInstructorsDAO iDAO = (EmsInstructorsDAO) dmapNew.getHsDAO(DfbsDataMap2.INSTRUCTOR);
        EmsCourseDAO cDAO = (EmsCourseDAO) dmapNew.getHsDAO(DfbsDataMap2.COURSE);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
        // provider persons
     
          if (method.equals("addNewInstructor"))
        {   EmsInstitution institution = (EmsInstitution)session.getAttribute("INSTITUTION");
          EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
           EmsInstructors instructor = instructorForm.getEmsInstructors();
           iDAO.addNewInstructor(course, instructor);
          instructorForm.setProperties(null);
          List instList= iDAO.selectPersonList(course.getCourseId());
          request.setAttribute("COURSE_INSTRUCTOR_SELECTED",instList);
            session.setAttribute("INSTRUCTOR_SELECTED",instructor);
            setOptions(request,dfbsUtilityDAO);
            session.setAttribute("NEW_COURSE","In Process");
             session.setAttribute("NEW_COURSE_INSTITUTION","Selected:" +institution.getInstitutionName());
             session.setAttribute("NEW_COURSE_INFORMATION","Completed");
            session.setAttribute("NEW_COURSE_INSTRUCTOR","Selected:"+instructor.getPersonLastName()+" "+instructor.getPersonFirstName()); 
           return mapping.findForward("addNewInstructor");
        } 
        
          if (method.equals("otherStaff"))
        {    String agreeTo ="NO";
          setOptions(request,dfbsUtilityDAO);
             session.setAttribute("AGREE_TO",agreeTo);
            return mapping.findForward("addNewInstructor");
        } 
          if (method.equals("goToNewInstructor"))
        {   EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("addNewInstructor");
        } 
         if (method.equals("instructorListOrganization"))
        {  
           String orgName=request.getParameter("orgName");
            setListOrganization(request,iDAO,instructorForm,orgName);
          
           return mapping.findForward("addNewInstructor");
        } 
         if (method.equals("saveInstructor"))
        {  EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
           EmsInstructors instructor = instructorForm.getEmsInstructors();
            session.setAttribute("INSTRUCTOR_SELECTED",instructor);
          String agreeTo =(String) session.getAttribute("AGREE_TO");
           if (agreeTo != null && agreeTo.equals("NO"))
           { iDAO.addNewInstructor(course, instructor);
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/ems/course.do?method=courseDetail&courseId=" +course.getCourseId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
           }
           else
           {EmsInstitution institution = (EmsInstitution)session.getAttribute("INSTITUTION");
           session.setAttribute("NEW_COURSE","In Process");
           session.setAttribute("NEW_COURSE_INSTITUTION","Selected:" +institution.getInstitutionName());
           session.setAttribute("NEW_COURSE_INFORMATION","Completed");
           session.setAttribute("NEW_COURSE_INSTRUCTOR","Selected:"+instructor.getPersonLastName()+" "+instructor.getPersonFirstName()); 
           session.setAttribute("NEW_COURSE_AGREEMENT","In Process"); 
            return mapping.findForward("agreeToText");
           }
        } 
         
      else if (method.equals("instructorDetail")) 
        {
           String id = request.getParameter("personId");
          
       //  this.setView(request,iDAO,instructorForm,id);
         EmsInstructors instructor = (EmsInstructors) session.getAttribute("EMS_INSTRUCTOR");
            if (otherUser !=null)
          {
           instructor.setFileList(eDAO.selectFileList(request.getParameter("personId"),"Person",otherUser.getUserLoginId(),null)); 
          }
         
          return mapping.findForward("instructorDetail");
        }
                
        
          
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
  private static void setList(HttpServletRequest request,EmsInstructorsDAO eiDAO,EmsInstructorsForm instructorForm,int relationId, String personType) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
       
        if (personType == "I")
        {
       // list = eiDAO.selectPersonList(EmsSQL.SELECT_PERSON_LIST_INST,relationId,personType);
         session.setAttribute("EMS_INSTRUCTOR_LIST_TYPE","I");
         session.setAttribute("EMS_INSTRUCTOR_LIST_ID",Integer.toString(relationId));
        }
       
    
     request.setAttribute("PERSON_LIST",new HsListWrapper(list));
     
  }

 
 
   private static void setListOrganization(HttpServletRequest request,EmsInstructorsDAO eiDAO,
        EmsInstructorsForm instructorForm, String orgName) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
       
        list = eiDAO.selectInstructorListOrganization(orgName);
       
        
    
     request.setAttribute("INSTRUCTOR_LIST_LOOKUP",new HsListWrapper(list));
     
  }

 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List personType = uDAO.getOptions(EmsSQL.SELECT_COURSE_PERSON_TYPE_OPTIONS);
    request.setAttribute("INSTRUCTOR_PERSON_TYPE",personType);
 
 
 }
  
}