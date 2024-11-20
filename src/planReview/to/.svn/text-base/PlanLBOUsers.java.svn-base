package planReview.to;
import hs.to.*;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class PlanLBOUsers extends HsCompleteContact implements Serializable{
    public PlanLBOUsers() {
      
    }
 private int userId;
  private String userFirstName;
  private String userLastName;
  private String userLoginId;
  private String userPassword;
  private String userStatus;
  private Date userPasswordExpiresDate;
  private String userTelephone;
  private int ownerId;
  private int userPasswordCheck;
   public int getUserPasswordCheck()
  { 
   return userPasswordCheck;
  }
public void setUserPasswordCheck(int userPasswordCheck)
  { 
  this.userPasswordCheck = userPasswordCheck;
  }
  public int getOwnerId()
  { 
   return ownerId;
  }
public void setOwnerId(int ownerId)
  { 
  this.ownerId = ownerId;
  }
  public void setUserPasswordExpiresDateString(String dateParam)
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
try {
userPasswordExpiresDate = formatter.parse(dateParam);
} catch (Exception e)
{userPasswordExpiresDate = null; }
}
public String getUserPasswordExpiresDateString()
{
DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
if(userPasswordExpiresDate == null)
{ return ""; }
else { return formatter.format(userPasswordExpiresDate); }
}
public Date getUserPasswordExpiresDate()
  { 
   return userPasswordExpiresDate;
  }
public void setUserPasswordExpiresDate(Date userPasswordExpiresDate)
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
}



