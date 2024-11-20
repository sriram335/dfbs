package hazmat.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class HazmatCarrier   extends HsCompleteContact implements Serializable
{private int carrierId;
 private String carrierName;
 private String carrierContact;
 private String carrierTitle;
 private String carrierPhone;
 private String carrierFax;
 private String carrierEmail;
 private int orgId;
 private List shipments;
 private Map shipmentsMap;
 private String applicationKey;
 private int shipmentCounter;
 private int shipmentsCount;
 private double amount;
  public HazmatCarrier()
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
   public String getCarrierName()
  { 
   return carrierName;
  }
public void setCarrierName(String carrierName)
  { 
  this.carrierName = carrierName;
  }
  public String getCarrierContact()
  { 
   return carrierContact;
  }
public void setCarrierContact(String carrierContact)
  { 
  this.carrierContact = carrierContact;
  }
public String getCarrierEmail()
  { 
   return carrierEmail;
  }
public void setCarrierEmail(String carrierEmail)
  { 
  this.carrierEmail = carrierEmail;
  }
public String getCarrierFax()
  { 
   return carrierFax;
  }
public void setCarrierFax(String carrierFax)
  { 
  this.carrierFax = carrierFax;
  }
public String getCarrierPhone()
  { 
   return carrierPhone;
  }
public void setCarrierPhone(String carrierPhone)
  { 
  this.carrierPhone = carrierPhone;
  }
public String getCarrierTitle()
  { 
   return carrierTitle;
  }
public void setCarrierTitle(String carrierTitle)
  { 
  this.carrierTitle = carrierTitle;
  }
public int getCarrierId()
  { 
   return carrierId;
  }
public void setCarrierId(int carrierId)
  { 
  this.carrierId = carrierId;
  }
public int getOrgId()
  { 
   return orgId;
  }
public void setOrgId(int orgId)
  { 
  this.orgId = orgId;
  }
   public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public boolean isNew() 
  {
    return (this.getApplicationKey() == null || this.getApplicationKey().trim().equals("") ) ? true : false;
  }
   public int getShipmentsCount () 
  {
    return (shipments == null) ? 0 : shipments.size();
  }

  public List getShipments()
  {
    if(shipments == null) 
    {
      shipments = new ArrayList();
    } 
    return shipments;
  }

  public void setShipments(List shipments)
  {
    this.shipments = shipments;
  }
    public Map getShipmentsMap()
  {
    if(shipmentsMap == null) 
    {
      shipmentsMap = new HashMap();
    }
    return shipmentsMap;
  }

  public void setShipmentsMap(Map shipmentsMap)
  {
    this.shipmentsMap = shipmentsMap;
  }
  public int getShipmentsMapCount()
  {
    return (this.shipmentsMap == null) ? 0 : shipmentsMap.size();
  }

  public void addShipment(HazmatShipment shipment) throws Exception
  {
    if(shipment == null) return;
    
    shipment.setCarrierId(this.getCarrierId());
    
    if(shipment.isNew()) 
    {
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getShipmentCounter() + 1);
      Map map = this.getShipmentsMap();
      shipment.setApplicationKey(sb.toString());
      map.put(sb.toString(),shipment);
      setShipmentCounter(getShipmentCounter() + 1);
    } 
    else 
    {
      Map map = this.getShipmentsMap();
      shipment.setApplicationKey(Integer.toString(shipment.getShipmentId()));
      map.put(Integer.toString(shipment.getShipmentId()),shipment);
    }
    this.addCount(shipment);
  }
  public HazmatShipment getShipment(String key)
  {
    
    if(key == null || shipmentsMap == null ) {
       
      return new HazmatShipment();
    }
    HazmatShipment shipment = (HazmatShipment) shipmentsMap.get(key);
    return (shipment == null) ? new HazmatShipment() : shipment;
  }
  
  
  public void removeShipment(String key) throws Exception
  {
    if(key == null || shipmentsMap == null ) return;
    
    HazmatShipment shipment = (HazmatShipment) shipmentsMap.get(key);
    this.removeCount(shipment);
    shipmentsMap.remove(key);
  }
    public int getShipmentCounter()
  {
    return shipmentCounter;
  }

  public void setShipmentCounter(int shipmentCounter)
  {
    this.shipmentCounter = shipmentCounter;
  }
   private void addCount(HazmatShipment shipment) throws Exception
  {
   
      this.shipmentsCount++; 
      
  }
  
  private void removeCount(HazmatShipment shipment) throws Exception
  {
    
      this.shipmentsCount--; 
      
  }
}
