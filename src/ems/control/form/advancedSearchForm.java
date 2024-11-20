package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;

import main.control.form.ContactForm;

import org.apache.struts.action.*;

public class advancedSearchForm  extends ContactForm
{ private String county1;
  private String county2;
  private String county3;
  private String county4;
  private String zip1;
  private String zip2;
  private String zip3;
  private String zip4;
  private String startDate;
  private String endDate;
  private String provLevel;
  private String certType;
  // to delete
   private String incidentName;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String zip;
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
  public advancedSearchForm()
  {
  }
  public String getCertType()
  { 
   return certType;
  }
public void setCertType(String certType)
  { 
  this.certType = certType;
  }
public String getCounty1()
  { 
   return county1;
  }
public void setCounty1(String county1)
  { 
  this.county1 = county1;
  }
public String getCounty2()
  { 
   return county2;
  }
public void setCounty2(String county2)
  { 
  this.county2 = county2;
  }
public String getCounty3()
  { 
   return county3;
  }
public void setCounty3(String county3)
  { 
  this.county3 = county3;
  }
public String getCounty4()
  { 
   return county4;
  }
public void setCounty4(String county4)
  { 
  this.county4 = county4;
  }
public String getEndDate()
  { 
   return endDate;
  }
public void setEndDate(String endDate)
  { 
  this.endDate = endDate;
  }
public String getProvLevel()
  { 
   return provLevel;
  }
public void setProvLevel(String provLevel)
  { 
  this.provLevel = provLevel;
  }
public String getStartDate()
  { 
   return startDate;
  }
public void setStartDate(String startDate)
  { 
  this.startDate = startDate;
  }
public String getZip1()
  { 
   return zip1;
  }
public void setZip1(String zip1)
  { 
  this.zip1 = zip1;
  }
public String getZip2()
  { 
   return zip2;
  }
public void setZip2(String zip2)
  { 
  this.zip2 = zip2;
  }
public String getZip3()
  { 
   return zip3;
  }
public void setZip3(String zip3)
  { 
  this.zip3 = zip3;
  }
public String getZip4()
  { 
   return zip4;
  }
public void setZip4(String zip4)
  { 
  this.zip4 = zip4;
  }
  // to delete
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
public String getIncidentName()
  { 
   return incidentName;
  }
public void setIncidentName(String incidentName)
  { 
  this.incidentName =
incidentName;
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

}