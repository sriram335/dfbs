package amuseRide.control.form;
import hs.to.*;
import amuseRide.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
public class amuseRideForm extends ActionForm{
    
  private String serialNumber;
  private String deviceName;
  private String permitNumber;
  private String manName;
  private int insuranceId;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String zip2;
  private String phone;
  private String capacity;
  private String speed;
  private String otherDocs;
  private String assignInspector;
  private String location;
  private String rideStatus;
  private int deviceId;
  private int numberAccidents;
  private String expDate;
  private String appDate;
  private String ndtDate;
  private String inspDate;
  private String accDate;
    public amuseRideForm() {
       
    }
 
  public String getAddress1()
    {    return address1;  }
  public void setAddress1(String address1)
    {   this.address1 = address1;  }
  public String getAddress2()
    {    return address2;  }
  public void setAddress2(String address2)
    {   this.address2 = address2;  }
  public String getCapacity()
    {    return capacity;  }
  public void setCapacity(String capacity)
    {   this.capacity = capacity;  }
  public String getCity()
    {    return city;  }
  public void setCity(String city)
    {   this.city = city;  }
  public String getDeviceName()
    {    return deviceName;  }
  public void setDeviceName(String deviceName)
    {   this.deviceName = deviceName;
  }
  public String getManName()
    {    return manName;  }
  public void setManName(String manName)
    {   this.manName = manName;  }
  public String getPermitNumber()
    {    return permitNumber;  }
  public void setPermitNumber(String permitNumber)
    {   this.permitNumber =
  permitNumber;  }
  public String getPhone()
    {    return phone;  }
  public void setPhone(String phone)
    {   this.phone = phone;  }
  public String getSerialNumber()
    {    return serialNumber;  }
  public void setSerialNumber(String serialNumber)
    {   this.serialNumber =
  serialNumber;  }
  public String getState()
    {    return state;  }
  public void setState(String state)
    {   this.state = state;  }
  public String getZip()
    {    return zip;  }
  public void setZip(String zip)
    {   this.zip = zip;  }
  public String getZip2()
    {    return zip2;  }
  public void setZip2(String zip2)
    {   this.zip2 = zip2;  }
  public int getInsuranceId()
    {    return insuranceId;  }
  public void setInsuranceId(int insuranceId)
    {   this.insuranceId =
  insuranceId;  }
  public String getAccDate()
    {    return accDate;  }
  public void setAccDate(String accDate)
    {   this.accDate = accDate;  }
  public String getAppDate()
    {    return appDate;  }
  public void setAppDate(String appDate)
    {   this.appDate = appDate;  }
  public String getExpDate()
    {    return expDate;  }
  public void setExpDate(String expDate)
    {   this.expDate = expDate;  }
  public String getInspDate()
    {    return inspDate;  }
  public void setInspDate(String inspDate)
    {   this.inspDate = inspDate;  }
  public String getNdtDate()
    {    return ndtDate;  }
  public void setNdtDate(String ndtDate)
    {   this.ndtDate = ndtDate;  }
  public String getAssignInspector()
    {    return assignInspector;  }
  public void setAssignInspector(String assignInspector)
    {
  this.assignInspector = assignInspector;  }
  public String getLocation()
    {    return location;  }
  public void setLocation(String location)
    {   this.location = location;  }
  public String getOtherDocs()
    {    return otherDocs;  }
  public void setOtherDocs(String otherDocs)
    {   this.otherDocs = otherDocs;  }
  public String getRideStatus()
    {    return rideStatus;  }
  public void setRideStatus(String rideStatus)
    {   this.rideStatus = rideStatus;
  }
  public String getSpeed()
    {    return speed;  }
  public void setSpeed(String speed)
    {   this.speed = speed;  }
  public int getDeviceId()
    {    return deviceId;  }
  public void setDeviceId(int deviceId)
    {   this.deviceId = deviceId;  }
  public int getNumberAccidents()
    {    return numberAccidents;  }
  public void setNumberAccidents(int numberAccidents)
    {   this.numberAccidents =
  numberAccidents;  }
  
  public void setProperties(amuseRide ride) 
   {
     this.setAccDate(ride.getAccDateString());
     this.setAppDate(ride.getAppDateString());
     this.setExpDate(ride.getExpDateString());
     this.setInspDate(ride.getInspDateString());
     this.setNdtDate(ride.getNdtDateString());
     this.setAddress1(ride.getAddress1());
     this.setAddress2(ride.getAddress2());
     this.setAssignInspector(ride.getAssignInspector());
     this.setCapacity(ride.getCapacity());
     this.setCity(ride.getCity());
     this.setDeviceName(ride.getDeviceName());
     this.setLocation(ride.getLocation());
     this.setManName(ride.getManName());
     this.setOtherDocs(ride.getOtherDocs());
     this.setPermitNumber(ride.getPermitNumber());
     this.setPhone(ride.getPhone());
     this.setRideStatus(ride.getRideStatus());
     this.setSerialNumber(ride.getSerialNumber());
     this.setSpeed(ride.getSpeed());
     this.setState(ride.getState());
     this.setZip(ride.getZip());
     this.setZip2(ride.getZip2());
     this.setDeviceId(ride.getDeviceId());
     this.setInsuranceId(ride.getInsuranceId());
     this.setNumberAccidents(ride.getNumberAccidents());
   }
   
    public amuseRide getAmuseRide() 
   {
     amuseRide ride = new amuseRide();
     ride.setAccDateString(this.getAccDate());
     ride.setAppDateString(this.getAppDate());
     ride.setExpDateString(this.getExpDate());
     ride.setInspDateString(this.getInspDate());
     ride.setNdtDateString(this.getNdtDate());
     ride.setAddress1(this.getAddress1());
     ride.setAddress2(this.getAddress2());
     ride.setAssignInspector(this.getAssignInspector());
     ride.setCapacity(this.getCapacity());
     ride.setCity(this.getCity());
     ride.setDeviceName(this.getDeviceName());
     ride.setLocation(this.getLocation());
     ride.setManName(this.getManName());
     ride.setOtherDocs(this.getOtherDocs());
     ride.setPermitNumber(this.getPermitNumber());
     ride.setPhone(this.getPhone());
     ride.setRideStatus(this.getRideStatus());
     ride.setSerialNumber(this.getSerialNumber());
     ride.setSpeed(this.getSpeed());
     ride.setState(this.getState());
     ride.setZip(this.getZip());
     ride.setZip2(this.getZip2());
     ride.setDeviceId(this.getDeviceId());
     ride.setInsuranceId(this.getInsuranceId());
     ride.setNumberAccidents(this.getNumberAccidents());
     return ride;
   }
   
   public void setUpdatedProperties(amuseRide updateRide,amuseRide ride) throws Exception
   { 
   
     ride.setAccDate(updateRide.getAccDate());
     ride.setAppDate(updateRide.getAppDate());
     ride.setExpDate(updateRide.getExpDate());
     ride.setInspDate(updateRide.getInspDate());
     ride.setNdtDate(updateRide.getNdtDate());
     ride.setAddress1(updateRide.getAddress1());
     ride.setAddress2(updateRide.getAddress2());
     ride.setAssignInspector(updateRide.getAssignInspector());
     ride.setCapacity(updateRide.getCapacity());
     ride.setCity(updateRide.getCity());
     ride.setDeviceName(updateRide.getDeviceName());
     ride.setLocation(updateRide.getLocation());
     ride.setManName(updateRide.getManName());
     ride.setOtherDocs(updateRide.getOtherDocs());
     ride.setPermitNumber(updateRide.getPermitNumber());
     ride.setPhone(updateRide.getPhone());
     ride.setRideStatus(updateRide.getRideStatus());
     ride.setSerialNumber(updateRide.getSerialNumber());
     ride.setSpeed(updateRide.getSpeed());
     ride.setState(updateRide.getState());
     ride.setZip(updateRide.getZip());
     ride.setZip2(updateRide.getZip2());
     ride.setDeviceId(updateRide.getDeviceId());
     ride.setInsuranceId(updateRide.getInsuranceId());
     ride.setNumberAccidents(updateRide.getNumberAccidents());
   } 
}
