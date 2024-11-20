package code.control.form;

import code.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
import main.to.*;
import main.control.form.*;
public class CodePersonForm extends ActionForm
{
private int codePersonId;
  private String codePersonType;
  private String codePersonLastName;
  private String codePersonFirstName;
  private String codePersonMiddleName;
  private String codePersonTitle;
  private String codePersonTelephone;
  private String codePersonFax;
  private String codePersonEmail;
  private int codePersonCompanyId;
  private CodeManufacturer manufacturer;
  private CodeFacility facility;
  private int applicationKey;
  public CodePersonForm()
  {
  }
   public void setProperties(CodePerson person) throws Exception
  {
    this.setCodePersonId(person.getCodePersonId());
    this.setCodePersonLastName(person.getCodePersonLastName());
    this.setCodePersonMiddleName(person.getCodePersonMiddleName());
    this.setCodePersonFirstName(person.getCodePersonFirstName());
    this.setCodePersonTitle(person.getCodePersonTitle());
    this.setCodePersonTelephone(person.getCodePersonTelephone());
    this.setCodePersonFax(person.getCodePersonFax());
    this.setCodePersonEmail(person.getCodePersonEmail());
    this.setCodePersonType(person.getCodePersonType());
    this.setCodePersonCompanyId(person.getCodePersonCompanyId());
   
    
  }
 public CodePerson getCodePerson() throws Exception
  {
    
    CodePerson person = new CodePerson ();
    person.setCodePersonId(this.getCodePersonId());
    person.setCodePersonLastName(this.getCodePersonLastName());
    person.setCodePersonMiddleName(this.getCodePersonMiddleName());
    person.setCodePersonFirstName(this.getCodePersonFirstName());
    person.setCodePersonTitle(this.getCodePersonTitle());
    person.setCodePersonTelephone(this.getCodePersonTelephone());
    person.setCodePersonFax(this.getCodePersonFax());
    person.setCodePersonEmail(this.getCodePersonEmail());
    person.setCodePersonType(this.getCodePersonType());
    person.setCodePersonCompanyId(this.getCodePersonCompanyId());
    
    
    return(person);
  }
 
  public int getCodePersonId() 
  {
    return codePersonId;
  }
  public void setCodePersonId(int codePersonId) 
  {
    this.codePersonId = codePersonId;
   
  }
   public String getCodePersonType() 
  {
    return codePersonType;
  }
  public void setCodePersonType(String codePersonType) 
  {
    this.codePersonType=codePersonType;
  }
  public String getCodePersonLastName() 
  {
    return codePersonLastName;
  }
  public void setCodePersonLastName(String codePersonLastName) 
  {
    this.codePersonLastName=codePersonLastName;
  }
  
   public String getCodePersonFirstName() 
  {
    return codePersonFirstName;
  }
  public void setCodePersonFirstName(String codePersonFirstName) 
  {
    this.codePersonFirstName=codePersonFirstName;
  }
  
  public String getCodePersonMiddleName() 
  {
    return codePersonMiddleName;
  }
  public void setCodePersonMiddleName(String codePersonMiddleName) 
  {
    this.codePersonMiddleName=codePersonMiddleName;
  }
  
  public String getCodePersonTitle() 
  {
    return codePersonTitle;
  }
  public void setCodePersonTitle(String codePersonTitle) 
  {
    this.codePersonTitle=codePersonTitle;
  }
  
  public  String getCodePersonTelephone()
  {
  return codePersonTelephone;
  }
  public void setCodePersonTelephone(String codePersonTelephone)
  {
  this.codePersonTelephone = codePersonTelephone;
  }
  public  String getCodePersonFax()
  {
  return codePersonFax;
  }
  public void setCodePersonFax(String codePersonFax)
  {
  this.codePersonFax = codePersonFax;
  }
  public  String getCodePersonEmail()
  {
  return codePersonEmail;
  }
  public void setCodePersonEmail(String codePersonEmail)
  {
  this.codePersonEmail = codePersonEmail;
  }
  public  int getCodePersonCompanyId()
  {
  return codePersonCompanyId;
  }
  public void setCodePersonCompanyId(int codePersonCompanyId)
  {
  this.codePersonCompanyId = codePersonCompanyId;
  }
   public CodeManufacturer getManufacturer()
  {
    return manufacturer;
  }

  public void setManufacturer(CodeManufacturer manufacturer)
  {
    this.manufacturer = manufacturer;
  }
  public int getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(int applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public CodeFacility getFacility()
  {
    return facility;
  }

  public void setFacility(CodeFacility facility)
  {
    this.facility = facility;
  }
}
