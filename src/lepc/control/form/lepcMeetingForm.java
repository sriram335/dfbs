package lepc.control.form;

import fireworks.to.DfbsFireworksPermit;

import lepc.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;
public class lepcMeetingForm  extends ActionForm{
    public lepcMeetingForm() {
       
    }
  private int meetingId;
  private String meetingDate;
  private String meetingTime;
  private String meetingAddress1;
  private String meetingAddress2;
  private String meetingCity;
  private String meetingState;
  private String meetingZip;
  private String meetingContact;
  private String meetingContactPhone;
  private String meetingContactEmail;
  private int lepcId;
  public int getLepcId()
    {    return lepcId;  }
  public void setLepcId(int lepcId)
    {   this.lepcId = lepcId;  }
  public String getMeetingDate()
    {    return meetingDate;  }
  public void setMeetingDate(String meetingDate)
    {   this.meetingDate = meetingDate;  }
  public int getMeetingId()
    {    return meetingId;  }
  public void setMeetingId(int meetingId)
    {   this.meetingId = meetingId;  }
  public String getMeetingAddress1()
    {    return meetingAddress1;  }
  public void setMeetingAddress1(String meetingAddress1)
    {   this.meetingAddress1 = meetingAddress1;  }
  public String getMeetingAddress2()
    {    return meetingAddress2;  }
  public void setMeetingAddress2(String meetingAddress2)
    {   this.meetingAddress2 = meetingAddress2;  }
  public String getMeetingCity()
    {    return meetingCity;  }
  public void setMeetingCity(String meetingCity)
    {   this.meetingCity = meetingCity;  }
  public String getMeetingContact()
    {    return meetingContact;  }
  public void setMeetingContact(String meetingContact)
    {   this.meetingContact = meetingContact;  }
  public String getMeetingContactEmail()
    {    return meetingContactEmail;  }
  public void setMeetingContactEmail(String meetingContactEmail)
    {   this.meetingContactEmail = meetingContactEmail;  }
  public String getMeetingContactPhone()
    {    return meetingContactPhone;  }
  public void setMeetingContactPhone(String meetingContactPhone)
    {   this.meetingContactPhone = meetingContactPhone;  }
  public String getMeetingState()
    {    return meetingState;  }
  public void setMeetingState(String meetingState)
    {   this.meetingState = meetingState;  }
  public String getMeetingTime()
    {    return meetingTime;  }
  public void setMeetingTime(String meetingTime)
    {   this.meetingTime = meetingTime;  }
  public String getMeetingZip()
    {    return meetingZip;  }
  public void setMeetingZip(String meetingZip)
    {   this.meetingZip = meetingZip;  }
  
  public lepcMeeting getLepcMeeting() throws Exception
   {
     
     lepcMeeting meeting = new lepcMeeting ();
     meeting.setMeetingDateString(this.getMeetingDate());
     meeting.setMeetingAddress1(this.getMeetingAddress1());
     meeting.setMeetingAddress2(this.getMeetingAddress2());
     meeting.setMeetingCity(this.getMeetingCity());
     meeting.setMeetingContact(this.getMeetingContact());
     meeting.setMeetingContactEmail(this.getMeetingContactEmail());
     meeting.setMeetingContactPhone(this.getMeetingContactPhone());
     meeting.setMeetingState(this.getMeetingState());
     meeting.setMeetingTime(this.getMeetingTime());
     meeting.setMeetingZip(this.getMeetingZip());
     meeting.setMeetingId(this.getMeetingId());
     meeting.setLepcId(this.getLepcId());
  return meeting;
   }
    public void setProperties(lepcMeeting meeting) throws Exception
   { 
     this.setMeetingDate(meeting.getMeetingDateString());
     this.setMeetingAddress1(meeting.getMeetingAddress1());
     this.setMeetingAddress2(meeting.getMeetingAddress2());
     this.setMeetingCity(meeting.getMeetingCity());
     this.setMeetingContact(meeting.getMeetingContact());
     this.setMeetingContactEmail(meeting.getMeetingContactEmail());
     this.setMeetingContactPhone(meeting.getMeetingContactPhone());
     this.setMeetingState(meeting.getMeetingState());
     this.setMeetingTime(meeting.getMeetingTime());
     this.setMeetingZip(meeting.getMeetingZip());
     this.setMeetingId(meeting.getMeetingId());
     this.setLepcId(meeting.getLepcId());
   }
  public void setUpdatedProperties(lepcMeeting updatedMeeting,lepcMeeting meeting ) throws Exception
  {
    meeting.setMeetingDate(updatedMeeting.getMeetingDate());
    meeting.setMeetingAddress1(updatedMeeting.getMeetingAddress1());
    meeting.setMeetingAddress2(updatedMeeting.getMeetingAddress2());
    meeting.setMeetingCity(updatedMeeting.getMeetingCity());
    meeting.setMeetingContact(updatedMeeting.getMeetingContact());
    meeting.setMeetingContactEmail(updatedMeeting.getMeetingContactEmail());
    meeting.setMeetingContactPhone(updatedMeeting.getMeetingContactPhone());
    meeting.setMeetingState(updatedMeeting.getMeetingState());
    meeting.setMeetingTime(updatedMeeting.getMeetingTime());
    meeting.setMeetingZip(updatedMeeting.getMeetingZip());
    meeting.setMeetingId(updatedMeeting.getMeetingId());
    meeting.setLepcId(updatedMeeting.getLepcId());
  }
}
