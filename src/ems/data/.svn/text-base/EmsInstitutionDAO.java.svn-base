package ems.data;
import ems.to.*;
import ems.data.*;

import ems.to.EmsInstitution;

import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;

public class EmsInstitutionDAO  extends HsDAO
{
  public EmsInstitutionDAO()
  {
  }
  
   public List selectInstitutionList(String sql,String param) throws SQLException, Exception 
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
       EmsInstitution institution = new EmsInstitution();
      institution.setInstitutionId(rs.getInt(1));
      institution.setInstitutionName(rs.getString(2));
      institution.setInstitutionAddress1(rs.getString(3));
      institution.setInstitutionAddress2(rs.getString(4));
      institution.setInstitutionCounty(rs.getString(6));
      institution.setInstitutionCity(rs.getString(5));
      institution.setInstitutionState(rs.getString(7));
      institution.setInstitutionZip(rs.getString(8));
      institution.setInstitutionZip2(rs.getString(9));
      institution.setInstPersonEmail(this.findPersonEmail(institution.getInstitutionId()));
        
        list.add(institution);
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
  public EmsInstitution selectInstitution(int InstitutionId) throws SQLException, Exception 
  {
     EmsInstitution institution = new EmsInstitution();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      institution = selectInstitution(conn,InstitutionId);
      return institution;
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
   public EmsInstitution selectInstitution(Connection conn,int institutionId) throws SQLException, Exception 
  {
    EmsInstitution institution = new EmsInstitution();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
     try 
    {
      pstmt = conn.prepareStatement(EmsSQL.SELECT_INSTITUTION);
     pstmt.clearParameters();
      pstmt.setInt(1,institutionId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      institution.setInstitutionCertDate(rs.getDate(4));
      institution.setInstitutionCertReQDate(rs.getDate(19));
      institution.setInstitutionExpDate(rs.getDate(5));
      institution.setInstitutionReceivedDate(rs.getDate(17));
      institution.setInstitutionReviewedDate(rs.getDate(18));
      institution.setInstitutionType(rs.getString(16));
      institution.setInstitutionAddress1(rs.getString(6));
      institution.setInstitutionAddress2(rs.getString(7));
      institution.setInstitutionCertNumber(rs.getString(3));
      institution.setInstitutionCity(rs.getString(8));
      institution.setInstitutionCounty(rs.getString(9));
      institution.setInstitutionEmail(rs.getString(15));
      institution.setInstitutionFax(rs.getString(14));
      institution.setInstitutionName(rs.getString(2));
      institution.setInstitutionPhone(rs.getString(13));
      institution.setInstitutionState(rs.getString(10));
      institution.setInstitutionZip(rs.getString(11));
      institution.setInstitutionZip2(rs.getString(12));
      institution.setInstitutionId(rs.getInt(1));
      institution.setInstPersonEmail(this.findPersonEmail(institutionId));  
      System.out.println("set email county:"+institution.getInstPersonEmail());
      }
      return institution;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public void updateInstitution(EmsInstitution institution) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.UPDATE_INSTITUTION);
          pstmt.clearParameters();
          pstmt.setString(4,institution.getInstitutionCertDateString());
          pstmt.setString(1,institution.getInstitutionCertReQDateString());
          pstmt.setString(5,institution.getInstitutionExpDateString());
          pstmt.setString(17,institution.getInstitutionReceivedDateString());
          pstmt.setString(18,institution.getInstitutionReviewedDateString());
          pstmt.setString(16,institution.getInstitutionType());
          pstmt.setString(6,institution.getInstitutionAddress1());
          pstmt.setString(7,institution.getInstitutionAddress2());
          pstmt.setString(3,institution.getInstitutionCertNumber());
          pstmt.setString(8,institution.getInstitutionCity());
          pstmt.setString(9,institution.getInstitutionCounty());
          pstmt.setString(15,institution.getInstitutionEmail());
          pstmt.setString(14,institution.getInstitutionFax());
          pstmt.setString(2,institution.getInstitutionName());
          pstmt.setString(13,institution.getInstitutionPhone());
          pstmt.setString(10,institution.getInstitutionState());
          pstmt.setString(11,institution.getInstitutionZip());
          pstmt.setString(12,institution.getInstitutionZip2());
          pstmt.setInt(19,institution.getInstitutionId());
          
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
   public void addProvider(int institutionId,int providerId) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt =null ;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          
          int id = this.getId(conn,EmsSQL.SELECT_INERMEDIATE_NEXT_ID);
          pstmt = conn.prepareStatement(EmsSQL.INSERT_INERMEDIATE);
          pstmt.clearParameters();
          pstmt.setInt(1,id);
          pstmt.setInt(2,providerId);
          pstmt.setInt(3,institutionId);
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
   public void deleteProvider(int institutionId,int providerId) throws SQLException, Exception 
  {
          Connection conn = null;
          PreparedStatement pstmt = null;
       try 
    {
          conn = getConnection();
          conn.setAutoCommit(true);
          pstmt = conn.prepareStatement(EmsSQL.DELETE_INERMEDIATE);
          pstmt.clearParameters();
          pstmt.setInt(1,providerId);
          pstmt.setInt(2,institutionId);
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
  
  public int findPersonEmail(int institutionId) throws SQLException, Exception 
  {
  
    int emailCount =0;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(EmsSQL.SELECT_PERSON_EMAIL_COUNT);
      pstmt.clearParameters();
      if(institutionId != 0) {
        pstmt.setInt(1,institutionId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
         emailCount =(rs.getInt(1));
         
        
      }
       return emailCount;
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


