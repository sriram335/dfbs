package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
public class EmsInstructorsForm  extends ActionForm
{ private String personId;
  private String psId;
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
  private String personPhone;
  private int personInstId;
  private String personTitle;
  private String personType;
  private String endDate;
  private String personCounty;
  private String orgName;
  public EmsInstructorsForm()
  {
  }
 public String getPersonId()
  { 
   return personId;
  }
public void setPersonId(String personId)
  { 
  this.personId = personId;
  }
   public String getPsId()
  { 
   return psId;
  }
public void setPsId(String psId)
  { 
  this.psId = psId;
  }
  public String getPersonCounty()
  { 
   return personCounty;
  }
public void setPersonCounty(String personCounty)
  { 
  this.personCounty = personCounty;
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

public String getPersonPhone()
  { 
   return personPhone;
  }
public void setPersonPhone(String personPhone)
  { 
  this.personPhone = personPhone;
  }
  
  public int getPersonInstId()
  { 
   return personInstId;
  }
public void setPersonInstId(int personInstId)
  { 
  this.personInstId = personInstId;
  }
   public void setProperties(EmsInstructors person) 
  {System.out.println("22");
  
  this.setPersonId(person.getPersonId());
  this.setPersonLastName(person.getPersonLastName());
  this.setPersonFirstName(person.getPersonFirstName());
  this.setPersonMi(person.getPersonMi());
  this.setPersonAddress1(person.getPersonAddress1());
  this.setPersonAddress2(person.getPersonAddress2());
  this.setPersonCity(person.getPersonCity());
  this.setPersonState(person.getPersonState());
  this.setPersonZip(person.getPersonZip());
  this.setPersonZip2(person.getPersonZip2());
  this.setPersonEmail(person.getPersonEmail());
  this.setPersonPhone(person.getPersonPhone());
  this.setPersonFax(person.getPersonFax());
  this.setPersonType(person.getPersonType());
  this.setPersonTitle(person.getPersonTitle());
  this.setEndDate(person.getEndDateString());
  this.setPersonCounty(person.getPersonCounty());
  this.setPsId(person.getPsId());
  this.setOrgName(person.getOrgName());
  }
  public EmsInstructors getEmsInstructors() 
  {
    EmsInstructors person = new EmsInstructors();
   
  person.setPersonId(getPersonId());
  person.setPersonLastName(getPersonLastName());
  person.setPersonFirstName(getPersonFirstName());
  person.setPersonMi(getPersonMi());
  person.setPersonAddress1(getPersonAddress1());
  person.setPersonAddress2(getPersonAddress2());
  person.setPersonCity(getPersonCity());
  person.setPersonState(getPersonState());
  person.setPersonZip(getPersonZip());
  person.setPersonZip2(getPersonZip2());
  person.setPersonEmail(getPersonEmail());
  person.setPersonPhone(getPersonPhone());
  person.setPersonFax(getPersonFax());
  person.setPersonTitle(getPersonTitle());
  person.setPersonType(getPersonType());
  person.setEndDateString(getEndDate());
  person.setPersonCounty(getPersonCounty());
  person.setPsId(getPsId());
  person.setOrgName(getOrgName());
  return person;
  }
      public String getPersonTitle()
  { 
   return personTitle;
  }
public void setPersonTitle(String personTitle)
  { 
  this.personTitle = personTitle;
  }
   public String getPersonType()
  { 
   return personType;
  }
public void setPersonType(String personType)
  { 
  this.personType = personType;
  }
  public String getEndDate()
  { 
   return endDate;
  }
public void setEndDate (String  endDate)
  { 
  this.endDate = endDate;
  }
   public String getOrgName()
  { 
   return orgName;
  }
public void setOrgName(String orgName)
  { 
  this.orgName = orgName;
  }
}