package childcare.data;

import childcare.data.*;

import java.util.*;
import java.sql.*;
import java.io.InputStream;
import java.io.OutputStream;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import org.apache.struts.upload.FormFile;
import hs.data.*;
import childcare.to.*;
import main.to.*;
public class DfbsChildcarePermitDAO extends HsDAO
{
  
   
  
   public DfbsChildcarePermitDAO()
   {
     
   }
  
 public static List selectPermits(Connection conn,int ownerId ,String byType, String byName,DfbsOwner owner) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsChildcarePermit childCare = new DfbsChildcarePermit();
        childCare.setEndDate(rs.getDate(23));
        childCare.setReceivedDate(rs.getDate(24));
        childCare.setStartDate(rs.getDate(22));
        childCare.setActive(rs.getString(11));
        childCare.setAddress1(rs.getString(3));
        childCare.setAddress2(rs.getString(4));
        childCare.setCity(rs.getString(5));
        childCare.setCounty(rs.getString(7));
        childCare.setDaycareName(rs.getString(2));
        childCare.setDaycareType(rs.getString(21));
        childCare.setEmail(rs.getString(20));
        childCare.setFax(rs.getString(19));
        childCare.setFdId(rs.getString(13));
        childCare.setNotes(rs.getString(25));
        childCare.setPermitNumber(rs.getString(1));
        childCare.setState(rs.getString(6));
        childCare.setStructureType(rs.getString(10));
        childCare.setTelephone(rs.getString(18));
        childCare.setZip(rs.getString(8));
        childCare.setZip2(rs.getString(9));
        childCare.setOwnerId(rs.getInt(14));
        String inspectorName=findInspectorName(childCare.getCounty(),childCare.getZip(),conn);
        childCare.setInspectorName(inspectorName);
        getFssaStatus(conn,childCare);
       list.add(childCare);
      // owner.addPermit(childCare);
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
  
  public  List selectPermits(String sql ,String filterValue, String chilcareType) throws SQLException, Exception 
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
      if(filterValue != null) {
        pstmt.setString(2,chilcareType);
        pstmt.setString(1,filterValue);
      }
      rs = pstmt.executeQuery();
       while(rs.next()) 
      {
        DfbsChildcarePermit childCare = new DfbsChildcarePermit();
        childCare.setEndDate(rs.getDate(23));
        childCare.setReceivedDate(rs.getDate(24));
        childCare.setStartDate(rs.getDate(22));
        childCare.setActive(rs.getString(11));
        childCare.setAddress1(rs.getString(3));
        childCare.setAddress2(rs.getString(4));
        childCare.setCity(rs.getString(5));
        childCare.setCounty(rs.getString(7));
        childCare.setDaycareName(rs.getString(2));
        childCare.setDaycareType(rs.getString(21));
        childCare.setEmail(rs.getString(20));
        childCare.setFax(rs.getString(19));
        childCare.setFdId(rs.getString(13));
        childCare.setNotes(rs.getString(25));
        childCare.setPermitNumber(rs.getString(1));
        childCare.setState(rs.getString(6));
        childCare.setStructureType(rs.getString(10));
        childCare.setTelephone(rs.getString(18));
        childCare.setZip(rs.getString(8));
        childCare.setZip2(rs.getString(9));
        childCare.setOwnerId(rs.getInt(14));
        String inspectorName=findInspectorName(childCare.getCounty(),childCare.getZip(),conn);
        childCare.setInspectorName(inspectorName);
        getFssaStatus(conn,childCare);
       list.add(childCare);
      // owner.addPermit(childCare);
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
 
  
  
  
  public int insertPermit(DfbsChildcarePermit childCare,int ownerId, String permitType) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    try 
    { 
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_CHILDCARE_PERMIT);
      int id = 0;
      String prefix="";
      
      if (childCare.getStructureType().equals("Migrant Day Care"))
      {
        prefix="MD";
        id = this.getId(conn,DfbsChildcareSQL.SELECT_NEXT_PERMIT_ID);
        childCare.setPermitNumber(prefix + Integer.toString(id));
      }
       pstmt.setString(11,"A");
      pstmt.setString(3,childCare.getAddress1());
      pstmt.setString(4,childCare.getAddress2());
      pstmt.setString(5,childCare.getCity());
      pstmt.setString(7,childCare.getCounty());
      pstmt.setString(2,childCare.getDaycareName());
      pstmt.setString(17,childCare.getDaycareType());
      pstmt.setString(16,childCare.getEmail());
      pstmt.setString(15,childCare.getFax());
      pstmt.setString(12,childCare.getFdId());
      pstmt.setString(18,childCare.getNotes());
      pstmt.setString(1,childCare.getPermitNumber());
      pstmt.setString(6,childCare.getState());
      pstmt.setString(10,childCare.getStructureType());
      pstmt.setString(14,childCare.getTelephone());
      pstmt.setString(8,childCare.getZip());
      pstmt.setString(9,childCare.getZip2());
      pstmt.setInt(13,ownerId);
       
      
      pstmt.execute();
      pstmt1 = conn.prepareStatement(DfbsChildcareSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,childCare.getPermitNumber());
      pstmt1.setString(2,childCare.getApplicationKey());
      pstmt1.setString(3,"Childcare");
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
        pstmt.close();
        pstmt1.close();
      } catch (Exception e) {}
    }
  }
  public void updatePermit(DfbsChildcarePermit childCare) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsChildcareSQL.UPDATE_CHILDCARE_PERMIT);
      pstmt.clearParameters();
      
    
      pstmt.setString(11,"A");
      pstmt.setString(3,childCare.getAddress1());
      pstmt.setString(4,childCare.getAddress2());
      pstmt.setString(5,childCare.getCity());
      pstmt.setString(7,childCare.getCounty());
      pstmt.setString(2,childCare.getDaycareName());
      pstmt.setString(17,childCare.getDaycareType());
      pstmt.setString(16,childCare.getEmail());
      pstmt.setString(15,childCare.getFax());
      pstmt.setString(12,childCare.getFdId());
      pstmt.setString(1,childCare.getNotes());
      pstmt.setString(18,childCare.getPermitNumber());
      pstmt.setString(6,childCare.getState());
      pstmt.setString(10,childCare.getStructureType());
      pstmt.setString(14,childCare.getTelephone());
      pstmt.setString(8,childCare.getZip());
      pstmt.setString(9,childCare.getZip2());
      pstmt.setInt(13,childCare.getOwnerId());
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
 
  
  public double selectFeesMapping() throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    double total=0;
   try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_FEES_CHILDCARE);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      {
      // Double  amount = (Double) (rs.getDouble(2));
      // total= amount.doubleValue();
      total= rs.getDouble(1);
      }
      
       return (total);
    } 
   
    finally 
    {
     try {
        rs.close();
        conn.close();
        pstmt.close();
      } catch (Exception e) {}
    }
    
   
  }
  
  
  
  
 public void insertPermitTransaction(DfbsChildcarePermit permit, DfbsOwner owner) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,DfbsChildcareSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,permit.getAmount());
      StringBuffer sb = new StringBuffer();
      pstmt.setString(3,"Annual Registered Ministry Fee: " +permit.getPermitNumber()+" CONFIRMATION "+permit.getReceiptId());
      pstmt.setInt(4,permit.getReceiptId());
      pstmt.setString(5,permit.getPermitNumber());
      pstmt.setString(6,owner.getOwnerLastName());
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
  
  public void computeFees(ShoppingCart cart) throws Exception 
 {
     
    double renewFee= selectFeesMapping();
    
     double totalCart = 0;
     cart.setTotalPermits(0);
     List ownerList = new ArrayList();
    Map mapOwners= cart.getOwnerMap();
    Set ownerkeys = mapOwners.keySet();
    Iterator j = ownerkeys.iterator();
    while(j.hasNext())
    {double totalOwner = 0;
     String ownerkey = (String) j.next();
     DfbsOwner owner = (DfbsOwner) mapOwners.get(ownerkey); 
     ownerList.add(owner);
    Map mapPermit = owner.getChildCaresMap();
    Set permitkeys = mapPermit.keySet();
    Iterator i = permitkeys.iterator();
   List permitList = new ArrayList();
    while(i.hasNext())
    {
     String key = (String) i.next();
     DfbsChildcarePermit permit = (DfbsChildcarePermit) mapPermit.get(key); 
     permitList.add(permit);
     totalOwner=totalOwner + renewFee;
     totalCart=totalCart + renewFee;
     permit.setAmount(renewFee);
     cart.setTotalPermits(cart.getTotalPermits()+1);
    }
    owner.setChildCares(permitList);
     owner.setAmount(totalOwner);
    }    
    cart.setOwnerList(ownerList);
   cart.setAmount(totalCart);
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
     // pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_PERMITS_BY_STREET);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,streetNumber);
     
      rs = pstmt.executeQuery();
      }
     
      if (byType == "byPermit")
      {
     // pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_PERMITS_BY_PERMIT);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,streetNumber);
     
      rs = pstmt.executeQuery();
      }
     
      if (byType == "byStatus")
      {
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_PERMITS_BY_STATUS);
       pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      }
      while(rs.next()) 
      { 
        DfbsChildcarePermit childCare = new DfbsChildcarePermit();
        childCare.setEndDate(rs.getDate(23));
        childCare.setReceivedDate(rs.getDate(24));
        childCare.setStartDate(rs.getDate(22));
        childCare.setActive(rs.getString(11));
        childCare.setAddress1(rs.getString(3));
        childCare.setAddress2(rs.getString(4));
        childCare.setCity(rs.getString(5));
        childCare.setCounty(rs.getString(7));
        childCare.setDaycareName(rs.getString(2));
        childCare.setDaycareType(rs.getString(21));
        childCare.setEmail(rs.getString(20));
        childCare.setFax(rs.getString(19));
        childCare.setFdId(rs.getString(13));
        childCare.setNotes(rs.getString(25));
        childCare.setPermitNumber(rs.getString(1));
        childCare.setState(rs.getString(6));
        childCare.setStructureType(rs.getString(10));
        childCare.setTelephone(rs.getString(18));
        childCare.setZip(rs.getString(8));
        childCare.setZip2(rs.getString(9));
        childCare.setOwnerId(rs.getInt(14));
        String inspectorName=findInspectorName(childCare.getCounty(),childCare.getZip(),conn);
        childCare.setInspectorName(inspectorName);
         getFssaStatus(conn,childCare);
       list.add(childCare);
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
  public DfbsChildcarePermit selectPermit(String pemitNumber ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     Connection conn = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_PERMIT);
      pstmt.clearParameters();
      pstmt.setString(1,pemitNumber);
     DfbsChildcarePermit childCare = new DfbsChildcarePermit();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        
        
        childCare.setEndDate(rs.getDate(23));
        childCare.setReceivedDate(rs.getDate(24));
        childCare.setStartDate(rs.getDate(22));
        childCare.setActive(rs.getString(11));
        childCare.setAddress1(rs.getString(3));
        childCare.setAddress2(rs.getString(4));
        childCare.setCity(rs.getString(5));
        childCare.setCounty(rs.getString(7));
        childCare.setDaycareName(rs.getString(2));
        childCare.setDaycareType(rs.getString(21));
        childCare.setEmail(rs.getString(20));
        childCare.setFax(rs.getString(19));
        childCare.setFdId(rs.getString(13));
        childCare.setNotes(rs.getString(25));
        childCare.setPermitNumber(rs.getString(1));
        childCare.setState(rs.getString(6));
        childCare.setStructureType(rs.getString(10));
        childCare.setTelephone(rs.getString(18));
        childCare.setZip(rs.getString(8));
        childCare.setZip2(rs.getString(9));
        childCare.setOwnerId(rs.getInt(14));
         String inspectorName=findInspectorName(childCare.getCounty(),childCare.getZip(),conn);
        childCare.setInspectorName(inspectorName);
         }
      
      return childCare;
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
     
     
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_DOCUMENT_COUNT);
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
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_ACCT_DUES);
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
   public void  uploadFile(FormFile oneFile,String permitNumber) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,DfbsChildcareSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(DfbsChildcareSQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,"web");
       pstmt.setString(6,permitNumber);
        pstmt.setString(7,"ChildCare");
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

    public List selectFileList(String permitIdNumber) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_DOCUMENT_NAMES_ALL);
      pstmt.clearParameters();
      if(permitIdNumber != null) {
        pstmt.setString(1,permitIdNumber);
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
   String sql = DfbsChildcareSQL.SELECT_DOCUMENT; 
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
   public static List selectPermits(Connection conn,int ownerId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        DfbsChildcarePermit childCare = new DfbsChildcarePermit();
        childCare.setEndDate(rs.getDate(23));
        childCare.setReceivedDate(rs.getDate(24));
        childCare.setStartDate(rs.getDate(22));
        childCare.setActive(rs.getString(11));
        childCare.setAddress1(rs.getString(3));
        childCare.setAddress2(rs.getString(4));
        childCare.setCity(rs.getString(5));
        childCare.setCounty(rs.getString(7));
        childCare.setDaycareName(rs.getString(2));
        childCare.setDaycareType(rs.getString(21));
        childCare.setEmail(rs.getString(20));
        childCare.setFax(rs.getString(19));
        childCare.setFdId(rs.getString(13));
        childCare.setNotes(rs.getString(25));
        childCare.setPermitNumber(rs.getString(1));
        childCare.setState(rs.getString(6));
        childCare.setStructureType(rs.getString(10));
        childCare.setTelephone(rs.getString(18));
        childCare.setZip(rs.getString(8));
        childCare.setZip2(rs.getString(9));
        childCare.setOwnerId(rs.getInt(14));
        String inspectorName=findInspectorName(childCare.getCounty(),childCare.getZip(),conn);
        childCare.setInspectorName(inspectorName);
         getFssaStatus(conn,childCare);
       list.add(childCare);
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
  
  public static DfbsChildcarePermit getFssaStatus(Connection conn,DfbsChildcarePermit childCare ) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
           
    try 
    {
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_FSSA);
      pstmt.clearParameters();
      pstmt.setString(1,childCare.getPermitNumber());
      pstmt.setString(2,childCare.getPermitNumber());
      pstmt.setString(3,childCare.getPermitNumber());
      pstmt.setString(4,childCare.getPermitNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        childCare.setFssaExpDate(rs.getDate(1));
        childCare.setFssaStatus(rs.getString(2));
        
      }
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_INSP_LAST);
      pstmt.clearParameters();
      pstmt.setString(1,childCare.getPermitNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        childCare.setLastInspDate(rs.getDate(1));
               
      }
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_INSP_VIO);
      pstmt.clearParameters();
      pstmt.setString(1,childCare.getPermitNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        childCare.setLastInspVioDate(rs.getDate(1));
        
      }
      pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CHILDCARE_INSP_COMP);
      pstmt.clearParameters();
      pstmt.setString(1,childCare.getPermitNumber());
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        childCare.setLastInspCompDate(rs.getDate(1));
        
      }
      return childCare;
    }
 finally 
    {
     try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
 
}

public void  childCareReassignId (String idOld,String idNew ) throws SQLException, Exception 
  {
    CallableStatement proc = null;
    Connection conn = null;
    try 
    {  conn = getConnection();  
      proc = conn.prepareCall("{ call FIRE_DAY_CARE_REASSIGN_ID(?,?) }");
      proc.setString(1, idOld);
      proc.setString(2, idNew);
      proc.execute();
     } catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try { conn.close();
          proc.close();
         
      } catch (Exception e) {}
    }
  } 
  public int  childCareNewStatus (String idNew ) throws SQLException, Exception 
  {
    CallableStatement proc = null;
    Connection conn = null;
    try 
    {  conn = getConnection();  
      proc = conn.prepareCall("{ call CHECK_RM_NEW_STATUS(?,?) }");
      proc.setString(1, idNew);
       proc.registerOutParameter(2,Types.INTEGER);
      proc.execute();
     return(proc.getInt(2)); 
    }  
    
      catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try { conn.close();
          proc.close();
        
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
         
           pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_CURRENT_YEAR);
        
         
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
         
           pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_INSPECTOR);
        
         
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
  public  int selectFacilityNewOld(String facilityId) throws SQLException, Exception 
   {
   
     
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     int facilityCount=0;
     try 
     {  conn = getConnection();
       
         pstmt = conn.prepareStatement(DfbsChildcareSQL.SELECT_FACILITY_COUNT);
      
       
       pstmt.clearParameters();
       pstmt.setString(1,facilityId);
       rs = pstmt.executeQuery();
       
       while(rs.next()) 
       { 
         
         facilityCount=rs.getInt(1);
         
       }
       
       return facilityCount;
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