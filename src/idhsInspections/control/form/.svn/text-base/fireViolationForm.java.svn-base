package idhsInspections.control.form;
import idhsInspections.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.action.ActionForm;

public class fireViolationForm extends ActionForm{
    private int vioId;
    private int vioOrder;
    private String vioYear;
    private String vioCode;
    private String vioDescription;
    private String vioRemarks;
    private String vioType;
    private String vioDate;
    private String correctionDate;
    private String vioCompDate;
    private int inspectionId;
    private int compDays;
    private String stdViolation;
    public fireViolationForm() {
    }
    public int getInspectionId()
     { 
      return inspectionId;
     }
    public void setInspectionId(int inspectionId)
     { 
     this.inspectionId = inspectionId;
     }
        public int getCompDays()
         { 
          return compDays;
         }
        public void setCompDays(int compDays)
         { 
         this.compDays = compDays;
         }
     public String getVioCompDate()
     { 
      return vioCompDate;
     }
    public void setVioCompDate(String vioCompDate)
     { 
     this.vioCompDate = vioCompDate;
     }
    public String getVioDate()
     { 
      return vioDate;
     }
    public void setVioDate(String vioDate)
     { 
     this.vioDate = vioDate;
     }
        public String getCorrectionDate()
         { 
          return correctionDate;
         }
        public void setCorrectionDate(String correctionDate)
         { 
         this.correctionDate = correctionDate;
         }
    public String getVioCode()
     { 
      return vioCode;
     }
    public void setVioCode(String vioCode)
     { 
     this.vioCode = vioCode;
     }
        public String getVioType()
         { 
          return vioType;
         }
        public void setVioType(String vioType)
         { 
         this.vioType = vioType;
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
    public void setVioYear(String vioYear) {
        this.vioYear = vioYear;
    }

    public String getVioYear() {
        return vioYear;
    }
    public fireViolation getfireViolation() 
        {   fireViolation violation = new fireViolation();
            violation.setVioCode(getVioCode());
            violation.setVioYear(getVioYear());
            violation.setVioType(getVioType());
            violation.setCompDays(getCompDays());
            violation.setVioDateString(getVioDate());
            violation.setVioCompDateString(getVioCompDate());
            violation.setCorrectionDateString(getCorrectionDate());
            violation.setVioDescription(getVioDescription());
            violation.setVioId(getVioId());
            violation.setVioOrder(getVioOrder());
            violation.setVioRemarks(getVioRemarks());
            violation.setInspectionId(getInspectionId());
          return violation;
        }
         public void setProperties(fireViolation violation) 
      {  
          this.setVioCode(violation.getVioCode());
          this.setVioYear(violation.getVioYear());
          this.setVioCompDate(violation.getVioCompDateString());
          this.setVioDate(violation.getVioDateString());
          this.setCorrectionDate(violation.getCorrectionDateString());
          this.setVioDescription(violation.getVioDescription());
          this.setVioId(violation.getVioId());
          this.setVioOrder(violation.getVioOrder());
          this.setVioType(violation.getVioType());
          this.setCompDays(violation.getCompDays());
          this.setVioRemarks(violation.getVioRemarks());
          this.setInspectionId(violation.getInspectionId());
      }
      
      public String getStdViolation()
      { 
       return stdViolation;
      }
    public void setStdViolation(String stdViolation)
      { 
      this.stdViolation = stdViolation;
      }

  
}
