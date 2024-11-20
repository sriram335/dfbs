package display.data;

import display.to.*;
import display.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;


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
       pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_CONTACT_BY_ID);
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
      int personId = HsDAO.getId(conn,DfbsDisplaySQL.SELECT_NEXT_PERSON_ID);
      contact.setPersonId(personId);
      pstmt = conn.prepareStatement(DfbsDisplaySQL.INSERT_PERSON);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setInt(2,personId);
      pstmt.setString(3,contact.getPersonFirstName());
      pstmt.setString(4,contact.getPersonLastName());
      pstmt.setString(5,contact.getPersonPhone());
      pstmt.execute();
      if (contact.getPersonAddress1().length() > 4)
      {
      this.insertAddress(conn,contact);
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
  
   public void updateContact( DfbsContact contact) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.UPDATE_PERSON);
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
      int addressId = HsDAO.getId(conn,DfbsDisplaySQL.SELECT_NEXT_ADDRESS_ID);
      pstmt = conn.prepareStatement(DfbsDisplaySQL.INSERT_PERSON_ADDRESS);
      pstmt.clearParameters();
      pstmt.setInt(1,addressId);
      pstmt.setInt(2,contact.getPersonId());
      pstmt.setInt(3,contact.getPersonStateId());
      pstmt.setString(4,contact.getPersonAddress1());
      pstmt.setString(5,contact.getPersonAddress2());
      pstmt.setString(6,contact.getPersonCity());
      pstmt.setString(7,contact.getPersonZip());
      pstmt.setString(8,contact.getPersonEmail());
      pstmt.setString(9,contact.getPersonFax());
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
      pstmt = conn.prepareStatement(DfbsDisplaySQL.UPDATE_PERSON_ADDRESS);
      pstmt.clearParameters();
      pstmt.setInt(8,contact.getPersonId());
      pstmt.setInt(1,contact.getPersonStateId());
      pstmt.setString(2,contact.getPersonAddress1());
      pstmt.setString(3,contact.getPersonAddress2());
      pstmt.setString(4,contact.getPersonCity());
      pstmt.setString(5,contact.getPersonZip());
      pstmt.setString(6,contact.getPersonEmail());
      pstmt.setString(7,contact.getPersonFax());
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
