package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
public class EmsProviderForm extends ActionForm
{private  int providerId;
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
  private String providerAppliedDate;
  private String providerExpDate;
  private String providerComments;
  private String providerInsCarrier;
  private String providerInsEffDate;
  private String providerInsExpDate;
  private String providerInsPolNumber;
  private String providerCertRequired;
  private String providerApplicationDate;
  private String providerReviewDate;
  private String providerApprovedDate;
  private String providerUpdateDate;
  private String providerCertDate;
  private String providerCertReqDate;
  private String providerReapplyDate;
  private String providerDataCollection;
  private String providerVisitDate;
  private String providerAuditDate;
  private String providerIniCertDate;
  private String providerIniDfbrDate;
  private String providerIniMeetingDate;
  private String providerIniIntDate;
  private String providerIniAemtDate;
  private String providerIniParaDate;
  private String providerTerminationDate;
  private String providerReapplyExpDate;
  private String providerTactExpDate;
  private String providerIhernExpDate;
   private String providerLevel;
  public EmsProviderForm()
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
  public String getProviderAppliedDate()
  { 
   return providerAppliedDate;
  }
public void setProviderAppliedDate (String  providerAppliedDate)
  { 
  this.providerAppliedDate = providerAppliedDate;
  }
  public String getProviderExpDate()
  { 
   return providerExpDate;
  }
public void setProviderExpDate (String  providerExpDate)
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
  public String getProviderInsEffDate()
  { 
   return providerInsEffDate;
  }
public void setProviderInsEffDate (String  providerInsEffDate)
  { 
  this.providerInsEffDate = providerInsEffDate;
  }

public String getProviderInsExpDate()
  { 
   return providerInsExpDate;
  }
public void setProviderInsExpDate (String  providerInsExpDate)
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
public String getProviderApplicationDate()
  { 
   return providerApplicationDate;
  }
public void setProviderApplicationDate (String  providerApplicationDate)
  { 
  this.providerApplicationDate = providerApplicationDate;
  }

public String getProviderReviewDate()
  { 
   return providerReviewDate;
  }
public void setProviderReviewDate (String  providerReviewDate)
  { 
  this.providerReviewDate = providerReviewDate;
  }

public String getProviderApprovedDate()
  { 
   return providerApprovedDate;
  }
public void setProviderApprovedDate (String  providerApprovedDate)
  { 
  this.providerApprovedDate = providerApprovedDate;
  }

public String getProviderUpdateDate()
  { 
   return providerUpdateDate;
  }
public void setProviderUpdateDate (String  providerUpdateDate)
  { 
  this.providerUpdateDate = providerUpdateDate;
  }

public String getProviderCertDate()
  { 
   return providerCertDate;
  }
public void setProviderCertDate (String  providerCertDate)
  { 
  this.providerCertDate = providerCertDate;
  }

public String getProviderCertReqDate()
  { 
   return providerCertReqDate;
  }
public void setProviderCertReqDate (String  providerCertReqDate)
  { 
  this.providerCertReqDate = providerCertReqDate;
  }

public String getProviderReapplyDate()
  { 
   return providerReapplyDate;
  }
public void setProviderReapplyDate (String  providerReapplyDate)
  { 
  this.providerReapplyDate = providerReapplyDate;
  }
public String getProviderVisitDate()
  { 
   return providerVisitDate;
  }
public void setProviderVisitDate (String  providerVisitDate)
  { 
  this.providerVisitDate = providerVisitDate;
  }

public String getProviderAuditDate()
  { 
   return providerAuditDate;
  }
public void setProviderAuditDate (String  providerAuditDate)
  { 
  this.providerAuditDate = providerAuditDate;
  }

public String getProviderIniCertDate()
  { 
   return providerIniCertDate;
  }
public void setProviderIniCertDate (String  providerIniCertDate)
  { 
  this.providerIniCertDate = providerIniCertDate;
  }

public String getProviderIniDfbrDate()
  { 
   return providerIniDfbrDate;
  }
public void setProviderIniDfbrDate (String  providerIniDfbrDate)
  { 
  this.providerIniDfbrDate = providerIniDfbrDate;
  }

public String getProviderIniMeetingDate()
  { 
   return providerIniMeetingDate;
  }
public void setProviderIniMeetingDate (String  providerIniMeetingDate)
  { 
  this.providerIniMeetingDate = providerIniMeetingDate;
  }

public String getProviderIniIntDate()
  { 
   return providerIniIntDate;
  }
public void setProviderIniIntDate (String  providerIniIntDate)
  { 
  this.providerIniIntDate = providerIniIntDate;
  }

public String getProviderIniAemtDate()
  { 
   return providerIniAemtDate;
  }
public void setProviderIniAemtDate (String  providerIniAemtDate)
  { 
  this.providerIniAemtDate = providerIniAemtDate;
  }

public String getProviderIniParaDate()
  { 
   return providerIniParaDate;
  }
public void setProviderIniParaDate (String  providerIniParaDate)
  { 
  this.providerIniParaDate = providerIniParaDate;
  }

public String getProviderTerminationDate()
  { 
   return providerTerminationDate;
  }
public void setProviderTerminationDate (String  providerTerminationDate)
  { 
  this.providerTerminationDate = providerTerminationDate;
  }

public String getProviderReapplyExpDate()
  { 
   return providerReapplyExpDate;
  }
public void setProviderReapplyExpDate (String  providerReapplyExpDate)
  { 
  this.providerReapplyExpDate = providerReapplyExpDate;
  }

public String getProviderTactExpDate()
  { 
   return providerTactExpDate;
  }
public void setProviderTactExpDate (String  providerTactExpDate)
  { 
  this.providerTactExpDate = providerTactExpDate;
  }

public String getProviderIhernExpDate()
  { 
   return providerIhernExpDate;
  }
public void setProviderIhernExpDate (String  providerIhernExpDate)
  { 
  this.providerIhernExpDate = providerIhernExpDate;
  }
  public String getProviderLevel()
  { 
   return providerLevel;
  }
public void setProviderLevel (String  providerLevel)
  { 
  this.providerLevel = providerLevel;
  }
  public void setProperties(EmsProvider provider) 
  {System.out.println("22");
  
  this.setProviderId(provider.getProviderId());
  this.setProviderName(provider.getProviderName());
  this.setProviderAddress1(provider.getProviderAddress1());
  this.setProviderAddress2(provider.getProviderAddress2());
  this.setProviderCity(provider.getProviderCity());
  this.setProviderState(provider.getProviderState());
  this.setProviderZip(provider.getProviderZip());
  this.setProviderCounty(provider.getProviderCounty());
  this.setProviderZip2(provider.getProviderZip2());
  this.setProviderComments(provider.getProviderComments());
  this.setProviderType(provider.getProviderType());
  this.setProviderCertNumber(provider.getProviderCertNumber());
  this.setProviderDistrict(provider.getProviderDistrict());
  this.setProviderCounty(provider.getProviderCounty());
  this.setProviderEmail(provider.getProviderEmail());
  this.setProviderPhone(provider.getProviderPhone());
  this.setProviderFax(provider.getProviderFax());
  this.setProviderEmergencyPhone(provider.getProviderEmergencyPhone());
  this.setProviderAppliedDate(provider.getProviderAppliedDateString());
  this.setProviderExpDate(provider.getProviderExpDateString());
  this.setProviderComments(provider.getProviderComments());
  this.setProviderInsCarrier(provider.getProviderInsCarrier());
  this.setProviderInsEffDate(provider.getProviderInsEffDateString());
  this.setProviderInsExpDate(provider.getProviderInsExpDateString());
  this.setProviderInsPolNumber(provider.getProviderInsPolNumber());
  this.setProviderCertRequired(provider.getProviderCertRequired());
  this.setProviderApplicationDate(provider.getProviderApplicationDateString());
  this.setProviderReviewDate(provider.getProviderReviewDateString());
  this.setProviderApprovedDate(provider.getProviderApprovedDateString());
  this.setProviderUpdateDate(provider.getProviderUpdateDateString());
  this.setProviderCertDate(provider.getProviderCertDateString());
  this.setProviderCertReqDate(provider.getProviderCertReqDateString());
  this.setProviderReapplyDate(provider.getProviderReapplyDateString());
  this.setProviderDataCollection(provider.getProviderDataCollection());
  this.setProviderVisitDate(provider.getProviderVisitDateString());
  this.setProviderAuditDate(provider.getProviderAuditDateString());
  this.setProviderIniCertDate(provider.getProviderIniCertDateString());
  this.setProviderIniDfbrDate(provider.getProviderIniDfbrDateString());
  this.setProviderIniMeetingDate(provider.getProviderIniMeetingDateString());
  this.setProviderIniIntDate(provider.getProviderIniIntDateString());
  this.setProviderIniAemtDate(provider.getProviderIniAemtDateString());
  this.setProviderIniParaDate(provider.getProviderIniParaDateString());
  this.setProviderTerminationDate(provider.getProviderTerminationDateString());
  this.setProviderReapplyExpDate(provider.getProviderReapplyExpDateString());
  this.setProviderTactExpDate(provider.getProviderTactExpDateString());
  this.setProviderIhernExpDate(provider.getProviderIhernExpDateString());
  
  
  
  System.out.println("33");
  }
  public EmsProvider getEmsProvider() 
  {
    EmsProvider provider = new EmsProvider();
   
  provider.setProviderId(getProviderId());
  provider.setProviderName(getProviderName());
  provider.setProviderAddress1(getProviderAddress1());
  provider.setProviderAddress2(getProviderAddress2());
  provider.setProviderCity(getProviderCity());
  provider.setProviderState(getProviderState());
  provider.setProviderZip(getProviderZip());
  provider.setProviderCounty(getProviderCounty());
  provider.setProviderZip2(getProviderZip2());
  provider.setProviderComments(getProviderComments());
  provider.setProviderType(getProviderType());
  provider.setProviderCertNumber(getProviderCertNumber());
  provider.setProviderDistrict(getProviderDistrict());
  provider.setProviderCounty(getProviderCounty());
  provider.setProviderEmail(getProviderEmail());
  provider.setProviderPhone(getProviderPhone());
  provider.setProviderFax(getProviderFax());
  provider.setProviderEmergencyPhone(getProviderEmergencyPhone());
  provider.setProviderAppliedDateString(getProviderAppliedDate());
  provider.setProviderExpDateString(getProviderExpDate());
  provider.setProviderComments(getProviderComments());
  provider.setProviderInsCarrier(getProviderInsCarrier());
  provider.setProviderInsEffDateString(getProviderInsEffDate());
  provider.setProviderInsExpDateString(getProviderInsExpDate());
  provider.setProviderInsPolNumber(getProviderInsPolNumber());
  provider.setProviderCertRequired(getProviderCertRequired());
  provider.setProviderApplicationDateString(getProviderApplicationDate());
  provider.setProviderReviewDateString(getProviderReviewDate());
  provider.setProviderApprovedDateString(getProviderApprovedDate());
  provider.setProviderUpdateDateString(getProviderUpdateDate());
  provider.setProviderCertDateString(getProviderCertDate());
  provider.setProviderCertReqDateString(getProviderCertReqDate());
  provider.setProviderReapplyDateString(getProviderReapplyDate());
  provider.setProviderDataCollection(getProviderDataCollection());
  provider.setProviderVisitDateString(getProviderVisitDate());
  provider.setProviderAuditDateString(getProviderAuditDate());
  provider.setProviderIniCertDateString(getProviderIniCertDate());
  provider.setProviderIniDfbrDateString(getProviderIniDfbrDate());
  provider.setProviderIniMeetingDateString(getProviderIniMeetingDate());
  provider.setProviderIniIntDateString(getProviderIniIntDate());
  provider.setProviderIniAemtDateString(getProviderIniAemtDate());
  provider.setProviderIniParaDateString(getProviderIniParaDate());
  provider.setProviderTerminationDateString(getProviderTerminationDate());
  provider.setProviderReapplyExpDateString(getProviderReapplyExpDate());
  provider.setProviderTactExpDateString(getProviderTactExpDate());
  provider.setProviderIhernExpDateString(getProviderIhernExpDate());
    return provider;
  }

}