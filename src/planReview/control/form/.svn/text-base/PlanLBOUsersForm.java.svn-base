package planReview.control.form;
import planReview.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import org.apache.struts.action.*;
import main.control.form.*;
import main.to.*;
public class PlanLBOUsersForm extends ActionForm{
    public PlanLBOUsersForm() {
       
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
  private String idNumber;
  public String getUserPasswordExpiresDate()
  { 
   return userPasswordExpiresDate;
  }
   public String getIdNumber()
     { 
      return idNumber;
     }
    public void setIdNumber(String idNumber)
     { 
     this.idNumber = idNumber;
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
  public PlanLBOUsers getPlanLBOUsers() throws Exception
  {
    
    PlanLBOUsers planUser = new PlanLBOUsers ();
    planUser.setUserPasswordExpiresDateString(getUserPasswordExpiresDate());
    planUser.setUserFirstName(getUserFirstName());
    planUser.setUserLastName(getUserLastName());
    planUser.setUserLoginId(getUserLoginId());
    planUser.setUserPassword(getUserPassword());
    planUser.setUserStatus(getUserStatus());
    planUser.setUserTelephone(getUserTelephone());
    planUser.setUserId(getUserId());
    planUser.setOwnerId(getOwnerId());
return planUser;
  }
   public void setProperties(PlanLBOUsers planUser) throws Exception
  { 
    this.setUserPasswordExpiresDate(planUser.getUserPasswordExpiresDateString());
    this.setUserFirstName(planUser.getUserFirstName());
    this.setUserLastName(planUser.getUserLastName());
    this.setUserLoginId(planUser.getUserLoginId());
    this.setUserPassword(planUser.getUserPassword());
    this.setUserStatus(planUser.getUserStatus());
    this.setUserTelephone(planUser.getUserTelephone());
    this.setUserId(planUser.getUserId());
    this.setOwnerId(planUser.getOwnerId());
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



