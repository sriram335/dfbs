package ust.to;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class ustApplicant implements Serializable
{ private int ustId;
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
  private String personPhoneOffice;
  private String personPhoneHome;
  private String addressFlag;  
  private String renewStatus;
  private String companyName;
  private String expDate;
  private String status;
  private String issueDate;
  private List fileList;
  private String comments;
  private int fileListCount;
    public ustApplicant() {
       
    }
    public String getCompanyName()
    {
    return companyName;
    }
    public void setCompanyName(String companyName)
    {
    this.companyName = companyName;
    }
  public String getRenewStatus()
  {
  return renewStatus;
  }
  public void setRenewStatus(String renewStatus)
  {
  this.renewStatus = renewStatus;
  }
  public int getUstId()
  {
  return ustId;
  }
  public void setUstId(int ustId)
  {
  this.ustId = ustId;
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

  public String getPersonPhoneOffice()
  {
  return personPhoneOffice;
  }
  public void setPersonPhoneOffice(String personPhoneOffice)
  {
  this.personPhoneOffice = personPhoneOffice;
  }
    public String getPersonPhoneHome()
    {
    return personPhoneHome;
    }
    public void setPersonPhoneHome(String personPhoneHome)
    {
    this.personPhoneHome = personPhoneHome;
    }
  
  public String getAddressFlag()
  {
  return addressFlag;
  }
  public void setAddressFlag(String addressFlag)
  {
  this.addressFlag = addressFlag;
  }
  public boolean isNew()
  {
   return (getUstId() == 0) ? true : false;
  }
    public List getFileList()
      {
        if(fileList == null) 
        {
          fileList = new ArrayList();
        } 
        return fileList;
      }

      public void setFileList(List fileList)
      {
        this.fileList = fileList;
      }
    public String getIssueDate()
    {
    return issueDate;
    }
    public void setIssueDate(String issueDate)
    {
    this.issueDate = issueDate;
    }
    public String getExpDate()
    {
    return expDate;
    }
    public void setExpDate(String expDate)
    {
    this.expDate = expDate;
    }
    public String getStatus()
    {
    return status;
    }
    public void setStatus(String status)
    {
    this.status = status;
    }
    public String getComments()
    {
    return comments;
    }
    public void setComments(String comments)
    {
    this.comments = comments;
    }
    public int getFileListCount() {
      return (fileList == null) ? 0 : fileList.size();
    }
    
    public void setFileListCount(int fileListCount)
    {
    this.fileListCount = fileListCount;
    }
  }
