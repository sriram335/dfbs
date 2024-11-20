package ust.data;




import ust.to.*;
import ust.data.*;
import hs.data.*;

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




public class ustDAO  extends HsDAO {
    public ustDAO() {
       
    }
  public int insertUst(ustApplicant ust) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,ustSQL.SELECT_NEXT_UST_SEQUENCE );
     ust.setUstId(id);
     pstmt = conn.prepareStatement(ustSQL.INSERT_UST);
     pstmt.clearParameters();
     pstmt.setString(3,ust.getCompanyName());
     pstmt.setString(4,ust.getPersonAddress1());
     pstmt.setString(5,ust.getPersonAddress2());
     pstmt.setString(6,ust.getPersonCity());
     pstmt.setString(13,ust.getPersonEmail());
     pstmt.setString(12,ust.getPersonFax());
     pstmt.setString(14,ust.getPersonFirstName());
     pstmt.setString(15,ust.getPersonLastName());
     pstmt.setString(16,ust.getPersonMi());
     pstmt.setString(10,ust.getPersonPhoneHome());
     pstmt.setString(11,ust.getPersonPhoneOffice());   
     pstmt.setString(7,ust.getPersonState());
     pstmt.setString(8,ust.getPersonZip());
     pstmt.setString(9,ust.getPersonZip2());
     pstmt.setString(2,ust.getAddressFlag());
     pstmt.setInt(1,id);
     pstmt.setString(17,ust.getComments());   
     pstmt.execute();
     pstmt1 = conn.prepareStatement(ustSQL.UPDATE_DOCUMENT_NAME);
     pstmt1.clearParameters();
     pstmt1.setString(1,Integer.toString(id));
     pstmt1.setString(2,"100");
     pstmt1.setString(3,"UST");
      pstmt1.execute();
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
   public ustApplicant selectust(int ustId) throws SQLException, Exception 
  {
  
   ustApplicant ust = new ustApplicant();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
     pstmt = conn.prepareStatement(ustSQL.SELECT_UST);
     pstmt.clearParameters();
     pstmt.setInt(1,ustId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     { ust.setCompanyName(rs.getString(3));
        ust.setPersonAddress1(rs.getString(4));
        ust.setPersonAddress2(rs.getString(5));
        ust.setPersonCity(rs.getString(6));
        ust.setPersonEmail(rs.getString(13));
        ust.setPersonFax(rs.getString(12));
        ust.setPersonFirstName(rs.getString(14));
        ust.setPersonLastName(rs.getString(15));
        ust.setPersonMi(rs.getString(16));
        ust.setPersonPhoneHome(rs.getString(10));
        ust.setPersonState(rs.getString(7));
        ust.setPersonPhoneOffice(rs.getString(11));
        ust.setPersonZip(rs.getString(8));
        ust.setPersonZip2(rs.getString(9));
        ust.setAddressFlag(rs.getString(2));
        ust.setUstId(rs.getInt(1));
   }
    return ust;
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
  
  public void updateUst(ustApplicant ust) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(ustSQL.UPDATE_UST);
     pstmt.clearParameters();
     pstmt.setString(3,ust.getCompanyName());
     pstmt.setString(4,ust.getPersonAddress1());
     pstmt.setString(5,ust.getPersonAddress2());
     pstmt.setString(6,ust.getPersonCity());
     pstmt.setString(13,ust.getPersonEmail());
     pstmt.setString(12,ust.getPersonFax());
     pstmt.setString(14,ust.getPersonFirstName());
     pstmt.setString(15,ust.getPersonLastName());
     pstmt.setString(1,ust.getPersonMi());
     pstmt.setString(10,ust.getPersonPhoneHome());
     pstmt.setString(11,ust.getPersonPhoneOffice());   
     pstmt.setString(7,ust.getPersonState());
     pstmt.setString(8,ust.getPersonZip());
     pstmt.setString(9,ust.getPersonZip2());
     pstmt.setString(2,ust.getAddressFlag());
     pstmt.setString(16,ust.getComments());
     pstmt.setInt(17,ust.getUstId());
       
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
  public List selectUstList(String psql,String byLetter) throws SQLException, Exception 
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
     
       ustApplicant ust = new ustApplicant();
       ust.setCompanyName(rs.getString(3));
               ust.setPersonAddress1(rs.getString(4));
               ust.setPersonAddress2(rs.getString(5));
               ust.setPersonCity(rs.getString(6));
               ust.setPersonEmail(rs.getString(13));
               ust.setPersonFax(rs.getString(12));
               ust.setPersonFirstName(rs.getString(14));
               ust.setPersonLastName(rs.getString(15));
               ust.setPersonMi(rs.getString(16));
               ust.setIssueDate(rs.getString(17));
               ust.setExpDate(rs.getString(18));
               ust.setStatus(rs.getString(19));
               ust.setPersonPhoneHome(rs.getString(10));
               ust.setPersonState(rs.getString(7));
               ust.setPersonPhoneOffice(rs.getString(11));
               ust.setPersonZip(rs.getString(8));
               ust.setPersonZip2(rs.getString(9));
               ust.setAddressFlag(rs.getString(2));
               ust.setUstId(rs.getInt(1));   
       list.add(ust);
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
    String sql = ustSQL.SELECT_DOCUMENT; 
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
  public List selectFileList(String ustId) throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  { 
  
    conn = getConnection();
   
   
    pstmt = conn.prepareStatement(ustSQL.SELECT_DOCUMENT_NAMES_ALL);
       pstmt.clearParameters();
   
    if(ustId != null) {
      pstmt.setString(1,ustId);
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
      names.setFileConnector(rs.getString(6)); 
      names.setFilePlanType(rs.getString(7)); 
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
  public void  uploadFile(FormFile oneFile,String ustId) throws SQLException, Exception
   { 
     Connection conn = null;
     PreparedStatement pstmt = null;
     try 
     
     {  conn = getConnection();
        int id = this.getId(conn,ustSQL.SELECT_DOCUMENT_ID);
        byte[] byteArray=oneFile.getFileData();
        pstmt =conn.prepareStatement(ustSQL.UPLOAD_DOCUMENT);
        pstmt.setBytes(4,byteArray);
        pstmt.setInt(1,id);
        pstmt.setString(2,oneFile.getFileName());
        pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
        pstmt.setString(5,"web");
        pstmt.setString(6,ustId);
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
  public void insertPermitTransaction(ustApplicant ust,int receiptId,ShoppingCart cart,ustCertification cert) throws SQLException, Exception 
   {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
       int id = this.getId(conn,ustSQL.SELECT_NEXT_TRANSACTION_ID);
       pstmt = conn.prepareStatement(ustSQL.INSERT_PERMIT_TRANSACTION);
       pstmt.clearParameters();
       pstmt.setInt(1,id);
       pstmt.setDouble(2,cart.getAmount());
       StringBuffer sb = new StringBuffer();
       pstmt.setString(3,"UST Certificate Fee: " +cert.getCertNumber()+":"+Integer.toString(ust.getUstId())+" CONFIRMATION "+receiptId);
       pstmt.setInt(4,receiptId);
       pstmt.setString(5,cert.getCertNumber());
       pstmt.setString(6,ust.getPersonLastName());
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
