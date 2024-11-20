package code.data;

import code.to.*;
import code.data.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import main.to.*;
import main.data.*;
import hs.data.*;

public class CodeManufacturerDAO extends HsDAO
{

 public CodeManufacturerDAO()
  {
   super();
  }
  
  public CodeManufacturerDAO(HsDataSource pool)
  {
   super(pool);
  }

   
   public List selectManufacturerList(String sql,String param) throws SQLException, Exception 
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
      
        CodeManufacturer manufacturer = new CodeManufacturer();
        
        
         manufacturer.setManufacturerEntityId(rs.getInt(1));
         manufacturer.setManufacturerName(rs.getString(2));
         manufacturer.setManufacturerAddress1(rs.getString(3));
         manufacturer.setManufacturerAddress2(rs.getString(4));
         manufacturer.setManufacturerCity(rs.getString(5));
         manufacturer.setManufacturerState(rs.getString(6));
         manufacturer.setManufacturerZip(rs.getString(7));
         manufacturer.setManufacturerCounty(rs.getString(8));
         manufacturer.setManufacturerCountry(rs.getString(9));
         manufacturer.setManufacturerStatus(rs.getString(10));
         manufacturer.setManufacturerWebPage(rs.getString(11));
         manufacturer.setManufacturerNameId(rs.getInt(12));  
 
        list.add(manufacturer);
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
  
   public CodeManufacturer selectManufacturer(int manufacturerEntityId,CodeFacilityDAO pDAO) throws SQLException, Exception 
  {
    CodeManufacturer manufacturer = new CodeManufacturer();
    Connection conn = null;
     
    try 
    {
      conn = getConnection();
      manufacturer = selectManufacturer(conn,manufacturerEntityId);
      manufacturer.setFacilityList(CodeFacilityDAO.selectFacilities(conn,manufacturerEntityId,manufacturer));
      manufacturer.setReleaseList(CodeDesignReleaseDAO.selectReleases(conn,manufacturerEntityId,manufacturer));
      manufacturer.setPersonMList(CodePersonDAO.selectPersons(conn,manufacturerEntityId,"M",manufacturer,null));
      manufacturer.setManufacturerFees(this.selectManufacturerFees(manufacturerEntityId,conn,pDAO));
      return manufacturer;
    }
    finally 
    {
     try {
        conn.close();
      } catch (Exception e) {}
    }
  }
  
  
  public static CodeManufacturer selectManufacturer(Connection conn,int manufacturerEntityId) throws SQLException, Exception 
  {
  
    CodeManufacturer manufacturer = new CodeManufacturer();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(CodeSQL.SELECT_MANUFACTURER);
      pstmt.clearParameters();
      pstmt.setInt(1,manufacturerEntityId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
         manufacturer.setManufacturerEntityId(rs.getInt(1));
         manufacturer.setManufacturerName(rs.getString(2));
         manufacturer.setManufacturerAddress1(rs.getString(3));
         manufacturer.setManufacturerAddress2(rs.getString(4));
         manufacturer.setManufacturerCity(rs.getString(5));
         manufacturer.setManufacturerState(rs.getString(6));
         manufacturer.setManufacturerZip(rs.getString(7));
         manufacturer.setManufacturerCounty(rs.getString(8));
         manufacturer.setManufacturerCountry(rs.getString(9));
         manufacturer.setManufacturerStatus(rs.getString(10));
         manufacturer.setManufacturerWebPage(rs.getString(11));
         manufacturer.setManufacturerNameId(rs.getInt(12));

      }
      return manufacturer;
    }
    finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
 
 
 
 /* public int insertManufacturer(CodeManufacturer manufacturer) throws SQLException, Exception 
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
  } */
  public void saveRecords(CodeManufacturer manufacturer , int receiptId,String sealDetails) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
//    manufacturer      
      pstmt = conn.prepareStatement(CodeSQL.UPDATE_MANUFACTURER);
      pstmt.clearParameters();
      pstmt.setString(1,manufacturer.getManufacturerWebPage());
      pstmt.setInt(2,manufacturer.getManufacturerEntityId());
      pstmt.execute();
//    fee records sels
      if (manufacturer.getTotalAmountSeals() >0 )
      {
      int idt = this.getId(conn,CodeSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(CodeSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,idt);
      pstmt.setDouble(2,manufacturer.getTotalAmountSeals());
       if (receiptId != manufacturer.getManufacturerNameId())
      {
       pstmt.setString(3,"Seals purchased - " + sealDetails+"- CONFIRMATION " +receiptId);
      }
      else
      {
         pstmt.setString(3,"Seals purchased - " +sealDetails+ manufacturer.getTotalSealsCount());
      }
     
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,new Integer(manufacturer.getManufacturerEntityId()).toString());
      pstmt.setString(6,manufacturer.getManufacturerName());
      pstmt.execute();
      
      }
 //    fee records cdrs
      if (manufacturer.getTotalAmountReleases() > 0)
      {
      int idt = this.getId(conn,CodeSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(CodeSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,idt);
      pstmt.setDouble(2,manufacturer.getTotalAmountReleases());
      if (receiptId != manufacturer.getManufacturerNameId())
      {
      pstmt.setString(3,"releases submitted - " + manufacturer.getDesignReleaseCounter()+"- CONFIRMATION " +receiptId);
      }
      else
      {
        pstmt.setString(3,"releases submitted - " + manufacturer.getDesignReleaseCounter());
      }
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,new Integer(manufacturer.getManufacturerEntityId()).toString());
      pstmt.setString(6,manufacturer.getManufacturerName());
      pstmt.execute();
       
      }
   // manufacturer person  
       Map personManufacturer = manufacturer.getPersonMap();
       Set personkeys = personManufacturer.keySet();
       Iterator i = personkeys.iterator();
       while(i.hasNext()){
       String key = (String) i.next();
        CodePerson person = (CodePerson) personManufacturer.get(key); 
        if (person.isNew())
        {
          int id = this.getId(conn,CodeSQL.SELECT_NEXT_PERSON_ID);
          pstmt = conn.prepareStatement(CodeSQL.INSERT_PERSON);
          pstmt.clearParameters();
          pstmt.setInt(1,id);
          pstmt.setString(2,"M");
          pstmt.setString(3,person.getCodePersonLastName());
          pstmt.setString(4,person.getCodePersonFirstName());
          pstmt.setString(5,person.getCodePersonMiddleName());
          pstmt.setString(6,person.getCodePersonTitle());
          pstmt.setString(7,person.getCodePersonTelephone());
          pstmt.setString(8,person.getCodePersonFax());
          pstmt.setString(9,person.getCodePersonTitle());
          pstmt.setInt(10,manufacturer.getManufacturerEntityId());
          pstmt.execute();
        }
        else
        {
          pstmt = conn.prepareStatement(CodeSQL.UPDATE_PERSON);
          pstmt.setString(1,"M");
          pstmt.setString(2,person.getCodePersonLastName());
          pstmt.setString(3,person.getCodePersonFirstName());
          pstmt.setString(4,person.getCodePersonMiddleName());
          pstmt.setString(5,person.getCodePersonTitle());
          pstmt.setString(6,person.getCodePersonTelephone());
          pstmt.setString(7,person.getCodePersonFax());
          pstmt.setString(8,person.getCodePersonTitle());
          pstmt.setInt(9,person.getCodePersonId());
          pstmt.execute();
        }
       }
    // facility    
       Map facilityMap = manufacturer.getFacilityMap();
       Set facilitykeys = facilityMap.keySet();
       Iterator j = facilitykeys.iterator();
       while(j.hasNext()){
       String keyf = (String) j.next();
       CodeFacility facility = (CodeFacility)  facilityMap.get(keyf);
     // add seals
         if (facility.getMseals() >0 || facility.getPseals()>0)
         {
          pstmt = conn.prepareStatement(CodeSQL.INSERT_FACILITY_SEALS);
          pstmt.clearParameters();
          pstmt.setInt(1,facility.getFacilityId());
          pstmt.setInt(2,facility.getMseals());
          pstmt.setInt(3,facility.getPseals());
          pstmt.setInt(4,receiptId);
          pstmt.execute();
         }
       Map personFacility = facility.getPersonMap();
       Set personkeysf =personFacility.keySet();
       Iterator k = personkeysf.iterator();
       while(k.hasNext()){
       String keypf = (String) k.next();
        CodePerson person = (CodePerson) personFacility.get(keypf); 
        if (person.isNew())
        {
          int id = this.getId(conn,CodeSQL.SELECT_NEXT_PERSON_ID);
          pstmt = conn.prepareStatement(CodeSQL.INSERT_PERSON);
          pstmt.clearParameters();
          pstmt.setInt(1,id);
          pstmt.setString(2,"M");
          pstmt.setString(3,person.getCodePersonLastName());
          pstmt.setString(4,person.getCodePersonFirstName());
          pstmt.setString(5,person.getCodePersonMiddleName());
          pstmt.setString(6,person.getCodePersonTitle());
          pstmt.setString(7,person.getCodePersonTelephone());
          pstmt.setString(8,person.getCodePersonFax());
          pstmt.setString(9,person.getCodePersonTitle());
          pstmt.setInt(10,manufacturer.getManufacturerEntityId());
          pstmt.execute();
        }
        else
        {
          pstmt = conn.prepareStatement(CodeSQL.UPDATE_PERSON);
          pstmt.clearParameters();
          pstmt.setString(1,"M");
          pstmt.setString(2,person.getCodePersonLastName());
          pstmt.setString(3,person.getCodePersonFirstName());
          pstmt.setString(4,person.getCodePersonMiddleName());
          pstmt.setString(5,person.getCodePersonTitle());
          pstmt.setString(6,person.getCodePersonTelephone());
          pstmt.setString(7,person.getCodePersonFax());
          pstmt.setString(8,person.getCodePersonTitle());
          pstmt.setInt(9,person.getCodePersonId());
          pstmt.execute();
        }
       }
       
       }
      // cdrs 
       Map releasesMap = manufacturer.getDesignReleaseMap();
       Set releasekeys = releasesMap.keySet();
       Iterator l = releasekeys.iterator();
       while(l.hasNext()){
       String keyr = (String) l.next();
        CodeDesignRelease release = (CodeDesignRelease) releasesMap.get(keyr); 
         int idRelease = this.getId(conn,CodeSQL.SELECT_NEXT_RELEASE_ID);
          pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR);
          pstmt.clearParameters();
          pstmt.setInt(1,idRelease);
          release.setSystemId(idRelease);
          pstmt.setInt(2,manufacturer.getManufacturerEntityId());
          pstmt.setString(3,release.getSystemType());
          pstmt.setString(4,release.getLengthsOfUnit());
          pstmt.setString(5,release.getOccupancy());
          pstmt.setInt(6,release.getNumberStories());
          pstmt.setInt(7,release.getNumberInstructure());
          pstmt.setString(8,release.getCompletedWidth());
          pstmt.setInt(9,release.getNumberPublic());
          pstmt.setInt(10,release.getNumberPrivate());
          pstmt.setString(11,release.getComments());
          pstmt.setString(12,release.getReleaseNumber());
          pstmt.setString(13,release.getAddReleaseNumber());
          pstmt.setInt(14,release.getAddSequence());
          pstmt.setString(15,release.getCodeReference());
          pstmt.setString(16,release.getConstructionCode());
          pstmt.setString(17,release.getReleaseDateString());
          pstmt.setInt(18,release.getReviewerId());
          pstmt.setString(19,release.getFilingStatus());
          pstmt.setString(20,release.getStructureUse());
          pstmt.setString(21,release.getReleaseType());
          pstmt.setString(22,release.getBuildType());
          pstmt.setString(23,release.getPrevReleaseNumber());
          pstmt.setInt(24,release.getAddPlans());
          pstmt.setInt(25,release.getAddStructures());
          pstmt.setString(26,release.getOccCode());
          pstmt.setString(27,release.getReleaseScope());
          pstmt.setString(28,release.getReleaseApproval());
          pstmt.execute();
          
        // release scopes
        
          if (release.getUnitElectrical()!=null && release.getUnitElectrical().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"EL");
            pstmt.execute();
             
          }
          
          if (release.getUnitFireAlaram() !=null && release.getUnitFireAlaram().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"FA");
            pstmt.execute();
            
          }
           
          if (release.getUnitHood() !=null && release.getUnitHood().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"HS");
            pstmt.execute();
             
          }
          
          if (release.getUnitMechanical() !=null && release.getUnitMechanical().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"ME");
            pstmt.execute();
          }
          
          if (release.getUnitSprinkler() !=null && release.getUnitSprinkler().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"SP");
            pstmt.execute();
          }
           
          if (release.getUnitPlumbing() !=null && release.getUnitPlumbing().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"PL");
            pstmt.execute();
          }
          
          if (release.getUnitStructural() !=null && release.getUnitStructural().equals("on"))
          {
            pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_UNIT_CONTAINS);
            pstmt.clearParameters();
            pstmt.setInt(1,idRelease);
            pstmt.setString(2,"ST");
            pstmt.execute();
          }
      // release manufacturing facilities
       Map relFacilityMap = release.getFacilityMap();
       Set relFacilitykeys = relFacilityMap.keySet();
       Iterator m = relFacilitykeys.iterator();
       while(m.hasNext()){
       String keyfr = (String) m.next();
       CodeFacility relFacility = (CodeFacility)  relFacilityMap.get(keyfr);
       int idFacility = this.getId(conn,CodeSQL.SELECT_NEXT_FACILITY_RELEASE_ID);
       pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_FACILITIES);
       pstmt.clearParameters();
       pstmt.setInt(1,idFacility);
       pstmt.setInt(2,relFacility.getFacilityId());
       pstmt.setInt(3,idRelease);
       pstmt.execute();
       }
       // insert designer
            pstmt = conn.prepareStatement(CodeSQL.INSERT_RELEASE_DESIGNER);
            int id = this.getId(conn,CodeSQL.SELECT_NEXT_REL_DESIGNER_ID);
            pstmt.clearParameters();
            pstmt.setInt(1,id);
            pstmt.setString(2,release.getDesignerFirmName());
            pstmt.setString(3,release.getDesignerFirstName());
            pstmt.setString(4,release.getDesignerLastName());
            pstmt.setString(5,release.getAddress1());
            pstmt.setString(6,release.getAddress2());
            pstmt.setString(7,release.getCity());
            pstmt.setString(8,release.getState());
            pstmt.setString(9,release.getZip());
            pstmt.setString(10,release.getDesignerIndianaNumber());
            pstmt.setString(11,release.getDesignerType());
            pstmt.setString(12,release.getTelephone());
            pstmt.setString(13,release.getEmail());
            pstmt.setInt(14,idRelease);
            pstmt.execute();
       // update document connector
      pstmt = conn.prepareStatement(CodeSQL.UPDATE_DOCUMENT_NAME);
      pstmt.clearParameters();
      pstmt.setString(1,Integer.toString(release.getSystemId()));
      pstmt.setString(2,release.getApplicationKey());
      pstmt.setString(3,"CodeRelease");
       pstmt.execute();
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
  
  public List selectManufacturerFees(int manufacturerEntityId,Connection conn,CodeFacilityDAO pDAO) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int x =0;
    
    try 
    {
      pstmt = conn.prepareStatement(CodeSQL.SELECT_ACCT_DUES);
      pstmt.clearParameters();
      pstmt.setString(1,Integer.toString(manufacturerEntityId));
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        feeDetails f = new feeDetails();
        f.setDue(rs.getString(1));
        f.setAmountPaid(rs.getString(2));
        f.setReceiptNumber(rs.getString(3));
        int lStatus=pDAO.selectSealOrderStatus(Integer.parseInt(rs.getString(7)));
        if(lStatus >0)
        {
          f.setPostDate(rs.getString(4)+" Order processed");
        }
        else
        {
        f.setPostDate(rs.getString(4));
        } 
        f.setDescription(rs.getString(5));
        
        list.add(f);
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

}