package idhsInspections.data;

import idhsInspections.to.*;

import hs.data.HsDAO;
import main.to.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* excel 97-2003 imports */
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.NumberFormulaCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import jxl.read.biff.BiffException;

import jxl.BooleanCell;

import jxl.write.*;


import org.apache.struts.upload.FormFile;

public class idhsInspectionsDAO  extends HsDAO{
    public idhsInspectionsDAO() {
    }
    
    public List selectElevators(String facName, String streetNumber,String city,String county,inspectionSearch search,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      String lsql =InspectionsSQL.SELECT_ELEVATORS_BY_ADDRESS;
      int elevCount=0;
      try 
      {
     
        conn = getConnection();
          if (city != null && city.length()>0) {
              lsql  =lsql+ " AND UPPER(LOCATION_CITY)=UPPER('"+city+"')";
          }
          if (county !=null && county.length()>0) {
              lsql  =lsql+ " AND LOCATION_COUNTY='"+county+"'";  
          }
          if (facName !=null && facName.length()>0) {
              lsql  =lsql+ " AND UPPER(USER_LAST_NAME) LIKE UPPER('%"+facName+"%')";  
          }
          if (streetNumber != null && streetNumber.length()>1) {
              lsql  =lsql+ " AND UPPER(LOCATION_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
          }
          if (searchZip != null && searchZip.length()==5) {
              lsql  =lsql+ " AND LOCATION_ZIP ='"+searchZip+"'";
          }
          
          lsql  =lsql+ " order by state_number";
        pstmt = conn.prepareStatement(lsql);
        pstmt.clearParameters();
         rs = pstmt.executeQuery();
        while(rs.next()) 
        {
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
          result.setFacilityName(rs.getString(7)); 
          elevCount=elevCount+1;
            list.add(result);
        }
        search.setElevCount(elevCount);
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
    public List selectBpv(String facName ,String streetNumber,String city,String county,inspectionSearch search,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
        List list = new ArrayList();
                Connection conn = null;
                ResultSet rs = null;
                PreparedStatement pstmt = null;
                String lsql =InspectionsSQL.SELECT_BPV_BY_ADDRESS;
                int bpvCount=0;
              try 
              {     
                conn = getConnection();
                      if (city !=null && city.length()>0) {
                          lsql  =lsql+ " AND UPPER(LOCATION_CITY)=UPPER('"+city+"')";
                      }
                      if (county !=null && county.length()>0) {
                          lsql  =lsql+ " AND LOCATION_COUNTY='"+county+"'";  
                      }
                      if (facName !=null && facName.length()>0) {
                          lsql  =lsql+ " AND UPPER(USER_NAME) LIKE UPPER('%"+facName+"%')";  
                      }
                      if (streetNumber != null && streetNumber.length()>1) {
                          lsql  =lsql+ " AND UPPER(LOCATION_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
                      }
                      if (searchZip != null && searchZip.length()==5) {
                          lsql  =lsql+ " AND LOCATION_ZIP ='"+searchZip+"'";
                      }
                 lsql  =lsql+ " order by state_number";
          System.out.println("lsql:"+lsql);
                  pstmt = conn.prepareStatement(lsql);
                  pstmt.clearParameters();
                  rs = pstmt.executeQuery();
        while(rs.next()) 
        {
            
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
          result.setFacilityName(rs.getString(7)); 
          bpvCount=bpvCount+1;
            list.add(result); 
        }
        search.setBpvCount(bpvCount);
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
    public List selectAepermits(String facName ,String streetNumber,String city,String county,inspectionSearch search, String idNumber,String searchFor,String searchZip,String searchActive) throws SQLException, Exception 
    {
      
        List list = new ArrayList();
                Connection conn = null;
                ResultSet rs = null;
                PreparedStatement pstmt = null;
                String lsql =InspectionsSQL.SELECT_AEPERMIT_BY_ADDRESS;
                int aeCount=0;
              try 
              {    
                conn = getConnection();
                      if (city != null && city.length()>0) { 
                          lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"') ";
                      }
                      if (county !=null && county.length()>0) {
                          lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";  
                      }
                      if (facName !=null && facName.length()>0) {
                          lsql  =lsql+ " AND UPPER(ACTUAL_LOCATION) LIKE UPPER('%"+facName+"%')";  
                      }
                      if (streetNumber !=null && streetNumber.length()>1) { 
                          lsql  =lsql+ " AND UPPER(FACILITY_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
                      }
                      if (idNumber !=null && idNumber.length()>0) {
                          lsql  =lsql+ " AND UPPER(identification_number)=UPPER('"+idNumber+"')";
                      }
                      else {if (searchActive !=null && searchActive.equals("Active"))
                          {
                          lsql  =lsql+ " AND ACTIVE='A'";
                          }
                      }
                      if (searchFor != null && searchFor.equals("toBeInspected")) {
                          lsql  =lsql+ " AND issue_date is null and application_date >sysdate-365 AND ONLINE_STATUS!='DENIED'";
                      }
                      if (searchFor != null && searchFor.equals("Complied")) {
                          lsql  =lsql+ " AND issue_date is not null and issue_date>sysdate";
                      }
                      if (searchZip != null && searchZip.length()==5) {
                          lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
                      }
                      lsql=lsql+ " order by identification_number ";
                  pstmt = conn.prepareStatement(lsql);
                  pstmt.clearParameters();
                   rs = pstmt.executeQuery();
        while(rs.next()) 
        {
           searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
          result.setFacilityName(rs.getString(8)); 
          aeCount=aeCount+1;
            list.add(result); 
        }
        if (search != null)
        {
        search.setAeCount(aeCount);
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
    public List selectDaycares(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchFor,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
        List list = new ArrayList();
               Connection conn = null;
               ResultSet rs = null;
               PreparedStatement pstmt = null;
               String lsql =InspectionsSQL.SELECT_DAYCARE_BY_ADDRESS;
               int daycareCount=0;
              try 
             {     
               conn = getConnection();
                     if (city !=null && city.length()>0) {
                         lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
                     }
                     if (county != null && county.length()>0) {
                         lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";
                     }
                      if (facName !=null && facName.length()>0) {
                          lsql  =lsql+ " AND UPPER(DAY_CARE_NAME) LIKE UPPER('%"+facName+"%')";  
                      }
                      if (streetNumber !=null && streetNumber.length()>1) {
                          lsql  =lsql+ " AND UPPER(ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
                      }
                      if (idNumber !=null && idNumber.length()>0) {
                          lsql  =lsql+ " AND UPPER(fire_id)=UPPER('"+idNumber+"')";
                      }
                      else {if (searchActive !=null && searchActive.equals("Active"))
                          {
                          lsql  =lsql+ " AND ACTIVE='A'";
                          }
                      }
                      if (searchZip != null && searchZip.length()==5) {
                          lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
                      }
                      if (searchFor != null && searchFor.length()>3) {
                          this.findDaycareDueInspect();
                           lsql  =lsql+ " AND FIRE_ID IN (SELECT A.FIRE_ID FROM DAYCARE_TO_INSPECT A WHERE A.FIRE_ID=FIRE_ID AND "+
                          " (A.last_complied_date is null or A.last_complied_date<sysdate-335)) ";
                      }
                  lsql=lsql+ " order by fire_id ";
                  pstmt = conn.prepareStatement(lsql);
                 pstmt.clearParameters();
                 rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6));
          result.setFacilityName(rs.getString(7)); 
          result.setResultType(rs.getString(8));  
          daycareCount=daycareCount+1;
            list.add(result); 
        }
        search.setDaycareCount(daycareCount);
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
    public List selectSchools(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
                List list = new ArrayList();
                Connection conn = null;
                ResultSet rs = null;
                PreparedStatement pstmt = null;
                String lsql =InspectionsSQL.SELECT_SCHOOL_BY_ADDRESS;
                int schoolCount=0;
              try 
              {    
                conn = getConnection();
                      if (city !=null && city.length()>0) {
                          lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
                      }
                      if (county != null && county.length()>0) {
                          lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";  
                      }
                      if (facName !=null && facName.length()>0) {
                          lsql  =lsql+ " AND UPPER(SCHOOL_NAME) LIKE UPPER('%"+facName+"%')";  
                      }
                      if (streetNumber !=null && streetNumber.length()>1) {
                          lsql  =lsql+ " AND UPPER(ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
                      }
                      if (idNumber !=null && idNumber.length()>0) {
                          lsql  =lsql+ " AND UPPER(fire_id)=UPPER('"+idNumber+"')";
                      }
                      else {if (searchActive !=null && searchActive.equals("Active"))
                          {
                          lsql  =lsql+ " AND STATUS='Active' ";
                          }
                      }
                      if (searchZip != null && searchZip.length()==5) {
                          lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
                      }
                  lsql=lsql+ " order by fire_id ";
                  pstmt = conn.prepareStatement(lsql);
                  pstmt.clearParameters();
                  rs = pstmt.executeQuery();
        while(rs.next()) 
        {
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6));
            result.setFacilityName(rs.getString(7)); 
          schoolCount=schoolCount+1;
            list.add(result); 
        }
        search.setSchoolCount(schoolCount);
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
    public List selectOthers(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchZip,String searchActive) throws SQLException, Exception 
    {
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String lsql =InspectionsSQL.SELECT_OTHER_BY_ADDRESS;
        int otherCount=0;
      try 
      {     
        conn = getConnection();
              if (city !=null && city.length()>0) {
                  lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
              }
              if (county != null && county.length()>0) {
                  lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";  
              }
              if (facName !=null && facName.length()>0) {
                  lsql  =lsql+ " AND UPPER(STRUCTURE_NAME) LIKE UPPER('%"+facName+"%')";  
              }
              if (streetNumber !=null && streetNumber.length()>1) {
                  lsql  =lsql+ " AND UPPER(ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
              }
              if (idNumber !=null && idNumber.length()>0) {
                                       lsql  =lsql+ " AND UPPER(identification_number)=UPPER('"+idNumber+"')";
                }
              else {if (searchActive !=null && searchActive.equals("Active"))
                          {
                  lsql  =lsql+ " AND ACTIVE='A' ";
                          }
              }
          
              if (searchZip != null && searchZip.length()==5) {
                  lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
              }
          lsql=lsql+ " order by identification_number ";
          pstmt = conn.prepareStatement(lsql);
          pstmt.clearParameters();
          rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
            result.setFacilityName(rs.getString(7)); 
          otherCount=otherCount+1;
            list.add(result);
        }
        search.setOtherCount(otherCount);
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
    public List selectHospitals(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
        List list = new ArrayList();
               Connection conn = null;
               ResultSet rs = null;
               PreparedStatement pstmt = null;
               String lsql =InspectionsSQL.SELECT_HOSPITAL_BY_ADDRESS;
               int hospitalCount=0;
             try 
             {     
               conn = getConnection();
                     if (city !=null && city.length()>0) {
                         lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
                     }
                     if (county != null && county.length()>0) {
                         lsql  =lsql+ " AND COUNTY_CODE='"+county+"'"; 
                     }
                      if (facName !=null && facName.length()>0) {
                          lsql  =lsql+ " AND UPPER(HOSPITAL_NAME) LIKE UPPER('%"+facName+"%')";  
                      }
                      if (streetNumber !=null && streetNumber.length()>1) {
                          lsql  =lsql+ " AND UPPER(ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
                      }
                  if (idNumber !=null && idNumber.length()>0) {
                                           lsql  =lsql+ " AND UPPER(fire_id)=UPPER('"+idNumber+"')";
                                       }                                       
                      else {if (searchActive !=null && searchActive.equals("Active"))
                          {
                          lsql  =lsql+ " AND STATUS='Active'  ";
                          }
                      }
          
                  if (searchZip != null && searchZip.length()==5) {
                      lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
                  }
          lsql=lsql+ " order by fire_id ";
                 pstmt = conn.prepareStatement(lsql);
                 pstmt.clearParameters();
                 rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6));
          result.setFacilityName(rs.getString(7)); 
          result.setResultType(rs.getString(8));
          hospitalCount=hospitalCount+1;
            list.add(result);
        }
        search.setHospitalCount(hospitalCount);
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
    public List selectPlans(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
        String lsql =InspectionsSQL.SELECT_PLAN_BY_ADDRESS;
        int planCount=0;
      try 
      {
     
          conn = getConnection();
            if (city !=null && city.length()>0) {
                lsql  =lsql+ " AND UPPER(PROJECT_CITY)=UPPER('"+city+"')";
            }
            if (county !=null &&county.length()>0) {
                lsql  =lsql+ " AND CNTYS_COUNTY_CODE='"+county+"'";  
            }
          if (facName !=null && facName.length()>0) {
              lsql  =lsql+ " AND UPPER(PROJECT_NAME) LIKE UPPER('%"+facName+"%')";  
          }
          if (streetNumber !=null && streetNumber.length()>1) {
              lsql  =lsql+ " AND UPPER(PROJECT_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
          }
          if (idNumber !=null && idNumber.length()>3) {
              lsql  =lsql+ " AND to_char(SBC_PROJECT_NUMBER) ="+idNumber;
          }
           else {if (searchActive !=null && searchActive.equals("Active"))
                          {
                          lsql  =lsql+ " AND TO_CHAR(SBC_PROJECT_NUMBER) NOT IN ( SELECT FACILITY_ID FROM IDHS_FIRE_CODE_INSPECTIONS " +
                              " WHERE TO_CHAR(SBC_PROJECT_NUMBER)=FACILITY_ID AND INSP_TYPE='Final Inspection')  AND NURSING_HOME_INTER IS NULL" ;
                          }
                      }
          if (searchZip != null && searchZip.length()==5) {
              lsql  =lsql+ " AND PROJECT_ZIP ='"+searchZip+"'";
          }
          lsql  =lsql+ "  and SBC_PROJECT_NUMBER not like '9%' order by 1 desc";
          pstmt = conn.prepareStatement(lsql);
          pstmt.clearParameters();
           rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
          result.setFacilityName(rs.getString(7)); 
          planCount=planCount+1;
            list.add(result);
            List planList =this.selectFileList(rs.getString(1),"PLAN_REVIEW");
            result.setResultFileList(planList);
        }
         search.setPlanCount(planCount);
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
    public List selectUSTs(String facName, String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      String lsql =InspectionsSQL.SELECT_UST_BY_ADDRESS;
      int ustCount=0;
      try 
      {
     
        conn = getConnection();
          if (city !=null && city.length()>0) {
              lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
          }
          if (county != null && county.length()>0) {
              lsql  =lsql+ " AND COUNTY='"+county+"'";  
          }
          if (facName !=null && facName.length()>0) {
              lsql  =lsql+ " AND UPPER(UST_NAME) LIKE UPPER('%"+facName+"%')";  
          }
          if (streetNumber !=null && streetNumber.length()>1) {
              lsql  =lsql+ " AND UPPER(UST_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
          }
          if (idNumber !=null && idNumber.length()>0) {
              lsql  =lsql+ " AND UPPER(identification_number)=UPPER('"+idNumber+"')";
          }
          else {if (searchActive !=null && searchActive.equals("Active"))
                          {
              lsql  =lsql+ " AND ACTIVE='A'   ";
                          }
          }
         
          if (searchZip != null && searchZip.length()==5) {
              lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
          }
          lsql=lsql+ " order by identification_number ";
        pstmt = conn.prepareStatement(lsql);
        pstmt.clearParameters();
         rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
            result.setFacilityName(rs.getString(7)); 
          ustCount=ustCount+1;
            list.add(result);
        }
        search.setUstCount(ustCount);
        
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
    public List selectFireworksRetail(String facName, String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchFor,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      String lsql =InspectionsSQL.SELECT_FIREWORKS_RETAIL_BY_ADDRESS;
      int FireworksRetailCount=0;
      try 
      {
     
        conn = getConnection();
          if (city !=null && city.length()>0) {
              lsql  =lsql+ " AND UPPER(SALES_CITY)=UPPER('"+city+"')";
          }
          if (county != null && county.length()>0) {
              lsql  =lsql+ " AND SALES_COUNTY='"+county+"'"; 
          }
          if (facName !=null && facName.length()>0) {
              lsql  =lsql+ " AND UPPER(LAST_NAME) LIKE UPPER('%"+facName+"%')";  
          }
          if (streetNumber !=null && streetNumber.length()>1) {
              lsql  =lsql+ " AND UPPER(SALES_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
          }
          if (idNumber !=null && idNumber.length()>0) {
              lsql  =lsql+ " AND UPPER(identification_number)=UPPER('"+idNumber+"')";
          }
          else {if (searchActive !=null && searchActive.equals("Active"))
                          {
              lsql  =lsql+ " AND ACTIVE='1'   ";
                          }
          }
          if (searchFor != null && searchFor.equals("toBeInspected")) {
              lsql  =lsql+ " AND issue_date is null and application_date>sysdate-365";
          }
          if (searchFor != null && searchFor.equals("Complied")) {
              lsql  =lsql+ " AND issue_date is not null and issue_date>sysdate ";
          }
          if (searchZip != null && searchZip.length()==5) {
              lsql  =lsql+ " AND SALES_ZIP ='"+searchZip+"'";
          }
          lsql=lsql+ " order by identification_number ";
        pstmt = conn.prepareStatement(lsql);
        pstmt.clearParameters();
        rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
            result.setFacilityName(rs.getString(7)); 
          FireworksRetailCount=FireworksRetailCount+1;
            list.add(result);
        }
        search.setFireworksRetailCount(FireworksRetailCount);
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
    public List selectFireworksWholesale(String facName, String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchFor,String searchZip,String searchActive) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      String lsql =InspectionsSQL.SELECT_FIREWORKS_WHOLESALE_BY_ADDRESS;
      int FireworksWholesaleCount=0;
      try 
      {
     
        conn = getConnection();
          if (city !=null && city.length()>0) {
              lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
          }
          if (county != null && county.length()>0) {
              lsql  =lsql+ " AND COUNTY='"+county+"'";  
          }
          if (facName !=null &&  facName !=null && facName.length()>0) {
              lsql  =lsql+ " AND UPPER(LAST_NAME) LIKE UPPER('%"+facName+"%')";  
          }
          if (streetNumber !=null && streetNumber.length()>1) {
              lsql  =lsql+ " AND UPPER(SALES_ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
          }
          if (idNumber !=null && idNumber.length()>0) {
              lsql  =lsql+ " AND UPPER(identification_number)=UPPER('"+idNumber+"')";
          }
          else {if (searchActive !=null && searchActive.equals("Active"))
                          {
              lsql  =lsql+ " AND ACTIVE='1'   ";
                          }
          }
          if (searchFor != null && searchFor.equals("toBeInspected")) {
              lsql  =lsql+ " AND issue_date is null  and application_date>sysdate-365 ";
          }
          if (searchFor != null && searchFor.equals("Complied")) {
              lsql  =lsql+ " AND issue_date is not null and issue_date>sysdate ";
          }
          if (searchZip != null && searchZip.length()==5) {
              lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
          }
          lsql=lsql+ " order by identification_number ";
        pstmt = conn.prepareStatement(lsql);
        pstmt.clearParameters();
        rs = pstmt.executeQuery();
        while(rs.next()) 
        {
        
          searchResults result = new searchResults();
          result.setResultId(rs.getString(1));
          result.setResultAddress1(rs.getString(2)); 
          result.setResultAddress2(rs.getString(3));  
          result.setResultCity(rs.getString(4)); 
          result.setResultZip(rs.getString(5)); 
          result.setResultCounty(rs.getString(6)); 
          result.setFacilityName(rs.getString(7)); 
          FireworksWholesaleCount=FireworksWholesaleCount+1;
            list.add(result);
        }
        search.setFireworksWholesaleCount(FireworksWholesaleCount);
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
      public List selectNewInspections(String facName, String streetNumber,String city,String county,inspectionSearch search,String searchZip) throws SQLException, Exception 
      {
      
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String lsql =InspectionsSQL.SELECT_IDHS_INSPECTIONS_BY_ADDRESS;
        int newInspCount=0;
        try 
        {
           
          conn = getConnection();
            if (city !=null && city.length()>0) {
                lsql  =lsql+ " AND UPPER(FACILITY_CITY)=UPPER('"+city+"')";
            }
            if (county !=null && county.length()>0) {
                lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";  
            }
            if (facName !=null && facName.length()>0) {
                lsql  =lsql+ " AND UPPER(FACILITY_NAME) LIKE UPPER('%"+facName+"%')";  
            }
            if (streetNumber !=null && streetNumber.length()>1) {
                lsql  =lsql+ " AND UPPER(to_char(FACILITY_STREET_NUMBER)||'%'||FACILITY_STREET_NAME) LIKE UPPER('"+streetNumber+"%')";
            }
            if (searchZip != null && searchZip.length()==5) {
                lsql  =lsql+ " AND FACILITY_ZIP ='"+searchZip+"'";
            }
            lsql=lsql+ " order by inspection_id ";
          pstmt = conn.prepareStatement(lsql);
          pstmt.clearParameters();
           rs = pstmt.executeQuery();
          while(rs.next()) 
          {
          
            searchResults result = new searchResults();
             result.setResultId(rs.getString(1));
            result.setResultAddress1(rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(6)+" "+rs.getString(7)); 
            result.setResultAddress2(rs.getString(8));  
            result.setResultCity(rs.getString(9)); 
            result.setResultZip(rs.getString(12)); 
            result.setResultCounty(rs.getString(10)); 
              result.setFacilityName(rs.getString(3)); 
            newInspCount=newInspCount+1;
              list.add(result);
          }
          search.setComplaintCount(newInspCount);
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
      public List selectNewInspectionsIndMobile(String facName, String streetNumber,String city,String county,inspectionSearch search,String searchZip,String sealNumber) throws SQLException, Exception 
      {
      
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String lsql =InspectionsSQL.SELECT_IDHS_INSPECTIONS_INDUSTRIAL_MOBILE;
        int newInspCount=0;
        try 
        {
          conn = getConnection();
            if (city !=null && city.length()>0) {
                lsql  =lsql+ " AND UPPER(FACILITY_CITY)=UPPER('"+city+"')";
            }
            if (county !=null && county.length()>0) {
                lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";  
            }
            if (facName !=null && facName.length()>0) {
                lsql  =lsql+ " AND UPPER(FACILITY_NAME) LIKE UPPER('%"+facName+"%')";  
            }
            if (streetNumber !=null && streetNumber.length()>1) {
                lsql  =lsql+ " AND UPPER(to_char(FACILITY_STREET_NUMBER)||'%'||FACILITY_STREET_NAME) LIKE UPPER('"+streetNumber+"%')";
            }
            if (searchZip != null && searchZip.length()==5) {
                lsql  =lsql+ " AND FACILITY_ZIP ='"+searchZip+"'";
            }
          if (sealNumber != null && sealNumber.length()>3) {
              lsql  =lsql+ " AND FACILITY_ID =UPPER('"+sealNumber+"')";
          }
            lsql=lsql+ " order by inspection_id ";
          pstmt = conn.prepareStatement(lsql);
          pstmt.clearParameters();
           rs = pstmt.executeQuery();
          while(rs.next()) 
          {
            searchResults result = new searchResults();
             result.setResultId(rs.getString(1));
            result.setResultAddress1(rs.getString(5)+" "+rs.getString(4)+" "+rs.getString(6)+" "+rs.getString(7)); 
            result.setResultAddress2(rs.getString(8));  
            result.setResultCity(rs.getString(9)); 
            result.setResultZip(rs.getString(12)); 
            result.setResultCounty(rs.getString(10)); 
              result.setFacilityName(rs.getString(3)); 
            newInspCount=newInspCount+1;
              list.add(result);
          }
          search.setIndMobileCount(newInspCount);
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
      
      public void  uploadFile(FormFile oneFile,String IdNumber, String IdType) throws SQLException, Exception
        { 
          Connection conn = null;
          PreparedStatement pstmt = null;
          try 
          
          {  
             conn = getConnection();
             int id = this.getId(conn,InspectionsSQL.SELECT_DOCUMENT_ID);
             byte[] byteArray=oneFile.getFileData();
             pstmt =conn.prepareStatement(InspectionsSQL.UPLOAD_DOCUMENT);
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

    public int  uploadFile(FormFile oneFile) throws SQLException, Exception
     { 
       Connection conn = null;
       PreparedStatement pstmt = null;
       try 
       
       {  
          conn = getConnection();
          int id = this.getId(conn,InspectionsSQL.SELECT_DOCUMENT_ID);
          byte[] byteArray=oneFile.getFileData();
          pstmt =conn.prepareStatement(InspectionsSQL.UPLOAD_DOCUMENT);
          pstmt.setBytes(4,byteArray);
          pstmt.setInt(1,id);
          pstmt.setString(2,oneFile.getFileName());
          pstmt.setString(3,oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")));
          pstmt.setString(5,"Web");
          pstmt.setString(6,"");
          pstmt.setString(7,"FireInspection");
          pstmt.execute();
          conn.commit();
         return(id);
    
       }
         finally
         { 
           try {
             
              conn.close();
              pstmt.close();
            } 
           
            catch (Exception e){}
         }
     }
    public int downLoad(OutputStream os,int fileId)  throws SQLException, Exception 
     {  Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
         int len_total = 0; 
       try {
       String sql = InspectionsSQL.SELECT_DOCUMENT;  
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
    /*  public InputStream downLoad(OutputStream os,int fileId,fireInspection  inspection)  throws SQLException, Exception
    {  Connection conn = null;
       ResultSet rs = null;
       PreparedStatement pstmt = null;
        int len_total = 0; 
      try {
      String sql = InspectionsSQL.SELECT_DOCUMENT; 
      conn = getConnection(); 
      pstmt = conn.prepareStatement(sql);
          InputStream is =null;
       if(fileId != 0) {
           pstmt.setInt(1,fileId);
         }
     
      rs = pstmt.executeQuery(); 
       while(rs.next()) 
      {
      java.sql.Blob blob =  rs.getBlob(1);
      is = blob.getBinaryStream();
          excelFileName(is,inspection);
       //  processOneSheet(is);
      is.close(); 
      
      }
       return is;
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
      
     
     } */
      public List selectFileList(String IdNumber,String IdType) throws SQLException, Exception 
      {
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        
        try 
        { 
       
          conn = getConnection();
         
         
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_DOCUMENT_NAMES_ALL);
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
     public void  findDaycareDueInspect() throws SQLException, Exception 
      {
       CallableStatement proc = null;
       Connection conn = null;
       try 
       {  
           conn = getConnection();
         conn.setAutoCommit(false);
         proc = conn.prepareCall("{ call GET_LIST_INSP_DAYCARE()}");
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
      public List selectFacilities(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String searchZip,String searchActive) throws SQLException, Exception 
      {
      
        List list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
          String lsql =InspectionsSQL.SELECT_FACILITIES;
          int planCount=0;
        try 
        {
       
            conn = getConnection();
              
            pstmt = conn.prepareStatement(lsql);
            pstmt.clearParameters();
          if (idNumber !=null && idNumber.length()>3) {
            pstmt.setString(1,idNumber);
          }
             rs = pstmt.executeQuery();
          while(rs.next()) 
          {
            searchResults result = new searchResults();
            result.setResultId((idNumber));
            result.setResultAddress1(rs.getString(2)); 
            result.setResultAddress2(rs.getString(3));  
            result.setResultCity(rs.getString(4)); 
            result.setResultZip(rs.getString(7)); 
            result.setResultCounty(rs.getString(6)); 
            result.setFacilityName(rs.getString(1)); 
            result.setResultType(rs.getString(8));
            result.setSearchSequence(rs.getString(9));
              list.add(result);
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
      public List selectDaycaresFssa(String facName ,String streetNumber,String city,String county,inspectionSearch search,String idNumber,String startDate,String searchZip,String endDate) throws SQLException, Exception 
      {
      
          List list = new ArrayList();
                 Connection conn = null;
                 ResultSet rs = null;
                 PreparedStatement pstmt = null;
                 String lsql =InspectionsSQL.SELECT_DAYCARE_BY_ADDRESS;
                 int daycareCount=0;
                try 
               {     
                 conn = getConnection();
                       if (city !=null && city.length()>0) {
                           lsql  =lsql+ " AND UPPER(CITY)=UPPER('"+city+"')";
                       }
                       if (county != null && county.length()>0) {
                           lsql  =lsql+ " AND COUNTY_CODE='"+county+"'";
                       }
                        if (facName !=null && facName.length()>0) {
                            lsql  =lsql+ " AND UPPER(DAY_CARE_NAME) LIKE UPPER('%"+facName+"%')";  
                        }
                        if (streetNumber !=null && streetNumber.length()>1) {
                            lsql  =lsql+ " AND UPPER(ADDRESS1) LIKE UPPER('"+streetNumber+"%')";
                        }
                        if (idNumber !=null && idNumber.length()>0) {
                            lsql  =lsql+ " AND UPPER(fire_id)=UPPER('"+idNumber+"')";
                        }
                       
                        if (searchZip != null && searchZip.length()==5) {
                            lsql  =lsql+ " AND ZIP ='"+searchZip+"'";
                        }
          System.out.println("1:"+startDate);System.out.println("2:"+endDate);
                        if (startDate != null &&startDate.length()>9) {
                            lsql  =lsql+ " AND FIRE_ID IN (SELECT FACILITY_ID FROM IDHS_FIRE_CODE_INSPECTIONS WHERE  to_char(inspection_date) >=to_date('"+startDate+"','mm/dd/yyyy'))";
                        }
                        if (endDate != null &&endDate.length()>9 ) {
                            lsql  =lsql+ " AND FIRE_ID IN (SELECT FACILITY_ID FROM IDHS_FIRE_CODE_INSPECTIONS WHERE to_char(inspection_date) <=to_date('"+endDate+"','mm/dd/yyyy'))";
                        }
                       System.out.println(lsql);
                    lsql=lsql+ " order by fire_id ";
                    pstmt = conn.prepareStatement(lsql);
                   pstmt.clearParameters();
                   rs = pstmt.executeQuery();
          while(rs.next()) 
          {
          
            searchResults result = new searchResults();
            result.setResultId(rs.getString(1));
            result.setResultAddress1(rs.getString(2)); 
            result.setResultAddress2(rs.getString(3));  
            result.setResultCity(rs.getString(4)); 
            result.setResultZip(rs.getString(5)); 
            result.setResultCounty(rs.getString(6));
            result.setFacilityName(rs.getString(7)); 
            result.setResultType(rs.getString(8));  
            daycareCount=daycareCount+1;
              list.add(result); 
          }
          search.setDaycareCount(daycareCount);
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
    /*  public static void excelFileName(InputStream inStream,fireInspection  inspection) throws BiffException, Exception
      {
        try
        { 
          WorkbookSettings ws = new WorkbookSettings();
          ws.setLocale(new Locale("en", "EN"));
          Workbook workbook = Workbook.getWorkbook(
            inStream,ws);
             String[] wbSheets=workbook.getSheetNames();
           for (int i=0;i<wbSheets.length;i++) {
           }
            List vioList = new ArrayList();
          Sheet s1  = workbook.getSheet("Cover");
            Sheet s2  = workbook.getSheet("Sheet1");
            int j=0;
            while (j<=472) {
                    if( s2.getCell(0,j).getContents() !=null && s2.getCell(0,j).getContents().length()>5)
                    {fireViolation  violation = new fireViolation ();
                    violation.setVioCode(s2.getCell(0,j).getContents());
                    violation.setVioDescription(s2.getCell(1,j).getContents());
                    vioList.add(violation);
                    }
                    j++;
                    inspection.setViolations(vioList);    
                }
         /* Sheet s2  = workbook.getSheet("Violations");
                      readDataSheet1(s1,inspection);
            int j=0;
          int k=3;
          int c=1;
          while (j<=0) {
              if( s2.getCell(c+1,k).getContents().equals("END VIOLATIONS") ) { 
                  j=1;
              }
              else { 
                   fireViolation  violation = new fireViolation ();
                  if( s2.getCell(c,k).getContents() !=null && s2.getCell(c,k).getContents().length()>5)
                  {
                  violation.setVioCode(s2.getCell(c,k).getContents());
                  }
                  if( s2.getCell(c+1,k+1).getContents() !=null && s2.getCell(c+1,k+1).getContents().length()>5 
                      &&s2.getCell(c,k+1).getContents().length()<2 ) { 
                      violation.setVioRemarks(s2.getCell(c+1,k+1).getContents());
                      violation.setVioDescription(s2.getCell(c+1,k).getContents());
                      violation.setVioOrder(Integer.parseInt(s2.getCell(c-1,k).getContents()));
                      k++;
                  }
                  else
                  {
                  violation.setVioDescription(s2.getCell(c+1,k).getContents());
                  }
                 vioList.add(violation);
                 k++; 
                 
              }
          } 
          inspection.setViolations(vioList); 
       
          workbook.close();      
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        catch (BiffException e)
        {
          e.printStackTrace();
        }
        
      }
    
     

    private static void readDataSheet1(Sheet s,fireInspection  inspection)
      {  
          if(s.getCell(0,7).getContents().length()>0)
          {
          LabelCell facId =(LabelCell) s.getCell(0,7);
          inspection.setInspFacId(facId.getString());
          inspection.setFacilityId(facId.getString());
          }
          if(s.getCell(0,9).getContents().length()>0)
          {
          LabelCell facName =(LabelCell) s.getCell(0,9);
          inspection.setFacilityName(facName.getString());
          }
          if(s.getCell(3,11).getContents().length()>0)
          {
          inspection.setFacilityStreetPrefix(s.getCell(3,11).getContents());
          }
          if(s.getCell(13,7).getContents().length()>0)
          {
          inspection.setFacilityCounty(s.getCell(13,7).getContents());
          }
          if(s.getCell(13,9).getContents().length()>0)
          {
          inspection.setFacilityPhone(s.getCell(13,9).getContents());
          }
          if(s.getCell(0,11).getContents().length()>0)
          {
          inspection.setFacilitySteetNumber(s.getCell(0,11).getContents());
          }
          if(s.getCell(4,11).getContents().length()>0)
          {
          LabelCell facStName =(LabelCell) s.getCell(4,11);
          inspection.setFacilityStreetName(facStName.getString());
          }
          if(s.getCell(9,11).getContents().length()>0)
          {
          inspection.setFacilityStreetSuffix(s.getCell(9,11).getContents());
          }
          if(s.getCell(12,11).getContents().length()>0)
          {
          LabelCell facCity =(LabelCell) s.getCell(12,11);
          inspection.setFacilityCity(facCity.getString());
          }
          if(s.getCell(13,11).getContents().length()>0)
          {
          LabelCell facState =(LabelCell) s.getCell(13,11);
          inspection.setFacilityState(facState.getString());
          }
          if(s.getCell(14,11).getContents().length()>0)
          {
          NumberCell facZip =(NumberCell) s.getCell(14,11);
          inspection.setFacilityZip(s.getCell(14,11).getContents());
          }
          if(s.getCell(0,13).getContents().length()>0)
          {
          LabelCell facContact =(LabelCell) s.getCell(0,13);
          inspection.setFacilityContact(facContact.getString());
          }
          if(s.getCell(8,13).getContents().length()>0)
          {
          LabelCell facEmail =(LabelCell) s.getCell(8,13);
          inspection.setFacilityEmail(facEmail.getString());
          }
          // final 
              if(s.getCell(17,15).getContents().length()>0)
              {
              BooleanCell inspFinalG =(BooleanCell) s.getCell(17,15);
              String finalG =inspFinalG.getContents();
              if(finalG.equals("true")) {
              inspection.setInspConstFinal("G");
              } 
              }
              if(s.getCell(18,15).getContents().length()>0)
              {
              BooleanCell inspFinalR =(BooleanCell) s.getCell(18,15);
              String finalR =inspFinalR.getContents();
              if(finalR.equals("true")) {
              inspection.setInspConstFinal("R");
              } 
              }
          // foundation 
               if(s.getCell(17,16).getContents().length()>0)
              {
              BooleanCell inspFoundationG =(BooleanCell) s.getCell(17,16);
              String foudationG =inspFoundationG.getContents();
              if(foudationG.equals("true")) {
              inspection.setInspConstFoundation("G");
              } 
              }
              if(s.getCell(18,16).getContents().length()>0)
              {
              BooleanCell inspFoundationR =(BooleanCell) s.getCell(18,16);
              String foudationR =inspFoundationR.getContents();
              if(foudationR.equals("true")) {
              inspection.setInspConstFoundation("R");
              } 
              }
        // concrete
            
             if(s.getCell(17,17).getContents().length()>0)
             {
             BooleanCell inspSlabG =(BooleanCell) s.getCell(17,17);
             String slabG =inspSlabG.getContents();
             if(slabG.equals("true")) {
             inspection.setInspConstSlab("G");
             } 
             }
             if(s.getCell(18,17).getContents().length()>0)
             {
             BooleanCell inspSlabR =(BooleanCell) s.getCell(18,17);
             String slabR =inspSlabR.getContents();
             if(slabR.equals("true")) {
             inspection.setInspConstSlab("R");
             } 
             }
        // framing
             
             if(s.getCell(17,18).getContents().length()>0)
             {
             BooleanCell inspFramingG =(BooleanCell) s.getCell(17,18);
             String framingG =inspFramingG.getContents();
             if(framingG.equals("true")) {
             inspection.setInspConstFraming("G");
             } 
             }
             if(s.getCell(18,18).getContents().length()>0)
             {
             BooleanCell inspFramingR =(BooleanCell) s.getCell(18,18);
             String framingR =inspFramingR.getContents();
             if(framingR.equals("true")) {
             inspection.setInspConstFraming("R");
             } 
             }
          // plumbing
              
               if(s.getCell(17,19).getContents().length()>0)
               {
               BooleanCell inspPlumbingG =(BooleanCell) s.getCell(17,19);
               String plumbingG =inspPlumbingG.getContents();
               if(plumbingG.equals("true")) {
               inspection.setInspConstPlumbing("G");
               } 
               }
               if(s.getCell(18,19).getContents().length()>0)
               {
               BooleanCell inspPlumbingR =(BooleanCell) s.getCell(18,19);
               String plumbingR =inspPlumbingR.getContents();
               if(plumbingR.equals("true")) {
               inspection.setInspConstPlumbing("R");
               } 
               }
            //mechanical
            
             if(s.getCell(17,20).getContents().length()>0)
             {
             BooleanCell inspMechG =(BooleanCell) s.getCell(17,20);
             String mechG =inspMechG.getContents();
             if(mechG.equals("true")) {
             inspection.setInspConstMech("G");
             } 
             }
             if(s.getCell(18,20).getContents().length()>0)
             {
             BooleanCell inspMechR =(BooleanCell) s.getCell(18,20);
             String mechR =inspMechR.getContents();
             if(mechR.equals("true")) {
             inspection.setInspConstMech("R");
             } 
             }
        // electrical
        
         if(s.getCell(17,21).getContents().length()>0)
         {
         BooleanCell inspElecG =(BooleanCell) s.getCell(17,21);
         String elecG =inspElecG.getContents();
         if(elecG.equals("true")) {
         inspection.setInspConstElec("G");
         } 
         }
         if(s.getCell(18,21).getContents().length()>0)
         {
         BooleanCell inspElecR =(BooleanCell) s.getCell(18,21);
         String elecR =inspElecR.getContents();
         if(elecR.equals("true")) {
         inspection.setInspConstElec("R");
         } 
         }
    //Energy
         
         if(s.getCell(17,22).getContents().length()>0)
         {
         BooleanCell inspEnergyG =(BooleanCell) s.getCell(17,22);
         String energyG =inspEnergyG.getContents();
         if(energyG.equals("true")) {
         inspection.setInspConstEnergy("G");
         } 
         }
         if(s.getCell(18,22).getContents().length()>0)
         {
         BooleanCell inspEnergyR =(BooleanCell) s.getCell(18,22);
         String energyR =inspEnergyR.getContents();
         if(energyR.equals("true")) {
         inspection.setInspConstEnergy("R");
         } 
         }
    //Sprinkler
         
         if(s.getCell(17,23).getContents().length()>0)
         {
         BooleanCell inspSprinklerG =(BooleanCell) s.getCell(17,23);
         String sprinklerG =inspSprinklerG.getContents();
         if(sprinklerG.equals("true")) {
         inspection.setInspConstSprinkler("G");
         } 
         }
         if(s.getCell(18,23).getContents().length()>0)
         {
         BooleanCell inspSprinklerR =(BooleanCell) s.getCell(18,23);
         String sprinklerR =inspSprinklerR.getContents();
         if(sprinklerR.equals("true")) {
         inspection.setInspConstSprinkler("R");
         } 
         }
    // egress
         if(s.getCell(17,24).getContents().length()>0)
         {
         BooleanCell inspEgressG =(BooleanCell) s.getCell(17,24);
         String egressG =inspEgressG.getContents();
         if(egressG.equals("true")) {
         inspection.setInspConstEgress("G");
         } 
         }
         if(s.getCell(18,24).getContents().length()>0)
         {
         BooleanCell inspEgressR =(BooleanCell) s.getCell(18,24);
         String egressR =inspEgressR.getContents();
         if(egressR.equals("true")) {
         inspection.setInspConstEgress("R");
         } 
         }
        //alarm
         
          if(s.getCell(17,25).getContents().length()>0)
          {
          BooleanCell inspAlarmG =(BooleanCell) s.getCell(17,25);
          String alarmG =inspAlarmG.getContents();
          if(alarmG.equals("true")) {
          inspection.setInspAlarm("G");
          } 
          }
          if(s.getCell(18,25).getContents().length()>0)
          {
          BooleanCell inspAlarmR =(BooleanCell) s.getCell(18,25);
          String alarmR =inspAlarmR.getContents();
          if(alarmR.equals("true")) {
          inspection.setInspAlarm("R");
          } 
          }
        // Suppression
          
          if(s.getCell(17,26).getContents().length()>0)
          {
          BooleanCell inspSuppressionG =(BooleanCell) s.getCell(17,26);
          String suppressionG =inspSuppressionG.getContents();
          if(suppressionG.equals("true")) {
          inspection.setInspConstSuppression("G");
          } 
          }
          if(s.getCell(18,26).getContents().length()>0)
          {
          BooleanCell inspSuppressionR =(BooleanCell) s.getCell(18,26);
          String suppressionR =inspSuppressionR.getContents();
          if(suppressionR.equals("true")) {
          inspection.setInspConstSuppression("R");
          } 
          }
          // back flow
         
          if(s.getCell(17,27).getContents().length()>0)
          {
          BooleanCell inspBackFlowG =(BooleanCell) s.getCell(17,27);
          String BackFlowG =inspBackFlowG.getContents();
          if(BackFlowG.equals("true")) {
          inspection.setInspConstBackFlow("G");
          } 
          }
          if(s.getCell(18,27).getContents().length()>0)
          {
          BooleanCell inspBackFlowR =(BooleanCell) s.getCell(18,27);
          String BackFlowR =inspBackFlowR.getContents();
          if(BackFlowR.equals("true")) {
          inspection.setInspConstBackFlow("R");
          } 
          }
          // kithen hood
          
          if(s.getCell(17,28).getContents().length()>0)
          {
          BooleanCell inspHoodG =(BooleanCell) s.getCell(17,28);
          String HoodG =inspHoodG.getContents();
          if(HoodG.equals("true")) {
          inspection.setInspConstHood("G");
          } 
          }
          if(s.getCell(18,28).getContents().length()>0)
          {
          BooleanCell inspHoodR =(BooleanCell) s.getCell(18,28);
          String HoodR =inspHoodR.getContents();
          if(HoodR.equals("true")) {
          inspection.setInspConstHood("R");
          } 
          }
          // Fire Extinguishers
          
          if(s.getCell(17,29).getContents().length()>0)
          {
          BooleanCell inspFireExtG =(BooleanCell) s.getCell(17,29);
          String FireExtG =inspFireExtG.getContents();
          if(FireExtG.equals("true")) {
          inspection.setInspConstFireExt("G");
          } 
          }
          if(s.getCell(18,29).getContents().length()>0)
          {
          BooleanCell inspFireExtR =(BooleanCell) s.getCell(18,29);
          String FireExtR =inspFireExtR.getContents();
          if(FireExtR.equals("true")) {
          inspection.setInspConstFireExt("R");
          } 
          }
          // Interior
          
          if(s.getCell(17,30).getContents().length()>0)
          {
          BooleanCell inspInteriorG =(BooleanCell) s.getCell(17,30);
          String InteriorG =inspInteriorG.getContents();
          if(InteriorG.equals("true")) {
          inspection.setInspConstInterior("G");
          } 
          }
          if(s.getCell(18,30).getContents().length()>0)
          {
          BooleanCell inspInteriorR =(BooleanCell) s.getCell(18,30);
          String InteriorR =inspInteriorR.getContents();
          if(InteriorR.equals("true")) {
          inspection.setInspConstInterior("R");
          } 
          }
          // pool
          
          if(s.getCell(17,31).getContents().length()>0)
          {
          BooleanCell inspPoolG =(BooleanCell) s.getCell(17,31);
          String PoolG =inspPoolG.getContents();
          if(PoolG.equals("true")) {
          inspection.setInspConstPool("G");
          } 
          }
          if(s.getCell(18,31).getContents().length()>0)
          {
          BooleanCell inspPoolR =(BooleanCell) s.getCell(18,31);
          String PoolR =inspPoolR.getContents();
          if(PoolR.equals("true")) {
          inspection.setInspConstPool("R");
          } 
          }
          // project 
           if(s.getCell(16,28).getContents().length()>0)
          {
          BooleanCell inspProjNew =(BooleanCell) s.getCell(16,28);
          String projNew =inspProjNew.getContents();
          if(projNew.equals("true")) {
          inspection.setInspProjNew("Y");
          } 
          }
          if(s.getCell(16,29).getContents().length()>0)
          {
          BooleanCell inspProjAddition =(BooleanCell) s.getCell(16,29);
          String projAddition =inspProjAddition.getContents();
          if(projAddition.equals("true")) {
          inspection.setInspProjAddition("Y");
          } 
          }
          if(s.getCell(16,30).getContents().length()>0)
          {
          BooleanCell inspProjRemodel =(BooleanCell) s.getCell(16,30);
          String projRemodel =inspProjRemodel.getContents();
          if(projRemodel.equals("true")) {
          inspection.setInspProjRemodel("Y");
          } 
          }
          if(s.getCell(16,31).getContents().length()>0)
          {
          BooleanCell inspProjOccChange =(BooleanCell) s.getCell(16,31);
          String projOccChange =inspProjOccChange.getContents();
          if(projOccChange.equals("true")) {
          inspection.setInspProjOccChange("Y");
          } 
          }
          if(s.getCell(16,32).getContents().length()>0)
          {
          BooleanCell inspProjExisting =(BooleanCell) s.getCell(16,32);
          String projExisting =inspProjExisting.getContents();
          if(projExisting.equals("true")) {
          inspection.setInspProjExisting("Y");
          } 
          }
          if(s.getCell(2,16).getContents().length()>0)
          {
          inspection.setInspMaintType(s.getCell(2,16).getContents());
          }
         
          if(s.getCell(3,20).getContents().length()>0)
          {
          inspection.setInspType(s.getCell(3,20).getContents());
          }
    // fire drill
     if(s.getCell(20,15).getContents().length()>0)
     {
     BooleanCell inspFireDrillG =(BooleanCell) s.getCell(20,15);
     String FireDrillG =inspFireDrillG.getContents();
     if(FireDrillG.equals("true")) {
     inspection.setInspFireDrillCurrent("G");
     } 
     }
     if(s.getCell(21,15).getContents().length()>0)
     {
     BooleanCell inspFireDrillR =(BooleanCell) s.getCell(21,15);
     String FireDrillR =inspFireDrillR.getContents();
     if(FireDrillR.equals("true")) {
     inspection.setInspFireDrillCurrent("R");
     } 
     }
     // evac
      if(s.getCell(20,16).getContents().length()>0)
      {
      BooleanCell inspFireEvacCurrentG =(BooleanCell) s.getCell(20,16);
      String FireEvacCurrentG =inspFireEvacCurrentG.getContents();
      if(FireEvacCurrentG.equals("true")) {
      inspection.setInspFireEvacCurrent("G");
      } 
      }
      if(s.getCell(21,16).getContents().length()>0)
      {
      BooleanCell inspFireEvacCurrentR =(BooleanCell) s.getCell(21,16);
      String FireEvacCurrentR =inspFireEvacCurrentR.getContents();
      if(FireEvacCurrentR.equals("true")) {
      inspection.setInspFireEvacCurrent("R");
      } 
      }    
    // emp train
          
           if(s.getCell(20,17).getContents().length()>0)
           {
           BooleanCell inspEmpTrainG =(BooleanCell) s.getCell(20,17);
           String EmpTrainG =inspEmpTrainG.getContents();
           if(EmpTrainG.equals("true")) {
           inspection.setInspEmpTrainCurrent("G");
           } 
           }
           if(s.getCell(21,17).getContents().length()>0)
           {
           BooleanCell inspEmpTrainR =(BooleanCell) s.getCell(21,17);
           String EmpTrainR =inspEmpTrainR.getContents();
           if(EmpTrainR.equals("true")) {
           inspection.setInspEmpTrainCurrent("R");
           } 
           }
        //alarm current
            if(s.getCell(20,18).getContents().length()>0)
            {
            BooleanCell inspAlarmCurrentG =(BooleanCell) s.getCell(20,18);
            String alarmCurrentG =inspAlarmCurrentG.getContents();
            if(alarmCurrentG.equals("true")) {
            inspection.setInspAlarmCurrent("G");
            } 
            }
            if(s.getCell(21,18).getContents().length()>0)
            {
            BooleanCell inspAlarmCurrentR =(BooleanCell) s.getCell(21,18);
            String alarmCurrentR =inspAlarmCurrentR.getContents();
            if(alarmCurrentR.equals("true")) {
            inspection.setInspAlarmCurrent("R");
            } 
            }   
    //sensitivity
    
     if(s.getCell(20,19).getContents().length()>0)
     {
     BooleanCell inspSensitivityG =(BooleanCell) s.getCell(20,19);
     String SensitivityG =inspSensitivityG.getContents();
     if(SensitivityG.equals("true")) {
     inspection.setInspSensitivityCurrent("G");
     } 
     }
     if(s.getCell(21,19).getContents().length()>0)
     {
     BooleanCell inspSensitivityR =(BooleanCell) s.getCell(21,19);
     String SensitivityR =inspSensitivityR.getContents();
     if(SensitivityR.equals("true")) {
     inspection.setInspSensitivityCurrent("R");
     } 
     }
     // sprinkler
      if(s.getCell(20,20).getContents().length()>0)
      {
      BooleanCell inspSprinklerCurrentG =(BooleanCell) s.getCell(20,20);
      String SprinklerCurrentG =inspSprinklerCurrentG.getContents();
      if(SprinklerCurrentG.equals("true")) {
      inspection.setInspSprinklerCurrent("G");
      } 
      }
      if(s.getCell(21,20).getContents().length()>0)
      {
      BooleanCell inspSprinklerCurrentR =(BooleanCell) s.getCell(21,20);
      String SprinklerCurrentR =inspSprinklerCurrentR.getContents();
      if(SprinklerCurrentR.equals("true")) {
      inspection.setInspSprinklerCurrent("R");
      } 
      } 
    // hood
     
     if(s.getCell(20,21).getContents().length()>0)
     {
     BooleanCell inspHoodG =(BooleanCell) s.getCell(20,21);
     String HoodG =inspHoodG.getContents();
     if(HoodG.equals("true")) {
     inspection.setInspHoodCurrent("G");
     } 
     }
     if(s.getCell(21,21).getContents().length()>0)
     {
     BooleanCell inspHoodR =(BooleanCell) s.getCell(21,21);
     String HoodR =inspHoodR.getContents();
     if(HoodR.equals("true")) {
     inspection.setInspHoodCurrent("R");
     } 
     }
    //exit light
      if(s.getCell(20,22).getContents().length()>0)
      {
      BooleanCell inspExtLightCurrentG =(BooleanCell) s.getCell(20,22);
      String ExtLightCurrentG =inspExtLightCurrentG.getContents();
      if(ExtLightCurrentG.equals("true")) {
      inspection.setInspExtLightCurrent("G");
      } 
      }
      if(s.getCell(21,22).getContents().length()>0)
      {
      BooleanCell inspExtLightCurrentR =(BooleanCell) s.getCell(21,22);
      String ExtLightR =inspExtLightCurrentR.getContents();
      if(ExtLightR.equals("true")) {
      inspection.setInspExtLightCurrent("R");
      } 
      } 
    // fire Ext
     
     if(s.getCell(20,23).getContents().length()>0)
     {
     BooleanCell inspFireExtG =(BooleanCell) s.getCell(20,23);
     String FireExtG =inspFireExtG.getContents();
     if(FireExtG.equals("true")) {
     inspection.setInspFireExtCurrent("G");
     } 
     }
     if(s.getCell(21,23).getContents().length()>0)
     {
     BooleanCell inspFireExtR =(BooleanCell) s.getCell(21,23);
     String FireExtR =inspFireExtR.getContents();
     if(FireExtR.equals("true")) {
     inspection.setInspFireExtCurrent("R");
     } 
     }
          // site
               if(s.getCell(21,24).getContents().length()>0)
               {
               BooleanCell inspSiteR =(BooleanCell) s.getCell(21,24);
               String SiteR =inspSiteR.getContents();
               if(SiteR.equals("true")) { 
               inspection.setInspSiteCurrent("R");
               } 
               }
               
              
     /*
      if(s.getCell(20,34).getContents().length()>0)
      {
      BooleanCell inspHoodCurrentG =(BooleanCell) s.getCell(20,34);
      String hoodCurrentG =inspHoodCurrentG.getContents();
      if(hoodCurrentG.equals("true")) {
      inspection.setInspHoodCurrent("G");
      } 
      }
      if(s.getCell(21,34).getContents().length()>0)
      {
      BooleanCell inspHoodCurrentR =(BooleanCell) s.getCell(21,34);
      String hoodCurrentR =inspHoodCurrentR.getContents();
      if(hoodCurrentR.equals("true")) {
      inspection.setInspHoodCurrent("R");
      } 
      }  
      // violation status
           if(s.getCell(16,37).getContents().length()>0)
          {
          BooleanCell vioStatus =(BooleanCell) s.getCell(16,37);
          String violationStatus =vioStatus.getContents();
          if(violationStatus.equals("true")) {
          inspection.setInspViolationStatus("VIOLATION");
          } 
          } 
          if(s.getCell(16,38).getContents().length()>0)
          {
          BooleanCell vioStatus =(BooleanCell) s.getCell(16,38);
          String violationStatus =vioStatus.getContents();
          if(violationStatus.equals("true")) {
          inspection.setInspViolationStatus("ORDER");
          } 
          } 
          if(s.getCell(19,37).getContents().length()>0)
          {
          BooleanCell vioStatus =(BooleanCell) s.getCell(19,37);
          String violationStatus =vioStatus.getContents();
          if(violationStatus.equals("true")) {
          inspection.setInspViolationStatus("COMPLIED");
          } 
          } 
          if(s.getCell(19,38).getContents().length()>0)
          {
          BooleanCell vioStatus =(BooleanCell) s.getCell(19,38);
          String violationStatus =vioStatus.getContents();
          if(violationStatus.equals("true")) {
          inspection.setInspViolationStatus("NOTES");
          } 
          } 
          if(s.getCell(19,38).getContents().length()>0)
          {
          BooleanCell vioStatus =(BooleanCell) s.getCell(19,38);
          String violationStatus =vioStatus.getContents();
          if(violationStatus.equals("true")) {
          inspection.setInspViolationStatus("NOTES");
          } 
          } 
          if(s.getCell(12,40).getContents().length()>0)
          {
          LabelCell inspStatus =(LabelCell) s.getCell(12,40);
          inspection.setInspStatus(inspStatus.getString());
          }
          DateCell inspDate =(DateCell) s.getCell(12,43);
          inspection.setInspDate(inspDate.getDate());
           inspection.setNextInspDays(s.getCell(13,39).getContents());
          
      } */

    public List selectFileList(String IdNumber) throws SQLException, Exception
    {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
    
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(InspectionsSQL.SELECT_DOCUMENT_NAMES_BY_ID);
       pstmt.clearParameters();
     
      if(IdNumber != null) {
        pstmt.setString(1,IdNumber);
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
    
    }
    /*   public int createExcelFile(OutputStream os,int fileId)  throws SQLException, Exception
     {  Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
         int len_total = 0; 
       try {
       String sql = InspectionsSQL.SELECT_DOCUMENT; 
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
       ModifyExcelFile(is,os);
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
    public static void ModifyExcelFile(InputStream inStream,OutputStream outStream) 
      {
        try
        {
          WorkbookSettings ws = new WorkbookSettings();
          ws.setLocale(new Locale("en", "EN"));
          Workbook workbook = Workbook.getWorkbook(
            inStream,ws);
            WritableWorkbook writeWorkBook = Workbook.createWorkbook(outStream,workbook);
         // WritableSheet s  = writeWorkBook.getSheet("Cover");
            WritableSheet s= writeWorkBook.createSheet("Sheet1", 3);
            s.addCell(new Label(0, 9, "Hello World"));
        // aeNumber=(new Label(0, 9, "Hello World"));
       //  aeNumber.addCell(new Label(0, 9, "Hello World"))
        //  readImageSheet(s);
         writeWorkBook.write(); 
        writeWorkBook.close();  
         workbook.close();    
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        catch (BiffException e)
        {
          e.printStackTrace();
        }
          catch (WriteException e)
          {
            e.printStackTrace();
          }
        
      }
      
    

    }
    public void processOneSheet(InputStream inStream) throws Exception {
                    Package pkg = Package.open(inStream);
                    XSSFReader r = new XSSFReader( pkg );
                    SharedStringsTable sst = r.getSharedStringsTable();
                InputStream inp = new FileInputStream("excelTest.xlsx");

                    XMLReader parser = fetchSheetParser(sst);

                    // rId2 found by processing the Workbook
                    // Seems to either be rId# or rSheet#
                    InputStream sheet2 = r.getSheet("Cover");
                    InputSource sheetSource = new InputSource(sheet2);
                    parser.parse(sheetSource);
                    sheet2.close();
            }

            public void processAllSheets(String filename) throws Exception {
                    Package pkg = Package.open(filename);
                    XSSFReader r = new XSSFReader( pkg );
                    SharedStringsTable sst = r.getSharedStringsTable();
                    
                    XMLReader parser = fetchSheetParser(sst);

                    Iterator<InputStream> sheets = r.getSheetsData();
                    while(sheets.hasNext()) {
                             InputStream sheet = sheets.next();
                            InputSource sheetSource = new InputSource(sheet);
                            parser.parse(sheetSource);
                            sheet.close();
                            
                    }
            }

            public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
                    XMLReader parser =
                            XMLReaderFactory.createXMLReader(
                                            "org.apache.xerces.parsers.SAXParser"
                            );
                    ContentHandler handler = new SheetHandler(sst);
                    parser.setContentHandler(handler);
                    return parser;
            }

            /** 
             * See org.xml.sax.helpers.DefaultHandler javadocs 
             
            private static class SheetHandler extends DefaultHandler {
                    private SharedStringsTable sst;
                    private String lastContents;
                    private boolean nextIsString;
                    
                    private SheetHandler(SharedStringsTable sst) {
                            this.sst = sst;
                    }
                    
                    public void startElement(String uri, String localName, String name,
                                    Attributes attributes) throws SAXException {
                            // c => cell
                            if(name.equals("c")) {
                                    // Print the cell reference
                                    // Figure out if the value is an index in the SST
                                    String cellType = attributes.getValue("t");
                                    if(cellType != null && cellType.equals("s")) {
                                            nextIsString = true;
                                    } else {
                                            nextIsString = false;
                                    }
                            }
                            // Clear contents cache
                            lastContents = "";
                    }
                    
                    public void endElement(String uri, String localName, String name)
                                    throws SAXException {
                            // Process the last contents as required.
                            // Do now, as characters() may be called more than once
                            if(nextIsString) {
                                    int idx = Integer.parseInt(lastContents);
                                    lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                            }

                            // v => contents of a cell
                            // Output after we've seen the string contents
                            if(name.equals("v")) {
                                    
                            }
                    }

                    public void characters(char[] ch, int start, int length)
                                    throws SAXException {
                            lastContents += new String(ch, start, length);
                    }
            }
            
            public static void main(String[] args) throws Exception {
                    FromHowTo howto = new FromHowTo();
                    howto.processOneSheet(args[0]);
                    howto.processAllSheets(args[0]);
            } */