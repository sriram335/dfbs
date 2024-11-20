package main.data;
import hs.data.*;

public class DfbsReportsSQL 
{
     public final static String INSERT_ERROR_LOG =
    " INSERT INTO  online_apps_log_errors VALUES(?,?,SYSDATE,?)";
    /* SEQUENCE SQL */
   
   
    public final static String SELECT_MAG_CONTACTS=
    " SELECT canned_code,description,abbreviation,section from DFBS_CODE " +
    " WHERE canned_code in('MAGAZINE_CONTACT1','MAGAZINE_CONTACT2')";
     
      
      // FROM ,MAGAZINE
     
     
      public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
  
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,?)";
    public final static String SELECT_DOCUMENT_NAMES_ALL =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? order by 3";
     public final static String SELECT_DOCUMENT_NAMES_USER =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? AND DOCUMENT_UPLOADED_BY=? order by 3";
     public final static String SELECT_DOCUMENT =
    " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE,DOCUMENT_ID from DFBS_DOCUMENTS where DOCUMENT_ID=?";
    public final static String SELECT_DOCUMENT_NAME =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_ID " +
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? and DOCUMENT_UPLOAD_DATE >sysdate-3";
    public final static String UPDATE_DOCUMENT_NAME =
    " UPDATE DFBS_DOCUMENTS set DOCUMENT_CONNECTOR=? where DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? ";
     public final static String SELECT_DOCUMENT_COUNT =
    " SELECT COUNT(*) " +
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? and DOCUMENT_UPLOAD_DATE >sysdate-1";
    
    public final static String SELECT_DFBS_DIVISION_OPTIONS =
" SELECT '911','Boiler and Pressure Vessel' division_name FROM DUAL UNION " +
" SELECT '99','Emergency Medical Services' division_name FROM DUAL UNION " +
" SELECT '91','Amusement Ride Inspections' division_name FROM DUAL UNION " +
" SELECT '93','Elevator Inspections' division_name FROM DUAL UNION " +
" SELECT '96','Fire Marshal Inspections' division_name FROM DUAL UNION " +
" SELECT '98','Variance Tracking' division_name FROM DUAL UNION " +
" SELECT '913','Code Enforcement' division_name FROM DUAL UNION " +
" SELECT '94','Plan Review' division_name FROM DUAL UNION " +
" SELECT '914','Accounting' division_name FROM DUAL ";
  public final static String SELECT_REPORT_NAMES =
      "SELECT DISTINCT REPORT_NAME,REPORT_CODE FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=? AND PARAMETER1=?";
       public final static String SELECT_REPORT_GROUP =
      "SELECT DISTINCT PARAMETER1 FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=? ";   
} 

