package fireworks.data;
import fireworks.to.*;
import fireworks.data.*;

public class DfbsFireworksSQL 
{
    /* SEQUENCE SQL */
    public final static String SELECT_NEXT_OWNER_ID =
    "select OWNER_ID.NEXTVAL from dual";
    
    public final static String SELECT_NEXT_ADDRESS_ID =
    "select ADDRESS_ID.NEXTVAL from dual";
    
    public final static String SELECT_NEXT_TRANSACTION_ID =
    "select TRANSACTION_ID.NEXTVAL from dual";
    
     public final static String SELECT_NEXT_PERMIT_ID =
    "select FWORK_ID.NEXTVAL from dual";
    
     public final static String SELECT_NEXT_PERSON_ID =
    "select PERSON_ID.NEXTVAL from dual";
    
    public final static String SELECT_NEXT_CATEGORY =
    "select FSALES_CATEGORY.NEXTVAL from dual";
    
    /* OPTIONS SQL */
    public final static String SELECT_STATE_OPTIONS =
     "select state_id,state_name from dfbs_state order by state_name";
     
    public final static String SELECT_STATE_OPTIONS2 =
    "select state_initial,state_name from dfbs_state order by state_name";
     
     public final static String SELECT_COUNTY_OPTIONS =
     "select county_code,county_name from dfbs_county order by county_name";
    
    
     /* OWNER SQL */
    public final static String SELECT_OWNERS_PREFIX =
    "select a.owner_id,a.LAST_NAME, " +
    "b.ADDRESS_ID,b.ADDRESS1,b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.COUNTY, " +
    "a.TELEPHONE,b.FAX,b.E_MAIL,p.PERSON_ID,p.FIRST_NAME,p.LAST_NAME,p.TELEPHONE_NUMBER,b.STTS_STATE_ID ";
   
   
    public final static String SELECT_OWNERS_FROM =
    " from dfbs_owner a, dfbs_address b,dfbs_person p , dfbs_state c where  a.OWNER_ID = b.WNRS_OWNER_ID " +
    " and a.OWNER_ID = p.WNRS_OWNER_ID(+) and person_type(+)='Contact Person' and   b.STTS_STATE_ID = c.STATE_ID " +
    " and (a.owner_id in (select DISTINCT WNRS_OWNER_ID from FIRE_SALES s where a.owner_id = s.WNRS_OWNER_ID) " +
    " or a.owner_id in (select DISTINCT WNRS_OWNER_ID from FIRE_COMPLIANCE fc where a.owner_id = fc.WNRS_OWNER_ID)) " ; 
    
    public final static String SELECT_OWNERS_COUNTY_APPROVAL =
   SELECT_OWNERS_PREFIX + 
         " from dfbs_owner a, dfbs_address b,dfbs_person p , dfbs_state c where  a.OWNER_ID = b.WNRS_OWNER_ID " +
    " and a.OWNER_ID = p.WNRS_OWNER_ID(+) and person_type(+)='Contact Person' and   b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id in (select DISTINCT s.WNRS_OWNER_ID from FIRE_SALES s,dfbs_county d  where a.owner_id = s.WNRS_OWNER_ID " +
    " and s.sales_county=d.county_code and d.county_name=?  "+
   " AND TO_CHAR(APPLICATION_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY') ";
    
    public final static String SELECT_OWNERS_SUFFIX =
    "order by upper(a.last_name) ";
   
    public final static String SELECT_OWNER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
    "and a.OWNER_ID=? ";

    public final static String SELECT_OWNERS_ALL =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_LETTER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND substr(a.last_name,1,1) = ? " +
    SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_CITY =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND b.CITY = ? AND c.STATE_NAME='INDIANA' " +
    SELECT_OWNERS_SUFFIX;  
    public final static String SELECT_OWNERS_BY_COUNTY =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " and (a.owner_id in (select DISTINCT WNRS_OWNER_ID from FIRE_SALES s where s.sales_county = ?) " +
    " or a.owner_id in (select DISTINCT WNRS_OWNER_ID from FIRE_COMPLIANCE fc where fc.county = ?)) " + 
    SELECT_OWNERS_SUFFIX; 
    public final static String SELECT_OWNERS_BY_OTHER_STATE =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND c.STATE_NAME = ? " +
    SELECT_OWNERS_SUFFIX;  
    public final static String SELECT_OWNERS_BY_STREET1 =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM ;
    
    public final static String SELECT_OWNERS_BY_PERMIT =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND a.OWNER_ID IN (SELECT WNRS_OWNER_ID FROM FIRE_SALES " +
    " WHERE IDENTIFICATION_NUMBER=? UNION " +
    " SELECT WNRS_OWNER_ID FROM FIRE_COMPLIANCE " +
    " WHERE IDENTIFICATION_NUMBER=?) " +
    SELECT_OWNERS_SUFFIX; 
    public final static String SELECT_FIRST_LETTER_OPTIONS =
        "select distinct LETTER, null \"COUNT\"  from ( " +
        "select substr(a.last_name,1,1) \"LETTER\" " +
        SELECT_OWNERS_FROM +
        ") group by LETTER order by LETTER ";
       
       public final static String SELECT_CITIES_OPTIONS =
        "select  CITY,CITY || ', ' || STATE_NAME ||  STATE_COUNT  from ( " +
        "select distinct b.CITY \"CITY\",c.STATE_NAME \"STATE_NAME\", null \"STATE_COUNT\" " +
        SELECT_OWNERS_FROM +
        "and c.STATE_NAME = 'INDIANA' " +
        "group by b.CITY,c.STATE_NAME ) " +
        "order by CITY";
          
        public final static String SELECT_OTHER_STATES_OPTIONS =
        "select STATE_NAME,STATE_NAME || STATE_COUNT  from ( " +
        "select distinct c.STATE_NAME, null \"STATE_COUNT\" " +
        SELECT_OWNERS_FROM +
        "and c.STATE_NAME <> 'INDIANA'  " +
        "group by c.STATE_NAME ) " +
        "order by state_name"; 


     
    public final static String UPDATE_OWNER_STATE =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=? " +
    "where ADDRESS_ID=?";
    
    public final static String INSERT_OWNER =
    "insert into DFBS_OWNER " +
    "(OWNER_ID,LAST_NAME,TELEPHONE,DIVISION) " +
    "values(?,?,?,6)";
    
    public final static String UPDATE_OWNER =
    "update DFBS_OWNER " +
    "set LAST_NAME=?,TELEPHONE=? " +
    "where OWNER_ID=?";
    
    public final static String INSERT_OWNER_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,WNRS_OWNER_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,?,?,?,?,?,?,6)";
    
     public final static String UPDATE_OWNER_ADDRESS =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    "where ADDRESS_ID=?";
    
    public final static String INSERT_PERSON =
    "insert into DFBS_PERSON " +
    "(WNRS_OWNER_ID,PERSON_ID,PERSON_TYPE,FIRST_NAME,LAST_NAME,TELEPHONE_NUMBER,DIVISION) " +
    "values (?,?,'Contact Person',?,?,?,6)";
    
    public final static String UPDATE_PERSON =
    "UPDATE DFBS_PERSON " +
    "set FIRST_NAME=?,LAST_NAME=?,TELEPHONE_NUMBER=? " +
    "WHERE PERSON_ID=?";
  
    /* PERMIT SQL */
    /* PERMIT SQL */
    /* PERMIT SQL */
    /* PERMIT SQL */
    /* PERMIT SQL */
    public final static String INSERT_WHOLESALE_PERMIT =
    "insert into FIRE_COMPLIANCE " +
    "(IDENTIFICATION_NUMBER,WNRS_OWNER_ID," +
    "SALES_ADDRESS1,SALES_ADDRESS2,CITY,STATE,ZIP,COUNTY," +
    "LOC_PHONE,LOC_FAX,LOC_E_MAIL," +
    "OPEN_DATE,HOURS_OF_OPERATION,ONLINE_STATUS,ACTIVE,APPLICATION_DATE,SHIPMENT_ADDRESS2) " +
    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,'NEW',1,sysdate,?)";

    public final static String INSERT_CONSUMER_PERMIT =
    "insert into FIRE_SALES " +
    "(IDENTIFICATION_NUMBER,WNRS_OWNER_ID, " + // 2
    " SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,SALES_COUNTY," + // 6
    " RETAIL_TYPE,CONTACT_PERSON,LOC_PHONE,LOC_FAX,LOC_E_MAIL, " + // 5
    " RETAILER_MERCHANT_NUMBER, " + // 1
    " INSPECTION_DATE,OPEN_DATE,HOURS_OF_OPERATION,ONLINE_STATUS,ACTIVE,RETAILER_TYPE,APPLICATION_DATE  ) " +//7
    " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'NEW',1,'New',sysdate)";

    public final static String UPDATE_WHOLESALE_PERMIT =
    "update FIRE_COMPLIANCE " +
    "set ZIP=?, " +
    "LOC_PHONE=?,LOC_FAX=?,LOC_E_MAIL=?,OPEN_DATE=?,HOURS_OF_OPERATION=?,ONLINE_STATUS='NEW',ISSUE_DATE=null,APPLICATION_DATE=sysdate,SHIPMENT_ADDRESS2=? " +
    "where IDENTIFICATION_NUMBER=? ";

    public final static String UPDATE_CONSUMER_PERMIT =
    "update FIRE_SALES " +
    "set SALES_ADDRESS1=?,SALES_ADDRESS2=?,SALES_CITY=?,SALES_STATE=?,SALES_ZIP=?, " +
    "RETAIL_TYPE=?,CONTACT_PERSON=?,LOC_PHONE=?,LOC_FAX=?,LOC_E_MAIL=?,RETAILER_MERCHANT_NUMBER=?, " +
    "INSPECTION_DATE=?,OPEN_DATE=?,HOURS_OF_OPERATION=?,ONLINE_STATUS='NEW',ISSUE_DATE=null,APPLICATION_DATE=sysdate " +
    "where IDENTIFICATION_NUMBER=?";
    public final static String UPDATE_CONSUMER_PERMIT_COUNTY_APPROVAL =
    "update FIRE_SALES " +
    " set ONLINE_STATUS=? ,COMMENTS=SUBSTR((COMMENTS||':'||? ||to_char(sysdate,'mm/dd/yyyy')),1,1000)" +
    " where IDENTIFICATION_NUMBER=?";
   
    public final static String INSERT_CATEGORY =
    "insert into FIRE_SALES_TYPE " +
    "(CATEGORY_ID,MANUFACTURER,WHOLESALER,DISTRIBUTOR,IMPORTER,FRCPL_IDENTIFICATION_NUMBER) " +
    "values(?,?,?,?,?,?)";

    public final static String UPDATE_CATEGORY =
    "update FIRE_SALES_TYPE " +
    "set MANUFACTURER=?,WHOLESALER=?,DISTRIBUTOR=?,IMPORTER=? " +
    "where CATEGORY_ID=?";

    public final static String SELECT_CONSUMER_PERMITS_TO_PRINT =
    " select  " +
    " IDENTIFICATION_NUMBER,  " +
     " ISSUE_DATE,  " +
    " TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    " last_name || ' ' || first_name ,  " +
     "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,a.WNRS_OWNER_ID,  " +
    "  DECODE(RETAILER_TYPE,'A','A-Tent','B(1)','B(1)-Unlimited','B(2)','B(2)-Unlimited','C','C-3000 lbs','H(3)','H(3)-Unlimited','New','New','Sec 8')  " +
    " from FIRE_SALES  a,DFBS_OWNER b  " +
     " where  a.WNRS_OWNER_ID = b.OWNER_ID and a.ISSUE_DATE is not null   " +
    " and to_char(a.ISSUE_DATE,'yyyy') >= to_char(SYSDATE,'yyyy')" ;
    
    
    public final static String SELECT_CONSUMER_PERMITS_TO_PRINT_BY_ID =
    "select " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,a.WNRS_OWNER_ID, " +
    " DECODE(RETAILER_TYPE,'A','A-Tent','B(1)','B(1)-Unlimited','B(2)','B(2)-Unlimited','C','C-3000 lbs','H(3)','H(3)-Unlimited','New','New','Sec 8') " +
    "from FIRE_SALES  a,DFBS_OWNER b " +
    "where a.WNRS_OWNER_ID = b.OWNER_ID " +
    "and a.IDENTIFICATION_NUMBER=? and a.ISSUE_DATE is not null "  +
    "and to_char(a.ISSUE_DATE,'yyyy') >= to_char(SYSDATE,'yyyy') ";
    

    public final static String SELECT_WHOLESALER_PERMITS_TO_PRINT =
    "select  " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "SALES_ADDRESS1,SALES_ADDRESS2,CITY,STATE,ZIP,a.WNRS_OWNER_ID,  " +
    " DECODE(RETAILER_TYPE,'A','A-Tent','B(1)','B(1)-Unlimited','B(2)','B(2)-Unlimited','C','C-3000 lbs','H(3)','H(3)-Unlimited','New','New','Sec 8') " +
    "from FIRE_COMPLIANCE a,DFBS_OWNER b  " +
   "where  a.WNRS_OWNER_ID = b.OWNER_ID and a.ISSUE_DATE is not null "  +
    "and to_char(a.ISSUE_DATE,'yyyy') >= to_char(SYSDATE,'yyyy') ";
    
    

    public final static String SELECT_WHOLESALER_PERMITS_TO_PRINT_BY_ID =
    "select distinct " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "SALES_ADDRESS1,SALES_ADDRESS2,CITY,STATE,ZIP,a.WNRS_OWNER_ID,  " +
    " DECODE(RETAILER_TYPE,'A','A-Tent','B(1)','B(1)-Unlimited','B(2)','B(2)-Unlimited','C','C-3000 lbs','H(3)','H(3)-Unlimited','New','New','Sec 8') " +
    "from FIRE_COMPLIANCE a,DFBS_OWNER b  " +
    "where a.WNRS_OWNER_ID = b.OWNER_ID " +
    "and a.IDENTIFICATION_NUMBER=? and a.ISSUE_DATE is not null "  +
    "and to_char(a.ISSUE_DATE,'yyyy') >= to_char(SYSDATE,'yyyy') ";
  /*
    public final static String SELECT_WHOLESALER_PERMITS_TO_PRINT_RESTORE =
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE,  " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "last_name || ' ' || first_name ,  " +
    "SALES_ADDRESS1,SALES_ADDRESS2,CITY,STATE,ZIP,a.WNRS_OWNER_ID   " +
    "from FIRE_COMPLIANCE a,DFBS_OWNER b   " +
    "where a.WNRS_OWNER_ID = b.OWNER_ID and a.ISSUE_DATE is not null  " +
    "and to_char(a.ISSUE_DATE,'yyyy') >= 2009 ";
    
    public final static String SELECT_CONSUMER_PERMITS_TO_PRINT_RESTORE =
    "select  " +
    "IDENTIFICATION_NUMBER,  " +
   "ISSUE_DATE,  " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "last_name || ' ' || first_name ,  " +
    "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,a.WNRS_OWNER_ID  " +
    "from FIRE_SALES  a,DFBS_OWNER b  " +
    "where  a.WNRS_OWNER_ID = b.OWNER_ID and a.ISSUE_DATE is not null  " +
    "and to_char(a.ISSUE_DATE,'yyyy') >= 2009 ";
  */
   
    public final static String SELECT_PERMITS =
    "select  " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "SALES_ADDRESS1,SALES_ADDRESS2,CITY,STATE,ZIP,'',CATEGORY_ID,MANUFACTURER,WHOLESALER,DISTRIBUTOR,IMPORTER,COUNTY,COUNTY_NAME, " +
    "'',LOC_PHONE,LOC_FAX,LOC_E_MAIL,'',APPLICATION_DATE,TO_CHAR(APPLICATION_DATE,'yyyy'), " +
    "null,null,null,'' " +
    "from FIRE_COMPLIANCE LEFT OUTER JOIN  FIRE_SALES_TYPE  ON IDENTIFICATION_NUMBER = FRCPL_IDENTIFICATION_NUMBER " +
    " LEFT OUTER JOIN  DFBS_COUNTY  ON COUNTY = COUNTY_CODE " +
    "where FIRE_COMPLIANCE.WNRS_OWNER_ID = ? and (TO_CHAR(ISSUE_DATE,'yyyy') >=  ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ? ) AND ACTIVE='1'  " +
    "UNION " +
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,RETAIL_TYPE,'0','','','','',SALES_COUNTY,COUNTY_NAME, " +
    "CONTACT_PERSON,LOC_PHONE,LOC_FAX,LOC_E_MAIL,'',APPLICATION_DATE,TO_CHAR(APPLICATION_DATE,'yyyy') , " +
    "null,null,null,RETAILER_TYPE " +
    "from FIRE_SALES  " +
    " LEFT OUTER JOIN  DFBS_COUNTY  ON SALES_COUNTY = COUNTY_CODE " +
    "WHERE FIRE_SALES.WNRS_OWNER_ID = ? and (TO_CHAR(ISSUE_DATE,'yyyy') >=  ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ? ) AND ACTIVE='1'";
    public final static String SELECT_PERMITS_COUNTY_APPROVAL =
     "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,RETAIL_TYPE,'0','','','','',SALES_COUNTY,COUNTY_NAME, " +
    "CONTACT_PERSON,LOC_PHONE,LOC_FAX,LOC_E_MAIL,RETAILER_MERCHANT_NUMBER,APPLICATION_DATE,TO_CHAR(APPLICATION_DATE,'yyyy') , " +
    "null,null,null,RETAILER_TYPE,COMMENTS " +
    "from FIRE_SALES , DFBS_COUNTY  where  SALES_COUNTY = COUNTY_CODE " +
    " and FIRE_SALES.WNRS_OWNER_ID = ? and county_name=?  AND TO_CHAR(APPLICATION_DATE,'YYYY')=TO_CHAR(SYSDATE,'YYYY')"; 
     public final static String SELECT_PERMIT_BY_ID =
    "select  " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "SALES_ADDRESS1,SALES_ADDRESS2,CITY,STATE,ZIP,'',CATEGORY_ID,MANUFACTURER,WHOLESALER,DISTRIBUTOR,IMPORTER,COUNTY,COUNTY_NAME, " +
    "'',LOC_PHONE,LOC_FAX,LOC_E_MAIL,'',APPLICATION_DATE,TO_CHAR(APPLICATION_DATE,'yyyy'), " +
    "null,open_date,hours_of_operation,'' " +
    "from FIRE_COMPLIANCE LEFT OUTER JOIN  FIRE_SALES_TYPE  ON IDENTIFICATION_NUMBER = FRCPL_IDENTIFICATION_NUMBER " +
    " LEFT OUTER JOIN  DFBS_COUNTY  ON COUNTY = COUNTY_CODE " +
    "where FIRE_COMPLIANCE.IDENTIFICATION_NUMBER = ?  " +
    "UNION " +
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,RETAIL_TYPE,'0','','','','',SALES_COUNTY,COUNTY_NAME, " +
    "CONTACT_PERSON,LOC_PHONE,LOC_FAX,LOC_E_MAIL,RETAILER_MERCHANT_NUMBER,APPLICATION_DATE,TO_CHAR(APPLICATION_DATE,'yyyy') , " +
    "inspection_date,open_date,hours_of_operation,RETAILER_TYPE " +
    "from FIRE_SALES  " +
    " LEFT OUTER JOIN  DFBS_COUNTY  ON SALES_COUNTY = COUNTY_CODE " +
    "WHERE FIRE_SALES.IDENTIFICATION_NUMBER = ?  ";

    public final static String SELECT_FEES =
    "select " +
    "decode ( " +
    "DESCRIPTION, " +
    "'First Retail',0, " +
    "'Wholesale',1, " +
    "'Additiona Tent',4, " +
    "'Additiona Class1',3, " +
    "'0' " +
    ") \"PERMIT_TYPE\",  " +
    "AMOUNT " +
    "from DFBS_FEE_STRUCTURE " +
    "where (DEVICE_TYPE='FIRE_FEE_RET' or DESCRIPTION='Wholesale')";

    public final static String SELECT_RETAIL_STAND_FEES =
    "select DESCRIPTION,amount,OTHER_FEE " +
    "from DFBS_FEE_STRUCTURE " +
    "where (DEVICE_TYPE='FIRE_FEE' and DESCRIPTION like'Retail%')";

    public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION) " +
    "values(?,0,?,UPPER(?),?,?,?,sysdate,6) ";

    public final static String SELECT_WHOLESALE_COUNT =
    "select count(*) " +
    "from fire_compliance " +
    "where WNRS_OWNER_ID = ?  AND (to_char(ISSUE_DATE,'yyyy') >= ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ?) ";
    
    public final static String SELECT_RETAIL_TENT_COUNT =
    "select count(*) " +
    "from fire_sales " +
    "where WNRS_OWNER_ID = ?  " + 
    "AND ((retail_type = 'First Retail' AND  retailer_type = 'A') " + 
      "OR retail_type = 'retail tent' )" + 
    "AND (to_char(ISSUE_DATE,'yyyy') >= ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ?) ";
    
     public final static String SELECT_RETAIL_CLASS_COUNT =
    "select count(*) " +
    "from fire_sales " +
    "where WNRS_OWNER_ID = ?  " + 
    "AND ((retail_type = 'First Retail' AND  retailer_type <> 'A') " + 
      "OR retail_type = 'retail class1' )" + 
    "AND (to_char(ISSUE_DATE,'yyyy') >= ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ?) ";
    
    public final static String SELECT_RETAIL_STAND_COUNT =
    "select count(*) " +
    "from fire_sales " +
    "where WNRS_OWNER_ID = ?  " + 
    "AND substr(identification_number,3,1) = 'R' " +
    "AND (to_char(ISSUE_DATE,'yyyy') >= ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ?) ";

    public final static String SELECT_FIREWORKS_CONTACTS=
    " SELECT canned_code,description,abbreviation,section,remarks from DFBS_CODE " +
    " WHERE canned_code in('FIREWORKS_CONTACT1','FIREWORKS_CONTACT2')";
    
      public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
  
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,sysdate,?,?,?)";
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
     public final static String SELECT_INSPECTIONS_CONSUMER =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRSALES_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS , FIRST_NAME ||' '||LAST_NAME ,SYSDATE-NVL(NEXT_INSPECTION_DATE,SYSDATE)" +
     " FROM FIRE_INSPECTION, DFBS_INSPECTOR  WHERE NSPCTRS_INSPECTOR_ID=INSPECTOR_ID " +
     " AND FRSALES_IDENTIFICATION_NUMBER=? order by INSPECTION_DATE desc" ;
     public final static String SELECT_INSPECTIONS_WHOLESALE =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRCPL_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS , FIRST_NAME ||' '||LAST_NAME ,SYSDATE-NVL(NEXT_INSPECTION_DATE,SYSDATE)" +
     " FROM FIRE_INSPECTION, DFBS_INSPECTOR  WHERE NSPCTRS_INSPECTOR_ID=INSPECTOR_ID " +
     " AND FRCPL_IDENTIFICATION_NUMBER=? order by INSPECTION_DATE desc" ;  
      
      
      public final static String SELECT_INSPECTORS=
      "SELECT INSPECTOR_ID, LAST_NAME||','||FIRST_NAME NAME FROM DFBS_INSPECTOR WHERE " +
      " DIVISION=6 AND INSPECTOR_STATUS='A'";
      
     
      public final static String SELECT_ACCT_DUES =
      "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >'01-DEC-09' ORDER BY POST_DATE ASC";
      
       public final static String SELECT_CURRENT_YEAR =
      "SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL";
      
        public final static String UPDATE_ISSUE_DATE_WHOLESALE =
      " UPDATE FIRE_COMPLIANCE SET ISSUE_DATE=TO_DATE(?,'MM/DD/YYYY') WHERE IDENTIFICATION_NUMBER=? AND ISSUE_DATE IS  NULL";

          public final static String UPDATE_ISSUE_DATE_RETAIL =
      " UPDATE FIRE_SALES SET ISSUE_DATE=TO_DATE(?,'MM/DD/YYYY') WHERE IDENTIFICATION_NUMBER=? AND ISSUE_DATE IS  NULL";
      
       public final static String SELECT_CURRENT_DATE =
      "SELECT TO_CHAR(SYSDATE,'MM/DD/YYYY') FROM DUAL";
     public final static String SELECT_INSPECTOR =
      "SELECT INSPECTOR_ID FROM DFBS_INSPECTOR WHERE DIVISION=6 AND INSPECTOR_STATUS='A' AND E_MAIL=?";
      
       public final static String SELECT_PERMIT_YEAR =
      "SELECT TO_CHAR(SYSDATE+45,'YYYY')  , TO_CHAR(SYSDATE+45,'YYYY') PERMIT_YEAR FROM DUAL UNION SELECT TO_CHAR(SYSDATE+365+45,'YYYY'),TO_CHAR(SYSDATE+365+45,'YYYY') PERMIT_YEAR FROM DUAL ORDER BY 1 ASC ";
}
