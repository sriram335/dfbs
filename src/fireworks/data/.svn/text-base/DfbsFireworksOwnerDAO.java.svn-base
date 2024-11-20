package fireworks.data;
import fireworks.to.*;
import fireworks.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

public class DfbsFireworksOwnerDAO extends HsDAO
{
  
   
  
   public DfbsFireworksOwnerDAO()
   {
      super();
   }
  
   public  DfbsFireworksOwnerDAO(HsDataSource pool)
   {
    super(pool);
    
   }
   
   public List selectOwnersList(String sql,String param) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String queryType="";
    try 
    {
      conn = getConnection();
      if(sql.equals("byPermit"))
      { 
        sql=DfbsFireworksSQL.SELECT_OWNERS_BY_PERMIT;
        queryType = "byPermit";
       }
       if(sql.equals("byCounty"))
      { 
        sql=DfbsFireworksSQL.SELECT_OWNERS_BY_COUNTY;
        queryType = "byCounty";
        
       }
      pstmt = conn.prepareStatement(sql);
      pstmt.clearParameters();
      if(param != null) { 
        pstmt.setString(1,param);
      }
      if(queryType.equals("byPermit")||queryType.equals("byCounty"))
      {  
        pstmt.setString(2,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        DfbsOwner owner = new DfbsOwner();
         owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(2));
        
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
        owner.setCounty(rs.getString(9));
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        
        owner.setContactId(rs.getInt(13));
        owner.setFirstName(rs.getString(14));
        owner.setLastName(rs.getString(15));
        owner.setContactPhoneNumber(rs.getString(16));
        owner.setStateId(rs.getInt(17));

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
  
   public DfbsOwner selectOwner(String ownerId,String permitYear, String renewYear) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    Connection conn = null;
    
    try 
    {
      
      conn = getConnection();
      owner = selectOwner(conn,ownerId);
      owner.setPermits(DfbsFireworksPermitDAO.selectPermits(conn,ownerId,permitYear,renewYear));
      return owner;
    }
    finally 
    {
     try {
        conn.close();
      } catch (Exception e) {}
    }
  }
  
    public List selectOwnerCounty( String county,String recFlag) throws SQLException, Exception 
  {
  
   List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String queryType="";
    try 
    {  conn = getConnection();
     
      if (recFlag == null || recFlag.equals("PENDING"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_OWNERS_COUNTY_APPROVAL+" and online_status='COUNTY') order by a.last_name ");
      }
       if (recFlag.equals("APPROVED"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_OWNERS_COUNTY_APPROVAL+" and online_status='APPROVED') order by a.last_name ");
      }
       if (recFlag.equals("DENIED"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_OWNERS_COUNTY_APPROVAL+" and online_status='DENIED') order by a.last_name ");
      }
         if (recFlag.equals("ALL"))
      {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_OWNERS_COUNTY_APPROVAL+") order by a.last_name ");
      }
      pstmt.clearParameters();
      if(county != null) { 
        pstmt.setString(1,county);
      }
       rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsOwner owner = new DfbsOwner();
         owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(2));
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
        owner.setCounty(rs.getString(9));
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        owner.setContactId(rs.getInt(13));
        owner.setFirstName(rs.getString(14));
        owner.setLastName(rs.getString(15));
        owner.setContactPhoneNumber(rs.getString(16));
        owner.setStateId(rs.getInt(17));
         owner.setPermits(DfbsFireworksPermitDAO.selectPermitsCounty(conn,owner.getOwnerId(),county,recFlag));
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
  public static DfbsOwner selectOwner(Connection conn,String ownerId) throws SQLException, Exception 
  {
  
    DfbsOwner owner = new DfbsOwner();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.SELECT_OWNER);
      pstmt.clearParameters();
      pstmt.setString(1,ownerId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        owner.setOwnerId(rs.getInt(1));
        owner.setOwnerName(rs.getString(2));
        
        owner.setAddressId(rs.getInt(3));
        owner.setStreet1(rs.getString(4));
        owner.setStreet2(rs.getString(5));
        owner.setCity(rs.getString(6));
        owner.setState(rs.getString(7));
        owner.setZip(rs.getString(8));
        owner.setCounty(rs.getString(9));
        
        owner.setPhoneNumber(rs.getString(10));
        owner.setFaxNumber(rs.getString(11));
        owner.setOwnerEmail(rs.getString(12));
        
        owner.setContactId(rs.getInt(13));
        owner.setFirstName(rs.getString(14));
        owner.setLastName(rs.getString(15));
        owner.setContactPhoneNumber(rs.getString(16));
        owner.setStateId(rs.getInt(17));

      }
      return owner;
    }
    finally 
    {
     try {
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
      int id = this.getId(conn,DfbsFireworksSQL.SELECT_NEXT_OWNER_ID);
      owner.setOwnerId(id);
      
      pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_OWNER);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      pstmt.setString(2,owner.getOwnerName());
      pstmt.setString(3,owner.getPhoneNumber());
      pstmt.execute();
      
      this.insertAddress(conn,owner);
      this.insertPerson(conn,owner);
      
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
      
      pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_OWNER);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getOwnerName());
      pstmt.setString(2,owner.getPhoneNumber());
      pstmt.setInt(3,owner.getOwnerId());
      pstmt.execute();
     
      if(owner.getAddressId() == 0) {
        this.insertAddress(conn,owner);
      } 
      else 
      {
        this.updateAddress(conn,owner);
      }
     
      if(owner.getContactId() == 0) {
        this.insertPerson(conn,owner);
      } 
      else 
      {
        this.updatePerson(conn,owner);
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
  
  private static void insertAddress(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    
    try 
    {
      int addressId = HsDAO.getId(conn,DfbsFireworksSQL.SELECT_NEXT_ADDRESS_ID);
      owner.setAddressId(addressId);
      pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_OWNER_ADDRESS);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getAddressId());
      pstmt.setInt(2,owner.getOwnerId());
      pstmt.setInt(3,owner.getStateId());
      pstmt.setString(4,owner.getStreet1());
      pstmt.setString(5,owner.getStreet2());
      pstmt.setString(6,owner.getCity());
      pstmt.setString(7,owner.getZip());
      pstmt.setString(8,owner.getOwnerEmail());
      pstmt.setString(9,owner.getFaxNumber());
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
      pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_OWNER_ADDRESS);
      pstmt.clearParameters();
      
      pstmt.setInt(1,owner.getStateId());
      pstmt.setString(2,owner.getStreet1());
      pstmt.setString(3,owner.getStreet2());
      pstmt.setString(4,owner.getCity());
      pstmt.setString(5,owner.getZip());
      pstmt.setString(6,owner.getOwnerEmail());
      pstmt.setString(7,owner.getFaxNumber());
      pstmt.setInt(8,owner.getAddressId());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
  private static void insertPerson(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    try 
    {
      int personId = HsDAO.getId(conn,DfbsFireworksSQL.SELECT_NEXT_PERSON_ID);
      owner.setContactId(personId);
      pstmt = conn.prepareStatement(DfbsFireworksSQL.INSERT_PERSON);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      pstmt.setInt(2,owner.getContactId());
      pstmt.setString(3,owner.getFirstName());
      pstmt.setString(4,owner.getLastName());
      pstmt.setString(5,owner.getContactPhoneNumber());
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   private static void updatePerson(Connection conn, DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(DfbsFireworksSQL.UPDATE_PERSON);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getFirstName());
      pstmt.setString(2,owner.getLastName());
      pstmt.setString(3,owner.getContactPhoneNumber());
      pstmt.setInt(4,owner.getContactId());
      pstmt.execute();
    } 
    finally 
    {
     try {
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
 
  public int selectCount(String sql,int ownerId,String year) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,year);
      pstmt.setString(3,year);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        return rs.getInt(1);
      }
      
      return 0;
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
