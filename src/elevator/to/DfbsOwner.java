package elevator.to;

import elevator.to.*;

import hs.to.*;


import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DfbsOwner  implements Serializable
{
  
  private List elevators;
  private Map elevatorsMap;
  private Map elevatorAppMap;
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
  private int elevatorsCount;
  private int elevatorListCount;
  private int elevatorCounter;
   private String ownerKey;
  private double amount;
  private int ownerStateId ;
  private boolean ownerError;
  private int ownerFee;
  public DfbsOwner() 
  {
    
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

  public Map getElevatorsMap()
  {
    if(elevatorsMap == null) 
    {
      elevatorsMap = new HashMap();
    }
    return elevatorsMap;
  }

  public void setElevatorsMap(Map elevatorsMap)
  {
    this.elevatorsMap = elevatorsMap;
  }
  public int getElevatorsMapCount()
  {
    return (this.elevatorsMap == null) ? 0 : elevatorsMap.size();
  }

  public void addElevator(elevator elevator) throws Exception
  {
    if(elevator == null) return;
    
    
    if(elevator.isNew()) 
    {
      StringBuffer sb = new StringBuffer(elevator.getLocUserPhone());
      sb.append(getPermitCounter() + 1);
      Map map = this.getElevatorsMap();
      elevator.setElevApplicationKey(sb.toString());
      map.put(sb.toString(),elevator);
      setPermitCounter(getPermitCounter() + 1);
    } 
    else 
    {
      Map map = this.getElevatorsMap();
      elevator.setElevApplicationKey(elevator.getStateNumber());
      map.put(elevator.getStateNumber(),elevator);
    }
    this.addCount();
  }
  public elevator getElevator(String key)
  {
    
    if(key == null || elevatorsMap == null ) {
       
      return new elevator();
    }
    elevator elev = (elevator) elevatorsMap.get(key);
    return (elev == null) ? new elevator() : elev;
  }
  
  
  public void removeElevator(String key) throws Exception
  {
    if(key == null || elevatorsMap == null ) return;
    
    elevator elev = ( elevator) elevatorsMap.get(key);
     this.removeCount();
    elevatorsMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getOwnerId() == 0) ? true : false;
  }

 
  
 
 
 private void addCount() throws Exception
  {
   
      this.elevatorsCount++; 
      
  }
  
  private void removeCount() throws Exception
  {
    
      this.elevatorsCount--; 
      
  }
   
 public int getElevatorsCount()
  {
    return elevatorsCount;
  }
  public void setElevatorsCount(int elevatorsCount)
  {
    this.elevatorsCount = elevatorsCount;
  }

  public int getPermitCounter()
  {
    return elevatorCounter;
  }

  public void setPermitCounter(int elevatorCounter)
  {
    this.elevatorCounter = elevatorCounter;
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
   public List getElevators()
  {
    if(elevators == null) 
    {
      elevators = new ArrayList();
    } 
    return elevators;
  }

  public void setElevators(List elevators)
  {
    this.elevators = elevators;
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
   public int getElevatorListCount () 
  {
    return (elevators == null) ? 0 : elevators.size();
  }
  public Map getElevatorAppMap()
  {
    if(elevatorAppMap == null) 
    {
      elevatorAppMap = new HashMap();
    }
    return elevatorAppMap;
  }

  public void setElevatorAppMap(Map elevatorAppMap)
  {
    this.elevatorAppMap = elevatorAppMap;
  }
  public int getElevatorAppMapCount()
  {
    return (this.elevatorAppMap == null) ? 0 : elevatorAppMap.size();
  }

  public void addElevatorApp(elevatorApplication elevatorApp,elevator elev,ShoppingCart cart) throws Exception
  {
    if(elevatorApp == null) return;
      StringBuffer sb = new StringBuffer("ELEVAPP");
      Map map = this.getElevatorAppMap();
      if(elevatorApp.getStateNumber().equals("New")) { 
        elevatorApp.setElevAppKey(sb.toString()+elevatorApp.getStateNumber()+cart.getAppRunningCount());
      }
      else
      {
      elevatorApp.setElevAppKey(sb.toString()+elevatorApp.getStateNumber());
      }
       map.put(elevatorApp.getElevAppKey(),elevatorApp);   
  }
  public elevatorApplication getElevatorApp(String key)
  {
    
    if(key == null || elevatorAppMap == null ) {
       
      return new elevatorApplication();
    }
    elevatorApplication elevatorApp = (elevatorApplication) elevatorAppMap.get(key);
    return (elevatorApp == null) ? new elevatorApplication() : elevatorApp;
  }
  
  
  public void removeElevatorApp(String key) throws Exception
  {
    if(key == null || elevatorAppMap == null ) return;
     elevatorAppMap.remove(key);
  }
  public int getOwnerAppCount()
  {
    return (this.elevatorAppMap == null) ? 0 : elevatorAppMap.size();
  }
}