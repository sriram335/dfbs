package aepermits.data;

import  aepermits.to.*;
import  aepermits.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import main.to.*;
import hs.data.*;

public class DfbsEntrPermitDAO extends HsDAO
{
  
   
  
   public DfbsEntrPermitDAO()
   {
      super();
   }
  
   public  DfbsEntrPermitDAO(HsDataSource pool)
   {
    super(pool);
    
   }
  protected static List selectPermits(Connection conn,String ownerId ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {      
   
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMITS);
      pstmt.clearParameters();
      pstmt.setInt(1,Integer.parseInt(ownerId));
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsEntrPermit p = new DfbsEntrPermit();
        p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setPermitYear(rs.getInt(3));
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
        p.setCounty(rs.getString(9));
        p.setPermitType(rs.getString(10));
        p.setContactPerson(rs.getString(11));
        p.setPhoneNumber(rs.getString(12));
        p.setFaxNumber(rs.getString(13));
        p.setEmailAddress(rs.getString(14));
        p.setApplicationDate(rs.getDate(15));
        p.setActualLocation(rs.getString(16));
        p.setFacilityDescription(rs.getString(17));
        p.setMaximumOccupancy(rs.getString(18));
        p.setEventDate(rs.getDate(19));
        p.setEventTime(rs.getString(20));
        p.setEventName(rs.getString(21));
        p.setIntendedOccupantLoad(rs.getString(22));
        p.setFeeExempt(rs.getString(23));
        int permitStatus=(rs.getInt(24));
        p.setOwnerId(rs.getInt(25));  
        if (permitStatus <=0)
        {
          p.setStatus("EXPIRED");
        }
        if (permitStatus >=0)
        {
          p.setStatus("VALID");
        }
         if (permitStatus >0 && permitStatus <=90)
        { 
          p.setStatus("EXPIRES SOON");
        }
         if (p.getIssueDate()==null)
        {
          p.setStatus("PENDING");
        }
          
       // p.setCurrentYear(Integer.parseInt(applicationYear));
         p.setSpecials(DfbsEntrSpecialDAO.selectSpecials(conn,p.getPermitNumber()));
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
  
  
 
 
  
  
  public int insertPermit(DfbsEntrPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
       pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_CONSUMER_PERMIT);
      int id = this.getId(conn,DfbsEntrSQL.SELECT_NEXT_PERMIT_ID);
      permit.createPermitNumber(id);
      
      pstmt.clearParameters();
      pstmt.setString(1,permit.getPermitNumber());
      pstmt.setInt(2,permit.getOwnerId());
      pstmt.setString(3,permit.getStreet1());
      pstmt.setString(4,permit.getStreet2());
      pstmt.setString(5,permit.getCity());
      pstmt.setString(6,permit.getState());
      pstmt.setString(7,permit.getZip());
      pstmt.setString(8,permit.getCounty());
      if(permit.getPermitType() !=null) {
        permit.setPermitType("SU");
      }
      pstmt.setString(9,permit.getPermitType());
      pstmt.setString(10,permit.getContactPerson());
      pstmt.setString(11,permit.getPhoneNumber());
      pstmt.setString(12,permit.getFaxNumber());
      pstmt.setString(13,permit.getEmailAddress());
      pstmt.setString(14,permit.getActualLocation());
      pstmt.setString(15,permit.getFacilityDescription());
      pstmt.setString(16,permit.getMaximumOccupancy());
      pstmt.setString(17,permit.getEventDateString());
      pstmt.setString(18,permit.getEventTime());
      pstmt.setString(19,permit.getEventName());
      pstmt.setString(20,permit.getIntendedOccupantLoad());
      pstmt.setString(21,permit.getFeeExempt());
      pstmt.setString(22,permit.getNotes());
       
      
      pstmt.execute();
      pstmt1 = conn.prepareStatement(DfbsEntrSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,permit.getPermitNumber());
      pstmt1.setString(2,permit.getApplicationKey());
      pstmt1.setString(3,"AEPermit");
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
  public void updatePermit(DfbsEntrPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_CONSUMER_PERMIT);
      pstmt.clearParameters();
       pstmt.setString(1,permit.getStreet1());
      pstmt.setString(2,permit.getStreet2());
      pstmt.setString(3,permit.getCity());
      pstmt.setString(4,permit.getState());
      pstmt.setString(5,permit.getZip());
      pstmt.setString(6,permit.getCounty());
       if(permit.getPermitType() !=null) {
        permit.setPermitType("SU");
      }
      pstmt.setString(7,permit.getPermitType());
      pstmt.setString(8,permit.getContactPerson());
      pstmt.setString(9,permit.getPhoneNumber());
      pstmt.setString(10,permit.getFaxNumber());
      pstmt.setString(11,permit.getEmailAddress());
      pstmt.setString(12,permit.getActualLocation());
      pstmt.setString(13,permit.getFacilityDescription());
      pstmt.setString(14,permit.getMaximumOccupancy());
      pstmt.setString(15,permit.getEventDateString());
      pstmt.setString(16,permit.getEventTime());
      pstmt.setString(17,permit.getEventName());
      pstmt.setString(18,permit.getIntendedOccupantLoad());
      pstmt.setString(19,permit.getFeeExempt());
      pstmt.setString(20,permit.getStatus());
      pstmt.setString(22,permit.getPermitNumber());
      pstmt.setString(21,permit.getNotes());
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
 
   public void updatePermitWithNotes(DfbsEntrPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_CONSUMER_PERMIT_WITH_NOTES);
      pstmt.clearParameters();
      pstmt.setString(1,permit.getStreet1());
      pstmt.setString(2,permit.getStreet2());
      pstmt.setString(3,permit.getCity());
      pstmt.setString(4,permit.getState());
      pstmt.setString(5,permit.getZip());
      pstmt.setString(6,permit.getCounty());
       if(permit.getPermitType() !=null) {
        permit.setPermitType("SU");
      }
      pstmt.setString(7,permit.getPermitType());
      pstmt.setString(8,permit.getContactPerson());
      pstmt.setString(9,permit.getPhoneNumber());
      pstmt.setString(10,permit.getFaxNumber());
      pstmt.setString(11,permit.getEmailAddress());
      pstmt.setString(12,permit.getActualLocation());
      pstmt.setString(13,permit.getFacilityDescription());
      pstmt.setString(14,permit.getMaximumOccupancy());
      pstmt.setString(15,permit.getEventDateString());
      pstmt.setString(16,permit.getEventTime());
      pstmt.setString(17,permit.getEventName());
      pstmt.setString(18,permit.getIntendedOccupantLoad());
      pstmt.setString(19,permit.getFeeExempt());
      pstmt.setString(20,permit.getStatus());
      pstmt.setString(23,permit.getPermitNumber());
      pstmt.setString(21,permit.getNotes());
      pstmt.setString(22,permit.getApplicationDateString());
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
 
  public Map selectFeesMapping() throws SQLException, Exception 
  {
    Map map = new HashMap();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_FEES_ENTR);
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
  
  
  
  
 public void insertPermitTransaction(DfbsEntrPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,DfbsEntrSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,permit.getPermitTotalAmount());
      StringBuffer sb = new StringBuffer();
      sb.append(permit.getSpecialsCount());
      pstmt.setString(3,permit.getTransactionDescription() +"-Permit fee:("+permit.getAmount()+")" + "Additional Plans Fee("+(permit.getPermitTotalAmount()-permit.getAmount())+")");
      pstmt.setInt(4,permit.getReceiptId());
      pstmt.setString(5,permit.getPermitNumber());
      pstmt.setString(6,permit.getOwnerName());
      pstmt.setInt(7,0);
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
   public void insertPermitTransaction(DfbsEntrSpecial special,DfbsEntrPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,DfbsEntrSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(DfbsEntrSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,special.getAmount());
      StringBuffer sb = new StringBuffer();
      pstmt.setString(3,permit.getTransactionDescription()+"Special Endorsement for " + permit.getPermitNumber()+" "+special.getEventName());
      pstmt.setInt(4,permit.getReceiptId());
      pstmt.setString(5,permit.getPermitNumber());
      pstmt.setString(6,permit.getOwnerName());
      pstmt.setInt(7,special.getSpecialId());
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
    
    List permits = new ArrayList();
    int cartPermitCount=0;
    Map mapPermit = ownerApplication.getPermitsMap();
    Set permitkeys = mapPermit.keySet();
    Iterator i = permitkeys.iterator();
    double total = 0;
    double addPlansFee =0;
   
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsEntrPermit permit = (DfbsEntrPermit) mapPermit.get(key); 
     double totalAmount = 0;
      double addPlansFeePermit =0;
      cartPermitCount =cartPermitCount+1;
  
 if ( permit.getFeeExempt().trim().equals("N") && (permit.getRenewStatus() != null || permit.isNew()== true))
     
      { 
       
       if (permit.getIntendedOccupantLoad().trim().equals("1-99 Persons"))
         {
           Double amount = (Double) feeMap.get(new Integer("1"));
           total = total + amount.doubleValue();
           permit.setAmount(amount.doubleValue());
           totalAmount = amount.doubleValue();
           
         }
         if (permit.getIntendedOccupantLoad().trim().equals("100-499 Persons"))
         {
           Double amount = (Double) feeMap.get(new Integer("2"));
           total = total + amount.doubleValue();
           permit.setAmount(amount.doubleValue());
           totalAmount = amount.doubleValue();
         } 
          if (permit.getIntendedOccupantLoad().trim().equals("500-999 Persons"))
         {
           Double amount = (Double) feeMap.get(new Integer("3"));
           total = total + amount.doubleValue();
           permit.setAmount(amount.doubleValue());
           totalAmount = amount.doubleValue();
         } 
          if (permit.getIntendedOccupantLoad().trim().equals("1,000-4,999 Persons"))
         {
           Double amount = (Double) feeMap.get(new Integer("4"));
           total = total + amount.doubleValue();
           permit.setAmount(amount.doubleValue());
           totalAmount = amount.doubleValue();
         } 
          if (permit.getIntendedOccupantLoad().trim().equals("5,000-9,999 Persons"))
         {
           Double amount = (Double) feeMap.get(new Integer("5"));
           total = total + amount.doubleValue();
           permit.setAmount(amount.doubleValue());
           totalAmount = amount.doubleValue();
         } 
          if (permit.getIntendedOccupantLoad().trim().equals("10,000 or more"))
         {
           Double amount = (Double) feeMap.get(new Integer("6"));
           total = total + amount.doubleValue();
           permit.setAmount(amount.doubleValue());
           totalAmount = amount.doubleValue();
         } 
       
         int plansCount = this.CountFileList(permit.getApplicationKey(),"AEPermit");
         if (plansCount >=2)
       {
         Double planAmount = (Double) feeMap.get(new Integer("8"));
         addPlansFee =addPlansFee + (plansCount-1)*planAmount.doubleValue();
         addPlansFeePermit =addPlansFeePermit + (plansCount-1)*planAmount.doubleValue();
       }
      }
          // special fee logic here raj
       List specials = new ArrayList();
       Map mapSpecial = permit.getSpecialMap();
       Set specialkeys = mapSpecial.keySet();
       Iterator j = specialkeys.iterator();
       double specialAmount = 0;
       
       while(j.hasNext())
      {
       String keySpecial = (String) j.next();
       DfbsEntrSpecial special = (DfbsEntrSpecial) mapSpecial.get(keySpecial); 
       if (special.getFeeExempt().trim().equals("N") )
       {cartPermitCount =cartPermitCount+1;
       Double SpecialAmount = (Double) feeMap.get(new Integer("7"));
       special.setAmount(SpecialAmount.doubleValue());
       total = total + SpecialAmount.doubleValue();
       totalAmount = totalAmount + SpecialAmount.doubleValue();
       specialAmount = specialAmount + SpecialAmount.doubleValue();
       }
       specials.add(special);
       int plansCount = this.CountFileList(special.getApplicationKey(),"AESpecial");
       if (plansCount >=2 && special.getFeeExempt().trim().equals("N"))
       {
         Double planAmount = (Double) feeMap.get(new Integer("8"));
         addPlansFee =addPlansFee + (plansCount-1)*planAmount.doubleValue(); 
         addPlansFeePermit =addPlansFeePermit + (plansCount-1)*planAmount.doubleValue();
       }
       }
     permit.setSpecials(specials);
     permit.setPermitSpecialAmount(specialAmount);
     permit.setPermitTotalAmount(permit.getAmount()+addPlansFeePermit);
      permit.setAddPlansFee(addPlansFeePermit);
     permits.add(permit); 
    }    
    
   ownerApplication.setPermits(permits);
   ownerApplication.setTotalPermitsAmount(total+addPlansFee);
   ownerApplication.setAddPlansFee(addPlansFee);
   ownerApplication.setCartPermitCount(cartPermitCount);
 }
  public String countyCode(DfbsEntrPermit permit, String countyName) throws SQLException, Exception 
  {
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    conn = getConnection();
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_COUNTY_CODE);
      pstmt.clearParameters();
      pstmt.setString(1,countyName);
      rs = pstmt.executeQuery();
      
      pstmt.clearParameters();
      while(rs.next()) 
      {
      permit.setCountyCode(rs.getString(1));
      }
      
      return  permit.getCountyCode();
    } catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try {rs.close();
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   protected static List selectPermitsByStreet(Connection conn,int ownerId,String streetNumber, String byType) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
      if (byType == "byStreet")
      { 
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMITS_BY_STREET);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,streetNumber);
      rs = pstmt.executeQuery();
      }
     
      if (byType == "byPermit")
      {
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMITS_BY_PERMIT);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,streetNumber);
      rs = pstmt.executeQuery();
      }
     
      if (byType == "byStatus")
      {
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMITS_BY_STATUS);
       pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      }
      while(rs.next()) 
      { 
       DfbsEntrPermit p = new DfbsEntrPermit();
        p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setPermitYear(rs.getInt(3));
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
        p.setCounty(rs.getString(9));
        p.setPermitType(rs.getString(10));
        p.setContactPerson(rs.getString(11));
        p.setPhoneNumber(rs.getString(12));
        p.setFaxNumber(rs.getString(13));
        p.setEmailAddress(rs.getString(14));
        p.setApplicationDate(rs.getDate(15));
        p.setActualLocation(rs.getString(16));
        p.setFacilityDescription(rs.getString(17));
        p.setMaximumOccupancy(rs.getString(18));
        p.setEventDate(rs.getDate(19));
        p.setEventTime(rs.getString(20));
        p.setEventName(rs.getString(21));
        p.setIntendedOccupantLoad(rs.getString(22));
        p.setFeeExempt(rs.getString(23));
       // p.setCurrentYear(Integer.parseInt(applicationYear));
         String inspectorName=findInspectorName(p.getCounty(),p.getZip(),conn);
        p.setInspectorName(inspectorName);
        p.setSpecials(DfbsEntrSpecialDAO.selectSpecials(conn,p.getPermitNumber()));
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
  public DfbsEntrPermit selectPermit(String pemitNumber ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     Connection conn = null;
   
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMIT);
      pstmt.clearParameters();
      pstmt.setString(1,pemitNumber);
     DfbsEntrPermit p = new DfbsEntrPermit();
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
        p.setCounty(rs.getString(9));
        p.setPermitType(rs.getString(10));
        p.setContactPerson(rs.getString(11));
        p.setPhoneNumber(rs.getString(12));
        p.setFaxNumber(rs.getString(13));
        p.setEmailAddress(rs.getString(14));
        p.setApplicationDate(rs.getDate(15));
        p.setActualLocation(rs.getString(16));
        p.setFacilityDescription(rs.getString(17));
        p.setMaximumOccupancy(rs.getString(18));
        p.setEventDate(rs.getDate(19));
        p.setEventTime(rs.getString(20));
        p.setEventName(rs.getString(21));
        p.setIntendedOccupantLoad(rs.getString(22));
        p.setFeeExempt(rs.getString(23));
        p.setNotes(rs.getString(24));
        p.setOwnerId(rs.getInt(25)); 
         String inspectorName=findInspectorName(p.getCounty(),p.getZip(),conn);
        p.setInspectorName(inspectorName);
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
   public DfbsEntrPermit selectPermitUpdate(String pemitNumber ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     Connection conn = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMIT_FOR_UPDATE);
      pstmt.clearParameters();
      pstmt.setString(1,pemitNumber);
     DfbsEntrPermit p = new DfbsEntrPermit();
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
        p.setCounty(rs.getString(9));
        p.setPermitType(rs.getString(10));
        p.setContactPerson(rs.getString(11));
        p.setPhoneNumber(rs.getString(12));
        p.setFaxNumber(rs.getString(13));
        p.setEmailAddress(rs.getString(14));
        p.setApplicationDate(rs.getDate(15));
        p.setActualLocation(rs.getString(16));
        p.setFacilityDescription(rs.getString(17));
        p.setMaximumOccupancy(rs.getString(18));
        p.setEventDate(rs.getDate(19));
        p.setEventTime(rs.getString(20));
        p.setEventName(rs.getString(21));
        p.setIntendedOccupantLoad(rs.getString(22));
        p.setFeeExempt(rs.getString(23));
        p.setStatus(rs.getString(24));
        p.setNotes(rs.getString(25));
        int   permitStatus=(rs.getInt(26));
           if (permitStatus <=0)
           {
             p.setStatus("EXPIRED");
           }
           if (permitStatus >=0)
           {
             p.setStatus("VALID");
           }
            if (permitStatus >=0 && permitStatus <=90)
           {
             p.setStatus("EXPIRES SOON");
           }
            if (p.getIssueDate()==null)
           {
             p.setStatus("PENDING");
           }
          p.setPermitType(rs.getString(27));
         String inspectorName=findInspectorName(p.getCounty(),p.getZip(),conn);
        p.setInspectorName(inspectorName);
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
   public int CountFileList(String IdNumber,String IdType) throws SQLException, Exception 
  {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int fileCount =0;
    try 
    { 
   
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_DOCUMENT_COUNT);
       pstmt.clearParameters();
     
      if(IdNumber != null) {
        pstmt.setString(1,IdNumber);
        pstmt.setString(2,IdType);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
        fileCount =(rs.getInt(1));
        
      }
       return fileCount;
     
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
   public String findInspSupEmail (String inspEmail ) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call FIND_INSP_SUP_EMAIL(?,?) }");
      proc.setString(1, inspEmail);
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
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_ACCT_DUES);
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
       
          appDate =rs.getString(2);
       
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
   public  List selectOwnersListEmail( ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {  conn = getConnection();
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_PERMITS_FOR_EMAIL);
      pstmt.clearParameters();
     
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsEntrPermit p = new DfbsEntrPermit();
        p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setOwnerId(rs.getInt(3));
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
        p.setCounty(rs.getString(9));
        p.setPhoneNumber(rs.getString(10));
        p.setFaxNumber(rs.getString(11));
        p.setEmailAddress(rs.getString(12));
        p.setActualLocation(rs.getString(13));
        p.setFacilityDescription(rs.getString(14));
        p.setMaximumOccupancy(rs.getString(15));
        p.setExpDate(rs.getDate(17));
        p.setFeeExempt(rs.getString(16));
        p.setContactPerson(rs.getString(18));
       list.add(p);
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
   public String selectContactEmail(int idNumber) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
      String contactEmail=null;  
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_CONTACT_EMAIL);
      pstmt.clearParameters();
      pstmt.setInt(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      contactEmail=rs.getString(1);
      
      }
    
   
      return contactEmail;
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
   public String selectOwnerEmail(int idNumber) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
      String ownerEmail=null;  
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_OWNER_EMAIL);
      pstmt.clearParameters();
      pstmt.setInt(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
      ownerEmail=rs.getString(1);
      
      }
    
   
      return ownerEmail;
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
  public void updatePermitEmailDate(DfbsEntrPermit permit) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsEntrSQL.UPDATE_ENTR_PERMIT_EMAIL_DATE);
      pstmt.clearParameters();
      
      
      pstmt.setString(1,permit.getPermitNumber());
      
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
   public  List selectNewPermits(DfbsEntrOwnerDAO oDAO) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    {  conn = getConnection();
      conn.setAutoCommit(false);
       pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_NEW_PERMITS);
       pstmt.clearParameters();
     
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
       DfbsEntrPermit p = new DfbsEntrPermit();
        p.setPermitNumber(rs.getString(1));
        p.setIssueDate(rs.getDate(2));
        p.setPermitYear(rs.getInt(3));
        p.setStreet1(rs.getString(4));
        p.setStreet2(rs.getString(5));
        p.setCity(rs.getString(6));
        p.setState(rs.getString(7));
        p.setZip(rs.getString(8));
        p.setCounty(rs.getString(9));
        p.setPermitType(rs.getString(10));
        p.setContactPerson(rs.getString(11));
        p.setPhoneNumber(rs.getString(12));
        p.setFaxNumber(rs.getString(13));
        p.setEmailAddress(rs.getString(14));
        p.setApplicationDate(rs.getDate(15));
        p.setActualLocation(rs.getString(16));
        p.setFacilityDescription(rs.getString(17));
        p.setMaximumOccupancy(rs.getString(18));
        p.setEventDate(rs.getDate(19));
        p.setEventTime(rs.getString(20));
        p.setEventName(rs.getString(21));
        p.setIntendedOccupantLoad(rs.getString(22));
        p.setFeeExempt(rs.getString(23));
        
       list.add(p);
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
   public  List selectAEPermitsForEmail() throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    
    try 
    {  conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_EMAIL_INSPECTOR_AFTER_FEEPAID);
     
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {DfbsEntrPermit feePermit = new DfbsEntrPermit();
        feePermit.setPermitNumber(rs.getString(1));
         feePermit.setReceiptId(rs.getInt(2));
       list.add(feePermit);
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
    public  String selectCurrentYear() throws SQLException, Exception 
     {
     
       
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       Connection conn = null;
       String currentYear="";
       try 
       {  conn = getConnection();
         
           pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_CURRENT_YEAR);
        
         
         pstmt.clearParameters();
         rs = pstmt.executeQuery();
         
         while(rs.next()) 
         { 
           
           currentYear=rs.getString(1);
           
         }
         
         return currentYear;
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
     public  int selectInspector(String inspectorEmail) throws SQLException, Exception 
     {
     
       
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       Connection conn = null;
       int inspectorId=0;
       try 
       {  conn = getConnection();
         
           pstmt = conn.prepareStatement(DfbsEntrSQL.SELECT_INSPECTOR);
        
         
         pstmt.clearParameters();
         pstmt.setString(1,inspectorEmail);
         rs = pstmt.executeQuery();
         
         while(rs.next()) 
         { 
           
           inspectorId=rs.getInt(1);
           
         }
         
         return inspectorId;
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