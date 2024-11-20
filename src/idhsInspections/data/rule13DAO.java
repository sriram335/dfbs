package idhsInspections.data;
import idhsInspections.to.*;

import hs.data.HsDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* excel 97-2003 imports */
import java.util.ArrayList;
import java.util.List;

public class rule13DAO extends HsDAO{
    public rule13DAO() {
    }
    public  List selectRule13s(int inspectionId) throws SQLException, Exception 
      {
     
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_RULE13);
          pstmt.clearParameters();
            pstmt.setInt(1,inspectionId);
          rs = pstmt.executeQuery();
          
          while(rs.next()) 
          { 
            rule13  rule = new rule13 ();
            
            rule.setRule13SeqId(rs.getInt(1));
              rule.setRule13Description(rs.getString(2));
              rule.setRule13Remarks(rs.getString(3));
              rule.setRule13Id(rs.getInt(4));
           list.add(rule);
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
    public  List selectRule13sInfo() throws SQLException, Exception 
      {
     
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_RULE13_INFO);
          pstmt.clearParameters();
          rs = pstmt.executeQuery();
          
          while(rs.next()) 
          { 
            rule13  rule = new rule13 ();
            
            rule.setRule13SeqId(rs.getInt(1));
              rule.setRule13Description(rs.getString(2));
           list.add(rule);
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
       
      
     
     
        
        public void insertRule13 (rule13 rule,int projectNumber) throws SQLException, Exception 
        {
         Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
               
        try 
        {
          conn = getConnection();
          conn.setAutoCommit(false);
          if(rule.getRule13SeqId()==1)
          {
            pstmt1 = conn.prepareStatement(InspectionsSQL.INSERT_CODE_SITE_INSPECTION);
            pstmt1.setInt(2,projectNumber);
            pstmt1.setInt(1,rule.getInspectionId());
            pstmt1.execute();
          }
          pstmt = conn.prepareStatement(InspectionsSQL.INSERT_RULE13);
          int id = this.getId(conn,InspectionsSQL.SELECT_NEXT_RESPONSE_ID);
          pstmt.setInt(2,rule.getRule13SeqId());
          pstmt.setString(3,rule.getRule13Remarks());
          pstmt.setInt(4,rule.getInspectionId());
          pstmt.setInt(1,id);
          rule.setRule13Id(id);
           
          pstmt.execute();
         
          conn.commit();
          
          
        }
        finally 
        {
         try {
            conn.close();
            pstmt1.close();
           pstmt.close();
          } catch (Exception e) {}
        }
        }
      
       public void updateRule13 (rule13  rule) throws SQLException, Exception 
      {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try 
        {
          conn = getConnection();
          conn.setAutoCommit(false);
         
          pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_RULE13);
          pstmt.clearParameters();
          
          pstmt.setString(1,rule.getRule13Remarks());
          pstmt.setInt(2,rule.getRule13Id());
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
      
    public  int countRule13s(int inspectionId) throws SQLException, Exception 
      {
     
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        int ruleCount =0;
        try 
        { 
          conn = getConnection();
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_COUNT_RULE13);
          pstmt.clearParameters();
          pstmt.setInt(1,inspectionId);
          rs = pstmt.executeQuery();
          
          while(rs.next()) 
          { 
             ruleCount = rs.getInt(1);
             
          }
          
          return ruleCount;
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
       
    public  rule13 selectRule13(int responseId) throws SQLException, Exception 
      {
     
         ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
         try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_RULE13_BY_ID);
          pstmt.clearParameters();
            pstmt.setInt(1,responseId);
          rs = pstmt.executeQuery();
            rule13  rule = new rule13 ();
          while(rs.next()) 
          { 
            
            rule.setRule13SeqId(rs.getInt(1));
              rule.setRule13Description(rs.getString(2));
              rule.setRule13Remarks(rs.getString(3));
              rule.setRule13Id(rs.getInt(4));
           
          }
          
          return rule;
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
