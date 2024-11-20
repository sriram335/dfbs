package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;

public class EmsInstAgmtsForm extends ActionForm
{ private int agreementId;
  private int institutionId;
  private String effectiveDate;
  private String expirationDate;
  private String name;
  private String remarks;
  public EmsInstAgmtsForm()
  {
  }
  
  public String getEffectiveDate()
  { 
   return effectiveDate;
  }
public void setEffectiveDate(String effectiveDate)
  { 
  this.effectiveDate = effectiveDate;
  }
public String getExpirationDate()
  { 
   return expirationDate;
  }
public void setExpirationDate(String expirationDate)
  { 
  this.expirationDate = expirationDate;
  }
public String getName()
  { 
   return name;
  }
public void setName(String name)
  { 
  this.name = name;
  }
public String getRemarks()
  { 
   return remarks;
  }
public void setRemarks(String remarks)
  { 
  this.remarks = remarks;
  }
public int getAgreementId()
  { 
   return agreementId;
  }
public void setAgreementId(int agreementId)
  { 
  this.agreementId = agreementId;
  }
public int getInstitutionId()
  { 
   return institutionId;
  }
public void setInstitutionId(int institutionId)
  { 
  this.institutionId = institutionId;
  }
   public EmsInstAgmts getEmsInstAgmts() 
  {
    EmsInstAgmts agmts = new EmsInstAgmts();
   
    agmts.setAgreementId(getAgreementId());
    agmts.setEffectiveDateString(getEffectiveDate());
    agmts.setExpirationDateString(getExpirationDate());
    agmts.setInstitutionId(getInstitutionId());
    agmts.setName(getName());
    agmts.setRemarks(getRemarks());
    return agmts;
  }
   public void setProperties(EmsInstAgmts agmts) 
  {
    this.setAgreementId(agmts.getAgreementId());
    this.setEffectiveDate(agmts.getEffectiveDateString());
    this.setExpirationDate(agmts.getExpirationDateString());
    this.setInstitutionId(agmts.getInstitutionId());
    this.setName(agmts.getName());
    this.setRemarks(agmts.getRemarks());
  }
}