package magazine.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;




public class PermitsToPrint  extends HsCompleteContact implements Serializable
 { private String ownerName;
   private String ownAddress1;
   private String ownAddress2;
   private String ownCity;
   private String ownState;
   private String ownZip;
   private String magAddress1;
   private String magAddress2;
   private String magCity;
   private String magState;
   private String magZip;
   private String magTownShip;
   private String magCounty;
   private String magCountyName;
   private String magExpType;
   private int magMinQuantity;
   private String magType;
   private Date magIssueDate;
   private Date magExpDate;
   private String magPermitNumber;
   private String magIdNumber;
   private String inspector;
  public PermitsToPrint()
  {
  }
    public String getMagIdNumber()
  { 
   return magIdNumber;
  }
public void setMagIdNumber(String magIdNumber)
  { 
  this.magIdNumber = magIdNumber;
  }
   public String getMagPermitNumber()
  { 
   return magPermitNumber;
  }
public void setMagPermitNumber(String magPermitNumber)
  { 
  this.magPermitNumber = magPermitNumber;
  }
  public String getOwnAddress1()
  { 
   return ownAddress1;
  }
public void setOwnAddress1(String ownAddress1)
  { 
  this.ownAddress1 = ownAddress1;
  }
public String getOwnAddress2()
  { 
   return ownAddress2;
  }
public void setOwnAddress2(String ownAddress2)
  { 
  this.ownAddress2 = ownAddress2;
  }
public String getOwnCity()
  { 
   return ownCity;
  }
public void setOwnCity(String ownCity)
  { 
  this.ownCity = ownCity;
  }
public String getOwnState()
  { 
   return ownState;
  }
public void setOwnState(String ownState)
  { 
  this.ownState = ownState;
  }
public String getOwnZip()
  { 
   return ownZip;
  }
public void setOwnZip(String ownZip)
  { 
  this.ownZip = ownZip;
  }
public String getOwnerName()
  { 
   return ownerName;
  }
public void setOwnerName(String ownerName)
  { 
  this.ownerName = ownerName;
  }
  public Date getMagExpDate()
  { 
   return magExpDate;
  }
public void setMagExpDate(Date magExpDate)
  { 
  this.magExpDate = magExpDate;
  }
public Date getMagIssueDate()
  { 
   return magIssueDate;
  }
public void setMagIssueDate(Date magIssueDate)
  { 
  this.magIssueDate = magIssueDate;
  }
public String getMagAddress1()
  { 
   return magAddress1;
  }
public void setMagAddress1(String magAddress1)
  { 
  this.magAddress1 = magAddress1;
  }
public String getMagAddress2()
  { 
   return magAddress2;
  }
public void setMagAddress2(String magAddress2)
  { 
  this.magAddress2 = magAddress2;
  }
public String getMagCity()
  { 
   return magCity;
  }
public void setMagCity(String magCity)
  { 
  this.magCity = magCity;
  }
public String getMagCounty()
  { 
   return magCounty;
  }
public void setMagCounty(String magCounty)
  { 
  this.magCounty = magCounty;
  }
public String getMagExpType()
  { 
   return magExpType;
  }
public void setMagExpType(String magExpType)
  { 
  this.magExpType = magExpType;
  }
public int getMagMinQuantity()
  { 
   return magMinQuantity;
  }
public void setMagMinQuantity(int magMinQuantity)
  { 
  this.magMinQuantity = magMinQuantity;
  }
public String getMagState()
  { 
   return magState;
  }
public void setMagState(String magState)
  { 
  this.magState = magState;
  }
public String getMagTownShip()
  { 
   return magTownShip;
  }
public void setMagTownShip(String magTownShip)
  { 
  this.magTownShip = magTownShip;
  }
public String getMagType()
  { 
   return magType;
  }
public void setMagType(String magType)
  { 
  this.magType = magType;
  }
public String getMagZip()
  { 
   return magZip;
  }
public void setMagZip(String magZip)
  { 
  this.magZip = magZip;
  }
  public void setMagExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
magExpDate = formatter.parse(dateParam);
} catch (Exception e)
{magExpDate = null; }
}
public String getMagExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(magExpDate == null)
{ return ""; }
else { return formatter.format(magExpDate); }
}
public void setMagIssueDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
magIssueDate = formatter.parse(dateParam);
} catch (Exception e)
{magIssueDate = null; }
}
public String getMagIssueDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(magIssueDate == null)
{ return ""; }
else { return formatter.format(magIssueDate); }
}

public String getMagIssueDateStringMonth()
{
DateFormat formatter = new SimpleDateFormat("MMMMMMMMM");
if(magIssueDate == null)
{ return ""; }
else { return formatter.format(magIssueDate); }
}
public String getMagIssueDateStringDay()
{
DateFormat formatter = new SimpleDateFormat("dd");
if(magIssueDate == null)
{ return ""; }
else { return formatter.format(magIssueDate); }
}
public String getMagIssueDateStringYear()
{
DateFormat formatter = new SimpleDateFormat("yyyy");
if(magIssueDate == null)
{ return ""; }
else { return formatter.format(magIssueDate); }
}
public String getMagExpDateStringMonth()
{
DateFormat formatter = new SimpleDateFormat("MMMMMMMMM");
if(magExpDate == null)
{ return ""; }
else { return formatter.format(magExpDate); }
}
public String getMagExpDateStringDay()
{
DateFormat formatter = new SimpleDateFormat("dd");
if(magExpDate == null)
{ return ""; }
else { return formatter.format(magExpDate); }
}
public String getMagExpDateStringYear()
{
DateFormat formatter = new SimpleDateFormat("yyyy");
if(magExpDate == null)
{ return ""; }
else { return formatter.format(magExpDate); }
}
public String getInspector()
  { 
   return inspector;
  }
public void setInspector(String inspector)
  { 
  this.inspector = inspector;
  }
  public String getMagCountyName()
  { 
   return magCountyName;
  }
public void setMagCountyName(String magCountyName)
  { 
  this.magCountyName = magCountyName;
  }
}