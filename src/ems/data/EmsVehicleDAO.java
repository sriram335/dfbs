package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
public class EmsVehicleDAO  extends HsDAO
{
  public EmsVehicleDAO()
  {
  }
    public List selectVehicleList(String sql,int param) throws SQLException, Exception 
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
      
        EmsVehicle vehicle = new EmsVehicle();
         vehicle.setCertIssueDate(rs.getDate(6));
          vehicle.setCertReqDate(rs.getDate(7));
          vehicle.setCorrLetterDate(rs.getDate(14));
          vehicle.setExpDate(rs.getDate(4));
          vehicle.setFileUpdateDate(rs.getDate(5));
          vehicle.setInspectionDate(rs.getDate(3));
          vehicle.setTempExpDate(rs.getDate(19));
          vehicle.setProviderId(rs.getInt(20));
          vehicle.setVehicleId(rs.getInt(1));
          vehicle.setCertNumber(rs.getString(2));
          vehicle.setColorScheme(rs.getString(13));
          vehicle.setComments(rs.getString(16));
          vehicle.setConversionBy(rs.getString(22));
          vehicle.setDistrict(rs.getString(21));
          vehicle.setMileage(rs.getString(15));
          vehicle.setModelMake(rs.getString(9));
          vehicle.setModelType(rs.getString(12));
          vehicle.setModelVin(rs.getString(10));
          vehicle.setModelYear(rs.getString(8));
          vehicle.setPlate(rs.getString(24));
          vehicle.setTempMake(rs.getString(17));
          vehicle.setTempModelType(rs.getString(23));
          vehicle.setTempVin(rs.getString(18));
          vehicle.setTempYear(rs.getString(25));
          vehicle.setVehicleLocation(rs.getString(26));
          vehicle.setFuelType(rs.getString(27));
          vehicle.setPrevCertNumber(rs.getString(28));
          vehicle.setVehOldNew(rs.getString(29));
        list.add(vehicle);
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
  public EmsVehicle selectVehicle(int vehicleId) throws SQLException, Exception 
  {
     EmsVehicle vehicle = new EmsVehicle();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      vehicle = selectVehicle(conn,vehicleId);
      return vehicle;
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
   public EmsVehicle selectVehicle(Connection conn,int vehicleId) throws SQLException, Exception 
  {
    EmsVehicle vehicle = new EmsVehicle();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_VEHICLE);
      pstmt.clearParameters();
      pstmt.setInt(1,vehicleId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
           vehicle.setCertIssueDate(rs.getDate(6));
          vehicle.setCertReqDate(rs.getDate(7));
          vehicle.setCorrLetterDate(rs.getDate(14));
          vehicle.setExpDate(rs.getDate(4));
          vehicle.setFileUpdateDate(rs.getDate(5));
          vehicle.setInspectionDate(rs.getDate(3));
          vehicle.setTempExpDate(rs.getDate(19));
          vehicle.setProviderId(rs.getInt(20));
          vehicle.setVehicleId(rs.getInt(1));
          vehicle.setCertNumber(rs.getString(2));
          vehicle.setColorScheme(rs.getString(13));
          vehicle.setComments(rs.getString(16));
          vehicle.setConversionBy(rs.getString(22));
          vehicle.setDistrict(rs.getString(21));
          vehicle.setMileage(rs.getString(15));
          vehicle.setModelMake(rs.getString(9));
          vehicle.setModelType(rs.getString(12));
          vehicle.setModelVin(rs.getString(10));
          vehicle.setModelYear(rs.getString(8));
          vehicle.setPlate(rs.getString(24));
          vehicle.setTempMake(rs.getString(17));
          vehicle.setTempModelType(rs.getString(23));
          vehicle.setTempVin(rs.getString(18));
          vehicle.setTempYear(rs.getString(25));
          vehicle.setVehicleLocation(rs.getString(26));
           vehicle.setFuelType(rs.getString(27));
          vehicle.setPrevCertNumber(rs.getString(28));
          vehicle.setVehOldNew(rs.getString(29));
      }
      return vehicle;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateVehicle(EmsVehicle vehicle) throws SQLException, Exception 
  {
 
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_VEHICLE);
          pstmt.clearParameters();
          pstmt.setString(5,vehicle.getCertIssueDateString());
          pstmt.setString(6,vehicle.getCertReqDateString());
          pstmt.setString(13,vehicle.getCorrLetterDateString());
          pstmt.setString(3,vehicle.getExpDateString());
          pstmt.setString(4,vehicle.getFileUpdateDateString());
          pstmt.setString(2,vehicle.getInspectionDateString());
          pstmt.setString(20,vehicle.getTempExpDateString());
          pstmt.setInt(28,vehicle.getVehicleId());
          pstmt.setString(1,vehicle.getCertNumber());
          pstmt.setString(11,vehicle.getVehicleType());
          pstmt.setString(12,vehicle.getColorScheme());
          pstmt.setString(16,vehicle.getComments());
          pstmt.setString(22,vehicle.getConversionBy());
          pstmt.setString(21,vehicle.getDistrict());
          pstmt.setString(14,vehicle.getMileage());
          pstmt.setString(8,vehicle.getModelMake());
          pstmt.setString(10,vehicle.getModelType());
          pstmt.setString(9,vehicle.getModelVin());
          pstmt.setString(7,vehicle.getModelYear());
           pstmt.setString(15,vehicle.getPlate());
          pstmt.setString(18,vehicle.getTempMake());
          pstmt.setString(23,vehicle.getTempModelType());
          pstmt.setString(19,vehicle.getTempVin());
          pstmt.setString(17,vehicle.getTempYear());
          pstmt.setString(24,vehicle.getVehicleLocation());
          pstmt.setString(25,vehicle.getFuelType());
          pstmt.setString(26,vehicle.getPrevCertNumber());
          pstmt.setString(27,vehicle.getVehOldNew());
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
   public void insertVehicle(EmsVehicle vehicle,int providerId) throws SQLException, Exception 
  {
 
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.INSERT_VEHICLE);
          pstmt.clearParameters();
          pstmt.setString(5,vehicle.getCertIssueDateString());
          pstmt.setString(6,vehicle.getCertReqDateString());
          pstmt.setString(13,vehicle.getCorrLetterDateString());
          pstmt.setString(3,vehicle.getExpDateString());
          pstmt.setString(4,vehicle.getFileUpdateDateString());
          pstmt.setString(2,vehicle.getInspectionDateString());
          pstmt.setString(20,vehicle.getTempExpDateString());
          pstmt.setString(1,vehicle.getCertNumber());
          pstmt.setString(11,vehicle.getVehicleType());
          pstmt.setString(12,vehicle.getColorScheme());
          pstmt.setString(16,vehicle.getComments());
          pstmt.setString(23,vehicle.getConversionBy());
          pstmt.setString(22,vehicle.getDistrict());
          pstmt.setString(14,vehicle.getMileage());
          pstmt.setString(8,vehicle.getModelMake());
          pstmt.setString(10,vehicle.getModelType());
          pstmt.setString(9,vehicle.getModelVin());
          pstmt.setString(7,vehicle.getModelYear());
          pstmt.setString(15,vehicle.getPlate());
          pstmt.setString(18,vehicle.getTempMake());
          pstmt.setString(24,vehicle.getTempModelType());
          pstmt.setString(19,vehicle.getTempVin());
          pstmt.setString(17,vehicle.getTempYear());
          pstmt.setInt(21,providerId);
          pstmt.setString(25,vehicle.getVehicleLocation());
          pstmt.setString(26,vehicle.getFuelType());
          pstmt.setString(27,vehicle.getPrevCertNumber());
          pstmt.setString(28,vehicle.getVehOldNew());
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
   public void updateVehicleLocation(int vehicleId, String vehLocation) throws SQLException, Exception 
  {
 
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_VEHICLE_LOCATION);
          pstmt.clearParameters();
          pstmt.setString(1,vehLocation);
          pstmt.setInt(2,vehicleId);
         
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
   public void updateRenewVehicle(EmsVehicle vehicle) throws SQLException, Exception 
  {
 
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_VEHICLE_RENEWAL);
          pstmt.clearParameters();
          pstmt.setString(1,vehicle.getVehicleLocation());
          pstmt.setString(2,vehicle.getMileage());
          pstmt.setString(3,vehicle.getPlate());
          pstmt.setString(4,vehicle.getComments());
          pstmt.setInt(5,vehicle.getVehicleId());
         
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
   public void inactivateVehicle(String certNumber) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.INACTIVATE_VEHICLE);
          pstmt.clearParameters();
          
          pstmt.setString(1,certNumber);
                   
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