package childcare.data;

import childcare.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;
import childcare.to.*;

public class DfbsContactDAO extends HsDAO
{
  public DfbsContactDAO()
  {
   
  }
   public DfbsContact selectContact(int idNumber) throws SQLException, Exception 
  {
  
    DfbsContact contact = new DfbsContact();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CONTACT_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      contact.setPersonAddress1(rs.getString(6));
      contact.setPersonAddress2(rs.getString(7));
      contact.setPersonCity(rs.getString(8));
      contact.setPersonEmail(rs.getString(13));
      contact.setPersonFax(rs.getString(12));
      contact.setPersonFirstName(rs.getString(2));
      contact.setPersonLastName(rs.getString(3));
      contact.setPersonMi(rs.getString(4));
      contact.setPersonPhone(rs.getString(11));
      contact.setPersonStateId(rs.getInt(9));
      contact.setPersonType(rs.getString(5));
      contact.setPersonZip(rs.getString(10));
      contact.setPersonZip2(rs.getString(11));
      contact.setPersonId(rs.getInt(1));
      contact.setOwnerId(rs.getInt(14));
      contact.setAddressId(rs.getInt(15));
      }
    
   
      return contact;
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
  
   public void insertContact(DfbsContact contact, int ownerId)  throws SQLException, Exception 
  
  {
    PreparedStatement pstmt = null;
    Connection conn = null;
    
    try 
    { conn = getConnection();
      int personId = HsDAO.getId(conn,DfbsChildcareSQL.SELECT_NEXT_PERSON_ID);
      contact.setPersonId(personId);
      pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_PERSON);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setInt(2,personId);
      pstmt.setString(3,contact.getPersonFirstName());
      pstmt.setString(4,contact.getPersonLastName());
      pstmt.setString(5,contact.getPersonPhone());
      pstmt.execute();
           this.insertAddress(conn,contact);
      
    } 
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   public void updateContact( DfbsContact contact) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    Connection conn = null;
     try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(DfbsChildcareSQL.UPDATE_PERSON);
      pstmt.clearParameters();
      pstmt.setString(1,contact.getPersonFirstName());
      pstmt.setString(2,contact.getPersonLastName());
      pstmt.setString(3,contact.getPersonPhone());
      pstmt.setInt(4,contact.getPersonId());
      pstmt.execute();
       if (contact.getAddressId()== 0)
      {
      this.insertAddress(conn,contact);
      }
      else 
      {
      this.updateAddress(conn,contact);
      }
    } 
    finally 
    {
     try {
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
  private static void insertAddress(Connection conn, DfbsContact contact) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    
    try 
    {
      int addressId = HsDAO.getId(conn,DfbsChildcareSQL.SELECT_NEXT_ADDRESS_ID);
      pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_PERSON_ADDRESS);
      pstmt.clearParameters();
      pstmt.setInt(1,addressId);
      pstmt.setInt(2,contact.getPersonId());
      pstmt.setString(3,contact.getPersonEmail());
      pstmt.setString(4,contact.getPersonFax());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   private static void updateAddress(Connection conn, DfbsContact contact) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsChildcareSQL.UPDATE_PERSON_ADDRESS);
      pstmt.clearParameters();
     
      pstmt.setInt(3,contact.getPersonId());
      pstmt.setString(2,contact.getPersonEmail());
      pstmt.setString(1,contact.getPersonFax());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   public List selectContactList(int idNumber) throws SQLException, Exception 
  {
  
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    List contactList = new ArrayList();
       
    try 
    {
     
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CONTACTS);
      pstmt.clearParameters();
      pstmt.setInt(1,idNumber);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { DfbsContact contact = new DfbsContact();
      contact.setPersonAddress1(rs.getString(6));
      contact.setPersonAddress2(rs.getString(7));
      contact.setPersonCity(rs.getString(8));
      contact.setPersonEmail(rs.getString(13));
      contact.setPersonFax(rs.getString(12));
      contact.setPersonFirstName(rs.getString(2));
      contact.setPersonLastName(rs.getString(3));
      contact.setPersonMi(rs.getString(4));
      contact.setPersonPhone(rs.getString(11));
      contact.setPersonStateId(rs.getInt(9));
      contact.setPersonType(rs.getString(5));
      contact.setPersonZip(rs.getString(10));
      contact.setPersonZip2(rs.getString(11));
      contact.setPersonId(rs.getInt(1));
      contact.setOwnerId(rs.getInt(14));
      contact.setAddressId(rs.getInt(15));
      contactList.add(contact);
      }
    
   
      return contactList;
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
   public static List selectContactList(DfbsOwner owner,Connection conn, int idNumber) throws SQLException, Exception 
  {
  
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    List contactList = new ArrayList();
       
    try 
    {
     
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CONTACTS);
      pstmt.clearParameters();
      pstmt.setInt(1,idNumber);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { DfbsContact contact = new DfbsContact();
      contact.setPersonAddress1(rs.getString(6));
      contact.setPersonAddress2(rs.getString(7));
      contact.setPersonCity(rs.getString(8));
      contact.setPersonEmail(rs.getString(13));
      contact.setPersonFax(rs.getString(12));
      contact.setPersonFirstName(rs.getString(2));
      contact.setPersonLastName(rs.getString(3));
      contact.setPersonMi(rs.getString(4));
      contact.setPersonPhone(rs.getString(11));
      contact.setPersonStateId(rs.getInt(9));
      contact.setPersonType(rs.getString(5));
      contact.setPersonZip(rs.getString(10));
      contact.setPersonZip2(rs.getString(11));
      contact.setPersonId(rs.getInt(1));
      contact.setOwnerId(rs.getInt(14));
      contact.setAddressId(rs.getInt(15));
      contactList.add(contact);
      owner.addContact(contact);
      }
    
   
      return contactList;
    }
    finally 
    {
     try {
         rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void selectContactMap(int idNumber,DfbsOwner owner ) throws SQLException, Exception 
  {
  
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    List contactList = new ArrayList();
       
    try 
    {
     
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CONTACTS);
      pstmt.clearParameters();
      pstmt.setInt(1,idNumber);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { DfbsContact contact = new DfbsContact();
      contact.setPersonAddress1(rs.getString(6));
      contact.setPersonAddress2(rs.getString(7));
      contact.setPersonCity(rs.getString(8));
      contact.setPersonEmail(rs.getString(13));
      contact.setPersonFax(rs.getString(12));
      contact.setPersonFirstName(rs.getString(2));
      contact.setPersonLastName(rs.getString(3));
      contact.setPersonMi(rs.getString(4));
      contact.setPersonPhone(rs.getString(11));
      contact.setPersonStateId(rs.getInt(9));
      contact.setPersonType(rs.getString(5));
      contact.setPersonZip(rs.getString(10));
      contact.setPersonZip2(rs.getString(11));
      contact.setPersonId(rs.getInt(1));
      contact.setOwnerId(rs.getInt(14));
      contact.setAddressId(rs.getInt(15));
       if (owner.getRenewStatus().equals("Y"))
       {
         contact.setRenewStatus("Y");
       }
       owner.addContact(contact);
      }
    
   
      
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
