package ems.data;
import ems.to.*;
import ems.data.*;

import ems.to.EmsLevel;

import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

public class EmsLevelDAO extends HsDAO
{
  public EmsLevelDAO()
  {
  }
  public List selectLevelList(String sql,int param) throws SQLException, Exception 
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
      
        EmsLevel level = new EmsLevel();
        level.setHospitalId(rs.getInt(4));
        level.setInstitutionId(rs.getInt(5));
        level.setLevelId(rs.getInt(1));
        level.setLevelName(rs.getString(2));
        level.setProviderId(rs.getInt(3));
        list.add(level);
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
 
   public void insertLevel(EmsLevel level,int providerId) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.INSERT_LEVEL);
          pstmt.clearParameters();
          
          pstmt.setString(1,level.getLevelName());
          pstmt.setInt(2,providerId);
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
 public EmsLevel selectLevel(int levelId) throws SQLException, Exception 
  {
     EmsLevel level = new EmsLevel();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      level = selectLevel(conn,levelId);
      return level;
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
   public EmsLevel selectLevel(Connection conn,int levelId) throws SQLException, Exception 
  {
    EmsLevel level = new EmsLevel();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {System.out.println("level:" + levelId);
        pstmt = conn.prepareStatement(EmsSQL.SELECT_LEVEL);
     pstmt.clearParameters();
      pstmt.setInt(1,levelId);
      rs = pstmt.executeQuery();
     
      if(rs.next()) 
      {    System.out.println(rs.getString(2));
          level.setLevelName(rs.getString(2));
          level.setLevelId(rs.getInt(1));
          level.setProviderId(rs.getInt(3));
         
      }System.out.println("done select level");
      return level;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateLevel(EmsLevel level) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_LEVEL);
          pstmt.clearParameters();
          pstmt.setString(1,level.getLevelName());
          pstmt.setInt(2,level.getLevelId());
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
  
