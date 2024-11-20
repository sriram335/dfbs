package code.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class SealUsage implements Serializable
{ private int facilityId;
  private String sealNumber;
  private Date sealUsedDate;
  private Date sealInspDate;
  private String sealRelNumber;
  private Date sealUpdateDate;
  private String sealUnitNumber;
  private int inspectorId;
  private int companyId;
  private String inspectorName;
  private String companyName;
  private int orderId;
  public SealUsage()
  {
  }
  public Date getSealUpdateDate()
  { 
   return sealUpdateDate;
  }
public void setSealUpdateDate(Date sealUpdateDate)
  { 
  this.sealUpdateDate = sealUpdateDate;
  }
   public Date getSealInspDate()
  { 
   return sealInspDate;
  }
public void setSealInspDate(Date sealInspDate)
  { 
  this.sealInspDate = sealInspDate;
  }
public Date getSealUsedDate()
  { 
   return sealUsedDate;
  }
public void setSealUsedDate(Date sealUsedDate)
  { 
  this.sealUsedDate = sealUsedDate;
  }
public int getInspectorId()
  { 
   return inspectorId;
  }
public void setInspectorId(int inspectorId)
  { 
  this.inspectorId = inspectorId;
  }
  public int getOrderId()
  { 
   return orderId;
  }
public void setOrderId(int orderId)
  { 
  this.orderId = orderId;
  }
public String getSealNumber()
  { 
   return sealNumber;
  }
public void setSealNumber(String sealNumber)
  { 
  this.sealNumber = sealNumber;
  }
public String getSealRelNumber()
  { 
   return sealRelNumber;
  }
public void setSealRelNumber(String sealRelNumber)
  { 
  this.sealRelNumber = sealRelNumber;
  }
public String getSealUnitNumber()
  { 
   return sealUnitNumber;
  }
public void setSealUnitNumber(String sealUnitNumber)
  { 
  this.sealUnitNumber = sealUnitNumber;
  }
public int getFacilityId()
  { 
   return facilityId;
  }
public void setFacilityId(int facilityId)
  { 
  this.facilityId = facilityId;
  }
  public void setSealUpdateDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
sealUpdateDate = formatter.parse(dateParam);
} catch (Exception e)
{sealUpdateDate = null; }
}
public String getSealUpdateDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(sealUpdateDate == null)
{ return ""; }
else { return formatter.format(sealUpdateDate); }
}
public void setSealUsedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
sealUsedDate = formatter.parse(dateParam);
} catch (Exception e)
{sealUsedDate = null; }
}
public String getSealUsedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(sealUsedDate == null)
{ return ""; }
else { return formatter.format(sealUsedDate); }
}
public void setSealInspDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
sealInspDate = formatter.parse(dateParam);
} catch (Exception e)
{sealInspDate = null; }
}
public String getSealInspDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(sealInspDate == null)
{ return ""; }
else { return formatter.format(sealInspDate); }
}

public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
  public String getCompanyName()
  { 
   return companyName;
  }
public void setCompanyName(String companyName)
  { 
  this.companyName = companyName;
  }
public String getInspectorName()
  { 
   return inspectorName;
  }
public void setInspectorName(String inspectorName)
  { 
  this.inspectorName = inspectorName;
  }
//end
}