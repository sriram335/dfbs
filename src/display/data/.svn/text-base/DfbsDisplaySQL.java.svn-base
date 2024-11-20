package display.data;

public class DfbsDisplaySQL {
    public DfbsDisplaySQL() {
    }
    
     public final static String SELECT_NEXT_OWNER_ID =
      "select OWNER_ID.NEXTVAL from dual";
      
      public final static String SELECT_NEXT_ADDRESS_ID =
      "select ADDRESS_ID.NEXTVAL from dual";
      
      public final static String SELECT_NEXT_TRANSACTION_ID =
      "select TRANSACTION_ID.NEXTVAL from dual";
      
       public final static String SELECT_NEXT_DISPLAY_ID =
      "select FWORK_ID.nextval from dual";
      
       public final static String SELECT_NEXT_PERSON_ID =
      "select PERSON_ID.NEXTVAL from dual";
     
      
      /* OPTIONS SQL */
      public final static String SELECT_STATE_OPTIONS =
       "select state_id,state_name from dfbs_state order by state_name";
       
      public final static String SELECT_STATE_OPTIONS2 =
      "select state_initial,state_name from dfbs_state order by state_name";
       
       public final static String SELECT_COUNTY_LIST_OPTIONS =
       "select county_code,county_name from dfbs_county order by 2";
      public final static String SELECT_display_TYPE_OPTIONS =
       "select description, description from dfbs_code where division=6 and canned_code='display' ";
        public final static String SELECT_FIRE_DEPT_OPTIONS =
       "select department_id,department_name from dfbs_fire_department order by 2 ";
        public final static String SELECT_FIRE_DEPT_OPTIONS_COUNTY =
        "select department_id,department_name from dfbs_fire_department where county in(select county_name from dfbs_county where county_code=?) order by 2 ";
        public final static String SELECT_COUNTY_CODE =
       "select county_code from dfbs_county where county_name=?";
       
       public final static String SELECT_OCCUPANCY_OPTIONS =
       "select description,description  from dfbs_code  where canned_code = 'Occupant Load' and division = 6";
      
       /* OWNER SQL */
      public final static String SELECT_OWNERS_PREFIX =
      "select distinct a.owner_id,a.LAST_NAME, " +
      "a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
      " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
      "null,null,b.E_MAIL,b.STTS_STATE_ID ";
     
     
      public final static String SELECT_OWNERS_FROM =
      " from dfbs_owner a , dfbs_address b ,fire_display x,dfbs_state c  " +
      " where a.owner_id=b.wnrs_owner_id  " +
      " and  b.STTS_STATE_ID = c.STATE_ID AND nvl(x.ISSUE_DATE,sysdate)>SYSDATE-500 AND x.ACTIVE='1'" +
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
      "AND upper(substr(a.last_name,1,1)) = nvl(?,'A') " +
      SELECT_OWNERS_SUFFIX;  
     
      public final static String SELECT_OWNERS_BY_CITY =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      "AND x.CITY = ?  " +
      SELECT_OWNERS_SUFFIX;  
     
      public final static String SELECT_OWNERS_BY_COUNTY =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      "AND x.county = ? " +
      SELECT_OWNERS_SUFFIX; 
      public final static String SELECT_OWNERS_BY_FAC_ADDRESS1 =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      "AND b.ADDRESS1 LIKE  upper(?||'%') " +
      SELECT_OWNERS_SUFFIX;  
      
      public final static String SELECT_OWNERS_BY_PERMIT_NUMBER =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      " AND x.IDENTIFICATION_NUMBER=? " +
      SELECT_OWNERS_SUFFIX; 
     
      public final static String SELECT_OWNERS_BY_OWNER_NAME =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      "AND UPPER(A.LAST_NAME) LIKE  upper('%'||?||'%') " +
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
      " from fire_display fm,dfbs_county dc where fm.county=dc.county_code " +
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
      "update DFBS_ADDRESS " +
      "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
      "where DP_PERSON_ID=?";
       public final static String SELECT_FEES_DISPLAY =
      "select " +
      "decode ( " +
      "DESCRIPTION, " +
      "'Display',1, " +
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
      " AND upper(x.display_address1) like  upper(?||'%') " +
      SELECT_OWNERS_SUFFIX;  
        public final static String SELECT_OWNERS_BY_PERMIT =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      " AND x.identification_number = ? " +
      SELECT_OWNERS_SUFFIX;  
        public final static String SELECT_OWNERS_BY_ID =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      " AND x.identification_number = ? " +
      SELECT_OWNERS_SUFFIX;  
         public final static String SELECT_OWNERS_BY_STATUS =
      SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
      " AND x.online_status =? " +
      SELECT_OWNERS_SUFFIX;  
       public final static String SELECT_PERMITS =
       " SELECT IDENTIFICATION_NUMBER ,DISPLAY_DATE,DISPLAY_TIME,DISPLAY_ADDRESS1,DISPLAY_ADDRESS2,DISPLAY_CITY ," +
       " DISPLAY_STATE ,DISPLAY_ZIP,DISPLAY_ZIP2,ISSUE_DATE,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID,DISPLAY_DATE_TO, " +
       " LOC_PHONE,LOC_FAX,LOC_E_MAIL,RETAILER_TYPE,ONLINE_STATUS,DIAGRAM_COMMENTS,county,county_name, (nvl(DISPLAY_DATE_TO,sysdate-10)+2)-sysdate+30 from fire_display ,dfbs_county " +
       " where  fire_display.county=dfbs_county.county_code and WNRS_OWNER_ID =? AND nvl(ISSUE_DATE,sysdate)>SYSDATE-500 AND ACTIVE='1'" ;  
       
       public final static String SELECT_PERMITS_BY_COUNTY =
       SELECT_PERMITS + " AND fire_display.county=?";
       
        public final static String SELECT_PERMITS_BY_STREET =
       SELECT_PERMITS + " AND upper(fire_display.display_address1) like  upper(?||'%') ";
       
      public final static String SELECT_PERMITS_BY_OWNER_NAME =
      SELECT_PERMITS  ;
      
       public final static String SELECT_PERMITS_BY_PERMIT =
       SELECT_PERMITS + " AND fire_display.identification_number = ? ";
       
      
       public final static String SELECT_PERMITS_BY_CITY =
       SELECT_PERMITS + " AND fire_display.display_city=?";
       
        public final static String SELECT_PERMITS_BY_STATUS =
        " SELECT IDENTIFICATION_NUMBER ,DISPLAY_DATE,DISPLAY_TIME,DISPLAY_ADDRESS1,DISPLAY_ADDRESS2,DISPLAY_CITY ," +
       " DISPLAY_STATE ,DISPLAY_ZIP,DISPLAY_ZIP2,ISSUE_DATE,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID,DISPLAY_DATE_TO, " +
       " LOC_PHONE,LOC_FAX,LOC_E_MAIL,RETAILER_TYPE,ONLINE_STATUS,DIAGRAM_COMMENTS,county from fire_display  " +
       " where  fire_display.county=dfbs_county.county_code and  fire_display.online_status='New'";
       
        public final static String SELECT_PERMIT_BY_ID=
       " SELECT IDENTIFICATION_NUMBER ,null,null,DISPLAY_ADDRESS1,DISPLAY_ADDRESS2,DISPLAY_CITY ," +
       " DISPLAY_STATE ,DISPLAY_ZIP,DISPLAY_ZIP2,null,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID,null, " +
       " LOC_PHONE,LOC_FAX,LOC_E_MAIL,RETAILER_TYPE,ONLINE_STATUS,NULL,county,fd_email,storage_address1,storage_address2 " +
        " from fire_display  " +
       " where  fire_display.identification_number =?";  
         public final static String SELECT_PERMIT_FOR_UPDATE=
       " SELECT IDENTIFICATION_NUMBER ,DISPLAY_DATE,DISPLAY_TIME,DISPLAY_ADDRESS1,DISPLAY_ADDRESS2,DISPLAY_CITY ," +
       " DISPLAY_STATE ,DISPLAY_ZIP,DISPLAY_ZIP2,ISSUE_DATE,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID,DISPLAY_DATE_TO, " +
       " LOC_PHONE,LOC_FAX,LOC_E_MAIL,RETAILER_TYPE,ONLINE_STATUS,DIAGRAM_COMMENTS,county,fd_email,storage_address1,storage_address2 "+
       " from fire_display  " +
       " where  fire_display.identification_number =?"; 
       
       public final static String SELECT_CONTACT_BY_ID =
       "SELECT PERSON_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL,PERSON_TYPE ,ADDRESS1," +
       " ADDRESS2,CITY,STTS_STATE_ID,ZIP,TELEPHONE_NUMBER,FAX,E_MAIL,DFBS_PERSON.WNRS_OWNER_ID,DFBS_ADDRESS.ADDRESS_ID" +
       " FROM DFBS_PERSON, DFBS_ADDRESS WHERE DFBS_PERSON.PERSON_ID=DFBS_ADDRESS.DP_PERSON_ID(+) " +
       "  AND DFBS_PERSON.DIVISION=6 AND DFBS_PERSON.WNRS_OWNER_ID=? " +
       "  AND ROWNUM<2 ";
        public final static String INSERT_DISPLAY =
       " INSERT INTO fire_display (IDENTIFICATION_NUMBER ,DISPLAY_DATE,DISPLAY_TIME,DISPLAY_ADDRESS1,DISPLAY_ADDRESS2,DISPLAY_CITY ," +
       " DISPLAY_STATE ,DISPLAY_ZIP,DISPLAY_ZIP2,ISSUE_DATE,ACTIVE,DFBSFRDPT_DEPARTMENT_ID,WNRS_OWNER_ID,DISPLAY_DATE_TO, " +
       " LOC_PHONE,LOC_FAX,LOC_E_MAIL,ONLINE_STATUS,DIAGRAM_COMMENTS,COUNTY,FD_EMAIL,storage_address1,storage_address2) " +
       " VALUES(?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?," +
       " ?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,TO_DATE(?,'MM/DD/YYYY'), " +
       " ?,?,?,?,?,?,?,?,?)";  
        public final static String INSERT_DISPLAY_DATE =
        " INSERT INTO FIRE_DISPLAY_DATES VALUES(?,TO_DATE(?,'MM/DD/YYYY'),?,DISPLAY_DATE_ID.nextval)";
        public final static String SELECT_DISPLAY_DATES =
        " SELECT DISPLAY_DATE,DISPLAY_TIME,DISPLAY_DATE_ID FROM FIRE_DISPLAY_DATES WHERE IDENTIFICATION_NUMBER=?";
         public final static String UPDATE_DISPLAY_DATE =
        " UPDATE  FIRE_DISPLAY_DATES SET DISPLAY_DATE=TO_DATE(?,'MM/DD/YYYY'),DISPLAY_TIME=?  WHERE DISPLAY_DATE_ID=?";
         public final static String DELETE_DISPLAY_DATE =
        " DELETE FROM FIRE_DISPLAY_DATES WHERE DISPLAY_DATE_ID=?";
         public final static String SELECT_DISPLAY_DATE =
        " SELECT DISPLAY_DATE,DISPLAY_TIME,DISPLAY_DATE_ID FROM FIRE_DISPLAY_DATES WHERE DISPLAY_DATE_ID=?";
        public final static String UPDATE_DISPLAY =
       " UPDATE fire_display SET COUNTY=?,DISPLAY_DATE=TO_DATE(?,'MM/DD/YYYY'),DISPLAY_TIME=?,DISPLAY_ADDRESS1=?,DISPLAY_ADDRESS2=?,DISPLAY_CITY =?," +
       " DISPLAY_STATE =?,DISPLAY_ZIP=?,DISPLAY_ZIP2=?,ISSUE_DATE=TO_DATE(?,'MM/DD/YYYY'),ACTIVE=?,DFBSFRDPT_DEPARTMENT_ID=?,WNRS_OWNER_ID=?,DISPLAY_DATE_TO=TO_DATE(?,'MM/DD/YYYY'), " +
       " LOC_PHONE=?,LOC_FAX=?,LOC_E_MAIL=?,ONLINE_STATUS=?,DIAGRAM_COMMENTS=?,FD_EMAIL=? ,"+
       " STORAGE_ADDRESS1=?, STORAGE_ADDRESS2=? WHERE IDENTIFICATION_NUMBER=? ";  
       
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
       " x.EXPLOSIVE_STORAGE_TYPE,x.display_ADDRESS1,x.display_ADDRESS2,x.CITY,x.ZIP,x.ZIP2,x.STATE, "+
       " x.display_TYPE,x.TOWNSHIP,x.COUNTY,x.ACTIVE,x.DFBSFRDPT_DEPARTMENT_ID,x.LOC_PHONE , " +
       " x.LOC_FAX ,x.LOC_E_MAIL,x.NOTES,x.display_CONTACT,x.WNRS_OWNER_ID, " +
       " a.LAST_NAME , a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
       " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
       "a.TELEPHONE,b.FAX,b.E_MAIL,b.STTS_STATE_ID " +
       " from dfbs_owner a , dfbs_address b ,fire_display x,dfbs_state c  " +
      " where a.owner_id=b.wnrs_owner_id  " +
      " and  b.STTS_STATE_ID = c.STATE_ID " +
      " and a.owner_id = x.WNRS_OWNER_ID  " +
      " and x.identification_number=? "; 
    
     
      public final static String SELECT_MAG_CONTACTS=
      " SELECT canned_code,description,abbreviation,section,remarks from DFBS_CODE " +
      " WHERE canned_code in('DISPLAY_CONTACT1','DISPLAY_CONTACT2')";
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
        " FROM dfbs_fee WHERE unique_number=? and post_date >'15-DEC-07' ORDER BY POST_DATE ASC";
         public final static String SELECT_DOCUMENT_ID =
      "select DOCUMENT_ID.NEXTVAL from dual";
    
      public final static String UPLOAD_DOCUMENT =
      " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
      " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
      " values(?,?,?,?,SYSDATE,?,?,?)";
      public final static String SELECT_DOCUMENT_NAMES_ALL =
      " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='FireDisplay' order by 3";
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
      " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='FireDisplay' and DOCUMENT_UPLOAD_DATE >sysdate-1";
      
       public final static String SELECT_CONTACT_EMAIL =
      " SELECT a.E_MAIL FROM DFBS_PERSON b,DFBS_ADDRESS a" +
      " WHERE a.DP_PERSON_ID=b.PERSON_ID AND a.WNRS_OWNER_ID=? AND a.E_MAIL IS NOT NULL AND ROWNUM<2";
       public final static String SELECT_DATE_COUNT =
      " SELECT COUNT(*) " +
      " from FIRE_DISPLAY_DATES WHERE DISPLAY_ID=? ";
      
    }

