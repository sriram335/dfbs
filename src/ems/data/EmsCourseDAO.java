package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
public class EmsCourseDAO  extends HsDAO
{
  public EmsCourseDAO()
  {
  }
    public List selectCourseList(String sql,int param) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.clearParameters();
      if(param != 0) {
        pstmt.setInt(1,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsCourse course = new EmsCourse();
           course.setCompletionDate(rs.getDate(6));
          course.setReceivedDate(rs.getDate(20));
          course.setStartDate(rs.getDate(5));
          course.setAddress1(rs.getString(12));
          course.setAddress2(rs.getString(13));
          course.setCity(rs.getString(14));
          course.setClassLocation(rs.getString(21));
          course.setCounty(rs.getString(18));
          course.setCourseLength(rs.getString(7));
          course.setCourseNumber(rs.getString(4));
          course.setDayClassMeet(rs.getString(2));
          course.setDayClassTime(rs.getString(3));
          course.setEmail(rs.getString(19));
          course.setFirstName(rs.getString(9));
          course.setLastName(rs.getString(10));
          course.setMi(rs.getString(11));
          course.setState(rs.getString(15));
          course.setTelephone(rs.getString(22));
          course.setZip(rs.getString(16));
          course.setZip2(rs.getString(17));
          course.setCourseId(rs.getInt(1));
          course.setCrssCourseId(rs.getInt(8));
           course.setApprovedDate(rs.getDate(23));
          course.setPendingItems(rs.getString(24));
          course.setCompReport(rs.getString(25));
          course.setRecCreatedBy(rs.getString(26));
        
        list.add(course);
      }
     
      return list;
    }
    finally 
    {
     try {
        conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public EmsCourse selectCourse(int courseId) throws SQLException, Exception 
  {
     EmsCourse course = new EmsCourse();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      course = selectCourse(conn,courseId);
      return course;
    }
    finally 
    {
     try {
        conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public EmsCourse selectCourseByNumber(String courseNumber) throws SQLException, Exception 
  {
     EmsCourse course = new EmsCourse();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      course = selectCourseByNumber(conn,courseNumber);
      return course;
    }
    finally 
    {
     try {
        conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public EmsCourse selectCourse(Connection conn,int courseId) throws SQLException, Exception 
  {
    EmsCourse course = new EmsCourse();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE);
      pstmt.clearParameters();
      pstmt.setInt(1,courseId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
          course.setCompletionDate(rs.getDate(6));
          course.setReceivedDate(rs.getDate(20));
          course.setStartDate(rs.getDate(5));
          course.setAddress1(rs.getString(12));
          course.setAddress2(rs.getString(13));
          course.setCity(rs.getString(14));
          course.setClassLocation(rs.getString(21));
          course.setCounty(rs.getString(18));
          course.setCourseLength(rs.getString(7));
          course.setCourseNumber(rs.getString(4));
          course.setDayClassMeet(rs.getString(2));
          course.setDayClassTime(rs.getString(3));
          course.setEmail(rs.getString(19));
          course.setFirstName(rs.getString(9));
          course.setLastName(rs.getString(10));
          course.setMi(rs.getString(11));
          course.setState(rs.getString(15));
          course.setTelephone(rs.getString(22));
          course.setZip(rs.getString(16));
          course.setZip2(rs.getString(17));
          course.setCourseId(rs.getInt(1));
          course.setCrssCourseId(rs.getInt(8));
          course.setInstitutionId(rs.getInt(23));
          course.setApprovedDate(rs.getDate(24));
          course.setPendingItems(rs.getString(25));
          course.setCompReport(rs.getString(26));
          course.setRecCreatedBy(rs.getString(27));
      }
      course.setInstructors(EmsInstructorsDAO.selectInstructorListCourse(conn,course));
      return course;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateCourse(EmsCourse course) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_COURSE);
          pstmt.clearParameters();
          pstmt.setString(6,course.getCompletionDateString());
          pstmt.setString(20,course.getReceivedDateString());
          pstmt.setString(5,course.getStartDateString());
          pstmt.setString(12,course.getAddress1());
          pstmt.setString(13,course.getAddress2());
          pstmt.setString(14,course.getCity());
          pstmt.setString(21,course.getClassLocation());
          pstmt.setString(18,course.getCounty());
          pstmt.setString(7,course.getCourseLength());
          pstmt.setString(4,course.getCourseNumber());
          pstmt.setString(2,course.getDayClassMeet());
          pstmt.setString(3,course.getDayClassTime());
          pstmt.setString(19,course.getEmail());
          pstmt.setString(9,course.getFirstName());
          pstmt.setString(10,course.getLastName());
          pstmt.setString(11,course.getMi());
          pstmt.setString(15,course.getState());
          pstmt.setString(1,course.getTelephone());
          pstmt.setString(16,course.getZip());
          pstmt.setString(17,course.getZip2());
          pstmt.setInt(26,course.getCourseId());
          pstmt.setInt(8,course.getCrssCourseId());
          pstmt.setString(22,course.getApprovedDateString());
          pstmt.setString(23,course.getPendingItems());
          pstmt.setString(24,course.getCompReport());
          pstmt.setString(25,course.getRecCreatedBy());
          
          
          
           pstmt.execute();
           conn.commit();
      
    } catch (Exception ex)
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } 
   public void insertCourse(EmsCourse course,int institutionId,String courseNumber, String currentUser) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          int id = this.getId(conn,"select ems_ems_course_id.nextval from dual");
          pstmt = conn.prepareStatement(EmsSQL.INSERT_COURSE);
          pstmt.clearParameters();
          pstmt.setString(5,course.getCompletionDateString());
          pstmt.setString(19,course.getReceivedDateString());
          pstmt.setString(4,course.getStartDateString());
          pstmt.setString(11,course.getAddress1());
          pstmt.setString(12,course.getAddress2());
          pstmt.setString(13,course.getCity());
          pstmt.setString(20,course.getClassLocation());
          pstmt.setString(17,course.getCounty());
          pstmt.setString(6,course.getCourseLength());
          pstmt.setString(3,courseNumber);
          course.setCourseNumber(courseNumber);
          pstmt.setString(1,course.getDayClassMeet());
          pstmt.setString(2,course.getDayClassTime());
          pstmt.setString(18,course.getEmail());
          pstmt.setString(8,course.getFirstName());
          pstmt.setString(9,course.getLastName());
          pstmt.setString(10,course.getMi());
          pstmt.setString(14,course.getState());
          pstmt.setString(21,course.getTelephone());
          pstmt.setString(15,course.getZip());
          pstmt.setString(16,course.getZip2());
          pstmt.setInt(7,course.getCrssCourseId());
          pstmt.setInt(22,institutionId);
          pstmt.setInt(23,id);
          pstmt.setString(24,course.getApprovedDateString());
          pstmt.setString(25,course.getPendingItems());
          pstmt.setString(26,course.getCompReport());
          pstmt.setString(27,currentUser);
          course.setRecCreatedBy(currentUser);
          course.setCourseId(id);
          
          
          pstmt.execute();
           conn.commit();
    } catch (Exception ex)
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } 
 
    public String selectCountyCode(String CountyName) throws SQLException, Exception 
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String countyCode="";
    
     try 
    {
     conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COUNTY_CODE);
      pstmt.clearParameters();
      pstmt.setString(1,CountyName);
      rs = pstmt.executeQuery();
      
      if(rs.next()) 
      {
         countyCode=(rs.getString(1));
          
      }
      return countyCode;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
    public String selectCourseType(int crssCourseId) throws SQLException, Exception 
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String courseType="";
    
     try 
    {
     conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE_TYPE);
     // System.out.println(pstmt);
      pstmt.clearParameters();
      pstmt.setInt(1,crssCourseId);
      rs = pstmt.executeQuery();
      
      if(rs.next()) 
      {
         courseType=(rs.getString(1));
          
      }
      return courseType;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public void insertCourseCalender(EmsCourse course,String courseManager) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          String courseName = this.selectCourseName(course.getCrssCourseId());
          pstmt = conn.prepareStatement(EmsSQL.INSERT_DHS_CALENDER);
          pstmt.clearParameters();
          pstmt.setString(5,course.getStartDateString());
          pstmt.setString(3,course.getClassLocation());
          pstmt.setString(4,course.getCity()+":"+course.getCounty());
          pstmt.setString(6,course.getCompletionDateString());
          pstmt.setString(1,course.getCourseNumber());
           pstmt.setString(7,course.getDayClassTime());
          pstmt.setString(2,courseName);
          pstmt.setString(8,courseManager);
          pstmt.setString(9,course.getTelephone());
          pstmt.setString(10,course.getRecCreatedBy());
          pstmt.setString(11,course.getRecCreatedBy().substring(1,15));
         // pstmt.setString(12,"for details use link https://oas.dhs.in.gov/dfbs/ems/course.do?method=courseDetail&courseId="+course.getCourseId());
          pstmt.setString(12,"notes");
          
           pstmt.execute();
           conn.commit();
      
    } catch (Exception ex)
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } 
   public EmsCourse selectCourseByNumber(Connection conn,String courseNumber) throws SQLException, Exception 
  {
    EmsCourse course = new EmsCourse();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE_BY_NUMBER);
      pstmt.clearParameters();
      pstmt.setString(1,courseNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
          course.setCompletionDate(rs.getDate(6));
          course.setReceivedDate(rs.getDate(20));
          course.setStartDate(rs.getDate(5));
          course.setAddress1(rs.getString(12));
          course.setAddress2(rs.getString(13));
          course.setCity(rs.getString(14));
          course.setClassLocation(rs.getString(21));
          course.setCounty(rs.getString(18));
          course.setCourseLength(rs.getString(7));
          course.setCourseNumber(rs.getString(4));
          course.setDayClassMeet(rs.getString(2));
          course.setDayClassTime(rs.getString(3));
          course.setEmail(rs.getString(19));
          course.setFirstName(rs.getString(9));
          course.setLastName(rs.getString(10));
          course.setMi(rs.getString(11));
          course.setState(rs.getString(15));
          course.setTelephone(rs.getString(22));
          course.setZip(rs.getString(16));
          course.setZip2(rs.getString(17));
          course.setCourseId(rs.getInt(1));
          course.setCrssCourseId(rs.getInt(8));
          course.setInstitutionId(rs.getInt(23));
          course.setApprovedDate(rs.getDate(24));
          course.setPendingItems(rs.getString(25));
          course.setCompReport(rs.getString(26));
          course.setRecCreatedBy(rs.getString(27));
      }
      course.setInstructors(EmsInstructorsDAO.selectInstructorListCourse(conn,course));
      return course;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public int verifyCourseDate(String courseDate) throws SQLException, Exception 
  {
    EmsCourse course = new EmsCourse();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    int countDays =0;
     try 
    {     conn = getConnection();
          conn.setAutoCommit(true);
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,courseDate);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        countDays=  rs.getInt(1);
      }
       return countDays;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
    public int selectCountCourse(String courseType,String startDate) throws SQLException, Exception 
  {
    EmsCourse course = new EmsCourse();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    int countCourse =0;
     try 
    {     conn = getConnection();
          conn.setAutoCommit(true);
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COUNT_COURSE);
      pstmt.clearParameters();
      pstmt.setString(1,courseType);
      pstmt.setString(2,startDate.substring(6));
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        countCourse=  rs.getInt(1);
      }
       return countCourse;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateCourseApproval(EmsCourse course) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_COURSE_APPROVAL);
          pstmt.clearParameters();
          
          pstmt.setInt(1,course.getCourseId());
           pstmt.execute();
           conn.commit();
      
    } catch (Exception ex)
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } 
    public String selectCourseName(int courseId) throws SQLException, Exception 
  {
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String courseName ="";
     try 
    {     conn = getConnection();
          conn.setAutoCommit(true);
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE_NAME);
      pstmt.clearParameters();
      pstmt.setInt(1,courseId);
       rs = pstmt.executeQuery();
      
      if(rs.next()) 
      {
        courseName=  rs.getString(1);
      }
       return courseName;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
    public String selectInstEmailMD(int institutionId) throws SQLException, Exception 
  {
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String emailMD ="";
     try 
    {     conn = getConnection();
          conn.setAutoCommit(true);
      pstmt = conn.prepareStatement(EmsSQL.SELECT_TI_MD_EMAIL);
      pstmt.clearParameters(); 
      pstmt.setInt(1,institutionId);
       rs = pstmt.executeQuery();
      
      if(rs.next()) 
      {
        emailMD=  rs.getString(1);
      }
       return emailMD;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
    public String selectInstEmailTIO(int institutionId) throws SQLException, Exception 
  {
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String emailTIO ="";
     try 
    {     conn = getConnection();
          conn.setAutoCommit(true);
      pstmt = conn.prepareStatement(EmsSQL.SELECT_TI_OFFICIAL_EMAIL);
      pstmt.clearParameters();
      pstmt.setInt(1,institutionId);
       rs = pstmt.executeQuery();
      
      if(rs.next()) 
      {
        emailTIO=  rs.getString(1);
      }
       return emailTIO;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public List selectPersonEmailList(int institutionId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_PERSON_LIST_INST_EMAILS);
      pstmt.clearParameters();
      if(institutionId != 0) {
        pstmt.setInt(1,institutionId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsPerson person = new EmsPerson();
         person.setEmsPersonId(rs.getInt(1));
         person.setPersonFirstName(rs.getString(2));
         person.setPersonLastName(rs.getString(3));
         person.setPersonMi(rs.getString(4));
         person.setPersonAddress1(rs.getString(8));
         person.setPersonAddress2(rs.getString(9));
         person.setPersonCity(rs.getString(10));
         person.setPersonState(rs.getString(11));
         person.setPersonZip(rs.getString(12));
         person.setPersonZip2(rs.getString(13));
         person.setPersonEmail(rs.getString(5));
         person.setPersonPhone(rs.getString(6));
         person.setPersonFax(rs.getString(7));
         person.setPersonTitle(rs.getString(15));
        list.add(person);
      }
       return list;
    }
    finally 
    {
     try {
        conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public void updatePersonEmail(EmsPerson person,String personEmail) throws SQLException, Exception 
  {
         Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_PERSON_EMAIL);
          pstmt.clearParameters();
          
         
          pstmt.setString(1,personEmail);
          pstmt.setInt(2,person.getEmsPersonId());
          
          pstmt.execute();
           conn.commit();
         
      
    } catch (Exception ex)
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } 
}