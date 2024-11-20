package display.control.form;

import display.to.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;


public class DisplayDatesForm extends ActionForm
{private String displayId;
 private String displayDate;
 private String displayTime;
 private String saveType;
 private int displayDateId;
 private String saveDateType;
  public DisplayDatesForm()
  {
  }
  public int getDisplayDateId()
  { 
   return displayDateId;
  }
public void setDisplayDateId(int displayDateId)
  { 
  this.displayDateId = displayDateId;
  }
   public String getDisplayId()
  { 
   return displayId;
  }
public void setDisplayId(String displayId)
  { 
  this.displayId = displayId;
  }
   public String getSaveType()
  { 
   return saveType;
  }
public void setSaveType(String saveType)
  { 
  this.saveType = saveType;
  }
  public String getDisplayTime()
  { 
   return displayTime;
  }
public void setDisplayTime(String displayTime)
  { 
  this.displayTime = displayTime;
  }
   public String getDisplayDate()
  { 
   return displayDate;
  }
public void setDisplayDate(String displayDate)
  { 
  this.displayDate = displayDate;
  }
   public DisplayDates getDisplayDates() 
  { DisplayDates displayDate = new DisplayDates();
    displayDate.setDisplayId(getDisplayId());
    displayDate.setDisplayDateString(getDisplayDate());
    displayDate.setDisplayTime(getDisplayTime());
    displayDate.setSaveType(getSaveType());
    displayDate.setDisplayDateId(getDisplayDateId());
    return(displayDate);
  }
  public void setUpdatedProperties(DisplayDates updatedDisplayDate,DisplayDates displayDate) throws Exception
  { 
    displayDate.setDisplayDate(updatedDisplayDate.getDisplayDate());
    displayDate.setDisplayTime(updatedDisplayDate.getDisplayTime());
   
 }
  
  public void setProperties(DisplayDates displayDate) 
  {        
    this.setDisplayDate(displayDate.getDisplayDateString());
    this.setDisplayTime(displayDate.getDisplayTime());
    this.setDisplayId(displayDate.getDisplayId());
    this.setDisplayDateId(displayDate.getDisplayDateId());
  }
    public String getSaveDateType()
    {
    return saveDateType;
    }
    public void setSaveDateType(String saveDateType)
    {
    this.saveDateType = saveDateType;
    }
}