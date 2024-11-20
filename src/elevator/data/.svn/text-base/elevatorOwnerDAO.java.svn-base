package elevator.data;


import hs.data.HsDAO;

   import java.util.*;
  import java.sql.*;

  import oracle.jdbc.*; 
  import hs.to.*;
  import hs.util.*;
  import elevator.to.*;
  import hs.data.*;
  import java.io.InputStream;
  import java.io.OutputStream;
  import org.apache.struts.upload.FormFile;
  public class elevatorOwnerDAO extends HsDAO
  {
    
     
    
     public elevatorOwnerDAO()
     {
        
     }
    
     public List selectOwnersList(String sql,String param,String byType, String byName) throws SQLException, Exception 
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
        if(param != null) {
          pstmt.setString(1,param);
        }
        rs = pstmt.executeQuery();
       
        while(rs.next()) 
        {
        
          DfbsOwner owner = new DfbsOwner();
          System.out.println(rs.getInt(1));
          owner.setOwnerId(rs.getInt(1));
          owner.setOwnerFirstName(rs.getString(3));
          owner.setOwnerLastName(rs.getString(2));
          owner.setOwnerMI(rs.getString(4));
          owner.setOwnerDBA(rs.getString(5));
          owner.setOwnerAddress1(rs.getString(7));
          owner.setOwnerAddress2(rs.getString(8));
          owner.setOwnerCity(rs.getString(9));
          owner.setOwnerState(rs.getString(10));
          owner.setOwnerZip(rs.getString(11));
          owner.setOwnerZip2(rs.getString(12)); 
          owner.setOwnerPhone(rs.getString(13));
          owner.setOwnerFax(rs.getString(14));
          owner.setOwnerEmail(rs.getString(15));
          
       //  owner.setElevators(elevatorDAO.selectElevatorList(conn,owner.getOwnerId()));
          list.add(owner);
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
    
     public DfbsOwner selectOwner(int ownerId) throws SQLException, Exception 
    {
    
      DfbsOwner owner = new DfbsOwner();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
         
      try 
      {
        conn = getConnection();
         pstmt = conn.prepareStatement(elevatorSQL.SELECT_OWNER);
        pstmt.clearParameters();
        pstmt.setInt(1,ownerId);
        rs = pstmt.executeQuery();
        if(rs.next()) 
        {System.out.println("1:"+rs.getInt(1));
          owner.setOwnerId(rs.getInt(1));
          owner.setOwnerLastName(rs.getString(2));
          owner.setOwnerFirstName(rs.getString(3));
          owner.setOwnerMI(rs.getString(4));
          owner.setOwnerDBA(rs.getString(5));
          owner.setOwnerAddress1(rs.getString(7));
          owner.setOwnerAddress2(rs.getString(8));
          owner.setOwnerCity(rs.getString(9));
          owner.setOwnerState(rs.getString(10));
          owner.setOwnerZip(rs.getString(11));
          owner.setOwnerZip2(rs.getString(12));
          owner.setOwnerPhone(rs.getString(13));
          owner.setOwnerFax(rs.getString(14));
          owner.setOwnerEmail(rs.getString(15));
          owner.setOwnerStateId(rs.getInt(16));
          owner.setElevators(elevatorDAO.selectElevatorList(conn,owner.getOwnerId()));
        }
      
     
        return owner;
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
    
    
   
   

    public int insertOwner(DfbsOwner owner) throws SQLException, Exception 
    {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try 
      {
        conn = getConnection();
        conn.setAutoCommit(false);
        int id = this.getId(conn,elevatorSQL.SELECT_NEXT_OWNER_ID);
        owner.setOwnerId(id);
        
        pstmt = conn.prepareStatement(elevatorSQL.INSERT_OWNER);
        pstmt.clearParameters();
        pstmt.setInt(1,owner.getOwnerId());
        pstmt.setString(2,owner.getOwnerLastName());
        pstmt.setString(3,owner.getOwnerFirstName());
        pstmt.setString(4,owner.getOwnerMI());
        pstmt.setString(5,owner.getOwnerDBA());
        pstmt.setString(6,owner.getOwnerPhone());
        
        pstmt.execute();
        this.insertAddress(conn,owner);
        
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
    public void updateOwner(DfbsOwner owner) throws SQLException, Exception 
    {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try 
      {
        conn = getConnection();
        conn.setAutoCommit(false);
        
        pstmt = conn.prepareStatement(elevatorSQL.UPDATE_OWNER);
        pstmt.clearParameters();
        pstmt.setString(4,owner.getOwnerLastName());
        pstmt.setString(2,owner.getOwnerFirstName());
        pstmt.setString(3,owner.getOwnerMI());
        pstmt.setString(1,owner.getOwnerDBA());
        pstmt.setString(5,owner.getOwnerPhone());
        pstmt.setInt(6,owner.getOwnerId());
       
        pstmt.execute();
       
        
        this.updateAddress(conn,owner);
       
      
        
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
    
    private static void insertAddress(Connection conn, DfbsOwner owner) 
    throws SQLException, Exception 
    {
      PreparedStatement pstmt = null;
      
      try 
      {
        int addressId = HsDAO.getId(conn,elevatorSQL.SELECT_NEXT_ADDRESS_ID);
        pstmt = conn.prepareStatement(elevatorSQL.INSERT_OWNER_ADDRESS);
        pstmt.clearParameters();
        pstmt.setInt(1,addressId);
        pstmt.setInt(2,owner.getOwnerId());
        pstmt.setInt(3,owner.getOwnerStateId());
        pstmt.setString(4,owner.getOwnerAddress1());
        pstmt.setString(5,owner.getOwnerAddress2());
        pstmt.setString(6,owner.getOwnerCity());
        pstmt.setString(7,owner.getOwnerZip());
        pstmt.setString(8,owner.getOwnerEmail());
        pstmt.setString(9,owner.getOwnerFax());
        pstmt.execute();
      } 
      finally 
      {
       try {
          
          pstmt.close();
        } catch (Exception e) {}
      }
    }  
    
     private static void updateAddress(Connection conn, DfbsOwner owner) 
    throws SQLException, Exception 
    {
      PreparedStatement pstmt = null;
      
      try 
      {
        pstmt = conn.prepareStatement(elevatorSQL.UPDATE_OWNER_ADDRESS);
        pstmt.clearParameters();
        
        
        pstmt.setInt(8,owner.getOwnerId());
        pstmt.setInt(1,owner.getOwnerStateId());
        pstmt.setString(2,owner.getOwnerAddress1());
        pstmt.setString(3,owner.getOwnerAddress2());
        pstmt.setString(4,owner.getOwnerCity());
        pstmt.setString(5,owner.getOwnerZip());
        pstmt.setString(6,owner.getOwnerEmail());
        pstmt.setString(7,owner.getOwnerFax());
        pstmt.execute();
      } 
      finally 
      {
       try {
          
          pstmt.close();
        } catch (Exception e) {}
      }
    }  
    
    
   public List selectOwnersList(String sql,String param,String structureType) throws SQLException, Exception 
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
        if(structureType != null) {
          pstmt.setString(1,structureType);
           pstmt.setString(2,param);
        }
        rs = pstmt.executeQuery();
        while(rs.next()) 
        { 
        
          DfbsOwner owner = new DfbsOwner();
          
          owner.setOwnerId(rs.getInt(1));
          owner.setOwnerFirstName(rs.getString(3));
          owner.setOwnerLastName(rs.getString(2));
          owner.setOwnerMI(rs.getString(4));
          owner.setOwnerDBA(rs.getString(5));
          owner.setOwnerAddress1(rs.getString(7));
          owner.setOwnerAddress2(rs.getString(8));
          owner.setOwnerCity(rs.getString(9));
          owner.setOwnerState(rs.getString(10));
          owner.setOwnerZip(rs.getString(11));
          owner.setOwnerZip2(rs.getString(12)); 
          owner.setOwnerPhone(rs.getString(13));
          owner.setOwnerFax(rs.getString(14));
          owner.setOwnerEmail(rs.getString(15));
          
          list.add(owner);
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
   
  public void calculateElevAppFee(elevator elev,elevatorApplication elevApp, ShoppingCart cart ) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
    
     proc = conn.prepareCall("{ call elevator_fee_online(?,?,?) }");
     proc.setInt(1, elev.getDeviceId());
     proc.setString(2, elevApp.getApplicationType());
     proc.registerOutParameter(3,java.sql.Types.INTEGER);
     proc.execute();
     conn.commit();
     elevApp.setAppFee(proc.getInt(3));
     cart.setAmount(0);
     int totalPermits =0;
     Map mapOwner = cart.getOwnerMap();
     Set ownerkeys = mapOwner.keySet();
     Iterator i = ownerkeys.iterator();
     while(i.hasNext())
     {
        String key = (String) i.next();
        DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
       Map mapApps = owner.getElevatorAppMap();
       Set appkeys = mapApps.keySet();
       Iterator j = appkeys.iterator();
         owner.setAmount(0);
         while(j.hasNext())
         {
          String appkey = (String) j.next();
          elevatorApplication elevAppl = (elevatorApplication) mapApps.get(appkey);
          owner.setAmount(owner.getAmount()+elevAppl.getAppFee());
           totalPermits= totalPermits+1;   
         }
         cart.setAmount(cart.getAmount()+owner.getAmount());
     }
     cart.setTotalPermits(totalPermits);
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
}
