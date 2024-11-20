package lepc.to;



import java.io.Serializable;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class lepcRoster implements Serializable{
    public lepcRoster() {
       
    }
    private int rosterId;
    private String rosterAddress1;
    private String rosterAddress2;
    private String rosterCity;
    private String rosterZip;
    private String rosterEmail;
    private String rosterChairman;
    private String rosterInfoCoor;
    private String rosterPlanCoor;
    private String rosterEmerCoor;
    private String rosterLocation;
    private String rosterPhoneEmer;
    private String rosterFax;
    private String rosterPhoneAdmin;
    private String rosterPhoneAlt;
    private int lepcId;
    private String rosterStatus;
    private String rosterAddBy;
    private Date rosterDate;
    private List rosterPersons;
    
  public Date getRosterDate()
    {    return rosterDate;  }
  public void setRosterDate(Date rosterDate)
    {   this.rosterDate = rosterDate;
  }
  public String getRosterAddBy()
    {    return rosterAddBy;  }
  public void setRosterAddBy(String rosterAddBy)
    {   this.rosterAddBy =
  rosterAddBy;  }
  public String getRosterFax()
    {    return rosterFax;  }
  public void setRosterFax(String rosterFax)
    {   this.rosterFax = rosterFax;  }
  public String getRosterPhoneAdmin()
    {    return rosterPhoneAdmin;  }
  public void setRosterPhoneAdmin(String rosterPhoneAdmin)
    {
  this.rosterPhoneAdmin = rosterPhoneAdmin;  }
  public String getRosterPhoneAlt()
    {    return rosterPhoneAlt;  }
  public void setRosterPhoneAlt(String rosterPhoneAlt)
    {   this.rosterPhoneAlt =
  rosterPhoneAlt;  }
  public String getRosterPhoneEmer()
    {    return rosterPhoneEmer;  }
  public void setRosterPhoneEmer(String rosterPhoneEmer)
    {
  this.rosterPhoneEmer = rosterPhoneEmer;  }
  public String getRosterStatus()
    {    return rosterStatus;  }
  public void setRosterStatus(String rosterStatus)
    {   this.rosterStatus =
  rosterStatus;  }
  public int getLepcId()
    {    return lepcId;  }
  public void setLepcId(int lepcId)
    {   this.lepcId = lepcId;  }

  public String getRosterAddress1()
    {    return rosterAddress1;  }
  public void setRosterAddress1(String rosterAddress1)
    {   this.rosterAddress1 =
  rosterAddress1;  }
  public String getRosterAddress2()
    {    return rosterAddress2;  }
  public void setRosterAddress2(String rosterAddress2)
    {   this.rosterAddress2 =
  rosterAddress2;  }
  public String getRosterChairman()
    {    return rosterChairman;  }
  public void setRosterChairman(String rosterChairman)
    {   this.rosterChairman =
  rosterChairman;  }
  public String getRosterCity()
    {    return rosterCity;  }
  public void setRosterCity(String rosterCity)
    {   this.rosterCity = rosterCity;
  }
  public String getRosterEmail()
    {    return rosterEmail;  }
  public void setRosterEmail(String rosterEmail)
    {   this.rosterEmail =
  rosterEmail;  }
  public String getRosterEmerCoor()
    {    return rosterEmerCoor;  }
  public void setRosterEmerCoor(String rosterEmerCoor)
    {   this.rosterEmerCoor =
  rosterEmerCoor;  }
  public String getRosterInfoCoor()
    {    return rosterInfoCoor;  }
  public void setRosterInfoCoor(String rosterInfoCoor)
    {   this.rosterInfoCoor =
  rosterInfoCoor;  }
  public String getRosterLocation()
    {    return rosterLocation;  }
  public void setRosterLocation(String rosterLocation)
    {   this.rosterLocation =
  rosterLocation;  }
  public String getRosterPlanCoor()
    {    return rosterPlanCoor;  }
  public void setRosterPlanCoor(String rosterPlanCoor)
    {   this.rosterPlanCoor =
  rosterPlanCoor;  }
  public String getRosterZip()
    {    return rosterZip;  }
  public void setRosterZip(String rosterZip)
    {   this.rosterZip = rosterZip;  }
  
  public int getPersonsCount () 
  {
    return (rosterPersons == null) ? 0 : rosterPersons.size();
  }

  public List getRosterPersons()
  {
    if(rosterPersons == null) 
    {
      rosterPersons = new ArrayList();
    } 
    return rosterPersons;
  }

  public void setRosterPersons(List rosterPersons)
  {
    this.rosterPersons = rosterPersons;
  }
  
  public void setRosterDateString(String dateParam)
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  try {
  rosterDate = formatter.parse(dateParam);
  } catch (Exception e)
  {rosterDate = null; }
  }
  public String getRosterDateString()
  {
  DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
  if(rosterDate == null)
  { return ""; }
  else { return formatter.format(rosterDate); }
  }
  public int getRosterId()
    {    return rosterId;  }
  public void setRosterId(int rosterId)
    {   this.rosterId = rosterId;  }
}
