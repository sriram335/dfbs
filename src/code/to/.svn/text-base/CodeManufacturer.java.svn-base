package code.to;

import hs.to.*;



import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CodeManufacturer implements Serializable
{

  private List facilityList;
  private List personMList;
  private Map facilityMap;
  private Map personMap;
  private int totalSealsCount;
  private int manufacturerNameId;
  private int manufacturerEntityId;
  private String manufacturerName;
  private String manufacturerAddress1;
  private String manufacturerAddress2;
  private String manufacturerCity;
  private String manufacturerState;
  private String manufacturerZip;
  private String manufacturerCounty;
  private String manufacturerCountry;
  private String manufacturerStatus;
  private String manufacturerWebPage;
  private String manufacturerComments;
  private String checkoutId;
  private double totalAmountSeals;
  private double totalAmountReleases;
  private double grandTotal;
  private Map designReleaseMap;
  private int designReleaseCounter;
  private List releaseList;
  private String fileStatus;
  private List manufacturerFees;

  public CodeManufacturer()
  {
    
  }
   public String getManufacturerName() 
  {
    return manufacturerName;
  }
  public void setManufacturerName(String manufacturerName) 
  {
    this.manufacturerName=manufacturerName;
  }
   public String getManufacturerAddress1() 
  {
    return manufacturerAddress1;
  }
  public void setManufacturerAddress1(String manufacturerAddress1) 
  {
    this.manufacturerAddress1=manufacturerAddress1;
  }
  public String getManufacturerAddress2() 
  {
    return manufacturerAddress2;
  }
  public void setManufacturerAddress2(String manufacturerAddress2) 
  {
    this.manufacturerAddress2=manufacturerAddress2;
  }
   public String getManufacturerCity() 
  {
    return manufacturerCity;
  }
  public void setManufacturerCity(String manufacturerCity) 
  {
    this.manufacturerCity=manufacturerCity;
  }
   public String getManufacturerState() 
  {
    return manufacturerState;
  }
  public void setManufacturerState(String manufacturerState) 
  {
    this.manufacturerState=manufacturerState;
  }
  public String getManufacturerZip() 
  {
    return manufacturerZip;
  }
  public void setManufacturerZip(String manufacturerZip) 
  {
    this.manufacturerZip=manufacturerZip;
  }
   public String getManufacturerCounty() 
  {
    return manufacturerCounty;
  }
  public void setManufacturerCounty(String manufacturerCounty) 
  {
    this.manufacturerCounty=manufacturerCounty;
  }
   public String getManufacturerCountry() 
  {
    return manufacturerCountry;
  }
  public void setManufacturerCountry(String manufacturerCountry) 
  {
    this.manufacturerCountry=manufacturerCountry;
  }
   public String getManufacturerStatus() 
  {
    return manufacturerStatus;
  }
  public void setManufacturerStatus(String manufacturerStatus) 
  {
    this.manufacturerStatus=manufacturerStatus;
  }
  public String getManufacturerWebPage() 
  {
    return manufacturerWebPage;
  }
  public void setManufacturerWebPage(String manufacturerWebPage) 
  {
    this.manufacturerWebPage=manufacturerWebPage;
  }
   public String getManufacturerComments() 
  {
    return manufacturerComments;
  }
  public void setManufacturerComments(String manufacturerComments) 
  {
    this.manufacturerComments=manufacturerComments;
  }
   public int getManufacturerEntityId() 
  {
    return manufacturerEntityId;
  }
  public void setManufacturerEntityId(int manufacturerEntityId) 
  {
    this.manufacturerEntityId=manufacturerEntityId;
  }
   public int getManufacturerNameId() 
  {
    return manufacturerNameId;
  }
  public void setManufacturerNameId(int manufacturerNameId) 
  {
    this.manufacturerNameId=manufacturerNameId;
  }
  // seals count code for map list etc 
  
   public Map getFacilityMap()
  {
    if(facilityMap == null) 
    {
      facilityMap = new HashMap();
    }
    return facilityMap;
  }

  public void setFacilityMap(Map facilityMap)
  {
    this.facilityMap = facilityMap;
  }
  public int getFacilityMapCount()
  {
    return (this.facilityMap == null) ? 0 : facilityMap.size();
  }

  public void addFacility(CodeFacility facility) throws Exception
  {
    if(facility == null) return;
    
    facility.setFacilityEntityId(this.getManufacturerEntityId());
    
    if(facility.isNew()) 
    {// changed to take care of application key being a int
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getFacilityMapCount() + 1);
      Map map = this.getFacilityMap();
      facility.setApplicationKey(getFacilityMapCount() + 1);
      map.put(sb.toString(),facility);
    } 
    else 
    {
      String str = "" + facility.getFacilityId();
      Map map = this.getFacilityMap();
      facility.setApplicationKey(facility.getFacilityId());
      map.put(str,facility);
    }
  //  this.addCount(facility);
  }
  public CodeFacility getFacility(String key)
  {
    
    if(key == null || facilityMap == null ) {
       
      return new CodeFacility();
    }
    CodeFacility facility = (CodeFacility) facilityMap.get(key);
    return (facility == null) ? new CodeFacility() : facility;
  }
  
  
  public void removeFacility(String key) throws Exception
  {
    if(key == null || facilityMap == null ) return;
    
    CodeFacility facility = (CodeFacility) facilityMap.get(key);
 //   this.removeCount(facility);
    facilityMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getManufacturerEntityId() == 0) ? true : false;
  }

  public String getCheckoutId()
  {
    return checkoutId;
  }

  public void setCheckoutId(String checkoutId)
  {
    this.checkoutId = checkoutId;
  }
  
  public double getTotalAmountSeals()
  {
    return totalAmountSeals;
  }

  public void setTotalAmountSeals(double totalAmountSeals)
  {
    this.totalAmountSeals = totalAmountSeals;
  }
  
 
  public String getTotalAmountSealsString()
  {
    return NumberFormat.getCurrencyInstance().format(getTotalAmountSeals());
    
  }
 
  public void setTotalAmountSeals(String newAmount) throws Exception
  {
    if (newAmount == null) 
    {
      totalAmountSeals=0;
      return;
    }
  
    try {
    totalAmountSeals= NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       totalAmountSeals= NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         totalAmountSeals=0;
       }
    }
  }


 
   
 public List getFacilityList()
  {
    if(facilityList == null) 
    {
      facilityList = new ArrayList();
    } 
    return facilityList;
  }

  public void setFacilityList(List facilityList)
  {
    this.facilityList = facilityList;
  }
  public List getPersonMList()
  {
    if(personMList == null) 
    {
      personMList = new ArrayList();
    } 
    return personMList;
  }

  public void setPersonMList(List personMList)
  {
    this.personMList = personMList;
  }
 public int getTotalSealsCount() 
  {
    return totalSealsCount;
  }
  public void setTotalSealsCount(int totalSealsCount) 
  {
    this.totalSealsCount=totalSealsCount;
    
  }
  
  public Map getPersonMap()
  {
    if(personMap == null) 
    {
      personMap = new HashMap();
    }
    return personMap;
  }

  public void setPersonMap(Map personMap)
  {
    this.personMap = personMap;
  }
  public int getPersonMapCount()
  {
    return (this.personMap == null) ? 0 : personMap.size();
  }

  public void addPerson(CodePerson person) throws Exception
  {
    if(person == null) return;
    
    person.setCodePersonCompanyId(this.getManufacturerNameId());
    
    if(person.isNew()) 
    {// changed to take care of application key being a int
      StringBuffer sb = new StringBuffer("");
      sb.append(getPersonMapCount() + 1);
      Map map = this.getPersonMap();
      person.setApplicationKey(getPersonMapCount() + 1);
      map.put(sb.toString(),person);
    } 
    else 
    { StringBuffer sb = new StringBuffer("");
      sb.append(""+ person.getCodePersonId());
      Map map = this.getPersonMap();
      person.setApplicationKey(person.getCodePersonId());
      map.put(sb.toString(),person);
    }
  //  this.addCount(person);
  }
  public CodePerson getPerson(String key)
  {
    
    if(key == null || personMap == null ) {
       
      return new CodePerson();
    }
    CodePerson person = (CodePerson) personMap.get(key);
    return (person == null) ? new CodePerson() : person;
  }
  
  
  public void removePerson(String key) throws Exception
  {
    if(key == null || personMap == null ) return;
    
    CodePerson person = (CodePerson) personMap.get(key);
 //   this.removeCount(person);
 
    personMap.remove(key);
  }
   public Map getDesignReleaseMap()
  {
    if(designReleaseMap == null) 
    {
      designReleaseMap = new HashMap();
    }
    return designReleaseMap;
  }

  public void setDesignReleaseMap(Map designReleaseMap)
  {
    this.designReleaseMap = designReleaseMap;
  }
  public int getDesignReleaseMapCount()
  {
    return (this.designReleaseMap == null) ? 0 : designReleaseMap.size();
  }

  public void addRelease(CodeDesignRelease release) throws Exception
  {
    if(release == null) return;
    
    release.setManufacturerEntityId(this.getManufacturerEntityId());
    
    if(release.isNew()) 
    {// changed to take care of application key being a int
      StringBuffer sb = new StringBuffer(Integer.toString(getManufacturerEntityId()));
      sb.append(getDesignReleaseCounter() + 1);
      Map map = this.getDesignReleaseMap();
      release.setApplicationKey(sb.toString());
      release.setFileStatus("NO");
      map.put(sb.toString(),release);
      setDesignReleaseCounter(getDesignReleaseCounter() + 1);
      
    } 
    else 
    { 
      String str = "" + release.getSystemId();
      Map map = this.getDesignReleaseMap();
      release.setApplicationKey(str.toString());
      map.put(str,release);
    }
   }
  public CodeDesignRelease getCodeRelease(String key)
  {
    
    if(key == null || designReleaseMap == null ) {
       
      return new CodeDesignRelease();
    }
    CodeDesignRelease release = (CodeDesignRelease) designReleaseMap.get(key);
    return (release == null) ? new CodeDesignRelease() : release;
  }
  
  
  public void removeCodeDesignRelease(String key) throws Exception
  {
    if(key == null || designReleaseMap == null ) return;
    
    CodeDesignRelease release = (CodeDesignRelease) designReleaseMap.get(key);
    this.setTotalAmountReleases(this.getTotalAmountReleases()- release.getReleaseFee());;
    designReleaseMap.remove(key);
  } 
  
   public int getDesignReleaseCounter()
  {
    return designReleaseCounter;
  }

  public void setDesignReleaseCounter(int  designReleaseCounter)
  {
    this. designReleaseCounter =  designReleaseCounter;
  }
  public List getReleaseList()
  {
    if(releaseList == null) 
    {
      releaseList = new ArrayList();
    } 
    return releaseList;
  }

  public void setReleaseList(List releaseList)
  {
    this.releaseList = releaseList;
  }
  
  public double getTotalAmountReleases()
  {
    return totalAmountReleases;
  }

  public void setTotalAmountReleases(double totalAmountReleases)
  {
    this.totalAmountReleases = totalAmountReleases;
  }
  
 
  public String getTotalAmountReleasesString()
  {
    return NumberFormat.getCurrencyInstance().format(getTotalAmountReleases());
    
  }
 
  public void setTotalAmount(String newAmount) throws Exception
  {
    if (newAmount == null) 
    {
      totalAmountReleases=0;
      return;
    }
  
    try {
    totalAmountReleases= NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       totalAmountReleases= NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         totalAmountReleases=0;
       }
    }
  }

 public String getGrandTotalString()
  {
    return NumberFormat.getCurrencyInstance().format(getGrandTotal());
    
  }
public double getGrandTotal()
  {
    return getTotalAmountReleases() + getTotalAmountSeals();
    
  }
 public void setGrandTotal(double grandTotal)
  {
    this.grandTotal = grandTotal;
  }
 
   public String getFileStatus() 
  {
    return fileStatus;
  }
  public void setFileStatus(String fileStatus) 
  {
    this.fileStatus=fileStatus;
  }
  public List getManufacturerFees()
  {
    if(manufacturerFees == null) 
    {
      manufacturerFees = new ArrayList();
    } 
    return manufacturerFees;
  }

  public void setManufacturerFees(List manufacturerFees)
  {
    this.manufacturerFees = manufacturerFees;
  }
}
