package hazmat.control.form;
import hazmat.to.*;
import main.control.form.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;



public class HazmatCarrierForm  extends CompleteContactForm
{private int carrierId;
 private String carrierName;
 private String carrierContact;
 private String carrierTitle;
 private String carrierPhone;
 private String carrierFax;
 private String carrierEmail;
 private int orgId;
 private String key;
 private String applicationKey;
  public HazmatCarrierForm()
  {
  }
  public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
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
   public String getCarrierName()
  { 
   return carrierName;
  }
public void setCarrierName(String carrierName)
  { 
  this.carrierName = carrierName;
  }
 public HazmatCarrier getHazmatCarrier() throws Exception
  {
    
    HazmatCarrier carrier = new HazmatCarrier ();
    carrier.setCarrierName(getCarrierName());
    carrier.setCarrierContact(getCarrierContact());
    carrier.setCarrierEmail(getCarrierEmail());
    carrier.setCarrierFax(getCarrierFax());
    carrier.setCarrierId(getCarrierId());
    carrier.setOrgId(getOrgId());
    carrier.setCarrierPhone(getCarrierPhone());
    carrier.setCarrierTitle(getCarrierTitle());
    carrier.setApplicationKey(getApplicationKey());
 return carrier;
  }
 public void setProperties(HazmatCarrier carrier) throws Exception
  { 
    this.setCarrierName(carrier.getCarrierName());
    this.setCarrierContact(carrier.getCarrierContact());
    this.setCarrierTitle(carrier.getCarrierTitle());
    this.setCarrierEmail(carrier.getCarrierEmail());
    this.setCarrierFax(carrier.getCarrierFax());
    this.setCarrierId(carrier.getCarrierId());
    this.setCarrierPhone(carrier.getCarrierPhone());
    this.setOrgId(carrier.getOrgId());
    this.setApplicationKey(carrier.getApplicationKey());
    
  }
    public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }
   public void setUpdatedProperties(HazmatCarrier Updatedcarrier,HazmatCarrier carrier) throws Exception
  { 
    carrier.setCarrierName(Updatedcarrier.getCarrierName());
    carrier.setCarrierContact(Updatedcarrier.getCarrierContact());
    carrier.setCarrierEmail(Updatedcarrier.getCarrierEmail());
    carrier.setCarrierFax(Updatedcarrier.getCarrierFax());
    carrier.setCarrierId(Updatedcarrier.getCarrierId());
    carrier.setOrgId(Updatedcarrier.getOrgId());
    carrier.setCarrierPhone(Updatedcarrier.getCarrierPhone());
    carrier.setCarrierTitle(Updatedcarrier.getCarrierTitle());
   
    
  }
}
