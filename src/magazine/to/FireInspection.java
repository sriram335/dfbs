package magazine.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class FireInspection    implements Serializable
{ private int inspId;
  private Date inspDate;
  private Date inspVioPrintDate;
  private String inspFacId;
  private String inspDistrict;
  private String inspOccLoad;
  private String inspAlaram;
  private String inspSprinkler;
  private int inspInspectorId;
  private String inspType;
  private String inspStatus;
  private String inspCompliance;
  private String inspRemarks;
  private String inspPermitPrint;
  private List violations;
  private String inspectorName;
  private String inspKey;
  private int editFlag;
  public FireInspection()
  {
  }
  public Date getInspDate()
  { 
   return inspDate;
  }
public void setInspDate(Date inspDate)
  { 
  this.inspDate = inspDate;
  }
public Date getInspVioPrintDate()
  { 
   return inspVioPrintDate;
  }
public void setInspVioPrintDate(Date inspVioPrintDate)
  { 
  this.inspVioPrintDate = inspVioPrintDate;
  }
public String getInspAlaram()
  { 
   return inspAlaram;
  }
public void setInspAlaram(String inspAlaram)
  { 
  this.inspAlaram = inspAlaram;
  }
public String getInspDistrict()
  { 
   return inspDistrict;
  }
public void setInspDistrict(String inspDistrict)
  { 
  this.inspDistrict = inspDistrict;
  }
public String getInspFacId()
  { 
   return inspFacId;
  }
public void setInspFacId(String inspFacId)
  { 
  this.inspFacId = inspFacId;
  }
public String getInspOccLoad()
  { 
   return inspOccLoad;
  }
public void setInspOccLoad(String inspOccLoad)
  { 
  this.inspOccLoad = inspOccLoad;
  }
public String getInspSprinkler()
  { 
   return inspSprinkler;
  }
public void setInspSprinkler(String inspSprinkler)
  { 
  this.inspSprinkler = inspSprinkler;
  }
public int getInspId()
  { 
   return inspId;
  }
public void setInspId(int inspId)
  { 
  this.inspId = inspId;
  }
  public String getInspCompliance()
  { 
   return inspCompliance;
  }
public void setInspCompliance(String inspCompliance)
  { 
  this.inspCompliance = inspCompliance;
  }
public int getInspInspectorId()
  { 
   return inspInspectorId;
  }
public void setInspInspectorId(int inspInspectorId)
  { 
  this.inspInspectorId = inspInspectorId;
  }
public String getInspPermitPrint()
  { 
   return inspPermitPrint;
  }
public void setInspPermitPrint(String inspPermitPrint)
  { 
  this.inspPermitPrint = inspPermitPrint;
  }
public String getInspRemarks()
  { 
   return inspRemarks;
  }
public void setInspRemarks(String inspRemarks)
  { 
  this.inspRemarks = inspRemarks;
  }
public String getInspStatus()
  { 
   return inspStatus;
  }
public void setInspStatus(String inspStatus)
  { 
  this.inspStatus = inspStatus;
  }
public String getInspType()
  { 
   return inspType;
  }
public void setInspType(String inspType)
  { 
  this.inspType = inspType;
  }
  public void setInspDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
inspDate = formatter.parse(dateParam);
} catch (Exception e)
{inspDate = null; }
}
public String getInspDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(inspDate == null)
{ return ""; }
else { return formatter.format(inspDate); }
}
public void setInspVioPrintDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
inspVioPrintDate = formatter.parse(dateParam);
} catch (Exception e)
{inspVioPrintDate = null; }
}
public String getInspVioPrintDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(inspVioPrintDate == null)
{ return ""; }
else { return formatter.format(inspVioPrintDate); }
}

 public String getInspKey() 
  {
    return inspKey;
  }
  public void setInspKey(String inspKey) 
  {
   this.inspKey = inspKey;
  }
   public boolean isNew() 
  {
    return (getInspId() == 0) ? true : false;
  }

  public List getViolations()
  {
    if(violations == null) 
    {
      violations = new ArrayList();
    } 
    return violations;
  }

  public void setViolations(List violations)
  {
    this.violations = violations;
  }
   public String getInspectorName() 
  {
    return inspectorName;
  }
  public void setInspectorName(String inspectorName) 
  {
   this.inspectorName = inspectorName;
  }
   public int getEditFlag()
  { 
   return editFlag;
  }
public void setEditFlag(int editFlag)
  { 
  this.editFlag = editFlag;
  } 
}
