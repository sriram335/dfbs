package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

public class EmsProvRenewalDAO extends HsDAO
{
  public EmsProvRenewalDAO()
  {
  }
  


 public List selectRenewalList(int providerId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_RENEWAL_LIST);
      pstmt.clearParameters();
      if(providerId != 0) {
        pstmt.setInt(1,providerId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsProviderRenewal renewal = new EmsProviderRenewal();
          renewal.setIhernExpDate(rs.getDate(4));
          renewal.setOtherExpDate(rs.getDate(6));
          renewal.setTactExpDate(rs.getDate(3));
          renewal.setUhfExpDate(rs.getDate(5));
          renewal.setOrgStaffing(rs.getString(18));
          renewal.setAuditHospCommittee(rs.getString(31));
          renewal.setAuditMD(rs.getString(30));
          renewal.setAuditProvCommittee(rs.getString(32));
          renewal.setDispatchCentral(rs.getString(12));
          renewal.setDispatchOther(rs.getString(14));
          renewal.setDispatchOtherDetails(rs.getString(15));
          renewal.setDispatchProvider(rs.getString(13));
          renewal.setEms247(rs.getString(16));
          renewal.setEms247Details(rs.getString(17));
          renewal.setEmsDataRegistry(rs.getString(22));
          renewal.setOtherDetails(rs.getString(7));
          renewal.setRecordLocation(rs.getString(20));
          renewal.setServiceAreaChange(rs.getString(19));
          renewal.setSoftwareUsed(rs.getString(23));
          renewal.setTrainingDaily(rs.getString(24));
          renewal.setTrainingHeld(rs.getString(29));
          renewal.setTrainingMonthly(rs.getString(25));
          renewal.setTrainingOther(rs.getString(27));
          renewal.setTrainingOtherDetails(rs.getString(28));
          renewal.setTrainingQuarterly(rs.getString(26));
          renewal.setUhf400(rs.getString(9));
          renewal.setUhf800(rs.getString(10));
          renewal.setUhfCellular(rs.getString(8));
          renewal.setUhfOther(rs.getString(11));
          renewal.setWaiverList(rs.getString(21));
          renewal.setEopProviderId(rs.getInt(2));
          renewal.setRenewalId(rs.getInt(1));
          renewal.setVehicleMaintenance(rs.getString(47));
          renewal.setAuditRecordsWith(rs.getString(37));
          renewal.setAuditSessionMonthly(rs.getString(33));
          renewal.setAuditSessionOther(rs.getString(35));
          renewal.setAuditSessionOtherDetails(rs.getString(36));
          renewal.setAuditSessionQuarterly(rs.getString(34));
          renewal.setEquipMaintenance(rs.getString(42));
          renewal.setVehIntCheckDaily(rs.getString(43));
          renewal.setVehIntCheckMonthly(rs.getString(45));
          renewal.setVehIntCheckOther(rs.getString(49));
          renewal.setVehIntCheckOtherDetails(rs.getString(46));
          renewal.setVehIntCheckWeekly(rs.getString(44));
          renewal.setVehicleCheckDaily(rs.getString(38));
          renewal.setVehicleCheckMonthly(rs.getString(40));
          renewal.setVehicleCheckOther(rs.getString(48));
          renewal.setVehicleCheckOtherDetails(rs.getString(41));
          renewal.setVehicleCheckWeekly(rs.getString(39));
          renewal.setRenewalRequestDate(rs.getDate(50));
        list.add(renewal);
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
  public EmsProviderRenewal selectRenewal(int renewalId) throws SQLException, Exception 
  {
     
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    EmsProviderRenewal renewal = new EmsProviderRenewal(); 
    try 
    {
      conn = getConnection();
      renewal = selectRenewal(conn,renewalId);
      return renewal;
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
   public EmsProviderRenewal selectRenewal(Connection conn,int renewalId) throws SQLException, Exception 
  {
     EmsProviderRenewal renewal = new EmsProviderRenewal();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_RENEWAL_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,renewalId);
      rs = pstmt.executeQuery();
      
      if(rs.next()) 
      {
           renewal.setIhernExpDate(rs.getDate(4));
          renewal.setOtherExpDate(rs.getDate(6));
          renewal.setTactExpDate(rs.getDate(3));
          renewal.setUhfExpDate(rs.getDate(5));
          renewal.setOrgStaffing(rs.getString(18));
          renewal.setAuditHospCommittee(rs.getString(31));
          renewal.setAuditMD(rs.getString(30));
          renewal.setAuditProvCommittee(rs.getString(32));
          renewal.setDispatchCentral(rs.getString(12));
          renewal.setDispatchOther(rs.getString(14));
          renewal.setDispatchOtherDetails(rs.getString(15));
          renewal.setDispatchProvider(rs.getString(13));
          renewal.setEms247(rs.getString(16));
          renewal.setEms247Details(rs.getString(17));
          renewal.setEmsDataRegistry(rs.getString(22));
          renewal.setOtherDetails(rs.getString(7));
          renewal.setRecordLocation(rs.getString(20));
          renewal.setServiceAreaChange(rs.getString(19));
          renewal.setSoftwareUsed(rs.getString(23));
          renewal.setTrainingDaily(rs.getString(24));
          renewal.setTrainingHeld(rs.getString(29));
          renewal.setTrainingMonthly(rs.getString(25));
          renewal.setTrainingOther(rs.getString(27));
          renewal.setTrainingOtherDetails(rs.getString(28));
          renewal.setTrainingQuarterly(rs.getString(26));
          renewal.setUhf400(rs.getString(9));
          renewal.setUhf800(rs.getString(10));
          renewal.setUhfCellular(rs.getString(8));
          renewal.setUhfOther(rs.getString(11));
          renewal.setWaiverList(rs.getString(21));
          renewal.setEopProviderId(rs.getInt(2));
          renewal.setRenewalId(rs.getInt(1));
          renewal.setVehicleMaintenance(rs.getString(47));
          renewal.setAuditRecordsWith(rs.getString(37));
          renewal.setAuditSessionMonthly(rs.getString(33));
          renewal.setAuditSessionOther(rs.getString(35));
          renewal.setAuditSessionOtherDetails(rs.getString(36));
          renewal.setAuditSessionQuarterly(rs.getString(34));
          renewal.setEquipMaintenance(rs.getString(42));
          renewal.setVehIntCheckDaily(rs.getString(43));
          renewal.setVehIntCheckMonthly(rs.getString(45));
          renewal.setVehIntCheckOther(rs.getString(49));
          renewal.setVehIntCheckOtherDetails(rs.getString(46));
          renewal.setVehIntCheckWeekly(rs.getString(44));
          renewal.setVehicleCheckDaily(rs.getString(38));
          renewal.setVehicleCheckMonthly(rs.getString(40));
          renewal.setVehicleCheckOther(rs.getString(48));
          renewal.setVehicleCheckOtherDetails(rs.getString(41));
          renewal.setVehicleCheckWeekly(rs.getString(39));
          renewal.setRenewalRequestDate(rs.getDate(50));
      }
      return renewal;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateRenewal(EmsProviderRenewal renewal) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_RENEWAL);
          pstmt.clearParameters();
          pstmt.setString(4,renewal.getIhernExpDateString());
          pstmt.setString(6,renewal.getOtherExpDateString());
          pstmt.setString(3,renewal.getTactExpDateString());
          pstmt.setString(5,renewal.getUhfExpDateString());
          pstmt.setString(18,renewal.getOrgStaffing());
          pstmt.setString(31,renewal.getAuditHospCommittee());
          pstmt.setString(30,renewal.getAuditMD());
          pstmt.setString(32,renewal.getAuditProvCommittee());
          pstmt.setString(12,renewal.getDispatchCentral());
          pstmt.setString(14,renewal.getDispatchOther());
          pstmt.setString(15,renewal.getDispatchOtherDetails());
          pstmt.setString(13,renewal.getDispatchProvider());
          pstmt.setString(16,renewal.getEms247());
          pstmt.setString(17,renewal.getEms247Details());
          pstmt.setString(22,renewal.getEmsDataRegistry());
          pstmt.setString(7,renewal.getOtherDetails());
          pstmt.setString(20,renewal.getRecordLocation());
          pstmt.setString(19,renewal.getServiceAreaChange());
          pstmt.setString(23,renewal.getSoftwareUsed());
          pstmt.setString(24,renewal.getTrainingDaily());
          pstmt.setString(29,renewal.getTrainingHeld());
          pstmt.setString(25,renewal.getTrainingMonthly());
          pstmt.setString(27,renewal.getTrainingOther());
          pstmt.setString(28,renewal.getTrainingOtherDetails());
          pstmt.setString(26,renewal.getTrainingQuarterly());
          pstmt.setString(9,renewal.getUhf400());
          pstmt.setString(10,renewal.getUhf800());
          pstmt.setString(8,renewal.getUhfCellular());
          pstmt.setString(11,renewal.getUhfOther());
          pstmt.setString(21,renewal.getWaiverList());
          pstmt.setInt(2,renewal.getEopProviderId());
          pstmt.setInt(49,renewal.getRenewalId());
          pstmt.setString(47,renewal.getVehicleMaintenance());
          pstmt.setString(37,renewal.getAuditRecordsWith());
          pstmt.setString(33,renewal.getAuditSessionMonthly());
          pstmt.setString(35,renewal.getAuditSessionOther());
          pstmt.setString(36,renewal.getAuditSessionOtherDetails());
          pstmt.setString(34,renewal.getAuditSessionQuarterly());
          pstmt.setString(42,renewal.getEquipMaintenance());
          pstmt.setString(43,renewal.getVehIntCheckDaily());
          pstmt.setString(45,renewal.getVehIntCheckMonthly());
          pstmt.setString(1,renewal.getVehIntCheckOther());
          pstmt.setString(46,renewal.getVehIntCheckOtherDetails());
          pstmt.setString(44,renewal.getVehIntCheckWeekly());
          pstmt.setString(38,renewal.getVehicleCheckDaily());
          pstmt.setString(40,renewal.getVehicleCheckMonthly());
          pstmt.setString(48,renewal.getVehicleCheckOther());
          pstmt.setString(41,renewal.getVehicleCheckOtherDetails());
          pstmt.setString(39,renewal.getVehicleCheckWeekly());
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
  public void insertRenewal(EmsProviderRenewal renewal, EmsProvider provider) throws SQLException, Exception 
  {
  
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.INSERT_RENEWAL);
          pstmt.clearParameters();
          pstmt.setString(4,renewal.getIhernExpDateString());
          pstmt.setString(6,renewal.getOtherExpDateString());
          pstmt.setString(3,renewal.getTactExpDateString());
          pstmt.setString(5,renewal.getUhfExpDateString());
          pstmt.setString(18,renewal.getOrgStaffing());
          pstmt.setString(31,renewal.getAuditHospCommittee());
          pstmt.setString(30,renewal.getAuditMD());
          pstmt.setString(32,renewal.getAuditProvCommittee());
          pstmt.setString(12,renewal.getDispatchCentral());
          pstmt.setString(14,renewal.getDispatchOther());
          pstmt.setString(15,renewal.getDispatchOtherDetails());
          pstmt.setString(13,renewal.getDispatchProvider());
          pstmt.setString(16,renewal.getEms247());
          pstmt.setString(17,renewal.getEms247Details());
          pstmt.setString(22,renewal.getEmsDataRegistry());
          pstmt.setString(7,renewal.getOtherDetails());
          pstmt.setString(20,renewal.getRecordLocation());
          pstmt.setString(19,renewal.getServiceAreaChange());
          pstmt.setString(23,renewal.getSoftwareUsed());
          pstmt.setString(24,renewal.getTrainingDaily());
          pstmt.setString(29,renewal.getTrainingHeld());
          pstmt.setString(25,renewal.getTrainingMonthly());
          pstmt.setString(27,renewal.getTrainingOther());
          pstmt.setString(28,renewal.getTrainingOtherDetails());
          pstmt.setString(26,renewal.getTrainingQuarterly());
          pstmt.setString(9,renewal.getUhf400());
          pstmt.setString(10,renewal.getUhf800());
          pstmt.setString(8,renewal.getUhfCellular());
          pstmt.setString(11,renewal.getUhfOther());
          pstmt.setString(21,renewal.getWaiverList());
          pstmt.setInt(2,provider.getProviderId());
          int idRenewal = this.getId(conn,EmsSQL.SELECT_NEXT_RENEWAL_ID);
          pstmt.setInt(1,idRenewal);
          renewal.setRenewalId(idRenewal);
          pstmt.setString(47,renewal.getVehicleMaintenance());
          pstmt.setString(37,renewal.getAuditRecordsWith());
          pstmt.setString(33,renewal.getAuditSessionMonthly());
          pstmt.setString(35,renewal.getAuditSessionOther());
          pstmt.setString(36,renewal.getAuditSessionOtherDetails());
          pstmt.setString(34,renewal.getAuditSessionQuarterly());
          pstmt.setString(42,renewal.getEquipMaintenance());
          pstmt.setString(43,renewal.getVehIntCheckDaily());
          pstmt.setString(45,renewal.getVehIntCheckMonthly());
          pstmt.setString(49,renewal.getVehIntCheckOther());
          pstmt.setString(46,renewal.getVehIntCheckOtherDetails());
          pstmt.setString(44,renewal.getVehIntCheckWeekly());
          pstmt.setString(38,renewal.getVehicleCheckDaily());
          pstmt.setString(40,renewal.getVehicleCheckMonthly());
          pstmt.setString(48,renewal.getVehicleCheckOther());
          pstmt.setString(41,renewal.getVehicleCheckOtherDetails());
          pstmt.setString(39,renewal.getVehicleCheckWeekly());
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