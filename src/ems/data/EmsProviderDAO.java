package ems.data;
import ems.to.*;
import ems.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

public class EmsProviderDAO extends HsDAO
{
  public EmsProviderDAO()
  {
  
  }
   
   public List selectProviderList(String sql,String param) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.clearParameters();
      if(param != null) {
        pstmt.setString(1,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsProvider provider = new EmsProvider();
         provider.setProviderId(rs.getInt(1));
         provider.setProviderName(rs.getString(2));
         provider.setProviderAddress1(rs.getString(3));
         provider.setProviderAddress2(rs.getString(4));
         provider.setProviderCity(rs.getString(5));
         provider.setProviderState(rs.getString(6));
         provider.setProviderZip(rs.getString(7));
         provider.setProviderCounty(rs.getString(8));
         provider.setProviderCertNumber(rs.getString(9));
        list.add(provider);
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
  public EmsProvider selectProvider(int providerId) throws SQLException, Exception 
  {
     EmsProvider provider = new EmsProvider();
    Connection conn = null;
     
    try 
    {
      conn = getConnection();
      provider = selectProvider(conn,providerId);
      return provider;
    }
    finally 
    {
     try {
        conn.close();
       } catch (Exception e) {}
    }
  }
   public EmsProvider selectProvider(Connection conn,int providerId) throws SQLException, Exception 
  {
    EmsProvider provider = new EmsProvider();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_PROVIDER);
      pstmt.clearParameters();
      pstmt.setInt(1,providerId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
           provider.setProviderId(rs.getInt(1));
           provider.setProviderName(rs.getString(12));
           provider.setProviderAddress1(rs.getString(13));
           provider.setProviderAddress2(rs.getString(14));
           provider.setProviderCity(rs.getString(15));
           provider.setProviderState(rs.getString(16));
           provider.setProviderZip(rs.getString(17));
           
           provider.setProviderCounty(rs.getString(7));
           provider.setProviderZip2(rs.getString(18));
           provider.setProviderType(rs.getString(26));
          provider.setProviderCertNumber(rs.getString(6));
          provider.setProviderDistrict(rs.getString(23));
          provider.setProviderEmail(rs.getString(27));
          provider.setProviderPhone(rs.getString(5));
          provider.setProviderFax(rs.getString(28));
          provider.setProviderEmergencyPhone(rs.getString(24));
          provider.setProviderAppliedDate(rs.getDate(25));
          provider.setProviderExpDate(rs.getDate(11));
          provider.setProviderComments(rs.getString(29));
          provider.setProviderInsCarrier(rs.getString(44));
          provider.setProviderInsEffDate(rs.getDate(42));
          provider.setProviderInsExpDate(rs.getDate(43));
          provider.setProviderInsPolNumber(rs.getString(41));
          provider.setProviderCertRequired(rs.getString(8));
          provider.setProviderApplicationDate(rs.getDate(25));
          provider.setProviderReviewDate(rs.getDate(2));
          provider.setProviderApprovedDate(rs.getDate(3));
          provider.setProviderUpdateDate(rs.getDate(39));
          provider.setProviderCertDate(rs.getDate(10));
          provider.setProviderCertReqDate(rs.getDate(34));
          provider.setProviderReapplyDate(rs.getDate(19));
          provider.setProviderDataCollection(rs.getString(35));
          provider.setProviderVisitDate(rs.getDate(20));
          provider.setProviderAuditDate(rs.getDate(21));
          provider.setProviderIniCertDate(rs.getDate(33));
          provider.setProviderIniDfbrDate(rs.getDate(31));
          provider.setProviderIniMeetingDate(rs.getDate(9));
          provider.setProviderIniIntDate(rs.getDate(9));
          provider.setProviderIniAemtDate(rs.getDate(30));
          provider.setProviderIniParaDate(rs.getDate(32));
          provider.setProviderTerminationDate(rs.getDate(38));
          provider.setProviderReapplyExpDate(rs.getDate(21));
          provider.setProviderTactExpDate(rs.getDate(36));
          provider.setProviderIhernExpDate(rs.getDate(37));

      }
      return provider;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateProvider(EmsProvider provider) throws SQLException, Exception 
  {
 
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
         
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_PROVIDER);
          pstmt.clearParameters();
          pstmt.setString(1,provider.getProviderReviewDateString());
          pstmt.setString(2,provider.getProviderApprovedDateString());
          pstmt.setString(3,provider.getProviderAuditDateString());
          pstmt.setString(4,provider.getProviderPhone());
          pstmt.setString(5,provider.getProviderCertNumber());
          pstmt.setString(6,provider.getProviderCounty());
          pstmt.setString(7,provider.getProviderCertRequired());
          pstmt.setString(8,provider.getProviderIniMeetingDateString());
          pstmt.setString(9,provider.getProviderCertDateString());
          pstmt.setString(10,provider.getProviderExpDateString());
          pstmt.setString(11,provider.getProviderName());
          pstmt.setString(12,provider.getProviderAddress1());
          pstmt.setString(13,provider.getProviderAddress2());
          pstmt.setString(14,provider.getProviderCity());
          pstmt.setString(15,provider.getProviderState());
          pstmt.setString(16,provider.getProviderZip());
          pstmt.setString(17,provider.getProviderZip2());
          pstmt.setString(18,provider.getProviderReapplyDateString());
          pstmt.setString(19,provider.getProviderVisitDateString());
          pstmt.setString(20,provider.getProviderReapplyExpDateString());
          pstmt.setString(21,provider.getProviderDistrict());
          pstmt.setString(22,provider.getProviderEmergencyPhone());
          pstmt.setString(23,provider.getProviderApplicationDateString());
          pstmt.setString(24,provider.getProviderType());
          pstmt.setString(25,provider.getProviderEmail());
          pstmt.setString(26,provider.getProviderFax());
          pstmt.setString(27,provider.getProviderComments());
          pstmt.setString(28,provider.getProviderIniAemtDateString());
          pstmt.setString(29,provider.getProviderIniDfbrDateString());
          pstmt.setString(30,provider.getProviderIniParaDateString());
          pstmt.setString(31,provider.getProviderIniCertDateString());
          pstmt.setString(32,provider.getProviderCertReqDateString());
          pstmt.setString(33,provider.getProviderDataCollection());
          pstmt.setString(34,provider.getProviderTactExpDateString());
          pstmt.setString(35,provider.getProviderIhernExpDateString());
          pstmt.setString(36,provider.getProviderTerminationDateString());
          pstmt.setString(37,provider.getProviderUpdateDateString());
          pstmt.setString(38,provider.getProviderInsPolNumber());
          pstmt.setString(39,provider.getProviderInsEffDateString());
          pstmt.setString(40,provider.getProviderInsExpDateString());
          pstmt.setString(41,provider.getProviderInsCarrier());
          pstmt.setInt(42,provider.getProviderId());
          
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
  public List selectProviderListHosp(String sql, int hospitalId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.clearParameters();
      pstmt.setInt(1,hospitalId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        EmsProvider provider = new EmsProvider();
         provider.setProviderId(rs.getInt(1));
         provider.setProviderName(rs.getString(2));
         provider.setProviderAddress1(rs.getString(3));
         provider.setProviderAddress2(rs.getString(4));
         provider.setProviderCity(rs.getString(5));
         provider.setProviderState(rs.getString(6));
         provider.setProviderZip(rs.getString(7));
         provider.setProviderCounty(rs.getString(8));
        
        list.add(provider);
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
  public EmsProvider selectProviderByCert(String certNumber) throws SQLException, Exception 
  {
   EmsProvider provider = new EmsProvider();
   ResultSet rs = null;
   PreparedStatement pstmt = null;
    Connection conn = null;
    try 
   { conn = getConnection();
     pstmt = conn.prepareStatement(EmsSQL.SELECT_PROVIDER_BY_CERT);
     pstmt.clearParameters();
     pstmt.setString(1,certNumber);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
          provider.setProviderId(rs.getInt(1));
          provider.setProviderName(rs.getString(12));
          provider.setProviderAddress1(rs.getString(13));
          provider.setProviderAddress2(rs.getString(14));
          provider.setProviderCity(rs.getString(15));
          provider.setProviderState(rs.getString(16));
          provider.setProviderZip(rs.getString(17));
          
          provider.setProviderCounty(rs.getString(7));
          provider.setProviderZip2(rs.getString(18));
          provider.setProviderType(rs.getString(26));
         provider.setProviderCertNumber(rs.getString(6));
         provider.setProviderDistrict(rs.getString(23));
         provider.setProviderEmail(rs.getString(27));
         provider.setProviderPhone(rs.getString(5));
         provider.setProviderFax(rs.getString(28));
         provider.setProviderEmergencyPhone(rs.getString(24));
         provider.setProviderAppliedDate(rs.getDate(25));
         provider.setProviderExpDate(rs.getDate(11));
         provider.setProviderComments(rs.getString(29));
         provider.setProviderInsCarrier(rs.getString(44));
         provider.setProviderInsEffDate(rs.getDate(42));
         provider.setProviderInsExpDate(rs.getDate(43));
         provider.setProviderInsPolNumber(rs.getString(41));
         provider.setProviderCertRequired(rs.getString(8));
         provider.setProviderApplicationDate(rs.getDate(25));
         provider.setProviderReviewDate(rs.getDate(2));
         provider.setProviderApprovedDate(rs.getDate(3));
         provider.setProviderUpdateDate(rs.getDate(39));
         provider.setProviderCertDate(rs.getDate(10));
         provider.setProviderCertReqDate(rs.getDate(34));
         provider.setProviderReapplyDate(rs.getDate(19));
         provider.setProviderDataCollection(rs.getString(35));
         provider.setProviderVisitDate(rs.getDate(20));
         provider.setProviderAuditDate(rs.getDate(21));
         provider.setProviderIniCertDate(rs.getDate(33));
         provider.setProviderIniDfbrDate(rs.getDate(31));
         provider.setProviderIniMeetingDate(rs.getDate(9));
         provider.setProviderIniIntDate(rs.getDate(9));
         provider.setProviderIniAemtDate(rs.getDate(30));
         provider.setProviderIniParaDate(rs.getDate(32));
         provider.setProviderTerminationDate(rs.getDate(38));
         provider.setProviderReapplyExpDate(rs.getDate(21));
         provider.setProviderTactExpDate(rs.getDate(36));
         provider.setProviderIhernExpDate(rs.getDate(37));

     }
     return provider;
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