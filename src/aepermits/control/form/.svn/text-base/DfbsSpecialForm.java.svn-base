package aepermits.control.form;
import aepermits.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

import main.control.form.*;

import org.apache.struts.action.*;
public class DfbsSpecialForm extends CompleteContactForm
{

  private int specialId;
  private String applicationDate;
  private String eventName;
  private String eventDate;
  private String eventTime;
  private String maximumOccupancy;
  private String comments;
  private String permitNumber;
  private String issueDate;
  private String expirationDate;
  private String feeExempt;
  private String contactPerson;
  private String contactPhone;
  private String contactFax;
  private String contactEmail;
  private String onlineStatus;
  private String emailTo;
  private String permitType;
  public DfbsSpecialForm()
  {
  }
  public void setProperties(DfbsEntrSpecial special) throws Exception
  {
    
    this.setSpecialId(special.getSpecialId());    
    this.setPermitNumber(special.getPermitNumber());
    this.setIssueDate(special.getIssueDateString());
    this.setApplicationDate(special.getApplicationDateString());
    this.setExpirationDate(special.getExpirationDateString());
    this.setEventTime(special.getEventTime());
    this.setEventName(special.getEventName());
    this.setEventDate(special.getEventDateString());
    this.setMaximumOccupancy(special.getMaximumOccupancy());
    this.setFeeExempt(special.getFeeExempt());
    this.setComments(special.getComments());
    this.setContactPerson(special.getContactPerson());
    this.setContactPhone(special.getContactPhone());
    this.setContactFax(special.getContactFax());
    this.setContactEmail(special.getContactEmail());
    this.setOnlineStatus(special.getOnlineStatus());
    this.setPermitType(special.getPermitType());
  }
  
  public DfbsEntrSpecial getDfbsEntrSpecial() throws Exception
  {
    
    DfbsEntrSpecial special = new DfbsEntrSpecial ();
    special.setPermitNumber(this.getPermitNumber());
    special.setIssueDate(this.getIssueDate());
    special.setApplicationDate(this.getApplicationDate());
    special.setEventTime(this.getEventTime());
    special.setEventName(this.getEventName());
    special.setEventDate(this.getEventDate());
    special.setComments(this.getComments());
    special.setExpirationDate(this.getExpirationDate());
    special.setMaximumOccupancy(this.getMaximumOccupancy());
    special.setFeeExempt(this.getFeeExempt());
    special.setSpecialId(this.getSpecialId());
    special.setContactPerson(this.getContactPerson());
    special.setContactPhone(this.getContactPhone());
    special.setContactFax(this.getContactFax());
    special.setContactEmail(this.getContactEmail());
    special.setOnlineStatus(this.getOnlineStatus());
    special.setPermitType(this.getPermitType());
    return special;
  }
   public String getApplicationDate()
  {
    return applicationDate;
  }

  public void setApplicationDate(String applicationDate)
  {
    this.applicationDate = applicationDate;
  }

  public int getSpecialId()
  {
    return specialId;
  }

  public void setSpecialId(int specialId)
  {
    this.specialId = specialId;
  }

  public String getIssueDate()
  {
    return issueDate;
  }

  public void setIssueDate(String issueDate)
  {
    this.issueDate = issueDate;
  }
  public String getExpirationDate()
  {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate)
  {
    this.expirationDate = expirationDate;
  }
 public String getEventDate()
  {
    return eventDate;
  }

  public void setEventDate(String eventDate)
  {
    this.eventDate = eventDate;
  }
  
 public String getPermitNumber()
  {
    return permitNumber;
  }

  public void setPermitNumber(String permitNumber)
  {
    this.permitNumber = permitNumber;
  }

 
  public String getEventTime()
  {
    return eventTime;
  }

  public void setEventTime(String eventTime)
  {
    this.eventTime = eventTime; 
  } 
   public String getEventName()
  {
    return eventName;
  }

  public void setEventName(String eventName)
  {
    this.eventName = eventName; 
  } 
   public String getMaximumOccupancy()
  {
    return maximumOccupancy;
  }

  public void setMaximumOccupancy(String maximumOccupancy)
  {
    this.maximumOccupancy = maximumOccupancy;
  }
   public String getFeeExempt()
  {
    return feeExempt;
  }

  public void setFeeExempt(String feeExempt)
  {
    this.feeExempt = feeExempt;
  }
  public String getComments()
  {
    return comments;
  }
 public void setComments(String comments)
  {
    this.comments = comments;
  }
  public void setContactPerson(String contactPerson)
  {
    this.contactPerson = contactPerson;
  }
  public String getContactPerson()
  {
    return contactPerson;
  }
 public void setContactPhone(String contactPhone)
  {
    this.contactPhone = contactPhone;
  }
  public String getContactPhone()
  {
    return contactPhone;
  }
 public void setContactFax(String contactFax)
  {
    this.contactFax = contactFax;
  }
  public String getContactFax()
  {
    return contactFax;
  }
  public void setContactEmail(String contactEmail)
  {
    this.contactEmail = contactEmail;
  }
  public String getContactEmail()
  {
    return contactEmail;
  }
   public String getOnlineStatus()
  {
    return onlineStatus;
  }

  public void setOnlineStatus(String onlineStatus)
  {
    this.onlineStatus = onlineStatus;
  }
   public String getEmailTo()
  {
    return emailTo;
  }

  public void setEmailTo(String emailTo)
  {
    this.emailTo = emailTo;
  }
   public String getPermitType()
  {
    return permitType;
  }

  public void setPermitType(String permitType)
  {
    this.permitType = permitType;
  }
}
