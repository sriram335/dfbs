package elevator.control.form;
import hs.control.form.*;
import hs.to.*;
import elevator.to.*;
import java.util.Map;
import org.apache.struts.action.*;
import java.util.Date;

import org.apache.struts.upload.FormFile;

public class elevatorForm extends ActionForm{

    private String stateNumber;
    private String deviceCapacity;
    private String contactSpeed;
    private String mailName;
    private String mailAddress1;
    private String mailAddress2;
    private String mailCity;
    private String mailState;
    private String mailZip;
    private String mailZip2;
    private String locUserName;
    private String locAddress1;
    private String locAddress2;
    private String locCity;
    private String locState;
    private String locZip;
    private String locZip2;
    private String locCounty;
    private String locUserPhone;
    private String locUserEmail;
    private String numberOpenings;
    private String totalTravel;
    private String platformSize;
    private String contractNumber;
    private String typeControl;
    private String reasonInactive;
    private String adoptedCode;
    private int contractorId;
    private int deviceId;
    private int ownerId;
    private int accidentNumber;
    private String elevStatus;
    private String accidentDate;
    private String yearInstalled;
    private String permitDate;
    private String occCode;
    private String floors;
    private String elevAppKey;
    private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
    public elevatorForm() {
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
    public String getContactSpeed()
      { 
       return contactSpeed;
      }
    public void setContactSpeed(String contactSpeed)
      { 
      this.contactSpeed = contactSpeed;
      }
    public String getDeviceCapacity()
      { 
       return deviceCapacity;
      }
    public void setDeviceCapacity(String deviceCapacity)
      { 
      this.deviceCapacity = deviceCapacity;
      }
    public String getMailAddress1()
      { 
       return mailAddress1;
      }
    public void setMailAddress1(String mailAddress1)
      { 
      this.mailAddress1 = mailAddress1;
      }
    public String getMailAddress2()
      { 
       return mailAddress2;
      }
    public void setMailAddress2(String mailAddress2)
      { 
      this.mailAddress2 = mailAddress2;
      }
    public String getMailCity()
      { 
       return mailCity;
      }
    public void setMailCity(String mailCity)
      { 
      this.mailCity = mailCity;
      }
    public String getMailName()
      { 
       return mailName;
      }
    public void setMailName(String mailName)
      { 
      this.mailName = mailName;
      }
    public String getMailState()
      { 
       return mailState;
      }
    public void setMailState(String mailState)
      { 
      this.mailState = mailState;
      }
    public String getMailZip()
      { 
       return mailZip;
      }
    public void setMailZip(String mailZip)
      { 
      this.mailZip = mailZip;
      }
    public String getMailZip2()
      { 
       return mailZip2;
      }
    public void setMailZip2(String mailZip2)
      { 
      this.mailZip2 = mailZip2;
      }
    public String getStateNumber()
      { 
       return stateNumber;
      }
    public void setStateNumber(String stateNumber)
      { 
      this.stateNumber = stateNumber;
      }
    public String getLocAddress1()
      { 
       return locAddress1;
      }
    public void setLocAddress1(String locAddress1)
      { 
      this.locAddress1 = locAddress1;
      }
    public String getLocAddress2()
      { 
       return locAddress2;
      }
    public void setLocAddress2(String locAddress2)
      { 
      this.locAddress2 = locAddress2;
      }
    public String getLocCity()
      { 
       return locCity;
      }
    public void setLocCity(String locCity)
      { 
      this.locCity = locCity;
      }
    public String getLocCounty()
      { 
       return locCounty;
      }
    public void setLocCounty(String locCounty)
      { 
      this.locCounty = locCounty;
      }
    public String getLocState()
      { 
       return locState;
      }
    public void setLocState(String locState)
      { 
      this.locState = locState;
      }
    public String getLocUserEmail()
      { 
       return locUserEmail;
      }
    public void setLocUserEmail(String locUserEmail)
      { 
      this.locUserEmail = locUserEmail;
      }
    public String getLocUserName()
      { 
       return locUserName;
      }
    public void setLocUserName(String locUserName)
      { 
      this.locUserName = locUserName;
      }
    public String getLocUserPhone()
      { 
       return locUserPhone;
      }
    public void setLocUserPhone(String locUserPhone)
      { 
      this.locUserPhone = locUserPhone;
      }
    public String getLocZip()
      { 
       return locZip;
      }
    public void setLocZip(String locZip)
      { 
      this.locZip = locZip;
      }
    public String getLocZip2()
      { 
       return locZip2;
      }
    public void setLocZip2(String locZip2)
      { 
      this.locZip2 = locZip2;
      }
    public String getNumberOpenings()
      { 
       return numberOpenings;
      }
    public void setNumberOpenings(String numberOpenings)
      { 
      this.numberOpenings = numberOpenings;
      }
    public String getPlatformSize()
      { 
       return platformSize;
      }
    public void setPlatformSize(String platformSize)
      { 
      this.platformSize = platformSize;
      }
    public String getTotalTravel()
      { 
       return totalTravel;
      }
    public void setTotalTravel(String totalTravel)
      { 
      this.totalTravel = totalTravel;
      }
    public String getAdoptedCode()
      { 
       return adoptedCode;
      }
    public void setAdoptedCode(String adoptedCode)
      { 
      this.adoptedCode = adoptedCode;
      }
    public String getContractNumber()
      { 
       return contractNumber;
      }
    public void setContractNumber(String contractNumber)
      { 
      this.contractNumber = contractNumber;
      }
    public String getElevStatus()
      { 
       return elevStatus;
      }
    public void setElevStatus(String elevStatus)
      { 
      this.elevStatus = elevStatus;
      }
    public String getReasonInactive()
      { 
       return reasonInactive;
      }
    public void setReasonInactive(String reasonInactive)
      { 
      this.reasonInactive = reasonInactive;
      }
    public String getTypeControl()
      { 
       return typeControl;
      }
    public void setTypeControl(String typeControl)
      { 
      this.typeControl = typeControl;
      }
    public int getAccidentNumber()
      { 
       return accidentNumber;
      }
    public void setAccidentNumber(int accidentNumber)
      { 
      this.accidentNumber = accidentNumber;
      }
    public int getContractorId()
      { 
       return contractorId;
      }
    public void setContractorId(int contractorId)
      { 
      this.contractorId = contractorId;
      }
    public int getDeviceId()
      { 
       return deviceId;
      }
    public void setDeviceId(int deviceId)
      { 
      this.deviceId = deviceId;
      }
    public int getOwnerId()
      { 
       return ownerId;
      }
    public void setOwnerId(int ownerId)
      { 
      this.ownerId = ownerId;
      }
    public String getAccidentDate()
      { 
       return accidentDate;
      }
    public void setAccidentDate(String accidentDate)
      { 
      this.accidentDate = accidentDate;
      }
    public String getPermitDate()
      { 
       return permitDate;
      }
    public void setPermitDate(String permitDate)
      { 
      this.permitDate = permitDate;
      }
    public String getYearInstalled()
      { 
       return yearInstalled;
      }
    public void setYearInstalled(String yearInstalled)
      { 
      this.yearInstalled = yearInstalled;
      }
    public String getFloors()
      { 
       return floors;
      }
    public void setFloors(String floors)
      { 
      this.floors = floors;
      }
    public String getOccCode()
      { 
       return occCode;
      }
    public void setOccCode(String occCode)
      { 
      this.occCode = occCode;
      }
    public elevator  getElev()
    {
    elevator elev =new elevator();
        elev.setAccidentDateString(this.getAccidentDate());
        elev.setPermitDateString(this.getPermitDate());
        elev.setYearInstalledString(this.getYearInstalled());
        elev.setAdoptedCode(this.getAdoptedCode());
        elev.setContactSpeed(this.getContactSpeed());
        elev.setContractNumber(this.getContractNumber());
        elev.setDeviceCapacity(this.getDeviceCapacity());
        elev.setElevStatus(this.getElevStatus());
        elev.setFloors(this.getFloors());
        elev.setLocAddress1(this.getLocAddress1());
        elev.setLocAddress2(this.getLocAddress2());
        elev.setLocCity(this.getLocCity());
        elev.setLocCounty(this.getLocCounty());
        elev.setLocState(this.getLocState());
        elev.setLocUserEmail(this.getLocUserEmail());
        elev.setLocUserName(this.getLocUserName());
        elev.setLocUserPhone(this.getLocUserPhone());
        elev.setLocZip(this.getLocZip());
        elev.setLocZip2(this.getLocZip2());
        elev.setMailAddress1(this.getMailAddress1());
        elev.setMailAddress2(this.getMailAddress2());
        elev.setMailCity(this.getMailCity());
        elev.setMailName(this.getMailName());
        elev.setMailState(this.getMailState());
        elev.setMailZip(this.getMailZip());
        elev.setMailZip2(this.getMailZip2());
        elev.setNumberOpenings(this.getNumberOpenings());
        elev.setOccCode(this.getOccCode());
        elev.setPlatformSize(this.getPlatformSize());
        elev.setReasonInactive(this.getReasonInactive());
        elev.setStateNumber(this.getStateNumber());
        elev.setTotalTravel(this.getTotalTravel());
        elev.setTypeControl(this.getTypeControl());
        elev.setAccidentNumber(this.getAccidentNumber());
        elev.setContractorId(this.getContractorId());
        elev.setDeviceId(this.getDeviceId());
        elev.setOwnerId(this.getOwnerId());
    return elev;
    }
    public void setProperties(elevator elev) 
    {
        this.setAccidentDate(elev.getAccidentDateString());
        this.setPermitDate(elev.getPermitDateString());
        this.setYearInstalled(elev.getYearInstalledString());
        this.setAdoptedCode(elev.getAdoptedCode());
        this.setContactSpeed(elev.getContactSpeed());
        this.setContractNumber(elev.getContractNumber());
        this.setDeviceCapacity(elev.getDeviceCapacity());
        this.setElevStatus(elev.getElevStatus());
        this.setFloors(elev.getFloors());
        this.setLocAddress1(elev.getLocAddress1());
        this.setLocAddress2(elev.getLocAddress2());
        this.setLocCity(elev.getLocCity());
        this.setLocCounty(elev.getLocCounty());
        this.setLocState(elev.getLocState());
        this.setLocUserEmail(elev.getLocUserEmail());
        this.setLocUserName(elev.getLocUserName());
        this.setLocUserPhone(elev.getLocUserPhone());
        this.setLocZip(elev.getLocZip());
        this.setLocZip2(elev.getLocZip2());
        this.setMailAddress1(elev.getMailAddress1());
        this.setMailAddress2(elev.getMailAddress2());
        this.setMailCity(elev.getMailCity());
        this.setMailName(elev.getMailName());
        this.setMailState(elev.getMailState());
        this.setMailZip(elev.getMailZip());
        this.setMailZip2(elev.getMailZip2());
        this.setNumberOpenings(elev.getNumberOpenings());
        this.setOccCode(elev.getOccCode());
        this.setPlatformSize(elev.getPlatformSize());
        this.setReasonInactive(elev.getReasonInactive());
        this.setStateNumber(elev.getStateNumber());
        this.setTotalTravel(elev.getTotalTravel());
        this.setTypeControl(elev.getTypeControl());
        this.setAccidentNumber(elev.getAccidentNumber());
        this.setContractorId(elev.getContractorId());
        this.setDeviceId(elev.getDeviceId());
        this.setOwnerId(elev.getOwnerId());
    }
    public void setUpdatedProperties(elevator updatedElevator,elevator elev) throws Exception
     { 
         elev.setAccidentDate(updatedElevator.getAccidentDate());
         elev.setPermitDate(updatedElevator.getPermitDate());
         elev.setYearInstalled(updatedElevator.getYearInstalled());
         elev.setAdoptedCode(updatedElevator.getAdoptedCode());
         elev.setContactSpeed(updatedElevator.getContactSpeed());
         elev.setContractNumber(updatedElevator.getContractNumber());
         elev.setDeviceCapacity(updatedElevator.getDeviceCapacity());
         elev.setElevStatus(updatedElevator.getElevStatus());
         elev.setFloors(updatedElevator.getFloors());
         elev.setLocAddress1(updatedElevator.getLocAddress1());
         elev.setLocAddress2(updatedElevator.getLocAddress2());
         elev.setLocCity(updatedElevator.getLocCity());
         elev.setLocCounty(updatedElevator.getLocCounty());
         elev.setLocState(updatedElevator.getLocState());
         elev.setLocUserEmail(updatedElevator.getLocUserEmail());
         elev.setLocUserName(updatedElevator.getLocUserName());
         elev.setLocUserPhone(updatedElevator.getLocUserPhone());
         elev.setLocZip(updatedElevator.getLocZip());
         elev.setLocZip2(updatedElevator.getLocZip2());
         elev.setMailAddress1(updatedElevator.getMailAddress1());
         elev.setMailAddress2(updatedElevator.getMailAddress2());
         elev.setMailCity(updatedElevator.getMailCity());
         elev.setMailName(updatedElevator.getMailName());
         elev.setMailState(updatedElevator.getMailState());
         elev.setMailZip(updatedElevator.getMailZip());
         elev.setMailZip2(updatedElevator.getMailZip2());
         elev.setNumberOpenings(updatedElevator.getNumberOpenings());
         elev.setOccCode(updatedElevator.getOccCode());
         elev.setPlatformSize(updatedElevator.getPlatformSize());
         elev.setReasonInactive(updatedElevator.getReasonInactive());
         elev.setStateNumber(updatedElevator.getStateNumber());
         elev.setTotalTravel(updatedElevator.getTotalTravel());
         elev.setTypeControl(updatedElevator.getTypeControl());
         elev.setAccidentNumber(updatedElevator.getAccidentNumber());
         elev.setContractorId(updatedElevator.getContractorId());
         elev.setDeviceId(updatedElevator.getDeviceId());
         elev.setOwnerId(updatedElevator.getOwnerId());
     }
 
 
}
