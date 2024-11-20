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


public class fiscalReportDAO extends HsDAO{
    public fiscalReportDAO() {
       
    }
    
  public int insertFiscalReport(fiscalReport fiscalRep,String userId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,"SELECT LEPC_FISCAL_ID.NEXTVAL FROM DUAL" );
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_FISCAL_REPORT);
     pstmt.clearParameters();
     pstmt.setInt(5,fiscalRep.getLepcId());
     pstmt.setDouble(2,fiscalRep.getAcctBalance());
     pstmt.setDouble(6,fiscalRep.getAcctGrants());
     pstmt.setDouble(7,fiscalRep.getInvBalance());
     pstmt.setDouble(8,fiscalRep.getAcctReceiptsCurrent());
     pstmt.setDouble(9,fiscalRep.getAmendCredit());
     pstmt.setDouble(10,fiscalRep.getAmendDebit());
     pstmt.setString(4,userId);
     pstmt.setDouble(3,fiscalRep.getAcctReceipts());
     pstmt.setInt(1,id);
     fiscalRep.setFiscalId(id);
    
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
   public fiscalReport selectFiscalReport(int fiscalId) throws SQLException, Exception 
  {
  
   fiscalReport fiscalRep = new fiscalReport();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_FISCAL_REPORT );
     pstmt.clearParameters();
     pstmt.setInt(1,fiscalId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     { fiscalRep.setRepDate(rs.getDate(5));
        fiscalRep.setReportBy(rs.getString(4));
        fiscalRep.setAcctBalance(rs.getDouble(2));
        fiscalRep.setAcctReceipts(rs.getDouble(3));
        fiscalRep.setFiscalId(rs.getInt(1));
        fiscalRep.setLepcId(rs.getInt(7));
        fiscalRep.setFiscalStatus(rs.getString(6));
        fiscalRep.setAcctGrants(rs.getDouble(8));
        fiscalRep.setInvBalance(rs.getDouble(9));
        fiscalRep.setAcctReceiptsCurrent(rs.getDouble(10));
       fiscalRep.setAmendCredit(rs.getDouble(11));
       fiscalRep.setAmendDebit(rs.getDouble(12));
   }
    return fiscalRep;
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
  
  public void updateFiscalReport(fiscalReport fiscalRep) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
         
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_FISCAL_REPORT);
     pstmt.clearParameters();
     pstmt.setString(4,fiscalRep.getRepDateString());
     pstmt.setInt(6,fiscalRep.getLepcId());
     pstmt.setString(5,fiscalRep.getFiscalStatus());  
     pstmt.setDouble(1,fiscalRep.getAcctBalance());
     pstmt.setDouble(8,fiscalRep.getInvBalance());
     pstmt.setString(3,fiscalRep.getReportBy());
     pstmt.setDouble(2,fiscalRep.getAcctReceipts());
     pstmt.setDouble(9,fiscalRep.getAcctReceiptsCurrent());
     pstmt.setDouble(7,fiscalRep.getAcctGrants()); 
       pstmt.setDouble(10,fiscalRep.getAmendCredit()); 
       pstmt.setDouble(11,fiscalRep.getAmendDebit()); 
       pstmt.setInt(12,fiscalRep.getFiscalId());
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
  public void FindFiscalBalance (fiscalReport fisReport, String lepcYear ,String lepcCounty) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call lepc_find_account_blance(?,?,?,?) }");
      proc.setString(1, lepcYear);
      proc.setString(4, lepcCounty); 
      proc.registerOutParameter(3,java.sql.Types.VARCHAR);
      proc.registerOutParameter(2,java.sql.Types.DOUBLE);
      proc.execute();
       fisReport.setAcctBalance(proc.getDouble(2));
       fisReport.setReportBy(proc.getString(3));
     
   } catch (Exception ex) 
   {
     conn.rollback();
     throw new Exception(ex.getMessage());
   }
   finally 
   {
    try { proc.close();
       conn.close();
     } catch (Exception e) {}
   }
  }
  public fiscalReport selectFiscalReportByLepc(int lepcId) throws SQLException, Exception 
  {
  
  fiscalReport fiscalRep = new fiscalReport();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_FISCAL_REPORT_BY_LEPC );
    pstmt.clearParameters();
    pstmt.setInt(1,lepcId);
    rs = pstmt.executeQuery();
    if(rs.next()) 
    { fiscalRep.setRepDate(rs.getDate(5));
       fiscalRep.setReportBy(rs.getString(4));
       fiscalRep.setAcctBalance(rs.getDouble(2));
       fiscalRep.setAcctReceipts(rs.getDouble(3));
       fiscalRep.setFiscalId(rs.getInt(1));
       fiscalRep.setLepcId(rs.getInt(7));
       fiscalRep.setAcctGrants(rs.getDouble(8));
       fiscalRep.setFiscalStatus(rs.getString(6));
       fiscalRep.setInvBalance(rs.getDouble(9));
       fiscalRep.setAcctReceiptsCurrent(rs.getDouble(10));
       fiscalRep.setAmendCredit(rs.getDouble(11));
       fiscalRep.setAmendDebit(rs.getDouble(12));
  }
   return fiscalRep;
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
  public int insertFiscalExpenditure(fiscalReport fiscalRep) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
    try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,"SELECT LEPC_EXERCISE_ID.NEXTVAL FROM DUAL" );
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_FISCAL_EXPENDITURE);
     pstmt.clearParameters();
      pstmt.setInt(2,fiscalRep.getFiscalId());
     pstmt.setString(3,fiscalRep.getReportBy());
     pstmt.setDouble(4,fiscalRep.getAcctBalance());
     pstmt.setInt(1,id);
     fiscalRep.setLepcId(id);
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
   public fiscalReport selectFiscalExpenditure(int fiscalId) throws SQLException, Exception 
  {
  
   fiscalReport fiscalRep = new fiscalReport();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_FISCAL_EXPENDITURE );
     pstmt.clearParameters();
     pstmt.setInt(1,fiscalId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     { 
        fiscalRep.setReportBy(rs.getString(3));
        fiscalRep.setAcctBalance(rs.getDouble(4));
        fiscalRep.setFiscalId(rs.getInt(2));
        fiscalRep.setLepcId(rs.getInt(1));
   }
    return fiscalRep;
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
  
  public void updateFiscalExpenditure(fiscalReport fiscalRep) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
         
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_FISCAL_EXPENDITURE);
     pstmt.clearParameters();
     pstmt.setInt(4,fiscalRep.getLepcId());
     pstmt.setDouble(3,fiscalRep.getAcctBalance());
     pstmt.setString(2,fiscalRep.getReportBy());
     pstmt.setInt(1,fiscalRep.getFiscalId());
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
  public List selectExpenditureList(int fiscalId) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_FISCAL_EXPENDITURE_BY_REPORT);
     pstmt.clearParameters();
     pstmt.setInt(1,fiscalId);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { fiscalReport fiscalRep = new fiscalReport();
       fiscalRep.setReportBy(rs.getString(3));
       fiscalRep.setAcctBalance(rs.getDouble(4));
       fiscalRep.setFiscalId(rs.getInt(2));
       fiscalRep.setLepcId(rs.getInt(1));
       list.add(fiscalRep);
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
  public double FindFiscalBalanceCurrentYear (fiscalReport fisReport, int fiscalId ) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   double expenditure =0;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call lepc_find_acct_bal_curt_year(?,?,?) }");
      proc.setInt(1, fiscalId);
      proc.registerOutParameter(2,java.sql.Types.DOUBLE);
      proc.registerOutParameter(3,java.sql.Types.DOUBLE);
      proc.execute();
       fisReport.setAcctBalance(proc.getDouble(2));
       
       expenditure =proc.getDouble(3);
     return (expenditure);
   } 
   
   catch (Exception ex) 
   {
     conn.rollback();
     throw new Exception(ex.getMessage());
   }
   finally 
   {
    try { proc.close();
       conn.close();
     } catch (Exception e) {}
   }
  }
  // END
}
