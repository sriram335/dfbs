package lepc.to;


import java.io.Serializable;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class lepcRosterRep implements Serializable {
  private int personId;
  private String personLastName;
  private String personFirstName;
  private String personMi;
  private String personEmail;
  private String personFax;
  private String personPhone;
  private String personType;
  private int rosterId;
  private Date lepcDate;
  private Date addDate;
  private Date statusDate;
  private String personStatus;
  private String personEthicsId;
  private String personEthicsPwd;
  private String personTrainStatus;
  private List rosterProxies;
  private String personDept;
    public lepcRosterRep() {
       
    }
  public String getPersonDept()
    {    return personDept;  }
  public void setPersonDept(String personDept)
    {   this.personDept = personDept;  }
  public String getPersonEmail()
    {    return personEmail;  }
  public void setPersonEmail(String personEmail)
    {   this.personEmail = personEmail;  }
  public String getPersonFax()
    {    return personFax;  }
  public void setPersonFax(String personFax)
    {   this.personFax = personFax;  }
  public String getPersonFirstName()
    {    return personFirstName;  }
  public void setPersonFirstName(String personFirstName)
    {   this.personFirstName = personFirstName;  }
  public String getPersonLastName()
    {    return personLastName;  }
  public void setPersonLastName(String personLastName)
    {   this.personLastName = personLastName;  }
  public String getPersonMi()
    {    return personMi;  }
  public void setPersonMi(String personMi)
    {   this.personMi = personMi;  }
  public String getPersonPhone()
    {    return personPhone;  }
  public void setPersonPhone(String personPhone)
    {   this.personPhone = personPhone;  }
  public String getPersonType()
    {    return personType;  }
  public void setPersonType(String personType)
    {   this.personType = personType;  }
  public int getPersonId()
    {    return personId;  }
  public void setPersonId(int personId)
    {   this.personId = personId;  }
  public Date getAddDate()
    {    return addDate;  }
  public void setAddDate(Date addDate)
    {   this.addDate = addDate;  }
  public Date getLepcDate()
    {    return lepcDate;  }
  public void setLepcDate(Date lepcDate)
    {   this.lepcDate = lepcDate;  }
  public Date getStatusDate()
    {    return statusDate;  }
  public void setStatusDate(Date statusDate)
    {   this.statusDate = statusDate;  }
  public String getPersonEthicsId()
    {    return personEthicsId;  }
  public void setPersonEthicsId(String personEthicsId)
    {   this.personEthicsId = personEthicsId;  }
  public String getPersonEthicsPwd()
    {    return personEthicsPwd;  }
  public void setPersonEthicsPwd(String personEthicsPwd)
    {   this.personEthicsPwd = personEthicsPwd;  }
  public String getPersonStatus()
    {    return personStatus;  }
  public void setPersonStatus(String personStatus)
    {   this.personStatus = personStatus;  }
  public String getPersonTrainStatus()
    {    return personTrainStatus;  }
  public void setPersonTrainStatus(String personTrainStatus)
    {   this.personTrainStatus = personTrainStatus;  }
  public int getRosterId()
    {    return rosterId;  }
  public void setRosterId(int rosterId)
    {   this.rosterId = rosterId;  }
  public void setAddDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  addDate = formatter.parse(dateParam);
  } catch (Exception e)
  {addDate = null; }
  }
  public String getAddDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(addDate == null)
  { return ""; }
  else { return formatter.format(addDate); }
  }
  public void setLepcDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  lepcDate = formatter.parse(dateParam);
  } catch (Exception e)
  {lepcDate = null; }
  }
  public String getLepcDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(lepcDate == null)
  { return ""; }
  else { return formatter.format(lepcDate); }
  }
  public void setStatusDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  statusDate = formatter.parse(dateParam);
  } catch (Exception e)
  {statusDate = null; }
  }
  public String getStatusDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(statusDate == null)
  { return ""; }
  else { return formatter.format(statusDate); }
  }
  public int getProxiesCount () 
  {
    return (rosterProxies == null) ? 0 : rosterProxies.size();
  }

  public List getRosterProxies()
  {
    if(rosterProxies == null) 
    {
      rosterProxies = new ArrayList();
    } 
    return rosterProxies;
  }

  public void setRosterProxies(List rosterProxies)
  {
    this.rosterProxies = rosterProxies;
  }
}
