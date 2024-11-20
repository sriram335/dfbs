package hazmat.control.form;

import hazmat.to.*;
import main.control.form.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;


public class HazmatShipmentForm  extends CompleteContactForm
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
 private String shipDate;
 private String shipIssueDate;
 private String shipExpDate;
 private String applicationKey;
 private String shipPermitNumber;
 private String shipWeightType;
  private String shipmentType;
  private int numberOfCasks;
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
  public String getShipPermitNumber()
  { 
   return shipPermitNumber;
  }
public void setShipPermitNumber(String shipPermitNumber)
  { 
  this.shipPermitNumber = shipPermitNumber;
  }
  public HazmatShipmentForm()
  {
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
  public String getShipDate()
  { 
   return shipDate;
  }
public void setShipDate(String shipDate)
  { 
  this.shipDate = shipDate;
  }
public String getShipExpDate()
  { 
   return shipExpDate;
  }
public void setShipExpDate(String shipExpDate)
  { 
  this.shipExpDate = shipExpDate;
  }
public String getShipIssueDate()
  { 
   return shipIssueDate;
  }
public void setShipIssueDate(String shipIssueDate)
  { 
  this.shipIssueDate = shipIssueDate;
  }
public String getApplicationKey()
  { 
   return applicationKey;
  }
public void setApplicationKey(String applicationKey)
  { 
  this.applicationKey = applicationKey;
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
   public HazmatShipment getHazmatShipment() throws Exception
  {
    
    HazmatShipment shipment = new HazmatShipment ();
    shipment.setCarrierId(getCarrierId());
    shipment.setShipActIsotope(getShipActIsotope());
    shipment.setShipAmount(getShipAmount());
    shipment.setShipComments(getShipComments());
    shipment.setShipDateString(getShipDate());
    shipment.setShipDestination(getShipDestination());
    shipment.setShipExpDateString(getShipExpDate());
    shipment.setShipFeeId(getShipFeeId());
    shipment.setShipIsotope(getShipIsotope());
    shipment.setShipIssueDateString(getShipIssueDate());
    shipment.setShipMaterialType(getShipMaterialType());
    shipment.setShipRadLevel(getShipRadLevel());
    shipment.setShipRoute(getShipRoute());
    shipment.setShipVicvsa(getShipVicvsa());
    shipment.setShipVicvsaState(getShipVicvsaState());
    shipment.setShipmentId(getShipmentId());
    shipment.setShipOrigin(getShipOrigin());
    shipment.setShipWeightType(getShipWeightType());
    shipment.setShipmentType(getShipmentType());
    shipment.setNumberOfCasks(getNumberOfCasks());
    return(shipment);
    
  }
   public void setProperties(HazmatShipment shipment) throws Exception
  { 
    this.setCarrierId(shipment.getCarrierId());
    this.setShipActIsotope(shipment.getShipActIsotope());
    this.setShipAmount(shipment.getShipAmount());
    this.setShipComments(shipment.getShipComments());
    this.setShipDate(shipment.getShipDateString());
    this.setShipOrigin(shipment.getShipOrigin());
    this.setShipDestination(shipment.getShipDestination());
    this.setShipExpDate(shipment.getShipExpDateString());
    this.setShipFeeId(shipment.getShipFeeId());
    this.setShipIsotope(shipment.getShipIsotope());
    this.setShipIssueDate(shipment.getShipIssueDateString());
    this.setShipMaterialType(shipment.getShipMaterialType());
    this.setShipRadLevel(shipment.getShipRadLevel());
    this.setShipRoute(shipment.getShipRoute());
    this.setShipVicvsa(shipment.getShipVicvsa());
    this.setShipVicvsaState(shipment.getShipVicvsaState());
    this.setShipmentId(shipment.getShipmentId());
    this.setShipWeightType(shipment.getShipWeightType());
    this.setShipmentType(shipment.getShipmentType());
    this.setNumberOfCasks(shipment.getNumberOfCasks());
  }
  public String getShipWeightType()
  { 
   return shipWeightType;
  }
public void setShipWeightType(String shipWeightType)
  { 
  this.shipWeightType = shipWeightType;
  }   
}