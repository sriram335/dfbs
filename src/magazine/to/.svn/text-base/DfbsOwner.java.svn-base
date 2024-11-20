package magazine.to;
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
  private int permitsCount;
  private int permitCounter;
  private int contactsCount;
  private int contactCounter;
  private String ownerKey;
  private double amount;
  private int ownerStateId ;
  private List fileList;
  public DfbsOwner() 
  {
    
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
  public int getFileListCount () 
  {
    return (fileList == null) ? 0 : fileList.size();
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
   return ownerLastName;
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

  public void addPermit(DfbsPermit permit) throws Exception
  {
    if(permit == null) return;
    
    
    if(permit.isNew()) 
    {
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getPermitCounter() + 1);
      Map map = this.getPermitsMap();
      permit.setMagPermitKey(sb.toString());
      map.put(sb.toString(),permit);
      setPermitCounter(getPermitCounter() + 1);
    } 
    else 
    {
      Map map = this.getPermitsMap();
      permit.setMagPermitKey(permit.getMagIdNumber());
      map.put(permit.getMagIdNumber(),permit);
    }
    this.addCount(permit);
  }
  public DfbsPermit getPermit(String key)
  {
    
    if(key == null || permitsMap == null ) {
       
      return new DfbsPermit();
    }
    DfbsPermit permit = (DfbsPermit) permitsMap.get(key);
    return (permit == null) ? new DfbsPermit() : permit;
  }
  
  
  public void removePermit(String key) throws Exception
  {
    if(key == null || permitsMap == null ) return;
    
    DfbsPermit permit = (DfbsPermit) permitsMap.get(key);
    setAmount(getAmount()-permit.getAmount());
    this.removeCount(permit);
    permitsMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getOwnerId() == 0) ? true : false;
  }

 
  
 
 
 private void addCount(DfbsPermit permit) throws Exception
  {
   
      this.permitsCount++; 
      
  }
  
  private void removeCount(DfbsPermit permit) throws Exception
  {
    
      this.permitsCount--; 
      
  }
   
 public int getPermitsCount()
  {
    return permitsCount;
  }
  public void setPermitsCount(int permitsCount)
  {
    this.permitsCount = permitsCount;
  }

  public int getPermitCounter()
  {
    return permitCounter;
  }

  public void setPermitCounter(int permitCounter)
  {
    this.permitCounter = permitCounter;
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
}
