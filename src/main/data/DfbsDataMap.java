package main.data;
import hs.data.*;
public class DfbsDataMap extends DataMap
{//general
public static final String UTILITY = "hs.data.system.HsUtilityDAO";
public static final String DFBS_SECURITY = "main.data.ApplicationSecurityDAO";
public static final String FIRE_REPORTS = "main.data.FireReportsDAO";
public static final String DFBS_REPORTS = "main.data.DfbsReportsDAO";

//code education
 public static final String CODE_COURSE = "codeEducation.data.CodeEducationCourseDAO"; 
 public static final String CODE_USER = "codeEducation.data.CodeEducationUserDAO"; 
 public static final String CODE_EDU_PLAN = "codeEducation.data.CodeEducationPlanDAO"; 
  // idhs inspections 
  public static final String IDHS_INSPECTION_SEARCH = "idhsInspections.data.idhsInspectionsDAO"; 
  public static final String FIRE_INSPECTION = "idhsInspections.data.fireInspectionDAO";
  public static final String IDHS_INSPECTION = "idhsInspections.data.idhsInspectionsDAO";
  public static final String FIRE_VIOLATION = "idhsInspections.data.fireViolationDAO";
  public static final String FIRE_INSP_ACTIVITY = "idhsInspections.data.fireInspActivityDAO";
  public static final String FIRE_AE_FAC_USE = "idhsInspections.data.fireAEPermitApprovalDAO";
   public static final String IDHS_INSPECTION_RULE13 = "idhsInspections.data.rule13DAO";  
    
    // fireworks
    public static final String FIREWORKS_OWNER = "fireworks.data.DfbsFireworksOwnerDAO";
    public static final String FIREWORKS_PERMIT = "fireworks.data.DfbsFireworksPermitDAO";
    // aepermits
    public static final String AEPERMITS_OWNER = "aepermits.data.DfbsEntrOwnerDAO";
    public static final String AEPERMITS_PERMIT = "aepermits.data.DfbsEntrPermitDAO";
    public static final String AEPERMITS_SPECIAL_PERMIT = "aepermits.data.DfbsEntrSpecialDAO";
    public static final String AEPERMITS_SHOPPING_CART = "aepermits.data.ShoppingCartDAO";
  // child care
  public static final String CHILDCARE_OWNER = "childcare.data.DfbsChildcareOwnerDAO";
  public static final String CHILDCARE_LICENSE = "childcare.data.DfbsChildcarePermitDAO";
  public static final String CHILDCARE_CONTACT = "childcare.data.DfbsContactDAO";
  // dispaly
  public static final String DISPLAY_OWNER = "display.data.DfbsOwnerDAO";
  public static final String DISPLAY_PERMIT = "display.data.DfbsDisplayDAO";
  public static final String DISPLAY_CONTACT = "display.data.DfbsContactDAO";
  // ems only on part  done rest differed
   public static final String EMS_PERSON = "ems.data.EmsPersonDAO";
    // cigarette
     public static final String CIG_COMPANY = "cigarette.data.CigaretteCompanyDAO";
     public static final String CIG_APPLICATION = "cigarette.data.CigaretteApplicationDAO";
     public static final String CIG_BRAND = "cigarette.data.CigaretteBrandDAO"; 
     public static final String CIG_USER = "cigarette.data.CigaretteUsersDAO";  
  // code 
  public static final String MANUFACTURER = "code.data.CodeManufacturerDAO";
  public static final String FACILITY = "code.data.CodeFacilityDAO";
  public static final String PERSON = "code.data.CodePersonDAO";
  public static final String RELEASE = "code.data.CodeDesignReleaseDAO";
  // arson
  public static final String ARSON_UPLOAD = "arson.data.ArsonUploadDAO";
   // hazmat
  public static final String HAZMAT_ORG = "hazmat.data.HazmatPermitDAO";
  public static final String HAZMAT_CARRIER = "hazmat.data.HazmatCarrierDAO";
  public static final String HAZMAT_SHIPMENT = "hazmat.data.HazmatShipmentDAO";
  public static final String DFBS_IMAGES="hazmat.data.HazmatImageDAO";
  //magazine
   public static final String MAGAZINE_OWNER = "magazine.data.DfbsOwnerDAO";
  public static final String MAGAZINE_PERMIT = "magazine.data.DfbsPermitDAO";
  public static final String MAG_SHOPPING_CART = "magazine.data.ShoppingCartDAO";
  // to check why this is causing a problem later
// public static final String MAGAZINE_CONTACT = "magazine.data.DfbsContactDAO";
 // public static final String FIRE_MAG_INSPECTION = "magazine.data.FireInspectionDAO";
 // public static final String FIRE_MAG_VIOLATION = "magazine.data.FireViolationDAO";
 public DfbsDataMap(HsDataSource ds) throws Exception
 { 
   super(ds);
   this.initialize(this,this.getDataSource());
 }
  
}