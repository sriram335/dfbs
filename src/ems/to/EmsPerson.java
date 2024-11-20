package ems.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;


public class EmsPerson implements Serializable
{ private int emsPersonId;
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
  private int personProvId;
  private int personHospId;
  private int personInstId;
  private String personTitle;
  private String personType;
  private Date endDate;
  public EmsPerson()
  {
  }
  
  public int getEmsPersonId()
  { 
   return emsPersonId;
  }
public void setEmsPersonId(int emsPersonId)
  { 
  this.emsPersonId = emsPersonId;
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
  public int getPersonProvId()
  { 
   return personProvId;
  }
public void setPersonProvId(int personProvId)
  { 
  this.personProvId = personProvId;
  }
  public int getPersonHospId()
  { 
   return personHospId;
  }
public void setPersonHospId(int personHospId)
  { 
  this.personHospId = personHospId;
  }
  public int getPersonInstId()
  { 
   return personInstId;
  }
public void setPersonInstId(int personInstId)
  { 
  this.personInstId = personInstId;
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
  public Date getEndDate()
  { 
   return endDate;
  }
public void setEndDate(Date endDate)
  { 
  this.endDate = endDate;
  }
public void setEndDateString(String dateParam)
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  endDate = formatter.parse(dateParam);
  } catch (Exception e)
  {
  endDate = null;
  }
}
public String getEndDateString()
{
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(endDate == null)
  {
  return "";
  }
  else
  {
  return formatter.format(endDate);
  }
}
}
