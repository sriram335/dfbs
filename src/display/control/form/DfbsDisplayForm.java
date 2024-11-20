package display.control.form;

import aepermits.to.DfbsEntrPermit;

import display.to.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

public class DfbsDisplayForm extends ActionForm
{private String displayIdNumber;
  private String displayIssueDate;
  private String displayDateFrom;
  private String displayDateTo; 
  private String displayTime;
  private String displayAddress1;
  private String displayAddress2;
  private String displayCity;
  private String displayState;
  private String displayZip;
  private String displayZip2;
  private String displayCounty;
  private String displayFd;
  private String displayPhone;
  private String displayFax;
  private String displayEmail;
  private String displayPermitKey;
  private String displayFdEmail;
  private double amount;
  private int displayOwnerId;
  private String displayOnlineStatus;
  private String displayComments;
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String displayDate1;
  private String displayTime1;
  private String displayDate2;
  private String displayTime2;
  private String displayDate3;
  private String displayTime3;
  private String displayDate4;
  private String displayTime4;
  private String displayDate5;
  private String displayTime5;
  private String displayDate6;
  private String displayTime6;
  private String displayDate7;
  private String displayTime7;
  private String displayDate8;
  private String displayTime8;
  private String displayDate9;
  private String displayTime9;
  private String displayDate10;
  private String displayTime10;
  private String shooterName;
  private String companyName;
  public DfbsDisplayForm()
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
    public double getAmount()
  { 
   return amount;
  }
public void setAmount(double amount)
  { 
  this.amount = amount;
  }
 public String getDisplayDateFrom()
  { 
   return displayDateFrom;
  }
public void setDisplayDateFrom(String displayDateFrom)
  { 
  this.displayDateFrom = displayDateFrom;
  }
   public String getDisplayDateTo()
  { 
   return displayDateTo;
  }
public void setDisplayDateTo(String displayDateTo)
  { 
  this.displayDateTo = displayDateTo;
  }
public String getDisplayIssueDate()
  { 
   return displayIssueDate;
  }
public void setDisplayIssueDate(String displayIssueDate)
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
public String getDisplayFd()
  { 
   return displayFd;
  }
public void setDisplayFd(String displayFd)
  { 
  this.displayFd = displayFd;
  }
public String getDisplayFdEmail()
  { 
   return displayFdEmail;
  }
public void setDisplayFdEmail(String displayFdEmail)
  { 
  this.displayFdEmail = displayFdEmail;
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
  public String getDisplayTime()
  { 
   return displayTime;
  }
public void setDisplayTime(String displayTime)
  { 
  this.displayTime = displayTime;
  }
  public DfbsDisplay getDfbsDisplay() 
  { DfbsDisplay display = new DfbsDisplay();
    display.setDisplayAddress1(getDisplayAddress1());
    display.setDisplayAddress2(getDisplayAddress2());
    display.setDisplayCity(getDisplayCity());
    display.setDisplayComments(getDisplayComments());
    display.setDisplayCounty(getDisplayCounty());
    display.setDisplayDateFromString(getDisplayDateFrom());
    display.setDisplayDateToString(getDisplayDateTo());
    display.setDisplayEmail(getDisplayEmail());
    display.setDisplayFax(getDisplayFax());
    display.setDisplayFd(getDisplayFd());
    display.setDisplayFdEmail(getDisplayFdEmail());
    display.setDisplayIdNumber(getDisplayIdNumber());
    display.setDisplayIssueDateString(getDisplayIssueDate());
    display.setDisplayOnlineStatus(getDisplayOnlineStatus());
    display.setDisplayOwnerId(getDisplayOwnerId());
    display.setDisplayPermitKey(getDisplayPermitKey());
    display.setDisplayPhone(getDisplayPhone());
    display.setDisplayState(getDisplayState());
    display.setDisplayTime(getDisplayTime());
    display.setDisplayZip(getDisplayZip());
    display.setDisplayZip2(getDisplayZip2());
    display.setCompanyName(getCompanyName());
    display.setShooterName(getShooterName());
    display.setDisplayDatesMap(null);
    display.setDisplayDatesList(null);
    List datesList = new ArrayList();
    if (this.getDisplayDate1().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate1());
      ddates.setDisplayTime(this.getDisplayTime1());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate2().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate2());
      ddates.setDisplayTime(this.getDisplayTime2());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate3().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate3());
      ddates.setDisplayTime(this.getDisplayTime3());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate4().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate4());
      ddates.setDisplayTime(this.getDisplayTime4());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate5().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate5());
      ddates.setDisplayTime(this.getDisplayTime5());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate6().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate6());
      ddates.setDisplayTime(this.getDisplayTime6());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate7().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate7());
      ddates.setDisplayTime(this.getDisplayTime7());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate8().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate8());
      ddates.setDisplayTime(this.getDisplayTime8());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate9().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate9());
      ddates.setDisplayTime(this.getDisplayTime9());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
    if (this.getDisplayDate10().length()>6) {
      DisplayDates ddates = new DisplayDates();
      ddates.setDisplayDateString(this.getDisplayDate10());
      ddates.setDisplayTime(this.getDisplayTime10());  
      display.addDate(ddates);
      datesList.add(ddates);
      }
   display.setDisplayDatesList(datesList);
    return(display);
  }
  public void setUpdatedProperties(DfbsDisplay updatedDisplay,DfbsDisplay display) throws Exception
  { 
    display.setDisplayAddress1(updatedDisplay.getDisplayAddress1());
    display.setDisplayAddress2(updatedDisplay.getDisplayAddress2());
    display.setDisplayCity(updatedDisplay.getDisplayCity());
    display.setDisplayComments(updatedDisplay.getDisplayComments());
    display.setDisplayCounty(updatedDisplay.getDisplayCounty());
    display.setDisplayDateFrom(updatedDisplay.getDisplayDateFrom());
    display.setDisplayDateTo(updatedDisplay.getDisplayDateTo());
    display.setDisplayEmail(updatedDisplay.getDisplayEmail());
    display.setDisplayFax(updatedDisplay.getDisplayFax());
    display.setDisplayFd(updatedDisplay.getDisplayFd());
    display.setDisplayFdEmail(updatedDisplay.getDisplayFdEmail());
    display.setDisplayIdNumber(updatedDisplay.getDisplayIdNumber());
    display.setDisplayIssueDate(updatedDisplay.getDisplayIssueDate());
    display.setDisplayOnlineStatus(updatedDisplay.getDisplayOnlineStatus());
    display.setDisplayOwnerId(updatedDisplay.getDisplayOwnerId());
    display.setDisplayPermitKey(updatedDisplay.getDisplayPermitKey());
    display.setDisplayPhone(updatedDisplay.getDisplayPhone());
    display.setDisplayState(updatedDisplay.getDisplayState());
    display.setDisplayTime(updatedDisplay.getDisplayTime());
    display.setDisplayZip(updatedDisplay.getDisplayZip());
    display.setDisplayZip2(updatedDisplay.getDisplayZip2());
    display.setCompanyName(updatedDisplay.getCompanyName());
    display.setShooterName(updatedDisplay.getShooterName());
    display.setDisplayDatesMap(updatedDisplay.getDisplayDatesMap());
  }
  
  public void setProperties(DfbsDisplay display) 
  {        
    this.setDisplayAddress1(display.getDisplayAddress1());
    this.setDisplayAddress2(display.getDisplayAddress2());
    this.setDisplayCity(display.getDisplayCity());
    this.setDisplayComments(display.getDisplayComments());
    this.setDisplayCounty(display.getDisplayCounty());
    this.setDisplayDateFrom(display.getDisplayDateFromString());
    this.setDisplayDateTo(display.getDisplayDateToString());
    this.setDisplayEmail(display.getDisplayEmail());
    this.setDisplayFax(display.getDisplayFax());
    this.setDisplayFd(display.getDisplayFd());
    this.setDisplayFdEmail(display.getDisplayFdEmail());
    this.setDisplayIdNumber(display.getDisplayIdNumber());
    this.setDisplayIssueDate(display.getDisplayIssueDateString());
    this.setDisplayOnlineStatus(display.getDisplayOnlineStatus());
    this.setDisplayOwnerId(display.getDisplayOwnerId());
    this.setDisplayPermitKey(display.getDisplayPermitKey());
    this.setDisplayPhone(display.getDisplayPhone());
    this.setDisplayState(display.getDisplayState());
    this.setDisplayTime(display.getDisplayTime());
    this.setDisplayZip(display.getDisplayZip());
    this.setDisplayZip2(display.getDisplayZip2());
    this.setCompanyName(display.getCompanyName());
    this.setShooterName(display.getShooterName());
    List displayDates = display.getDisplayDatesList();
    Iterator i = displayDates.iterator();
    int dateCounter =1;
    while(i.hasNext())
    {
      
      DisplayDates ddates = (DisplayDates) i.next();  
      if (dateCounter==1 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate1(ddates.getDisplayDateString());
        this.setDisplayTime1(ddates.getDisplayTime());        
      }
      if (dateCounter==2 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate2(ddates.getDisplayDateString());
        this.setDisplayTime2(ddates.getDisplayTime());        
      }
      if (dateCounter==3 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate3(ddates.getDisplayDateString());
        this.setDisplayTime3(ddates.getDisplayTime());        
      }
      if (dateCounter==4 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate4(ddates.getDisplayDateString());
        this.setDisplayTime4(ddates.getDisplayTime());        
      }
      if (dateCounter==5 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate5(ddates.getDisplayDateString());
        this.setDisplayTime5(ddates.getDisplayTime());        
      }
      if (dateCounter==6 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate6(ddates.getDisplayDateString());
        this.setDisplayTime6(ddates.getDisplayTime());        
      }
      if (dateCounter==7 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate7(ddates.getDisplayDateString());
        this.setDisplayTime7(ddates.getDisplayTime());        
      }
      if (dateCounter==8 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate8(ddates.getDisplayDateString());
        this.setDisplayTime8(ddates.getDisplayTime());        
      }
      if (dateCounter==9 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate9(ddates.getDisplayDateString());
        this.setDisplayTime9(ddates.getDisplayTime());        
      }
      if (dateCounter==10 && ddates.getDisplayDateString().length()>6) {
        this.setDisplayDate10(ddates.getDisplayDateString());
        this.setDisplayTime10(ddates.getDisplayTime());        
      }
     dateCounter =dateCounter+1;
    }
  }
     public FormFile getCaseFile()
  { 
   return caseFile;
  }
public void setCaseFile( FormFile caseFile)
  { 
  this.caseFile = caseFile;
  } 
   public String getXlName()
  { 
   return xlName;
  }
public void setXlName(String xlName)
  { 
  this.xlName = xlName;
  } 
  public String getDisplayDate1()
  {
  return displayDate1;
  }
  public void setDisplayDate1(String displayDate1)
  {
  this.displayDate1 = displayDate1;
  }
  public String getDisplayTime1()
  {
  return displayTime1;
  }
  public void setDisplayTime1(String displayTime1)
  {
  this.displayTime1 = displayTime1;
  }
  
  public String getDisplayDate2()
  {
  return displayDate2;
  }
  public void setDisplayDate2(String displayDate2)
  {
  this.displayDate2 = displayDate2;
  }
  public String getDisplayTime2()
  {
  return displayTime2;
  }
  public void setDisplayTime2(String displayTime2)
  {
  this.displayTime2 = displayTime2;
  }
  
  public String getDisplayDate3()
  {
  return displayDate3;
  }
  public void setDisplayDate3(String displayDate3)
  {
  this.displayDate3 = displayDate3;
  }
  public String getDisplayTime3()
  {
  return displayTime3;
  }
  public void setDisplayTime3(String displayTime3)
  {
  this.displayTime3 = displayTime3;
  }
  
  public String getDisplayDate4()
  {
  return displayDate4;
  }
  public void setDisplayDate4(String displayDate4)
  {
  this.displayDate4 = displayDate4;
  }
  public String getDisplayTime4()
  {
  return displayTime4;
  }
  public void setDisplayTime4(String displayTime4)
  {
  this.displayTime4 = displayTime4;
  }
  
  public String getDisplayDate5()
  {
  return displayDate5;
  }
  public void setDisplayDate5(String displayDate5)
  {
  this.displayDate5 = displayDate5;
  }
  public String getDisplayTime5()
  {
  return displayTime5;
  }
  public void setDisplayTime5(String displayTime5)
  {
  this.displayTime5 = displayTime5;
  }
  
  public String getDisplayDate6()
  {
  return displayDate6;
  }
  public void setDisplayDate6(String displayDate6)
  {
  this.displayDate6 = displayDate6;
  }
  public String getDisplayTime6()
  {
  return displayTime6;
  }
  public void setDisplayTime6(String displayTime6)
  {
  this.displayTime6 = displayTime6;
  }
  
  public String getDisplayDate7()
  {
  return displayDate7;
  }
  public void setDisplayDate7(String displayDate7)
  {
  this.displayDate7 = displayDate7;
  }
  public String getDisplayTime7()
  {
  return displayTime7;
  }
  public void setDisplayTime7(String displayTime7)
  {
  this.displayTime7 = displayTime7;
  }
  
  public String getDisplayDate8()
  {
  return displayDate8;
  }
  public void setDisplayDate8(String displayDate8)
  {
  this.displayDate8 = displayDate8;
  }
  public String getDisplayTime8()
  {
  return displayTime8;
  }
  public void setDisplayTime8(String displayTime8)
  {
  this.displayTime8 = displayTime8;
  }
  
  public String getDisplayDate9()
  {
  return displayDate9;
  }
  public void setDisplayDate9(String displayDate9)
  {
  this.displayDate9 = displayDate9;
  }
  public String getDisplayTime9()
  {
  return displayTime9;
  }
  public void setDisplayTime9(String displayTime9)
  {
  this.displayTime9 = displayTime9;
  }
  
  public String getDisplayDate10()
  {
  return displayDate10;
  }
  public void setDisplayDate10(String displayDate10)
  {
  this.displayDate10 = displayDate10;
  }
  public String getDisplayTime10()
  {
  return displayTime10;
  }
  public void setDisplayTime10(String displayTime10)
  {
  this.displayTime10 = displayTime10;
  }
  
  
  
}
