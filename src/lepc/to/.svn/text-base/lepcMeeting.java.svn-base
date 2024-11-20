package lepc.to;
import java.io.Serializable;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class lepcMeeting implements Serializable{
    public lepcMeeting() {
        
    }
  private int meetingId;
  private Date meetingDate;
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
  public Date getMeetingDate()
    {    return meetingDate;  }
  public void setMeetingDate(Date meetingDate)
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
  public void setMeetingDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  meetingDate = formatter.parse(dateParam);
  } catch (Exception e)
  {meetingDate = null; }
  }
  public String getMeetingDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(meetingDate == null)
  { return ""; }
  else { return formatter.format(meetingDate); }
  }
  public int getLepcId()
    {    return lepcId;  }
  public void setLepcId(int lepcId)
    {   this.lepcId = lepcId;  }
}
