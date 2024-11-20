package idhsInspections.data;
import codeEducation.data.CodeEducationSQL;

import codeEducation.to.CodeEducationPlan;

import  idhsInspections.to.*;
import  idhsInspections.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import main.to.ApplicationSecurity;
public class fireInspActivityDAO extends HsDAO{
    public fireInspActivityDAO() {
    }
  public  List selectInspeActivities(int inspectorId,String fromDate, String toDate) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      try 
      {  conn = getConnection();
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTOR_ACTIVITIES);
        pstmt.clearParameters();
        pstmt.setInt(1,inspectorId);
        pstmt.setString(2,fromDate);
        pstmt.setString(3,toDate);  
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        {  
          fireInspectorActivity   inspActivity = new  fireInspectorActivity ();
            inspActivity.setActivityDate(rs.getDate(3));
            inspActivity.setActivityContact(rs.getString(5));
            inspActivity.setActivityLocation(rs.getString(6));
            inspActivity.setActivityRemarks(rs.getString(7));
            inspActivity.setActivityType(rs.getString(2));
            inspActivity.setActivityDuration(rs.getDouble(4));
            inspActivity.setActivityId(rs.getInt(1));
            inspActivity.setInspectorId(rs.getInt(8));
          inspActivity.setInspectorName(rs.getString(9));
         list.add(inspActivity);
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
  public  List selectInspeActivitiesGroup(int inspectorId,String fromDate, String toDate,String filterInspId ) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      try 
      {  conn = getConnection();
        if (filterInspId == "" ||filterInspId.equals("")) 
        {
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTOR_ACTIVITIES_GROUP);
        pstmt.clearParameters();
        pstmt.setInt(1,inspectorId);
        pstmt.setString(2,fromDate);
        pstmt.setString(3,toDate);  
        }
        else {System.out.println(filterInspId+":"+inspectorId);System.out.println(fromDate);System.out.println(toDate);
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTOR_ACTIVITIES_GROUP_FILTER);
          pstmt.clearParameters();
          pstmt.setString(1,fromDate);
          pstmt.setString(2,toDate);  
          pstmt.setString(3,filterInspId);     
        }
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        {  
          fireInspectorActivity   inspActivity = new  fireInspectorActivity ();
            inspActivity.setActivityDate(rs.getDate(3));
            inspActivity.setActivityContact(rs.getString(5));
            inspActivity.setActivityLocation(rs.getString(6));
            inspActivity.setActivityRemarks(rs.getString(7));
            inspActivity.setActivityType(rs.getString(2));
            inspActivity.setActivityDuration(rs.getDouble(4));
            inspActivity.setActivityId(rs.getInt(1));
            inspActivity.setInspectorId(rs.getInt(8));
          inspActivity.setInspectorName(rs.getString(9));
         list.add(inspActivity);
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
  public fireInspectorActivity  selectInspActivity(int activityId) throws SQLException, Exception 
  {
  
      fireInspectorActivity   inspActivity = new  fireInspectorActivity ();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
      pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTOR_ACTIVITY);
     pstmt.clearParameters();
     pstmt.setInt(1,activityId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
         inspActivity.setActivityDate(rs.getDate(3));
         inspActivity.setActivityContact(rs.getString(5));
         inspActivity.setActivityLocation(rs.getString(6));
         inspActivity.setActivityRemarks(rs.getString(7));
         inspActivity.setActivityType(rs.getString(2));
         inspActivity.setActivityDuration(rs.getDouble(4));
         inspActivity.setActivityId(rs.getInt(8));
         inspActivity.setInspectorId(rs.getInt(1));
     }
   
  
     return inspActivity;
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
  
  public void insertInspActivity (fireInspectorActivity   inspActivity,int inspectorId) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
     
  try 
  { 
    
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.INSERT_INSPECTOR_ACTIVITY);
      int id = this.getId(conn,InspectionsSQL.SELECT_NEXT_ACTIVITY_ID);
    
      pstmt.setString(3,inspActivity.getActivityDateString());
      pstmt.setString(5,inspActivity.getActivityContact());
      pstmt.setString(6,inspActivity.getActivityLocation());
      pstmt.setString(7,inspActivity.getActivityRemarks());
      pstmt.setString(2,inspActivity.getActivityType());
      pstmt.setDouble(4,inspActivity.getActivityDuration());
      pstmt.setInt(8,inspectorId);
      pstmt.setInt(1,id);
    pstmt.execute();
    conn.commit();
     
  }
  finally 
  {
   try {
      conn.close();
     pstmt.close();
    } catch (Exception e) { }
  }
  } 
  public void updateInspActivity (fireInspectorActivity   inspActivity) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
     
  try 
  { 
    
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_INSPECTOR_ACTIVITY);
    
      pstmt.setString(2,inspActivity.getActivityDateString());
      pstmt.setString(4,inspActivity.getActivityContact());
      pstmt.setString(5,inspActivity.getActivityLocation());
      pstmt.setString(6,inspActivity.getActivityRemarks());
      pstmt.setString(1,inspActivity.getActivityType());
      pstmt.setDouble(3,inspActivity.getActivityDuration());
      pstmt.setInt(7,inspActivity.getActivityId());
    pstmt.execute();
    conn.commit();
     
  }
  finally 
  {
   try {
      conn.close();
     pstmt.close();
    } catch (Exception e) { }
  }
   }
    public void deleteInspActivity (int  inspActivity) throws SQLException, Exception 
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
       
    try 
    { 
      
        conn = getConnection();
        conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(InspectionsSQL.DELETE_INSPECTOR_ACTIVITY);
          pstmt.setInt(1,inspActivity);
      pstmt.execute();
      conn.commit();
       
    }
    finally 
    {
     try {
        conn.close();
       pstmt.close();
      } catch (Exception e) { }
    }
     }
  }