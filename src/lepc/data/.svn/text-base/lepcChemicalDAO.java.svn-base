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
public class lepcChemicalDAO extends HsDAO{
    public lepcChemicalDAO() {
       
    }
  public int insertChemical(lepcChemical chem) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     int id = this.getId(conn,"SELECT EXERCISE_CHEM_ID.NEXTVAL FROM DUAL");
     pstmt = conn.prepareStatement(lepcSQL.INSERT_CHEMICAL);
     chem.setChemicalId(id) ;
     pstmt.clearParameters();
     pstmt.setString(4,chem.getIsChemCercla());
     pstmt.setString(3,chem.getIsChemEHS());
     pstmt.setString(6,chem.getQuantityReleased());
     pstmt.setString(5,chem.getRqForChem());
     pstmt.setInt(1,chem.getChemicalId());
     pstmt.setInt(2,chem.getExerciseId());
     pstmt.setString(7,chem.getChemName());
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
  public void updateChemical(lepcChemical chem) throws SQLException, Exception
    {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
      
       pstmt = conn.prepareStatement(lepcSQL.UPDATE_CHEMICAL);
       pstmt.clearParameters();
       pstmt.setString(4,chem.getIsChemCercla());
       pstmt.setString(3,chem.getIsChemEHS());
       pstmt.setString(1,chem.getQuantityReleased());
       pstmt.setString(5,chem.getRqForChem());
       pstmt.setInt(7,chem.getChemicalId());
       pstmt.setInt(2,chem.getExerciseId());
       pstmt.setString(6,chem.getChemName());
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
   public lepcChemical  selectChemical(int chemId) throws SQLException, Exception 
  {
  
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     lepcChemical chem = new lepcChemical();
     conn = getConnection();
     pstmt = conn.prepareStatement(lepcSQL.SELECT_CHEMICAL );
     pstmt.clearParameters();
     pstmt.setInt(1,chemId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {  
     chem.setIsChemCercla(rs.getString(4));
     chem.setIsChemEHS(rs.getString(3));
     chem.setQuantityReleased(rs.getString(6));
     chem.setRqForChem(rs.getString(5));
     chem.setChemicalId(rs.getInt(1));
     chem.setExerciseId(rs.getInt(2));
     chem.setChemName(rs.getString(7));
   }
    return chem;
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
  public List selectChemicalByExercise(int exerciseId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_CHEMICALS);
    pstmt.clearParameters();
    pstmt.setInt(1,exerciseId);
    rs = pstmt.executeQuery();
    while(rs.next()) 
    {  lepcChemical chem = new lepcChemical();
    chem.setIsChemCercla(rs.getString(4));
    chem.setIsChemEHS(rs.getString(3));
    chem.setQuantityReleased(rs.getString(6));
    chem.setRqForChem(rs.getString(5));
    chem.setChemicalId(rs.getInt(1));
    chem.setExerciseId(rs.getInt(2));
    chem.setChemName(rs.getString(7));
    list.add(chem);
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
  public List selectChemicalList() throws SQLException, Exception 
  {
  
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_CHEM_LIST);
    pstmt.clearParameters();
     rs = pstmt.executeQuery();
    while(rs.next()) 
    {  lepcChemList chem = new lepcChemList();
    chem.setChemicalName(rs.getString(1));
    chem.setChemName(rs.getString(2));
    chem.setChemCas(rs.getString(3));
    chem.setChemEhsRq(rs.getString(4));
    chem.setChemCerlaRq(rs.getString(5));
    list.add(chem);
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
  public lepcChemList  selectChemicalListByCas(String casNumber) throws SQLException, Exception 
  {
  
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
     
  try 
  {
    lepcChemList chem = new lepcChemList();
    conn = getConnection();
    pstmt = conn.prepareStatement(lepcSQL.SELECT_LEPC_CHEM_LIST_BY_CAS );
    pstmt.clearParameters();
    pstmt.setString(1,casNumber);
    rs = pstmt.executeQuery();
    if(rs.next()) 
    {  
    chem.setChemicalName(rs.getString(1));
    chem.setChemName(rs.getString(2));
    chem.setChemCas(rs.getString(3));
    chem.setChemEhsRq(rs.getString(4));
    chem.setChemCerlaRq(rs.getString(5));
  }
   return chem;
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
  public  void deleteChemical(int chemicalId)   throws SQLException, Exception 
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  
  try
  { conn = getConnection();
   pstmt = conn.prepareStatement(lepcSQL.DELETE_CHEMICAL);
   pstmt.clearParameters();
    pstmt.setInt(1,chemicalId);
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
}
