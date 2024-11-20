package Variance.control.form;
import hs.to.*;
import java.util.Map;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import Variance.to.*;

import java.util.Date;

public class varCodeDetailForm  extends ActionForm{
  private int varCodeId;
   private int varId;
   private String varEdition;
   private String varCode;
   private String varCodeName;
   private String varNatureNonComp;
   private String varCommStatus;
   private String varCommDate;
   private String varCommPrintDate;
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
    public varCodeDetailForm() {
        
    }
  public String getVarCommDate()
    {    return varCommDate;  }
  public void setVarCommDate(String varCommDate)
    {   this.varCommDate = varCommDate;  }
  public String getVarCommPrintDate()
    {    return varCommPrintDate;  }
  public void setVarCommPrintDate(String varCommPrintDate)
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
  public void setProperties(VarCodeDetail code)
  {
    this.setVarCodeId(code.getVarCodeId());
    this.setVarId(code.getVarId());
    this.setVarCode(code.getVarCode());
    this.setVarCodeName(code.getVarCodeName());
    this.setVarCommDate(code.getVarCommDateString());
    this.setVarCommPrintDate(code.getVarCommPrintDateString());
    this.setVarCommStatus(code.getVarCommStatus());
    this.setVarEdition(code.getVarEdition());
    this.setVarNatureNonComp(code.getVarNatureNonComp());
    this.setVarAppHistAffExc(code.getVarAppHistAffExc());
    this.setVarAppHistAffHist(code.getVarAppHistAffHist());
    this.setVarAppHistAffMaj(code.getVarAppHistAffMaj());
    this.setVarAppHistAffPhy(code.getVarAppHistAffPhy());
    this.setVarAppHistAffPhyComment(code.getVarAppHistAffPhyComment());
    this.setVarAppPhAffirm(code.getVarAppPhAffirm());
    this.setVarAppPhAffirmComment(code.getVarAppPhAffirmComment());
    this.setStaffComments(code.getStaffComments());
    this.setStaffCommentsRec(code.getStaffCommentsRec());
    this.setVarReleaseFlag(code.getVarReleaseFlag());
    this.setVarReleaseFlag(code.getVarReleaseFlag());
    this.setVarAppStaffCommConditions(code.getVarAppStaffCommConditions());
  }
  
  public VarCodeDetail getVarCodeDetail() 
  {
   VarCodeDetail code = new VarCodeDetail();
    code.setVarCodeId(this.getVarCodeId());
    code.setVarId(this.getVarId());
    code.setVarCode(this.getVarCode());
    code.setVarCodeName(this.getVarCodeName());
    code.setVarCommDateString(this.getVarCommDate());
    code.setVarCommPrintDateString(this.getVarCommPrintDate());
    code.setVarCommStatus(this.getVarCommStatus());
    code.setVarEdition(this.getVarEdition());
    code.setVarNatureNonComp(this.getVarNatureNonComp());
    code.setVarAppHistAffExc(this.getVarAppHistAffExc());
    code.setVarAppHistAffHist(this.getVarAppHistAffHist());
    code.setVarAppHistAffMaj(this.getVarAppHistAffMaj());
    code.setVarAppHistAffPhy(this.getVarAppHistAffPhy());
    code.setVarAppHistAffPhyComment(this.getVarAppHistAffPhyComment());
    code.setVarAppPhAffirm(this.getVarAppPhAffirm());
    code.setVarAppPhAffirmComment(this.getVarAppPhAffirmComment());
    code.setStaffComments(this.getStaffComments());
    code.setStaffCommentsRec(this.getStaffCommentsRec());
    code.setVarReleaseFlag(this.getVarReleaseFlag());
    code.setVarAppStaffCommConditions(this.getVarAppStaffCommConditions());
   return code;
  }
  
  public void setUpdatedProperties(VarCodeDetail updatedCode,VarCodeDetail code) throws Exception
  {
  
    code.setVarCodeId(updatedCode.getVarCodeId());
    code.setVarId(updatedCode.getVarId());
    code.setVarCode(updatedCode.getVarCode());
    code.setVarCodeName(updatedCode.getVarCodeName());
    code.setVarCommDate(updatedCode.getVarCommDate());
    code.setVarCommPrintDate(updatedCode.getVarCommPrintDate());
    code.setVarCommStatus(updatedCode.getVarCommStatus());
    code.setVarEdition(updatedCode.getVarEdition());
    code.setVarNatureNonComp(updatedCode.getVarNatureNonComp());
    code.setVarAppHistAffExc(updatedCode.getVarAppHistAffExc());
    code.setVarAppHistAffHist(updatedCode.getVarAppHistAffHist());
    code.setVarAppHistAffMaj(updatedCode.getVarAppHistAffMaj());
    code.setVarAppHistAffPhy(updatedCode.getVarAppHistAffPhy());
    code.setVarAppHistAffPhyComment(updatedCode.getVarAppHistAffPhyComment());
    code.setVarAppPhAffirm(updatedCode.getVarAppPhAffirm());
    code.setVarAppPhAffirmComment(updatedCode.getVarAppPhAffirmComment());
    code.setStaffComments(updatedCode.getStaffComments());
    code.setStaffCommentsRec(updatedCode.getStaffCommentsRec());
    code.setVarReleaseFlag(updatedCode.getVarReleaseFlag());
    code.setVarAppStaffCommConditions(updatedCode.getVarAppStaffCommConditions());
      
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
