package ems.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;


public class EmsInstitution implements Serializable
{ private int institutionId;
  private String institutionName;
  private String institutionAddress1;
  private String institutionAddress2;
  private String institutionCity;
  private String institutionState;
  private String institutionZip;
  private String institutionZip2;
  private String institutionCounty;
  private String institutionFax;
  private String institutionPhone;
  private String institutionEmail;
  private String institutionCertNumber;
  private Date institutionCertDate;
  private Date institutionExpDate;
  private String institutionType;
  private Date institutionReceivedDate;
  private Date institutionReviewedDate;
  private Date institutionCertReQDate;
  private List fileList;
  private Date recCreatedDate;
  private String recCreatedBy;
  private int instPersonEmail;
  
  public EmsInstitution()
  {
  }
  public int getInstPersonEmail()
  { 
   return instPersonEmail;
  }
public void setInstPersonEmail(int instPersonEmail)
  { 
  this.instPersonEmail = instPersonEmail;
  }
  public Date getInstitutionCertDate()
  { 
   return institutionCertDate;
  }
public void setInstitutionCertDate(Date institutionCertDate)
  { 
  this.institutionCertDate = institutionCertDate;
  }
public Date getInstitutionCertReQDate()
  { 
   return institutionCertReQDate;
  }
public void setInstitutionCertReQDate(Date institutionCertReQDate)
  { 
  this.institutionCertReQDate = institutionCertReQDate;
  }
public Date getInstitutionExpDate()
  { 
   return institutionExpDate;
  }
public void setInstitutionExpDate(Date institutionExpDate)
  { 
  this.institutionExpDate = institutionExpDate;
  }
public Date getInstitutionReceivedDate()
  { 
   return institutionReceivedDate;
  }
public void setInstitutionReceivedDate(Date institutionReceivedDate)
  { 
  this.institutionReceivedDate = institutionReceivedDate;
  }
public Date getInstitutionReviewedDate()
  { 
   return institutionReviewedDate;
  }
public void setInstitutionReviewedDate(Date institutionReviewedDate)
  { 
  this.institutionReviewedDate = institutionReviewedDate;
  }
public String getInstitutionType()
  { 
   return institutionType;
  }
public void setInstitutionType(String institutionType)
  { 
  this.institutionType = institutionType;
  }
public String getInstitutionAddress1()
  { 
   return institutionAddress1;
  }
public void setInstitutionAddress1(String institutionAddress1)
  { 
  this.institutionAddress1 = institutionAddress1;
  }
public String getInstitutionAddress2()
  { 
   return institutionAddress2;
  }
public void setInstitutionAddress2(String institutionAddress2)
  { 
  this.institutionAddress2 = institutionAddress2;
  }
public String getInstitutionCertNumber()
  { 
   return institutionCertNumber;
  }
public void setInstitutionCertNumber(String institutionCertNumber)
  { 
  this.institutionCertNumber = institutionCertNumber;
  }
public String getInstitutionCity()
  { 
   return institutionCity;
  }
public void setInstitutionCity(String institutionCity)
  { 
  this.institutionCity = institutionCity;
  }
public String getInstitutionCounty()
  { 
   return institutionCounty;
  }
public void setInstitutionCounty(String institutionCounty)
  { 
  this.institutionCounty = institutionCounty;
  }
public String getInstitutionEmail()
  { 
   return institutionEmail;
  }
public void setInstitutionEmail(String institutionEmail)
  { 
  this.institutionEmail = institutionEmail;
  }
public String getInstitutionFax()
  { 
   return institutionFax;
  }
public void setInstitutionFax(String institutionFax)
  { 
  this.institutionFax = institutionFax;
  }
public String getInstitutionName()
  { 
   return institutionName;
  }
public void setInstitutionName(String institutionName)
  { 
  this.institutionName = institutionName;
  }
public String getInstitutionPhone()
  { 
   return institutionPhone;
  }
public void setInstitutionPhone(String institutionPhone)
  { 
  this.institutionPhone = institutionPhone;
  }
public String getInstitutionState()
  { 
   return institutionState;
  }
public void setInstitutionState(String institutionState)
  { 
  this.institutionState = institutionState;
  }
public String getInstitutionZip()
  { 
   return institutionZip;
  }
public void setInstitutionZip(String institutionZip)
  { 
  this.institutionZip = institutionZip;
  }
public String getInstitutionZip2()
  { 
   return institutionZip2;
  }
public void setInstitutionZip2(String institutionZip2)
  { 
  this.institutionZip2 = institutionZip2;
  }
public int getInstitutionId()
  { 
   return institutionId;
  }
public void setInstitutionId(int institutionId)
  { 
  this.institutionId = institutionId;
  }
  public void setInstitutionCertDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
institutionCertDate = formatter.parse(dateParam);
} catch (Exception e)
{
institutionCertDate = null;
}
}
public String getInstitutionCertDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(institutionCertDate == null)
{
return "";
}
else
{
return formatter.format(institutionCertDate);
}
}
public void setInstitutionExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
institutionExpDate = formatter.parse(dateParam);
} catch (Exception e)
{
institutionExpDate = null;
}
}
public String getInstitutionExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(institutionExpDate == null)
{
return "";
}
else
{
return formatter.format(institutionExpDate);
}
}


public void setInstitutionCertReQDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
institutionCertReQDate = formatter.parse(dateParam);
} catch (Exception e)
{
institutionCertReQDate = null;
}
}
public String getInstitutionCertReQDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(institutionCertReQDate == null)
{
return "";
}
else
{
return formatter.format(institutionCertReQDate);
}
}
public void setInstitutionReceivedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
institutionReceivedDate = formatter.parse(dateParam);
} catch (Exception e)
{
institutionReceivedDate = null;
}
}
public String getInstitutionReceivedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(institutionReceivedDate == null)
{
return "";
}
else
{
return formatter.format(institutionReceivedDate);
}
}
public void setInstitutionReviewedDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
institutionReviewedDate = formatter.parse(dateParam);
} catch (Exception e)
{
institutionReviewedDate = null;
}
}
public String getInstitutionReviewedDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(institutionReviewedDate == null)
{
return "";
}
else
{
return formatter.format(institutionReviewedDate);
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