package elevator.control.form;
import elevator.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
public class elevViolationForm extends ActionForm{
      private int vioId;
      private int vioOrder;
      private String vioCode;
      private String vioDescription;
      private String vioRemarks;
      private String vioCorDate;
      private String vioCompDate;
      private int inspectionId;
      private String stdViolation;
    public elevViolationForm() {
    }
    public String getVioCompDate()
     { 
      return vioCompDate;
     }
    public void setVioCompDate(String  vioCompDate)
     { 
     this.vioCompDate = vioCompDate;
     }
    public String getVioCorDate()
     { 
      return vioCorDate;
     }
    public void setVioCorDate(String  vioCorDate)
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
     
    
     public int getInspectionId()
     { 
      return inspectionId;
     }
    public void setInspectionId(int inspectionId)
     { 
     this.inspectionId = inspectionId;
     }
    public elevViolation getFireViolation() 
       {   elevViolation violation = new elevViolation();
           violation.setVioCode(getVioCode());
           violation.setVioCompDateString(getVioCompDate());
           violation.setVioCorDateString(getVioCorDate());
           violation.setVioDescription(getVioDescription());
           violation.setVioId(getVioId());
           violation.setVioOrder(getVioOrder());
           violation.setVioRemarks(getVioRemarks());
           violation.setInspectionId(getInspectionId());
         return violation;
       }
        public void setProperties(elevViolation violation) 
     {  
         this.setVioCode(violation.getVioCode());
         this.setVioCompDate(violation.getVioCompDateString());
         this.setVioCorDate(violation.getVioCorDateString());
         this.setVioDescription(violation.getVioDescription());
         this.setVioId(violation.getVioId());
         this.setVioOrder(violation.getVioOrder());
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
