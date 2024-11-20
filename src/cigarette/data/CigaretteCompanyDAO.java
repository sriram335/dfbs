package cigarette.data;

import cigarette.to.*;
import cigarette.data.*;
import main.to.*;
import main.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;


public class CigaretteCompanyDAO extends HsDAO
{
  public CigaretteCompanyDAO()
  {
  }
  public int insertCompany(CigaretteCompany company) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,CigarettePermitSQL.SELECT_NEXT_COMPANY_ID);
      company.setCompanyId(id);
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_COMPANY);
      pstmt.clearParameters();
      pstmt.setString(3,company.getCompanyAddress1());
      pstmt.setString(4,company.getCompanyAddress2());
      pstmt.setString(5,company.getCompanyCity());
      pstmt.setString(10,company.getCompanyFax());
      pstmt.setString(2,company.getCompanyName());
      pstmt.setString(9,company.getCompanyPhone());
      pstmt.setString(6,company.getCompanyState());
      pstmt.setString(7,company.getCompanyZip());
      pstmt.setString(8,company.getCompanyZip2());
      pstmt.setString(11,company.getCompanyProvince());
      pstmt.setString(12,company.getCompanyCountry());
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
      } catch (Exception e) {}
    }
  }

  
   public CigaretteCompany selectCompany(int companyId) throws SQLException, Exception 
  {
  
    CigaretteCompany company = new CigaretteCompany();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_COMPANY_BY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,companyId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
         company.setCompanyName(rs.getString(1));
          company.setCompanyAddress1(rs.getString(2));
         company.setCompanyAddress2(rs.getString(3));
         company.setCompanyCity(rs.getString(4));
         company.setCompanyState(rs.getString(5));
         company.setCompanyZip(rs.getString(6));
         company.setCompanyZip2(rs.getString(7));
         company.setCompanyPhone(rs.getString(8));
         company.setCompanyFax(rs.getString(9));
         company.setCompanyId(rs.getInt(10));
         company.setCompanyProvince(rs.getString(11));
         company.setCompanyCountry(rs.getString(12));
    }
     return company;
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
   public CigaretteAgent selectAgent(int companyId) throws SQLException, Exception 
  {
  
    CigaretteAgent agent = new CigaretteAgent();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
       
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_AGENT_BY_COMPANY_ID);
      pstmt.clearParameters();
      pstmt.setInt(1,companyId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
         agent.setAgentName(rs.getString(1));
         agent.setAgentAddress1(rs.getString(2));
         agent.setAgentAddress2(rs.getString(3));
         agent.setAgentCity(rs.getString(4));
         agent.setAgentState(rs.getString(5));
         agent.setAgentZip(rs.getString(6));
         agent.setAgentZip2(rs.getString(7));
         agent.setAgentPhone(rs.getString(8));
         agent.setAgentFax(rs.getString(9));
         agent.setAgentEmail(rs.getString(10));
         agent.setAgentId(rs.getInt(12));
    }
     return agent;
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
  public List selectCompanyList(String sql,String param) throws SQLException, Exception 
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
      
        CigaretteCompany company = new CigaretteCompany();
        
         company.setCompanyName(rs.getString(1));
          company.setCompanyAddress1(rs.getString(2));
         company.setCompanyAddress2(rs.getString(3));
         company.setCompanyCity(rs.getString(4));
         company.setCompanyState(rs.getString(5));
         company.setCompanyZip(rs.getString(6));
         company.setCompanyZip2(rs.getString(7));
         company.setCompanyPhone(rs.getString(8));
         company.setCompanyFax(rs.getString(9));
         company.setCompanyId(rs.getInt(10));
         company.setCompanyProvince(rs.getString(11));
         company.setCompanyCountry(rs.getString(12));
         company.setApplications(this.selectApplicationList(CigarettePermitSQL.SELECT_APPLICATION_BY_COMPANY,rs.getInt(10)));
         list.add(company);
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
  
   public List selectApplicationList(String sql,int param) throws SQLException, Exception 
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
      if(param != 0) {
        pstmt.setInt(1,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        CigaretteApplication application = new CigaretteApplication();
        
         application.setAppDate(rs.getDate(2));
      application.setAppType(rs.getString(3));
      application.setContact(rs.getString(4));
      application.setEmail(rs.getString(8));
      application.setFax(rs.getString(7));
      application.setPhone(rs.getString(6));
      application.setTitle(rs.getString(5));
      application.setAppId(rs.getInt(1));
      application.setCompanyId(rs.getInt(9));
      application.setComments(rs.getString(10)); 
       application.setAppIssueDate(rs.getDate(11));
       application.setAppExpDate(rs.getDate(12));
       application.setAppStatus(rs.getString(13));
       application.setFeesPending(this.selectFeesPending(Integer.toString(rs.getInt(1))));
        list.add(application);
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
  
   public List selectBrandList(String sql,int param) throws SQLException, Exception 
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
      if(param != 0) {
        pstmt.setInt(1,param);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        CigaretteBrand brand = new CigaretteBrand();
        
        brand.setCigaretteBrand(rs.getString(2));
        brand.setCigaretteFilter(rs.getString(8));
        brand.setCigaretteFlavor(rs.getString(7));
        brand.setCigaretteMarking(rs.getString(6));
        brand.setCigarettePackage(rs.getString(9));
        brand.setCigaretteStyle(rs.getString(3));
        brand.setApplicationId(rs.getInt(10));
        brand.setCigaretteCircumference(rs.getInt(5));
        brand.setCigaretteId(rs.getInt(1));
        brand.setCigaretteLength(rs.getInt(4));
        brand.setCigaretteFlavorOther(rs.getString(11));
        list.add(brand);
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
    public List selectPermitFees(String appId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int x =0;
    Connection conn = null;
    
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_ACCT_DUES);
      pstmt.clearParameters();
      pstmt.setString(1,appId);
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
   public int selectFeesPending(String appId) throws SQLException, Exception 
  {
  
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    int x=0;
    try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_ACCT_PENDING);
      pstmt.clearParameters();
      pstmt.setString(1,appId);
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
       x=(rs.getInt(1));
        
      }
      
      return x;
    }
    finally 
    {
     try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }
  public void updateCompany(CigaretteCompany company) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_COMPANY);
      pstmt.clearParameters();
      
      pstmt.setString(1,company.getCompanyPhone());
      pstmt.setString(2,company.getCompanyFax());
      pstmt.setInt(3,company.getCompanyId());
      
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
 public void updateAgent(CigaretteAgent agent) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_COMPANY_AGENT);
      pstmt.clearParameters();
      
      pstmt.setString(1,agent.getAgentPhone());
      pstmt.setString(2,agent.getAgentFax());
      pstmt.setString(3,agent.getAgentEmail());
      pstmt.setString(4,agent.getAgentEndDateString());
      pstmt.setInt(5,agent.getAgentId());
      
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
  public int insertAgent(CigaretteAgent agent,CigaretteCompany company) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      int id = this.getId(conn,CigarettePermitSQL.SELECT_NEXT_AGENT_ID);
      agent.setAgentId(id);
      pstmt = conn.prepareStatement(CigarettePermitSQL.INSERT_COMPANY_AGENT);
      pstmt.clearParameters();
      pstmt.setString(3,agent.getAgentAddress1());
      pstmt.setString(4,agent.getAgentAddress2());
      pstmt.setString(5,agent.getAgentCity());
       pstmt.setString(10,agent.getAgentFax());
      pstmt.setString(2,agent.getAgentName());
      pstmt.setString(9,agent.getAgentPhone());
      pstmt.setString(6,agent.getAgentState());
      pstmt.setString(7,agent.getAgentZip());
      pstmt.setString(8,agent.getAgentZip2());
      pstmt.setString(11,agent.getAgentEmail());
      pstmt.setInt(12,company.getCompanyId());
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
      } catch (Exception e) {}
    }
  }
 public void updateChangeCompany(CigaretteCompany company) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_CHANGE_COMPANY);
      pstmt.clearParameters();
      
      pstmt.setString(2,company.getCompanyAddress1());
      pstmt.setString(3,company.getCompanyAddress2());
      pstmt.setString(4,company.getCompanyCity());
       pstmt.setString(9,company.getCompanyFax());
      pstmt.setString(1,company.getCompanyName());
      pstmt.setString(8,company.getCompanyPhone());
      pstmt.setString(5,company.getCompanyState());
      pstmt.setString(6,company.getCompanyZip());
      pstmt.setString(7,company.getCompanyZip2());
      pstmt.setString(10,company.getCompanyProvince());
      pstmt.setString(11,company.getCompanyCountry());
      pstmt.setInt(12,company.getCompanyId());
      
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
  public void updateChangeAgent(CigaretteAgent agent) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
     
      pstmt = conn.prepareStatement(CigarettePermitSQL.UPDATE_CHANGE_COMPANY_AGENT);
      pstmt.clearParameters();
      
       pstmt.setString(3,agent.getAgentAddress1());
      pstmt.setString(4,agent.getAgentAddress2());
      pstmt.setString(5,agent.getAgentCity());
       pstmt.setString(10,agent.getAgentFax());
      pstmt.setString(2,agent.getAgentName());
      pstmt.setString(9,agent.getAgentPhone());
      pstmt.setString(6,agent.getAgentState());
      pstmt.setString(7,agent.getAgentZip());
      pstmt.setString(8,agent.getAgentZip2());
      pstmt.setString(11,agent.getAgentEmail());
      pstmt.setString(1,agent.getAgentEndDateString());
      pstmt.setInt(12,agent.getAgentId());
      
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
   public int selectCompanyAppCount(int companyId) throws SQLException, Exception 
  {
  
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    int appCount =0;   
    try 
    {
      
      conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_COMPANY_APP_COUNT);
      pstmt.clearParameters();
      pstmt.setInt(1,companyId);
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        
        
        appCount= rs.getInt(1);
        
    }
     return appCount;
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
   
    public List selectBrandsExpDateList(int companyId,int appId) throws SQLException, Exception 
  {
  
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    CallableStatement proc = null;
    try 
    {
     conn = getConnection();
      pstmt = conn.prepareStatement(CigarettePermitSQL.SELECT_APPLICATION_BRANDS);
      pstmt.clearParameters();
      if(appId != 0) {
        pstmt.setInt(1,appId);
      }
      rs = pstmt.executeQuery();
      while(rs.next()) 
      {
      
        CigaretteBrand brand = new CigaretteBrand();
          String brandName =rs.getString(1);
             try 
         {  
           
            proc = conn.prepareCall("{ call find_brand_exp_date(?,?,?,?) }");
            proc.setInt(1, companyId);
            proc.setInt(2, appId);
            proc.setString(3, brandName);
            proc.registerOutParameter(4,java.sql.Types.VARCHAR);
            proc.execute();
            brand.setCigaretteBrand(proc.getString(4));
            
         } catch (Exception ex) 
         {
           conn.rollback();
           throw new Exception(ex.getMessage());
         }
       
        list.add(brand);
      }
      
      return list;
    }
    finally 
    {
     try {proc.close();
        conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  } 
    //last
}
