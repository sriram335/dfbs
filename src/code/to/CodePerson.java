package code.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

public class CodePerson 
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
  public CodePerson()
  {
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
   public boolean isNew() 
  {
    return (getCodePersonId() == 0 && getApplicationKey() == 0) ? true : false;
  }

}
