package lepc.data;
import childcare.to.DfbsContact;

import lepc.to.*;
import lepc.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import idhsInspections.to.searchResults;

import java.io.InputStream;
import java.io.OutputStream;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;
public class lepcMeetingDAO extends HsDAO{
    public lepcMeetingDAO() {
       
    }
    
  public int insertLepcMeeting(lepcMeeting meeting) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,"SELECT LEPC_MEETING_ID.NEXTVAL FROM DUAL" );
       meeting.setMeetingId(id);
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_MEETING);
     pstmt.clearParameters();
     pstmt.setString(2,meeting.getMeetingDateString());
     pstmt.setString(7,meeting.getMeetingAddress1());
     pstmt.setString(8,meeting.getMeetingAddress2());
     pstmt.setString(9,meeting.getMeetingCity());
     pstmt.setString(4,meeting.getMeetingContact());
     pstmt.setString(6,meeting.getMeetingContactEmail());
     pstmt.setString(5,meeting.getMeetingContactPhone());
     pstmt.setString(10,meeting.getMeetingState());
     pstmt.setString(3,meeting.getMeetingTime());
     pstmt.setString(11,meeting.getMeetingZip());
     pstmt.setInt(1,meeting.getMeetingId());
     pstmt.setInt(12,meeting.getLepcId());
    
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
   public lepcMeeting selectLepcMeeting(int meetingId) throws SQLException, Exception 
  {
  
   lepcMeeting meeting = new lepcMeeting();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_MEETING );
     pstmt.clearParameters();
     pstmt.setInt(1,meetingId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {  meeting.setMeetingDate(rs.getDate(2));
        meeting.setMeetingAddress1(rs.getString(7));
        meeting.setMeetingAddress2(rs.getString(8));
        meeting.setMeetingCity(rs.getString(9));
        meeting.setMeetingContact(rs.getString(4));
        meeting.setMeetingContactEmail(rs.getString(6));
        meeting.setMeetingContactPhone(rs.getString(5));
        meeting.setMeetingState(rs.getString(10));
        meeting.setMeetingTime(rs.getString(3));
        meeting.setMeetingZip(rs.getString(11));
        meeting.setMeetingId(rs.getInt(1));
        meeting.setLepcId(rs.getInt(12));
   }
    return meeting;
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
  
  public void updateLepcMeeting(lepcMeeting meeting) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
         
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_MEETING);
     pstmt.clearParameters();
     pstmt.setString(2,meeting.getMeetingDateString());
     pstmt.setString(7,meeting.getMeetingAddress1());
     pstmt.setString(8,meeting.getMeetingAddress2());
     pstmt.setString(9,meeting.getMeetingCity());
     pstmt.setString(4,meeting.getMeetingContact());
     pstmt.setString(6,meeting.getMeetingContactEmail());
     pstmt.setString(5,meeting.getMeetingContactPhone());
     pstmt.setString(10,meeting.getMeetingState());
     pstmt.setString(3,meeting.getMeetingTime());
     pstmt.setString(11,meeting.getMeetingZip());
     pstmt.setInt(12,meeting.getMeetingId());
     pstmt.setInt(1,meeting.getLepcId());
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
  public List selectMeetingList(int lepcId) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_MEETING_BY_LEPC);
     pstmt.clearParameters();
     pstmt.setInt(1,lepcId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { lepcMeeting meeting = new lepcMeeting();
       meeting.setMeetingDate(rs.getDate(2));
               meeting.setMeetingAddress1(rs.getString(7));
               meeting.setMeetingAddress2(rs.getString(8));
               meeting.setMeetingCity(rs.getString(9));
               meeting.setMeetingContact(rs.getString(4));
               meeting.setMeetingContactEmail(rs.getString(6));
               meeting.setMeetingContactPhone(rs.getString(5));
               meeting.setMeetingState(rs.getString(10));
               meeting.setMeetingTime(rs.getString(3));
               meeting.setMeetingZip(rs.getString(11));
               meeting.setMeetingId(rs.getInt(1));
               meeting.setLepcId(rs.getInt(12));
       list.add(meeting);
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
