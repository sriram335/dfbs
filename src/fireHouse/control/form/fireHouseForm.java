package fireHouse.control.form;
import hs.control.form.*;
import hs.to.*;
import java.util.Map;
import org.apache.struts.action.*;
import java.util.Date;
import org.apache.struts.upload.FormFile;

public class fireHouseForm extends ActionForm{
    public fireHouseForm() {
      
    }
  private transient FormFile caseFile = null;//get & set methods
  private String certNumber;
  private String email;
  private String comments;
  private String startDate;
  private String endDate;
  public FormFile getCaseFile()
  {
  return caseFile;
  }
  public void setCaseFile( FormFile caseFile)
  {
  this.caseFile = caseFile;
  }
  public String getCertNumber()
  { 
   return certNumber;
  }
  public void setCertNumber(String certNumber)
  { 
  this.certNumber = certNumber;
  }
  public String getEmail()
  { 
   return email;
  }
  public void setEmail(String email)
  { 
  this.email = email;
  }
  public String getComments()
  { 
   return comments;
  }
  public void setComments(String comments)
  { 
  this.comments = comments;
  }
  public String getStartDate()
  { 
   return startDate;
  }
  public void setStartDate(String startDate)
  { 
  this.startDate = startDate;
  }
  public String getEndDate()
  { 
   return endDate;
  }
  public void setEndDate(String endDate)
  { 
  this.endDate = endDate;
  }
}
