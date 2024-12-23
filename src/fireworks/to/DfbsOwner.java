package fireworks.to;
import hs.to.*;



import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DfbsOwner  implements Serializable
{
  
  private List permits;
  private Map permitsMap;
  
  private int stateId;
  private int contactId;
  private String contactPhoneNumber;
  private String checkoutId;
  private int addressId;
  private int ownerId;
  private String firstName;
  private String lastName;
  private String title;
  private String street1;
  private String Street2;
  private String city;
  private String State;
  private String zip;
  private String county;
  private String phoneNumber;
  private String faxNumber;
  private String ownerName;
  private String ownerEmail;
  private double totalPermitsAmount;
  private int wholesalePermitsCount; 
  private int retailTentPermitsCount; 
  private int retailClassPermitsCount; 
   private int retailStandPermitsCount; 
  
  
  public DfbsOwner() 
  {
   
  }
     public String getOwnerEmail()
  { 
   return ownerEmail;
  }
public void setOwnerEmail(String ownerEmail)
  { 
  this.ownerEmail = ownerEmail;
  } 
 
  public int getPermitsCount () 
  {
    return (permits == null) ? 0 : permits.size();
  }

  public List getPermits()
  {
    if(permits == null) 
    {
      permits = new ArrayList();
    } 
    return permits;
  }

  public void setPermits(List permits)
  {
    this.permits = permits;
  }

  public int getStateId()
  {
    return stateId;
  }

  public void setStateId(int stateId)
  {
    this.stateId = stateId;
  }

  public String getContactPhoneNumber()
  {
    return contactPhoneNumber;
  }

  public void setContactPhoneNumber(String contactPhoneNumber)
  {
    this.contactPhoneNumber = contactPhoneNumber;
  }
  public int getContactId() 
  {
    return contactId;
  }
  public void setContactId(int contactId) 
  {
    this.contactId = contactId;
  }

  public Map getPermitsMap()
  {
    if(permitsMap == null) 
    {
      permitsMap = new HashMap();
    }
    return permitsMap;
  }

  public void setPermitsMap(Map permitsMap)
  {
    this.permitsMap = permitsMap;
  }
  public int getPermitsMapCount()
  {
    return (this.permitsMap == null) ? 0 : permitsMap.size();
  }

  public void addPermit(DfbsFireworksPermit permit) throws Exception
  {
    if(permit == null) return;
    
    permit.setOwnerId(this.getOwnerId());
    
    if(permit.isNew()) 
    {
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getPermitsMapCount() + 1);
      Map map = this.getPermitsMap();
      permit.setApplicationKey(sb.toString());
      map.put(sb.toString(),permit);
    } 
    else 
    {
      Map map = this.getPermitsMap();
      permit.setApplicationKey(permit.getPermitNumber());
      map.put(permit.getPermitNumber(),permit);
    }
    this.addCount(permit);
  }
  public DfbsFireworksPermit getPermit(String key)
  {
    
    if(key == null || permitsMap == null ) {
       
      return new DfbsFireworksPermit();
    }
    DfbsFireworksPermit permit = (DfbsFireworksPermit) permitsMap.get(key);
    return (permit == null) ? new DfbsFireworksPermit() : permit;
  }
  
  
  public void removePermit(String key) throws Exception
  {
    if(key == null || permitsMap == null ) return;
    
    DfbsFireworksPermit permit = (DfbsFireworksPermit) permitsMap.get(key);
    this.removeCount(permit);
    permitsMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getOwnerId() == 0) ? true : false;
  }

  public String getCheckoutId()
  {
    return checkoutId;
  }

  public void setCheckoutId(String checkoutId)
  {
    this.checkoutId = checkoutId;
  }
  
  public double getTotalPermitsAmount()
  {
    return totalPermitsAmount;
  }

  public void setTotalPermitsAmount(double totalPermitsAmount)
  {
    this.totalPermitsAmount = totalPermitsAmount;
  }
  
 
  public String getTotalPermitsAmountString()
  {
    return NumberFormat.getCurrencyInstance().format(getTotalPermitsAmount());
    
  }
 
  public void setTotalPermitsAmount(String newAmount) throws Exception
  {
    if (newAmount == null) 
    {
      totalPermitsAmount=0;
      return;
    }
  
    try {
    totalPermitsAmount= NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       totalPermitsAmount= NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         totalPermitsAmount=0;
       }
    }
  }

  public int getWholesalePermitsCount()
  {
    return wholesalePermitsCount;
  }

  public void setWholesalePermitsCount(int wholesalePermitsCount)
  {
    this.wholesalePermitsCount = wholesalePermitsCount;
  }

  public int getRetailTentPermitsCount()
  {
    return retailTentPermitsCount;
  }

  public void setRetailTentPermitsCount(int retailTentPermitsCount)
  {
    this.retailTentPermitsCount = retailTentPermitsCount;
  }

  public int getRetailClassPermitsCount()
  {
    return retailClassPermitsCount;
  }

  public void setRetailClassPermitsCount(int retailClassPermitsCount)
  {
    this.retailClassPermitsCount = retailClassPermitsCount;
  }

  public int getRetailStandPermitsCount()
  {
    return retailStandPermitsCount;
  }

  public void setRetailStandPermitsCount(int retailStandPermitsCount)
  {
    this.retailStandPermitsCount = retailStandPermitsCount;
  }

  private void addCount(DfbsFireworksPermit permit) throws Exception
  {
    switch(permit.getPermitType()) 
    {
      case DfbsFireworksPermit.TYPE_WHOLESALE:
      this.wholesalePermitsCount++; 
      break;
      case DfbsFireworksPermit.TYPE_RETAIL_STAND:
      this.retailStandPermitsCount++;
      break;
      case DfbsFireworksPermit.TYPE_RETAIL_TENT:
      this.retailTentPermitsCount++;
      break;
      case DfbsFireworksPermit.TYPE_RETAIL_CLASS:
      this.retailClassPermitsCount++;
      break;
    }
  }
  
  private void removeCount(DfbsFireworksPermit permit) throws Exception
  {
    switch(permit.getPermitType()) 
    {
      case DfbsFireworksPermit.TYPE_WHOLESALE:
      this.wholesalePermitsCount--; 
      break;
      case DfbsFireworksPermit.TYPE_RETAIL_STAND:
      this.retailStandPermitsCount--;
      break;
      case DfbsFireworksPermit.TYPE_RETAIL_TENT:
      this.retailTentPermitsCount--;
      break;
      case DfbsFireworksPermit.TYPE_RETAIL_CLASS:
      this.retailClassPermitsCount--;
      break;
    }
  }
 
 public String getState()
  { 
   return State;
  }
public void setState(String State)
  { 
  this.State = State;
  }
public String getStreet2()
  { 
   return Street2;
  }
public void setStreet2(String Street2)
  { 
  this.Street2 = Street2;
  }
public String getCity()
  { 
   return city;
  }
public void setCity(String city)
  { 
  this.city = city;
  }
public String getCounty()
  { 
   return county;
  }
public void setCounty(String county)
  { 
  this.county = county;
  }
public String getFaxNumber()
  { 
   return faxNumber;
  }
public void setFaxNumber(String faxNumber)
  { 
  this.faxNumber = faxNumber;
  }
public String getFirstName()
  { 
   return firstName;
  }
public void setFirstName(String firstName)
  { 
  this.firstName = firstName;
  }
public String getLastName()
  { 
   return lastName;
  }
public void setLastName(String lastName)
  { 
  this.lastName = lastName;
  }
public String getOwnerName()
  { 
   return ownerName;
  }
public void setOwnerName(String ownerName)
  { 
  this.ownerName = ownerName;
  }
public String getPhoneNumber()
  { 
   return phoneNumber;
  }
public void setPhoneNumber(String phoneNumber)
  { 
  this.phoneNumber = phoneNumber;
  }
public String getStreet1()
  { 
   return street1;
  }
public void setStreet1(String street1)
  { 
  this.street1 = street1;
  }
public String getTitle()
  { 
   return title;
  }
public void setTitle(String title)
  { 
  this.title = title;
  }
public String getZip()
  { 
   return zip;
  }
public void setZip(String zip)
  { 
  this.zip = zip;
  }
public int getAddressId()
  { 
   return addressId;
  }
public void setAddressId(int addressId)
  { 
  this.addressId = addressId;
  } 
  public int getOwnerId()
  { 
   return ownerId;
  }
public void setOwnerId(int ownerId)
  { 
  this.ownerId = ownerId;
  } 
}