package hazmat.data;
import hazmat.to.*;
import hazmat.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;


public class HazmatCarrierDAO  extends HsDAO
{
  public HazmatCarrierDAO()
  {
  }
  public int insertCarrier(HazmatCarrier carrier,int orgId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,HazmatPermitSQL.SELECT_NEXT_CAR_ID);
      carrier.setCarrierId(id);
      pstmt = conn.prepareStatement(HazmatPermitSQL.INSERT_CARRIER);
      pstmt.clearParameters();
      
      pstmt.setString(3,carrier.getCarrierContact());
      pstmt.setString(7,carrier.getCarrierEmail());
      pstmt.setString(6,carrier.getCarrierFax());
      pstmt.setString(2,carrier.getCarrierName());
      pstmt.setString(5,carrier.getCarrierPhone());
      pstmt.setInt(8,orgId);
      pstmt.setString(4,carrier.getCarrierTitle());
     
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
  
   public HazmatCarrier selectCarrier(String carrierId) throws SQLException, Exception 
  {
  
    HazmatCarrier carrier = new HazmatCarrier();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(HazmatPermitSQL.SELECT_CARRIER_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(1,carrierId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
         carrier.setCarrierName(rs.getString(1));
         carrier.setCarrierContact(rs.getString(2));
         carrier.setCarrierTitle(rs.getString(3));
         carrier.setCarrierPhone(rs.getString(4));
         carrier.setCarrierFax(rs.getString(5));
         carrier.setCarrierEmail(rs.getString(6));
     
    }
     return carrier;
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
