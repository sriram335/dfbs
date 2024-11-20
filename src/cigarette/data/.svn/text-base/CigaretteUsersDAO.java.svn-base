package cigarette.data;
import cigarette.to.*;
import cigarette.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;



public class CigaretteUsersDAO extends HsDAO
{
  public CigaretteUsersDAO()
  {
  }
   public int insertCigUser(CigaretteUsers cigUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,CigarettePermitSQL.SELECT_NEXT_CIGARETTE_USER_ID);
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_CIG_USERS);
      pstmt.clearParameters();
      pstmt.setString(6,cigUser.getUserPasswordExpiresDateString());
      pstmt.setString(2,cigUser.getUserFirstName());
      pstmt.setString(3,cigUser.getUserLastName());
      pstmt.setString(7,cigUser.getUserLoginId());
      pstmt.setString(4,cigUser.getUserPassword());
      pstmt.setString(5,"A");
      pstmt.setString(8,cigUser.getUserTelephone());
      pstmt.setInt(9,cigUser.getCompanyId());
      pstmt.setInt(1,id);
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
    public CigaretteUsers selectCigUser(int userId) throws SQLException, Exception 
  {
  
    CigaretteUsers cigUser = new CigaretteUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_CIG_USERS_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,userId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
    }
     return cigUser;
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
 public void updateCigUser(CigaretteUsers cigUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_CIG_USERS);
      pstmt.clearParameters();
      pstmt.setString(5,cigUser.getUserPasswordExpiresDateString());
      pstmt.setString(1,cigUser.getUserFirstName());
      pstmt.setString(2,cigUser.getUserLastName());
      pstmt.setString(6,cigUser.getUserLoginId());
      pstmt.setString(3,cigUser.getUserPassword());
      pstmt.setString(4,cigUser.getUserStatus());
      pstmt.setString(7,cigUser.getUserTelephone());
      pstmt.setInt(8,cigUser.getUserId());
      pstmt.execute();
      
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
   public List selectUserList(String psql,String byLetter) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(psql);
      pstmt.clearParameters();
      if(byLetter != null) {
        pstmt.setString(1,byLetter);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        CigaretteUsers cigUser = new CigaretteUsers();
        
         cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
        list.add(cigUser);
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
   public CigaretteUsers selectCigUser(String userLoginId, String userPassword, int companyId) throws SQLException, Exception 
  {
  
    CigaretteUsers cigUser = new CigaretteUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.VERIFY_CIG_USERS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
        pstmt.setInt(3,companyId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
        cigUser.setUserPasswordCheck(rs.getInt(10));
    }
     return cigUser;
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
  
   public void updateCigUserPassword(CigaretteUsers cigUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_CIG_USER_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,cigUser.getUserPassword());
      pstmt.setInt(2,cigUser.getUserId());
      pstmt.execute();
      
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
  public CigaretteUsers emailCigUserPassword(String userLoginId) throws SQLException, Exception 
  {
  
    CigaretteUsers cigUser = new CigaretteUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.EMAIL_CIG_USERS_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
        cigUser.setUserPasswordCheck(rs.getInt(10));
    }
     return cigUser;
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
   public List selectUserList(int companyId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_CIG_USERS);
      pstmt.clearParameters();
      if(companyId != 0) {
        pstmt.setInt(1,companyId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        CigaretteUsers cigUser = new CigaretteUsers();
        
         cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
        list.add(cigUser);
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
  public CigaretteUsers selectCigUser(String userLoginId, String userPassword) throws SQLException, Exception 
  {
  
    CigaretteUsers cigUser = new CigaretteUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.CIG_USERS_ACCOUNTS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
        cigUser.setUserPasswordCheck(rs.getInt(10));
    }
     return cigUser;
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
  
   public List selectUserList() throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_CIG_USERS_ALL);
      pstmt.clearParameters();
     
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        CigaretteUsers cigUser = new CigaretteUsers();
        
         cigUser.setUserPasswordExpiresDate(rs.getDate(6));
        cigUser.setUserFirstName(rs.getString(2));
        cigUser.setUserLastName(rs.getString(3));
        cigUser.setUserLoginId(rs.getString(7));
        cigUser.setUserPassword(rs.getString(4));
        cigUser.setUserStatus(rs.getString(5));
        cigUser.setUserTelephone(rs.getString(8));
        cigUser.setUserId(rs.getInt(1)); 
        cigUser.setCompanyId(rs.getInt(9));
        list.add(cigUser);
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
    public List selectEmailsForExpiration() throws SQLException, Exception 
  {
  
    
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_APPLICATION_BY_EXPIRATION);
      pstmt.clearParameters();
       rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        CigaretteApplication application = new CigaretteApplication();
      application.setEmail(rs.getString(1));
      application.setAppId(rs.getInt(2));
      application.setAppType(rs.getString(3));
      application.setAppDate(rs.getDate(4));    
     list.add(application);
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
     public void updateApplication(int applicationId) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call UPDATE_CIG_APP_EMAIL_STATUS(?) }");
      proc.setInt(1, applicationId);
      proc.execute();
   
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
}
