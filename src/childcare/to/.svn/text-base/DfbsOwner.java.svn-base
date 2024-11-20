package childcare.to;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import hs.to.*;
public class DfbsOwner  implements Serializable
{
  
  private List childCares;
  private Map childCaresMap;
  private List contacts;
  private Map contactsMap;
  private int ownerId;
  private String ownerLastName;
  private String ownerFirstName;
  private String ownerMI;
  private String ownerDBA;
  private String ownerAddress1;
  private String ownerAddress2;
  private String ownerCity;
  private String ownerState;
  private String ownerZip;
  private String ownerZip2;
  private String ownerPhone;
  private String ownerFax;
  private String ownerEmail;
  private int childCaresCount;
  private int childCareCounter;
  private int contactsCount;
  private int contactCounter;
  private String ownerKey;
  private double amount;
  private int ownerStateId ;
  private int checkoutId ;
  private boolean ownerError;
  private String renewStatus;
  public DfbsOwner() 
  {
    
  }
   public String getRenewStatus()
  { 
   return renewStatus;
  }
public void setRenewStatus(String renewStatus)
  { 
  this.renewStatus = renewStatus;
  }
  
   public double getAmount()
  { 
   return amount;
  }
public void setAmount(double amount)
  { 
  this.amount = amount;
  }
  public String getOwnerAddress1()
  { 
   return ownerAddress1;
  }
public void setOwnerAddress1(String ownerAddress1)
  { 
  this.ownerAddress1 = ownerAddress1;
  }
   public boolean getOwnerError()
  { 
   return ownerError;
  }
public void setOwnerError(boolean ownerError)
  { 
  this.ownerError = ownerError;
  }
public String getOwnerAddress2()
  { 
   return ownerAddress2;
  }
public void setOwnerAddress2(String ownerAddress2)
  { 
  this.ownerAddress2 = ownerAddress2;
  }
public String getOwnerCity()
  { 
   return ownerCity;
  }
public void setOwnerCity(String ownerCity)
  { 
  this.ownerCity = ownerCity;
  }
public String getOwnerDBA()
  { 
   return ownerDBA;
  }
public void setOwnerDBA(String ownerDBA)
  { 
  this.ownerDBA = ownerDBA;
  }
public String getOwnerFirstName()
  { 
   return ownerFirstName;
  }
public void setOwnerFirstName(String ownerFirstName)
  { 
  this.ownerFirstName = ownerFirstName;
  }
public String getOwnerLastName()
  { 
  
   return (ownerLastName == null) ? "xxx" : ownerLastName;
  }
public void setOwnerLastName(String ownerLastName)
  { 
  this.ownerLastName = ownerLastName;
  }
public String getOwnerMI()
  { 
   return ownerMI;
  }
public void setOwnerMI(String ownerMI)
  { 
  this.ownerMI = ownerMI;
  }
public String getOwnerState()
  { 
   return ownerState;
  }
public void setOwnerState(String ownerState)
  { 
  this.ownerState = ownerState;
  }
public String getOwnerZip()
  { 
   return ownerZip;
  }
public void setOwnerZip(String ownerZip)
  { 
  this.ownerZip = ownerZip;
  }
public String getOwnerZip2()
  { 
   return ownerZip2;
  }
public void setOwnerZip2(String ownerZip2)
  { 
  this.ownerZip2 = ownerZip2;
  }
public int getOwnerId()
  { 
   return ownerId;
  }
public void setOwnerId(int ownerId)
  { 
  this.ownerId = ownerId;
  }

  public Map getChildCaresMap()
  {
    if(childCaresMap == null) 
    {
      childCaresMap = new HashMap();
    }
    return childCaresMap;
  }

  public void setChildCaresMap(Map childCaresMap)
  {
    this.childCaresMap = childCaresMap;
  }
  public int getChildCaresMapCount()
  {
    return (this.childCaresMap == null) ? 0 : childCaresMap.size();
  }

  public void addPermit(DfbsChildcarePermit childCare) throws Exception
  {
    if(childCare == null) return;
    
    
    if(childCare.isNew()) 
    {
      StringBuffer sb = new StringBuffer(childCare.getTelephone());
      sb.append(getPermitCounter() + 1);
      Map map = this.getChildCaresMap();
      childCare.setApplicationKey(sb.toString());
      map.put(sb.toString(),childCare);
      setPermitCounter(getPermitCounter() + 1);
    } 
    else 
    {
      Map map = this.getChildCaresMap();
      childCare.setApplicationKey(childCare.getPermitNumber());
      map.put(childCare.getPermitNumber(),childCare);
    }
    this.addCount(childCare);
     
  }
  public DfbsChildcarePermit getPermit(String key)
  {
    
    if(key == null || childCaresMap == null ) {
       
      return new DfbsChildcarePermit();
    }
    DfbsChildcarePermit childCare = (DfbsChildcarePermit) childCaresMap.get(key);
    return (childCare == null) ? new DfbsChildcarePermit() : childCare;
  }
  
  
  public void removePermit(String key) throws Exception
  {
    if(key == null || childCaresMap == null ) return;
    
    DfbsChildcarePermit childCare = (DfbsChildcarePermit) childCaresMap.get(key);
    setAmount(getAmount()-childCare.getAmount());
    this.removeCount(childCare);
    childCaresMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getOwnerId() == 0) ? true : false;
  }

 
  
 
 
 private void addCount(DfbsChildcarePermit childCare) throws Exception
  {
   
      this.childCaresCount++; 
      
  }
  
  private void removeCount(DfbsChildcarePermit childCare) throws Exception
  {
    
      this.childCaresCount--; 
      
  }
   
 public int getPermitsCount()
  {
    return childCaresCount;
  }
  public void setPermitsCount(int childCaresCount)
  {
    this.childCaresCount = childCaresCount;
  }

  public int getPermitCounter()
  {
    return childCareCounter;
  }

  public void setPermitCounter(int childCareCounter)
  {
    this.childCareCounter = childCareCounter;
  }
  
  public String getOwnerKey() 
  {
    return ownerKey;
  }
  public void setOwnerKey(String ownerKey) 
  {
   this.ownerKey = ownerKey;
  }
  public Map getContactsMap()
  {
    if(contactsMap == null) 
    {
      contactsMap = new HashMap();
    }
    return contactsMap;
  }

  public void setContactsMap(Map contactsMap)
  {
    this.contactsMap = contactsMap;
  }
  public int getContactsMapCount()
  {
    return (this.contactsMap == null) ? 0 : contactsMap.size();
  }

  public void addContact(DfbsContact contact) throws Exception
  {
    if(contact == null) return;
    
      
    if(contact.isNew()) 
    { 
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getContactCounter() + 1);
      Map map = this.getContactsMap();
      contact.setContactKey(sb.toString());
      map.put(sb.toString(),contact);
      setContactCounter(getContactCounter() + 1);
    } 
    else 
    {
      Map map = this.getContactsMap();
      contact.setContactKey(Integer.toString(contact.getPersonId()));
      map.put(contact.getContactKey(),contact);
    }
    this.addCountContact(contact);
  }
  public DfbsContact getContact(String key)
  {
    
    if(key == null || contactsMap == null ) {
       
      return new DfbsContact();
    }
    DfbsContact contact = (DfbsContact) contactsMap.get(key);
    return (contact == null) ? new DfbsContact() : contact;
  }
  
  
  public void removeContact(String key) throws Exception
  {
    if(key == null || contactsMap == null ) return;
    
    DfbsContact contact = (DfbsContact) contactsMap.get(key);
     this.removeCountContact(contact);
    contactsMap.remove(key);
  }
  
 
 
  
 
 
 private void addCountContact(DfbsContact contact) throws Exception
  {
   
      this.contactsCount++; 
      
  }
  
  private void removeCountContact(DfbsContact contact) throws Exception
  {
    
      this.contactsCount--; 
      
  }
   

  public void setContactsCount(int contactsCount)
  {
    this.contactsCount = contactsCount;
  }

  public int getContactCounter()
  {
    return contactCounter;
  }

  public void setContactCounter(int contactCounter)
  {
    this.contactCounter = contactCounter;
  }
  public String getOwnerFax()
  { 
   return ownerFax;
  }
public void setOwnerFax(String OwnerFax)
  { 
  this.ownerFax = OwnerFax;
  }
public String getOwnerEmail()
  { 
   return ownerEmail;
  }
public void setOwnerEmail(String ownerEmail)
  { 
  this.ownerEmail = ownerEmail;
  }
public String getOwnerPhone()
  { 
   return ownerPhone;
  }
public void setOwnerPhone(String ownerPhone)
  { 
  this.ownerPhone = ownerPhone;
  }
   public List getChildCares()
  {
    if(childCares == null) 
    {
      childCares = new ArrayList();
    } 
    return childCares;
  }

  public void setChildCares(List childCares)
  {
    this.childCares = childCares;
  }
   public void setOwnerStateId(int ownerStateId)
  {
    this.ownerStateId = ownerStateId;
  }

  public int getOwnerStateId()
  {
    return ownerStateId;
  }
  public List getContacts()
  {
    if(contacts == null) 
    {
      contacts = new ArrayList();
    } 
    return contacts;
  }

  public void setContacts(List contacts)
  {
    this.contacts = contacts;
  }
   public void setCheckoutId(int checkoutId)
  {
    this.checkoutId = checkoutId;
  }

  public int getCheckoutId()
  {
    return checkoutId;
  }
}