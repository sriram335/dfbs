package amuseRide.data;




import hs.data.HsDAO;

   import java.util.*;
  import java.sql.*;

  import oracle.jdbc.*; 
  import hs.to.*;
  import hs.util.*;
  import amuseRide.to.*;
  import hs.data.*;
  import java.io.InputStream;
  import java.io.OutputStream;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;
  public class amuseOwnerDAO extends HsDAO
  {
    
     
    
     public amuseOwnerDAO()
     {
        
     }
    
     public List selectOwnersList(String sql,String param) throws SQLException, Exception 
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
    
     public DfbsOwner selectOwner(int ownerId) throws SQLException, Exception 
    {
    
      DfbsOwner owner = new DfbsOwner();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
         
      try 
      {
        conn = getConnection();
         pstmt = conn.prepareStatement(amuseRideSQL.SELECT_OWNER);
        pstmt.clearParameters();
        pstmt.setInt(1,ownerId);
        rs = pstmt.executeQuery();
        if(rs.next()) 
        {
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
          owner.setRides(this.selectRidesList(conn,owner.getOwnerId()));
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
        int id = this.getId(conn,amuseRideSQL.SELECT_NEXT_OWNER_ID);
        owner.setOwnerId(id);
        
        pstmt = conn.prepareStatement(amuseRideSQL.INSERT_OWNER);
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
        
        pstmt = conn.prepareStatement(amuseRideSQL.UPDATE_OWNER);
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
        int addressId = HsDAO.getId(conn,amuseRideSQL.SELECT_NEXT_ADDRESS_ID);
        pstmt = conn.prepareStatement(amuseRideSQL.INSERT_OWNER_ADDRESS);
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
        pstmt = conn.prepareStatement(amuseRideSQL.UPDATE_OWNER_ADDRESS);
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
    
  public void calculateAmuseFee(ShoppingCart cart ) throws SQLException, Exception 
  {
   
   try 
   {  
     cart.setAmount(0);
     int totalPermits =0;
     Map mapOwner = cart.getOwnerMap();
     Set ownerkeys = mapOwner.keySet();
     Iterator i = ownerkeys.iterator();
     while(i.hasNext())
     {
        String key = (String) i.next();
        DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
       Map mapRides = owner.getRidesMap();
       Set appkeys = mapRides.keySet();
       Iterator j = appkeys.iterator();
         owner.setAmount(0);
         while(j.hasNext())
         {
          String appkey = (String) j.next();
          amuseRide ride =(amuseRide) mapRides.get(appkey);
           int rideFee=this.selectRideFee(ride.getDeviceId()) ; 
             ride.setAppFee(rideFee);
          owner.setAmount(owner.getAmount()+rideFee);
           totalPermits= totalPermits+1;  
          owner.addRide(ride);
         }
         cart.setAmount(cart.getAmount()+owner.getAmount());
     }
     cart.setTotalPermits(totalPermits);
   } 
   finally 
   {
    try { 
     } catch (Exception e) {}
   }
  }
  public int selectRideFee(int deviceId) throws SQLException, Exception 
  {
  
    amuseRide ride = new amuseRide();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   int rideFee =0;  
   try 
   {
     conn = getConnection();
      pstmt = conn.prepareStatement(amuseRideSQL.SELECT_AMUSE_FEE);
     pstmt.clearParameters();
     pstmt.setInt(1,deviceId);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
       rideFee =(rs.getInt(1));
       
     }
     return rideFee;
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
  
  private static List selectRidesList(Connection conn,int ownerId) throws SQLException, Exception 
  {
   List list = new ArrayList();
   ResultSet rs1 = null;
   PreparedStatement pstmt1 = null;
   
   try 
   {
     pstmt1 = conn.prepareStatement(amuseRideSQL.SELECT_AMUSE_RIDES);
     pstmt1.clearParameters();
     if(ownerId != 0) {
       pstmt1.setInt(1,ownerId);
     }
     rs1 = pstmt1.executeQuery();
    
     while(rs1.next()) 
     {
     
       amuseRide ride = new amuseRide();
       ride.setAccDate(rs1.getDate(24));
       ride.setAppDate(rs1.getDate(16));
       ride.setExpDate(rs1.getDate(15));
       ride.setInspDate(rs1.getDate(20));
       ride.setNdtDate(rs1.getDate(17));
       ride.setAddress1(rs1.getString(6));
       ride.setAddress2(rs1.getString(7));
       ride.setAssignInspector(rs1.getString(19));
       ride.setCapacity(rs1.getString(13));
       ride.setCity(rs1.getString(8));
       ride.setDeviceName(rs1.getString(2));
       ride.setLocation(rs1.getString(21));
       ride.setManName(rs1.getString(4));
       ride.setOtherDocs(rs1.getString(18));
       ride.setPermitNumber(rs1.getString(3));
       ride.setPhone(rs1.getString(12));
       ride.setRideStatus(rs1.getString(23));
       ride.setSerialNumber(rs1.getString(1));
       ride.setSpeed(rs1.getString(14));
       ride.setState(rs1.getString(9));
       ride.setZip(rs1.getString(10));
       ride.setZip2(rs1.getString(11));
       ride.setDeviceId(rs1.getInt(22));
       ride.setInsuranceId(rs1.getInt(5));
       ride.setNumberAccidents(rs1.getInt(25));
         list.add(ride);
     }
     return list;
   }
   finally 
   {
    try {
       rs1.close();
       pstmt1.close();
     } catch (Exception e) {}
   }
  }
  public void  uploadFile(FormFile oneFile,String IdNumber, String IdType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,amuseRideSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(amuseRideSQL.UPLOAD_DOCUMENT);
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
     
     
      pstmt = conn.prepareStatement(amuseRideSQL.SELECT_DOCUMENT_NAMES_ALL);
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
  
  public int downLoad(OutputStream os,int fileId,String fileName)  throws SQLException, Exception
  {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = amuseRideSQL.SELECT_DOCUMENT; 
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
   {   
       os.write(buf,0,len);
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
  }
