package Variance.data;

import Variance.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import Variance.to.*;
import hs.data.*;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
public class DfbsOwnerDAO extends HsDAO
{
  
  
   public DfbsOwnerDAO()
   {
      
   }
  
  
   public DfbsOwner selectOwner(int ownerId) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
        
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(varianceSQL.SELECT_OWNER);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerLastName(rs.getString(2));
        owner.setOwnerFirstName(rs.getString(3));
        owner.setOwnerMI(rs.getString(4));
        owner.setOwnerDBA(rs.getString(5));
        owner.setOwnerAddress1(rs.getString(7));
        owner.setOwnerAddress2(rs.getString(8));
        owner.setOwnerCity(rs.getString(9));
        owner.setOwnerZip(rs.getString(11));
        owner.setOwnerZip2(rs.getString(12));
        owner.setOwnerPhone(rs.getString(13));
        owner.setOwnerFax(rs.getString(14));
        owner.setOwnerEmail(rs.getString(15));
        owner.setOwnerStateId(rs.getInt(16));
     }
    
   
      return owner;
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
  
  
 
 

  public int insertOwner(DfbsOwner owner) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
     int id = this.getId(conn,varianceSQL.SELECT_NEXT_OWNER_ID);
      owner.setOwnerId(id);
      
      pstmt = conn.prepareStatement(varianceSQL.INSERT_OWNER);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      pstmt.setString(2,owner.getOwnerLastName());
      pstmt.setString(3,owner.getOwnerFirstName());
      pstmt.setString(4,owner.getOwnerMI());
      pstmt.setString(5,owner.getOwnerDBA());
      pstmt.setString(6,owner.getOwnerPhone());
      
      pstmt.execute();
this.insertAddress(conn,owner);
      
      conn.commit();
      
      return 1;
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
      
      pstmt = conn.prepareStatement(varianceSQL.UPDATE_OWNER);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getOwnerLastName());
      pstmt.setString(2,owner.getOwnerFirstName());
      pstmt.setString(3,owner.getOwnerMI());
      pstmt.setString(4,owner.getOwnerDBA());
      pstmt.setString(5,owner.getOwnerPhone());
      pstmt.setInt(6,owner.getOwnerId());
     
      pstmt.execute();
     
      
      this.updateAddress(conn,owner);
     
     
      
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
      int addressId = HsDAO.getId(conn,varianceSQL.SELECT_NEXT_ADDRESS_ID);
      pstmt = conn.prepareStatement(varianceSQL.INSERT_OWNER_ADDRESS);
      pstmt.clearParameters();
      pstmt.setInt(1,addressId);
      pstmt.setInt(2,owner.getOwnerId());
      pstmt.setInt(3,owner.getOwnerStateId());
      pstmt.setString(4,owner.getOwnerAddress1());
      pstmt.setString(5,owner.getOwnerAddress2());
      pstmt.setString(6,owner.getOwnerCity());
      pstmt.setString(7,owner.getOwnerZip());
      pstmt.setString(8,owner.getOwnerEmail());
      pstmt.setString(9,owner.getOwnerFax());
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
      pstmt = conn.prepareStatement(varianceSQL.UPDATE_OWNER_ADDRESS);
      pstmt.clearParameters();
      
      
      pstmt.setInt(8,owner.getOwnerId());
      pstmt.setInt(1,owner.getOwnerStateId());
      pstmt.setString(2,owner.getOwnerAddress1());
      pstmt.setString(3,owner.getOwnerAddress2());
      pstmt.setString(4,owner.getOwnerCity());
      pstmt.setString(5,owner.getOwnerZip());
      pstmt.setString(6,owner.getOwnerEmail());
      pstmt.setString(7,owner.getOwnerFax());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
  

 
   
}
