package lepc.data;
import lepc.to.*;
import lepc.data.*;
import childcare.to.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;
public class lepcAgencyDAO extends HsDAO{
    public lepcAgencyDAO() {
       
    }
  public int insertAgency(lepcAgency agency) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,"SELECT AGENCY_ID.NEXTVAL FROM DUAL");
     pstmt = conn.prepareStatement(lepcSQL.INSERT_AGENCY);
     pstmt.clearParameters();
     pstmt.setString(6,agency.getAgencyDebrief());
     pstmt.setString(3,agency.getAgencyName());
     pstmt.setString(5,agency.getAgencyType());
     pstmt.setString(4,agency.getAgencyPresence());
     pstmt.setInt(1,id);
     agency.setAgencyId(id);  
     pstmt.setInt(2,agency.getExerciseId());
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
  public void updateAgency(lepcAgency agency) throws SQLException, Exception
    {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
      
       pstmt = conn.prepareStatement(lepcSQL.UPDATE_AGENCY);
       pstmt.clearParameters();
       pstmt.setString(1,agency.getAgencyDebrief());
       pstmt.setString(3,agency.getAgencyName());
       pstmt.setString(5,agency.getAgencyType());
       pstmt.setString(4,agency.getAgencyPresence());
       pstmt.setInt(6,agency.getAgencyId());
       pstmt.setInt(2,agency.getExerciseId());  
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
   public lepcAgency  selectAgency(int agencyId) throws SQLException, Exception 
  {
  
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     lepcAgency agency = new lepcAgency();
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_AGENCY);
     pstmt.clearParameters();
     pstmt.setInt(1,agencyId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {  
     agency.setAgencyDebrief(rs.getString(6));
     agency.setAgencyName(rs.getString(3));
     agency.setAgencyType(rs.getString(5));
     agency.setAgencyPresence(rs.getString(4));
     agency.setAgencyId(rs.getInt(1));
     agency.setExerciseId(rs.getInt(2));
   }
    return agency;
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
  public List selectAgencies(int exerciseId) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_AGENCIES);
     pstmt.clearParameters();
     pstmt.setInt(1,exerciseId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { lepcAgency agency = new lepcAgency();
       agency.setAgencyDebrief(rs.getString(6));
       agency.setAgencyName(rs.getString(3));
       agency.setAgencyType(rs.getString(5));
       agency.setAgencyPresence(rs.getString(4));
       agency.setAgencyId(rs.getInt(1));
       agency.setExerciseId(rs.getInt(2));
       list.add(agency);
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
}
