package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;


public class EmsInstAgmtsDAO extends HsDAO
{
  public EmsInstAgmtsDAO()
  {
  }
  public List selectAgmtsList(String sql,int institutionId) throws SQLException, Exception 
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
      pstmt.setInt(1,institutionId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
       EmsInstAgmts agmts = new EmsInstAgmts();
      agmts.setEffectiveDate(rs.getDate(3));
      agmts.setExpirationDate(rs.getDate(4));
      agmts.setName(rs.getString(5));
      agmts.setRemarks(rs.getString(6));
      agmts.setAgreementId(rs.getInt(1));
      agmts.setInstitutionId(rs.getInt(2));
      
        
        list.add(agmts);
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
  public EmsInstAgmts selectAgmts(int AgmtsId) throws SQLException, Exception 
  {
     EmsInstAgmts agmts = new EmsInstAgmts();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      agmts = selectAgmts(conn,AgmtsId);
      return agmts;
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
   public EmsInstAgmts selectAgmts(Connection conn,int agmtsId) throws SQLException, Exception 
  {
    EmsInstAgmts agmts = new EmsInstAgmts();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {System.out.println(agmtsId);
      pstmt = conn.prepareStatement(EmsSQL.SELECT_INST_AGMTS);
      System.out.println(pstmt);
      pstmt.clearParameters();
      pstmt.setInt(1,agmtsId);
      rs = pstmt.executeQuery();
      System.out.println(" select Agmts2");
      if(rs.next()) 
      {
      agmts.setEffectiveDate(rs.getDate(3));
      agmts.setExpirationDate(rs.getDate(4));
      agmts.setName(rs.getString(5));
      agmts.setRemarks(rs.getString(6));
      agmts.setAgreementId(rs.getInt(1));
      agmts.setInstitutionId(rs.getInt(2));
        

      }System.out.println("done select Agmts");
      return agmts;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateAgmts(EmsInstAgmts agmts) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_INST_AGMTS);
          pstmt.setString(1,agmts.getEffectiveDateString());
          pstmt.setString(2,agmts.getExpirationDateString());
          pstmt.setString(3,agmts.getName());
          pstmt.setString(4,agmts.getRemarks());
          pstmt.setInt(5,agmts.getAgreementId());
          
          
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
   public void insertAgmts(EmsInstAgmts agmts, int institutionId) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.INSERT_INST_AGMTS);
          pstmt.setInt(1,institutionId);
          pstmt.setString(2,agmts.getEffectiveDateString());
          pstmt.setString(3,agmts.getExpirationDateString());
          pstmt.setString(4,agmts.getName());
          pstmt.setString(5,agmts.getRemarks());
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

