package childcare.data;



public class DfbsChildcareSQL 
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
    "select state_initial,state_name from dfbs_state order by state_name";
     
     public final static String SELECT_COUNTY_OPTIONS =
     "select county_name,county_name from dfbs_county ";
     
      public final static String SELECT_COUNTY_CODE =
     "select county_code from dfbs_county where county_name=?";
     
     public final static String SELECT_OCCUPANCY_OPTIONS =
     "select description,description  from dfbs_code  where canned_code = 'Occupant Load' and division = 6";
     
     public final static String SELECT_FIRE_DEPT_OPTIONS =
     "select department_id,department_name from dfbs_fire_department order by 2 ";
     
      public final static String SELECT_DAYCARE_STRUCTURE_OPTIONS =
     " select description structure_type, description structure_type from dfbs_code where canned_code='DAYCARE'";
     
     /* OWNER SQL */
    public final static String SELECT_OWNERS_PREFIX =
   "select distinct a.owner_id,a.LAST_NAME, " +
    "a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
    " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
    "a.telephone,b.fax,b.E_MAIL,b.STTS_STATE_ID ";
   
    public final static String SELECT_OWNERS_FROM =
    " from dfbs_owner a , dfbs_address b ,fire_day_care x,dfbs_state c  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID " +
    " and a.owner_id = x.WNRS_OWNER_ID and x.ACTIVE='A'   "; 
    
   
    public final static String SELECT_OWNERS_SUFFIX =
    "order by UPPER(a.last_name) ";
   
    public final static String SELECT_OWNER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
    "and a.OWNER_ID=? ";

    public final static String SELECT_OWNERS_ALL =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + SELECT_OWNERS_SUFFIX;  
   
    public final static String SELECT_OWNERS_BY_LETTER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND substr(a.last_name,1,1) = UPPER(?) " +
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
   
    public final static String SELECT_FIRST_LETTER_OPTIONS_CHILDCARE =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select upper(substr(DAY_CARE_NAME,1,1)) \"LETTER\" " +
    " FROM FIRE_DAY_CARE  WHERE STRUCTURE_TYPE=?  and active='A' " +
    ") group by LETTER order by LETTER ";
    
   public final static String SELECT_CITIES_OPTIONS =
    "select  CITY,CITY  || ' (' || STATE_COUNT || ')' from ( " +
    "select distinct CITY \"CITY\", count(*) \"STATE_COUNT\" " +
     " from fire_day_care  where  active='A' " +
    "group by CITY ) " +
    "order by CITY";
     public final static String SELECT_COUNTIES_OPTIONS =
    "select COUNTY ,COUNTY_NAME || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct fe.county \"COUNTY\",fe.county \"COUNTY_NAME\", count(*) \"COUNTY_COUNT\" " +
    " from fire_day_care fe where  active='A' " +
    "group by fe.county) " +
    "order by county";   
   
     
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
    "values(?,?,?,upper(?),upper(?),upper(?),upper(?),upper(?),?,6)";
    
     public final static String UPDATE_OWNER_ADDRESS =
     "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    "where WNRS_OWNER_ID=?";
    
    public final static String INSERT_PERSON =
    "insert into DFBS_PERSON " +
    "(WNRS_OWNER_ID,PERSON_ID,PERSON_TYPE,FIRST_NAME,LAST_NAME,TELEPHONE_NUMBER,DIVISION) " +
    "values (?,?,'Contact Person',upper(?),upper(?),?,6)";
    
    public final static String UPDATE_PERSON =
    "UPDATE DFBS_PERSON " +
    "set FIRST_NAME=?,LAST_NAME=?,TELEPHONE_NUMBER=? " +
    "WHERE PERSON_ID=?";
  
     /* PERMIT SQL */

       public final static String SELECT_CONTACTS =
     "SELECT PERSON_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL,PERSON_TYPE ,ADDRESS1," +
     " ADDRESS2,CITY,STTS_STATE_ID,ZIP,TELEPHONE_NUMBER,FAX,E_MAIL,DFBS_PERSON.WNRS_OWNER_ID,DFBS_ADDRESS.ADDRESS_ID" +
     " FROM DFBS_PERSON, DFBS_ADDRESS WHERE DFBS_PERSON.PERSON_ID=DFBS_ADDRESS.DP_PERSON_ID(+) " +
     "  AND DFBS_PERSON.DIVISION=6 AND DFBS_PERSON.WNRS_OWNER_ID=? AND LAST_NAME IS NOT NULL" ;
     
     public final static String SELECT_CONTACT_BY_ID =
     "SELECT PERSON_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL,PERSON_TYPE ,ADDRESS1," +
     " ADDRESS2,CITY,STTS_STATE_ID,ZIP,TELEPHONE_NUMBER,FAX,E_MAIL,DFBS_PERSON.WNRS_OWNER_ID,DFBS_ADDRESS.ADDRESS_ID" +
     " FROM DFBS_PERSON, DFBS_ADDRESS WHERE DFBS_PERSON.PERSON_ID=DFBS_ADDRESS.DP_PERSON_ID(+) " +
     "  AND DFBS_PERSON.DIVISION=6 AND DFBS_PERSON.PERSON_ID=? " ;
     public final static String INSERT_CHILDCARE_PERMIT =
    "insert into FIRE_DAY_CARE " +
    "(FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES  ) " +
    "values(?,upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?),upper(?)," +
    " ?,upper(?),null,upper(?),upper(?)," +
    " null,null,sysdate,upper(?),upper(?),upper(?),upper(?),NULL,NULL,SYSDATE,?)";


  
     public final static String UPDATE_CHILDCARE_PERMIT =
    "update FIRE_DAY_CARE " +
    "set NOTES=upper(?),DAY_CARE_NAME=upper(?),ADDRESS1=upper(?),ADDRESS2=upper(?),CITY=upper(?),STATE =upper(?)," +
     " COUNTY=upper(?),ZIP =upper(?),ZIP2=upper(?), " +                   
    " STRUCTURE_TYPE=?,ACTIVE=upper(?),DFBSFRDPT_DEPARTMENT_ID=?,WNRS_OWNER_ID=?, " +
    " RECEIVED_DATE=sysdate,LOC_PHONE=upper(?),LOC_FAX=upper(?),LOC_E_MAIL=upper(?),DAY_CARE_TYPE=upper(?),RECORD_CREATED_DATE=SYSDATE " +
    "where FIRE_ID=?";

 public final static String UPDATE_CHILDCARE_PERMIT_REC_DATE =
    "update FIRE_DAY_CARE " +
    "set RECORD_CREATED_DATE=SYSDATE " +
    "where FIRE_ID=?";
   
        public final static String SELECT_CHILCARE_PERMITS_TO_PRINT =
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
  
    public final static String SELECT_CHILCARE_PERMITS_TO_PRINT_BY_ID =
    "select " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "FACILITY_ADDRESS1,FACILITY_ADDRESS2,CITY,STATE,ZIP " +
    "from FIRE_ENTR  a,DFBS_OWNER b " +
    "where a.WNRS_OWNER_ID = b.OWNER_ID " +
    "and a.IDENTIFICATION_NUMBER=? and a.ISSUE_DATE is not null"; 

   
  
   
   public final static String SELECT_CHILDCARE_PERMIT =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE FIRE_ID = ? "  ; 
      public final static String SELECT_CHILDCARE_PERMITS =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE WNRS_OWNER_ID = ? AND ACTIVE='A'"  ; 
// done to get special data raj
  public final static String SELECT_CHILDCARE_PERMITS_BY_FIRST_LETTER =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE active='A' and UPPER(SUBSTR(DAY_CARE_NAME,1,1)) = ? AND STRUCTURE_TYPE=? order by DAY_CARE_NAME"  ; 
  public final static String SELECT_CHILDCARE_PERMITS_BY_CITY =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE active='A' and CITY = ?  AND STRUCTURE_TYPE=?"  ; 
    
    public final static String SELECT_CHILDCARE_PERMITS_BY_COUNTY =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE active='A' and COUNTY = ? AND STRUCTURE_TYPE=?"  ; 
   
    public final static String SELECT_CHILDCARE_PERMITS_BY_ADDRESS =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE active='A' and UPPER(ADDRESS1) LIKE upper(?||'%') AND STRUCTURE_TYPE=?"  ; 
     public final static String SELECT_CHILDCARE_PERMITS_BY_ID =
    "select FIRE_ID,DAY_CARE_NAME,ADDRESS1,ADDRESS2,CITY,STATE ,COUNTY,ZIP ,ZIP2, " +                   
    " STRUCTURE_TYPE,ACTIVE,FROCCPNCY_OCCUPANCY_ID,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID, " +
    " TELEPHONE,FEE_EXEMPT,RECEIVED_DATE,LOC_PHONE,LOC_FAX,LOC_E_MAIL,DAY_CARE_TYPE,START_DATE , " +
    " END_DATE ,RECORD_CREATED_DATE,NOTES " +
    "from FIRE_DAY_CARE  "  +
    "WHERE active='A' and FIRE_ID = ? AND STRUCTURE_TYPE=?"  ; 
   
    public final static String SELECT_FEES_CHILDCARE =
    "select " +
    " AMOUNT " +
    "from DFBS_FEE_STRUCTURE " +
    "where DESCRIPTION IN ('Child Care')" ;
  
    public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION) " +
    "values(?,0,?,UPPER(?),?,?,upper(?),SYSDATE,6) ";

  
    
   
  public final static String SELECT_INSPECTIONS =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRNTR_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS , FIRST_NAME ||' '||LAST_NAME ,SYSDATE-NVL(NEXT_INSPECTION_DATE,SYSDATE)" +
     " FROM FIRE_INSPECTION, DFBS_INSPECTOR  WHERE INSPECTOR_ID=NSPCTRS_INSPECTOR_ID " +
     " AND FDC_FIRE_ID=? order by INSPECTION_DATE desc" ;
      
      
       public final static String SELECT_INSPECTION_BY_ID =
     " SELECT INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FRNTR_IDENTIFICATION_NUMBER ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS FROM FIRE_INSPECTION WHERE INSPECTION_ID=?" ;
     
      public final static String SELECT_NEXT_INSPECTION_ID =
      "SELECT unique_id.nextval FROM DUAL" ;
      
      public final static String INSERT_INSPECTION =
      "INSERT INTO FIRE_INSPECTION(INSPECTION_ID ,INSPECTION_DISTRICT ,OCCUPANT_LOAD,ALARM_SYSTEM ,SPRINKLER_SYSTEM, "  +
     " INSPECTION_DATE ,COMPLIED_VIOLATION,INSPECTOR_REMARKS,FDC_FIRE_ID ,VIOLATION_LETTER_DATE, " +    
     " NSPCTRS_INSPECTOR_ID,TYPE1,PREVIOUS_VIOLATIONS) VALUES " +
     " (?,?,?,?,?," +"to_date(?,'MM/DD/YYYY'),?,?,?,to_date(?,'MM/DD/YYYY')," +"?,?,?)";
     
      
      public final static String UPDATE_INSPECTION =
      " UPDATE FIRE_INSPECTION SET PREVIOUS_VIOLATIONS=?,INSPECTION_DISTRICT =?,OCCUPANT_LOAD=?,ALARM_SYSTEM =?,SPRINKLER_SYSTEM=?, "  +
     " INSPECTION_DATE =to_date(?,'MM/DD/YYYY'),COMPLIED_VIOLATION=?,INSPECTOR_REMARKS=?,FDC_FIRE_ID =?,VIOLATION_LETTER_DATE=to_date(?,'MM/DD/YYYY'), " +    
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
 
   
    public final static String SELECT_CHILDCARE_CONTACTS=
    " SELECT canned_code,description,abbreviation,section,remarks from DFBS_CODE " +
    " WHERE canned_code in('CHILDCARE_CONTACT1','CHILDCARE_CONTACT2')";
      public final static String SELECT_REPORT_NAMES =
      "SELECT DISTINCT REPORT_NAME,REPORT_CODE FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=96 AND PARAMETER1=?";
       public final static String SELECT_REPORT_GROUP =
      "SELECT DISTINCT PARAMETER1 FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=96 ";
      
      // FROM ,MAGAZINE
       public final static String SELECT_OWNERS_BY_STREET =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND upper(x.address1) like  upper(?||'%') " +
    SELECT_OWNERS_SUFFIX;  
      public final static String SELECT_OWNERS_BY_PERMIT =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.fire_id = ? " +
    SELECT_OWNERS_SUFFIX;  
      
       public final static String SELECT_OWNERS_BY_STATUS =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.online_status =? " +
    SELECT_OWNERS_SUFFIX;  
    
     
     
     
    
    
       public final static String SELECT_PERMITS_BY_STATUS =
     SELECT_CHILDCARE_PERMITS + "  AND online_status='New'";
     
      public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
  
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,?)";
    public final static String SELECT_DOCUMENT_NAMES_ALL =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID "+
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='ChildCare' AND DOCUMENT_UPLOAD_DATE >SYSDATE-365 order by 3";
     public final static String SELECT_DOCUMENT_NAMES_USER =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='ChildCare' AND DOCUMENT_UPLOADED_BY=? order by 3";
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
    
     public final static String SELECT_STANDARD_VIOLATION_LIKE =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 AND upper(abbreviation) like upper('%'||?||'%')";
     public final static String SELECT_STANDARD_VIOLATION_ALL =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 ";  
       public final static String SELECT_STANDARD_VIOLATION =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='FIRE_VIOLATION' AND division=6 AND upper(abbreviation) = upper(?)";
      public final static String SELECT_ACCT_DUES =
      "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >'15-DEC-08' ORDER BY POST_DATE ASC";
     
     public final static String SELECT_CURRENT_YEAR =
      "SELECT TO_CHAR(SYSDATE,'MM/DD/YYYY') FROM DUAL";
     public final static String SELECT_INSPECTOR =
      "SELECT INSPECTOR_ID FROM DFBS_INSPECTOR WHERE DIVISION=6 AND INSPECTOR_STATUS='A' AND E_MAIL=?";
      
       public final static String INSERT_PERSON_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,DP_PERSON_ID,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,?,6)";
    
    
     public final static String UPDATE_PERSON_ADDRESS =
    "update DFBS_ADDRESS " +
    "set FAX=? ,E_MAIL=? " +
    "where DP_PERSON_ID=?";
       public final static String INSERT_CONTACT_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,DP_PERSON_ID,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,?,6)";
    
     public final static String UPDATE_CONTACT_ADDRESS =
    "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    "where DP_PERSON_ID=?";
      public final static String SELECT_COUNTY_LIST_OPTIONS =
     "select county_code,county_name from dfbs_county order by 2";
      
      public final static String SELECT_CHILDCARE_FSSA =
     "select expirationdate,status from rccs_data where SUBSTR(FACILITYNUMBER,4,12)=SUBSTR(?,3,12) and substr(?,1,2)='RM' " +
      " UNION select expirationdate,status from rccs_data where FACILITYNUMBER=SUBSTR(?,3,12) and substr(?,1,2)='DC'";
      public final static String SELECT_CHILDCARE_INSP_LAST =
     "select distinct(max(inspection_date)) from fire_inspection  where fdc_fire_id=?";
      public final static String SELECT_CHILDCARE_INSP_VIO =
     "select distinct(max(inspection_date)) from fire_inspection  where fdc_fire_id=? AND complied_violation='V'";
      public final static String SELECT_CHILDCARE_INSP_COMP =
     "select distinct(max(inspection_date)) from fire_inspection  where fdc_fire_id=? AND complied_violation='C'";
      public final static String SELECT_COUNT_VIOLATIONS =
     "select COUNT(*) from dfbs_violation  where FRINSPTN_INSPECTION_ID=? ";
      
  public final static String SELECT_FACILITY_COUNT =
      "SELECT COUNT(*) FROM FIRE_DAY_CARE WHERE FIRE_ID=?";
} 


