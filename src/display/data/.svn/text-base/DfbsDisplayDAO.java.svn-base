package display.data;

import display.to.*;
import main.to.*;
import main.data.*;
import java.util.*;
import java.sql.*;
import java.io.InputStream;
import java.io.OutputStream;
import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;
import org.apache.struts.upload.FormFile;
import hs.data.*;

public class DfbsDisplayDAO extends HsDAO
{
  public DfbsDisplayDAO()
  {
  }
 protected static List selectPermits(Connection conn,int ownerId,String byType, String byName) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { if (byType == "byCounty")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS_BY_COUNTY);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "byCity")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS_BY_CITY);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "byStreet")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS_BY_STREET);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,byName);
      rs = pstmt.executeQuery();
      }
      
      if (byType == "byPermit")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS_BY_PERMIT);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      pstmt.setString(2,byName);
      rs = pstmt.executeQuery();
      }
      if (byType == "All")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS);
      pstmt.clearParameters();
      pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      }
      if (byType == "byStatus")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS_BY_STATUS);
       pstmt.clearParameters();
      //pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      }
      if (byType == "byOwnerName")
      {
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMITS_BY_OWNER_NAME);
       pstmt.clearParameters();
       pstmt.setInt(1,ownerId);
      rs = pstmt.executeQuery();
      }
      
      while(rs.next()) 
      { 
        DfbsDisplay display = new DfbsDisplay();
        display.setDisplayAddress1(rs.getString(4));
        display.setDisplayAddress2(rs.getString(5));
        display.setDisplayCity(rs.getString(6));
        display.setDisplayComments(rs.getString(20));
        display.setDisplayCounty(rs.getString(21));
        display.setDisplayDateFrom(rs.getDate(2));
        display.setDisplayDateTo(rs.getDate(14));
        display.setDisplayEmail(rs.getString(17));
        display.setDisplayFax(rs.getString(16));
        display.setDisplayFd(rs.getString(12));
        display.setDisplayIdNumber(rs.getString(1));
        display.setDisplayIssueDate(rs.getDate(10));
        display.setDisplayOnlineStatus(rs.getString(19));
        display.setDisplayPhone(rs.getString(15));
        display.setDisplayState(rs.getString(7));
        display.setDisplayTime(rs.getString(3));
        display.setDisplayZip(rs.getString(8));
        display.setDisplayZip2(rs.getString(9));
        display.setDisplayOwnerId(rs.getInt(13));
        display.setDisplayCountyName(rs.getString(22));
        display.setDisplayValid(rs.getInt(23));
       list.add(display);
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
  
   public DfbsDisplay selectPermit(String idNumber) throws SQLException, Exception 
  {
  
    DfbsDisplay display = new DfbsDisplay();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
      
    try 
    {
      
      conn = getConnection();
       pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMIT_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
        display.setDisplayAddress1(rs.getString(4));
        display.setDisplayAddress2(rs.getString(5));
        display.setDisplayCity(rs.getString(6));
        display.setDisplayComments(rs.getString(20));
        display.setDisplayCounty(rs.getString(21));
        display.setDisplayDateFrom(rs.getDate(2));
        display.setDisplayDateTo(rs.getDate(14));
        display.setDisplayEmail(rs.getString(17));
        display.setDisplayFax(rs.getString(16));
        display.setDisplayFd(rs.getString(12));
        display.setDisplayIdNumber(rs.getString(1));
        display.setDisplayIssueDate(rs.getDate(10));
        display.setDisplayOnlineStatus(rs.getString(19));
        display.setDisplayPhone(rs.getString(15));
        display.setDisplayState(rs.getString(7));
        display.setDisplayTime(rs.getString(3));
        display.setDisplayZip(rs.getString(8));
        display.setDisplayZip2(rs.getString(9));
        display.setDisplayOwnerId(rs.getInt(13));
        display.setDisplayFdEmail(rs.getString(22));
        display.setShooterName(rs.getString(23));
        display.setCompanyName(rs.getString(24));
       // set file list 
        display.setFileList(selectFileList(display.getDisplayIdNumber()));
        display.setDisplayDatesList(this.selectDisplayDates(display.getDisplayIdNumber()));
      }
   
      return display;
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
public Map selectFeesMapping() throws SQLException, Exception 
  {
    Map map = new HashMap();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_FEES_DISPLAY);
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
  
  
  
  
 public void insertPermitTransaction(DfbsDisplay display,DfbsOwner owner,ShoppingCart cart,int receiptId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
   try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,DfbsDisplaySQL.SELECT_NEXT_TRANSACTION_ID);
     
      pstmt = conn.prepareStatement(DfbsDisplaySQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,display.getAmount());
      if (cart.getPaymentType().equals("CC"))
      {
      pstmt.setString(3,display.getDisplayIdNumber() +"-display "+ "CC CONFIRMATION " +Integer.toString(receiptId));
      }
      else
      {
        pstmt.setString(3,display.getDisplayIdNumber() +"-display"+ " Check number " +Integer.toString(receiptId));
      
      }
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,display.getDisplayIdNumber());
      pstmt.setString(6,owner.getOwnerLastName());
      pstmt.setString(7,cart.getPaymentType());
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
  
  public void computeFees(ShoppingCart cart,String renewFlag) throws Exception 
 {
    Map feeMap = this.selectFeesMapping();
    
    List owners = new ArrayList();
    Map mapOwner = cart.getOwnerMap();
    Set ownerkeys = mapOwner.keySet();
    Iterator i = ownerkeys.iterator();
    double cartTotal = 0;
    int cartPermits = 0;
     while(i.hasNext())
    { 
     String key = (String) i.next();
     DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
     
     double ownerAmount = 0;
          List displays = new ArrayList();
          Map mapPermit = owner.getPermitsMap();
          Set displaykeys = mapPermit.keySet();
          
          Iterator j = displaykeys.iterator();
          double displayTotal = 0;
          
          while(j.hasNext()) 
          { 
             String keys = (String) j.next();
            DfbsDisplay display = (DfbsDisplay) mapPermit.get(keys); 
             double total = 0;
              
                   
             {
               Double amount = (Double) feeMap.get(new Integer("1"));
               total = total + amount.doubleValue();
               display.setAmount(amount.doubleValue());
               ownerAmount= ownerAmount + amount.doubleValue();
               cartTotal= cartTotal + amount.doubleValue();
               cartPermits= cartPermits + 1;
             }
           
           
             displays.add(display);
           }
     owner.setPermits(displays);
     owner.setAmount(ownerAmount);
     owners.add(owner);
     cart.addCount(owner);
     }    
   
   cart.setOwnerList(owners);
   cart.setAmount(cartTotal);
   cart.setTotalPermits(cartPermits);
 }
   
   
    public String insertPermit(DfbsDisplay display,int ownerId,String onlineStatus) throws SQLException, Exception 
  {
  
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
       
    try 
    {
      
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsDisplaySQL.INSERT_DISPLAY);
      int id = this.getId(conn,DfbsDisplaySQL.SELECT_NEXT_DISPLAY_ID);
      StringBuffer sb = new StringBuffer("");
      sb.append("FW"  + Integer.toString(id));
       display.setDisplayIdNumber(sb.toString());
           
      pstmt.setString(4,display.getDisplayAddress1());
      pstmt.setString(5,display.getDisplayAddress2());
      pstmt.setString(6,display.getDisplayCity());
      pstmt.setString(19,display.getDisplayComments());
      pstmt.setString(20,display.getDisplayCounty());
      pstmt.setString(2,display.getDisplayDateFromString());
      pstmt.setString(14,display.getDisplayDateToString());
      pstmt.setString(17,display.getDisplayEmail());
      pstmt.setString(16,display.getDisplayFax());
      pstmt.setString(12,display.getDisplayFd());
      pstmt.setString(1,display.getDisplayIdNumber());
      pstmt.setString(10,display.getDisplayIssueDateString());
      pstmt.setString(11,"1");
      pstmt.setString(18,"NEW");
      pstmt.setString(15,display.getDisplayPhone());
      pstmt.setString(7,display.getDisplayState());
      pstmt.setString(3,display.getDisplayTime());
      pstmt.setString(8,display.getDisplayZip());
      pstmt.setString(9,display.getDisplayZip2());
      pstmt.setString(21,display.getDisplayFdEmail());
      pstmt.setString(22,display.getShooterName());
      pstmt.setString(23,display.getCompanyName());
      pstmt.setInt(13,ownerId); 
            pstmt.execute();
      pstmt1 = conn.prepareStatement(DfbsDisplaySQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,display.getDisplayIdNumber());
      pstmt1.setString(2,display.getDisplayPermitKey());
      pstmt1.setString(3,"FireDisplay");
       pstmt1.execute();
   
      conn.commit();
      
      return display.getDisplayIdNumber();  
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
  
   public void updatePermit(DfbsDisplay display) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsDisplaySQL.UPDATE_DISPLAY);
      pstmt.clearParameters();
      
      pstmt.setString(4,display.getDisplayAddress1());
      pstmt.setString(5,display.getDisplayAddress2());
      pstmt.setString(6,display.getDisplayCity());
      pstmt.setString(19,display.getDisplayComments());
      pstmt.setString(1,display.getDisplayCounty());
      pstmt.setString(2,display.getDisplayDateFromString());
      pstmt.setString(14,display.getDisplayDateToString());
      pstmt.setString(17,display.getDisplayEmail());
      pstmt.setString(16,display.getDisplayFax());
      pstmt.setString(12,display.getDisplayFd());
      pstmt.setString(11,"1");
      pstmt.setString(23,display.getDisplayIdNumber());
      pstmt.setString(21,display.getShooterName());
      pstmt.setString(22,display.getCompanyName());
      pstmt.setString(20,display.getDisplayFdEmail());
      pstmt.setString(10,display.getDisplayIssueDateString());
      pstmt.setString(18,display.getDisplayOnlineStatus());
      pstmt.setString(15,display.getDisplayPhone());
      pstmt.setString(7,display.getDisplayState());
      pstmt.setString(3,display.getDisplayTime());
      pstmt.setString(8,display.getDisplayZip());
      pstmt.setString(9,display.getDisplayZip2());
      pstmt.setInt(13,display.getDisplayOwnerId()); 
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

   public void  uploadFile(FormFile oneFile,String displayIdNumber) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,SecuritySQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(DfbsDisplaySQL.UPLOAD_DOCUMENT);
       pstmt.setBytes(4,byteArray);
       pstmt.setInt(1,id);
       pstmt.setString(2,oneFile.getFileName());
       pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
       pstmt.setString(5,"Web");
       pstmt.setString(6,displayIdNumber);
       pstmt.setString(7,"FireDisplay");
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

    public List selectFileList(String displayIdNumber) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_DOCUMENT_NAMES_ALL);
      pstmt.clearParameters();
      if(displayIdNumber != null) {
        pstmt.setString(1,displayIdNumber);
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
  
 public int downLoad(OutputStream os,int fileId)  throws SQLException, Exception 
 {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = DfbsDisplaySQL.SELECT_DOCUMENT; 
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
   public String selectContactEmail(int ownerId) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String contactEmail=null;
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_CONTACT_EMAIL);
      pstmt.clearParameters();
      if(ownerId != 0) {
        pstmt.setInt(1,ownerId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {  contactEmail=rs.getString(1);
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
    public List selectPermitFees(String displayNumber) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int x =0;
    Connection conn = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_ACCT_DUES);
      pstmt.clearParameters();
      pstmt.setString(1,displayNumber);
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
   public int CountFileList(String IdNumber) throws SQLException, Exception 
  {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int fileCount =0;
    try 
    { 
   
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_DOCUMENT_COUNT);
       pstmt.clearParameters();
     
      if(IdNumber != null) {
        pstmt.setString(1,IdNumber);
      }
      else
      {
        pstmt.setString(1,"xxx"); 
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
   public DfbsDisplay selectPermitUpdate(String idNumber) throws SQLException, Exception 
  {
  
    DfbsDisplay display = new DfbsDisplay();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
     conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_PERMIT_FOR_UPDATE);
      pstmt.clearParameters();
      pstmt.setString(1,idNumber);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      { 
        display.setDisplayAddress1(rs.getString(4));
        display.setDisplayAddress2(rs.getString(5));
        display.setDisplayCity(rs.getString(6));
        display.setDisplayComments(rs.getString(20));
        display.setDisplayCounty(rs.getString(21));
        display.setDisplayDateFrom(rs.getDate(2));
        display.setDisplayDateTo(rs.getDate(14));
        display.setDisplayEmail(rs.getString(17));
        display.setDisplayFax(rs.getString(16));
        display.setDisplayFd(rs.getString(12));
        display.setDisplayIdNumber(rs.getString(1));
        display.setDisplayIssueDate(rs.getDate(10));
        display.setDisplayOnlineStatus(rs.getString(19));
        display.setDisplayPhone(rs.getString(15));
        display.setDisplayState(rs.getString(7));
        display.setDisplayTime(rs.getString(3));
        display.setDisplayZip(rs.getString(8));
        display.setDisplayZip2(rs.getString(9));
        display.setDisplayOwnerId(rs.getInt(13));
        display.setDisplayFdEmail(rs.getString(22));
        display.setShooterName(rs.getString(23));
        display.setCompanyName(rs.getString(24));
       // set file list 
        display.setFileList(selectFileList(display.getDisplayIdNumber()));
       display.setDisplayDatesList(this.selectDisplayDates(display.getDisplayIdNumber()));
      }
   
      return display;
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
    public void insertPermitDate(DisplayDates displayDate) throws SQLException, Exception 
  {
  
    Connection conn = null;
    PreparedStatement pstmt = null;
        
    try 
    {
      
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(DfbsDisplaySQL.INSERT_DISPLAY_DATE);
           
      pstmt.setString(1,displayDate.getDisplayId());
      pstmt.setString(2,displayDate.getDisplayDateString());
      pstmt.setString(3,displayDate.getDisplayTime());
     
            pstmt.execute();
      
   
      conn.commit();
      
      
    }
    finally 
    {
     try {
        conn.close();
       pstmt.close();
      } catch (Exception e) {}
    }
  }
    public void updateDisplayDate(DisplayDates displayDate) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsDisplaySQL.UPDATE_DISPLAY_DATE);
      pstmt.clearParameters();
      
      pstmt.setString(1,displayDate.getDisplayDateString());
      pstmt.setString(2,displayDate.getDisplayTime());
      pstmt.setInt(3,displayDate.getDisplayDateId());
     
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
    public void deleteDisplayDate(int displayDateId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(DfbsDisplaySQL.DELETE_DISPLAY_DATE);
      pstmt.clearParameters();
       pstmt.setInt(1,displayDateId);
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
    public List selectDisplayDates(String displayIdNumber) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_DISPLAY_DATES);
      pstmt.clearParameters();
      if(displayIdNumber != null) {
        pstmt.setString(1,displayIdNumber);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
        DisplayDates ddates = new DisplayDates();
        ddates.setDisplayDate(rs.getDate(1));
        ddates.setDisplayTime(rs.getString(2));
        ddates.setDisplayDateId(rs.getInt(3));
        ddates.setApplicationKey(Integer.toString(rs.getInt(3)));

        list.add(ddates);
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
    public DisplayDates selectDisplayDates(int displayId) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      pstmt = conn.prepareStatement(DfbsDisplaySQL.SELECT_DISPLAY_DATE);
      DisplayDates ddates = new DisplayDates();
      pstmt.clearParameters();
      if(displayId != 0) {
        pstmt.setInt(1,displayId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      { 
        
        ddates.setDisplayDate(rs.getDate(1));
        ddates.setDisplayTime(rs.getString(2));
        ddates.setDisplayDateId(rs.getInt(3));
        
      }
      
      return ddates;
     
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
