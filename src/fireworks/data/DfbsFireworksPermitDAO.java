package fireworks.data;
import fireworks.to.*;
import fireworks.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import main.to.*;
import hs.util.*;

import hs.data.*;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
public class DfbsFireworksPermitDAO extends HsDAO
{
  
   
  
   public DfbsFireworksPermitDAO()
   {
      super();
   }
  
   public  DfbsFireworksPermitDAO(HsDataSource pool)
   {
    super(pool);
    
   }
   
 
 
  protected static List selectPermits(Connection conn,String ownerId,String permitYear, String renewYear) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMITS);
      pstmt.clearParameters();
      pstmt.setString(1,ownerId);
      pstmt.setString(4,ownerId);
      pstmt.setString(2,renewYear);
      pstmt.setString(3,renewYear);
       pstmt.setString(5,renewYear);
      pstmt.setString(6,renewYear);
      rs = pstmt.executeQuery();
       while(rs.next()) 
         { 
        DfbsFireworksPermit p = new DfbsFireworksPermit();
        p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setPermitYear(rs.getInt(3));
        
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
        p.setAffidavit(rs.getString(27)); // need to call this first
        if(p.isRetail() && rs.getString(9)!=null) 
        { p.setRetailType(rs.getString(9));
        }
         String catId = rs.getString(10);
        if(catId == null || catId.equals("0")) 
        {
          p.setCategoryId(0);
        } 
        else 
        {
          p.setCategoryId(Integer.parseInt(catId));
        }
        p.setManufacturer(rs.getString(11));
        p.setWholesaler(rs.getString(12));
        p.setDistributor(rs.getString(13));
        p.setImporter(rs.getString(14));
        p.setCountyId(rs.getString(15));
        p.setCounty(rs.getString(16));
        p.setContactPerson(rs.getString(17));
        p.setPhoneNumber(rs.getString(18));
        p.setFaxNumber(rs.getString(19));
        p.setEmailAddress(rs.getString(20));
        p.setMerchantNumber(rs.getString(21));
        p.setApplicationDate(rs.getDate(22));
        p.setApplicationYear(rs.getInt(23));
        p.setInspectDate(rs.getDate(24));
        p.setOpenDate(rs.getDate(25));
        p.setHoursOfOperation(rs.getString(26));
         p.setPermitTypeOnSelect();
        p.setCurrentYear(Integer.parseInt(permitYear));
        String inspectorName=findInspectorName(p.getCounty(),p.getZip(),conn);
        p.setInspectorName(inspectorName);
        list.add(p);
      }
      
      return list;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   protected static List selectPermitsCounty(Connection conn,int ownerId,String county,String recFlag) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    { 
       if (recFlag == null || recFlag.equals("PENDING"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMITS_COUNTY_APPROVAL +" and ONLINE_STATUS='COUNTY'");
      }
        if (recFlag == null || recFlag.equals("APPROVED"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMITS_COUNTY_APPROVAL +" and ONLINE_STATUS='APPROVED'");
      }
        if (recFlag == null || recFlag.equals("DENIED"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMITS_COUNTY_APPROVAL +" and ONLINE_STATUS='DENIED'");
      }
        if (recFlag == null || recFlag.equals("ALL"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMITS_COUNTY_APPROVAL);
      }
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,county);
      rs = pstmt.executeQuery();
       while(rs.next()) 
         { 
        DfbsFireworksPermit p = new DfbsFireworksPermit();
        p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setPermitYear(rs.getInt(3));
        
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
        p.setAffidavit(rs.getString(27)); // need to call this first
        if(p.isRetail() && rs.getString(9)!=null) 
        { p.setRetailType(rs.getString(9));
        }
         String catId = rs.getString(10);
        if(catId == null || catId.equals("0")) 
        {
          p.setCategoryId(0);
        } 
        else 
        {
          p.setCategoryId(Integer.parseInt(catId));
        }
        p.setManufacturer(rs.getString(11));
        p.setWholesaler(rs.getString(12));
        p.setDistributor(rs.getString(13));
        p.setImporter(rs.getString(14));
        p.setCountyId(rs.getString(15));
        p.setCounty(rs.getString(16));
        p.setContactPerson(rs.getString(17));
        p.setPhoneNumber(rs.getString(18));
        p.setFaxNumber(rs.getString(19));
        p.setEmailAddress(rs.getString(20));
        p.setMerchantNumber(rs.getString(21));
        p.setApplicationDate(rs.getDate(22));
        p.setApplicationYear(rs.getInt(23));
        p.setInspectDate(rs.getDate(24));
        p.setOpenDate(rs.getDate(25));
        p.setHoursOfOperation(rs.getString(26));
        p.setPermitComments(rs.getString(28));
         p.setPermitTypeOnSelect();
        String inspectorName=findInspectorName(p.getCounty(),p.getZip(),conn);
        p.setInspectorName(inspectorName);
        list.add(p);
      }
      
      return list;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
  
 
  
  public List selectPermitToPrint(boolean consumer) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
    
      conn = getConnection();
      if(consumer) {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_CONSUMER_PERMITS_TO_PRINT);
      } 
      else 
      {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_WHOLESALER_PERMITS_TO_PRINT);
      }
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsFireworksPermit permit = new DfbsFireworksPermit();
        permit.setPermitNumber(rs.getString(1));
        permit.setIssueDate(rs.getDate(2));
        permit.setPermitYear(rs.getInt(3));
        permit.setOwnerName(rs.getString(4));
        permit.setStreet1(rs.getString(5));
        permit.setStreet2(rs.getString(6));
        permit.setCity(rs.getString(7));
        permit.setState(rs.getString(8));
        permit.setZip(rs.getString(9));
        permit.setOwnerId(rs.getInt(10));
        permit.setRetailerType(rs.getString(11));
        DfbsOwner owner = DfbsFireworksOwnerDAO.selectOwner(conn,
          Integer.toString(permit.getOwnerId()));
        permit.setOwner(owner);
        list.add(permit);
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
  
  public List selectPermitToPrint(boolean consumer,String permitNumber) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
    
      conn = getConnection();
      if(consumer) {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_CONSUMER_PERMITS_TO_PRINT_BY_ID);
      } 
      else 
      {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_WHOLESALER_PERMITS_TO_PRINT_BY_ID);
      }
      pstmt.clearParameters();
      pstmt.setString(1,permitNumber);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsFireworksPermit permit = new DfbsFireworksPermit();
        permit.setPermitNumber(rs.getString(1));
        permit.setIssueDate(rs.getDate(2));
        permit.setPermitYear(rs.getInt(3));
        permit.setOwnerName(rs.getString(4));
        permit.setStreet1(rs.getString(5));
        permit.setStreet2(rs.getString(6));
        permit.setCity(rs.getString(7));
        permit.setState(rs.getString(8));
        permit.setZip(rs.getString(9));
        permit.setOwnerId(rs.getInt(10));
        permit.setRetailerType(rs.getString(11));
        if (permit.getRetailerType() !=null && permit.getRetailerType().equals("New")) {
          String inspSpkStatus=this.findInspSpkStatus(permit, conn);
          if (inspSpkStatus !=null && inspSpkStatus.equals("G")) {
            permit.setRetailerType(permit.getRetailerType()+",1000 lbs");
          }
        }
        DfbsOwner owner = DfbsFireworksOwnerDAO.selectOwner(conn,
          Integer.toString(permit.getOwnerId()));
        permit.setOwner(owner);
        list.add(permit);
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
 
  
  public int insertPermit(DfbsFireworksPermit permit, String permitYear) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      String appDate = this.selectApplicationDate(permitYear);
      permit.setApplicationDate(appDate);
      int type = permit.getPermitType();
      if(type == DfbsFireworksPermit.TYPE_WHOLESALE) 
      {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_WHOLESALE_PERMIT);
      } 
      else 
      { 
         pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_CONSUMER_PERMIT);
      }
      
      int id = this.getId(conn,DfbsFireworksSQL.SELECT_NEXT_PERMIT_ID);
      permit.createPermitNumber(id);
      pstmt.clearParameters();
      pstmt.setString(1,permit.getPermitNumber());
      pstmt.setInt(2,permit.getOwnerId());
      pstmt.setString(3,permit.getStreet1());
      pstmt.setString(4,permit.getStreet2());
      pstmt.setString(5,permit.getCity());
      pstmt.setString(6,permit.getState());
      pstmt.setString(7,permit.getZip());
      pstmt.setString(8,permit.getCountyId());
      
      if(type == DfbsFireworksPermit.TYPE_WHOLESALE) 
      {
        pstmt.setString(9,permit.getPhoneNumber());
        pstmt.setString(10,permit.getFaxNumber());
        pstmt.setString(11,permit.getEmailAddress());
        pstmt.setDate(12,new java.sql.Date(permit.getOpenDate().getTime()));
        pstmt.setString(13,permit.getHoursOfOperation());
        pstmt.setString(14,permit.getMerchantNumber());
      //  pstmt.setString(14,appDate); 
      
      } 
      else 
      {    if(permit.getPermitNumber().substring(0,3).equals("FWT")) 
        {   
           pstmt.setString(9,permit.getRetailTypeString());
        } 
        else
        {
               pstmt.setString(9,"");
        }
        pstmt.setString(10,permit.getContactPerson());
        pstmt.setString(11,permit.getPhoneNumber());
        pstmt.setString(12,permit.getFaxNumber());
        pstmt.setString(13,permit.getEmailAddress());
        pstmt.setString(14,permit.getMerchantNumber());
        pstmt.setDate(15,new java.sql.Date(permit.getInspectDate().getTime()));
        pstmt.setDate(16,new java.sql.Date(permit.getOpenDate().getTime()));
        pstmt.setString(17,permit.getHoursOfOperation());
        //  pstmt.setString(18,appDate);
      }
      
      
      
      
      
      pstmt.execute();
      
      if(type == DfbsFireworksPermit.TYPE_WHOLESALE) 
      {
        insertCategory(conn,permit);
      }
      pstmt1 = conn.prepareStatement(DfbsFireworksSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,permit.getPermitNumber());
      pstmt1.setString(2,permit.getApplicationKey());
      pstmt1.setString(3,"Fireworks");
       pstmt1.execute();
      
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
        pstmt1.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public void updatePermit(DfbsFireworksPermit permit, String permitYear) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      String appDate = this.selectApplicationDate(permitYear);
      permit.setApplicationDate(appDate);
      int type = permit.getPermitType();
      if(type == DfbsFireworksPermit.TYPE_WHOLESALE) 
      {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_WHOLESALE_PERMIT);
      } 
      else 
      {
         pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_CONSUMER_PERMIT);
      }
      pstmt.clearParameters();
      if(type == DfbsFireworksPermit.TYPE_WHOLESALE) 
      {
        pstmt.setString(1,permit.getZip());
        pstmt.setString(2,permit.getPhoneNumber());
        pstmt.setString(3,permit.getFaxNumber());
        pstmt.setString(4,permit.getEmailAddress());
        pstmt.setDate(5,new java.sql.Date(permit.getOpenDate().getTime()));
        pstmt.setString(6,permit.getHoursOfOperation());
        pstmt.setString(7,permit.getMerchantNumber());
        pstmt.setString(8,permit.getPermitNumber());
      //  pstmt.setString(7,appDate);
      } 
      else 
      {
        //"set SALES_ADDRESS1=?,SALES_ADDRESS2=?,SALES_CITY=?,SALES_STATE=?,SALES_ZIP=?, " +
        pstmt.setString(1,permit.getStreet1());
        pstmt.setString(2,permit.getStreet2());
        pstmt.setString(3,permit.getCity());
        pstmt.setString(4,permit.getState());
         pstmt.setString(5,permit.getZip());
        
         if(permit.getPermitNumber().substring(0,3).equals("FWT")) 
        {
          
          pstmt.setString(6,permit.getRetailTypeString());
        } 
        else
        {
          pstmt.setString(6,"");
        }
        pstmt.setString(7,permit.getContactPerson());
        pstmt.setString(8,permit.getPhoneNumber());
        pstmt.setString(9,permit.getFaxNumber());
        pstmt.setString(10,permit.getEmailAddress());
        pstmt.setString(11,permit.getMerchantNumber());
        pstmt.setDate(12,new java.sql.Date(permit.getInspectDate().getTime()));
        pstmt.setDate(13,new java.sql.Date(permit.getOpenDate().getTime()));
        pstmt.setString(14,permit.getHoursOfOperation());
        pstmt.setString(15,permit.getPermitNumber());
       //  pstmt.setString(15,appDate);
      }
      
       
      pstmt.execute();
      
       if(type == DfbsFireworksPermit.TYPE_WHOLESALE && permit.getCategoryId() == 0) 
      {
        insertCategory(conn,permit);
      } 
      else if(type == DfbsFireworksPermit.TYPE_WHOLESALE && permit.getCategoryId() > 0) 
      {
        updateCategory(conn,permit);
      }
      
      
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
  
  public Map selectFeesMapping() throws SQLException, Exception 
  {
    Map map = new HashMap();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_FEES);
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
  
  
  private static void insertCategory(Connection conn, DfbsFireworksPermit permit) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    try 
    {
      int categoryId = HsDAO.getId(conn,DfbsFireworksSQL.SELECT_NEXT_CATEGORY);
      permit.setCategoryId(categoryId);
      pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_CATEGORY);
      pstmt.clearParameters();
      pstmt.setInt(1,permit.getCategoryId());
      pstmt.setString(2,permit.getManufacturer());
      pstmt.setString(3,permit.getWholesaler());
      pstmt.setString(4,permit.getDistributor());
      pstmt.setString(5,permit.getImporter());
      pstmt.setString(6,permit.getPermitNumber());
      pstmt.execute();
    } 
    finally 
    {
     try {
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
  private static void updateCategory(Connection conn, DfbsFireworksPermit permit) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_CATEGORY);
      pstmt.clearParameters();
      pstmt.setString(1,permit.getManufacturer());
      pstmt.setString(2,permit.getWholesaler());
      pstmt.setString(3,permit.getDistributor());
      pstmt.setString(4,permit.getImporter());
      pstmt.setInt(5,permit.getCategoryId());
      pstmt.execute();
    } 
    finally 
    {
     try {
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
  
  public List selectRetailStandFees() throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_RETAIL_STAND_FEES);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      {
        DfbsFee fee = new DfbsFee();
        fee.setDescription(rs.getString(1));
        fee.setAmount(rs.getDouble(2));
        fee.setOtherAmount(rs.getDouble(3));
        setRetailStandIndex(fee);
        
        list.add(fee);
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
    
    return list;
  }
  
   private static void setRetailStandIndex(DfbsFee fee) throws Exception 
  {
    String desc = fee.getDescription();
    if(desc == null) return;
    
    
    boolean startFound = false;
    try {
    
    
      String[] tokens = desc.split("-");
      if(tokens.length == 2) 
      {
        String[] start = tokens[0].split(" ");
        String[] end = tokens[1].split(" ");
        fee.setStartIndex(Integer.parseInt(start[start.length - 1]));
        fee.setEndIndex(Integer.parseInt(end[0]));
        return;
      } 
      
      tokens = desc.split(" Over ");
      if(tokens.length == 2) 
      {
        String[] start = tokens[1].split(" ");
        fee.setStartIndex(Integer.parseInt(start[0]));
        fee.setEndIndex(-1);
      }
    } 
    catch (Exception e) 
    {
      throw new Exception("INVALID_FEE_FROM_DATABASE");
    }
    
    
  }
  
 
  
 public void insertPermitTransaction(DfbsFireworksPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
       
      int id = this.getId(conn,DfbsFireworksSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,permit.getAmount());
      pstmt.setString(3,permit.getTransactionDescription());
      pstmt.setInt(4,permit.getReceiptId());
      pstmt.setString(5,permit.getPermitNumber());
      pstmt.setString(6,permit.getOwnerName());
      //pstmt.setString(7,permit.getApplicationDateString());
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
  
  public void computeFees(DfbsOwner ownerDb,DfbsOwner ownerApplication) throws Exception 
 {
    Map feeMap = this.selectFeesMapping();
    List retailFees = this.selectRetailStandFees();
    
   List permits = new ArrayList();
   List retailStands = new ArrayList();
    
    Map mapPermit = ownerApplication.getPermitsMap();
    Set permitkeys = mapPermit.keySet();
    
    Iterator i = permitkeys.iterator();
    double total = 0;
    boolean kChargedAlready = false;
    
    if(ownerDb.getWholesalePermitsCount() > 0 
    || ownerDb.getRetailTentPermitsCount() > 0 
    || ownerDb.getRetailClassPermitsCount() > 0 ) 
    {
      kChargedAlready = true;
    }
    
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsFireworksPermit permit = (DfbsFireworksPermit) mapPermit.get(key); 
     
     if(permit.getPermitType() != DfbsFireworksPermit.TYPE_RETAIL_STAND) 
     {
       if(kChargedAlready ) {
        Double amount = null;
        if(permit.getPermitType() == DfbsFireworksPermit.TYPE_WHOLESALE) {
          amount = (Double) feeMap.get(new Integer(DfbsFireworksPermit.TYPE_RETAIL_CLASS));
        } 
        else 
        {
          amount = (Double) feeMap.get(new Integer(permit.getPermitType()));
        }
        total = total + amount.doubleValue();
        permit.setAmount(amount.doubleValue());
       } 
       //Ensure retail tent is NOT charged with 1000 first 
       else if(permit.getPermitType() == DfbsFireworksPermit.TYPE_RETAIL_TENT
       && (ownerApplication.getWholesalePermitsCount() > 0 || ownerApplication.getRetailClassPermitsCount() > 0)){
          Double amount = null;
          amount = (Double) feeMap.get(new Integer(permit.getPermitType()));
          total = total + amount.doubleValue();
          permit.setAmount(amount.doubleValue());
       }
       else 
       {
        Double amount = (Double) feeMap.get(new Integer(DfbsFireworksPermit.TYPE_WHOLESALE));
        total = total + amount.doubleValue();
        permit.setAmount(amount.doubleValue());
        kChargedAlready = true;
       }
       permits.add(permit);
     } 
     else 
     {
       permit.setAmount(0);
       retailStands.add(permit);
     }
   }
   
   
   
   if(retailStands.size() > 0) {
    double totalStandsAmount = 
      computeRetailStandFees(retailFees,
        ownerDb.getRetailStandPermitsCount() + ownerApplication.getRetailStandPermitsCount());
    double dbStandsAmount = 
      computeRetailStandFees(retailFees,ownerDb.getRetailStandPermitsCount());
    DfbsFireworksPermit permit = (DfbsFireworksPermit) retailStands.get(0);
    permit.setAmount(totalStandsAmount - dbStandsAmount);
    total = total + (totalStandsAmount - dbStandsAmount);
    permits.addAll(retailStands);
   }
   
   
   ownerApplication.setPermits(permits);
   ownerApplication.setTotalPermitsAmount(total);
 }
 
 private double computeRetailStandFees(List retailFees,int standsCount) 
 {
   if(standsCount > 0) {
    Iterator j = retailFees.iterator();
      while(j.hasNext())
      {
        DfbsFee fee = (DfbsFee) j.next();
        double feeAmount = fee.getFee(standsCount);
        if(feeAmount != 0) 
        {
          
          return feeAmount;
        }
      }
   }
   return 0;
 }
 
  public void  uploadFile(FormFile oneFile,String IdNumber, String IdType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,DfbsFireworksSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(DfbsFireworksSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,"web");
       pstmt.setString(6,IdNumber);
       pstmt.setString(7,IdType);
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

    public List selectFileList(String IdNumber,String IdType) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
   
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_DOCUMENT_NAMES_ALL);
       pstmt.clearParameters();
     
      if(IdNumber != null) {
        pstmt.setString(1,IdNumber);
        pstmt.setString(2,IdType);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
        FileNames names = new FileNames();
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
   String sql = DfbsFireworksSQL.SELECT_DOCUMENT; 
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
   public String  getUploadFileName(String connectorId,String connectorType) throws SQLException, Exception
  {
    ResultSet rs = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String fileName="NoName";
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_DOCUMENT_NAME);
      pstmt.clearParameters();
      pstmt.setString(1,connectorId);
      pstmt.setString(2,connectorType);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        fileName=(rs.getString(1));
     
      }
    
      return fileName;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
 public DfbsFireworksPermit selectPermit(String permitType,String permitNumber ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     Connection conn = null;
    
    try 
    {  conn = getConnection();
      if(permitType.equals("Consumer")) {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMIT_BY_ID);
      } 
      else 
      {
        pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_PERMIT_BY_ID);
      }
      pstmt.clearParameters();
      pstmt.setString(1,permitNumber);
      pstmt.setString(2,permitNumber);
      rs = pstmt.executeQuery();
       
     DfbsFireworksPermit p = new DfbsFireworksPermit();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        
         p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setPermitYear(rs.getInt(3));
        
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
       
        p.setAffidavit(rs.getString(27)); // need to call this first
        if(p.isRetail()) 
        {
          p.setRetailType(rs.getString(9));
        }
        
        String catId = rs.getString(10);
        if(catId == null) 
        {
          p.setCategoryId(0);
        } 
        else 
        {
          p.setCategoryId(Integer.parseInt(catId));
        }
        
        p.setManufacturer(rs.getString(11));
        p.setWholesaler(rs.getString(12));
        p.setDistributor(rs.getString(13));
        p.setImporter(rs.getString(14));
        p.setCountyId(rs.getString(15));
        p.setCounty(rs.getString(16));
        p.setContactPerson(rs.getString(17));
        p.setPhoneNumber(rs.getString(18));
        p.setFaxNumber(rs.getString(19));
        p.setEmailAddress(rs.getString(20));
        p.setMerchantNumber(rs.getString(21));
        p.setApplicationDate(rs.getDate(22));
        p.setApplicationYear(rs.getInt(23));
        p.setInspectDate(rs.getDate(24));
        p.setOpenDate(rs.getDate(25));
        p.setHoursOfOperation(rs.getString(26));
        p.setPermitTypeOnSelect();
         }
     
      return p;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
   public String findEmail (String county,String zip ) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call FIND_EMAILS_ENTR(?,?,?) }");
      proc.setString(1, county);
      proc.setString(2, zip);
      proc.registerOutParameter(3,java.sql.Types.VARCHAR);
      proc.execute();

     return(proc.getString(3));
      
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
   protected static String findInspectorName (String county,String zip ,Connection conn) throws SQLException, Exception 
  {
     CallableStatement proc = null;
    try 
    {  
      
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call FIND_INSPECTOR_NAME_ENTR(?,?,?) }");
      proc.setString(1, county);
      proc.setString(2, zip);
      proc.registerOutParameter(3,java.sql.Types.VARCHAR);
      proc.execute();
     
     return(proc.getString(3));
      
    } catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try { proc.close();
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
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_ACCT_DUES);
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
  
   public String selectApplicationDate(String permitYear) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String appDate ="";
    try 
    {
   
    
      conn = getConnection();
      pstmt = conn.prepareStatement(" select (to_number(to_char(sysdate,'yyyy'))- to_number("+permitYear+")),to_char(sysdate,'mm/dd/yyyy') from dual");
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        double appDateTest =rs.getDouble(1);
        if (appDateTest >0 )
        {
          appDate =rs.getString(2);
        }
        else
       {
          appDate ="01/01/"+permitYear;
        }
      }
      
      return appDate;
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
  protected static String findInspSpkStatus (DfbsFireworksPermit permit,Connection conn) throws SQLException, Exception 
  {
   CallableStatement proc = null;
   try 
   {  
     
     conn.setAutoCommit(false);
     proc = conn.prepareCall("{ call find_insp_Sprinkler_Status(?,?) }");
     proc.setString(1, permit.getPermitNumber());
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
   
     } catch (Exception e) {}
   }
  }
   public void updatePermitCountyApproval(String permitNumber,String onlineStatus,String permitComments) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
         pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_CONSUMER_PERMIT_COUNTY_APPROVAL);
         pstmt.clearParameters();
         pstmt.setString(1,onlineStatus);
         pstmt.setString(3,permitNumber);
         pstmt.setString(2,permitComments);
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
