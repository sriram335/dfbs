package lepc.control.action;

import aepermits.to.DfbsEntrPermit;

import lepc.control.form.*;
import lepc.to.*;
import lepc.data.*;
import main.data.*;
import main.to.*;
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

import org.apache.struts.upload.FormFile;

import otherUsers.data.otherUsersSQL;

import otherUsers.to.otherUsers;

public class lepcMeetingAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcMeetingForm meetingForm = (lepcMeetingForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
      fiscalReportDAO fDAO = (fiscalReportDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_FISCAL_REPORT);
      lepcExerciseDAO eDAO = (lepcExerciseDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_EXERCISE);
      lepcAgencyDAO aDAO = (lepcAgencyDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_AGENCY);
      lepcMeetingDAO mDAO = (lepcMeetingDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_MEETING);
      String method = request.getParameter("method");
      HttpSession session = request.getSession();
      ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
      ApplicationContacts contacts = sDAO.setContacts("LEPC_CONTACT1","LEPC_CONTACT2");      
      otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");    
      if (method == null ||((otherUser ==null || otherUser.getUserLoginId().length() < 3 )&&
      (security ==null || security.getUserId().length() < 3))) 
      {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      else if (method.equals("meetingList")) 
      { String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
        List meetingList =mDAO.selectMeetingList(Integer.parseInt(lepcId));
        request.setAttribute("LEPC_MEETING_LIST",new HsListWrapper(meetingList));
      return mapping.findForward("meetingList"); 
      } 
      else if (method.equals("addMeeting")) 
      { String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
       meetingForm.setLepcId(Integer.parseInt(lepcId));
       if (otherUser !=null)
       {
       meetingForm.setMeetingContact(otherUser.getUserFirstName()+" "+otherUser.getUserLastName());
        meetingForm.setMeetingContactEmail(otherUser.getUserLoginId());
        meetingForm.setMeetingContactPhone(otherUser.getUserPhone());
       }
        return mapping.findForward("addMeeting"); 
      } 
      else if (method.equals("updateMeeting")) 
      { String meetingId =request.getParameter("meetingId");
        lepcMeeting meeting = mDAO.selectLepcMeeting(Integer.parseInt(meetingId));
        meetingForm.setProperties(meeting);
        session.setAttribute("LEPC_MEETING",meeting); 
     return mapping.findForward("updateMeeting"); 
      } 
      else if (method.equals("copyMeeting")) 
      { String meetingId =request.getParameter("meetingId");
        lepcMeeting meeting = mDAO.selectLepcMeeting(Integer.parseInt(meetingId));
        meetingForm.setProperties(meeting);
        meetingForm.setMeetingDate(null);
        meetingForm.setMeetingTime(null);
        meeting.setMeetingId(0);
        session.setAttribute("LEPC_MEETING",meeting); 
        return mapping.findForward("addMeeting"); 
      } 
      else if (method.equals("sendEmail")) 
      {  String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
        lepcRoster roster= rDAO.selectRosterByLepc(Integer.parseInt(lepcId));
        List repList= rDAO.selectRepPersons(roster.getRosterId()); 
        List repListAdmin= rDAO.selectRepPersonsAdmin(roster.getRosterId());
        Iterator i = repList.iterator();
        while(i.hasNext())
        {
          lepcRosterRep person = (lepcRosterRep) i.next();
          if (person.getPersonEmail()!=null &&person.getPersonEmail().length()>6)
          {
          sendEmailMeetingUpdate(otherUser,person.getPersonEmail());
          }
        }
        Iterator j = repListAdmin.iterator();
        while(j.hasNext())
        {
          lepcRosterRep person2 = (lepcRosterRep) j.next();
          if (person2.getPersonEmail().length()>6)
          {
          sendEmailMeetingUpdate(otherUser,person2.getPersonEmail());
          }
        }
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/meeting.do?method=meetingList");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }     
      else if (method.equals("saveMeeting")) 
      { lepcMeeting meeting = meetingForm.getLepcMeeting();
         if (meeting.getMeetingId()==0) {
          mDAO.insertLepcMeeting(meeting);
        }
        else {
          mDAO.updateLepcMeeting(meeting);
        }
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/meeting.do?method=meetingList");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      } 
    throw new Exception("LEPC Meeting Action"); 
    }
    
    catch (Exception e)
    {
    saveError(request,e);
    request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
    return mapping.findForward("error");
    
    }
    }
 
  private static void sendEmailMeetingUpdate(otherUsers otherUser,String personEmail) throws Exception
  {
         try {
           HsMailer mail = new HsMailer();
           
           String[] emailTo = {personEmail}; 
           // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           String[] bccTo = {otherUser.getUserLoginId()};
           StringBuffer sb = new StringBuffer("\r\n LEPC meeting schedule for this year is updated.");
             sb.append("\n \n Please use this link to get upto date calander information at https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=lepc_meeting_schedule&p_county="+otherUser.getUserCounty());
             sb.append("\n \n Please contact "+otherUser.getUserLoginId()+" if you have questions or concerns.");
            StringBuffer sub = new StringBuffer();
           sub.append("IERC LEPC meeting Calandar updated ");
            mail.createMail(otherUser.getUserLoginId(),emailTo,null,sub.toString(),sb.toString());
           mail.sendAndClose();
           } 
           catch(Exception e) 
           {
              HsMailer mail1 = new HsMailer();
           mail1.createMail("ierc_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
           mail1.sendAndClose();
           }
  }
}
