package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;

public class EmsVehicleForm extends ActionForm
{private int vehicleId;
 private String ovNumber;
 private String certNumber;
 private String inspectionDate;
 private String expDate;
 private String fileUpdateDate;
 private String certIssueDate;
 private String certReqDate;
 private String modelYear;
 private String modelMake;
 private String modelVin;
 private String modelType;
 private String colorScheme;
 private String corrLetterDate;
 private String mileage;
 private String plate;
 private String comments;
 private String tempYear;
 private String tempMake;
 private String tempVin;
 private String   tempExpDate;
 private int providerId;
 private String district;
 private String conversionBy;
 private String tempModelType;
 private String vehicleType;
 private String vehicleLocation;
 private String fuelType;
 private String prevCertNumber;
 private String vehOldNew;
  public EmsVehicleForm()
  {
  }
  public int getVehicleId()
  { 
   return vehicleId;
  }
public void setVehicleId(int vehicleId)
  { 
  this.vehicleId = vehicleId;
  }
   public String getVehOldNew()
  { 
   return vehOldNew;
  }
public void setVehOldNew(String vehOldNew)
  { 
  this.vehOldNew = vehOldNew;
  }
   public String getPrevCertNumber()
  { 
   return prevCertNumber;
  }
public void setPrevCertNumber(String prevCertNumber)
  { 
  this.prevCertNumber = prevCertNumber;
  }
   public String getFuelType()
  { 
   return fuelType;
  }
public void setFuelType(String fuelType)
  { 
  this.fuelType = fuelType;
  }
  public String getCertNumber()
  { 
   return certNumber;
  }
public void setCertNumber(String certNumber)
  { 
  this.certNumber = certNumber;
  }

public String getOvNumber()
  { 
   return ovNumber;
  }
public void setOvNumber(String ovNumber)
  { 
  this.ovNumber = ovNumber;
  }
  public String getInspectionDate()
  { 
   return inspectionDate;
  }
public void setInspectionDate (String  inspectionDate)
  { 
  this.inspectionDate = inspectionDate;
  }

public String getExpDate()
  { 
   return expDate;
  }
public void setExpDate (String  expDate)
  { 
  this.expDate = expDate;
  }

public String getFileUpdateDate()
  { 
   return fileUpdateDate;
  }
public void setFileUpdateDate (String  fileUpdateDate)
  { 
  this.fileUpdateDate = fileUpdateDate;
  }

public String getCertIssueDate()
  { 
   return certIssueDate;
  }
public void setCertIssueDate (String  certIssueDate)
  { 
  this.certIssueDate = certIssueDate;
  }

public String getCertReqDate()
  { 
   return certReqDate;
  }
public void setCertReqDate (String  certReqDate)
  { 
  this.certReqDate = certReqDate;
  }
public String getModelYear()
  { 
   return modelYear;
  }
public void setModelYear(String modelYear)
  { 
  this.modelYear = modelYear;
  }

public String getModelMake()
  { 
   return modelMake;
  }
public void setModelMake(String modelMake)
  { 
  this.modelMake = modelMake;
  }

public String getModelVin()
  { 
   return modelVin;
  }
public void setModelVin(String modelVin)
  { 
  this.modelVin = modelVin;
  }

public String getModelType()
  { 
   return modelType;
  }
public void setModelType(String modelType)
  { 
  this.modelType = modelType;
  }

public String getColorScheme()
  { 
   return colorScheme;
  }
public void setColorScheme(String colorScheme)
  { 
  this.colorScheme = colorScheme;
  }

public String getCorrLetterDate()
  { 
   return corrLetterDate;
  }
public void setCorrLetterDate (String  corrLetterDate)
  { 
  this.corrLetterDate = corrLetterDate;
  }

public String getMileage()
  { 
   return mileage;
  }
public void setMileage(String mileage)
  { 
  this.mileage = mileage;
  }

public String getPlate()
  { 
   return plate;
  }
public void setPlate(String plate)
  { 
  this.plate = plate;
  }

public String getComments()
  { 
   return comments;
  }
public void setComments(String comments)
  { 
  this.comments = comments;
  }

public String getTempYear()
  { 
   return tempYear;
  }
public void setTempYear(String tempYear)
  { 
  this.tempYear = tempYear;
  }

public String getTempMake()
  { 
   return tempMake;
  }
public void setTempMake(String tempMake)
  { 
  this.tempMake = tempMake;
  }
  public String getTempVin()
  { 
   return tempVin;
  }
  public void setTempVin(String tempVin)
  { 
  this.tempVin = tempVin;
  }
  public String getTempExpDate()
  { 
   return tempExpDate;
  }
public void setTempExpDate(String tempExpDate)
  { 
  this.tempExpDate = tempExpDate;
  }
 
public String getConversionBy()
  {

   return conversionBy;
  }
public void setConversionBy(String conversionBy)
  { 
  this.conversionBy = conversionBy;
  }
public String getDistrict()
  {

   return district;
  }
public void setDistrict(String district)
  { 
  this.district = district;
  }
public String getTempModelType()
  {

   return tempModelType;
  }
public void setTempModelType(String tempModelType)
  { 
  this.tempModelType = tempModelType;
  }
public int getProviderId()
  {

   return providerId;
  }
public void setProviderId(int providerId)
  { 
  this.providerId = providerId;
  }

public String getVehicleType()
  { 
   return vehicleType;
  }
public void setVehicleType(String vehicleType)
  { 
  this.vehicleType = vehicleType;
  }



  
   public void setProperties(EmsVehicle vehicle) 
  {
  
  this.setVehicleId(vehicle.getVehicleId());
  this.setOvNumber(vehicle.getOvNumber());
  this.setCertNumber(vehicle.getCertNumber());
  this.setInspectionDate(vehicle.getInspectionDateString());
  this.setExpDate(vehicle.getExpDateString());
  this.setFileUpdateDate(vehicle.getFileUpdateDateString());
  this.setCertIssueDate(vehicle.getCertIssueDateString());
  this.setCertReqDate(vehicle.getCertReqDateString());
  this.setFileUpdateDate(vehicle.getFileUpdateDateString());
  this.setModelYear(vehicle.getModelYear());
  this.setModelMake(vehicle.getModelMake());
  this.setModelVin(vehicle.getModelVin());
  this.setModelType(vehicle.getModelType());
  this.setColorScheme(vehicle.getColorScheme());
  this.setCorrLetterDate(vehicle.getCorrLetterDateString());
  this.setMileage(vehicle.getMileage());
  this.setPlate(vehicle.getPlate());
  this.setComments(vehicle.getComments());
  this.setTempYear(vehicle.getTempYear());
  this.setConversionBy(vehicle.getConversionBy());
  this.setDistrict(vehicle.getDistrict());
  this.setProviderId(vehicle.getProviderId());
  this.setTempExpDate(vehicle.getTempExpDateString());
  this.setTempMake(vehicle.getTempMake());
  this.setTempModelType(vehicle.getTempModelType());
  this.setTempVin(vehicle.getTempVin());
  this.setVehicleType(vehicle.getVehicleType());
  this.setVehicleLocation(vehicle.getVehicleLocation());
  this.setFuelType(vehicle.getFuelType());
  this.setPrevCertNumber(vehicle.getPrevCertNumber());
  this.setVehOldNew(vehicle.getVehOldNew());
  }
  public EmsVehicle getEmsVehicle() 
  {
    EmsVehicle vehicle = new EmsVehicle();
vehicle.setCertIssueDateString(getCertIssueDate());
vehicle.setCertNumber(getCertNumber());
vehicle.setCertReqDateString(getCertReqDate());
vehicle.setColorScheme(getColorScheme());
vehicle.setComments(getComments());
vehicle.setConversionBy(getConversionBy());
vehicle.setCorrLetterDateString(getCorrLetterDate());
vehicle.setDistrict(getDistrict());
vehicle.setExpDateString(getExpDate());
vehicle.setFileUpdateDateString(getFileUpdateDate());
vehicle.setInspectionDateString(getInspectionDate());
vehicle.setMileage(getMileage());
vehicle.setModelMake(getModelMake());
vehicle.setModelType(getModelType());
vehicle.setModelVin(getModelVin());
vehicle.setModelYear(getModelYear());
vehicle.setOvNumber(getOvNumber());
vehicle.setPlate(getPlate());
vehicle.setProviderId(getProviderId());
vehicle.setTempExpDateString(getTempExpDate());
vehicle.setTempMake(getTempMake());
vehicle.setTempModelType(getTempModelType());
vehicle.setTempYear(getTempYear());
vehicle.setVehicleId(getVehicleId());
vehicle.setVehicleType(getVehicleType());
vehicle.setVehicleLocation(getVehicleLocation());
vehicle.setFuelType(getFuelType());
vehicle.setPrevCertNumber(getPrevCertNumber());
vehicle.setVehOldNew(getVehOldNew());
  return vehicle;
  }
  
public String getVehicleLocation()
  { 
   return vehicleLocation;
  }
public void setVehicleLocation(String vehicleLocation)
  { 
  this.vehicleLocation = vehicleLocation;
  }  
}