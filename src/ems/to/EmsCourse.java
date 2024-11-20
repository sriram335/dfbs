package ems.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;


public class EmsCourse  implements Serializable

{ private int courseId;
  private String dayClassMeet;
  private String dayClassTime;
  private String  courseNumber;
  private Date startDate;
  private Date completionDate;
  private String courseLength;
  private int crssCourseId;
  private String firstName;
  private String lastName;
  private String mi;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String zip2;
  private String county;
  private Date receivedDate;
  private String classLocation;
  private String telephone;
  private String email;
  private int institutionId;
  private Date approvedDate;
  private String pendingItems;
  private String compReport;
  private String recCreatedBy;
  private List fileList;
  private List instructors;
  private int instructorCount;
  private String institutionName;
  private List checkBoxes;
  public EmsCourse()
  {
  }
  public Date getCompletionDate()
  { 
   return completionDate;
  }
public void setCompletionDate(Date completionDate)
  { 
  this.completionDate = completionDate;
  }
public Date getStartDate()
  { 
   return startDate;
  }
public void setStartDate(Date startDate)
  { 
  this.startDate = startDate;
  }
public String getCourseLength()
  { 
   return courseLength;
  }
public void setCourseLength(String courseLength)
  { 
  this.courseLength = courseLength;
  }
public String getCourseNumber()
  { 
   return courseNumber;
  }
public void setCourseNumber(String courseNumber)
  { 
  this.courseNumber = courseNumber;
  }
public String getDayClassMeet()
  { 
   return dayClassMeet;
  }
public void setDayClassMeet(String dayClassMeet)
  { 
  this.dayClassMeet = dayClassMeet;
  }
public String getDayClassTime()
  { 
   return dayClassTime;
  }
public void setDayClassTime(String dayClassTime)
  { 
  this.dayClassTime = dayClassTime;
  }
public String getFirstName()
  { 
   return firstName;
  }
public void setFirstName(String firstName)
  { 
  this.firstName = firstName;
  }
public int getCourseId()
  { 
   return courseId;
  }
public void setCourseId(int courseId)
  { 
  this.courseId = courseId;
  }
public int getCrssCourseId()
  { 
   return crssCourseId;
  }
public void setCrssCourseId(int crssCourseId)
  { 
  this.crssCourseId = crssCourseId;
  }
  public Date getReceivedDate()
  { 
   return receivedDate;
  }
public void setReceivedDate(Date receivedDate)
  { 
  this.receivedDate = receivedDate;
  }
public String getAddress1()
  { 
   return address1;
  }
public void setAddress1(String address1)
  { 
  this.address1 = address1;
  }
public String getAddress2()
  { 
   return address2;
  }
public void setAddress2(String address2)
  { 
  this.address2 = address2;
  }
public String getCity()
  { 
   return city;
  }
public void setCity(String city)
  { 
  this.city = city;
  }
public String getClassLocation()
  { 
   return classLocation;
  }
public void setClassLocation(String classLocation)
  { 
  this.classLocation = classLocation;
  }
public String getCounty()
  { 
   return county;
  }
public void setCounty(String county)
  { 
  this.county = county;
  }
public String getEmail()
  { 
   return email;
  }
public void setEmail(String email)
  { 
  this.email = email;
  }
public String getLastName()
  { 
   return lastName;
  }
public void setLastName(String lastName)
  { 
  this.lastName = lastName;
  }
public String getMi()
  { 
   return mi;
  }
public void setMi(String mi)
  { 
  this.mi = mi;
  }
public String getState()
  { 
   return state;
  }
public void setState(String state)
  { 
  this.state = state;
  }
public String getTelephone()
  { 
   return telephone;
  }
public void setTelephone(String telephone)
  { 
  this.telephone = telephone;
  }
public String getZip()
  { 
   return zip;
  }
public void setZip(String zip)
  { 
  this.zip = zip;
  }
public String getZip2()
  { 
   return zip2;
  }
public void setZip2(String zip2)
  { 
  this.zip2 = zip2;
  }
public int getInstitutionId()
  { 
   return institutionId;
  }
public void setInstitutionId(int institutionId)
  { 
  this.institutionId = institutionId;
  }
  public void setCompletionDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
completionDate = formatter.parse(dateParam);
} catch (Exception e)
{completionDate = null; }
}
public String getCompletionDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(completionDate == null)
{ return ""; }
else { return formatter.format(completionDate); }
}
public void setReceivedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
receivedDate = formatter.parse(dateParam);
} catch (Exception e)
{receivedDate = null; }
}
public String getReceivedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(receivedDate == null)
{ return ""; }
else { return formatter.format(receivedDate); }
}
public void setStartDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
startDate = formatter.parse(dateParam);
} catch (Exception e)
{startDate = null; }
}
public String getStartDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(startDate == null)
{ return ""; }
else { return formatter.format(startDate); }
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
  
   public List getInstructors()
  {
    if(instructors == null) 
    {
      instructors = new ArrayList();
    } 
    return instructors;
  }

  public void setInstructors(List instructors)
  {
    this.instructors = instructors;
  }
  public Date getApprovedDate()
  { 
   return approvedDate;
  }
public void setApprovedDate(Date approvedDate)
  { 
  this.approvedDate = approvedDate;
  }
public String getCompReport()
  { 
   return compReport;
  }
public void setCompReport(String compReport)
  { 
  this.compReport = compReport;
  }
public String getPendingItems()
  { 
   return pendingItems;
  }
public void setPendingItems(String pendingItems)
  { 
  this.pendingItems = pendingItems;
  }
public String getRecCreatedBy()
  { 
   return recCreatedBy;
  }
public void setRecCreatedBy(String recCreatedBy)
  { 
  this.recCreatedBy = recCreatedBy;
  }
public void setApprovedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
approvedDate = formatter.parse(dateParam);
} catch (Exception e)
{approvedDate = null; }
}
public String getApprovedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(approvedDate == null)
{ return ""; }
else { return formatter.format(approvedDate); }
}
public void setInstructorCount(int instructorCount)
  { 
  this.instructorCount = instructorCount;
  }
public int getInstructorCount()
  { 
   return instructorCount;
  }
  public String getInstitutionName()
  { 
   return institutionName;
  }
public void setInstitutionName(String institutionName)
  { 
  this.institutionName = institutionName;
  }
  
  public List getCheckBoxes()
  {
    if(checkBoxes == null) 
    {
      checkBoxes = new ArrayList();
    } 
    return checkBoxes;
  }

  public void setCheckBoxes(List checkBoxes)
  {
    this.checkBoxes = checkBoxes;
  }
}