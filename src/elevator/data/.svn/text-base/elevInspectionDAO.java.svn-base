package elevator.data;
import elevator.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import elevator.to.*;
public class elevInspectionDAO extends HsDAO{
    public elevInspectionDAO() {
       
    }
  public static List selectElevInspectionList(Connection conn,String stateNumber) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_INSPECTION_LIST);
       pstmt.clearParameters();
       pstmt.setString(1,stateNumber);
       rs = pstmt.executeQuery();
       while(rs.next()) 
       {  
         elevInspection elevInsp = new elevInspection();
         elevInsp.setRunToBottom(rs.getString(15));
         elevInsp.setBreakTest(rs.getString(25));
         elevInsp.setEmptyBottom(rs.getString(19));
         elevInsp.setEmptyUp(rs.getString(18));
         elevInsp.setFireFightService(rs.getDate(32));
         elevInsp.setFlexHoseDate(rs.getDate(26));
         elevInsp.setFullUp(rs.getString(20));
         elevInsp.setFulldown(rs.getString(21));
         elevInsp.setGovTripSpeed(rs.getString(22));
         elevInsp.setInspectionDate(rs.getDate(2));
         elevInsp.setInspectionStatus(rs.getString(30));
         elevInsp.setInspectionType(rs.getString(31));
         elevInsp.setLast5YearTest(rs.getDate(7));
         elevInsp.setLastAnnualTest(rs.getDate(10));
         elevInsp.setNearStrkingPoint(rs.getString(29));
         elevInsp.setNext5YearTest(rs.getDate(9));
         elevInsp.setNextAnnualTest(rs.getDate(11));
         elevInsp.setOverSpeedSwitch(rs.getString(23));
         elevInsp.setRefugeBottom(rs.getString(17));
         elevInsp.setRefugeTop(rs.getString(16));
         elevInsp.setReliefPressure(rs.getString(28));
         elevInsp.setRunToTop(rs.getString(14));
         elevInsp.setSealNumber(rs.getString(13));
         elevInsp.setSlide(rs.getString(24));
         elevInsp.setSprinklers(rs.getString(6));
         elevInsp.setWorkingPressure(rs.getString(27));
         elevInsp.setInspectionId(rs.getInt(1));
         elevInsp.setAscendingCar(rs.getString(45));
         elevInsp.setBufferStroke(rs.getString(51));
         elevInsp.setCarMotion(rs.getString(44));
         elevInsp.setComments(rs.getString(40));
         elevInsp.setCylinderCheck(rs.getString(60));
         elevInsp.setDistrict(rs.getString(42));
         elevInsp.setHositway(rs.getString(58));
         elevInsp.setInspCategory(rs.getString(49));
         elevInsp.setInspTime(rs.getString(43));
         elevInsp.setMachineRoom(rs.getString(57));
         elevInsp.setMaterialRails(rs.getString(52));
         elevInsp.setPit(rs.getString(56));
         elevInsp.setReceiverReport(rs.getString(35));
         elevInsp.setRemarks(rs.getString(34));
         elevInsp.setSafetyDeviceType(rs.getString(54));
         elevInsp.setShutoffVolve(rs.getString(59));
         elevInsp.setStandbyPower(rs.getString(55));
         elevInsp.setStateNumber(rs.getString(38));
         elevInsp.setSubmittor(rs.getString(36));
         elevInsp.setTelephone(rs.getString(37));
         elevInsp.setTypeBuffer(rs.getString(50));
         elevInsp.setTypeGovernor(rs.getString(52));
         elevInsp.setContractorId(rs.getInt(46));
         elevInsp.setInspectorId(rs.getInt(39));
         elevInsp.setSerMechanicId(rs.getInt(48));
         elevInsp.setSevContractorId(rs.getInt(47));
         list.add(elevInsp);
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
   public elevInspection selectElevInspection(int inspectionId) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     
     try 
     {
    
       conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_INSPECTION);
       pstmt.clearParameters();
       pstmt.setInt(1,inspectionId);
       rs = pstmt.executeQuery();
       elevInspection elevInsp = new elevInspection();
       while(rs.next()) 
       {
         elevInsp.setRunToBottom(rs.getString(15));
         elevInsp.setBreakTest(rs.getString(25));
         elevInsp.setEmptyBottom(rs.getString(19));
         elevInsp.setEmptyUp(rs.getString(18));
         elevInsp.setFireFightService(rs.getDate(32));
         elevInsp.setFlexHoseDate(rs.getDate(26));
         elevInsp.setFullUp(rs.getString(20));
         elevInsp.setFulldown(rs.getString(21));
         elevInsp.setGovTripSpeed(rs.getString(22));
         elevInsp.setInspectionDate(rs.getDate(2));
         elevInsp.setInspectionStatus(rs.getString(30));
         elevInsp.setInspectionType(rs.getString(31));
         elevInsp.setLast5YearTest(rs.getDate(7));
         elevInsp.setLastAnnualTest(rs.getDate(10));
         elevInsp.setNearStrkingPoint(rs.getString(29));
         elevInsp.setNext5YearTest(rs.getDate(9));
         elevInsp.setNextAnnualTest(rs.getDate(11));
         elevInsp.setOverSpeedSwitch(rs.getString(23));
         elevInsp.setRefugeBottom(rs.getString(17));
         elevInsp.setRefugeTop(rs.getString(16));
         elevInsp.setReliefPressure(rs.getString(28));
         elevInsp.setRunToTop(rs.getString(14));
         elevInsp.setSealNumber(rs.getString(13));
         elevInsp.setSlide(rs.getString(24));
         elevInsp.setSprinklers(rs.getString(6));
         elevInsp.setWorkingPressure(rs.getString(27));
         elevInsp.setInspectionId(rs.getInt(1));
         elevInsp.setAscendingCar(rs.getString(45));
         elevInsp.setBufferStroke(rs.getString(51));
         elevInsp.setCarMotion(rs.getString(44));
         elevInsp.setComments(rs.getString(40));
         elevInsp.setCylinderCheck(rs.getString(60));
         elevInsp.setDistrict(rs.getString(42));
         elevInsp.setHositway(rs.getString(58));
         elevInsp.setInspCategory(rs.getString(49));
         elevInsp.setInspTime(rs.getString(43));
         elevInsp.setMachineRoom(rs.getString(57));
         elevInsp.setMaterialRails(rs.getString(52));
         elevInsp.setPit(rs.getString(56));
         elevInsp.setReceiverReport(rs.getString(35));
         elevInsp.setRemarks(rs.getString(34));
         elevInsp.setSafetyDeviceType(rs.getString(54));
         elevInsp.setShutoffVolve(rs.getString(59));
         elevInsp.setStandbyPower(rs.getString(55));
         elevInsp.setStateNumber(rs.getString(38));
         elevInsp.setSubmittor(rs.getString(36));
         elevInsp.setTelephone(rs.getString(37));
         elevInsp.setTypeBuffer(rs.getString(50));
         elevInsp.setTypeGovernor(rs.getString(52));
         elevInsp.setContractorId(rs.getInt(46));
         elevInsp.setInspectorId(rs.getInt(39));
         elevInsp.setSerMechanicId(rs.getInt(48));
         elevInsp.setSevContractorId(rs.getInt(47));
        
           
       }
       return elevInsp;
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
   public void insertElevInspection(elevInspection elevInsp) throws SQLException, Exception 
   {
   
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          int id = this.getId(conn,"select EINSPECTION_id.nextval from dual");
              pstmt = conn.prepareStatement(elevatorSQL.INSERT_ELEVATOR_INSPECTION);
             elevInsp.setInspectionId(id);
          pstmt.clearParameters();
      pstmt.setInt(33,0);
      pstmt.setString(41,null);
      pstmt.setString(53,elevInsp.getTypeGovernor());  
      pstmt.setInt(3,0);  
      pstmt.setString(4,null);
      pstmt.setString(5,null);    
      pstmt.setString(8,null);      
      pstmt.setString(12,elevInsp.getNextAnnualTestString());
      pstmt.setString(15,elevInsp.getRunToBottom());
      pstmt.setString(25,elevInsp.getBreakTest());
      pstmt.setString(19,elevInsp.getEmptyBottom());
      pstmt.setString(18,elevInsp.getEmptyUp());
      pstmt.setString(32,elevInsp.getFireFightServiceString());
      pstmt.setString(26,elevInsp.getFlexHoseDateString());
      pstmt.setString(20,elevInsp.getFullUp());
      pstmt.setString(21,elevInsp.getFulldown());
      pstmt.setString(22,elevInsp.getGovTripSpeed());
      pstmt.setString(2,elevInsp.getInspectionDateString());
      pstmt.setString(30,elevInsp.getInspectionStatus());
      pstmt.setString(31,elevInsp.getInspectionType());
      pstmt.setString(7,elevInsp.getLast5YearTestString());
      pstmt.setString(10,elevInsp.getLastAnnualTestString());
      pstmt.setString(29,elevInsp.getNearStrkingPoint());
      pstmt.setString(9,elevInsp.getNext5YearTestString());
      pstmt.setString(11,elevInsp.getNextAnnualTestString());
      pstmt.setString(23,elevInsp.getOverSpeedSwitch());
      pstmt.setString(17,elevInsp.getRefugeBottom());
      pstmt.setString(16,elevInsp.getRefugeTop());
      pstmt.setString(28,elevInsp.getReliefPressure());
      pstmt.setString(14,elevInsp.getRunToTop());
      pstmt.setString(13,elevInsp.getSealNumber());
      pstmt.setString(24,elevInsp.getSlide());
      pstmt.setString(6,elevInsp.getSprinklers());
      pstmt.setString(27,elevInsp.getWorkingPressure());
      pstmt.setInt(1,elevInsp.getInspectionId());
      pstmt.setString(45,elevInsp.getAscendingCar());
      pstmt.setString(51,elevInsp.getBufferStroke());
      pstmt.setString(44,elevInsp.getCarMotion());
      pstmt.setString(40,elevInsp.getComments());
      pstmt.setString(60,elevInsp.getCylinderCheck());
      pstmt.setString(42,elevInsp.getDistrict());
      pstmt.setString(58,elevInsp.getHositway());
      pstmt.setString(49,elevInsp.getInspCategory());
      pstmt.setString(43,elevInsp.getInspTime());
      pstmt.setString(57,elevInsp.getMachineRoom());
      pstmt.setString(52,elevInsp.getMaterialRails());
      pstmt.setString(56,elevInsp.getPit());
      pstmt.setString(35,elevInsp.getReceiverReport());
      pstmt.setString(34,elevInsp.getRemarks());
      pstmt.setString(54,elevInsp.getSafetyDeviceType());
      pstmt.setString(59,elevInsp.getShutoffVolve());
      pstmt.setString(55,elevInsp.getStandbyPower());
      pstmt.setString(38,elevInsp.getStateNumber());
      pstmt.setString(36,elevInsp.getSubmittor());
      pstmt.setString(37,elevInsp.getTelephone());
      pstmt.setString(50,elevInsp.getTypeBuffer());
      pstmt.setInt(46,elevInsp.getContractorId());
      pstmt.setInt(39,elevInsp.getInspectorId());
      pstmt.setInt(48,elevInsp.getSerMechanicId());
      pstmt.setInt(47,elevInsp.getSevContractorId());
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
  public void updateElevInspection(elevInspection elevInsp) throws SQLException, Exception
  {
  
        Connection conn = null;
        PreparedStatement pstmt = null;
     try 
  {
        conn = getConnection();
        conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(elevatorSQL.UPDATE_ELEVATOR_INSPECTION);
         pstmt.clearParameters();
    pstmt.setInt(33,0);
    pstmt.setString(41,null);
    pstmt.setString(53,elevInsp.getTypeGovernor());  
    pstmt.setInt(3,0);  
    pstmt.setString(4,null);
    pstmt.setString(5,null);    
    pstmt.setString(8,null);      
    pstmt.setString(12,elevInsp.getNextAnnualTestString());
    pstmt.setString(15,elevInsp.getRunToBottom());
    pstmt.setString(25,elevInsp.getBreakTest());
    pstmt.setString(19,elevInsp.getEmptyBottom());
    pstmt.setString(18,elevInsp.getEmptyUp());
    pstmt.setString(32,elevInsp.getFireFightServiceString());
    pstmt.setString(26,elevInsp.getFlexHoseDateString());
    pstmt.setString(20,elevInsp.getFullUp());
    pstmt.setString(21,elevInsp.getFulldown());
    pstmt.setString(22,elevInsp.getGovTripSpeed());
    pstmt.setString(2,elevInsp.getInspectionDateString());
    pstmt.setString(30,elevInsp.getInspectionStatus());
    pstmt.setString(31,elevInsp.getInspectionType());
    pstmt.setString(7,elevInsp.getLast5YearTestString());
    pstmt.setString(10,elevInsp.getLastAnnualTestString());
    pstmt.setString(29,elevInsp.getNearStrkingPoint());
    pstmt.setString(9,elevInsp.getNext5YearTestString());
    pstmt.setString(11,elevInsp.getNextAnnualTestString());
    pstmt.setString(23,elevInsp.getOverSpeedSwitch());
    pstmt.setString(17,elevInsp.getRefugeBottom());
    pstmt.setString(16,elevInsp.getRefugeTop());
    pstmt.setString(28,elevInsp.getReliefPressure());
    pstmt.setString(14,elevInsp.getRunToTop());
    pstmt.setString(13,elevInsp.getSealNumber());
    pstmt.setString(24,elevInsp.getSlide());
    pstmt.setString(6,elevInsp.getSprinklers());
    pstmt.setString(27,elevInsp.getWorkingPressure());
    pstmt.setInt(60,elevInsp.getInspectionId());
    pstmt.setString(45,elevInsp.getAscendingCar());
    pstmt.setString(51,elevInsp.getBufferStroke());
    pstmt.setString(44,elevInsp.getCarMotion());
    pstmt.setString(40,elevInsp.getComments());
    pstmt.setString(1,elevInsp.getCylinderCheck());
    pstmt.setString(42,elevInsp.getDistrict());
    pstmt.setString(58,elevInsp.getHositway());
    pstmt.setString(49,elevInsp.getInspCategory());
    pstmt.setString(43,elevInsp.getInspTime());
    pstmt.setString(57,elevInsp.getMachineRoom());
    pstmt.setString(52,elevInsp.getMaterialRails());
    pstmt.setString(56,elevInsp.getPit());
    pstmt.setString(35,elevInsp.getReceiverReport());
    pstmt.setString(34,elevInsp.getRemarks());
    pstmt.setString(54,elevInsp.getSafetyDeviceType());
    pstmt.setString(59,elevInsp.getShutoffVolve());
    pstmt.setString(55,elevInsp.getStandbyPower());
    pstmt.setString(38,elevInsp.getStateNumber());
    pstmt.setString(36,elevInsp.getSubmittor());
    pstmt.setString(37,elevInsp.getTelephone());
    pstmt.setString(50,elevInsp.getTypeBuffer());
    pstmt.setInt(46,elevInsp.getContractorId());
    pstmt.setInt(39,elevInsp.getInspectorId());
    pstmt.setInt(48,elevInsp.getSerMechanicId());
    pstmt.setInt(47,elevInsp.getSevContractorId());
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
