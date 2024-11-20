package ems.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;


public class EmsHospital implements Serializable
{ private int hospitalId;
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
  private Date reapplyDate;
  private Date iniCertDate;
  private Date expDate;
  private Date appRecivedDate;
  private Date updateDate;
  private Date visitDate;
  private Date approvedDate;
  private Date auditDate;
  private Date reviewedDate;
  private String comments;
  private Date certRequestedDate;
  private List fileList;
  private Date recCreatedDate;
  private String recCreatedBy;
  public EmsHospital()
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
 public Date getAppRecivedDate()
  { 
   return appRecivedDate;
  }
public void setAppRecivedDate(Date appRecivedDate)
  { 
  this.appRecivedDate =
appRecivedDate;
  }
public Date getApprovedDate()
  { 
   return approvedDate;
  }
public void setApprovedDate(Date approvedDate)
  { 
  this.approvedDate =
approvedDate;
  }
public Date getAuditDate()
  { 
   return auditDate;
  }
public void setAuditDate(Date auditDate)
  { 
  this.auditDate = auditDate;
  }
public Date getCertRequestedDate()
  { 
   return certRequestedDate;
  }
public void setCertRequestedDate(Date certRequestedDate)
  { 

this.certRequestedDate = certRequestedDate;
  }
public Date getExpDate()
  { 
   return expDate;
  }
public void setExpDate(Date expDate)
  { 
  this.expDate = expDate;
  }
public Date getIniCertDate()
  { 
   return iniCertDate;
  }
public void setIniCertDate(Date iniCertDate)
  { 
  this.iniCertDate =
iniCertDate;
  }
public Date getReapplyDate()
  { 
   return reapplyDate;
  }
public void setReapplyDate(Date reapplyDate)
  { 
  this.reapplyDate =
reapplyDate;
  }
public Date getReviewedDate()
  { 
   return reviewedDate;
  }
public void setReviewedDate(Date reviewedDate)
  { 
  this.reviewedDate =
reviewedDate;
  }
public Date getUpdateDate()
  { 
   return updateDate;
  }
public void setUpdateDate(Date updateDate)
  { 
  this.updateDate = updateDate;

}
public Date getVisitDate()
  { 
   return visitDate;
  }
public void setVisitDate(Date visitDate)
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

  public void setAppRecivedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
appRecivedDate = formatter.parse(dateParam);
} catch (Exception e)
{
appRecivedDate = null;
}
}
public String getAppRecivedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(appRecivedDate == null)
{
return "";
}
else
{
return formatter.format(appRecivedDate);
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
public void setIniCertDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
iniCertDate = formatter.parse(dateParam);
} catch (Exception e)
{
iniCertDate = null;
}
}
public String getIniCertDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(iniCertDate == null)
{
return "";
}
else
{
return formatter.format(iniCertDate);
}
}
public void setReapplyDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
reapplyDate = formatter.parse(dateParam);
} catch (Exception e)
{
reapplyDate = null;
}
}
public String getReapplyDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(reapplyDate == null)
{
return "";
}
else
{
return formatter.format(reapplyDate);
}
}
public void setUpdateDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
updateDate = formatter.parse(dateParam);
} catch (Exception e)
{
updateDate = null;
}
}
public String getUpdateDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(updateDate == null)
{
return "";
}
else
{
return formatter.format(updateDate);
}
}
public void setApprovedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
approvedDate = formatter.parse(dateParam);
} catch (Exception e)
{
approvedDate = null;
}
}
public String getApprovedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(approvedDate == null)
{
return "";
}
else
{
return formatter.format(approvedDate);
}
}
public void setAuditDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
auditDate = formatter.parse(dateParam);
} catch (Exception e)
{
auditDate = null;
}
}
public String getAuditDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(auditDate == null)
{
return "";
}
else
{
return formatter.format(auditDate);
}
}
public void setCertRequestedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
certRequestedDate = formatter.parse(dateParam);
} catch (Exception e)
{
certRequestedDate = null;
}
}
public String getCertRequestedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(certRequestedDate == null)
{
return "";
}
else
{
return formatter.format(certRequestedDate);
}
}
public void setReviewedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
reviewedDate = formatter.parse(dateParam);
} catch (Exception e)
{
reviewedDate = null;
}
}
public String getReviewedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(reviewedDate == null)
{
return "";
}
else
{
return formatter.format(reviewedDate);
}
}
public void setVisitDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
visitDate = formatter.parse(dateParam);
} catch (Exception e)
{
visitDate = null;
}
}
public String getVisitDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(visitDate == null)
{
return "";
}
else
{
return formatter.format(visitDate);
}
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
  public String getRecCreatedBy()
  { 
   return recCreatedBy;
  }
public void setRecCreatedBy(String recCreatedBy)
  { 
  this.recCreatedBy = recCreatedBy;
  }
  public Date getRecCreatedDate()
  { 
   return recCreatedDate;
  }
public void setRecCreatedDate(Date recCreatedDate)
  { 
  this.recCreatedDate = recCreatedDate;

}
public void setRecCreatedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
recCreatedDate = formatter.parse(dateParam);
} catch (Exception e)
{
recCreatedDate = null;
}
}
public String getRecCreatedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(recCreatedDate == null)
{
return "";
}
else
{
return formatter.format(recCreatedDate);
}
}
}
