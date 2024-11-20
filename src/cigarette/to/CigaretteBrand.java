package cigarette.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class CigaretteBrand implements Serializable
{
  public CigaretteBrand()
  {
  }
  private int cigaretteId;
  private String cigaretteBrand;
  private String cigaretteStyle;
  private double cigaretteLength;
  private double cigaretteCircumference;
  private String cigaretteMarking;
  private String cigaretteFlavor;
   private String cigaretteFlavorOther;
  private String cigaretteFilter;
  private String cigarettePackage;
  private String testLabName;
  private Date testDate;
  private String address1;
  private String  address2;
  private String city;
  private String  state;
  private String zip;
  private String zip2;
  private String phone;
  private String fax;
  private int applicationId;
  private String applicationKey;
  private double amount;
  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public boolean isNew() 
  {
    return (this.getCigaretteId() == 0) ? true : false;
  }
  public String getCigaretteBrand()
  { 
   return cigaretteBrand;
  }
public void setCigaretteBrand(String cigaretteBrand)
  { 
  this.cigaretteBrand = cigaretteBrand;
  }
public String getCigaretteFilter()
  { 
   return cigaretteFilter;
  }
public void setCigaretteFilter(String cigaretteFilter)
  { 
  this.cigaretteFilter = cigaretteFilter;
  }
public String getCigaretteFlavor()
  { 
   return cigaretteFlavor;
  }
public void setCigaretteFlavor(String cigaretteFlavor)
  { 
  this.cigaretteFlavor = cigaretteFlavor;
  }
public String getCigaretteMarking()
  { 
   return cigaretteMarking;
  }
public void setCigaretteMarking(String cigaretteMarking)
  { 
  this.cigaretteMarking = cigaretteMarking;
  }
public String getCigarettePackage()
  { 
   return cigarettePackage;
  }
public void setCigarettePackage(String cigarettePackage)
  { 
  this.cigarettePackage = cigarettePackage;
  }
public String getCigaretteStyle()
  { 
   return cigaretteStyle;
  }
public void setCigaretteStyle(String cigaretteStyle)
  { 
  this.cigaretteStyle = cigaretteStyle;
  }
public double getCigaretteCircumference()
  { 
   return cigaretteCircumference;
  }
public void setCigaretteCircumference(double cigaretteCircumference)
  { 
  this.cigaretteCircumference = cigaretteCircumference;
  }
public int getCigaretteId()
  { 
   return cigaretteId;
  }
public void setCigaretteId(int cigaretteId)
  { 
  this.cigaretteId = cigaretteId;
  }
public double getCigaretteLength()
  { 
   return cigaretteLength;
  }
public void setCigaretteLength(double cigaretteLength)
  { 
  this.cigaretteLength = cigaretteLength;
  }
  
  public Date getTestDate()
  { 
   return testDate;
  }
public void setTestDate(Date testDate)
  { 
  this.testDate = testDate;
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
public String getFax()
  { 
   return fax;
  }
public void setFax(String fax)
  { 
  this.fax = fax;
  }
public String getPhone()
  { 
   return phone;
  }
public void setPhone(String phone)
  { 
  this.phone = phone;
  }
public String getState()
  { 
   return state;
  }
public void setState(String state)
  { 
  this.state = state;
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
public int getApplicationId()
  { 
   return applicationId;
  }
public void setApplicationId(int applicationId)
  { 
  this.applicationId = applicationId;
  }
  public void setTestDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
testDate = formatter.parse(dateParam);
} catch (Exception e)
{testDate = null; }
}
public String getTestDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(testDate == null)
{ return ""; }
else { return formatter.format(testDate); }
}
public String getTestLabName()
  { 
   return testLabName;
  }
public void setTestLabName(String testLabName)
  { 
  this.testLabName = testLabName;
  }
  public String getCigaretteFlavorOther()
  { 
   return cigaretteFlavorOther;
  }
public void setCigaretteFlavorOther(String cigaretteFlavorOther)
  { 
  this.cigaretteFlavorOther = cigaretteFlavorOther;
  }
}
