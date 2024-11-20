package ems.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;

public class EmsVehicle  implements Serializable
{private int vehicleId;
 private String ovNumber;
 private String certNumber;
 private Date inspectionDate;
 private Date expDate;
 private Date fileUpdateDate;
 private Date certIssueDate;
 private Date certReqDate;
 private String modelYear;
 private String modelMake;
 private String modelVin;
 private String modelType;
 private String colorScheme;
 private Date corrLetterDate;
 private String mileage;
 private String plate;
 private String comments;
 private String tempYear;
 private String tempMake;
 private String tempVin;
 private Date   tempExpDate;
 private int providerId;
 private String district;
 private String conversionBy;
 private String tempModelType;
 private String vehicleType;
 private List fileList;
 private String vehicleLocation;
 private String fuelType;
 private String prevCertNumber;
 private String vehOldNew;
  public EmsVehicle()
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
  public Date getInspectionDate()
  { 
   return inspectionDate;
  }
public void setInspectionDate(Date inspectionDate)
  { 
  this.inspectionDate = inspectionDate;
  }

public Date getExpDate()
  { 
   return expDate;
  }
public void setExpDate(Date expDate)
  { 
  this.expDate = expDate;
  }

public Date getFileUpdateDate()
  { 
   return fileUpdateDate;
  }
public void setFileUpdateDate(Date fileUpdateDate)
  { 
  this.fileUpdateDate = fileUpdateDate;
  }

public Date getCertIssueDate()
  { 
   return certIssueDate;
  }
public void setCertIssueDate(Date certIssueDate)
  { 
  this.certIssueDate = certIssueDate;
  }

public Date getCertReqDate()
  { 
   return certReqDate;
  }
public void setCertReqDate(Date certReqDate)
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

public Date getCorrLetterDate()
  { 
   return corrLetterDate;
  }
public void setCorrLetterDate(Date corrLetterDate)
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
  

public String getVehicleLocation()
  { 
   return vehicleLocation;
  }
public void setVehicleLocation(String vehicleLocation)
  { 
  this.vehicleLocation = vehicleLocation;
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
public void setInspectionDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
inspectionDate = formatter.parse(dateParam);
} catch (Exception e)
{
inspectionDate = null;
}
}
public String getInspectionDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(inspectionDate == null)
{
return "";
}
else
{
return formatter.format(inspectionDate);
}
}

public void setExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
expDate = formatter.parse(dateParam);
} catch (Exception e)
{
expDate = null;
}
}
public String getExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(expDate == null)
{
return "";
}
else
{
return formatter.format(expDate);
}
}

public void setFileUpdateDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
fileUpdateDate = formatter.parse(dateParam);
} catch (Exception e)
{
fileUpdateDate = null;
}
}
public String getFileUpdateDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(fileUpdateDate == null)
{
return "";
}
else
{
return formatter.format(fileUpdateDate);
}
}

public void setCertIssueDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
certIssueDate = formatter.parse(dateParam);
} catch (Exception e)
{
certIssueDate = null;
}
}
public String getCertIssueDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(certIssueDate == null)
{
return "";
}
else
{
return formatter.format(certIssueDate);
}
}

public void setCertReqDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
certReqDate = formatter.parse(dateParam);
} catch (Exception e)
{
certReqDate = null;
}
}
public String getCertReqDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(certReqDate == null)
{
return "";
}
else
{
return formatter.format(certReqDate);
}
}

public void setCorrLetterDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
corrLetterDate = formatter.parse(dateParam);
} catch (Exception e)
{
corrLetterDate = null;
}
}
public String getCorrLetterDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(corrLetterDate == null)
{
return "";
}
else
{
return formatter.format(corrLetterDate);
}
}

public void setTempExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
tempExpDate = formatter.parse(dateParam);
} catch (Exception e)
{
tempExpDate = null;
}
}
public String getTempExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(tempExpDate == null)
{
return "";
}
else
{
return formatter.format(tempExpDate);
}
}

public Date getTempExpDate()
  {

   return  tempExpDate;
  }
public void setTempExpDate(Date  tempExpDate)
  { 
  this. tempExpDate =  tempExpDate;
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


  public String getTempVin()
  {

   return tempVin;
  }
public void setTempVin(String tempVin)
  { 
  this.tempVin = tempVin;
  }
  public String getVehicleType()
  { 
   return vehicleType;
  }
public void setVehicleType(String vehicleType)
  { 
  this.vehicleType = vehicleType;
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
}