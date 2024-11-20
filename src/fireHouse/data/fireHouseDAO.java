package fireHouse.data;




import hs.data.HsDAO;

import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;

public class fireHouseDAO extends HsDAO{
    public fireHouseDAO() {
        
    }
  public void  uploadFile(FormFile oneFile,String provNumber,String userEmail) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   try 
   
   {  
      conn = getConnection();
      int id = this.getId(conn,fireHouseSQL.SELECT_DOCUMENT_ID);
      byte[] byteArray=oneFile.getFileData();
      pstmt =conn.prepareStatement(fireHouseSQL.UPLOAD_DOCUMENT);
      pstmt.setBytes(4,byteArray);
      pstmt.setInt(1,id);
      pstmt.setString(2,oneFile.getFileName());
      pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
      pstmt.setString(5,userEmail);
      pstmt.setString(7,"HIPAA");
      pstmt.setString(6,provNumber);
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

   public List selectFileList(String permitIdNumber) throws SQLException, Exception 
  {
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   
   try 
   {
  
     conn = getConnection();
     pstmt = conn.prepareStatement(fireHouseSQL.SELECT_DOCUMENT_LIST);
     pstmt.clearParameters();
     if(permitIdNumber != null) {
       pstmt.setString(1,permitIdNumber);
     }
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { FileNames names = new FileNames();
       names.setFileName(rs.getString(1));
       names.setFileType(rs.getString(2));
       names.setFileDate(rs.getDate(3));
       names.setFileLoadedBy(rs.getString(5));
       names.setFileId(rs.getInt(4));
       names.setFilePlanType(rs.getString(6));
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
  String sql = fireHouseSQL.SELECT_DOCUMENT; 
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
  public List selectFileListAdmin(String startDate,String endDate) throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  {
  
    conn = getConnection();
    String sql=fireHouseSQL.SELECT_DOCUMENT_LIST_ADMIN;
    if (startDate == null || endDate == null ||startDate.equals("") || endDate.equals("")) {
      sql =sql +" AND DOCUMENT_STATUS='Pending'";
    }
    else if (startDate.equals("xx") || endDate.equals("xx")) {
      sql =sql ;
    }
    else {
      sql =sql +" AND DOCUMENT_UPLOAD_DATE BETWEEN TO_DATE('"+startDate+"','mm/dd/yyyy') and TO_DATE('"+endDate+"','mm/dd/yyyy')";
    }
    sql = sql +" order by DOCUMENT_UPLOAD_DATE ";
    pstmt = conn.prepareStatement(sql);
    pstmt.clearParameters();
    rs = pstmt.executeQuery();
    while(rs.next()) 
    { FileNames names = new FileNames();
      names.setFileName(rs.getString(1));
      names.setFileType(rs.getString(2));
      names.setFileDate(rs.getDate(3));
      names.setFileLoadedBy(rs.getString(5));
      names.setFileId(rs.getInt(4));
      names.setFilePlanType(rs.getString(6));
      names.setFileConnector(rs.getString(7));
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
  public void  updateFileStatus(int fileId ) throws SQLException, Exception
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  try
  {
  conn = getConnection();
  pstmt = conn.prepareStatement(fireHouseSQL.UPDATE_DOCUMENT_STATUS);
  pstmt.clearParameters();
  pstmt.setInt(1,fileId);
  pstmt.execute();
  
  }
  finally
  {
  try {conn.close();
  pstmt.close();
  } catch (Exception e) {}
  }
  }
  public  void deleteFile(int fileId)   throws SQLException, Exception 
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  
  try
  { conn = getConnection();
   pstmt = conn.prepareStatement(" DELETE FROM  DFBS_DOCUMENTS_FIREHOUSE WHERE DOCUMENT_ID=?");
   pstmt.clearParameters();
    pstmt.setInt(1,fileId);
   pstmt.execute();
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
