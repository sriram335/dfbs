package idhsFines.control.form;
import org.apache.struts.action.ActionForm;
import hs.control.form.*;
import hs.to.*;
import idhsFines.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
public class idhsFinesForm extends ActionForm{
    private String due;
    private String amountPaid;
    private String description;
    private String receiptNumber;
    private String postDate;
    private String searchFor;
    private String ownerId;
    private String stateNumber;
    private String transactionId;  

    public idhsFinesForm() {
      
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
  public String getStateNumber()
    { 
     return stateNumber;
    }
  public void setStateNumber(String stateNumber)
    { 
    this.stateNumber = stateNumber;
    }
  public String getOwnerId()
    { 
     return ownerId;
    }
  public void setOwnerId(String ownerId)
    { 
    this.ownerId = ownerId;
    }
  public String getSearchFor()
    { 
     return searchFor;
    }
  public void setSearchFor(String searchFor)
    { 
    this.searchFor = searchFor;
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
