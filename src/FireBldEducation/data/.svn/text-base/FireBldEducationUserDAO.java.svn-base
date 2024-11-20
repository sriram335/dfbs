package FireBldEducation.data;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import FireBldEducation.to.*;
import FireBldEducation.data.*;
import main.data.SecuritySQL;

import main.to.ApplicationSecurity;

public class FireBldEducationUserDAO extends HsDAO{
    public FireBldEducationUserDAO() {
    }
    public List selectUserList(String sql) throws SQLException, Exception 
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
          FireBldEducationUser codeUser = new FireBldEducationUser();
            codeUser.setUserAddress1(rs.getString(8));
            codeUser.setUserAddress2(rs.getString(9));
            codeUser.setUserCity(rs.getString(10));
            codeUser.setUserCounty(rs.getString(12));
            codeUser.setUserFax(rs.getString(16));
            codeUser.setUserFirstName(rs.getString(6));
            codeUser.setUserId(rs.getString(2));
            codeUser.setUserLastName(rs.getString(5));
            codeUser.setUserMI(rs.getString(7));
            codeUser.setUserPassword(rs.getString(3));
            codeUser.setUserPhone(rs.getString(15));
            codeUser.setUserState(rs.getString(11));
            codeUser.setUserStatus(rs.getString(17));
            codeUser.setUserType(rs.getString(4));
            codeUser.setUserZip(rs.getString(13));
            codeUser.setUserZip2(rs.getString(14));
            codeUser.setUserPersonId(rs.getInt(1));
            codeUser.setUserPsid(rs.getString(18));
          list.add(codeUser);
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
    public FireBldEducationUser selectUser(int userId) throws SQLException, Exception 
    {
       FireBldEducationUser codeUser = new FireBldEducationUser();
      Connection conn = null;
       
      try 
      {
        conn = getConnection();
        codeUser = selectUser(conn,userId);
        return codeUser;
      }
      finally 
      {
       try {
          conn.close();
        } catch (Exception e) {}
      }
    }
    public FireBldEducationUser selectUser(Connection conn,int userId) throws SQLException, Exception 
      {
        FireBldEducationUser codeUser = new FireBldEducationUser();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        
         try 
        {
          pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_USER_BY_ID);
          pstmt.clearParameters();
          pstmt.setInt(1,userId);
          rs = pstmt.executeQuery();
          
          if(rs.next()) 
          {
              codeUser.setUserAddress1(rs.getString(8));
              codeUser.setUserAddress2(rs.getString(9));
              codeUser.setUserCity(rs.getString(10));
              codeUser.setUserCounty(rs.getString(12));
              codeUser.setUserFax(rs.getString(16));
              codeUser.setUserFirstName(rs.getString(6));
              codeUser.setUserId(rs.getString(2));
              codeUser.setUserLastName(rs.getString(5));
              codeUser.setUserMI(rs.getString(7));
              codeUser.setUserPassword(rs.getString(3));
              codeUser.setUserPhone(rs.getString(15));
              codeUser.setUserState(rs.getString(11));
              codeUser.setUserStatus(rs.getString(17));
              codeUser.setUserType(rs.getString(4));
              codeUser.setUserZip(rs.getString(13));
              codeUser.setUserZip2(rs.getString(14));
              codeUser.setUserPersonId(rs.getInt(1));
              codeUser.setUserPsid(rs.getString(18));
              
          }
            return codeUser;
        }
        finally 
        {
         try {
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      }
    public void updateUser(FireBldEducationUser codeUser) throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             pstmt = conn.prepareStatement(FireBldEducationSQL.UPDATE_CODE_USER_BY_ID);
             pstmt.clearParameters();
           pstmt.setString(3,codeUser.getUserAddress1());
           pstmt.setString(4,codeUser.getUserAddress2());
           pstmt.setString(5,codeUser.getUserCity());
           pstmt.setString(7,codeUser.getUserCounty());
           pstmt.setString(2,codeUser.getUserFax());
           pstmt.setString(10,codeUser.getUserPhone());
           pstmt.setString(6,codeUser.getUserState());
           pstmt.setString(1,codeUser.getUserStatus());
           pstmt.setString(8,codeUser.getUserZip());
           pstmt.setString(9,codeUser.getUserZip2());
           pstmt.setInt(16,codeUser.getUserPersonId());
           pstmt.setString(11,codeUser.getUserPsid());
           pstmt.setString(13,codeUser.getUserFirstName());
           pstmt.setString(12,codeUser.getUserLastName());
           pstmt.setString(14,codeUser.getUserMI());
           pstmt.setString(15,codeUser.getUserType());
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
      public void insertUser(FireBldEducationUser codeUser) throws SQLException, Exception 
     {
     
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             int id = this.getId(conn,"select code_edu_user_id.nextval from dual");
                 pstmt = conn.prepareStatement(FireBldEducationSQL.INSERT_CODE_USER);
                codeUser.setUserPersonId(id);
             pstmt.clearParameters();
              pstmt.setString(7,codeUser.getUserAddress1());
           pstmt.setString(8,codeUser.getUserAddress2());
           pstmt.setString(9,codeUser.getUserCity());
           pstmt.setString(11,codeUser.getUserCounty());
           pstmt.setString(15,codeUser.getUserFax());
           pstmt.setString(5,codeUser.getUserFirstName());
           pstmt.setString(1,codeUser.getUserId());
           pstmt.setString(4,codeUser.getUserLastName());
           pstmt.setString(6,codeUser.getUserMI());
           pstmt.setString(2,codeUser.getUserPassword());
           pstmt.setString(14,codeUser.getUserPhone());
           pstmt.setString(10,codeUser.getUserState());
           pstmt.setString(3,codeUser.getUserType());
           pstmt.setString(12,codeUser.getUserZip());
           pstmt.setString(13,codeUser.getUserZip2());
           pstmt.setString(16,"A");
           pstmt.setInt(17,id);
           pstmt.setString(18,codeUser.getUserPsid());
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
    public FireBldEducationUser selectUser(String userId) throws SQLException, Exception 
      {
        FireBldEducationUser codeUser = new FireBldEducationUser();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
          Connection conn = null;
        
         try 
        { conn = getConnection();
          pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_USER_BY_USER_ID);
          pstmt.clearParameters();
          pstmt.setString(1,userId);
          rs = pstmt.executeQuery();
          if(rs.next()) 
          {
              codeUser.setUserAddress1(rs.getString(8));
              codeUser.setUserAddress2(rs.getString(9));
              codeUser.setUserCity(rs.getString(10));
              codeUser.setUserCounty(rs.getString(12));
              codeUser.setUserFax(rs.getString(16));
              codeUser.setUserFirstName(rs.getString(6));
              codeUser.setUserId(rs.getString(2));
              codeUser.setUserLastName(rs.getString(5));
              codeUser.setUserMI(rs.getString(7));
              codeUser.setUserPassword(rs.getString(3));
              codeUser.setUserPhone(rs.getString(15));
              codeUser.setUserState(rs.getString(11));
              codeUser.setUserStatus(rs.getString(17));
              codeUser.setUserType(rs.getString(4));
              codeUser.setUserZip(rs.getString(13));
              codeUser.setUserZip2(rs.getString(14));
              codeUser.setUserPersonId(rs.getInt(1));
              codeUser.setUserPsid(rs.getString(18));
          }
            return codeUser;
        }
        finally 
        {
         try {conn.close();
            rs.close();
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
             FireBldEducationUser codeUser = new FireBldEducationUser();
               
               codeUser.setUserFirstName(rs.getString(2));
               codeUser.setUserId(rs.getString(1));
               codeUser.setUserLastName(rs.getString(3));
               codeUser.setUserPassword(rs.getString(4));
                codeUser.setUserStatus(rs.getString(5));
             codeUser.setUserPhone(rs.getString(7));
               codeUser.setUserPersonId(rs.getInt(6));
           list.add(codeUser);
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
     
    public void updateUserPasswords() throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             pstmt = conn.prepareStatement(FireBldEducationSQL.CODE_EDU_UPDATE_PASSWORDS);
             pstmt.clearParameters();
          
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
    public List selectUserListByName( String userLastName,String userFirstName) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
        pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_USERS_BY_NAME);
        pstmt.clearParameters();
          pstmt.clearParameters();
            pstmt.setString(1,userLastName);
          pstmt.setString(2,userFirstName);
         rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          FireBldEducationUser codeUser = new FireBldEducationUser();
            codeUser.setUserAddress1(rs.getString(8));
            codeUser.setUserAddress2(rs.getString(9));
            codeUser.setUserCity(rs.getString(10));
            codeUser.setUserCounty(rs.getString(12));
            codeUser.setUserFax(rs.getString(16));
            codeUser.setUserFirstName(rs.getString(6));
            codeUser.setUserId(rs.getString(2));
            codeUser.setUserLastName(rs.getString(5));
            codeUser.setUserMI(rs.getString(7));
            codeUser.setUserPassword(rs.getString(3));
            codeUser.setUserPhone(rs.getString(15));
            codeUser.setUserState(rs.getString(11));
            codeUser.setUserStatus(rs.getString(17));
            codeUser.setUserType(rs.getString(4));
            codeUser.setUserZip(rs.getString(13));
            codeUser.setUserZip2(rs.getString(14));
            codeUser.setUserPersonId(rs.getInt(1));
            codeUser.setUserPsid(rs.getString(18));
          list.add(codeUser);
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
   /* public int selectCodeSuperUser(String userId) throws SQLException, Exception 
      {
        FireBldEducationUser codeUser = new FireBldEducationUser();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
          Connection conn = null;
          int userFlag=0;
         try 
        {
            conn = getConnection();
          pstmt = conn.prepareStatement(FireBldEducationSQL.CODE_EDU_SUPER_USER);
         
          pstmt.clearParameters();
          pstmt.setString(1,userId);
          rs = pstmt.executeQuery();
          
          if(rs.next()) 
          {
              userFlag=rs.getInt(1);
          }
            return userFlag;
        }
        finally 
        {
         try {
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      } */
    public List selectClassList(int codePersonId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
       
        pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_COURSE_LIST_PERSON);
          pstmt.clearParameters();
          pstmt.setInt(1,codePersonId);
         rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
            FireBldEducationCourse codeCourse = new FireBldEducationCourse();
            codeCourse.setClassLocation(rs.getString(2));
            codeCourse.setCourseName(rs.getString(1));
            codeCourse.setCourseId(rs.getInt(4));
            codeCourse.setClassSize(rs.getInt(3));
          list.add(codeCourse);
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
    public FireBldEducationUser selectUser(FireBldEducationUser codeUser,String userId,String pwd) throws SQLException, Exception 
      {
         ResultSet rs = null;
        PreparedStatement pstmt = null;
          Connection conn = null;
        
         try 
        { conn = getConnection();
          pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_USER_BY_USER_ID_PWD);
           pstmt.clearParameters();
          pstmt.setString(1,userId);
         pstmt.setString(2,pwd);
          rs = pstmt.executeQuery();
          if(rs.next()) 
          {
              codeUser.setUserAddress1(rs.getString(8));
              codeUser.setUserAddress2(rs.getString(9));
              codeUser.setUserCity(rs.getString(10));
              codeUser.setUserCounty(rs.getString(12));
              codeUser.setUserFax(rs.getString(16));
              codeUser.setUserFirstName(rs.getString(6));
              codeUser.setUserId(rs.getString(2));
              codeUser.setUserLastName(rs.getString(5));
              codeUser.setUserMI(rs.getString(7));
              codeUser.setUserPassword(rs.getString(3));
              codeUser.setUserPhone(rs.getString(15));
              codeUser.setUserState(rs.getString(11));
              codeUser.setUserStatus(rs.getString(17));
              codeUser.setUserType(rs.getString(4));
              codeUser.setUserZip(rs.getString(13));
              codeUser.setUserZip2(rs.getString(14));
              codeUser.setUserPersonId(rs.getInt(1));
              codeUser.setUserPsid(rs.getString(18));
          }
            return codeUser;
        }
        finally 
        {
         try {conn.close();
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      }
    public void  updatePassword(FireBldEducationUser codeUser,String newPassword) throws SQLException, Exception
    { 
      Connection conn = null;
      PreparedStatement pstmt = null;
      try 
      {  
        conn = getConnection();
        String userId = codeUser.getUserId();
        pstmt = conn.prepareStatement(FireBldEducationSQL.CODE_EDU_CHANGE_PASSWORD);
        pstmt.clearParameters();
        pstmt.setString(2,userId);
        pstmt.setString(1,newPassword);
        pstmt.execute();
    
      }
      finally 
      {
       try {conn.close();
          pstmt.close();
        } catch (Exception e) {}
      }
    }
    public int selectUserCheck(String userId) throws SQLException, Exception 
      {
        FireBldEducationUser codeUser = new FireBldEducationUser();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
          Connection conn = null;
         int l_check = 0;
         try 
        {conn = getConnection();
          pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_USER_CHECK);
          pstmt.clearParameters();
          pstmt.setString(1,userId);
          rs = pstmt.executeQuery();
          
          if(rs.next()) 
          {
             l_check = rs.getInt(1);
          }
            return l_check;
        }
        finally 
        {
         try {conn.close();
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      } 
    public List selectUserListByLastName( String userLastName) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
        pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_USERS +" WHERE UPPER(USER_LAST_NAME)=UPPER(?)");
        pstmt.clearParameters();
          pstmt.clearParameters();
            pstmt.setString(1,userLastName);
          
         rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          FireBldEducationUser codeUser = new FireBldEducationUser();
            codeUser.setUserAddress1(rs.getString(8));
            codeUser.setUserAddress2(rs.getString(9));
            codeUser.setUserCity(rs.getString(10));
            codeUser.setUserCounty(rs.getString(12));
            codeUser.setUserFax(rs.getString(16));
            codeUser.setUserFirstName(rs.getString(6));
            codeUser.setUserId(rs.getString(2));
            codeUser.setUserLastName(rs.getString(5));
            codeUser.setUserMI(rs.getString(7));
            codeUser.setUserPassword(rs.getString(3));
            codeUser.setUserPhone(rs.getString(15));
            codeUser.setUserState(rs.getString(11));
            codeUser.setUserStatus(rs.getString(17));
            codeUser.setUserType(rs.getString(4));
            codeUser.setUserZip(rs.getString(13));
            codeUser.setUserZip2(rs.getString(14));
            codeUser.setUserPersonId(rs.getInt(1));
            codeUser.setUserPsid(rs.getString(18));
          list.add(codeUser);
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
  public List selectUserListCertPrint( int classId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(FireBldEducationSQL.SELECT_CODE_COURSE_USER_CERT_PRINT);
      pstmt.clearParameters();
        pstmt.clearParameters();
          pstmt.setInt(1,classId);
        rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        FireBldEducationUser codeUser = new FireBldEducationUser();
          codeUser.setUserFirstName(rs.getString(2));
          codeUser.setUserLastName(rs.getString(1));
          codeUser.setUserPersonId(rs.getInt(3));
        codeUser.setUserId(rs.getString(4));
        list.add(codeUser);
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
