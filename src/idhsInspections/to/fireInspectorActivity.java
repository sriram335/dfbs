package idhsInspections.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class fireInspectorActivity implements Serializable{
    private int activityId;
    private String activityType;
    private Date activityDate;
    private double activityDuration;
    private String activityContact;
    private String activityLocation;
    private String activityRemarks;
    private int inspectorId;
    private String inspectorName;
    


    public fireInspectorActivity() {
    }
    public Date getActivityDate()
      { 
       return activityDate;
      }
    public void setActivityDate(Date activityDate)
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
    public void setActivityDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    activityDate = formatter.parse(dateParam);
    } catch (Exception e)
    {activityDate = null; }
    }
    public String getActivityDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(activityDate == null)
    { return ""; }
    else { return formatter.format(activityDate); }
    }
  public String getInspectorName()
    { 
     return inspectorName;
    }
  public void setInspectorName(String inspectorName)
    { 
    this.inspectorName = inspectorName;
    }
}
