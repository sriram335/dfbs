package cigarette.control.form;

import cigarette.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;

public class CigaretteBrandForm extends CompleteContactForm
{ private int cigaretteId;
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
  private String testDate;
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
  private String selectedBrand;
  public CigaretteBrandForm()
  {
  }
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
  
  public String getTestDate()
  { 
   return testDate;
  }
public void setTestDate(String testDate)
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
   public CigaretteBrand getCigaretteBrandForm() throws Exception
  {  CigaretteBrand brand = new CigaretteBrand();
     brand.setAddress1(getAddress1());
      brand.setAddress2(getAddress2());
      brand.setCigaretteBrand(getCigaretteBrand());
      brand.setCigaretteCircumference(getCigaretteCircumference());
      brand.setCigaretteFilter(getCigaretteFilter());
      brand.setCigaretteFlavor(getCigaretteFlavor());
      brand.setCigaretteId(getCigaretteId());
      brand.setCigaretteLength(getCigaretteLength());
      brand.setCigaretteMarking(getCigaretteMarking());
      brand.setCigarettePackage(getCigarettePackage());
      brand.setCigaretteStyle(getCigaretteStyle());
      brand.setCity(getCity());
      brand.setState(getState());
      brand.setTestDateString(getTestDate());
      brand.setTestLabName(getTestLabName());
      brand.setZip(getZip());
      brand.setZip2(getZip2());
      brand.setPhone(getPhone());
      brand.setFax(getFax());
      brand.setApplicationId(getApplicationId());
      brand.setCigaretteFlavorOther(getCigaretteFlavorOther());
    return brand;
  }
public void setProperties(CigaretteBrand brand) throws Exception
  {   
      this.setAddress1(brand.getAddress1());
    this.setAddress2(brand.getAddress2());
    this.setCigaretteBrand(brand.getCigaretteBrand());
    this.setCigaretteCircumference(brand.getCigaretteCircumference());
    this.setCigaretteFilter(brand.getCigaretteFilter());
    this.setCigaretteFlavor(brand.getCigaretteFlavor());
    this.setCigaretteId(brand.getCigaretteId());
    this.setCigaretteLength(brand.getCigaretteLength());
    this.setCigaretteMarking(brand.getCigaretteMarking());
    this.setCigarettePackage(brand.getCigarettePackage());
    this.setCigaretteStyle(brand.getCigaretteStyle());
    this.setCity(brand.getCity());
    this.setState(brand.getState());
    this.setTestDate(brand.getTestDateString());
    this.setTestLabName(brand.getTestLabName());
    this.setZip(brand.getZip());
    this.setZip2(brand.getZip2());
    this.setPhone(brand.getPhone());
    this.setFax(brand.getFax());
    this.setApplicationId(brand.getApplicationId());
     this.setCigaretteFlavorOther(brand.getCigaretteFlavorOther());
  }
  public String getTestLabName()
  { 
   return testLabName;
  }
public void setTestLabName(String testLabName)
  { 
  this.testLabName = testLabName;
  }
   public String getSelectedBrand()
  {
    return selectedBrand;
  }

  public void setSelectedBrand(String selectedBrand)
  {
    this.selectedBrand = selectedBrand;
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
