package ems.control.action;
import ems.to.*;
import ems.control.form.*;
import main.to.*;
import ems.data.*;
import java.io.*;
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

import main.data.*;

import otherUsers.to.otherUsers;

public class  EmsCourseAction extends ControlAction
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
        EmsCourseForm courseForm = (EmsCourseForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsCourseDAO cDAO = (EmsCourseDAO) dmapNew.getHsDAO(DfbsDataMap2.COURSE);
        EmsInstructorsDAO iDAO = (EmsInstructorsDAO) dmapNew.getHsDAO(DfbsDataMap2.INSTRUCTOR);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        EmsInstitutionDAO inDAO = (EmsInstitutionDAO) dmapNew.getHsDAO(DfbsDataMap2.INSTITUTION);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
      if (method.equals("courseList"))
        {  int institutionId = Integer.parseInt(request.getParameter("institutionId"));
           setList(request,cDAO,courseForm,institutionId);
           return mapping.findForward("courseList");
        } 
        if (method.equals("courseListAdmin"))
          {    setList(request,cDAO,courseForm,0);
             return mapping.findForward("courseList");
          } 
      else if (method.equals("courseDetail")) 
        {
          int id = Integer.parseInt(request.getParameter("courseId"));
           setOptions(request,dfbsUtilityDAO);
          this.setView(request,cDAO,courseForm,id);
           EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
            if (otherUser !=null || security !=null)
          {
           course.setFileList(eDAO.selectFileList(request.getParameter("courseId"),"Course",null,null)); 
          }
          return mapping.findForward("courseDetail");
        }
         else if (method.equals("courseRegister")) 
        {
          String courseNumber = request.getParameter("courseNumber");
           setOptions(request,dfbsUtilityDAO);
          this.setViewByNumber(request,cDAO,courseForm,courseNumber);
          return mapping.findForward("courseDetail");
        }
          else if (method.equals("addNewCourse")) 
        {  session.setAttribute("NEW_COURSE","In Process");
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("selectCourse");
        }   
          else if (method.equals("selectCourse")) 
        {
          int id = Integer.parseInt(request.getParameter("crssCourseId"));
          String courseName=cDAO.selectCourseName(id);
          session.setAttribute("NEW_COURSE_TYPE","Selected:" +courseName);
           session.setAttribute("NEW_COURSE_ID",Integer.toString(id));
           courseForm.setCrssCourseId(id);
           EmsInstitution institution =(EmsInstitution) session.getAttribute("INSTITUTION");
           courseForm.setInstitutionId(institution.getInstitutionId());
              setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("courseDetail");
            }   
         else if (method.equals("addInstitution")) 
        {
            int id = Integer.parseInt(request.getParameter("institutionId"));
             EmsInstitution institution = inDAO.selectInstitution(id);
            session.setAttribute("INSTITUTION",institution);
          session.setAttribute("NEW_COURSE","In Process");
          session.setAttribute("NEW_COURSE_INSTITUTION","Selected:" +institution.getInstitutionName());
          session.setAttribute("NEW_COURSE_INFORMATION","");
           session.setAttribute("NEW_COURSE_INSTRUCTOR",""); 
           session.setAttribute("NEW_COURSE_INSTRUCTOR_TYPE",""); 
           session.setAttribute("NEW_COURSE_AGREEMENT",""); 
           session.setAttribute("NEW_COURSE_FILE_UPLOAD","");
          // List list = cDAO.selectPersonEmailList(id);
         //  request.setAttribute("PERSON_CONTACT_EMAIL",new HsListWrapper(list));
         String courseId = (String) session.getAttribute("NEW_COURSE_ID");
         courseForm.setCrssCourseId(Integer.parseInt(courseId));
         session.setAttribute("COURSE",new EmsCourse());
         EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
         course.setCourseNumber("New");
           setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("courseDetail");
        } 
     /*  removed after discussion  else if (method.equals("updateInstEmails")) 
        {    EmsInstitution institution = (EmsInstitution) session.getAttribute("INSTITUTION");
             List personEmails = cDAO.selectPersonEmailList(institution.getInstitutionId());
              Iterator i = personEmails.iterator();
              System.out.println("1");
              while(i.hasNext())
              {
                EmsPerson person = (EmsPerson) i.next();
                StringBuffer sb = new StringBuffer("");
                sb.append(Integer.toString(person.getEmsPersonId()));
                String personEmail=request.getParameter(sb.toString());
                if (personEmail.trim().length() > 5)
                {
                System.out.println(personEmail);
                  cDAO.updatePersonEmail(person,personEmail);
                }
              }
           session.setAttribute("NEW_COURSE","In Process");
          session.setAttribute("NEW_COURSE_INSTITUTION","Selected:" +institution.getInstitutionName());
          session.setAttribute("NEW_COURSE_UPDATE_EMAILS","Done");
          session.setAttribute("NEW_COURSE_INFORMATION","In Process");
           session.setAttribute("NEW_COURSE_INSTRUCTOR",""); 
           session.setAttribute("NEW_COURSE_INSTRUCTOR_TYPE",""); 
           session.setAttribute("NEW_COURSE_AGREEMENT",""); 
           session.setAttribute("NEW_COURSE_FILE_UPLOAD","");
           EmsCourse course = new EmsCourse();
          session.setAttribute("COURSE",course);
          course.setCourseNumber("New");
         String courseId = (String) session.getAttribute("NEW_COURSE_ID") ;
          courseForm.setCrssCourseId(Integer.parseInt(courseId));
          courseForm.setReceivedDate(security.getCurrentDateString());
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("courseDetail");
        }  */
         else if (method.equals("verifyChecks")) 
        { EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
           session.setAttribute("NEW_COURSE_AGREEMENT","Completed"); 
           session.setAttribute("NEW_COURSE_FILE_UPLOAD","In Process");
          verifyChecks(request,session,course);
          return mapping.findForward("agreeToText");
        }        
        else if(method.equals("saveCourse")) 
         
       { 
          EmsCourse course =  courseForm.getEmsCourse();
          EmsCourseForm errorForm = new EmsCourseForm();
           if (validateCourse(course,errorForm,cDAO))
          {  if (course.getCourseId()==0)
             {
              EmsInstitution institution = (EmsInstitution)session.getAttribute("INSTITUTION");
              int institutionId = institution.getInstitutionId();
            //  setList(request,cDAO,courseForm,institutionId);
              setOptions(request,dfbsUtilityDAO); System.out.println("1");
              verifyChecks(request,session,course); System.out.println("2");
              session.setAttribute("COURSE",course);
              List list = null;
           //  list = iDAO.selectPersonList(institution.getInstitutionId());
          //   request.setAttribute("INSTRUCTOR_INSTITUTION",new HsListWrapper(list));
              session.setAttribute("NEW_COURSE","In Process");
             session.setAttribute("NEW_COURSE_INSTITUTION","Selected:" +institution.getInstitutionName());
             session.setAttribute("NEW_COURSE_INFORMATION","Completed");
            session.setAttribute("NEW_COURSE_INSTRUCTOR","In Process");  System.out.println("4");
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
               redirectUrl.append(request.getContextPath()).append("/ems/instructor.do?method=goToNewInstructor");
               response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;
             }
             else
             {
             cDAO.updateCourse(course);
             setOptions(request,dfbsUtilityDAO);
             this.setView(request,cDAO,courseForm,course.getCourseId());
             return mapping.findForward("courseDetail");
             }
          }
          else
          { request.setAttribute("COURSE_ERROR",errorForm);
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("courseDetail");
          }
        }
         else if (method.equals("createNewCourse")) 
        { EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
           setOptions(request,dfbsUtilityDAO);
            EmsInstitution institution = (EmsInstitution)session.getAttribute("INSTITUTION");
            int institutionId = institution.getInstitutionId();
              String courseNumber= createCourseNumber(cDAO,course.getCrssCourseId(),course.getStartDateString(),institution.getInstitutionCertNumber());
             cDAO.insertCourse(course,institutionId,courseNumber,otherUser.getUserLoginId());
             String emailMD = cDAO.selectInstEmailMD(institutionId);
             if (emailMD !=null && emailMD.trim().length() >5)
             {
             sendEmailNewCourseConfirm(course,otherUser,emailMD,context);
             }
             String emailTIO = cDAO.selectInstEmailTIO(institutionId);
             if (emailTIO !=null && emailTIO.trim().length() >5)
             {
             sendEmailNewCourseConfirm(course,otherUser,emailTIO,context);
             }
            sendEmailNewCourse(course,otherUser);
            EmsInstructors instructor =(EmsInstructors) session.getAttribute("INSTRUCTOR_SELECTED");
          iDAO.addNewInstructor(course, instructor);
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/ems/main.do?method=goToUpload&idNumber=" +course.getCourseId()+"&idType=Course");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return(null);
        }  
         else if (method.equals("courseApprove")) 
        {   EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
           cDAO.updateCourseApproval(course);System.out.println("2");
          EmsInstitution institution = (EmsInstitution)session.getAttribute("INSTITUTION");                                  
           String emailMD = cDAO.selectInstEmailMD(course.getInstitutionId());
           String emailTIO = cDAO.selectInstEmailTIO(course.getInstitutionId());
           sendEmailNewCourseApprove(course,institution,security,emailMD,emailTIO,context);
          String email=emailTIO + ",chilton@dhs.in.gov,nnimmagadda@dhs.in.gov";
          if (emailMD!=null && emailMD.trim().length() >5)
          {
            email=emailTIO +",chilton@dhs.in.gov,nnimmagadda@dhs.in.gov," + emailMD;
          }
           setOptions(request,dfbsUtilityDAO);
           if (course.getApprovedDate()==null)
           {
           System.out.println("in calendar");
           cDAO.insertCourseCalender(course,course.getRecCreatedBy());System.out.println("out calendar");
           }
       //   StringBuffer redirectUrl = new StringBuffer("https://oasdev.dhs.in.gov/reports/rwservlet?");
        //  redirectUrl.append("dfbsmailipdf&report=ems_course_app_letter.rdf&desname="+email+"&destype=mail&subject=Course Approval"+"&from=chilton@dhs.in.gov&p_approval=Full&p_course_id="+course.getCourseId());
        //redirectUrl.append("dfbsmailtestpdf&report=ems_course_app_letter.rdf&desname=nnimmagadda@dhs.in.gov&destype=mail&subject="+course.getCourseNumber()+"Course Approval"+"&from=chilton@dhs.in.gov&p_approval=Full&p_course_id="+course.getCourseId());
         // response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
         this.setView(request,cDAO,courseForm,course.getCourseId());
          return mapping.findForward("courseDetail");
        }  
          else if (method.equals("courseCondApprove")) 
        {   EmsCourse course = (EmsCourse) session.getAttribute("COURSE");
           //cDAO.updateCourseApproval(course);
           String emailMD = cDAO.selectInstEmailMD(course.getInstitutionId());
           String emailTIO = cDAO.selectInstEmailTIO(course.getInstitutionId());
          // sendEmailNewCourseCondApprove(course,institution,security,emailMD,emailTIO,context);
           String email="";
          if (emailMD!=null && emailMD.trim().length() >5)
          {
             email=emailTIO+";"+emailMD;
          }
           setOptions(request,dfbsUtilityDAO);
           if (course.getApprovedDate()==null)
           {
           cDAO.insertCourseCalender(course,course.getRecCreatedBy());
           }
         //   StringBuffer redirectUrl = new StringBuffer("https://oasdev.dhs.in.gov/reports/rwservlet?");
       //   redirectUrl.append("dfbsipnpdf&report=ems_course_app_letter.rdf&desname="+"nnimmagadda@dhs.in.gov"+"&destype=mail&subject=Course Conditional Approval"+"&from=chilton@dhs.in.gov&p_approval=Conditional&p_course_id="+course.getCourseId());
      //  redirectUrl.append("dfbsmailtestpdf&report=ems_course_app_letter.rdf&desname=nnimmagadda@dhs.in.gov&destype=mail&subject="+course.getCourseNumber()+"Course Approval"+"&from=chilton@dhs.in.gov&p_approval=Full&p_course_id="+course.getCourseId());
       //   response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
       this.setView(request,cDAO,courseForm,course.getCourseId());
        return mapping.findForward("courseDetail");
        }  
        
        else if (method.equals("registerForCourse")) 
        {  
          return mapping.findForward("registerForCourse");
        }  
         else if (method.equals("confirmCourse")) 
        {  
          String courseId = request.getParameter("courseId");
           EmsCourse course = cDAO.selectCourse(Integer.parseInt(courseId));
          String confirmType = request.getParameter("confirmType");
          String confirmBy = request.getParameter("confirmBy");
          session.setAttribute("NEW_COURSE_ACCEPTANCE",confirmType);
         sendEmailNewCourseAcceptance(course,confirmType,confirmBy);
          return mapping.findForward("acceptance");
        }  
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
  private static void setList(HttpServletRequest request,EmsCourseDAO cDAO,EmsCourseForm courseForm,int institutionId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     if (institutionId>0)
     {
     list = cDAO.selectCourseList(EmsSQL.SELECT_COURSE_LIST,institutionId);
     }
     else {System.out.println("in course list");
       list = cDAO.selectCourseList(EmsSQL.SELECT_COURSE_LIST_ADMIN,0);
     }
     request.setAttribute("COURSE_LIST",new HsListWrapper(list));
     
  }
  protected static EmsCourse setView(HttpServletRequest request,
    EmsCourseDAO rDAO,
      EmsCourseForm courseForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsCourse course = rDAO.selectCourse(id);
    session.setAttribute("COURSE",course);
    
    courseForm.setProperties(course);
    return course; 
   
  }
   protected static EmsCourse setViewByNumber(HttpServletRequest request,
    EmsCourseDAO rDAO,
      EmsCourseForm courseForm, String courseNumber) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsCourse course = rDAO.selectCourseByNumber(courseNumber);
    session.setAttribute("COURSE",course);
    
    courseForm.setProperties(course);
    return course; 
   
  }
  protected static boolean validateCourse(EmsCourse course ,EmsCourseForm errorForm,EmsCourseDAO cDAO) throws Exception
  {
    boolean noError = true;
    
    if(course.getDayClassMeet() == null || course.getDayClassMeet().trim().equals("")  )
    {
    errorForm.setDayClassMeet("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setDayClassMeet("");
       
    }
    if(course.getDayClassTime() == null || course.getDayClassTime().trim().equals("")  )
    {
    errorForm.setDayClassTime("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setDayClassTime("");
       
    }
    if(course.getDayClassTime() == null || course.getDayClassTime().trim().equals("")  )
    {
    errorForm.setDayClassTime("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setDayClassTime("");
       
    }
     if(course.getStartDateString() == null || course.getStartDateString().trim().equals("")  )
    {
   errorForm.setStartDate("ERROR");
     noError = false;
      }
    else
    {  
        int countDays=cDAO.verifyCourseDate(course.getStartDateString());
        if (countDays <= 30)
        {
          errorForm.setStartDate("ERROR");
          noError = false;
        }
        else
        {
        errorForm.setStartDate("");
        }
    }
    
     if(course.getCompletionDateString() == null || course.getCompletionDateString().trim().equals("")  )
    {
    errorForm.setCompletionDate("ERROR");
     noError = false;
      }
    else
    {  
        int countDays=cDAO.verifyCourseDate(course.getCompletionDateString());
        if (countDays <= 31)
        {
          errorForm.setCompletionDate("ERROR");
          noError = false;
        }
        else
        {
        errorForm.setCompletionDate("");
        }
       
    }
     if(course.getCourseLength() == null || course.getCourseLength().trim().equals("")  )
    {
    errorForm.setCourseLength("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setCourseLength("");
       
    }
    if( course.getCrssCourseId()== 0  )
    {
    errorForm.setCrssCourseId(0);
     noError = false;
      }
    else
    {  
        errorForm.setCrssCourseId(course.getCrssCourseId());
       
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
      if(course.getTelephone() == null || course.getTelephone().trim().equals("")  )
    {
    errorForm.setTelephone("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setTelephone("");
       
    }
      if(course.getEmail() == null || course.getEmail().trim().equals("")  )
    {
    errorForm.setEmail("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setEmail("");
       
    }
        if(course.getCounty() == null || course.getCounty().trim().equals("")  )
    {
    errorForm.setCounty("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setCounty("");
       
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
    
    
     List courseList = uDAO.getOptions(EmsSQL.SELECT_COURSE_LIST_OPTIONS);
   
    request.setAttribute("COURSE_LIST_OPTIONS",courseList);
    List county = uDAO.getOptions(EmsSQL.SELECT_COUNTY_OPTIONS);
     request.setAttribute("COURSE_COUNTY_OPTIONS",county);
    List state = uDAO.getOptions(EmsSQL.SELECT_STATE_OPTIONS2);
     request.setAttribute("COURSE_STATE_OPTIONS",state);
    
 } 
   protected static String createCourseNumber( EmsCourseDAO rDAO,int crssCourseId,String startDate,String instCertNumber) throws Exception
  { 
    String courseNumber ="";
   // String countyCode = rDAO.selectCountyCode(countyName);
    String courseType=rDAO.selectCourseType(crssCourseId);
    int courseCount=rDAO.selectCountCourse(courseType+instCertNumber,startDate);
    courseNumber =  courseType + instCertNumber+"-"+(courseCount+1)+"-"+ startDate.substring(8);
    return courseNumber; 
   
  }
   private static void sendEmailNewCourse(EmsCourse course,otherUsers otherUser) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {"rstump@dhs.in.gov","chilton@dhs.in.gov",otherUser.getUserLoginId(),course.getEmail()};
           
         // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n Thank you for using IDHS online EMS Course Registration Application. This is to confirm that you have entered " +course.getCourseId()+ " course in the EMS system.");
            sb.append("\r\n After we get the approval from the Training Institution's Medical Director or TI official we will approve the course.");
            sb.append("\r\n All the status updates will be sent in the form of a email. You can also log in to the system to get the upto date information on this course.");
            StringBuffer sub = new StringBuffer();
            sub.append("EMS New course : " +course.getCourseId()+ " submission confirmation ");
          
          
          
            mail.createMail("chilton@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
             HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS New course entered in the system error email ","EmsCourseAction");
            mail1.sendAndClose();            }
  }
   private static void sendEmailNewCourseConfirm(EmsCourse course,otherUsers otherUser,String email,ServletContext context) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
          String[] emailTo = {"rstump@dhs.in.gov","chilton@dhs.in.gov",email};
          //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
        
          StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
             StringBuffer sb = new StringBuffer("\r\n This is to confirm that we have received " +course.getCourseId()+ " course application in the EMS system");
            sb.append("\r\n  This course was entered by = " + otherUser.getUserLoginId());
            sb.append("\n\n For details of the course click the line below.");
            sb.append("\n\n "+homeUrl+"/dfbs/ems/course.do?method=courseDetail&courseId="+course.getCourseId());
            sb.append("\n\n  If you agree to this course being taught by the instructor at your institution click the link below to confirm acceptance.");
            sb.append("\n\n "+homeUrl+"/dfbs/ems/course.do?method=confirmCourse&confirmType=Approved&courseId="+course.getCourseId()+
                               "&confirmBy="+email);
            sb.append("\n\n  If you DO NOT agree to this course being taught by the instructor at your Institution click the link below to deny the acceptance.");
            sb.append("\n\n "+homeUrl+"/dfbs/ems/course.do?method=confirmCourse&confirmType=Denied&courseId="+course.getCourseId()+
                               "&confirmBy="+email);
             
            StringBuffer sub = new StringBuffer();
            sub.append("EMS New course :" +course.getCourseId()+ " submission confirmation ");
          
          
          
            mail.createMail("chilton@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
             HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS New course entered in the system error email ","EmsCourseAction");
            mail1.sendAndClose();            }
  }
  private static void sendEmailNewCourseApprove(EmsCourse course,EmsInstitution institution,ApplicationSecurity security,String emailMD,String emailTIO,ServletContext context) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"chilton@dhs.in.gov","rstump@dhs.in.gov",course.getEmail(),emailMD,emailTIO,course.getEmail()};
         //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
            StringBuffer sb = new StringBuffer("\r\n EMS New course approved");
            sb.append("\r\n Your course course number=" + course.getCourseNumber());
            sb.append(" is approved by " +security.getUserId());
            sb.append("\r\n if you have any questions about this email contact " +security.getUserId()+"@dhs.in.gov" );
            sb.append("\r\n For details of the course click the lin below.");
            sb.append("\r\n "+homeUrl+"/dfbs/ems/course.do?method=courseDetail&courseId="+course.getCourseId());
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS New course approved ");
          
          
          
            mail.createMail("chilton@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS New course approved error email ","EmsCourseAction");
            mail1.sendAndClose();              }
  } 
  

   private static void sendEmailNewCourseCondApprove(EmsCourse course,EmsInstitution institution,EmsSecurity security,String emailMD,String emailTIO,ServletContext context) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
           String[] emailTo = {"chilton@dhs.in.gov","rstump@dhs.in.gov",emailMD,emailTIO,course.getEmail()};
          // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
            StringBuffer sb = new StringBuffer("\r\n EMS New course is conditionally approved");
            sb.append("\r\n your course course number=" + course.getCourseNumber());
            sb.append("\r\n is approved by " +security.getCurrentUser()+".");
            sb.append("\r\n If you have any questions about this email contact " + security.getCurrentUser());
            sb.append("\r\n  is approved with the following condtions : \r\n" + course.getPendingItems());
             sb.append("\r\n  To look up all the conditions log in to the system and see the course details");
            sb.append("\r\n For details of the course click the lin below.");
            sb.append("\r\n "+homeUrl+"/dfbs/ems/course.do?method=courseDetail&courseId="+course.getCourseId());
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS New course approved with conditions ");
          
          
          
            mail.createMail("chilton@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
           HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS New course approved with conditions error email ","EmsCourseAction");
            mail1.sendAndClose();            }
  } 
  
   private static void sendEmailNewCourseAcceptance(EmsCourse course,String confirmType,String confirmBy) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"chilton@dhs.in.gov","rstump@dhs.in.gov",course.getEmail()};
          // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS Institution " + confirmType + " the");
            sb.append("\r\n course for course Id=" + course.getCourseId()+".");
            sb.append("\r\n This " + confirmType +" for course is received from "+confirmBy+".");
            sb.append("\r\n Process the Course Approval based on this Email.");
            
            StringBuffer sub = new StringBuffer();
            sub.append("\r\n EMS New course Institution " + confirmType +" course for course id=" + course.getCourseId()+".");
          
          
          
            mail.createMail("chilton@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," EMS New course Institution approval error email ","EmsCourseAction");
            mail1.sendAndClose();              }
  } 
     private static void verifyChecks(HttpServletRequest request,HttpSession session,EmsCourse course) throws Exception
  {  List list = new ArrayList();
  /* for first reponder */
    if (course.getCrssCourseId() == 150012)
    {
      int j=15;
      int i=1;
    session.setAttribute("AGREE_TO_FLAG","YES");
    while(i <= j)
    {
      EmsCheckboxes chkbox = new EmsCheckboxes();
      
      String param = request.getParameter("chbox"+Integer.toString(i));
      if((param ==  null || param.equals("off"))) 
      {
      chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("off");
      session.setAttribute("AGREE_TO_FLAG","NO");
      }
      else
      {
       chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("on"); 
      }
      list.add(chkbox);
      String agFlag =(String) session.getAttribute("AGREE_TO_FLAG");
       i=i+1;
      
    }
    course.setCheckBoxes(list);
    }
    /* for advanced */
     if (course.getCrssCourseId() == 150019)
    {
      int j=31;
      int i=1;
    session.setAttribute("AGREE_TO_FLAG","YES");
    while(i <= j)
    {
      EmsCheckboxes chkbox = new EmsCheckboxes();
      
      String param = request.getParameter("chbox"+Integer.toString(i));
      if((param ==  null || param.equals("off")) && i!=5 && i!=6) 
      {
      chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("off");
      session.setAttribute("AGREE_TO_FLAG","NO");
      }
      else
      {
       chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("on"); 
      }
      list.add(chkbox);
      String agFlag =(String) session.getAttribute("AGREE_TO_FLAG");
       i=i+1;
      
    }
    course.setCheckBoxes(list);
    }
     /* for basic */
     if (course.getCrssCourseId() == 150015)
    {
      int j=48;
      int i=1;
    session.setAttribute("AGREE_TO_FLAG","YES");
    while(i <= j)
    {
      EmsCheckboxes chkbox = new EmsCheckboxes();
      
      String param = request.getParameter("chbox"+Integer.toString(i));
      if((param ==  null || param.equals("off")) && i!=5 && i!=6) 
      {
      chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("off");
      session.setAttribute("AGREE_TO_FLAG","NO");
      }
      else
      {
       chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("on"); 
      }
      list.add(chkbox);
      String agFlag =(String) session.getAttribute("AGREE_TO_FLAG");
      i=i+1;
      
    }
    course.setCheckBoxes(list);
    }
    /* for paramedic */
     if (course.getCrssCourseId() == 150013)
    {
      int j=62;
      int i=1;
    session.setAttribute("AGREE_TO_FLAG","YES");
    while(i <= j)
    {
      EmsCheckboxes chkbox = new EmsCheckboxes();
      
      String param = request.getParameter("chbox"+Integer.toString(i));
      if((param ==  null || param.equals("off")) && i!=5 && i!=6) 
      {
      chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("off");
      session.setAttribute("AGREE_TO_FLAG","NO");
      }
      else
      {
       chkbox.setBoxName("chbox"+Integer.toString(i));
      chkbox.setBoxValue("on"); 
      }
      list.add(chkbox);
      String agFlag =(String) session.getAttribute("AGREE_TO_FLAG");
      i=i+1;
      
    }
    course.setCheckBoxes(list);
    }
  }
}