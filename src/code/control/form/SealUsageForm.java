package code.control.form;

import code.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
import main.to.*;
import main.control.form.*;
import code.data.*;


public class SealUsageForm extends CompleteContactForm

{ private int facilityId;
  private int orderId;
  private String sealNumber;
  private String sealUsedDate;
  private String sealInspDate;
  private String sealRelNumber;
  private String sealUpdateDate;
  private String sealUnitNumber;
  private int inspectorId;
  private int companyId;
  private String inspectorName;
  private String companyName;
  public SealUsageForm()
  {
  }
 public int getOrderId()
  { 
   return orderId;
  }
public void setOrderId(int orderId)
  { 
  this.orderId = orderId;
  }
  public String getSealUpdateDate()
  { 
   return sealUpdateDate;
  }
public void setSealUpdateDate( String  sealUpdateDate)
  { 
  this.sealUpdateDate = sealUpdateDate;
  }
   public String getSealInspDate()
  { 
   return sealInspDate;
  }
public void setSealInspDate( String  sealInspDate)
  { 
  this.sealInspDate = sealInspDate;
  }
public String getSealUsedDate()
  { 
   return sealUsedDate;
  }
public void setSealUsedDate( String  sealUsedDate)
  { 
  this.sealUsedDate = sealUsedDate;
  }
public int getInspectorId()
  { 
   return inspectorId;
  }
public void setInspectorId(int inspectorId)
  { 
  this.inspectorId = inspectorId;
  }
public String getSealNumber()
  { 
   return sealNumber;
  }
public void setSealNumber(String sealNumber)
  { 
  this.sealNumber = sealNumber;
  }
public String getSealRelNumber()
  { 
   return sealRelNumber;
  }
public void setSealRelNumber(String sealRelNumber)
  { 
  this.sealRelNumber = sealRelNumber;
  }
public String getSealUnitNumber()
  { 
   return sealUnitNumber;
  }
public void setSealUnitNumber(String sealUnitNumber)
  { 
  this.sealUnitNumber = sealUnitNumber;
  }
public int getFacilityId()
  { 
   return facilityId;
  }
public void setFacilityId(int facilityId)
  { 
  this.facilityId = facilityId;
  }
  public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
  public String getCompanyName()
  { 
   return companyName;
  }
public void setCompanyName(String companyName)
  { 
  this.companyName = companyName;
  }
public String getInspectorName()
  { 
   return inspectorName;
  }
public void setInspectorName(String inspectorName)
  { 
  this.inspectorName = inspectorName;
  }
  public void setProperties(SealUsage seal) throws Exception
  {
    
    this.setSealNumber(seal.getSealNumber());
    this.setFacilityId(seal.getFacilityId());
    this.setSealUsedDate(seal.getSealUsedDateString());
    this.setSealInspDate(seal.getSealInspDateString());
    this.setSealRelNumber(seal.getSealRelNumber());
    this.setSealUpdateDate(seal.getSealUpdateDateString());
    this.setSealUnitNumber(seal.getSealUnitNumber());
    this.setInspectorId(seal.getInspectorId());
    this.setCompanyId(seal.getCompanyId());
    this.setOrderId(seal.getOrderId());
  }
 public SealUsage  getsealUsage() throws Exception
  {
    
    SealUsage seal = new SealUsage ();
    seal.setSealNumber(this.getSealNumber());
    seal.setFacilityId(this.getFacilityId());
    seal.setSealUsedDateString(this.getSealUsedDate());
    seal.setSealInspDateString(this.getSealInspDate());
    seal.setSealRelNumber(this.getSealRelNumber());
    seal.setSealUpdateDateString(this.getSealUpdateDate());
    seal.setSealUnitNumber(this.getSealUnitNumber());
    seal.setInspectorId(this.getInspectorId());
    seal.setCompanyId(this.getCompanyId());
    seal.setOrderId(this.getOrderId());
    
    return(seal);
  }
}