package idhsInspections.data;




import childcare.data.DfbsChildcareSQL;

import childcare.to.DfbsOwner;

import  idhsInspections.to.*;
import  idhsInspections.data.*;
import java.util.*;
import java.sql.*;

import oracle.jdbc.*; 
import hs.to.*;
import hs.util.*;

import hs.data.*;

import main.to.ApplicationSecurity;

public class fireInspectionDAO extends HsDAO {
    public fireInspectionDAO() {
    }
  public  List selectInspections(String facilityId, String fileterSQL) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      try 
      {  conn = getConnection();
        pstmt = conn.prepareStatement(fileterSQL);
        pstmt.clearParameters();
        pstmt.setString(1,facilityId);
        pstmt.setString(2,facilityId);
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        {  
          fireInspection  inspection = new fireInspection ();
          inspection.setInspDate(rs.getDate(6));
          inspection.setInspVioPrintDate(rs.getDate(10));
          inspection.setInspAlarm(rs.getString(4));
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
          inspection.setInspInspectorId(rs.getInt(11));
          inspection.setInspUploadError(rs.getString(16));
          inspection.setRecCreatedDate(rs.getDate(17));
          inspection.setFacilityName(rs.getString(18));
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
    public  List selectInspectionsNew(String facilityId, String fileterSQL) throws SQLException, Exception 
      {
      
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(fileterSQL);
          pstmt.clearParameters();
          pstmt.setString(1,facilityId);
          rs = pstmt.executeQuery();
          
          while(rs.next()) 
          {  
            fireInspection  inspection = new fireInspection ();
            inspection.setInspDate(rs.getDate(6));
            inspection.setInspVioPrintDate(rs.getDate(10));
            inspection.setInspAlarm(rs.getString(4));
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
            inspection.setInspInspectorId(rs.getInt(11));
            inspection.setInspUploadError(rs.getString(16));
            inspection.setRecCreatedDate(rs.getDate(17));
            inspection.setFacilityName(rs.getString(18));
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
     public fireInspection  selectInspection(int idNumber) throws SQLException, Exception 
    {
    
      fireInspection  inspection = new fireInspection ();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
         
      try 
      {
        
        conn = getConnection();
         pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTION_BY_ID);
        pstmt.clearParameters();
        pstmt.setInt(1,idNumber);
        rs = pstmt.executeQuery();
        if(rs.next()) 
        {
          inspection.setInspDate(rs.getDate(6));
          inspection.setInspVioPrintDate(rs.getDate(10));
          inspection.setInspAlarm(rs.getString(4));
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
      public int findFacilityIdMatch (String facilityId,inspectionSearch search) throws SQLException, Exception 
    {
    
      Connection conn = null;
      PreparedStatement pstmt = null;
        ResultSet rs = null;   
        int facCount=0;
        String facCheck="N";
      try 
      {
        conn = getConnection();
        conn.setAutoCommit(false);
        if(facilityId.substring(0,2).toUpperCase().equals("AE")) {
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_ENTR WHERE IDENTIFICATION_NUMBER=upper(?)");  
            facCheck="Y";
            search.setSearchFor("AE");
        }
          if(facilityId.substring(0,2).toUpperCase().equals("GH")||
              facilityId.substring(0,2).toUpperCase().equals("RM")||
              facilityId.substring(0,2).toUpperCase().equals("DC")||
              facilityId.substring(0,2).toUpperCase().equals("MD")||
              facilityId.substring(0,2).toUpperCase().equals("CI")) {
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_DAY_CARE WHERE FIRE_ID=upper(?)");  
              facCheck="Y";
              search.setSearchFor("DC");
          }
          if(facilityId.substring(0,2).toUpperCase().equals("SC")) {
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_SCHOOL WHERE FIRE_ID=upper(?)"); 
              facCheck="Y";
               search.setSearchFor("SC");
           }
          if(facilityId.substring(0,2).toUpperCase().equals("UO")) {
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_UST_LOCATION WHERE IDENTIFICATION_NUMBER=upper(?)"); 
              facCheck="Y";
              search.setSearchFor("UO");
          }
          if(facilityId.substring(0,2).toUpperCase().equals("FW"))
          {
          if(facilityId.substring(0,3).toUpperCase().equals("FWR")||
              facilityId.substring(0,3).toUpperCase().equals("FWT"))
               {
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_SALES WHERE IDENTIFICATION_NUMBER=upper(?)");  
              facCheck="Y";
             search.setSearchFor("FWT");
          }
          else
              {
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_COMPLIANCE WHERE IDENTIFICATION_NUMBER=upper(?)"); 
              facCheck="Y";
              search.setSearchFor("FW");
              }
          }
          if(facilityId.substring(0,2).toUpperCase().equals("DD")||
              facilityId.substring(0,2).toUpperCase().equals("SG")||
              facilityId.substring(0,2).toUpperCase().equals("AM")||
              facilityId.substring(0,2).toUpperCase().equals("SA")||
              facilityId.substring(0,2).toUpperCase().equals("SH")||
              facilityId.substring(0,2).toUpperCase().equals("ES")||
              facilityId.substring(0,2).toUpperCase().equals("PS")||
              facilityId.substring(0,2).toUpperCase().equals("LO")||
              facilityId.substring(0,2).toUpperCase().equals("LT")||
              facilityId.substring(0,2).toUpperCase().equals("HO")||
              facilityId.substring(0,2).toUpperCase().equals("AS")||
              facilityId.substring(0,2).toUpperCase().equals("RA")||
              facilityId.substring(0,2).toUpperCase().equals("TR")||
              facilityId.substring(0,2).toUpperCase().equals("DS")){
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_HOSPITAL WHERE FIRE_ID=upper(?)"); 
              facCheck="Y";
              search.setSearchFor("HO");
          }
          if(facilityId.substring(0,2).toUpperCase().equals("HM")||
              facilityId.substring(0,2).toUpperCase().equals("JP")||
              facilityId.substring(0,2).toUpperCase().equals("CS")||
              facilityId.substring(0,2).toUpperCase().equals("BU")) {
              pstmt = conn.prepareStatement("SELECT COUNT(*) FROM FIRE_OTHER WHERE IDENTIFICATION_NUMBER=upper(?)"); 
              facCheck="Y";
              search.setSearchFor("OTHER");
          }
          if( facCheck.equals("Y"))
          { pstmt.setString(1,facilityId);
         rs = pstmt.executeQuery();
          if(rs.next()) 
          {
            facCount=rs.getInt(1);
          }
          }
         return(facCount); 
      }
      finally 
      {
       try {rs.close();
          conn.close();
         pstmt.close();
        } catch (Exception e) {facCount=0;}
      }
    }
     
    
    
    
     public void updateIssueDateEntr (String entrId,String inspDate ,String applicationYear) throws SQLException, Exception 
    {
      Connection conn = null;
      CallableStatement proc = null;
      try 
      {  
        conn = getConnection();
        conn.setAutoCommit(false);
        proc = conn.prepareCall("{ call fire_entr_add_issue_date(?,?,?) }");
        proc.setString(1, entrId);
        proc.setString(2, inspDate);
        proc.setString(3, applicationYear);
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
   
     
   public  String selectCurrentYear() throws SQLException, Exception 
    {
    
      
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      String currentYear="";
      try 
      {  conn = getConnection();
        
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_CURRENT_YEAR);
       
        
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
      
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTOR);
     
      
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
  public String selectInspectionSupervisor(int inspectorId) throws SQLException, Exception 
  {
  
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String supervisorId="";
    try 
    {  conn = getConnection();
      
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTOR_SUPERVISOR_BY_ID);
     
      
      pstmt.clearParameters();
      pstmt.setInt(1,inspectorId);
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
        
        supervisorId=rs.getString(1);
        
      }
      
      return supervisorId;
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
  public  String selectCountyCode(String countyName) throws SQLException, Exception 
  {
  
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    String countyCode="";
    try 
    {  conn = getConnection();
      
        pstmt = conn.prepareStatement("SELECT COUNTY_CODE FROM DFBS_COUNTY WHERE COUNTY_NAME='"+countyName+"'");
     
     pstmt.clearParameters();
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
        
        countyCode=rs.getString(1);
        
      }
      
      return countyCode;
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
    public  String selectCountyName(String countyCode) throws SQLException, Exception 
    {
    
      
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      String countyName="";
      try 
      {  conn = getConnection();
        
          pstmt = conn.prepareStatement("SELECT COUNTY_NAME FROM DFBS_COUNTY WHERE COUNTY_CODE='"+countyCode+"'");
       
       pstmt.clearParameters();
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        { 
          
          countyName=rs.getString(1);
          
        }
        
        return countyName;
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
  public  int selectMaxInspection(String faciliyId) throws SQLException, Exception 
  {
  
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    int maxInspectionId=0;
    try 
    {  conn = getConnection();
      
        pstmt = conn.prepareStatement(InspectionsSQL.MAX_IDHS_INSPECTION);
     
     pstmt.clearParameters();
        pstmt.setString(1,faciliyId);
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
        maxInspectionId=rs.getInt(1);
      }
      
      return maxInspectionId;
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
  public  int selectMaxInspectionForViolation(String faciliyId,int inspectionId) throws SQLException, Exception 
  {
  
    
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    int maxInspectionId=0;
    try 
    {  conn = getConnection();
      
        pstmt = conn.prepareStatement(InspectionsSQL.MAX_IDHS_INSPECTION_FOR_VIOLATION);
     
     pstmt.clearParameters();
        pstmt.setString(1,faciliyId);
        pstmt.setInt(2,inspectionId);
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      { 
        
        maxInspectionId=rs.getInt(1);
        
      }
      
      return maxInspectionId;
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
       public fireInspection  selectInspectionNew(int idNumber) throws SQLException, Exception 
    {
    
      fireInspection  inspection = new fireInspection ();
      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
         
      try 
      {
        
        conn = getConnection();
         
         pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_INSPECTION);
         
        pstmt.clearParameters();
        pstmt.setInt(1,idNumber);
        rs = pstmt.executeQuery();
        if(rs.next()) 
        {
              inspection.setInspDate(rs.getDate(49));
              inspection.setNextInspDate(rs.getDate(50));
              inspection.setInspUploadError(rs.getString(52));
            inspection.setRecCreatedDate(rs.getDate(51));
              inspection.setInspFireDrillCurrent(rs.getString(39));
              inspection.setInspFacId(rs.getString(2));
              inspection.setInspInspectorId(rs.getInt(48));
                inspection.setInspAlarm(rs.getString(38));
               inspection.setInspType(rs.getString(37));
              inspection.setInspId(rs.getInt(1));
            inspection.setFacilityAddress2(rs.getString(8));
            inspection.setFacilityCity(rs.getString(9));
            inspection.setFacilityContact(rs.getString(14));
            inspection.setFacilityCounty(rs.getString(10));
            inspection.setFacilityEmail(rs.getString(16));
            inspection.setFacilityName(rs.getString(3));
            inspection.setFacilityPhone(rs.getString(15));
            inspection.setFacilityState(rs.getString(11));
            inspection.setFacilitySteetNumber(rs.getString(4));
            inspection.setFacilityStreetName(rs.getString(6));
            inspection.setFacilityStreetPrefix(rs.getString(5));
            inspection.setFacilityStreetSuffix(rs.getString(7));
            inspection.setFacilityZip(rs.getString(12));
            inspection.setFacilityZip2(rs.getString(13));
            inspection.setInspConstFireExt(rs.getString(29));
            inspection.setInspConstElec(rs.getString(22));
            inspection.setInspConstEnergy(rs.getString(23));
            inspection.setInspConstEgress(rs.getString(27));
            inspection.setInspConstFoundation(rs.getString(17));
            inspection.setInspConstFraming(rs.getString(19));
            inspection.setInspConstInterior(rs.getString(25));
            inspection.setInspConstMech(rs.getString(21));
            inspection.setInspConstSuppression(rs.getString(28));
            inspection.setInspConstPlumbing(rs.getString(20));
            inspection.setInspConstPool(rs.getString(26));
            inspection.setInspConstSlab(rs.getString(18));
            inspection.setInspConstSprinkler(rs.getString(24));
             inspection.setInspSensitivityCurrent(rs.getString(44));
            inspection.setInspEmpTrainCurrent(rs.getString(41));
            inspection.setInspHoodCurrent(rs.getString(46));
            inspection.setInspSprinklerCurrent(rs.getString(42));
            inspection.setInspMaintType(rs.getString(35));
            inspection.setInspOtherActivity(rs.getString(36));
            inspection.setInspProjAddition(rs.getString(31));
            inspection.setInspProjExisting(rs.getString(34));
            inspection.setInspProjNew(rs.getString(30));
            inspection.setInspProjOccChange(rs.getString(33));
            inspection.setInspProjRemodel(rs.getString(32));
            inspection.setInspFireEvacCurrent(rs.getString(40));
            inspection.setInspExtLightCurrent(rs.getString(45));
            inspection.setInspAlarmCurrent(rs.getString(43));
            inspection.setInspViolationStatus(rs.getString(47)); 
            inspection.setInspFireExtCurrent(rs.getString(53)); 
            inspection.setNotes(rs.getString(54));
            inspection.setEditFlag(rs.getInt(55));
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
  
      public void insertInspectionNew (fireInspection  inspection,ApplicationSecurity security) throws SQLException, Exception 
      {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
           int facCount=0;   
      try 
      { 
        
          conn = getConnection();
          conn.setAutoCommit(false);
          pstmt = conn.prepareStatement(InspectionsSQL.INSERT_IDHS_INSPECTION);
          int id = this.getId(conn,InspectionsSQL.SELECT_NEXT_INSPECTION_ID);
          pstmt.setString(50,inspection.getNextInspDateString());
          pstmt.setString(51,"0");
         pstmt.setString(49,inspection.getInspDateString());
        pstmt.setString(38,inspection.getInspAlarm());
        pstmt.setString(2,inspection.getInspFacId());
        if (inspection.getInspInspectorId() ==0)
        {
        int inspectorId= this.selectInspector(security.getUserId()+"@dhs.in.gov");
        pstmt.setInt(48,inspectorId);
           
        } 
        else {
            pstmt.setInt(48,inspection.getInspInspectorId()); 
        }
          pstmt.setString(37,inspection.getInspType());
         pstmt.setInt(1,id);
          inspection.setInspId(id);
           pstmt.setString(8,inspection.getFacilityAddress2());
          pstmt.setString(9,inspection.getFacilityCity());
          pstmt.setString(14,inspection.getFacilityContact());
          pstmt.setString(10,inspection.getFacilityCounty());
          pstmt.setString(16,inspection.getFacilityEmail());
          pstmt.setString(3,inspection.getFacilityName());
          pstmt.setString(15,inspection.getFacilityPhone());
          pstmt.setString(11,inspection.getFacilityState());
          pstmt.setString(4,inspection.getFacilitySteetNumber());
          pstmt.setString(6,inspection.getFacilityStreetName());
          pstmt.setString(5,inspection.getFacilityStreetPrefix());
          pstmt.setString(7,inspection.getFacilityStreetSuffix());
          pstmt.setString(12,inspection.getFacilityZip());
          pstmt.setString(13,inspection.getFacilityZip2());
         pstmt.setString(29,inspection.getInspConstFireExt());
          pstmt.setString(22,inspection.getInspConstElec());
          pstmt.setString(23,inspection.getInspConstEnergy());
          pstmt.setString(27,inspection.getInspConstEgress());
          pstmt.setString(17,inspection.getInspConstFoundation());
          pstmt.setString(19,inspection.getInspConstFraming());
          pstmt.setString(25,inspection.getInspConstInterior());
          pstmt.setString(21,inspection.getInspConstMech());
          pstmt.setString(28,inspection.getInspConstSuppression());
          pstmt.setString(20,inspection.getInspConstPlumbing());
          pstmt.setString(26,inspection.getInspConstPool());
          pstmt.setString(18,inspection.getInspConstSlab());
          pstmt.setString(24,inspection.getInspConstSprinkler());
          pstmt.setString(43,inspection.getInspAlarmCurrent());
          pstmt.setString(40,inspection.getInspFireEvacCurrent());
          pstmt.setString(45,inspection.getInspExtLightCurrent());
          pstmt.setString(41,inspection.getInspEmpTrainCurrent());
          pstmt.setString(46,inspection.getInspHoodCurrent());
          pstmt.setString(35,inspection.getInspMaintType());
          pstmt.setString(36,inspection.getInspOtherActivity());
           pstmt.setString(31,inspection.getInspProjAddition());
          pstmt.setString(34,inspection.getInspProjExisting());
          pstmt.setString(30,inspection.getInspProjNew());
          pstmt.setString(33,inspection.getInspProjOccChange());
          pstmt.setString(32,inspection.getInspProjRemodel());
          pstmt.setString(39,inspection.getInspFireDrillCurrent());
          pstmt.setString(44,inspection.getInspSensitivityCurrent());
          pstmt.setString(42,inspection.getInspSprinklerCurrent());
          pstmt.setString(47,inspection.getInspViolationStatus()); 
          pstmt.setString(53,inspection.getInspFireExtCurrent()); 
          pstmt.setString(54,inspection.getInspConstFinal());
          pstmt.setString(55,inspection.getInspConstBackFlow());
          pstmt.setString(56,inspection.getInspConstHood());
          pstmt.setString(57,inspection.getInspSiteCurrent());
          pstmt.setString(58,inspection.getInspStatus());
          pstmt.setString(59,inspection.getNotes());
          pstmt.setString(52,"Facility Number/ID does not match database, Please verify"); 
          inspectionSearch search = new inspectionSearch();
          if (inspection.getInspFacId()!=null)
          { facCount = findFacilityIdMatch(inspection.getInspFacId(),search);
          if (facCount >0)  {
              pstmt.setString(52,"Facility matched"); 
          }
          
          } 
          
        pstmt.execute();
        conn.commit();
          String currentYear= this.selectCurrentDate();
        //  if(search.getSearchFor() !=null && search.getSearchFor().equals("AE")&& inspection.getInspViolationStatus().equals("COMPLIED"))
        //  {
         // this.updateIssueDateEntr(inspection.getInspFacId(),inspection.getInspDateString(),currentYear.substring(6,10));
           //  this.EndFine(inspection,security);
           // this.createIdhsFines(inspection.getInspFacId());
        //  } 
          if(search.getSearchFor() !=null &&( search.getSearchFor().equals("FWT")||search.getSearchFor().equals("FWR"))&& inspection.getInspViolationStatus().equals("COMPLIED"))
          {
          updateIssueDateFireworks(inspection.getInspFacId(),inspection.getInspDateString(),"Consumer");
           //  this.EndFine(inspection,security);
           //  this.createIdhsFines(inspection.getInspFacId());
           }
          if(search.getSearchFor() !=null && search.getSearchFor().equals("FW")&& inspection.getInspViolationStatus().equals("COMPLIED"))
          { 
          updateIssueDateFireworks(inspection.getInspFacId(),inspection.getInspDateString(),"Wholesale");
           //  this.EndFine(inspection,security);
           //  this.createIdhsFines(inspection.getInspFacId());
           }
          if(inspection.getInspViolationStatus().equals("COMPLIED")) {
             this.deleteViolations(inspection.getInspId()) ;
          }
      }
      finally 
      {
       try {
          conn.close();
         pstmt.close();
        } catch (Exception e) { }
      }
      } 
  public void updateInspectionNew (fireInspection  inspection) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
  PreparedStatement pstmt1 = null;   
  try 
  {
    conn = getConnection();
    conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_IDHS_INSPECTION);
      pstmt.clearParameters();
   
    pstmt.setString(50,inspection.getInspDateString());
    pstmt.setString(1,inspection.getNextInspDateString());
    pstmt.setString(39,inspection.getInspAlarm());
    pstmt.setString(2,inspection.getInspFacId());
    pstmt.setInt(49,inspection.getInspInspectorId());
    pstmt.setString(38,inspection.getInspSprinkler());
      pstmt.setString(37,inspection.getInspType());
    pstmt.setString(52,inspection.getInspUploadError());
    pstmt.setInt(53,inspection.getInspId()); 
      pstmt.setString(51,inspection.getNotes());
      pstmt.setString(8,inspection.getFacilityAddress2());
      pstmt.setString(9,inspection.getFacilityCity());
      pstmt.setString(14,inspection.getFacilityContact());
      pstmt.setString(10,inspection.getFacilityCounty());
      pstmt.setString(16,inspection.getFacilityEmail());
      pstmt.setString(3,inspection.getFacilityName());
      pstmt.setString(15,inspection.getFacilityPhone());
      pstmt.setString(11,inspection.getFacilityState());
      pstmt.setString(4,inspection.getFacilitySteetNumber());
      pstmt.setString(6,inspection.getFacilityStreetName());
      pstmt.setString(5,inspection.getFacilityStreetPrefix());
      pstmt.setString(7,inspection.getFacilityStreetSuffix());
      pstmt.setString(12,inspection.getFacilityZip());
      pstmt.setString(13,inspection.getFacilityZip2());
      pstmt.setString(22,inspection.getInspConstElec());
      pstmt.setString(23,inspection.getInspConstEnergy());
      pstmt.setString(17,inspection.getInspConstFoundation());
      pstmt.setString(19,inspection.getInspConstFraming());
      pstmt.setString(25,inspection.getInspConstInterior());
      pstmt.setString(21,inspection.getInspConstMech());
      pstmt.setString(20,inspection.getInspConstPlumbing());
      pstmt.setString(26,inspection.getInspConstPool());
      pstmt.setString(18,inspection.getInspConstSlab());
      pstmt.setString(24,inspection.getInspConstSprinkler());
      pstmt.setString(44,inspection.getInspAlarmCurrent());
      pstmt.setString(45,inspection.getInspHoodCurrent());
      pstmt.setString(35,inspection.getInspMaintType());
      pstmt.setString(36,inspection.getInspOtherActivity());
      pstmt.setString(31,inspection.getInspProjAddition());
      pstmt.setString(34,inspection.getInspProjExisting());
      pstmt.setString(30,inspection.getInspProjNew());
      pstmt.setString(33,inspection.getInspProjOccChange());
      pstmt.setString(32,inspection.getInspProjRemodel());
      pstmt.setString(43,inspection.getInspSprinklerCurrent());
      pstmt.setString(48,inspection.getInspViolationStatus());
      pstmt.setString(27,inspection.getInspConstEgress());
      pstmt.setString(29,inspection.getInspConstFireExt());
      pstmt.setString(28,inspection.getInspConstSuppression());
      pstmt.setString(42,inspection.getInspEmpTrainCurrent());
      pstmt.setString(47,inspection.getInspExtLightCurrent());
      pstmt.setString(40,inspection.getInspFireDrillCurrent());
      pstmt.setString(41,inspection.getInspFireEvacCurrent());
      pstmt.setString(38,inspection.getInspFireExtCurrent());
      pstmt.setString(46,inspection.getInspSensitivityCurrent()); 
    pstmt.execute();
      pstmt1 = conn.prepareStatement(InspectionsSQL.UPDATE_IDHS_VIOLATIONS_VIOLATION_DATE);
      pstmt1.clearParameters();
      pstmt1.setString(1,inspection.getNextInspDateString());
      pstmt1.setInt(2,inspection.getInspId());
      pstmt1.execute(); 
    conn.commit();
      if(inspection.getInspViolationStatus().equals("COMPLIED")) {
         this.deleteViolations(inspection.getInspId()) ;
      }
     
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
  public fireInspection  selectInspectionsNew(String idNumber) throws SQLException, Exception 
  {
  
  fireInspection  inspection = new fireInspection ();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
    
  try
  {
   
   conn = getConnection();
    pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_INSPECTIONS);
   pstmt.clearParameters();
   pstmt.setString(1,idNumber);
   rs = pstmt.executeQuery();
   if(rs.next()) 
   {
         inspection.setInspDate(rs.getDate(50));
         inspection.setNextInspDate(rs.getDate(51));
         inspection.setInspAlarm(rs.getString(39));
         inspection.setInspFacId(rs.getString(2));
         inspection.setInspInspectorId(rs.getInt(49));
           inspection.setInspSprinkler(rs.getString(38));
          inspection.setInspType(rs.getString(37));
         inspection.setInspId(rs.getInt(1));
       inspection.setFacilityAddress2(rs.getString(8));
       inspection.setFacilityCity(rs.getString(9));
       inspection.setFacilityContact(rs.getString(14));
       inspection.setFacilityCounty(rs.getString(10));
       inspection.setFacilityEmail(rs.getString(16));
       inspection.setFacilityName(rs.getString(3));
       inspection.setFacilityPhone(rs.getString(15));
       inspection.setFacilityState(rs.getString(11));
       inspection.setFacilitySteetNumber(rs.getString(4));
       inspection.setFacilityStreetName(rs.getString(6));
       inspection.setFacilityStreetPrefix(rs.getString(5));
       inspection.setFacilityStreetSuffix(rs.getString(7));
       inspection.setFacilityZip(rs.getString(12));
       inspection.setFacilityZip2(rs.getString(13));
       inspection.setInspConstElec(rs.getString(22));
       inspection.setInspConstEnergy(rs.getString(23));
       inspection.setInspConstFoundation(rs.getString(17));
       inspection.setInspConstFraming(rs.getString(19));
       inspection.setInspConstInterior(rs.getString(25));
       inspection.setInspConstMech(rs.getString(21));
       inspection.setInspConstPlumbing(rs.getString(20));
       inspection.setInspConstPool(rs.getString(26));
       inspection.setInspConstSlab(rs.getString(18));
       inspection.setInspConstSprinkler(rs.getString(24));
       inspection.setNextInspDate(rs.getDate(51));
    /*    inspection.setInspBackFlow(rs.getString(41));
       inspection.setInspBackFlowCurrent(rs.getString(46));
       inspection.setInspHood(rs.getString(42));
      
       inspection.setInspMaintType(rs.getString(35));
       inspection.setInspOtherActivity(rs.getString(36));
       inspection.setInspProjAddition(rs.getString(31));
       inspection.setInspProjExisting(rs.getString(34));
       inspection.setInspProjNew(rs.getString(30));
       inspection.setInspProjOccChange(rs.getString(33));
       inspection.setInspProjRemodel(rs.getString(32));
       inspection.setInspSmoke(rs.getString(40));
       inspection.setInspSmokeCurrent(rs.getString(45));
       inspection.setInspSprinklerCurrent(rs.getString(43));
       inspection.setInspViolationStatus(rs.getString(48));
       inspection.setInspConstEgress(rs.getString(27));
       inspection.setInspConstFireExt(rs.getString(29));
       inspection.setInspConstSuppression(rs.getString(28));
       inspection.setInspEmpTrainCurrent(rs.getString());
       inspection.setInspExtLightCurrent(rs.getString());
       inspection.setInspFireDrillCurrent(rs.getString());
       inspection.setInspFireEvacCurrent(rs.getString());
       inspection.setInspFireExtCurrent(rs.getString());
       inspection.setInspSensitivityCurrent(rs.getString()); */
       
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
  public  List selectInspections(String inspStartDate, String inspEndDate,String inspectorId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      try 
      {  conn = getConnection();
         
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_INSPECTIONS_BY_INSPECTOR);
        pstmt.clearParameters();
        pstmt.setString(2,inspStartDate);
        pstmt.setString(3,inspEndDate);
        pstmt.setString(1,inspectorId);
         
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        {  
          fireInspection  inspection = new fireInspection ();
            inspection.setInspId(rs.getInt(1));
            inspection.setInspDate(rs.getDate(2));
           inspection.setInspCompliance(rs.getString(3));
          inspection.setInspFacId(rs.getString(4));
          inspection.setInspectorName(rs.getString(5));
            inspection.setInspUploadError(rs.getString(6));
            inspection.setRecCreatedDate(rs.getDate(7));
            inspection.setInspFacId(rs.getString(8));
            inspection.setFacilityName(rs.getString(9));
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
  public  List selectInspectionsGroup(String inspStartDate, String inspEndDate,String inspectorId,String filterInspId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      try 
      {  conn = getConnection();
        if (filterInspId == "" ||filterInspId.equals("")) 
        {
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_INSPECTIONS_BY_INSPECTOR_GROUP);
        pstmt.clearParameters();
        pstmt.setString(2,inspStartDate);
        pstmt.setString(3,inspEndDate);
        pstmt.setString(1,inspectorId);
        }
        else {
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_INSPECTIONS_BY_INSPECTOR_GROUP_FILTER);
          pstmt.clearParameters();
          pstmt.setString(1,inspStartDate);
          pstmt.setString(2,inspEndDate);
          pstmt.setString(3,filterInspId);
        }
         
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        {  
          fireInspection  inspection = new fireInspection ();
            inspection.setInspId(rs.getInt(1));
            inspection.setInspDate(rs.getDate(2));
           inspection.setInspCompliance(rs.getString(3));
          inspection.setInspFacId(rs.getString(4));
          inspection.setInspectorName(rs.getString(5));
            inspection.setInspUploadError(rs.getString(6));
            inspection.setRecCreatedDate(rs.getDate(7));
            inspection.setInspFacId(rs.getString(8));
            inspection.setFacilityName(rs.getString(9));
            inspection.setInspType(rs.getString(10));
            inspection.setInspRemarks(rs.getString(11));
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
  public void deleteExcelFileUploaded(int fileId) throws SQLException, Exception 
   {
           Connection conn = null;
           PreparedStatement pstmt = null;
        try 
     {
           conn = getConnection();
           conn.setAutoCommit(true);
           pstmt = conn.prepareStatement(InspectionsSQL.DELETE_EXCEL_FILE_UPLOADED);
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
  public String  selectCurrentDate() throws SQLException, Exception 
  {
  
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   String l_currentDate=null;    
   try 
   {
     
     conn = getConnection();
      pstmt = conn.prepareStatement(InspectionsSQL.SELECT_CURRENT_DATE);
     pstmt.clearParameters();
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
       
       l_currentDate =(rs.getString(1));
      
     }
   
  
     return l_currentDate;
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
  public void updateIssueDateFireworks (String facId,String inspDate ,String permitType) throws SQLException, Exception 
   {
    Connection conn = null;
     PreparedStatement pstmt = null;
     
     try 
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       if(permitType.equals("Consumer")) {
         pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_ISSUE_DATE_RETAIL);
       } 
       else 
       {
         pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_ISSUE_DATE_WHOLESALE);
       }
       pstmt.clearParameters();
       pstmt.setString(1,inspDate);
       pstmt.setString(2,facId);
      
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
  public  List setAlerts(inspectionSearch search) throws SQLException, Exception 
    {
      List alertList = new ArrayList();
  // ae alerts
      List selectedList = search.getAeList();
      String alertMessage="";
        Iterator i = selectedList.iterator();
        String expStatus ="";
        int vioCount=0;
        String lastInspDate="";
        int j=0;
        while(i.hasNext())
        {searchResults result = (searchResults) i.next();
          this.FindEntrStatus(result);
          
          if (expStatus!=null && expStatus.equals("Expired")) {
              alertMessage= result.getResultId()+" permit expired.";
              alertList.add(alertMessage);
          }
            if (vioCount >0) {
                alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                alertList.add(alertMessage);
            } j++;
        } 
        if (j==0) {
            alertList.add("No current AE permit for this facility in the database");
        } 
  //  day care alerts
             selectedList = search.getDayCareList();
              i = selectedList.iterator();
               expStatus ="";
              vioCount=0;
              lastInspDate="";
              while(i.hasNext())
              {searchResults result = (searchResults) i.next();
                this.FindDayCareStatus(result);
                if (expStatus!=null && expStatus.equals("Expired")) {
                    alertMessage= result.getResultId()+" permit expired.";
                    alertList.add(alertMessage);
                }
                  if (vioCount >0) {
                      alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                      alertList.add(alertMessage);
                  }
              } 
  //school alerts
          selectedList = search.getSchoolList();
            i = selectedList.iterator();
             expStatus ="";
            vioCount=0;
            lastInspDate="";
            while(i.hasNext())
            {searchResults result = (searchResults) i.next();
              this.FindSchoolStatus(result);
              if (expStatus!=null && expStatus.equals("Expired")) {
                  alertMessage= result.getResultId()+" permit expired.";
                  alertList.add(alertMessage);
              }
                if (vioCount >0) {
                    alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                    alertList.add(alertMessage);
                }
            } 
  // others alerts
           selectedList = search.getOtherList();
            i = selectedList.iterator();
             expStatus ="";
            vioCount=0;
            lastInspDate="";
             while(i.hasNext())
            {searchResults result = (searchResults) i.next();
              this.FindOtherStatus(result);
              if (expStatus!=null && expStatus.equals("Expired")) {
                  alertMessage= result.getResultId()+" permit expired.";
                  alertList.add(alertMessage);
              }
                if (vioCount >0) {
                    alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                    alertList.add(alertMessage);
                }
               
            } 
       
  // hospital alerts
            selectedList = search.getHospitalList();
             i = selectedList.iterator();
              expStatus ="";
             vioCount=0;
             lastInspDate="";
             while(i.hasNext())
             {searchResults result = (searchResults) i.next();
               this.FindHospitalStatus(result);
               if (expStatus!=null && expStatus.equals("Expired")) {
                   alertMessage= result.getResultId()+" permit expired.";
                   alertList.add(alertMessage);
               }
                 if (vioCount >0) {
                     alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                     alertList.add(alertMessage);
                 }
             } 
  // fw retail
        selectedList = search.getFireworksRetailList();
         i = selectedList.iterator();
          expStatus ="";
         vioCount=0;
         lastInspDate="";
         while(i.hasNext())
         {searchResults result = (searchResults) i.next();
           this.FindFWRetailStatus(result);
           if (expStatus!=null &&expStatus.equals("Expired")) {
               alertMessage= result.getResultId()+" permit expired.";
               alertList.add(alertMessage);
           }
             if (vioCount >0) {
                 alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                 alertList.add(alertMessage);
             }
         } 
  // fw wholesale alerts
        selectedList = search.getFireworksWholesaleList();
         i = selectedList.iterator();
          expStatus ="";
         vioCount=0;
         lastInspDate="";
         while(i.hasNext())
         {searchResults result = (searchResults) i.next();
           this.FindFWwholesaleStatus(result);
           if (expStatus!=null && expStatus.equals("Expired")) {
               alertMessage= result.getResultId()+" permit expired.";
               alertList.add(alertMessage);
           }
             if (vioCount >0) {
                 alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                 alertList.add(alertMessage);
             }
         } 
  // ust status
        selectedList = search.getUstList();
         i = selectedList.iterator();
          expStatus ="";
         vioCount=0;
         lastInspDate="";
         while(i.hasNext())
         {searchResults result = (searchResults) i.next();
           this.FindUstStatus(result);
           if (expStatus!=null && expStatus.equals("Expired")) {
               alertMessage= result.getResultId()+" permit expired.";
               alertList.add(alertMessage);
           }
             if (vioCount >0) {
                 alertMessage= result.getResultId()+" last inspection on "+lastInspDate+ " has " + vioCount +" open";
                 alertList.add(alertMessage);
             }
         } 
  // elevators
        selectedList = search.getElevList();
         i = selectedList.iterator();
          expStatus ="";
         vioCount=0;
         lastInspDate="";
         while(i.hasNext())
         {searchResults result = (searchResults) i.next();
           this.FindElevatorStatus(result);
           if (result.getResultType() !=null && result.getResultType().equals("Expired")) {
               alertMessage="Elevator:"+ result.getResultId()+" permit expired.";
               alertList.add(alertMessage);
           }
             if (vioCount >0) {
                 alertMessage= "Elevator:"+result.getResultId()+" last inspection on "+result.getResultAddress1()+ " has " + result.getResultAddress1() +" open";
                 alertList.add(alertMessage);
             }
         } 
  // bpv
       selectedList = search.getBpvList();
        i = selectedList.iterator();
         expStatus ="";
        vioCount=0;
        lastInspDate="";
        int k=0;
        while(i.hasNext() )
        {searchResults result = (searchResults) i.next();
          
          this.FindBpvStatus(result);
           
          if (result.getResultType() !=null && result.getResultType().equals("Expired")) {  
              alertMessage= "Boiler" +result.getResultId()+" permit expired.";
              alertList.add(alertMessage);
          }
            if (Integer.parseInt(result.getResultAddress1()) >0) {
                alertMessage="Boiler"+ result.getResultId()+" last inspection on "+result.getResultAddress1()+ " has " + result.getResultAddress1() +" open";
                alertList.add(alertMessage);
            } k++;
          
        } 
        search.setAlertList(alertList);
        return alertList;
    }
  public void FindEntrStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_aepermits_status(?,?,?,?) }");
       proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
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
  public void FindDayCareStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_daycare_permits_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindSchoolStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
    CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_school_permits_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindOtherStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_other_permits_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindHospitalStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
    CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_hospital_permits_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindFWRetailStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_fwretail_permits_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindFWwholesaleStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_fwwholesale_perm_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindUstStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
    CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call fire_ust_perm_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindElevatorStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call elevators_status(?,?,?,?) }");
      proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.INTEGER);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      
      proc.execute();
       result.setResultType(proc.getString(2));
       result.setResultAddress1(proc.getString(3));
       result.setResultAddress2(proc.getString(4));
     
     
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
  public void FindBpvStatus (searchResults result) throws SQLException, Exception 
  {
   Connection conn = null;
   CallableStatement proc = null;
   try 
   {  
     conn = getConnection();
     conn.setAutoCommit(false);
      proc = conn.prepareCall("{ call bpv_status(?,?,?,?) }");
       proc.setString(1, result.getResultId());
      proc.registerOutParameter(2,java.sql.Types.VARCHAR);
      proc.registerOutParameter(3,java.sql.Types.VARCHAR);
      proc.registerOutParameter(4,java.sql.Types.VARCHAR);
      proc.execute();
       result.setResultType(proc.getString(2));
      result.setResultAddress1(proc.getString(3));
      result.setResultAddress2(proc.getString(4));
     
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
  public void deleteInspectionNew (fireInspection  inspection) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
  PreparedStatement pstmt1 = null;   
  try 
  {
    conn = getConnection();
    conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.DELETE_IDHS_VIOLATIONS);
      pstmt.clearParameters();
      pstmt.setInt(1,inspection.getInspId());
      pstmt.execute();
      pstmt1 = conn.prepareStatement(InspectionsSQL.DELETE_IDHS_INSPECTION);
      pstmt1.clearParameters();
      pstmt1.setInt(1,inspection.getInspId());
      pstmt1.execute(); 
    conn.commit();
     
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
  
  public void updateInactivateDayCare (String notes,String fireId) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
  try 
  {
    conn = getConnection();
    conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_INACTIVATE_DAY_CARE);
      pstmt.clearParameters();
      pstmt.setString(1,notes);
      pstmt.setString(2,fireId);
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
   public void updateInactivatePlanProject (String projectNumber) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
  try 
  {
    conn = getConnection();
    conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_PLAN_PROJECT_FINAL);
      pstmt.clearParameters();
      pstmt.setInt(1,Integer.parseInt(projectNumber));
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
  public void updateInactivateFireEntr (String notes,String fireId) throws SQLException, Exception 
  {
  
  Connection conn = null;
  PreparedStatement pstmt = null;
  try 
  {
    conn = getConnection();
    conn.setAutoCommit(false);
      pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_INACTIVATE_FIRE_ENTR);
      pstmt.clearParameters();
      pstmt.setString(1,notes);
      pstmt.setString(2,fireId);
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
    public void updateInactivateFireOther (String fireId) throws SQLException, Exception 
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_OTHER_INACTIVE);
        pstmt.clearParameters();
        pstmt.setString(1,fireId);
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
  public void deleteViolations (int  inspId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
    
     pstmt = conn.prepareStatement(InspectionsSQL.DELETE_IDHS_VIOLATIONS_BY_INSPECTION);
     pstmt.clearParameters();
     
    
     pstmt.setInt(1,inspId);
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
  public  List selectInspectionsAeApproval(String facilityId) throws SQLException, Exception 
    {
    
      List list = new ArrayList();
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Connection conn = null;
      try 
      {  conn = getConnection();
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_INSPECTIONS_AE_APPROVAL);
        pstmt.clearParameters();
        pstmt.setString(1,facilityId);
        pstmt.setString(2,facilityId);
        rs = pstmt.executeQuery();
        
        while(rs.next()) 
        {  
          fireInspection  inspection = new fireInspection ();
          inspection.setInspDate(rs.getDate(6));
          inspection.setInspVioPrintDate(rs.getDate(10));
          inspection.setInspAlarm(rs.getString(4));
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
          inspection.setInspInspectorId(rs.getInt(11));
          inspection.setInspUploadError(rs.getString(16));
          inspection.setRecCreatedDate(rs.getDate(17));
          inspection.setFacilityName(rs.getString(18));
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
    public void startFine (fireInspection inspection,ApplicationSecurity security,String fineStartDate) throws SQLException, Exception 
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
       conn = getConnection();
      conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(InspectionsSQL.INSERT_IDHS_FIRE_FINES);
        pstmt.clearParameters();
        pstmt.setString(1,inspection.getInspFacId());
        pstmt.setString(2,security.getUserId());
      if (fineStartDate.length()==10)
      {
        pstmt.setString(3,fineStartDate);  
      }
      else
      {
       pstmt.setString(3,inspection.getInspDateString());
      }
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
    public void EndFine (fireInspection inspection,ApplicationSecurity security,String fineEndDate) throws SQLException, Exception 
    {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    {
       conn = getConnection();
      conn.setAutoCommit(false);
        pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_IDHS_FIRE_FINES);
        pstmt.clearParameters();
        pstmt.setString(3,inspection.getInspFacId());
        pstmt.setString(1,security.getUserId());
        if (fineEndDate.length()==10)
        {
          pstmt.setString(2,fineEndDate);  
        }
        else
        {
         pstmt.setString(2,inspection.getInspDateString());
        }
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
    public void createAEandBUnumbers (fireInspection inspection) throws SQLException, Exception 
    {
     Connection conn = null;
      CallableStatement proc = null;
      try 
     {  
       conn = getConnection();
       conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call create_ae_bu_numbers(?,?,?) }");
       proc.setString(1, inspection.getFacilityCounty());
       proc.setString(2, inspection.getInspFacId());
       proc.registerOutParameter(3,java.sql.Types.VARCHAR);
       proc.execute();
       inspection.setInspFacId(proc.getString(3));
       
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
    public List selectOwnersList(String sql,String ownerLike) throws SQLException, Exception 
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
       if(ownerLike != null) {
         pstmt.setString(1,ownerLike);
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
    public void createAEandBUdata (DfbsOwner owner,fireInspection inspection,String oldNew) throws SQLException, Exception 
    {
     Connection conn = null;
     CallableStatement proc = null;
      try 
     {  
       conn = getConnection();
       conn.setAutoCommit(false);
       if (oldNew.equals("New")) {
         this.insertOwner(owner);
       }
       proc = conn.prepareCall("{ call create_ae_bu_facility_contact(?,?,?) }");
       proc.setInt(1, inspection.getInspId());
       proc.setInt(2, owner.getOwnerId());
       proc.setString(3, oldNew);
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
    public String formatDfbsDate (String formatDate) throws SQLException, Exception 
    {
     Connection conn = null;
     CallableStatement proc = null;
     try 
     {  
       conn = getConnection();
       conn.setAutoCommit(false);
      
       proc = conn.prepareCall("{ call format_date_dfbs(?,?) }");
       proc.setString(1, formatDate);
       proc.registerOutParameter(2,java.sql.Types.VARCHAR);
       proc.execute();
       conn.commit();
       return(proc.getString(2));
       
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
    public void insertOwner(DfbsOwner owner) throws SQLException, Exception 
    {
      Connection conn = null;
      PreparedStatement pstmt = null;
       try 
      {
        conn = getConnection();
        conn.setAutoCommit(false);
        int id = this.getId(conn,DfbsChildcareSQL.SELECT_NEXT_OWNER_ID);
        owner.setOwnerId(id);
        
        pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_OWNER);
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
        int addressId = HsDAO.getId(conn,DfbsChildcareSQL.SELECT_NEXT_ADDRESS_ID);
        pstmt = conn.prepareStatement(DfbsChildcareSQL.INSERT_OWNER_ADDRESS);
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
    public void createIdhsFines (String facilityId,String fineEndDate) throws SQLException, Exception 
    {
     Connection conn = null;
     CallableStatement proc = null;
     try 
     {  
       conn = getConnection();
       conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call create_idhs_fines(?,?) }");
       proc.setString(1, facilityId);
       proc.setString(2, fineEndDate);  
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
    public void deleteIdhsFines (String facilityId,String fineStartDate) throws SQLException, Exception 
    {
     Connection conn = null;
     CallableStatement proc = null;
     try 
     {  
       conn = getConnection();
       conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call delete_idhs_fines(?,?) }");
       proc.setString(1, facilityId);
       proc.setString(2, fineStartDate);  
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
    public DfbsOwner selectOwner(int ownerId) throws SQLException, Exception 
    {
    
     DfbsOwner owner = new DfbsOwner();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
         
     try 
     {
       conn = getConnection();
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_OWNER);
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
    public  List selectFines(String facilityId) throws SQLException, Exception 
      {
      
        List list = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try 
        {  conn = getConnection();
          pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_FIRE_FINES);
          pstmt.clearParameters();
          pstmt.setString(1,facilityId);
           rs = pstmt.executeQuery();
          while(rs.next()) 
          {  
            idhsFines  fine = new  idhsFines ();
            fine.setFineId(rs.getInt(1));
            fine.setFacilityId(rs.getString(2));
            fine.setFineCreatedBy(rs.getString(3));
            fine.setFineStartDate(rs.getDate(4));
            fine.setFineEndedBy(rs.getString(5));
            fine.setFineEndDate(rs.getDate(6));
            if (fine.getFineEndDateString().length()==10)
            {
            this.selectFineDue(fine);}
           list.add(fine);
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
    public idhsFines selectFineDue(idhsFines  fine) throws SQLException, Exception 
    {
    
     DfbsOwner owner = new DfbsOwner();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
         
     try 
     {
       conn = getConnection();
        pstmt = conn.prepareStatement(InspectionsSQL.SELECT_IDHS_FIRE_FINES_DUE);
       pstmt.clearParameters();
       pstmt.setString(1,fine.getFacilityId());
       pstmt.setString(2,fine.getFineEndDateString());  
       rs = pstmt.executeQuery();
       if(rs.next()) 
       {
         fine.setFine(rs.getDouble(1));
         fine.setReceiptNumber(rs.getInt(2));
       }
     
    
       return fine;
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
    public  void deleteFile(int fileId)   throws SQLException, Exception 
    {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try
    { conn = getConnection();
     pstmt = conn.prepareStatement(InspectionsSQL.DELETE_DOCUMENT);
     pstmt.clearParameters();
      pstmt.setInt(1,fileId);
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
    public void updateFireReportSecurity (String inspId) throws SQLException, Exception 
     {
      Connection conn = null;
       PreparedStatement pstmt = null;
       
       try 
       {
         conn = getConnection();
         conn.setAutoCommit(false);
        
           pstmt = conn.prepareStatement(InspectionsSQL.UPDATE_DFBS_FIRE_INSP_SECURITY);
         pstmt.clearParameters();
         pstmt.setString(1,inspId);
        
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
   
  // end //
  }
 