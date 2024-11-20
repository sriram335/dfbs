package Variance.data;
import Variance.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import Variance.to.*;
import hs.data.*;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
public class varDesignerDAO  extends HsDAO{
    public varDesignerDAO() {
        
    }
  public varDesigner selectDes(int desId) throws SQLException, Exception 
  {
  
   varDesigner des = new varDesigner();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
       
   try 
   {
     conn = getConnection();
     pstmt = conn.prepareStatement(varianceSQL.SELECT_VAR_DESIGNER);
     pstmt.clearParameters();
     pstmt.setInt(1,desId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
       des.setDesId(rs.getInt(1));
       des.setDesLastName(rs.getString(2));
       des.setDesFirstName(rs.getString(4));
       des.setDesMI(rs.getString(3));
       des.setDesDBA(rs.getString(13));
       des.setDesAddress1(rs.getString(5));
       des.setDesAddress2(rs.getString(6));
       des.setDesCity(rs.getString(7));
       des.setDesZip(rs.getString(9));
       des.setDesZip2(rs.getString(10));
       des.setDesPhone(rs.getString(11));
        des.setDesEmail(rs.getString(15));
       des.setDesState(rs.getString(8));
      des.setDesProjId(rs.getInt(12));
    }
   
  
     return des;
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
  
  
  
  

  public int insertDes(varDesigner des) throws SQLException, Exception
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
    int id = this.getId(conn,varianceSQL.SELECT_NEXT_DESIGNER_ID);
     des.setDesId(id);
     
     pstmt = conn.prepareStatement(varianceSQL.INSERT_VAR_DESIGNER);
     pstmt.clearParameters();
     pstmt.setInt(1,des.getDesId());
     pstmt.setString(2,des.getDesLastName());
     pstmt.setString(4,des.getDesFirstName());
     pstmt.setString(3,des.getDesMI());
     pstmt.setString(13,des.getDesDBA());
     pstmt.setString(5,des.getDesAddress1());
     pstmt.setString(6,des.getDesAddress2());
     pstmt.setString(7,des.getDesCity());
     pstmt.setString(8,des.getDesState());
     pstmt.setString(9,des.getDesZip());
     pstmt.setString(10,des.getDesZip2());
     pstmt.setString(11,des.getDesPhone());
     pstmt.setInt(12,des.getDesProjId());
     pstmt.setString(14,des.getDesEmail());  
     pstmt.execute();
     conn.commit();
     
     return 1;
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
  public void updateDes(varDesigner des) throws SQLException, Exception
  {
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
     pstmt = conn.prepareStatement(varianceSQL.UPDATE_VAR_DESIGNER);
     pstmt.clearParameters();
     pstmt.setInt(14,des.getDesId());
     pstmt.setString(2,des.getDesLastName());
     pstmt.setString(4,des.getDesFirstName());
     pstmt.setString(3,des.getDesMI());
     pstmt.setString(13,des.getDesDBA());
     pstmt.setString(5,des.getDesAddress1());
     pstmt.setString(6,des.getDesAddress2());
     pstmt.setString(7,des.getDesCity());
     pstmt.setString(8,des.getDesState());
     pstmt.setString(9,des.getDesZip());
     pstmt.setString(10,des.getDesZip2());
     pstmt.setString(11,des.getDesPhone());
     pstmt.setInt(12,des.getDesProjId());
     pstmt.setString(1,des.getDesEmail());  
    
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
