package codeEducation.control.form;

import hs.control.form.*;
import hs.to.*;
import codeEducation.to.*;
import java.util.Map;
import org.apache.struts.action.*;
import java.util.Date;
public class CodeEduPlanForm extends ActionForm{
    private int codeEduId;
    private int codePersonId;
    private String eduDescription;
    private String eduType;
    private String eduStatus;
    public CodeEduPlanForm() {
    }
    public String getEduDescription()
      { 
       return eduDescription;
      }
    public void setEduDescription(String eduDescription)
      { 
      this.eduDescription = eduDescription;
      }
    public String getEduStatus()
      { 
       return eduStatus;
      }
    public void setEduStatus(String eduStatus)
      { 
      this.eduStatus = eduStatus;
      }
    public String getEduType()
      { 
       return eduType;
      }
    public void setEduType(String eduType)
      { 
      this.eduType = eduType;
      }
    public int getCodeEduId()
      { 
       return codeEduId;
      }
    public void setCodeEduId(int codeEduId)
      { 
      this.codeEduId = codeEduId;
      }
    public int getCodePersonId()
      { 
       return codePersonId;
      }
    public void setCodePersonId(int codePersonId)
      { 
      this.codePersonId = codePersonId;
      }
    public CodeEducationPlan  getCodePlan()
    {
    CodeEducationPlan codePlan =new CodeEducationPlan();
        codePlan.setEduDescription(this.getEduDescription());
        codePlan.setEduStatus(this.getEduStatus());
        codePlan.setEduType(this.getEduType());
        codePlan.setCodeEduId(this.getCodeEduId());
        codePlan.setCodePersonId(this.getCodePersonId());
    return codePlan;
    }
    public void setProperties(CodeEducationPlan codePlan) 
    {
        this.setEduDescription(codePlan.getEduDescription());
        this.setEduStatus(codePlan.getEduStatus());
        this.setEduType(codePlan.getEduType());
        this.setCodeEduId(codePlan.getCodeEduId());
        this.setCodePersonId(codePlan.getCodePersonId());
    }
}
