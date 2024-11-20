package lepc.to;
import java.io.Serializable;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class lepcAgency implements Serializable{
    public lepcAgency() {
        
    }
    private int agencyId;
    private int exerciseId;
    private String agencyName;
    private String agencyPresence;
    private String agencyType;
    private String agencyDebrief;
  public String getAgencyDebrief()
    {    return agencyDebrief;  }
  public void setAgencyDebrief(String agencyDebrief)
    {   this.agencyDebrief =
  agencyDebrief;  }
  public String getAgencyName()
    {    return agencyName;  }
  public void setAgencyName(String agencyName)
    {   this.agencyName = agencyName;
  }
  public String getAgencyType()
    {    return agencyType;  }
  public void setAgencyType(String agencyType)
    {   this.agencyType = agencyType;
  }
  public String getAgencyPresence()
    {    return agencyPresence;  }
  public void setAgencyPresence(String agencyPresence)
    {
  this.agencyPresence = agencyPresence;  }
  public int getAgencyId()
    {    return agencyId;  }
  public void setAgencyId(int agencyId)
    {   this.agencyId = agencyId;  }
  public int getExerciseId()
    {    return exerciseId;  }
  public void setExerciseId(int exerciseId)
    {   this.exerciseId = exerciseId;  }
}
