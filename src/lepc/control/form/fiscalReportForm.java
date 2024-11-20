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

public class fiscalReportForm extends ActionForm{
    public fiscalReportForm() {
        
    }
  private int fiscalId;
  private int lepcId;
  private double acctBalance;
  private double acctReceipts;
  private double acctReceiptsCurrent;
  private String reportBy;
  private String repDate;
  private String fiscalStatus;
  private double acctGrants;
  private double invBalance;
    private double amendCredit;
    private double amendDebit;
  public double getInvBalance()
    {    return invBalance;  }
  public void setInvBalance(double invBalance)
    {   this.invBalance = invBalance;  }
  public double getAcctGrants()
    {    return acctGrants;  }
  public void setAcctGrants(double acctGrants)
    {   this.acctGrants = acctGrants;  }
  public String getRepDate()
    {    return repDate;  }
  public void setRepDate(String repDate)
    {   this.repDate = repDate;  }
  public String getReportBy()
    {    return reportBy;  }
  public void setReportBy(String reportBy)
    {   this.reportBy = reportBy;  }
  public double getAcctBalance()
    {    return acctBalance;  }
  public void setAcctBalance(double acctBalance)
    {   this.acctBalance = acctBalance;  }
  public double getAcctReceipts()
    {    return acctReceipts;  }
  public void setAcctReceipts(double acctReceipts)
    {   this.acctReceipts = acctReceipts;  }
  public int getFiscalId()
    {    return fiscalId;  }
  public void setFiscalId(int fiscalId)
    {   this.fiscalId = fiscalId;  }
  public int getLepcId()
    {    return lepcId;  }
  public void setLepcId(int lepcId)
    {   this.lepcId = lepcId;  }
  public String getFiscalStatus()
    {    return fiscalStatus;  }
  public void setFiscalStatus(String fiscalStatus)
    {   this.fiscalStatus = fiscalStatus;  }
  public fiscalReport getFiscalReport() throws Exception
   {
     
     fiscalReport fiscalRep = new fiscalReport ();
     fiscalRep.setRepDateString(this.getRepDate());
     fiscalRep.setReportBy(this.getReportBy());
     fiscalRep.setFiscalStatus(this.getFiscalStatus());
     fiscalRep.setAcctBalance(this.getAcctBalance());
     fiscalRep.setAcctReceipts(this.getAcctReceipts());
     fiscalRep.setAcctReceiptsCurrent(this.getAcctReceiptsCurrent());
     fiscalRep.setFiscalId(this.getFiscalId());
     fiscalRep.setLepcId(this.getLepcId());
     fiscalRep.setAcctGrants(this.getAcctGrants());
     fiscalRep.setInvBalance(this.getInvBalance());
     fiscalRep.setAmendCredit(this.getAmendCredit());
     fiscalRep.setAmendDebit(this.getAmendDebit());
  return fiscalRep;
   }
    public void setProperties(fiscalReport fiscalRep) throws Exception
   { 
     this.setRepDate(fiscalRep.getRepDateString());
     this.setReportBy(fiscalRep.getReportBy());
     this.setFiscalStatus(fiscalRep.getFiscalStatus());
     this.setAcctBalance(fiscalRep.getAcctBalance());
     this.setAcctReceipts(fiscalRep.getAcctReceipts());
     this.setAcctReceiptsCurrent(fiscalRep.getAcctReceiptsCurrent());
     this.setFiscalId(fiscalRep.getFiscalId());
     this.setLepcId(fiscalRep.getLepcId());
     this.setAcctGrants(fiscalRep.getAcctGrants());
     this.setInvBalance(fiscalRep.getInvBalance());
     this.setAmendCredit(fiscalRep.getAmendCredit());
     this.setAmendDebit(fiscalRep.getAmendDebit());
   }
  public double getAcctReceiptsCurrent()
    {    return acctReceiptsCurrent;  }
  public void setAcctReceiptsCurrent(double acctReceiptsCurrent)
    {   this.acctReceiptsCurrent = acctReceiptsCurrent;  }

    public void setAmendCredit(double amendCredit) {
        this.amendCredit = amendCredit;
    }

    public double getAmendCredit() {
        return amendCredit;
    }

    public void setAmendDebit(double amendDebit) {
        this.amendDebit = amendDebit;
    }

    public double getAmendDebit() {
        return amendDebit;
    }
}
