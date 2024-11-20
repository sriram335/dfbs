package code.data;
import code.to.*;
import code.data.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import main.to.*;
import main.data.*;
import hs.data.*;
import org.apache.struts.upload.FormFile;

public class CodeDesignReleaseDAO extends HsDAO
{
  public CodeDesignReleaseDAO()
  {
  }
  
protected static   List selectReleases(Connection conn,int manufacturerEntityId,CodeManufacturer manufacturer) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
      pstmt = conn.prepareStatement(CodeSQL.SELECT_RELEASES);
      pstmt.clearParameters();
      pstmt.setInt(1,manufacturerEntityId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        CodeDesignRelease release = new CodeDesignRelease();
        release.setSystemId(rs.getInt(1));
        release.setSystemReleaseNumber(rs.getString(3));
        release.setAddReleaseNumber(rs.getString(4));
        release.setSystemReleaseDate(rs.getDate(22));
        release.setAddSequence(rs.getInt(18));
        release.setReleaseApproval(rs.getString(25));
        release.setFileList(selectFileList(Integer.toString(rs.getInt(1)),conn));
        
        list.add(release);
       /* manufacturer.addRelease(release);*/
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
   public void computeFees(CodeManufacturer manufacturer ) throws Exception 
 {
    Map feeMap = this.selectFeesMapping();
    
    List releases = new ArrayList();
    Map mapRelease = manufacturer.getDesignReleaseMap();
    Set permitkeys = mapRelease.keySet();
    
    Iterator i = permitkeys.iterator();
    double total = 0;
    while(i.hasNext())
    {
     String key = (String) i.next();
     CodeDesignRelease release = (CodeDesignRelease) mapRelease.get(key); 
     double releaseAmount = 0;
      // NORMAL FILING STANDARD
        
 if ( release.getFilingStatus().trim().equals("N"))
   {       
     
   if ( release.getReleaseType().trim().equals("S"))
      {    Double amount = (Double) feeMap.get(new Integer("2"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
      if (release.getBuildType().trim().equals("RESIDENTIAL" ))
      
      { 
           amount = (Double) feeMap.get(new Integer("4"));
           total = total + amount.doubleValue();
           releaseAmount =  releaseAmount + amount.doubleValue();
           
         }
        if ( release.getBuildType().trim().equals("COMMERCIAL" ))
        {
           amount = (Double) feeMap.get(new Integer("6"));
           total = total + amount.doubleValue();
           releaseAmount =  releaseAmount + amount.doubleValue();
        }
      }
   } // LATE FILING SYSTEM
   if ( release.getFilingStatus().trim().equals("L"))
   {      
     
   if ( release.getReleaseType().trim().equals("S"))
      {  Double amount = (Double) feeMap.get(new Integer("3"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
      if (release.getBuildType().trim().equals("RESIDENTIAL" ))
      
      { 
           amount = (Double) feeMap.get(new Integer("5"));
           total = total + amount.doubleValue();
           releaseAmount =  releaseAmount + amount.doubleValue();
           
         }
        if ( release.getBuildType().trim().equals("COMMERCIAL" ))
        {
           amount = (Double) feeMap.get(new Integer("7"));
           total = total + amount.doubleValue();
           releaseAmount =  releaseAmount + amount.doubleValue();
        }
      }
   }// NORMAL ADDENDUM
     if ( release.getFilingStatus().trim().equals("N"))
   {     
     
   if ( release.getReleaseType().trim().equals("A"))
      { Double amount = (Double) feeMap.get(new Integer("8"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
      if (release.getBuildType().trim().equals("RESIDENTIAL" ))
      
      { 
           amount = (Double) feeMap.get(new Integer("10"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
           
         }
        if ( release.getBuildType().trim().equals("COMMERCIAL" ))
        {
           amount = (Double) feeMap.get(new Integer("12"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
        }
      }
      
   }  
   // LATE ADDENDUM
     if ( release.getFilingStatus().trim().equals("L"))
   {       
     
   if ( release.getReleaseType().trim().equals("A"))
      { Double amount = (Double) feeMap.get(new Integer("9"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
      if (release.getBuildType().trim().equals("RESIDENTIAL" ))
      
      { 
           amount = (Double) feeMap.get(new Integer("11"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
           
         }
        if ( release.getBuildType().trim().equals("COMMERCIAL" ))
        {
           amount = (Double) feeMap.get(new Integer("13"));
           total = total + amount.doubleValue();
           releaseAmount = releaseAmount + amount.doubleValue();
        }
      }
       
   }  // additional plans and structures
     if ( release.getAddPlans() > 0 ) 
       {
         Double amount = (Double) feeMap.get(new Integer("14"));
           total = total + amount.doubleValue()* release.getAddPlans();
           releaseAmount = releaseAmount + amount.doubleValue()* release.getAddPlans();
       }
        if ( release.getAddStructures() > 0  ) 
       {
         Double amount = (Double) feeMap.get(new Integer("15"));
           total = total + amount.doubleValue()* release.getAddStructures();
           releaseAmount = releaseAmount + amount.doubleValue()* release.getAddStructures();
       }
   release.setReleaseFee(releaseAmount);
   }    
   manufacturer.setTotalAmountReleases(total);
 
 }
  public void  uploadFile(FormFile oneFile,String systemIdNumber) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,SecuritySQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(CodeSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,"CodeRelease");
       pstmt.setString(6,systemIdNumber);
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

    public List selectFileList(String appKey) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_DOCUMENT_NAMES_ALL);
      pstmt.clearParameters();
      if(appKey != null) {
        pstmt.setString(1,appKey);
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
   String sql = CodeSQL.SELECT_DOCUMENT; 
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
  public void updateCDR(CodeDesignRelease release,CodeManufacturer manufacturer) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
//    manufacturer      
      pstmt = conn.prepareStatement(CodeSQL.UPDATE_CDR);
      pstmt.clearParameters();
          pstmt.setString(1,release.getFilingStatus());
          pstmt.setInt(2,release.getManufacturerEntityId());
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
          pstmt.setString(19,release.getStructureUse());
          pstmt.setString(20,release.getReleaseType());
          pstmt.setString(21,release.getOccCode());
          pstmt.setString(22,release.getBuildType());
          pstmt.setString(23,release.getPrevReleaseNumber());
          pstmt.setInt(24,release.getAddPlans());
          pstmt.setInt(25,release.getAddStructures());
          pstmt.setString(26,release.getReleaseScope());
          pstmt.setString(27,release.getReleaseApproval());
          pstmt.setInt(28,release.getSystemId());
          
          
          pstmt.execute();
      // designer update
       if (release.getDesignerId()==0)
       {
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
            pstmt.setInt(14,release.getSystemId());
            pstmt.execute();
       }
       else
       {
         pstmt = conn.prepareStatement(CodeSQL.UPDATE_RELEASE_DESIGNER);
        pstmt.clearParameters();
              pstmt.setString(1,release.getDesignerFirmName());
              pstmt.setString(2,release.getDesignerFirstName());
              pstmt.setString(3,release.getDesignerLastName());
              pstmt.setString(4,release.getAddress1());
              pstmt.setString(5,release.getAddress2());
              pstmt.setString(6,release.getCity());
              pstmt.setString(7,release.getState());
              pstmt.setString(8,release.getZip());
              pstmt.setString(9,release.getDesignerIndianaNumber());
              pstmt.setString(10,release.getDesignerType());
              pstmt.setString(11,release.getTelephone());
              pstmt.setString(12,release.getEmail());
              pstmt.setInt(13,release.getSystemId());
             
              pstmt.execute(); 
       }
       // unit contains
       
       this.updateCDRScopes (release.getSystemId(),"EL",release.getUnitElectrical());
       this.updateCDRScopes (release.getSystemId(),"FA",release.getUnitFireAlaram());
       this.updateCDRScopes (release.getSystemId(),"HS",release.getUnitHood());   
       this.updateCDRScopes (release.getSystemId(),"ME",release.getUnitMechanical());
       this.updateCDRScopes (release.getSystemId(),"SP",release.getUnitSprinkler());
       this.updateCDRScopes (release.getSystemId(),"PL",release.getUnitPlumbing());
       this.updateCDRScopes (release.getSystemId(),"ST",release.getUnitStructural());
       
       
      conn.commit();
    } 
    
     catch (Exception ex) 
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
  public CodeDesignRelease selectCDR(int systemId,CodeManufacturer manufacturer) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
     ResultSet rs = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
//    manufacturer      
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CDR);
      pstmt.clearParameters();
      pstmt.setInt(1,systemId);
      rs = pstmt.executeQuery();
      CodeDesignRelease release = new CodeDesignRelease();
      while(rs.next()) 
      {   release.setSystemId(rs.getInt(1));
          release.setFilingStatus(rs.getString(2));
          release.setManufacturerEntityId(rs.getInt(3));
          release.setSystemType(rs.getString(4));
          release.setLengthsOfUnit(rs.getString(5));
          release.setOccupancy(rs.getString(6));
          release.setNumberStories(rs.getInt(7));
          release.setNumberInstructure(rs.getInt(8));
          release.setCompletedWidth(rs.getString(9));
          release.setNumberPublic(rs.getInt(10));
          release.setNumberPrivate(rs.getInt(11));
          release.setComments(rs.getString(12));
          release.setReleaseNumber(rs.getString(13));
          release.setAddReleaseNumber(rs.getString(14));
          release.setAddSequence(rs.getInt(15));
          release.setOccCode(rs.getString(16));
          release.setConstructionCode(rs.getString(17));
          release.setReleaseDate(rs.getDate(18));
          release.setReviewerId(rs.getInt(19));
          release.setStructureUse(rs.getString(20));
          release.setReleaseType(rs.getString(21));
          release.setOccCode(rs.getString(22));
          release.setBuildType(rs.getString(23));
          release.setCodeReference(rs.getString(24));
          release.setAddPlans(rs.getInt(25));
          release.setAddStructures(rs.getInt(26));
          release.setPrevReleaseNumber(rs.getString(27));
          release.setReleaseScope(rs.getString(28));
          release.setReleaseApproval(rs.getString(29));
         this.selectUnitContains(rs.getInt(1),release);
        this.selectManFacility(rs.getInt(1),release,manufacturer);
        this.selectDesigner(rs.getInt(1),release);
        this.selectCannedCodes(rs.getInt(1),release);
        release.setFileList(this.selectFileList(Integer.toString(rs.getInt(1))));
      }
     return release;
    } 
     catch (Exception ex) 
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
  public CodeDesignRelease selectUnitContains(int systemId,CodeDesignRelease release) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
     ResultSet rs = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
//    manufacturer      
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CDR_UNIT_CONTAINS);
      pstmt.clearParameters();
      pstmt.setInt(1,systemId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {   
        if (rs.getString(2).equals("PL"))
         {
           release.setUnitPlumbing("true");
         }
          if (rs.getString(2).equals("SP"))
         {
           release.setUnitSprinkler("true");
         }
          if (rs.getString(2).equals("FA"))
         {
           release.setUnitFireAlaram("true");
         }
          if (rs.getString(2).equals("EL"))
         {
           release.setUnitElectrical("true");
         }
          if (rs.getString(2).equals("ME"))
         {
           release.setUnitMechanical("true");
         }
          if (rs.getString(2).equals("HS"))
         {
           release.setUnitHood("true");
         }
          if (rs.getString(2).equals("ST"))
         {
           release.setUnitStructural("true");
         }
      }
     return release;
    } 
     catch (Exception ex) 
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
  public  CodeDesignRelease selectDesigner(int systemId,CodeDesignRelease release) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
     ResultSet rs = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(CodeSQL.SELECT_RELEASE_DESIGNER);
      pstmt.clearParameters();
      pstmt.setInt(1,systemId);
      rs = pstmt.executeQuery();
          while(rs.next()) 
          {   release.setDesignerId(rs.getInt(1));
              release.setDesignerFirmName(rs.getString(2));
              release.setDesignerFirstName(rs.getString(3));
              release.setDesignerLastName(rs.getString(4));
              release.setAddress1(rs.getString(5));
              release.setAddress2(rs.getString(6));
              release.setCity(rs.getString(7));
              release.setState(rs.getString(8));
              release.setZip(rs.getString(9));
              release.setDesignerIndianaNumber(rs.getString(10));
              release.setDesignerType(rs.getString(11));
              release.setTelephone(rs.getString(12));
              release.setEmail(rs.getString(13));
              
             
          }
      
     return release;
    } 
     catch (Exception ex) 
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
  
   
   public void updateCDRScopes (int systemId,String scopeType,String scopeValue ) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call update_code_release_scopes(?,?,?) }");
      proc.setInt(1, systemId);
      proc.setString(2, scopeType);
      proc.setString(3, scopeValue);
      proc.execute();

      
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
  
    public void updateFacility (int systemId,int facilityId,String scopeValue ) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    { 
      conn = getConnection();
      conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call update_code_rel_facilities(?,?,?) }");
      proc.setInt(1, systemId);
      proc.setInt(2, facilityId);
      proc.setString(3, scopeValue);
      proc.execute();
      
      
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
   public CodeDesignRelease selectManFacility(int systemId,CodeDesignRelease release,CodeManufacturer manufacturer) throws SQLException, Exception 
  {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
     ResultSet rs = null;
     int facilityCount =1;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CDR_FACILITIES);
      pstmt.clearParameters();
      pstmt.setInt(1,systemId);
      rs = pstmt.executeQuery();
          while(rs.next()) 
          {  
              if (facilityCount ==1)
              {
                release.setFacilityId1(rs.getString(1));
              }
              if (facilityCount ==2)
              {
                release.setFacilityId2(rs.getString(1));
              }
              if (facilityCount ==3)
              {
                release.setFacilityId3(rs.getString(1));
              }
              if (facilityCount ==4)
              {
                release.setFacilityId4(rs.getString(1));
              }
              facilityCount=facilityCount+1;
          }
      
      
     return release;
    } 
     catch (Exception ex) 
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
  
   public  List selectStdCodes()  throws SQLException, Exception 
  {
 
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection(); 
      pstmt = conn.prepareStatement(CodeSQL.SELECT_STANDARD_CODE);
      pstmt.clearParameters();
      
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
        CannedCodes  code = new CannedCodes ();
        
        code.setCodeId(rs.getInt(1));
        code.setCannedCode(rs.getString(2));
       
       list.add(code);
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
   public String  selectStdCode(int codeId) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String stdCode ="";   
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(CodeSQL.SELECT_ADD_CODE);
      pstmt.clearParameters();
      pstmt.setInt(1,codeId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
       stdCode=rs.getString(1);
       
      }
    
   
      return stdCode;
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
  
   public  CodeDesignRelease selectCannedCodes(int systemId, CodeDesignRelease release)  throws SQLException, Exception 
  {
 
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CDR_CODE);
      pstmt.clearParameters();
      pstmt.setInt(1,systemId);
      rs = pstmt.executeQuery();
     
      while(rs.next()) 
      { 
        CannedCodes  code = new CannedCodes ();
        code.setCodeId(rs.getInt(2));
        code.setCannedCode(rs.getString(3));
       
       list.add(code);
      }
      release.setCannedCodes(list);
      return release;
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
     public void addStdCode (String cannedCode,CodeDesignRelease release ) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
     try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
       pstmt = conn.prepareStatement(CodeSQL.INSERT_CDR_CODE);
      pstmt.clearParameters();
      pstmt.setInt(1,release.getSystemId());
      pstmt.setString(2,cannedCode);
      pstmt.execute();
       conn.commit();
    }
   
    catch (Exception ex) 
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
  
   public  List selectSystemType()  throws SQLException, Exception 
  {
 
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection(); 
      pstmt = conn.prepareStatement(CodeSQL.SELECT_CDR_SYSTEM_TYPE_OPTIONS);
      pstmt.clearParameters();
      
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
        CannedCodes  code = new CannedCodes ();
        
        code.setCodeId(rs.getInt(1));
        code.setCannedCode(rs.getString(2));
       
       list.add(code);
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
   public  String selectCannedCode(int codeId)  throws SQLException, Exception 
  {
 
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(CodeSQL.SELECT_SAVED_CODE);
      pstmt.clearParameters();
      pstmt.setInt(1,codeId);
      rs = pstmt.executeQuery();
     CannedCodes  code = new CannedCodes ();
      while(rs.next()) 
      { 
        
        code.setCodeId(rs.getInt(2));
        code.setCannedCode(rs.getString(3));
       
       
      }
      
      return code.getCannedCode();
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
   public  void removeCannedCode(int codeId)  throws SQLException, Exception 
  {
 
    List list = new ArrayList();
     PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { 
      conn = getConnection();
      conn.setAutoCommit(true);
//    manufacturer      
      pstmt = conn.prepareStatement(CodeSQL.REMOVE_SAVED_CODE);
      pstmt.clearParameters();
          
          pstmt.setInt(1,codeId);
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
  
    protected static List selectFileList(String appKey,Connection conn) throws SQLException, Exception 
  {
    List list = new ArrayList();
     ResultSet rs = null;
    PreparedStatement pstmt = null;
  try 
    {
   
      pstmt = conn.prepareStatement(CodeSQL.SELECT_DOCUMENT_NAMES_ALL);
      pstmt.clearParameters();
      if(appKey != null) {
        pstmt.setString(1,appKey);
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
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  
   public   List selectReleasesList(CodeManufacturer manufacturer) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { 
      conn = getConnection();
      conn.setAutoCommit(true);
      pstmt = conn.prepareStatement(CodeSQL.SELECT_RELEASES);
      pstmt.clearParameters();
      pstmt.setInt(1,manufacturer.getManufacturerEntityId());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        CodeDesignRelease release = new CodeDesignRelease();
        release.setSystemId(rs.getInt(1));
        release.setSystemReleaseNumber(rs.getString(3));
        release.setAddReleaseNumber(rs.getString(4));
        release.setSystemReleaseDate(rs.getDate(22));
        release.setAddSequence(rs.getInt(18));
        release.setReleaseApproval(rs.getString(25));
        release.setFileList(selectFileList(Integer.toString(rs.getInt(1)),conn));
        
        list.add(release);
       /* manufacturer.addRelease(release);*/
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
 // END  
}
