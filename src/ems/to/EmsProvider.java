package ems.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;


public class EmsProvider  implements Serializable
{ private  int providerId;
  private String providerName;
  private String providerAddress1;
  private String providerAddress2;
  private String providerCity;
  private String providerState;
  private String providerZip;
  private String providerZip2;
  private String providerType;
  private String providerCertNumber;
  private String providerDistrict;
  private String providerCounty;
  private String providerEmail;
  private String providerPhone;
  private String providerFax;
  private String providerEmergencyPhone;
  private Date providerAppliedDate;
  private Date providerExpDate;
  private String providerComments;
  private String providerInsCarrier;
  private Date providerInsEffDate;
  private Date providerInsExpDate;
  private String providerInsPolNumber;
  private String providerCertRequired;
  private Date providerApplicationDate;
  private Date providerReviewDate;
  private Date providerApprovedDate;
  private Date providerUpdateDate;
  private Date providerCertDate;
  private Date providerCertReqDate;
  private Date providerReapplyDate;
  private String providerDataCollection;
  private Date providerVisitDate;
  private Date providerAuditDate;
  private Date providerIniCertDate;
  private Date providerIniDfbrDate;
  private Date providerIniMeetingDate;
  private Date providerIniIntDate;
  private Date providerIniAemtDate;
  private Date providerIniParaDate;
  private Date providerTerminationDate;
  private Date providerReapplyExpDate;
  private Date providerTactExpDate;
  private Date providerIhernExpDate;
  private List fileList;
  private Date recCreatedDate;
  private String recCreatedBy;
  public EmsProvider()
  {
  }
  public int getProviderId()
  { 
   return providerId;
  }
public void setProviderId(int providerId)
  { 
  this.providerId = providerId;
  }
  public String getProviderName()
  { 
   return providerName;
  }
public void setProviderName(String providerName)
  { 
  this.providerName = providerName;
  }
  public String getProviderAddress1()
  { 
   return providerAddress1;
  }
public void setProviderAddress1(String providerAddress1)
  { 
  this.providerAddress1 = providerAddress1;
  }
  public String getProviderAddress2()
  { 
   return providerAddress2;
  }
public void setProviderAddress2(String providerAddress2)
  { 
  this.providerAddress2 = providerAddress2;
  }
  public String getProviderCity()
  { 
   return providerCity;
  }
public void setProviderCity(String providerCity)
  { 
  this.providerCity = providerCity;
  }
public String getProviderState()
  { 
   return providerState;
  }
public void setProviderState(String providerState)
  { 
  this.providerState = providerState;
  }
  public String getProviderZip()
  { 
   return providerZip;
  }
public void setProviderZip(String providerZip)
  { 
  this.providerZip = providerZip;
  }
  public String getProviderZip2()
  { 
   return providerZip2;
  }
public void setProviderZip2(String providerZip2)
  { 
  this.providerZip2 = providerZip2;
  }
  public String getProviderType()
  { 
   return providerType;
  }
public void setProviderType(String providerType)
  { 
  this.providerType = providerType;
  }
  public String getProviderCertNumber()
  { 
   return providerCertNumber;
  }
public void setProviderCertNumber(String providerCertNumber)
  { 
  this.providerCertNumber = providerCertNumber;
  }
public String getProviderDistrict()
  { 
   return providerDistrict;
  }
public void setProviderDistrict(String providerDistrict)
  { 
  this.providerDistrict = providerDistrict;
  }
  public String getProviderCounty()
  { 
   return providerCounty;
  }
public void setProviderCounty(String providerCounty)
  { 
  this.providerCounty = providerCounty;
  }

public String getProviderEmail()
  { 
   return providerEmail;
  }
public void setProviderEmail(String providerEmail)
  { 
  this.providerEmail = providerEmail;
  }

public String getProviderPhone()
  { 
   return providerPhone;
  }
public void setProviderPhone(String providerPhone)
  { 
  this.providerPhone = providerPhone;
  }

public String getProviderFax()
  { 
   return providerFax;
  }
public void setProviderFax(String providerFax)
  { 
  this.providerFax = providerFax;
  }

public String getProviderEmergencyPhone()
  { 
   return providerEmergencyPhone;
  }
public void setProviderEmergencyPhone(String providerEmergencyPhone)
  { 
  this.providerEmergencyPhone = providerEmergencyPhone;
  }
  public Date getProviderAppliedDate()
  { 
   return providerAppliedDate;
  }
public void setProviderAppliedDate(Date providerAppliedDate)
  { 
  this.providerAppliedDate = providerAppliedDate;
  }
  public Date getProviderExpDate()
  { 
   return providerExpDate;
  }
public void setProviderExpDate(Date providerExpDate)
  { 
  this.providerExpDate = providerExpDate;
  }
  public String getProviderComments()
  { 
   return providerComments;
  }
public void setProviderComments(String providerComments)
  { 
  this.providerComments = providerComments;
  }
  public String getProviderInsCarrier()
  { 
   return providerInsCarrier;
  }
public void setProviderInsCarrier(String providerInsCarrier)
  { 
  this.providerInsCarrier = providerInsCarrier;
  }
  public Date getProviderInsEffDate()
  { 
   return providerInsEffDate;
  }
public void setProviderInsEffDate(Date providerInsEffDate)
  { 
  this.providerInsEffDate = providerInsEffDate;
  }

public Date getProviderInsExpDate()
  { 
   return providerInsExpDate;
  }
public void setProviderInsExpDate(Date providerInsExpDate)
  { 
  this.providerInsExpDate = providerInsExpDate;
  }
public String getProviderInsPolNumber()
  { 
   return providerInsPolNumber;
  }
public void setProviderInsPolNumber(String providerInsPolNumber)
  { 
  this.providerInsPolNumber = providerInsPolNumber;
  }

public String getProviderCertRequired()
  { 
   return providerCertRequired;
  }
public void setProviderCertRequired(String providerCertRequired)
  { 
  this.providerCertRequired = providerCertRequired;
  }



public String getProviderDataCollection()
  { 
   return providerDataCollection;
  }
public void setProviderDataCollection(String providerDataCollection)
  { 
  this.providerDataCollection = providerDataCollection;
  }
public Date getProviderApplicationDate()
  { 
   return providerApplicationDate;
  }
public void setProviderApplicationDate(Date providerApplicationDate)
  { 
  this.providerApplicationDate = providerApplicationDate;
  }

public Date getProviderReviewDate()
  { 
   return providerReviewDate;
  }
public void setProviderReviewDate(Date providerReviewDate)
  { 
  this.providerReviewDate = providerReviewDate;
  }

public Date getProviderApprovedDate()
  { 
   return providerApprovedDate;
  }
public void setProviderApprovedDate(Date providerApprovedDate)
  { 
  this.providerApprovedDate = providerApprovedDate;
  }

public Date getProviderUpdateDate()
  { 
   return providerUpdateDate;
  }
public void setProviderUpdateDate(Date providerUpdateDate)
  { 
  this.providerUpdateDate = providerUpdateDate;
  }

public Date getProviderCertDate()
  { 
   return providerCertDate;
  }
public void setProviderCertDate(Date providerCertDate)
  { 
  this.providerCertDate = providerCertDate;
  }

public Date getProviderCertReqDate()
  { 
   return providerCertReqDate;
  }
public void setProviderCertReqDate(Date providerCertReqDate)
  { 
  this.providerCertReqDate = providerCertReqDate;
  }

public Date getProviderReapplyDate()
  { 
   return providerReapplyDate;
  }
public void setProviderReapplyDate(Date providerReapplyDate)
  { 
  this.providerReapplyDate = providerReapplyDate;
  }
public Date getProviderVisitDate()
  { 
   return providerVisitDate;
  }
public void setProviderVisitDate(Date providerVisitDate)
  { 
  this.providerVisitDate = providerVisitDate;
  }

public Date getProviderAuditDate()
  { 
   return providerAuditDate;
  }
public void setProviderAuditDate(Date providerAuditDate)
  { 
  this.providerAuditDate = providerAuditDate;
  }

public Date getProviderIniCertDate()
  { 
   return providerIniCertDate;
  }
public void setProviderIniCertDate(Date providerIniCertDate)
  { 
  this.providerIniCertDate = providerIniCertDate;
  }

public Date getProviderIniDfbrDate()
  { 
   return providerIniDfbrDate;
  }
public void setProviderIniDfbrDate(Date providerIniDfbrDate)
  { 
  this.providerIniDfbrDate = providerIniDfbrDate;
  }

public Date getProviderIniMeetingDate()
  { 
   return providerIniMeetingDate;
  }
public void setProviderIniMeetingDate(Date providerIniMeetingDate)
  { 
  this.providerIniMeetingDate = providerIniMeetingDate;
  }

public Date getProviderIniIntDate()
  { 
   return providerIniIntDate;
  }
public void setProviderIniIntDate(Date providerIniIntDate)
  { 
  this.providerIniIntDate = providerIniIntDate;
  }

public Date getProviderIniAemtDate()
  { 
   return providerIniAemtDate;
  }
public void setProviderIniAemtDate(Date providerIniAemtDate)
  { 
  this.providerIniAemtDate = providerIniAemtDate;
  }

public Date getProviderIniParaDate()
  { 
   return providerIniParaDate;
  }
public void setProviderIniParaDate(Date providerIniParaDate)
  { 
  this.providerIniParaDate = providerIniParaDate;
  }

public Date getProviderTerminationDate()
  { 
   return providerTerminationDate;
  }
public void setProviderTerminationDate(Date providerTerminationDate)
  { 
  this.providerTerminationDate = providerTerminationDate;
  }

public Date getProviderReapplyExpDate()
  { 
   return providerReapplyExpDate;
  }
public void setProviderReapplyExpDate(Date providerReapplyExpDate)
  { 
  this.providerReapplyExpDate = providerReapplyExpDate;
  }

public Date getProviderTactExpDate()
  { 
   return providerTactExpDate;
  }
public void setProviderTactExpDate(Date providerTactExpDate)
  { 
  this.providerTactExpDate = providerTactExpDate;
  }

public Date getProviderIhernExpDate()
  { 
   return providerIhernExpDate;
  }
public void setProviderIhernExpDate(Date providerIhernExpDate)
  { 
  this.providerIhernExpDate = providerIhernExpDate;
  }
public void setProviderAppliedDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerAppliedDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerAppliedDate = null;
  }
}
public String getProviderAppliedDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerAppliedDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerAppliedDate);
  }
}
  public void setProviderExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerExpDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerExpDate = null;
  }
}
public String getProviderExpDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerExpDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerExpDate);
  }
}
public void setProviderInsEffDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerInsEffDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerInsEffDate = null;
  }
}
public String getProviderInsEffDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerInsEffDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerInsEffDate);
  }
}
public void setProviderInsExpDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerInsExpDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerInsExpDate = null;
  }
}
public String getProviderInsExpDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerInsExpDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerInsExpDate);
  }
}
public void setProviderApplicationDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerApplicationDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerApplicationDate = null;
  }
}
public String getProviderApplicationDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerApplicationDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerApplicationDate);
  }
}
public void setProviderReviewDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerReviewDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerReviewDate = null;
  }
}
public String getProviderReviewDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerReviewDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerReviewDate);
  }
}
public void setProviderApprovedDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerApprovedDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerApprovedDate = null;
  }
}
public String getProviderApprovedDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerApprovedDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerApprovedDate);
  }
}
public void setProviderUpdateDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerUpdateDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerUpdateDate = null;
  }
}
public String getProviderUpdateDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerUpdateDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerUpdateDate);
  }
}
public void setProviderCertDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerCertDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerCertDate = null;
  }
}
public String getProviderCertDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerCertDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerCertDate);
  }
}
public void setProviderCertReqDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerCertReqDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerCertReqDate = null;
  }
}
public String getProviderCertReqDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerCertReqDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerCertReqDate);
  }
}

public void setProviderReapplyDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerReapplyDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerReapplyDate = null;
  }
}
public String getProviderReapplyDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerReapplyDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerReapplyDate);
  }
}
public void setProviderVisitDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerVisitDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerVisitDate = null;
  }
}
public String getProviderVisitDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if( providerVisitDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format( providerVisitDate);
  }
}

public void setProviderAuditDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerAuditDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerAuditDate = null;
  }
}
public String getProviderAuditDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if( providerAuditDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format( providerAuditDate);
  }
}
public void setProviderIniCertDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIniCertDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIniCertDate = null;
  }
}
public String getProviderIniCertDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIniCertDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIniCertDate);
  }
}

public void setProviderIniDfbrDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIniDfbrDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIniDfbrDate = null;
  }
}
public String getProviderIniDfbrDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIniDfbrDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIniDfbrDate);
  }
}
public void setProviderIniMeetingDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIniMeetingDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIniMeetingDate = null;
  }
}
public String getProviderIniMeetingDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIniMeetingDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIniMeetingDate);
  }
}

public void setProviderIniIntDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIniIntDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIniIntDate = null;
  }
}
public String getProviderIniIntDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIniIntDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIniIntDate);
  }
}
public void setProviderIniAemtDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIniAemtDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIniAemtDate = null;
  }
}
public String getProviderIniAemtDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIniAemtDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIniAemtDate);
  }
}

public void setProviderIniParaDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIniParaDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIniParaDate = null;
  }
}
public String getProviderIniParaDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIniParaDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIniParaDate);
  }
}
public void setProviderTerminationDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerTerminationDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerTerminationDate = null;
  }
}
public String getProviderTerminationDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerTerminationDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerTerminationDate);
  }
}

public void setProviderReapplyExpDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerReapplyExpDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerReapplyExpDate = null;
  }
}
public String getProviderReapplyExpDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerReapplyExpDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerReapplyExpDate);
  }
}
public void setProviderTactExpDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerTactExpDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerTactExpDate = null;
  }
}
public String getProviderTactExpDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerTactExpDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerTactExpDate);
  }
}

public void setProviderIhernExpDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  providerIhernExpDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  providerIhernExpDate = null;
  }
}
public String getProviderIhernExpDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(providerIhernExpDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(providerIhernExpDate);
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