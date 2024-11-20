package cigarette.data;

import cigarette.to.*;
import cigarette.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;



public class CigaretteApplicationDAO extends HsDAO
{
  public CigaretteApplicationDAO()
  {
  }
   public int insertApplication(int companyId,int receiptId,CigaretteApplication application) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
   try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,CigarettePermitSQL.SELECT_NEXT_APP_ID);
      application.setAppId(id);
     pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_APPLICATION);
      pstmt.clearParameters();
      pstmt.setString(2,application.getAppType());
      pstmt.setString(3,application.getContact());
      pstmt.setString(7,application.getEmail());
      pstmt.setString(6,application.getFax());
      pstmt.setString(5,application.getPhone());
      pstmt.setString(4,application.getTitle());
      pstmt.setInt(1,application.getAppId());
      pstmt.setInt(8,companyId);
      pstmt.setInt(9,receiptId);
      pstmt.setString(10,application.getComments());
      pstmt.execute();
      
    
      
      conn.commit();
      return id;
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

  
   public CigaretteApplication selectApplication(String applicationId) throws SQLException, Exception 
  {
  
    CigaretteApplication application = new CigaretteApplication();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_APPLICATION_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(1,applicationId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
      application.setAppDate(rs.getDate(2));
      application.setAppType(rs.getString(3));
      application.setContact(rs.getString(4));
      application.setEmail(rs.getString(8));
      application.setFax(rs.getString(7));
      application.setPhone(rs.getString(6));
      application.setTitle(rs.getString(5));
      application.setAppId(rs.getInt(1));
      application.setCompanyId(rs.getInt(9));
       application.setComments(rs.getString(10)); 
       application.setAppIssueDate(rs.getDate(11));
       application.setAppExpDate(rs.getDate(12));
       application.setAppStatus(rs.getString(13));
    }
     return application;
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
   public void updateApplication(CigaretteApplication application) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_APPLICATION_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(2,application.getAppIssueDateString());
      pstmt.setString(3,application.getAppExpDateString());
      pstmt.setString(4,application.getAppStatus());
      pstmt.setInt(5,application.getAppId());
      pstmt.setString(1,application.getComments());
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
   public int addNewUser (int appId) throws SQLException, Exception 
  {
    Connection conn = null;
     CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call create_cig_users_new(?,?) }");
      proc.setInt(1, appId);
      proc.registerOutParameter(2,java.sql.Types.INTEGER);
      proc.execute();

     return(proc.getInt(2));
    
     
      
    } catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try { proc.close();
        conn.close();
      } catch (Exception e) {}
    }
  }
  
     public int selectMaxAppId(int companyId) throws SQLException, Exception 
  {
  
    CigaretteApplication application = new CigaretteApplication();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int appId=0;  
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_MAX_APPLICATION_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,companyId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
       appId=(rs.getInt(1));
      }
     return appId;
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
