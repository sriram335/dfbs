package ems.data;
import ems.to.*;
import ems.data.*;

import ems.to.EmsHospital;

import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
public class EmsHospitalDAO  extends HsDAO
{
  public EmsHospitalDAO()
  {
  }
  public List selectHospitalList(String sql,String param) throws SQLException, Exception 
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
       EmsHospital hospital = new EmsHospital();
      hospital.setHospitalId(rs.getInt(1));
      hospital.setHospitalName(rs.getString(2));
      hospital.setAddress1(rs.getString(3));
      hospital.setAddress2(rs.getString(4));
      hospital.setCounty(rs.getString(5));
      hospital.setCity(rs.getString(6));
      hospital.setState(rs.getString(7));
      hospital.setZip(rs.getString(8));
      hospital.setZip2(rs.getString(9));
      
        
        list.add(hospital);
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
  public EmsHospital selectHospital(int HospitalId) throws SQLException, Exception 
  {
     EmsHospital hospital = new EmsHospital();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      hospital = selectHospital(conn,HospitalId);
      return hospital;
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
   public EmsHospital selectHospital(Connection conn,int hospitalId) throws SQLException, Exception 
  {
    EmsHospital hospital = new EmsHospital();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_HOSPITAL);
      pstmt.clearParameters();
      pstmt.setInt(1,hospitalId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      hospital.setAppRecivedDate(rs.getDate(15));
      hospital.setApprovedDate(rs.getDate(19));
      hospital.setAuditDate(rs.getDate(20));
      hospital.setCertRequestedDate(rs.getDate(22));
      hospital.setExpDate(rs.getDate(14));
      hospital.setIniCertDate(rs.getDate(13));
      hospital.setReapplyDate(rs.getDate(7));
      hospital.setReviewedDate(rs.getDate(18));
      hospital.setUpdateDate(rs.getDate(16));
      hospital.setVisitDate(rs.getDate(17));
      hospital.setAddress1(rs.getString(3));
      hospital.setAddress2(rs.getString(4));
      hospital.setCertNumber(rs.getString(11));
      hospital.setCity(rs.getString(6));
      hospital.setComments(rs.getString(21));
      hospital.setCounty(rs.getString(5));
      hospital.setDistirict(rs.getString(12));
      hospital.setHospitalName(rs.getString(2));
      hospital.setState(rs.getString(8));
      hospital.setZip(rs.getString(9));
      hospital.setZip2(rs.getString(10));
      hospital.setHospitalId(rs.getInt(1));
        

      }
      return hospital;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateHospital(EmsHospital hospital) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_HOSPITAL);
          pstmt.clearParameters();
          pstmt.setString(15,hospital.getAppRecivedDateString());
          pstmt.setString(19,hospital.getApprovedDateString());
          pstmt.setString(20,hospital.getAuditDateString());
          pstmt.setString(1,hospital.getCertRequestedDateString());
          pstmt.setString(14,hospital.getExpDateString());
          pstmt.setString(13,hospital.getIniCertDateString());
          pstmt.setString(7,hospital.getReapplyDateString());
          pstmt.setString(18,hospital.getReviewedDateString());
          pstmt.setString(16,hospital.getUpdateDateString());
          pstmt.setString(17,hospital.getVisitDateString());
          pstmt.setString(3,hospital.getAddress1());
          pstmt.setString(4,hospital.getAddress2());
          pstmt.setString(11,hospital.getCertNumber());
          pstmt.setString(6,hospital.getCity());
          pstmt.setString(21,hospital.getComments());
          pstmt.setString(5,hospital.getCounty());
          pstmt.setString(12,hospital.getDistirict());
          pstmt.setString(2,hospital.getHospitalName());
          pstmt.setString(8,hospital.getState());
          pstmt.setString(9,hospital.getZip());
          pstmt.setString(10,hospital.getZip2());
          pstmt.setInt(22,hospital.getHospitalId());
          
          
          System.out.println(pstmt);
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
  
}


