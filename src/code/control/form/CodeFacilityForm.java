package code.control.form;

import code.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
import main.to.*;
import main.control.form.*;
public class CodeFacilityForm extends CompleteContactForm
{
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
  private int pseals;
  private int mseals;
 
  public CodeFacilityForm()
  {
  
  }
  public void setProperties(CodeFacility facility) throws Exception
  {
    this.setFacilityName(facility.getFacilityName());
    this.setFacilityAddress1(facility.getFacilityAddress1());
    this.setFacilityAddress2(facility.getFacilityAddress2());
    this.setFacilityCity(facility.getFacilityCity());
    this.setFacilityState(facility.getFacilityState());
    this.setFacilityZip(facility.getFacilityZip());
    this.setFacilityCounty(facility.getFacilityCounty());
    this.setFacilityCountry(facility.getFacilityCountry());
    this.setFacilityStatus(facility.getFacilityStatus());
    this.setFacilityInspector(facility.getFacilityInspector());
    this.setFacilitySaa1(facility.getFacilitySaa1());
    this.setFacilitySaa2(facility.getFacilitySaa2());
    this.setFacilityTypeHud(facility.getFacilityTypeHud());
    this.setFacilityTypeMoc(facility.getFacilityTypeMoc());
    this.setFacilityTypeMor(facility.getFacilityTypeMor());
    this.setFacilityTypeMob(facility.getFacilityTypeMob());
    this.setFacilityTypePan(facility.getFacilityTypePan());
    
    
  }
 public CodeFacility getCodeFacility() throws Exception
  {
    
    CodeFacility facility = new CodeFacility ();
    facility.setFacilityName(this.getFacilityName());
    facility.setFacilityAddress1(this.getFacilityAddress1());
    facility.setFacilityAddress2(this.getFacilityAddress2());
    facility.setFacilityCity(this.getFacilityCity());
    facility.setFacilityState(this.getFacilityState());
    facility.setFacilityZip(this.getFacilityZip());
    facility.setFacilityCounty(this.getFacilityCounty());
    facility.setFacilityCountry(this.getFacilityCountry());
    facility.setFacilityStatus(this.getFacilityStatus());
    facility.setFacilityInspector(this.getFacilityInspector());
    facility.setFacilitySaa1(this.getFacilitySaa1());
    facility.setFacilitySaa2(this.getFacilitySaa2());
    facility.setFacilityTypeHud(this.getFacilityTypeHud());
    facility.setFacilityTypeMoc(this.getFacilityTypeMoc());
    facility.setFacilityTypeMor(this.getFacilityTypeMor());
    facility.setFacilityTypeMob(this.getFacilityTypeMob());
    facility.setFacilityTypePan(this.getFacilityTypePan());
    
    
    return(facility);
  }
   public int getFacilityId() 
  {
    return facilityId;
  }
  public void setFacilityId(int facilityId) 
  {
    this.facilityId = facilityId;
   
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
 
 

}