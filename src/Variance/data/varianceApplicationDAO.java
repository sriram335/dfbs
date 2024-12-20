package Variance.data;

import Variance.to.*;


import aepermits.data.DfbsEntrSQL;

import aepermits.to.DfbsEntrPermit;
import aepermits.to.DfbsEntrSpecial;

import hs.data.HsDAO;

import idhsInspections.to.searchResults;

import java.io.InputStream;
import java.io.OutputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import main.to.FileNames;

import org.apache.struts.upload.FormFile;

import util.logging.DHSLogLevel;
import util.logging.Log;

public class varianceApplicationDAO extends HsDAO{
    private static final String CLASS_NAME="varianceApplicationDAO";
    public varianceApplicationDAO() {
       
    }
    public List selectAppList(String listType,String sortType,String sortSQL) throws SQLException, Exception 
    {
      List list = new ArrayList();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
      String lsql=null;
      if(sortType != null && (sortType.equals("listSortCodeDesc")||sortType.equals("listSortCodeAsc")))
      {
     lsql=varianceSQL.SELECT_VARIANCE_APP_LIST_CODE;
      }
     else {
       lsql=varianceSQL.SELECT_VARIANCE_APP_LIST;
     }
     try 
     {
       conn = getConnection();
         if( sortSQL != null) {
           lsql=lsql+sortSQL;  
         }
       if(listType != null && listType.equals("Pre")) {
           if(sortType != null) {
         lsql=lsql+" and VA.variance_number is null AND VA.RECEIVED_DATE >sysdate-365 ";
           }else{
         lsql=lsql+" and VA.variance_number is null AND VA.RECEIVED_DATE >sysdate-365 order by var_id asc ";
           }
       }
         if(listType != null && listType.equals("Post")){
            if(sortType != null) {
             lsql=lsql+" and VA.variance_number is not null AND VA.RECEIVED_DATE >sysdate-365 and VA.var_id in (select va_var_id from var_specific_code where (commission_action_date is null or commission_action_date is not null and commission_status='T') and STAFF_COMMENTS_REC <>'VOID')  "; 
            }else{
       // if(listType != null && listType.equals("Post")) {
             lsql=lsql+" and VA.variance_number is not null AND VA.RECEIVED_DATE >sysdate-365 and VA.var_id in (select va_var_id from var_specific_code where (commission_action_date is null or commission_action_date is not null and commission_status='T') and STAFF_COMMENTS_REC <>'VOID') order by VAR_NUMBER asc  "; 
            }
        }
         //System.out.println("lsql12....:"+lsql);
       if(listType != null && listType.equals("commDate")) {
          lsql=lsql+" and VA.variance_number is not null AND VA.VAR_ID IN  (select VA_VAR_ID from var_specific_code where commission_action_date = (select MAX(commission_action_date) FROM var_specific_code) )  "; 
       }
       if(listType != null && listType.equals("Old")){
          lsql=lsql+" and VA.var_id not in (select va_var_id from var_specific_code where (commission_action_date is not null and commission_status='T') and STAFF_COMMENTS_REC <>'VOID') ";
         }
       if(listType !=null && listType.equals("Old") && sortType != null && sortType.length()>30) {
         lsql=lsql+sortType;
      }
    /*   if( sortSQL != null && sortSQL.length()>30) {
         lsql=lsql+sortSQL;
       
       }*/
           if(sortType != null && sortType.equals("listSortVarAsc")) {
               
             lsql=lsql+" order by 17,1 Asc";
           }
           if(sortType != null && sortType.equals("listSortVarDesc")) {
             lsql=lsql+" order by 17,1 Desc";
           }
       if(sortType != null && sortType.equals("listSortProjAsc")) {
           
         lsql=lsql+" order by 46 Asc";
       }
       if(sortType != null && sortType.equals("listSortProjDesc")) {
         lsql=lsql+" order by 46 Desc";
       }
       if(sortType != null && sortType.equals("listSortCouAsc")) {
           
         lsql=lsql+" order by 47 Asc";
       }
       if(sortType != null && sortType.equals("listSortCouDesc")) {
         lsql=lsql+" order by 47 Desc";
       }
       if(sortType != null && sortType.equals("listSortCodeAsc")) {
           
         lsql=lsql+" order by 48,49 Asc";
       }
       if(sortType != null && sortType.equals("listSortCodeDesc")) {
         lsql=lsql+" order by 48,49 Desc";
       } 
       if(sortType != null && ! sortType.substring(0,4).equals("list")) {
         lsql=lsql+" order by 17,1 Asc";
       }  
         System.out.println("lsql....:"+lsql); 
          pstmt = conn.prepareStatement(lsql);
       pstmt.clearParameters();
        rs = pstmt.executeQuery();
      
       while(rs.next()) 
       {
       

         varianceApplication app = new varianceApplication();
         
           app.setVarAppRecDate(rs.getDate(15));
           app.setVarAppAddress1(rs.getString(5));
           app.setVarAppAddress2(rs.getString(6));
           app.setVarAppBDName(rs.getString(34));
           app.setVarAppCity(rs.getString(7));
           app.setVarAppCodeType(rs.getString(27));
           app.setVarAppCommConditions(rs.getString(11));
           app.setVarAppDesSign(rs.getString(22));
           app.setVarAppEmail(rs.getString(32));
           app.setVarAppFDName(rs.getString(33));
           app.setVarAppFax(rs.getString(31));
           app.setVarAppFirmName(rs.getString(30));
           app.setVarAppLboEmail(rs.getString(37));
           app.setVarAppLboName(rs.getString(35));
           app.setVarAppLboNotified(rs.getString(23));
           app.setVarAppLfoEmail(rs.getString(38));
           app.setVarAppLfoName(rs.getString(36));
           app.setVarAppLfoNotified(rs.getString(24));
           app.setVarAppNumCode(rs.getInt(26));
           app.setVarAppOwnerSign(rs.getString(21));
           app.setVarAppPlanCorrection(rs.getString(14));
           app.setVarAppPlansReceived(rs.getString(25));
           app.setVarProjId(rs.getInt(28));
           //app.setVarAppSBCNum(rs.getInt(29));
           if(rs.getInt(29) != 0)
           app.setVarAppSBCNum(rs.getInt(29));
           else
           app.setVarAppSBCNum(0);
           app.setVarAppState(rs.getString(18));
           app.setVarAppTelephone(rs.getString(16));
           app.setVarAppVarNumber(rs.getString(17));
           app.setVarAppVioIssBy(rs.getString(19));
           app.setVarAppViolation(rs.getString(18));
           app.setVarAppZip(rs.getString(9));
           app.setVarAppZip2(rs.getString(10));
           app.setVarAppFirstName(rs.getString(2));
           app.setVarAppLastName(rs.getString(3));
           app.setVarApppMI(rs.getString(4));
           app.setVarId(rs.getInt(1));
         app.setVarAppHistAffExc(rs.getString(44));
         app.setVarAppHistAffHist(rs.getString(45));
         app.setVarAppHistAffMaj(rs.getString(43));
         app.setVarAppHistAffPhy(rs.getString(41));
         app.setVarAppHistAffPhyComment(rs.getString(42));
         app.setVarAppPhAffirm(rs.getString(39));
         app.setVarAppPhAffirmComment(rs.getString(40)); 
         app.setVarAppLboPhone(rs.getString(46));
         app.setVarAppLfoPhone(rs.getString(47));
           //Added below code to update values from staff entered comments when comm status is B...Dev 03/27/2015
           if(sortType != null && (sortType.equals("listSortCodeDesc")||sortType.equals("listSortCodeAsc"))){
               app.setVarAppStaffCommConditions(rs.getString(52));
           }else{
               app.setVarAppStaffCommConditions(" ");
           }

         selectVarProject(conn,app); 
         if((listType != null && listType.equals("Post")||listType != null && listType.equals("Old")) && app.getVarAppVarNumber() !=null ) {
           app.setFileList(this.selectFileList(app.getVarAppVarNumber(),"Variance"));
         } 
         if(listType != null && listType.equals("Pre")) {
           app.setFileList(this.selectFileList(Integer.toString(app.getVarId()),"Variance"));
         }  
         app.setVarCodesList(this.selectVarCodeList(conn, app)); 
        int appCheck = this.selectVarAppType(app);
        if (appCheck >0) {
          app.setVarAppType("(Online)");
        }
         list.add(app);
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
    public varianceApplication selectApp(int varId) throws SQLException, Exception 
    {
    
        varianceApplication app = new varianceApplication();
     Connection conn = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     try 
     {
       conn = getConnection();
       pstmt = conn.prepareStatement(varianceSQL.SELECT_VARIANCE_APP);
       pstmt.clearParameters();
       pstmt.setInt(1,varId);
       rs = pstmt.executeQuery();
       if(rs.next()) 
       {
          app.setVarAppRecDate(rs.getDate(15));
          app.setVarAppAddress1(rs.getString(5));
          app.setVarAppAddress2(rs.getString(6));
          app.setVarAppBDName(rs.getString(34));
          app.setVarAppCity(rs.getString(7));
          app.setVarAppCodeType(rs.getString(27));
          app.setVarAppCommConditions(rs.getString(11));
          app.setVarAppDesSign(rs.getString(22));
          app.setVarAppEmail(rs.getString(32));
          app.setVarAppFDName(rs.getString(33));
          app.setVarAppFax(rs.getString(31));
          app.setVarAppFirmName(rs.getString(30));
          app.setVarAppLboEmail(rs.getString(37));
          app.setVarAppLboName(rs.getString(35));
          app.setVarAppLboNotified(rs.getString(23));
          app.setVarAppLfoEmail(rs.getString(38));
          app.setVarAppLfoName(rs.getString(36));
          app.setVarAppLfoNotified(rs.getString(24));
          app.setVarAppNumCode(rs.getInt(26));
          app.setVarAppOwnerSign(rs.getString(21));
          app.setVarAppPlanCorrection(rs.getString(14));
          app.setVarAppPlansReceived(rs.getString(25));
          app.setVarProjId(rs.getInt(28));
         // app.setVarAppSBCNum(rs.getInt(29));
          if(rs.getInt(29) != 0)
          app.setVarAppSBCNum(rs.getInt(29));
          else
          app.setVarAppSBCNum(0);
          app.setVarAppState(rs.getString(8));
          app.setVarAppTelephone(rs.getString(16));
          app.setVarAppVarNumber(rs.getString(17));
          app.setVarAppVioIssBy(rs.getString(19));
          app.setVarAppViolation(rs.getString(18));
          app.setVarAppZip(rs.getString(9));
          app.setVarAppZip2(rs.getString(10));
          app.setVarAppFirstName(rs.getString(2));
          app.setVarAppLastName(rs.getString(3));
          app.setVarApppMI(rs.getString(4));
          app.setVarId(rs.getInt(1));
        app.setVarAppHistAffExc(rs.getString(44));
        app.setVarAppHistAffHist(rs.getString(45));
        app.setVarAppHistAffMaj(rs.getString(43));
        app.setVarAppHistAffPhy(rs.getString(41));
        app.setVarAppHistAffPhyComment(rs.getString(42));
        app.setVarAppPhAffirm(rs.getString(39));
        app.setVarAppPhAffirmComment(rs.getString(40));
        app.setVarAppLboPhone(rs.getString(46));
        app.setVarAppLfoPhone(rs.getString(47));
        selectVarProject(conn,app);
        app.setVarCodesList(this.selectVarCodeList(conn, app));
        
      }
     
    
       return app;
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
  public varianceApplication selectAppByVarNum(String varNum) throws SQLException, Exception 
  {
  
      varianceApplication app = new varianceApplication();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
       
   try 
   {
     conn = getConnection();
     pstmt = conn.prepareStatement(varianceSQL.SELECT_VARIANCE_APP_BY_VAR);
     pstmt.clearParameters();
     pstmt.setString(1,varNum);
     rs = pstmt.executeQuery();
     if(rs.next()) 
     {
        app.setVarAppRecDate(rs.getDate(15));
        app.setVarAppAddress1(rs.getString(5));
        app.setVarAppAddress2(rs.getString(6));
        app.setVarAppBDName(rs.getString(34));
        app.setVarAppCity(rs.getString(7));
        app.setVarAppCodeType(rs.getString(27));
        app.setVarAppCommConditions(rs.getString(11));
        app.setVarAppDesSign(rs.getString(22));
        app.setVarAppEmail(rs.getString(32));
        app.setVarAppFDName(rs.getString(33));
        app.setVarAppFax(rs.getString(31));
        app.setVarAppFirmName(rs.getString(30));
        app.setVarAppLboEmail(rs.getString(37));
        app.setVarAppLboName(rs.getString(35));
        app.setVarAppLboNotified(rs.getString(23));
        app.setVarAppLfoEmail(rs.getString(38));
        app.setVarAppLfoName(rs.getString(36));
        app.setVarAppLfoNotified(rs.getString(24));
        app.setVarAppNumCode(rs.getInt(26));
        app.setVarAppOwnerSign(rs.getString(21));
        app.setVarAppPlanCorrection(rs.getString(14));
        app.setVarAppPlansReceived(rs.getString(25));
        app.setVarProjId(rs.getInt(28));
       // app.setVarAppSBCNum(rs.getInt(29));
        if(rs.getInt(29) != 0)
        app.setVarAppSBCNum(rs.getInt(29));
        else
        app.setVarAppSBCNum(0);
        app.setVarAppState(rs.getString(8));
        app.setVarAppTelephone(rs.getString(16));
        app.setVarAppVarNumber(rs.getString(17));
        app.setVarAppVioIssBy(rs.getString(19));
        app.setVarAppViolation(rs.getString(18));
        app.setVarAppZip(rs.getString(9));
        app.setVarAppZip2(rs.getString(10));
        app.setVarAppFirstName(rs.getString(2));
        app.setVarAppLastName(rs.getString(3));
        app.setVarApppMI(rs.getString(4));
        app.setVarId(rs.getInt(1));
      app.setVarAppHistAffExc(rs.getString(44));
      app.setVarAppHistAffHist(rs.getString(45));
      app.setVarAppHistAffMaj(rs.getString(43));
      app.setVarAppHistAffPhy(rs.getString(41));
      app.setVarAppHistAffPhyComment(rs.getString(42));
      app.setVarAppPhAffirm(rs.getString(39));
      app.setVarAppPhAffirmComment(rs.getString(40));
      app.setVarAppLboPhone(rs.getString(46));
      app.setVarAppLfoPhone(rs.getString(47));
      selectVarProject(conn,app);
    }
   
  
     return app;
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
    public void insertApp(varianceApplication app,DfbsOwner owner) throws SQLException, Exception 
    {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try 
      {
        conn = getConnection();
        conn.setAutoCommit(false);
        int id = this.getId(conn,varianceSQL.SELECT_NEXT_VAR_ID);
        int projId = HsDAO.getId(conn,varianceSQL.SELECT_NEXT_PROJECT_ID);
        app.setVarProjId(projId);
        app.setVarId(id);
        insertVarProject(conn,app,owner);
        pstmt = conn.prepareStatement(varianceSQL.INSERT_VARIANCE_APP);
        pstmt.clearParameters();
          pstmt.setString(5,app.getVarAppAddress1());
          pstmt.setString(6,app.getVarAppAddress2());
          pstmt.setString(34,app.getVarAppBDName());
          pstmt.setString(7,app.getVarAppCity());
          pstmt.setString(27,app.getVarAppCodeType());
          pstmt.setString(11,app.getVarAppCommConditions());
          pstmt.setString(22,app.getVarAppDesSign());
          pstmt.setString(32,app.getVarAppEmail());
          pstmt.setString(33,app.getVarAppFDName());
          pstmt.setString(31,app.getVarAppFax());
          pstmt.setString(30,app.getVarAppFirmName());
          pstmt.setString(37,app.getVarAppLboEmail());
          pstmt.setString(38,app.getVarAppLboPhone());
          pstmt.setString(35,app.getVarAppLboName());
          pstmt.setString(23,app.getVarAppLboNotified());
          pstmt.setString(39,app.getVarAppLfoEmail());
          pstmt.setString(40,app.getVarAppLfoPhone());
          pstmt.setString(36,app.getVarAppLfoName());
          pstmt.setString(24,app.getVarAppLfoNotified());
          pstmt.setInt(26,app.getVarAppNumCode());
          pstmt.setString(21,app.getVarAppOwnerSign());
          pstmt.setString(14,app.getVarAppPlanCorrection());
          pstmt.setString(25,app.getVarAppPlansReceived());
          pstmt.setInt(28,(app.getVarProjId()));
          if(app.getVarAppSBCNum() != null)
                    pstmt.setInt(29,(app.getVarAppSBCNum().intValue()));
          else
                    pstmt.setInt(29,(0));
          pstmt.setString(8,app.getVarAppState());
          pstmt.setString(16,app.getVarAppTelephone());
          pstmt.setString(17,app.getVarAppVarNumber());
          pstmt.setString(19,app.getVarAppVioIssBy());
          pstmt.setString(18,app.getVarAppViolation());
          if (app.getVarAppZip()==null ||app.getVarAppZip().equals(""))
          {
            app.setVarAppZip("00000");
           }
          pstmt.setInt(9,Integer.parseInt(app.getVarAppZip()));
          pstmt.setString(10,app.getVarAppZip2());
          pstmt.setString(2,app.getVarAppFirstName());
          pstmt.setString(3,app.getVarAppLastName());
          pstmt.setString(4,app.getVarApppMI());
          pstmt.setInt(1,app.getVarId());
        pstmt.setString(15,app.getVarAppHistAffExc());
        pstmt.setString(20,app.getVarAppHistAffHist());
        pstmt.setString(45,app.getVarAppHistAffMaj());
        pstmt.setString(43,app.getVarAppHistAffPhy());
        pstmt.setString(44,app.getVarAppHistAffPhyComment());
        pstmt.setString(41,app.getVarAppPhAffirm());
        pstmt.setString(42,app.getVarAppPhAffirmComment());
        pstmt.setString(12,null);
        pstmt.setString(13,null);
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
    public void updateApp(varianceApplication app) throws SQLException, Exception 
    {
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      PreparedStatement pstmt1 = null;
     
      try 
      {
        conn = getConnection();
        conn.setAutoCommit(false);
        
        pstmt = conn.prepareStatement(varianceSQL.UPDATE_VARIANCE_APP);
        pstmt.clearParameters();
          pstmt.setString(15,app.getVarAppRecDateString());
          pstmt.setString(5,app.getVarAppAddress1());
          pstmt.setString(6,app.getVarAppAddress2());
          pstmt.setString(34,app.getVarAppBDName());
          pstmt.setString(7,app.getVarAppCity());
          pstmt.setString(27,app.getVarAppCodeType());
          pstmt.setString(11,app.getVarAppCommConditions());
          pstmt.setString(22,app.getVarAppDesSign());
          pstmt.setString(32,app.getVarAppEmail());
          pstmt.setString(33,app.getVarAppFDName());
          pstmt.setString(31,app.getVarAppFax());
          pstmt.setString(30,app.getVarAppFirmName());
          pstmt.setString(37,app.getVarAppLboEmail());
          pstmt.setString(45,app.getVarAppLboPhone());
          pstmt.setString(35,app.getVarAppLboName());
          pstmt.setString(23,app.getVarAppLboNotified());
          pstmt.setString(1,app.getVarAppLfoEmail());
          pstmt.setString(46,app.getVarAppLfoPhone());
          pstmt.setString(36,app.getVarAppLfoName());
          pstmt.setString(24,app.getVarAppLfoNotified());
          pstmt.setInt(26,app.getVarAppNumCode());
          pstmt.setString(21,app.getVarAppOwnerSign());
          pstmt.setString(14,app.getVarAppPlanCorrection());
          pstmt.setString(25,app.getVarAppPlansReceived());
          pstmt.setInt(28,app.getVarProjId());
          // pstmt.setInt(29,app.getVarAppSBCNum());
        /*  if(app.getVarAppSBCNum() >0)
              pstmt.setInt(29,(app.getVarAppSBCNum()));
          else
              pstmt.setString(29,"");
*/
        if(app.getVarAppSBCNum() != null)
               pstmt.setInt(29,(app.getVarAppSBCNum().intValue()));
        else
               pstmt.setInt(29,(0));
          pstmt.setString(8,app.getVarAppState());
          pstmt.setString(16,app.getVarAppTelephone());
          pstmt.setString(17,app.getVarAppVarNumber());
          pstmt.setString(19,app.getVarAppVioIssBy());
          pstmt.setString(18,app.getVarAppViolation());
          pstmt.setString(9,app.getVarAppZip());
          pstmt.setString(10,app.getVarAppZip2());
          pstmt.setString(2,app.getVarAppFirstName());
          pstmt.setString(3,app.getVarAppLastName());
          pstmt.setString(4,app.getVarApppMI());
          pstmt.setInt(47,app.getVarId());
        pstmt.setString(43,app.getVarAppHistAffExc());
        pstmt.setString(43,app.getVarAppHistAffExc());
        pstmt.setString(44,app.getVarAppHistAffHist());
        pstmt.setString(42,app.getVarAppHistAffMaj());
        pstmt.setString(40,app.getVarAppHistAffPhy());
        pstmt.setString(41,app.getVarAppHistAffPhyComment());
        pstmt.setString(38,app.getVarAppPhAffirm());
        pstmt.setString(39,app.getVarAppPhAffirmComment());
        pstmt.setString(12,null);
        pstmt.setString(13,null);
        pstmt.setString(20,null);
        pstmt.execute();
          Log.log("varAppDAO-UserId::", DHSLogLevel.INFO, CLASS_NAME, "updateApp:", "app.:"+app); 
        updateVarProject(conn,app);
          Log.log("varAppDAO-UserId::", DHSLogLevel.INFO, CLASS_NAME, "updateApp:", "app.getVarId.:"+app.getVarId()); 
          Log.log("varAppDAO-UserId::", DHSLogLevel.INFO, CLASS_NAME, "updateApp:", "app.getVarAppVarNumber.:"+app.getVarAppVarNumber()); 
        if (app.getVarAppVarNumber()!=null && app.getVarAppVarNumber().length()>3)
        {
        pstmt1 = conn.prepareStatement(varianceSQL.UPDATE_DOCUMENT_NAME);
        pstmt1.clearParameters();
        pstmt1.setString(2,Integer.toString(app.getVarId()));
        pstmt1.setString(1,app.getVarAppVarNumber());
        pstmt1.setString(3,"Variance");
         pstmt1.execute();    
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
  public void  uploadFile(FormFile oneFile,String IdNumber, String IdType) throws SQLException, Exception
  { 
    Connection conn = null;
    PreparedStatement pstmt = null;
    try 
    
    {  
       conn = getConnection();
       int id = this.getId(conn,varianceSQL.SELECT_DOCUMENT_ID);
       byte[] byteArray=oneFile.getFileData();
       pstmt =conn.prepareStatement(varianceSQL.UPLOAD_DOCUMENT);
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

    public List selectFileList(String IdNumber,String IdType) throws SQLException, Exception 
  {
    List list = new ArrayList();
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    try 
    { 
   
      conn = getConnection();
     
     
      pstmt = conn.prepareStatement(varianceSQL.SELECT_DOCUMENT_NAMES_ALL);
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
  
  public int downLoad(OutputStream os,int fileId,String fileName)  throws SQLException, Exception
  {  Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
     int len_total = 0; 
   try {
   String sql = varianceSQL.SELECT_DOCUMENT; 
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
   {   
       os.write(buf,0,len);
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
  private static void selectVarProject(Connection conn, varianceApplication app) 
  throws SQLException, Exception 
  {
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(varianceSQL.SELECT_VARIANCE_PROJ);
      pstmt.clearParameters();
      pstmt.setInt(1,app.getVarProjId());
      rs = pstmt.executeQuery();
      if(rs.next()) 
      {
        app.setVarProjAddress1(rs.getString(5));
        app.setVarProjAddress2(rs.getString(6));
        app.setVarProjCity(rs.getString(7));
        app.setVarProjCounty(rs.getString(8));
        app.setVarProjName(rs.getString(2));
        app.setVarProjState(rs.getString(9));
        app.setVarProjType(rs.getString(4));
        app.setVarProjZip(rs.getString(10));
        app.setVarProjZip2(rs.getString(11));
        app.setVarProjId(rs.getInt(1));
        app.setVarProjOwnerId(rs.getInt(12));
        app.setVarProjStatus(rs.getString(3));
      }
      
      }
      finally
      {
      try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
      }
  }  
  private static void insertVarProject(Connection conn, varianceApplication app,DfbsOwner owner) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    
    try 
    {
      pstmt = conn.prepareStatement(varianceSQL.INSERT_VARIANCE_PROJ);
      pstmt.clearParameters();
      pstmt.setString(5,app.getVarProjAddress1());
      pstmt.setString(6,app.getVarProjAddress2());
      pstmt.setString(7,app.getVarProjCity());
      pstmt.setString(8,app.getVarProjCounty());
      pstmt.setString(2,app.getVarProjName());
      pstmt.setString(9,app.getVarProjState());
      pstmt.setString(4,app.getVarProjType());
      pstmt.setString(10,app.getVarProjZip());
      pstmt.setString(11,app.getVarProjZip2());
      pstmt.setInt(1,app.getVarProjId());
      pstmt.setInt(12,app.getVarProjOwnerId());
      pstmt.setString(3,app.getVarProjStatus());  
      pstmt.execute();
      pstmt1 = conn.prepareStatement(varianceSQL.UPDATE_DOCUMENT_NAME);
      pstmt1.clearParameters();
      pstmt1.setString(1,Integer.toString(app.getVarId()));
      pstmt1.setString(2,owner.getOwnerPhone());
      pstmt1.setString(3,"Variance");
       pstmt1.execute();   
      
    } 
    finally 
    {
     try {
        pstmt1.close();
        pstmt.close();
      } catch (Exception e) {}
    }
  }  
  
   private static void updateVarProject(Connection conn, varianceApplication app) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    
    try 
    {
      pstmt = conn.prepareStatement(varianceSQL.UPDATE_VARIANCE_PROJ);
      pstmt.clearParameters();
      
      
      pstmt.setString(5,app.getVarProjAddress1());
      pstmt.setString(6,app.getVarProjAddress2());
      pstmt.setString(7,app.getVarProjCity());
      pstmt.setString(8,app.getVarProjCounty());
      pstmt.setString(2,app.getVarProjName());
      pstmt.setString(9,app.getVarProjState());
      pstmt.setString(4,app.getVarProjType());
      pstmt.setString(10,app.getVarProjZip());
      pstmt.setString(11,app.getVarProjZip2());
      pstmt.setInt(12,app.getVarProjId());
      pstmt.setInt(1,app.getVarProjOwnerId());
      pstmt.setString(3,app.getVarProjStatus());  
      pstmt.execute();
    } 
    finally 
    {
     try {
        
        pstmt.close();
      } catch (Exception e) {}
    }
}
  private static List selectVarCodeList(Connection conn, varianceApplication app) 
  throws SQLException, Exception 
  {
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    try 
    {
      pstmt = conn.prepareStatement(varianceSQL.SELECT_VAR_CODE_LIST);
      pstmt.clearParameters();
      pstmt.setInt(1,app.getVarId());
      rs = pstmt.executeQuery();
        List list = new ArrayList();
        
     while(rs.next()) 
      { VarCodeDetail code = new VarCodeDetail();
        code.setVarCodeId(rs.getInt(1));
        code.setVarId(rs.getInt(6));
        code.setVarCode(rs.getString(2));
        code.setVarCodeName(rs.getString(3));
        code.setVarCommDate(rs.getDate(7));
        code.setVarCommPrintDate(rs.getDate(9));
        code.setVarCommStatus(rs.getString(8));
        code.setVarEdition(rs.getString(5));
        code.setVarNatureNonComp(rs.getString(4));
        code.setVarAppHistAffExc(rs.getString(15));
        code.setVarAppHistAffHist(rs.getString(16));
        code.setVarAppHistAffMaj(rs.getString(14));
        code.setVarAppHistAffPhy(rs.getString(12));
        code.setVarAppHistAffPhyComment(rs.getString(13));
        code.setVarAppPhAffirm(rs.getString(10));
        code.setVarAppPhAffirmComment(rs.getString(11));
        code.setStaffComments(rs.getString(17));
        code.setStaffCommentsRec(rs.getString(18));
        code.setVarReleaseFlag(rs.getString(19)); 
        code.setVarAppStaffCommConditions(rs.getString(20));
        list.add(code);
      }
        return (list);
      }
      finally
      {
      try {
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
      }
  }  
 public  VarCodeDetail selectVarCode( int varId) 
  throws SQLException, Exception 
  {
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(varianceSQL.SELECT_VAR_CODE);
      pstmt.clearParameters();
      pstmt.setInt(1,varId);
      rs = pstmt.executeQuery();
        VarCodeDetail code = new VarCodeDetail();
     while(rs.next()) 
      {  
        code.setVarCodeId(rs.getInt(1));
        code.setVarId(rs.getInt(6));
        code.setVarCode(rs.getString(2));
        code.setVarCodeName(rs.getString(3));
        code.setVarCommDate(rs.getDate(7));
        code.setVarCommPrintDate(rs.getDate(9));
        code.setVarCommStatus(rs.getString(8));
        code.setVarEdition(rs.getString(5));
        code.setVarNatureNonComp(rs.getString(4));
        code.setVarAppHistAffExc(rs.getString(15));
        code.setVarAppHistAffHist(rs.getString(16));
        code.setVarAppHistAffMaj(rs.getString(14));
        code.setVarAppHistAffPhy(rs.getString(12));
        code.setVarAppHistAffPhyComment(rs.getString(13));
        code.setVarAppPhAffirm(rs.getString(10));
        code.setVarAppPhAffirmComment(rs.getString(11));
        code.setStaffComments(rs.getString(17));
        code.setStaffCommentsRec(rs.getString(18));
        code.setVarReleaseFlag(rs.getString(19));
        code.setVarAppStaffCommConditions(rs.getString(20)); 
      }
      return(code);
      }
      finally
      {
      try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
      }
  }  
  public void insertVarCode( VarCodeDetail code,varianceApplication app) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    {conn = getConnection();
      int id = this.getId(conn,"SELECT specific_id.nextval FROM DUAL");
      code.setVarCodeId(id);
      pstmt = conn.prepareStatement(varianceSQL.INSERT_VAR_CODE);
      pstmt.clearParameters();
      
      pstmt.setInt(1,code.getVarCodeId());
      pstmt.setInt(6,app.getVarId());
      pstmt.setString(2,code.getVarCode());
      pstmt.setString(3,code.getVarCodeName());
       pstmt.setString(5,code.getVarEdition());
      pstmt.setString(4,code.getVarNatureNonComp());
      pstmt.setString(12,code.getVarAppHistAffExc());
      pstmt.setString(13,code.getVarAppHistAffHist());
      pstmt.setString(11,code.getVarAppHistAffMaj());
      pstmt.setString(9,code.getVarAppHistAffPhy());
      pstmt.setString(10,code.getVarAppHistAffPhyComment());
      pstmt.setString(7,code.getVarAppPhAffirm());
      pstmt.setString(8,code.getVarAppPhAffirmComment());
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
  
   public void updateVarCode( VarCodeDetail code) 
  throws SQLException, Exception 
  {
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    {conn = getConnection();
       pstmt = conn.prepareStatement(varianceSQL.UPDATE_VAR_CODE);
      pstmt.clearParameters();
      pstmt.setInt(19,code.getVarCodeId());
      pstmt.setString(2,code.getVarCode());
      pstmt.setString(6,code.getVarCommDateString());
      pstmt.setString(7,code.getVarCommPrintDateString());
      pstmt.setString(3,code.getVarCodeName());
      pstmt.setString(1,code.getVarCommStatus());
      pstmt.setString(5,code.getVarEdition());
      pstmt.setString(4,code.getVarNatureNonComp());
      pstmt.setString(13,code.getVarAppHistAffExc());
      pstmt.setString(14,code.getVarAppHistAffHist());
      pstmt.setString(12,code.getVarAppHistAffMaj());
      pstmt.setString(10,code.getVarAppHistAffPhy());
      pstmt.setString(11,code.getVarAppHistAffPhyComment());
      pstmt.setString(8,code.getVarAppPhAffirm());
      pstmt.setString(9,code.getVarAppPhAffirmComment());
      pstmt.setString(15,code.getStaffComments());
      pstmt.setString(16,code.getStaffCommentsRec());  
      pstmt.setString(17,code.getVarReleaseFlag());  
      pstmt.setString(18,code.getVarAppStaffCommConditions()); 
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
  public double selectFeesMapping(int varNumCode,String varNumber, int varId) throws SQLException, Exception 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    int feeDue=0;
    double total=0;
    String feeCodeType="";
   try 
    {
      conn = getConnection();
      pstmt = conn.prepareStatement(varianceSQL.SELECT_FEES_VARIANCE);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
      
      while(rs.next()) 
      {
      feeCodeType =rs.getString(2);
          if (feeCodeType.equals("VAR_FEE_APP")) {
            total=total +rs.getDouble(1);
          }
        if (feeCodeType.equals("VAR_FEE_SC")) {
          total=total +rs.getDouble(1);
        }
        if (feeCodeType.equals("VAR_FEE_AD")&&varNumCode>0) {
          total=total +(rs.getDouble(1)*(varNumCode));
        }
      
      }
      if(varNumber !=null || varId >0)
      {
      pstmt1 = conn.prepareStatement(varianceSQL.SELECT_DUES_VARIANCE_IN_ACCT);
      pstmt1.clearParameters();
        pstmt1.setInt(1,varId);
        pstmt1.setString(2,varNumber);
      rs1 = pstmt1.executeQuery();
      while(rs1.next()) 
      {
        feeDue= feeDue+ rs1.getInt(1);
      }
      }
        return (total-feeDue);
    } 
   
    finally 
    {
     try {
        rs.close();
        rs1.close();
        conn.close();
        pstmt.close();
        pstmt1.close();
      } catch (Exception e) {}
    }
    
   
  }
  
  
  
  
  public void insertPermitTransaction(varianceApplication varApp, DfbsOwner owner,int receiptId) throws SQLException, Exception
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try 
    {
      conn = getConnection();
      conn.setAutoCommit(false);
      
      int id = this.getId(conn,varianceSQL.SELECT_NEXT_TRANSACTION_ID);
      pstmt = conn.prepareStatement(varianceSQL.INSERT_PERMIT_TRANSACTION);
      pstmt.clearParameters();
      pstmt.setInt(1,id);
      pstmt.setDouble(2,varApp.getAmount());
      StringBuffer sb = new StringBuffer();
      pstmt.setString(3,"Variance Application Fee: " +varApp.getVarId()+":CONFIRMATION:"+receiptId);
      pstmt.setInt(4,receiptId);
      pstmt.setString(5,Integer.toString(varApp.getVarId()));
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
  public void selectPlanData( int sbcProjectNumber,DfbsOwner owner,varDesigner des,varianceApplication varApp) 
   throws SQLException, Exception 
   {
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     try 
     { conn = getConnection();
       pstmt = conn.prepareStatement(varianceSQL.SELECT_PLAN_DATA);
       pstmt.clearParameters();
       pstmt.setInt(1,sbcProjectNumber);
       rs = pstmt.executeQuery();
      while(rs.next()) 
       {  
         owner.setOwnerDBA(rs.getString(8));
         owner.setOwnerLastName(rs.getString(9));
         owner.setOwnerFirstName(rs.getString(10));
         owner.setOwnerMI(rs.getString(11));
         owner.setOwnerAddress1(rs.getString(12));
         owner.setOwnerAddress2(rs.getString(13));
         owner.setOwnerCity(rs.getString(14));
         owner.setOwnerStateId(rs.getInt(15));
         owner.setOwnerZip(rs.getString(16));
         owner.setOwnerZip2(rs.getString(17));
         owner.setOwnerPhone(rs.getString(18));
         owner.setOwnerFax(rs.getString(19));
         owner.setOwnerEmail(rs.getString(20));  
         varApp.setVarProjName(rs.getString(1));
         varApp.setVarProjAddress1(rs.getString(2));
         varApp.setVarProjAddress2(rs.getString(3));
         varApp.setVarProjCity(rs.getString(4));
         varApp.setVarProjState(rs.getString(5));
         varApp.setVarProjZip(rs.getString(6));
         varApp.setVarProjZip2(rs.getString(7));
         varApp.setVarProjCounty(rs.getString(33));
         varApp.setVarAppBDName(rs.getString(34));
         varApp.setVarAppFDName(rs.getString(35)); 
         des.setDesDBA(rs.getString(21));
         des.setDesLastName(rs.getString(28));
         des.setDesFirstName(rs.getString(29));
        des.setDesAddress1(rs.getString(22));
         des.setDesAddress2(rs.getString(23));
         des.setDesCity(rs.getString(24));
         des.setDesState(rs.getString(25));
         des.setDesZip(rs.getString(26));
         des.setDesZip2(rs.getString(27));
         des.setDesPhone(rs.getString(31));
         des.setDesEmail(rs.getString(30)); 
         
       }
       }
       finally
       {
       try {conn.close();
         rs.close();
         pstmt.close();
       } catch (Exception e) {}
       }
   } 
 public  List selectStdVarCodeList() 
  throws SQLException, Exception 
  {
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Connection conn = null;
    try 
    { conn = getConnection();
      pstmt = conn.prepareStatement(varianceSQL.VAR_CODE_TYPE_OPTIONS);
      pstmt.clearParameters();
      rs = pstmt.executeQuery();
        List list = new ArrayList();
      while(rs.next()) 
      { VarCodeDetail code = new VarCodeDetail();
       code.setVarCode(rs.getString(2));
        list.add(code);
      }
        return (list);
      }
      finally
      {
      try {conn.close();
        rs.close();
        pstmt.close();
      } catch (Exception e) {}
      }
  }  
  public List selectCommDates() throws SQLException, Exception 
  {
  List list = new ArrayList();
  Connection conn = null;
  ResultSet rs = null;
  PreparedStatement pstmt = null;
  
  try 
  { 
    conn = getConnection();
    pstmt = conn.prepareStatement(varianceSQL.SELECT_VAR_COMM_DATES);
     pstmt.clearParameters();
    rs = pstmt.executeQuery();
    while(rs.next()) 
    { 
      FileNames names = new FileNames();
      names.setFileName(rs.getString(1));
      names.setFileType(rs.getString(2));
      names.setFilePlanType(rs.getString(3));
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
  public List selectEmailList(String county) throws SQLException, Exception 
  {
    List list = new ArrayList();
   Connection conn = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   
   String lsql=varianceSQL.SELECT_EMAIL_LIST;
   try 
   {
     conn = getConnection();
     
        pstmt = conn.prepareStatement(lsql);
     pstmt.clearParameters();
     pstmt.setString(1,county);
     pstmt.setString(2,county);
     
      rs = pstmt.executeQuery();
     while(rs.next()) 
     {searchResults result =new searchResults();
      result.setResultCity (rs.getString(2));
      result.setResultAddress1 (rs.getString(1));
      result.setResultAddress2 (rs.getString(3));
      if (result.getResultCity() !=null && result.getResultCity().length()>5)
      {
      list.add(result);
      }
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
  public void selectNextVarianceList(searchResults result) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
       proc = conn.prepareCall("{ call var_next_number(?,?,?) }");
       proc.registerOutParameter(1,java.sql.Types.VARCHAR);
       proc.registerOutParameter(2,java.sql.Types.VARCHAR);
       proc.registerOutParameter(3,java.sql.Types.VARCHAR);
       proc.execute();
        result.setResultType(proc.getString(1));
        result.setResultAddress1(proc.getString(2));
        result.setResultAddress2(proc.getString(3));
      
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
  public void updatePermitTransaction(String varId,String varNumber) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
     pstmt = conn.prepareStatement(varianceSQL.UPDATE_PERMIT_TRANSACTION);
     pstmt.clearParameters();
     pstmt.setString(3,varId);
    pstmt.setString(2,varNumber);
     pstmt.setString(1,varNumber);
     
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
  public  int selectVarAppVerify(String ownerEmail,String varianceNumber,String receiptNumber, int ownerId) 
   throws SQLException, Exception 
   {
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     int verifyApp=0;
     try 
     { conn = getConnection();
       pstmt = conn.prepareStatement(varianceSQL.SELECT_VAR_APP_VERIFICATION);
       pstmt.clearParameters();
         pstmt.setString(3,ownerEmail);
         pstmt.setString(1,varianceNumber);
         pstmt.setInt(2,Integer.parseInt(varianceNumber));
         pstmt.setString(4,receiptNumber);
         pstmt.setInt(5,ownerId);
       rs = pstmt.executeQuery();
       while(rs.next()) 
       { 
          verifyApp=   rs.getInt(1);
        
       }
         return (verifyApp);
       }
       finally
       {
       try {conn.close();
         rs.close();
         pstmt.close();
       } catch (Exception e) {}
       }
   } 
  public void selectLboDetails(searchResults result,String projectCity, String projCounty,String projCityLimits,
                               String projStateOwned,String projZip) throws SQLException, Exception 
  {
    Connection conn = null;
    CallableStatement proc = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    ResultSet rs1 = null;
    PreparedStatement pstmt1 = null;
    try 
    {  
      conn = getConnection();
      conn.setAutoCommit(false);
             pstmt = conn.prepareStatement(varianceSQL.SELECT_COUNTY_CODE);
             pstmt.clearParameters();
             pstmt.setString(1,projCounty);
            rs = pstmt.executeQuery();
             while(rs.next()) 
             { 
                projCounty=   rs.getString(1);
              
             }
       proc = conn.prepareCall("{ call plan_find_dbc_lbo_new(?,?,?,?,?,?,?,?,?) }");
       proc.setString(1, projStateOwned);
       proc.setString(2, projCounty);
       proc.setString(3, projectCity);
       proc.setString(4,projCityLimits);
       proc.setString(5, projZip); 
       proc.registerOutParameter(6,java.sql.Types.INTEGER);
       proc.registerOutParameter(7,java.sql.Types.INTEGER);
       proc.registerOutParameter(8,java.sql.Types.VARCHAR);
      proc.registerOutParameter(9,java.sql.Types.VARCHAR); 
       proc.execute();
        result.setResultId(Integer.toString(proc.getInt(6)));
      pstmt1 = conn.prepareStatement(varianceSQL.SELECT_INSPECTOR_BY_ID);
      pstmt1.clearParameters();
      pstmt1.setInt(1,proc.getInt(6));
      rs1 = pstmt1.executeQuery();
      while(rs1.next()) 
      { 
        result.setResultType( rs1.getString(3));
        result.setResultAddress1( rs1.getString(1));
        result.setResultAddress2( rs1.getString(2));
      }
      
    } catch (Exception ex) 
    {
      conn.rollback();
      throw new Exception(ex.getMessage());
    }
    finally 
    {
     try { proc.close();
        conn.close();
        rs.close();
        pstmt.close();
        rs1.close();
        pstmt1.close();
      } catch (Exception e) {}
    }
  }
  public void updateLfoEmail(String varLfoEmail,String varLboEmail,int varId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
     
     pstmt = conn.prepareStatement(varianceSQL.UPDATE_LFO_EMAIL);
     pstmt.clearParameters();
     pstmt.setString(1,varLfoEmail);
     pstmt.setString(2,"N"); 
     pstmt.setString(3,varLboEmail);
     pstmt.setString(4,"N"); 
     pstmt.setInt(5,varId);
    
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
  public void updateLfoLboNotified(String lboLfo,int varId) throws SQLException, Exception 
  {
   Connection conn = null;
   PreparedStatement pstmt = null;
   
   try 
   {
     conn = getConnection();
     conn.setAutoCommit(false);
       if(lboLfo.equals("LFO")){
     pstmt = conn.prepareStatement(varianceSQL.UPDATE_LFO_CONFIRMATION );
       }
     if(lboLfo.equals("LBO")){
     pstmt = conn.prepareStatement(varianceSQL.UPDATE_LBO_CONFIRMATION );
     }
     pstmt.clearParameters();
     pstmt.setString(1,"A"); 
     pstmt.setInt(2,varId);
    
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
  public  int selectVarAppType( varianceApplication varApp) 
   throws SQLException, Exception 
   {
     ResultSet rs = null;
     PreparedStatement pstmt = null;
     Connection conn = null;
     int appCheck =0;
     try 
     { conn = getConnection();
       pstmt = conn.prepareStatement(varianceSQL.SELECT_VARIANCE_TYPE);
       pstmt.clearParameters();
       pstmt.setInt(1,varApp.getVarId());
       pstmt.setString(2,varApp.getVarAppVarNumber());  
       rs = pstmt.executeQuery();
       
      while(rs.next()) 
       {  
         appCheck =(rs.getInt(1));
        
       }
       return(appCheck);
       }
       finally
       {
       try {conn.close();
         rs.close();
         pstmt.close();
       } catch (Exception e) {}
       }
   }  
}
