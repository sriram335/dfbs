package ust.to;
import java.io.Serializable;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;

public class ustCertification implements Serializable {
    private int ustId;
    private String certNumber;
    private String certType;
    private Date mailDate;
    private Date issueDate;
    private Date expDate;
    private String status;
    private String install;
    private String cathode;
    private String testing;
    private String deccom;
    private String decomStatus;
    private String evidenceType;
    private String licState;
  
    public ustCertification() {
       
    }
  public Date getExpDate()
    {    return expDate;  }
  public void setExpDate(Date expDate)
    {   this.expDate = expDate;  }
  public Date getIssueDate()
    {    return issueDate;  }
  public void setIssueDate(Date issueDate)
    {   this.issueDate = issueDate;  }
  public Date getMailDate()
    {    return mailDate;  }
  public void setMailDate(Date mailDate)
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
  public void setExpDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  expDate = formatter.parse(dateParam);
  } catch (Exception e)
  {expDate = null; }
  }
  public String getExpDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(expDate == null)
  { return ""; }
  else { return formatter.format(expDate); }
  }
  public void setIssueDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  issueDate = formatter.parse(dateParam);
  } catch (Exception e)
  {issueDate = null; }
  }
  public String getIssueDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(issueDate == null)
  { return ""; }
  else { return formatter.format(issueDate); }
  }
  public void setMailDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  mailDate = formatter.parse(dateParam);
  } catch (Exception e)
  {mailDate = null; }
  }
  public String getMailDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(mailDate == null)
  { return ""; }
  else { return formatter.format(mailDate); }
  }
}
