package lepc.control.form;
import lepc.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;

public class lepcAgencyForm extends ActionForm{
    public lepcAgencyForm() {
        
    }
  private int agencyId;
  private int exerciseId;
  private String agencyName;
  private String agencyPresence;
  private String agencyType;
  private String agencyDebrief;
  private String agencyOther;
  public String getAgencyDebrief()
  {    return agencyDebrief;  }
  public void setAgencyDebrief(String agencyDebrief)
  {   this.agencyDebrief =
  agencyDebrief;  }
    public String getAgencyOther()
    {    return agencyOther;  }
    public void setAgencyOther(String agencyOther)
    {   this.agencyOther =    agencyOther;  }
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
  
    public lepcAgency getAgency() throws Exception
     {
       lepcAgency agency = new lepcAgency();
       agency.setAgencyDebrief(this.getAgencyDebrief());
       agency.setAgencyName(this.getAgencyName());
       agency.setAgencyType(this.getAgencyType());
       agency.setAgencyPresence(this.getAgencyPresence());
       agency.setAgencyId(this.getAgencyId());
       agency.setExerciseId(this.getExerciseId());
    return agency;
     }
      public void setProperties(lepcAgency agency) throws Exception
     { 
      System.out.println("1:"+agency.getAgencyDebrief());
       this.setAgencyDebrief(agency.getAgencyDebrief());
       this.setAgencyName(agency.getAgencyName());
       this.setAgencyType(agency.getAgencyType());System.out.println("2:"+agency.getAgencyType());
       this.setAgencyPresence(agency.getAgencyPresence());System.out.println("3:"+agency.getAgencyPresence());
       this.setAgencyId(agency.getAgencyId());
       this.setExerciseId(agency.getExerciseId());
     }
  }

