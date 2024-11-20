package aepermits.data;
import  aepermits.to.*;
import  aepermits.data.*;

public class DfbsEntrSQL 
{
     public final static String INSERT_ERROR_LOG =
    " INSERT INTO  online_apps_log_errors VALUES(?,?,SYSDATE,?)";
    /* SEQUENCE SQL */
    public final static String SELECT_NEXT_OWNER_ID =
    "select OWNER_ID.NEXTVAL from dual";
    
    public final static String SELECT_NEXT_ADDRESS_ID =
    "select ADDRESS_ID.NEXTVAL from dual";
    
    
    public final static String SELECT_NEXT_TRANSACTION_ID =
    "select TRANSACTION_ID.NEXTVAL from dual";
    
     public final static String SELECT_NEXT_PERMIT_ID =
    "select fentr_id.NEXTVAL from dual";
    
     public final static String SELECT_NEXT_PERSON_ID =
    "select PERSON_ID.NEXTVAL from dual";
     public final static String SELECT_SPECIAL_ID =
    "select SPECIAL_ID.NEXTVAL from dual";
    
    /* OPTIONS SQL */
    public final static String SELECT_STATE_OPTIONS =
     "select state_id,state_name from dfbs_state order by state_name";
     
    public final static String SELECT_STATE_OPTIONS2 =
    "select state_initial,state_name from dfbs_state order by 1";
     
     public final static String SELECT_COUNTY_OPTIONS =
     "select county_name,county_name from dfbs_county ORDER BY 1";
     
      public final static String SELECT_COUNTY_CODE =
     "select county_code from dfbs_county where county_name=?";
     
     public final static String SELECT_OCCUPANCY_OPTIONS =
     "select description,description  from dfbs_code  where canned_code = 'Occupant Load' and division = 6";
    
     /* OWNER SQL */
    public final static String SELECT_OWNERS_PREFIX =
    "select distinct a.owner_id,a.LAST_NAME, " +
    "b.ADDRESS_ID,b.ADDRESS1,b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.COUNTY, " +
    "a.TELEPHONE,b.FAX,b.E_MAIL,NULL,NULL,NULL,NULL,b.STTS_STATE_ID ";
   
   
    public final static String SELECT_OWNERS_FROM =
    " from dfbs_owner a , dfbs_address b ,fire_entr x,dfbs_state c  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID  and x.active='A' " +
    " and a.owner_id = x.WNRS_OWNER_ID  "; 
    
   
    public final static String SELECT_OWNERS_SUFFIX =
    "order by UPPER(a.last_name) ";
   
    public final static String SELECT_OWNER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
    "and a.OWNER_ID=? ";
    
  public final static String SELECT_OWNER_FOR_UPDATE =
  " select distinct a.owner_id,a.LAST_NAME, " +
  " b.ADDRESS_ID,b.ADDRESS1,b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.COUNTY, " +
  " a.TELEPHONE,b.FAX,b.E_MAIL,a.FIRST_NAME,a.DOING_BUSINESS_AS,b.STTS_STATE_ID "+
  " from dfbs_owner a , dfbs_address b ,dfbs_state c  " +
  " where a.owner_id=b.wnrs_owner_id  " +
  " and  b.STTS_STATE_ID = c.STATE_ID   " +
  " and a.OWNER_ID=? ";

    public final static String SELECT_OWNERS_ALL =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_LETTER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND substr(a.last_name,1,1) = UPPER(?) " +
    SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_CITY =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND b.CITY = ? AND c.STATE_NAME='INDIANA' " +
    SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_COUNTY =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    "AND x.county = ? " +
    SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_FIRST_LETTER_OPTIONS =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select upper(substr(a.last_name,1,1)) \"LETTER\" " +
    SELECT_OWNERS_FROM +
    ") group by LETTER order by LETTER ";
   
   public final static String SELECT_CITIES_OPTIONS =
    "select  CITY,CITY || ', ' || STATE_NAME || ' (' || STATE_COUNT || ')' from ( " +
    "select distinct b.CITY \"CITY\",c.STATE_NAME \"STATE_NAME\", count(*) \"STATE_COUNT\" " +
    SELECT_OWNERS_FROM +
    "and c.STATE_NAME = 'INDIANA' " +
    "group by b.CITY,c.STATE_NAME ) " +
    "order by CITY";
     public final static String SELECT_COUNTIES_OPTIONS =
    "select COUNTY ,COUNTY_NAME || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct fe.county \"COUNTY\",fe.county \"COUNTY_NAME\", count(*) \"COUNTY_COUNT\" " +
    " from fire_entr fe where  ACTIVE='A' " +
    "group by fe.county) " +
    "order by county";   
   
     
    public final static String UPDATE_OWNER_STATE =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=? " +
    "where ADDRESS_ID=?";
    
    public final static String INSERT_OWNER =
    "insert into DFBS_OWNER " +
    "(OWNER_ID,LAST_NAME,TELEPHONE,DIVISION) " +
    "values(?,upper(?),?,6)";
    
    public final static String UPDATE_OWNER =
    "update DFBS_OWNER " +
    "set LAST_NAME=upper(?),TELEPHONE=? " +
    "where OWNER_ID=?";
  public final static String UPDATE_OWNER_INTERNAL=
  "update DFBS_OWNER " +
  "set DOING_BUSINESS_AS=upper(?), FIRST_NAME=upper(?),LAST_NAME=upper(?),TELEPHONE=? " +
  "where OWNER_ID=?";
    
    public final static String INSERT_OWNER_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,WNRS_OWNER_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,upper(?),upper(?),upper(?),upper(?),upper(?),?,6)";
    
     public final static String UPDATE_OWNER_ADDRESS =
    "update DFBS_ADDRESS " +
    "set E_MAIL=upper(?),FAX=? " +
    "where ADDRESS_ID=?";
  public final static String UPDATE_OWNER_ADDRESS_INTERNAL =
  " update DFBS_ADDRESS " +
  " set ADDRESS1=upper(?) ,ADDRESS2=upper(?),CITY=upper(?),STTS_STATE_ID=?,ZIP=?, E_MAIL=upper(?),FAX=? " +
  " where ADDRESS_ID=?";
     
  public final static String SELECT_CONTACT_PERSON =
  "SELECT PERSON_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL,PERSON_TYPE ,ADDRESS1," +
  " ADDRESS2,CITY,STTS_STATE_ID,ZIP,TELEPHONE_NUMBER,FAX,E_MAIL,DFBS_PERSON.WNRS_OWNER_ID,DFBS_ADDRESS.ADDRESS_ID" +
  " FROM DFBS_PERSON, DFBS_ADDRESS WHERE DFBS_PERSON.PERSON_ID=DFBS_ADDRESS.DP_PERSON_ID(+) " +
  "  AND DFBS_PERSON.DIVISION=6 AND DFBS_PERSON.WNRS_OWNER_ID=? AND LAST_NAME IS NOT NULL" +
  "  AND PERSON_TYPE='Contact Person'";
  
    public final static String INSERT_PERSON =
    "insert into DFBS_PERSON " +
    "(WNRS_OWNER_ID,PERSON_ID,PERSON_TYPE,FIRST_NAME,LAST_NAME,TELEPHONE_NUMBER,DIVISION) " +
    "values (?,?,'Contact Person',upper(?),upper(?),?,6)";
    
    public final static String UPDATE_PERSON =
    "UPDATE DFBS_PERSON " +
    "set FIRST_NAME=?,LAST_NAME=?,TELEPHONE_NUMBER=? " +
    "WHERE PERSON_ID=?";
  
     /* PERMIT SQL */

   
     public final static String INSERT_CONSUMER_PERMIT =
    "insert into FIRE_ENTR " +
    "(IDENTIFICATION_NUMBER,WNRS_OWNER_ID,FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE, " +
    " ZIP,COUNTY,PERMIT_REQUEST_TYPE,CONTACT_PERSON,LOC_PHONE,LOC_FAX,LOC_E_MAIL, " +
    " ACTUAL_LOCATION,FACILITY_DESCRIPTION,MAXIMUM_OCCUPANY,EVENT_DATE, " +
    " EVENT_TIME,EVENT_NAME,INTENDED_OCCUPANT_LOAD,FEE_EXEMPT,EXPIRATION_DATE,ONLINE_STATUS,ACTIVE,NOTES,APPLICATION_DATE ) " +
    "values(?,?,upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?)," +
    "upper(?),upper(?),?,to_date(?,'mm/dd/yyyy'),?,?,?,?,null,'NEW','A',?,sysdate)";


  
     public final static String UPDATE_CONSUMER_PERMIT =
    "update FIRE_ENTR " +
    "set FACILITY_ADDRESS1=upper(?),FACILITY_ADDRESS2=upper(?),CITY=upper(?),STATE=?,ZIP=?,COUNTY=upper(?), " +
    "PERMIT_REQUEST_TYPE=?,CONTACT_PERSON=upper(?),LOC_PHONE=?,LOC_FAX=?,LOC_E_MAIL=upper(?), " +
    "ACTUAL_LOCATION=?,FACILITY_DESCRIPTION=upper(?),MAXIMUM_OCCUPANY=?,EVENT_DATE=to_date(?,'mm/dd/yyyy'),EVENT_TIME=?,EVENT_NAME=?,INTENDED_OCCUPANT_LOAD=?,FEE_EXEMPT=?," +
    "ISSUE_DATE=null,EXPIRATION_DATE=null,ONLINE_STATUS=?,NOTES=substr(notes||?,1,1000),APPLICATION_DATE=sysdate,ACTIVE='A' " +
    " where IDENTIFICATION_NUMBER=?";

  public final static String UPDATE_CONSUMER_PERMIT_WITH_NOTES =
  "update FIRE_ENTR " +
  "set FACILITY_ADDRESS1=upper(?),FACILITY_ADDRESS2=upper(?),CITY=upper(?),STATE=?,ZIP=?,COUNTY=upper(?), " +
  "PERMIT_REQUEST_TYPE=?,CONTACT_PERSON=upper(?),LOC_PHONE=?,LOC_FAX=?,LOC_E_MAIL=upper(?), " +
  "ACTUAL_LOCATION=?,FACILITY_DESCRIPTION=upper(?),MAXIMUM_OCCUPANY=?,EVENT_DATE=to_date(?,'mm/dd/yyyy'),EVENT_TIME=?,EVENT_NAME=?,INTENDED_OCCUPANT_LOAD=?,FEE_EXEMPT=?," +
  "ISSUE_DATE=null,EXPIRATION_DATE=null,ONLINE_STATUS=?,NOTES=?,APPLICATION_DATE=to_date(?,'mm/dd/yyyy') ,ACTIVE='A' " +
  " where IDENTIFICATION_NUMBER=?";
   
        public final static String SELECT_CONSUMER_PERMITS_TO_PRINT =
    "select " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP " +
    "from FIRE_ENTR  a,DFBS_OWNER b " +
    "where IDENTIFICATION_NUMBER IN ( " +
    "select FRNTR_IDENTIFICATION_NUMBER  " +
    "from DFBS.FIRE_INSPECTION  " +
    "where FRNTR_IDENTIFICATION_NUMBER is not null  " +
    "and type5 = 'Y' and INSPECTION_DATE > APPLICATION_DATE) " +
    "and a.WNRS_OWNER_ID = b.OWNER_ID and a.ISSUE_DATE is not null ";
  
    public final static String SELECT_CONSUMER_PERMITS_TO_PRINT_BY_ID =
    "select " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP " +
    "from FIRE_ENTR  a,DFBS_OWNER b " +
    "where a.WNRS_OWNER_ID = b.OWNER_ID " +
    "and a.IDENTIFICATION_NUMBER=? and a.ISSUE_DATE is not null"; 
    public final static String SELECT_NEW_PERMITS =
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP,COUNTY, " +
    "PERMIT_REQUEST_TYPE,null,LOC_PHONE,LOC_FAX,LOC_E_MAIL,nvl(APPLICATION_DATE,to_date('01012009','mmddyyyy')), " +
    "ACTUAL_LOCATION,FACILITY_DESCRIPTION,MAXIMUM_OCCUPANY,null,null,EVENT_NAME,INTENDED_OCCUPANT_LOAD,nvl(FEE_EXEMPT,'N'), " +
    " EXPIRATION_DATE-SYSDATE " +
    "from FIRE_ENTR  "  +
    "WHERE ONLINE_STATUS NOT IN('APPROVED','DENIED') AND ISSUE_DATE IS NULL order by 1 asc " ;
    public final static String SELECT_PERMITS =
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP,COUNTY, " +
    "PERMIT_REQUEST_TYPE,null,LOC_PHONE,LOC_FAX,LOC_E_MAIL,nvl(APPLICATION_DATE,to_date('01012009','mmddyyyy')), " +
    "ACTUAL_LOCATION,FACILITY_DESCRIPTION,MAXIMUM_OCCUPANY,null,null,EVENT_NAME,INTENDED_OCCUPANT_LOAD,nvl(FEE_EXEMPT,'N'), " +
    " EXPIRATION_DATE-SYSDATE ,WNRS_OWNER_ID" +
    " from FIRE_ENTR  "  +
    " WHERE FIRE_ENTR.WNRS_OWNER_ID = ? AND NVL(ACTIVE,'X') <>'O' "  ;
     public final static String SELECT_PERMIT =
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP,COUNTY, " +
    "PERMIT_REQUEST_TYPE,NULL,LOC_PHONE,LOC_FAX,LOC_E_MAIL,nvl(APPLICATION_DATE,to_date('01012009','mmddyyyy')), " +
    "ACTUAL_LOCATION,FACILITY_DESCRIPTION,MAXIMUM_OCCUPANY,null,null,EVENT_NAME,INTENDED_OCCUPANT_LOAD,nvl(FEE_EXEMPT,'N'),null,WNRS_OWNER_ID " +
    "from FIRE_ENTR  "  +
    "WHERE IDENTIFICATION_NUMBER = ? "  ; 
      public final static String SELECT_PERMIT_FOR_UPDATE =
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'),  " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP,COUNTY, " +
    "PERMIT_REQUEST_TYPE,CONTACT_PERSON,LOC_PHONE,LOC_FAX,LOC_E_MAIL,APPLICATION_DATE, " +
    "ACTUAL_LOCATION,FACILITY_DESCRIPTION,MAXIMUM_OCCUPANY,EVENT_DATE,EVENT_TIME,EVENT_NAME,INTENDED_OCCUPANT_LOAD,nvl(FEE_EXEMPT,'N')," +
    " ONLINE_STATUS,NOTES ,EXPIRATION_DATE-SYSDATE,PERMIT_REQUEST_TYPE " +
    "from FIRE_ENTR  "  +
    "WHERE IDENTIFICATION_NUMBER = ? "  ; 
     public final static String SELECT_PERMITS_FOR_EMAIL =
    "select " +
    "IDENTIFICATION_NUMBER,  " +
    "ISSUE_DATE,WNRS_OWNER_ID, " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP,COUNTY, " +
    " LOC_PHONE,LOC_FAX,LOC_E_MAIL, " +
    "ACTUAL_LOCATION,FACILITY_DESCRIPTION,MAXIMUM_OCCUPANY,nvl(FEE_EXEMPT,'N'), " +
    " EXPIRATION_DATE,CONTACT_PERSON " +
    "from FIRE_ENTR  "  +
    "WHERE  FIRE_ENTR.EXPIRATION_DATE BETWEEN SYSDATE AND SYSDATE+90  " +
    " and ISSUE_DATE IS NOT NULL AND " +
     " ACTIVE='A' AND to_date(nvl(fire_entr,to_char(sysdate-110,'mm/dd/yyyy')),'mm/dd/yyyy') <sysdate-100 " ;
// done to get special data raj
    public final static String UPDATE_ENTR_PERMIT_EMAIL_DATE=
    " UPDATE FIRE_ENTR SET FIRE_ENTR=TO_CHAR(SYSDATE,'MM/DD/YYYY') WHERE IDENTIFICATION_NUMBER=?";
     public final static String SELECT_OWNER_EMAIL=
     " SELECT E_MAIL FROM DFBS_ADDRESS WHERE WNRS_OWNER_ID=? AND E_MAIL IS NOT NULL";
    public final static String SELECT_CONTACT_EMAIL=
     " SELECT E_MAIL FROM DFBS_ADDRESS, DFBS_PERSON  WHERE DP_PERSON_ID=PERSON_ID AND DFBS_PERSON.WNRS_OWNER_ID=? AND E_MAIL IS NOT NULL";
   public final static String SELECT_SPECIALS =
   " select " +
   " SPECIAL_ID, APPLICATION_DATE, EVENT_NAME, EVENT_DATE,  EVENT_TIME , " +
   " MAXIMUM_OCCUPANCY,COMMENTS,FRNTR_IDENTIFICATION_NUMBER, ISSUE_DATE , " + 
   " EXPIRATION_DATE ,FEE_EXEMPT ,CONTACT_PERSON_NAME,CONTACT_PHONE,CONTACT_FAX,CONTACT_EMAIL,PERMIT_TYPE " +        
   " from FIRE_ENTR_SPECIALS " +
   " WHERE  FRNTR_IDENTIFICATION_NUMBER=? AND EVENT_DATE >SYSDATE-15 " +
   " order by event_date asc";
    public final static String SELECT_NEW_SPECIALS =
   " select " +
   " SPECIAL_ID, APPLICATION_DATE, EVENT_NAME, EVENT_DATE,  EVENT_TIME , " +
   " MAXIMUM_OCCUPANCY,COMMENTS,FRNTR_IDENTIFICATION_NUMBER, ISSUE_DATE , " + 
   " EXPIRATION_DATE ,FEE_EXEMPT ,CONTACT_PERSON_NAME,CONTACT_PHONE,CONTACT_FAX,CONTACT_EMAIL,PERMIT_TYPE " +        
   " from FIRE_ENTR_SPECIALS " +
   " WHERE ISSUE_DATE IS NULL AND ONLINE_STATUS='NEW' order by FRNTR_IDENTIFICATION_NUMBER asc";
   public final static String SELECT_SPECIAL =
   " select " +
   " SPECIAL_ID, APPLICATION_DATE, EVENT_NAME, EVENT_DATE,  EVENT_TIME , " +
   " MAXIMUM_OCCUPANCY,COMMENTS,FRNTR_IDENTIFICATION_NUMBER, ISSUE_DATE , " + 
   " EXPIRATION_DATE ,FEE_EXEMPT ,CONTACT_PERSON_NAME,CONTACT_PHONE,CONTACT_FAX,CONTACT_EMAIL,PERMIT_TYPE " +        
   " from FIRE_ENTR_SPECIALS " +
   " WHERE  SPECIAL_ID=? AND EVENT_DATE >SYSDATE-15 " +
   " order by event_date asc";
  
    public final static String SELECT_SPECIAL_BY_ID =
   " select " +
   " SPECIAL_ID, APPLICATION_DATE, EVENT_NAME, EVENT_DATE,  EVENT_TIME , " +
   " MAXIMUM_OCCUPANCY,COMMENTS,FRNTR_IDENTIFICATION_NUMBER, ISSUE_DATE , " + 
   " EXPIRATION_DATE ,FEE_EXEMPT ,CONTACT_PERSON_NAME,CONTACT_PHONE,CONTACT_FAX,CONTACT_EMAIL ,ONLINE_STATUS,PERMIT_TYPE" +        
   " from FIRE_ENTR_SPECIALS " +
   " WHERE  SPECIAL_ID=?  " +
   " order by event_date asc";
    public final static String SELECT_FEES_ENTR =
    "select " +
    "decode ( " +
    "DESCRIPTION, " +
    "'1-99 Persons',1, " +
    "'100-499 Persons',2, " +
    "'500-999 Persons',3, " +
    "'1,000-4,999 Persons',4, " +
    "'5,000-9,999 Persons',5," +
    "'10,000 or more',6," +
    "'Special Event Endorsement',7," +
    "'Additional Plans',8," +
    "'0' " +
    ") PERMIT_REQUEST_TYPE,  " +
    "AMOUNT " +
    "from DFBS_FEE_STRUCTURE " +
    "where DEVICE_TYPE IN ('FIRE_ENTR_SPECIAL','FIRE_ENTR_ANNUAL')" +
    " union select 0 ,0 from dual" ;
  
    public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION,MANUFACTURER_ID) " +
    "values(?,0,?,UPPER(?),?,?,upper(?),SYSDATE,6,?) ";

    public final static String SELECT_PERMIT_COUNT =
    "select count(*) " +
    "from fire_entr " +
    "where WNRS_OWNER_ID = ?  AND (to_char(ISSUE_DATE,'yyyy') >= ? OR TO_CHAR(APPLICATION_DATE,'yyyy') >= ?) ";
     
   public final static String SELECT_PERMIT_COUNT_RENEW =
    "select count(*) " +
    "from fire_entr " +
    "where WNRS_OWNER_ID = ?  AND (to_char(ISSUE_DATE,'yyyy') = ? " +
    " OR TO_CHAR(EXPIRATION_DATE,'yyyy') = ?) AND  PERMIT_REQUEST_TYPE='A'";
    
     public final static String INSERT_SPECIAL_PERMIT =
     " insert into FIRE_ENTR_SPECIALS (SPECIAL_ID,EVENT_NAME,EVENT_DATE,EVENT_TIME ," +
     " MAXIMUM_OCCUPANCY,COMMENTS,FRNTR_IDENTIFICATION_NUMBER,ISSUE_DATE,FEE_EXEMPT, " +
     " CONTACT_PERSON_NAME,CONTACT_PHONE,CONTACT_FAX,CONTACT_EMAIL,ONLINE_STATUS,APPLICATION_DATE,EXPIRATION_DATE,PERMIT_TYPE )" +
     " values(?,?,TO_DATE(?,'mm/dd/yyyy'),?,?,?,?,NULL,?,?,?,?,?,'NEW',sysdate,to_date(?,'mm/dd/yyyy')+30,?)";
       public final static String UPDATE_SPECIAL_PERMIT =
     " UPDATE FIRE_ENTR_SPECIALS SET EXPIRATION_DATE=TO_DATE(?,'mm/dd/yyyy'),EVENT_NAME=?,EVENT_DATE=TO_DATE(?,'mm/dd/yyyy'),EVENT_TIME=? ," +
     " MAXIMUM_OCCUPANCY=?,COMMENTS=?,FRNTR_IDENTIFICATION_NUMBER=?,ISSUE_DATE=TO_DATE(?,'mm/dd/yyyy'),FEE_EXEMPT=?, " +
     " CONTACT_PERSON_NAME=?,CONTACT_PHONE=?,CONTACT_FAX=?,CONTACT_EMAIL=?,ONLINE_STATUS=?,APPLICATION_DATE=sysdate,PERMIT_TYPE=? WHERE SPECIAL_ID=? " ;

  public final static String SELECT_INSPECTIONS =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRNTR_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS , FIRST_NAME ||' '||LAST_NAME ,SYSDATE-NVL(NEXT_INSPECTION_DATE,SYSDATE)" +
     " FROM FIRE_INSPECTION, DFBS_INSPECTOR  WHERE NSPCTRS_INSPECTOR_ID=INSPECTOR_ID " +
     " AND FRNTR_IDENTIFICATION_NUMBER=? order by INSPECTION_DATE desc" ;
      
      
       public final static String SELECT_INSPECTION_BY_ID =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRNTR_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS FROM FIRE_INSPECTION WHERE INSPECTION_ID=?" ;
     
      public final static String SELECT_NEXT_INSPECTION_ID =
      "SELECT unique_id.nextval FROM DUAL" ;
      
      public final static String INSERT_INSPECTION =
      "INSERT INTO FIRE_INSPECTION(INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRNTR_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS) VALUES " +
     " (?,?,?,?,?," +"to_date(?,'MM/DD/YYYY'),?,?,?,to_date(?,'MM/DD/YYYY')," +"?,?,?)";
     
      
      public final static String UPDATE_INSPECTION =
      " UPDATE FIRE_INSPECTION SET PREVIOUS_VIOLATIONS=?,INSPECTION_DISTRICT =?,OCCUPANT_LOAD=?,ALARM_SYSTEM =?,SPRINKLER_SYSTEM=?, "  +
     " INSPECTION_DATE =to_date(?,'MM/DD/YYYY'),COMPLIED_VIOLATION=?,INSPECTOR_REMARKS=?,FRNTR_IDENTIFICATION_NUMBER =?,VIOLATION_LETTER_DATE=to_date(?,'MM/DD/YYYY'), " +    
     " NSPCTRS_INSPECTOR_ID=?,TYPE1=? WHERE INSPECTION_ID =?" ;
     
      public final static String SELECT_INSPECTORS=
      "SELECT INSPECTOR_ID, LAST_NAME||','||FIRST_NAME NAME FROM DFBS_INSPECTOR WHERE " +
      " DIVISION=6 AND INSPECTOR_STATUS='A'";
      
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
     
    public final static String SELECT_PERMIT_TO_PRINT=
     " SELECT x.IDENTIFICATION_NUMBER,x.SITE_NAME,x.SITE_DIRECTIONS,x.CONSTRUCTION_DESCRIPTION, " +       
     " x.MAXIMUM_QUANTITY,x.NEAR_BUILDING_DISTANCE,x.NEAR_HIGHWAY_DISTANCE,x.NEAR_RAILWAY_DISTANCE, " +
     " x.NEAR_FACTORY_DISTANCE,x.SIGNED_DATE,x.PERMIT_NUMBER,x.PERMIT_YEAR,x.ISSUE_DATE,x.EXPIRATION_DATE, " +               
     " x.EXPLOSIVE_STORAGE_TYPE,x.MAGAZINE_ADDRESS1,x.MAGAZINE_ADDRESS2,x.CITY,x.ZIP,x.ZIP2,x.STATE, "+
     " x.MAGAZINE_TYPE,x.TOWNSHIP,x.COUNTY,x.ACTIVE,x.DFBSFRDPT_DEPARTMENT_ID,x.LOC_PHONE , " +
     " x.LOC_FAX ,x.LOC_E_MAIL,x.NOTES,x.MAGAZINE_CONTACT,x.WNRS_OWNER_ID, " +
     " a.LAST_NAME , a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
     " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
     "a.TELEPHONE,b.FAX,b.E_MAIL,b.STTS_STATE_ID " +
     " from dfbs_owner a , dfbs_address b ,fire_magazine x,dfbs_state c  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID  " +
    " and x.identification_number=? "; 
 
   
    public final static String SELECT_AE_CONTACTS=
    " SELECT canned_code,description,abbreviation,section,remarks from DFBS_CODE " +
    " WHERE canned_code in('AE_CONTACT1','AE_CONTACT2')";
      public final static String SELECT_REPORT_NAMES =
      "SELECT DISTINCT REPORT_NAME,REPORT_CODE FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=96 AND PARAMETER1=?";
       public final static String SELECT_REPORT_GROUP =
      "SELECT DISTINCT PARAMETER1 FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=96 ";
      
      
       public final static String SELECT_OWNERS_BY_STREET =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND upper(x.facility_address1) like  upper(?||'%') " +
    SELECT_OWNERS_SUFFIX;  
    
     public final static String SELECT_OWNERS_BY_PERMIT =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND OWNER_ID IN (SELECT WNRS_OWNER_ID FROM FIRE_ENTR " +
    " WHERE IDENTIFICATION_NUMBER=? ) " +
    SELECT_OWNERS_SUFFIX; 
   
      
       public final static String SELECT_OWNERS_BY_STATUS =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.online_status =? " +
    SELECT_OWNERS_SUFFIX;  
    
     
     
     
      public final static String SELECT_PERMITS_BY_STREET =
     SELECT_PERMITS + "  AND upper(facility_address1) like  upper(?||'%') ORDER BY COUNTY";
     
     public final static String SELECT_PERMITS_BY_PERMIT =
      SELECT_PERMITS + "  AND identification_number = ?  ORDER BY COUNTY";
    
       public final static String SELECT_PERMITS_BY_STATUS =
     SELECT_PERMITS + "  AND online_status='NEW'";
     
      public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
  
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,?)";
    public final static String SELECT_DOCUMENT_NAMES_ALL =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID "+
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND  DOCUMENT_UPLOAD_DATE >sysdate-3 AND DOCUMENT_CONNECTOR_TYPE=? order by 3 ";
     public final static String SELECT_DOCUMENT_NAMES_USER =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID "+
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? AND DOCUMENT_UPLOADED_BY=? order by 3";
     public final static String SELECT_DOCUMENT =
    " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE,DOCUMENT_ID from DFBS_DOCUMENTS where DOCUMENT_ID=?";
    public final static String SELECT_DOCUMENT_NAME =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_ID " +
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? and DOCUMENT_UPLOAD_DATE >sysdate-3";
    public final static String UPDATE_DOCUMENT_NAME =
    " UPDATE DFBS_DOCUMENTS set DOCUMENT_CONNECTOR=? where DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? ";
     public final static String SELECT_DOCUMENT_COUNT =
    " SELECT COUNT(distinct doucument_name) " +
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? and DOCUMENT_UPLOAD_DATE >sysdate-1";
     public final static String SELECT_DOCUMENT_NAMES_VIEW =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID "+
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND  DOCUMENT_UPLOAD_DATE >sysdate-365 AND DOCUMENT_CONNECTOR_TYPE=? order by 3 ";
     public final static String SELECT_STANDARD_VIOLATION_LIKE =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 AND upper(abbreviation) like upper('%'||?||'%')";
     public final static String SELECT_STANDARD_VIOLATION_ALL =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 ";  
       public final static String SELECT_STANDARD_VIOLATION =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 AND upper(abbreviation) = upper(?)";
      public final static String SELECT_ACCT_DUES =
      "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >'15-DEC-08' AND UPPER(description) NOT LIKE '%FINE%' ORDER BY POST_DATE ASC";
      public final static String DELETE_DOCUMENT =
    " DELETE FROM  DFBS_DOCUMENTS WHERE DOCUMENT_ID=?  ";
     public final static String SELECT_CURRENT_YEAR =
      "SELECT TO_CHAR(SYSDATE,'MM/DD/YYYY') FROM DUAL";
     public final static String SELECT_INSPECTOR =
      "SELECT INSPECTOR_ID FROM DFBS_INSPECTOR WHERE DIVISION=6 AND INSPECTOR_STATUS='A' AND upper(E_MAIL)=upper(?)";
     public final static String SELECT_EMAIL_INSPECTOR_AFTER_FEEPAID = 
     " SELECT UNIQUE_NUMBER,TRANSACTION_ID FROM DFBS_FEE WHERE UNIQUE_NUMBER LIKE 'AE%' AND POST_DATE >SYSDATE-3 AND SSN IS NULL AND FEESPD_RECEIPT_NUMBER IS NOT NULL "+
     " AND DESCRIPTION NOT LIKE '%VOID%'";
} 


