package idhsFines.data;

public class finesSQL {
    
    
  public final static String SELECT_ACCT_DUES_BY_STATE_NUMBER =
  "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description ,unique_number,to_char(transaction_id)" +
  " FROM dfbs_fee WHERE unique_number=? and  division=? and upper(description) not like '%CONFIRMATION%' " +
  " and feespd_receipt_number is null ORDER BY POST_DATE ASC";
  public final static String SELECT_ACCT_DUES_BY_OWNER_ID =
  "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description,unique_number,to_char(transaction_id) " +
  " FROM dfbs_fee WHERE owner_id=?  and division=? and upper(description) not like '%CONFIRMATION%' "+
  " and feespd_receipt_number is null ORDER BY POST_DATE ASC";
  public final static String SELECT_ACCT_DUES_BY_TRANSACTION_ID =
  "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description,unique_number,to_char(transaction_id) " +
  " FROM dfbs_fee WHERE transaction_id=? and post_date >'15-DEC-08' ";
  
  public final static String INSERT_PERMIT_TRANSACTION =
  "insert into DFBS_FEE " +
  "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
  "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION,MANUFACTURER_ID) " +
  "values(?,0,?,UPPER(?),null,?,upper(?),SYSDATE,5,null) ";
  
  public final static String UPDATE_DFBS_FEE=
      " UPDATE DFBS_FEE SET DESCRIPTION=DESCRIPTION||' :CONFIRMATION:'||?,OWNER_ID=? WHERE TRANSACTION_ID=?";
  public final static String UPDATE_HAZMAT_SHIPMENT=
      " UPDATE HAZMAT_SHIPMENT SET FEE_ID=? WHERE PERMIT_NUMBER=?";
  
  public final static String SELECT_APP_CONTACTS=
      " SELECT abbreviation,remarks,canned_code from DFBS_CODE WHERE CANNED_CODE LIKE '%FEE_CONTACT' AND DIVISION=2";
  public final static String SELECT_NEXT_TRANSACTION_ID =
  "select TRANSACTION_ID.NEXTVAL from dual";
  
  public final static String SELECT_BLASTER_FEE =
  "select SECTION FROM DFBS_CODE WHERE CANNED_CODE='FEE_TYPE' AND ABBREVIATION='BLASTER' AND DIVISION=5";
  public final static String SELECT_OPERATOR_FEE =
  "select SECTION FROM DFBS_CODE WHERE CANNED_CODE='FEE_TYPE' AND ABBREVIATION='OPERATOR' AND DIVISION=5";
  
  public final static String SELECT_BLASTER_FEE_FROM_DFBS =
  "select count(*) FROM DFBS_FEE WHERE UNIQUE_NUMBER=? AND DIVISION=5 and upper(description) like '%'||upper(?)||'%'";
}
