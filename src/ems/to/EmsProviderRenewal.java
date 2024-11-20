package ems.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;

public class EmsProviderRenewal  implements Serializable
{ private int renewalId;
  private int eopProviderId;
  private Date tactExpDate;
  private Date ihernExpDate;
  private Date uhfExpDate;
  private Date otherExpDate;
  private String otherDetails;
  private String uhfCellular;
  private String uhf400;
  private String uhf800;
  private String uhfOther;
  private String dispatchCentral;
  private String dispatchProvider;
  private String dispatchOther;
  private String dispatchOtherDetails;
  private String ems247;
  private String ems247Details;
  private String OrgStaffing;
  private String serviceAreaChange;
  private String recordLocation;
  private String waiverList;
  private String emsDataRegistry;
  private String softwareUsed;
  private String trainingDaily;
  private String trainingMonthly;
  private String trainingQuarterly;
  private String trainingOther;
  private String trainingOtherDetails;
  private String trainingHeld;
  private String auditMD;
  private String auditProvCommittee;
  private String auditHospCommittee;
  private String auditSessionMonthly;
  private String auditSessionQuarterly;
  private String auditSessionOther;
  private String auditSessionOtherDetails;
  private String auditRecordsWith;
  private String vehicleCheckDaily;
  private String vehicleCheckMonthly;
  private String vehicleCheckWeekly;
  private String vehicleCheckOther;
  private String vehicleCheckOtherDetails;
  private String equipMaintenance;
  private String vehIntCheckDaily;
  private String vehIntCheckMonthly;
  private String vehIntCheckWeekly;
  private String vehIntCheckOther;
  private String vehIntCheckOtherDetails;
  private String vehicleMaintenance;
  private Date renewalRequestDate;
  public EmsProviderRenewal()
  {
  }
  public Date getIhernExpDate()
  { 
   return ihernExpDate;
  }
public void setIhernExpDate(Date ihernExpDate)
  { 
  this.ihernExpDate = ihernExpDate;
  }
public Date getOtherExpDate()
  { 
   return otherExpDate;
  }
public void setOtherExpDate(Date otherExpDate)
  { 
  this.otherExpDate = otherExpDate;
  }
public Date getTactExpDate()
  { 
   return tactExpDate;
  }
public void setTactExpDate(Date tactExpDate)
  { 
  this.tactExpDate = tactExpDate;
  }
public Date getUhfExpDate()
  { 
   return uhfExpDate;
  }
public void setUhfExpDate(Date uhfExpDate)
  { 
  this.uhfExpDate = uhfExpDate;
  }
public String getOtherDetails()
  { 
   return otherDetails;
  }
public void setOtherDetails(String otherDetails)
  { 
  this.otherDetails = otherDetails;
  }
public String getUhf400()
  { 
   return uhf400;
  }
public void setUhf400(String uhf400)
  { 
  this.uhf400 = uhf400;
  }
public String getUhf800()
  { 
   return uhf800;
  }
public void setUhf800(String uhf800)
  { 
  this.uhf800 = uhf800;
  }
public String getUhfCellular()
  { 
   return uhfCellular;
  }
public void setUhfCellular(String uhfCellular)
  { 
  this.uhfCellular = uhfCellular;
  }
public String getUhfOther()
  { 
   return uhfOther;
  }
public void setUhfOther(String uhfOther)
  { 
  this.uhfOther = uhfOther;
  }
public int getEopProviderId()
  { 
   return eopProviderId;
  }
public void setEopProviderId(int eopProviderId)
  { 
  this.eopProviderId = eopProviderId;
  }
public int getRenewalId()
  { 
   return renewalId;
  }
public void setRenewalId(int renewalId)
  { 
  this.renewalId = renewalId;
  }
  public String getOrgStaffing()
  { 
   return OrgStaffing;
  }
public void setOrgStaffing(String OrgStaffing)
  { 
  this.OrgStaffing = OrgStaffing;
  }
public String getDispatchCentral()
  { 
   return dispatchCentral;
  }
public void setDispatchCentral(String dispatchCentral)
  { 
  this.dispatchCentral = dispatchCentral;
  }
public String getDispatchOther()
  { 
   return dispatchOther;
  }
public void setDispatchOther(String dispatchOther)
  { 
  this.dispatchOther = dispatchOther;
  }
public String getDispatchOtherDetails()
  { 
   return dispatchOtherDetails;
  }
public void setDispatchOtherDetails(String dispatchOtherDetails)
  { 
  this.dispatchOtherDetails = dispatchOtherDetails;
  }
public String getDispatchProvider()
  { 
   return dispatchProvider;
  }
public void setDispatchProvider(String dispatchProvider)
  { 
  this.dispatchProvider = dispatchProvider;
  }
public String getEms247()
  { 
   return ems247;
  }
public void setEms247(String ems247)
  { 
  this.ems247 = ems247;
  }
public String getEms247Details()
  { 
   return ems247Details;
  }
public void setEms247Details(String ems247Details)
  { 
  this.ems247Details = ems247Details;
  }
public String getEmsDataRegistry()
  { 
   return emsDataRegistry;
  }
public void setEmsDataRegistry(String emsDataRegistry)
  { 
  this.emsDataRegistry = emsDataRegistry;
  }
public String getRecordLocation()
  { 
   return recordLocation;
  }
public void setRecordLocation(String recordLocation)
  { 
  this.recordLocation = recordLocation;
  }
public String getServiceAreaChange()
  { 
   return serviceAreaChange;
  }
public void setServiceAreaChange(String serviceAreaChange)
  { 
  this.serviceAreaChange = serviceAreaChange;
  }
public String getWaiverList()
  { 
   return waiverList;
  }
public void setWaiverList(String waiverList)
  { 
  this.waiverList = waiverList;
  }
  public String getAuditHospCommittee()
  { 
   return auditHospCommittee;
  }
public void setAuditHospCommittee(String auditHospCommittee)
  { 
  this.auditHospCommittee = auditHospCommittee;
  }
public String getAuditMD()
  { 
   return auditMD;
  }
public void setAuditMD(String auditMD)
  { 
  this.auditMD = auditMD;
  }
public String getAuditProvCommittee()
  { 
   return auditProvCommittee;
  }
public void setAuditProvCommittee(String auditProvCommittee)
  { 
  this.auditProvCommittee = auditProvCommittee;
  }
public String getSoftwareUsed()
  { 
   return softwareUsed;
  }
public void setSoftwareUsed(String softwareUsed)
  { 
  this.softwareUsed = softwareUsed;
  }
public String getTrainingDaily()
  { 
   return trainingDaily;
  }
public void setTrainingDaily(String trainingDaily)
  { 
  this.trainingDaily = trainingDaily;
  }
public String getTrainingHeld()
  { 
   return trainingHeld;
  }
public void setTrainingHeld(String trainingHeld)
  { 
  this.trainingHeld = trainingHeld;
  }
public String getTrainingMonthly()
  { 
   return trainingMonthly;
  }
public void setTrainingMonthly(String trainingMonthly)
  { 
  this.trainingMonthly = trainingMonthly;
  }
public String getTrainingOther()
  { 
   return trainingOther;
  }
public void setTrainingOther(String trainingOther)
  { 
  this.trainingOther = trainingOther;
  }
public String getTrainingOtherDetails()
  { 
   return trainingOtherDetails;
  }
public void setTrainingOtherDetails(String trainingOtherDetails)
  { 
  this.trainingOtherDetails = trainingOtherDetails;
  }
public String getTrainingQuarterly()
  { 
   return trainingQuarterly;
  }
public void setTrainingQuarterly(String trainingQuarterly)
  { 
  this.trainingQuarterly = trainingQuarterly;
  }
  public String getAuditRecordsWith()
  { 
   return auditRecordsWith;
  }
public void setAuditRecordsWith(String auditRecordsWith)
  { 
  this.auditRecordsWith = auditRecordsWith;
  }
public String getAuditSessionMonthly()
  { 
   return auditSessionMonthly;
  }
public void setAuditSessionMonthly(String auditSessionMonthly)
  { 
  this.auditSessionMonthly = auditSessionMonthly;
  }
public String getAuditSessionOther()
  { 
   return auditSessionOther;
  }
public void setAuditSessionOther(String auditSessionOther)
  { 
  this.auditSessionOther = auditSessionOther;
  }
public String getAuditSessionOtherDetails()
  { 
   return auditSessionOtherDetails;
  }
public void setAuditSessionOtherDetails(String auditSessionOtherDetails)
  { 
  this.auditSessionOtherDetails = auditSessionOtherDetails;
  }
public String getAuditSessionQuarterly()
  { 
   return auditSessionQuarterly;
  }
public void setAuditSessionQuarterly(String auditSessionQuarterly)
  { 
  this.auditSessionQuarterly = auditSessionQuarterly;
  }
public String getVehicleCheckDaily()
  { 
   return vehicleCheckDaily;
  }
public void setVehicleCheckDaily(String vehicleCheckDaily)
  { 
  this.vehicleCheckDaily = vehicleCheckDaily;
  }
public String getVehicleCheckMonthly()
  { 
   return vehicleCheckMonthly;
  }
public void setVehicleCheckMonthly(String vehicleCheckMonthly)
  { 
  this.vehicleCheckMonthly = vehicleCheckMonthly;
  }
public String getVehicleCheckWeekly()
  { 
   return vehicleCheckWeekly;
  }
public void setVehicleCheckWeekly(String vehicleCheckWeekly)
  { 
  this.vehicleCheckWeekly = vehicleCheckWeekly;
  }
  public String getVehicleMaintenance()
  { 
   return vehicleMaintenance;
  }
public void setVehicleMaintenance(String vehicleMaintenance)
  { 
  this.vehicleMaintenance = vehicleMaintenance;
  }
public String getEquipMaintenance()
  { 
   return equipMaintenance;
  }
public void setEquipMaintenance(String equipMaintenance)
  { 
  this.equipMaintenance = equipMaintenance;
  }
public String getVehIntCheckDaily()
  { 
   return vehIntCheckDaily;
  }
public void setVehIntCheckDaily(String vehIntCheckDaily)
  { 
  this.vehIntCheckDaily = vehIntCheckDaily;
  }
public String getVehIntCheckMonthly()
  { 
   return vehIntCheckMonthly;
  }
public void setVehIntCheckMonthly(String vehIntCheckMonthly)
  { 
  this.vehIntCheckMonthly = vehIntCheckMonthly;
  }
public String getVehIntCheckOther()
  { 
   return vehIntCheckOther;
  }
public void setVehIntCheckOther(String vehIntCheckOther)
  { 
  this.vehIntCheckOther = vehIntCheckOther;
  }
public String getVehIntCheckOtherDetails()
  { 
   return vehIntCheckOtherDetails;
  }
public void setVehIntCheckOtherDetails(String vehIntCheckOtherDetails)
  { 
  this.vehIntCheckOtherDetails = vehIntCheckOtherDetails;
  }
public String getVehIntCheckWeekly()
  { 
   return vehIntCheckWeekly;
  }
public void setVehIntCheckWeekly(String vehIntCheckWeekly)
  { 
  this.vehIntCheckWeekly = vehIntCheckWeekly;
  }
public String getVehicleCheckOther()
  { 
   return vehicleCheckOther;
  }
public void setVehicleCheckOther(String vehicleCheckOther)
  { 
  this.vehicleCheckOther = vehicleCheckOther;
  }
public String getVehicleCheckOtherDetails()
  { 
   return vehicleCheckOtherDetails;
  }
public void setVehicleCheckOtherDetails(String vehicleCheckOtherDetails)
  { 
  this.vehicleCheckOtherDetails = vehicleCheckOtherDetails;
  }
  public void setIhernExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
ihernExpDate = formatter.parse(dateParam);
} catch (Exception e)
{ihernExpDate = null; }
}
public String getIhernExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(ihernExpDate == null)
{ return ""; }
else { return formatter.format(ihernExpDate); }
}
public void setOtherExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
otherExpDate = formatter.parse(dateParam);
} catch (Exception e)
{otherExpDate = null; }
}
public String getOtherExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(otherExpDate == null)
{ return ""; }
else { return formatter.format(otherExpDate); }
}
public void setTactExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
tactExpDate = formatter.parse(dateParam);
} catch (Exception e)
{tactExpDate = null; }
}
public String getTactExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(tactExpDate == null)
{ return ""; }
else { return formatter.format(tactExpDate); }
}
public void setUhfExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
uhfExpDate = formatter.parse(dateParam);
} catch (Exception e)
{uhfExpDate = null; }
}
public String getUhfExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(uhfExpDate == null)
{ return ""; }
else { return formatter.format(uhfExpDate); }
}
public Date getRenewalRequestDate()
  { 
   return renewalRequestDate;
  }
public void setRenewalRequestDate(Date renewalRequestDate)
  { 
  this.renewalRequestDate = renewalRequestDate;
  }
public void setRenewalRequestDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
renewalRequestDate = formatter.parse(dateParam);
} catch (Exception e)
{renewalRequestDate = null; }
}
public String getRenewalRequestDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(renewalRequestDate == null)
{ return ""; }
else { return formatter.format(renewalRequestDate); }
}
}