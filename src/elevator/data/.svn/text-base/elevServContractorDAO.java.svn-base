package elevator.data;
import elevator.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import elevator.to.*;

import java.io.InputStream;
import java.io.OutputStream;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;



public class elevServContractorDAO  extends HsDAO{
    public elevServContractorDAO() {
        
    }
  public  List selectElevContractorList(String startLetter) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     try 
     {
       conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_SERVICE_CONTRACTOR_LIST);
       pstmt.clearParameters();
         pstmt.setString(1,startLetter);
       rs = pstmt.executeQuery();
       while(rs.next()) 
       { 
       
         elevServiceContractor cont = new elevServiceContractor();
         cont.setContExpDate(rs.getDate(17));
         cont.setContIssueDate(rs.getDate(16));
         cont.setContLetterDate(rs.getDate(19));
         cont.setContPrintDate(rs.getDate(20));
         cont.setContFee(rs.getDouble(18));
         cont.setContAddress1(rs.getString(7));
         cont.setContAddress2(rs.getString(8));
         cont.setContCity(rs.getString(9));
         cont.setContEmail(rs.getString(15));
         cont.setContFax(rs.getString(14));
         cont.setContFirstName(rs.getString(4));
         cont.setContLastName(rs.getString(6));
         cont.setContLicStatus(rs.getString(21));
         cont.setContMI(rs.getString(5));
         cont.setContPhone(rs.getString(13));
         cont.setContState(rs.getString(10));
         cont.setContType(rs.getString(3));
         cont.setContZip(rs.getString(11));
         cont.setContZip2(rs.getString(12));
         cont.setLicNumber(rs.getString(2));
         cont.setContComplaints(rs.getInt(22));
         cont.setContConvictions(rs.getInt(24));
         cont.setContId (rs.getInt(1));
         cont.setContParentId(rs.getInt(25));
         cont.setContViolations(rs.getInt(23));
          list.add(cont);
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
   public elevServiceContractor selectElevContractor(int contId) throws SQLException, Exception 
   {
   
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     try 
     {
    
       conn = getConnection();
       pstmt = conn.prepareStatement(elevatorSQL.SELECT_SERVICE_CONTRACTOR);
       pstmt.clearParameters();
       pstmt.setInt(1,contId);
       rs = pstmt.executeQuery();
       elevServiceContractor cont = new elevServiceContractor();
       while(rs.next()) 
       {  
         cont.setContAddress1(rs.getString(7));
         cont.setContAddress2(rs.getString(8));
         cont.setContExpDate(rs.getDate(17));
         cont.setContIssueDate(rs.getDate(16));
         cont.setContLetterDate(rs.getDate(19));
         cont.setContPrintDate(rs.getDate(20)); 
         cont.setContFee(rs.getDouble(18));
         cont.setContCity(rs.getString(9));
         cont.setContEmail(rs.getString(15));
         cont.setContFax(rs.getString(14));
         cont.setContFirstName(rs.getString(4));
         cont.setContLastName(rs.getString(6));
         cont.setContLicStatus(rs.getString(21));
         cont.setContMI(rs.getString(5));
         cont.setContPhone(rs.getString(13));
         cont.setContState(rs.getString(10));
         cont.setContType(rs.getString(3));
         cont.setContZip(rs.getString(11));
         cont.setContZip2(rs.getString(12));
         cont.setLicNumber(rs.getString(2));
         cont.setContComplaints(rs.getInt(22));
         cont.setContId (rs.getInt(1));
         cont.setContConvictions(rs.getInt(24));
         cont.setContViolations(rs.getInt(23));
         cont.setContParentId(rs.getInt(25));
         
         
       }
       return cont;
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
   public void insertElevContractor(elevServiceContractor cont) throws SQLException, Exception 
   {
   
          Connection conn = null;
          PreparedStatement pstmt = null;
          PreparedStatement pstmt1 = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
      int id = this.getId(conn,"select con_service.nextval from dual");
      cont.setContParentId(id);
      this.calculateElevServContFee(cont);
       pstmt = conn.prepareStatement(elevatorSQL.INSERT_SELECT_SERVICE_CONTRACTOR);
          pstmt.clearParameters();
       pstmt.setDouble(14,cont.getContFee());
      pstmt.setString(5,cont.getContAddress1());
      pstmt.setString(6,cont.getContAddress2());
      pstmt.setString(7,cont.getContCity());
      pstmt.setString(13,cont.getContEmail());
      pstmt.setString(12,cont.getContFax());
      pstmt.setString(2,cont.getContFirstName());
      pstmt.setString(4,cont.getContLastName());
      pstmt.setString(3,cont.getContMI());
      pstmt.setString(11,cont.getContPhone());
      pstmt.setString(8,cont.getContState());
      pstmt.setString(1,cont.getContType());
      pstmt.setString(9,cont.getContZip());
      pstmt.setString(10,cont.getContZip2());
      pstmt.setInt(15,cont.getContParentId());
      pstmt.setInt(16,cont.getContId());
      pstmt.setString(17,cont.getContType()+Integer.toString(id));
          pstmt.execute();
      pstmt1 = conn.prepareStatement(elevatorSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,cont.getContPhone());
      pstmt1.setString(2,Integer.toString(cont.getContId()));
      pstmt1.setString(3,"ElevatorCont");
       pstmt1.execute();
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
        pstmt1.close();
      } catch (Exception e) {}
    }
   } 
  public void updateElevServiceContractor(elevServiceContractor cont) throws SQLException, Exception
  {
  
        Connection conn = null;
        PreparedStatement pstmt = null;
     try 
  {
        conn = getConnection();
        conn.setAutoCommit(true);
     this.calculateElevServContFee(cont);
            pstmt = conn.prepareStatement(elevatorSQL.UPDATE_SERVICE_CONTRACTOR);
        pstmt.clearParameters();
     pstmt.setString(17,cont.getContExpDateString());
     pstmt.setString(16,cont.getContIssueDateString());
     pstmt.setString(19,cont.getContLetterDateString());
     pstmt.setString(20,cont.getContPrintDateString());
     pstmt.setDouble(18,cont.getContFee());
     pstmt.setString(7,cont.getContAddress1());
     pstmt.setString(8,cont.getContAddress2());
     pstmt.setString(9,cont.getContCity());
     pstmt.setString(15,cont.getContEmail());
     pstmt.setString(14,cont.getContFax());
     pstmt.setString(4,cont.getContFirstName());
     pstmt.setString(6,cont.getContLastName());
     pstmt.setString(21,cont.getContLicStatus());
     pstmt.setString(5,cont.getContMI());
     pstmt.setString(13,cont.getContPhone());
     pstmt.setString(10,cont.getContState());
     pstmt.setString(3,cont.getContType());
     pstmt.setString(11,cont.getContZip());
     pstmt.setString(12,cont.getContZip2());
     pstmt.setString(2,cont.getLicNumber());
     pstmt.setInt(22,cont.getContComplaints());
     pstmt.setInt(24,cont.getContConvictions());
     pstmt.setInt(25,cont.getContId ());
     pstmt.setInt(1,cont.getContParentId());
     pstmt.setInt(23,cont.getContViolations());
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
  public void updateElevServiceContRenewal(elevServiceContractor cont) throws SQLException, Exception
  {
  
        Connection conn = null;
        PreparedStatement pstmt = null;
     try 
  {
        conn = getConnection();
        conn.setAutoCommit(true);
     this.calculateElevServContFee(cont);
            pstmt = conn.prepareStatement(elevatorSQL.UPDATE_SERVICE_CONTRACTOR_RENEWAL);
        pstmt.clearParameters();
    
    pstmt.setString(2,cont.getContAddress1());
     pstmt.setString(3,cont.getContAddress2());
     pstmt.setString(4,cont.getContCity());
     pstmt.setString(10,cont.getContEmail());
     pstmt.setString(9,cont.getContFax());
      pstmt.setString(8,cont.getContPhone());
     pstmt.setString(5,cont.getContState());
     pstmt.setString(3,cont.getContType());
     pstmt.setString(6,cont.getContZip());
     pstmt.setString(7,cont.getContZip2());
     pstmt.setDouble(11,cont.getContFee ());
     pstmt.setInt(12,cont.getContId ());
     pstmt.setInt(1,cont.getContParentId());
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
  public void calculateElevServContFee(elevServiceContractor cont ) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
       int lfee=0;
     double lfeeDouble=0;
     proc = conn.prepareCall("{ call elevator_serv_cont_fee_online(?,?) }");
     proc.setString(1, cont.getContType());
     proc.registerOutParameter(2,java.sql.Types.INTEGER);
     proc.execute();
     conn.commit();
     lfee=(proc.getInt(2));
     lfeeDouble=lfee;
     cont.setContFee(lfeeDouble);
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
  public void insertPermitTransaction(elevServiceContractor cont) throws SQLException, Exception 
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
       pstmt.setDouble(2,cont.getContFee());
       pstmt.setString(3,"Elevator Contractor Certificate Fee: " +cont.getLicNumber()+":"+cont.getContLastName()+" CONFIRMATION "+cont.getReceiptId());
       pstmt.setInt(4,cont.getReceiptId());
       pstmt.setString(5,cont.getLicNumber());
       pstmt.setString(6,cont.getContLastName());
       pstmt.setString(7,null);
       pstmt.setInt(8,0);
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
  public List selectFileList(String permitIdNumber) throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(elevatorSQL.SELECT_DOCUMENT_NAMES_CONT_ALL);
    pstmt.clearParameters();
    if(permitIdNumber != null) {
      pstmt.setString(1,permitIdNumber);
    }
    rs = pstmt.executeQuery();
    while(rs.next()) 
    {
        FileNames names = new FileNames();
      names.setFileName(rs.getString(1));
      names.setFileType(rs.getString(2));
      names.setFileDate(rs.getDate(3));
      names.setFileLoadedBy(rs.getString(4));
      names.setFileId(rs.getInt(5));
      list.add(names);
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
