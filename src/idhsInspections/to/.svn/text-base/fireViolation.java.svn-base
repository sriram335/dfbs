package idhsInspections.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class fireViolation implements Serializable {
      private int vioId;
      private int vioOrder;
      private String vioCode;
      private String vioYear;
      private String vioDescription;
      private String vioRemarks;
      private String vioType;
      private Date vioDate;
      private Date correctionDate;
      private Date vioCompDate;
      private int inspectionId;
      private int compDays;
    public fireViolation() {
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
     public Date getVioCompDate()
     { 
      return vioCompDate;
     }
    public void setVioCompDate(Date vioCompDate)
     { 
     this.vioCompDate = vioCompDate;
     }
    public Date getVioDate()
     { 
      return vioDate;
     }
    public void setVioDate(Date vioDate)
     { 
     this.vioDate = vioDate;
     }
        public Date getCorrectionDate()
         { 
          return correctionDate;
         }
        public void setCorrectionDate(Date correctionDate)
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
    public void setVioDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    vioDate = formatter.parse(dateParam);
    } catch (Exception e)
    {vioDate = null; }
    }
    public String getVioDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(vioDate == null)
    { return ""; }
    else { return formatter.format(vioDate); }
    }
    
        public void setCorrectionDateString(String dateParam)
        {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
        correctionDate = formatter.parse(dateParam);
        } catch (Exception e)
        {correctionDate = null; }
        }
        public String getCorrectionDateString()
        {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        if(correctionDate == null)
        { return ""; }
        else { return formatter.format(correctionDate); }
        }
         public boolean isNew() 
         {
           return (getVioId() == 0) ? true : false;
         }

    public void setVioYear(String vioYear) {
        this.vioYear = vioYear;
    }

    public String getVioYear() {
        return vioYear;
    }
}
