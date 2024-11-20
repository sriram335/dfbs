package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
 
public class EmsLevelForm  extends ActionForm
{ private int levelId;
  private String levelName;
  private int providerId;
  private int hospitalId;
  private int institutionId; 
  public EmsLevelForm()
  {
  }
  public String getLevelName()
  { 
   return levelName;
  }
public void setLevelName(String levelName)
  { 
  this.levelName = levelName;
  }
public int getHospitalId()
  { 
   return hospitalId;
  }
public void setHospitalId(int hospitalId)
  { 
  this.hospitalId = hospitalId;
  }
public int getInstitutionId()
  { 
   return institutionId;
  }
public void setInstitutionId(int institutionId)
  { 
  this.institutionId = institutionId;
  }
public int getLevelId()
  { 
   return levelId;
  }
public void setLevelId(int levelId)
  { 
  this.levelId = levelId;
  }
public int getProviderId()
  { 
   return providerId;
  }
public void setProviderId(int providerId)
  { 
  this.providerId = providerId;
  }
  public EmsLevel getEmsLevel() 
  {
    EmsLevel level = new EmsLevel();
    
   
    level.setLevelId(getLevelId());
    level.setLevelName(getLevelName());
    level.setProviderId(getProviderId());
    return level;
  }
   public void setProperties(EmsLevel level) 
  {System.out.println("22");
   
    this.setLevelId(level.getLevelId());
    this.setLevelName(level.getLevelName());
    this.setProviderId(level.getProviderId());
  }
}