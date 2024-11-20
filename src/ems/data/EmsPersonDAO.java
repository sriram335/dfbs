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

public class EmsPersonDAO extends HsDAO
{
  public EmsPersonDAO()
  {
  }
public List selectPersonList(String sql,int param,String personType) throws SQLException, Exception 
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
      if(param != 0) {
        pstmt.setInt(1,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsPerson person = new EmsPerson();
         person.setEmsPersonId(rs.getInt(1));
         person.setPersonFirstName(rs.getString(2));
         person.setPersonLastName(rs.getString(3));
         person.setPersonMi(rs.getString(4));
         person.setPersonAddress1(rs.getString(8));
         person.setPersonAddress2(rs.getString(9));
         person.setPersonCity(rs.getString(10));
         person.setPersonState(rs.getString(11));
         person.setPersonZip(rs.getString(12));
         person.setPersonZip2(rs.getString(13));
         person.setPersonEmail(rs.getString(5));
         person.setPersonPhone(rs.getString(6));
         person.setPersonFax(rs.getString(7));
          if (personType=="P")
         {
         person.setPersonProvId(rs.getInt(14));
         }
         if (personType=="I")
         {
         person.setPersonInstId(rs.getInt(14));
         }
         if (personType=="H")
         {
         person.setPersonHospId(rs.getInt(14));
         }
         person.setPersonTitle(rs.getString(15));
        list.add(person);
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
  public EmsPerson selectPerson(int personId,int relationId, String relationType) throws SQLException, Exception 
  {
     EmsPerson person = new EmsPerson();
    Connection conn = null;
     
    try 
    {
      conn = getConnection();
      person = selectPerson(conn,personId,relationId,relationType);
      return person;
    }
    finally 
    {
     try {
        conn.close();
       } catch (Exception e) {}
    }
  }
   public EmsPerson selectPerson(Connection conn,int personId,int relationId, String relationType) throws SQLException, Exception 
  {
    EmsPerson person = new EmsPerson();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      if (relationType =="P")
      {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_PERSON_PROVIDER);
      }
       if (relationType =="H")
      {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_PERSON_HOSPITAL);
      }
       if (relationType =="I")
      {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_PERSON_INSTITUTION);
      }
      pstmt.clearParameters();
      pstmt.setInt(1,personId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
         person.setEmsPersonId(rs.getInt(1));
         person.setPersonFirstName(rs.getString(2));
         person.setPersonLastName(rs.getString(3));
         person.setPersonMi(rs.getString(4));
         person.setPersonAddress1(rs.getString(8));
         person.setPersonAddress2(rs.getString(9));
         person.setPersonCity(rs.getString(10));
         person.setPersonState(rs.getString(11));
         person.setPersonZip(rs.getString(12));
         person.setPersonZip2(rs.getString(13));
         person.setPersonEmail(rs.getString(5));
         person.setPersonPhone(rs.getString(6));
         person.setPersonFax(rs.getString(7));
         person.setPersonType(rs.getString(14));
         person.setPersonTitle(rs.getString(15));
         if (relationType =="P")
         {
           person.setPersonProvId(relationId);
         }
         if (relationType =="I")
         {
           person.setPersonInstId(relationId);
         }
         if (relationType =="H")
         {
           person.setPersonHospId(relationId);
         }
        
      }
      return person;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updatePerson(EmsPerson person,String relationType, int relationId) throws SQLException, Exception 
  {
           Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_PERSON);
          pstmt.clearParameters();
          
          pstmt.setString(1,person.getPersonFirstName());
          pstmt.setString(2,person.getPersonLastName());
          pstmt.setString(3,person.getPersonMi());
          pstmt.setString(4,person.getPersonEmail());
          pstmt.setString(5,person.getPersonPhone());
          pstmt.setString(6,person.getPersonFax());
          pstmt.setString(7,person.getPersonAddress1());
          pstmt.setString(8,person.getPersonAddress2());
          pstmt.setString(9,person.getPersonCity());
          pstmt.setString(10,person.getPersonState());
          pstmt.setString(11,person.getPersonZip());
          pstmt.setString(12,person.getPersonZip2());
          pstmt.setInt(13,person.getEmsPersonId());
          
          pstmt.execute();
           conn.commit();
          if (relationType =="P")
          {
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_PERSON_PROVIDER);
          }
          if (relationType =="H")
          {
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_PERSON_HOSPITAL);
          }
          if (relationType =="I")
          {
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_PERSON_INSTITUTION);
          }
          pstmt.clearParameters();
          pstmt.setString(1,person.getPersonType());
          pstmt.setString(2,person.getPersonTitle());
          pstmt.setString(3,person.getEndDateString());
          pstmt.setInt(4,person.getEmsPersonId());
          pstmt.setInt(5,relationId);
          
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
  
  public List selectPersonListLookup(String sql,String lastName) throws SQLException, Exception 
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
      pstmt.setString(1,lastName);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsPerson person = new EmsPerson();
         person.setEmsPersonId(rs.getInt(1));
         person.setPersonFirstName(rs.getString(2));
         person.setPersonLastName(rs.getString(3));
         person.setPersonMi(rs.getString(4));
         person.setPersonAddress1(rs.getString(8));
         person.setPersonAddress2(rs.getString(9));
         person.setPersonCity(rs.getString(10));
         person.setPersonState(rs.getString(11));
         person.setPersonZip(rs.getString(12));
         person.setPersonZip2(rs.getString(13));
         person.setPersonEmail(rs.getString(5));
         person.setPersonPhone(rs.getString(6));
         person.setPersonFax(rs.getString(7));
         
        list.add(person);
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
  public void addNewContact(int personId,int relationId,String relationType, String personTitle,String personType) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          if (relationType == "H")
          {
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON_HOSPITAL);
          }
           if (relationType == "I")
          {
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON_INSTITUTION);
          }
           if (relationType == "P")
          {
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON_PROVIDER);
          }
          pstmt.clearParameters();
          
          pstmt.setInt(1,personId);
          pstmt.setInt(2,relationId);
          pstmt.setString(3,personType);
          pstmt.setString(4,personTitle);
          
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
  
   public void insertPerson(EmsPerson person,String relationType, int relationId) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          int personId = this.getId(conn,EmsSQL.SELECT_NEXT_PERSON_ID);
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON);
          pstmt.clearParameters();
          
          pstmt.setInt(1,personId);
          pstmt.setString(2,person.getPersonFirstName());
          pstmt.setString(3,person.getPersonLastName());
          pstmt.setString(4,person.getPersonMi());
          pstmt.setString(5,person.getPersonEmail());
          pstmt.setString(6,person.getPersonPhone());
          pstmt.setString(7,person.getPersonFax());
          pstmt.setString(8,person.getPersonAddress1());
          pstmt.setString(9,person.getPersonAddress2());
          pstmt.setString(10,person.getPersonCity());
          pstmt.setString(11,person.getPersonState());
          pstmt.setString(12,person.getPersonZip());
          pstmt.setString(13,person.getPersonZip2());
          
          
          pstmt.execute();
           conn.commit();
          if (relationType == "H")
          {
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON_HOSPITAL);
          }
           if (relationType == "I")
          {
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON_INSTITUTION);
          }
           if (relationType == "P")
          {
          pstmt = conn.prepareStatement(EmsSQL.INSERT_PERSON_PROVIDER);
          }
          pstmt.clearParameters();
          
          pstmt.setInt(1,personId);
          pstmt.setInt(2,relationId);
          pstmt.setString(3,person.getPersonType());
          pstmt.setString(4,person.getPersonTitle());
          
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
  
 
  public void  uploadFile(FormFile oneFile,String permitNumber) throws SQLException, Exception
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
      pstmt.setString(5,"web");
      pstmt.setString(6,permitNumber);
       pstmt.setString(7,"Blaster/Operator");
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
     pstmt = conn.prepareStatement(EmsSQL.SELECT_DOCUMENT_NAMES_ALL);
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
}
