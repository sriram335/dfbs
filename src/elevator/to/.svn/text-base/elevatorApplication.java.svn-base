package elevator.to;
import hs.to.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;
public class elevatorApplication implements Serializable{
   private int applicationId;
   private String applicationType;
   private Date applicationDate;
   private String codeForAlterations;
   private String stateNumber;
   private String appliedBy;
   private String appliedDesignation;
   private String appliedFirm;
   private String ownerBy;
   private String ownerDesignation;
  private String ownerEmail;
  private String appliedEmail;
   private String ownerFirm;
  private String elevAppKey;
  private int appFee;
  private String appStatus;
    public elevatorApplication() {
       
       
    }
  public Date getApplicationDate()
    { 
     return applicationDate;
    }
  public void setApplicationDate(Date applicationDate)
    { 
    this.applicationDate = applicationDate;
    }
  public String getApplicationType()
    { 
     return applicationType;
    }
  public void setApplicationType(String applicationType)
    { 
    this.applicationType = applicationType;
    }
  public String getAppStatus()
    { 
     return appStatus;
    }
  public void setAppStatus(String appStatus)
    { 
    this.appStatus = appStatus;
    }
  public String getAppliedBy()
    { 
     return appliedBy;
    }
  public void setAppliedBy(String appliedBy)
    { 
    this.appliedBy = appliedBy;
    }
  public String getAppliedDesignation()
    { 
     return appliedDesignation;
    }
  public void setAppliedDesignation(String appliedDesignation)
    { 
    this.appliedDesignation = appliedDesignation;
    }
  public String getAppliedFirm()
    { 
     return appliedFirm;
    }
  public void setAppliedFirm(String appliedFirm)
    { 
    this.appliedFirm = appliedFirm;
    }
  public String getCodeForAlterations()
    { 
     return codeForAlterations;
    }
  public void setCodeForAlterations(String codeForAlterations)
    { 
    this.codeForAlterations = codeForAlterations;
    }
  public String getOwnerBy()
    { 
     return ownerBy;
    }
  public void setOwnerBy(String ownerBy)
    { 
    this.ownerBy = ownerBy;
    }
  public String getOwnerDesignation()
    { 
     return ownerDesignation;
    }
  public void setOwnerDesignation(String ownerDesignation)
    { 
    this.ownerDesignation = ownerDesignation;
    }
  public String getOwnerFirm()
    { 
     return ownerFirm;
    }
  public void setOwnerFirm(String ownerFirm)
    { 
    this.ownerFirm = ownerFirm;
    }
  public String getStateNumber()
    { 
     return stateNumber;
    }
  public void setStateNumber(String stateNumber)
    { 
    this.stateNumber = stateNumber;
    }
  public int getApplicationId()
    { 
     return applicationId;
    }
  public void setApplicationId(int applicationId)
    { 
    this.applicationId = applicationId;
    }
  public int getAppFee()
    { 
     return appFee;
    }
  public void setAppFee(int appFee)
    { 
    this.appFee = appFee;
    }
  public void setApplicationDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  applicationDate = formatter.parse(dateParam);
  } catch (Exception e)
  {applicationDate = null; }
  }
  public String getApplicationDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(applicationDate == null)
  { return ""; }
  else { return formatter.format(applicationDate); }
  }
  public String getElevAppKey()
    { 
     return elevAppKey;
    }
  public void setElevAppKey(String elevAppKey)
    { 
    this.elevAppKey = elevAppKey;
    }
  public boolean isNew() 
  {
    return (getApplicationId() == 0) ? true : false;
  }
  public String getOwnerEmail()
    { 
     return ownerEmail;
    }
  public void setOwnerEmail(String ownerEmail)
    { 
    this.ownerEmail = ownerEmail;
    }
  public String getAppliedEmail()
    { 
     return appliedEmail;
    }
  public void setAppliedEmail(String appliedEmail)
    { 
    this.appliedEmail = appliedEmail;
    }
}
