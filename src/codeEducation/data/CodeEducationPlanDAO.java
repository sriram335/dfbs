package codeEducation.data;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import hs.data.*;
import codeEducation.to.*;
public class CodeEducationPlanDAO extends HsDAO{
    public CodeEducationPlanDAO() {
    }
    public List selectPlanList(String sql, int codePersonId) throws SQLException, Exception 
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
          pstmt.clearParameters();
          pstmt.setInt(1,codePersonId);
        rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          CodeEducationPlan codePlan = new CodeEducationPlan();
            codePlan.setEduDescription(rs.getString(3));
            codePlan.setEduStatus(rs.getString(5));
            codePlan.setEduType(rs.getString(4));
            codePlan.setCodeEduId(rs.getInt(1));
            codePlan.setCodePersonId(rs.getInt(2));
          
          list.add(codePlan);
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
    public CodeEducationPlan selectPlan( int planId) throws SQLException, Exception 
      {
        CodeEducationPlan codePlan = new CodeEducationPlan();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
          Connection conn = null;
         try 
        { conn = getConnection();
          pstmt = conn.prepareStatement(CodeEducationSQL.SELECT_CODE_EDU_PLAN_BY_ID);
          pstmt.clearParameters();
          pstmt.setInt(1,planId);
          rs = pstmt.executeQuery();
          if(rs.next()) 
          {
              codePlan.setEduDescription(rs.getString(3));
              codePlan.setEduStatus(rs.getString(5));
              codePlan.setEduType(rs.getString(4));
              codePlan.setCodeEduId(rs.getInt(1));
              codePlan.setCodePersonId(rs.getInt(2));
              
          }
            return codePlan;
        }
        finally 
        {
         try {conn.close();
            rs.close();
            pstmt.close();
          } catch (Exception e) {}
        }
      }
    public void updatePlan(CodeEducationPlan codePlan) throws SQLException, Exception 
     {
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             pstmt = conn.prepareStatement(CodeEducationSQL.UPDATE_CODE_EDU_PLAN);
             pstmt.clearParameters();
           pstmt.setString(2,codePlan.getEduDescription());
           pstmt.setString(4,codePlan.getEduStatus());
           pstmt.setString(3,codePlan.getEduType());
           pstmt.setInt(5,codePlan.getCodeEduId());
           pstmt.setInt(1,codePlan.getCodePersonId());
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
      public void insertPlan(CodeEducationPlan codePlan) throws SQLException, Exception 
     {
     
             Connection conn = null;
             PreparedStatement pstmt = null;
          try 
       {
             conn = getConnection();
             conn.setAutoCommit(true);
             int id = this.getId(conn,"select code_edu_plan_id.nextval from dual");
             pstmt = conn.prepareStatement(CodeEducationSQL.INSERT_CODE_EDU_PLAN);
             pstmt.clearParameters();
          pstmt.setString(3,codePlan.getEduDescription());
          pstmt.setString(5,codePlan.getEduStatus());
          pstmt.setString(4,codePlan.getEduType());
          pstmt.setInt(1,id);
          pstmt.setInt(2,codePlan.getCodePersonId());
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
