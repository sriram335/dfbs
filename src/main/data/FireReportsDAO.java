package main.data;
import hs.data.*;
import main.to.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;


public class FireReportsDAO  extends HsDAO
{
  public FireReportsDAO()
  {
  }
 public List selectReportsGroup(String sql) throws SQLException, Exception 
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
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
         FireReportsGroup reportList = new FireReportsGroup();
         reportList.setReportGroup(rs.getString(1));
         list.add(reportList);
               
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
  
      public List selectReportNames(String sql, String param) throws SQLException, Exception 
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
       if(param != null) {
        pstmt.setString(1,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
         FireReportsName reportNames = new FireReportsName();
         reportNames.setReportName(rs.getString(1));
         reportNames.setReportCode(rs.getString(2));
         list.add(reportNames);
               
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