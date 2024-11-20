package cigarette.control.form;

import cigarette.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;
public class CigaretteUsersForm extends CompleteContactForm
{
  public CigaretteUsersForm()
  {
  }
   private int userId;
  private String userFirstName;
  private String userLastName;
  private String userLoginId;
  private String userPassword;
  private String userStatus;
  private String userPasswordExpiresDate;
  private String userTelephone;
  private int companyId;
  private String newPassword;
  private String retypeNewPassword;
  public String getUserPasswordExpiresDate()
  { 
   return userPasswordExpiresDate;
  }
  public int getCompanyId()
  { 
   return companyId;
  }
public void setCompanyId(int companyId)
  { 
  this.companyId = companyId;
  }
public void setUserPasswordExpiresDate(String userPasswordExpiresDate)
  { 
  this.userPasswordExpiresDate = userPasswordExpiresDate;
  }

public String getUserFirstName()
  { 
   return userFirstName;
  }
public void setUserFirstName(String userFirstName)
  { 
  this.userFirstName = userFirstName;
  }
public String getUserLastName()
  { 
   return userLastName;
  }
public void setUserLastName(String userLastName)
  { 
  this.userLastName = userLastName;
  }
public String getUserLoginId()
  { 
   return userLoginId;
  }
public void setUserLoginId(String userLoginId)
  { 
  this.userLoginId = userLoginId;
  }
public String getUserPassword()
  { 
   return userPassword;
  }
public void setUserPassword(String userPassword)
  { 
  this.userPassword = userPassword;
  }
public String getUserStatus()
  { 
   return userStatus;
  }
public void setUserStatus(String userStatus)
  { 
  this.userStatus = userStatus;
  }
public String getUserTelephone()
  { 
   return userTelephone;
  }
public void setUserTelephone(String userTelephone)
  { 
  this.userTelephone = userTelephone;
  }
public int getUserId()
  { 
   return userId;
  }
public void setUserId(int userId)
  { 
  this.userId = userId;
  }
  public CigaretteUsers getCigaretteUsers() throws Exception
  {
    
    CigaretteUsers cigUser = new CigaretteUsers ();
    cigUser.setUserPasswordExpiresDateString(getUserPasswordExpiresDate());
    cigUser.setUserFirstName(getUserFirstName());
    cigUser.setUserLastName(getUserLastName());
    cigUser.setUserLoginId(getUserLoginId());
    cigUser.setUserPassword(getUserPassword());
    cigUser.setUserStatus(getUserStatus());
    cigUser.setUserTelephone(getUserTelephone());
    cigUser.setUserId(getUserId());
    cigUser.setCompanyId(getCompanyId());
return cigUser;
  }
   public void setProperties(CigaretteUsers cigUser) throws Exception
  { 
    this.setUserPasswordExpiresDate(cigUser.getUserPasswordExpiresDateString());
    this.setUserFirstName(cigUser.getUserFirstName());
    this.setUserLastName(cigUser.getUserLastName());
    this.setUserLoginId(cigUser.getUserLoginId());
    this.setUserPassword(cigUser.getUserPassword());
    this.setUserStatus(cigUser.getUserStatus());
    this.setUserTelephone(cigUser.getUserTelephone());
    this.setUserId(cigUser.getUserId());
    this.setCompanyId(cigUser.getCompanyId());
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
}
