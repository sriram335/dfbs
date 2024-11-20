package Variance.to;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import hs.to.*;
public class VarCodeDetail implements Serializable{
   private int varCodeId;
    private int varId;
    private String varEdition;
    private String varCode;
    private String varCodeName;
    private String varNatureNonComp;
    private String varCommStatus;
    private Date varCommDate;
    private Date varCommPrintDate;
    private String codeKey;
  private String varAppPhAffirm;
  private String varAppPhAffirmComment;
  private String varAppHistAffPhy;
  private String varAppHistAffPhyComment;
  private String varAppHistAffMaj;
  private String varAppHistAffExc;
  private String varAppHistAffHist;
  private String staffComments;
  private String staffCommentsRec;
  private String varReleaseFlag;
  private String varAppStaffCommConditions;
    public VarCodeDetail() {
        
    }
  public Date getVarCommDate()
    {    return varCommDate;  }
  public void setVarCommDate(Date varCommDate)
    {   this.varCommDate = varCommDate;  }
  public Date getVarCommPrintDate()
    {    return varCommPrintDate;  }
  public void setVarCommPrintDate(Date varCommPrintDate)
    {   this.varCommPrintDate = varCommPrintDate;  }
  public int getVarCodeId()
    {    return varCodeId;  }
  public void setVarCodeId(int varCodeId)
    {   this.varCodeId = varCodeId;  }
  public int getVarId()
    {    return varId;  }
  public void setVarId(int varId)
    {   this.varId = varId;  }
  public String getVarCode()
    {    return varCode;  }
  public void setVarCode(String varCode)
    {   this.varCode = varCode;  }
  public String getVarCodeName()
    {    return varCodeName;  }
  public void setVarCodeName(String varCodeName)
    {   this.varCodeName = varCodeName;  }
  public String getVarCommStatus()
    {    return varCommStatus;  }
  public void setVarCommStatus(String varCommStatus)
    {   this.varCommStatus = varCommStatus;  }
  public String getVarEdition()
    {    return varEdition;  }
  public void setVarEdition(String varEdition)
    {   this.varEdition = varEdition;  }
  public String getVarNatureNonComp()
    {    return varNatureNonComp;  }
  public void setVarNatureNonComp(String varNatureNonComp)
    {   this.varNatureNonComp = varNatureNonComp;  }

public void setVarCommDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
varCommDate = formatter.parse(dateParam);
} catch (Exception e)
{varCommDate = null; }
}
public String getVarCommDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(varCommDate == null)
{ return ""; }
else { return formatter.format(varCommDate); }
}
public void setVarCommPrintDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
varCommPrintDate = formatter.parse(dateParam);
} catch (Exception e)
{varCommPrintDate = null; }
}
public String getVarCommPrintDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(varCommPrintDate == null)
{ return ""; }
else { return formatter.format(varCommPrintDate); }
}
  public String getCodeKey()
  {
    return codeKey;
  }

  public void setCodeKey(String codeKey)
  {
    this.codeKey = codeKey;
  }
  public String getVarAppHistAffExc()
    {    return varAppHistAffExc;  }
  public void setVarAppHistAffExc(String varAppHistAffExc)
    {   this.varAppHistAffExc = varAppHistAffExc;  }
  public String getVarAppHistAffHist()
    {    return varAppHistAffHist;  }
  public void setVarAppHistAffHist(String varAppHistAffHist)
    {   this.varAppHistAffHist = varAppHistAffHist;  }
  public String getVarAppHistAffMaj()
    {    return varAppHistAffMaj;  }
  public void setVarAppHistAffMaj(String varAppHistAffMaj)
    {   this.varAppHistAffMaj = varAppHistAffMaj;  }
  public String getVarAppHistAffPhy()
    {    return varAppHistAffPhy;  }
  public void setVarAppHistAffPhy(String varAppHistAffPhy)
    {   this.varAppHistAffPhy = varAppHistAffPhy;  }
  public String getVarAppHistAffPhyComment()
    {    return varAppHistAffPhyComment;  }
  public void setVarAppHistAffPhyComment(String varAppHistAffPhyComment)
    {   this.varAppHistAffPhyComment = varAppHistAffPhyComment;  }
  public String getVarAppPhAffirm()
    {    return varAppPhAffirm;  }
  public void setVarAppPhAffirm(String varAppPhAffirm)
    {   this.varAppPhAffirm = varAppPhAffirm;  }
  public String getVarAppPhAffirmComment()
    {    return varAppPhAffirmComment;  }
  public void setVarAppPhAffirmComment(String varAppPhAffirmComment)
    {   this.varAppPhAffirmComment = varAppPhAffirmComment;  }
  public String getStaffComments()
    {    return staffComments;  }
  public void setStaffComments(String staffComments)
    {   this.staffComments = staffComments;  }
  public String getStaffCommentsRec()
    {    return staffCommentsRec;  }
  public void setStaffCommentsRec(String staffCommentsRec)
    {   this.staffCommentsRec = staffCommentsRec;  }
  public String getVarReleaseFlag()
    {    return varReleaseFlag;  }
  public void setVarReleaseFlag(String varReleaseFlag)
    {   this.varReleaseFlag = varReleaseFlag;  }

    public void setVarAppStaffCommConditions(String varAppStaffCommConditions) {
        this.varAppStaffCommConditions = varAppStaffCommConditions;
    }

    public String getVarAppStaffCommConditions() {
        return varAppStaffCommConditions;
  
    }
}
