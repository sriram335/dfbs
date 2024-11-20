package elevator.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class elevViolation implements Serializable
{ private int vioId;
  private int vioOrder;
  private String vioCode;
  private String vioDescription;
  private String vioRemarks;
  private Date vioCorDate;
  private Date vioCompDate;
  private int inspectionId;
    public elevViolation() {
    }
    public int getInspectionId()
      { 
       return inspectionId;
      }
    public void setInspectionId(int inspectionId)
      { 
      this.inspectionId = inspectionId;
      }
      public Date getVioCompDate()
      { 
       return vioCompDate;
      }
    public void setVioCompDate(Date vioCompDate)
      { 
      this.vioCompDate = vioCompDate;
      }
    public Date getVioCorDate()
      { 
       return vioCorDate;
      }
    public void setVioCorDate(Date vioCorDate)
      { 
      this.vioCorDate = vioCorDate;
      }
    public String getVioCode()
      { 
       return vioCode;
      }
    public void setVioCode(String vioCode)
      { 
      this.vioCode = vioCode;
      }
    public String getVioDescription()
      { 
       return vioDescription;
      }
    public void setVioDescription(String vioDescription)
      { 
      this.vioDescription = vioDescription;
      }
    public String getVioRemarks()
      { 
       return vioRemarks;
      }
    public void setVioRemarks(String vioRemarks)
      { 
      this.vioRemarks = vioRemarks;
      }
    public int getVioId()
      { 
       return vioId;
      }
    public void setVioId(int vioId)
      { 
      this.vioId = vioId;
      }
    public int getVioOrder()
      { 
       return vioOrder;
      }
    public void setVioOrder(int vioOrder)
      { 
      this.vioOrder = vioOrder;
      }
      public void setVioCompDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    vioCompDate = formatter.parse(dateParam);
    } catch (Exception e)
    {vioCompDate = null; }
    }
    public String getVioCompDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(vioCompDate == null)
    { return ""; }
    else { return formatter.format(vioCompDate); }
    }
    public void setVioCorDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    vioCorDate = formatter.parse(dateParam);
    } catch (Exception e)
    {vioCorDate = null; }
    }
    public String getVioCorDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(vioCorDate == null)
    { return ""; }
    else { return formatter.format(vioCorDate); }
    }
      public boolean isNew() 
      {
        return (getVioId() == 0) ? true : false;
      }
    }
