package codeEducation.to;
import hs.to.*;
import java.io.Serializable;
import java.util.*;
import java.text.*;

public class CodeEducationCourse implements Serializable {
      private int courseId;
      private Date startDate;
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
    public CodeEducationCourse() {
    }
    public int getClassVacancy()
      { 
       return classVacancy;
      }
    public void setClassVacancy(int classVacancy)
      { 
      this.classVacancy = classVacancy;
      }
    public Date getStartDate()
      { 
       return startDate;
      }
    public void setStartDate(Date startDate)
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
    public void setStartDateString(String dateParam)
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    try {
    startDate = formatter.parse(dateParam);
    } catch (Exception e)
    {startDate = null; }
    }
    public String getStartDateString()
    {
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    if(startDate == null)
    { return ""; }
    else { return formatter.format(startDate); }
    }
}
