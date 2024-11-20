package amuseRide.to;

import amuseRide.to.*;

import hs.to.*;


import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DfbsOwner  implements Serializable
{
  
  private List rides;
  private Map ridesMap;
  private List insList;
  private Map insMap;
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
  private int ridesCount;
  private double amount;
  private int ownerStateId ;
  private boolean ownerError;
  private String ownerKey;
  private List fileList;
  public DfbsOwner() 
  {
    
  }
  public int getFileListCount () 
  {
    return (fileList == null) ? 0 : fileList.size();
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
  public String getOwnerKey() 
  {
    return ownerKey;
  }
  public void setOwnerKey(String ownerKey) 
  {
   this.ownerKey = ownerKey;
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

  public Map getRidesMap()
  {
    if(ridesMap == null) 
    {
      ridesMap = new HashMap();
    }
    return ridesMap;
  }

  public void setRidesMap(Map ridesMap)
  {
    this.ridesMap = ridesMap;
  }
  public int getRidesMapCount()
  {
    return (this.ridesMap == null) ? 0 : ridesMap.size();
  }

  public void addRide(amuseRide ride) throws Exception
  {
    if(ride == null) return;
    
    
    if(ride.isNew()) 
    {
      StringBuffer sb = new StringBuffer(ride.getSerialNumber());
      sb.append(ridesCount + 1);
      Map map = this.getRidesMap();
      ride.setAmuseApplicationKey(sb.toString());
      map.put(sb.toString(),ride);
    } 
    else 
    {
      Map map = this.getRidesMap();
      ride.setAmuseApplicationKey(ride.getSerialNumber());
      map.put(ride.getSerialNumber(),ride);
    }
    this.addCount();
  }
  public amuseRide getRide(String key)
  {
    
    if(key == null || ridesMap == null ) {
       
      return new amuseRide();
    }
    amuseRide ride = (amuseRide) ridesMap.get(key);
    return (ride == null) ? new amuseRide() : ride;
  }
  
  
  public void removeRide(String key) throws Exception
  {
    if(key == null || ridesMap == null ) return;
    
    amuseRide ride = ( amuseRide) ridesMap.get(key);
     this.removeCount();
    ridesMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getOwnerId() == 0) ? true : false;
  }

 
 private void addCount() throws Exception
  {
   
      this.ridesCount++; 
      
  }
  
  private void removeCount() throws Exception
  {
    
      this.ridesCount--; 
      
  }
   
 public int getRidesCount()
  {
    return ridesCount;
  }
  public void setRidesCount(int ridesCount)
  {
    this.ridesCount = ridesCount;
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
   public List getRides()
  {
    if(rides == null) 
    {
      rides = new ArrayList();
    } 
    return rides;
  }

  public void setRides(List rides)
  {
    this.rides = rides;
  }
   public void setOwnerStateId(int ownerStateId)
  {
    this.ownerStateId = ownerStateId;
  }

  public int getOwnerStateId()
  {
    return ownerStateId;
  }
 
   public int getRideListCount () 
  {
    return (rides == null) ? 0 : rides.size();
  }
  public Map getInsMap()
  {
    if(insMap == null) 
    {
      insMap = new HashMap();
    }
    return insMap;
  }

  public void setInsMap(Map insMap)
  {
    this.insMap = insMap;
  }
  public int getInsMapCount()
  {
    return (this.insMap == null) ? 0 : insMap.size();
  }

  public void addIns(insurance ins) throws Exception
  {
    if(ins == null) return;
   if(ins.isNew()) 
    {
      StringBuffer sb = new StringBuffer(ins.getPolicyNumber());
      Map map = this.getInsMap();
     ins.setInsApplicationKey(sb.toString());
      map.put(sb.toString(),ins);
    } 
    else 
    {
      Map map = this.getInsMap();
      ins.setInsApplicationKey(ins.getPolicyNumber());
      map.put(ins.getInsApplicationKey(),ins);
    }
   
  }
  public insurance getIns(String key)
  {
    
    if(key == null || insMap == null ) {
       
      return new insurance();
    }
     insurance ins = ( insurance) insMap.get(key);
    return (ins == null) ? new insurance() : ins;
  }
  
  
  public void removeIns(String key) throws Exception
  {
    if(key == null || insMap == null ) return;
    
    insurance ins = ( insurance) insMap.get(key);
     this.removeCount();
    insMap.remove(key);
  }
  public List getInsList()
  {
   if(insList == null) 
   {
     insList = new ArrayList();
   } 
   return insList;
  }

  public void setInsList(List insList)
  {
   this.insList = insList;
  }
 
}