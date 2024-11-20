package planReview.data;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

import idhsInspections.to.searchResults;

import main.to.*;

import org.apache.struts.upload.FormFile;
public class PlanReviewDAO extends HsDAO{
    public PlanReviewDAO() {
    }
    public void  uploadFile(FormFile oneFile,String IdNumber,String userId, String planType,String planPath) throws SQLException, Exception
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
          pstmt.setString(2,planType+oneFile.getFileName());
          pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
          pstmt.setString(5,userId);
          pstmt.setString(6,IdNumber);
          pstmt.setString(7,"planReview");
          pstmt.setString(8,planPath);
          if (planType.equals("FL")) {
            pstmt.setString(9,"GIS");
          }
           else {
            pstmt.setString(9,"");
          }
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

       public List selectFileList(String IdNumber,String IdType,int gisCheck) throws SQLException, Exception 
     {
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       
       try 
       { 
      
         conn = getConnection();
        
        
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_DOCUMENT_NAMES_ALL);
         if (gisCheck>0) {
           pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_DOCUMENT_NAMES_OTHERS);
         }
          pstmt.clearParameters();
        
         if(IdNumber != null) {
           pstmt.setString(1,IdNumber);
           pstmt.setString(2,IdType);
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
           names.setFileConnector(rs.getString(6)); 
           names.setFilePlanType(rs.getString(7)); 
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
      public String  getUploadFileName(String connectorId,String connectorType) throws SQLException, Exception
     {
       ResultSet rs = null;
       Connection conn = null;
       PreparedStatement pstmt = null;
      
       String fileName="NoName";
       try 
       {  conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_DOCUMENT_NAME);
         pstmt.clearParameters();
         pstmt.setString(1,connectorId);
         pstmt.setString(2,connectorType);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
           fileName=(rs.getString(1));
        
         }
       
         return fileName;
       }
       finally 
       {
        try {conn.close();
           rs.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     }
        public int  checkPlanMarkups(int fileId,String connString) throws SQLException, Exception
     {
       ResultSet rs = null;
       Connection conn = null;
       PreparedStatement pstmt = null;
       int correctionId =0;
       try 
       {  conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_PLANS_CHECK);
         pstmt.clearParameters();
         pstmt.setInt(1,fileId);
         pstmt.setString(2,connString);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
           correctionId=(rs.getInt(1));
        
         }
       
         return correctionId;
       }
       finally 
       {
        try {conn.close();
           rs.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     }
        public List selectFileListMarkups(int correctionId) throws SQLException, Exception 
     {
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       
       try 
       { 
      
         conn = getConnection();
        
        
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_PLANS_FOR_MARKUPS);
          pstmt.clearParameters();
        
         if(correctionId >0) {
           pstmt.setInt(1,correctionId);
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
           names.setFileConnector(rs.getString(6)); 
           names.setFilePlanType(rs.getString(7));  
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
     public void  getConnectionInfo(ApplicationSecurity security,String sudoPassword) throws SQLException, Exception
     {
       ResultSet rs = null;
       Connection conn = null;
       PreparedStatement pstmt = null;
       String userLevel="";
       try 
       {  conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_DFBS_CONNECT);
         pstmt.clearParameters();
         pstmt.setString(1,sudoPassword);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
           security.setUserId(rs.getString(1));
           security.setUserPassword(rs.getString(2));
         }
       
         
       }
       finally 
       {
        try { conn.close();
           rs.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     }
     
      public void  resetConnectionInfo(String sudoPassword) throws SQLException, Exception
     { 
       Connection conn = null;
       PreparedStatement pstmt = null;
       try 
       {  
         conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.DELETE_DFBS_CONNECT);
         pstmt.clearParameters();
         pstmt.setString(1,sudoPassword);
         pstmt.execute();
    
       }
       finally 
       {
        try {conn.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     }
          public List selectReviewerEmails(int projectId) throws SQLException, Exception 
     {
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       ResultSet rs1 = null;
       PreparedStatement pstmt = null;
       PreparedStatement pstmt1 = null;
       try 
       { 
      
         conn = getConnection();
        
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_PLAN_REVIEWER_EMAIL);
         pstmt1 = conn.prepareStatement(PlanReviewSQL.SELECT_PLAN_CORRECTION_EMAIL);
          pstmt.clearParameters();
         pstmt1.clearParameters();
         if( projectId >0) {
           pstmt1.setInt(1, projectId);
         }
         rs1 = pstmt1.executeQuery();
         while(rs1.next()) 
         { 
             list.add(rs1.getString(1));
             
         }
        
           if( projectId >0) {
             pstmt.setInt(1, projectId);
           }
           rs = pstmt.executeQuery();
           while(rs.next()) 
           { 
               list.add(rs.getString(1));
               
           }
         
         return list;
        
       }
       finally 
       {
        try {
           conn.close();
           rs.close();
           pstmt1.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     }
        public int selectPlanCount(int projectId) throws SQLException, Exception 
     {
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       int planCount =0;
       try 
       { 
      
         conn = getConnection();
        
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_DOCUMENT_COUNT);
          pstmt.clearParameters();
        
         if( projectId >0) {
           pstmt.setInt(1, projectId);
         }
         rs = pstmt.executeQuery();
         while(rs.next()) 
         { 
             planCount =rs.getInt(1);
         }
         return planCount;
        
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
      public int selectFormFiledCount(int projectId) throws SQLException, Exception 
      {
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      int planCount =0;
      try
      {
      
       conn = getConnection();
      
       pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_FORM_FILED_COUNT);
        pstmt.clearParameters();
      
       if( projectId >0) {
         pstmt.setInt(1, projectId);
       }
       rs = pstmt.executeQuery();
       while(rs.next()) 
       { 
           planCount =rs.getInt(1);
       }
       return planCount;
      
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
         public void  updateFileName(int fileId , String fileName) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {  
      conn = getConnection();
      pstmt = conn.prepareStatement(PlanReviewSQL.UPDATE_DOCUMENT_NAME);
      pstmt.clearParameters();
      pstmt.setInt(2,fileId);
      pstmt.setString(1,fileName);
      pstmt.execute();
 
    }
    finally 
    {
     try {conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void insertPlanFormFiled (int projectNumber) throws SQLException, Exception 
          {
          
          Connection conn = null;
          PreparedStatement pstmt = null;
             
          try 
          {
            
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(PlanReviewSQL.INSERT_PLAN_FORM_FILED);
            pstmt.setInt(1,projectNumber);
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
    public  void deleteFile(int fileId)   throws SQLException, Exception 
    {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try
    { conn = getConnection();
     pstmt = conn.prepareStatement(PlanReviewSQL.DELETE_DOCUMENT);
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
      public int checkPrivateSecurity(String pUser,int projNumber) throws SQLException, Exception 
      {
        Connection conn = null;
        CallableStatement proc = null;
        int priCheck;
        try 
        {  
          conn = getConnection();
          conn.setAutoCommit(false);
           proc = conn.prepareCall("{ call CHECK_PLAN_PRIVATE_SECURITY(?,?,?) }");
           proc.setString(1,pUser);
           proc.setInt(2,projNumber);
           proc.registerOutParameter(3,java.sql.Types.INTEGER);
           proc.execute();
           priCheck=proc.getInt(3);
          return( priCheck);
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
    
      public int  checkListValidation(int fileId) throws SQLException, Exception
     {
       ResultSet rs = null;
       Connection conn = null;
       PreparedStatement pstmt = null;
      
       int checkListCheck=0;
       try 
       {  conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_CHECK_LIST_VALIDATION);
         pstmt.clearParameters();
         pstmt.setInt(1,fileId);
        rs = pstmt.executeQuery();
         if(rs.next()) 
         {
           checkListCheck=(rs.getInt(1));
        
         }
       
         return checkListCheck;
       }
       finally 
       {
        try {conn.close();
           rs.close();
           pstmt.close();
         } catch (Exception e) {}
       }
     }
}