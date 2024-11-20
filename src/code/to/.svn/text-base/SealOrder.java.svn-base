package code.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;






public class SealOrder implements Serializable
{ private int mseals;
  private int pseals;
  private int facilityId;
  private int orderId;
  private Date orderDate;
  private int msealsFrom;
  private int msealsTo;
  private int psealsFrom;
  private int psealsTo;
  private List sealUsage;
  public SealOrder()
  {
  }
  public Date getOrderDate()
  { 
   return orderDate;
  }
public void setOrderDate(Date orderDate)
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
 public void setOrderDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
orderDate = formatter.parse(dateParam);
} catch (Exception e)
{orderDate = null; }
}
public String getOrderDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(orderDate == null)
{ return ""; }
else { return formatter.format(orderDate); }
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
   public List getSealUsage()
  {
    if(sealUsage == null) 
    {
      sealUsage = new ArrayList();
    } 
    return sealUsage;
  }

  public void setSealUsage(List sealUsage)
  {
    this.sealUsage = sealUsage;
  }
}
