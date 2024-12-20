package hazmat.to;

import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;





public class HazmatShipment extends HsCompleteContact implements Serializable
{private int shipmentId;
 private int shipFeeId;
 private int carrierId;
 private String shipOrigin;
 private String shipDestination;
 private String shipAmount;
 private String shipMaterialType;
 private String shipIsotope;
 private String shipActIsotope;
 private String shipVicvsa;
 private String shipVicvsaState;
 private String shipComments;
 private String shipRoute;
 private String shipRadLevel;
 private Date shipDate;
 private Date shipIssueDate;
 private Date shipExpDate;
 private String applicationKey;
 private String shipPermitNumber;
 private Date shipAppDate;
 private String shipWeightType;
 private String shipmentType;
 private int numberOfCasks;
 private double amount;
  public HazmatShipment()
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
  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }
  public String getApplicationKey()
  {
    return applicationKey;
  }

  public void setApplicationKey(String applicationKey)
  {
    this.applicationKey = applicationKey;
  }
  public boolean isNew() 
  {
    return (this.getShipmentId() == 0) ? true : false;
  }
  public String getShipActIsotope()
  { 
   return shipActIsotope;
  }
public void setShipActIsotope(String shipActIsotope)
  { 
  this.shipActIsotope = shipActIsotope;
  }
public String getShipAmount()
  { 
   return shipAmount;
  }
public void setShipAmount(String shipAmount)
  { 
  this.shipAmount = shipAmount;
  }
public String getShipDestination()
  { 
   return shipDestination;
  }
public void setShipDestination(String shipDestination)
  { 
  this.shipDestination = shipDestination;
  }
public String getShipIsotope()
  { 
   return shipIsotope;
  }
public void setShipIsotope(String shipIsotope)
  { 
  this.shipIsotope = shipIsotope;
  }
public String getShipMaterialType()
  { 
   return shipMaterialType;
  }
public void setShipMaterialType(String shipMaterialType)
  { 
  this.shipMaterialType = shipMaterialType;
  }
public String getShipVicvsa()
  { 
   return shipVicvsa;
  }
public void setShipVicvsa(String shipVicvsa)
  { 
  this.shipVicvsa = shipVicvsa;
  }
public String getShipVicvsaState()
  { 
   return shipVicvsaState;
  }
public void setShipVicvsaState(String shipVicvsaState)
  { 
  this.shipVicvsaState = shipVicvsaState;
  }
  
  public String getShipPermitNumber()
  { 
     return shipPermitNumber;
  }
public void setShipPermitNumber(String shipPermitNumber)
  { 
  this.shipPermitNumber = shipPermitNumber;
  }
  
public int getCarrierId()
  { 
   return carrierId;
  }
public void setCarrierId(int carrierId)
  { 
  this.carrierId = carrierId;
  }
public int getShipFeeId()
  { 
   return shipFeeId;
  }
public void setShipFeeId(int shipFeeId)
  { 
  this.shipFeeId = shipFeeId;
  }
public int getShipmentId()
  { 
   return shipmentId;
  }
public void setShipmentId(int shipmentId)
  { 
  this.shipmentId = shipmentId;
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

public String getShipComments()
  { 
   return shipComments;
  }
public void setShipComments(String shipComments)
  { 
  this.shipComments = shipComments;
  }
public String getShipRadLevel()
  { 
   return shipRadLevel;
  }
public void setShipRadLevel(String shipRadLevel)
  { 
  this.shipRadLevel = shipRadLevel;
  }
public String getShipRoute()
  { 
   return shipRoute;
  }
public void setShipRoute(String shipRoute)
  { 
  this.shipRoute = shipRoute;
  }
   public String getShipOrigin()
  { 
   return shipOrigin;
  }
public void setShipOrigin(String shipOrigin)
  { 
  this.shipOrigin = shipOrigin;
  }
   public String getShipWeightType()
  { 
   return shipWeightType;
  }
public void setShipWeightType(String shipWeightType)
  { 
  this.shipWeightType = shipWeightType;
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
DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
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
 public Date getShipAppDate()
  { 
   return shipAppDate;
  }
public void setShipAppDate(Date shipAppDate)
  { 
  this.shipAppDate = shipAppDate;
  }
public void setShipAppDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
shipAppDate = formatter.parse(dateParam);
} catch (Exception e)
{shipAppDate = null; }
}
public String getShipAppDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(shipAppDate == null)
{ return ""; }
else { return formatter.format(shipAppDate); }
}

}
