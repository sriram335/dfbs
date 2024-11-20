package FireBldEducation.control.form;
import FireBldEducation.to.*;
import hs.control.form.*;
import hs.to.*;
import java.util.Map;
import org.apache.struts.action.*;
import java.util.Date;

public class FireBldEducationCourseForm extends ActionForm{
    private int courseId;
    private String startDate;
    private String courseLength;
    private String courseName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String zip2;
    private String county;
    private String classLocation;
    private String courseDescription;
    private int classSize;
    private int classVacancy;
    private String courseHours;
    public FireBldEducationCourseForm() {
    }
  public String getCourseHours()
    { 
     return courseHours;
    }
  public void setCourseHours(String courseHours)
    { 
    this.courseHours = courseHours;
    }
    public String getStartDate()
      { 
       return startDate;
      }
    public void setStartDate(String startDate)
      { 
      this.startDate = startDate;
      }
    public String getAddress1()
      { 
       return address1;
      }
    public void setAddress1(String address1)
      { 
      this.address1 = address1;
      }
    public String getAddress2()
      { 
       return address2;
      }
    public void setAddress2(String address2)
      { 
      this.address2 = address2;
      }
    public String getCity()
      { 
       return city;
      }
    public void setCity(String city)
      { 
      this.city = city;
      }
    public String getClassLocation()
      { 
       return classLocation;
      }
    public void setClassLocation(String classLocation)
      { 
      this.classLocation = classLocation;
      }
    public String getCounty()
      { 
       return county;
      }
    public void setCounty(String county)
      { 
      this.county = county;
      }
    public String getCourseDescription()
      { 
       return courseDescription;
      }
    public void setCourseDescription(String courseDescription)
      { 
      this.courseDescription = courseDescription;
      }
    public String getCourseLength()
      { 
       return courseLength;
      }
    public void setCourseLength(String courseLength)
      { 
      this.courseLength = courseLength;
      }
    public String getState()
      { 
       return state;
      }
    public void setState(String state)
      { 
      this.state = state;
      }
    public String getZip()
      { 
       return zip;
      }
    public void setZip(String zip)
      { 
      this.zip = zip;
      }
    public String getZip2()
      { 
       return zip2;
      }
    public void setZip2(String zip2)
      { 
      this.zip2 = zip2;
      }
    public int getClassSize()
      { 
       return classSize;
      }
    public void setClassSize(int classSize)
      { 
      this.classSize = classSize;
      }
    public int getClassVacancy()
      { 
       return classVacancy;
      }
    public void setClassVacancy(int classVacancy)
      { 
      this.classVacancy = classVacancy;
      }
    public int getCourseId()
      { 
       return courseId;
      }
    public void setCourseId(int courseId)
      { 
      this.courseId = courseId;
      }
    public String getCourseName()
      { 
       return courseName;
      }
    public void setCourseName(String courseName)
      { 
      this.courseName = courseName;
      }
    public FireBldEducationCourse  getCodeCourse()
    {
    
    FireBldEducationCourse codeCourse =new FireBldEducationCourse();
    codeCourse.setStartDateString(getStartDate());
    codeCourse.setAddress1(getAddress1());
    codeCourse.setAddress2(getAddress2());
    codeCourse.setCity(getCity());
    codeCourse.setClassLocation(getClassLocation());
    codeCourse.setCounty(getCounty());
    codeCourse.setCourseDescription(getCourseDescription());
    codeCourse.setCourseLength(getCourseLength());
    codeCourse.setState(getState());
    codeCourse.setZip(getZip());
    codeCourse.setZip2(getZip2());
    codeCourse.setClassSize(getClassSize());
    codeCourse.setCourseId(getCourseId());
    codeCourse.setCourseName(getCourseName());
    codeCourse.setClassVacancy(getClassVacancy());
      codeCourse.setCourseHours(getCourseHours());
    return codeCourse;
    }
    public void setProperties(FireBldEducationCourse codeCourse) 
    {
    this.setStartDate(codeCourse.getStartDateString());
    this.setAddress1(codeCourse.getAddress1());
    this.setAddress2(codeCourse.getAddress2());
    this.setCity(codeCourse.getCity());
    this.setClassLocation(codeCourse.getClassLocation());
    this.setCounty(codeCourse.getCounty());
    this.setCourseDescription(codeCourse.getCourseDescription());
    this.setCourseLength(codeCourse.getCourseLength());
    this.setState(codeCourse.getState());
    this.setZip(codeCourse.getZip());
    this.setZip2(codeCourse.getZip2());
    this.setClassSize(codeCourse.getClassSize());
    this.setCourseId(codeCourse.getCourseId());
    this.setCourseName(codeCourse.getCourseName());
    this.setClassVacancy(codeCourse.getClassVacancy());
      this.setCourseHours(codeCourse.getCourseHours());
    }
}
