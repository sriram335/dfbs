package fireworks.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

public class DfbsFireworksPermit extends HsCompleteContact implements Serializable
{

  public final static int TYPE_WHOLESALE = 1;
  public final static int TYPE_RETAIL_STAND = 2;
  public final static int TYPE_RETAIL_CLASS = 3;
  public final static int TYPE_RETAIL_TENT = 4;
  private List permitFees;
  private String permitNumber;
  private String ownerName;
  private Date issueDate;
  private Date applicationDate;
  private int permitYear;
  private int applicationYear;
  private int currentYear;
  private String countyId;
  
  private int permitType;
  
  private int retailType;
  private String affidavit;
  
  
  private int ownerId;
  
  private double amount;
  
  //MANUFACTURER,WHOLESALER,DISTRIBUTOR,IMPORTER
  private int categoryId;
  private boolean manufacturer;
  private boolean wholesaler;
  private boolean distributor;
  private boolean importer;
  private String contactPerson;
  private String merchantNumber;
  private Date inspectDate;
  private Date openDate;
  private String hoursOfOperation;
  private String noMap;
   private int receiptId;
  private DfbsOwner owner;
  private String applicationKey;
  private List inspections;
  private List fileList;
  private String retailerType;
  private String inspectorName;
  private String permitComments;
  

  public DfbsFireworksPermit()
  {
   
  }
  
   public DfbsFireworksPermit(HsCompleteContact cc)
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
   public String getApplicationDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(applicationDate == null) 
    {
      return "";
    } else {
      return formatter.format(applicationDate);
    }
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
 public String getPermitComments()
  {
    return permitComments;
  }

  public void setPermitComments(String permitComments)
  {
    this.permitComments = permitComments;
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
  {
    if(permitYear >= currentYear)
    {
      return  "VALID";
    } else if(applicationYear >= currentYear) 
    {
      return  "PENDING";
    } 
    return "EXPIRED";
  }
  public  int getCurrentYear() 
  {
    return currentYear;
  }

  public void setCurrentYear(int currentYear)
  {
    this.currentYear = currentYear;
  }
  
  
  public int getRetailType() throws Exception
  {
    if (retailType == this.TYPE_RETAIL_CLASS
     || retailType == this.TYPE_RETAIL_TENT) 
     {
      return retailType;   
     } 
     else 
     {
      throw new Exception("Invalid Retail Type");  
     }
    
  }

  public void setRetailType(int retailType) throws Exception
  {
    if (retailType == this.TYPE_RETAIL_CLASS
     || retailType == this.TYPE_RETAIL_TENT) 
     {
      this.retailType = retailType;
     } 
     else 
     {
      throw new Exception("Invalid Retail Type");  
     }
  }
  
  public String getRetailTypeString() throws Exception
  {
    if(permitType == this.TYPE_RETAIL_CLASS) 
    {
      return "retail class1";
    } 
    else if(permitType == this.TYPE_RETAIL_TENT) 
    {
       return "retail tent";
    } 
    else 
    {
      throw new Exception("Invalid Tent Type");  
    }
  }
  public void setRetailType(String type) throws Exception
  {
    if (type == null) 
    {
      throw new Exception("Invalid Retail Type from Database");  
    } 
    else if(type.equals("First Retail") && affidavit != null && affidavit.equals("A")) 
    {
      setRetailType(this.TYPE_RETAIL_TENT);
      return;
    }  
     else if(type.equals("First Retail")) 
    {
      setRetailType(this.TYPE_RETAIL_CLASS);
      return;
    } 
    else if(type.equals("Additiona Tent") ) 
    {
      setRetailType(this.TYPE_RETAIL_TENT);
      return;
    } 
    else if(type.equals("Additional Tent") ) 
    {
      setRetailType(this.TYPE_RETAIL_TENT);
      return;
    }  
     else if(type.equals("Additiona Class1") ) 
    {
      setRetailType(this.TYPE_RETAIL_CLASS);
      return;
    }  
   
    else if(type.equals("retail tent")) 
    {
      setRetailType(this.TYPE_RETAIL_TENT);
      return;
    } 
    else if(type.equals("retail class1")) 
    {
      setRetailType(this.TYPE_RETAIL_CLASS);
      return;
    } 
     throw new Exception("Invalid Retail Type from Database"); 
  
  }
  
   public boolean isRetail() throws Exception
  { 
    if(permitNumber == null || permitNumber.length() < 3 )
    {
      throw new Exception("Invalid Permit Number");  
    } 
    else if(permitNumber.substring(0,3).equals("FWT") || permitNumber.substring(0,3).equals("FWR")) 
    { 
      return true;   
    }
    
    return false;
    
  }

  public int getPermitType() throws Exception
  {
  
    
    if ( permitType == TYPE_RETAIL_STAND
     || permitType == this.TYPE_WHOLESALE
     || permitType == this.TYPE_RETAIL_TENT
     || permitType == this.TYPE_RETAIL_CLASS) 
     {
        return permitType;
     } 
     else 
    {
      throw new Exception("Invalid Permit Type");  
    }
  }
  public String getPermitTypeString() throws Exception
  {
    switch(getPermitType()) 
    {
      case TYPE_WHOLESALE:
        return "wholesale";
      case TYPE_RETAIL_STAND:
        return "retail stand (section 8a only) ";
      case TYPE_RETAIL_TENT:
        return "retail tent";
      case TYPE_RETAIL_CLASS:
        return "retail class1";
    }
    throw new Exception("Invalid Permit Type");  
   
  }
  
  public void setPermitType(int permitType) throws Exception
  {
    if ( permitType == this.TYPE_RETAIL_STAND
     || permitType == this.TYPE_WHOLESALE
     || permitType == this.TYPE_RETAIL_TENT
     || permitType == this.TYPE_RETAIL_CLASS) 
     {
        this.permitType = permitType;
     } 
     else 
    {
      throw new Exception("Invalid Permit Type");  
    }
  
    
  }
  
  public void setPermitTypeOnSelect() throws Exception
  {
    if(permitNumber == null || permitNumber.length() < 3 )
    {
      throw new Exception("Invalid Permit Number");  
    } 
    else if(permitNumber.substring(0,3).equals("FWR")) 
    {
      setPermitType(TYPE_RETAIL_STAND);
      return;
    } 
    else if(isRetail()) 
    {
      setPermitType(getRetailType());
      return;
    }
    else if(permitNumber.charAt(1) == 'W') 
    {
       setPermitType(TYPE_WHOLESALE);
       return;
    } 
     
     throw new Exception("Invalid Permit Type from Database");  
    
  }
  
  public String createPermitNumber(int id) throws Exception
  {
    StringBuffer sb = new StringBuffer("FW");
    switch(getPermitType())
    {
      case TYPE_WHOLESALE:
        sb.append(id);
      break;
      case TYPE_RETAIL_STAND:
        sb.append("R").append(id);
      break;
      case TYPE_RETAIL_TENT :
      case TYPE_RETAIL_CLASS:
        sb.append("T").append(id);
      break;
    }
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
    return (this.getPermitNumber() == null || this.getPermitNumber().trim().equals("") ) ? true : false;
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

  public boolean isManufacturer()
  {
    return manufacturer;
  }

  public void setManufacturer(boolean manufacturer)
  {
    this.manufacturer = manufacturer;
  }

  public boolean isWholesaler()
  {
    return wholesaler;
  }

  public void setWholesaler(boolean wholesaler)
  {
    this.wholesaler = wholesaler;
  }
  
  public String getWholesaler()
  {
    return (wholesaler) ? "Wholesaler" : "";
  }
  
  public void setWholesaler(String newWholesaler)
  {
   wholesaler = (newWholesaler != null && newWholesaler.equals("Wholesaler")) ? true :  false;
  }
  
  public String getManufacturer()
  {
    return (manufacturer) ? "Manufacturer" : "";
  }
  
  public void setManufacturer(String newManufacturer)
  {
   manufacturer = (newManufacturer != null && newManufacturer.equals("Manufacturer")) ? true :  false;
  }
  
  public String getImporter()
  {
    return (importer) ? "Importer" : "";
  }
  
  public void setImporter(String newImporter)
  {
   importer = (newImporter != null && newImporter.equals("Importer")) ? true :  false;
  }
  
  public String getDistributor()
  {
    return (distributor) ? "Distributor" : "";
  }
  
  public void setDistributor(String newDistributor)
  {
   distributor = (newDistributor != null && newDistributor.equals("Distributor")) ? true :  false;
  }

  public int getCategoryId()
  {
    return categoryId;
  }

  public void setCategoryId(int categoryId)
  {
    this.categoryId = categoryId;
  }
  
  public String getCategoryString()
  {
    //MANUFACTURER,WHOLESALER,DISTRIBUTOR,IMPORTER
    StringBuffer sb = new StringBuffer();
    if(isManufacturer()) 
    {
      sb.append(getManufacturer()).append(", ");
    }
    if(isWholesaler()) 
    {
      sb.append(getWholesaler()).append(", ");
    }
    if(isDistributor()) 
    {
      sb.append(getDistributor()).append(", ");
    }
    if(isImporter()) 
    {
      sb.append(getImporter()).append(", ");
    } 
    
    if(sb.length() > 2 ) {
      sb.deleteCharAt(sb.length() - 1);
      sb.deleteCharAt(sb.length() - 1);
    }
    
    return sb.toString();
    
    
  }

 

  public String getContactPerson()
  {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson)
  {
    this.contactPerson = contactPerson;
  }

  public String getMerchantNumber()
  {
    return merchantNumber;
  }

  public void setMerchantNumber(String merchantNumber)
  {
    this.merchantNumber = merchantNumber;
  }

  public String getCountyId()
  {
    return countyId;
  }

  public void setCountyId(String countyId)
  {
    this.countyId = countyId;
  }
  public String getTransactionDescription() throws Exception
  {
    StringBuffer sb = new StringBuffer();
    sb.append(this.getPermitTypeString());
    sb.append("-");
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

  public int getApplicationYear()
  {
    return applicationYear;
  }

  public void setApplicationYear(int applicationYear)
  {
    this.applicationYear = applicationYear;
  }

  public String getHoursOfOperation()
  {
    return hoursOfOperation;
  }

  public void setHoursOfOperation(String hoursOfOperation)
  {
    this.hoursOfOperation = hoursOfOperation;
  }

  public Date getInspectDate()
  {
    return inspectDate;
  }

  public void setInspectDate(Date inspectDate)
  {
    this.inspectDate = inspectDate;
  }

  public Date getOpenDate()
  {
    return openDate;
  }

  public void setOpenDate(Date openDate)
  {
    this.openDate = openDate;
  }
  
  public void setInspectDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      inspectDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      inspectDate = null;
    }
  }
   public String getInspectDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(inspectDate == null) 
    {
      return "";
    } else {
      return formatter.format(inspectDate);
    }
  }
  public void setOpenDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      openDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      openDate = null;
    }
  }
   public String getOpenDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(openDate == null) 
    {
      return "";
    } else {
      return formatter.format(openDate);
    }
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

  public String getAffidavit()
  {
    return affidavit;
  }

  public void setAffidavit(String affidavit)
  {
    this.affidavit = affidavit;
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
   public String getNoMap()
  {
    return noMap;
  }

  public void setNoMap(String noMap)
  {
    this.noMap = noMap;
  }
   public String getRetailerType()
  {
    return retailerType;
  }

  public void setRetailerType(String retailerType)
  {
    this.retailerType = retailerType;
  }
    public String getInspectorName()
  {
    return inspectorName;
  }

  public void setInspectorName(String inspectorName)
  {
    this.inspectorName = inspectorName;
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
}