package ems.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;

public class EmsSecurity implements Serializable
{ private int securityId;
  private String userId;
  private String userPassword;
  private String userLevel;
  private String userPhone;
  private String userLastName;
  private String userFirstName;
  private String userTitle;
  private String userStatus;
  private Map userDetailMap;
  private int userDetailCounter;
  private Date passwordExpiresDate;
  private Date recordCreatedDate;
  private String recordCreatedBy;
  private int loginAttemptCounter;
  private String loginStatus;
  private String newPassword;
  private String retypeNewPassword;
  private Date currentDate;
  private String oldPassword;
  private String currentUser;
  private String currentUserLevel;
  private String idNumber;
  private String idType;
  private List detailList;
  private String comments;
  public EmsSecurity()
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
  public int getSecurityId()
  { 
   return securityId;
  }
public void setSecurityId(int securityId)
  { 
  this.securityId = securityId;
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
  public String getUserLevel()
  { 
   return userLevel;
  }
public void setUserLevel(String userLevel)
  { 
  this.userLevel = userLevel;
  }
 
  public String getUserPhone()
  { 
   return userPhone;
  }
public void setUserPhone(String userPhone)
  { 
  this.userPhone = userPhone;
  }
  public String getUserLastName()
  { 
   return userLastName;
  }
public void setUserLastName(String userLastName)
  { 
  this.userLastName = userLastName;
  }
  public String getUserFirstName()
  { 
   return userFirstName;
  }
public void setUserFirstName(String userFirstName)
  { 
  this.userFirstName = userFirstName;
  }
  public String getUserTitle()
  { 
   return userTitle;
  }
public void setUserTitle(String userTitle)
  { 
  this.userTitle = userTitle;
  }
  public String getUserStatus()
  { 
   return userStatus;
  }
public void setUserStatus(String userStatus)
  { 
  this.userStatus = userStatus;
  }
  public int getUserDetailCounter()
  { 
   return userDetailCounter;
  }
public void setUserDetailCounter(int userDetailCounter)
  { 
  this.userDetailCounter = userDetailCounter;
  }
  
   public Map getUserDetailMap()
  {
    if(userDetailMap == null) 
    {
      userDetailMap = new HashMap();
    }
    return userDetailMap;
  }

  public void setUserDetailMap(Map userDetailMap)
  {
    this.userDetailMap = userDetailMap;
  }
  public int getUserDetailMapCount()
  {
    return (this.userDetailMap == null) ? 0 : userDetailMap.size();
  }

  public void addDetail(EmsSecurityDetail securityDetail) throws Exception
  {
    if(securityDetail == null) return;
    
    securityDetail.setUserId(this.getUserId());
    
    if(securityDetail.isNew()) 
    {
      StringBuffer sb = new StringBuffer("NEW");
      sb.append(getUserDetailCounter() + 1);
      Map map = this.getUserDetailMap();
      securityDetail.setApplicationKey(sb.toString());
      map.put(sb.toString(),securityDetail);
      setUserDetailCounter(getUserDetailCounter() + 1);
    } 
    else 
    {
      Map map = this.getUserDetailMap();
      StringBuffer sb = new StringBuffer("");
      sb.append(securityDetail.getDetailId());
      securityDetail.setApplicationKey(sb.toString());
      map.put(securityDetail.getUserId(),securityDetail);
    }
    
  }
  public EmsSecurityDetail getSecurityDetail(String key)
  {
    
    if(key == null || userDetailMap == null ) {
       
      return new EmsSecurityDetail();
    }
    EmsSecurityDetail securityDetail = (EmsSecurityDetail) userDetailMap.get(key);
    return (securityDetail == null) ? new EmsSecurityDetail() : securityDetail;
  }
  
  
  public void removeSecurityDetail(String key) throws Exception
  {
    if(key == null || userDetailMap == null ) return;
    
    EmsSecurityDetail securityDetail = (EmsSecurityDetail) userDetailMap.get(key);
    userDetailMap.remove(key);
  }
  
  public boolean isNew() 
  {
    return (getSecurityId() == 0) ? true : false;
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

   public Date getRecordCreatedDate()
  {
    return recordCreatedDate;
  }

  public void setRecordCreatedDate(Date recoredCreatedDate)
  {
    this.recordCreatedDate = recordCreatedDate;
  }
  public String getRecordCreatedBy()
  { 
   return recordCreatedBy;
  }
  public void setRecordCreatedBy(String recordCreatedBy)
  { 
  this.recordCreatedBy = recordCreatedBy;
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
   public void setRecordCreatedDateString(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      recordCreatedDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      recordCreatedDate = null;
    }
  }
   public String getRecordCreatedDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(recordCreatedDate == null) 
    {
      return "";
    } else {
      return formatter.format(recordCreatedDate);
    }
  }
  public String getIdType()
  { 
   return idType;
  }
public void setIdType(String idType)
  { 
  this.idType = idType;
  }
public String getIdNumber()
  { 
   return idNumber;
  }
public void setIdNumber(String idNumber)
  { 
  this.idNumber = idNumber;
  }
   public List getDetailList()
  {
    if(detailList == null) 
    {
      detailList = new ArrayList();
    } 
    return detailList;
  }

  public void setDetailList(List detailList)
  {
    this.detailList = detailList;
  }
   public String getComments()
  { 
   return comments;
  }
  public void setComments(String comments)
  { 
  this.comments = comments;
  }
   public void setCurrentDateString(String dateParam) 
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
      currentDate = formatter.parse(dateParam);
    } catch (Exception e) 
    {
      currentDate = null;
    }
  }
   public String getCurrentDateString()
  {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(currentDate == null) 
    {
      return "";
    } else {
      return formatter.format(currentDate);
    }
  }
}