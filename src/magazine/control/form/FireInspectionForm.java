package magazine.control.form;
import magazine.to.*;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;


public class FireInspectionForm extends ActionForm
{ private int inspId;
  private String inspDate;
  private String inspVioPrintDate;
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
  private int editFlag;
  public FireInspectionForm()
  {
  }
   public String getInspDate()
  { 
   return inspDate;
  }
public void setInspDate(String inspDate)
  { 
  this.inspDate = inspDate;
  }
public String getInspVioPrintDate()
  { 
   return inspVioPrintDate;
  }
public void setInspVioPrintDate(String inspVioPrintDate)
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
  
   public void setProperties(FireInspection inspection) 
  {  
    this.setInspAlaram(inspection.getInspAlaram());
    this.setInspCompliance(inspection.getInspCompliance());
    this.setInspDate(inspection.getInspDateString());
    this.setInspDistrict(inspection.getInspDistrict());
    this.setInspFacId(inspection.getInspFacId());
    this.setInspId(inspection.getInspId());
    this.setInspInspectorId(inspection.getInspInspectorId());
    this.setInspOccLoad(inspection.getInspOccLoad());
    this.setInspPermitPrint(inspection.getInspPermitPrint());
    this.setInspRemarks(inspection.getInspRemarks());
    this.setInspSprinkler(inspection.getInspSprinkler());
    this.setInspStatus(inspection.getInspStatus());
    this.setInspType(inspection.getInspType());
    this.setInspVioPrintDate(inspection.getInspVioPrintDateString());
    this.setEditFlag(inspection.getEditFlag());
  }
    public FireInspection getFireInspection() 
    {   FireInspection inspection = new FireInspection();
      inspection.setInspAlaram(getInspAlaram());
      inspection.setInspCompliance(getInspCompliance());
      inspection.setInspDateString(getInspDate());
      inspection.setInspDistrict(getInspDistrict());
      inspection.setInspFacId(getInspFacId());
      inspection.setInspId(getInspId());
      inspection.setInspInspectorId(getInspInspectorId());
      inspection.setInspOccLoad(getInspOccLoad());
      inspection.setInspPermitPrint(getInspPermitPrint());
      inspection.setInspRemarks(getInspRemarks());
      inspection.setInspSprinkler(getInspSprinkler());
      inspection.setInspStatus(getInspStatus());
      inspection.setInspType(getInspType());
      inspection.setInspVioPrintDateString(getInspVioPrintDate());
      inspection.setEditFlag(getEditFlag());
      return inspection;
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
