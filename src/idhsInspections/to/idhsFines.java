package idhsInspections.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class idhsFines implements Serializable{
    private int fineId;
    private String facilityId;
    private String fineCreatedBy;
    private String fineEndedBy;
    private Date fineStartDate;
    private Date fineEndDate;
    private double fine;
    private int receiptNumber;
     public idhsFines() {
       
    }
  public Date getFineEndDate()
    { 
     return fineEndDate;
    }
  public void setFineEndDate(Date fineEndDate)
    { 
    this.fineEndDate = fineEndDate;
    }
  public Date getFineStartDate()
    { 
     return fineStartDate;
    }
  public void setFineStartDate(Date fineStartDate)
    { 
    this.fineStartDate = fineStartDate;
    }
  public String getFacilityId()
    { 
     return facilityId;
    }
  public void setFacilityId(String facilityId)
    { 
    this.facilityId = facilityId;
    }
  public String getFineCreatedBy()
    { 
     return fineCreatedBy;
    }
  public void setFineCreatedBy(String fineCreatedBy)
    { 
    this.fineCreatedBy = fineCreatedBy;
    }
  public String getFineEndedBy()
    { 
     return fineEndedBy;
    }
  public void setFineEndedBy(String fineEndedBy)
    { 
    this.fineEndedBy = fineEndedBy;
    }
  public int getFineId()
    { 
     return fineId;
    }
  public void setFineId(int fineId)
    { 
    this.fineId = fineId;
    }
  public double getFine()
    { 
     return fine;
    }
  public void setFine(double fine)
    { 
    this.fine = fine;
    }
  public void setFineEndDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  fineEndDate = formatter.parse(dateParam);
  } catch (Exception e)
  {fineEndDate = null; }
  }
  public String getFineEndDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(fineEndDate == null)
  { return ""; }
  else { return formatter.format(fineEndDate); }
  }
  public void setFineStartDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  fineStartDate = formatter.parse(dateParam);
  } catch (Exception e)
  {fineStartDate = null; }
  }
  public String getFineStartDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(fineStartDate == null)
  { return ""; }
  else { return formatter.format(fineStartDate); }
  }
  public int getReceiptNumber()
    { 
     return receiptNumber;
    }
  public void setReceiptNumber(int receiptNumber)
    { 
    this.receiptNumber = receiptNumber;
    }
}
