package cigarette.control.form;

import cigarette.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;

import main.control.form.*;
import main.to.*;

public class CigaretteApplicationForm extends CompleteContactForm
{private int appId ;
  private int companyId ;
  private String appDate;
  private String appType;
  private String contact;
  private String title;
  private String phone;
  private String fax;
  private String email;
  private String applicationKey;
  private double amount;
  private String comments;
  private String appIssueDate;
  private String appExpDate;
  private String appStatus;
  public CigaretteApplicationForm()
  {
  }
  public String getAppDate()
  { 
   return appDate;
  }
public void setAppDate(String appDate)
  { 
  this.appDate = appDate;
  }
public String getAppType()
  { 
   return appType;
  }
public void setAppType(String appType)
  { 
  this.appType = appType;
  }
public String getContact()
  { 
   return contact;
  }
public void setContact(String contact)
  { 
  this.contact = contact;
  }
public String getEmail()
  { 
   return email;
  }
public void setEmail(String email)
  { 
  this.email = email;
  }
public String getFax()
  { 
   return fax;
  }
public void setFax(String fax)
  { 
  this.fax = fax;
  }
public String getPhone()
  { 
   return phone;
  }
public void setPhone(String phone)
  { 
  this.phone = phone;
  }
public String getTitle()
  { 
   return title;
  }
public void setTitle(String title)
  { 
  this.title = title;
  }
public int getAppId()
  { 
   return appId;
  }
public void setAppId(int appId)
  { 
  this.appId = appId;
  }
public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
 
  public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public boolean isNew() 
  {
    return (this.getApplicationKey() == null || this.getApplicationKey().trim().equals("") ) ? true : false;
  }
 public CigaretteApplication getCigaretteApplication() throws Exception
  {  CigaretteApplication application = new CigaretteApplication ();
     application.setAppDateString(getAppDate());
    application.setAppId(getAppId());
    application.setAppType(getAppType());
    application.setCompanyId(getCompanyId());
    application.setContact(getContact());
    application.setEmail(getEmail());
    application.setFax(getFax());
    application.setPhone(getPhone());
    application.setTitle(getTitle());
    application.setComments(getComments());
    application.setAppIssueDateString(getAppIssueDate());
    application.setAppExpDateString(getAppExpDate());
    application.setAppStatus(getAppStatus());
    return application;
  }
 public void setProperties(CigaretteApplication application) throws Exception
  {
      this.setAppDate(application.getAppDateString());
    this.setAppId(application.getAppId());
    this.setAppType(application.getAppType());
    this.setCompanyId(application.getCompanyId());
    this.setContact(application.getContact());
    this.setEmail(application.getEmail());
    this.setFax(application.getFax());
    this.setPhone(application.getPhone());
    this.setTitle(application.getTitle());
    this.setComments(application.getComments());
    this.setAppIssueDate(application.getAppIssueDateString());
    this.setAppExpDate(application.getAppExpDateString());
     this.setAppStatus(application.getAppStatus());
  }
  
   public void setUpdatedProperties(CigaretteApplication updatedApplication,CigaretteApplication application) throws Exception
  {
    application.setAppDate(updatedApplication.getAppDate());
    application.setAppId(updatedApplication.getAppId());
    application.setAppType(updatedApplication.getAppType());
    application.setCompanyId(updatedApplication.getCompanyId());
    application.setContact(updatedApplication.getContact());
    application.setEmail(updatedApplication.getEmail());
    application.setFax(updatedApplication.getFax());
    application.setPhone(updatedApplication.getPhone());
    application.setTitle(updatedApplication.getTitle());
    application.setComments(getComments());
    application.setAppIssueDate(updatedApplication.getAppIssueDate());
    application.setAppExpDate(updatedApplication.getAppExpDate());
    application.setAppStatus(getAppStatus());
  }
  public String getComments()
  { 
   return comments;
  }
public void setComments(String comments)
  { 
  this.comments = comments;
  } 
    public String getAppIssueDate()
  { 
   return appIssueDate;
  }
public void setAppIssueDate(String appIssueDate)
  { 
  this.appIssueDate = appIssueDate;
  }
   public String getAppExpDate()
  { 
   return appExpDate;
  }
public void setAppExpDate(String appExpDate)
  { 
  this.appExpDate = appExpDate;
  }
   public String getAppStatus()
  { 
   return appStatus;
  }
public void setAppStatus(String appStatus)
  { 
  this.appStatus = appStatus;
  }
}
