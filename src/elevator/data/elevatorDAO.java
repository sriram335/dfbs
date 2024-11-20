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

import planReview.data.PlanReviewSQL;

import ust.data.ustSQL;

public class elevatorDAO extends HsDAO{
    public elevatorDAO() {
    }
   public static List selectElevatorList(Connection conn,int ownerId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_LIST_BY_OWNER);
        pstmt.clearParameters();
          pstmt.setInt(1,ownerId);
        rs = pstmt.executeQuery();
        while(rs.next()) 
        { 
        
          elevator elev = new elevator();
            elev.setAccidentDate(rs.getDate(33));
            elev.setPermitDate(rs.getDate(26));
            elev.setYearInstalled(rs.getDate(34));
            elev.setAdoptedCode(rs.getString(25));
            elev.setContactSpeed(rs.getString(3));
            elev.setContractNumber(rs.getString(15));
            elev.setDeviceCapacity(rs.getString(2));
            elev.setElevStatus(rs.getString(31));
            elev.setFloors(rs.getString(36));
            elev.setLocAddress1(rs.getString(17));
            elev.setLocAddress2(rs.getString(18));
            elev.setLocCity(rs.getString(19));
            elev.setLocCounty(rs.getString(4));
            elev.setLocState(rs.getString(20));
            elev.setLocUserEmail(rs.getString(37));
            elev.setLocUserName(rs.getString(24));
            elev.setLocUserPhone(rs.getString(27));
            elev.setLocZip(rs.getString(21));
            elev.setLocZip2(rs.getString(22));
            elev.setMailAddress1(rs.getString(6));
            elev.setMailAddress2(rs.getString(7));
            elev.setMailCity(rs.getString(8));
            elev.setMailName(rs.getString(5));
            elev.setMailState(rs.getString(9));
            elev.setMailZip(rs.getString(10));
            elev.setMailZip2(rs.getString(11));
            elev.setNumberOpenings(rs.getString(12));
            elev.setOccCode(rs.getString(35));
            elev.setPlatformSize(rs.getString(14));
            elev.setReasonInactive(rs.getString(23));
            elev.setStateNumber(rs.getString(1));
            elev.setTotalTravel(rs.getString(13));
            elev.setTypeControl(rs.getString(16));
            elev.setAccidentNumber(rs.getInt(32));
            elev.setContractorId(rs.getInt(28));
            elev.setDeviceId(rs.getInt(29));
            elev.setOwnerId(rs.getInt(30));
          selectElevatorInvoiceDate(elev,conn);
          list.add(elev);
        }
        return list;
      }
      finally 
      {
       try {
         
          rs.close();
          pstmt.close();
        } catch (Exception e) {}
      }
    }
    public elevator selectElevator(String stateNumber) throws SQLException, Exception 
    {
    
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      
      try 
      {
     
        conn = getConnection();
        pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_BY_STATE_NUMBER);
        pstmt.clearParameters();
        pstmt.setString(1,stateNumber);
        rs = pstmt.executeQuery();
        elevator elev = new elevator();
        while(rs.next()) 
        {   elev.setAccidentDate(rs.getDate(33));
            elev.setPermitDate(rs.getDate(26));
            elev.setYearInstalled(rs.getDate(34));
            elev.setAdoptedCode(rs.getString(25));
            elev.setContactSpeed(rs.getString(3));
            elev.setContractNumber(rs.getString(15));
            elev.setDeviceCapacity(rs.getString(2));
            elev.setElevStatus(rs.getString(31));
            elev.setFloors(rs.getString(36));
            elev.setLocAddress1(rs.getString(17));
            elev.setLocAddress2(rs.getString(18));
            elev.setLocCity(rs.getString(19));
            elev.setLocCounty(rs.getString(4));
            elev.setLocState(rs.getString(20));
            elev.setLocUserEmail(rs.getString(37));
            elev.setLocUserName(rs.getString(24));
            elev.setLocUserPhone(rs.getString(27));
            elev.setLocZip(rs.getString(21));
            elev.setLocZip2(rs.getString(22));
            elev.setMailAddress1(rs.getString(6));
            elev.setMailAddress2(rs.getString(7));
            elev.setMailCity(rs.getString(8));
            elev.setMailName(rs.getString(5));
            elev.setMailState(rs.getString(9));
            elev.setMailZip(rs.getString(10));
            elev.setMailZip2(rs.getString(11));
            elev.setNumberOpenings(rs.getString(12));
            elev.setOccCode(rs.getString(35));
            elev.setPlatformSize(rs.getString(14));
            elev.setReasonInactive(rs.getString(23));
            elev.setStateNumber(rs.getString(1));
            elev.setTotalTravel(rs.getString(13));
            elev.setTypeControl(rs.getString(16));
            elev.setAccidentNumber(rs.getInt(32));
            elev.setContractorId(rs.getInt(28));
            elev.setDeviceId(rs.getInt(29));
            elev.setOwnerId(rs.getInt(30));
            elev.setElevInspections(elevInspectionDAO.selectElevInspectionList(conn, stateNumber));
        }
        return elev;
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
    public void insertElevator(elevator elev) throws SQLException, Exception 
    {
    
           Connection conn = null;
           PreparedStatement pstmt = null;
           PreparedStatement pstmt1 = null;
        try 
     {
           conn = getConnection();
           conn.setAutoCommit(true);
          
               pstmt = conn.prepareStatement(elevatorSQL.INSERT_ELEVATOR);
           pstmt.clearParameters();
         pstmt.setString(33,elev.getAccidentDateString());
         pstmt.setString(26,elev.getPermitDateString());
         pstmt.setString(25,elev.getAdoptedCode());
         pstmt.setString(3,elev.getContactSpeed());
         pstmt.setString(15,elev.getContractNumber());
         pstmt.setString(2,elev.getDeviceCapacity());
         pstmt.setString(31,"A");
         pstmt.setString(35,elev.getFloors());
         pstmt.setString(17,elev.getLocAddress1());
         pstmt.setString(18,elev.getLocAddress2());
         pstmt.setString(19,elev.getLocCity());
         pstmt.setString(4,elev.getLocCounty());
         pstmt.setString(20,elev.getLocState());
         pstmt.setString(36,elev.getLocUserEmail());
         pstmt.setString(24,elev.getLocUserName());
         pstmt.setString(27,elev.getLocUserPhone());
         pstmt.setString(21,elev.getLocZip());
         pstmt.setString(22,elev.getLocZip2());
         pstmt.setString(6,elev.getMailAddress1());
         pstmt.setString(7,elev.getMailAddress2());
         pstmt.setString(8,elev.getMailCity());
         pstmt.setString(5,elev.getMailName());
         pstmt.setString(9,elev.getMailState());
         pstmt.setString(10,elev.getMailZip());
         pstmt.setString(11,elev.getMailZip2());
         pstmt.setString(12,elev.getNumberOpenings());
         pstmt.setString(34,elev.getOccCode());
         pstmt.setString(14,elev.getPlatformSize());
         pstmt.setString(23,elev.getReasonInactive());
         String newStateNumber=this.createNewStateNumber();
         if (newStateNumber ==null ||newStateNumber.equals("") ||newStateNumber.length() <4)
         {
           int id = this.getId(conn,"select ESTATE_NUMBER.nextval from dual");
           elev.setStateNumber(Integer.toString(id));
           pstmt.setString(1,elev.getStateNumber());
         }
        else {
           pstmt.setString(1,newStateNumber);
           elev.setStateNumber(newStateNumber);
         }
         pstmt.setString(13,elev.getTotalTravel());
         pstmt.setString(16,elev.getTypeControl());
         pstmt.setInt(32,elev.getAccidentNumber());
         pstmt.setInt(28,elev.getContractorId());
         pstmt.setInt(29,elev.getDeviceId());
         pstmt.setInt(30,elev.getOwnerId());
           pstmt.execute();
       pstmt1 = conn.prepareStatement(ustSQL.UPDATE_DOCUMENT_NAME);
       pstmt1.clearParameters();
       pstmt1.setString(1,elev.getStateNumber());
       pstmt1.setString(2,elev.getElevApplicationKey());
       pstmt1.setString(3,"Elevator");
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
         pstmt1.close();
         pstmt.close();
       } catch (Exception e) {}
     }
    } 
  public void updateElevator(elevator elev) throws SQLException, Exception 
  {
  
         Connection conn = null;
         PreparedStatement pstmt = null;
      try 
   {
         conn = getConnection();
         conn.setAutoCommit(true);
             pstmt = conn.prepareStatement(elevatorSQL.UPDATE_ELEVATOR);
         pstmt.clearParameters();
     
       pstmt.setString(33,elev.getAccidentDateString());
       pstmt.setString(26,elev.getPermitDateString());
       pstmt.setString(34,elev.getYearInstalledString());
       pstmt.setString(25,elev.getAdoptedCode());
       pstmt.setString(3,elev.getContactSpeed());
       pstmt.setString(15,elev.getContractNumber());
       pstmt.setString(2,elev.getDeviceCapacity());
       pstmt.setString(31,elev.getElevStatus());
       pstmt.setString(36,elev.getFloors());
       pstmt.setString(17,elev.getLocAddress1());
       pstmt.setString(18,elev.getLocAddress2());
       pstmt.setString(19,elev.getLocCity());
       pstmt.setString(4,elev.getLocCounty());
       pstmt.setString(20,elev.getLocState());
       pstmt.setString(1,elev.getLocUserEmail());
       pstmt.setString(24,elev.getLocUserName());
       pstmt.setString(27,elev.getLocUserPhone());
       pstmt.setString(21,elev.getLocZip());
       pstmt.setString(22,elev.getLocZip2());
       pstmt.setString(6,elev.getMailAddress1());
       pstmt.setString(7,elev.getMailAddress2());
       pstmt.setString(8,elev.getMailCity());
       pstmt.setString(5,elev.getMailName());
       pstmt.setString(9,elev.getMailState());
       pstmt.setString(10,elev.getMailZip());
       pstmt.setString(11,elev.getMailZip2());
       pstmt.setString(12,elev.getNumberOpenings());
       pstmt.setString(35,elev.getOccCode());
       pstmt.setString(14,elev.getPlatformSize());
       pstmt.setString(23,elev.getReasonInactive());
       pstmt.setString(37,elev.getStateNumber());
       pstmt.setString(13,elev.getTotalTravel());
       pstmt.setString(16,elev.getTypeControl());
       pstmt.setInt(32,elev.getAccidentNumber());
       pstmt.setInt(28,elev.getContractorId());
       pstmt.setInt(29,elev.getDeviceId());
       pstmt.setInt(30,elev.getOwnerId());
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
  public static elevator selectElevatorInvoiceDate(elevator elev, Connection conn) throws SQLException, Exception 
  {
  
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_INVOICE_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setInvoiceDate(rs.getDate(1));
          
      }
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_5YEAR_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setYear5TestDate(rs.getDate(1));
          
      }
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_1YEAR_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setYear1TestDate(rs.getDate(1));
          
      }
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_ANNUAL_PERMIT_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setPermitDate(rs.getDate(1));
          
      }
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_TEMP_PERMIT_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setYearTempDate(rs.getDate(1));
          
      }
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_INSTALL_PERMIT_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setYearInstallDate(rs.getDate(1));
          
      }
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_ELEVATOR_PAID_DATE);
      pstmt.clearParameters();
      pstmt.setString(1,elev.getStateNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   elev.setYearPaidDate(rs.getDate(1));
          
      }
       return elev;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void  uploadFile(FormFile oneFile,String stateNumber,String userEmail,String uploadType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,elevatorSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(elevatorSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       if (userEmail== null ||userEmail.equals(""))
       {
       pstmt.setString(5,"web");
       pstmt.setString(8,"");
       }
       else {
         pstmt.setString(5,userEmail);
         pstmt.setString(8,"ElevatorTest");
       }
      if (uploadType== null ||uploadType.equals("")) 
      {
      pstmt.setString(7,"Elevator");
      }
        else {
        pstmt.setString(7,"ElevatorCont");
      }
       pstmt.setString(6,stateNumber);
       
       pstmt.execute();
       conn.commit();
      
 
    }
    finally 
    {
     try {conn.close();
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
      pstmt = conn.prepareStatement(elevatorSQL.SELECT_DOCUMENT_NAMES_ALL);
      pstmt.clearParameters();
      if(permitIdNumber != null) {
        pstmt.setString(1,permitIdNumber);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { FileNames names = new FileNames();
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
  public List selectFileListTests() throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  {
  
    conn = getConnection();
    pstmt = conn.prepareStatement(elevatorSQL.SELECT_DOCUMENT_NAMES_TESTS);
    pstmt.clearParameters();
   
    rs = pstmt.executeQuery();
    while(rs.next()) 
    { FileNames names = new FileNames();
      names.setFileName(rs.getString(1));
      names.setFileType(rs.getString(2));
      names.setFileDate(rs.getDate(3));
      names.setFileLoadedBy(rs.getString(4));
      names.setFileId(rs.getInt(5));
      names.setFileConnector(rs.getString(6));
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
 public int downLoad(OutputStream os,int fileId)  throws SQLException, Exception 
 {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = elevatorSQL.SELECT_DOCUMENT; 
   conn = getConnection(); 
   pstmt = conn.prepareStatement(sql);
    if(fileId != 0) {
        pstmt.setInt(1,fileId);
      }
   rs = pstmt.executeQuery(); 
    while(rs.next()) 
   {
   java.sql.Blob blob =  rs.getBlob(1);
   InputStream is = blob.getBinaryStream();
   byte[] buf = new byte[1024];
   int len = -1; 
   while ( (len=is.read(buf,0,1024)) != -1)
   {    os.write(buf,0,len);
        len_total += len; 
   } 
     is.close(); 
   
   }
    return len_total;
   }
   
 
   finally
   { 
     try {
       
        conn.close();
        rs.close();
        pstmt.close();
      } 
     
      catch (Exception e){}
   }
   
  
  } 
  public String createNewStateNumber () throws SQLException, Exception 
  {
   Connection conn = null;
  CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
     proc = conn.prepareCall("{ call CREATE_NEW_ELEV_NUMBER(?) }");
     proc.registerOutParameter(1,java.sql.Types.VARCHAR);
     proc.execute();
     return(proc.getString(1));
     
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
  public void  updateFileStatus(int fileId ) throws SQLException, Exception
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  try
  {
  conn = getConnection();
  pstmt = conn.prepareStatement(elevatorSQL.UPDATE_DOCUMENT_STATUS);
  pstmt.clearParameters();
  pstmt.setInt(1,fileId);
  pstmt.execute();
  
  }
  finally
  {
  try {conn.close();
  pstmt.close();
  } catch (Exception e) {}
  }
  }
}
