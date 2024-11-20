package hazmat.to;

import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;




public class PermitsToPrint  extends HsCompleteContact implements Serializable
 { private String orgName;
   private String orgAddress1;
   private String orgAddress2;
   private String orgCity;
   private String orgState;
   private String orgZip;
   private String carrierName;
   private String shipOrigin;
   private String shipDestination;
   private String shipRadLevel;
   private String shipPermitNumber;
   private Date shipDate;
   private Date shipIssueDate;
   private Date shipExpDate;
   private String shipAmount;
   private int orgId;
   private int carrierId;
   private int shipmentId;
   private int feeId;
   private String orgEmail;
  private String shipmentType;
  private int numberOfCasks;
  public PermitsToPrint()
  {
  }
  public int getNumberOfCasks()
    { 
     return numberOfCasks;
    }
  public void setNumberOfCasks(int numberOfCasks)
    { 
    this.numberOfCasks = numberOfCasks;
    }
  public String getShipmentType()
  {
    return shipmentType;
  }

  public void setShipmentType(String shipmentType)
  {
    this.shipmentType = shipmentType;
  }
  public String getOrgEmail()
  { 
   return orgEmail;
  }
public void setOrgEmail(String orgEmail)
  { 
  this.orgEmail = orgEmail;
  }
   public String getCarrierName()
  { 
   return carrierName;
  }
public void setCarrierName(String carrierName)
  { 
  this.carrierName = carrierName;
  }
   public String getOrgAddress1()
  { 
   return orgAddress1;
  }
public void setOrgAddress1(String orgAddress1)
  { 
  this.orgAddress1 = orgAddress1;
  }
public String getOrgAddress2()
  { 
   return orgAddress2;
  }
public void setOrgAddress2(String orgAddress2)
  { 
  this.orgAddress2 = orgAddress2;
  }
public String getOrgCity()
  { 
   return orgCity;
  }
public void setOrgCity(String orgCity)
  { 
  this.orgCity = orgCity;
  }
public String getOrgName()
  { 
   return orgName;
  }
public void setOrgName(String orgName)
  { 
  this.orgName = orgName;
  }

public String getOrgState()
  { 
   return orgState;
  }
public void setOrgState(String orgState)
  { 
  this.orgState = orgState;
  }

public String getOrgZip()
  { 
   return orgZip;
  }
public void setOrgZip(String orgZip)
  { 
  this.orgZip = orgZip;
  }
 
public String getShipDestination()
  { 
   return shipDestination;
  }
public void setShipDestination(String shipDestination)
  { 
  this.shipDestination = shipDestination;
  }


  public String getShipPermitNumber()
  { 
    return shipPermitNumber;
  }
public void setShipPermitNumber(String shipPermitNumber)
  { 
  this.shipPermitNumber = shipPermitNumber;
  }
   public String getShipAmount()
  { 
    return shipAmount;
  }
public void setShipAmount(String shipAmount)
  { 
  this.shipAmount = shipAmount;
  }


  public Date getShipDate()
  { 
   return shipDate;
  }
public void setShipDate(Date shipDate)
  { 
  this.shipDate = shipDate;
  }
public Date getShipExpDate()
  { 
   return shipExpDate;
  }
public void setShipExpDate(Date shipExpDate)
  { 
  this.shipExpDate = shipExpDate;
  }
public Date getShipIssueDate()
  { 
   return shipIssueDate;
  }
public void setShipIssueDate(Date shipIssueDate)
  { 
  this.shipIssueDate = shipIssueDate;
  }


public String getShipRadLevel()
  { 
   return shipRadLevel;
  }
public void setShipRadLevel(String shipRadLevel)
  { 
  this.shipRadLevel = shipRadLevel;
  }

   public String getShipOrigin()
  { 
   return shipOrigin;
  }
public void setShipOrigin(String shipOrigin)
  { 
  this.shipOrigin = shipOrigin;
  }
  public void setShipDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
shipDate = formatter.parse(dateParam);
} catch (Exception e)
{shipDate = null; }
}
public String getShipDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(shipDate == null)
{ return ""; }
else { return formatter.format(shipDate); }
}
public void setShipExpDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
shipExpDate = formatter.parse(dateParam);
} catch (Exception e)
{shipExpDate = null; }
}
public String getShipExpDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(shipExpDate == null)
{ return ""; }
else { return formatter.format(shipExpDate); }
}
public void setShipIssueDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
shipIssueDate = formatter.parse(dateParam);
} catch (Exception e)
{shipIssueDate = null; }
}
public String getShipIssueDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(shipIssueDate == null)
{ return ""; }
else { return formatter.format(shipIssueDate); }
}
public int getCarrierId()
  { 
   return carrierId;
  }
public void setCarrierId(int carrierId)
  { 
  this.carrierId = carrierId;
  }
public int getFeeId()
  { 
   return feeId;
  }
public void setFeeId(int feeId)
  { 
  this.feeId = feeId;
  }
public int getOrgId()
  { 
   return orgId;
  }
public void setOrgId(int orgId)
  { 
  this.orgId = orgId;
  }
public int getShipmentId()
  { 
   return shipmentId;
  }
public void setShipmentId(int shipmentId)
  { 
  this.shipmentId = shipmentId;

}
}
