package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;


public class EmsWaiverDAO extends HsDAO
{
  public EmsWaiverDAO()
  {
  }
  
   public List selectWaiverList(String sql,int param) throws SQLException, Exception 
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
      
        EmsWaiver waiver = new EmsWaiver();
        waiver.setWaiverId(rs.getInt(1));
        waiver.setWaiverCode(rs.getString(2));
        waiver.setWaiverDate(rs.getDate(3));
        waiver.setLevelId(rs.getInt(4));
         list.add(waiver);
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
 
   public void insertWaiver(EmsWaiver waiver, int levelId) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.INSERT_WAIVER);
          pstmt.clearParameters();
          
          
          pstmt.setString(1,waiver.getWaiverCode());
          pstmt.setInt(3,levelId);
          pstmt.setString(2,waiver.getWaiverDateString());
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
   public EmsWaiver selectWaiver(int waiverId) throws SQLException, Exception 
  {
     EmsWaiver waiver = new EmsWaiver();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      waiver = selectWaiver(conn,waiverId);
      return waiver;
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
   public EmsWaiver selectWaiver(Connection conn,int waiverId) throws SQLException, Exception 
  {
    EmsWaiver waiver = new EmsWaiver();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
        pstmt = conn.prepareStatement(EmsSQL.SELECT_WAIVER);
      pstmt.clearParameters();
      pstmt.setInt(1,waiverId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
          waiver.setWaiverCode(rs.getString(6));
          waiver.setWaiverDate(rs.getDate(7));
          waiver.setWaiverId(rs.getInt(14));
          waiver.setLevelId(rs.getInt(4));
         
      }
      return waiver;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateWaiver(EmsWaiver waiver) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_WAIVER);
          pstmt.clearParameters();
          pstmt.setString(1,waiver.getWaiverCode());
          pstmt.setInt(3,waiver.getLevelId());
          pstmt.setString(2,waiver.getWaiverDateString());
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
  

