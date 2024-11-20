package elevator.to;
import hs.to.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;
public class elevInspection implements Serializable{
    private int inspectionId;
    private Date inspectionDate;
    private String sprinklers;
    private Date next5YearTest;
    private Date last5YearTest;
    private Date lastAnnualTest;
    private Date nextAnnualTest;
    private String sealNumber;
    private String runToTop;
    private String  runToBottom;
    private String refugeTop;
    private String refugeBottom;
    private String emptyUp;
    private String emptyBottom;
    private String fullUp;
    private String fulldown;
    private String govTripSpeed;
    private String overSpeedSwitch;
    private String slide;
    private String breakTest;
    private Date flexHoseDate;
    private String workingPressure;
    private String reliefPressure;
    private String nearStrkingPoint;
    private String inspectionStatus;
    private String inspectionType;
    private Date fireFightService;
    private String remarks;
    private String receiverReport;
    private String submittor;
    private String telephone;
    private String stateNumber;
    private int inspectorId;
    private String comments;
    private String district;
    private String inspTime;
    private String carMotion;
    private String ascendingCar;
    private int contractorId;
    private int sevContractorId;
  private int serMechanicId;
  private String inspCategory;
  private String typeBuffer;
  private String bufferStroke;
  private String materialRails;
  private String typeGovernor;
  private String safetyDeviceType;
  private String standbyPower;
  private String pit;
  private String machineRoom;
  private String hositway;
  private String shutoffVolve;
  private String cylinderCheck;
    
    public elevInspection() {
    }
    public Date getInspectionDate()
      { 
       return inspectionDate;
      }
    public void setInspectionDate(Date inspectionDate)
      { 
      this.inspectionDate = inspectionDate;
      }
    public Date getLast5YearTest()
      { 
       return last5YearTest;
      }
    public void setLast5YearTest(Date last5YearTest)
      { 
      this.last5YearTest = last5YearTest;
      }
    public Date getLastAnnualTest()
      { 
       return lastAnnualTest;
      }
    public void setLastAnnualTest(Date lastAnnualTest)
      { 
      this.lastAnnualTest = lastAnnualTest;
      }
    public Date getNext5YearTest()
      { 
       return next5YearTest;
      }
    public void setNext5YearTest(Date next5YearTest)
      { 
      this.next5YearTest = next5YearTest;
      }
    public Date getNextAnnualTest()
      { 
       return nextAnnualTest;
      }
    public void setNextAnnualTest(Date nextAnnualTest)
      { 
      this.nextAnnualTest = nextAnnualTest;
      }
    public String getRunToBottom()
      { 
       return  runToBottom;
      }
    public void setRunToBottom(String  runToBottom)
      { 
      this. runToBottom =  runToBottom;
      }
    public String getRefugeBottom()
      { 
       return refugeBottom;
      }
    public void setRefugeBottom(String refugeBottom)
      { 
      this.refugeBottom = refugeBottom;
      }
    public String getRefugeTop()
      { 
       return refugeTop;
      }
    public void setRefugeTop(String refugeTop)
      { 
      this.refugeTop = refugeTop;
      }
    public String getRunToTop()
      { 
       return runToTop;
      }
    public void setRunToTop(String runToTop)
      { 
      this.runToTop = runToTop;
      }
    public String getSealNumber()
      { 
       return sealNumber;
      }
    public void setSealNumber(String sealNumber)
      { 
      this.sealNumber = sealNumber;
      }
    public String getSprinklers()
      { 
       return sprinklers;
      }
    public void setSprinklers(String sprinklers)
      { 
      this.sprinklers = sprinklers;
      }
    public int getInspectionId()
      { 
       return inspectionId;
      }
    public void setInspectionId(int inspectionId)
      { 
      this.inspectionId = inspectionId;
      }
    public Date getFlexHoseDate()
      { 
       return flexHoseDate;
      }
    public void setFlexHoseDate(Date flexHoseDate)
      { 
      this.flexHoseDate = flexHoseDate;
      }
    public String getBreakTest()
      { 
       return breakTest;
      }
    public void setBreakTest(String breakTest)
      { 
      this.breakTest = breakTest;
      }
    public String getEmptyBottom()
      { 
       return emptyBottom;
      }
    public void setEmptyBottom(String emptyBottom)
      { 
      this.emptyBottom = emptyBottom;
      }
    public String getEmptyUp()
      { 
       return emptyUp;
      }
    public void setEmptyUp(String emptyUp)
      { 
      this.emptyUp = emptyUp;
      }
    public String getFullUp()
      { 
       return fullUp;
      }
    public void setFullUp(String fullUp)
      { 
      this.fullUp = fullUp;
      }
    public String getFulldown()
      { 
       return fulldown;
      }
    public void setFulldown(String fulldown)
      { 
      this.fulldown = fulldown;
      }
    public String getGovTripSpeed()
      { 
       return govTripSpeed;
      }
    public void setGovTripSpeed(String govTripSpeed)
      { 
      this.govTripSpeed = govTripSpeed;
      }
    
    public String getOverSpeedSwitch()
      { 
       return overSpeedSwitch;
      }
    public void setOverSpeedSwitch(String overSpeedSwitch)
      { 
      this.overSpeedSwitch = overSpeedSwitch;
      }
       public String getSlide()
      { 
       return slide;
      }
    public void setSlide(String slide)
      { 
      this.slide = slide;
      }
    public String getWorkingPressure()
      { 
       return workingPressure;
      }
    public void setWorkingPressure(String workingPressure)
      { 
      this.workingPressure = workingPressure;
      }
    public String getComments()
      { 
       return comments;
      }
    public void setComments(String comments)
      { 
      this.comments = comments;
      }
    public Date getFireFightService()
      { 
       return fireFightService;
      }
    public void setFireFightService(Date fireFightService)
      { 
      this.fireFightService = fireFightService;
      }
    public String getInspectionStatus()
      { 
       return inspectionStatus;
      }
    public void setInspectionStatus(String inspectionStatus)
      { 
      this.inspectionStatus = inspectionStatus;
      }
    public String getInspectionType()
      { 
       return inspectionType;
      }
    public void setInspectionType(String inspectionType)
      { 
      this.inspectionType = inspectionType;
      }
    public String getNearStrkingPoint()
      { 
       return nearStrkingPoint;
      }
    public void setNearStrkingPoint(String nearStrkingPoint)
      { 
      this.nearStrkingPoint = nearStrkingPoint;
      }
    public String getReceiverReport()
      { 
       return receiverReport;
      }
    public void setReceiverReport(String receiverReport)
      { 
      this.receiverReport = receiverReport;
      }
    public String getReliefPressure()
      { 
       return reliefPressure;
      }
    public void setReliefPressure(String reliefPressure)
      { 
      this.reliefPressure = reliefPressure;
      }
    public String getRemarks()
      { 
       return remarks;
      }
    public void setRemarks(String remarks)
      { 
      this.remarks = remarks;
      }
    public String getStateNumber()
      { 
       return stateNumber;
      }
    public void setStateNumber(String stateNumber)
      { 
      this.stateNumber = stateNumber;
      }
    public String getSubmittor()
      { 
       return submittor;
      }
    public void setSubmittor(String submittor)
      { 
      this.submittor = submittor;
      }
    public String getTelephone()
      { 
       return telephone;
      }
    public void setTelephone(String telephone)
      { 
      this.telephone = telephone;
      }
    public int getInspectorId()
      { 
       return inspectorId;
      }
    public void setInspectorId(int inspectorId)
      { 
      this.inspectorId = inspectorId;
      }
    public String getAscendingCar()
      { 
       return ascendingCar;
      }
    public void setAscendingCar(String ascendingCar)
      { 
      this.ascendingCar = ascendingCar;
      }
    public String getCarMotion()
      { 
       return carMotion;
      }
    public void setCarMotion(String carMotion)
      { 
      this.carMotion = carMotion;
      }
    public String getDistrict()
      { 
       return district;
      }
    public void setDistrict(String district)
      { 
      this.district = district;
      }
    public String getInspTime()
      { 
       return inspTime;
      }
    public void setInspTime(String inspTime)
      { 
      this.inspTime = inspTime;
      }
    public int getContractorId()
      { 
       return contractorId;
      }
    public void setContractorId(int contractorId)
      { 
      this.contractorId = contractorId;
      }
    public void setInspectionDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    inspectionDate = formatter.parse(dateParam);
    } catch (Exception e)
    {inspectionDate = null; }
    }
    public String getInspectionDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(inspectionDate == null)
    { return ""; }
    else { return formatter.format(inspectionDate); }
    }
    public void setLast5YearTestString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    last5YearTest = formatter.parse(dateParam);
    } catch (Exception e)
    {last5YearTest = null; }
    }
    public String getLast5YearTestString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(last5YearTest == null)
    { return ""; }
    else { return formatter.format(last5YearTest); }
    }
    public void setLastAnnualTestString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    lastAnnualTest = formatter.parse(dateParam);
    } catch (Exception e)
    {lastAnnualTest = null; }
    }
    public String getLastAnnualTestString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(lastAnnualTest == null)
    { return ""; }
    else { return formatter.format(lastAnnualTest); }
    }
    public void setNext5YearTestString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    next5YearTest = formatter.parse(dateParam);
    } catch (Exception e)
    {next5YearTest = null; }
    }
    public String getNext5YearTestString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(next5YearTest == null)
    { return ""; }
    else { return formatter.format(next5YearTest); }
    }
    public void setFlexHoseDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    flexHoseDate = formatter.parse(dateParam);
    } catch (Exception e)
    {flexHoseDate = null; }
    }
    public String getFlexHoseDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(flexHoseDate == null)
    { return ""; }
    else { return formatter.format(flexHoseDate); }
    }
    public void setNextAnnualTestString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    nextAnnualTest = formatter.parse(dateParam);
    } catch (Exception e)
    {nextAnnualTest = null; }
    }
    public String getNextAnnualTestString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(nextAnnualTest == null)
    { return ""; }
    else { return formatter.format(nextAnnualTest); }
    }
  public int getSerMechanicId()
    {    return serMechanicId;  }
  public void setSerMechanicId(int serMechanicId)
    {   this.serMechanicId =
  serMechanicId;  }
  public int getSevContractorId()
    {    return sevContractorId;  }
  public void setSevContractorId(int sevContractorId)
    {   this.sevContractorId =
  sevContractorId;  }
  public String getBufferStroke()
    {    return bufferStroke;  }
  public void setBufferStroke(String bufferStroke)
    {   this.bufferStroke =
  bufferStroke;  }
  public String getInspCategory()
    {    return inspCategory;  }
  public void setInspCategory(String inspCategory)
    {   this.inspCategory =
  inspCategory;  }
  public String getMaterialRails()
    {    return materialRails;  }
  public void setMaterialRails(String materialRails)
    {   this.materialRails =
  materialRails;  }
  public String getTypeBuffer()
    {    return typeBuffer;  }
  public void setTypeBuffer(String typeBuffer)
    {   this.typeBuffer = typeBuffer;
  }
  public String getCylinderCheck()
    {    return cylinderCheck;  }
  public void setCylinderCheck(String cylinderCheck)
    {   this.cylinderCheck =
  cylinderCheck;  }
  public String getHositway()
    {    return hositway;  }
  public void setHositway(String hositway)
    {   this.hositway = hositway;  }
  public String getMachineRoom()
    {    return machineRoom;  }
  public void setMachineRoom(String machineRoom)
    {   this.machineRoom =
  machineRoom;  }
  public String getPit()
    {    return pit;  }
  public void setPit(String pit)
    {   this.pit = pit;  }
  public String getSafetyDeviceType()
    {    return safetyDeviceType;  }
  public void setSafetyDeviceType(String safetyDeviceType)
    {
  this.safetyDeviceType = safetyDeviceType;  }
  public String getShutoffVolve()
    {    return shutoffVolve;  }
  public void setShutoffVolve(String shutoffVolve)
    {   this.shutoffVolve =
  shutoffVolve;  }
  public String getStandbyPower()
    {    return standbyPower;  }
  public void setStandbyPower(String standbyPower)
    {   this.standbyPower =
  standbyPower;  }
  public String getTypeGovernor()
    {    return typeGovernor;  }
  public void setTypeGovernor(String typeGovernor)
    {   this.typeGovernor =
  typeGovernor;  }
  public void setFireFightServiceString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  fireFightService = formatter.parse(dateParam);
  } catch (Exception e)
  {fireFightService = null; }
  }
  public String getFireFightServiceString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(fireFightService == null)
  { return ""; }
  else { return formatter.format(fireFightService); }
  }
 
}
