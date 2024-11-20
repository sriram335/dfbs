package cigarette.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class CigaretteApplication extends HsCompleteContact implements Serializable
{
  public CigaretteApplication()
  {
  }
  private int appId ;
  private int companyId ;
  private Date appDate;
  private String appType;
  private String contact;
  private String title;
  private String phone;
  private String fax;
  private String email;
  private List brands;
  private Map brandsMap;
  private String applicationKey;
  private int brandsCounter;
  private int brandsCount;
  private double amount;
  private Map mapUniqueBrand;
  private String comments;
  private Date appIssueDate;
  private Date appExpDate;
  private String appStatus;
  private List appFees;
  private int feesPending;
   public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  public Date getAppDate()
  { 
   return appDate;
  }
public void setAppDate(Date appDate)
  { 
  this.appDate = appDate;
  }
public String getAppType()
  { 
   return appType;
  }
public void setAppType(String appType)
  { 
  this.appType = appType;
  }
public String getContact()
  { 
   return contact;
  }
public void setContact(String contact)
  { 
  this.contact = contact;
  }
public String getEmail()
  { 
   return email;
  }
public void setEmail(String email)
  { 
  this.email = email;
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
public String getTitle()
  { 
   return title;
  }
public void setTitle(String title)
  { 
  this.title = title;
  }
public int getAppId()
  { 
   return appId;
  }
public void setAppId(int appId)
  { 
  this.appId = appId;
  }
public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
  public void setAppDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
appDate = formatter.parse(dateParam);
} catch (Exception e)
{appDate = null; }
}
public String getAppDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(appDate == null)
{ return ""; }
else { return formatter.format(appDate); }
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
    return (this.getApplicationKey() == null || this.getApplicationKey().trim().equals("") ) ? true : false;
  }
   public int getBrandsCount () 
  {
    return (brands == null) ? 0 : brands.size();
  }

  public List getBrands()
  {
    if(brands == null) 
    {
      brands = new ArrayList();
    } 
    return brands;
  }

  public void setBrands(List brands)
  {
    this.brands = brands;
  }
    public Map getBrandsMap()
  {
    if(brandsMap == null) 
    {
      brandsMap = new HashMap();
    }
    return brandsMap;
  }

  public void setBrandsMap(Map brandsMap)
  {
    this.brandsMap = brandsMap;
  }
  public int getBrandsMapCount()
  {
    return (this.brandsMap == null) ? 0 : brandsMap.size();
  }

  public void addBrand(CigaretteBrand brand) throws Exception
  {
    if(brand == null) return;
    
    brand.setApplicationId(this.getAppId());
    
    if(brand.isNew()) 
    { 
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getBrandCounter() + 1);
      Map map = this.getBrandsMap();
      brand.setApplicationKey(sb.toString());
      map.put(sb.toString(),brand);
      setBrandCounter(getBrandCounter() + 1);
      
    } 
    else 
    {
      Map map = this.getBrandsMap();
      brand.setApplicationKey(Integer.toString(brand.getCigaretteId()));
      map.put(Integer.toString(brand.getCigaretteId()),brand);
    }
    this.addCount(brand);
  }
  public CigaretteBrand getBrand(String key)
  {
    
    if(key == null || brandsMap == null ) {
       
      return new CigaretteBrand();
    }
    CigaretteBrand brands = (CigaretteBrand) brandsMap.get(key);
    return (brands == null) ? new CigaretteBrand() : brands;
  }
  
  
  public void removeBrand(String key) throws Exception
  {
    if(key == null || brandsMap == null ) return;
    
    CigaretteBrand brands = (CigaretteBrand) brandsMap.get(key);
    this.removeCount(brands);
    brandsMap.remove(key);
  }
    public int getBrandCounter()
  {
    return brandsCounter;
  }

  public void setBrandCounter(int brandsCounter)
  {
    this.brandsCounter = brandsCounter;
  }
   private void addCount(CigaretteBrand brands) throws Exception
  {
   
      this.brandsCount++; 
      
  }
  
  private void removeCount(CigaretteBrand brands) throws Exception
  {
    
      this.brandsCount--; 
      
  } 
  
    public Map getMapUniqueBrand()
  {
    if(mapUniqueBrand == null) 
    {
      mapUniqueBrand = new HashMap();
    }
    return mapUniqueBrand;
  }

  public void setMapUniqueBrand(Map mapUniqueBrand)
  {
    this.mapUniqueBrand = mapUniqueBrand;
  }
  public String getComments()
  { 
   return comments;
  }
public void setComments(String comments)
  { 
  this.comments = comments;
  }
   public Date getAppIssueDate()
  { 
   return appIssueDate;
  }
public void setAppIssueDate(Date appIssueDate)
  { 
  this.appIssueDate = appIssueDate;
  }
   public Date getAppExpDate()
  { 
   return appExpDate;
  }
public void setAppExpDate(Date appExpDate)
  { 
  this.appExpDate = appExpDate;
  }
   public String getAppStatus()
  { 
   return appStatus;
  }
public void setAppStatus(String appStatus)
  { 
  this.appStatus = appStatus;
  }
   public void setAppExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
appExpDate = formatter.parse(dateParam);
} catch (Exception e)
{appExpDate = null; }
}
public String getAppExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(appExpDate == null)
{ return ""; }
else { return formatter.format(appExpDate); }
}
 public void setAppIssueDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
appIssueDate = formatter.parse(dateParam);
} catch (Exception e)
{appIssueDate = null; }
}
public String getAppIssueDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(appIssueDate == null)
{ return ""; }
else { return formatter.format(appIssueDate); }
}

 public List getAppFees()
  {
    if(appFees == null) 
    {
      appFees = new ArrayList();
    } 
    return appFees;
  }

  public void setAppFees(List appFees)
  {
    this.appFees = appFees;
  }
  public int getFeesPending()
  { 
   return feesPending;
  }
public void setFeesPending(int feesPending)
  { 
  this.feesPending = feesPending;
  }
}
