package cigarette.to;

import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class CigaretteCompany  extends HsCompleteContact implements Serializable
{ private int companyId;
  private String companyName;
  private String companyAddress1;
  private String companyAddress2;
  private String companyCity;
  private String companyState;
  private String companyZip;
  private String companyZip2;
  private String companyPhone;
  private String companyFax;
  private String companyProvince;
  private String companyCountry;
  private List applications;
  private Map applicationsMap;
  private Map usersMap;
  private List users;
  private int applicationCounter;
  private int applicationsCount;
  private int totalBrands;
  private int receiptId;
  private double amount;
  private String checkoutId;
  private String paymentType;
  public CigaretteCompany()
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
    public String getPaymentType()
  { 
   return paymentType;
  }
public void setPaymentType(String paymentType)
  { 
  this.paymentType = paymentType;
  }
   public String getCheckoutId()
  { 
   return checkoutId;
  }
public void setCheckoutId(String checkoutId)
  { 
  this.checkoutId = checkoutId;
  }
  public String getCompanyAddress1()
  { 
   return companyAddress1;
  }
public void setCompanyAddress1(String companyAddress1)
  { 
  this.companyAddress1 = companyAddress1;
  }
public String getCompanyAddress2()
  { 
   return companyAddress2;
  }
public void setCompanyAddress2(String companyAddress2)
  { 
  this.companyAddress2 = companyAddress2;
  }
public String getCompanyCity()
  { 
   return companyCity;
  }
public void setCompanyCity(String companyCity)
  { 
  this.companyCity = companyCity;
  }

public String getCompanyFax()
  { 
   return companyFax;
  }
public void setCompanyFax(String companyFax)
  { 
  this.companyFax = companyFax;
  }
public String getCompanyName()
  { 
   return companyName;
  }
public void setCompanyName(String companyName)
  { 
  this.companyName = companyName;
  }
public String getCompanyPhone()
  { 
   return companyPhone;
  }
public void setCompanyPhone(String companyPhone)
  { 
  this.companyPhone = companyPhone;
  }
public String getCompanyState()
  { 
   return companyState;
  }
public void setCompanyState(String companyState)
  { 
  this.companyState = companyState;
  }

public String getCompanyZip()
  { 
   return companyZip;
  }
public void setCompanyZip(String companyZip)
  { 
  this.companyZip = companyZip;
  }
public String getCompanyZip2()
  { 
   return companyZip2;
  }
public void setCompanyZip2(String companyZip2)
  { 
  this.companyZip2 = companyZip2;
  }
public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
   public int getApplicationsCount () 
  {
    return (applications == null) ? 0 : applications.size();
  }

  public List getApplications()
  {
    if(applications == null) 
    {
      applications = new ArrayList();
    } 
    return applications;
  }

  public void setApplications(List applications)
  {
    this.applications = applications;
  }
    public Map getApplicationsMap()
  {
    if(applicationsMap == null) 
    {
      applicationsMap = new HashMap();
    }
    return applicationsMap;
  }

  public void setApplicationsMap(Map applicationsMap)
  {
    this.applicationsMap = applicationsMap;
  }
  public int getApplicationsMapCount()
  {
    return (this.applicationsMap == null) ? 0 : applicationsMap.size();
  }

  public void addApplication(CigaretteApplication application) throws Exception
  {
    if(application == null) return;
    
    application.setCompanyId(this.getCompanyId());
    
    
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getApplicationCounter() + 1);
      Map map = this.getApplicationsMap();
      application.setApplicationKey(sb.toString());
      map.put(sb.toString(),application);
      setApplicationCounter(getApplicationCounter() + 1);
     
   
    this.addCount(application);
  }
  public CigaretteApplication getApplication(String key)
  {
    
    if(key == null || applicationsMap == null ) {
       
      return new CigaretteApplication();
    }
    CigaretteApplication application = (CigaretteApplication) applicationsMap.get(key);
    return (application == null) ? new CigaretteApplication() : application;
  }
  
  
  public void removeApplication(String key) throws Exception
  {
    if(key == null || applicationsMap == null ) return;
    
    CigaretteApplication application = (CigaretteApplication) applicationsMap.get(key);
    this.removeCount(application);
    applicationsMap.remove(key);
  }
    public int getApplicationCounter()
  {
    return applicationCounter;
  }

  public void setApplicationCounter(int applicationCounter)
  {
    this.applicationCounter = applicationCounter;
  }
   private void addCount(CigaretteApplication application) throws Exception
  {
   
      this.applicationsCount++; 
      
  }
  
  private void removeCount(CigaretteApplication application) throws Exception
  {
    
      this.applicationsCount--; 
      
  }
    public int getTotalBrands()
  {
    return totalBrands;
  }

  public void setTotalBrands(int totalBrands)
  {
    this.totalBrands = totalBrands;
  }
     public int getReceiptId()
  {
    return receiptId;
  }

  public void setReceiptId(int receiptId)
  {
    this.receiptId = receiptId;
  }
  public String getCompanyProvince()
  { 
   return companyProvince;
  }
public void setCompanyProvince(String companyProvince)
  { 
  this.companyProvince = companyProvince;
  }
  public String getCompanyCountry()
  { 
   return companyCountry;
  }
public void setCompanyCountry(String companyCountry)
  { 
  this.companyCountry = companyCountry;
  }
   public List getUsers()
  {
    if(users == null) 
    {
      users = new ArrayList();
    } 
    return users;
  }

  public void setUsers(List users)
  {
    this.users = users;
  }
}
