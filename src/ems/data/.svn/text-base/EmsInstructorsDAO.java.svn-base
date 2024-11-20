package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
public class EmsInstructorsDAO extends HsDAO
{
  public EmsInstructorsDAO()
  {
  }
  public List selectPersonList(int courseId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE_INSTRUCTOR_LIST);
      pstmt.clearParameters();
      if(courseId != 0) {
        pstmt.setInt(1,courseId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsInstructors instructor = new EmsInstructors();
         instructor.setPersonId(rs.getString(1));
         instructor.setPersonFirstName(rs.getString(2));
         instructor.setPersonLastName(rs.getString(3));
         instructor.setPersonMi(rs.getString(4));
         instructor.setPersonType(rs.getString(5));
         instructor.setPsId(rs.getString(6));
        
        list.add(instructor);
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
 /* public EmsInstructors selectInstructor(String instructorId) throws SQLException, Exception 
  {
    EmsInstructors instructor = new EmsInstructors();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      instructor = selectInstructor(conn,instructorId);
      return instructor;
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
   public EmsInstructors selectInstructor(Connection conn,String instructorId) throws SQLException, Exception 
  {
    EmsInstructors instructor = new EmsInstructors();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_ACADIS_PERSON);
     
      pstmt.clearParameters();
      pstmt.setString(1,instructorId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
         instructor.setPersonId(rs.getString(1));
         instructor.setPersonFirstName(rs.getString(3));
         instructor.setPersonLastName(rs.getString(2));
         instructor.setPersonMi(rs.getString(4));
         instructor.setPersonAddress1(rs.getString(5));
         instructor.setPersonAddress2(rs.getString(6));
         instructor.setPersonCity(rs.getString(7));
         instructor.setPersonState(rs.getString(8));
         instructor.setPersonZip(rs.getString(9));
         instructor.setPersonCounty(rs.getString(10));
         instructor.setPsId(rs.getString(11));
         instructor.setCertList(this.selectCertList(conn,instructorId));
        
      }
      return instructor;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } */
   public void removeInstructor(String instructorId) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.REMOVE_EMS_INSRTUCTOR);
          pstmt.clearParameters();
          pstmt.setString(1,instructorId);
         
          
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
  
  
  public void addNewInstructor(EmsCourse course, EmsInstructors instructor ) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          
          pstmt = conn.prepareStatement(EmsSQL.INSERT_EMS_INSRTUCTOR);
          
          pstmt.clearParameters();
          
          pstmt.setInt(1,course.getCourseId());
          pstmt.setString(2,instructor.getPersonType());
          pstmt.setString(3,instructor.getPersonLastName()); 
          pstmt.setString(4,instructor.getPersonFirstName()); 
          pstmt.setString(5,instructor.getPsId()); 
          
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
 
 protected static List selectInstructorListCourse(Connection conn,EmsCourse course) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int instCount=0;
    try 
    {
   
      pstmt = conn.prepareStatement(EmsSQL.SELECT_COURSE_INSTRUCTOR_LIST);
      pstmt.clearParameters();
      pstmt.setInt(1,course.getCourseId());System.out.println(" select inst");
      rs = pstmt.executeQuery();System.out.println(" execute query inst");
      while(rs.next()) 
      {
      
         EmsInstructors instructor = new EmsInstructors();
         instructor.setPersonId(rs.getString(1));
         instructor.setPersonFirstName(rs.getString(2));
         instructor.setPersonLastName(rs.getString(3));
         instructor.setPersonType(rs.getString(4));
        instructor.setPsId(rs.getString(5));
         instCount=instCount+1;
        list.add(instructor);
      }
       course.setInstructorCount(instCount);System.out.println(" execute query inst2");
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
  
  public List selectInstructorListOrganization(String orgName) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_INST_ORGANIZATION_LIST);
      pstmt.clearParameters();
      pstmt.setString(1,orgName);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
         EmsInstructors instructor = new EmsInstructors();
         instructor.setPersonId(rs.getString(1));
         instructor.setPersonFirstName(rs.getString(2));
         instructor.setPersonLastName(rs.getString(3));
         instructor.setPersonMi(rs.getString(4));
         instructor.setPersonCity(rs.getString(5));
         instructor.setPsId(rs.getString(6));
         instructor.setOrgName(rs.getString(7));
         
        list.add(instructor);
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
}