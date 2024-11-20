package amuseRide.data;
import hs.data.HsDAO;

   import java.util.*;
  import java.sql.*;

  import oracle.jdbc.*; 
  import hs.to.*;
  import hs.util.*;
  import amuseRide.to.*;
public class insuranceDAO extends HsDAO{
    public insuranceDAO() {
       
    }
  public insurance selectInsurance(int insuranceId) throws SQLException, Exception 
  {
  
    insurance ins = new insurance();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     conn = getConnection();
      pstmt = conn.prepareStatement(amuseRideSQL.SELECT_INSURANCE_BY_ID);
     pstmt.clearParameters();
     pstmt.setInt(1,insuranceId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
       ins.setEffDate(rs.getDate(5));
       ins.setExpDate(rs.getDate(6));
       ins.setSignedDate(rs.getDate(8));
       ins.setAddress1(rs.getString(11));
       ins.setAddress2(rs.getString(12));
       ins.setAuthRep(rs.getString(10));
       ins.setBranchOffice(rs.getString(9));
       ins.setCity(rs.getString(13));
       ins.setFax(rs.getString(18));
       ins.setInsName(rs.getString(2));
       ins.setInsOther(rs.getString(3));
       ins.setOwnerId(rs.getString(20));
       ins.setPhone(rs.getString(17));
       ins.setPolicyNumber(rs.getString(4));
       ins.setState(rs.getString(16));
       ins.setZip(rs.getString(14));
       ins.setZip2(rs.getString(15));
       ins.setAmountCovered(rs.getInt(7));
       ins.setInsuranceId(rs.getInt(1));
     }
   
  
     return ins;
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
  
  public int insertInsurance(insurance ins,int ownerId) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,amuseRideSQL.SELECT_NEXT_INS_ID);
     pstmt = conn.prepareStatement(amuseRideSQL.INSERT_INSURANCE);
     pstmt.clearParameters();
     pstmt.setString(5,ins.getEffDateString());
     pstmt.setString(6,ins.getExpDateString());
     pstmt.setString(8,ins.getSignedDateString());
     pstmt.setString(11,ins.getAddress1());
     pstmt.setString(12,ins.getAddress2());
     pstmt.setString(10,ins.getAuthRep());
     pstmt.setString(9,ins.getBranchOffice());
     pstmt.setString(13,ins.getCity());
     pstmt.setString(18,ins.getFax());
     pstmt.setString(2,ins.getInsName());
     pstmt.setString(3,ins.getInsOther());
     pstmt.setString(19,ins.getOwnerId());
     pstmt.setString(17,ins.getPhone());
     pstmt.setString(4,ins.getPolicyNumber());
     pstmt.setString(16,ins.getState());
     pstmt.setString(14,ins.getZip());
     pstmt.setString(15,ins.getZip2());
     pstmt.setInt(7,ins.getAmountCovered());
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
  public void updateInsurance(insurance ins) throws SQLException, Exception
  {
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
     pstmt = conn.prepareStatement(amuseRideSQL.UPDATE_INSURANCE );
     pstmt.clearParameters();
     pstmt.setString(5,ins.getEffDateString());
     pstmt.setString(6,ins.getExpDateString());
     pstmt.setString(8,ins.getSignedDateString());
     pstmt.setString(11,ins.getAddress1());
     pstmt.setString(12,ins.getAddress2());
     pstmt.setString(10,ins.getAuthRep());
     pstmt.setString(9,ins.getBranchOffice());
     pstmt.setString(13,ins.getCity());
     pstmt.setString(18,ins.getFax());
     pstmt.setString(2,ins.getInsName());
     pstmt.setString(3,ins.getInsOther());
     pstmt.setString(1,ins.getOwnerId());
     pstmt.setString(17,ins.getPhone());
     pstmt.setString(4,ins.getPolicyNumber());
     pstmt.setString(16,ins.getState());
     pstmt.setString(14,ins.getZip());
     pstmt.setString(15,ins.getZip2());
     pstmt.setInt(7,ins.getAmountCovered());
     pstmt.setInt(19,ins.getInsuranceId());
    
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
  public  List selectInsuranceList(int ownerId) throws SQLException, Exception 
  {
   List list = new ArrayList();
   ResultSet rs = null;
   PreparedStatement pstmt = null;
    Connection conn = null;
   try 
   {
     conn = getConnection();
     pstmt = conn.prepareStatement(amuseRideSQL.SELECT_INSURANCE_BY_OWNER);
     pstmt.clearParameters();
     if(ownerId != 0) {
       pstmt.setInt(1,ownerId);
     }
     rs = pstmt.executeQuery();
      while(rs.next()) 
     {
     
       insurance ins = new insurance();
       ins.setEffDate(rs.getDate(5));
       ins.setExpDate(rs.getDate(6));
       ins.setSignedDate(rs.getDate(8));
       ins.setAddress1(rs.getString(11));
       ins.setAddress2(rs.getString(12));
       ins.setAuthRep(rs.getString(10));
       ins.setBranchOffice(rs.getString(9));
       ins.setCity(rs.getString(13));
       ins.setFax(rs.getString(18));
       ins.setInsName(rs.getString(2));
       ins.setInsOther(rs.getString(3));
       ins.setOwnerId(rs.getString(20));
       ins.setPhone(rs.getString(17));
       ins.setPolicyNumber(rs.getString(4));
       ins.setState(rs.getString(16));
       ins.setZip(rs.getString(14));
       ins.setZip2(rs.getString(15));
       ins.setAmountCovered(rs.getInt(7));
       ins.setInsuranceId(rs.getInt(1));
         list.add(ins);
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
}
