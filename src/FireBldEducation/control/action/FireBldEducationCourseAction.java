package FireBldEducation.control.action;
import main.data.*;
import FireBldEducation.control.form.*;
import main.to.*;
import FireBldEducation.to.*;
import FireBldEducation.data.*;

import aepermits.to.DfbsEntrPermit;
import aepermits.to.DfbsEntrSpecial;

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

public class FireBldEducationCourseAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
           
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
            
          
          DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          FireBldEducationCourseForm courseForm = (FireBldEducationCourseForm) form;
          FireBldEducationCourseDAO courseDAO = (FireBldEducationCourseDAO) dmapNew.getHsDAO(DfbsDataMap2.FIRE_BLDG_COURSE);
            FireBldEducationUserDAO userDAO = (FireBldEducationUserDAO) dmapNew.getHsDAO(DfbsDataMap2.FIRE_BLDG_USER);
            HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
           ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS"); 
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
           if (method == null ) 
          {
            List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
              request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
              setFilterOptions(request,dfbsUtilityDAO);
               return mapping.findForward("courseList");
          } 
          else if (method.equals("courseList") ) 
          {  List courseList=null;
            if (security !=null) {
                courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES_ADMIN);
            }
            else
            {
              courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
            }
              request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
              setFilterOptions(request,dfbsUtilityDAO);
               return mapping.findForward("courseList");
          } 
            else if (method.equals("courseListUser") ) 
                        {  
                List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
                setFilterOptions(request,dfbsUtilityDAO);
                 return mapping.findForward("courseList");
                
            } 
            else if (method.equals("courseRoster") ) 
            {   
                String courseId = request.getParameter("courseId");
                List courseRoster=courseDAO.selectCourseRoster(Integer.parseInt(courseId));
                FireBldEducationCourse course = courseDAO.selectCourse(Integer.parseInt(courseId));
                session.setAttribute("COURSE_SELECTED",course);
                session.setAttribute("COURSE_ROSTER",new HsListWrapper(courseRoster));
                 return mapping.findForward("courseRoster");
            } 
          else if (method.equals("emailCerts") ) 
          {    FireBldEducationCourse course = (FireBldEducationCourse) session.getAttribute("COURSE_SELECTED");
            List userList=userDAO.selectUserListCertPrint(course.getCourseId());
            Iterator j = userList.iterator();
             while(j.hasNext())
             {
               FireBldEducationUser userFDU = (FireBldEducationUser) j.next();
              sendEmailCertification(userFDU,course);
             }
            return mapping.findForward("courseRoster");
          } 
          else if (method.equals("emailCertSingle") ) 
          {    FireBldEducationCourse course = (FireBldEducationCourse) session.getAttribute("COURSE_SELECTED");
            String userId = request.getParameter("userPersonId");
            FireBldEducationUser user = userDAO.selectUser(Integer.parseInt(userId));
              sendEmailCertification(user,course);
            return mapping.findForward("courseRoster");
          } 
          else if (method.equals("emailClassCancel") ) 
          {    String courseId = request.getParameter("courseId");
            String courseReason = request.getParameter("courseDescription");
             FireBldEducationCourse course = courseDAO.selectCourse(Integer.parseInt(courseId));
            course.setCourseDescription( course.getCourseDescription()+" "+courseReason+".Class cancelled by "+security.getUserId()+".");
            course.setClassSize(0);
            courseDAO.updateCourse(course);
            List userList=courseDAO.selectCourseRoster(course.getCourseId());
            Iterator j = userList.iterator();
             while(j.hasNext())
             {
               FireBldEducationUser userFDU = (FireBldEducationUser) j.next();
              sendEmailClassCancellation(userFDU,course, courseReason);
             }
            return mapping.findForward("courseRoster");
          } 
         
            else if (method.equals("updateRoster") ) 
            {   FireBldEducationCourse course = (FireBldEducationCourse) session.getAttribute("COURSE_SELECTED");
                List courseRoster=courseDAO.selectCourseRoster(course.getCourseId());
                Iterator i = courseRoster.iterator();
                while(i.hasNext())
                {
                  FireBldEducationUser codeUser = (FireBldEducationUser) i.next();
                    StringBuffer sb = new StringBuffer("");
                    sb.append(Integer.toString(codeUser.getUserPersonId()));
                          String param = request.getParameter(sb.toString());
                          if(param != null) 
                          {
                            courseDAO.updateCourseRoster("NO_SHOW",course.getCourseId(),codeUser.getUserPersonId());
                          }
                          else
                              {
                                courseDAO.updateCourseRoster("SHOW",course.getCourseId(),codeUser.getUserPersonId());
                              }
                  List userList=userDAO.selectUserListCertPrint(course.getCourseId());
                 courseRoster=courseDAO.selectCourseRoster(course.getCourseId());
                 session.setAttribute("COURSE_ROSTER",new HsListWrapper(courseRoster));
                  request.setAttribute("USER_EMAIL_LIST",new HsListWrapper(userList));
               }
              return mapping.findForward("courseRoster");
            } 
            else if (method.equals("cancelCourse") ) 
            {   String userPersonId = request.getParameter("userPersonId");
                List courseList=courseDAO.selectCourseListCancel(FireBldEducationSQL.SELECT_CODE_COURSES_CANCEL,Integer.parseInt(userPersonId));
                session.setAttribute("CANCEL_COURSE_PERSON",userPersonId);
                request.setAttribute("CANCEL_COURSE_LIST",new HsListWrapper(courseList));
                setFilterOptions(request,dfbsUtilityDAO);
                 return mapping.findForward("cancelCourse");
            } 
            else if (method.equals("cancelCourseSave") ) 
            {   String userPersonId = request.getParameter("personId");
                String courseId = request.getParameter("courseId");
                courseDAO.deletePersonCourse(FireBldEducationSQL.DELETE_CODE_COURSE_USER,Integer.parseInt(userPersonId),Integer.parseInt(courseId));
                List courseList=courseDAO.selectCourseListCancel(FireBldEducationSQL.SELECT_CODE_COURSES_CANCEL,Integer.parseInt(userPersonId));
                session.setAttribute("CANCEL_COURSE_PERSON",userPersonId);
                request.setAttribute("CANCEL_COURSE_LIST",new HsListWrapper(courseList));
                FireBldEducationUser viewUser = (FireBldEducationUser) session.getAttribute("VIEW_USER");
                 FireBldEducationCourse course = courseDAO.selectCourse(Integer.parseInt(courseId));
                sendEmailCancellation(viewUser.getUserId(),contacts,course);
                setFilterOptions(request,dfbsUtilityDAO);
                 return mapping.findForward("cancelCourse");
            } 
           
              else if (method.equals("filter")) 
                     {
                       setFilterSession(request,courseForm);
                       setFilterOptions(request,dfbsUtilityDAO);
                      setList(request,courseDAO,courseForm,security);
                       return mapping.findForward("courseList");
                     } 
            else if (method.equals("addNewCourse") ) 
            {
                setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("newCourse");
            } 
            else if (method.equals("updateCourse") ) 
            {   String courseId = request.getParameter("courseId");
           FireBldEducationCourse course = courseDAO.selectCourse(Integer.parseInt(courseId));
           courseForm.setProperties(course);
                setOptions(request,dfbsUtilityDAO);
                session.setAttribute("UPDATE_COURSE",course);
              return mapping.findForward("updateCourse");
            } 
            else if (method.equals("updateSaveCourse") ) 
            {                   FireBldEducationCourse course =  courseForm.getCodeCourse();
                          FireBldEducationCourseForm errorForm = new FireBldEducationCourseForm();
                        
                           if (validateCourse(course,errorForm,courseDAO))
                           {
                               courseDAO.updateCourse(course);
                               courseDAO.updateCourseCalender(course);
                               List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                               request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
                               return mapping.findForward("courseList");
                           }
                            else {setOptions(request,dfbsUtilityDAO);
                                return mapping.findForward("updateCourse");
                            }
            } 
            else if (method.equals("saveCourse") ) 
            {
                FireBldEducationCourse course =  courseForm.getCodeCourse();
                          FireBldEducationCourseForm errorForm = new FireBldEducationCourseForm();
                        
                           if (validateCourse(course,errorForm,courseDAO))
                           {
                               courseDAO.insertCourse(course);
                               courseDAO.insertCourseCalender(course);
                               List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                               request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
                               return mapping.findForward("courseList");
                           }
                            else {setOptions(request,dfbsUtilityDAO);
                                return mapping.findForward("newCourse");
                            }
            } 
          else if (method.equals("insertCalandar") ) 
          {
            String courseId = request.getParameter("courseId");
                       FireBldEducationCourse course = courseDAO.selectCourse(Integer.parseInt(courseId));System.out.println("before");
            courseDAO.insertCourseCalender(course);
                           List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                           request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
                           return mapping.findForward("courseList");
          } 
            else if (method.equals("courseAdmin") ) 
            { 
                if (security.getGroupLevelAll() !=null || security.getGroupLevelFireBldEducation().equals("USER") ) {
                    session.setAttribute("SECURITY_SUPER_USER","YES");
                }
                else {
                    session.setAttribute("SECURITY_SUPER_USER","NO"); 
                }
              return mapping.findForward("courseAdmin");
            } 
            else if (method.equals("registerCourse") ) 
            {   String courseId = request.getParameter("courseId");
               FireBldEducationUser viewUser = (FireBldEducationUser) session.getAttribute("VIEW_USER");
               if (viewUser !=null && viewUser.getUserId().length() >3 )
               {
               FireBldEducationCourse course = courseDAO.selectCourse(Integer.parseInt(courseId));
                int regCount= courseDAO.selectCourseRegCount(course.getCourseId());
                int verifyCount= courseDAO.selectPersonRegCount(viewUser.getUserPersonId(),course.getCourseId());
                if (verifyCount == 1) {   
                List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                               request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
                    request.setAttribute("REGISTERED_PREVIOUSLY","Registered");
                               return mapping.findForward("courseList");
                }
                else {
                    request.setAttribute("REGISTERED_PREVIOUSLY","");
                }
                if (course.getClassSize() - regCount ==0) {
                    return mapping.findForward("waitList");  
                }
                else {
               
                     courseDAO.registerCourse(viewUser.getUserPersonId(),Integer.parseInt(courseId));
                       sendEmailRegistration(viewUser.getUserId(),contacts,course);
                     request.setAttribute("REGISTERED_SUCCESSFULLY","Y");
                     List courseList=courseDAO.selectCourseList(FireBldEducationSQL.SELECT_CODE_COURSES);
                                    request.setAttribute("COURSE_LIST",new HsListWrapper(courseList));
                     return mapping.findForward("courseList");
                   }
               }
               else {
                 return mapping.findForward("start");
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
    protected static boolean validateCourse(FireBldEducationCourse course ,FireBldEducationCourseForm errorForm,FireBldEducationCourseDAO cDAO) throws Exception
     {
       boolean noError = true;
       
      
        if(course.getStartDateString() == null || course.getStartDateString().trim().equals("")  )
       {
      errorForm.setStartDate("ERROR");
             
        noError = false;
         }
       else
       {  
          
           errorForm.setStartDate("");
           
       }
       
       
        if(course.getCourseName() == null || course.getCourseName().trim().equals("")  )
       {
       errorForm.setCourseName("ERROR");
            
        noError = false;
         }
       else
       {  
           errorForm.setCourseName("");
          
       }
         if(course.getClassLocation() == null || course.getClassLocation().trim().equals("")  )
         {
         errorForm.setClassLocation("ERROR");
              
         noError = false;
          }
         else
         {
            errorForm.setClassLocation("");
           
         }
       if(course.getCity() == null || course.getCity().trim().equals("")  )
       {
       errorForm.setCity("ERROR");
            
       noError = false;
        }
       else
       {
          errorForm.setCity("");
         
       }
       return noError;
     }
    protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
    {
      
      
        
    //  List county = uDAO.getOptions(FireBldEducationSQL.SELECT_COUNTY_LIST_OPTIONS);
   //    request.setAttribute("COURSE_COUNTY_OPTIONS",county);
      List state = uDAO.getOptions(FireBldEducationSQL.SELECT_STATE_OPTIONS2);
       request.setAttribute("COURSE_STATE_OPTIONS",state);
      
    }
    private static void setList(HttpServletRequest request,FireBldEducationCourseDAO courseDAO,FireBldEducationCourseForm courseForm,ApplicationSecurity security ) throws Exception
     {
     
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        
        List list = null;
        
        HsFilter filterSession = (HsFilter) session.getAttribute("COURSE_LIST_FILTER");
        
        
        if(filterSession == null) {
           filterSession = new HsFilter();
           filterSession.setType("byLetter");
           filterSession.setValue("R");
           session.setAttribute("COURSE_LIST_FILTER",filterSession);
            if (security !=null) {
                list = courseDAO.selectCourseList(FireBldEducationSQL.SELECT_COURSE_BY_LETTER_ADMIN ,filterSession.getValue());
            }
            else
            {
                list = courseDAO.selectCourseList(FireBldEducationSQL.SELECT_COURSE_BY_LETTER ,filterSession.getValue());
            } 
        } 
        else if(filterSession.getType().equals("byLetter")) 
        { 
        
            if (security !=null) {
                list = courseDAO.selectCourseList(FireBldEducationSQL.SELECT_COURSE_BY_LETTER_ADMIN ,filterSession.getValue());
            }
            else
            {
                list = courseDAO.selectCourseList(FireBldEducationSQL.SELECT_COURSE_BY_LETTER ,filterSession.getValue());
            } 
        } 
       
       
       
       
        request.setAttribute("COURSE_LIST",new HsListWrapper(list));
        
     }
           
       protected static void setFilterSession(HttpServletRequest request,FireBldEducationCourseForm courseForm ) throws Exception
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
       
        session.setAttribute("COURSE_LIST_FILTER",filterSession);
       
     }   
     
    protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
    {
      List charNav = uDAO.getCharacterNavs(FireBldEducationSQL.SELECT_COURSE_FIRST_LETTER_OPTIONS);
      
      
      request.setAttribute("COURSE_FIRST_LETTERS_OPTIONS",charNav);
      
     
    }     
    
    private static void sendEmailRegistration(String userId,ApplicationContacts contacts,FireBldEducationCourse course ) throws Exception
     {
             try {
               HsMailer mail = new HsMailer();
               String[] emailTo = {userId};
              // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
               String[] bccTo = {"sadkinson@dhs.in.gov"};
             
             
               StringBuffer sb = new StringBuffer("\r\n");
               sb.append(" This is to confirm that you are registered for the course: " +course.getCourseName()+".");
               sb.append(" The course is scheduled on " +course.getStartDateString()+" at "+course.getClassLocation()+","+course.getAddress1()+","+course.getCity()+" IN "+course.getZip());
               sb.append(" \r\n Couse Description: "+ course.getCourseDescription());
               sb.append(" \r\n If you  have any questions regarding this email or the course you can contact ");
               sb.append(contacts.getContact1Email() +" by email.");
               sb.append(". To cancel or  register for IDHS Fire and Building Code Education classes log back  into the system at https://oas.dhs.in.gov/dfbs/fireBldEducation/start.do.");
               StringBuffer sub = new StringBuffer();
               sub.append(" Fire and Building Code Education Class Registration Confirmation Email  ");
              mail.createMail("FireBldEducation_online@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
               mail.sendAndClose();
               } 
               catch(Exception e) 
               {
                 HsMailer mail = new HsMailer();
               mail.createMail("FireBldEducation_online@dhs.in.gov","sadkinson@dhs.in.gov","error email class registration",userId);
               mail.sendAndClose();
               }
     }  
    private static void sendEmailCancellation(String userId,ApplicationContacts contacts,FireBldEducationCourse course ) throws Exception
     {
             try {
               HsMailer mail = new HsMailer();
               String[] emailTo = {userId};
             //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
             //  String[] bccTo = {"nnimmagadda@dhs.in.gov"};
             
             
               StringBuffer sb = new StringBuffer("\r\n");
               sb.append(" This is to confirm that you have cancelled the course" +course.getCourseName()+".");
               sb.append(" The course is scheduled on date " +course.getStartDateString()+" at "+course.getClassLocation()+".");
               sb.append(" If you  have any questions regarding this email or the course you can contact ");
               sb.append(contacts.getContact2Email() +" by email or call "+ contacts.getContact2Phone());
               sb.append("\r\n Couse Description: "+ course.getCourseDescription()+".");
               sb.append("\r\n To cancel or register for IDHS Fire and Building Code Education classes log back into the system at https://oas.dhs.in.gov/dfbs/fireBldEducation/start.do.");
                                  
               
               
               StringBuffer sub = new StringBuffer();
               sub.append(" Fire and Building Code Education Class Cancellation Confirmation Email  ");
             
             
             
               mail.createMail("FireBldEducation_online@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
               mail.sendAndClose();
               } 
               catch(Exception e) 
               {
                 HsMailer mail = new HsMailer();
               mail.createMail("FireBldEducation_online@dhs.in.gov","sadkinson@dhs.in.gov","error email class cancellation",userId);
               mail.sendAndClose();
               }
     }  
  private static void sendEmailCertification(FireBldEducationUser user,FireBldEducationCourse course ) throws Exception
   {
           try {
             HsMailer mail = new HsMailer();
             String[] emailTo = {user.getUserId()};
            // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
             String[] bccTo = {"sadkinson@dhs.in.gov"};
           
           
             StringBuffer sb = new StringBuffer("\r\n");
             sb.append(" This is to confirm that you have completed the course: " +course.getCourseName()+".");
             sb.append(" The course is scheduled on " +course.getStartDateString()+" at "+course.getClassLocation()+","+course.getAddress1()+","+course.getCity()+" IN "+course.getZip());
             sb.append(" \r\n Couse Description: "+ course.getCourseDescription());
             sb.append(" \r\n use this link to print course completion certificate." );
             sb.append(" \r\n https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf");
             sb.append("&report=fire_bldg_class_certificate.rdf"+"&p_course_Id="+course.getCourseId()+"&p_user_Id="+user.getUserPersonId()+" ");
             StringBuffer sub = new StringBuffer();
             sub.append(" Fire and Building Code Education Class Completion Certificate.");
            mail.createMail("sadkinson@dhs.in.gov@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
             mail.sendAndClose();
             } 
             catch(Exception e) 
             {
               HsMailer mail = new HsMailer();
             mail.createMail("tshoaff@dhs.in.gov","sadkinson@dhs.in.gov","error email class registration",user.getUserId());
             mail.sendAndClose();
             }
   }   
  private static void sendEmailClassCancellation(FireBldEducationUser user,FireBldEducationCourse course,String cancelReason ) throws Exception
   {
           try {
             HsMailer mail = new HsMailer();
            // String[] emailTo = {user.getUserId()};
             String[] emailTo = {"nnimmagadda@dhs.in.gov"};
             String[] bccTo = {"nnimmagadda@dhs.in.gov"};
           
           
             StringBuffer sb = new StringBuffer("\r\n");
             sb.append(" This is to inform that the course: " +course.getCourseName()+".");
             sb.append(" scheduled on " +course.getStartDateString()+" at "+course.getClassLocation()+","+course.getAddress1()+","+course.getCity()+" IN "+course.getZip());
             sb.append(" is cancelled for the following reason."+cancelReason+";");
               sb.append(" If you  have any questions regarding this email or the course you can contact ");
               sb.append("sadkinson@dhs.in.gov");
             StringBuffer sub = new StringBuffer();
             sub.append(" Fire and Building Code Education Class Cancelled.");
            mail.createMail("FireBldEducation_online@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
             mail.sendAndClose();
             } 
             catch(Exception e) 
             {
               HsMailer mail = new HsMailer();
             mail.createMail("tshoaff@dhs.in.gov","sadkinson@dhs.in.gov","error email class registration",user.getUserId());
             mail.sendAndClose();
             }
   }   
  
}
