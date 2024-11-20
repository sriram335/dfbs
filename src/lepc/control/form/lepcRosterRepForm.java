package lepc.control.form;
import lepc.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;
public class lepcRosterRepForm extends ActionForm{
  private int personId;
  private String personLastName;
  private String personFirstName;
  private String personMi;
  private String personEmail;
  private String personFax;
  private String personPhone;
  private String personType;
  private int rosterId;
  private String lepcDate;
  private String addDate;
  private String statusDate;
  private String personStatus;
  private String personEthicsId;
  private String personEthicsPwd;
  private String personTrainStatus;
  private String personDept;
    public lepcRosterRepForm() {
        
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
  public String getAddDate()
    {    return addDate;  }
  public void setAddDate(String addDate)
    {   this.addDate = addDate;  }
  public String getLepcDate()
    {    return lepcDate;  }
  public void setLepcDate(String lepcDate)
    {   this.lepcDate = lepcDate;  }
  public String getStatusDate()
    {    return statusDate;  }
  public void setStatusDate(String statusDate)
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
  
  public void setProperties(lepcRosterRep rep) 
  {
    this.setAddDate(rep.getAddDateString());
    this.setLepcDate(rep.getLepcDateString());
    this.setStatusDate(rep.getStatusDateString());
    this.setPersonEmail(rep.getPersonEmail());
    this.setPersonEthicsId(rep.getPersonEthicsId());
    this.setPersonEthicsPwd(rep.getPersonEthicsPwd());
    this.setPersonFax(rep.getPersonFax());
    this.setPersonFirstName(rep.getPersonFirstName());
    this.setPersonLastName(rep.getPersonLastName());
    this.setPersonMi(rep.getPersonMi());
    this.setPersonPhone(rep.getPersonPhone());
    this.setPersonStatus(rep.getPersonStatus());
    this.setPersonTrainStatus(rep.getPersonTrainStatus());
    this.setPersonType(rep.getPersonType());
    this.setPersonId(rep.getPersonId());
    this.setRosterId(rep.getRosterId());
    this.setPersonDept(rep.getPersonDept());
  }
  
   public lepcRosterRep getRosterRep() 
  {
    lepcRosterRep rep = new lepcRosterRep();
    rep.setAddDateString(this.getAddDate());
    rep.setLepcDateString(this.getLepcDate());
    rep.setStatusDateString(this.getStatusDate());
    rep.setPersonEmail(this.getPersonEmail());
    rep.setPersonEthicsId(this.getPersonEthicsId());
    rep.setPersonEthicsPwd(this.getPersonEthicsPwd());
    rep.setPersonFax(this.getPersonFax());
    rep.setPersonFirstName(this.getPersonFirstName());
    rep.setPersonLastName(this.getPersonLastName());
    rep.setPersonMi(this.getPersonMi());
    rep.setPersonPhone(this.getPersonPhone());
    rep.setPersonStatus(this.getPersonStatus());
    rep.setPersonTrainStatus(this.getPersonTrainStatus());
    rep.setPersonType(this.getPersonType());
    rep.setPersonId(this.getPersonId());
    rep.setRosterId(this.getRosterId());
    rep.setPersonDept(this.getPersonDept());
    return rep;
  }

}
