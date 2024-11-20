package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

public class advancedSearchDAO extends HsDAO
{
  public advancedSearchDAO()
  {
  }
    public List selectCourseSearch(String sqlSelectCounty,String sqlSelectZip,String startDate,String endDate) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String sqlBuild ="";
    try 
    {
   
      conn = getConnection();
      sqlBuild = EmsSQL.SELECT_COURSE_LIST_SEARCH;
      if (sqlSelectCounty.length() >8 )
      {
       sqlBuild = sqlBuild + " and institution_county " +sqlSelectCounty;
      }
       if (sqlSelectZip.length() >8 )
      {
       sqlBuild = sqlBuild + " and institution_zip " +sqlSelectZip;
      }
       if (startDate.length() >5 )
      {
       sqlBuild = sqlBuild + " and starting_date >=to_date( '"+startDate+"','mm/dd/yyyy')";
      }
       if (endDate.length() >5 )
      {
        sqlBuild = sqlBuild + " and starting_date <=to_date( '"+endDate+"','mm/dd/yyyy')";
      }
      pstmt = conn.prepareStatement(sqlBuild);
      pstmt.clearParameters();
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
          course.setInstitutionName(rs.getString(27));
          course.setInstitutionId(rs.getInt(28));
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
}