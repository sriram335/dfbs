package elevator.data;




import elevator.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import elevator.to.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class elevApplicationDAO extends HsDAO{
    public elevApplicationDAO() {
        
    }
  public List selectElevatorApplications(String stateNumber) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     try 
     {
       conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_APPLICATIONS);
       pstmt.clearParameters();
       pstmt.setString(1,stateNumber);
       rs = pstmt.executeQuery();
       while(rs.next()) 
       { 
        elevatorApplication elevApp = new elevatorApplication();
         elevApp.setApplicationDate(rs.getDate(4));
         elevApp.setApplicationType(rs.getString(2));
         elevApp.setAppliedBy(rs.getString(8));
         elevApp.setAppliedDesignation(rs.getString(9));
         elevApp.setAppliedFirm(rs.getString(11));
         elevApp.setCodeForAlterations(rs.getString(6));
         elevApp.setOwnerBy(rs.getString(3));
         elevApp.setOwnerDesignation(rs.getString(5));
         elevApp.setOwnerFirm(rs.getString(11));
         elevApp.setOwnerEmail(rs.getString(12)); 
         elevApp.setAppliedEmail(rs.getString(13));   
         elevApp.setStateNumber(rs.getString(7));
         elevApp.setApplicationId(rs.getInt(1));
         elevApp.setAppStatus(rs.getString(14)); 
         list.add(elevApp);
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
   public elevatorApplication selectElevatorApplication(int applicationId) throws SQLException, Exception 
   {
   
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
    
     try 
     {
    
       conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_APPLICATION);
       pstmt.clearParameters();
       pstmt.setInt(1,applicationId);
       rs = pstmt.executeQuery();
       elevatorApplication elevApp = new elevatorApplication();
       while(rs.next()) 
       {   elevApp.setApplicationDate(rs.getDate(4));
         elevApp.setApplicationType(rs.getString(2));
         elevApp.setAppliedBy(rs.getString(8));
         elevApp.setAppliedDesignation(rs.getString(9));
         elevApp.setAppliedFirm(rs.getString(10));
         elevApp.setCodeForAlterations(rs.getString(6));
         elevApp.setOwnerBy(rs.getString(3));
         elevApp.setOwnerDesignation(rs.getString(5));
         elevApp.setOwnerFirm(rs.getString(11));
         elevApp.setOwnerEmail(rs.getString(12)); 
         elevApp.setAppliedEmail(rs.getString(13));   
         elevApp.setStateNumber(rs.getString(7));
         elevApp.setApplicationId(rs.getInt(1));
         elevApp.setAppStatus(rs.getString(14)); 
       }
        return elevApp;
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
   public void insertElevatorApplication(elevatorApplication elevApp) throws SQLException, Exception 
   {
   
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(elevatorSQL.INSERT_ELEVATOR_APPLICATION);
          int id = this.getId(conn,elevatorSQL.SELECT_NEXT_APP_ID);
          elevApp.setApplicationId(id);   
          pstmt.clearParameters();
      
      pstmt.setString(1,elevApp.getApplicationType());
      pstmt.setString(6,elevApp.getAppliedBy());
      pstmt.setString(7,elevApp.getAppliedDesignation());
      pstmt.setString(8,elevApp.getAppliedFirm());
      pstmt.setString(4,elevApp.getCodeForAlterations());
       pstmt.setString(2,elevApp.getOwnerBy());
      pstmt.setString(3,elevApp.getOwnerDesignation());
      pstmt.setString(5,elevApp.getStateNumber());
      pstmt.setString(9,elevApp.getOwnerFirm());    
      pstmt.setString(10,elevApp.getOwnerEmail());
      pstmt.setString(11,elevApp.getAppliedEmail());
       pstmt.setInt(12,elevApp.getApplicationId());    
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
  public void updateElevatorApplication(elevatorApplication elevApp) throws SQLException, Exception
  {
  
        Connection conn = null;
        PreparedStatement pstmt = null;
     try 
  {
        conn = getConnection();
        conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(elevatorSQL.UPDATE_ELEVATOR_APPLICATION);
        pstmt.clearParameters();
    
    pstmt.setString(4,elevApp.getApplicationDateString());
    pstmt.setString(2,elevApp.getApplicationType());
    pstmt.setString(8,elevApp.getAppliedBy());
    pstmt.setString(9,elevApp.getAppliedDesignation());
    pstmt.setString(10,elevApp.getAppliedFirm());
    pstmt.setString(6,elevApp.getCodeForAlterations());
    pstmt.setString(1,elevApp.getOwnerFirm());     
     pstmt.setString(3,elevApp.getOwnerBy());
    pstmt.setString(5,elevApp.getOwnerDesignation());
    pstmt.setString(7,elevApp.getStateNumber());
    pstmt.setString(13,elevApp.getAppStatus());     
    pstmt.setInt(14,elevApp.getApplicationId());
    pstmt.setString(11,elevApp.getOwnerEmail());
    pstmt.setString(12,elevApp.getAppliedEmail()); 
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
    public void insertElevatorTransaction(elevatorApplication elevApp,ShoppingCart cart,DfbsOwner owner) throws SQLException, Exception 
     {
       Connection conn = null;
       PreparedStatement pstmt = null;
       
       try 
       {
         conn = getConnection();
         conn.setAutoCommit(false);
         
         int id = this.getId(conn,elevatorSQL.SELECT_NEXT_TRANSACTION_ID);
         int annualFee = this.getId(conn,elevatorSQL.SELECT_ANNUAL_FEE);
         pstmt = conn.prepareStatement(elevatorSQL.INSERT_PERMIT_TRANSACTION);
         pstmt.clearParameters();
         pstmt.setInt(1,id);
         pstmt.setDouble(2,elevApp.getAppFee()-annualFee);
         pstmt.setString(3,elevApp.getApplicationType() +"-Application fee:("+(elevApp.getAppFee()-annualFee)+")" );
         pstmt.setInt(4,cart.getReceiptId());
         pstmt.setString(5,elevApp.getStateNumber());
         pstmt.setString(6,owner.getOwnerDBA());
         pstmt.setInt(7,0);
         pstmt.setInt(8,2);  
         pstmt.execute();
         conn.commit();
         int id2 = this.getId(conn,elevatorSQL.SELECT_NEXT_TRANSACTION_ID);
         pstmt = conn.prepareStatement(elevatorSQL.INSERT_PERMIT_TRANSACTION);
         pstmt.clearParameters();
         pstmt.setInt(1,id2);
         pstmt.setDouble(2,annualFee);
         pstmt.setString(3,elevApp.getApplicationType() +"- Annual permit fee:("+annualFee+")" );
         pstmt.setInt(4,cart.getReceiptId());
         pstmt.setString(5,elevApp.getStateNumber());
         pstmt.setString(6,owner.getOwnerDBA());
         pstmt.setInt(7,0);
         pstmt.setInt(8,1); 
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
    public void insertElevFineTransaction(elevator elev,String fineType,int fineAmount ,DfbsOwner owner) throws SQLException, Exception 
     {
       Connection conn = null;
       PreparedStatement pstmt = null;
       
       try 
       {
         conn = getConnection();
         conn.setAutoCommit(false);
         
         int id = this.getId(conn,elevatorSQL.SELECT_NEXT_TRANSACTION_ID);
         
         pstmt = conn.prepareStatement(elevatorSQL.INSERT_PERMIT_TRANSACTION);
         pstmt.clearParameters();
         pstmt.setInt(1,id);
         pstmt.setDouble(2,fineAmount);
         pstmt.setString(3,fineType +"- re-inspection fine:("+fineAmount+")" );
         pstmt.setInt(4,owner.getOwnerId());
         pstmt.setString(5,elev.getStateNumber());
         pstmt.setString(6,owner.getOwnerDBA());
         pstmt.setInt(7,0);
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
    public String findInspectorEmail (String county,String zip) throws SQLException, Exception 
    {
     CallableStatement proc = null;
      Connection conn = null;
     try 
     {  
       
       conn = getConnection();
       conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call FIND_ELEV_INSPECTOR_EMAIL(?,?,?) }");
       proc.setString(1, county);
       proc.setString(2, zip);
       proc.registerOutParameter(3,java.sql.Types.VARCHAR);
       proc.execute();
       
      return(proc.getString(3));
       
     } catch (Exception ex) 
     {
       conn.rollback();
       throw new Exception(ex.getMessage());
     }
     finally 
     {
      try { conn.close();
          proc.close();
       } catch (Exception e) {}
     }
    }
  }
