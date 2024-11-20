package ems.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;

public class EmsInstAgmts implements Serializable
{ private int agreementId;
  private int institutionId;
  private Date effectiveDate;
  private Date expirationDate;
  private String name;
  private String remarks;
  
  public EmsInstAgmts()
  {
  }
  public Date getEffectiveDate()
  { 
   return effectiveDate;
  }
public void setEffectiveDate(Date effectiveDate)
  { 
  this.effectiveDate = effectiveDate;
  }
public Date getExpirationDate()
  { 
   return expirationDate;
  }
public void setExpirationDate(Date expirationDate)
  { 
  this.expirationDate = expirationDate;
  }
public String getName()
  { 
   return name;
  }
public void setName(String name)
  { 
  this.name = name;
  }
public String getRemarks()
  { 
   return remarks;
  }
public void setRemarks(String remarks)
  { 
  this.remarks = remarks;
  }
public int getAgreementId()
  { 
   return agreementId;
  }
public void setAgreementId(int agreementId)
  { 
  this.agreementId = agreementId;
  }
public int getInstitutionId()
  { 
   return institutionId;
  }
public void setInstitutionId(int institutionId)
  { 
  this.institutionId = institutionId;
  }
  
  public void setEffectiveDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
effectiveDate = formatter.parse(dateParam);
} catch (Exception e)
{effectiveDate = null; }
}
public String getEffectiveDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(effectiveDate == null)
{ return ""; }
else { return formatter.format(effectiveDate); }
}
public void setExpirationDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
expirationDate = formatter.parse(dateParam);
} catch (Exception e)
{expirationDate = null; }
}
public String getExpirationDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(expirationDate == null)
{ return ""; }
else { return formatter.format(expirationDate); }
}
}