package ust.control.form;

import java.io.Serializable;

import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import ust.to.*;
public class ustCertificationForm extends ActionForm{
  private int ustId;
  private String certNumber;
  private String certType;
  private String mailDate;
  private String issueDate;
  private String expDate;
  private String status;
  private String install;
  private String cathode;
  private String testing;
  private String deccom;
  private String decomStatus;
  private String evidenceType;
  private String licState;
    public ustCertificationForm() {
      
    }
 
  public String getExpDate()
    {    return expDate;  }
  public void setExpDate(String expDate)
    {   this.expDate = expDate;  }
  public String getIssueDate()
    {    return issueDate;  }
  public void setIssueDate(String issueDate)
    {   this.issueDate = issueDate;  }
  public String getMailDate()
    {    return mailDate;  }
  public void setMailDate(String mailDate)
    {   this.mailDate = mailDate;  }
  public String getCathode()
    {    return cathode;  }
  public void setCathode(String cathode)
    {   this.cathode = cathode;  }
  public String getCertNumber()
    {    return certNumber;  }
  public void setCertNumber(String certNumber)
    {   this.certNumber = certNumber;  }
  public String getCertType()
    {    return certType;  }
  public void setCertType(String certType)
    {   this.certType = certType;  }
  public String getDeccom()
    {    return deccom;  }
  public void setDeccom(String deccom)
    {   this.deccom = deccom;  }
  public String getDecomStatus()
    {    return decomStatus;  }
  public void setDecomStatus(String decomStatus)
    {   this.decomStatus = decomStatus;  }
  public String getEvidenceType()
    {    return evidenceType;  }
  public void setEvidenceType(String evidenceType)
    {   this.evidenceType = evidenceType;  }
  public String getInstall()
    {    return install;  }
  public void setInstall(String install)
    {   this.install = install;  }
  public String getLicState()
    {    return licState;  }
  public void setLicState(String licState)
    {   this.licState = licState;  }
  public String getStatus()
    {    return status;  }
  public void setStatus(String status)
    {   this.status = status;  }
  public String getTesting()
    {    return testing;  }
  public void setTesting(String testing)
    {   this.testing = testing;  }
  public int getUstId()
    {    return ustId;  }
  public void setUstId(int ustId)
    {   this.ustId = ustId;  }
  
  public ustCertification getUstCertification() throws Exception
   {
     
     ustCertification cert = new ustCertification ();
     cert.setIssueDateString(this.getIssueDate());
     cert.setExpDateString(this.getExpDate());
     cert.setMailDateString(this.getMailDate());
     cert.setCathode(this.getCathode());
     cert.setCertNumber(this.getCertNumber());
     cert.setCertType(this.getCertType());
     cert.setDeccom(this.getDeccom());
     cert.setDecomStatus(this.getDecomStatus());
     cert.setEvidenceType(this.getEvidenceType());
     cert.setInstall(this.getInstall());
     cert.setLicState(this.getLicState());
     cert.setStatus(this.getStatus());
     cert.setTesting(this.getTesting());
     cert.setUstId(this.getUstId());
     cert.setStatus(this.getStatus());
  return cert;
   }
    public void setProperties(ustCertification cert) throws Exception
   { 
     this.setExpDate(cert.getExpDateString());
     this.setIssueDate(cert.getIssueDateString());
     this.setMailDate(cert.getMailDateString());
     this.setCathode(cert.getCathode());
     this.setCertNumber(cert.getCertNumber());
     this.setCertType(cert.getCertType());
     this.setDeccom(cert.getDeccom());
     this.setDecomStatus(cert.getDecomStatus());
     this.setEvidenceType(cert.getEvidenceType());
     this.setInstall(cert.getInstall());
     this.setLicState(cert.getLicState());
     this.setStatus(cert.getStatus());
     this.setTesting(cert.getTesting());
     this.setUstId(cert.getUstId());
   }
}
