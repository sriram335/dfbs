package idhsInspections.data;
import codeEducation.data.CodeEducationSQL;

import codeEducation.to.CodeEducationPlan;

import  idhsInspections.to.*;
import  idhsInspections.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import main.to.ApplicationSecurity;
public class fireAEPermitApprovalDAO extends HsDAO{
    public fireAEPermitApprovalDAO() {
    }
    public fireAEPermitApproval  selectFacUse(String idNumber) throws SQLException, Exception 
    {
    
     fireAEPermitApproval  facUse = new fireAEPermitApproval ();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     try 
     {
       
       conn = getConnection();
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_ENTR_FAC_USE);
       pstmt.clearParameters();
       pstmt.setString(1,idNumber);
       rs = pstmt.executeQuery();
       if(rs.next()) 
       {
           facUse.setFacAmLegion(rs.getString(13));
           facUse.setFacBanquet(rs.getString(8));
           facUse.setFacBar(rs.getString(1));
           facUse.setFacBilliardPool(rs.getString(10));
           facUse.setFacBowling(rs.getString(9));
           facUse.setFacCasino(rs.getString(2));
           facUse.setFacHaunted(rs.getString(3));
           facUse.setFacOther(rs.getString(15));
           facUse.setFacPrivClub(rs.getString(14));
           facUse.setFacRacTrack(rs.getString(12));
           facUse.setFacRestaurant(rs.getString(11));
           facUse.setFacSwimPool(rs.getString(7));
           facUse.setFac_arena(rs.getString(4));
           facUse.setFac_school(rs.getString(6));
           facUse.setFac_thetre(rs.getString(5));
           facUse.setInspFacId(rs.getString(16));
           facUse.setFacFairFestival(rs.getString(17));
           
       }
     
       return facUse;
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
    public void insertFacUse (fireAEPermitApproval  facUse) throws SQLException, Exception 
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
       
    try 
    { 
      
        conn = getConnection();
        conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(InspectionsSQL.INSERT_ENTR_FAC_USE);
      
        pstmt.setString(14,facUse.getFacAmLegion());
        pstmt.setString(9,facUse.getFacBanquet());
        pstmt.setString(2,facUse.getFacBar());
        pstmt.setString(11,facUse.getFacBilliardPool());
        pstmt.setString(10,facUse.getFacBowling());
        pstmt.setString(3,facUse.getFacCasino());
        pstmt.setString(4,facUse.getFacHaunted());
        pstmt.setString(16,facUse.getFacOther());
        pstmt.setString(17,facUse.getFacFairFestival());
        pstmt.setString(15,facUse.getFacPrivClub());
        pstmt.setString(13,facUse.getFacRacTrack());
        pstmt.setString(12,facUse.getFacRestaurant());
        pstmt.setString(8,facUse.getFacSwimPool());
        pstmt.setString(5,facUse.getFac_arena());
        pstmt.setString(7,facUse.getFac_school());
        pstmt.setString(6,facUse.getFac_thetre());
        pstmt.setString(1,facUse.getInspFacId());
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
    public void updateFacUse (fireAEPermitApproval  facUse) throws SQLException, Exception
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
       
    try
    {
    conn = getConnection();
    conn.setAutoCommit(false);
    pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_ENTR_FAC_USE);
    
        pstmt.setString(14,facUse.getFacAmLegion());
        pstmt.setString(9,facUse.getFacBanquet());
        pstmt.setString(2,facUse.getFacBar());
        pstmt.setString(11,facUse.getFacBilliardPool());
        pstmt.setString(10,facUse.getFacBowling());
        pstmt.setString(3,facUse.getFacCasino());
        pstmt.setString(4,facUse.getFacHaunted());
        pstmt.setString(1,facUse.getFacOther());
        pstmt.setString(15,facUse.getFacPrivClub());
        pstmt.setString(16,facUse.getFacFairFestival());
        pstmt.setString(13,facUse.getFacRacTrack());
        pstmt.setString(12,facUse.getFacRestaurant());
        pstmt.setString(8,facUse.getFacSwimPool());
        pstmt.setString(5,facUse.getFac_arena());
        pstmt.setString(7,facUse.getFac_school());
        pstmt.setString(6,facUse.getFac_thetre());
        pstmt.setString(17,facUse.getInspFacId());
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
    public List  selectPermitsForApproval(String idNumber) throws SQLException, Exception 
    {
        List list = new ArrayList();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
      ResultSet rs1 = null;
     PreparedStatement pstmt1 = null;
     try 
     {
       
       conn = getConnection();
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_AE_ANNUAL_APPROVAL);
       pstmt.clearParameters();
       pstmt.setString(1,idNumber);
       rs = pstmt.executeQuery();
       if(rs.next()) 
       {
           searchResults result = new searchResults();
           result.setResultId(rs.getString(1));
           result.setResultAddress1(rs.getString(2)); 
           result.setResultAddress2(rs.getString(3)); 
           result.setResultCity(rs.getString(4));
           result.setResultType("AE");
           list.add(result);
       }
         pstmt1 = conn.prepareStatement(InspectionsSQL.SELECT_AE_SPECIAL_APPROVAL);
         pstmt1.clearParameters();
         pstmt1.setString(1,idNumber);
         rs1 = pstmt1.executeQuery();
         while(rs1.next())
         {
            searchResults result = new searchResults();
            result.setResultId(rs1.getString(1));
            result.setResultAddress1(rs1.getString(2)); 
            result.setResultAddress2(rs1.getString(3)); 
            result.setResultCity(rs1.getString(4));
             result.setResultType("SP");
            list.add(result);
         }
        return list;
     }
     finally 
     {
      try {
         conn.close();
         rs.close();
         pstmt.close();
        rs1.close();
         pstmt1.close();
       } catch (Exception e) {}
     }
    }
    public void updateAEPermitIssueDate (String idNumber,String inspDate, String setInspDate) throws SQLException, Exception
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
       
    try
    {
    conn = getConnection();
    conn.setAutoCommit(false);
        if(idNumber.substring(0,2).equals("AE"))
        {
        pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_AE_ANNUAL_APPROVAL);
            pstmt.setString(1,setInspDate);
            pstmt.setString(2,setInspDate);
            pstmt.setString(3,idNumber);
        }
        else {
            pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_SPECIAL_ANNUAL_APPROVAL); 
            pstmt.setString(1,inspDate);
            pstmt.setString(2,idNumber);
        }
    
        
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
      public List  selectSpecialsforPrint(String idNumber) throws SQLException, Exception 
      {
          List list = new ArrayList();
       Connection conn = null;
        ResultSet rs1 = null;
       PreparedStatement pstmt1 = null;
       try 
       {
         
         conn = getConnection();
          
           pstmt1 = conn.prepareStatement(InspectionsSQL.SELECT_SPECIALS_FOR_PRINT);
           pstmt1.clearParameters();
           pstmt1.setString(1,idNumber);
           rs1 = pstmt1.executeQuery();
           while(rs1.next())
           {
              searchResults result = new searchResults();
              result.setResultId(rs1.getString(1));
              result.setResultAddress1(rs1.getString(2)); 
              result.setResultAddress2(rs1.getString(3)); 
              result.setResultCity(rs1.getString(4));
               result.setResultType("SP");
              list.add(result);
           }
          return list;
       }
       finally 
       {
        try {
           conn.close();
          rs1.close();
           pstmt1.close();
         } catch (Exception e) {}
       }
      }
    }
