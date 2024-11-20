package planReview.control.form;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
public class PlanReviewForm extends ActionForm
{ 
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String idNumber;
  private String idType;
  private String submittedBy;
  private String planType;
  private String projStreetNumber;
  private String projCity;
    public PlanReviewForm() {
    }
    
    public FormFile getCaseFile()
     { 
      return caseFile;
     }
    public void setCaseFile( FormFile caseFile)
     { 
     this.caseFile = caseFile;
     } 
      public String getXlName()
     { 
      return xlName;
     }
    public void setXlName(String xlName)
     { 
     this.xlName = xlName;
     } 
      public String getIdNumber()
     { 
      return idNumber;
     }
    public void setIdNumber(String idNumber)
     { 
     this.idNumber = idNumber;
     }
      public String getIdType()
     { 
      return idType;
     }
    public void setIdType(String idType)
     { 
     this.idType = idType;
     }
      public String getSubmittedBy()
     { 
      return submittedBy;
     }
    public void setSubmittedBy(String submittedBy)
     { 
     this.submittedBy = submittedBy;
     }
      public String getPlanType()
     { 
      return planType;
     }
    public void setPlanType(String  planType)
     { 
     this.planType =  planType;
     }
      public String getProjStreetNumber()
     { 
      return projStreetNumber;
     }
    public void setProjStreetNumber(String  projStreetNumber)
     { 
     this.projStreetNumber =  projStreetNumber;
     }
      public String getProjCity()
     { 
      return projCity;
     }
    public void setProjCity(String  projCity)
     { 
     this.projCity =  projCity;
     }
    }
