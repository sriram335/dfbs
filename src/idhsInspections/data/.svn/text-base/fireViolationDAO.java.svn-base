package idhsInspections.data;
import  idhsInspections.to.*;
import  idhsInspections.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;
public class fireViolationDAO extends HsDAO{
    public fireViolationDAO() {
    }
      public  List selectIdhsViolations(int inspectionId) throws SQLException, Exception 
        {
       
          List list = new ArrayList();
          ResultSet rs = null;
          PreparedStatement pstmt = null;
          Connection conn = null;
          try 
          {  conn = getConnection();
            pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_VIOLATIONS);
            pstmt.clearParameters();
            pstmt.setInt(1,inspectionId);
            rs = pstmt.executeQuery();
            
            while(rs.next()) 
            { 
              fireViolation  violation = new fireViolation ();
              violation.setVioCompDate(rs.getDate(5));
              violation.setVioDate(rs.getDate(6));
              violation.setVioCode(rs.getString(2));
              violation.setVioDescription(rs.getString(3));
              violation.setVioRemarks(rs.getString(4));
              violation.setVioId(rs.getInt(1));
                violation.setVioOrder(rs.getInt(7));
             list.add(violation);
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
          public  List selectIdhsViolationsReInsp(int inspectionId) throws SQLException, Exception 
            {
           
              List list = new ArrayList();
              ResultSet rs = null;
              PreparedStatement pstmt = null;
              Connection conn = null;
              try 
              {  conn = getConnection();
                pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_VIOLATIONS_PENDING);
                pstmt.clearParameters();
                pstmt.setInt(1,inspectionId);
                rs = pstmt.executeQuery();
                
                while(rs.next()) 
                { 
                  fireViolation  violation = new fireViolation ();
                  violation.setVioCompDate(rs.getDate(5));
                  violation.setVioDate(rs.getDate(6));
                  violation.setVioCode(rs.getString(2));
                  violation.setVioDescription(rs.getString(3));
                  violation.setVioRemarks(rs.getString(4));
                    violation.setVioOrder(rs.getInt(7));
                    violation.setVioId(rs.getInt(1));
                 list.add(violation);
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
        
         public fireViolation  selectIdhsViolation(int idNumber) throws SQLException, Exception 
        {
        
          fireViolation  violation = new fireViolation ();
          Connection conn = null;
          ResultSet rs = null;
          PreparedStatement pstmt = null;
             
          try 
          {
             
            conn = getConnection();
             pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_VIOLATION_BY_ID);
            pstmt.clearParameters();
            pstmt.setInt(1,idNumber);
            rs = pstmt.executeQuery();
            if(rs.next()) 
            {
              violation.setVioCompDate(rs.getDate(10));
              violation.setVioDate(rs.getDate(4));
              violation.setVioCode(rs.getString(2));
              violation.setVioDescription(rs.getString(3));
              violation.setVioRemarks(rs.getString(6));
              violation.setVioId(rs.getInt(1));
              violation.setVioOrder(rs.getInt(8));
              violation.setInspectionId(rs.getInt(9));
              
            }
          
         
            return violation;
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
       
          public fireViolation  selectIdhsViolationMin(int vioId,int inspId) throws SQLException, Exception 
          {
          
           fireViolation  violation = new fireViolation ();
           Connection conn = null;
           ResultSet rs = null;
           PreparedStatement pstmt = null;
           try 
           {
              
             conn = getConnection();
              pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_VIOLATION_BY_ID_MIN);
             pstmt.clearParameters();
               pstmt.setInt(1,vioId);
                 pstmt.setInt(2,inspId);
             rs = pstmt.executeQuery();
             if(rs.next()) 
             {
               violation.setVioCompDate(rs.getDate(10));
               violation.setVioDate(rs.getDate(4));
               violation.setVioCode(rs.getString(2));
               violation.setVioDescription(rs.getString(3));
               violation.setVioRemarks(rs.getString(6));
               violation.setVioId(rs.getInt(1));
               violation.setVioOrder(rs.getInt(8));
               violation.setInspectionId(rs.getInt(9));
               
             }
           
          
             return violation;
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
          public fireViolation  selectIdhsViolationMax(int vioId,int inspId) throws SQLException, Exception 
          {
          
           fireViolation  violation = new fireViolation ();
           Connection conn = null;
           ResultSet rs = null;
           PreparedStatement pstmt = null;
           try 
           {
              
             conn = getConnection();
              pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_VIOLATION_BY_ID_MAX);
             pstmt.clearParameters();
             pstmt.setInt(1,vioId);
               pstmt.setInt(2,inspId);
             rs = pstmt.executeQuery();
             if(rs.next()) 
             {
               violation.setVioCompDate(rs.getDate(10));
               violation.setVioDate(rs.getDate(4));
               violation.setVioCode(rs.getString(2));
               violation.setVioDescription(rs.getString(3));
               violation.setVioRemarks(rs.getString(6));
               violation.setVioId(rs.getInt(1));
               violation.setVioOrder(rs.getInt(8));
               violation.setInspectionId(rs.getInt(9));
               
             }
           
          
             return violation;
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
          public int insertIdhsViolation (fireViolation violation,fireInspection inspection) throws SQLException, Exception 
          {
          
          Connection conn = null;
          PreparedStatement pstmt = null;
             
          try 
          {
            
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(InspectionsSQL.INSERT_IDHS_VIOLATION);
            int id = this.getId(conn,InspectionsSQL.SELECT_NEXT_VIOLATION_ID);
            pstmt.setString(4,inspection.getNextInspDateString());
            pstmt.setString(2,violation.getVioCode());
            pstmt.setString(3,violation.getVioDescription());
            pstmt.setString(6,inspection.getInspStatus());
            pstmt.setString(7,violation.getVioRemarks());
            pstmt.setInt(8,violation.getVioOrder());
            pstmt.setInt(1,id);
            violation.setVioId(id);
             pstmt.setString(5,Integer.toString(inspection.getInspId()));
            pstmt.execute();
            
          
            conn.commit();
            
             return(id); 
          }
          finally 
          {
           try {
              conn.close();
             pstmt.close();
            } catch (Exception e) {}
          }
          }
        
         public void updateViolation (fireViolation  violation) throws SQLException, Exception 
        {
          Connection conn = null;
          PreparedStatement pstmt = null;
          
          try 
          {
            conn = getConnection();
            conn.setAutoCommit(false);
           
            pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_IDHS_VIOLATION);
            pstmt.clearParameters();
            
            pstmt.setString(5,violation.getVioCompDateString());
            pstmt.setString(4,violation.getVioDateString());
            pstmt.setString(2,violation.getVioCode());
            pstmt.setString(3,violation.getVioDescription());
            pstmt.setString(7,violation.getVioRemarks());
            pstmt.setString(6,violation.getVioType());
            pstmt.setInt(8,violation.getVioId());
            pstmt.setInt(1,violation.getVioOrder());
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
          public void deleteViolation (int  violationId) throws SQLException, Exception 
          {
           Connection conn = null;
           PreparedStatement pstmt = null;
           
           try 
           {
             conn = getConnection();
             conn.setAutoCommit(false);
             
             pstmt = conn.prepareStatement(InspectionsSQL.DELETE_IDHS_VIOLATION);
             pstmt.clearParameters();
             
            
             pstmt.setInt(1,violationId);
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
         public  List selectStdViolations(String stdViolation,String vioYear,String lookFor,String lsql) throws SQLException, Exception 
        {
       
          List list = new ArrayList();
          ResultSet rs = null;
          PreparedStatement pstmt = null;
          Connection conn = null;
          try 
          {  conn = getConnection();
             lsql= lsql+" LIKE UPPER('%"+stdViolation+"%')";
              if(vioYear!=null && vioYear.length()==4){
                  lsql= lsql+" AND upper(abbreviation) LIKE UPPER('%"+vioYear+"%')";    
              }
              System.out.println("lsql:"+lsql);
            pstmt = conn.prepareStatement(lsql);
            pstmt.clearParameters();
            rs = pstmt.executeQuery();
            while(rs.next()) 
            { 
              fireViolation  violation = new fireViolation ();
              
              violation.setVioCode(rs.getString(2));
              violation.setVioDescription(rs.getString(1));
              violation.setVioId(rs.getInt(3));
              violation.setVioYear(vioYear);
             list.add(violation);
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
        
        public void  selectStdVioDescription(String stdVioId,fireViolation  violation) throws SQLException, Exception 
        {
        
          Connection conn = null;
          ResultSet rs = null;
          PreparedStatement pstmt = null;
          try 
          {
              
            conn = getConnection();
             pstmt = conn.prepareStatement(InspectionsSQL.SELECT_STANDARD_VIOLATION);
            pstmt.clearParameters();
            pstmt.setInt(1,Integer.parseInt(stdVioId));
            rs = pstmt.executeQuery();
            if(rs.next()) 
            {
                violation.setVioCode(rs.getString(2));
                violation.setVioDescription(rs.getString(1));
             
            }
          
         
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
          public void insertIdhsStandardViolation (fireViolation violation) throws SQLException, Exception 
          {
          
          Connection conn = null;
          PreparedStatement pstmt = null;
             
          try 
          {
            
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(InspectionsSQL.INSERT_STANDARD_VIOLATION);
            pstmt.setString(1,violation.getVioCode());
            pstmt.setString(2,violation.getVioDescription());
            
            pstmt.execute();
            
          
            conn.commit();
            
             
          }
          finally 
          {
           try {
              conn.close();
             pstmt.close();
            } catch (Exception e) {}
          }
          }
          public  List selectStdVioCode() throws SQLException, Exception 
          {
          
           List list = new ArrayList();
           ResultSet rs = null;
           PreparedStatement pstmt = null;
           Connection conn = null;
           try 
           {  conn = getConnection();
                   pstmt = conn.prepareStatement(InspectionsSQL.IDHS_VIO_CODE_OPTIONS);
             pstmt.clearParameters();
             rs = pstmt.executeQuery();
             while(rs.next()) 
             { 
               fireViolation  violation = new fireViolation ();
               
               violation.setVioCode(rs.getString(1));
              
              list.add(violation);
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
 
          public  List selectStdVioCode(String year) throws SQLException, Exception 
          {
          
           List list = new ArrayList();
           ResultSet rs = null;
           PreparedStatement pstmt = null;
           Connection conn = null;
           try 
           {  conn = getConnection();
                   pstmt = conn.prepareStatement(InspectionsSQL.IDHS_VIO_CODE_OPTIONS_YR);
             pstmt.clearParameters();
             pstmt.setString(1,year);
             rs = pstmt.executeQuery();
             while(rs.next()) 
             { 
               fireViolation  violation = new fireViolation ();
               
               violation.setVioCode(rs.getString(1));
              
              list.add(violation);
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
