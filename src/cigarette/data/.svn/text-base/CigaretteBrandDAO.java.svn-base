package cigarette.data;
import cigarette.to.*;
import cigarette.data.*;

import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;
import org.apache.struts.util.LabelValueBean;

import util.logging.DHSLogLevel;
import util.logging.Log;


public class CigaretteBrandDAO  extends HsDAO
{  
    private static final String CLASS_NAME="CigaretteBrandDAO";
  public CigaretteBrandDAO()
  {
  }
    public CigaretteBrand selectBrand(String brandId) throws SQLException, Exception 
  {
  
    CigaretteBrand brand = new CigaretteBrand();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_BRAND_BY_ID);
      pstmt.clearParameters();
      pstmt.setString(1,brandId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
       
        brand.setCigaretteBrand(rs.getString(2));
        brand.setCigaretteFilter(rs.getString(8));
        brand.setCigaretteFlavor(rs.getString(7));
        brand.setCigaretteMarking(rs.getString(6));
        brand.setCigarettePackage(rs.getString(9));
        brand.setCigaretteStyle(rs.getString(3));
        brand.setApplicationId(rs.getInt(10));
        brand.setCigaretteCircumference(rs.getDouble(5));
        brand.setCigaretteId(rs.getInt(1));
        brand.setCigaretteLength(rs.getDouble(4));
        brand.setCigaretteFlavorOther(rs.getString(11));
    }
     return brand;
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
    public List SelectBrandStyleList3Year(String companyId) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    List brandStyles = new ArrayList();   
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_BRANDS_FOR_3YEAR_RENEWAL);
      pstmt.clearParameters();
      pstmt.setString(1,companyId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {CigaretteBrand brand = new CigaretteBrand();
        brand.setCigaretteBrand(rs.getString(2));
        brand.setCigaretteFilter(rs.getString(8));
        brand.setCigaretteFlavor(rs.getString(7));
        brand.setCigaretteMarking(rs.getString(6));
        brand.setCigarettePackage(rs.getString(9));
        brand.setCigaretteStyle(rs.getString(3));
        brand.setApplicationId(rs.getInt(10));
        brand.setCigaretteCircumference(rs.getDouble(5));
        brand.setCigaretteId(rs.getInt(1));
        brand.setCigaretteLength(rs.getDouble(4));
        brand.setCigaretteFlavorOther(rs.getString(11));
        brandStyles.add(brand);
    }
     return brandStyles;
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
    public List SelectBrandStyleList3YearRenewal() throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    List brandStyles = new ArrayList();   
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_BRANDS_FOR_3YEAR_RENEWAL_UPDATE);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {CigaretteBrand brand = new CigaretteBrand();
        brand.setCigaretteBrand(rs.getString(2));
        brand.setCigaretteFilter(rs.getString(8));
        brand.setCigaretteFlavor(rs.getString(7));
        brand.setCigaretteMarking(rs.getString(6));
        brand.setCigarettePackage(rs.getString(9));
        brand.setCigaretteStyle(rs.getString(3));
        brand.setApplicationId(rs.getInt(10));
        brand.setCigaretteCircumference(rs.getDouble(5));
        brand.setCigaretteId(rs.getInt(1));
        brand.setCigaretteLength(rs.getDouble(4));
        brand.setCigaretteFlavorOther(rs.getString(11));
        brandStyles.add(brand);
    }
     return brandStyles;
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
  
   public int insertBrand(CigaretteBrand brand,int applicationId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
     try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,CigarettePermitSQL.SELECT_NEXT_BRAND_ID);
      brand.setCigaretteId(id);
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_BRAND);
      
      pstmt.setString(2,brand.getCigaretteBrand());
      pstmt.setString(8,brand.getCigaretteFilter());
      pstmt.setString(7,brand.getCigaretteFlavor());
      pstmt.setString(6,brand.getCigaretteMarking());
      pstmt.setString(9,brand.getCigarettePackage());
      pstmt.setString(3,brand.getCigaretteStyle());
      pstmt.setInt(10,applicationId);
      pstmt.setDouble(5,brand.getCigaretteCircumference());
      pstmt.setInt(1,brand.getCigaretteId());
      pstmt.setDouble(4,brand.getCigaretteLength());
      pstmt.setString(11,brand.getCigaretteFlavorOther());
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
      } catch (Exception e) {}
    }
  }
    public void insertBrandRenewal(CigaretteBrand brand) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
     try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_BRAND_RENEWAL);
      pstmt.setString(2,brand.getCigaretteBrand());
      pstmt.setString(8,brand.getCigaretteFilter());
      pstmt.setString(7,brand.getCigaretteFlavor());
      pstmt.setString(6,brand.getCigaretteMarking());
      pstmt.setString(9,brand.getCigarettePackage());
      pstmt.setString(3,brand.getCigaretteStyle());
      pstmt.setInt(10,0);
      pstmt.setDouble(5,brand.getCigaretteCircumference());
      pstmt.setInt(1,brand.getCigaretteId());
      pstmt.setDouble(4,brand.getCigaretteLength());
      pstmt.setString(11,brand.getCigaretteFlavorOther());
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
 public void computeFees(CigaretteCompany company) throws Exception 
 {
     String METHOD_NAME="computeFees";  
    Map feeMap = this.selectFeesMapping();
    
    List applications = new ArrayList();
    Map mapApplication = company.getApplicationsMap();
    Set appkeys = mapApplication.keySet();
    
    Iterator i = appkeys.iterator();
    double permitTotal = 0;
    int totalBrands = 0;
    while(i.hasNext())
    { 
     String key = (String) i.next();
     CigaretteApplication application = (CigaretteApplication) mapApplication.get(key); 
     
     double totalAmount = 0;
          List brands = new ArrayList();
          application.setMapUniqueBrand(null);
          Map mapBrand = application.getBrandsMap();
          int countBrands =0;
          Set applicationkeys = mapBrand.keySet();
          Iterator j = applicationkeys.iterator();
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "j.:"+j);  
          double applicationTotal = 0;
           Map mapUniqueBrand = application.getMapUniqueBrand();
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "mapUniqueBrand...1:"+mapUniqueBrand);  
          while(j.hasNext()) 
          { 
            String keys = (String) j.next();
            CigaretteBrand brand = (CigaretteBrand) mapBrand.get(keys); 
              Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "brand...1:"+brand); 
              Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "mapUniqueBrand...2:"+mapUniqueBrand); 
             double total = 0;
             if (mapUniqueBrand == null)
             { 
               if (company.getCompanyId() >0)
               {
                 int countBrand=this.selectBrandCount(company,brand.getCigaretteBrand());
                   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrand...1:"+countBrands); 
                 if (countBrand >0)
                 {
                   mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                 }
                  else
                 {
                   mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                   
                 }
                   countBrands=countBrands +1;
                   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrand...2:"+countBrands); 
               }
               else
               {
                 mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                 countBrands=countBrands +1; 
                   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrand...3:"+countBrands); 
                 
               }
              
             }
             else
             {
                 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrand...3:"+countBrands); 
               if(mapUniqueBrand.get(brand.getCigaretteBrand())== null)
               {
                if (company.getCompanyId() >0)
                 {
                   int countBrand=this.selectBrandCount(company,brand.getCigaretteBrand());
                   if (countBrand > 0)
                   {
                     mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                   }
                       else
                   {
                     mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                    // countBrands=countBrands +1;
                   }
                     Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrand.1..else:"+countBrands); 
                 }
                    else
                 {
                   mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                  // countBrands=countBrands +1;
                 }
                   countBrands=countBrands +1;
                   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrand...2..else:"+countBrands); 
               }
             }
             brands.add(brand); 
          }    
        Double amount = (Double) feeMap.get(new Integer("1"));
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "amount...2..else:"+amount); 
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "applicationTotal...1..else:"+applicationTotal); 
               applicationTotal= applicationTotal + amount.doubleValue()*countBrands;
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "applicationTotal...2..else:"+applicationTotal); 
               permitTotal= permitTotal + applicationTotal;
               totalBrands =countBrands;
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "totalBrands...2..else:"+totalBrands); 
     application.setBrands(brands);
     application.setAmount(applicationTotal);
     applications.add(application);
     }    
    
   company.setApplications(applications);
   company.setAmount(permitTotal);
   company.setTotalBrands(totalBrands);
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
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_FEES_CIGARETTE);
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
   public void insertPermitTransaction(CigaretteApplication application,CigaretteCompany company,int receiptId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
     try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,CigarettePermitSQL.SELECT_NEXT_TRANSACTION_ID);
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,application.getAmount());
      if (company.getPaymentType().equals("CC"))
      {
      pstmt.setString(3,application.getAppId() +"-cigarette(" + application.getAppDateString() + ")"+ "CC CONFIRMATION " +Integer.toString(receiptId));
      }
      else
      {
        pstmt.setString(3,application.getAppId() +"-cigarette(" + application.getAppDateString() + ")"+ " Check number " +Integer.toString(receiptId));
      
      }
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,Integer.toString(application.getAppId()));
      pstmt.setString(6,company.getCompanyName());
      pstmt.setString(7,company.getPaymentType());
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
  public void updateBrand(CigaretteBrand brand) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_BRAND);
      
      pstmt.setString(2,brand.getCigaretteBrand());
      pstmt.setString(8,brand.getCigaretteFilter());
      pstmt.setString(7,brand.getCigaretteFlavor());
      pstmt.setString(6,brand.getCigaretteMarking());
      pstmt.setString(1,brand.getCigarettePackage());
      pstmt.setString(3,brand.getCigaretteStyle());
      pstmt.setDouble(5,brand.getCigaretteCircumference());
      pstmt.setString(9,brand.getCigaretteFlavorOther());
      pstmt.setInt(10,brand.getCigaretteId());
      pstmt.setDouble(4,brand.getCigaretteLength());
      
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
  
    public int selectBrandCount(CigaretteCompany company,String brandName) throws SQLException, Exception 
  {
  
    CigaretteBrand brand = new CigaretteBrand();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int brandCount=0; 
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_COUNT_BRAND_COMPANY);
      pstmt.clearParameters();
      pstmt.setString(1,brandName);
      pstmt.setInt(2,company.getCompanyId());
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
        brandCount=rs.getInt(1);
       
    }
     return brandCount;
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
   public List selectBrands(String companyName) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    {
   
      conn = getConnection();
      if(companyName.equals(""))
      {
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_BRANDS_ALL);
      }
      else
      {
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_BRANDS);
      pstmt.clearParameters();
      if(companyName != null) {
        pstmt.setString(1,companyName);
      }
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
        list.add(new LabelValueBean(rs.getString(2),rs.getString(1)));
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
  
  public void insertNewBrand(String brandName,String companyId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
     try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_NEW_BRAND);
      pstmt.setString(1,brandName);
      pstmt.setString(2,companyId);
     
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
  public void computeFees3YearRenewal(CigaretteCompany company) throws Exception 
 {
     String METHOD_NAME="computeFees3YearRenewal";  
    Map feeMap = this.selectFeesMapping();
    
    List applications = new ArrayList();
    Map mapApplication = company.getApplicationsMap();
    Set appkeys = mapApplication.keySet();
    
    Iterator i = appkeys.iterator();
    double permitTotal = 0;
    int totalBrands = 0;
    while(i.hasNext())
    { 
     String key = (String) i.next();
     CigaretteApplication application = (CigaretteApplication) mapApplication.get(key); 
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "application...:"+application);   
     double totalAmount = 0;
          List brands = new ArrayList();
          application.setMapUniqueBrand(null);
          Map mapBrand = application.getBrandsMap();
          int countBrands =0;
          Set applicationkeys = mapBrand.keySet();
          Iterator j = applicationkeys.iterator();
          double applicationTotal = 0;
           Map mapUniqueBrand = application.getMapUniqueBrand();
         int countBrand;
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "mapUniqueBrand...:"+mapUniqueBrand.size()); 
          while(j.hasNext()) 
          { 
            String keys = (String) j.next();
            CigaretteBrand brand = (CigaretteBrand) mapBrand.get(keys); 
              Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "brand...:"+brand); 
             double total = 0;
             if (mapUniqueBrand == null)
             { 
                  countBrand=this.selectBrandCount3YearRenewal(company,brand.getCigaretteBrand());
                 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrands...if..:"+countBrands); 
                 if (countBrand >0)
                 {
                   mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                 }
                  else
                 {
                   mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                 
                 }
                 countBrands=countBrands +1;
             }
             else
             {
               if(mapUniqueBrand.get(brand.getCigaretteBrand())== null)
               {
                   countBrand=this.selectBrandCount3YearRenewal(company,brand.getCigaretteBrand());
                   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrands...else..:"+countBrands); 
                   if (countBrand > 0)
                   {
                     mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                   }
                       else
                   {
                     mapUniqueBrand.put(brand.getCigaretteBrand(),brand);
                   
                   }
                   countBrands=countBrands +1;
               }
             }
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "countBrands...last..:"+countBrands); 
             brands.add(brand); 
          }    Double amount = (Double) feeMap.get(new Integer("1"));
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "amount...2..else:"+amount); 
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "applicationTotal...1..else:"+applicationTotal); 
               applicationTotal= applicationTotal + amount.doubleValue()*countBrands;
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "applicationTotal...2..else:"+applicationTotal); 
               permitTotal= permitTotal + applicationTotal;
               totalBrands =countBrands;
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "totalBrands...2..else:"+totalBrands); 
        
     application.setBrands(brands);
     application.setAmount(applicationTotal);
     applications.add(application);
     }    
    
   company.setApplications(applications);
   company.setAmount(permitTotal);
   company.setTotalBrands(totalBrands);
 }
    public int selectBrandCount3YearRenewal(CigaretteCompany company,String brandName) throws SQLException, Exception 
  {
  
    CigaretteBrand brand = new CigaretteBrand();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int brandCount=0; 
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_COUNT_BRAND_COMPANY_3YEAR_RENEWAL);
      pstmt.clearParameters();
      pstmt.setString(1,brandName);
      pstmt.setInt(2,company.getCompanyId());
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
        brandCount=rs.getInt(1);
       
    }
     return brandCount;
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
     public void deleteBrandsRenewal() throws SQLException, Exception 
   {
           Connection conn = null;
           PreparedStatement pstmt = null;
        try 
     {
           conn = getConnection();
           conn.setAutoCommit(true);
           pstmt = conn.prepareStatement(CigarettePermitSQL.DELETE_BRAND_RENEWAL);
           pstmt.clearParameters();
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
//last
}
