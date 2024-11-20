package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
 
public class EmsWaiverForm  extends ActionForm
{private int waiverId;
  private String waiverCode;
  private String waiverDate;
  private int levelId;
  public EmsWaiverForm()
  {
  }
   public String getWaiverDate()
  { 
   return waiverDate;
  }
public void setWaiverDate(String waiverDate)
  { 
  this.waiverDate = waiverDate;
  }
public String getWaiverCode()
  { 
   return waiverCode;
  }
public void setWaiverCode(String waiverCode)
  { 
  this.waiverCode = waiverCode;
  }
public int getLevelId()
  { 
   return levelId;
  }
public void setLevelId(int levelId)
  { 
  this.levelId = levelId;
  }
public int getWaiverId()
  { 
   return waiverId;
  }
public void setWaiverId(int waiverId)
  { 
  this.waiverId = waiverId;
  }
   public EmsWaiver getEmsWaiver() 
  {
    EmsWaiver waiver = new EmsWaiver();
    waiver.setWaiverId(this.getWaiverId());
    waiver.setLevelId(this.getLevelId());
    waiver.setWaiverDateString(this.getWaiverDate());
    waiver.setWaiverCode(this.getWaiverCode());
    return waiver;
  }
   public void setProperties(EmsWaiver waiver) 
  {System.out.println("22");
    this.setWaiverId(waiver.getWaiverId());
    this.setLevelId(waiver.getLevelId());
    this.setWaiverCode(waiver.getWaiverCode());
    this.setWaiverDate(waiver.getWaiverDateString());
   
  }
}