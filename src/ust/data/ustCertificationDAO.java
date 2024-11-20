package ust.data;
import ust.to.*;
import ust.data.*;
import hs.data.*;

import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;
public class ustCertificationDAO extends HsDAO {
    public ustCertificationDAO() {
       
    }
  public void insertUst(ustCertification cert) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    ResultSet rs = null;
    String curYear="";
    try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     pstmt1 = conn.prepareStatement(ustSQL.SELECT_CURRENT_YEAR);
     pstmt1.clearParameters();
     rs = pstmt1.executeQuery();
     if(rs.next()) 
     { 
       curYear=  rs.getString(1);
     }
     pstmt = conn.prepareStatement(ustSQL.INSERT_UST_CERTIFICATION);
     pstmt.setString(12,cert.getExpDateString());
     pstmt.setString(11,cert.getIssueDateString());
     pstmt.setString(13,cert.getMailDateString());
     pstmt.setString(5,cert.getCathode());
     if (cert.getLicState() !=null ||cert.getLicState() !="")
     {
     pstmt.setString(1,"UC"+curYear+cert.getLicState()+Integer.toString(cert.getUstId()));
     cert.setCertNumber("UC"+curYear+cert.getLicState()+Integer.toString(cert.getUstId()));
     }
     else 
     {
           pstmt.setString(1,"UC"+curYear+Integer.toString(cert.getUstId()));   
           cert.setCertNumber("UC"+curYear+Integer.toString(cert.getUstId()));
         }
     pstmt.setString(8,cert.getCertType());
     pstmt.setString(7,cert.getDeccom());
     pstmt.setString(14,cert.getDecomStatus());
     pstmt.setString(10,cert.getEvidenceType());
     pstmt.setString(4,cert.getInstall());
     pstmt.setString(9,cert.getLicState());
     pstmt.setString(3,"3");
     pstmt.setString(6,cert.getTesting());
     pstmt.setString(2,Integer.toString(cert.getUstId()));
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
       pstmt1.close(); 
        rs.close();
     } catch (Exception e) {}
   }
  }
   public ustCertification selectUstCert(int ustId) throws SQLException, Exception 
  {
  
  ustCertification cert = new ustCertification();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
     pstmt = conn.prepareStatement(ustSQL.SELECT_UST_CERT);
     pstmt.clearParameters();
     pstmt.setInt(1,ustId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     { cert.setExpDate(rs.getDate(12));
        cert.setIssueDate(rs.getDate(11));
        cert.setMailDate(rs.getDate(13));
        cert.setCathode(rs.getString(5));
        cert.setCertNumber(rs.getString(1));
        cert.setCertType(rs.getString(8));
        cert.setDeccom(rs.getString(7));
        cert.setDecomStatus(rs.getString(14));
        cert.setEvidenceType(rs.getString(10));
        cert.setInstall(rs.getString(4));
        cert.setLicState(rs.getString(9));
        cert.setStatus(rs.getString(3));
        cert.setTesting(rs.getString(6));
        cert.setUstId(rs.getInt(2));
   }
    return cert;
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
  
  public void updateUst(ustCertification cert) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
    
     pstmt = conn.prepareStatement(ustSQL.UPDATE_UST_CERT);
     pstmt.clearParameters();
     pstmt.setString(12,cert.getExpDateString());
     pstmt.setString(11,cert.getIssueDateString());
     pstmt.setString(13,cert.getMailDateString());
     pstmt.setString(5,cert.getCathode());
     pstmt.setString(1,cert.getCertNumber());
     pstmt.setString(8,cert.getCertType());
     pstmt.setString(7,cert.getDeccom());
     pstmt.setString(2,cert.getDecomStatus());
     pstmt.setString(10,cert.getEvidenceType());
     pstmt.setString(4,cert.getInstall());
     pstmt.setString(9,cert.getLicState());
     pstmt.setString(3,cert.getStatus());
     pstmt.setString(6,cert.getTesting());
     pstmt.setInt(14,cert.getUstId());
     pstmt.execute();
     
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
  public int selectUstFee() throws SQLException, Exception 
  {
  
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  int ustFee =0;   
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(ustSQL.SELECT_UST_FEE);
    pstmt.clearParameters();
    rs = pstmt.executeQuery();
    if(rs.next()) 
    { ustFee =(rs.getInt(1));
       
  }
   return ustFee;
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
