package ust.control.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import ust.to.*;

public class ustApplicantForm extends ActionForm{
  private int ustId;
    private String personLastName;
    private String personFirstName;
    private String personMi;
    private String personAddress1;
    private String personAddress2;
    private String personCity;
    private String personState;
    private String personZip;
    private String personZip2;
    private String personEmail;
    private String personFax;
    private String personPhoneOffice;
    private String personPhoneHome;
    private String addressFlag;  
    private String renewStatus;
    private String companyName;
    private String comments;
  
 
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
    public ustApplicantForm() {
        
    }
  public String getComments()
  {
  return comments;
  }
  public void setComments(String comments)
  {
  this.comments = comments;
  }
  public String getPersonPhoneOffice()
  {
  return personPhoneOffice;
  }
  public void setPersonPhoneOffice(String personPhoneOffice)
  {
  this.personPhoneOffice = personPhoneOffice;
  }
    public String getPersonPhoneHome()
    {
    return personPhoneHome;
    }
    public void setPersonPhoneHome(String personPhoneHome)
    {
    this.personPhoneHome = personPhoneHome;
    }
  
  public String getAddressFlag()
  {
  return addressFlag;
  }
  public void setAddressFlag(String addressFlag)
  {
  this.addressFlag = addressFlag;
  }
  public FormFile getCaseFile()
   { 
    return caseFile;
   }
  public void setCaseFile( FormFile caseFile)
   { 
   this.caseFile = caseFile;
   } 
    public String getXlName()
   { 
    return xlName;
   }
  public void setXlName(String xlName)
   { 
   this.xlName = xlName;
   }
  public String getCompanyName()
  {
  return companyName;
  }
  public void setCompanyName(String companyName)
  {
  this.companyName = companyName;
  }
  public String getRenewStatus()
  {
  return renewStatus;
  }
  public void setRenewStatus(String renewStatus)
  {
  this.renewStatus = renewStatus;
  }
  public int getUstId()
  {
  return ustId;
  }
  public void setUstId(int ustId)
  {
  this.ustId = ustId;
  }
  public String getPersonLastName()
  {
  return personLastName;
  }
  public void setPersonLastName(String personLastName)
  {
  this.personLastName = personLastName;
  }

  public String getPersonFirstName()
  {
  return personFirstName;
  }
  public void setPersonFirstName(String personFirstName)
  {
  this.personFirstName = personFirstName;
  }

  public String getPersonMi()
  {
  return personMi;
  }
  public void setPersonMi(String personMi)
  {
  this.personMi = personMi;
  }

  public String getPersonAddress1()
  {
  return personAddress1;
  }
  public void setPersonAddress1(String personAddress1)
  {
  this.personAddress1 = personAddress1;
  }

  public String getPersonAddress2()
  {
  return personAddress2;
  }
  public void setPersonAddress2(String personAddress2)
  {
  this.personAddress2 = personAddress2;
  }

  public String getPersonCity()
  {
  return personCity;
  }
  public void setPersonCity(String personCity)
  {
  this.personCity = personCity;
  }

  public String getPersonState()
  {
  return personState;
  }
  public void setPersonState(String personState)
  {
  this.personState = personState;
  }

  public String getPersonZip()
  {
  return personZip;
  }
  public void setPersonZip(String personZip)
  {
  this.personZip = personZip;
  }

  public String getPersonZip2()
  {
  return personZip2;
  }
  public void setPersonZip2(String personZip2)
  {
  this.personZip2 = personZip2;
  }

  public String getPersonEmail()
  {
  return personEmail;
  }
  public void setPersonEmail(String personEmail)
  {
  this.personEmail = personEmail;
  }

  public String getPersonFax()
  {
  return personFax;
  }
  public void setPersonFax(String personFax)
  {
  this.personFax = personFax;
  }
  public ustApplicant getUstApplicant() throws Exception
   {
     
     ustApplicant ust = new ustApplicant ();
     ust.setCompanyName(this.getCompanyName());
     ust.setPersonAddress1(this.getPersonAddress1());
     ust.setPersonAddress2(this.getPersonAddress2());
     ust.setPersonCity(this.getPersonCity());
     ust.setPersonEmail(this.getPersonEmail());
     ust.setPersonFax(this.getPersonFax());
     ust.setPersonFirstName(this.getPersonFirstName());
     ust.setPersonLastName(this.getPersonLastName());
     ust.setPersonMi(this.getPersonMi());
     ust.setPersonPhoneHome(this.getPersonPhoneHome());
     ust.setPersonPhoneOffice(this.getPersonPhoneOffice());
     ust.setPersonState(this.getPersonState());
     ust.setAddressFlag(this.getAddressFlag());
     ust.setPersonZip(this.getPersonZip());
     ust.setPersonZip2(this.getPersonZip2());
     ust.setRenewStatus(this.getRenewStatus());
     ust.setComments(this.getComments());
     ust.setUstId(this.getUstId());
  return ust;
   }
    public void setProperties(ustApplicant ust) throws Exception
   { 
     this.setCompanyName(ust.getCompanyName());
     this.setPersonAddress1(ust.getPersonAddress1());
     this.setPersonAddress2(ust.getPersonAddress2());
     this.setPersonCity(ust.getPersonCity());
     this.setPersonEmail(ust.getPersonEmail());
     this.setPersonFax(ust.getPersonFax());
     this.setPersonFirstName(ust.getPersonFirstName());
     this.setPersonLastName(ust.getPersonLastName());
     this.setPersonMi(ust.getPersonMi());
     this.setPersonPhoneHome(ust.getPersonPhoneHome());
     this.setPersonPhoneOffice(ust.getPersonPhoneOffice());
     this.setPersonState(ust.getPersonState());
     this.setAddressFlag(ust.getAddressFlag());
     this.setPersonZip(ust.getPersonZip());
     this.setPersonZip2(ust.getPersonZip2());
     this.setRenewStatus(ust.getRenewStatus());
     this.setComments(ust.getComments());
     this.setUstId(ust.getUstId());
   }
}
