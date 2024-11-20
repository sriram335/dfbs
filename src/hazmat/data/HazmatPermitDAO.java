package hazmat.data;
import hazmat.to.*;
import hazmat.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

public class HazmatPermitDAO extends HsDAO
{
  public HazmatPermitDAO()
  {
  }
  public int insertPermit(HazmatPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
     try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,HazmatPermitSQL.SELECT_NEXT_ORG_ID);
      permit.setOrgId(id);
      pstmt = conn.prepareStatement(HazmatPermitSQL.INSERT_ORGANIZATION);
      pstmt.clearParameters();
      pstmt.setString(5,permit.getOrgAddress1());
      pstmt.setString(6,permit.getOrgAddress2());
      pstmt.setString(7,permit.getOrgCity());
      pstmt.setString(3,permit.getOrgContact());
      pstmt.setString(13,permit.getOrgEmail());
      pstmt.setString(14,permit.getOrgCounty());   
      pstmt.setString(12,permit.getOrgFax());
      pstmt.setString(2,permit.getOrgName());
      pstmt.setString(11,permit.getOrgPhone());
      pstmt.setString(8,permit.getOrgState());
      pstmt.setString(4,permit.getOrgTitle());
      pstmt.setString(9,permit.getOrgZip());
      pstmt.setString(10,permit.getOrgZip2());
      pstmt.setInt(1,id);
      pstmt.execute();
      
    
      
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
public List selectPermitToPrint(int receiptId,String orgEmail) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(HazmatPermitSQL.SELECT_PERMITS_TO_PRINT);
      pstmt.clearParameters();
      pstmt.setInt(1,receiptId);
      pstmt.setString(2,orgEmail);
       pstmt.setInt(3,receiptId);
      pstmt.setString(4,orgEmail);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
        PermitsToPrint permit = new PermitsToPrint();
        
         permit.setOrgName(rs.getString(1));
         permit.setOrgAddress1(rs.getString(2));
         permit.setOrgAddress2(rs.getString(3));
         permit.setOrgCity(rs.getString(4));
         permit.setOrgState(rs.getString(5));
         permit.setOrgZip(rs.getString(6));
         permit.setCarrierName(rs.getString(7));
         permit.setShipAmount(rs.getString(8));
         permit.setShipOrigin(rs.getString(9));
         permit.setShipDestination(rs.getString(10));
         permit.setShipRadLevel(rs.getString(11));
         permit.setShipPermitNumber(rs.getString(13));
         permit.setShipDate(rs.getDate(12));
         permit.setShipIssueDate(rs.getDate(14));
         permit.setShipExpDate(rs.getDate(15));
         permit.setShipmentType(rs.getString(16));
         permit.setNumberOfCasks(rs.getInt(17));
       
        list.add(permit);
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
  public List selectOrgList(String sql,String param) throws SQLException, Exception 
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
      
        PermitsToPrint permit = new PermitsToPrint();
        
         permit.setOrgName(rs.getString(1));
         permit.setOrgAddress1(rs.getString(2));
         permit.setOrgAddress2(rs.getString(3));
         permit.setOrgCity(rs.getString(4));
         permit.setOrgState(rs.getString(5));
         permit.setOrgZip(rs.getString(6));
         permit.setCarrierName(rs.getString(7));
         permit.setShipAmount(rs.getString(8));
         permit.setShipOrigin(rs.getString(9));
         permit.setShipDestination(rs.getString(10));
         permit.setShipRadLevel(rs.getString(11));
         permit.setShipPermitNumber(rs.getString(13));
         permit.setShipDate(rs.getDate(12));
         permit.setShipIssueDate(rs.getDate(14));
         permit.setShipExpDate(rs.getDate(15));
         permit.setOrgId(rs.getInt(16));
         permit.setCarrierId(rs.getInt(17));
         permit.setShipmentId(rs.getInt(18));
         permit.setFeeId(rs.getInt(19));
         permit.setOrgEmail(rs.getString(20));
        list.add(permit);
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
  
   public HazmatPermit selectOrg(String orgId) throws SQLException, Exception 
  {
  
    HazmatPermit org = new HazmatPermit();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(HazmatPermitSQL.SELECT_ORG_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(1,orgId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
         org.setOrgName(rs.getString(1));
         org.setOrgContact(rs.getString(2));
         org.setOrgTitle(rs.getString(3));
         org.setOrgAddress1(rs.getString(4));
         org.setOrgAddress2(rs.getString(5));
         org.setOrgCity(rs.getString(6));
         org.setOrgState(rs.getString(7));
         org.setOrgZip(rs.getString(8));
         org.setOrgZip2(rs.getString(9));
         org.setOrgPhone(rs.getString(10));
         org.setOrgFax(rs.getString(11));
         org.setOrgEmail(rs.getString(12));
     
    }
     return org;
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
