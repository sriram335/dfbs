package arson.data;

public class ArsonSQL 
{   public final static String SELECT_USER =
   " select ds.login_id,ds.password,ds.password_expires_date,dga.access_level,sysdate from dfbs_security ds, dfbs_group_access dga " +
   " where ds.login_id=dga.scrtys_login_id and dga.grps_group_id IN('ARSON','ALL') " +
   " and ds.status='A' and dga.access_level IN('SUPERVISOR','USER','DBA') and upper(ds.login_id)=upper(?) and upper(ds.password)=upper(?)";
    public final static String RECOVER_PASSWORD =
   " select ds.login_id,ds.password from dfbs_security ds " +
   " where ds.status='A' and UPPER(ds.e_mail)=UPPER(?)";
    public final static String CHANGE_PASSWORD =
   " update dfbs_security  set password=? password_expires_date=sysdate+90" +
   " where login_id=?";
     
      public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
  
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,?)";
    public final static String SELECT_DOCUMENT_NAMES_ALL =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? order by 3";
     public final static String SELECT_DOCUMENT_NAME =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? order by 3";
     public final static String SELECT_DOCUMENT =
    " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE,DOCUMENT_ID from DFBS_DOCUMENTS where DOCUMENT_ID=?";
     public final static String SELECT_DOCUMENT_CONNECT =
    " SELECT USER_NAME,PASSWORD from  arson_document_connector where DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=?";
      public final static String DELETE_DOCUMENT_CONNECT =
    " DELETE from  arson_document_connector where DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=?"; 
}
