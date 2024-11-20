package hazmat.data;
import hazmat.to.*;
import hazmat.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;




public class HazmatShipmentDAO  extends HsDAO
{
  public HazmatShipmentDAO()
  {
  }
  
   public int insertShipment(HazmatShipment shipment,int receiptId,int carrierId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    ResultSet rs = null;
    String permitString =null; 
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,HazmatPermitSQL.SELECT_NEXT_SHIP_ID);
      pstmt1 = conn.prepareStatement(HazmatPermitSQL.SELECT_PERMIT_STRING);
      pstmt1.clearParameters();
      rs = pstmt1.executeQuery();
      if(rs.next()) 
      {
       permitString=rs.getString(1);
      } 
      pstmt = conn.prepareStatement(HazmatPermitSQL.INSERT_SHIPMENT);
      pstmt.clearParameters();
      pstmt.setInt(16,carrierId);
      pstmt.setString(7,shipment.getShipActIsotope());
      pstmt.setString(4,shipment.getShipAmount());
      pstmt.setString(10,shipment.getShipComments());
      pstmt.setString(2,shipment.getShipDateString());
      pstmt.setString(3,shipment.getShipDestination());
      pstmt.setString(17,shipment.getShipDateString());
      pstmt.setInt(13,receiptId);
      pstmt.setInt(14,carrierId);
      pstmt.setString(6,shipment.getShipIsotope());
      pstmt.setString(18,shipment.getShipDateString());
      pstmt.setString(5,shipment.getShipMaterialType());
      pstmt.setString(12,shipment.getShipRadLevel());
      pstmt.setString(11,shipment.getShipRoute());
      pstmt.setString(8,shipment.getShipVicvsa());
      pstmt.setString(9,shipment.getShipVicvsaState());
      pstmt.setString(15,shipment.getShipOrigin());
      pstmt.setString(16,"T"+permitString+Integer.toString(id));
      shipment.setShipPermitNumber("T"+permitString+Integer.toString(id));
      pstmt.setString(19,shipment.getShipWeightType());
      pstmt.setString(20,shipment.getShipmentType());
      pstmt.setInt(21,shipment.getNumberOfCasks());
      pstmt.setInt(1,id);
     
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
        pstmt1.close();
         rs.close();
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
      pstmt = conn.prepareStatement(HazmatPermitSQL.SELECT_FEES_HAZMAT);
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
  
  
  
  
 public void insertPermitTransaction(HazmatShipment shipment,HazmatPermit permit,int receiptId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,HazmatPermitSQL.SELECT_NEXT_TRANSACTION_ID);
     
      pstmt = conn.prepareStatement(HazmatPermitSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,shipment.getAmount());
      if (permit.getPaymentType().equals("CC"))
      {
      pstmt.setString(3,shipment.getShipmentType()+" and num. of casks="+shipment.getNumberOfCasks()+";"+shipment.getShipRadLevel() +"-shipment(" + shipment.getShipPermitNumber() + ")"+ "CC CONFIRMATION " +Integer.toString(receiptId));
      }
      else
      {
        pstmt.setString(3,shipment.getShipmentType()+" and num. of casks="+shipment.getNumberOfCasks()+";"+shipment.getShipRadLevel() +"-shipment(" + shipment.getShipPermitNumber() + ")"+ " Check number " +Integer.toString(receiptId));
      
      }
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,shipment.getShipPermitNumber());
      pstmt.setString(6,permit.getOrgName());
      pstmt.setString(7,permit.getPaymentType());
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
  
  public void computeFees(HazmatPermit permit) throws Exception 
 {
    Map feeMap = this.selectFeesMapping();
    
    List carriers = new ArrayList();
    Map mapCarrier = permit.getCarriersMap();
    Set permitkeys = mapCarrier.keySet();
    
    Iterator i = permitkeys.iterator();
    double permitTotal = 0;
    int totalShipments = 0;
    while(i.hasNext())
    { 
     String key = (String) i.next();
     HazmatCarrier carrier = (HazmatCarrier) mapCarrier.get(key); 
     
     double totalAmount = 0;
          List shipments = new ArrayList();
          Map mapShipment = carrier.getShipmentsMap();
          Set carrierkeys = mapShipment.keySet();
          
          Iterator j = carrierkeys.iterator();
          double carrierTotal = 0;
          
        
          while(j.hasNext()) 
          { 
            String keys = (String) j.next();
            HazmatShipment shipment = (HazmatShipment) mapShipment.get(keys); 
             double total = 0;
           if (shipment.getShipRadLevel().trim().equals("LOW"))
             {
               Double amount = (Double) feeMap.get(new Integer("1"));
               total = total + amount.doubleValue();
               shipment.setAmount(amount.doubleValue());
               carrierTotal= carrierTotal + amount.doubleValue();
               permitTotal= permitTotal + amount.doubleValue();
                 totalShipments= totalShipments + 1;
               }
              
             
             if (shipment.getShipRadLevel().trim().equals("HIGH")  )
             {
               if (shipment.getShipmentType().equals("TRUCK"))
               {
               Double amount = (Double) feeMap.get(new Integer("2"));
                 total = total + amount.doubleValue();
                 shipment.setAmount(amount.doubleValue());
                 carrierTotal= carrierTotal + amount.doubleValue();
                 permitTotal= permitTotal + amount.doubleValue();
               }
               else {
                 Double amountRail = (Double) feeMap.get(new Integer("3"));
                 Double amountRailCasks = (Double) feeMap.get(new Integer("4"));  
                 shipment.setAmount(amountRail.doubleValue()+amountRailCasks.doubleValue()*((shipment.getNumberOfCasks()-1)));
                 carrierTotal= carrierTotal + amountRail.doubleValue()+amountRailCasks.doubleValue()*(shipment.getNumberOfCasks()-1);
                 permitTotal= permitTotal + amountRail.doubleValue()+amountRailCasks.doubleValue()*(shipment.getNumberOfCasks()-1);
               }
               
               totalShipments= totalShipments + 1;
             }
             if (shipment.getShipRadLevel().trim().equals("HRCQ")  )
             {
               if (shipment.getShipmentType().equals("TRUCK"))
               {
               Double amount = (Double) feeMap.get(new Integer("5"));
                 total = total + amount.doubleValue();
                 shipment.setAmount(amount.doubleValue());
                 carrierTotal= carrierTotal + amount.doubleValue();
                 permitTotal= permitTotal + amount.doubleValue();
               }
               else {Double amountRail = (Double) feeMap.get(new Integer("6"));
                 Double amountRailCasks = (Double) feeMap.get(new Integer("7"));  
                 shipment.setAmount(amountRail.doubleValue()+amountRailCasks.doubleValue()*(shipment.getNumberOfCasks()-1));
                 carrierTotal= carrierTotal + amountRail.doubleValue()+amountRailCasks.doubleValue()*(shipment.getNumberOfCasks()-1);
                 permitTotal= permitTotal + amountRail.doubleValue()+amountRailCasks.doubleValue()*(shipment.getNumberOfCasks()-1);
               }
               
               totalShipments= totalShipments + 1;
             }
             
            
             shipments.add(shipment);
           }
     carrier.setShipments(shipments);
     carrier.setAmount(carrierTotal);
     carriers.add(carrier);
     }    
    
   permit.setCarriers(carriers);
   permit.setAmount(permitTotal);
   permit.setTotalShipments(totalShipments);
 }
  
   public HazmatShipment selectShipment(String shipmentId) throws SQLException, Exception 
  {
  
    HazmatShipment shipment = new HazmatShipment();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(HazmatPermitSQL.SELECT_SHIPMENT_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(1,shipmentId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
         shipment.setShipDate(rs.getDate(1));
         shipment.setShipOrigin(rs.getString(2));
         shipment.setShipDestination(rs.getString(3));
         shipment.setShipAmount(rs.getString(4));
         shipment.setShipMaterialType(rs.getString(5));
         shipment.setShipIsotope(rs.getString(6));
         shipment.setShipActIsotope(rs.getString(7));
         shipment.setShipVicvsa(rs.getString(8));
         shipment.setShipVicvsaState(rs.getString(9));
         shipment.setShipComments(rs.getString(10));
         shipment.setShipRoute(rs.getString(11));
         shipment.setShipRadLevel(rs.getString(12));
         shipment.setShipAppDate(rs.getDate(13));
         shipment.setShipIssueDate(rs.getDate(14));
         shipment.setShipExpDate(rs.getDate(15));
         shipment.setShipPermitNumber(rs.getString(16));
          shipment.setShipWeightType(rs.getString(17));
          shipment.setShipmentType(rs.getString(18));
          shipment.setNumberOfCasks(rs.getInt(19));
    }
     return shipment;
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
  
 public HazmatShipment findShipment(String permitNumber, String issueDate,  HazmatPermit org,HazmatCarrier carrier) throws SQLException, Exception 
  {
  
    HazmatShipment shipment = new HazmatShipment();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(HazmatPermitSQL.SELECT_SHIPMENT_BY_PERMIT);
      pstmt.clearParameters();
      pstmt.setString(1,issueDate);
      pstmt.setString(2,permitNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
        
         org.setOrgName(rs.getString(1));
         carrier.setCarrierName(rs.getString(2));
         shipment.setShipIssueDate(rs.getDate(3));
         shipment.setShipExpDate(rs.getDate(4));
         
    }
     return shipment;
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

