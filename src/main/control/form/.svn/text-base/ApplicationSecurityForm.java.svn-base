package main.control.form;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.to.*;
import org.apache.struts.upload.FormFile;


public class ApplicationSecurityForm extends ActionForm
{ private String userId;
  private String userPassword;
  private String passwordExpiresDate;
  private int loginAttemptCounter;
  private String loginStatus;
  private String newPassword;
  private String retypeNewPassword;
  private String currentDate;
  private String oldPassword;
  private String currentUser;
  private String currentUserLevel;
  private String userEmail;
 
  public ApplicationSecurityForm()
  {
  }
  public String getUserEmail()
  { 
   return userEmail;
  }
public void setUserEmail(String userEmail)
  { 
  this.userEmail = userEmail;
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
 
 
  
 
   public String getPasswordExpiresDate()
  {
    return passwordExpiresDate;
  }

  public void setPasswordExpiresDate(String passwordExpiresDate)
  {
    this.passwordExpiresDate = passwordExpiresDate;
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
   public String getCurrentDate()
  {
    return currentDate;
  }

  public void setCurrentDate(String currentDate)
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
  public void setProperties(ApplicationSecurity security) throws Exception
  {
  
  this.setUserId(security.getUserId());
  this.setUserPassword(security.getUserPassword());
  this.setPasswordExpiresDate(security.getPasswordExpiresDateString());
  
  }
  public ApplicationSecurity getApplicationSecurity() throws Exception
  {
  ApplicationSecurity security = new ApplicationSecurity();
 security.setUserId(this.getUserId());
  security.setUserPassword(this.getUserPassword());
  security.setPasswordExpiresDateString(this.getPasswordExpiresDate());
  
    return security;
  }
  
}
