package amuseRide.data;



import hs.data.HsDAO;

   import java.util.*;
  import java.sql.*;

  import oracle.jdbc.*; 
  import hs.to.*;
  import hs.util.*;
  import amuseRide.to.*;
public class amuseRideDAO extends HsDAO{
    public amuseRideDAO() {
       
    }
 
  
  public amuseRide selectRide(String serialNumber) throws SQLException, Exception 
  {
  
    amuseRide ride = new amuseRide();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     conn = getConnection();
      pstmt = conn.prepareStatement(amuseRideSQL.SELECT_AMUSE_RIDE);
     pstmt.clearParameters();
     pstmt.setString(1,serialNumber);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
       ride.setAccDate(rs.getDate(24));
       ride.setAppDate(rs.getDate(16));
       ride.setExpDate(rs.getDate(15));
       ride.setInspDate(rs.getDate(20));
       ride.setNdtDate(rs.getDate(17));
       ride.setAddress1(rs.getString(6));
       ride.setAddress2(rs.getString(7));
       ride.setAssignInspector(rs.getString(19));
       ride.setCapacity(rs.getString(13));
       ride.setCity(rs.getString(8));
       ride.setDeviceName(rs.getString(2));
       ride.setLocation(rs.getString(21));
       ride.setManName(rs.getString(4));
       ride.setOtherDocs(rs.getString(18));
       ride.setPermitNumber(rs.getString(3));
       ride.setPhone(rs.getString(12));
       ride.setRideStatus(rs.getString(23));
       ride.setSerialNumber(rs.getString(1));
       ride.setSpeed(rs.getString(14));
       ride.setState(rs.getString(9));
       ride.setZip(rs.getString(10));
       ride.setZip2(rs.getString(11));
       ride.setDeviceId(rs.getInt(22));
       ride.setInsuranceId(rs.getInt(5));
       ride.setNumberAccidents(rs.getInt(25));
     }
   
  
     return ride;
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
  
  public int insertRide(amuseRide ride,int ownerId) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,amuseRideSQL.SELECT_AMUSE_OWNER_ID);
     pstmt = conn.prepareStatement(amuseRideSQL.INSERT_AMUSE_RIDE);
    pstmt.clearParameters();
     pstmt.setString(24,ride.getAccDateString());
     pstmt.setString(15,ride.getAppDateString());
     pstmt.setString(16,ride.getExpDateString());
     pstmt.setString(20,ride.getInspDateString());
     pstmt.setString(17,ride.getNdtDateString());
     pstmt.setString(6,ride.getAddress1());
     pstmt.setString(7,ride.getAddress2());
     pstmt.setString(19,ride.getAssignInspector());
     pstmt.setString(13,ride.getCapacity());
     pstmt.setString(8,ride.getCity());
     pstmt.setString(2,ride.getDeviceName());
     pstmt.setString(21,ride.getLocation());
     pstmt.setString(4,ride.getManName());
     pstmt.setString(18,ride.getOtherDocs());
     pstmt.setString(3,ride.getPermitNumber());
     pstmt.setString(12,ride.getPhone());
     pstmt.setString(23,"Active");
     pstmt.setString(1,ride.getSerialNumber());
     pstmt.setString(14,ride.getSpeed());
     pstmt.setString(9,ride.getState());
     pstmt.setString(10,ride.getZip());
     pstmt.setString(11,ride.getZip2());
     pstmt.setInt(22,ride.getDeviceId());
     pstmt.setInt(5,3);
     pstmt.setInt(25,ride.getNumberAccidents());
     
     pstmt.execute();
      this.insertAmuseOwnerRide(conn,ride.getSerialNumber(),ownerId,id);
     this.insertAmuseInspFee(conn,ride.getSerialNumber(),ride.getAppFee());
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
  public void updateRide(amuseRide ride) throws SQLException, Exception
  {
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
     pstmt = conn.prepareStatement(amuseRideSQL.UPDATE_AMUSE_RIDE );
     pstmt.clearParameters();
    // pstmt.setString(23,ride.getAccDateString());
   //  pstmt.setString(15,ride.getAppDateString());
     pstmt.setString(15,ride.getExpDateString());
     pstmt.setString(19,ride.getInspDateString());
     pstmt.setString(16,ride.getNdtDateString());
     pstmt.setString(6,ride.getAddress1());
     pstmt.setString(7,ride.getAddress2());
     pstmt.setString(18,ride.getAssignInspector());
     pstmt.setString(13,ride.getCapacity());
     pstmt.setString(8,ride.getCity());
     pstmt.setString(2,ride.getDeviceName());
     pstmt.setString(20,ride.getLocation());
     pstmt.setString(4,ride.getManName());
     pstmt.setString(17,ride.getOtherDocs());
     pstmt.setString(3,ride.getPermitNumber());
     pstmt.setString(12,ride.getPhone());
     pstmt.setString(22,ride.getRideStatus());
     pstmt.setString(23,ride.getSerialNumber());
     pstmt.setString(14,ride.getSpeed());
     pstmt.setString(9,ride.getState());
     pstmt.setString(10,ride.getZip());
     pstmt.setString(11,ride.getZip2());
     pstmt.setInt(21,ride.getDeviceId());
     pstmt.setInt(5,ride.getInsuranceId());
     pstmt.setInt(1,ride.getNumberAccidents());
    
     pstmt.execute();
    
     this.insertAmuseInspFee(conn,ride.getSerialNumber(),ride.getAppFee());

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
  public int insertAmuseOwnerRide(Connection conn,String serialNumber,int ownerId,int id) throws SQLException, Exception
  {
   PreparedStatement pstmt = null;
   
   try 
   {
      conn.setAutoCommit(false);
     pstmt = conn.prepareStatement(amuseRideSQL.INSERT_AMUSE_OWNER_RIDE);
     pstmt.clearParameters();
     pstmt.setInt(1,ownerId);
     pstmt.setString(2,serialNumber);
     pstmt.setInt(3,id);
    
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
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  public void insertAmuseInspFee(Connection conn,String serialNumber,int inspFee) throws SQLException, Exception
  {
   PreparedStatement pstmt = null;
   
   try 
   {
      conn.setAutoCommit(false);
     pstmt = conn.prepareStatement(amuseRideSQL.INSERT_AMUSE_RIDE_INSPECTION_FEE);
     pstmt.clearParameters();
     pstmt.setString(1,serialNumber);
     pstmt.setInt(2,inspFee);
    
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
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  public void insertPermitTransaction(amuseRide ride,int receiptId, DfbsOwner owner) throws SQLException, Exception 
   {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
       int id = this.getId(conn,amuseRideSQL.SELECT_NEXT_TRANSACTION_ID);
       pstmt = conn.prepareStatement(amuseRideSQL.INSERT_PERMIT_TRANSACTION);
       pstmt.clearParameters();
       pstmt.setInt(1,id);
       pstmt.setDouble(2,ride.getAppFee());
        pstmt.setString(3,ride.getDeviceName() +"-Permit fee Annual:"+ride.getSerialNumber());
       pstmt.setInt(4,receiptId);
       pstmt.setString(5,ride.getSerialNumber());
       pstmt.setString(6,owner.getOwnerDBA());
       pstmt.setString(7,owner.getOwnerFirstName());
       pstmt.setString(8,owner.getOwnerLastName());
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
