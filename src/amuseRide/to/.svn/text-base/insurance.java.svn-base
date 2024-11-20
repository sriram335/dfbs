package amuseRide.to;
import amuseRide.to.*;

import hs.to.*;


import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class insurance implements Serializable {
  private int insuranceId;
  private String insName;
  private String insOther;
  private String policyNumber;
  private Date effDate;
  private Date expDate;
  private int amountCovered;
  private Date signedDate;
  private String branchOffice;
  private String authRep;
  private String address1;
  private String address2;
  private String city;
  private String zip;
  private String zip2;
  private String state;
  private String fax;
  private String phone;
  private String ownerId;
  private String insApplicationKey;
  
    public insurance() {
       
    }
  public String getInsApplicationKey()
    { 
     return insApplicationKey;
    }
  public void setInsApplicationKey(String insApplicationKey)
    { 
    this.insApplicationKey = insApplicationKey;
    }
  public Date getEffDate()
    {    return effDate;  }
  public void setEffDate(Date effDate)
    {   this.effDate = effDate;  }
  public Date getExpDate()
    {    return expDate;  }
  public void setExpDate(Date expDate)
    {   this.expDate = expDate;  }
  public Date getSignedDate()
    {    return signedDate;  }
  public void setSignedDate(Date signedDate)
    {   this.signedDate = signedDate;  }
  public String getAddress1()
    {    return address1;  }
  public void setAddress1(String address1)
    {   this.address1 = address1;  }
  public String getAddress2()
    {    return address2;  }
  public void setAddress2(String address2)
    {   this.address2 = address2;  }
  public String getAuthRep()
    {    return authRep;  }
  public void setAuthRep(String authRep)
    {   this.authRep = authRep;  }
  public String getBranchOffice()
    {    return branchOffice;  }
  public void setBranchOffice(String branchOffice)
    {   this.branchOffice = branchOffice;  }
  public String getInsName()
    {    return insName;  }
  public void setInsName(String insName)
    {   this.insName = insName;  }
  public String getInsOther()
    {    return insOther;  }
  public void setInsOther(String insOther)
    {   this.insOther = insOther;  }
  public String getPolicyNumber()
    {    return policyNumber;  }
  public void setPolicyNumber(String policyNumber)
    {   this.policyNumber = policyNumber;  }
  public int getAmountCovered()
    {    return amountCovered;  }
  public void setAmountCovered(int amountCovered)
    {   this.amountCovered = amountCovered;  }
  public int getInsuranceId()
    {    return insuranceId;  }
  public void setInsuranceId(int insuranceId)
    {   this.insuranceId = insuranceId;  }
  public String getCity()
    {    return city;  }
  public void setCity(String city)
    {   this.city = city;  }
  public String getFax()
    {    return fax;  }
  public void setFax(String fax)
    {   this.fax = fax;  }
  public String getOwnerId()
    {    return ownerId;  }
  public void setOwnerId(String ownerId)
    {   this.ownerId = ownerId;  }
  public String getPhone()
    {    return phone;  }
  public void setPhone(String phone)
    {   this.phone = phone;  }
  public String getState()
    {    return state;  }
  public void setState(String state)
    {   this.state = state;  }
  public String getZip()
    {    return zip;  }
  public void setZip(String zip)
    {   this.zip = zip;  }
  public String getZip2()
    {    return zip2;  }
  public void setZip2(String zip2)
    {   this.zip2 = zip2;  }
  public void setEffDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  effDate = formatter.parse(dateParam);
  } catch (Exception e)
  {effDate = null; }
  }
  public String getEffDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(effDate == null)
  { return ""; }
  else { return formatter.format(effDate); }
  }
  public void setExpDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  expDate = formatter.parse(dateParam);
  } catch (Exception e)
  {expDate = null; }
  }
  public String getExpDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(expDate == null)
  { return ""; }
  else { return formatter.format(expDate); }
  }
  public void setSignedDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  signedDate = formatter.parse(dateParam);
  } catch (Exception e)
  {signedDate = null; }
  }
  public String getSignedDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(signedDate == null)
  { return ""; }
  else { return formatter.format(signedDate); }
  }
  public boolean isNew() 
  {
    return (this.getInsuranceId() == 0 ) ? true : false;
  }
}
