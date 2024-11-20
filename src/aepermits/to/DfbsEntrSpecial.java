package aepermits.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
public class DfbsEntrSpecial extends HsCompleteContact implements Serializable
{
  private int specialId;
  private Date applicationDate;
  private String eventName;
  private String eventTime;
  private Date eventDate;
  private String maximumOccupancy;
  private String comments;
  private String permitNumber;
  private Date issueDate;
  private Date expirationDate;
  private String feeExempt;
  private String applicationKey;
  private double amount;
  private String contactPerson;
  private String contactPhone;
  private String contactFax;
  private String contactEmail;
  private String onlineStatus;
  private String noMap;
  private List fileList;
  private String permitType;
  public DfbsEntrSpecial()
  {
  }
   public String getPermitType()
  {
    return permitType;
  }

  public void setPermitType(String permitType)
  {
    this.permitType = permitType;
  }
  public int getSpecialId() 
  {
    return specialId;
  }
  public void setSpecialId(int specialId) 
  {
    this.specialId = specialId;
  }
  public Date getApplicationDate()
  {
    return applicationDate;
  }

  public void setApplicationDate(Date applicationDate)
  {
    this.applicationDate = applicationDate;
  }
 public Date getEventDate()
  {
    return eventDate;
  }

  public void setEventDate(Date eventDate)
  {
    this.eventDate = eventDate;
  }
  

  
   public void setApplicationDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      applicationDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      applicationDate = null;
    }
  }
   public String getApplicationDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(applicationDate == null) 
    {
      return "";
    } else {
      return formatter.format(applicationDate);
    }
  }

  public Date getIssueDate()
  {
    return issueDate;
  }

  public void setIssueDate(Date issueDate)
  {
    this.issueDate = issueDate;
  }
  
   public void setIssueDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      issueDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      issueDate = null;
    }
  }
   public String getIssueDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(issueDate == null) 
    {
      return "";
    } else {
      return formatter.format(issueDate);
    }
  }

 

  public String getPermitNumber()
  {
    return permitNumber;
  }

  public void setPermitNumber(String permitNumber)
  {
    this.permitNumber = permitNumber;
  }
  public String getEventName()
  {
    return eventName;
  }

  public void setEventName(String eventName)
  {
    this.eventName = eventName;
   }
   public String getEventTime()
  {
    return eventTime;
  }

  public void setEventTime(String eventTime)
  {
    this.eventTime = eventTime;
  }
    public String getMaximumOccupancy()
  {
    return maximumOccupancy;
  }

  

  public void setMaximumOccupancy(String maximumOccupancy)
  {
    this.maximumOccupancy = maximumOccupancy;
  }
    public String getComments()
  {
    return comments;
  }

  

  public void setComments(String comments)
  {
    this.comments = comments;
  }
    public String getFeeExempt()
  {
    return feeExempt;
  }

  public void setFeeExempt(String feeExempt)
  {
    this.feeExempt = feeExempt;
  }
   public void setEventDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      eventDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      eventDate = null;
    }
  }
   public String getEventDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(eventDate == null) 
    {
      return "";
    } else {
      return formatter.format(eventDate);
    }
  } 
 
  public String getFeeExemptString() throws Exception
  {
     if(feeExempt.equals("E")){
      return "Yes";
    } 
    else if(feeExempt.equals("N")) 
    {
      return  "No";
    } 
   
    return "";
   
  }
   public boolean isNew() 
  {
    return (getSpecialId()==0) ? true : false;
  }
   public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public Date getExpirationDate()
  {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate)
  {
    this.expirationDate =expirationDate;
  }
   public void setExpirationDate(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      expirationDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      expirationDate = null;
    }
  }
   public String getExpirationDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(expirationDate == null) 
    {
      return "";
    } else {
      return formatter.format(expirationDate);
    }
  } 
   public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  
 
  public String getAmountString()
  {
    return NumberFormat.getCurrencyInstance().format(getAmount());
    
  }
 
  public void setAmount(String newAmount) throws Exception
  {
    if (newAmount == null) 
    {
      amount=0;
      return;
    }
  
    try {
    amount= NumberFormat.getNumberInstance().parse(newAmount).doubleValue();
    }
    catch (Exception e) 
    {
       try {
       amount= NumberFormat.getCurrencyInstance().parse(newAmount).doubleValue(); 
       } catch (Exception e2) 
       {
         amount=0;
       }
    }
  }
 public String getContactPerson()
  {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson)
  {
    this.contactPerson = contactPerson;
  }
  public String getContactPhone()
  {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone)
  {
    this.contactPhone = contactPhone;
  }
   public String getContactFax()
  {
    return contactFax;
  }

  public void setContactFax(String contactFax)
  {
    this.contactFax = contactFax;
  }
   public String getContactEmail()
  {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail)
  {
    this.contactEmail = contactEmail;
  }
   public String getNoMap()
  {
    return noMap;
  }

  public void setNoMap(String noMap)
  {
    this.noMap = noMap;
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
   public String getOnlineStatus()
  {
    return onlineStatus;
  }

  public void setOnlineStatus(String onlineStatus)
  {
    this.onlineStatus = onlineStatus;
  }
}
