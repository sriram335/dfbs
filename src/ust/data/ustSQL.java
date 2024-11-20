package ust.data;

public class ustSQL {
      public final static String SELECT_UST  = " SELECT   UST_ID,ADDRESS_FLAG,COMPANY_JURISDICTION, UST_ADDRESS1," +
      " UST_ADDRESS2,CITY,STATE,ZIP,ZIP2,TELEPHONE_HOME, TELEPHONE_WORK,APPLICANT_FAX, APPLICANT_E_MAIL,"+
      " APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME, APPLICANT_MI FROM FIRE_UST WHERE UST_ID=?";
      public final static String SELECT_UST_LIST  = " SELECT  UST_ID, ADDRESS_FLAG,COMPANY_JURISDICTION, UST_ADDRESS1," +
      " UST_ADDRESS2,CITY,STATE,ZIP,ZIP2,TELEPHONE_HOME, TELEPHONE_WORK,APPLICANT_FAX, APPLICANT_E_MAIL,"+
      " APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME, APPLICANT_MI ,TO_CHAR(B.ISSUE_DATE,'mm/dd/yyyy'),TO_CHAR(B.EXPIRATION_DATE,'mm/dd/yyyy'),"+
      " DECODE(B.CERT_STATUS,'1','Approved','2','Denied','3','In Progress','In Progress') "+
      " FROM FIRE_UST, FIRE_UST_CERTIFICATION B WHERE UST_ID=B.NMSS_SSN AND  nvl(B.issue_date,sysdate) > sysdate -(5*365)"+
      " AND upper(substr(APPLICANT_LAST_NAME,1,1))=? order by  upper(APPLICANT_LAST_NAME) "; 
      public final static String SELECT_UST_LIST_NEW  = " SELECT  UST_ID, ADDRESS_FLAG,COMPANY_JURISDICTION, UST_ADDRESS1," +
      " UST_ADDRESS2,CITY,STATE,ZIP,ZIP2,TELEPHONE_HOME, TELEPHONE_WORK,APPLICANT_FAX, APPLICANT_E_MAIL,"+
      " APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME, APPLICANT_MI ,TO_CHAR(B.ISSUE_DATE,'mm/dd/yyyy'),TO_CHAR(B.EXPIRATION_DATE,'mm/dd/yyyy'),"+
      " B.CERT_STATUS "+
      " FROM FIRE_UST, FIRE_UST_CERTIFICATION B WHERE UST_ID=B.NMSS_SSN AND  nvl(B.issue_date,sysdate) > sysdate -(5*365)"+
      " AND B.CERT_STATUS='3' order by  upper(APPLICANT_LAST_NAME) "; 
      public final static String UPDATE_UST  = " UPDATE FIRE_UST SET APPLICANT_MI=?,ADDRESS_FLAG=?,COMPANY_JURISDICTION=?, UST_ADDRESS1=?," +
      " UST_ADDRESS2=?,CITY=?,STATE=?,ZIP=?,ZIP2=?,TELEPHONE_HOME=?, TELEPHONE_WORK=?,APPLICANT_FAX=?, APPLICANT_E_MAIL=?,"+
      " APPLICANT_FIRST_NAME=?,APPLICANT_LAST_NAME=?,  COMMENTS=?  WHERE UST_ID=? ";
      public final static String INSERT_UST = " INSERT INTO FIRE_UST (" +
      " UST_ID, ADDRESS_FLAG,COMPANY_JURISDICTION, UST_ADDRESS1," + 
      " UST_ADDRESS2,CITY,STATE,ZIP,ZIP2,TELEPHONE_HOME, TELEPHONE_WORK,APPLICANT_FAX, APPLICANT_E_MAIL," + 
      " APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME, APPLICANT_MI,COMMENTS )"    +
      " VALUES (?,?,?,?,"+
      " ?,?,?,?,?,?,?,?,?," +
      " ?,?,?,?)" ;
       public final static String SELECT_NEXT_UST_SEQUENCE = "SELECT FUST_ID.nextval FROM DUAL";
      public final static String SELECT_DOCUMENT_ID =
      "select DOCUMENT_ID.NEXTVAL from dual";
      
      public final static String UPLOAD_DOCUMENT =
      " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
      " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
      " values(?,?,?,?,SYSDATE,?,?,'UST')";
      public final static String SELECT_DOCUMENT_NAMES_ALL =
      " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY," +
          " DOCUMENT_ID,DOCUMENT_CONNECTOR ,UPPER(DOCUMENT_PLAN_TYPE) from DFBS_DOCUMENTS "+
      " WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='UST' order by 3 desc";
      public final static String SELECT_DOCUMENT =
      " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE,DOCUMENT_ID from DFBS_DOCUMENTS where DOCUMENT_ID=?";
      public final static String UPDATE_DOCUMENT_NAME =
      " UPDATE DFBS_DOCUMENTS set DOCUMENT_CONNECTOR=? where DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? ";
      public final static String SELECT_FIRST_LETTER_OPTIONS_UST =
      "select distinct LETTER, count(*) \"COUNT\"  from ( " +
      "select upper(substr(ust.APPLICANT_LAST_NAME,1,1)) \"LETTER\" " +
      " from FIRE_UST ust" +
      ") group by LETTER order by LETTER ";
      
      
     public final static String SELECT_UST_CERT  = " SELECT CERTIFICATE_NUMBER,NMSS_SSN, "+
      " CERT_STATUS,INSTALLATION,CATHODIC_PROTECTION,"+
      " TESTING,DECOMMISSION,CERTIFICATION_TYPE,LICENSED_STATE,EVIDENCE_TYPE,"+
      " ISSUE_DATE,EXPIRATION_DATE,MAILING_DATE,DECOM_STATUS FROM FIRE_UST_CERTIFICATION WHERE NMSS_SSN=? and nvl(issue_date,sysdate) > sysdate -(5*365)";
     
      public final static String INSERT_UST_CERTIFICATION = " INSERT INTO FIRE_UST_CERTIFICATION(" + 
     " CERTIFICATE_NUMBER,NMSS_SSN,CERT_STATUS,INSTALLATION,CATHODIC_PROTECTION,"+
            " TESTING,DECOMMISSION,CERTIFICATION_TYPE,LICENSED_STATE,EVIDENCE_TYPE,"+
            " ISSUE_DATE,EXPIRATION_DATE,MAILING_DATE,DECOM_STATUS) VALUES(" +
          "?,?,?,?,?,"+"?,?,?,?,?,"+" TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),?)";
      public final static String UPDATE_UST_CERT  = " UPDATE  FIRE_UST_CERTIFICATION SET CERTIFICATE_NUMBER=?,DECOM_STATUS=?,"+
        " CERT_STATUS=?,INSTALLATION=?,CATHODIC_PROTECTION=?,"+
       " TESTING=?,DECOMMISSION=?,CERTIFICATION_TYPE=?,LICENSED_STATE=?,EVIDENCE_TYPE=?,"+
       " ISSUE_DATE=TO_DATE(?,'MM/DD/YYYY'),EXPIRATION_DATE=TO_DATE(?,'MM/DD/YYYY'),MAILING_DATE=TO_DATE(?,'MM/DD/YYYY')  WHERE NMSS_SSN=?";    
      public final static String SELECT_UST_FEE =
      " select amount from dfbs_fee_structure where description = 'UST' and division =6 ";
      public final static String INSERT_PERMIT_TRANSACTION =
      "insert into DFBS_FEE " +
      "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
      "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION) " +
      "values(?,0,?,UPPER(?),?,?,upper(?),SYSDATE,6) ";
      public final static String SELECT_NEXT_TRANSACTION_ID =
      "select TRANSACTION_ID.NEXTVAL from dual";
      public final static String SELECT_CURRENT_YEAR =
      "select TO_CHAR(SYSDATE,'YYYY') from dual";  
      
      public final static String SELECT_STATE_OPTIONS2 =
      "select state_initial,state_name from dfbs_state where state_initial in ('KY','MI','OH','PA','TN') order by 1";
      public final static String SELECT_CERT_STATUS_OPTIONS =
      "select abbreviation,description from dfbs_code where CANNED_CODE in ('UST_CERT_STATUS') and division = 6 order by 1";
    }
