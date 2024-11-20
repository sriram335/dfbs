package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import java.io.InputStream;
import java.io.OutputStream;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;
public class EmsSecurityDAO extends HsDAO
{
  public EmsSecurityDAO()
  {
  }
 
  public List selectUserList(String sql,String param) throws SQLException, Exception 
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
      
        EmsSecurity security = new EmsSecurity();
        
        
         security.setSecurityId(rs.getInt(1));
         security.setUserId(rs.getString(2));
         security.setUserPassword(rs.getString(3));
         security.setUserLevel(rs.getString(4));
         security.setUserPhone(rs.getString(5));
         security.setUserLastName(rs.getString(6));
         security.setUserFirstName(rs.getString(7));
         security.setUserTitle(rs.getString(8));
         security.setUserStatus(rs.getString(9));
         security.setPasswordExpiresDate(rs.getDate(10));
         security.setRecordCreatedBy(rs.getString(11));
         security.setRecordCreatedDate(rs.getDate(12));  
 
        list.add(security);
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
   public void  uploadFile(FormFile oneFile,String IdNumber, String sysUser,String IdType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,EmsSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(EmsSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,sysUser);
       pstmt.setString(6,IdNumber);
       pstmt.setString(7,IdType);
       pstmt.execute();
       conn.commit();
      
 
    }
    finally 
    {
     try {conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }

    public List selectFileList(String IdNumber,String IdType,String userLevel,String userId) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
      conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_DOCUMENT_NAMES_ALL);
       pstmt.clearParameters();
     
      if(IdNumber != null) {
        pstmt.setString(1,IdNumber);
        pstmt.setString(2,IdType);
      } 
      rs = pstmt.executeQuery(); 
      while(rs.next()) 
      { 
        FileNames names = new FileNames();
        names.setFileName(rs.getString(1));
        names.setFileType(rs.getString(2));
        names.setFileDate(rs.getDate(3));
        names.setFileLoadedBy(rs.getString(4));
        names.setFileId(rs.getInt(5));
        list.add(names);
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
  
 public int downLoad(OutputStream os,int fileId)  throws SQLException, Exception 
 {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = EmsSQL.SELECT_DOCUMENT; 
   conn = getConnection(); 
   pstmt = conn.prepareStatement(sql);
    if(fileId != 0) {
        pstmt.setInt(1,fileId);
      }
  
   rs = pstmt.executeQuery(); 
    while(rs.next()) 
   {
   java.sql.Blob blob =  rs.getBlob(1);
   InputStream is = blob.getBinaryStream();
    byte[] buf = new byte[1024];
   int len = -1; 
   while ( (len=is.read(buf,0,1024)) != -1)
   {    os.write(buf,0,len);
        len_total += len; 
   } 
    is.close(); 
   
   }
    return len_total;
   }
   
 
   finally
   { 
     try {
       
        conn.close();
        rs.close();
        pstmt.close();
      } 
     
      catch (Exception e){}
   }
   
  
  } 
  public int  checkProviderSecurity(int userId,int providerId,String providerType) throws SQLException, Exception 
  {
         Connection conn = null;
          PreparedStatement pstmt = null;
           ResultSet rs = null;
           int secCheck =0;
       try 
    {
           conn = getConnection();
          pstmt = conn.prepareStatement(EmsSQL.SELECT_COUNT_SEC_DETAIL);
          pstmt.clearParameters();
          pstmt.setInt(1,providerId);
          pstmt.setString(2,providerType);
          pstmt.setInt(3,userId);
          rs = pstmt.executeQuery();
            while(rs.next()) 
      {
        secCheck= (rs.getInt(1));
      }
    return(secCheck);
      
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