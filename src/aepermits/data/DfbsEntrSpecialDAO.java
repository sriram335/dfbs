package aepermits.data;

import  aepermits.to.*;
import  aepermits.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

public class DfbsEntrSpecialDAO extends HsDAO
{
  public DfbsEntrSpecialDAO()
  {
   super();
  }
  
  public  DfbsEntrSpecialDAO(HsDataSource pool)
   {
    super(pool);
    
   }
   
    protected static List selectSpecials(Connection conn,String permitNumber) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int x =0;
    try 
    {
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_SPECIALS);
      pstmt.clearParameters();
      pstmt.setString(1,permitNumber);
       rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsEntrSpecial s = new DfbsEntrSpecial();
        s.setSpecialId(rs.getInt(1));
        s.setApplicationDate(rs.getDate(2));
        s.setEventName(rs.getString(3));
        s.setEventDate(rs.getDate(4));
        s.setEventTime(rs.getString(5));
        s.setMaximumOccupancy(rs.getString(6));
        s.setComments(rs.getString(7));
        s.setPermitNumber(rs.getString(8));
        s.setIssueDate(rs.getDate(9));
        s.setExpirationDate(rs.getDate(10));
        s.setFeeExempt(rs.getString(11));
        s.setContactPerson(rs.getString(12));
        s.setContactPhone(rs.getString(13));
        s.setContactFax(rs.getString(14));
        s.setContactEmail(rs.getString(15));
        s.setPermitType(rs.getString(16));
        list.add(s);
      }
      
      return list;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public List selectNewSpecials() throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_NEW_SPECIALS);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsEntrSpecial s = new DfbsEntrSpecial();
        s.setSpecialId(rs.getInt(1));
        s.setApplicationDate(rs.getDate(2));
        s.setEventName(rs.getString(3));
        s.setEventDate(rs.getDate(4));
        s.setEventTime(rs.getString(5));
        s.setMaximumOccupancy(rs.getString(6));
        s.setComments(rs.getString(7));
        s.setPermitNumber(rs.getString(8));
        s.setIssueDate(rs.getDate(9));
        s.setExpirationDate(rs.getDate(10));
        s.setFeeExempt(rs.getString(11));
        s.setContactPerson(rs.getString(12));
        s.setContactPhone(rs.getString(13));
        s.setContactFax(rs.getString(14));
        s.setContactEmail(rs.getString(15));
        s.setPermitType(rs.getString(16));
        list.add(s);
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
  public int insertPermit(DfbsEntrSpecial special,String permitNumber) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null; 
   
    try 
    { 
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_SPECIAL_PERMIT);
      int id = this.getId(conn,DfbsEntrSQL.SELECT_SPECIAL_ID);
       
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      special.setSpecialId(id);
      pstmt.setString(2,special.getEventName());
      pstmt.setString(3,special.getEventDateString());
      pstmt.setString(4,special.getEventTime());
      pstmt.setString(5,special.getMaximumOccupancy());
      pstmt.setString(6,special.getComments());
      pstmt.setString(7,permitNumber);
      pstmt.setString(8,special.getFeeExempt());
      pstmt.setString(9,special.getContactPerson());
      pstmt.setString(10,special.getContactPhone());
      pstmt.setString(11,special.getContactFax());
      pstmt.setString(12,special.getContactEmail());
      pstmt.setString(13,special.getEventDateString());
       if(special.getPermitType() !=null) {
        special.setPermitType("SU");
      }
      pstmt.setString(14,special.getPermitType());
       pstmt.execute();
      
      pstmt1 = conn.prepareStatement(DfbsEntrSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,Integer.toString(id));
      pstmt1.setString(2,special.getApplicationKey());
      pstmt1.setString(3,"AESpecial");
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
   
  
    public DfbsEntrSpecial selectSpecial(String specialId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_SPECIAL_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,Integer.parseInt(specialId));
      rs = pstmt.executeQuery();
     DfbsEntrSpecial s = new DfbsEntrSpecial(); 
      while(rs.next()) 
      {
        
        s.setSpecialId(rs.getInt(1));
        s.setApplicationDate(rs.getDate(2));
        s.setEventName(rs.getString(3));
        s.setEventDate(rs.getDate(4));
        s.setEventTime(rs.getString(5));
        s.setMaximumOccupancy(rs.getString(6));
        s.setComments(rs.getString(7));
        s.setPermitNumber(rs.getString(8));
        s.setIssueDate(rs.getDate(9));
        s.setExpirationDate(rs.getDate(10));
        s.setFeeExempt(rs.getString(11));
        s.setContactPerson(rs.getString(12));
        s.setContactPhone(rs.getString(13));
        s.setContactFax(rs.getString(14));
        s.setContactEmail(rs.getString(15));
        s.setOnlineStatus(rs.getString(16));
        s.setPermitType(rs.getString(17));
      }
      
      return s;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateSpecial(DfbsEntrSpecial special) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
   
    try 
    { 
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_SPECIAL_PERMIT);
       
      pstmt.clearParameters();
      pstmt.setInt(16,special.getSpecialId());
      pstmt.setString(2,special.getEventName());
      pstmt.setString(3,special.getEventDateString());
      pstmt.setString(4,special.getEventTime());
      pstmt.setString(5,special.getMaximumOccupancy());
      pstmt.setString(6,special.getComments());
      pstmt.setString(7,special.getPermitNumber());
      pstmt.setString(8,special.getIssueDateString());
      pstmt.setString(9,special.getFeeExempt());
      pstmt.setString(10,special.getContactPerson());
      pstmt.setString(11,special.getContactPhone());
      pstmt.setString(12,special.getContactFax());
      pstmt.setString(13,special.getContactEmail());
      pstmt.setString(14,special.getOnlineStatus());
       pstmt.setString(1,special.getExpirationDateString());
      if(special.getPermitType() !=null) {
        special.setPermitType("SU");
      }
      pstmt.setString(15,special.getPermitType());  
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