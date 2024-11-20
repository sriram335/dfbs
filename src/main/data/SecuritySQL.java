package main.data;
import hs.data.*;

public class SecuritySQL 
{    public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
   public final static String SELECT_USER =
   " select ds.login_id,ds.password,ds.password_expires_date,sysdate,upper(e_mail) from dfbs_security ds " +
   " where  ds.status='A'  and upper(ds.login_id)=upper(?) and upper(ds.password)=upper(?)";
   public final static String SELECT_USER_BY_EMAIL =
   " select ds.login_id,ds.password,ds.password_expires_date,sysdate from dfbs_security ds " +
   " where ds.status='A'  and upper(ds.E_MAIL)=upper(?) and upper(ds.password)=upper(?)";
    public final static String RECOVER_PASSWORD =
   " select ds.login_id,ds.password from dfbs_security ds " +
   " where ds.status='A' and UPPER(ds.e_mail)=UPPER(?)";
    public final static String CHANGE_PASSWORD =
   " update dfbs_security  set password=?, PASSWORD_EXPIRES_DATE=sysdate+90 " +
   " where login_id=?";
        public final static String SELECT_USER_URL =
   " select ds.login_id,ds.password,ds.password_expires_date,sysdate from dfbs_security ds,dfbs_code dc" +
   " where ds.status='A'  " +
   " and upper(ds.e_mail)=upper(dc.description) and upper(dc.remarks)=upper(?)" +
   " and dc.canned_code LIKE ?||'%' ";  
   public final static String SELECT_USER_GROUPS =
   " SELECT ACCESS_LEVEL,GRPS_GROUP_ID FROM DFBS_GROUP_ACCESS WHERE UPPER(SCRTYS_LOGIN_ID)=UPPER(?) AND ACCESS_LEVEL <>'READ'";
       public final static String SELECT_CONTACTS=
       " SELECT canned_code,upper(description),abbreviation,section,remarks from DFBS_CODE " +
       " WHERE canned_code in(?,?)";
       
      public final static String SELECT_APP_STATUS=
       " SELECT abbreviation,description from DFBS_CODE " +
       " WHERE division=2 and canned_code =? ";
         
    public final static String INSERT_USER_IP =
    " INSERT INTO LOG_IP(USER_ID,IP_ADDRESS,LOGIN_DATE) VALUES(?,?,sysdate)";
  public final static String INSERT_IDHS_APP_FAILURE =
  " INSERT INTO IDHS_ONLINE_APP_FAILURE(IDHS_FAILURE_ID,IDHS_APP_TYPE ,IDHS_FAILED_DATE,FAILED_ERROR)"+
  " VALUES( IDHS_FAILURE_ID.NEXTVAL,?,sysdate,?)";
  public final static String SELECT_CURRENT_DATE =
  " SELECT TO_CHAR(SYSDATE,'MM/DD/YYYY') FROM DUAL";
  public final static String INSERT_JAVA_ERROR =
  " INSERT INTO java_error VALUES(?)";
}