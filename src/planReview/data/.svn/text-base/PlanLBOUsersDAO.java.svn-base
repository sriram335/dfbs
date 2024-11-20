package planReview.data;
import planReview.to.*;

import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;


import main.to.FileNames;

import planReview.to.PlanLBOUsers;

public class PlanLBOUsersDAO extends HsDAO{
    public PlanLBOUsersDAO() {
     
    }

  

 
   public PlanLBOUsers selectPlanLBOUser(String userLoginId, String userPassword) throws SQLException, Exception 
  {
  
    PlanLBOUsers planUser = new PlanLBOUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(PlanReviewSQL.VERIFY_PLAN_LBO_USERS);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
       pstmt.setString(2,userPassword);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { planUser.setUserPasswordExpiresDate(rs.getDate(6));
        planUser.setUserFirstName(rs.getString(2));
        planUser.setUserLastName(rs.getString(3));
        planUser.setUserLoginId(rs.getString(7));
        planUser.setUserPassword(rs.getString(4));
        planUser.setUserStatus(rs.getString(5));
        planUser.setUserTelephone(rs.getString(8));
        planUser.setUserId(rs.getInt(1)); 
        planUser.setOwnerId(rs.getInt(9));
        planUser.setUserPasswordCheck(rs.getInt(10));
    }
     return planUser;
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
  
   public void updateplanUserPassword(PlanLBOUsers planUser) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(PlanReviewSQL.UPDATE_PLAN_LBO_USER_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,planUser.getUserPassword());
      pstmt.setInt(2,planUser.getUserId());
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
  public PlanLBOUsers emailPlanUserPassword(String userLoginId) throws SQLException, Exception 
  {
  
    PlanLBOUsers planUser = new PlanLBOUsers();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(PlanReviewSQL.EMAIL_PLAN_LBO_USERS_PASSWORD);
      pstmt.clearParameters();
      pstmt.setString(1,userLoginId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { planUser.setUserPasswordExpiresDate(rs.getDate(6));
        planUser.setUserFirstName(rs.getString(2));
        planUser.setUserLastName(rs.getString(3));
        planUser.setUserLoginId(rs.getString(7));
        planUser.setUserPassword(rs.getString(4));
        planUser.setUserStatus(rs.getString(5));
        planUser.setUserTelephone(rs.getString(8));
        planUser.setUserId(rs.getInt(1)); 
        planUser.setOwnerId(rs.getInt(9));
        planUser.setUserPasswordCheck(rs.getInt(10));
    }
     return planUser;
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
  
 
     public List selectProjectList(String lboLoginId, int lboTime, int lboProject) throws SQLException, Exception 
     {
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       PreparedStatement pstmt1 = null;
       PreparedStatement pstmt2 = null;
       ResultSet rs1 = null;
       ResultSet rs2 = null;
       try 
       {  
         conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_LBO_AREA);
         pstmt.clearParameters();
         if(lboLoginId != null) {
           pstmt.setString(1,lboLoginId);
         }
         rs = pstmt.executeQuery();
         while(rs.next()) 
         { 
          String countyName=rs.getString(1);
          String cityName=rs.getString(3);  
            if (cityName==null ||cityName.equals("")) 
            { 
               String allCityNames =this.FindCityNames(countyName);
               pstmt1 = conn.prepareStatement(PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_LBO_COUNTY);
               pstmt1.clearParameters();
               pstmt1.setInt(1,lboTime);
               pstmt1.setString(2,countyName);
               pstmt1.setString(3,allCityNames);
               pstmt1.setInt(4,lboTime);
               pstmt1.setString(5,countyName);
               rs1=pstmt1.executeQuery();
                while(rs1.next()) {
                  FileNames names = new FileNames();
                   names.setFileConnector(rs1.getString(1)); 
                   names.setFileName(rs1.getString(2)); 
                   names.setFileType(rs1.getString(3)); 
                  names.setFilePlanType(rs1.getString(4)); 
                   list.add(names); 
                }
            }
            else
            { 
               pstmt2 = conn.prepareStatement(PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_LBO_CITY);System.out.println("1");
               pstmt2.clearParameters();
               pstmt2.setInt(1,lboTime);
               pstmt2.setString(2,countyName);
               pstmt2.setString(3,cityName);
               rs2=pstmt2.executeQuery();
                while(rs2.next()) { 
                  FileNames names = new FileNames();
                   names.setFileConnector(rs2.getString(1)); 
                   names.setFileName(rs2.getString(2)); 
                   names.setFileType(rs2.getString(3)); 
                  names.setFilePlanType(rs2.getString(4)); 
                   list.add(names);
                }
            }
          
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
              rs2.close();
           pstmt2.close();
         } catch (Exception e) {}
       }
     } 
 public String FindCityNames (String countyName) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call plan_lbo_select_cities(?,?) }");
      proc.setString(1, countyName);
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.execute();
      
     return(proc.getString(2));
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
   public List selectProjectListProject(String lboId, int lboProject) throws SQLException, Exception 
     {
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       PreparedStatement pstmt2 = null;
         ResultSet rs2 = null;
       
       try 
       {  
         conn = getConnection();
         pstmt = conn.prepareStatement(PlanReviewSQL.SELECT_LBO_AREA);
         pstmt.clearParameters();
         if(lboId != null) {
           pstmt.setString(1,lboId);
         }
         rs = pstmt.executeQuery();
         while(rs.next()) 
         { 
          String countyName=rs.getString(1);
          String cityName=rs.getString(3);  
           
               pstmt2 = conn.prepareStatement(PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_LBO_CITY_PROJECT);
               pstmt2.clearParameters();
               pstmt2.setInt(1,lboProject);
               pstmt2.setString(2,countyName);
               pstmt2.setString(3,cityName);
               rs2=pstmt2.executeQuery();
                while(rs2.next()) {             
                  FileNames names = new FileNames();
                   names.setFileConnector(rs2.getString(1)); 
                    names.setFileName(rs2.getString(2)); 
                   names.setFileType(rs2.getString(3));
                  names.setFilePlanType(rs2.getString(4)); 
                   list.add(names);
                }
           
         }
         return list;
        
       }
       finally 
       {
        try {
           conn.close();
           rs.close();
           pstmt.close();
               rs2.close();
           pstmt2.close();
         } catch (Exception e) {}
       }
     } 
   public List selectProjectListOthers(String county,String projectNumber,String projectStreetNumber, String projectCity) throws SQLException, Exception 
     {
       List list = new ArrayList();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       String lsql=null;
       String lwhere ="";
       String lwhere1 ="";
       try 
       {  
         conn = getConnection();
          if (projectNumber != null && projectNumber.length()>5) {
            lwhere =" AND PP.SBC_PROJECT_NUMBER="+projectNumber;
             }
           else {
            lwhere =" AND PFF.APPLICATION_ENTERED_DATE >sysdate-90 ";
          }
           if(projectStreetNumber !=null && projectStreetNumber.length()>=2){
            lwhere1 =" AND  upper(PP.PROJECT_ADDRESS1) LIKE  UPPER('%"+projectStreetNumber+"%')";
           }
            if(projectCity !=null && projectCity.length()>=3 ){
            lwhere1 = lwhere1 + " AND UPPER(PROJECT_CITY) =UPPER('"+projectCity+"')";
           }
           
          if (lwhere1.length()>3) {
           lwhere = lwhere1;
          }
          if(county.equals("DNR") ) {
            lsql =PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_DNR + lwhere;
             pstmt = conn.prepareStatement(lsql);
         }
         else if (county.equals("IDEM")) {
               lsql =PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_IDEM +lwhere;
                pstmt = conn.prepareStatement(lsql);
             }
         else if (county.equals("ISDH")) {
               lsql =PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_ISDH+lwhere;
                pstmt = conn.prepareStatement(lsql);
             }
          else {  
           lsql =PlanReviewSQL.SELECT_PROJECTS_WITH_PLANS_OTHER + lwhere;
              pstmt = conn.prepareStatement(lsql);
           pstmt.clearParameters();
           pstmt.setString(1,county);
         }
          
           rs = pstmt.executeQuery();
         while(rs.next()) 
         { 
              FileNames names = new FileNames();
                   names.setFileConnector(rs.getString(1)); 
                   names.setFileName(rs.getString(2)); 
                   names.setFileType(rs.getString(3)); 
                   names.setFilePlanType(rs.getString(4));
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


