package idhsInspections.control.form;
import idhsInspections.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

public class fireInspectionForm extends ActionForm{
    private int inspId;
      private String inspDate;
      private String inspVioPrintDate;
      private String inspFacId;
      private String inspDistrict;
      private String inspOccLoad;
      private String inspAlarm;
      private String inspSprinkler;
      private int inspInspectorId;
      private String inspType;
      private String inspStatus;
      private String inspCompliance;
      private String inspRemarks;
      private String inspPermitPrint;
      private String userId;
      private String userPassword;
      private int editFlag;
    private String facilityId;
    private String facilityName;
    private String facilitySteetNumber;
    private String facilityStreetPrefix;
    private String facilityStreetName;
    private String facilityStreetSuffix;
    private String facilityAddress2;
    private String facilityCity;
    private String facilityCounty;
    private String facilityState;
    private String facilityZip;
    private String facilityZip2;
    private String facilityContact;
    private String facilityPhone;
    private String facilityEmail;
    private String inspConstFoundation;
    private String inspConstSlab;
    private String inspConstFraming;
    private String inspConstPlumbing;
    private String inspConstMech;
    private String inspConstElec;
    private String inspConstEnergy;
    private String inspConstSprinkler;
    private String inspConstInterior;
    private String inspConstPool;
    private String inspConstEgress;
    private String inspConstSuppression;
    private String inspConstFireExt;
    private String inspProjNew;
    private String inspProjAddition;
    private String inspProjRemodel;
    private String inspProjOccChange;
    private String inspProjExisting;
    private String inspMaintType;
    private String inspOtherActivity;
    private String inspFireDrillCurrent;
    private String inspFireEvacCurrent;
    private String inspEmpTrainCurrent;
    private String inspSensitivityCurrent;
    private String inspExtLightCurrent;
    private String inspFireExtCurrent;
    private String inspSprinklerCurrent;
    private String inspAlarmCurrent;
    private String inspSiteCurrent;
    private String inspHoodCurrent;
    private String inspViolationStatus;
    private String nextInspDate;
    private String recCreatedDate;
    private transient FormFile caseFile = null;//get & set methods
    private String idNumber;
    private String idType;
    private String inspStartDate;
    private String inspEndDate;
    private String inspGpStartDate;
    private String inspGpEndDate;
    private String inspUploadError;
    private String inspConstBackFlow;
     private String inspConstHood;
     private String inspConstFinal;
    private String vioYear;
    private String vioCode;
    private String vioDescription;
   private String vioType;
   private String emailTo;
   private String notes;
   private String createNewAE;
  private String createNewBU;
  private String createNewJP;
  private String createNewHM;
  private String inspectionAction;
  private String fineStartDate;
  private String fineEndDate;
  private String inspOldNewFlag;
    
    public fireInspectionForm() {
    }
  public String getInspectionAction()
   { 
    return inspectionAction;
   }
  public void setInspectionAction(String inspectionAction)
   { 
   this.inspectionAction = inspectionAction;
   }
  public String getInspOldNewFlag()
   { 
    return inspOldNewFlag;
   }
  public void setInspOldNewFlag(String inspOldNewFlag)
   { 
   this.inspOldNewFlag = inspOldNewFlag;
   }
    public String getNotes()
     { 
      return notes;
     }
    public void setNotes(String notes)
     { 
     this.notes = notes;
     }
    public FormFile getCaseFile()
     { 
      return caseFile;
     }
    public void setCaseFile( FormFile caseFile)
     { 
     this.caseFile = caseFile;
     } 
    public String getEmailTo()
     { 
      return emailTo;
     }
    public void setEmailTo(String emailTo)
     { 
     this.emailTo = emailTo;
     }
    public String getInspUploadError()
     { 
      return inspUploadError;
     }
    public void setInspUploadError(String inspUploadError)
     { 
     this.inspUploadError = inspUploadError;
     }
    public void setUserId(String userId)
      { 
      this.userId = userId;
      }
       public String getUserPassword()
      { 
       return userPassword;
      }
      public void setUserPassword(String userPassword)
      { 
      this.userPassword = userPassword;
      }
    public String getRecCreatedDate()
     { 
      return recCreatedDate;
     }
    public void setRecCreatedDate(String recCreatedDate)
     { 
     this.recCreatedDate = recCreatedDate;
     }
       public String getInspDate()
      { 
       return inspDate;
      }
    public void setInspDate(String inspDate)
      { 
      this.inspDate = inspDate;
      }
    public String getInspVioPrintDate()
      { 
       return inspVioPrintDate;
      }
    public void setInspVioPrintDate(String inspVioPrintDate)
      { 
      this.inspVioPrintDate = inspVioPrintDate;
      }
    public String getInspAlarm()
      { 
       return inspAlarm;
      }
    public void setInspAlarm(String inspAlarm)
      { 
      this.inspAlarm = inspAlarm;
      }
    public String getInspDistrict()
      { 
       return inspDistrict;
      }
    public void setInspDistrict(String inspDistrict)
      { 
      this.inspDistrict = inspDistrict;
      }
    public String getInspFacId()
      { 
       return inspFacId;
      }
    public void setInspFacId(String inspFacId)
      { 
      this.inspFacId = inspFacId;
      }
    public String getInspOccLoad()
      { 
       return inspOccLoad;
      }
    public void setInspOccLoad(String inspOccLoad)
      { 
      this.inspOccLoad = inspOccLoad;
      }
    public String getInspSprinkler()
      { 
       return inspSprinkler;
      }
    public void setInspSprinkler(String inspSprinkler)
      { 
      this.inspSprinkler = inspSprinkler;
      }
    public int getInspId()
      { 
       return inspId;
      }
    public void setInspId(int inspId)
      { 
      this.inspId = inspId;
      }
      public String getInspCompliance()
      { 
       return inspCompliance;
      }
    public void setInspCompliance(String inspCompliance)
      { 
      this.inspCompliance = inspCompliance;
      }
    public int getInspInspectorId()
      { 
       return inspInspectorId;
      }
    public void setInspInspectorId(int inspInspectorId)
      { 
      this.inspInspectorId = inspInspectorId;
      }
    public String getInspPermitPrint()
      { 
       return inspPermitPrint;
      }
    public void setInspPermitPrint(String inspPermitPrint)
      { 
      this.inspPermitPrint = inspPermitPrint;
      }
    public String getInspRemarks()
      { 
       return inspRemarks;
      }
    public void setInspRemarks(String inspRemarks)
      { 
      this.inspRemarks = inspRemarks;
      }
    public String getInspStatus()
      { 
       return inspStatus;
      }
    public void setInspStatus(String inspStatus)
      { 
      this.inspStatus = inspStatus;
      }
    public String getInspType()
      { 
       return inspType;
      }
    public void setInspType(String inspType)
      { 
      this.inspType = inspType;
      }
    public String getInspConstEgress()
      { 
       return inspConstEgress;
      }
    public void setInspConstEgress(String inspConstEgress)
      { 
      this.inspConstEgress = inspConstEgress;
      }
    public String getInspConstFireExt()
      { 
       return inspConstFireExt;
      }
    public void setInspConstFireExt(String inspConstFireExt)
      { 
      this.inspConstFireExt = inspConstFireExt;
      }
    public String getInspConstSuppression()
      { 
       return inspConstSuppression;
      }
    public void setInspConstSuppression(String inspConstSuppression)
      { 
      this.inspConstSuppression = inspConstSuppression;
      }
    public String getInspExtLightCurrent()
      { 
       return inspExtLightCurrent;
      }
    public void setInspExtLightCurrent(String inspExtLightCurrent)
      { 
      this.inspExtLightCurrent = inspExtLightCurrent;
      }
    public String getInspFireDrillCurrent()
      { 
       return inspFireDrillCurrent;
      }
    public void setInspFireDrillCurrent(String inspFireDrillCurrent)
      { 
      this.inspFireDrillCurrent = inspFireDrillCurrent;
      }
    public String getInspFireEvacCurrent()
      { 
       return inspFireEvacCurrent;
      }
    public void setInspFireEvacCurrent(String inspFireEvacCurrent)
      { 
      this.inspFireEvacCurrent = inspFireEvacCurrent;
      }
    public String getInspFireExtCurrent()
      { 
       return inspFireExtCurrent;
      }
    public void setInspFireExtCurrent(String inspFireExtCurrent)
      { 
      this.inspFireExtCurrent = inspFireExtCurrent;
      }
    public String getInspSensitivityCurrent()
      { 
       return inspSensitivityCurrent;
      }
    public void setInspSensitivityCurrent(String inspSensitivityCurrent)
      { 
      this.inspSensitivityCurrent = inspSensitivityCurrent;
      }
       public void setProperties(fireInspection inspection) 
      {  
        this.setInspAlarm(inspection.getInspAlarm());
        this.setInspCompliance(inspection.getInspCompliance());
        this.setInspDate(inspection.getInspDateString());
        this.setInspDistrict(inspection.getInspDistrict());
        this.setInspFacId(inspection.getInspFacId());
        this.setInspId(inspection.getInspId());
        this.setInspInspectorId(inspection.getInspInspectorId());
        this.setInspOccLoad(inspection.getInspOccLoad());
        this.setInspPermitPrint(inspection.getInspPermitPrint());
        this.setInspRemarks(inspection.getInspRemarks());
        this.setInspSprinkler(inspection.getInspSprinkler());
        this.setInspStatus(inspection.getInspStatus());
        this.setInspType(inspection.getInspType());
        this.setInspVioPrintDate(inspection.getInspVioPrintDateString());
        this.setEditFlag(inspection.getEditFlag());
          this.setNextInspDate(inspection.getNextInspDateString());
          this.setInspAlarmCurrent(inspection.getInspAlarmCurrent());
          this.setInspHoodCurrent(inspection.getInspHoodCurrent());
          this.setInspMaintType(inspection.getInspMaintType());
          this.setInspOtherActivity(inspection.getInspOtherActivity());
          this.setInspProjAddition(inspection.getInspProjAddition());
          this.setInspProjExisting(inspection.getInspProjExisting());
          this.setInspProjNew(inspection.getInspProjNew());
          this.setInspProjOccChange(inspection.getInspProjOccChange());
          this.setInspProjRemodel(inspection.getInspProjRemodel());
          this.setInspSprinklerCurrent(inspection.getInspSprinklerCurrent());
          this.setInspViolationStatus(inspection.getInspViolationStatus());
          this.setFacilityAddress2(inspection.getFacilityAddress2());
          this.setFacilityCity(inspection.getFacilityCity());
          this.setFacilityContact(inspection.getFacilityContact());
          this.setFacilityCounty(inspection.getFacilityCounty());
          this.setFacilityEmail(inspection.getFacilityEmail());
          this.setFacilityId(inspection.getFacilityId());
          this.setFacilityName(inspection.getFacilityName());
          this.setFacilityPhone(inspection.getFacilityPhone());
          this.setFacilityState(inspection.getFacilityState());
          this.setFacilitySteetNumber(inspection.getFacilitySteetNumber());
          this.setFacilityStreetName(inspection.getFacilityStreetName());
          this.setFacilityStreetPrefix(inspection.getFacilityStreetPrefix());
          this.setFacilityStreetSuffix(inspection.getFacilityStreetSuffix());
          this.setFacilityZip(inspection.getFacilityZip());
          this.setFacilityZip2(inspection.getFacilityZip2());
          this.setInspConstElec(inspection.getInspConstElec());
          this.setInspConstBackFlow(inspection.getInspConstBackFlow());
          this.setInspConstEnergy(inspection.getInspConstEnergy());
          this.setInspConstFoundation(inspection.getInspConstFoundation());
          this.setInspConstFraming(inspection.getInspConstFraming());
          this.setInspConstInterior(inspection.getInspConstInterior());
          this.setInspConstMech(inspection.getInspConstMech());
          this.setInspConstPlumbing(inspection.getInspConstPlumbing());
          this.setInspConstPool(inspection.getInspConstPool());
          this.setInspConstSlab(inspection.getInspConstSlab());
          this.setInspConstHood(inspection.getInspConstHood());
          this.setInspConstSprinkler(inspection.getInspConstSprinkler());
          this.setInspConstFinal(inspection.getInspConstFinal());
          this.setRecCreatedDate(inspection.getRecCreatedDateString());
          this.setNextInspDate(inspection.getNextInspDateString());
          this.setInspUploadError(inspection.getInspUploadError());
          this.setInspConstEgress(inspection.getInspConstEgress());
          this.setInspConstFireExt(inspection.getInspConstFireExt());
          this.setInspConstSuppression(inspection.getInspConstSuppression());
          this.setInspEmpTrainCurrent(inspection.getInspEmpTrainCurrent());
          this.setInspExtLightCurrent(inspection.getInspExtLightCurrent());
          this.setInspFireDrillCurrent(inspection.getInspFireDrillCurrent());
          this.setInspFireEvacCurrent(inspection.getInspFireEvacCurrent());
          this.setInspFireExtCurrent(inspection.getInspFireExtCurrent());
          this.setInspSensitivityCurrent(inspection.getInspSensitivityCurrent());
          this.setInspSiteCurrent(inspection.getInspSiteCurrent());
          this.setNotes(inspection.getNotes());
      }
        public fireInspection getFireInspection() 
        {   fireInspection inspection = new fireInspection();
          inspection.setInspAlarm(getInspAlarm());
          inspection.setInspCompliance(getInspCompliance());
          inspection.setInspDateString(getInspDate());
          inspection.setInspDistrict(getInspDistrict());
          inspection.setInspFacId(getInspFacId());
          inspection.setInspId(getInspId());
          inspection.setInspInspectorId(getInspInspectorId());
          inspection.setInspOccLoad(getInspOccLoad());
          inspection.setInspPermitPrint(getInspPermitPrint());
          inspection.setInspRemarks(getInspRemarks());
          inspection.setInspSprinkler(getInspSprinkler());
          inspection.setInspStatus(getInspStatus());
          inspection.setInspType(getInspType());
          inspection.setInspVioPrintDateString(getInspVioPrintDate());
          inspection.setEditFlag(getEditFlag());
            inspection.setFacilityAddress2(this.getFacilityAddress2());
            inspection.setFacilityCity(this.getFacilityCity());
            inspection.setFacilityContact(this.getFacilityContact());
            inspection.setFacilityCounty(this.getFacilityCounty());
            inspection.setFacilityEmail(this.getFacilityEmail());
            inspection.setFacilityId(this.getFacilityId());
            inspection.setFacilityName(this.getFacilityName());
            inspection.setFacilityPhone(this.getFacilityPhone());
            inspection.setFacilityState(this.getFacilityState());
            inspection.setFacilitySteetNumber(this.getFacilitySteetNumber());
            inspection.setFacilityStreetName(this.getFacilityStreetName());
            inspection.setFacilityStreetPrefix(this.getFacilityStreetPrefix());
            inspection.setFacilityStreetSuffix(this.getFacilityStreetSuffix());
            inspection.setFacilityZip(this.getFacilityZip());
            inspection.setFacilityZip2(this.getFacilityZip2());
            inspection.setInspConstElec(this.getInspConstElec());
            inspection.setInspConstBackFlow(this.getInspConstBackFlow());
            inspection.setInspConstEnergy(this.getInspConstEnergy());
            inspection.setInspConstFoundation(this.getInspConstFoundation());
            inspection.setInspConstFraming(this.getInspConstFraming());
            inspection.setInspConstInterior(this.getInspConstInterior());
            inspection.setInspConstMech(this.getInspConstMech());
            inspection.setInspConstPlumbing(this.getInspConstPlumbing());
            inspection.setInspConstPool(this.getInspConstPool());
            inspection.setInspConstSlab(this.getInspConstSlab());
            inspection.setInspConstHood(this.getInspConstHood());
            inspection.setInspConstSprinkler(this.getInspConstSprinkler());
            inspection.setNextInspDateString(this.getNextInspDate());
            inspection.setInspAlarmCurrent(this.getInspAlarmCurrent());
             inspection.setInspHoodCurrent(this.getInspHoodCurrent());
            inspection.setInspMaintType(this.getInspMaintType());
            inspection.setInspOtherActivity(this.getInspOtherActivity());
            inspection.setInspProjAddition(this.getInspProjAddition());
            inspection.setInspProjExisting(this.getInspProjExisting());
            inspection.setInspProjNew(this.getInspProjNew());
            inspection.setInspProjOccChange(this.getInspProjOccChange());
            inspection.setInspProjRemodel(this.getInspProjRemodel());
             inspection.setInspSprinklerCurrent(this.getInspSprinklerCurrent());
            inspection.setInspViolationStatus(this.getInspViolationStatus());
            inspection.setRecCreatedDateString(this.getRecCreatedDate());
            inspection.setNextInspDateString(this.getNextInspDate());
            inspection.setInspUploadError(this.getInspUploadError());
            inspection.setInspConstEgress(this.getInspConstEgress());
            inspection.setInspConstFireExt(this.getInspConstFireExt());
            inspection.setInspConstSuppression(this.getInspConstSuppression());
            inspection.setInspEmpTrainCurrent(this.getInspEmpTrainCurrent());
            inspection.setInspExtLightCurrent(this.getInspExtLightCurrent());
            inspection.setInspFireDrillCurrent(this.getInspFireDrillCurrent());
            inspection.setInspFireEvacCurrent(this.getInspFireEvacCurrent());
            inspection.setInspFireExtCurrent(this.getInspFireExtCurrent());
            inspection.setInspSensitivityCurrent(this.getInspSensitivityCurrent());
            inspection.setInspConstFinal(this.getInspConstFinal());
            inspection.setInspSiteCurrent(this.getInspSiteCurrent());
            inspection.setNotes(this.getNotes());
          return inspection;
        }
       public int getEditFlag()
      { 
       return editFlag;
      }
    public void setEditFlag(int editFlag)
      { 
      this.editFlag = editFlag;
      } 
    public String getFacilityAddress2()
      { 
       return facilityAddress2;
      }
    public void setFacilityAddress2(String facilityAddress2)
      { 
      this.facilityAddress2 = facilityAddress2;
      }
    public String getFacilityCity()
      { 
       return facilityCity;
      }
    public void setFacilityCity(String facilityCity)
      { 
      this.facilityCity = facilityCity;
      }
    public String getFacilityCounty()
      { 
       return facilityCounty;
      }
    public void setFacilityCounty(String facilityCounty)
      { 
      this.facilityCounty = facilityCounty;
      }
    public String getFacilityId()
      { 
       return facilityId;
      }
    public void setFacilityId(String facilityId)
      { 
      this.facilityId = facilityId;
      }
    public String getFacilityName()
      { 
       return facilityName;
      }
    public void setFacilityName(String facilityName)
      { 
      this.facilityName = facilityName;
      }
    public String getFacilitySteetNumber()
      { 
       return facilitySteetNumber;
      }
    public void setFacilitySteetNumber(String facilitySteetNumber)
      { 
      this.facilitySteetNumber = facilitySteetNumber;
      }
    public String getFacilityStreetName()
      { 
       return facilityStreetName;
      }
    public void setFacilityStreetName(String facilityStreetName)
      { 
      this.facilityStreetName = facilityStreetName;
      }
    public String getFacilityStreetPrefix()
      { 
       return facilityStreetPrefix;
      }
    public void setFacilityStreetPrefix(String facilityStreetPrefix)
      { 
      this.facilityStreetPrefix = facilityStreetPrefix;
      }
    public String getFacilityStreetSuffix()
      { 
       return facilityStreetSuffix;
      }
    public void setFacilityStreetSuffix(String facilityStreetSuffix)
      { 
      this.facilityStreetSuffix = facilityStreetSuffix;
      }
    public String getFacilityContact()
      { 
       return facilityContact;
      }
    public void setFacilityContact(String facilityContact)
      { 
      this.facilityContact = facilityContact;
      }
    public String getFacilityEmail()
      { 
       return facilityEmail;
      }
    public void setFacilityEmail(String facilityEmail)
      { 
      this.facilityEmail = facilityEmail;
      }
    public String getFacilityPhone()
      { 
       return facilityPhone;
      }
    public void setFacilityPhone(String facilityPhone)
      { 
      this.facilityPhone = facilityPhone;
      }
    public String getFacilityState()
      { 
       return facilityState;
      }
    public void setFacilityState(String facilityState)
      { 
      this.facilityState = facilityState;
      }
    public String getFacilityZip()
      { 
       return facilityZip;
      }
    public void setFacilityZip(String facilityZip)
      { 
      this.facilityZip = facilityZip;
      }
    public String getFacilityZip2()
      { 
       return facilityZip2;
      }
    public void setFacilityZip2(String facilityZip2)
      { 
      this.facilityZip2 = facilityZip2;
      }
    public String getInspConstFoundation()
      { 
       return inspConstFoundation;
      }
    public void setInspConstFoundation(String inspConstFoundation)
      { 
      this.inspConstFoundation = inspConstFoundation;
      }
    public String getInspConstFraming()
      { 
       return inspConstFraming;
      }
    public void setInspConstFraming(String inspConstFraming)
      { 
      this.inspConstFraming = inspConstFraming;
      }
    public String getInspConstPlumbing()
      { 
       return inspConstPlumbing;
      }
    public void setInspConstPlumbing(String inspConstPlumbing)
      { 
      this.inspConstPlumbing = inspConstPlumbing;
      }
    public String getInspConstSlab()
      { 
       return inspConstSlab;
      }
    public void setInspConstSlab(String inspConstSlab)
      { 
      this.inspConstSlab = inspConstSlab;
      }
   
    public String getInspConstElec()
      { 
       return inspConstElec;
      }
    public void setInspConstElec(String inspConstElec)
      { 
      this.inspConstElec = inspConstElec;
      }
    public String getInspConstEnergy()
      { 
       return inspConstEnergy;
      }
    public void setInspConstEnergy(String inspConstEnergy)
      { 
      this.inspConstEnergy = inspConstEnergy;
      }
    
    public String getInspConstInterior()
      { 
       return inspConstInterior;
      }
    public void setInspConstInterior(String inspConstInterior)
      { 
      this.inspConstInterior = inspConstInterior;
      }
    public String getInspConstMech()
      { 
       return inspConstMech;
      }
    public void setInspConstMech(String inspConstMech)
      { 
      this.inspConstMech = inspConstMech;
      }
   
    public String getInspConstPool()
      { 
       return inspConstPool;
      }
    public void setInspConstPool(String inspConstPool)
      { 
      this.inspConstPool = inspConstPool;
      }
    public String getInspConstSprinkler()
      { 
       return inspConstSprinkler;
      }
    public void setInspConstSprinkler(String inspConstSprinkler)
      { 
      this.inspConstSprinkler = inspConstSprinkler;
      }
   
    public String getInspMaintType()
      { 
       return inspMaintType;
      }
    public void setInspMaintType(String inspMaintType)
      { 
      this.inspMaintType = inspMaintType;
      }
    public String getInspOtherActivity()
      { 
       return inspOtherActivity;
      }
    public void setInspOtherActivity(String inspOtherActivity)
      { 
      this.inspOtherActivity = inspOtherActivity;
      }
    public String getInspProjAddition()
      { 
       return inspProjAddition;
      }
    public void setInspProjAddition(String inspProjAddition)
      { 
      this.inspProjAddition = inspProjAddition;
      }
    public String getInspProjExisting()
      { 
       return inspProjExisting;
      }
    public void setInspProjExisting(String inspProjExisting)
      { 
      this.inspProjExisting = inspProjExisting;
      }
    public String getInspProjNew()
      { 
       return inspProjNew;
      }
    public void setInspProjNew(String inspProjNew)
      { 
      this.inspProjNew = inspProjNew;
      }
    public String getInspProjOccChange()
      { 
       return inspProjOccChange;
      }
    public void setInspProjOccChange(String inspProjOccChange)
      { 
      this.inspProjOccChange = inspProjOccChange;
      }
    public String getInspProjRemodel()
      { 
       return inspProjRemodel;
      }
    public void setInspProjRemodel(String inspProjRemodel)
      { 
      this.inspProjRemodel = inspProjRemodel;
      }
    public String getNextInspDate()
      { 
       return nextInspDate;
      }
    public void setNextInspDate(String nextInspDate)
      { 
      this.nextInspDate = nextInspDate;
      }
    public String getInspAlarmCurrent()
      { 
       return inspAlarmCurrent;
      }
    public void setInspAlarmCurrent(String inspAlarmCurrent)
      { 
      this.inspAlarmCurrent = inspAlarmCurrent;
      }
   
    public String getInspHoodCurrent()
      { 
       return inspHoodCurrent;
      }
    public void setInspHoodCurrent(String inspHoodCurrent)
      { 
      this.inspHoodCurrent = inspHoodCurrent;
      }
   
    public String getInspSprinklerCurrent()
      { 
       return inspSprinklerCurrent;
      }
    public void setInspSprinklerCurrent(String inspSprinklerCurrent)
      { 
      this.inspSprinklerCurrent = inspSprinklerCurrent;
      }
    public String getInspViolationStatus()
      { 
       return inspViolationStatus;
      }
    public void setInspViolationStatus(String inspViolationStatus)
      { 
      this.inspViolationStatus = inspViolationStatus;
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
    public String getInspStartDate()
    {
    return inspStartDate;
    }
    public void setInspStartDate(String inspStartDate)
    {
    this.inspStartDate = inspStartDate;
    }
    public String getInspEndDate()
    {
    return inspEndDate;
    }
    public void setInspEndDate(String inspEndDate)
    {
    this.inspEndDate = inspEndDate;
    }
    public String getInspEmpTrainCurrent()
      { 
       return inspEmpTrainCurrent;
      }
    public void setInspEmpTrainCurrent(String inspEmpTrainCurrent)
      { 
      this.inspEmpTrainCurrent = inspEmpTrainCurrent;
      }
    public String getInspConstBackFlow()
      { 
       return inspConstBackFlow;
      }
    public void setInspConstBackFlow(String inspConstBackFlow)
      { 
      this.inspConstBackFlow = inspConstBackFlow;
      }
    public String getInspConstHood()
      { 
       return inspConstHood;
      }
    public void setInspConstHood(String inspConstHood)
      { 
      this.inspConstHood = inspConstHood;
      }
    public String getInspConstFinal()
      { 
       return inspConstFinal;
      }
    public void setInspConstFinal(String inspConstFinal)
      { 
      this.inspConstFinal = inspConstFinal;
      }
    public String getInspSiteCurrent()
      { 
       return inspSiteCurrent;
      }
    public void setInspSiteCurrent(String inspSiteCurrent)
      { 
      this.inspSiteCurrent = inspSiteCurrent;
      }
    public void setNewInspProperties(fireInspection inspection) 
    {
     
       this.setFacilityAddress2(inspection.getFacilityAddress2());
       this.setFacilityCity(inspection.getFacilityCity());
       this.setFacilityContact(inspection.getFacilityContact());
       this.setFacilityCounty(inspection.getFacilityCounty());
       this.setFacilityEmail(inspection.getFacilityEmail());
       this.setInspFacId(inspection.getInspFacId());
       this.setFacilityName(inspection.getFacilityName());
       this.setFacilityPhone(inspection.getFacilityPhone());
       this.setFacilityState(inspection.getFacilityState());
       this.setFacilitySteetNumber(inspection.getFacilitySteetNumber());
       this.setFacilityStreetName(inspection.getFacilityStreetName());
       this.setFacilityStreetPrefix(inspection.getFacilityStreetPrefix());
       this.setFacilityStreetSuffix(inspection.getFacilityStreetSuffix());
       this.setFacilityZip(inspection.getFacilityZip());
       this.setFacilityZip2(inspection.getFacilityZip2());
       
    }
    public String getVioCode()
     { 
      return vioCode;
     }
    public void setVioCode(String vioCode)
     { 
     this.vioCode = vioCode;
     }
        public String getVioType()
         { 
          return vioType;
         }
        public void setVioType(String vioType)
         { 
         this.vioType = vioType;
         }
    public String getVioDescription()
     { 
      return vioDescription;
     }
    public void setVioDescription(String vioDescription)
     { 
     this.vioDescription = vioDescription;
     }
  public void setReInspectionProperties(fireInspection inspection) 
  {
     this.setInspDistrict(inspection.getInspDistrict());
   this.setInspFacId(inspection.getInspFacId());
   this.setInspId(inspection.getInspId());
   this.setInspInspectorId(inspection.getInspInspectorId());
    this.setInspStatus(inspection.getInspStatus());
   this.setInspType(inspection.getInspType());
    this.setInspMaintType(inspection.getInspMaintType());
     this.setInspOtherActivity(inspection.getInspOtherActivity());
    this.setFacilityAddress2(inspection.getFacilityAddress2());
     this.setFacilityCity(inspection.getFacilityCity());
     this.setFacilityContact(inspection.getFacilityContact());
     this.setFacilityCounty(inspection.getFacilityCounty());
     this.setFacilityEmail(inspection.getFacilityEmail());
     this.setFacilityId(inspection.getFacilityId());
     this.setFacilityName(inspection.getFacilityName());
     this.setFacilityPhone(inspection.getFacilityPhone());
     this.setFacilityState(inspection.getFacilityState());
     this.setFacilitySteetNumber(inspection.getFacilitySteetNumber());
     this.setFacilityStreetName(inspection.getFacilityStreetName());
     this.setFacilityStreetPrefix(inspection.getFacilityStreetPrefix());
     this.setFacilityStreetSuffix(inspection.getFacilityStreetSuffix());
     this.setFacilityZip(inspection.getFacilityZip());
     this.setFacilityZip2(inspection.getFacilityZip2());
     
  }
  public String getCreateNewAE()
    { 
     return createNewAE;
    }
  public void setCreateNewAE(String createNewAE)
    { 
    this.createNewAE =
  createNewAE;
    }
  public String getCreateNewBU()
    { 
     return createNewBU;
    }
  public void setCreateNewBU(String createNewBU)
    { 
    this.createNewBU =
  createNewBU;
    }
  public String getCreateNewHM()
    { 
     return createNewHM;
    }
  public void setCreateNewHM(String createNewHM)
    { 
    this.createNewHM =
  createNewHM;
    }
  public String getCreateNewJP()
    { 
     return createNewJP;
    }
  public void setCreateNewJP(String createNewJP)
    { 
    this.createNewJP =
  createNewJP;
    }
  public String getFineStartDate()
    { 
     return fineStartDate;
    }
  public void setFineStartDate(String fineStartDate)
    { 
    this.fineStartDate =  fineStartDate;
    }
  public String getFineEndDate()
    { 
     return fineEndDate;
    }
  public void setFineEndDate(String fineEndDate)
    { 
    this.fineEndDate =  fineEndDate;
    }

    public void setInspGpStartDate(String inspGpStartDate) {
        this.inspGpStartDate = inspGpStartDate;
    }

    public String getInspGpStartDate() {
        return inspGpStartDate;
    }

    public void setInspGpEndDate(String inspGpEndDate) {
        this.inspGpEndDate = inspGpEndDate;
    }

    public String getInspGpEndDate() {
        return inspGpEndDate;
    }

    public void setVioYear(String vioYear) {
        this.vioYear = vioYear;
    }

    public String getVioYear() {
        return vioYear;
    }
}
