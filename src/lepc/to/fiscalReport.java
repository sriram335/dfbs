package lepc.to;
import java.io.Serializable;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class fiscalReport implements Serializable{
    public fiscalReport() {
       
    }
    private int fiscalId;
    private int lepcId;
    private double acctBalance;
    private double acctReceipts;
    private double acctReceiptsCurrent;
    private String reportBy;
    private Date repDate;
    private String fiscalStatus;
    private double acctGrants;
    private double invBalance;
  private double curAcctExps;
  private double curAcctBalance;
    private double amendCredit;
    private double amendDebit;
  public Date getRepDate()
    {    return repDate;  }
  public void setRepDate(Date repDate)
    {   this.repDate = repDate;  }
  public String getReportBy()
    {    return reportBy;  }
  public void setReportBy(String reportBy)
    {   this.reportBy = reportBy;  }
  public String getFiscalStatus()
    {    return fiscalStatus;  }
  public void setFiscalStatus(String fiscalStatus)
    {   this.fiscalStatus = fiscalStatus;  }
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
  public double getAcctGrants()
    {    return acctGrants;  }
  public void setAcctGrants(double acctGrants)
    {   this.acctGrants = acctGrants;  }
  public double getInvBalance()
    {    return invBalance;  }
  public void setInvBalance(double invBalance)
    {   this.invBalance = invBalance;  }
  public void setRepDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  repDate = formatter.parse(dateParam);
  } catch (Exception e)
  {repDate = null; }
  }
  public String getRepDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(repDate == null)
  { return ""; }
  else { return formatter.format(repDate); }
  }
  public double getCurAcctExps()
    {    return curAcctExps;  }
  public void setCurAcctExps(double curAcctExps)
    {   this.curAcctExps = curAcctExps;  }
  public double getCurAcctBalance()
    {    return  curAcctBalance;  }
  public void setCurAcctBalance(double  curAcctBalance)
    {   this. curAcctBalance =  curAcctBalance;  }
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
