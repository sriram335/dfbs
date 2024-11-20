package ems.control.form;
import ems.to.*;
import hs.to.*;
import hs.control.form.*;
import java.util.Map;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
public class EmsSecurityForm extends ActionForm
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
  private String passwordExpiresDate;
  private String recordCreatedDate;
  private String recordCreatedBy;
  private String newPassword;
  private String retypeNewPassword;
  private String oldPassword;
  private String currentUser;
  private String currentUserLevel;
  private transient FormFile caseFile = null;//get & set methods
  private String xlName;//get & set methods
  private String idNumber;
  private String idType;
  private String comments;
  public EmsSecurityForm()
  {
  }
  public String getRecordCreatedBy()
  { 
   return recordCreatedBy;
  }
public void setRecordCreatedBy(String recordCreatedBy)
  { 

this.recordCreatedBy = recordCreatedBy;
  }
  public String getPasswordExpiresDate()
  { 
   return passwordExpiresDate;
  }
public void setPasswordExpiresDate(String passwordExpiresDate)
  { 
  this.passwordExpiresDate = passwordExpiresDate;
  }
  public String getRecordCreatedDate()
  { 
   return recordCreatedDate;
  }
public void setRecordCreatedDate(String recordCreatedDate)
  { 

this.recordCreatedDate = recordCreatedDate;
  }
 public Map getUserDetailMap()
  {
    return userDetailMap;
  }

  public void setUserDetailMap(Map userDetailMap)
  {
    this.userDetailMap = userDetailMap;
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
 public void setProperties(EmsSecurity security) throws Exception
  {System.out.println("22");
  
  this.setSecurityId(security.getSecurityId());
  this.setUserId(security.getUserId());
  this.setUserPassword(security.getUserPassword());
  this.setUserLevel(security.getUserLevel());
  this.setRecordCreatedDate(security.getRecordCreatedDateString());
  this.setUserPhone(security.getUserPhone());
  this.setUserLastName(security.getUserLastName());
  this.setUserFirstName(security.getUserFirstName());
  this.setUserTitle(security.getUserTitle());
  this.setUserStatus(security.getUserStatus());
  this.setPasswordExpiresDate(security.getPasswordExpiresDateString());
  this.setRecordCreatedBy(security.getRecordCreatedBy());
  System.out.println(this.getUserId());
  }
  public EmsSecurity getEmsSecurity() throws Exception
  {System.out.println("in get emsSecurity");
  EmsSecurity security = new EmsSecurity();
  System.out.println(this.getUserId());
  security.setSecurityId(this.getSecurityId());
  security.setUserId(this.getUserId());
  security.setUserPassword(this.getUserPassword());
  security.setUserLevel(this.getUserLevel());
  security.setUserPhone(this.getUserPhone());
  security.setUserLastName(this.getUserLastName());
  security.setUserFirstName(this.getUserFirstName());
  security.setUserTitle(this.getUserTitle());
  security.setUserStatus(this.getUserStatus());
  security.setPasswordExpiresDateString(this.getPasswordExpiresDate());
  security.setRecordCreatedBy(this.getRecordCreatedBy());
  security.setRecordCreatedDateString(this.getRecordCreatedDate());
    return security;
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
  public String getNewPassword()
  { 
  return newPassword;
  }
  public String getOldPassword()
  { 
   return oldPassword;
  }
public void setOldPassword(String oldPassword)
  { 
  this.oldPassword = oldPassword;
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
   public FormFile getCaseFile()
  { 
   return caseFile;
  }
public void setCaseFile( FormFile caseFile)
  { 
  this.caseFile = caseFile;
  } 
   public String getXlName()
  { 
   return xlName;
  }
public void setXlName(String xlName)
  { 
  this.xlName = xlName;
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
   public String getComments()
  { 
   return comments;
  }
  public void setComments(String comments)
  { 
  this.comments = comments;
  }
}