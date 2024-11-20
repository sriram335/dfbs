package main.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;





public class feeDetails  implements Serializable
{ private String due;
  private String amountPaid;
  private String description;
  private String receiptNumber;
  private String postDate;
  private String uniqueNumber;
  private String transactionId;
  
  public feeDetails()
  {
  }
  public String getAmountPaid()
  { 
   return amountPaid;
  }
public void setAmountPaid(String amountPaid)
  { 
  this.amountPaid = amountPaid;
  }
public String getDescription()
  { 
   return description;
  }
public void setDescription(String description)
  { 
  this.description = description;
  }
public String getDue()
  { 
   return due;
  }
public void setDue(String due)
  { 
  this.due = due;
  }
public String getPostDate()
  { 
   return postDate;
  }
public void setPostDate(String postDate)
  { 
  this.postDate = postDate;
  }
public String getReceiptNumber()
  { 
   return receiptNumber;
  }
public void setReceiptNumber(String receiptNumber)
  { 
  this.receiptNumber = receiptNumber;
  }
  public String getUniqueNumber()
    { 
     return uniqueNumber;
    }
  public void setUniqueNumber(String uniqueNumber)
    { 
    this.uniqueNumber = uniqueNumber;
    }
  public String getTransactionId()
    { 
     return transactionId;
    }
  public void setTransactionId(String transactionId)
    { 
    this.transactionId = transactionId;
    }
}