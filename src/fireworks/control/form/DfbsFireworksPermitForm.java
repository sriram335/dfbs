package fireworks.control.form;

import main.control.form.*;
import hs.to.*;
import fireworks.to.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
public class DfbsFireworksPermitForm extends CompleteContactForm
{
  
  private String permitNumber;
  private String ownerName;
  private String issueDate;
  private String applicationDate;
  private int permitYear;
  private int currentYear; 
  private int permitType; 
  private int ownerId;
  private int categoryId;
  private boolean manufacturer;
  private boolean wholesaler;
  private boolean distributor;
  private boolean importer;
  private String countyId;
  private String key;
   private String contactPerson;
  private String merchantNumber;
  private String inspectDate;
  private String openDate;
  private String hoursOfOperation;
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String idNumber;
  private String idType;
  private String applicationKey;
  private String noMap;
  private String permitComments;
  public DfbsFireworksPermitForm()
  {
  
  }
    public void setUpdatedProperties(DfbsFireworksPermit updatedPermit,DfbsFireworksPermit permit ) throws Exception
  { 
    permit.setId(updatedPermit.getId());
    permit.setFirstname(updatedPermit.getFirstname());
    permit.setLastname(updatedPermit.getLastname());
    permit.setTitle(updatedPermit.getTitle());
    permit.setAddressId(updatedPermit.getAddressId());
    permit.setStreet1(updatedPermit.getStreet1());
    permit.setStreet2(updatedPermit.getStreet2());
    permit.setCity(updatedPermit.getCity());
    permit.setState(updatedPermit.getState());
    permit.setZip(updatedPermit.getZip());
    permit.setCounty(updatedPermit.getCounty());
    permit.setPhoneId(updatedPermit.getPhoneId());
    permit.setPhoneNumber(updatedPermit.getPhoneNumber());
    permit.setFaxId(updatedPermit.getFaxId());
    permit.setFaxNumber(updatedPermit.getFaxNumber());
    permit.setEmailId(updatedPermit.getEmailId());
    permit.setEmailAddress(updatedPermit.getEmailAddress());
    permit.setOrgName(updatedPermit.getOrgName());
    permit.setActive(updatedPermit.isActive());  
    permit.setPermitNumber(updatedPermit.getPermitNumber());
    permit.setOwnerName(updatedPermit.getOwnerName());
    permit.setIssueDate(updatedPermit.getIssueDate());
    permit.setApplicationDate(updatedPermit.getApplicationDate());
    permit.setPermitYear(updatedPermit.getPermitYear());
    permit.setPermitType(updatedPermit.getPermitType());
    permit.setOwnerId(updatedPermit.getOwnerId());
    permit.setCategoryId(updatedPermit.getCategoryId());
    permit.setManufacturer(updatedPermit.isManufacturer());
    permit.setWholesaler(updatedPermit.isWholesaler());
    permit.setDistributor(updatedPermit.isDistributor());
    permit.setImporter(updatedPermit.isImporter());
    permit.setContactPerson(updatedPermit.getContactPerson());
    permit.setCountyId(updatedPermit.getCountyId());
    permit.setMerchantNumber(updatedPermit.getMerchantNumber());
    permit.setHoursOfOperation(updatedPermit.getHoursOfOperation());
    permit.setInspectDate(updatedPermit.getInspectDate());
    permit.setOpenDate(updatedPermit.getOpenDate());
    permit.setApplicationKey(updatedPermit.getApplicationKey());
    permit.setNoMap(updatedPermit.getNoMap());
    permit.setPermitComments(updatedPermit.getPermitComments());
  }
   public void setProperties(DfbsFireworksPermit permit) throws Exception
  {
    
    super.setProperties(permit);
    
    this.setPermitNumber(permit.getPermitNumber());
    this.setOwnerName(permit.getOwnerName());
    this.setIssueDate(permit.getIssueDateString());
    this.setApplicationDate(permit.getApplicationDateString());
    this.setPermitYear(permit.getPermitYear());
    this.setCurrentYear(permit.getCurrentYear()); 
    this.setPermitType(permit.getPermitType());
    this.setOwnerId(permit.getOwnerId());
    this.setCategoryId(permit.getCategoryId());
    this.setManufacturer(permit.isManufacturer());
    this.setWholesaler(permit.isWholesaler());
    this.setDistributor(permit.isDistributor());
    this.setImporter(permit.isImporter());
    this.setContactPerson(permit.getContactPerson());
    this.setCountyId(permit.getCountyId());
    this.setMerchantNumber(permit.getMerchantNumber());
    this.setHoursOfOperation(permit.getHoursOfOperation());
    this.setInspectDate(permit.getInspectDateString());
    this.setOpenDate(permit.getOpenDateString());
     this.setApplicationKey(permit.getApplicationKey());
     this.setNoMap(permit.getNoMap()); 
    this.setPermitComments(permit.getPermitComments());
    
    
  }
  
  public DfbsFireworksPermit getDfbsFireworksPermit() throws Exception
  {
    
    DfbsFireworksPermit permit = new DfbsFireworksPermit (this.getHsCompleteContact());
    
    permit.setPermitNumber(this.getPermitNumber());
    permit.setOwnerName(this.getOwnerName());
    permit.setIssueDate(this.getIssueDate());
    permit.setApplicationDate(this.getApplicationDate());
    permit.setPermitYear(this.getPermitYear());
    permit.setPermitType(this.getPermitType());
    permit.setOwnerId(this.getOwnerId());
    permit.setCategoryId(this.getCategoryId());
    permit.setManufacturer(this.isManufacturer());
    permit.setWholesaler(this.isWholesaler());
    permit.setDistributor(this.isDistributor());
    permit.setImporter(this.isImporter());
    permit.setContactPerson(this.getContactPerson());
    permit.setCountyId(this.getCountyId());
    permit.setMerchantNumber(this.getMerchantNumber());
    permit.setHoursOfOperation(this.getHoursOfOperation());
    permit.setInspectDate(this.getInspectDate());
    permit.setOpenDate(this.getOpenDate());
    permit.setApplicationKey(this.getApplicationKey());
    permit.setNoMap(this.getNoMap());
    permit.setPermitComments(this.getPermitComments());
    return permit;
  }
   public String getPermitComments()
  {
    return permitComments;
  }

  public void setPermitComments(String permitComments)
  {
    this.permitComments = permitComments;
  }
   public String getNoMap()
  {
    return noMap;
  }

  public void setNoMap(String noMap)
  {
    this.noMap = noMap;
  }

  public String getApplicationDate()
  {
    return applicationDate;
  }

  public void setApplicationDate(String applicationDate)
  {
    this.applicationDate = applicationDate;
  }
 public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public int getCurrentYear()
  {
    return currentYear;
  }

  public void setCurrentYear(int currentYear)
  {
    this.currentYear = currentYear;
  }

  public String getIssueDate()
  {
    return issueDate;
  }

  public void setIssueDate(String issueDate)
  {
    this.issueDate = issueDate;
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

  public int getPermitType()
  {
    return permitType;
  }

  public void setPermitType(int permitType)
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

  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public int getCategoryId()
  {
    return categoryId;
  }

  public void setCategoryId(int categoryId)
  {
    this.categoryId = categoryId;
  }

  public boolean isDistributor()
  {
    return distributor;
  }

  public void setDistributor(boolean distributor)
  {
    this.distributor = distributor;
  }

  public boolean isImporter()
  {
    return importer;
  }

  public void setImporter(boolean importer)
  {
    this.importer = importer;
  }

  public boolean isWholesaler()
  {
    return wholesaler;
  }

  public void setWholesaler(boolean wholesaler)
  {
    this.wholesaler = wholesaler;
  }

  public boolean isManufacturer()
  {
    return manufacturer;
  }

  public void setManufacturer(boolean manufacturer)
  {
    this.manufacturer = manufacturer;
  }

 

  public String getContactPerson()
  {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson)
  {
    this.contactPerson = contactPerson;
  }

  public String getCountyId()
  {
    return countyId;
  }

  public void setCountyId(String countyId)
  {
    this.countyId = countyId;
  }

  public String getMerchantNumber()
  {
    return merchantNumber;
  }

  public void setMerchantNumber(String merchantNumber)
  {
    this.merchantNumber = merchantNumber;
  }

  public String getOpenDate()
  {
    return openDate;
  }

  public void setOpenDate(String openDate)
  {
    this.openDate = openDate;
  }

  public String getInspectDate()
  {
    return inspectDate;
  }

  public void setInspectDate(String inspectDate)
  {
    this.inspectDate = inspectDate;
  }

  public String getHoursOfOperation()
  {
    return hoursOfOperation;
  }

  public void setHoursOfOperation(String hoursOfOperation)
  {
    this.hoursOfOperation = hoursOfOperation;
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
}