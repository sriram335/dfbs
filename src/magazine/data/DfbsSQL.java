package magazine.data;
import magazine.to.*;
import magazine.data.*;

public class DfbsSQL 
{
  public DfbsSQL()
  {
  }
  
   public final static String SELECT_NEXT_OWNER_ID =
    "select OWNER_ID.NEXTVAL from dual";
    
    public final static String SELECT_NEXT_ADDRESS_ID =
    "select ADDRESS_ID.NEXTVAL from dual";
    
    public final static String SELECT_NEXT_TRANSACTION_ID =
    "select TRANSACTION_ID.NEXTVAL from dual";
    
    public final static String SELECT_CURRENT_DATE =
    "select TO_CHAR(SYSDATE,'MM/DD/YYYY') from dual";
    
     public final static String SELECT_NEXT_MAGAZINE_ID =
    "select FMAGAZINE_ID.nextval from dual";
    
     public final static String SELECT_NEXT_PERSON_ID =
    "select PERSON_ID.NEXTVAL from dual";
     public final static String SELECT_SPECIAL_ID =
    "select SPECIAL_ID.NEXTVAL from dual";
    
    /* OPTIONS SQL */
    public final static String SELECT_STATE_OPTIONS =
     "select state_id,state_name from dfbs_state order by state_name";
     
    public final static String SELECT_STATE_OPTIONS2 =
    "select state_initial,state_name from dfbs_state order by state_name";
     
     public final static String SELECT_COUNTY_LIST_OPTIONS =
     "select county_code,county_name from dfbs_county order by 2";
    public final static String SELECT_MAGAZINE_TYPE_OPTIONS =
     "select description, description from dfbs_code where division=6 and canned_code='Magazine' ";
      public final static String SELECT_FIRE_DEPT_OPTIONS =
     "select department_id,department_name from dfbs_fire_department order by 2 ";
     
      public final static String SELECT_COUNTY_CODE =
     "select county_code from dfbs_county where county_name=?";
     
     public final static String SELECT_OCCUPANCY_OPTIONS =
     "select description,description  from dfbs_code  where canned_code = 'Occupant Load' and division = 6";
    
     /* OWNER SQL */
    public final static String SELECT_OWNERS_PREFIX =
    "select distinct a.owner_id,a.LAST_NAME, " +
    "a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
    " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
    "a.telephone,b.fax,b.E_MAIL,b.STTS_STATE_ID ";
   
   
    public final static String SELECT_OWNERS_FROM =
    " from dfbs_owner a , dfbs_address b ,fire_magazine x,dfbs_state c  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID  "; 
    
   
    public final static String SELECT_OWNERS_SUFFIX =
    "order by upper(a.last_name) ";
   
    public final static String SELECT_OWNER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
    "and a.OWNER_ID=? ";

    public final static String SELECT_OWNERS_ALL =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_LETTER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND upper(substr(a.last_name,1,1)) = ? " +
    SELECT_OWNERS_SUFFIX;  
    public final static String SELECT_OWNERS_BY_OWNER_ID =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND a.owner_id = ? " +
    SELECT_OWNERS_SUFFIX;  
    
    public final static String SELECT_OWNERS_BY_CITY =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND x.CITY = ?  " +
    SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_COUNTY =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND x.county = ? " +
    SELECT_OWNERS_SUFFIX;  
    public final static String SELECT_OWNERS_BY_MAG_USER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND a.owner_id in (select owner_id from MAGAZINE_COMPANY_USERS where USER_LOGIN_ID = ?) " +
    SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_FIRST_LETTER_OPTIONS =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select substr(a.last_name,1,1) \"LETTER\" " +
    SELECT_OWNERS_FROM +
    ") group by LETTER order by LETTER ";
   
   public final static String SELECT_CITIES_OPTIONS =
    "select  CITY,CITY || ' (' || CITY_COUNT || ')' from ( " +
    "select distinct x.CITY \"CITY\", count(*) \"CITY_COUNT\" " +
    SELECT_OWNERS_FROM +
    "group by x.CITY ) " +
    "order by CITY";
      
    public final static String SELECT_COUNTY_OPTIONS =
    "select COUNTY ,COUNTY_NAME || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct fm.county \"COUNTY\",dc.COUNTY_NAME \"COUNTY_NAME\", count(*) \"COUNTY_COUNT\" " +
    " from fire_magazine fm,dfbs_county dc where fm.county=dc.county_code " +
    "group by dc.COUNTY_NAME ,fm.county) " +
    "order by county_name"; 

     
    public final static String UPDATE_OWNER_STATE =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=? " +
    "where ADDRESS_ID=?";
    
    public final static String INSERT_OWNER =
    "insert into DFBS_OWNER " +
    "(OWNER_ID,LAST_NAME,FIRST_NAME,MIDDLE_INITIAL,DOING_BUSINESS_AS,TELEPHONE,DIVISION) " +
    "values(?,?,?,?,?,?,6)";
    
    public final static String UPDATE_OWNER =
    "update DFBS_OWNER " +
    "set LAST_NAME=?,FIRST_NAME=?,MIDDLE_INITIAL=?,DOING_BUSINESS_AS=?,TELEPHONE=? " +
    "where OWNER_ID=?";
    
    public final static String INSERT_OWNER_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,WNRS_OWNER_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,?,?,?,?,?,?,6)";
    
     public final static String INSERT_PERSON_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,DP_PERSON_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,?,?,?,?,?,?,6)";
     public final static String UPDATE_OWNER_ADDRESS =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    "where WNRS_OWNER_ID=?";
    
    public final static String INSERT_PERSON =
    "insert into DFBS_PERSON " +
    "(WNRS_OWNER_ID,PERSON_ID,PERSON_TYPE,FIRST_NAME,LAST_NAME,TELEPHONE_NUMBER,DIVISION) " +
    "values (?,?,'Contact Person',?,?,?,6)";
    
    public final static String UPDATE_PERSON =
    "UPDATE DFBS_PERSON " +
    "set FIRST_NAME=?,LAST_NAME=?,TELEPHONE_NUMBER=? " +
    "WHERE PERSON_ID=?";
     public final static String UPDATE_PERSON_ADDRESS =
    " update DFBS_ADDRESS " +
    " set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    " where DP_PERSON_ID=?";
     public final static String SELECT_FEES_MAGAZINE =
    "select " +
    "decode ( " +
    "DESCRIPTION, " +
    "'Magazine 1,4, or 5 Issue',1, " +
    "'Magazine 1,4, or 5 Renewal',2, " +
    "'Magazine 2,3 or Indoor Renewal',3, " +
    "'Magazine 2,3 or Indoor Issue',4, " +
    "'0' " +
    ") PERMIT_REQUEST_TYPE,  " +
    "AMOUNT " +
    "from DFBS_FEE_STRUCTURE " +
    "where DEVICE_TYPE IN ('FIRE_FEE')" +
    " union select 0 ,0 from dual" ;
  
    public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION,SSN) " +
    "values(?,0,?,UPPER(?),?,?,?,SYSDATE,6,?) ";
    
     public final static String SELECT_OWNERS_BY_STREET =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND upper(x.magazine_address1) like  upper(?||'%') " +
    SELECT_OWNERS_SUFFIX;  
      public final static String SELECT_OWNERS_BY_PERMIT =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.permit_number = ? " +
    SELECT_OWNERS_SUFFIX;  
      public final static String SELECT_OWNERS_BY_ID =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.identification_number = ? " +
    SELECT_OWNERS_SUFFIX;  
       public final static String SELECT_OWNERS_BY_STATUS =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.online_status =? " +
    SELECT_OWNERS_SUFFIX;  
  public final static String SELECT_NEW_OWNERS =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
  " AND x.online_status ='New' AND NVL(x.ACTIVE,'1')='1' " ;
     public final static String SELECT_PERMITS =
     " SELECT IDENTIFICATION_NUMBER,SITE_NAME,SITE_DIRECTIONS,CONSTRUCTION_DESCRIPTION, " +       
     " MAXIMUM_QUANTITY,NEAR_BUILDING_DISTANCE,NEAR_HIGHWAY_DISTANCE,NEAR_RAILWAY_DISTANCE, " +
     " NEAR_FACTORY_DISTANCE,SIGNED_DATE,PERMIT_NUMBER,PERMIT_YEAR,ISSUE_DATE,EXPIRATION_DATE, " +               
     " EXPLOSIVE_STORAGE_TYPE,MAGAZINE_ADDRESS1,MAGAZINE_ADDRESS2,CITY,ZIP,ZIP2,STATE, "+
     " MAGAZINE_TYPE,TOWNSHIP,COUNTY_NAME,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,LOC_PHONE , " +
     " LOC_FAX ,LOC_E_MAIL,NOTES ,county,MAGAZINE_CONTACT,ONLINE_STATUS,sysdate-expiration_date,ATF_LICENSE,wnrs_owner_id "+
     " from fire_magazine ,dfbs_county " +
     " where  fire_magazine.county=dfbs_county.county_code AND ACTIVE='1' ";  
     
     public final static String SELECT_PERMITS_BY_COUNTY =
     SELECT_PERMITS + " AND fire_magazine.county=?";
        public final static String SELECT_PERMITS_BY_OWNER =
     SELECT_PERMITS + " and WNRS_OWNER_ID =? ";
      public final static String SELECT_PERMITS_BY_STREET =
     SELECT_PERMITS + " AND upper(fire_magazine.magazine_address1) like  upper(?||'%') ";
     
     public final static String SELECT_PERMITS_BY_PERMIT =
     SELECT_PERMITS + " AND fire_magazine.permit_number = ? ";
     
      public final static String SELECT_PERMITS_BY_MAG_ID =
     SELECT_PERMITS + " AND fire_magazine.identification_number = ? ";
     
     public final static String SELECT_PERMITS_BY_CITY =
     SELECT_PERMITS + " AND fire_magazine.city=?";
     
      public final static String SELECT_PERMITS_BY_STATUS =
      SELECT_PERMITS + " AND fire_magazine.online_status='New' AND NVL(fire_magazine.ACTIVE,'1')='1' ";
     
      public final static String SELECT_PERMIT_BY_ID=
     " SELECT IDENTIFICATION_NUMBER,SITE_NAME,SITE_DIRECTIONS,CONSTRUCTION_DESCRIPTION, " +       
     " MAXIMUM_QUANTITY,NEAR_BUILDING_DISTANCE,NEAR_HIGHWAY_DISTANCE,NEAR_RAILWAY_DISTANCE, " +
     " NEAR_FACTORY_DISTANCE,SYSDATE,PERMIT_NUMBER,PERMIT_YEAR,ISSUE_DATE,EXPIRATION_DATE, " +               
     " EXPLOSIVE_STORAGE_TYPE,MAGAZINE_ADDRESS1,MAGAZINE_ADDRESS2,CITY,ZIP,ZIP2,STATE, "+
     " MAGAZINE_TYPE,TOWNSHIP,COUNTY,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,LOC_PHONE , " +
     " LOC_FAX ,LOC_E_MAIL,NOTES,MAGAZINE_CONTACT,ONLINE_STATUS,WNRS_OWNER_ID,NOTES,ATF_LICENSE  from fire_magazine  " +
     " where  fire_magazine.identification_number =?";  
      
  public final static String SELECT_NEW_PERMIT_BY_ID=
  " SELECT IDENTIFICATION_NUMBER,SITE_NAME,SITE_DIRECTIONS,CONSTRUCTION_DESCRIPTION, " +
  " MAXIMUM_QUANTITY,NEAR_BUILDING_DISTANCE,NEAR_HIGHWAY_DISTANCE,NEAR_RAILWAY_DISTANCE, " +
  " NEAR_FACTORY_DISTANCE,SIGNED_DATE,PERMIT_NUMBER,PERMIT_YEAR,ISSUE_DATE,EXPIRATION_DATE, " +
  " EXPLOSIVE_STORAGE_TYPE,MAGAZINE_ADDRESS1,MAGAZINE_ADDRESS2,CITY,ZIP,ZIP2,STATE, "+
  " MAGAZINE_TYPE,TOWNSHIP,COUNTY,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,LOC_PHONE , " +
  " LOC_FAX ,LOC_E_MAIL,NOTES,MAGAZINE_CONTACT,ONLINE_STATUS,WNRS_OWNER_ID,NOTES,ATF_LICENSE  from fire_magazine  " +
  " where  fire_magazine.identification_number =?";
     
     public final static String SELECT_CONTACT_BY_ID =
     "SELECT PERSON_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL,PERSON_TYPE ,ADDRESS1," +
     " ADDRESS2,CITY,STTS_STATE_ID,ZIP,TELEPHONE_NUMBER,FAX,E_MAIL,DFBS_PERSON.WNRS_OWNER_ID,DFBS_ADDRESS.ADDRESS_ID" +
     " FROM DFBS_PERSON, DFBS_ADDRESS WHERE DFBS_PERSON.PERSON_ID=DFBS_ADDRESS.DP_PERSON_ID(+) " +
     "  AND DFBS_PERSON.DIVISION=6 AND DFBS_PERSON.WNRS_OWNER_ID=? " +
     "  AND ROWNUM<2 ";
      public final static String INSERT_MAGAZINE =
     " INSERT INTO FIRE_MAGAZINE (IDENTIFICATION_NUMBER,SITE_NAME,SITE_DIRECTIONS,CONSTRUCTION_DESCRIPTION, " +       
     " MAXIMUM_QUANTITY,NEAR_BUILDING_DISTANCE,NEAR_HIGHWAY_DISTANCE,NEAR_RAILWAY_DISTANCE, " +
     " NEAR_FACTORY_DISTANCE,SIGNED_DATE,PERMIT_NUMBER,PERMIT_YEAR, " +               
     " EXPLOSIVE_STORAGE_TYPE,MAGAZINE_ADDRESS1,MAGAZINE_ADDRESS2,CITY,ZIP,ZIP2,STATE, "+
     " MAGAZINE_TYPE,TOWNSHIP,COUNTY,MAGAZINE_CONTACT,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,LOC_PHONE , " +
     " LOC_FAX ,LOC_E_MAIL,WNRS_OWNER_ID,ONLINE_STATUS,NOTES,ATF_LICENSE) " +
     " VALUES(?,?,?,?," +
     " ?,?,?,?, " +
     " ?,sysdate,?,?," +
     " ?,?,?,?,?,?,?," +
     "?,?,?,?,'1',?,?,"+
      "?,?,?,?,?,?)";  
      public final static String UPDATE_MAGAZINE =
     " UPDATE FIRE_MAGAZINE SET LOC_E_MAIL=?,SITE_NAME=?,SITE_DIRECTIONS=?,CONSTRUCTION_DESCRIPTION=?, " +       
     " MAXIMUM_QUANTITY=?,NEAR_BUILDING_DISTANCE=?,NEAR_HIGHWAY_DISTANCE=?,NEAR_RAILWAY_DISTANCE=?, " +
     " NEAR_FACTORY_DISTANCE=?,SIGNED_DATE=TO_DATE(?,'MM/DD/YYYY'),PERMIT_NUMBER=?,PERMIT_YEAR=?,ISSUE_DATE=TO_DATE(?,'MM/DD/YYYY'),EXPIRATION_DATE=TO_DATE(?,'MM/DD/YYYY'), " +               
     " EXPLOSIVE_STORAGE_TYPE=?,MAGAZINE_ADDRESS1=?,MAGAZINE_ADDRESS2=?,CITY=?,ZIP=?,ZIP2=?,STATE=?, "+
     " MAGAZINE_TYPE=?,TOWNSHIP=?,COUNTY=?,MAGAZINE_CONTACT=?,ACTIVE='1',DFBSFRDPT_DEPARTMENT_ID=?,LOC_PHONE =?, " +
     " LOC_FAX =? ,ONLINE_STATUS=?,NOTES=? ,ATF_LICENSE =? WHERE IDENTIFICATION_NUMBER=? ";  
     
      public final static String INSERT_CONTACT_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,DP_PERSON_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,?,?,?,?,?,?,6)";
    
     public final static String UPDATE_CONTACT_ADDRESS =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    "where DP_PERSON_ID=?";
    
     public final static String SELECT_INSPECTIONS =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRMGZNS_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS , FIRST_NAME ||' '||LAST_NAME ,SYSDATE-NVL(NEXT_INSPECTION_DATE,SYSDATE)" +
     " FROM FIRE_INSPECTION, DFBS_INSPECTOR  WHERE NSPCTRS_INSPECTOR_ID=INSPECTOR_ID " +
     " AND FRMGZNS_IDENTIFICATION_NUMBER=? order by INSPECTION_DATE desc" ;
      
      
       public final static String SELECT_INSPECTION_BY_ID =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRMGZNS_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS FROM FIRE_INSPECTION WHERE INSPECTION_ID=?" ;
     
      public final static String SELECT_NEXT_INSPECTION_ID =
      "SELECT unique_id.nextval FROM DUAL" ;
      
      public final static String INSERT_INSPECTION =
      "INSERT INTO FIRE_INSPECTION(INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRMGZNS_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS,NEXT_INSPECTION_DATE) VALUES " +
     " (?,?,?,?,?," +"to_date(?,'MM/DD/YYYY'),?,?,?,to_date(?,'MM/DD/YYYY')," +"?,?,?,SYSDATE)";
     
      
      public final static String UPDATE_INSPECTION =
      " UPDATE FIRE_INSPECTION SET PREVIOUS_VIOLATIONS=?,INSPECTION_DISTRICT =?,OCCUPANT_LOAD=?,ALARM_SYSTEM =?,SPRINKLER_SYSTEM=?, "  +
     " INSPECTION_DATE =to_date(?,'MM/DD/YYYY'),COMPLIED_VIOLATION=?,INSPECTOR_REMARKS=?,FRMGZNS_IDENTIFICATION_NUMBER =?,VIOLATION_LETTER_DATE=to_date(?,'MM/DD/YYYY'), " +    
     " NSPCTRS_INSPECTOR_ID=?,TYPE1=? WHERE INSPECTION_ID =?" ;
     
      public final static String SELECT_INSPECTORS=
      "SELECT INSPECTOR_ID, LAST_NAME||','||FIRST_NAME NAME FROM DFBS_INSPECTOR WHERE " +
      " DIVISION=15 AND INSPECTOR_STATUS='A'";
      
      public final static String SELECT_INSPECTION_TYPE =
      "SELECT description,description from dfbs_code where division=6  and " +
      " canned_code='INSPECTION_TYPE' " ;
      
      public final static String SELECT_ALARAM_OPTIONS =
      " SELECT 'Y' option_value,'Yes' from dual union SELECT 'N' option_value,'No' from dual union SELECT 'P' option_value,'Partial' from dual ";
      public final static String SELECT_INSP_OPTIONS =
      " SELECT 'V' option_value,'Violation' from dual union SELECT 'C' option_value,'Complied' from dual union SELECT 'F' option_value,'File only' from dual ";

    public final static String SELECT_VIOLATIONS =
     " SELECT COMPLIANCE_VERIFIED,VIOLATION_ID,VIOLATION_CODE,VIOLATION_DESCRIPTION,REMARKS,CLEARED_DATE,VIOLATION_DATE " +
     " FROM DFBS_VIOLATION   WHERE FRINSPTN_INSPECTION_ID=? order by COMPLIANCE_VERIFIED,VIOLATION_ID asc" ;
      
      
       public final static String SELECT_VIOLATION_BY_ID =
     " SELECT COMPLIANCE_VERIFIED,VIOLATION_ID,VIOLATION_CODE,VIOLATION_DESCRIPTION,REMARKS,CLEARED_DATE,VIOLATION_DATE,FRINSPTN_INSPECTION_ID " +
     " FROM DFBS_VIOLATION   WHERE VIOLATION_ID=?" ;
     
      public final static String SELECT_NEXT_VIOLATION_ID =
      "SELECT VIOLATION_ID.NEXTVAL FROM DUAL" ;
      
      public final static String INSERT_VIOLATION =
      "INSERT INTO DFBS_VIOLATION(COMPLIANCE_VERIFIED,VIOLATION_ID,VIOLATION_CODE,VIOLATION_DESCRIPTION,REMARKS,CLEARED_DATE,VIOLATION_DATE,FRINSPTN_INSPECTION_ID,DIVISION) VALUES " +
     " (?,?,?,?,?,to_date(?,'MM/DD/YYYY'),to_date(?,'MM/DD/YYYY'),?,6)";
     
      
      public final static String UPDATE_VIOLATION =
      " UPDATE DFBS_VIOLATION SET COMPLIANCE_VERIFIED=?,VIOLATION_CODE=?," +
      " VIOLATION_DESCRIPTION=?,REMARKS=?,CLEARED_DATE=to_date(?,'MM/DD/YYYY'),VIOLATION_DATE=to_date(?,'MM/DD/YYYY') " +
      " WHERE VIOLATION_ID =?" ;
     
    public final static String SELECT_PERMIT_TO_PRINT_MAGAZINE=
     " SELECT x.IDENTIFICATION_NUMBER,x.SITE_NAME,x.SITE_DIRECTIONS,x.CONSTRUCTION_DESCRIPTION, " +       
     " x.MAXIMUM_QUANTITY,x.NEAR_BUILDING_DISTANCE,x.NEAR_HIGHWAY_DISTANCE,x.NEAR_RAILWAY_DISTANCE, " +
     " x.NEAR_FACTORY_DISTANCE,x.SIGNED_DATE,x.PERMIT_NUMBER,x.PERMIT_YEAR,x.ISSUE_DATE,x.EXPIRATION_DATE, " +               
     " x.EXPLOSIVE_STORAGE_TYPE,x.MAGAZINE_ADDRESS1,x.MAGAZINE_ADDRESS2,x.CITY,x.ZIP,x.ZIP2,x.STATE, "+
     " x.MAGAZINE_TYPE,nvl(x.TOWNSHIP,'N/A'),x.county,dc.county_name,x.DFBSFRDPT_DEPARTMENT_ID,x.LOC_PHONE , " +
     " x.LOC_FAX ,x.LOC_E_MAIL,x.NOTES,x.MAGAZINE_CONTACT,x.WNRS_OWNER_ID, " +
     " a.LAST_NAME , a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
     " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
     "a.TELEPHONE,b.FAX,b.E_MAIL,b.STTS_STATE_ID " +
     " from dfbs_owner a , dfbs_address b ,fire_magazine x,dfbs_state c, dfbs_county dc  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID   and dc.county_code=x.county" +
    " and x.identification_number=? and x.print_key=?"; 
     public final static String SELECT_PERMIT_TO_PRINT_MAGAZINE_SINGLE=
     " SELECT x.IDENTIFICATION_NUMBER,x.SITE_NAME,x.SITE_DIRECTIONS,x.CONSTRUCTION_DESCRIPTION, " +       
     " x.MAXIMUM_QUANTITY,x.NEAR_BUILDING_DISTANCE,x.NEAR_HIGHWAY_DISTANCE,x.NEAR_RAILWAY_DISTANCE, " +
     " x.NEAR_FACTORY_DISTANCE,x.SIGNED_DATE,x.PERMIT_NUMBER,x.PERMIT_YEAR,x.ISSUE_DATE,x.EXPIRATION_DATE, " +               
     " x.EXPLOSIVE_STORAGE_TYPE,x.MAGAZINE_ADDRESS1,x.MAGAZINE_ADDRESS2,x.CITY,x.ZIP,x.ZIP2,x.STATE, "+
     " x.MAGAZINE_TYPE,nvl(x.TOWNSHIP,'N/A'),x.county,dc.county_name,x.DFBSFRDPT_DEPARTMENT_ID,x.LOC_PHONE , " +
     " x.LOC_FAX ,x.LOC_E_MAIL,x.NOTES,x.MAGAZINE_CONTACT,x.WNRS_OWNER_ID, " +
     " a.LAST_NAME , a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
     " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
     "a.TELEPHONE,b.FAX,b.E_MAIL,b.STTS_STATE_ID " +
     " from dfbs_owner a , dfbs_address b ,fire_magazine x,dfbs_state c, dfbs_county dc  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID   and dc.county_code=x.county" +
    " and x.identification_number=? "; 
     public final static String SELECT_PERMIT_TO_PRINT_OWNER=
     " SELECT x.IDENTIFICATION_NUMBER,x.SITE_NAME,x.SITE_DIRECTIONS,x.CONSTRUCTION_DESCRIPTION, " +       
     " x.MAXIMUM_QUANTITY,x.NEAR_BUILDING_DISTANCE,x.NEAR_HIGHWAY_DISTANCE,x.NEAR_RAILWAY_DISTANCE, " +
     " x.NEAR_FACTORY_DISTANCE,x.SIGNED_DATE,x.PERMIT_NUMBER,x.PERMIT_YEAR,x.ISSUE_DATE,x.EXPIRATION_DATE, " +               
     " x.EXPLOSIVE_STORAGE_TYPE,x.MAGAZINE_ADDRESS1,x.MAGAZINE_ADDRESS2,x.CITY,x.ZIP,x.ZIP2,x.STATE, "+
     " x.MAGAZINE_TYPE,nvl(x.TOWNSHIP,'N/A'),x.county,dc.county_name,x.DFBSFRDPT_DEPARTMENT_ID,x.LOC_PHONE , " +
     " x.LOC_FAX ,x.LOC_E_MAIL,x.NOTES,x.MAGAZINE_CONTACT,x.WNRS_OWNER_ID, " +
     " a.LAST_NAME , a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
     " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
     "a.TELEPHONE,b.FAX,b.E_MAIL,b.STTS_STATE_ID " +
     " from dfbs_owner a , dfbs_address b ,fire_magazine x,dfbs_state c, dfbs_county dc  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID   and dc.county_code=x.county" +
    " and a.owner_id=? and b.attention=? and x.expiration_date >=sysdate"; 
    public final static String SELECT_PERMIT_TO_PRINT_OWNER_ALL=
    " SELECT x.IDENTIFICATION_NUMBER,x.SITE_NAME,x.SITE_DIRECTIONS,x.CONSTRUCTION_DESCRIPTION, " +       
    " x.MAXIMUM_QUANTITY,x.NEAR_BUILDING_DISTANCE,x.NEAR_HIGHWAY_DISTANCE,x.NEAR_RAILWAY_DISTANCE, " +
    " x.NEAR_FACTORY_DISTANCE,x.SIGNED_DATE,x.PERMIT_NUMBER,x.PERMIT_YEAR,x.ISSUE_DATE,x.EXPIRATION_DATE, " +               
    " x.EXPLOSIVE_STORAGE_TYPE,x.MAGAZINE_ADDRESS1,x.MAGAZINE_ADDRESS2,x.CITY,x.ZIP,x.ZIP2,x.STATE, "+
    " x.MAGAZINE_TYPE,nvl(x.TOWNSHIP,'N/A'),x.county,dc.county_name,x.DFBSFRDPT_DEPARTMENT_ID,x.LOC_PHONE , " +
    " x.LOC_FAX ,x.LOC_E_MAIL,x.NOTES,x.MAGAZINE_CONTACT,x.WNRS_OWNER_ID, " +
    " a.LAST_NAME , a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
    " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
    "a.TELEPHONE,b.FAX,b.E_MAIL,b.STTS_STATE_ID " +
    " from dfbs_owner a , dfbs_address b ,fire_magazine x,dfbs_state c, dfbs_county dc  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID   and dc.county_code=x.county" +
    " and a.owner_id=?  and x.expiration_date >=sysdate";
   
    public final static String SELECT_MAG_CONTACTS=
    " SELECT canned_code,description,abbreviation,section,remarks from DFBS_CODE " +
    " WHERE canned_code in('MAGAZINE_CONTACT1','MAGAZINE_CONTACT2')";
      public final static String SELECT_REPORT_NAMES =
      "SELECT DISTINCT REPORT_NAME,REPORT_CODE FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=96 AND PARAMETER1=?";
       public final static String SELECT_REPORT_GROUP =
      "SELECT DISTINCT PARAMETER1 FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=96 ";
       public final static String SELECT_STANDARD_VIOLATION_LIKE =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 AND upper(abbreviation) like upper('%'||?||'%')";
     public final static String SELECT_STANDARD_VIOLATION_ALL =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 ";  
       public final static String SELECT_STANDARD_VIOLATION =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 AND upper(abbreviation) = upper(?)";
      public final static String SELECT_ACCT_DUES =
      "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >'15-DEC-08' ORDER BY POST_DATE ASC";
     
       public final static String SELECT_FEE_DUE =
      "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >SYSDATE-300 ORDER BY POST_DATE ASC";
       public final static String SELECT_FEE_DUE_CHECK =
      "SELECT (due) " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >SYSDATE-300 AND description not like '%CONFIRMATION%'";
      
       public final static String SELECT_NEW_PERMITS =
       "SELECT fm.identification_number,fm.expiration_date,fm.online_status ,dc.county_name, fm.signed_date " +
      " FROM fire_magazine fm , dfbs_county dc WHERE fm.county=dc.county_code and fm.online_status ='New' AND NVL(fm.ACTIVE,'1')='1' "+
       " AND fm.wnrs_owner_id=?";
      
       public final static String SELECT_PRINT_KEY =
       "SELECT ?||TO_CHAR(SYSDATE,'SSYYHHMMMIDD') FROM DUAL";
       
       public final static String UPDATE_PRINT_KEY_OWNER =
       "UPDATE dfbs_address set attention=? where wnrs_owner_id=?";
       
       public final static String UPDATE_PRINT_KEY_MAGAZINE =
        "UPDATE fire_magazine set print_key=? where identification_number=?";
        
    public final static String UPLOAD_DOCUMENT =
       " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
       " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
       " values(?,?,?,?,SYSDATE,?,?,'Magazine')";
       public final static String SELECT_DOCUMENT_NAMES_ALL =
       " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Magazine' union "+
       " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Magazine' order by 3";
        public final static String SELECT_DOCUMENT_NAMES_OWNER =
      " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Magazine' order by 3";
  public final static String SELECT_DOCUMENT_NAMES_OWNER_NEW =
  " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE "+
  " DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Magazine' AND DOCUMENT_UPLOAD_DATE >SYSDATE-1 order by 3";
        public final static String SELECT_DOCUMENT_NAMES_USER =
       " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Magazine' AND DOCUMENT_UPLOADED_BY=? order by 3";
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
        
        // MAGAZINE USERS
        
         public final static String INSERT_MAGAZINE_USERS =
        " INSERT INTO MAGAZINE_COMPANY_USERS(USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID ) VALUES(?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?)";
         public final static String UPDATE_MAGAZINE_USERS =
         " UPDATE MAGAZINE_COMPANY_USERS SET USER_FIRST_NAME=?,USER_LAST_NAME=?,USER_PASSWORD=?,STATUS=?," +
         " PASSWORD_EXPIRATION_DATE=TO_DATE(?,'MM/DD/YYYY'), USER_LOGIN_ID=?,USER_PHONE=? WHERE USER_ID=?";
          public final static String UPDATE_MAGAZINE_USER_PASSWORD =
         " UPDATE MAGAZINE_COMPANY_USERS SET USER_PASSWORD=UPPER(?)," +
         " PASSWORD_EXPIRATION_DATE=SYSDATE+90 WHERE USER_ID=UPPER(?)";
          public final static String SELECT_MAGAZINE_USERS =
          " SELECT  USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID FROM MAGAZINE_COMPANY_USERS WHERE STATUS='A' AND OWNER_ID=?";
         public final static String SELECT_MAGAZINE_USERS_BY_ID =
          " SELECT  USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID FROM MAGAZINE_COMPANY_USERS WHERE STATUS='A' AND USER_ID=?";
         public final static String SELECT_NEXT_MAGAZINE_USER_ID =
         "select MAGAZINE_USER_ID.NEXTVAL from dual";
         public final static String VERIFY_MAGAZINE_USERS =
          " SELECT DISTINCT USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID,PASSWORD_EXPIRATION_DATE-SYSDATE FROM MAGAZINE_COMPANY_USERS "+
        " WHERE UPPER(USER_LOGIN_ID)=UPPER(?) and STATUS='A' AND UPPER(USER_PASSWORD)=UPPER(?) AND ROWNUM<2 ";
        public final static String EMAIL_MAGAZINE_USERS_PASSWORD =
          " SELECT  USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID,PASSWORD_EXPIRATION_DATE-SYSDATE FROM MAGAZINE_COMPANY_USERS " +
        " WHERE upper(USER_LOGIN_ID)=UPPER(?) and STATUS='A' ";
     public final static String SELECT_USER_FIRST_LETTER_OPTIONS =
         "select distinct LETTER, count(*) \"COUNT\"  from ( " +
         " select substr(USER_LOGIN_ID,1,1) \"LETTER\" " +
         " from MAGAZINE_COMPANY_USERS " +
         ") group by LETTER order by LETTER ";
      public final static String MAGAZINE_USERS_ACCOUNTS =
          " SELECT  USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID,PASSWORD_EXPIRATION_DATE-SYSDATE FROM MAGAZINE_COMPANY_USERS " +
        " WHERE UPPER(USER_LOGIN_ID)=UPPER(?) and STATUS='A' AND UPPER(USER_PASSWORD)=UPPER(?) ";    
         
        public final static String SELECT_USER_BY_LETTER =
        " SELECT  USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID from MAGAZINE_COMPANY_USERS " +
        " where  UPPER(substr(USER_LOGIN_ID,1,1))=UPPER(?) order by 2";
         public final static String SELECT_MAGAZINE_USERS_ALL =
          " SELECT  USER_ID,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,STATUS,PASSWORD_EXPIRATION_DATE, " +
        " USER_LOGIN_ID,USER_PHONE,OWNER_ID FROM MAGAZINE_COMPANY_USERS WHERE STATUS='A' ";
        
       
} 

