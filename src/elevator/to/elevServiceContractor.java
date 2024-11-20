package elevator.to;
import hs.to.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;
public class elevServiceContractor  implements Serializable{
  private int contId ;
  private String licNumber;
  private String contType; 
  private String contFirstName;
  private String contLastName;
  private String contMI;
  private String contAddress1;
  private String contAddress2;
  private String contCity;
  private String contState;
  private String contZip;
  private String contZip2;
  private String contPhone;
  private String contFax;
  private String contEmail;
  private String contLicStatus;
  private Double contFee;
  private Date contIssueDate;
  private Date contExpDate;
  private Date contLetterDate;
  private Date contPrintDate;
  private int contComplaints;
  private int contViolations;
  private int contConvictions;
  private int contParentId;
  private List fileList;
  private int receiptId;
  private String checkoutId;
  
 
    public elevServiceContractor() {
       
    }
  public String getCheckoutId()
  {
   return checkoutId;
  }

  public void setCheckoutId(String checkoutId)
  {
   this.checkoutId = checkoutId;
  } 
  public String getContAddress1()
    {    return contAddress1;  }
  public void setContAddress1(String contAddress1)
    {   this.contAddress1 = contAddress1;  }
  public String getContAddress2()
    {    return contAddress2;  }
  public void setContAddress2(String contAddress2)
    {   this.contAddress2 = contAddress2;  }
  public String getContCity()
    {    return contCity;  }
  public void setContCity(String contCity)
    {   this.contCity = contCity;  }
  public String getContFirstName()
    {    return contFirstName;  }
  public void setContFirstName(String contFirstName)
    {   this.contFirstName = contFirstName;  }
  public String getContLastName()
    {    return contLastName;  }
  public void setContLastName(String contLastName)
    {   this.contLastName = contLastName;  }
  public String getContMI()
    {    return contMI;  }
  public void setContMI(String contMI)
    {   this.contMI = contMI;  }
  public String getContState()
    {    return contState;  }
  public void setContState(String contState)
    {   this.contState = contState;  }
  public String getContType()
    {    return contType;  }
  public void setContType(String contType)
    {   this.contType = contType;  }
  public String getContZip()
    {    return contZip;  }
  public void setContZip(String contZip)
    {   this.contZip = contZip;  }
  public String getLicNumber()
    {    return licNumber;  }
  public void setLicNumber(String licNumber)
    {   this.licNumber = licNumber;  }
  public int getContId ()
    {    return contId ;  }
  public void setContId (int contId )
    {   this.contId  = contId ;  }
  public Date getContExpDate()
    {    return contExpDate;  }
  public void setContExpDate(Date contExpDate)
    {   this.contExpDate = contExpDate;  }
  public Date getContIssueDate()
    {    return contIssueDate;  }
  public void setContIssueDate(Date contIssueDate)
    {   this.contIssueDate = contIssueDate;  }
  public Date getContLetterDate()
    {    return contLetterDate;  }
  public void setContLetterDate(Date contLetterDate)
    {   this.contLetterDate = contLetterDate;  }
  public Date getContPrintDate()
    {    return contPrintDate;  }
  public void setContPrintDate(Date contPrintDate)
    {   this.contPrintDate = contPrintDate;  }
  public Double getContFee()
    {    return contFee;  }
  public void setContFee(Double contFee)
    {   this.contFee = contFee;  }
  public String getContEmail()
    {    return contEmail;  }
  public void setContEmail(String contEmail)
    {   this.contEmail = contEmail;  }
  public String getContFax()
    {    return contFax;  }
  public void setContFax(String contFax)
    {   this.contFax = contFax;  }
  public String getContLicStatus()
    {    return contLicStatus;  }
  public void setContLicStatus(String contLicStatus)
    {   this.contLicStatus = contLicStatus;  }
  public String getContPhone()
    {    return contPhone;  }
  public void setContPhone(String contPhone)
    {   this.contPhone = contPhone;  }
  public String getContZip2()
    {    return contZip2;  }
  public void setContZip2(String contZip2)
    {   this.contZip2 = contZip2;  }
  public int getContComplaints()
    {    return contComplaints;  }
  public void setContComplaints(int contComplaints)
    {   this.contComplaints = contComplaints;  }
  public int getContConvictions()
    {    return contConvictions;  }
  public void setContConvictions(int contConvictions)
    {   this.contConvictions = contConvictions;  }
  public int getContParentId()
    {    return contParentId;  }
  public void setContParentId(int contParentId)
    {   this.contParentId = contParentId;  }
  public int getContViolations()
    {    return contViolations;  }
  public void setContViolations(int contViolations)
    {   this.contViolations = contViolations;  }
  public void setContExpDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  contExpDate = formatter.parse(dateParam);
  } catch (Exception e)
  {contExpDate = null; }
  }
  public String getContExpDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(contExpDate == null)
  { return ""; }
  else { return formatter.format(contExpDate); }
  }
  public void setContIssueDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  contIssueDate = formatter.parse(dateParam);
  } catch (Exception e)
  {contIssueDate = null; }
  }
  public String getContIssueDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(contIssueDate == null)
  { return ""; }
  else { return formatter.format(contIssueDate); }
  }
  public void setContLetterDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  contLetterDate = formatter.parse(dateParam);
  } catch (Exception e)
  {contLetterDate = null; }
  }
  public String getContLetterDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(contLetterDate == null)
  { return ""; }
  else { return formatter.format(contLetterDate); }
  }
  public void setContPrintDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  contPrintDate = formatter.parse(dateParam);
  } catch (Exception e)
  {contPrintDate = null; }
  }
  public String getContPrintDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(contPrintDate == null)
  { return ""; }
  else { return formatter.format(contPrintDate); }
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
  public int getFileCount()
  {
    return (this.fileList == null) ? 0 : fileList.size();
  }
  public int getReceiptId()
    { 
     return receiptId;
    }
  public void setReceiptId(int receiptId)
    { 
    this.receiptId = receiptId;
    } 
}
