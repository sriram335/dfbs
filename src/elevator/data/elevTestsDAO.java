package elevator.data;
import elevator.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import elevator.to.*;
public class elevTestsDAO extends HsDAO{
    public elevTestsDAO() {
      
    }
  public  List selectElevTestsList(int inspectionId) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     try 
     { conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_TESTS);
       pstmt.clearParameters();
       pstmt.setInt(1,inspectionId);
       rs = pstmt.executeQuery();
       while(rs.next()) 
       { elevTests elevTest = new elevTests();
         elevTest.setInspectionId(rs.getInt(5));
         elevTest.setTestId(rs.getInt(1));
         elevTest.setTestName(rs.getString(2));
         elevTest.setTestStatus(rs.getString(3));
         elevTest.setTestValue(rs.getString(4));
         list.add(elevTest);
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
    public  List selectElevStandrdTestsList() throws SQLException, Exception 
     {
     
       List list = new ArrayList();
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       Connection conn = null;
       try 
       { conn = getConnection();
         pstmt = conn.prepareStatement(elevatorSQL.SELECT_STANDARD_ELEVATOR_TESTS);
         pstmt.clearParameters();
         rs = pstmt.executeQuery();
         while(rs.next()) 
         { elevTests elevTest = new elevTests();
           elevTest.setTestId(rs.getInt(1));
           elevTest.setTestName(rs.getString(2));
           elevTest.setTestValue(rs.getString(4));
            list.add(elevTest);
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
   public elevTests selectElevTest(int testId) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     
     try 
     {
    
       conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_TEST);
       pstmt.clearParameters();
       pstmt.setInt(1,testId);
       rs = pstmt.executeQuery();
       elevTests elevTest = new elevTests();
       while(rs.next()) 
       {
         elevTest.setInspectionId(rs.getInt(5));
         elevTest.setTestId(rs.getInt(1));
         elevTest.setTestName(rs.getString(2));
         elevTest.setTestStatus(rs.getString(3));
         elevTest.setTestValue(rs.getString(4));
           
       }
       return elevTest;
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
   public void insertElevTests(elevTests elevTest) throws SQLException, Exception 
   {
   
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
              pstmt = conn.prepareStatement(elevatorSQL.INSERT_ELEVATOR_TESTS);
           pstmt.clearParameters();
          pstmt.setInt(4,elevTest.getInspectionId());
          pstmt.setString(1,elevTest.getTestName());
          pstmt.setString(2,elevTest.getTestStatus());
          pstmt.setString(3,elevTest.getTestValue());
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
  public void updateElevTests(elevTests elevTest) throws SQLException, Exception
  {
  
        Connection conn = null;
        PreparedStatement pstmt = null;
     try 
  {
        conn = getConnection();
        conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(elevatorSQL.UPDATE_ELEVATOR_TESTS);
        pstmt.clearParameters();
    pstmt.setInt(4,elevTest.getTestId());
    pstmt.setString(1,elevTest.getTestName());
    pstmt.setString(2,elevTest.getTestStatus());
    pstmt.setString(3,elevTest.getTestValue());
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

