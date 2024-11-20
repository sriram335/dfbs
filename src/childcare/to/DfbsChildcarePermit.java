package childcare.to;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import hs.to.*;
public class DfbsChildcarePermit  implements Serializable
{ private List permitFees;
  private String permitNumber;
  private Date startDate;
  private Date endDate;
  private Date receivedDate;
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
  private List fileList;
  private List inspections;
  private String inspectorName;
  private String fssaStatus;
  private Date fssaExpDate;
  private Date lastInspDate;
  private Date lastInspVioDate;
  private Date lastInspCompDate;
  private String substrPermitNumber;
  private String uploadStatus;
  private String renewStatus;
  private Date recUpdateDate;
  public DfbsChildcarePermit()
  {
   
  }
  public String getUploadStatus()
  { 
   return uploadStatus;
  }
public void setUploadStatus(String uploadStatus)
  { 
  this.uploadStatus = uploadStatus;
  }
  public String getRenewStatus()
  { 
   return renewStatus;
  }
public void setRenewStatus(String renewStatus)
  { 
  this.renewStatus = renewStatus;
  }
 public String getSubstrPermitNumber()
  { 
   return permitNumber.substring(0,3);
  }
  public Date getEndDate()
  { 
   return endDate;
  }
public void setEndDate(Date endDate)
  { 
  this.endDate = endDate;
  }
public Date getReceivedDate()
  { 
   return receivedDate;
  }
public void setReceivedDate(Date receivedDate)
  { 
  this.receivedDate = receivedDate;
  }
public Date getStartDate()
  { 
   return startDate;
  }
public void setStartDate(Date startDate)
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
  
  
 
 
   public List getFileList()
  {
    if(fileList == null) 
    {
      fileList = new ArrayList();
    } 
    return fileList;
  }

  public void setFileList(List fileList)
  {
    this.fileList = fileList;
  }
   public List getInspections()
  {
    if(inspections == null) 
    {
      inspections = new ArrayList();
    } 
    return inspections;
  }

  public void setInspections(List inspections)
  {
    this.inspections = inspections;
  }
 public List getPermitFees()
  {
    if(permitFees == null) 
    {
      fileList = new ArrayList();
    } 
    return permitFees;
  }

  public void setPermitFees(List permitFees)
  {
    this.permitFees = permitFees;
  }
 
  public void setEndDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
endDate = formatter.parse(dateParam);
} catch (Exception e)
{endDate = null; }
}
public String getEndDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(endDate == null)
{ return ""; }
else { return formatter.format(endDate); }
}
public void setReceivedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
receivedDate = formatter.parse(dateParam);
} catch (Exception e)
{receivedDate = null; }
}
public String getReceivedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(receivedDate == null)
{ return ""; }
else { return formatter.format(receivedDate); }
}
public void setStartDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
startDate = formatter.parse(dateParam);
} catch (Exception e)
{startDate = null; }
}
public String getStartDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(startDate == null)
{ return ""; }
else { return formatter.format(startDate); }
}
 public boolean isNew() 
  {
    return (getPermitNumber() == null || getPermitNumber().substring(1,3).equals("NEW")) ? true : false;
  }


public Date getFssaExpDate()
  { 
   return fssaExpDate;
  }
public void setFssaExpDate(Date fssaExpDate)
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
  public void setFssaExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
fssaExpDate = formatter.parse(dateParam);
} catch (Exception e)
{fssaExpDate = null; }
}
public String getFssaExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(fssaExpDate == null)
{ return ""; }
else { return formatter.format(fssaExpDate); }
}
public Date getLastInspCompDate()
  { 
   return lastInspCompDate;
  }
public void setLastInspCompDate(Date lastInspCompDate)
  { 

this.lastInspCompDate = lastInspCompDate;
  }
public Date getLastInspDate()
  { 
   return lastInspDate;
  }
public void setLastInspDate(Date lastInspDate)
  { 
  this.lastInspDate =
lastInspDate;
  }
public Date getLastInspVioDate()
  { 
   return lastInspVioDate;
  }
public void setLastInspVioDate(Date lastInspVioDate)
  { 
  this.lastInspVioDate
= lastInspVioDate;
  }
  public void setLastInspCompDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
lastInspCompDate = formatter.parse(dateParam);
} catch (Exception e)
{lastInspCompDate = null; }
}
public String getLastInspCompDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(lastInspCompDate == null)
{ return ""; }
else { return formatter.format(lastInspCompDate); }
}
public void setLastInspDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
lastInspDate = formatter.parse(dateParam);
} catch (Exception e)
{lastInspDate = null; }
}
public String getLastInspDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(lastInspDate == null)
{ return ""; }
else { return formatter.format(lastInspDate); }
}
public void setLastInspVioDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
lastInspVioDate = formatter.parse(dateParam);
} catch (Exception e)
{lastInspVioDate = null; }
}
public String getLastInspVioDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(lastInspVioDate == null)
{ return ""; }
else { return formatter.format(lastInspVioDate); }
}

 public void setRecUpdateDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
recUpdateDate = formatter.parse(dateParam);
} catch (Exception e)
{endDate = null; }
}
public String getRecUpdateDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(recUpdateDate == null)
{ return ""; }
else { return formatter.format(recUpdateDate); }
}
public Date getRecUpdateDate()
  { 
   return recUpdateDate;
  }
public void setRecUpdateDate(Date recUpdateDate)
  { 
  this.recUpdateDate
= recUpdateDate;
  }
}