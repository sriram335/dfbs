package ems.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;


public class EmsWaiver implements Serializable
{ private int waiverId;
  private String waiverCode;
  private Date waiverDate;
  private int levelId;
  public EmsWaiver()
  {
  }
  public Date getWaiverDate()
  { 
   return waiverDate;
  }
public void setWaiverDate(Date waiverDate)
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
  public void setWaiverDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
waiverDate = formatter.parse(dateParam);
} catch (Exception e)
{
waiverDate = null;
}
}
public String getWaiverDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(waiverDate == null)
{
return "";
}
else
{
return formatter.format(waiverDate);
}
}
}