package hazmat.to;

import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class HazmatPermit  extends HsCompleteContact implements Serializable
{ private int orgId;
  private String orgName;
  private String orgContact;
  private String orgTitle;
  private String orgAddress1;
  private String orgAddress2;
  private String orgCity;
  private String orgState;
  private String orgZip;
  private String orgZip2;
  private String orgPhone;
  private String orgFax;
  private String orgEmail;
  private String orgCounty;
  private List carriers;
  private Map carriersMap;
  private int carrierCounter;
  private int carriersCount;
  private int totalShipments;
  private int receiptId;
  private double amount;
  private String checkoutId;
  private String paymentType;
  public HazmatPermit()
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
    public String getPaymentType()
  { 
   return paymentType;
  }
public void setPaymentType(String paymentType)
  { 
  this.paymentType = paymentType;
  }
   public String getCheckoutId()
  { 
   return checkoutId;
  }
public void setCheckoutId(String checkoutId)
  { 
  this.checkoutId = checkoutId;
  }
  public String getOrgAddress1()
  { 
   return orgAddress1;
  }
public void setOrgAddress1(String orgAddress1)
  { 
  this.orgAddress1 = orgAddress1;
  }
public String getOrgAddress2()
  { 
   return orgAddress2;
  }
public void setOrgAddress2(String orgAddress2)
  { 
  this.orgAddress2 = orgAddress2;
  }
public String getOrgCity()
  { 
   return orgCity;
  }
public void setOrgCity(String orgCity)
  { 
  this.orgCity = orgCity;
  }
  public String getOrgCounty()
    { 
     return orgCounty;
    }
  public void setOrgCounty(String orgCounty)
    { 
    this.orgCounty = orgCounty;
    }
public String getOrgContact()
  { 
   return orgContact;
  }
public void setOrgContact(String orgContact)
  { 
  this.orgContact = orgContact;
  }
public String getOrgEmail()
  { 
   return orgEmail;
  }
public void setOrgEmail(String orgEmail)
  { 
  this.orgEmail = orgEmail;
  }
public String getOrgFax()
  { 
   return orgFax;
  }
public void setOrgFax(String orgFax)
  { 
  this.orgFax = orgFax;
  }
public String getOrgName()
  { 
   return orgName;
  }
public void setOrgName(String orgName)
  { 
  this.orgName = orgName;
  }
public String getOrgPhone()
  { 
   return orgPhone;
  }
public void setOrgPhone(String orgPhone)
  { 
  this.orgPhone = orgPhone;
  }
public String getOrgState()
  { 
   return orgState;
  }
public void setOrgState(String orgState)
  { 
  this.orgState = orgState;
  }
public String getOrgTitle()
  { 
   return orgTitle;
  }
public void setOrgTitle(String orgTitle)
  { 
  this.orgTitle = orgTitle;
  }
public String getOrgZip()
  { 
   return orgZip;
  }
public void setOrgZip(String orgZip)
  { 
  this.orgZip = orgZip;
  }
public String getOrgZip2()
  { 
   return orgZip2;
  }
public void setOrgZip2(String orgZip2)
  { 
  this.orgZip2 = orgZip2;
  }
public int getOrgId()
  { 
   return orgId;
  }
public void setOrgId(int orgId)
  { 
  this.orgId = orgId;
  }
   public int getCarriersCount () 
  {
    return (carriers == null) ? 0 : carriers.size();
  }

  public List getCarriers()
  {
    if(carriers == null) 
    {
      carriers = new ArrayList();
    } 
    return carriers;
  }

  public void setCarriers(List carriers)
  {
    this.carriers = carriers;
  }
    public Map getCarriersMap()
  {
    if(carriersMap == null) 
    {
      carriersMap = new HashMap();
    }
    return carriersMap;
  }

  public void setCarriersMap(Map carriersMap)
  {
    this.carriersMap = carriersMap;
  }
  public int getCarriersMapCount()
  {
    return (this.carriersMap == null) ? 0 : carriersMap.size();
  }

  public void addCarrier(HazmatCarrier carrier) throws Exception
  {
    if(carrier == null) return;
    
    carrier.setOrgId(this.getOrgId());
    
    
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getCarrierCounter() + 1);
      Map map = this.getCarriersMap();
      carrier.setApplicationKey(sb.toString());
      map.put(sb.toString(),carrier);
      setCarrierCounter(getCarrierCounter() + 1);
     
   
    this.addCount(carrier);
  }
  public HazmatCarrier getCarrier(String key)
  {
    
    if(key == null || carriersMap == null ) {
       
      return new HazmatCarrier();
    }
    HazmatCarrier carrier = (HazmatCarrier) carriersMap.get(key);
    return (carrier == null) ? new HazmatCarrier() : carrier;
  }
  
  
  public void removeCarrier(String key) throws Exception
  {
    if(key == null || carriersMap == null ) return;
    
    HazmatCarrier carrier = (HazmatCarrier) carriersMap.get(key);
    this.removeCount(carrier);
    carriersMap.remove(key);
  }
    public int getCarrierCounter()
  {
    return carrierCounter;
  }

  public void setCarrierCounter(int carrierCounter)
  {
    this.carrierCounter = carrierCounter;
  }
   private void addCount(HazmatCarrier carrier) throws Exception
  {
   
      this.carriersCount++; 
      
  }
  
  private void removeCount(HazmatCarrier carrier) throws Exception
  {
    
      this.carriersCount--; 
      
  }
    public int getTotalShipments()
  {
    return totalShipments;
  }

  public void setTotalShipments(int totalShipments)
  {
    this.totalShipments = totalShipments;
  }
     public int getReceiptId()
  {
    return receiptId;
  }

  public void setReceiptId(int receiptId)
  {
    this.receiptId = receiptId;
  }
}
