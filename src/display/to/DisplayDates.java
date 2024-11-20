package display.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;




public class DisplayDates  implements Serializable
{ private String displayId;
 private Date displayDate;
 private String displayTime;
 private String applicationKey;
 private String saveType;
 private int displayDateId;
  public DisplayDates()
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
  public String getSaveType()
  { 
   return saveType;
  }
public void setSaveType(String saveType)
  { 
  this.saveType = saveType;
  }
  public String getDisplayId()
  { 
   return displayId;
  }
public void setDisplayId(String displayId)
  { 
  this.displayId = displayId;
  }
  public String getDisplayTime()
  { 
   return displayTime;
  }
public void setDisplayTime(String displayTime)
  { 
  this.displayTime = displayTime;
  }
   public Date getDisplayDate()
  { 
   return displayDate;
  }
public void setDisplayDate(Date displayDate)
  { 
  this.displayDate = displayDate;
  }
   public void setDisplayDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
displayDate = formatter.parse(dateParam);
} catch (Exception e)
{displayDate = null; }
}
public String getDisplayDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(displayDate == null)
{ return ""; }
else { return formatter.format(displayDate); }
}
 public String getApplicationKey()
  { 
   return applicationKey;
  }
public void setApplicationKey(String applicationKey)
  { 
  this.applicationKey = applicationKey;
  }
}