package code.control.form;

import code.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import main.to.*;
import main.control.form.*;
public class CodeReleaseForm extends CompleteContactForm
{private int systemId;
  private String  systemType;
  private String  releaseType;
  private String  lengthsOfUnit;
  private String  unitStructural;
  private String  unitElectrical;
  private String  unitPlumbing;
  private String  unitMechanical;
  private String  unitSprinkler;
  private String  unitFireAlaram;
  private String  unitHood;
  private String  occupancy;
  private  int numberStories;
  private  int numberInstructure;
  private String completedWidth;
  private String prevReleaseNumber;
  private String structureUse;
  private String comments;
  private int numberPublic;
  private int numberPrivate;
  private int codeDesignerId;               
  private String designerFirmName;             
  private String designerFirstName;            
  private String designerLastName;             
  private String address1;                       
  private String address2;
  private String city;
  private String state;                          
  private String zip;                            
  private String designerIndianaNumber;        
  private String designerType;                  
  private String telephone; 
  private String email;   
  private String filingStatus; 
  private int addPlans;
  private int addStructures;
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String idNumber;
  private String idType;
  private String applicationKey;
   private String releaseNumber;
  private String addReleaseNumber;
  private  int addSequence;
  private String constructionCode;
  private String occCode;
  private String releaseDate;
  private int reviewerId;
  private int designerId;
  private int manufacturerEntityId;
  private String cannedCode;
  private String buildType;
  private String codeReference;
  private String  releaseScope;
  private String  releaseApproval;
  private String facilityId1;
  private String facilityId2;
  private String facilityId3;
  private String facilityId4;
  public CodeReleaseForm()
  {
  }
   public void setProperties(CodeDesignRelease release) throws Exception
  {
     this.setSystemType(release.getSystemType());
     this.setReleaseType(release.getReleaseType());
     this.setLengthsOfUnit(release.getLengthsOfUnit());
     this.setUnitStructural(release.getUnitStructural());
     this.setUnitElectrical(release.getUnitElectrical());
     this.setUnitPlumbing(release.getUnitPlumbing());
    this.setUnitMechanical(release.getUnitMechanical());
    this.setUnitSprinkler(release.getUnitSprinkler());
    this.setUnitFireAlaram(release.getUnitFireAlaram());
    this.setUnitHood(release.getUnitHood());
    this.setOccupancy(release.getOccupancy());
    this.setNumberStories(release.getNumberStories());
    this.setNumberInstructure(release.getNumberInstructure());
    this.setCompletedWidth(release.getCompletedWidth());
    this.setReleaseNumber(release.getReleaseNumber());
    this.setStructureUse(release.getStructureUse());
    this.setComments(release.getComments());
    this.setNumberPublic(release.getNumberPublic());
    this.setNumberPrivate(release.getNumberPrivate());
    this.setCodeDesignerId(release.getCodeDesignerId());
    this.setDesignerFirmName(release.getDesignerFirmName());
     this.setDesignerFirstName(release.getDesignerFirstName());
    this.setDesignerLastName(release.getDesignerLastName());
    this.setAddress1(release.getAddress1());
    this.setAddress2(release.getAddress2());
    this.setCity(release.getCity());
    this.setState(release.getState());
    this.setZip(release.getZip());
    this.setDesignerIndianaNumber(release.getDesignerIndianaNumber());
    this.setDesignerType(release.getDesignerType());
    this.setTelephone(release.getTelephone());
    this.setEmail(release.getEmail());
    this.setFilingStatus(release.getFilingStatus());
    this.setAddPlans(release.getAddPlans());
    this.setAddStructures(release.getAddStructures());
    this.setApplicationKey(release.getApplicationKey());
     this.setPrevReleaseNumber(release.getPrevReleaseNumber());
     this.setAddReleaseNumber(release.getAddReleaseNumber());
     this.setAddSequence(release.getAddSequence());
     this.setConstructionCode(release.getConstructionCode());
     this.setOccCode(release.getOccCode());
     this.setReleaseDate(release.getReleaseDateString());
     this.setSystemId(release.getSystemId());
     this.setDesignerId(release.getDesignerId());
     this.setManufacturerEntityId(release.getManufacturerEntityId());
     this.setBuildType(release.getBuildType());
     this.setCodeReference(release.getCodeReference());
     this.setReviewerId(release.getReviewerId());
     this.setReleaseScope(release.getReleaseScope());
     this.setReleaseApproval(release.getReleaseApproval());
     this.setFacilityId1(release.getFacilityId1());
     this.setFacilityId2(release.getFacilityId2());
     this.setFacilityId3(release.getFacilityId3());
     this.setFacilityId4(release.getFacilityId4());
  }
 public CodeDesignRelease getCodeDesignRelease() throws Exception
  {
    
    CodeDesignRelease release = new CodeDesignRelease ();
    release.setSystemType(this.getSystemType());
    release.setReleaseType(this.getReleaseType());
    release.setLengthsOfUnit(this.getLengthsOfUnit());
    release.setUnitStructural(this.getUnitStructural());
    release.setUnitElectrical(this.getUnitElectrical());
    release.setUnitPlumbing(this.getUnitPlumbing());
    release.setUnitMechanical(this.getUnitMechanical());
    release.setUnitSprinkler(this.getUnitSprinkler());
    release.setUnitFireAlaram(this.getUnitFireAlaram());
    release.setUnitHood(this.getUnitHood());
    release.setOccupancy(this.getOccupancy());
    release.setNumberStories(this.getNumberStories());
    release.setNumberInstructure(this.getNumberInstructure());
    release.setCompletedWidth(this.getCompletedWidth());
    release.setReleaseNumber(this.getReleaseNumber());
    release.setStructureUse(this.getStructureUse());
    release.setComments(this.getComments());
    release.setNumberPublic(this.getNumberPublic());
    release.setNumberPrivate(this.getNumberPrivate());
    release.setCodeDesignerId(this.getCodeDesignerId());               
    release.setDesignerFirmName(this.getDesignerFirmName());             
    release.setDesignerFirstName(this.getDesignerFirstName());            
    release.setDesignerLastName(this.getDesignerLastName());             
    release.setAddress1(this.getAddress1());                       
    release.setAddress2(this.getAddress2());
    release.setCity(this.getCity());
    release.setState(this.getState());                          
    release.setZip(this.getZip());                            
    release.setDesignerIndianaNumber(this.getDesignerIndianaNumber());        
    release.setDesignerType(this.getDesignerType());                  
    release.setTelephone(this.getTelephone()); 
    release.setEmail(this.getEmail());   
    release.setFilingStatus(this.getFilingStatus());
    release.setAddPlans(this.getAddPlans());
    release.setAddStructures(this.getAddStructures());
    release.setApplicationKey(this.getApplicationKey());
    release.setPrevReleaseNumber(this.getPrevReleaseNumber());
    release.setAddReleaseNumber(this.getAddReleaseNumber());
    release.setAddSequence(this.getAddSequence());
    release.setConstructionCode(this.getConstructionCode());
    release.setOccCode(this.getOccCode());
    release.setReleaseDateString(this.getReleaseDate());
    release.setDesignerId(this.getDesignerId());
    release.setSystemId(this.getSystemId());
    release.setManufacturerEntityId(this.getManufacturerEntityId());
    release.setBuildType(this.getBuildType());
    release.setCodeReference(this.getCodeReference());
    release.setReviewerId(this.getReviewerId());
    release.setReleaseScope(this.getReleaseScope());
    release.setReleaseApproval(this.getReleaseApproval());
    release.setFacilityId1(this.getFacilityId1());
    release.setFacilityId2(this.getFacilityId2());
    release.setFacilityId3(this.getFacilityId3());
    release.setFacilityId4(this.getFacilityId4());
    return(release);
  }
  public int getDesignerId()
  { 
   return designerId;
  }
public void setDesignerId(int designerId)
  { 
  this.designerId = designerId;

}
  
   public String getSystemType() 
  {
    return systemType;
  }
  public void setSystemType(String systemType) 
  {
    this.systemType=systemType;
  }
  
  public String getLengthsOfUnit()
 { 
 return lengthsOfUnit;
 }
 public void setLengthsOfUnit(String lengthsOfUnit)
 { 
 this.lengthsOfUnit = lengthsOfUnit;
 }
  
 public String getUnitStructural()
  { 
   return unitStructural;
  }
 public void setUnitStructural(String unitStructural)
  { 
  this.unitStructural = unitStructural;
  }

public String getUnitElectrical()
  { 
  return  unitElectrical;
  }
public void setUnitElectrical(String unitElectrical)
  { 
  this.unitElectrical = unitElectrical;
  }
public String getUnitMechanical()
  { 
    return  unitMechanical;
  }
public void setUnitMechanical(String unitMechanical)
  { 
  this.unitMechanical = unitMechanical;
  }
public String getUnitPlumbing()
  { 
    return  unitPlumbing;
  }
public void setUnitPlumbing(String unitPlumbing)
  { 
  this.unitPlumbing = unitPlumbing;
  }
public String getUnitSprinkler()
  { 
    return  unitSprinkler;
  }
public void setUnitSprinkler(String unitSprinkler)
  { 
  this.unitSprinkler = unitSprinkler;
  }
public String getUnitFireAlaram()
  { 
    return  unitFireAlaram;
  }
public void setUnitFireAlaram(String unitFireAlaram)
  { 
  this.unitFireAlaram = unitFireAlaram;
  }
public String getUnitHood()
  { 
    return  unitHood;
  }
public void setUnitHood(String unitHood)
  { 
  this.unitHood = unitHood;
  }
public String getOccupancy()
  { 
    return  occupancy;
  }
public void setOccupancy(String occupancy)
  { 
  this.occupancy = occupancy;
  }
public  int getNumberStories()
  { 
    return  numberStories;
  }
public void setNumberStories( int numberStories)
  { 
  this.numberStories = numberStories;
  } 
public  int getNumberInstructure()
  { 
    return  numberInstructure;
  }
public void setNumberInstructure( int numberInstructure)
  { 
  this.numberInstructure = numberInstructure;
  } 
 public String getCompletedWidth()
  { 
    return  completedWidth;
  }
public void setCompletedWidth(String completedWidth)
  { 
  this.completedWidth = completedWidth;
  } 
public String getReleaseNumber()
  { 
    return  releaseNumber;
  }
public void setReleaseNumber(String releaseNumber)
  { 
  this.releaseNumber = releaseNumber;
  }  
public String getStructureUse()
  { 
    return  structureUse;
  }
public void setStructureUse(String structureUse)
  { 
  this.structureUse = structureUse;
  }
public String getComments()
  { 
    return  comments;
  }
public void setComments(String comments)
  { 
  this.comments = comments;
  }
public int getNumberPublic()
  { 
    return  numberPublic;
  }
public void setNumberPublic(int numberPublic)
  { 
  this.numberPublic = numberPublic;
  }
public int getNumberPrivate()
  { 
    return  numberPrivate;
  }
public void setNumberPrivate(int numberPrivate)
  { 
  this.numberPrivate = numberPrivate;
  }
  public int getCodeDesignerId()
  { 
   return  codeDesignerId;
  }
 public void setCodeDesignerId(int codeDesignerId)
  { 
  this.codeDesignerId = codeDesignerId;
  }
  public String getEmail()
  { 
   return  email;
  }
public void setEmail(String email)
  { 
  this.email = email;
  }
  public String getTelephone()
  { 
   return  telephone;
  }
public void setTelephone(String telephone)
  { 
  this.telephone = telephone;
  }
public String getDesignerType()
  { 
   return  designerType;
  }
public void setDesignerType(String designerType)
  { 
  this.designerType = designerType;
  }  
public String getDesignerIndianaNumber()
  { 
   return  designerIndianaNumber;
  }
public void setDesignerIndianaNumber(String designerIndianaNumber)
  { 
  this.designerIndianaNumber = designerIndianaNumber;
  }
public String getZip()
  { 
   return  zip;
  }
public void setZip(String zip)
  { 
  this.zip = zip;
  }
public String getState()
  { 
   return  state;
  }
public void setState(String state)
  { 
  this.state = state;
  }
public String getCity()
  { 
   return  city;
  }
public void setCity(String city)
  { 
  this.city = city;
  }
public String getAddress2()
  { 
   return  address2;
  }
public void setAddress2(String address2)
  { 
  this.address2 = address2;
  }
public String getAddress1()
  { 
   return  address1;
  }
public void setAddress1(String address1)
  { 
  this.address1 = address1;
  }
 public String getDesignerLastName()
  { 
   return  designerLastName;
  }
public void setDesignerLastName(String designerLastName)
  { 
  this.designerLastName = designerLastName;
  }
  public String getDesignerFirstName()
  { 
   return  designerFirstName;
  }
public void setDesignerFirstName(String designerFirstName)
  { 
  this.designerFirstName = designerFirstName;
  }
  public String getDesignerFirmName()
  { 
   return  designerFirmName;
  }
public void setDesignerFirmName(String designerFirmName)
  { 
  this.designerFirmName = designerFirmName;
  }
  
 public String getFilingStatus()
  { 
   return filingStatus;
  }
 public void setFilingStatus(String filingStatus)
  { 
  this.filingStatus = filingStatus;
  }
   public int getAddPlans()
  { 
   return addPlans;
  }
 public void setAddPlans(int addPlans)
  { 
  this.addPlans = addPlans;
  }
  public int getAddStructures()
  { 
   return addStructures;
  }
 public void setAddStructures(int addStructures)
  { 
  this.addStructures = addStructures;
  }
 public String getReleaseType()
  { 
   return releaseType;
  } 
   public void setReleaseType(String releaseType)
  {
    this.releaseType = releaseType;
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
    public String getIdNumber()
  { 
   return idNumber;
  }
public void setIdNumber(String idNumber)
  { 
  this.idNumber = idNumber;
  }
   public String getIdType()
  { 
   return idType;
  }
public void setIdType(String idType)
  { 
  this.idType = idType;
  }
   public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public String getReleaseDate()
  { 
   return releaseDate;
  }
public void setReleaseDate(String releaseDate)
  { 
  this.releaseDate =
releaseDate;
  }
public String getAddReleaseNumber()
  { 
   return addReleaseNumber;
  }
public void setAddReleaseNumber(String addReleaseNumber)
  { 

this.addReleaseNumber = addReleaseNumber;
  }
public  int getAddSequence()
  { 
   return addSequence;
  }
public void setAddSequence( int addSequence)
  { 
  this.addSequence =
addSequence;
  }
public String getConstructionCode()
  { 
   return constructionCode;
  }
public void setConstructionCode(String constructionCode)
  { 

this.constructionCode = constructionCode;
  }
public String getOccCode()
  { 
   return occCode;
  }
public void setOccCode(String occCode)
  { 
  this.occCode = occCode;
  }
public String getPrevReleaseNumber()
  { 
   return prevReleaseNumber;
  }
public void setPrevReleaseNumber(String prevReleaseNumber)
  { 

this.prevReleaseNumber = prevReleaseNumber;
  }

public int getReviewerId()
  { 
   return reviewerId;
  }
public void setReviewerId(int reviewerId)
  { 
  this.reviewerId = reviewerId;

}
 public int getSystemId()
  {
    return systemId;
  }

  public void setSystemId(int systemId)
  {
    this.systemId = systemId;
  }
  public int getManufacturerEntityId() 
  {
    return manufacturerEntityId;
  }
  public void setManufacturerEntityId(int manufacturerEntityId) 
  {
    this.manufacturerEntityId=manufacturerEntityId;
  }
   public String getCannedCode()
  { 
   return cannedCode;
  }
public void setCannedCode(String cannedCode)
  { 
  this.cannedCode =
cannedCode;
  }
  public String getBuildType()
  { 
   return buildType;
  }
public void setBuildType(String  buildType)
  { 
  this. buildType =  buildType;
  }
   public String getCodeReference()
  { 
   return codeReference;
  }
public void setCodeReference(String  codeReference)
  { 
  this. codeReference =  codeReference;
  }
  
    public String getReleaseScope()
  { 
   return releaseScope;
  } 
   public void setReleaseScope(String releaseScope)
  {
    this.releaseScope = releaseScope;
  }
   public String getReleaseApproval()
  { 
   return releaseApproval;
  } 
   public void setReleaseApproval(String releaseApproval)
  {
    this.releaseApproval = releaseApproval;
  }
  public String getFacilityId1()
  { 
   return facilityId1;
  }
public void setFacilityId1(String facilityId1)
  { 
  this.facilityId1 = facilityId1;
  }
public String getFacilityId2()
  { 
   return facilityId2;
  }
public void setFacilityId2(String facilityId2)
  { 
  this.facilityId2 = facilityId2;
  }
public String getFacilityId3()
  { 
   return facilityId3;
  }
public void setFacilityId3(String facilityId3)
  { 
  this.facilityId3 = facilityId3;
  }
public String getFacilityId4()
  { 
   return facilityId4;
  }
public void setFacilityId4(String facilityId4)
  { 
  this.facilityId4 = facilityId4;
  }
}
