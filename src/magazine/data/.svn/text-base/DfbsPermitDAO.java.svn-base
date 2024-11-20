package magazine.data;
import magazine.to.*;
import magazine.data.*;
import java.util.*;
import java.sql.*;
import java.io.InputStream;
import java.io.OutputStream;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import main.data.*;
import main.to.*;
import org.apache.struts.upload.FormFile;
import hs.data.*;

import idhsInspections.data.InspectionsSQL;

public class DfbsPermitDAO extends HsDAO
{
  public DfbsPermitDAO()
  {
  }
  
  public  List selectPermits(int ownerId,String byType, String byName) throws SQLException, Exception 
  {
    Connection conn = null;
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    { conn = getConnection();
      conn.setAutoCommit(false);
     if (byType == "byCounty")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_COUNTY);
      pstmt.clearParameters();
      pstmt.setString(1,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "byCity")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_CITY);
      pstmt.clearParameters();
       pstmt.setString(1,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "byStreet")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_STREET);
      pstmt.clearParameters();
      pstmt.setString(1,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "byMagId")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_MAG_ID);
      pstmt.clearParameters();
      pstmt.setString(1,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "byPermit")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_PERMIT);
      pstmt.clearParameters();
      pstmt.setString(1,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "All")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_OWNER);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      }
      if (byType == "byStatus")
      {
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMITS_BY_STATUS);
       pstmt.clearParameters();
      rs = pstmt.executeQuery();
      }
      while(rs.next()) 
      { 
        DfbsPermit permit = new DfbsPermit();
        permit.setMagExpDate(rs.getDate(14));
        permit.setMagIssueDate(rs.getDate(13));
        permit.setMagSignedDate(rs.getDate(10));
        permit.setMagAddress1(rs.getString(16));
        permit.setMagAddress2(rs.getString(17));
        permit.setMagCity(rs.getString(18));
        permit.setMagConstruction(rs.getString(4));
        permit.setMagCounty(rs.getString(24));
        permit.setMagDirections(rs.getString(3));
        permit.setMagEmail(rs.getString(29));
        permit.setMagExpType(rs.getString(15));
        permit.setMagFax(rs.getString(28));
        permit.setMagFd(rs.getString(26));
        permit.setMagIdNumber(rs.getString(1));
        permit.setMagMagType(rs.getString(22));
        permit.setMagMinQuantity(rs.getInt(5));
        permit.setMagNearBldg(rs.getString(6));
        permit.setMagNearFactory(rs.getString(9));
        permit.setMagNearHighway(rs.getString(7));
        permit.setMagNearRailway(rs.getString(8));
        permit.setMagPermitYear(rs.getString(12));
        permit.setMagPhone(rs.getString(27));
        permit.setMagState(rs.getString(21));
        permit.setMagTownship(rs.getString(23));
        permit.setMagZip(rs.getString(19));
        permit.setMagZip2(rs.getString(20));
        permit.setMagPermitNumber(rs.getString(11));
        permit.setMagContactPerson(rs.getString(32));
        permit.setMagExpStatus(rs.getString(33));
        permit.setMagExpDays(rs.getDouble(34));
        permit.setMagAtfLicense(rs.getString(35));
        permit.setMagSiteName(rs.getString(2));  
        permit.setMagOwnerId(rs.getInt(36));
        int feeStatusDue = this.selectPermitFeeStatus(rs.getString(1));
        permit.setFeeDue(feeStatusDue);
       list.add(permit);
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
  
   public DfbsPermit selectPermit(String idNumber,String permitType) throws SQLException, Exception 
  {
  
    DfbsPermit permit = new DfbsPermit();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      if(permitType.equals("New"))
      {
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_NEW_PERMIT_BY_ID);
      }
      else {
        pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMIT_BY_ID);
      }
      pstmt.clearParameters();
      pstmt.setString(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
        permit.setMagExpDate(rs.getDate(14));
        permit.setMagIssueDate(rs.getDate(13));
        permit.setMagSignedDate(rs.getDate(10));
        permit.setMagAddress1(rs.getString(16));
        permit.setMagAddress2(rs.getString(17));
        permit.setMagCity(rs.getString(18));
        permit.setMagConstruction(rs.getString(4));
        permit.setMagCounty(rs.getString(24));
        permit.setMagDirections(rs.getString(3));
        permit.setMagEmail(rs.getString(29));
        permit.setMagExpType(rs.getString(15));
        permit.setMagFax(rs.getString(28));
        permit.setMagFd(rs.getString(26));
        permit.setMagIdNumber(rs.getString(1));
        permit.setMagMagType(rs.getString(22));
        permit.setMagMinQuantity(rs.getInt(5));
        permit.setMagNearBldg(rs.getString(6));
        permit.setMagNearFactory(rs.getString(9));
        permit.setMagNearHighway(rs.getString(7));
        permit.setMagNearRailway(rs.getString(8));
        permit.setMagPermitYear(rs.getString(12));
        permit.setMagPhone(rs.getString(27));
        permit.setMagState(rs.getString(21));
        permit.setMagTownship(rs.getString(23));
        permit.setMagZip(rs.getString(19));
        permit.setMagZip2(rs.getString(20));
        permit.setMagPermitNumber(rs.getString(11));
        permit.setMagContactPerson(rs.getString(31));
        permit.setMagOnlineStatus(rs.getString(32));
        permit.setMagComments(rs.getString(34));
        permit.setMagOwnerId(rs.getInt(33));
        permit.setMagAtfLicense(rs.getString(35));
        permit.setMagSiteName(rs.getString(2));  
       // set file list 
        permit.setFileList(selectFileList(permit));
       
      }
   
      return permit;
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
public Map selectFeesMapping() throws SQLException, Exception 
  {
    Map map = new HashMap();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_FEES_MAGAZINE);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      {
        map.put(new Integer(rs.getString(1)),new Double(rs.getDouble(2)));
      }
    } 
    finally 
    {
     try {
        rs.close();
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
    
    return map;
  }
  
  
  
  
 public void insertPermitTransaction(DfbsPermit permit,DfbsOwner owner,ShoppingCart cart,int receiptId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,DfbsSQL.SELECT_NEXT_TRANSACTION_ID);
     
      pstmt = conn.prepareStatement(DfbsSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,permit.getAmount());
      if (cart.getPaymentType().equals("CC"))
      {
      pstmt.setString(3,permit.getMagIdNumber() +"-permit(" + permit.getMagPermitNumber() + ")"+ "CC CONFIRMATION " +Integer.toString(receiptId));
      }
      else
      { 
        pstmt.setString(3,permit.getMagIdNumber() +"-permit(" + permit.getMagPermitNumber() + ")"+ " Check number " +Integer.toString(receiptId));
      }
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,permit.getMagIdNumber());
      pstmt.setString(6,owner.getOwnerLastName());
      pstmt.setString(7,cart.getPaymentType());
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
   public void insertPermitTransaction(DfbsPermit permit,DfbsOwner owner) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    Map feeMap = this.selectFeesMapping();
     if ((permit.getMagMagType().trim().equals("Type 1")||
               permit.getMagMagType().trim().equals("Type 4")||
               permit.getMagMagType().trim().equals("Type 5"))) {
              Double amount = (Double) feeMap.get(new Integer("2"));
               permit.setAmount(amount.doubleValue());
              }
     if ((permit.getMagMagType().trim().equals("Type 2")||
               permit.getMagMagType().trim().equals("Type 3")||
               permit.getMagMagType().trim().equals("Indoor")) )
             {
               Double amount = (Double) feeMap.get(new Integer("3"));
               permit.setAmount(amount.doubleValue());
             }
     
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,DfbsSQL.SELECT_NEXT_TRANSACTION_ID);
     
      pstmt = conn.prepareStatement(DfbsSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,permit.getAmount());
      pstmt.setString(3,permit.getMagIdNumber() +"-permit(" + permit.getMagPermitNumber() + ") Annual magazine permit fee");
      pstmt.setInt(4,owner.getOwnerId());
      pstmt.setString(5,permit.getMagIdNumber());
      pstmt.setString(6,owner.getOwnerLastName());
      pstmt.setString(7,"Check");
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
  public void computeFees(ShoppingCart cart,String permitNew) throws Exception 
 {
    Map feeMap = this.selectFeesMapping();
    
    List owners = new ArrayList();
    Map mapOwner = cart.getOwnerMap();
    Set ownerkeys = mapOwner.keySet();
    Iterator i = ownerkeys.iterator();
    double cartTotal = 0;
    int cartPermits = 0;
     while(i.hasNext())
    { 
     String key = (String) i.next();
     DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
     
     double ownerAmount = 0;
          List permits = new ArrayList();
          Map mapPermit = owner.getPermitsMap();
          Set permitkeys = mapPermit.keySet();
          
          Iterator j = permitkeys.iterator();
          double permitTotal = 0;
          
          while(j.hasNext()) 
          { 
            String keys = (String) j.next();
            DfbsPermit permit = (DfbsPermit) mapPermit.get(keys); 
             if (permit.getMagIdNumber().equals(""))
            { permitNew="Y";
            }
            else
            { permitNew="N";
            }
             double total = 0;
              
           if ((permit.getMagMagType().trim().equals("Type 1")||
               permit.getMagMagType().trim().equals("Type 4")||
               permit.getMagMagType().trim().equals("Type 5")) &&
               permitNew.equals("Y"))
               
             {
               Double amount = (Double) feeMap.get(new Integer("1"));
               total = total + amount.doubleValue();
               permit.setAmount(amount.doubleValue());
               ownerAmount= ownerAmount + amount.doubleValue();
               cartTotal= cartTotal + amount.doubleValue();
               cartPermits= cartPermits + 1;
             }
             if ((permit.getMagMagType().trim().equals("Type 1")||
               permit.getMagMagType().trim().equals("Type 4")||
               permit.getMagMagType().trim().equals("Type 5")) &&
               permitNew.equals("N"))
             {
               Double amount = (Double) feeMap.get(new Integer("2"));
               total = total + amount.doubleValue();
               permit.setAmount(amount.doubleValue());
               ownerAmount= ownerAmount + amount.doubleValue();
               cartTotal= cartTotal + amount.doubleValue();
               cartPermits= cartPermits + 1;
             }
             if ((permit.getMagMagType().trim().equals("Type 2")||
               permit.getMagMagType().trim().equals("Type 3")||
               permit.getMagMagType().trim().equals("Indoor")) &&
               permitNew.equals("N"))
             {
               Double amount = (Double) feeMap.get(new Integer("3"));
               total = total + amount.doubleValue();
               permit.setAmount(amount.doubleValue());
               ownerAmount= ownerAmount + amount.doubleValue();
               cartTotal= cartTotal + amount.doubleValue();
               cartPermits= cartPermits + 1;
             }
             if ((permit.getMagMagType().trim().equals("Type 2")||
               permit.getMagMagType().trim().equals("Type 3")||
               permit.getMagMagType().trim().equals("Indoor")) &&
               permitNew.equals("Y"))
             {
               Double amount = (Double) feeMap.get(new Integer("4"));
               total = total + amount.doubleValue();
               permit.setAmount(amount.doubleValue());
               ownerAmount= ownerAmount + amount.doubleValue();
               cartTotal= cartTotal + amount.doubleValue();
               cartPermits= cartPermits + 1;
             }
             permits.add(permit);
           }
     owner.setPermits(permits);
     owner.setAmount(ownerAmount);
     owners.add(owner);
     cart.addCount(owner);
     }    
   
   cart.setOwnerList(owners);
   cart.setAmount(cartTotal);
   cart.setTotalPermits(cartPermits);
 }
   
   
    public void insertPermit(DfbsPermit permit,int ownerId,String onlineStatus) throws SQLException, Exception 
  {
  
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
        
    try 
    {
      
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsSQL.INSERT_MAGAZINE);
      int id = this.getId(conn,DfbsSQL.SELECT_NEXT_MAGAZINE_ID);
      StringBuffer sb = new StringBuffer("");
      sb.append("MA" + permit.getMagCounty() + Integer.toString(id));
       permit.setMagIdNumber(sb.toString());
       if ((permit.getMagMagType() !=null && permit.getMagMagType().trim().equals("Type 1")))
       {
         permit.setMagPermitNumber(permit.getMagCounty() +"1"+ Integer.toString(id));
       }
        if ((permit.getMagMagType() !=null && permit.getMagMagType().trim().equals("Type 2")))
       {
         permit.setMagPermitNumber(permit.getMagCounty() +"2"+ Integer.toString(id));
       }
        if ((permit.getMagMagType() !=null && permit.getMagMagType().trim().equals("Type 3")))
       {
         permit.setMagPermitNumber(permit.getMagCounty() +"3"+ Integer.toString(id));
       }
        if ((permit.getMagMagType() !=null && permit.getMagMagType().trim().equals("Type 4")))
       {
         permit.setMagPermitNumber(permit.getMagCounty() +"4"+ Integer.toString(id));
       }
        if ((permit.getMagMagType() !=null && permit.getMagMagType().trim().equals("Type 5")))
       {
         permit.setMagPermitNumber(permit.getMagCounty() +"5"+ Integer.toString(id));
       }
        if ((permit.getMagMagType() !=null && permit.getMagMagType().trim().equals("Indoor")))
       {
         permit.setMagPermitNumber(permit.getMagCounty() +"6"+ Integer.toString(id));
       }
      pstmt.setString(13,permit.getMagAddress1());
      pstmt.setString(14,permit.getMagAddress2());
      pstmt.setString(15,permit.getMagCity());
      pstmt.setString(4,permit.getMagConstruction());
      pstmt.setString(21,permit.getMagCounty());
      pstmt.setString(2,permit.getMagSiteName());
      pstmt.setString(3,permit.getMagDirections());
      pstmt.setString(26,permit.getMagEmail());
      pstmt.setString(12,permit.getMagExpType());
      pstmt.setString(25,permit.getMagFax());
      pstmt.setString(23,permit.getMagFd());
      pstmt.setString(1,permit.getMagIdNumber());
      pstmt.setString(19,permit.getMagMagType());
      pstmt.setInt(5,permit.getMagMinQuantity());
      pstmt.setString(6,permit.getMagNearBldg());
      pstmt.setString(9,permit.getMagNearFactory());
      pstmt.setString(7,permit.getMagNearHighway());
      pstmt.setString(8,permit.getMagNearRailway());
      pstmt.setString(11,permit.getMagPermitYear());
      pstmt.setString(24,permit.getMagPhone());
      pstmt.setString(18,permit.getMagState());
      pstmt.setString(20,permit.getMagTownship());
      pstmt.setString(16,permit.getMagZip());
      pstmt.setString(17,permit.getMagZip2());
      pstmt.setString(10,permit.getMagPermitNumber());  
      pstmt.setString(22,permit.getMagContactPerson());  
      pstmt.setInt(27,ownerId);
      pstmt.setString(28,"New");  
      pstmt.setString(29,permit.getMagComments());  
      pstmt.setString(30,permit.getMagAtfLicense());  
      pstmt.execute();
      pstmt1 = conn.prepareStatement(DfbsSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,permit.getMagIdNumber());
      pstmt1.setString(2,permit.getMagPermitKey());
      pstmt1.setString(3,"Magazine");
       pstmt1.execute(); 
   
      conn.commit();
      
      
    }
    finally 
    {
     try {
        conn.close();
        pstmt1.close();
       pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public void updatePermit(DfbsPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
     
     
      pstmt = conn.prepareStatement(DfbsSQL.UPDATE_MAGAZINE);
      pstmt.clearParameters();
      pstmt.setString(14,permit.getMagExpDateString());
      pstmt.setString(13,permit.getMagIssueDateString());
      pstmt.setString(10,permit.getMagSignedDateString());
      pstmt.setString(16,permit.getMagAddress1());
      pstmt.setString(17,permit.getMagAddress2());
      pstmt.setString(18,permit.getMagCity());
      pstmt.setString(4,permit.getMagConstruction());
      pstmt.setString(24,permit.getMagCounty());
       pstmt.setString(2,permit.getMagSiteName());
      pstmt.setString(3,permit.getMagDirections());
      pstmt.setString(1,permit.getMagEmail());
      pstmt.setString(15,permit.getMagExpType());
      pstmt.setString(28,permit.getMagFax());
      pstmt.setString(26,permit.getMagFd());
      pstmt.setString(32,permit.getMagIdNumber());
      pstmt.setString(22,permit.getMagMagType());
      pstmt.setInt(5,permit.getMagMinQuantity());
      pstmt.setString(6,permit.getMagNearBldg());
      pstmt.setString(9,permit.getMagNearFactory());
      pstmt.setString(7,permit.getMagNearHighway());
      pstmt.setString(8,permit.getMagNearRailway());
      pstmt.setString(12,permit.getMagPermitYear());
      pstmt.setString(27,permit.getMagPhone());
      pstmt.setString(21,permit.getMagState());
      pstmt.setString(23,permit.getMagTownship());
      pstmt.setString(19,permit.getMagZip());
      pstmt.setString(20,permit.getMagZip2());
      pstmt.setString(11,permit.getMagPermitNumber());  
      pstmt.setString(25,permit.getMagContactPerson());
       pstmt.setString(29,permit.getMagOnlineStatus());  
      pstmt.setString(30,permit.getMagComments()); 
       pstmt.setString(31,permit.getMagAtfLicense());  
     
       
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
  public List selectPermitToPrint(String key1,String key2,String key3) throws SQLException, Exception 
  {
  
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
      List permitList = new ArrayList();  
    try 
    {
       conn = getConnection();
      if (key2.equals("All"))
      {
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMIT_TO_PRINT_OWNER);
      }
        if (key2.equals("AllOwner"))
        {
         pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMIT_TO_PRINT_OWNER_ALL);
        }
      if(key2.equals("Permit") )
      {
       if( key3.equals("Single"))
      {
        pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMIT_TO_PRINT_MAGAZINE_SINGLE); 
      }
       else
      {
        pstmt = conn.prepareStatement(DfbsSQL.SELECT_PERMIT_TO_PRINT_MAGAZINE); 
      }
      }
      pstmt.clearParameters();
      pstmt.setString(1,key1);
      if( key3.equals("Single"))
      {
        System.out.println(key3);
      }
      else
      {
      pstmt.setString(2,key3);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { PermitsToPrint permit = new PermitsToPrint();
        permit.setMagIdNumber(rs.getString(1));
        permit.setMagExpDate(rs.getDate(14));
        permit.setMagIssueDate(rs.getDate(13));
        permit.setMagAddress1(rs.getString(16));
        permit.setMagAddress2(rs.getString(17));
        permit.setMagCity(rs.getString(18));
        permit.setMagCounty(rs.getString(24));
        permit.setMagCountyName(rs.getString(25));
        permit.setMagExpType(rs.getString(15));
        permit.setMagMinQuantity(rs.getInt(5));
        permit.setMagState(rs.getString(21));
        permit.setMagTownShip(rs.getString(23));
        permit.setMagZip(rs.getString(19));
        permit.setMagPermitNumber(rs.getString(11));
         if (rs.getString(33) ==null || rs.getString(33).equals(""))
        {
          permit.setOwnerName(rs.getString(36));
        }
        else
        {
          permit.setOwnerName(rs.getString(33));
        }
        permit.setOwnAddress1(rs.getString(38));
        permit.setOwnAddress2(rs.getString(39));
        permit.setOwnCity(rs.getString(40));
        permit.setOwnState(rs.getString(41));
        permit.setOwnZip(rs.getString(42));
        permit.setMagType(rs.getString(22));
        
        permit.setInspector(this.findInsp(permit.getMagCounty()));
        permitList.add(permit);
      }
      
      return permitList;
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
   public void  uploadFile(FormFile oneFile,String magIdNumber) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,SecuritySQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(DfbsSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,"web");
       pstmt.setString(6,magIdNumber);
       pstmt.execute();
       conn.commit();
      
 
    }
    finally 
    {
     try {conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }

    public List selectFileList(DfbsPermit permit) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
        conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_DOCUMENT_NAMES_ALL);
      pstmt.clearParameters();
      if(permit != null) {
        pstmt.setString(1,permit.getMagIdNumber());
        pstmt.setString(2,Integer.toString(permit.getMagOwnerId()));
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { FileNames names = new FileNames();
        names.setFileName(rs.getString(1));
        names.setFileType(rs.getString(2));
        names.setFileDate(rs.getDate(3));
        names.setFileLoadedBy(rs.getString(4));
        names.setFileId(rs.getInt(5));
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
  
 public int downLoad(OutputStream os,int fileId)  throws SQLException, Exception 
 {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = DfbsSQL.SELECT_DOCUMENT; 
   conn = getConnection(); 
   pstmt = conn.prepareStatement(sql);
    if(fileId != 0) {
        pstmt.setInt(1,fileId);
      }
   rs = pstmt.executeQuery(); 
    while(rs.next()) 
   {
   java.sql.Blob blob =  rs.getBlob(1);
    InputStream is = blob.getBinaryStream();
   byte[] buf = new byte[1024];
   int len = -1; 
   while ( (len=is.read(buf,0,1024)) != -1)
   {    os.write(buf,0,len);
        len_total += len; 
   } 
    is.close(); 
   
   }
    return len_total;
   }
   
 
   finally
   { 
     try {
       
        conn.close();
        rs.close();
        pstmt.close();
      } 
     
      catch (Exception e){}
   }
   
  
  } 
   public String findEmail (String magCounty ) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;

    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call FIND_EMAILS_MAG(?,?) }");
      proc.setString(1, magCounty);
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
   public String findInsp (String magCounty ) throws SQLException, Exception 
  {
    Connection conn = null;
    
    CallableStatement proc = null;

    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call FIND_INSP_MAG(?,?) }");
      proc.setString(1, magCounty);
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
    public List selectPermitFees(String permitNumber) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int x =0;
    Connection conn = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_ACCT_DUES);
      pstmt.clearParameters();
      pstmt.setString(1,permitNumber);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        feeDetails f = new feeDetails();
        f.setDue(rs.getString(1));
        f.setAmountPaid(rs.getString(2));
        f.setReceiptNumber(rs.getString(3));
        f.setPostDate(rs.getString(4));
        f.setDescription(rs.getString(5));
         
        list.add(f);
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
   public String getCurrentDate() throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String currentDate ="" ; 
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_CURRENT_DATE);
      pstmt.clearParameters();
       rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
      currentDate=rs.getString(1);
      }
   
      return currentDate;
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
   public List selectPermitsList(Integer ownerId) throws SQLException, Exception 
  {
   Connection conn = null;
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_NEW_PERMITS);
      pstmt.clearParameters();
      if(ownerId != 0) {
        pstmt.setInt(1,ownerId);
      }
       rs = pstmt.executeQuery();
      while(rs.next()) 
      { DfbsPermit permit = new DfbsPermit();
        permit.setMagIdNumber(rs.getString(1));
        permit.setMagIssueDate(rs.getDate(2)); 
        permit.setMagOnlineStatus(rs.getString(3));
        permit.setMagCounty(rs.getString(4));  
        permit.setMagSignedDate(rs.getDate(5));  
       list.add(permit);
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
   public int selectPermitFeeDue(String permitNumber) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int x =0;
    Connection conn = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_FEE_DUE);
      pstmt.clearParameters();
      pstmt.setString(1,permitNumber);
      rs = pstmt.executeQuery();
      int feeDue =100;
      while(rs.next()) 
      { if ((rs.getString(5).indexOf("CONFIRMATION")) >0)
        {
          feeDue=0;
        }
        else
        {
          if (rs.getString(3) !=null && rs.getString(3).length()>4)
          {
            feeDue=0;
          }
          else
          {
            feeDue =Integer.parseInt(rs.getString(1));
          }
        }
               
      }
      
      return feeDue;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public String getPrintKey(String keyPart) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String printKey ="" ; 
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_PRINT_KEY);
      pstmt.clearParameters();
      pstmt.setString(1,keyPart);
       rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
      printKey=rs.getString(1);
      }
   
      return printKey;
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
   public void updatePermitPrintKey(String magPermitNumber,String permitKey,String permitType) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      if (permitType.equals("All"))
      {
        pstmt = conn.prepareStatement(DfbsSQL.UPDATE_PRINT_KEY_OWNER);
      }
      else
      {
      pstmt = conn.prepareStatement(DfbsSQL.UPDATE_PRINT_KEY_MAGAZINE);
      }
      pstmt.clearParameters();
      pstmt.setString(1,permitKey);
      pstmt.setString(2,magPermitNumber);
     
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
   public int selectPermitFeeStatus(String idNumber) throws SQLException, Exception 
  {
  
    DfbsPermit permit = new DfbsPermit();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    int feeCheck=0;
    try 
    {  conn = getConnection();
      conn.setAutoCommit(false);
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_FEE_DUE_CHECK);
      pstmt.clearParameters();
      pstmt.setString(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { feeCheck=rs.getInt(1);
              
      }
     return feeCheck;
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
  public  void deleteFile(int fileId)   throws SQLException, Exception 
  {
  Connection conn = null;
  PreparedStatement pstmt = null;
  
  try
  { conn = getConnection();
   pstmt = conn.prepareStatement(InspectionsSQL.DELETE_DOCUMENT);
   pstmt.clearParameters();
    pstmt.setInt(1,fileId);
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
