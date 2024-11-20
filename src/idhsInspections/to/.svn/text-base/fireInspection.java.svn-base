package idhsInspections.to;

import fireworks.to.DfbsFireworksPermit;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class fireInspection implements Serializable{
      private int inspId;
      private Date inspDate;
      private Date inspVioPrintDate;
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
      private List violations;
      private String inspectorName;
      private String inspKey;
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
      private String inspConstFinal;
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
      private String inspHoodCurrent;
      private String inspViolationStatus;
      private Date nextInspDate;
      private Date recCreatedDate;
      private List fileList;
      private String nextInspDays;
      private String inspUploadError;
      private String inspConstBackFlow;
    private String inspConstHood;
    private String inspSiteCurrent;
    private String notes;
    private Map violationMap;
     public fireInspection() {
    }
    public int getFileListCount () 
     {
       return (fileList == null) ? 0 : fileList.size();
     }

     public List getFileList()
     {
       if(fileList == null) 
       {
        fileList = new ArrayList();
       } 
       return fileList;
     }

     public void setFileList(List fileList)
     {
       this.fileList = fileList;
     }
    public Date getInspDate()
     { 
      return inspDate;
     }
    public void setInspDate(Date inspDate)
     { 
     this.inspDate = inspDate;
     }
    public Date getInspVioPrintDate()
     { 
      return inspVioPrintDate;
     }
    public void setInspVioPrintDate(Date inspVioPrintDate)
     { 
     this.inspVioPrintDate = inspVioPrintDate;
     }
    public String getNotes()
     { 
      return notes;
     }
    public void setNotes(String notes)
     { 
     this.notes = notes;
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
     public void setInspDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    inspDate = formatter.parse(dateParam);
    } catch (Exception e)
    {inspDate = null; }
    }
    public String getInspDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(inspDate == null)
    { return ""; }
    else { return formatter.format(inspDate); }
    }
    public void setInspVioPrintDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    inspVioPrintDate = formatter.parse(dateParam);
    } catch (Exception e)
    {inspVioPrintDate = null; }
    }
    public String getInspVioPrintDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(inspVioPrintDate == null)
    { return ""; }
    else { return formatter.format(inspVioPrintDate); }
    }

    public String getInspKey() 
     {
       return inspKey;
     }
     public void setInspKey(String inspKey) 
     {
      this.inspKey = inspKey;
     }
      public boolean isNew() 
     {
       return (getInspId() == 0) ? true : false;
     }

     public List getViolations()
     {
       if(violations == null) 
       {
         violations = new ArrayList();
       } 
       return violations;
     }

     public void setViolations(List violations)
     {
       this.violations = violations;
     }
      public String getInspectorName() 
     {
       return inspectorName;
     }
     public void setInspectorName(String inspectorName) 
     {
      this.inspectorName = inspectorName;
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
    public String getInspConstFinal()
      { 
       return inspConstFinal;
      }
    public void setInspConstFinal(String inspConstFinal)
      { 
      this.inspConstFinal = inspConstFinal;
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
    public String getInspConstFireExt()
      { 
       return inspConstFireExt;
      }
    public void setInspConstFireExt(String inspConstFireExt)
      { 
      this.inspConstFireExt = inspConstFireExt;
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
    public String getInspConstEgress()
      { 
       return inspConstEgress;
      }
    public void setInspConstEgress(String inspConstEgress)
      { 
      this.inspConstEgress = inspConstEgress;
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
    public String getInspConstSuppression()
      { 
       return inspConstSuppression;
      }
    public void setInspConstSuppression(String inspConstSuppression)
      { 
      this.inspConstSuppression = inspConstSuppression;
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
    public Date getNextInspDate()
      { 
       return nextInspDate;
      }
    public void setNextInspDate(Date nextInspDate)
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
    public String getInspExtLightCurrent()
      { 
       return inspExtLightCurrent;
      }
    public void setInspExtLightCurrent(String inspExtLightCurrent)
      { 
      this.inspExtLightCurrent = inspExtLightCurrent;
      }
    public String getInspFireExtCurrent()
      { 
       return inspFireExtCurrent;
      }
    public void setInspFireExtCurrent(String inspFireExtCurrent)
      { 
      this.inspFireExtCurrent = inspFireExtCurrent;
      }
    public String getInspHoodCurrent()
      { 
       return inspHoodCurrent;
      }
    public void setInspHoodCurrent(String inspHoodCurrent)
      { 
      this.inspHoodCurrent = inspHoodCurrent;
      }
    public String getInspSensitivityCurrent()
      { 
       return inspSensitivityCurrent;
      }
    public void setInspSensitivityCurrent(String inspSensitivityCurrent)
      { 
      this.inspSensitivityCurrent = inspSensitivityCurrent;
      }
    public String getInspEmpTrainCurrent()
      { 
       return inspEmpTrainCurrent;
      }
    public void setInspEmpTrainCurrent(String inspEmpTrainCurrent)
      { 
      this.inspEmpTrainCurrent = inspEmpTrainCurrent;
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
    public void setNextInspDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    nextInspDate = formatter.parse(dateParam);
    } catch (Exception e)
    {nextInspDate = null; }
    }
    public String getNextInspDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(nextInspDate == null)
    { return ""; }
    else { return formatter.format(nextInspDate); }
    }
    public String getNextInspDays()
     { 
      return nextInspDays;
     }
    public void setNextInspDays(String nextInspDays)
     { 
     this.nextInspDays = nextInspDays;
     }
    
    public void setRecCreatedDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    recCreatedDate = formatter.parse(dateParam);
    } catch (Exception e)
    {recCreatedDate = null; }
    }
    public String getRecCreatedDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(recCreatedDate == null)
    { return ""; }
    else { return formatter.format(recCreatedDate); }
    }
    public Date getRecCreatedDate()
     { 
      return recCreatedDate;
     }
    public void setRecCreatedDate(Date recCreatedDate)
     { 
     this.recCreatedDate = recCreatedDate;
     }
    public String getInspUploadError()
     { 
      return inspUploadError;
     }
    public void setInspUploadError(String inspUploadError)
     { 
     this.inspUploadError = inspUploadError;
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
    public String getInspSiteCurrent()
      { 
       return inspSiteCurrent;
      }
    public void setInspSiteCurrent(String inspSiteCurrent)
      { 
      this.inspSiteCurrent = inspSiteCurrent;
      }
    public Map getViolationMap()
    {
      if(violationMap == null) 
      {
        violationMap = new HashMap();
      }
      return violationMap;
    }

    public void setViolationMap(Map violationMap)
    {
      this.violationMap = violationMap;
    }
    public int getViolationMapCount()
    {
      return (this.violationMap == null) ? 0 : violationMap.size();
    }

    public void addViolation(fireViolation violation) throws Exception
    {
      if(violation == null) return;
        Map map = this.getViolationMap();
        map.put(Integer.toString(violation.getVioId()),violation);
    }
    public fireViolation getViolation(int vioId)
    {
      
      if(vioId == 0 || violationMap == null ) {
         
        return new fireViolation();
      }
      fireViolation violation = (fireViolation) violationMap.get(Integer.toString(vioId));
      return (violation == null) ? new fireViolation() : violation;
    }
    
    public void removeViolation(int vioId) throws Exception
    {
      if(vioId == 0 || violationMap == null ) return;
       violationMap.remove(Integer.toString(vioId));
    }
   
}
