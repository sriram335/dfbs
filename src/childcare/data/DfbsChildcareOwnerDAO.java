package childcare.data;

import childcare.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import childcare.to.*;
import hs.data.*;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
public class DfbsChildcareOwnerDAO extends HsDAO
{
  
   
  
   public DfbsChildcareOwnerDAO()
   {
      
   }
  
   public List selectOwnersList(String sql,String param,String byType, String byName, String structureType) throws SQLException, Exception 
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
        pstmt.setString(1,structureType);
        pstmt.setString(2,param);
      }
      
      rs = pstmt.executeQuery();
     
      while(rs.next()) 
      {
      
        DfbsOwner owner = new DfbsOwner();
        
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerFirstName(rs.getString(3));
        owner.setOwnerLastName(rs.getString(2));
        owner.setOwnerMI(rs.getString(4));
        owner.setOwnerDBA(rs.getString(5));
        owner.setOwnerAddress1(rs.getString(7));
        owner.setOwnerAddress2(rs.getString(8));
        owner.setOwnerCity(rs.getString(9));
        owner.setOwnerState(rs.getString(10));
        owner.setOwnerZip(rs.getString(11));
        owner.setOwnerZip2(rs.getString(12)); 
        owner.setOwnerPhone(rs.getString(13));
        owner.setOwnerFax(rs.getString(14));
        owner.setOwnerEmail(rs.getString(15));
        
       owner.setChildCares(DfbsChildcarePermitDAO.selectPermits(conn,owner.getOwnerId(),byType,byName,owner));
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
  
   public DfbsOwner selectOwner(int ownerId) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
        
    try 
    {
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_OWNER);
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
        owner.setOwnerState(rs.getString(10));
        owner.setOwnerZip(rs.getString(11));
        owner.setOwnerZip2(rs.getString(12));
        owner.setOwnerPhone(rs.getString(13));
        owner.setOwnerFax(rs.getString(14));
        owner.setOwnerEmail(rs.getString(15));
        owner.setOwnerStateId(rs.getInt(16));
        //owner.setChildCares(DfbsChildcarePermitDAO.selectPermits(conn,owner.getOwnerId(),"All","",owner));
        //owner.setContacts(DfbsContactDAO.selectContactList(owner,conn,owner.getOwnerId()));
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
      int id = this.getId(conn,DfbsChildcareSQL.SELECT_NEXT_OWNER_ID);
      owner.setOwnerId(id);
      
      pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_OWNER);
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
      
      pstmt = conn.prepareStatement(DfbsChildcareSQL.UPDATE_OWNER);
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
      int addressId = HsDAO.getId(conn,DfbsChildcareSQL.SELECT_NEXT_ADDRESS_ID);
      pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_OWNER_ADDRESS);
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
      pstmt = conn.prepareStatement(DfbsChildcareSQL.UPDATE_OWNER_ADDRESS);
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
  
  
 public List selectOwnersList(String sql,String param,String structureType) throws SQLException, Exception 
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
      if(structureType != null) {
        pstmt.setString(1,structureType);
         pstmt.setString(2,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
      
        DfbsOwner owner = new DfbsOwner();
        
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerFirstName(rs.getString(3));
        owner.setOwnerLastName(rs.getString(2));
        owner.setOwnerMI(rs.getString(4));
        owner.setOwnerDBA(rs.getString(5));
        owner.setOwnerAddress1(rs.getString(7));
        owner.setOwnerAddress2(rs.getString(8));
        owner.setOwnerCity(rs.getString(9));
        owner.setOwnerState(rs.getString(10));
        owner.setOwnerZip(rs.getString(11));
        owner.setOwnerZip2(rs.getString(12)); 
        owner.setOwnerPhone(rs.getString(13));
        owner.setOwnerFax(rs.getString(14));
        owner.setOwnerEmail(rs.getString(15));
        
      owner.setChildCares(DfbsChildcarePermitDAO.selectPermits(conn,owner.getOwnerId()));
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
  public String ownerRenewStatus(DfbsOwner owner) throws SQLException, Exception 
  {
    
   String renewStatus ="Y";
      if(owner.getRenewStatus()==null)
      {
      renewStatus ="N";
      }
        Map mapContact = owner.getContactsMap();
          Set contactkeys = mapContact.keySet();
          Iterator k = contactkeys.iterator();
          while(k.hasNext())
           { 
           String key = (String) k.next();
           DfbsContact contact = (DfbsContact) mapContact.get(key); 
            if(contact.getRenewStatus()==null)
           { 
            renewStatus ="N";
            }
           }
          Map mapChildCare = owner.getChildCaresMap();
          Set ChildCarekeys = mapChildCare.keySet();
          Iterator p = ChildCarekeys.iterator();
          while(k.hasNext())
           { 
           String key = (String) k.next();
           DfbsChildcarePermit childCare = (DfbsChildcarePermit) mapChildCare.get(key); 
            if(childCare.getRenewStatus()==null)
           { 
            renewStatus ="N";
            }
           }
    return renewStatus;
  }
   
}
