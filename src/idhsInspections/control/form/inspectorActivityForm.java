package idhsInspections.control.form;

import idhsInspections.to.fireInspection;

import idhsInspections.to.fireInspectorActivity;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class inspectorActivityForm extends ActionForm{

    private int activityId;
    private String activityType;
    private String activityDate;
    private double activityDuration;
    private String activityContact;
    private String activityLocation;
    private String activityRemarks;
    private int inspectorId;
    public inspectorActivityForm() {
    }
        public String getActivityDate()
          { 
           return activityDate;
          }
        public void setActivityDate(String activityDate)
          { 
          this.activityDate = activityDate;
          }
        public int getActivityId()
          { 
           return activityId;
          }
        public void setActivityId(int activityId)
          { 
          this.activityId = activityId;
          }
        public String getActivityContact()
          { 
           return activityContact;
          }
        public void setActivityContact(String activityContact)
          { 
          this.activityContact = activityContact;
          }
        public String getActivityLocation()
          { 
           return activityLocation;
          }
        public void setActivityLocation(String activityLocation)
          { 
          this.activityLocation = activityLocation;
          }
        public String getActivityRemarks()
          { 
           return activityRemarks;
          }
        public void setActivityRemarks(String activityRemarks)
          { 
          this.activityRemarks = activityRemarks;
          }
        public String getActivityType()
          { 
           return activityType;
          }
        public void setActivityType(String activityType)
          { 
          this.activityType = activityType;
          }
        public double getActivityDuration()
          { 
           return activityDuration;
          }
        public void setActivityDuration(double activityDuration)
          { 
          this.activityDuration = activityDuration;
          }
        public int getInspectorId()
          { 
           return inspectorId;
          }
        public void setInspectorId(int inspectorId)
          { 
          this.inspectorId = inspectorId;
          }
    public void setProperties(fireInspectorActivity inspActivity) 
    {
        this.setActivityDate(inspActivity.getActivityDateString());
        this.setActivityContact(inspActivity.getActivityContact());
        this.setActivityLocation(inspActivity.getActivityLocation());
        this.setActivityRemarks(inspActivity.getActivityRemarks());
        this.setActivityType(inspActivity.getActivityType());
        this.setActivityDuration(inspActivity.getActivityDuration());
        this.setActivityId(inspActivity.getActivityId());
        this.setInspectorId(inspActivity.getInspectorId());
    }
     public fireInspectorActivity getInspActivity() 
     {   fireInspectorActivity inspActivity = new fireInspectorActivity();
         inspActivity.setActivityDateString(this.getActivityDate());
         inspActivity.setActivityContact(this.getActivityContact());
         inspActivity.setActivityLocation(this.getActivityLocation());
         inspActivity.setActivityRemarks(this.getActivityRemarks());
         inspActivity.setActivityType(this.getActivityType());
         inspActivity.setActivityDuration(this.getActivityDuration());
         inspActivity.setActivityId(this.getActivityId());
         inspActivity.setInspectorId(this.getInspectorId());
         return inspActivity;
     }
}
