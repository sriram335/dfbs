package otherUsers.data;

import otherUsers.to.*;
import otherUsers.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import idhsInspections.data.InspectionsSQL;

public class otherUsersDAO extends HsDAO
{
    public otherUsersDAO() {
        
    }
 
   public int insertOtherUser(otherUsers otherUser,String status,String groupName,int detNumber, String personType) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,otherUsersSQL.SELECT_NEXT_OTHER_USER_ID );
      pstmt = conn.prepareStatement(otherUsersSQL.INSERT_OTHER_USERS);
      pstmt.clearParameters();
      pstmt.setString(2,otherUser.getUserFirstName());
      pstmt.setString(3,otherUser.getUserLastName());
      pstmt.setString(4,otherUser.getUserLoginId());
      pstmt.setString(5,otherUser.getUserPassword());
      pstmt.setString(6,"A");
      pstmt.setString(7,otherUser.getUserCounty());  
      pstmt.setString(8,otherUser.getFDId());
      pstmt.setString(9,otherUser.getFDName());  
      pstmt.setString(10,otherUser.getUserPhone());    
      pstmt.setInt(1,id);
      pstmt.execute();
      pstmt1 = conn.prepareStatement(otherUsersSQL.INSERT_OTHER_USERS_GROUP);
      pstmt1.setInt(1,id); 
      pstmt1.setString(2,groupName);
      pstmt1.setString(3,null);  
      pstmt1.execute();  
      if (detNumber >0)
      {
      pstmt2 = conn.prepareStatement(otherUsersSQL.INSERT_OTHER_USERS_GROUP_DET);
      pstmt2.setInt(1,id); 
      pstmt2.setString(3,personType);
      pstmt2.setInt(2,detNumber);  
      pstmt2.execute(); 
      }
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
        pstmt1.close(); 
      } catch (Exception e) {}
    }
  }
    public otherUsers selectOtherUser(int userId) throws SQLException, Exception 
  {
  
    otherUsers otherUser = new otherUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_OTHER_USERS_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,userId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        otherUser.setFDName(rs.getString(10));
        otherUser.setUserCounty(rs.getString(11));
    }
     return otherUser;
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
       public int selectOtherUserLoginId(String userLoginId) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int userCheck =0;   
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_OTHER_USERS_BY_LOGIN_ID);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
        userCheck=(rs.getInt(1)); 
        
    }
     return userCheck;
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
 public void updateOtherUser(otherUsers otherUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(otherUsersSQL.UPDATE_OTHER_USERS);
      pstmt.clearParameters();
      pstmt.setString(5,otherUser.getUserPasswordExpiresDateString());
      pstmt.setString(1,otherUser.getUserFirstName());
      pstmt.setString(2,otherUser.getUserLastName());
      pstmt.setString(6,otherUser.getUserLoginId());
      pstmt.setString(3,otherUser.getUserPassword());
      pstmt.setString(4,otherUser.getUserStatus());
      pstmt.setString(7,otherUser.getUserPhone());
       pstmt.setString(9,otherUser.getFDId());  
      pstmt.setString(10,otherUser.getFDName());
        pstmt.setString(8,otherUser.getUserCounty());
      pstmt.setInt(11,otherUser.getUserId());
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
      
        otherUsers otherUser = new otherUsers();
         otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        otherUser.setFDName(rs.getString(10)); 
        otherUser.setUserCounty(rs.getString(11));   
        list.add(otherUser);
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
   public otherUsers selectLoginOtherUser(String userLoginId, String userPassword) throws SQLException, Exception 
  {
  
    otherUsers otherUser = new otherUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.VERIFY_OTHER_USERS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        otherUser.setUserPasswordCheck(rs.getInt(10));
        otherUser.setUserGroup(rs.getString(11));
        otherUser.setUserCounty(rs.getString(12));
     }
     return otherUser;
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
  
   public void updateOtherUserPassword(otherUsers otherUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(otherUsersSQL.UPDATE_OTHER_USER_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,otherUser.getUserPassword());
      pstmt.setInt(2,otherUser.getUserId());
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
  public otherUsers emailOtherUserPassword(String userLoginId) throws SQLException, Exception 
  {
  
    otherUsers otherUser = new otherUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.EMAIL_OTHER_USERS_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        otherUser.setUserPasswordCheck(rs.getInt(10));
    }
     return otherUser;
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
   public List selectUserList(String FDId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_OTHER_USERS);
      pstmt.clearParameters();
      if(FDId != null) {
        pstmt.setString(1,FDId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        otherUsers otherUser = new otherUsers();
        
         otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        list.add(otherUser);
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
  public otherUsers selectOtherUser(String userLoginId, String userPassword) throws SQLException, Exception 
  {
  
    otherUsers otherUser = new otherUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.OTHER_USERS_ACCOUNTS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        otherUser.setUserPasswordCheck(rs.getInt(10));
    }
     return otherUser;
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
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_OTHER_USERS_ALL);
      pstmt.clearParameters();
     
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
         otherUsers otherUser = new otherUsers();
         otherUser.setUserPasswordExpiresDate(rs.getDate(6));
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(3));
        otherUser.setUserLoginId(rs.getString(7));
        otherUser.setUserPassword(rs.getString(4));
        otherUser.setUserStatus(rs.getString(5));
        otherUser.setUserPhone(rs.getString(8));
        otherUser.setUserId(rs.getInt(1)); 
        otherUser.setFDId(rs.getString(9));
        list.add(otherUser);
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
   
   public List selectFDList(String county) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_FD_OTHERS);
      pstmt.clearParameters();
      if(county != null) {
        pstmt.setString(1,county);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        String FdName="";
        FdName=rs.getString(1)+":"+rs.getString(2);
        list.add(FdName);
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
   public int validateNewUserRequest(String fdId,String fcEmail,String accKey) throws SQLException, Exception 
  {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int verifyAccess=0;
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_FD_OTHERS_VERIFY);
      pstmt.clearParameters();
      if(fdId != null) {
        pstmt.setString(1,fdId);
        pstmt.setString(2,fcEmail);
        pstmt.setString(3,accKey);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
         verifyAccess =rs.getInt(1);
      }
      
      return verifyAccess;
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
     public String selectChiefEmail (String fdId) throws SQLException, Exception 
  {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String chiefEmail ="";
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_FD_CHIEF_EMAIL);
      pstmt.clearParameters();
      if(fdId != null) {
        pstmt.setString(1,fdId);
       }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
         chiefEmail =rs.getString(1);
      }
      
      return chiefEmail;
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
      public List selectUserListByFD(String county,String fireDeptId ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_USERS_BY_FD);
      pstmt.clearParameters();
      pstmt.setString(1,county);
      pstmt.setString(2,fireDeptId);  
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        otherUsers otherUser = new otherUsers();
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(1));
        otherUser.setUserLoginId(rs.getString(3));
        list.add(otherUser);
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
      
      public List selectFireChiefsEmail() throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(otherUsersSQL.SELECT_FD_CHIEF_EMAIL_LIST);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        otherUsers otherUser = new otherUsers();
        otherUser.setUserFirstName(rs.getString(2));
        otherUser.setUserLastName(rs.getString(1));
        otherUser.setUserLoginId(rs.getString(3));
        otherUser.setUserPassword(rs.getString(4));  
        list.add(otherUser);
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
  public List selectUserListByCounty(String county) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(otherUsersSQL.SELECT_OTHER_USERS_BY_COUNTY);
     pstmt.clearParameters();
     if(county != null) {
       pstmt.setString(1,county);
     }
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {
     
       otherUsers otherUser = new otherUsers();
        otherUser.setUserPasswordExpiresDate(rs.getDate(6));
       otherUser.setUserFirstName(rs.getString(2));
       otherUser.setUserLastName(rs.getString(3));
       otherUser.setUserLoginId(rs.getString(7));
       otherUser.setUserPassword(rs.getString(4));
       otherUser.setUserStatus(rs.getString(5));
       otherUser.setUserPhone(rs.getString(8));
       otherUser.setUserId(rs.getInt(1)); 
       otherUser.setFDId(rs.getString(9));
       otherUser.setFDName(rs.getString(10)); 
       otherUser.setUserCounty(rs.getString(11));   
       list.add(otherUser);
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
  public void deleteLepc (int  userId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
     pstmt = conn.prepareStatement(otherUsersSQL.DELETE_OTHER_USERS_GROUP);
     pstmt.clearParameters();
     pstmt.setInt(1,userId);
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
