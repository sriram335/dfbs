package main.data;
import hs.data.*;
import java.sql.*;
import main.to.*;


public class ApplicationSecurityDAO  extends HsDAO
{ 
  public ApplicationSecurityDAO()
  {
  }

  
  public String  getSecurity(ApplicationSecurity security,String userId,String passWord) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String userCheck="xxx";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(SecuritySQL.SELECT_USER);
      pstmt.clearParameters();
      pstmt.setString(1,userId);
      pstmt.setString(2,passWord);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
    
     
        userCheck=rs.getString(1);
        security.setUserId(rs.getString(1));
        security.setCurrentUser(rs.getString(1));
        security.setUserPassword(rs.getString(2));
        security.setPasswordExpiresDate(rs.getDate(3));
        security.setOldPassword(rs.getString(2));
        security.setCurrentDate(rs.getDate(4));
        security.setUserEmail(rs.getString(5));
        this.getGroupSecurity(security);
      }
      return userCheck;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public String  getSecurityEmail(ApplicationSecurity security,String userEmail,String passWord) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String userCheck="xxx";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(SecuritySQL.SELECT_USER_BY_EMAIL);
      pstmt.clearParameters();
      pstmt.setString(1,userEmail);
      pstmt.setString(2,passWord);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
     
     
       userCheck = rs.getString(1);
        security.setUserId(rs.getString(1));
        security.setCurrentUser(rs.getString(1));
        security.setUserPassword(rs.getString(2));
        security.setPasswordExpiresDate(rs.getDate(3));
        security.setOldPassword(rs.getString(2));
        security.setCurrentDate(rs.getDate(4));
         this.getGroupSecurity(security);
      }
    
      return userCheck;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public String  getPassword(ApplicationSecurity security,String userId) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    String userCheck="";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(SecuritySQL.RECOVER_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,userId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      
        userCheck=(rs.getString(1));
        security.setUserId(rs.getString(1));
        security.setUserPassword(rs.getString(2));
       pstmt1 = conn.prepareStatement(" ALTER USER " + rs.getString(1)  + " ACCOUNT UNLOCK");
       pstmt1.execute();
      }
    
      return userCheck;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public void  updatePassword(ApplicationSecurity security,String newPassword) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {  
      conn = getConnection();
      String userId = security.getUserId();
      pstmt = conn.prepareStatement(SecuritySQL.CHANGE_PASSWORD);
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
  
 
      public ApplicationContacts  setContacts(String conType1, String conType2) throws SQLException, Exception
      {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(SecuritySQL.SELECT_CONTACTS);
          pstmt.clearParameters();
            pstmt.setString(2,conType2);
            pstmt.setString(1,conType1);
          rs = pstmt.executeQuery();
         ApplicationContacts contacts= new ApplicationContacts();
          while(rs.next()) 
          { 
            if (rs.getString(1).equals(conType1))
            {
            contacts.setContact1Name(rs.getString(4));
            contacts.setContact1Email(rs.getString(2));
            contacts.setContact1Phone(rs.getString(3));
            contacts.setContact1IP(rs.getString(5));
            }
            if (rs.getString(1).equals(conType2))
            {
            contacts.setContact2Name(rs.getString(4));
            contacts.setContact2Email(rs.getString(2));
            contacts.setContact2Phone(rs.getString(3));
            contacts.setContact2IP(rs.getString(5));
            }
          }
         return contacts;
        }
        finally 
        {
         try {conn.close();
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      }
  public String findSecurityUser(String url,ApplicationSecurity security,String conType) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String userCheck="";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(SecuritySQL.SELECT_USER_URL);
      pstmt.clearParameters();
      pstmt.setString(1,url);
      pstmt.setString(2,conType); 
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
     
        userCheck=rs.getString(1);
        security.setUserId(rs.getString(1));
        security.setCurrentUser(rs.getString(1));
        security.setUserPassword(rs.getString(2));
        security.setPasswordExpiresDate(rs.getDate(3));
        security.setOldPassword(rs.getString(2));
        security.setCurrentDate(rs.getDate(4));
      }
    
      return userCheck;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public ApplicationSecurity  getGroupSecurity(ApplicationSecurity security) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(SecuritySQL.SELECT_USER_GROUPS);
      pstmt.clearParameters();
      pstmt.setString(1,security.getUserId());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
     
        if(rs.getString(2).equals("ALL") && rs.getString(1).equals("DBA"))
        {
          security.setGroupLevelAll(rs.getString(1));
          security.setGroupAll(rs.getString(2));
        }
         if(rs.getString(2).equals("ACCT"))
        {
          security.setGroupLevelAcct(rs.getString(1));
          security.setGroupAcct(rs.getString(2));
        }
         if(rs.getString(2).equals("AMUSE"))
        {
          security.setGroupLevelAmuse(rs.getString(1));
          security.setGroupAmuse(rs.getString(2));
        }
          if(rs.getString(2).equals("ARSON"))
        {
          security.setGroupLevelArson(rs.getString(1));
          security.setGroupArson(rs.getString(2));
        }
           if(rs.getString(2).equals("BPV"))
        {
          security.setGroupLevelBPV(rs.getString(1));
          security.setGroupBPV(rs.getString(2));
        }
           if(rs.getString(2).equals("CIGARETTE"))
        {
          security.setGroupLevelCigarette(rs.getString(1));
          security.setGroupCigarette(rs.getString(2));
        }
           if(rs.getString(2).equals("CODE"))
        {
          security.setGroupLevelCode(rs.getString(1));
          security.setGroupCode(rs.getString(2));
        }
          if(rs.getString(2).equals("ELEV"))
        {
          security.setGroupLevelElev(rs.getString(1));
          security.setGroupElev(rs.getString(2));
        }
          if(rs.getString(2).equals("EMS"))
        {
          security.setGroupLevelEms(rs.getString(1));
          security.setGroupEms(rs.getString(2));
        }
           if(rs.getString(2).equals("FFC"))
        {
          security.setGroupLevelFFC(rs.getString(1));
          security.setGroupFFC(rs.getString(2));
        }
           if(rs.getString(2).equals("FIRE"))
        {
          security.setGroupLevelFire(rs.getString(1));
          security.setGroupFire(rs.getString(2));
        }
           if(rs.getString(2).equals("HAZMAT"))
        {
          security.setGroupLevelHazmat(rs.getString(1));
          security.setGroupHazmat(rs.getString(2));
        }
            if(rs.getString(2).equals("PLAN"))
        {
          security.setGroupLevelPlan(rs.getString(1));
          security.setGroupPlan(rs.getString(2));
        }
          if(rs.getString(2).equals("VAR"))
        {
          security.setGroupLevelVar(rs.getString(1));
          security.setGroupVar(rs.getString(2));
        }
          if(rs.getString(2).equals("CODE_EDU"))
          {
          security.setGroupLevelCodeEducation(rs.getString(1));
          security.setGroupCodeEducation(rs.getString(2));
          }
        if(rs.getString(2).equals("LEPC"))
        {
        security.setGroupLevelLepc(rs.getString(1));
        security.setGroupLepc(rs.getString(2));
        }
          
        if(rs.getString(2).equals("HIPAA"))
        {
        security.setGroupLevelHipaa(rs.getString(1));
        security.setGroupHipaa(rs.getString(2));
        }
          
        if(rs.getString(2).equals("FIRE_BLD_EDU"))
        {
        security.setGroupLevelFireBldEducation(rs.getString(1));
        security.setGroupFireBldEducation(rs.getString(2));
        }
      }
    
      return security;
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
    public void insertIp (String UserId,String ipAddress) throws SQLException, Exception 
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
       
    try 
    { 
      
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(SecuritySQL.INSERT_USER_IP);
      pstmt.setString(1,UserId);
      pstmt.setString(2,ipAddress);
      pstmt.execute();
      conn.commit();
      
       
    }
    finally 
    {
     try {
        conn.close();
       pstmt.close();
      } catch (Exception e) {}
    }
    }
  public void insertIdhsAppFailure (String appId,String appError) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
     
  try 
  { 
    
    conn = getConnection();
    conn.setAutoCommit(false);
    pstmt = conn.prepareStatement(SecuritySQL.INSERT_IDHS_APP_FAILURE);
    pstmt.setString(1,appId);
    pstmt.setString(2,appError);
    pstmt.execute();
    conn.commit();
    
     
  }
  finally 
  {
   try {
      conn.close();
     pstmt.close();
    } catch (Exception e) {}
  }
  }
  public FileNames  selectAppStatus(String appType) throws SQLException, Exception
      {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        FileNames names = new FileNames();
        try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(SecuritySQL.SELECT_APP_STATUS);
          pstmt.clearParameters();
            pstmt.setString(1,appType);
          rs = pstmt.executeQuery();
          while(rs.next()) 
          { 
            names.setFileName(rs.getString(1));
            names.setFileType(rs.getString(2));  
          }
         return names;
        }
        finally 
        {
         try {conn.close();
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      }
  public String  selectCurrentDate() throws SQLException, Exception 
  {
  
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   String l_currentDate=null;    
   try 
   {
     
     conn = getConnection();
      pstmt = conn.prepareStatement(SecuritySQL.SELECT_CURRENT_DATE);
     pstmt.clearParameters();
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
       
       l_currentDate =(rs.getString(1));
      
     }
   
  
     return l_currentDate;
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
  public void insertJavaError (String javaError) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
     
  try 
  { 
    
    conn = getConnection();
    conn.setAutoCommit(false);
    pstmt = conn.prepareStatement(SecuritySQL.INSERT_JAVA_ERROR);
    pstmt.setString(1,javaError);
    pstmt.execute();
    conn.commit();
    
     
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