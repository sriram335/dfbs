package lepc.control.form;
import lepc.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;

import org.apache.struts.upload.FormFile;
import org.apache.struts.action.*;

public class lepcForm extends ActionForm{
    public lepcForm() {
       
    }
  private int lepcId;
  private String lepcCounty;
  private String lepcYear;
  private String lepcStatus;
  private String lepcApprovedBy;
  private String lepcApprovedDate;
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String lepcDocType;
  private String lepcNotes;
  public String getLepcNotes()
    {    return lepcNotes;  }
  public void setLepcNotes(String lepcNotes)
    {   this.lepcNotes = lepcNotes;  }
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
  public String getLepcApprovedDate()
    {    return lepcApprovedDate;  }
  public void setLepcApprovedDate(String lepcApprovedDate)
    {   this.lepcApprovedDate = lepcApprovedDate;  }
  public String getLepcDocType()
    {    return lepcDocType;  }
  public void setLepcDocType(String lepcDocType)
    {   this.lepcDocType = lepcDocType;  }
  public String getLepcYear()
    {    return lepcYear;  }
  public void setLepcYear(String lepcYear)
    {   this.lepcYear = lepcYear;  }
  public int getLepcId()
    {    return lepcId;  }
  public void setLepcId(int lepcId)
    {   this.lepcId = lepcId;  }
  public String getLepcApprovedBy()
    {    return lepcApprovedBy;  }
  public void setLepcApprovedBy(String lepcApprovedBy)
    {   this.lepcApprovedBy = lepcApprovedBy;  }
  public String getLepcCounty()
    {    return lepcCounty;  }
  public void setLepcCounty(String lepcCounty)
    {   this.lepcCounty = lepcCounty;  }
  public String getLepcStatus()
    {    return lepcStatus;  }
  public void setLepcStatus(String lepcStatus)
    {   this.lepcStatus = lepcStatus;  }
  
  public LepcYear getLepcYearData() throws Exception
   {
     
     LepcYear lepc = new LepcYear ();
     lepc.setLepcApprovedBy(this.getLepcApprovedBy());
     lepc.setLepcApprovedDateString(this.getLepcApprovedDate());
     lepc.setLepcCounty(this.getLepcCounty());
     lepc.setLepcStatus(this.getLepcStatus());
     lepc.setLepcYearString(this.getLepcYear());
     lepc.setLepcId(this.getLepcId());
     lepc.setLepcNotes(this.getLepcNotes());
  return lepc;
   }
    public void setProperties(LepcYear lepc) throws Exception
   { 
     this.setLepcApprovedBy(lepc.getLepcApprovedBy());
     this.setLepcApprovedDate(lepc.getLepcApprovedDateString());
     this.setLepcCounty(lepc.getLepcCounty());
     this.setLepcStatus(lepc.getLepcStatus());
     this.setLepcYear(lepc.getLepcYearString());
     this.setLepcId(lepc.getLepcId());
     this.setLepcNotes(lepc.getLepcNotes());
   }
}
