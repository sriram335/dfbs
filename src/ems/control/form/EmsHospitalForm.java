package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;

public class EmsHospitalForm  extends ActionForm
{private int hospitalId;
  private String hospitalName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
  private String zip2;
  private String county;
  private String distirict;
  private String certNumber;
  private String reapplyDate;
  private String iniCertDate;
  private String expDate;
  private String appRecivedDate;
  private String updateDate;
  private String visitDate;
  private String approvedDate;
  private String auditDate;
  private String reviewedDate;
  private String comments;
  private String certRequestedDate;
  public EmsHospitalForm()
  {
  }
  public String getAddress1()
  { 
   return address1;
  }
public void setAddress1(String address1)
  { 
  this.address1 = address1;
  }
public String getAddress2()
  { 
   return address2;
  }
public void setAddress2(String address2)
  { 
  this.address2 = address2;
  }
public String getCertNumber()
  { 
   return certNumber;
  }
public void setCertNumber(String certNumber)
  { 
  this.certNumber =
certNumber;
  }
public String getCity()
  { 
   return city;
  }
public void setCity(String city)
  { 
  this.city = city;
  }
public String getCounty()
  { 
   return county;
  }
public void setCounty(String county)
  { 
  this.county = county;
  }
public String getDistirict()
  { 
   return distirict;
  }
public void setDistirict(String distirict)
  { 
  this.distirict = distirict;

}
public String getHospitalName()
  { 
   return hospitalName;
  }
public void setHospitalName(String hospitalName)
  { 
  this.hospitalName =
hospitalName;
  }
public String getState()
  { 
   return state;
  }
public void setState(String state)
  { 
  this.state = state;
  }
public String getZip()
  { 
   return zip;
  }
public void setZip(String zip)
  { 
  this.zip = zip;
  }
public String getZip2()
  { 
   return zip2;
  }
public void setZip2(String zip2)
  { 
  this.zip2 = zip2;
  }
public int getHospitalId()
  { 
   return hospitalId;
  }
public void setHospitalId(int hospitalId)
  { 
  this.hospitalId = hospitalId;
 }
 public String getAppRecivedDate()
  { 
   return appRecivedDate;
  }
public void setAppRecivedDate(String  appRecivedDate)
  { 
  this.appRecivedDate =
appRecivedDate;
  }
public String getApprovedDate()
  { 
   return approvedDate;
  }
public void setApprovedDate(String  approvedDate)
  { 
  this.approvedDate =
approvedDate;
  }
public String getAuditDate()
  { 
   return auditDate;
  }
public void setAuditDate(String  auditDate)
  { 
  this.auditDate = auditDate;
  }
public String getCertRequestedDate()
  { 
   return certRequestedDate;
  }
public void setCertRequestedDate(String  certRequestedDate)
  { 

this.certRequestedDate = certRequestedDate;
  }
public String getExpDate()
  { 
   return expDate;
  }
public void setExpDate(String  expDate)
  { 
  this.expDate = expDate;
  }
public String getIniCertDate()
  { 
   return iniCertDate;
  }
public void setIniCertDate(String  iniCertDate)
  { 
  this.iniCertDate =
iniCertDate;
  }
public String getReapplyDate()
  { 
   return reapplyDate;
  }
public void setReapplyDate(String  reapplyDate)
  { 
  this.reapplyDate =
reapplyDate;
  }
public String getReviewedDate()
  { 
   return reviewedDate;
  }
public void setReviewedDate(String  reviewedDate)
  { 
  this.reviewedDate =
reviewedDate;
  }
public String getUpdateDate()
  { 
   return updateDate;
  }
public void setUpdateDate(String  updateDate)
  { 
  this.updateDate = updateDate;

}
public String getVisitDate()
  { 
   return visitDate;
  }
public void setVisitDate(String  visitDate)
  { 
  this.visitDate = visitDate;
  }
public String getComments()
  { 
   return comments;
  }
public void setComments(String comments)
  { 
  this.comments = comments;
  }

   public EmsHospital getEmsHospital() 
  {
    EmsHospital hospital = new EmsHospital();
   
    hospital.setAddress1(getAddress1());
    hospital.setAddress2(getAddress2());
    hospital.setAppRecivedDateString(getAppRecivedDate());
    hospital.setApprovedDateString(getApprovedDate());
    hospital.setAuditDateString(getAuditDate());
    hospital.setCertNumber(getCertNumber());
    hospital.setCertRequestedDateString(getCertRequestedDate());
    hospital.setCity(getCity());
    hospital.setComments(getComments());
    hospital.setCounty(getCounty());
    hospital.setDistirict(getDistirict());
    hospital.setExpDateString(getExpDate());
    hospital.setHospitalId(getHospitalId());
    hospital.setHospitalName(getHospitalName());
    hospital.setIniCertDateString(getIniCertDate());
    hospital.setReapplyDateString(getReapplyDate());
    hospital.setReviewedDateString(getReviewedDate());
    hospital.setState(getState());
    hospital.setUpdateDateString(getUpdateDate());
    hospital.setVisitDateString(getVisitDate());
    hospital.setZip(getZip());
    hospital.setZip2(getZip2());
    return hospital;
  }
  public void setProperties(EmsHospital hospital) 
  {
    this.setAddress1(hospital.getAddress1());
    this.setAddress2(hospital.getAddress2());
    this.setAppRecivedDate(hospital.getAppRecivedDateString());
    this.setApprovedDate(hospital.getApprovedDateString());
    this.setAuditDate(hospital.getAuditDateString());
    this.setCertNumber(hospital.getCertNumber());
    this.setCertRequestedDate(hospital.getCertRequestedDateString());
    this.setCity(hospital.getCity());
    this.setComments(hospital.getComments());
    this.setCounty(hospital.getCounty());
    this.setDistirict(hospital.getDistirict());
    this.setExpDate(hospital.getExpDateString());
    this.setHospitalId(hospital.getHospitalId());
    this.setHospitalName(hospital.getHospitalName());
    this.setIniCertDate(hospital.getIniCertDateString());
    this.setReapplyDate(hospital.getReapplyDateString());
    this.setReviewedDate(hospital.getReviewedDateString());
    this.setState(hospital.getState());
    this.setUpdateDate(hospital.getUpdateDateString());
    this.setVisitDate(hospital.getVisitDateString());
    this.setZip(hospital.getZip());
    this.setZip2(hospital.getZip2());
  }
}