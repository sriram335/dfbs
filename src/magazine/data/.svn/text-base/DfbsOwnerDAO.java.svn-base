package magazine.data;
import magazine.to.*;
import magazine.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import main.to.FileNames;

public class DfbsOwnerDAO extends HsDAO
{
  public DfbsOwnerDAO()
  {
  }
    public  List selectOwnersList(String sql,String param,String byType, String byName,DfbsPermitDAO pDAO) throws SQLException, Exception 
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
         owner.setPermits(pDAO.selectPermits(owner.getOwnerId(),byType,byName));
        owner.setFileList(this.selectFileList(owner));
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
  public  List selectNewOwnersList(DfbsPermitDAO pDAO) throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  {
  
    conn = getConnection();
    pstmt = conn.prepareStatement(DfbsSQL.SELECT_NEW_OWNERS);
    pstmt.clearParameters();
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
       owner.setPermits(pDAO.selectPermitsList(owner.getOwnerId()));
      owner.setFileList(this.selectFileList(owner));
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
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_OWNER);
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
    PreparedStatement pstmt1 = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,DfbsSQL.SELECT_NEXT_OWNER_ID);
      owner.setOwnerId(id);
      
      pstmt = conn.prepareStatement(DfbsSQL.INSERT_OWNER);
      pstmt.clearParameters();
      pstmt.setInt(1,owner.getOwnerId());
      pstmt.setString(2,owner.getOwnerLastName());
      pstmt.setString(3,owner.getOwnerFirstName());
      pstmt.setString(4,owner.getOwnerMI());
      pstmt.setString(5,owner.getOwnerDBA());
      pstmt.setString(6,owner.getOwnerPhone());
      
      pstmt.execute();
      pstmt1 = conn.prepareStatement(DfbsSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,Integer.toString(owner.getOwnerId()));
      pstmt1.setString(2,owner.getOwnerKey());
      pstmt1.setString(3,"Magazine");
       pstmt1.execute(); 
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
        pstmt1.close(); 
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
      
      pstmt = conn.prepareStatement(DfbsSQL.UPDATE_OWNER);
      pstmt.clearParameters();
      pstmt.setString(1,owner.getOwnerLastName());
      pstmt.setString(2,owner.getOwnerFirstName());
      pstmt.setString(3,owner.getOwnerMI());
      pstmt.setString(4,owner.getOwnerDBA());
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
      int addressId = HsDAO.getId(conn,DfbsSQL.SELECT_NEXT_ADDRESS_ID);
      pstmt = conn.prepareStatement(DfbsSQL.INSERT_OWNER_ADDRESS);
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
      pstmt = conn.prepareStatement(DfbsSQL.UPDATE_OWNER_ADDRESS);
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
  
    public void createBackUpData (int ownerId,String magId ) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;

    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call CREATE_BACKUP_DATA_FIRE_MAG(?, ?) }");
      proc.setString(2, magId);
      proc.setInt(1, ownerId);
      proc.execute();

      conn.commit();
      
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
 // contact
  public DfbsContact selectContact(int idNumber) throws SQLException, Exception 
  {
  
   DfbsContact contact = new DfbsContact();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
      
   try 
   {
     
     conn = getConnection();
      pstmt = conn.prepareStatement(DfbsSQL.SELECT_CONTACT_BY_ID);
     pstmt.clearParameters();
     pstmt.setInt(1,idNumber);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
     contact.setPersonAddress1(rs.getString(6));
     contact.setPersonAddress2(rs.getString(7));
     contact.setPersonCity(rs.getString(8));
     contact.setPersonEmail(rs.getString(13));
     contact.setPersonFax(rs.getString(12));
     contact.setPersonFirstName(rs.getString(2));
     contact.setPersonLastName(rs.getString(3));
     contact.setPersonMi(rs.getString(4));
     contact.setPersonPhone(rs.getString(11));
     contact.setPersonStateId(rs.getInt(9));
     contact.setPersonType(rs.getString(5));
     contact.setPersonZip(rs.getString(10));
     contact.setPersonZip2(rs.getString(11));
     contact.setPersonId(rs.getInt(1));
     contact.setOwnerId(rs.getInt(14));
     contact.setAddressId(rs.getInt(15));
     }
   
  
     return contact;
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
  
  public void insertContact(DfbsContact contact, int ownerId)  throws SQLException, Exception 
  
  {
   PreparedStatement pstmt = null;
   Connection conn = null;
   try 
   { conn = getConnection();
     int personId = HsDAO.getId(conn,DfbsSQL.SELECT_NEXT_PERSON_ID);
     contact.setPersonId(personId);
     pstmt = conn.prepareStatement(DfbsSQL.INSERT_PERSON);
     pstmt.clearParameters();
     pstmt.setInt(1,ownerId);
     pstmt.setInt(2,personId);
     pstmt.setString(3,contact.getPersonFirstName());
     pstmt.setString(4,contact.getPersonLastName());
     pstmt.setString(5,contact.getPersonPhone());
     pstmt.execute();
   
     this.insertAddress(conn,contact);
    
   } 
   finally 
   {
    try {
       conn.close();
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  
  public void updateContact( DfbsContact contact) 
  throws SQLException, Exception
  {
   PreparedStatement pstmt = null;
   Connection conn = null;
   try 
   { conn = getConnection();
     pstmt = conn.prepareStatement(DfbsSQL.UPDATE_PERSON);
     pstmt.clearParameters();
     pstmt.setString(1,contact.getPersonFirstName());
     pstmt.setString(2,contact.getPersonLastName());
     pstmt.setString(3,contact.getPersonPhone());
     pstmt.setInt(4,contact.getPersonId());
     pstmt.execute();
     if (contact.getAddressId()== 0)
     {
     this.insertAddress(conn,contact);
     }
     else 
     {
     this.updateAddress(conn,contact);
     } 
   } 
   finally 
   {
    try {
       conn.close();
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  
  private static void insertAddress(Connection conn, DfbsContact contact)
  throws SQLException, Exception
  {
   PreparedStatement pstmt = null;
   
   try 
   {
     int addressId = HsDAO.getId(conn,DfbsSQL.SELECT_NEXT_ADDRESS_ID);
     pstmt = conn.prepareStatement(DfbsSQL.INSERT_PERSON_ADDRESS);
     pstmt.clearParameters();
     pstmt.setInt(1,addressId);
     pstmt.setInt(2,contact.getPersonId());
     pstmt.setInt(3,15);
     pstmt.setString(4,contact.getPersonAddress1());
     pstmt.setString(5,contact.getPersonAddress2());
     pstmt.setString(6,contact.getPersonCity());
     pstmt.setString(7,contact.getPersonZip());
     pstmt.setString(8,contact.getPersonEmail());
     pstmt.setString(9,contact.getPersonFax());
     pstmt.execute();
   } 
   finally 
   {
    try {
       
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  
  private static void updateAddress(Connection conn, DfbsContact contact) 
  throws SQLException, Exception
  {
   PreparedStatement pstmt = null;
   
   try 
   {
       
     pstmt = conn.prepareStatement(DfbsSQL.UPDATE_PERSON_ADDRESS);
     pstmt.clearParameters();
     pstmt.setInt(8,contact.getPersonId());
     pstmt.setInt(1,15);
     pstmt.setString(2,contact.getPersonAddress1());
     pstmt.setString(3,contact.getPersonAddress2());
     pstmt.setString(4,contact.getPersonCity());
     pstmt.setString(5,contact.getPersonZip());
     pstmt.setString(6,contact.getPersonEmail());
     pstmt.setString(7,contact.getPersonFax());
     pstmt.execute();
   } 
   finally 
   {
    try {
       
       pstmt.close();
     } catch (Exception e) {}
   }
  }
  // inspection
   public  List selectInspections(String permitNumber) throws SQLException, Exception 
   {
   
     List list = new ArrayList();
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     try 
     {  conn = getConnection();
       pstmt = conn.prepareStatement(DfbsSQL.SELECT_INSPECTIONS);
       pstmt.clearParameters();
       pstmt.setString(1,permitNumber);
       rs = pstmt.executeQuery();
       
       while(rs.next()) 
       { 
         FireInspection  inspection = new FireInspection ();
         inspection.setInspDate(rs.getDate(6));
         inspection.setInspVioPrintDate(rs.getDate(10));
         inspection.setInspAlaram(rs.getString(4));
         inspection.setInspCompliance(rs.getString(7));
         inspection.setInspDistrict(rs.getString(2));
         inspection.setInspFacId(rs.getString(9));
         inspection.setInspInspectorId(rs.getInt(11));
         inspection.setInspOccLoad(rs.getString(3));
         inspection.setInspRemarks(rs.getString(8));
         inspection.setInspSprinkler(rs.getString(5));
         inspection.setInspStatus(rs.getString(12));
         inspection.setInspType(rs.getString(13));
         inspection.setInspId(rs.getInt(1));
         inspection.setInspectorName(rs.getString(14));
         inspection.setEditFlag(rs.getInt(15));
        list.add(inspection);
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
   
    public FireInspection  selectInspection(int idNumber) throws SQLException, Exception 
   {
   
     FireInspection  inspection = new FireInspection ();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
        
     try 
     {
       
       conn = getConnection();
        pstmt = conn.prepareStatement(DfbsSQL.SELECT_INSPECTION_BY_ID);
       pstmt.clearParameters();
       pstmt.setInt(1,idNumber);
       rs = pstmt.executeQuery();
       if(rs.next()) 
       {
         inspection.setInspDate(rs.getDate(6));
         inspection.setInspVioPrintDate(rs.getDate(10));
         inspection.setInspAlaram(rs.getString(4));
         inspection.setInspCompliance(rs.getString(7));
         inspection.setInspDistrict(rs.getString(2));
         inspection.setInspFacId(rs.getString(9));
         inspection.setInspInspectorId(rs.getInt(11));
         inspection.setInspOccLoad(rs.getString(3));
         inspection.setInspRemarks(rs.getString(8));
         inspection.setInspSprinkler(rs.getString(5));
         inspection.setInspStatus(rs.getString(12));
         inspection.setInspType(rs.getString(13));
         inspection.setInspId(rs.getInt(1));
       }
     
    
       return inspection;
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
     public int insertInspection (FireInspection  inspection) throws SQLException, Exception 
   {
   
     Connection conn = null;
     PreparedStatement pstmt = null;
        
     try 
     {
       
       conn = getConnection();
       conn.setAutoCommit(false);
       pstmt = conn.prepareStatement(DfbsSQL.INSERT_INSPECTION);
       int id = this.getId(conn,DfbsSQL.SELECT_NEXT_INSPECTION_ID);
       StringBuffer sb = new StringBuffer("");
       inspection.setInspId(id);
       pstmt.setString(6,inspection.getInspDateString());
       pstmt.setString(10,inspection.getInspVioPrintDateString());
       pstmt.setString(4,inspection.getInspAlaram());
       pstmt.setString(7,inspection.getInspCompliance());
       pstmt.setString(2,inspection.getInspDistrict());
       pstmt.setString(9,inspection.getInspFacId());
       pstmt.setInt(11,inspection.getInspInspectorId());
       pstmt.setString(3,inspection.getInspOccLoad());
       pstmt.setString(8,inspection.getInspRemarks());
       pstmt.setString(5,inspection.getInspSprinkler());
       pstmt.setString(12,inspection.getInspStatus());
       pstmt.setString(13,inspection.getInspType());
       pstmt.setInt(1,id);
       pstmt.execute();
       
    
       conn.commit();
       
        return(id); 
     }
     finally 
     {
      try {
         conn.close();
        pstmt.close();
       } catch (Exception e) {}
     }
   }
   
    public void updateInspection (FireInspection  inspection) throws SQLException, Exception 
   {
     Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
      
       pstmt = conn.prepareStatement(DfbsSQL.UPDATE_INSPECTION);
       pstmt.clearParameters();
       
       pstmt.setString(6,inspection.getInspDateString());
       pstmt.setString(10,inspection.getInspVioPrintDateString());
       pstmt.setString(4,inspection.getInspAlaram());
       pstmt.setString(7,inspection.getInspCompliance());
       pstmt.setString(2,inspection.getInspDistrict());
       pstmt.setString(9,inspection.getInspFacId());
       pstmt.setInt(11,inspection.getInspInspectorId());
       pstmt.setString(3,inspection.getInspOccLoad());
       pstmt.setString(8,inspection.getInspRemarks());
       pstmt.setString(5,inspection.getInspSprinkler());
       pstmt.setString(12,inspection.getInspStatus());
       pstmt.setString(1,inspection.getInspType());
       pstmt.setInt(13,inspection.getInspId());
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
   
   
    public void updateIssueDate (String magId,String inspDate, String appCounty ) throws SQLException, Exception 
   {
     Connection conn = null;
     CallableStatement proc = null;

     try 
     { 
       conn = getConnection();
       conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call fire_mag_add_issue_date(?,?,?) }");
       proc.setString(1, magId);
       proc.setString(2, inspDate);
       proc.setString(3, appCounty);
       proc.execute();

       conn.commit();
       
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
   public  String selectCurrentDate() throws SQLException, Exception 
   {
   
     
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     String currentYear="";
     try 
     {  conn = getConnection();
       
         pstmt = conn.prepareStatement(DfbsSQL.SELECT_CURRENT_DATE);
      
       
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
   // violation
    public  List selectViolations(int inspectionId) throws SQLException, Exception 
     {
    
       List list = new ArrayList();
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       Connection conn = null;
       try 
       {  conn = getConnection();
         pstmt = conn.prepareStatement(DfbsSQL.SELECT_VIOLATIONS);
         pstmt.clearParameters();
         pstmt.setInt(1,inspectionId);
         rs = pstmt.executeQuery();
         
         while(rs.next()) 
         { 
           FireViolation  violation = new FireViolation ();
           violation.setVioCompDate(rs.getDate(7));
           violation.setVioCorDate(rs.getDate(6));
           violation.setVioCode(rs.getString(3));
           violation.setVioDescription(rs.getString(4));
           violation.setVioRemarks(rs.getString(5));
           violation.setVioId(rs.getInt(2));
           violation.setVioOrder(rs.getInt(1));
          list.add(violation);
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
     
      public FireViolation  selectViolation(int idNumber) throws SQLException, Exception 
     {
     
       FireViolation  violation = new FireViolation ();
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
          
       try 
       {
         
         conn = getConnection();
          pstmt = conn.prepareStatement(DfbsSQL.SELECT_VIOLATION_BY_ID);
         pstmt.clearParameters();
         pstmt.setInt(1,idNumber);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
           violation.setVioCompDate(rs.getDate(7));
           violation.setVioCorDate(rs.getDate(6));
           violation.setVioCode(rs.getString(3));
           violation.setVioDescription(rs.getString(4));
           violation.setVioRemarks(rs.getString(5));
           violation.setVioId(rs.getInt(2));
           violation.setVioOrder(rs.getInt(1));
           violation.setInspectionId(rs.getInt(8));
         }
       
      
         return violation;
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
       public int insertViolation (FireViolation  violation) throws SQLException, Exception 
     {
     
       Connection conn = null;
       PreparedStatement pstmt = null;
           
       try 
       {
         
         conn = getConnection();
         conn.setAutoCommit(false);
         pstmt = conn.prepareStatement(DfbsSQL.INSERT_VIOLATION);
         int id = this.getId(conn,DfbsSQL.SELECT_NEXT_VIOLATION_ID);
         violation.setVioId(id);
         StringBuffer sb = new StringBuffer("");
         pstmt.setString(7,violation.getVioCompDateString());
         pstmt.setString(6,violation.getVioCorDateString());
         pstmt.setString(3,violation.getVioCode());
         pstmt.setString(4,violation.getVioDescription());
         pstmt.setString(5,violation.getVioRemarks());
         pstmt.setInt(2,violation.getVioId());
         pstmt.setInt(1,violation.getVioOrder());
          pstmt.setInt(8,violation.getInspectionId());
         pstmt.execute();
         
      
         conn.commit();
         
          return(id); 
       }
       finally 
       {
        try {
           conn.close();
          pstmt.close();
         } catch (Exception e) {}
       }
     }
     
      public void updateViolation (FireViolation  violation) throws SQLException, Exception 
     {
       Connection conn = null;
       PreparedStatement pstmt = null;
       
       try 
       {
         conn = getConnection();
         conn.setAutoCommit(false);
        
         pstmt = conn.prepareStatement(DfbsSQL.UPDATE_VIOLATION);
         pstmt.clearParameters();
         
         pstmt.setString(6,violation.getVioCompDateString());
         pstmt.setString(5,violation.getVioCorDateString());
         pstmt.setString(2,violation.getVioCode());
         pstmt.setString(3,violation.getVioDescription());
         pstmt.setString(4,violation.getVioRemarks());
         pstmt.setInt(7,violation.getVioId());
         pstmt.setInt(1,violation.getVioOrder());
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
       public  List selectStdViolations(String stdViolation) throws SQLException, Exception 
     {
    
       List list = new ArrayList();
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       Connection conn = null;
       try 
       {  conn = getConnection();
       if (stdViolation.equals(""))
         {
         pstmt = conn.prepareStatement(DfbsSQL.SELECT_STANDARD_VIOLATION_ALL);
         }
       else
        {
         pstmt = conn.prepareStatement(DfbsSQL.SELECT_STANDARD_VIOLATION_LIKE);
         pstmt.clearParameters();
         pstmt.setString(1,stdViolation);
         }
         rs = pstmt.executeQuery();
         
         while(rs.next()) 
         { 
           FireViolation  violation = new FireViolation ();
           
           violation.setVioCode(rs.getString(2));
           violation.setVioDescription(rs.getString(1));
          
          list.add(violation);
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
     
     public String  selectStdVioDescription(String stdVioCode) throws SQLException, Exception 
     {
     
       Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
       String stdVioDescription ="";   
       try 
       {
         
         conn = getConnection();
          pstmt = conn.prepareStatement(DfbsSQL.SELECT_STANDARD_VIOLATION);
         pstmt.clearParameters();
         pstmt.setString(1,stdVioCode);
         rs = pstmt.executeQuery();
         if(rs.next()) 
         {
          stdVioDescription=rs.getString(1);
          
         }
       
      
         return stdVioDescription;
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
  public List selectFileList(DfbsOwner owner) throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  {
      conn = getConnection();
    pstmt = conn.prepareStatement(DfbsSQL.SELECT_DOCUMENT_NAMES_OWNER);
    pstmt.clearParameters();
    if(owner != null) {
      pstmt.setString(1,Integer.toString(owner.getOwnerId()));
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
  public List selectFileListNew(DfbsOwner owner) throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  {
      conn = getConnection();
    pstmt = conn.prepareStatement(DfbsSQL.SELECT_DOCUMENT_NAMES_OWNER_NEW);
    pstmt.clearParameters();
    if(owner != null && Integer.toString(owner.getOwnerId()).length()>4 ) {
      pstmt.setString(1,Integer.toString(owner.getOwnerId()));
    }
    else {
      pstmt.setString(1,owner.getOwnerKey());
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
}
