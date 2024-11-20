package display.to;

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
   private String displayAddress1;
   private String displayAddress2;
   private String displayCity;
   private String displayState;
   private String displayZip;
   private String displayTownShip;
   private String displayCounty;
   private String displayExpType;
   private String displayMinQuantity;
   private String displayType;
   private Date displayIssueDate;
   private Date displayExpDate;
   private String displayPermitNumber;
   
  public PermitsToPrint()
  {
  }
   public String getDisplayPermitNumber()
  { 
   return displayPermitNumber;
  }
public void setDisplayPermitNumber(String displayPermitNumber)
  { 
  this.displayPermitNumber = displayPermitNumber;
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
  public Date getDisplayExpDate()
  { 
   return displayExpDate;
  }
public void setDisplayExpDate(Date displayExpDate)
  { 
  this.displayExpDate = displayExpDate;
  }
public Date getDisplayIssueDate()
  { 
   return displayIssueDate;
  }
public void setDisplayIssueDate(Date displayIssueDate)
  { 
  this.displayIssueDate = displayIssueDate;
  }
public String getDisplayAddress1()
  { 
   return displayAddress1;
  }
public void setDisplayAddress1(String displayAddress1)
  { 
  this.displayAddress1 = displayAddress1;
  }
public String getDisplayAddress2()
  { 
   return displayAddress2;
  }
public void setDisplayAddress2(String displayAddress2)
  { 
  this.displayAddress2 = displayAddress2;
  }
public String getDisplayCity()
  { 
   return displayCity;
  }
public void setDisplayCity(String displayCity)
  { 
  this.displayCity = displayCity;
  }
public String getDisplayCounty()
  { 
   return displayCounty;
  }
public void setDisplayCounty(String displayCounty)
  { 
  this.displayCounty = displayCounty;
  }
public String getDisplayExpType()
  { 
   return displayExpType;
  }
public void setDisplayExpType(String displayExpType)
  { 
  this.displayExpType = displayExpType;
  }
public String getDisplayMinQuantity()
  { 
   return displayMinQuantity;
  }
public void setDisplayMinQuantity(String displayMinQuantity)
  { 
  this.displayMinQuantity = displayMinQuantity;
  }
public String getDisplayState()
  { 
   return displayState;
  }
public void setDisplayState(String displayState)
  { 
  this.displayState = displayState;
  }
public String getDisplayTownShip()
  { 
   return displayTownShip;
  }
public void setDisplayTownShip(String displayTownShip)
  { 
  this.displayTownShip = displayTownShip;
  }
public String getDisplayType()
  { 
   return displayType;
  }
public void setDisplayType(String displayType)
  { 
  this.displayType = displayType;
  }
public String getDisplayZip()
  { 
   return displayZip;
  }
public void setDisplayZip(String displayZip)
  { 
  this.displayZip = displayZip;
  }
  public void setDisplayExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
displayExpDate = formatter.parse(dateParam);
} catch (Exception e)
{displayExpDate = null; }
}
public String getDisplayExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(displayExpDate == null)
{ return ""; }
else { return formatter.format(displayExpDate); }
}
public void setDisplayIssueDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
displayIssueDate = formatter.parse(dateParam);
} catch (Exception e)
{displayIssueDate = null; }
}
public String getDisplayIssueDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(displayIssueDate == null)
{ return ""; }
else { return formatter.format(displayIssueDate); }
}

public String getDisplayIssueDateStringMonth()
{
DateFormat formatter = new SimpleDateFormat("MM");
if(displayIssueDate == null)
{ return ""; }
else { return formatter.format(displayIssueDate); }
}
public String getDisplayIssueDateStringDay()
{
DateFormat formatter = new SimpleDateFormat("dd");
if(displayIssueDate == null)
{ return ""; }
else { return formatter.format(displayIssueDate); }
}
public String getDisplayIssueDateStringYear()
{
DateFormat formatter = new SimpleDateFormat("yy");
if(displayIssueDate == null)
{ return ""; }
else { return formatter.format(displayIssueDate); }
}
}