package magazine.control.form;

import magazine.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;

public class MagazineUsersForm extends ActionForm{
    public MagazineUsersForm() {
       
    }
     private int userId;
  private String userFirstName;
  private String userLastName;
  private String userLoginId;
  private String userPassword;
  private String userStatus;
  private String userPasswordExpiresDate;
  private String userTelephone;
  private int ownerId;
  private String newPassword;
  private String retypeNewPassword;
  public String getUserPasswordExpiresDate()
  { 
   return userPasswordExpiresDate;
  }
  public int getOwnerId()
  { 
   return ownerId;
  }
public void setOwnerId(int ownerId)
  { 
  this.ownerId = ownerId;
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
  public MagazineUsers getMagazineUsers() throws Exception
  {
    
    MagazineUsers magUser = new MagazineUsers ();
    magUser.setUserPasswordExpiresDateString(getUserPasswordExpiresDate());
    magUser.setUserFirstName(getUserFirstName());
    magUser.setUserLastName(getUserLastName());
    magUser.setUserLoginId(getUserLoginId());
    magUser.setUserPassword(getUserPassword());
    magUser.setUserStatus(getUserStatus());
    magUser.setUserTelephone(getUserTelephone());
    magUser.setUserId(getUserId());
    magUser.setOwnerId(getOwnerId());
return magUser;
  }
   public void setProperties(MagazineUsers magUser) throws Exception
  { 
    this.setUserPasswordExpiresDate(magUser.getUserPasswordExpiresDateString());
    this.setUserFirstName(magUser.getUserFirstName());
    this.setUserLastName(magUser.getUserLastName());
    this.setUserLoginId(magUser.getUserLoginId());
    this.setUserPassword(magUser.getUserPassword());
    this.setUserStatus(magUser.getUserStatus());
    this.setUserTelephone(magUser.getUserTelephone());
    this.setUserId(magUser.getUserId());
    this.setOwnerId(magUser.getOwnerId());
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


