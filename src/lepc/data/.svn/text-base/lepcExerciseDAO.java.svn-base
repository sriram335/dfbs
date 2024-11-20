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

public class lepcExerciseDAO extends HsDAO{
    public lepcExerciseDAO() {
        
    }
    
  public int insertExecise(lepcExercise exercise,String userId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   PreparedStatement pstmt1 = null;
    try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,"SELECT LEPC_EXERCISE_ID.NEXTVAL FROM DUAL");
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_EXERCISE);
     pstmt.clearParameters();
     pstmt.setString(12,exercise.getExerciseCity());
     pstmt.setString(13,exercise.getExerciseCounty());
     pstmt.setString(8,exercise.getExerciseEndDateString());
     pstmt.setString(10,exercise.getExerciseEndTime());
     pstmt.setString(15,exercise.getExerciseIncidentType());
     pstmt.setString(11,exercise.getExerciseLocation());
     pstmt.setString(14,exercise.getExerciseScenario());
     pstmt.setString(17,exercise.getExerciseNumber());  
     pstmt.setString(7,exercise.getExerciseStartDateString());
     pstmt.setString(9,exercise.getExerciseStartTime());
     pstmt.setString(16,"Pending");
     pstmt.setString(3,exercise.getExerciseType());
     pstmt.setString(4,userId);
     pstmt.setString(5,exercise.getReportDateString());
     pstmt.setString(6,exercise.getReportType());
     pstmt.setInt(1,id);
     exercise.setExerciseId(id) ;
     pstmt.setInt(2,exercise.getLepcId());
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
       pstmt1.close(); 
     } catch (Exception e) {}
   }
  }
  public void updateExercise(lepcExercise exercise) throws SQLException, Exception
    {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
      
       pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_EXERCISE);
       pstmt.clearParameters();
       pstmt.setString(12,exercise.getExerciseCity());
       pstmt.setString(13,exercise.getExerciseCounty());
       pstmt.setString(8,exercise.getExerciseEndDateString());
       pstmt.setString(10,exercise.getExerciseEndTime());
       pstmt.setString(15,exercise.getExerciseIncidentType());
       pstmt.setString(11,exercise.getExerciseLocation());
       pstmt.setString(14,exercise.getExerciseScenario());
       pstmt.setString(7,exercise.getExerciseStartDateString());
       pstmt.setString(9,exercise.getExerciseStartTime());
       pstmt.setString(1,exercise.getExerciseStatus());
       pstmt.setString(3,exercise.getExerciseType());
       pstmt.setString(4,exercise.getReportBy());
       pstmt.setString(5,exercise.getReportDateString());
       pstmt.setString(6,exercise.getReportType());
       pstmt.setInt(17,exercise.getExerciseId());
       pstmt.setString(16,exercise.getExerciseNumber());  
       pstmt.setInt(2,exercise.getLepcId());
       pstmt.setString(1,exercise.getExerciseStatus());  
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
   public lepcExercise  selectExercise(int exerciseId) throws SQLException, Exception 
  {
  
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     lepcExercise exercise = new lepcExercise();
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_EXERCISE );
     pstmt.clearParameters();
     pstmt.setInt(1,exerciseId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {  
     exercise.setExerciseCity(rs.getString(12));
     exercise.setExerciseCounty(rs.getString(13));
     exercise.setExerciseEndDate(rs.getDate(8));
     exercise.setExerciseEndTime(rs.getString(10));
     exercise.setExerciseIncidentType(rs.getString(15));
     exercise.setExerciseLocation(rs.getString(11));
     exercise.setExerciseScenario(rs.getString(14));
     exercise.setExerciseStartDate(rs.getDate(7));
     exercise.setExerciseStartTime(rs.getString(9));
     exercise.setExerciseStatus(rs.getString(16));
     exercise.setExerciseNumber(rs.getString(17));
     exercise.setExerciseType(rs.getString(3));
     exercise.setReportBy(rs.getString(4));
     exercise.setReportDate(rs.getDate(5));
     exercise.setReportType(rs.getString(6));
     exercise.setExerciseId(rs.getInt(1));
     exercise.setLepcId(rs.getInt(2));
   }
    return exercise;
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
  public List selectExerciseByLepc(int lepcId,String reportType) throws SQLException, Exception 
  {
  
 
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  List list = new ArrayList();
       
  try 
  {
    lepcExercise exercise = new lepcExercise();
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_EXERCISE_BY_LEPC);
    pstmt.clearParameters();
    pstmt.setInt(1,lepcId);
    pstmt.setString(2,reportType);
    rs = pstmt.executeQuery();
    while(rs.next()) 
    {  
    exercise.setExerciseCity(rs.getString(12));
    exercise.setExerciseCounty(rs.getString(13));
    exercise.setExerciseEndDate(rs.getDate(8));
    exercise.setExerciseEndTime(rs.getString(10));
    exercise.setExerciseIncidentType(rs.getString(15));
    exercise.setExerciseLocation(rs.getString(11));
    exercise.setExerciseScenario(rs.getString(14));
    exercise.setExerciseStartDate(rs.getDate(7));
    exercise.setExerciseStartTime(rs.getString(9));
    exercise.setExerciseStatus(rs.getString(16));
    exercise.setExerciseType(rs.getString(3));
    exercise.setReportBy(rs.getString(4));
    exercise.setReportDate(rs.getDate(5));
    exercise.setReportType(rs.getString(6));
    exercise.setExerciseId(rs.getInt(1));
    exercise.setLepcId(rs.getInt(2));
    exercise.setExerciseNumber(rs.getString(17));
        list.add(exercise);
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
