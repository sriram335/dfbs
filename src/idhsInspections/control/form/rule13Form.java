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
public class rule13Form extends ActionForm{
    private int rule13Id;
    private int rule13SeqId;
    private String rule13Remarks;
    private String rule13Description;
    private int inspectionId;
    private String id1;
    
    public rule13Form() {
    }
    public int getInspectionId()
     { 
      return inspectionId;
     }
    public void setInspectionId(int inspectionId)
     { 
     this.inspectionId = inspectionId;
     }
    public String getId1()
     { 
      return id1;
     }
    public void setId(String id1)
     { 
     this.id1 = id1;
     }
    
    public String getRule13Remarks()
     { 
      return rule13Remarks;
     }
    public void setRule13Remarks(String rule13Remarks)
     { 
     this.rule13Remarks = rule13Remarks;
     }
       
    public String getRule13Description()
     { 
      return rule13Description;
     }
    public void setRule13Description(String rule13Description)
     { 
     this.rule13Description = rule13Description;
     }
    
    public int getRule13Id()
     { 
      return rule13Id;
     }
    public void setRule13Id(int rule13Id)
     { 
     this.rule13Id = rule13Id;
     }
    public int getRule13SeqId()
     { 
      return rule13SeqId;
     }
    public void setRule13SeqId(int rule13SeqId)
     { 
     this.rule13SeqId = rule13SeqId;
     }
    public rule13 getRule13() 
        {   rule13 rule =new rule13();
            rule.setRule13Description(this.getRule13Description());
            rule.setRule13Remarks(this.getRule13Remarks());
            rule.setInspectionId(this.getInspectionId());
            rule.setRule13Id(this.getRule13Id());
            rule.setRule13SeqId(this.getRule13SeqId());
          return rule;
        }
         public void setProperties(rule13 rule) 
      {  
          this.setInspectionId(rule.getInspectionId());
          this.setRule13Id(rule.getRule13Id());
          this.setRule13Description(rule.getRule13Description());
          this.setRule13Remarks(rule.getRule13Remarks());
          this.setRule13SeqId(rule.getRule13SeqId());
      }
      
}
