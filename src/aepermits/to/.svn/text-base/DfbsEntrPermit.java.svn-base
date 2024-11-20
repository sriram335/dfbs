package aepermits.to;

import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

public class DfbsEntrPermit extends HsCompleteContact implements Serializable
{ private List permitFees;
  private List specials;
  private Map specialMap;
  private int specialsCount;
  private String permitNumber;
  private String ownerName;
  private Date issueDate;
  private Date applicationDate;
  private int permitYear;
  private int currentYear;
  private String actualLocation;
  private String facilityDescription;
  private String maximumOccupancy;
  private Date eventDate;
  private String eventTime;
  private String eventName;
  private String intendedOccupantLoad;
  private String feeExempt;
  private String county;
  private String permitType;
  private String contactPerson;
  private int ownerId;
  private double amount;
  private double permitTotalAmount;
  private int receiptId;
  private DfbsOwner owner;
  private String applicationKey;
  private String countyCode;
  private String renewStatus;
  private double permitSpecialAmount;
  private int specialCounter;
  private String notes;
  private List fileList;
  private String noMap;
   private List inspections;
   private double addPlansFee; 
   private String inspectorName;
   private String status;
   private Date expDate;
  public DfbsEntrPermit()
  {
   
  }
  
   public DfbsEntrPermit(HsCompleteContact cc)
  {
    this.setId(cc.getId());
    this.setFirstname(cc.getFirstname());
    this.setLastname(cc.getLastname());
    this.setTitle(cc.getTitle());
    this.setAddressId(cc.getAddressId());
    this.setStreet1(cc.getStreet1());
    this.setStreet2(cc.getStreet2());
    this.setCity(cc.getCity());
    this.setState(cc.getState());
    this.setZip(cc.getZip());
    this.setCounty(cc.getCounty());
    this.setPhoneId(cc.getPhoneId());
    this.setPhoneNumber(cc.getPhoneNumber());
    this.setFaxId(cc.getFaxId());
    this.setFaxNumber(cc.getFaxNumber());
    this.setEmailId(cc.getEmailId());
    this.setEmailAddress(cc.getEmailAddress());
    this.setOrgName(cc.getOrgName());
    this.setActive(cc.isActive());  
   
  }
  

  public Date getApplicationDate()
  {
    return applicationDate;
  }

  public void setApplicationDate(Date applicationDate)
  {
    this.applicationDate = applicationDate;
  }
 public Date getEventDate()
  {
    return eventDate;
  }

  public void setEventDate(Date eventDate)
  {
    this.eventDate = eventDate;
  }
  
public Date getExpDate()
  {
    return expDate;
  }

  public void setExpDate(Date expDate)
  {
    this.expDate = expDate;
  }
  
 
  

  public Date getIssueDate()
  {
    return issueDate;
  }

  public void setIssueDate(Date issueDate)
  {
    this.issueDate = issueDate;
  }
  
   public void setIssueDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      issueDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      issueDate = null;
    }
  }
   public String getIssueDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(issueDate == null) 
    {
      return "";
    } else {
      return formatter.format(issueDate);
    }
  }

 

  public String getPermitNumber()
  {
    return permitNumber;
  }

  public void setPermitNumber(String permitNumber)
  {
    this.permitNumber = permitNumber;
  }
  public String getPermitType()
  {
    return permitType;
  }

  public void setPermitType(String permitType)
  {
    this.permitType = permitType;
  }
  public String getPermitTypeString() throws Exception
  {
     if(permitType.equals("A")){
      return "Annual";
    } 
    else if(permitType.equals("S")) 
    {
      return  "Special";
    } 
   
    return "";
   
  }
 public String getEventName()
  {
    return eventName;
  }

  public void setEventName(String eventName)
  {
    this.eventName = eventName;
   }
   public String getEventTime()
  {
    return eventTime;
  }

  public void setEventTime(String eventTime)
  {
    this.eventTime = eventTime;
  }
    public String getMaximumOccupancy()
  {
    return maximumOccupancy;
  }

  

  public void setMaximumOccupancy(String maximumOccupancy)
  {
    this.maximumOccupancy = maximumOccupancy;
  }
   public String getIntendedOccupantLoad()
  {
    return intendedOccupantLoad;
  }

  public void setIntendedOccupantLoad(String intendedOccupantLoad)
  {
    this.intendedOccupantLoad = intendedOccupantLoad;
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
  public int getPermitYear()
  {
    return permitYear;
  }

  public void setPermitYear(int permitYear)
  {
    this.permitYear = permitYear;
  }

  public String getOwnerName()
  {
    return (ownerName == null) ? "" : ownerName  ;
  }

  public void setOwnerName(String ownerName)
  {
    this.ownerName = ownerName;
  }
  public String getStatus() 
  {/* if(permitYear == 0 && getApplicationDateString().substring(6).equals(Integer.toString(currentYear))) {
      return "PENDING";
    } 
    else if(permitYear < currentYear || getApplicationDateString().substring(6).equals(Integer.toString(currentYear-1))) 
    {
      return  "EXPIRED";
    } 
    else if(permitYear >= currentYear)
    {
      return  "VALID";
    }
    return "INVALID"; */
    return status;
  }
 
  
   public void setStatus(String status)
  {
    this.status = status;
  }
  
  
  public String createPermitNumber(int id) throws Exception
  {
    StringBuffer sb = new StringBuffer("AE");
    sb.append(getCountyCode());
    sb.append(id);
    setPermitNumber(sb.toString());
    return getPermitNumber();
    
  }

  public int getOwnerId()
  {
    return ownerId;
  }

  public void setOwnerId(int ownerId)
  {
    this.ownerId = ownerId;
  }
  
  public boolean isNew() 
  {
    return (this.getPermitNumber() == null) ? true : false;
  }

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  
 
  public String getAmountString()
  {
    return NumberFormat.getCurrencyInstance().format(getAmount());
    
  }
 
  public void setAmount(String newAmount) throws Exception
  {
    if (newAmount == null) 
    {
      amount=0;
      return;
    }
  
    try {
    amount= NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       amount= NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         amount=0;
       }
    }
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
   public String getCountyCode()
  {
    return countyCode;
  }

  public void setCountyCode(String countyCode)
  {
    this.countyCode = countyCode;
  }
  public String getTransactionDescription() throws Exception
  {
    StringBuffer sb = new StringBuffer();
    sb.append(this.getPermitNumber());
    sb.append("-");
    sb.append("confirmation");
    sb.append("-");
    sb.append(this.getReceiptId());
    
    return sb.toString();
  }

  public int getReceiptId()
  {
    return receiptId;
  }

  public void setReceiptId(int receiptId)
  {
    this.receiptId = receiptId;
  }
  
   public DfbsOwner getOwner()
  {
    return owner;
  }

  public void setOwner(DfbsOwner owner)
  {
    this.owner = owner;
  }

  

  public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }

 
  
  public void setEventDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      eventDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      eventDate = null;
    }
  }
   public String getEventDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(eventDate == null) 
    {
      return "";
    } else {
      return formatter.format(eventDate);
    }
  } 
 
  public String getFeeExemptString() throws Exception
  {
     if(feeExempt.equals("E")){
      return "Yes";
    } 
    else if(feeExempt.equals("N")) 
    {
      return  "No";
    } 
   
    return "";
   
  }
   public int getSpecialsCount () 
  {
    return (specials == null) ? 0 : specials.size();
  }

  public List getSpecials()
  {
    if(specials == null) 
    {
     specials = new ArrayList();
    } 
    return specials;
  }

  public void setSpecials(List specials)
  {
    this.specials = specials;
  }
   public Map getSpecialMap()
  {
    if(specialMap == null) 
    {
      specialMap = new HashMap();
    }
    return specialMap;
  }

  public void setSpecialMap(Map specialMap)
  {
    this.specialMap = specialMap;
  }
  public int getSpecialMapCount()
  {
    return (this.specialMap == null) ? 0 : specialMap.size();
  }

  public void addSpecial(DfbsEntrSpecial special) throws Exception
  {
    if(special == null) return;
    
    special.setPermitNumber(this.getPermitNumber());
    if(special.isNew()) 
    { 
      StringBuffer sb = new StringBuffer(special.getContactPhone());
      sb.append(getSpecialCounter() + 1);
      Map map = this.getSpecialMap();
      special.setApplicationKey(sb.toString());
      map.put(sb.toString(),special);
      setSpecialCounter(getSpecialCounter() + 1);
    } 
    else 
    { StringBuffer sb = new StringBuffer("");
      Map map = this.getSpecialMap();
      sb.append(special.getSpecialId());
      special.setApplicationKey(sb.toString());
      map.put(sb.toString(),special);
    }
    this.addCount(special);
   
  }
  public DfbsEntrSpecial getSpecial(String key)
  {
    
    if(key == null || specialMap == null ) {
       
      return new DfbsEntrSpecial();
    }
    DfbsEntrSpecial special = (DfbsEntrSpecial) specialMap.get(key);
    return (special == null) ? new DfbsEntrSpecial() : special;
  }
  
  
  public void removeSpecial(String key) throws Exception
  { if(key == null || specialMap == null ) return;
    DfbsEntrSpecial special = (DfbsEntrSpecial) specialMap.get(key);
    this.removeCount(special);
    specialMap.remove(key);
  }
  private void addCount(DfbsEntrSpecial special) throws Exception
  {
   
      this.specialsCount++; 
      
  }
  
  private void removeCount(DfbsEntrSpecial special) throws Exception
  {
    
      this.specialsCount--; 
      
  }
   

  public void setSpecialsCount(int specialsCount)
  {
    this.specialsCount = specialsCount;
  }
  public String getRenewStatus()
  {
    return renewStatus;
  }

  public void setRenewStatus(String renewStatus)
  {
    this.renewStatus = renewStatus;
  }
  
  public double getPermitTotalAmount()
  {
    return permitTotalAmount;
  }

  public void setPermitTotalAmount(double permitTotalAmount)
  {
    this.permitTotalAmount = permitTotalAmount;
  }
  
 
  public String getPermitTotalAmountString()
  {
    return NumberFormat.getCurrencyInstance().format(getPermitTotalAmount());
    
  }
 
  public void setPermitTotalAmount(String newPermitTotalAmount) throws Exception
  {
    if (newPermitTotalAmount == null) 
    {
      permitTotalAmount=0;
      return;
    }
  
    try {
    permitTotalAmount= NumberFormat.getNumberInstance().parse(newPermitTotalAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       permitTotalAmount= NumberFormat.getCurrencyInstance().parse(newPermitTotalAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         permitTotalAmount=0;
       }
    }
  }
public double getPermitSpecialAmount()
  {
    return permitSpecialAmount;
  }

  public void setPermitSpecialAmount(double permitSpecialAmount)
  {
    this.permitSpecialAmount = permitSpecialAmount;
  }
  
 
  public String getPermitSpecialAmountString()
  {
    return NumberFormat.getCurrencyInstance().format(getPermitSpecialAmount());
    
  }
 
  public void setPermitSpecialAmount(String newPermitSpecialAmount) throws Exception
  {
    if (newPermitSpecialAmount == null) 
    {
      permitSpecialAmount=0;
      return;
    }
  
    try {
    permitSpecialAmount= NumberFormat.getNumberInstance().parse(newPermitSpecialAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       permitSpecialAmount= NumberFormat.getCurrencyInstance().parse(newPermitSpecialAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         permitSpecialAmount=0;
       }
    }
  }
   public int getSpecialCounter()
  {
    return specialCounter;
  }

  public void setSpecialCounter(int specialCounter)
  {
    this.specialCounter = specialCounter;
  }
   public String getNotes()
  {
    return notes;
  }

  public void setNotes(String notes)
  {
    this.notes = notes;
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
 public int getCurrentYear()
  {
    return currentYear;
  }

  public void setCurrentYear(int currentYear)
  {
    this.currentYear = currentYear;
  } 
   public String getNoMap()
  {
    return noMap;
  }

  public void setNoMap(String noMap)
  {
    this.noMap = noMap;
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
   public double getAddPlansFee()
  {
    return addPlansFee;
  }

  public void setAddPlansFee(double addPlansFee)
  {
    this.addPlansFee = addPlansFee;
  }
    public void setApplicationDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      applicationDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      applicationDate = null;
    }
  }
  public void setApplicationDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
applicationDate = formatter.parse(dateParam);
} catch (Exception e)
{applicationDate = null; }
}
public String getApplicationDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(applicationDate == null)
{ return ""; }
else { return formatter.format(applicationDate); }
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
    public String getInspectorName()
  {
    return inspectorName;
  }

  public void setInspectorName(String inspectorName)
  {
    this.inspectorName = inspectorName;
  }
}