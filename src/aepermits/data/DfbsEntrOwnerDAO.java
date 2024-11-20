package aepermits.data;

import  aepermits.to.*;
import  aepermits.data.*;


import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;
public class DfbsEntrOwnerDAO extends HsDAO
{
  
   
  
   public DfbsEntrOwnerDAO()
   {
      super();
   }
  
   public  DfbsEntrOwnerDAO(HsDataSource pool)
   {
    super(pool);
    
   }
   
   public List selectOwnersList(String sql,String param) throws SQLException, Exception 
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
      
        DfbsOwner owner = new DfbsOwner();
        
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(2));
        
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
    //    owner.setCounty(rs.getString(9));
                
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        
        owner.setContactId(rs.getInt(13));
        owner.setFirstName(rs.getString(14));
        owner.setLastName(rs.getString(15));
        owner.setContactPhoneNumber(rs.getString(16));
        owner.setStateId(rs.getInt(17));

        list.add(owner);
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
  
   public DfbsOwner selectOwner(String ownerId) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    Connection conn = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      owner = selectOwner(conn,ownerId);
      owner.setPermits(DfbsEntrPermitDAO.selectPermits(conn,ownerId));
      return owner;
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
  
  public  DfbsOwner selectOwner(Connection conn,String ownerId) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_OWNER);
      pstmt.clearParameters();
      pstmt.setString(1,ownerId);
      rs = pstmt.executeQuery();
       if(rs.next()) 
      {
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(2));
        
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
   //     owner.setCounty(rs.getString(9));
        
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        owner.setStateId(rs.getInt(17));
      this.selectContact(conn,owner);
        

      }
    
       return owner;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
 
  public  DfbsOwner selectContact(Connection conn,DfbsOwner owner) throws SQLException, Exception 
  {
  
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_CONTACT_PERSON);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      rs = pstmt.executeQuery();
       if(rs.next()) 
      {
               
         owner.setContactId(rs.getInt(1));
        owner.setFirstName(rs.getString(2));
        owner.setLastName(rs.getString(3));
        owner.setContactPhoneNumber(rs.getString(11));
     
      }
    
       return owner;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
 
  public int insertOwner(DfbsOwner owner) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,DfbsEntrSQL.SELECT_NEXT_OWNER_ID);
      owner.setOwnerId(id);
      
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_OWNER);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      pstmt.setString(2,owner.getOwnerName());
      pstmt.setString(3,owner.getPhoneNumber());
      pstmt.execute();
      
      this.insertAddress(conn,owner);
      this.insertPerson(conn,owner);
      
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
  public void updateOwner(DfbsOwner owner) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_OWNER);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getOwnerName());
      pstmt.setString(2,owner.getPhoneNumber());
      pstmt.setInt(3,owner.getOwnerId());
      pstmt.execute();
     
      if(owner.getAddressId() == 0) {
        this.insertAddress(conn,owner);
      } 
      else 
      {
        this.updateAddress(conn,owner);
      }
     
      if(owner.getContactId() == 0) {
        this.insertPerson(conn,owner);
      } 
      else 
      {
        this.updatePerson(conn,owner);
      }
      
     
      
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
  
  private static void insertAddress(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    
    try 
    {
      int addressId = HsDAO.getId(conn,DfbsEntrSQL.SELECT_NEXT_ADDRESS_ID);
      owner.setAddressId(addressId);
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_OWNER_ADDRESS);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getAddressId());
      pstmt.setInt(2,owner.getOwnerId());
      pstmt.setInt(3,owner.getStateId());
      pstmt.setString(4,owner.getStreet1());
      pstmt.setString(5,owner.getStreet2());
      pstmt.setString(6,owner.getCity());
      pstmt.setString(7,owner.getZip());
      pstmt.setString(8,owner.getOwnerEmail());
      pstmt.setString(9,owner.getFaxNumber());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   private static void updateAddress(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_OWNER_ADDRESS);
      pstmt.clearParameters();
      
      
      pstmt.setString(1,owner.getOwnerEmail());
      pstmt.setString(2,owner.getFaxNumber());
      pstmt.setInt(3,owner.getAddressId());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
  private static void insertPerson(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    try 
    {
      int personId = HsDAO.getId(conn,DfbsEntrSQL.SELECT_NEXT_PERSON_ID);
      owner.setContactId(personId);
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_PERSON);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      pstmt.setInt(2,owner.getContactId());
      pstmt.setString(3,owner.getFirstName());
      pstmt.setString(4,owner.getLastName());
      pstmt.setString(5,owner.getContactPhoneNumber());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   private static void updatePerson(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_PERSON);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getFirstName());
      pstmt.setString(2,owner.getLastName());
      pstmt.setString(3,owner.getContactPhoneNumber());
      pstmt.setInt(4,owner.getContactId());
      pstmt.execute();
    } 
    finally 
    {
     try {
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
 
  public int selectCount(String sql,int ownerId,String year) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,year);
      pstmt.setString(3,year);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      
        return rs.getInt(1);
      }
      
      return 0;
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
  
  public List selectOwnersListByStreet(String sql,String streetNumber, String byType) throws SQLException, Exception 
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
      pstmt.setString(1,streetNumber);
      
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
       DfbsOwner owner = new DfbsOwner();
        
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(2));
        
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
                
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        
        owner.setContactId(rs.getInt(13));
        owner.setFirstName(rs.getString(14));
        owner.setLastName(rs.getString(15));
        owner.setContactPhoneNumber(rs.getString(16));
        owner.setStateId(rs.getInt(17));
         owner.setPermits(DfbsEntrPermitDAO.selectPermitsByStreet(conn,owner.getOwnerId(),streetNumber,byType));
         
         list.add(owner);
         
      }
      return list;
    }
    finally 
    {
     try {conn.close();
       
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
  public void  uploadFile(FormFile oneFile,String IdNumber, String IdType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,DfbsEntrSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(DfbsEntrSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,"web");
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
     
     
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_DOCUMENT_NAMES_ALL);
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
  
 public int downLoad(OutputStream os,int fileId,String fileName)  throws SQLException, Exception 
 {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = DfbsEntrSQL.SELECT_DOCUMENT; 
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
    // StringBuffer sb = new StringBuffer();
      // sb.append( "\""+"\""+"iotfilp40pw.shared.state.in.us\""+"DHS2\""+"Restricted\""+"INFIRS\""+"EMS NEMSIS in"+ fileName);
      //  fileName=sb.toString();
   //  FileOutputStream f = new FileOutputStream(new File(fileName));  
   while ( (len=is.read(buf,0,1024)) != -1)
   {   
       os.write(buf,0,len);
        len_total += len; 
      // f.write(buf); 
     } 
   // f.close();
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
    PreparedStatement pstmt1 = null;
    String passWord="";
    String fileName="NoName";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_DOCUMENT_NAME);
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
  
  public void insertErrorLog(String appName,String recordId,String errorLocation) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    Connection conn = null;
   
    try 
    {  conn = getConnection();
      
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_ERROR_LOG);
      pstmt.clearParameters();
      pstmt.setString(1,appName);
      pstmt.setString(2,recordId);
      pstmt.setString(3,errorLocation);
     
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
   public List viewFileList(String IdNumber,String IdType) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
   
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_DOCUMENT_NAMES_VIEW);
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
     public  void deleteFile(int fileId)   throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(DfbsEntrSQL.DELETE_DOCUMENT);
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
  public  DfbsOwner selectOwnerForUpdate(String ownerId) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    {      conn = getConnection();
           conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_OWNER_FOR_UPDATE);
      pstmt.clearParameters();
      pstmt.setInt(1,Integer.parseInt(ownerId));
      rs = pstmt.executeQuery();
       if(rs.next()) 
      {
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(14));
        owner.setFirstName(rs.getString(13));
        owner.setLastName(rs.getString(2));
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        owner.setStateId(rs.getInt(15));
        
      }
    
       return owner;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public void updateOwnerInternal(DfbsOwner owner) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_OWNER_INTERNAL);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getOwnerName());
      pstmt.setString(3,owner.getLastName());
      pstmt.setString(2,owner.getFirstName());
      pstmt.setString(4,owner.getPhoneNumber());
      pstmt.setInt(5,owner.getOwnerId());
      pstmt.execute();
     
      if(owner.getAddressId() == 0) {
        this.insertAddress(conn,owner);
      } 
      else 
      {
        this.updateAddressInternal(owner);
      }
     
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
  public void updateAddressInternal( DfbsOwner owner) 
  throws SQLException, Exception
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
   
   try 
   {  conn = getConnection();
      conn.setAutoCommit(false);
      
     pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_OWNER_ADDRESS_INTERNAL);
     pstmt.clearParameters();
     pstmt.setString(1,owner.getStreet1());
     pstmt.setString(2,owner.getStreet2());  
     pstmt.setString(3,owner.getCity());
     pstmt.setInt(4,owner.getStateId());
     pstmt.setString(5,owner.getZip());
     pstmt.setString(6,owner.getOwnerEmail());
     pstmt.setString(7,owner.getFaxNumber());
     pstmt.setInt(8,owner.getAddressId());
     pstmt.execute();
     conn.commit();
   } 
    catch (Exception ex) 
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
