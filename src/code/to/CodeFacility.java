package code.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;


public class CodeFacility implements Serializable
{
  private Map personMap;
  private int facilityId;
  private String facilityName;
  private String facilityAddress1;
  private String facilityAddress2;
  private String facilityCity;
  private String facilityState;
  private String facilityZip;
  private String facilityCounty;
  private String facilityCountry;
  private String facilityStatus;
  private int facilityInspector;
  private int facilitySaa1;
  private int facilitySaa2;
  private int facilityCompany;
  private int facilityEntityId;
  private String facilityTypeHud;
  private String facilityTypeMoc;
  private String facilityTypeMor;
  private String facilityTypeMob;
  private String facilityTypePan;
  private CodeManufacturer manufacturer;
  private int applicationKey;
  private int receiptId;
  private double amount;
  private int  pseals;
  private int mseals;
  private List personFList;
  private String  facilityPersonStatus;
  private String cdrFlag;
  
  public CodeFacility()
  {
  }
  
 public int getFacilityId() 
  {
    return facilityId;
  }
  public void setFacilityId(int facilityId) 
  {
    this.facilityId = facilityId;
   
  }
   public String getFacilityName() 
  {
    return facilityName;
  }
  public void setFacilityName(String facilityName) 
  {
    this.facilityName=facilityName;
  }
   public String getFacilityAddress1() 
  {
    return facilityAddress1;
  }
  public void setFacilityAddress1(String facilityAddress1) 
  {
    this.facilityAddress1=facilityAddress1;
  }
  public String getFacilityAddress2() 
  {
    return facilityAddress2;
  }
  public void setFacilityAddress2(String facilityAddress2) 
  {
    this.facilityAddress2=facilityAddress2;
  }
   public String getFacilityCity() 
  {
    return facilityCity;
  }
  public void setFacilityCity(String facilityCity) 
  {
    this.facilityCity=facilityCity;
  }
   public String getFacilityState() 
  {
    return facilityState;
  }
  public void setFacilityState(String facilityState) 
  {
    this.facilityState=facilityState;
  }
  public String getFacilityZip() 
  {
    return facilityZip;
  }
  public void setFacilityZip(String facilityZip) 
  {
    this.facilityZip=facilityZip;
  }
   public String getFacilityCounty() 
  {
    return facilityCounty;
  }
  public void setFacilityCounty(String facilityCounty) 
  {
    this.facilityCounty=facilityCounty;
  }
   public String getFacilityCountry() 
  {
    return facilityCountry;
  }
  public void setFacilityCountry(String facilityCountry) 
  {
    this.facilityCountry=facilityCountry;
  }
   public String getFacilityStatus() 
  {
    return facilityStatus;
  }
  public void setFacilityStatus(String facilityStatus) 
  {
    this.facilityStatus=facilityStatus;
  }
   public int getFacilityInspector() 
  {
    return facilityInspector;
  }
  public void setFacilityInspector(int facilityInspector) 
  {
    this.facilityInspector=facilityInspector;
  }
  public int getFacilitySaa1() 
  {
    return facilitySaa1;
  }
  public void setFacilitySaa1(int facilitySaa1) 
  {
    this.facilitySaa1=facilitySaa1;
  }
   public int getFacilitySaa2() 
  {
    return facilitySaa2;
  }
  public void setFacilitySaa2(int facilitySaa2) 
  {
    this.facilitySaa2=facilitySaa2;
  }
  public int getFacilityCompany() 
  {
    return facilityCompany;
  }
  public void setFacilityCompany(int facilityCompany) 
  {
    this.facilityCompany=facilityCompany;
  }
  public int getFacilityEntityId() 
  {
    return facilityEntityId;
  }
  public void setFacilityEntityId(int facilityEntityId) 
  {
    this.facilityEntityId=facilityEntityId;
  }
   public String getFacilityTypeHud() 
  {
    return facilityTypeHud;
  }
  public void setFacilityTypeHud(String facilityTypeHud) 
  {
    this.facilityTypeHud=facilityTypeHud;
  }
  public String getFacilityTypeMoc() 
  {
    return facilityTypeMoc;
  }
  public void setFacilityTypeMoc(String facilityTypeMoc) 
  {
    this.facilityTypeMoc=facilityTypeMoc;
  }
  public String getFacilityTypeMor() 
  {
    return facilityTypeMor;
  }
  public void setFacilityTypeMor(String facilityTypeMor) 
  {
    this.facilityTypeMor=facilityTypeMor;
  }
  public String getFacilityTypeMob() 
  {
    return facilityTypeMob;
  }
  public void setFacilityTypeMob(String facilityTypeMob) 
  {
    this.facilityTypeMob=facilityTypeMob;
  }
  public String getFacilityTypePan() 
  {
    return facilityTypePan;
  }
  public void setFacilityTypePan(String facilityTypePan) 
  {
    this.facilityTypePan=facilityTypePan;
  }
   public CodeManufacturer getManufacturer()
  {
    return manufacturer;
  }

  public void setManufacturer(CodeManufacturer manufacturer)
  {
    this.manufacturer = manufacturer;
  }
  public int getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(int applicationKey)
  {
    this.applicationKey = applicationKey;
  }
   public String getTransactionDescription() throws Exception
  {
    StringBuffer sb = new StringBuffer();
   // sb.append(this.getPermitTypeString());
    sb.append("-");
   // sb.append(this.getPermitNumber());
    sb.append("-");
    sb.append("confirmation");
    sb.append("-");
    sb.append(this.getReceiptId());
    
    return sb.toString();
  }

  public int getReceiptId()
  {
    return receiptId;
  }

  public void setReceiptId(int receiptId)
  {
    this.receiptId = receiptId;
  }
    public boolean isNew() 
  {
    return (this.getFacilityId() == 0 ) ? true : false;
  }

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  
 
  public String getAmountString()
  {
    return NumberFormat.getCurrencyInstance().format(getAmount());
    
  }
 
  public void setAmount(String newAmount) throws Exception
  {
    if (newAmount == null) 
    {
      amount=0;
      return;
    }
  
    try {
    amount= NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       amount= NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         amount=0;
       }
    }
  }

 public int getPseals()
  {
    return pseals;
  }

  public void setPseals(int pseals)
  {
    this.pseals = pseals;
  }
  public int getMseals()
  {
    return mseals;
  }

  public void setMseals(int mseals)
  {
    this.mseals = mseals;
  }
   public List getPersonFList()
  {
    if(personFList == null) 
    {
      personFList = new ArrayList();
    } 
    return personFList;
  }

  public void setPersonFList(List personFList)
  {
    this.personFList = personFList;
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
    
    person.setCodePersonCompanyId(this.getFacilityId());
    
    if(person.isNew()) 
    {// changed to take care of application key being a int
      StringBuffer sb = new StringBuffer("");
      sb.append(getPersonMapCount() + 1);
      Map map = this.getPersonMap();
      person.setApplicationKey(getPersonMapCount() + 1);
      map.put(sb.toString(),person);
    } 
    else 
    {
      StringBuffer sb = new StringBuffer("");
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
  public String getFacilityPersonStatus() 
  {
    return facilityPersonStatus;
  }
  public void setFacilityPersonStatus(String facilityPersonStatus) 
  {
    this.facilityPersonStatus=facilityPersonStatus;
  }
  public String getCdrFlag() 
  {
    return cdrFlag;
  }
  public void setCdrFlag(String cdrFlag) 
  {
    this.cdrFlag = cdrFlag;
  }
}
