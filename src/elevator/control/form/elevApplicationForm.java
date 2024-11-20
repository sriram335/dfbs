package elevator.control.form;
import elevator.to.*;

import org.apache.struts.action.ActionForm;

public class elevApplicationForm extends ActionForm{
  private int applicationId;
  private String applicationType;
  private String applicationDate;
  private String codeForAlterations;
  private String stateNumber;
  private String appliedBy;
  private String appliedDesignation;
  private String appliedFirm;
  private String ownerBy;
  private String ownerDesignation;
  private String ownerFirm;
  private String elevAppKey;
  private String ownerEmail;
  private String appliedEmail;
  private String appStatus;
    public elevApplicationForm() {
      
    }
  public void setAppStatus(String appStatus)
    { 
    this.appStatus = appStatus;
    }
  public String getAppStatus()
    { 
     return appStatus;
    }
  public String getApplicationDate()
    { 
     return applicationDate;
    }
  public void setApplicationDate(String applicationDate)
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
  public String getElevAppKey()
    { 
     return elevAppKey;
    }
  public void setElevAppKey(String elevAppKey)
    { 
    this.elevAppKey = elevAppKey;
    }
  public elevatorApplication  getElevatorApplication()
  {
    elevatorApplication elevApp =new elevatorApplication();
    elevApp.setApplicationDateString(this.getApplicationDate());
    elevApp.setApplicationType(this.getApplicationType());
    elevApp.setAppliedBy(this.getAppliedBy());
    elevApp.setAppliedDesignation(this.getAppliedDesignation());
    elevApp.setAppliedFirm(this.getAppliedFirm());
    elevApp.setCodeForAlterations(this.getCodeForAlterations());
    elevApp.setElevAppKey(this.getElevAppKey());
    elevApp.setOwnerBy(this.getOwnerBy());
    elevApp.setOwnerDesignation(this.getOwnerDesignation());
    elevApp.setOwnerFirm(this.getOwnerFirm());
    elevApp.setStateNumber(this.getStateNumber());
    elevApp.setApplicationId(this.getApplicationId());
    elevApp.setOwnerEmail(this.getOwnerEmail());
    elevApp.setAppliedEmail(this.getAppliedEmail());
    elevApp.setAppStatus(this.getAppStatus());
    return elevApp;
  }
  public void setProperties(elevatorApplication elevApp)
  {
    this.setApplicationDate(elevApp.getApplicationDateString());
    this.setApplicationType(elevApp.getApplicationType());
    this.setAppliedBy(elevApp.getAppliedBy());
    this.setAppliedDesignation(elevApp.getAppliedDesignation());
    this.setAppliedFirm(elevApp.getAppliedFirm());
    this.setCodeForAlterations(elevApp.getCodeForAlterations());
    this.setElevAppKey(elevApp.getElevAppKey());
    this.setOwnerBy(elevApp.getOwnerBy());
    this.setOwnerDesignation(elevApp.getOwnerDesignation());
    this.setOwnerFirm(elevApp.getOwnerFirm());
    this.setStateNumber(elevApp.getStateNumber());
    this.setApplicationId(elevApp.getApplicationId());
    this.setOwnerEmail(elevApp.getOwnerEmail());
    this.setAppliedEmail(elevApp.getAppliedEmail());
    this.setAppStatus(elevApp.getAppStatus()); 
  }
  public void setUpdatedProperties(elevatorApplication updatedElevApp,elevatorApplication elevApp) throws Exception
  {
    elevApp.setApplicationDate(updatedElevApp.getApplicationDate());
    elevApp.setApplicationType(updatedElevApp.getApplicationType());
    elevApp.setAppliedBy(updatedElevApp.getAppliedBy());
    elevApp.setAppliedDesignation(updatedElevApp.getAppliedDesignation());
    elevApp.setAppliedFirm(updatedElevApp.getAppliedFirm());
    elevApp.setCodeForAlterations(updatedElevApp.getCodeForAlterations());
    elevApp.setElevAppKey(updatedElevApp.getElevAppKey());
    elevApp.setOwnerBy(updatedElevApp.getOwnerBy());
    elevApp.setOwnerDesignation(updatedElevApp.getOwnerDesignation());
    elevApp.setOwnerFirm(updatedElevApp.getOwnerFirm());
    elevApp.setStateNumber(updatedElevApp.getStateNumber());
    elevApp.setApplicationId(updatedElevApp.getApplicationId());
    elevApp.setOwnerEmail(updatedElevApp.getOwnerEmail());
    elevApp.setAppliedEmail(updatedElevApp.getAppliedEmail());
    elevApp.setAppStatus(updatedElevApp.getAppStatus());
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
