package aepermits.control.form;
import aepermits.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

import main.control.form.*;

public class DfbsEntrPermitForm extends CompleteContactForm
{
  
  private String permitNumber;
  private String ownerName;
  private String issueDate;
  private String applicationDate;
  private int permitYear;
  private String permitType; 
  private int ownerId;
  private String intendedOccupantLoad;
  private String eventTime;
  private String county;
  private String eventDate;
  private String actualLocation;
  private String facilityDescription;
  private String maximumOccupancy;
  private String feeExempt;
  private String applicationKey;
  private String eventName;
  private String contactPerson;
  private Map specialsMap;
  private String notes;
  private String noMap;
  private String status;
  private String emailTo;
  public DfbsEntrPermitForm()
  {
  
  }
  
   public void setProperties(DfbsEntrPermit permit) throws Exception
  {
    
    super.setProperties(permit);
   this.setPermitNumber(permit.getPermitNumber());
    this.setOwnerName(permit.getOwnerName());
    this.setIssueDate(permit.getIssueDateString());
    this.setApplicationDate(permit.getApplicationDateString());
    this.setPermitYear(permit.getPermitYear());
    this.setApplicationKey(permit.getApplicationKey()); 
    this.setPermitType(permit.getPermitType());
    this.setOwnerId(permit.getOwnerId());
    this.setContactPerson(permit.getContactPerson());
    this.setCounty(permit.getCounty());
    this.setIntendedOccupantLoad(permit.getIntendedOccupantLoad());
    this.setEventTime(permit.getEventTime());
    this.setEventName(permit.getEventName());
    this.setEventDate(permit.getEventDateString());
    this.setFacilityDescription(permit.getFacilityDescription());
    this.setActualLocation(permit.getActualLocation());
    this.setMaximumOccupancy(permit.getMaximumOccupancy());
    this.setFeeExempt(permit.getFeeExempt());
    this.setNotes(permit.getNotes());
    this.setStatus(permit.getStatus());
  }
  
  public DfbsEntrPermit getDfbsEntrPermit() throws Exception
  {
    
    DfbsEntrPermit permit = new DfbsEntrPermit (this.getHsCompleteContact());
    
   
    permit.setPermitNumber(this.getPermitNumber());
    permit.setOwnerName(this.getOwnerName());
    permit.setIssueDate(this.getIssueDate());
    permit.setApplicationDate(this.getApplicationDate());
    permit.setPermitYear(this.getPermitYear());
    permit.setApplicationKey(this.getApplicationKey());
    permit.setPermitType(this.getPermitType());
    permit.setOwnerId(this.getOwnerId());
    permit.setContactPerson(this.getContactPerson());
    permit.setCounty(this.getCounty());
    permit.setIntendedOccupantLoad(this.getIntendedOccupantLoad());
    permit.setEventTime(this.getEventTime());
    permit.setEventName(this.getEventName());
    permit.setEventDate(this.getEventDate());
    permit.setFacilityDescription(this.getFacilityDescription());
    permit.setActualLocation(this.getActualLocation());
    permit.setMaximumOccupancy(this.getMaximumOccupancy());
    permit.setFeeExempt(this.getFeeExempt());
    permit.setNotes(this.getNotes());
    permit.setStatus(this.getStatus());
    return permit;
  }
  

  public String getApplicationDate()
  {
    return applicationDate;
  }

  public void setApplicationDate(String applicationDate)
  {
    this.applicationDate = applicationDate;
  }

 

  public String getIssueDate()
  {
    return issueDate;
  }

  public void setIssueDate(String issueDate)
  {
    this.issueDate = issueDate;
  }
 public String getEventDate()
  {
    return eventDate;
  }

  public void setEventDate(String eventDate)
  {
    this.eventDate = eventDate;
  }
  
  public String getOwnerName()
  {
    return ownerName;
  }

  public void setOwnerName(String ownerName)
  {
    this.ownerName = ownerName;
  }

  public String getPermitNumber()
  {
    return permitNumber;
  }

  public void setPermitNumber(String permitNumber)
  {
    this.permitNumber = permitNumber;
  }

  public int getPermitYear()
  {
    return permitYear;
  }

  public void setPermitYear(int permitYear)
  {
    this.permitYear = permitYear;
  }

  public String getPermitType()
  {
    return permitType;
  }

  public void setPermitType(String permitType)
  {
    this.permitType = permitType;
  }
  
  public int getOwnerId()
  {
    return ownerId;
  }

  public void setOwnerId(int ownerId)
  {
    this.ownerId = ownerId;
  }

  public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }

 public void setCounty(String county)
  {
    this.county = county; 
  } 
   public String getCounty()
  {
    return county;
  }

   public String getContactPerson()
  {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson)
  {
    this.contactPerson = contactPerson;
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
   public String getIntendedOccupantLoad()
  {
    return intendedOccupantLoad;
  }

  public void setIntendedOccupantLoad(String intendedOccupantLoad)
  {
    this.intendedOccupantLoad = intendedOccupantLoad;
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
   public String getActualLocation()
  {
    return actualLocation;
  }

  public void setActualLocation(String actualLocation)
  {
    this.actualLocation = actualLocation;
  }
   public String getFacilityDescription()
  {
    return facilityDescription;
  }

  public void setFacilityDescription(String facilityDescription)
  {
    this.facilityDescription = facilityDescription;
  }
  public Map getSpecialsMap()
  {
    return specialsMap;
  }

  public void setSpecialsMap(Map specialsMap)
  {
    this.specialsMap = specialsMap;
  }
   public String getNotes()
  {
    return notes;
  }

  public void setNotes(String notes)
  {
    this.notes = notes;
  }
   public String getNoMap()
  {
    return noMap;
  }

  public void setNoMap(String noMap)
  {
    this.noMap = noMap;
  }
   public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }
   public String getEmailTo()
  {
    return emailTo;
  }

  public void setEmailTo(String emailTo)
  {
    this.emailTo = emailTo;
  }
}
