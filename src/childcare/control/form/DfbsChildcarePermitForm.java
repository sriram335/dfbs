package childcare.control.form;


import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.upload.FormFile;
import childcare.to.*;
import hs.control.form.*;

import main.control.form.CompleteContactForm;

public class DfbsChildcarePermitForm extends CompleteContactForm
{
 private List permitFees;
  private String permitNumber;
  private String startDate;
  private String endDate;
  private String receivedDate;
  private String daycareName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String zip2;
  private String structureType;
  private String active;
  private String fdId;
  private String telephone;
  private String fax;
  private String email;
  private String daycareType;
  private String county;
  private String notes;
  private String contactPerson;
  private int ownerId;
  private double amount;
  private int receiptId;
  private String applicationKey;
  private String inspectorName;
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String fssaExpDate;
  private String fssaStatus;
  private String lastInspDate;
  private String lastInspVioDate;
  private String lastInspCompDate;
  private String idNumber;
  private String idType;
  private String idNew;
  private String recUpdateDate;
  public DfbsChildcarePermitForm()
  {
  
  }
      public String getIdNew()
  { 
   return idNew;
  }
public void setIdNew(String idNew)
  { 
  this.idNew = idNew;
  }
     public String getIdNumber()
  { 
   return idNumber;
  }
public void setIdNumber(String idNumber)
  { 
  this.idNumber = idNumber;
  }
   public String getIdType()
  { 
   return idType;
  }
public void setIdType(String idType)
  { 
  this.idType = idType;
  }
    public String getEndDate()
  { 
   return endDate;
  }
public void setEndDate(String endDate)
  { 
  this.endDate = endDate;
  }
public String getReceivedDate()
  { 
   return receivedDate;
  }
public void setReceivedDate(String receivedDate)
  { 
  this.receivedDate = receivedDate;
  }
public String getStartDate()
  { 
   return startDate;
  }
public void setStartDate(String startDate)
  { 
  this.startDate = startDate;
  }
public String getAddress1()
  { 
   return address1;
  }
public void setAddress1(String address1)
  { 
  this.address1 = address1;
  }
public String getAddress2()
  { 
   return address2;
  }
public void setAddress2(String address2)
  { 
  this.address2 = address2;
  }
public String getCity()
  { 
   return city;
  }
public void setCity(String city)
  { 
  this.city = city;
  }
public String getDaycareName()
  { 
   return daycareName;
  }
public void setDaycareName(String daycareName)
  { 
  this.daycareName = daycareName;
  }
public String getPermitNumber()
  { 
   return permitNumber;
  }
public void setPermitNumber(String permitNumber)
  { 
  this.permitNumber = permitNumber;
  }
public String getState()
  { 
   return state;
  }
public void setState(String state)
  { 
  this.state = state;
  }
  public String getActive()
  { 
   return active;
  }
public void setActive(String active)
  { 
  this.active = active;
  }
public String getContactPerson()
  { 
   return contactPerson;
  }
public void setContactPerson(String contactPerson)
  { 
  this.contactPerson = contactPerson;
  }
public String getCounty()
  { 
   return county;
  }
public void setCounty(String county)
  { 
  this.county = county;
  }
public String getDaycareType()
  { 
   return daycareType;
  }
public void setDaycareType(String daycareType)
  { 
  this.daycareType = daycareType;
  }
public String getEmail()
  { 
   return email;
  }
public void setEmail(String email)
  { 
  this.email = email;
  }
public String getFax()
  { 
   return fax;
  }
public void setFax(String fax)
  { 
  this.fax = fax;
  }
public String getFdId()
  { 
   return fdId;
  }
public void setFdId(String fdId)
  { 
  this.fdId = fdId;
  }
public String getNotes()
  { 
   return notes;
  }
public void setNotes(String notes)
  { 
  this.notes = notes;
  }
public String getStructureType()
  { 
   return structureType;
  }
public void setStructureType(String structureType)
  { 
  this.structureType = structureType;
  }
public String getTelephone()
  { 
   return telephone;
  }
public void setTelephone(String telephone)
  { 
  this.telephone = telephone;
  }
public String getZip()
  { 
   return zip;
  }
public void setZip(String zip)
  { 
  this.zip = zip;
  }
public String getZip2()
  { 
   return zip2;
  }
public void setZip2(String zip2)
  { 
  this.zip2 = zip2;
  }
  
  public String getApplicationKey()
  { 
   return applicationKey;
  }
public void setApplicationKey(String applicationKey)
  { 
  this.applicationKey = applicationKey;
  }
public String getInspectorName()
  { 
   return inspectorName;
  }
public void setInspectorName(String inspectorName)
  { 
  this.inspectorName = inspectorName;
  }
public double getAmount()
  { 
   return amount;
  }
public void setAmount(double amount)
  { 
  this.amount = amount;
  }

public int getOwnerId()
  { 
   return ownerId;
  }
public void setOwnerId(int ownerId)
  { 
  this.ownerId = ownerId;
  }
public int getReceiptId()
  { 
   return receiptId;
  }
public void setReceiptId(int receiptId)
  { 
  this.receiptId = receiptId;
  }
  public void setProperties(DfbsChildcarePermit childCare) 
  {
    this.setEndDate(childCare.getEndDateString());
    this.setReceivedDate(childCare.getReceivedDateString());
    this.setStartDate(childCare.getStartDateString());
    this.setActive(childCare.getActive());
    this.setAddress1(childCare.getAddress1());
    this.setAddress2(childCare.getAddress2());
    this.setApplicationKey(childCare.getApplicationKey());
    this.setCity(childCare.getCity());
    this.setContactPerson(childCare.getContactPerson());
    this.setCounty(childCare.getCounty());
    this.setDaycareName(childCare.getDaycareName());
    this.setDaycareType(childCare.getDaycareType());
    this.setEmail(childCare.getEmail());
    this.setFax(childCare.getFax());
    this.setFdId(childCare.getFdId());
    this.setInspectorName(childCare.getInspectorName());
    this.setNotes(childCare.getNotes());
    this.setPermitNumber(childCare.getPermitNumber());
    this.setState(childCare.getState());
    this.setStructureType(childCare.getStructureType());
    this.setTelephone(childCare.getTelephone());
    this.setZip(childCare.getZip());
    this.setZip2(childCare.getZip2());
    this.setAmount(childCare.getAmount());
    this.setOwnerId(childCare.getOwnerId());
    this.setReceiptId(childCare.getReceiptId());
    this.setRecUpdateDate(childCare.getRecUpdateDateString());
  }
  
   public DfbsChildcarePermit getChildcare() 
  {
    DfbsChildcarePermit childCare = new DfbsChildcarePermit();
    childCare.setEndDateString(getEndDate());
    childCare.setReceivedDateString(getReceivedDate());
    childCare.setStartDateString(getStartDate());
    childCare.setActive(getActive());
    childCare.setAddress1(getAddress1());
    childCare.setAddress2(getAddress2());
    childCare.setApplicationKey(getApplicationKey());
    childCare.setCity(getCity());
    childCare.setContactPerson(getContactPerson());
    childCare.setCounty(getCounty());
    childCare.setDaycareName(getDaycareName());
    childCare.setDaycareType(getDaycareType());
    childCare.setEmail(getEmail());
    childCare.setFax(getFax());
    childCare.setFdId(getFdId());
    childCare.setInspectorName(getInspectorName());
    childCare.setNotes(getNotes());
    childCare.setPermitNumber(getPermitNumber());
    childCare.setState(getState());
    childCare.setStructureType(getStructureType());
    childCare.setTelephone(getTelephone());
    childCare.setZip(getZip());
    childCare.setZip2(getZip2());
    childCare.setOwnerId(getOwnerId());
    childCare.setRecUpdateDateString(getRecUpdateDate());
    return childCare;
  }
  
  public void setUpdatedProperties(DfbsChildcarePermit updatedChildcare,DfbsChildcarePermit childCare) throws Exception
  { 
  
    childCare.setEndDate(updatedChildcare.getEndDate());
    childCare.setReceivedDate(updatedChildcare.getReceivedDate());
    childCare.setStartDate(updatedChildcare.getStartDate());
    childCare.setActive(updatedChildcare.getActive());
    childCare.setAddress1(updatedChildcare.getAddress1());
    childCare.setAddress2(updatedChildcare.getAddress2());
    childCare.setApplicationKey(updatedChildcare.getApplicationKey());
    childCare.setCity(updatedChildcare.getCity());
    childCare.setContactPerson(updatedChildcare.getContactPerson());
    childCare.setCounty(updatedChildcare.getCounty());
    childCare.setDaycareName(updatedChildcare.getDaycareName());
    childCare.setDaycareType(updatedChildcare.getDaycareType());
    childCare.setEmail(updatedChildcare.getEmail());
    childCare.setFax(updatedChildcare.getFax());
    childCare.setFdId(updatedChildcare.getFdId());
    childCare.setInspectorName(updatedChildcare.getInspectorName());
    childCare.setNotes(updatedChildcare.getNotes());
    childCare.setPermitNumber(updatedChildcare.getPermitNumber());
    childCare.setState(updatedChildcare.getState());
    childCare.setStructureType(updatedChildcare.getStructureType());
    childCare.setTelephone(updatedChildcare.getTelephone());
    childCare.setZip(updatedChildcare.getZip());
    childCare.setZip2(updatedChildcare.getZip2());
    childCare.setOwnerId(updatedChildcare.getOwnerId());
    childCare.setRenewStatus(updatedChildcare.getRenewStatus());
    childCare.setRecUpdateDate(updatedChildcare.getRecUpdateDate());
  } 
      public FormFile getCaseFile()
  { 
   return caseFile;
  }
public void setCaseFile( FormFile caseFile)
  { 
  this.caseFile = caseFile;
  } 
   public String getXlName()
  { 
   return xlName;
  }
public void setXlName(String xlName)
  { 
  this.xlName = xlName;
  } 
  public String getFssaExpDate()
  { 
   return fssaExpDate;
  }
public void setFssaExpDate(String fssaExpDate)
  { 
  this.fssaExpDate = fssaExpDate;
  }
public String getFssaStatus()
  { 
   return fssaStatus;
  }
public void setFssaStatus(String fssaStatus)
  { 
  this.fssaStatus = fssaStatus;
  }
  public String getLastInspCompDate()
  { 
   return lastInspCompDate;
  }
public void setLastInspCompDate(String lastInspCompDate)
  { 

this.lastInspCompDate = lastInspCompDate;
  }
public String getLastInspDate()
  { 
   return lastInspDate;
  }
public void setLastInspDate(String lastInspDate)
  { 
  this.lastInspDate =
lastInspDate;
  }
public String getLastInspVioDate()
  { 
   return lastInspVioDate;
  }
public void setLastInspVioDate(String lastInspVioDate)
  { 
  this.lastInspVioDate
= lastInspVioDate;
  }
  public String getRecUpdateDate()
  { 
   return recUpdateDate;
  }
public void setRecUpdateDate(String recUpdateDate)
  { 
  this.recUpdateDate
= recUpdateDate;
  }
}
