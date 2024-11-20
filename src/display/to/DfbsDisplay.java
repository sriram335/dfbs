package display.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class DfbsDisplay   implements Serializable
{ private String displayIdNumber;
  private Date displayIssueDate;
  private Date displayDateFrom;
  private Date displayDateTo; 
  private String displayTime;
  private String displayAddress1;
  private String displayAddress2;
  private String displayCity;
  private String displayState;
  private String displayZip;
  private String displayZip2;
  private String displayCounty;
  private String displayCountyName;
  private String displayFd;
  private String displayPhone;
  private String displayFax;
  private String displayEmail;
  private String displayPermitKey;
  private double amount;
  private int displayOwnerId;
  private List inspections;
  private List fileList;
  private String displayOnlineStatus;
  private String displayComments;
  private List displayFees;
  private int displayValid;
  private boolean displayError;
  private boolean displayUploadError;
  private boolean displayDateError;
  private String displayFdEmail;
  private Map displayDatesMap;
  private List displayDatesList;
  private int dateCounter;
  private int datesCount;
  private String shooterName;
  private String companyName;
 public DfbsDisplay()
  {
  }
  public String getShooterName()
  { 
   return shooterName;
  }
  public void setShooterName(String shooterName)
  { 
  this.shooterName = shooterName;
  }
  public String getCompanyName()
  { 
   return companyName;
  }
  public void setCompanyName(String companyName)
  { 
  this.companyName = companyName;
  }
  public String getDisplayFdEmail()
  { 
   return displayFdEmail;
  }
public void setDisplayFdEmail(String displayFdEmail)
  { 
  this.displayFdEmail = displayFdEmail;
  }
   public double getAmount()
  { 
   return amount;
  }
public void setAmount(double amount)
  { 
  this.amount = amount;
  }
 public Date getDisplayDateFrom()
  { 
   return displayDateFrom;
  }
public void setDisplayDateFrom(Date displayDateFrom)
  { 
  this.displayDateFrom = displayDateFrom;
  }
   public Date getDisplayDateTo()
  { 
   return displayDateTo;
  }
public void setDisplayDateTo(Date displayDateTo)
  { 
  this.displayDateTo = displayDateTo;
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
  public boolean getDisplayError()
  { 
   return displayError;
  }
public void setDisplayError(boolean displayError)
  { 
  this.displayError = displayError;
  }
   public boolean getDisplayDateError()
  { 
   return displayDateError;
  }
public void setDisplayDateError(boolean displayDateError)
  { 
  this.displayDateError = displayDateError;
  }
  public boolean getDisplayUploadError()
  { 
   return displayUploadError;
  }
public void setDisplayUploadError(boolean displayUploadError)
  { 
  this.displayUploadError = displayUploadError;
  }
 public String getDisplayTime()
  { 
   return displayTime;
  }
public void setDisplayTime(String displayTime)
  { 
  this.displayTime = displayTime;
  }
  public String getDisplayEmail()
  { 
   return displayEmail;
  }
public void setDisplayEmail(String displayEmail)
  { 
  this.displayEmail = displayEmail;
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

public String getDisplayIdNumber()
  { 
   return displayIdNumber;
  }
public void setDisplayIdNumber(String displayIdNumber)
  { 
  this.displayIdNumber = displayIdNumber;
  }

public String getDisplayState()
  { 
   return displayState;
  }
public void setDisplayState(String displayState)
  { 
  this.displayState = displayState;
  }
public String getDisplayZip()
  { 
   return displayZip;
  }
public void setDisplayZip(String displayZip)
  { 
  this.displayZip = displayZip;
  }
public String getDisplayZip2()
  { 
   return displayZip2;
  }
public void setDisplayZip2(String displayZip2)
  { 
  this.displayZip2 = displayZip2;
  }
 
public String getDisplayCounty()
  { 
   return displayCounty;
  }
public void setDisplayCounty(String displayCounty)
  { 
  this.displayCounty = displayCounty;
  }
  public String getDisplayCountyName()
  { 
   return displayCountyName;
  }
public void setDisplayCountyName(String displayCountyName)
  { 
  this.displayCountyName = displayCountyName;
  }
public String getDisplayFd()
  { 
   return displayFd;
  }
public void setDisplayFd(String displayFd)
  { 
  this.displayFd = displayFd;
  }


public String getDisplayFax()
  { 
   return displayFax;
  }
public void setDisplayFax(String displayFax)
  { 
  this.displayFax = displayFax;
  }

public String getDisplayPhone()
  { 
   return displayPhone;
  }
public void setDisplayPhone(String displayPhone)
  { 
  this.displayPhone = displayPhone;
  }
  
  public void setDisplayDateFromString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
displayDateFrom = formatter.parse(dateParam);
} catch (Exception e)
{displayDateFrom = null; }
}
public String getDisplayDateFromString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(displayDateFrom == null)
{ return ""; }
else { return formatter.format(displayDateFrom); }
}
 public void setDisplayDateToString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
displayDateTo = formatter.parse(dateParam);
} catch (Exception e)
{displayDateFrom = null; }
}
public String getDisplayDateToString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(displayDateTo == null)
{ return ""; }
else { return formatter.format(displayDateTo); }
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


  
  public String getDisplayPermitKey()
  { 
   return displayPermitKey;
  }
  public void setDisplayPermitKey(String displayPermitKey)
  { 
  this.displayPermitKey = displayPermitKey;
  }

public void setDisplayOwnerId(int displayOwnerId)
  { 
  this.displayOwnerId = displayOwnerId;
  }
   public int getDisplayOwnerId()
  { 
   return displayOwnerId;
  }
  
  public boolean isNew() 
  {
    return (getDisplayIdNumber() == null || getDisplayIdNumber().equals("")) ? true : false;
  }

  public List getInspections()
  {
    if(inspections == null) 
    {
      inspections = new ArrayList();
    } 
    return inspections;
  }

  public void setInspections(List inspections)
  {
    this.inspections = inspections;
  }
  
  public List getFileList()
  {
    if(fileList == null) 
    {
      fileList = new ArrayList();
    } 
    return fileList;
  }

  public void setFileList(List fileList)
  {
    this.fileList = fileList;
  }
 
 
  public void setDisplayOnlineStatus(String displayOnlineStatus)
  { 
  this.displayOnlineStatus = displayOnlineStatus;
  }
   public String getDisplayOnlineStatus()
  { 
   return displayOnlineStatus;
  } 
 
   public void setDisplayComments(String displayComments)
  { 
  this.displayComments = displayComments;
  }
   public String getDisplayComments()
  { 
   return displayComments;
  } 
 public List getDisplayFees()
  {
    if(displayFees == null) 
    {
      fileList = new ArrayList();
    } 
    return displayFees;
  }

  public void setDisplayFees(List displayFees)
  {
    this.displayFees = displayFees;
  }
  public void setDisplayValid(int displayValid)
  { 
  this.displayValid = displayValid;
  }
   public int getDisplayValid()
  { 
   return displayValid;
  }
  public Map getDisplayDatesMap()
  {
    if(displayDatesMap == null) 
    {
      displayDatesMap = new HashMap();
    }
    return displayDatesMap;
  }

  public void setDisplayDatesMap(Map displayDatesMap)
  {
    this.displayDatesMap = displayDatesMap;
  }
  public int getDisplayDatesMapCount()
  {
    return (this.displayDatesMap == null) ? 0 : displayDatesMap.size();
  }

  public void addDate(DisplayDates displayDate) 
  {
    if(displayDate == null) return;
    
       StringBuffer sb = new StringBuffer("NEW");
      sb.append(getDateCounter() + 1);
      Map map = this.getDisplayDatesMap();
      displayDate.setApplicationKey(sb.toString());
      map.put(sb.toString(),displayDate);
      setDateCounter(getDateCounter() + 1);
    
    this.setDatesCount(getDatesCount()+1);
   
  }
  public DisplayDates getDisplayDate(String key)
  {
    
    if(key == null || displayDatesMap == null ) {
       
      return new DisplayDates();
    }
    DisplayDates displayDate = (DisplayDates) displayDatesMap.get(key);
    return (displayDate == null) ? new DisplayDates() : displayDate;
  }
  
  
  public void removePermit(String key) throws Exception
  {
    if(key == null || displayDatesMap == null ) return;
    
    DisplayDates displayDate = (DisplayDates) displayDatesMap.get(key);
     this.setDatesCount(getDatesCount()-1);
    displayDatesMap.remove(key);
  }
  public void setDateCounter(int dateCounter)
  { 
  this.dateCounter = dateCounter;
  }
   public int getDateCounter()
  { 
   return dateCounter;
  }
  public void setDatesCount(int datesCount)
  { 
  this.datesCount = datesCount;
  }
   public int getDatesCount()
  { 
   return datesCount;
  }
  public List getDisplayDatesList()
  {
    if(displayDatesList == null) 
    {
      displayDatesList = new ArrayList();
    } 
    return displayDatesList;
  }

  public void setDisplayDatesList(List displayDatesList)
  {
    this.displayDatesList = displayDatesList;
  }
}