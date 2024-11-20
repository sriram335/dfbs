package main.to;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.struts.upload.FormFile;

public class ApplicationSecurity  implements Serializable
{
  private String userId;
  private String userPassword;
  private Date passwordExpiresDate;
  private int loginAttemptCounter;
  private String loginStatus;
  private String newPassword;
  private String retypeNewPassword;
  private Date currentDate;
  private String oldPassword;
  private String currentUser;
  private String currentUserLevel;
  private String userEmail;
  private String groupAcct;
  private String groupAll;
  private String groupAmuse;
  private String groupArson;
  private String groupBPV;
  private String groupCigarette;
  private String groupCode;
  private String groupElev;
  private String groupEms;
  private String groupFFC;
  private String groupFire;
  private String groupHazmat;
  private String groupVar;
  private String groupCodeEducation;
  private String groupPlan;
  private String groupLevelAcct;
  private String groupLevelAll;
  private String groupLevelAmuse;
  private String groupLevelArson;
  private String groupLevelBPV;
  private String groupLevelCigarette;
  private String groupLevelCode;
  private String groupLevelElev;
  private String groupLevelEms;
  private String groupLevelFFC;
  private String groupLevelFire;
  private String groupLevelHazmat;
  private String groupLevelVar;
  private String groupLevelPlan;
  private String groupLevelCodeEducation;
  private String groupLevelLepc;
  private String groupLepc;
  private String groupLevelHipaa;
  private String groupHipaa;
  private String groupFireBldEducation;
  private String groupLevelFireBldEducation;
  public ApplicationSecurity()
  {
  }
  public int getLoginAttemptCounter()
  { 
   return loginAttemptCounter;
  }
  public void setLoginAttemptCounter(int loginAttemptCounter)
  { 
  this.loginAttemptCounter = loginAttemptCounter;
  }
  
  public String getUserId()
  { 
   return userId;
  }
public void setUserId(String userId)
  { 
  this.userId = userId;
  }
  public String getCurrentUser()
  { 
   return currentUser;
  }
public void setCurrentUser(String currentUser)
  { 
  this.currentUser = currentUser;
  }
  public String getCurrentUserLevel()
  { 
   return currentUserLevel;
  }
public void setCurrentUserLevel(String currentUserLevel)
  { 
  this.currentUserLevel = currentUserLevel;
  }
  public String getUserPassword()
  { 
   return userPassword;
  }
  public void setUserPassword(String userPassword)
  { 
  this.userPassword = userPassword;
  }
  
 
  public String getUserEmail()
  { 
   return userEmail;
  }
public void setUserEmail(String userEmail)
  { 
  this.userEmail = userEmail;
  }
 
   public Date getPasswordExpiresDate()
  {
    return passwordExpiresDate;
  }

  public void setPasswordExpiresDate(Date passwordExpiresDate)
  {
    this.passwordExpiresDate = passwordExpiresDate;
  }
   public void setPasswordExpiresDateString(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      passwordExpiresDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      passwordExpiresDate = null;
    }
  }
   public String getPasswordExpiresDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(passwordExpiresDate == null) 
    {
      return "";
    } else {
      return formatter.format(passwordExpiresDate);
    }
  }
 
   public String getLoginStatus()
  { 
   return loginStatus;
  }
  public void setLoginStatus(String loginStatus)
  { 
  this.loginStatus = loginStatus;
  }
  public String getNewPassword()
  { 
   return newPassword;
  }
public void setNewPassword(String newPassword)
  { 
  this.newPassword = newPassword;
  }
  public String getRetypeNewPassword()
  { 
  return retypeNewPassword;
  }
public void setRetypeNewPassword(String retypeNewPassword)
  { 
  this.retypeNewPassword = retypeNewPassword;
  }
   public Date getCurrentDate()
  {
    return currentDate;
  }

  public void setCurrentDate(Date currentDate)
  {
    this.currentDate = currentDate;
  }
   public String getOldPassword()
  { 
   return oldPassword;
  }
public void setOldPassword(String oldPassword)
  { 
  this.oldPassword = oldPassword;
  }
  public String getGroupAcct()
  { 
   return groupAcct;
  }
public void setGroupAcct(String groupAcct)
  { 
  this.groupAcct = groupAcct;
  }
public String getGroupAll()
  { 
   return groupAll;
  }
public void setGroupAll(String groupAll)
  { 
  this.groupAll = groupAll;
  }
public String getGroupAmuse()
  { 
   return groupAmuse;
  }
public void setGroupAmuse(String groupAmuse)
  { 
  this.groupAmuse = groupAmuse;
  }
public String getGroupArson()
  { 
   return groupArson;
  }
public void setGroupArson(String groupArson)
  { 
  this.groupArson = groupArson;
  }
public String getGroupBPV()
  { 
   return groupBPV;
  }
public void setGroupBPV(String groupBPV)
  { 
  this.groupBPV = groupBPV;
  }
public String getGroupCigarette()
  { 
   return groupCigarette;
  }
public void setGroupCigarette(String groupCigarette)
  { 
  this.groupCigarette = groupCigarette;
  }
public String getGroupCode()
  { 
   return groupCode;
  }
public void setGroupCode(String groupCode)
  { 
  this.groupCode = groupCode;
  } 
  public String getGroupElev()
  { 
   return groupElev;
  }
public void setGroupElev(String groupElev)
  { 
  this.groupElev = groupElev;
  }
public String getGroupEms()
  { 
   return groupEms;
  }
public void setGroupEms(String groupEms)
  { 
  this.groupEms = groupEms;
  }
 
public String getGroupFFC()
  { 
   return groupFFC;
  }
public void setGroupFFC(String groupFFC)
  { 
  this.groupFFC = groupFFC;
  }
public String getGroupFire()
  { 
   return groupFire;
  }
public void setGroupFire(String groupFire)
  { 
  this.groupFire = groupFire;
  }
public String getGroupHazmat()
  { 
   return groupHazmat;
  }
public void setGroupHazmat(String groupHazmat)
  { 
  this.groupHazmat = groupHazmat;
  }
public String getGroupPlan()
  { 
   return groupPlan;
  }
public void setGroupPlan(String groupPlan)
  { 
  this.groupPlan = groupPlan;
  }
public String getGroupVar()
  { 
   return groupVar;
  }
public void setGroupVar(String groupVar)
  { 
  this.groupVar = groupVar;
  }
  public String getGroupLevelAcct()
  { 
   return groupLevelAcct;
  }
public void setGroupLevelAcct(String groupLevelAcct)
  { 
  this.groupLevelAcct = groupLevelAcct;
  }
public String getGroupLevelAll()
  { 
   return groupLevelAll;
  }
public void setGroupLevelAll(String groupLevelAll)
  { 
  this.groupLevelAll = groupLevelAll;
  }
public String getGroupLevelAmuse()
  { 
   return groupLevelAmuse;
  }
public void setGroupLevelAmuse(String groupLevelAmuse)
  { 
  this.groupLevelAmuse = groupLevelAmuse;
  }
public String getGroupLevelArson()
  { 
   return groupLevelArson;
  }
public void setGroupLevelArson(String groupLevelArson)
  { 
  this.groupLevelArson = groupLevelArson;
  }
public String getGroupLevelBPV()
  { 
   return groupLevelBPV;
  }
public void setGroupLevelBPV(String groupLevelBPV)
  { 
  this.groupLevelBPV = groupLevelBPV;
  }
public String getGroupLevelCigarette()
  { 
   return groupLevelCigarette;
  }
public void setGroupLevelCigarette(String groupLevelCigarette)
  { 
  this.groupLevelCigarette = groupLevelCigarette;
  }
public String getGroupLevelCode()
  { 
   return groupLevelCode;
  }
public void setGroupLevelCode(String groupLevelCode)
  { 
  this.groupLevelCode = groupLevelCode;
  } 
  public String getGroupLevelElev()
  { 
   return groupLevelElev;
  }
public void setGroupLevelElev(String groupLevelElev)
  { 
  this.groupLevelElev = groupLevelElev;
  }
public String getGroupLevelEms()
  { 
   return groupLevelEms;
  }
public void setGroupLevelEms(String groupLevelEms)
  { 
  this.groupLevelEms = groupLevelEms;
  }
public String getGroupLevelFFC()
  { 
   return groupLevelFFC;
  }
public void setGroupLevelFFC(String groupLevelFFC)
  { 
  this.groupLevelFFC = groupLevelFFC;
  }
public String getGroupLevelFire()
  { 
   return groupLevelFire;
  }
public void setGroupLevelFire(String groupLevelFire)
  { 
  this.groupLevelFire = groupLevelFire;
  }
public String getGroupLevelHazmat()
  { 
   return groupLevelHazmat;
  }
public void setGroupLevelHazmat(String groupLevelHazmat)
  { 
  this.groupLevelHazmat = groupLevelHazmat;
  }
public String getGroupLevelPlan()
  { 
   return groupLevelPlan;
  }
public void setGroupLevelPlan(String groupLevelPlan)
  { 
  this.groupLevelPlan = groupLevelPlan;
  }
public String getGroupLevelVar()
  { 
   return groupLevelVar;
  }
    public void setGroupLevelVar(String groupLevelVar)
      { 
      this.groupLevelVar = groupLevelVar;
      }
public void setGroupLevelCodeEducation(String groupLevelCodeEducation)
  { 
  this.groupLevelCodeEducation = groupLevelCodeEducation;
  }
    public String getGroupLevelCodeEducation()
      { 
       return groupLevelCodeEducation;
      }
    public String getGroupCodeEducation()
      { 
       return groupCodeEducation;
      }
    public void setGroupCodeEducation(String groupCodeEducation)
      { 
      this.groupCodeEducation = groupCodeEducation;
      }
  public String getGroupLepc()
    { 
     return groupLepc;
    }
  public void setGroupLepc(String groupLepc)
    { 
    this.groupLepc = groupLepc;
    }
  public String getGroupLevelLepc()
    { 
     return groupLevelLepc;
    }
  public void setGroupLevelLepc(String groupLevelLepc)
    { 
    this.groupLevelLepc = groupLevelLepc;
    }
  public String getGroupLevelHipaa()
    { 
     return groupLevelHipaa;
    }
  public void setGroupLevelHipaa(String groupLevelHipaa)
    { 
    this.groupLevelHipaa = groupLevelHipaa;
    }
  public String getGroupHipaa()
    { 
     return groupHipaa;
    }
  public void setGroupHipaa(String groupHipaa)
    { 
    this.groupHipaa = groupHipaa;
    }
  public String getGroupFireBldEducation()
    { 
     return groupFireBldEducation;
    }
  public void setGroupFireBldEducation(String groupFireBldEducation)
    { 
    this.groupFireBldEducation = groupFireBldEducation;
    }
  public String getGroupLevelFireBldEducation()
    { 
     return groupLevelFireBldEducation;
    }
  public void setGroupLevelFireBldEducation(String groupLevelFireBldEducation)
    { 
    this.groupLevelFireBldEducation = groupLevelFireBldEducation;
    }
}