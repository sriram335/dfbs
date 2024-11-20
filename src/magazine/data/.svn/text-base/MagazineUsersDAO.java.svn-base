package magazine.data;
import magazine.to.*;

import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

public class MagazineUsersDAO extends HsDAO{
    public MagazineUsersDAO() {
       
    }
 public int insertMagUser(MagazineUsers magUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,DfbsSQL.SELECT_NEXT_MAGAZINE_USER_ID);
      pstmt = conn.prepareStatement(DfbsSQL.INSERT_MAGAZINE_USERS);
      pstmt.clearParameters();
      pstmt.setString(6,magUser.getUserPasswordExpiresDateString());
      pstmt.setString(2,magUser.getUserFirstName());
      pstmt.setString(3,magUser.getUserLastName());
      pstmt.setString(7,magUser.getUserLoginId());
      pstmt.setString(4,magUser.getUserPassword());
      pstmt.setString(5,"A");
      pstmt.setString(8,magUser.getUserTelephone());
      pstmt.setInt(9,magUser.getOwnerId());
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
    public MagazineUsers selectMagUser(int userId) throws SQLException, Exception 
  {
  
    MagazineUsers magUser = new MagazineUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_MAGAZINE_USERS_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,userId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
    }
     return magUser;
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
 public void updateMagUser(MagazineUsers magUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsSQL.UPDATE_MAGAZINE_USERS);
      pstmt.clearParameters();
      pstmt.setString(5,magUser.getUserPasswordExpiresDateString());
      pstmt.setString(1,magUser.getUserFirstName());
      pstmt.setString(2,magUser.getUserLastName());
      pstmt.setString(6,magUser.getUserLoginId());
      pstmt.setString(3,magUser.getUserPassword());
      pstmt.setString(4,magUser.getUserStatus());
      pstmt.setString(7,magUser.getUserTelephone());
      pstmt.setInt(8,magUser.getUserId());
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
      
        MagazineUsers magUser = new MagazineUsers();
        
         magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
        list.add(magUser);
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
   public MagazineUsers selectMagazineUser(String userLoginId, String userPassword) throws SQLException, Exception 
  {
  
    MagazineUsers magUser = new MagazineUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.VERIFY_MAGAZINE_USERS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
        magUser.setUserPasswordCheck(rs.getInt(10));
    }
     return magUser;
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
  
   public void updateMagUserPassword(MagazineUsers magUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsSQL.UPDATE_MAGAZINE_USER_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,magUser.getUserPassword());
      pstmt.setInt(2,magUser.getUserId());
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
  public MagazineUsers emailMagUserPassword(String userLoginId) throws SQLException, Exception 
  {
  
    MagazineUsers magUser = new MagazineUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.EMAIL_MAGAZINE_USERS_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
        magUser.setUserPasswordCheck(rs.getInt(10));
    }
     return magUser;
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
   public List selectUserList(int ownerId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_MAGAZINE_USERS);
      pstmt.clearParameters();
      if(ownerId != 0) {
        pstmt.setInt(1,ownerId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        MagazineUsers magUser = new MagazineUsers();
        
         magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
        list.add(magUser);
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
  public MagazineUsers selectMagUser(String userLoginId, String userPassword) throws SQLException, Exception 
  {
  
    MagazineUsers magUser = new MagazineUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.MAGAZINE_USERS_ACCOUNTS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
        magUser.setUserPasswordCheck(rs.getInt(10));
    }
     return magUser;
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
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_MAGAZINE_USERS_ALL);
      pstmt.clearParameters();
     
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        MagazineUsers magUser = new MagazineUsers();
        
         magUser.setUserPasswordExpiresDate(rs.getDate(6));
        magUser.setUserFirstName(rs.getString(2));
        magUser.setUserLastName(rs.getString(3));
        magUser.setUserLoginId(rs.getString(7));
        magUser.setUserPassword(rs.getString(4));
        magUser.setUserStatus(rs.getString(5));
        magUser.setUserTelephone(rs.getString(8));
        magUser.setUserId(rs.getInt(1)); 
        magUser.setOwnerId(rs.getInt(9));
        list.add(magUser);
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
     public int addNewMagUser (int personId) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call create_mag_users_new(?,?) }");
      proc.setInt(1, personId);
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
}

