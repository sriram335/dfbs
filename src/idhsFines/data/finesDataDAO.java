package idhsFines.data;




import  idhsFines.to.*;


import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import main.to.*;
import hs.data.*;
public class finesDataDAO  extends HsDAO{
    public finesDataDAO() {
        super();
    }
  public  finesDataDAO(HsDataSource pool)
  {
   super(pool);
   
  }
  public List showDues(String stateNumber,String ownerId,String division,ShoppingCart cart) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   int x =0;
   Connection conn = null;
   
   try 
   {
     conn = getConnection();
     if (stateNumber !=null && stateNumber.length()>2)
     { pstmt = conn.prepareStatement(finesSQL.SELECT_ACCT_DUES_BY_STATE_NUMBER);
     pstmt.clearParameters();
     pstmt.setString(1,stateNumber);
     }
     else {
       pstmt = conn.prepareStatement(finesSQL.SELECT_ACCT_DUES_BY_OWNER_ID);
       pstmt.clearParameters();
       pstmt.setString(1,ownerId);
     }
     
     pstmt.setInt(2,Integer.parseInt(division)); 
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {
       feeDetails f = new feeDetails();
       f.setDue(rs.getString(1));
       f.setAmountPaid(rs.getString(2));
       f.setReceiptNumber(rs.getString(3));
       f.setPostDate(rs.getString(4));
       f.setDescription(rs.getString(5));
       f.setUniqueNumber(rs.getString(6));
       f.setTransactionId(rs.getString(7));  
         feeDetails testFee = cart.getFeeDetails(rs.getString(7));
      if(testFee ==null ||testFee.getTransactionId() ==null)
      {
       list.add(f);
      }
     }
      
     return list;
   }
   finally 
   {
    try {conn.close();
       rs.close();
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  public feeDetails  selectFeeDetails(String transactionId) throws SQLException, Exception 
  {
  
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   int x =0;
   Connection conn = null;
    feeDetails f = new feeDetails();
   try 
   { 
     conn = getConnection();
     pstmt = conn.prepareStatement(finesSQL.SELECT_ACCT_DUES_BY_TRANSACTION_ID);
     pstmt.setInt(1,Integer.parseInt(transactionId)); 
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {f.setDue(rs.getString(1));
       f.setAmountPaid(rs.getString(2));
       f.setReceiptNumber(rs.getString(3));
       f.setPostDate(rs.getString(4));
       f.setDescription(rs.getString(5));
       f.setUniqueNumber(rs.getString(6));
       f.setTransactionId(rs.getString(7));  
      }
     
     
     return f;
   }
   finally 
   {
    try {conn.close();
       rs.close();
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  public void updateFeeRecord(feeDetails feeDet ,int receiptId,String division) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(finesSQL.UPDATE_DFBS_FEE);
       pstmt.setString(1,Integer.toString(receiptId));
        pstmt.setString(2,Integer.toString(receiptId));
        pstmt.setInt(3,Integer.parseInt(feeDet.getTransactionId()));
       pstmt.execute();
      conn.commit();
        if (division.equals("15")) {
          pstmt1 = conn.prepareStatement(finesSQL.UPDATE_HAZMAT_SHIPMENT);
           pstmt1.setInt(1,receiptId);
            pstmt1.setString(2,feeDet.getUniqueNumber());
           pstmt1.execute();
          conn.commit();
        }
      
    } catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {
        conn.close();
        pstmt.close(); pstmt1.close();
      } catch (Exception e) {}
    }
  }
  public appContacts  selectAppContacts() throws SQLException, Exception 
  {
  
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   int x =0;
   Connection conn = null;
    appContacts contact = new appContacts();
   try 
   { 
     conn = getConnection();
     pstmt = conn.prepareStatement(finesSQL.SELECT_APP_CONTACTS);
     rs = pstmt.executeQuery();
     while(rs.next()) 
     {  if (rs.getString(3).equals("FIRE_FEE_CONTACT")) {
          contact.setFireContactName(rs.getString(1));
          contact.setFireContactEmail(rs.getString(2));
        }
        if (rs.getString(3).equals("BPV_FEE_CONTACT")) {
                  contact.setBpvContactName(rs.getString(1));
                  contact.setBpvContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("BLASTER_FEE_CONTACT")) {
                  contact.setBlasterContactName(rs.getString(1));
                  contact.setBlasterContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("CODE_FEE_CONTACT")) {
                  contact.setCodeContactName(rs.getString(1));
                  contact.setCodeContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("PLAN_FEE_CONTACT")) {
                  contact.setPlanContactName(rs.getString(1));
                  contact.setPlanContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("ELEV_FEE_CONTACT")) {
                  contact.setElevContactName(rs.getString(1));
                  contact.setElevContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("AMUSE_FEE_CONTACT")) {
                  contact.setRidesContactName(rs.getString(1));
                  contact.setRidesContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("VAR_FEE_CONTACT")) {
                  contact.setVarContactName(rs.getString(1));
                  contact.setVarContactEmail(rs.getString(2));
                }  
        if (rs.getString(3).equals("HAZMAT_FEE_CONTACT")) {
                  contact.setHazmatContactName(rs.getString(1));
                  contact.setHazmatContactEmail(rs.getString(2));
                } 
      }
    
     
     return contact;
   }
   finally 
   {
    try {conn.close();
       rs.close();
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  public void insertPermitTransaction(String PSID, String lastName,String feeType) throws SQLException, Exception 
   {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       
       int id = this.getId(conn,finesSQL.SELECT_NEXT_TRANSACTION_ID);
       double amount = Double.parseDouble(selectBlasterFee(feeType));
       pstmt = conn.prepareStatement(finesSQL.INSERT_PERMIT_TRANSACTION);
       pstmt.clearParameters();
       pstmt.setInt(1,id);
       pstmt.setDouble(2,amount);
       pstmt.setString(3,feeType+" Fee: " +PSID+":"+lastName);
       pstmt.setString(4,PSID);
       pstmt.setString(5,lastName);
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
  public String selectBlasterFee(String feeType) throws SQLException, Exception 
   {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
      try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
          if (feeType.equals("Blaster"))
                 {
           pstmt = conn.prepareStatement(finesSQL.SELECT_BLASTER_FEE);
                 }
          else {
            pstmt = conn.prepareStatement(finesSQL.SELECT_OPERATOR_FEE);
          }
       pstmt.clearParameters();
       rs = pstmt.executeQuery();
        String  blasterFee="0";
       while(rs.next()) 
       { 
         blasterFee=rs.getString(1);
       }  
       return(blasterFee);
     } 
      finally 
     {
       try {conn.close();
          rs.close();
          pstmt.close();
        } catch (Exception e) {}
     }
   }
  public int selectBlasterFeeExisting(String PSID,String feeType) throws SQLException, Exception 
   {
     Connection conn = null;
     PreparedStatement pstmt = null;
     ResultSet rs = null;
      try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       pstmt = conn.prepareStatement(finesSQL.SELECT_BLASTER_FEE_FROM_DFBS);
       pstmt.clearParameters();
        pstmt.setString(1,PSID);
       pstmt.setString(2,feeType); 
        rs = pstmt.executeQuery();
        int  blasterFee=0;
       while(rs.next()) 
       { 
         blasterFee=rs.getInt(1);
       }  
       return(blasterFee);
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
