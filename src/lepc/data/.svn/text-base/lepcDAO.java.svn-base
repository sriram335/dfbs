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

import idhsInspections.data.InspectionsSQL;

import java.io.InputStream;
import java.io.OutputStream;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;

import planReview.data.PlanReviewSQL;


public class lepcDAO extends HsDAO{
    public lepcDAO() {
       
    }
  public int insertLepc(LepcYear lepc) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
    try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,lepcSQL.SELECT_NEXT_LEPC_ID );
     pstmt = conn.prepareStatement(lepcSQL.INSERT_LEPC_YEAR);
     pstmt.clearParameters();
     pstmt.setString(5,lepc.getLepcApprovedBy());
     pstmt.setString(6,lepc.getLepcApprovedDateString());
     pstmt.setString(2,lepc.getLepcCounty());
     pstmt.setString(4,lepc.getLepcStatus());
     pstmt.setString(3,lepc.getLepcYearString());
     pstmt.setInt(1,id);
     pstmt.setString(7,lepc.getLepcNotes());
     lepc.setLepcId(id);
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
   public LepcYear selectLepcYear(int lepcId) throws SQLException, Exception 
  {
  
   LepcYear lepc = new LepcYear();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_YEAR_BY_ID );
     pstmt.clearParameters();
     pstmt.setInt(1,lepcId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     { lepc.setLepcApprovedBy(rs.getString(5));
        lepc.setLepcApprovedDate(rs.getDate(6));
        lepc.setLepcCounty(rs.getString(2));
        lepc.setLepcStatus(rs.getString(4));
        lepc.setLepcYear(rs.getDate(3));
        lepc.setLepcId(rs.getInt(1));
        lepc.setLepcNotes(rs.getString(7));
   }
    return lepc;
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
  public LepcYear selectLepcYear(String lepcYear,String lepcCounty) throws SQLException, Exception 
  {
  
  LepcYear lepc = new LepcYear();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_YEAR_BY_YEAR );
    pstmt.clearParameters();
    pstmt.setString(1,lepcYear);
    pstmt.setString(2,lepcCounty);  
    rs = pstmt.executeQuery();
    if(rs.next()) 
    { lepc.setLepcApprovedBy(rs.getString(5));
       lepc.setLepcApprovedDate(rs.getDate(6));
       lepc.setLepcCounty(rs.getString(2));
       lepc.setLepcStatus(rs.getString(4));
       lepc.setLepcYear(rs.getDate(3));
       lepc.setLepcId(rs.getInt(1));
       lepc.setLepcNotes(rs.getString(7));
  }
   return lepc;
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
  public void updateLepcYear(LepcYear lepc) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_YEAR);
     pstmt.clearParameters();
     pstmt.setString(5,lepc.getLepcApprovedBy());
     pstmt.setString(1,lepc.getLepcApprovedDateString());
     pstmt.setString(2,lepc.getLepcCounty());
     pstmt.setString(4,lepc.getLepcStatus());
     pstmt.setString(3,lepc.getLepcYearString());
     pstmt.setInt(7,lepc.getLepcId());
     pstmt.setString(6,lepc.getLepcNotes());
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
  public void updateLepcYearNotes(LepcYear lepc) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_LEPC_YEAR_NOTES);
     pstmt.clearParameters();
     
     pstmt.setInt(2,lepc.getLepcId());
     pstmt.setString(1,lepc.getLepcNotes());
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
  public List selectLepcList(String psql,String byYear) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(psql);
     pstmt.clearParameters();
     if(byYear != null) {
       pstmt.setString(1,byYear);
     }
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {
     
       LepcYear lepc = new LepcYear();
       
              lepc.setLepcApprovedBy(rs.getString(5));
               lepc.setLepcApprovedDateString(rs.getString(6));
               lepc.setLepcCounty(rs.getString(2));
               lepc.setLepcStatus(rs.getString(4));
               lepc.setLepcYearString(rs.getString(3));
               lepc.setLepcId(rs.getInt(1));
         
       list.add(lepc);
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
  
  public List selectCountyList() throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   String countyName = "";
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_COUNTY_NAMES);
     pstmt.clearParameters();
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {
       countyName= rs.getString(1);
       list.add(countyName);
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
  
  public List selectYearList(String countyName) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_YEARS);
     pstmt.clearParameters();
     if(countyName != null) {
       pstmt.setString(1,countyName);
     }
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {
       LepcYear lepc = new LepcYear();
              lepc.setLepcId((rs.getInt(2)));
              lepc.setLepcStatus((rs.getString(1)));  
              lepc.setLepcApprovedBy((rs.getString(3)));  
              list.add(lepc);
         List files= this.selectLepcDocListTier2(countyName,lepc.getLepcStatus());
         lepc.setLepcCountyPlan(files);
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
  public List selectLepcDocList() throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_DOC_LIST);
     pstmt.clearParameters();
    
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { FileNames fileName = new FileNames();
       fileName.setFileId(Integer.parseInt(rs.getString(2)));
       fileName.setFileName((rs.getString(1)));  
       list.add(fileName);
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
  public List selectLepcDocListCurrent(int lepcId,String docType) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_DOC_LIST_CURRENT);
     pstmt.clearParameters();
     pstmt.setInt(1,lepcId);
       pstmt.setString(2,docType);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { FileNames fileName = new FileNames();
       fileName.setFileId(Integer.parseInt(rs.getString(1)));
       fileName.setFileName((rs.getString(2)));  
       fileName.setFileType(rs.getString(3));
       fileName.setFilePlanType(rs.getString(4));  
       fileName.setFileDate(rs.getDate(5));
       fileName.setFileLoadedBy(rs.getString(6));  
       list.add(fileName);
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
  public void  uploadFile(FormFile oneFile,String lepcId,String userId, String planType) throws SQLException, Exception
   { 
     Connection conn = null;
     PreparedStatement pstmt = null;
     try 
     
     {  conn = getConnection();
        int id = this.getId(conn,PlanReviewSQL.SELECT_DOCUMENT_ID);
        byte[] byteArray=oneFile.getFileData();
        pstmt =conn.prepareStatement(PlanReviewSQL.UPLOAD_DOCUMENT);
        pstmt.setBytes(4,byteArray);
        pstmt.setInt(1,id);
        pstmt.setString(2,oneFile.getFileName());
        pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
        pstmt.setString(5,userId);
        pstmt.setString(6,lepcId);
        pstmt.setString(7,"LEPC");
        pstmt.setString(8,planType);
        pstmt.setString(9,"Pending");
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
  public int downLoad(OutputStream os,int fileId)  throws SQLException, Exception 
  {  Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
      int len_total = 0; 
    try {
    String sql = PlanReviewSQL.SELECT_DOCUMENT; 
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
  public void updateDfbsDocuments(int lepcId,String approvalType) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(lepcSQL.UPDATE_DFBS_DOCUMENTS);
     pstmt.clearParameters();
    
     pstmt.setInt(2,lepcId);
     if (approvalType !=null && approvalType.length() !=0) {
       pstmt.setString(1,approvalType);
     }
    else {
       pstmt.setString(1,"Approved");
     }
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
  public List selectLepcContacts(String county) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_USERS_BY_LEPC_COUNTY);
     pstmt.clearParameters();
     if(county != null) {
       pstmt.setString(1,county);
     }
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {
     
      DfbsContact person = new DfbsContact();
       
       person.setPersonId(rs.getInt(1));
       person.setPersonLastName(rs.getString(3));
       person.setPersonFirstName(rs.getString(4));
       person.setPersonEmail(rs.getString(7));
      
         
       list.add(person);
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
  public  void deleteFile(int fileId)   throws SQLException, Exception 
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  
  try
  { conn = getConnection();
   pstmt = conn.prepareStatement(InspectionsSQL.DELETE_DOCUMENT);
   pstmt.clearParameters();
    pstmt.setInt(1,fileId);
   pstmt.execute();
  }
  finally
  {
  try {
     conn.close();
     pstmt.close();
   } catch (Exception e) {}
  }
  }
  
  public List selectLepcDocListTier2(String countyName,String lepcYear) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
  
   try 
   {
      conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_DOC_LIST_TIER2);
     pstmt.clearParameters();
       pstmt.setString(1,countyName);
     pstmt.setString(2,lepcYear);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     { FileNames fileName = new FileNames();
       fileName.setFileId(Integer.parseInt(rs.getString(1)));
       fileName.setFileName((rs.getString(2)));  
       fileName.setFileType(rs.getString(3));
       fileName.setFilePlanType(rs.getString(4));  
       fileName.setFileDate(rs.getDate(5));
       fileName.setFileLoadedBy(rs.getString(6));  
       list.add(fileName);
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
  public List selectLepcCountyYear(String lepcYear,String lepcDocType,String lepcStatus) throws SQLException, Exception 
  {
  
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
    List list = new ArrayList();  
    String lsql = lepcSQL.SELECT_LEPC_ALL_YEAR;
  try 
  {
    
    conn = getConnection();
      if ((lepcDocType.equals(" Fiscal Report (Due by March 1)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='County Auditor Report' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
        }
        if (lepcDocType.equals(" Fiscal Report (Due by March 1)") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='County Auditor Report' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
          }
      if ((lepcDocType.equals(" Legal Notices (Due by January 31)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Legal Notice' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
        }
        if (lepcDocType.equals(" Legal Notices (Due by January 31)") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Legal Notice' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
          }
      if ((lepcDocType.equals(" Bylaws (Due by December 31)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Bylaws' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
        }
        if (lepcDocType.equals(" Bylaws (Due by December 31)") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Bylaws' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
          }
      if ((lepcDocType.equals(" LEPC Plan Update (Due by December 31)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Plan Updates' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
        }
        if (lepcDocType.equals(" LEPC Plan Update (Due by December 31)") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Plan Updates' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
          }
      if ((lepcDocType.equals(" Meeting Minutes with meeting sign in sheets (Due December 31 )") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Meeting Minutes and Sign-in' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
        }
        if (lepcDocType.equals(" Meeting Minutes with meeting sign in sheets (Due December 31 )") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
                " DOCUMENT_PLAN_TYPE='Meeting Minutes and Sign-in' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
          }
      if ((lepcDocType.equals(" Exercise Report (Due 30 days after exercise and by December 31 )") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND LEPC_ID NOT IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXERCISE_TYPE='Report')";
        }
        if (lepcDocType.equals(" Exercise Report (Due 30 days after exercise and by December 31 )") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXECISE_STATUS='Pending'  AND A.EXERCISE_TYPE='Report')";
          }
      if ((lepcDocType.equals(" Exercise Proposal (Due 30 days prior to exercise)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND LEPC_ID NOT IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXERCISE_TYPE='Exercise')";
        }
        if (lepcDocType.equals(" Exercise Proposal (Due 30 days prior to exercise)") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND LEPC_ID IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXECISE_STATUS='Pending'  AND A.EXERCISE_TYPE='Exercise')";
          }
      if ((lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
        lsql =lsql+" AND LEPC_ID NOT IN (SELECT A.LEPC_ID FROM LEPC_ROSTER A WHERE A.LEPC_ID=LEPC_ID)";
        }
        if (lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members") && lepcStatus.equals("Pending")) {
          lsql =lsql+" AND LEPC_ID IN (SELECT A.LEPC_ID FROM LEPC_ROSTER A WHERE A.LEPC_ID=LEPC_ID AND A.ROSTER_STATUS='Pending')";
          }
      if (lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members") && lepcStatus.equals("Approved")) {
        lsql =lsql+" AND LEPC_ID IN (SELECT A.LEPC_ID FROM LEPC_ROSTER A WHERE A.LEPC_ID=LEPC_ID AND A.ROSTER_STATUS='Approved')";
        }
      lsql = lsql+ " order by LEPC_COUNTY " ; 
      System.out.println("lsql..:"+lsql);
      pstmt = conn.prepareStatement(lsql);
     pstmt.clearParameters();
    pstmt.setString(1,lepcYear);
   rs = pstmt.executeQuery(); 
    while(rs.next()) 
    {   LepcYear lepc = new LepcYear();
        lepc.setLepcApprovedBy(rs.getString(5));
       lepc.setLepcApprovedDate(rs.getDate(6));
       lepc.setLepcCounty(rs.getString(2));
       lepc.setLepcStatus(rs.getString(4));
       lepc.setLepcYear(rs.getDate(3));
       lepc.setLepcId(rs.getInt(1));
       lepc.setLepcNotes(rs.getString(7));
      if (lepcDocType.equals(" Fiscal Report (Due by March 1)")) {
        lepc.setLepcCountyFiscal(this.selectFiscalReportByLepc(lepc.getLepcId()));
        }
      if (lepcDocType.equals(" Exercise Report (Due 30 days after exercise and by December 31 )")) {
       lepc.setLepcCountyExercise(this.selectExerciseByLepc(lepc.getLepcId(), "Exercise"));
        }
      if (lepcDocType.equals(" Exercise Proposal (Due 30 days prior to exercise)")) { 
       lepc.setLepcCountyProposal(this.selectExerciseByLepc(lepc.getLepcId(), "Proposal"));  
       }
    if (lepcDocType.equals(" Legal Notices (Due by January 31)")) { 
     lepc.setLepcCountyLegal(selectLepcDocListCurrent(lepc.getLepcId(),"Legal Notice"));  
     }
    if (lepcDocType.equals(" Bylaws (Due by December 31)")) { 
     lepc.setLepcCountyLaws(selectLepcDocListCurrent(lepc.getLepcId(),"Bylaws"));  
     }
    if (lepcDocType.equals(" LEPC Plan Update (Due by December 31)")) { 
     lepc.setLepcCountyPlan(selectLepcDocListCurrent(lepc.getLepcId(),"Plan Updates"));  
     }
    if (lepcDocType.equals(" Meeting Minutes with meeting sign in sheets (Due December 31 )")) { 
     lepc.setLepcCountyMinutes(selectLepcDocListCurrent(lepc.getLepcId(),"Meeting Minutes and Sign-in"));  
     }
    if (lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members")) { 
     lepc.setLepcCountyRoster(this.selectRosterByLepc(lepc.getLepcId()));;  
     }
        
       list.add(lepc);
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
  public List selectExerciseByLepc(int lepcId,String reportType) throws SQLException, Exception 
  {
  
  
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
    List list = new ArrayList();    
  try 
  {
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_EXERCISE_BY_LEPC);
    pstmt.clearParameters();
    pstmt.setInt(1,lepcId);
    pstmt.setString(2,reportType);
    rs = pstmt.executeQuery();
    while(rs.next()) 
    {  lepcExercise exercise = new lepcExercise();
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
  public List selectFiscalReportByLepc(int lepcId) throws SQLException, Exception 
  {
  
  fiscalReport fiscalRep = new fiscalReport();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  List list = new ArrayList();       
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_FISCAL_REPORT_BY_LEPC );
    pstmt.clearParameters();
    pstmt.setInt(1,lepcId);
    rs = pstmt.executeQuery();
   while(rs.next()) 
    { fiscalRep.setRepDate(rs.getDate(5));
       fiscalRep.setReportBy(rs.getString(4));
       fiscalRep.setAcctBalance(rs.getDouble(2));
       fiscalRep.setAcctReceipts(rs.getDouble(3));
       fiscalRep.setFiscalId(rs.getInt(1));
       fiscalRep.setLepcId(rs.getInt(7));
       fiscalRep.setAcctGrants(rs.getDouble(8));
       fiscalRep.setFiscalStatus(rs.getString(6));
       fiscalRep.setInvBalance(rs.getDouble(9));
          fiscalReport currentBalance = new fiscalReport();
          double currentExpenditure = this.FindFiscalBalanceCurrentYear(currentBalance,fiscalRep.getFiscalId(),conn); 
         fiscalRep.setCurAcctBalance(currentBalance.getAcctBalance()); 
         fiscalRep.setCurAcctExps(currentExpenditure);
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
  
  public static double FindFiscalBalanceCurrentYear (fiscalReport fisReport, int fiscalId ,Connection conn ) throws SQLException, Exception 
  {
   CallableStatement proc = null;
   double expenditure =0;
   try 
   {  
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
     } catch (Exception e) {}
   }
  }
  public List selectRosterByLepc(int lepcId) throws SQLException, Exception 
  {
  
  LepcYear lepc = new LepcYear();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
    List list = new ArrayList();   
  try 
  {
    lepcRoster roster = new lepcRoster();
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_ROSTER_BY_LEPC);
    pstmt.clearParameters();
    pstmt.setInt(1,lepcId);
    rs = pstmt.executeQuery();
    while(rs.next()) 
    {  
       roster.setRosterAddBy(rs.getString(18));
       roster.setRosterAddress1(rs.getString(2));
       roster.setRosterAddress2(rs.getString(3));
       roster.setRosterChairman(rs.getString(7));
       roster.setRosterCity(rs.getString(4));
       roster.setRosterDate(rs.getDate(19));
       roster.setRosterEmail(rs.getString(6));
       roster.setRosterEmerCoor(rs.getString(10));
       roster.setRosterFax(rs.getString(13));
       roster.setRosterInfoCoor(rs.getString(8));
       roster.setRosterLocation(rs.getString(11));
       roster.setRosterPhoneAdmin(rs.getString(14));
       roster.setRosterPhoneAlt(rs.getString(15));
       roster.setRosterPhoneEmer(rs.getString(12));
       roster.setRosterPlanCoor(rs.getString(9));
       roster.setRosterStatus(rs.getString(17));
       roster.setRosterZip(rs.getString(5));
       roster.setLepcId(rs.getInt(16));
       roster.setRosterId(rs.getInt(1));
    list.add(roster);
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
  public String selectEmails(String lepcDocType,String lepcStatus,String lepcYear) throws SQLException, Exception 
  {
  
  
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
    String emails ="";
    String lsql =lepcSQL.SELECT_LEPC_EMAILS;
  try 
  {
    conn = getConnection();
    if ((lepcDocType.equals(" Fiscal Report (Due by March 1)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='County Auditor Report' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
      }
      if (lepcDocType.equals(" Fiscal Report (Due by March 1)") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='County Auditor Report' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
        }
    if ((lepcDocType.equals(" Legal Notices (Due by January 31)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Legal Notice' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
      }
      if (lepcDocType.equals(" Legal Notices (Due by January 31)") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Legal Notice' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
        }
    if ((lepcDocType.equals(" Bylaws (Due by December 31)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Bylaws' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
      }
      if (lepcDocType.equals(" Bylaws (Due by December 31)") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Bylaws' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
        }
    if ((lepcDocType.equals(" LEPC Plan Update (Due by December 31)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Plan Updates' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
      }
      if (lepcDocType.equals(" LEPC Plan Update (Due by December 31)") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Plan Updates' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
        }
    if ((lepcDocType.equals(" Meeting Minutes with meeting sign in sheets (Due December 31 )") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND TO_CHAR(LEPC_ID) NOT IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Meeting Minutes and Sign-in' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID))";
      }
      if (lepcDocType.equals(" Meeting Minutes with meeting sign in sheets (Due December 31 )") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT DOCUMENT_CONNECTOR FROM DFBS_DOCUMENTS WHERE  "+
              " DOCUMENT_PLAN_TYPE='Meeting Minutes and Sign-in' AND DOCUMENT_CONNECTOR=TO_CHAR(LEPC_ID) AND GIS_FLAG='Pending')";
        }
    if ((lepcDocType.equals(" Exercise Report (Due 30 days after exercise and by December 31 )") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND LEPC_ID NOT IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXERCISE_TYPE='Report')";
      }
      if (lepcDocType.equals(" Exercise Report (Due 30 days after exercise and by December 31 )") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND TO_CHAR(LEPC_ID) IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXECISE_STATUS='Pending'  AND A.EXERCISE_TYPE='Report')";
        }
    if ((lepcDocType.equals(" Exercise Proposal (Due 30 days prior to exercise)") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND LEPC_ID NOT IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXERCISE_TYPE='Exercise')";
      }
      if (lepcDocType.equals(" Exercise Proposal (Due 30 days prior to exercise)") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND LEPC_ID IN (SELECT A.LEPC_ID FROM LEPC_EXERCISE A WHERE A.LEPC_ID=LEPC_ID AND A.EXECISE_STATUS='Pending'  AND A.EXERCISE_TYPE='Exercise')";
        }
    if ((lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members") ||lepcDocType.equals("All")) && lepcStatus.equals("Not Submitted")) {
      lsql =lsql+" AND LEPC_ID NOT IN (SELECT A.LEPC_ID FROM LEPC_ROSTER A WHERE A.LEPC_ID=LEPC_ID)";
      }
      if (lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members") && lepcStatus.equals("Pending")) {
        lsql =lsql+" AND LEPC_ID IN (SELECT A.LEPC_ID FROM LEPC_ROSTER A WHERE A.LEPC_ID=LEPC_ID AND A.ROSTER_STATUS='Pending')";
        }
    
  /* 
    if (lepcDocType.equals(" Roster (Due by March 1) Note: Ethics training required for all roster members")) {
    lepc.setLepcCountyRoster(this.selectRosterByLepc(lepc.getLepcId()));;
    } */
   
    pstmt = conn.prepareStatement(lsql);
    pstmt.clearParameters();
    /*  pstmt.setInt(1,lepcId);*/
    pstmt.setString(1,lepcYear); 
    rs = pstmt.executeQuery();
    while(rs.next()) 
    {  
        emails=emails+rs.getString(1)+";";
    }
   return emails;
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
  // END
}
