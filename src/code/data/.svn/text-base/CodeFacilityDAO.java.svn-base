package code.data;
import code.to.*;
import code.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;
public class CodeFacilityDAO  extends HsDAO
{
  public CodeFacilityDAO()
  {
      super();
   }
   
   public CodeFacilityDAO(HsDataSource pool)
  {
      super(pool);
   }
   protected static List selectFacilities(Connection conn,int manufacturerEntityId,CodeManufacturer manufacturer) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
      pstmt = conn.prepareStatement(CodeSQL.SELECT_FACILITY);
      pstmt.clearParameters();
      pstmt.setInt(1,manufacturerEntityId);
     //  pstmt.setString(2,ownerId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        CodeFacility facility = new CodeFacility();
        facility.setFacilityId(rs.getInt(1));
        facility.setFacilityEntityId(rs.getInt(2));
        facility.setFacilityInspector(rs.getInt(3));
        facility.setFacilityCompany(rs.getInt(4));
        facility.setFacilityName(rs.getString(5));
        facility.setFacilityAddress1(rs.getString(6));
        facility.setFacilityAddress2(rs.getString(7));
        facility.setFacilityCity(rs.getString(8));
        facility.setFacilityState(rs.getString(9));
        facility.setFacilityCountry(rs.getString(10));
        facility.setFacilityZip(rs.getString(11));
        facility.setFacilityCounty(rs.getString(12));
        facility.setFacilityStatus(rs.getString(13));
        facility.setFacilitySaa1(rs.getInt(14));
        facility.setFacilityTypeHud(rs.getString(15));
        facility.setFacilityTypeMoc(rs.getString(16));
        facility.setFacilityTypeMor(rs.getString(17));
        facility.setFacilityTypeMob(rs.getString(18));
        facility.setFacilityTypePan(rs.getString(19));
        facility.setFacilitySaa2(rs.getInt(20));
 
        
        list.add(facility);
        facility.setPersonFList(CodePersonDAO.selectPersons(conn,facility.getFacilityId(),"F",null,facility));
        manufacturer.addFacility(facility);
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
  
 
  public CodeFacility selectFacility(int facilityId) throws SQLException, Exception 
  {
    CodeFacility facility = new CodeFacility();
    Connection conn = null;
    
    try 
    {
      conn = getConnection();
      facility = selectFacility(conn,facilityId);
      facility.setPersonFList(CodePersonDAO.selectPersons(conn,facilityId,"F",null,facility));
      return facility;
    }
    finally 
    {
     try {
        conn.close();
      } catch (Exception e) {}
    }
  }
  public static CodeFacility selectFacility(Connection conn,int facilityId) throws SQLException, Exception 
  {
  
    CodeFacility facility = new CodeFacility();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(CodeSQL.SELECT_FACILITY_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,facilityId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        facility.setFacilityId(rs.getInt(1));
        facility.setFacilityEntityId(rs.getInt(2));
        facility.setFacilityInspector(rs.getInt(3));
        facility.setFacilityCompany(rs.getInt(4));
        facility.setFacilityName(rs.getString(5));
        facility.setFacilityAddress1(rs.getString(6));
        facility.setFacilityAddress2(rs.getString(7));
        facility.setFacilityCity(rs.getString(8));
        facility.setFacilityState(rs.getString(9));
        facility.setFacilityCountry(rs.getString(10));
        facility.setFacilityZip(rs.getString(11));
        facility.setFacilityCounty(rs.getString(12));
        facility.setFacilityStatus(rs.getString(13));
        facility.setFacilitySaa1(rs.getInt(14));
        facility.setFacilityTypeHud(rs.getString(15));
        facility.setFacilityTypeMoc(rs.getString(16));
        facility.setFacilityTypeMor(rs.getString(17));
        facility.setFacilityTypeMob(rs.getString(18));
        facility.setFacilityTypePan(rs.getString(19));
        facility.setFacilitySaa2(rs.getInt(20));

      }
      return facility;
    }
    finally 
    {
     try {
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
      pstmt = conn.prepareStatement(CodeSQL.SELECT_FEES);
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
  
  public void insertPermitTransaction(CodeFacility facility) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,CodeSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(CodeSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,facility.getAmount());
      pstmt.setString(3,facility.getTransactionDescription());
      pstmt.setInt(4,facility.getReceiptId());
      pstmt.setInt(5,facility.getFacilityId());
      pstmt.setString(6,facility.getFacilityName());
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
  
  public void computeFees(CodeManufacturer manufacturer) throws Exception 
 {
    Map feeMap = this.selectFeesMapping();
    
   List facilities = new ArrayList();
    
    Map mapFacility = manufacturer.getFacilityMap();
    Set facilitykeys = mapFacility.keySet();
    
    Iterator i = facilitykeys.iterator();
    double total = 0;
    int totalSeals = 0;
    while(i.hasNext())
    {
     String key = (String) i.next();
     CodeFacility facility = (CodeFacility) mapFacility.get(key); 
           Double amount = (Double) feeMap.get(new Integer("1"));
           total = total + amount.doubleValue()* facility.getPseals()+ amount.doubleValue()* facility.getMseals();
           totalSeals=facility.getPseals() + facility.getMseals();
           facility.setAmount(amount.doubleValue()* facility.getPseals()+ amount.doubleValue()* facility.getMseals());
           facilities.add(facility);
      
    }
       
   manufacturer.setFacilityList(facilities);
   manufacturer.setTotalSealsCount(totalSeals);
   manufacturer.setTotalAmountSeals(total);
 }
 
 public CodeFacility selectFacilityPerson(int facilityId) throws SQLException, Exception 
  {
    CodeFacility facility = new CodeFacility();
    Connection conn = null;
    
    try 
    {
      conn = getConnection();
      facility = selectFacility(conn,facilityId);
      return facility;
    }
    finally 
    {
     try {
        conn.close();
      } catch (Exception e) {}
    }
  }

   
  public SealOrder selectOrderSeals(int orderId) throws SQLException, Exception 
  {
   Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    SealOrder seal = new SealOrder();
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_SEALS_BY_ORDER);
      pstmt.clearParameters();
      pstmt.setInt(1,orderId);
      rs = pstmt.executeQuery();
     
       while(rs.next()) 
      { 
       seal.setPseals(rs.getInt(3));
      seal.setMseals(rs.getInt(2));
      seal.setFacilityId(rs.getInt(1));
      seal.setOrderDate(rs.getDate(4));
      seal.setOrderId(rs.getInt(5));
      seal.setMsealsFrom(rs.getInt(6));
      seal.setMsealsTo(rs.getInt(7));
      seal.setPsealsFrom(rs.getInt(8));
      seal.setPsealsTo(rs.getInt(9));
      
      }    
      return(seal);
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
   public List selectFacilityOrders(CodeFacility facility) throws SQLException, Exception 
  {
   Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    List orders = new ArrayList();
    try 
    {
    conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_FACILITY_SEALS );
      pstmt.clearParameters();
      pstmt.setInt(1,facility.getFacilityId());
      rs = pstmt.executeQuery();
     while(rs.next()) 
      { SealOrder seal = new SealOrder();
       seal.setPseals(rs.getInt(3));
      seal.setMseals(rs.getInt(2));
      seal.setFacilityId(rs.getInt(1));
      seal.setOrderDate(rs.getDate(4));
      seal.setOrderId(rs.getInt(5));
      seal.setMsealsFrom(rs.getInt(6));
      seal.setMsealsTo(rs.getInt(7));
      seal.setPsealsFrom(rs.getInt(8));
      seal.setPsealsTo(rs.getInt(9));
      
       orders.add(seal);
      }
      return(orders);
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
   public void updateSealOrder (SealOrder seal) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
     
      pstmt = conn.prepareStatement(CodeSQL.UPDATE_FACILITY_SEALS);
      pstmt.clearParameters();
       pstmt.setInt(1,seal.getMsealsFrom());
       pstmt.setInt(2,seal.getMsealsTo());
       pstmt.setInt(3,seal.getPsealsFrom());
       pstmt.setInt(4,seal.getPsealsTo());
       pstmt.setInt(5,seal.getOrderId());
      
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
   public void createSealUsageRecord (String sealNumber,SealOrder seal,CodeManufacturer manufacturer,CodeFacility facility) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
     
      pstmt = conn.prepareStatement(CodeSQL.INSERT_CODE_SEAL);
      pstmt.clearParameters();
       pstmt.setString(1,sealNumber);
       pstmt.setInt(2,manufacturer.getManufacturerNameId());
       pstmt.setInt(3,facility.getFacilityId());
       pstmt.setInt(4,facility.getFacilityInspector());
       pstmt.setInt(5,facility.getFacilityCompany());
       pstmt.setInt(6,manufacturer.getManufacturerNameId());
       pstmt.setString(7,seal.getOrderDateString());
       pstmt.setInt(8,seal.getOrderId());
      
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
   public  List selectCodeSealUsage(int orderId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CODE_SEAL_BY_ORDER);
      pstmt.clearParameters();
      pstmt.setInt(1,orderId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        SealUsage seal = new SealUsage();
        seal.setFacilityId(rs.getInt(2));
        seal.setSealNumber(rs.getString(1));
        seal.setInspectorId(rs.getInt(3));
        seal.setCompanyId(rs.getInt(4));
        seal.setSealUnitNumber(rs.getString(7));
        seal.setSealRelNumber(rs.getString(8));
        seal.setSealUsedDate(rs.getDate(9));
        seal.setSealUpdateDate(rs.getDate(10));
        seal.setSealInspDate(rs.getDate(11));
        seal.setOrderId(rs.getInt(12));
        seal.setInspectorName(this.selectInspectorName(seal.getInspectorId()));
        seal.setCompanyName(this.selectCompanyName(seal.getCompanyId()));
        list.add(seal);
       
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
    
     public  String selectInspectorName(int inspectorId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String inspectorName ="";
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_INSPECTOR_NAME);
      pstmt.clearParameters();
      pstmt.setInt(1,inspectorId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        
        inspectorName= rs.getString(1);
    
       
      }
      
      return inspectorName;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public  String selectCompanyName(int companyId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String companyName ="";
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_COMPANY_NAME);
      pstmt.clearParameters();
      pstmt.setInt(1,companyId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        
        companyName= rs.getString(1);
    
       
      }
      
      return companyName;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
  public  SealUsage selectSealUsage(String sealNumber) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CODE_SEAL_BY_NUMBER);
      pstmt.clearParameters();
      pstmt.setString(1,sealNumber);
      rs = pstmt.executeQuery();
      SealUsage seal = new SealUsage();
      while(rs.next()) 
      {
        
        seal.setFacilityId(rs.getInt(2));
        seal.setSealNumber(rs.getString(1));
        seal.setInspectorId(rs.getInt(3));
        seal.setCompanyId(rs.getInt(4));
        seal.setSealUnitNumber(rs.getString(7));
        seal.setSealRelNumber(rs.getString(8));
        seal.setSealUsedDate(rs.getDate(9));
        seal.setSealUpdateDate(rs.getDate(10));
        seal.setSealInspDate(rs.getDate(11));
        seal.setOrderId(rs.getInt(12));
        seal.setInspectorName(this.selectInspectorName(seal.getInspectorId()));
        seal.setCompanyName(this.selectCompanyName(seal.getCompanyId()));
        
      }
      
      return seal;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public void updateSealUsage (SealUsage seal) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
     
      pstmt = conn.prepareStatement(CodeSQL.UPDATE_CODE_SEAL);
      pstmt.clearParameters();
       pstmt.setString(1,seal.getSealUnitNumber());
       pstmt.setString(2,seal.getSealRelNumber());
       pstmt.setString(3,seal.getSealUsedDateString());
       pstmt.setString(4,seal.getSealUpdateDateString());
      pstmt.setString(5,seal.getSealInspDateString());
      pstmt.setString(6,seal.getSealNumber());
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
  public int selectSealOrderStatus(int orderId) throws SQLException, Exception 
  {
   Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
  int lStatus =0;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CODE_SEAL_ORDER_STATUS);
      pstmt.clearParameters();
      pstmt.setInt(1,orderId);
      rs = pstmt.executeQuery();
     
       while(rs.next()) 
      { 
      lStatus= rs.getInt(1);
      }    
      return(lStatus);
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
