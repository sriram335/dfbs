package FireBldEducation.to;
import hs.to.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;
public class FireBldEducationUser implements Serializable {
    private int userPersonId;
    private String userId;
    private String userPassword;
    private String userType;
    private String userLastName;
    private String userFirstName;
    private String userMI;
    private String userAddress1;
    private String userAddress2;
    private String userCity;
    private String userCounty;
    private String userState;
    private String userZip;
    private String userZip2;
    private String userPhone;
    private String userFax;
    private String userStatus;
    private String userPsid;
    private List plans;
    private List courses;
    private double registrationFee;
    private String checkoutId;
    public FireBldEducationUser() {
    }
    public String getCheckoutId()
     {
       return checkoutId;
     }

     public void setCheckoutId(String checkoutId)
     {
       this.checkoutId = checkoutId;
     }
    public String getUserAddress1()
      { 
       return userAddress1;
      }
    public void setUserAddress1(String userAddress1)
      { 
      this.userAddress1 = userAddress1;
      }
    public String getUserAddress2()
      { 
       return userAddress2;
      }
    public void setUserAddress2(String userAddress2)
      { 
      this.userAddress2 = userAddress2;
      }
    public String getUserFirstName()
      { 
       return userFirstName;
      }
    public void setUserFirstName(String userFirstName)
      { 
      this.userFirstName = userFirstName;
      }
    public String getUserId()
      { 
       return userId;
      }
    public void setUserId(String userId)
      { 
      this.userId = userId;
      }
    public String getUserLastName()
      { 
       return userLastName;
      }
    public void setUserLastName(String userLastName)
      { 
      this.userLastName = userLastName;
      }
    public String getUserMI()
      { 
       return userMI;
      }
    public void setUserMI(String userMI)
      { 
      this.userMI = userMI;
      }
    public String getUserPassword()
      { 
       return userPassword;
      }
    public void setUserPassword(String userPassword)
      { 
      this.userPassword = userPassword;
      }
    public String getUserType()
      { 
       return userType;
      }
    public void setUserType(String userType)
      { 
      this.userType = userType;
      }
    public int getUserPersonId()
      { 
       return userPersonId;
      }
    public void setUserPersonId(int userPersonId)
      { 
      this.userPersonId = userPersonId;
      }
    public String getUserCity()
      { 
       return userCity;
      }
    public void setUserCity(String userCity)
      { 
      this.userCity = userCity;
      }
    public String getUserCounty()
      { 
       return userCounty;
      }
    public void setUserCounty(String userCounty)
      { 
      this.userCounty = userCounty;
      }
    public String getUserFax()
      { 
       return userFax;
      }
    public void setUserFax(String userFax)
      { 
      this.userFax = userFax;
      }
    public String getUserPhone()
      { 
       return userPhone;
      }
    public void setUserPhone(String userPhone)
      { 
      this.userPhone = userPhone;
      }
    public String getUserState()
      { 
       return userState;
      }
    public void setUserState(String userState)
      { 
      this.userState = userState;
      }
    public String getUserStatus()
      { 
       return userStatus;
      }
    public void setUserStatus(String userStatus)
      { 
      this.userStatus = userStatus;
      }
    public String getUserZip()
      { 
       return userZip;
      }
    public void setUserZip(String userZip)
      { 
      this.userZip = userZip;
      }
    public String getUserZip2()
      { 
       return userZip2;
      }
    public void setUserZip2(String userZip2)
      { 
      this.userZip2 = userZip2;
      }
    public int getPlansCount () 
    {
      return (plans == null) ? 0 : plans.size();
    }

    public List getPlans()
    {
      if(plans == null) 
      {
        plans = new ArrayList();
      } 
      return plans;
    }

    public void setPlans(List plans)
    {
      this.plans = plans;
    }
    public int getCoursesCount () 
    {
      return (courses == null) ? 0 : courses.size();
    }

    public List getCourses()
    {
      if(courses == null) 
      {
        courses = new ArrayList();
      } 
      return courses;
    }

    public void setCourses(List courses)
    {
      this.courses = courses;
    }
    public void setRegistrationFee(double registrationFee)
      { 
      this.registrationFee = registrationFee;
      }
    public double getRegistrationFee()
      { 
       return registrationFee;
      }
      
    public String getUserPsid()
      { 
       return userPsid;
      }
    public void setUserPsid(String userPsid)
      { 
      this.userPsid = userPsid;
      }
}
