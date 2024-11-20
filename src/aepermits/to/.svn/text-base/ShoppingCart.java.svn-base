package aepermits.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ShoppingCart   implements Serializable
{ private List ownerList;
  private Map ownerMap;
  private int ownerCounter;
  private int ownerCount;
  private int totalOwners;
  private int totalPermits;
  private double amount;
  private int receiptId;
  private String paymentType;
  private String checkoutId;
  public ShoppingCart()
  {
  }
   public String getCheckoutId()
  {
    return checkoutId;
  }

  public void setCheckoutId(String checkoutId)
  {
    this.checkoutId = checkoutId;
  }
   public Map getOwnerMap()
  {
    if(ownerMap == null) 
    {
      ownerMap = new HashMap();
    }
    return ownerMap;
  }

  public void setOwnerMap(Map ownerMap)
  {
    this.ownerMap = ownerMap;
  }
  public int getOwnerMapCount()
  {
    return (this.ownerMap == null) ? 0 : ownerMap.size();
  }

  public void addOwner(DfbsOwner owner) throws Exception
  {
    
    if(owner == null) return;
    
    if(owner.isNew()) 
    {
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getOwnerCounter() + 1);
      Map map = this.getOwnerMap();
      //owner.setOwnerKey(sb.toString());
      map.put(sb.toString(),owner);
      setOwnerCounter(getOwnerCounter() + 1);
    } 
    else 
    {
      Map map = this.getOwnerMap();
     // owner.setOwnerKey(Integer.toString(owner.getOwnerId()));
      map.put(Integer.toString(owner.getOwnerId()),owner);
    }
    this.addCount(owner);
  }
  public DfbsOwner getOwner(String key)
  {
    
    if(key == null || ownerMap == null ) {
       
      return new DfbsOwner();
    }
    DfbsOwner owner = (DfbsOwner) ownerMap.get(key);
    return (owner == null) ? new DfbsOwner() : owner;
  }
  
  
  public void removeOwner(String key) throws Exception
  {
    if(key == null || ownerMap == null ) return;
    
    DfbsOwner owner = (DfbsOwner) ownerMap.get(key);
   // setAmount(getAmount()-owner.getAmount());
    this.removeCount(owner);
    ownerMap.remove(key);
    setOwnerCounter(getOwnerCounter() - 1);
    //setAmount(getAmount()-owner.getAmount());
  }
  
   public void addCount(DfbsOwner owner) throws Exception
  {
   
      this.ownerCount++; 
      
  }
  
  public void removeCount(DfbsOwner owner) throws Exception
  {
    
      this.ownerCount--; 
      
  }
  public int getOwnerCounter()
  {
    return ownerCounter;
  }

  public void setOwnerCounter(int ownerCounter)
  {
    this.ownerCounter = ownerCounter;
  }
   public int getOwnerCount()
  {
    return ownerCount;
  }

  public void setOwnerCount(int ownerCount)
  {
    this.ownerCount = ownerCount;
  }
  public double getAmount()
  { 
   return amount;
  }
public void setAmount(double amount)
  { 
  this.amount = amount;
  }
public int getTotalOwners()
  { 
   return totalOwners;
  }
public void setTotalOwners(int totalOwners)
  { 
  this.totalOwners = totalOwners;
  }
public int getTotalPermits()
  { 
   return totalPermits;
  }
public void setTotalPermits(int totalPermits)
  { 
  this.totalPermits = totalPermits;
  }
 public String getPaymentType()
  { 
   return paymentType;
  }
public void setPaymentType(String paymentType)
  { 
  this.paymentType = paymentType;
  }
public int getReceiptId()
  { 
   return receiptId;
  }
public void setReceiptId(int receiptId)
  { 
  this.receiptId = receiptId;
  } 
   public List getOwnerList()
  {
    if(ownerList == null) 
    {
      ownerList = new ArrayList();
    } 
    return ownerList;
  }

  public void setOwnerList(List ownerList)
  {
    this.ownerList = ownerList;
  }
 
}