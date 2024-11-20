package arson.data;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import main.to.*;
import hs.util.*;
import hs.data.*;

import org.apache.struts.upload.FormFile;
public class ArsonUploadDAO  extends HsDAO
{
  public ArsonUploadDAO()
  {
  }
   public void  uploadFile(FormFile oneFile,String IdNumber,String userId, String IdType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {   conn = getConnection();
       int id = this.getId(conn,ArsonSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(ArsonSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,userId);
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

    public List selectFileList(String IdNumber,String IdType) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
   
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(ArsonSQL.SELECT_DOCUMENT_NAMES_ALL);
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
   String sql = ArsonSQL.SELECT_DOCUMENT; 
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
   public String  getUploadFileName(String connectorId,String connectorType) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
   String fileName="NoName";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(ArsonSQL.SELECT_DOCUMENT_NAME);
      pstmt.clearParameters();
      pstmt.setString(1,connectorId);
      pstmt.setString(2,connectorType);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        fileName=(rs.getString(1));
     
      }
    
      return fileName;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public void  getConnectionInfo(ApplicationSecurity security,String connId,String connType) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(ArsonSQL.SELECT_DOCUMENT_CONNECT);
      pstmt.clearParameters();
      pstmt.setString(1,connId);
      pstmt.setString(2,connType);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
     
        security.setUserId(rs.getString(1));
        security.setUserPassword(rs.getString(2));
       
      }
    
      
    }
    finally 
    {
     try { conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public void  resetConnectionInfo(String connId,String connType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {  
      conn = getConnection();
      pstmt = conn.prepareStatement(ArsonSQL.DELETE_DOCUMENT_CONNECT);
      pstmt.clearParameters();
      pstmt.setString(1,connId);
      pstmt.setString(2,connType);
      pstmt.execute();
 
    }
    finally 
    {
     try {conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
}
