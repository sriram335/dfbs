package planReview.to;
import java.util.*;
import java.text.*;
import java.io.Serializable;

public class PlanReview implements Serializable
{ private List fileList;
  private String idNumber;
  private String idType;
   private String submittedBy;
  private String planType;
    public PlanReview() {
    }
    public List getFileList()
      {
        if(fileList == null) 
        {
          fileList = new ArrayList();
        } 
        return fileList;
      }

      public void setFileList(List fileList)
      {
        this.fileList = fileList;
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
    }
