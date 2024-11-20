package code.control.form;

import code.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
import main.to.*;
import main.control.form.*;
public class SealOrderForm  extends CompleteContactForm
{ private int mseals;
  private int pseals;
  private int facilityId;
  private int orderId;
  private String orderDate;
  private int msealsFrom;
  private int msealsTo;
  private int psealsFrom;
  private int psealsTo;
  public SealOrderForm()
  {
  }
public String getOrderDate()
  { 
   return orderDate;
  }
public void setOrderDate(String orderDate)
  { 
  this.orderDate = orderDate;
  }
public int getOrderId()
  { 
   return orderId;
  }
public void setOrderId(int orderId)
  { 
  this.orderId = orderId;
  }
public int getFacilityId()
  { 
   return facilityId;
  }
public void setFacilityId(int facilityId)
  { 
  this.facilityId = facilityId;
  }
public int getMseals()
  { 
   return mseals;
  }
public void setMseals(int mseals)
  { 
  this.mseals = mseals;
  }
public int getPseals()
  { 
   return pseals;
  }
public void setPseals(int pseals)
  { 
  this.pseals = pseals;
  }

public int getMsealsFrom()
  { 
   return msealsFrom;
  }
public void setMsealsFrom(int msealsFrom)
  { 
  this.msealsFrom = msealsFrom;
  }
public int getMsealsTo()
  { 
   return msealsTo;
  }
public void setMsealsTo(int msealsTo)
  { 
  this.msealsTo = msealsTo;
  }
public int getPsealsFrom()
  { 
   return psealsFrom;
  }
public void setPsealsFrom(int psealsFrom)
  { 
  this.psealsFrom = psealsFrom;
  }
public int getPsealsTo()
  { 
   return psealsTo;
  }
public void setPsealsTo(int psealsTo)
  { 
  this.psealsTo = psealsTo;
  }
   public void setProperties(SealOrder seal) throws Exception
  {
    
    this.setOrderDate(seal.getOrderDateString());
    this.setFacilityId(seal.getFacilityId());
    this.setMseals(seal.getMseals());
    this.setMsealsFrom(seal.getMsealsFrom());
    this.setMsealsTo(seal.getMsealsTo());
    this.setOrderId(seal.getOrderId());
    this.setPseals(seal.getPseals());
    this.setPsealsFrom(seal.getPsealsFrom());
    this.setPsealsTo(seal.getPsealsTo());
    
  }
 public SealOrder  getsealOrder() throws Exception
  {
    
    SealOrder seal = new SealOrder ();
    seal.setOrderDateString(getOrderDate());
    seal.setFacilityId(getFacilityId());
    seal.setMseals(getMseals());
    seal.setMsealsFrom(getMsealsFrom());
    seal.setMsealsTo(getMsealsTo());
    seal.setOrderId(getOrderId());
    seal.setPseals(getPseals());
    seal.setPsealsFrom(getPsealsFrom());
    seal.setPsealsTo(getPsealsTo());
    
    
    return(seal);
  }
}
