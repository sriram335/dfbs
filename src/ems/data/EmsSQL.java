package ems.data;

import ems.to.*;
import ems.data.*;

public class EmsSQL 
{  
   public final static String SELECT_NEXT_RENEWAL_ID =
    "select renewal_id.nextval from dual";
    public final static String SELECT_COUNTY_OPTIONS =
     "select county_name,county_name from dfbs_county ";
      public final static String SELECT_STATE_OPTIONS2 =
    "select state_initial,state_name from dfbs_state order by state_name";
    public final static String SELECT_PROVIDER_BY_CITY =
    " SELECT PROVIDER_ID,PROVIDE_NAME ,PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY ," +
    " PROVIDER_STATE,PROVIDER_ZIP , COUNTY,CERTIFICATION_NUMBER FROM EMS_OPS_PROVIDER " +
    " WHERE PROVIDER_CITY = ? order by 2 " ;
     public final static String SELECT_PROVIDER_BY_LEVEL =
    " SELECT PROVIDER_ID,PROVIDE_NAME ,PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY ," +
    " PROVIDER_STATE,PROVIDER_ZIP , COUNTY,CERTIFICATION_NUMBER FROM EMS_OPS_PROVIDER ,EMS_LEVEL" +
    " WHERE EMS_LEVEL.EOP_PROVIDER_ID=EMS_OPS_PROVIDER.PROVIDER_ID AND EMS_LEVEL.LEVEL_NAME = ? order by 2 " ;
     public final static String SELECT_PROVIDER_BY_COUNTY =
    " SELECT PROVIDER_ID,PROVIDE_NAME ,PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY ," +
    " PROVIDER_STATE,PROVIDER_ZIP , COUNTY,CERTIFICATION_NUMBER FROM EMS_OPS_PROVIDER  " +
    " WHERE COUNTY = ? order by 2 " ;
     public final static String SELECT_PROVIDER_BY_LETTER =
    " SELECT PROVIDER_ID,PROVIDE_NAME ,PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY ," +
    " PROVIDER_STATE,PROVIDER_ZIP , COUNTY,CERTIFICATION_NUMBER FROM EMS_OPS_PROVIDER " +
    " WHERE SUBSTR(PROVIDE_NAME,1,1) = ? order by 2 " ;
     public final static String SELECT_FIRST_LETTER_OPTIONS_PROV =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select UPPER(substr(provide_name,1,1)) \"LETTER\" " +
    " FROM ems_ops_provider " +
    " )group by LETTER  order by LETTER ";  
     public final static String SELECT_PROVIDER_BY_HOSPITAL =
    " SELECT PROVIDER_ID,PROVIDE_NAME ,PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY ," +
    " PROVIDER_STATE,PROVIDER_ZIP , COUNTY ,CERTIFICATION_NUMBER FROM EMS_OPS_PROVIDER, OPS_INTERMEDIATE " +
    " WHERE PROVIDER_ID=EOP_PROVIDER_ID AND OH_HOSPITAL_ID = ?  AND OPS_INTERMEDIATE.EXPIRATION_DATE IS NULL order by 2" ;
    
     public final static String SELECT_CITIES_OPTIONS_PROV =
    "select CITY,CITY || ' (' || CITY_COUNT || ')' from ( " +
    "select distinct PROVIDER_CITY \"CITY\", count(*) \"CITY_COUNT\" " +
    " FROM ems_ops_provider " + 
    " group by PROVIDER_CITY ) " +
    " order by city"; 
     public final static String SELECT_COUNTY_OPTIONS_PROV =
    "select COUNTY,COUNTY || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct COUNTY \"COUNTY\", count(*) \"COUNTY_COUNT\" " +
    " FROM ems_ops_provider " + 
    " group by COUNTY ) " +
    " order by COUNTY"; 
     public final static String SELECT_LEVELS_OPTIONS_PROV =
    "select LEVEL_NAME,LEVEL_NAME || ' (' || LEVEL_COUNT || ')' from ( " +
    "select distinct LEVEL_NAME \"LEVEL_NAME\", count(*) \"LEVEL_COUNT\" " +
    " FROM ems_ops_provider,ems_level WHERE ems_level.eop_provider_id=ems_ops_provider.provider_id " + 
    " group by LEVEL_NAME ) " +
    " order by LEVEL_NAME"; 
    
    public final static String SELECT_PROVIDER =
    " SELECT PROVIDER_ID,APPLICATION_REVIEWED,APPLICATION_APPROVED,AUDIT_DATE,BUSINESS_TELEPHONE,CERTIFICATION_NUMBER,COUNTY, " +
    " CERTIFICATION_REQUIRED,MEETING_DATE,CERTIFICATION_DATE,EXPIRATION_DATE,PROVIDE_NAME," +
    " PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_ZIP,PROVIDER_ZIP2 ," +
    " REAPPLY_DATE,VISIT_DATE,REAPPLY_EXPIRE_DATE,TYPE_CODE,DISTRICT,EMERGENCY_TELEPHONE  ," +
    " APPLICATION_REC_DATE,PROVIDER_TYPE,EMAIL,FAX,COMMENTS,INITIAL_AEMT,INITIAL_DFBR,INITIAL_PARAM ," +
    " INITIAL_CERTIFICATION,CERTIFICATION_REQUESTED,DATA_COLLECTION,TACTICAL_EXP_DATE,IHERN_EXP_DATE ," +
    " TERMINATION_DATE,UPDATED_DATE,CONTACT_TELEPHONE,POLICY_NUMBER,EFFECTIVE_DATE ," +
    " INS_EXPIRATION_DATE,CARRIER_NAME " +
    " FROM EMS_OPS_PROVIDER WHERE PROVIDER_ID=? ";
  public final static String SELECT_PROVIDER_BY_CERT =
  " SELECT PROVIDER_ID,APPLICATION_REVIEWED,APPLICATION_APPROVED,AUDIT_DATE,BUSINESS_TELEPHONE,CERTIFICATION_NUMBER,COUNTY, " +
  " CERTIFICATION_REQUIRED,MEETING_DATE,CERTIFICATION_DATE,EXPIRATION_DATE,PROVIDE_NAME," +
  " PROVIDER_ADDRESS1,PROVIDER_ADDRESS2,PROVIDER_CITY,PROVIDER_STATE,PROVIDER_ZIP,PROVIDER_ZIP2 ," +
  " REAPPLY_DATE,VISIT_DATE,REAPPLY_EXPIRE_DATE,TYPE_CODE,DISTRICT,EMERGENCY_TELEPHONE  ," +
  " APPLICATION_REC_DATE,PROVIDER_TYPE,EMAIL,FAX,COMMENTS,INITIAL_AEMT,INITIAL_DFBR,INITIAL_PARAM ," +
  " INITIAL_CERTIFICATION,CERTIFICATION_REQUESTED,DATA_COLLECTION,TACTICAL_EXP_DATE,IHERN_EXP_DATE ," +
  " TERMINATION_DATE,UPDATED_DATE,CONTACT_TELEPHONE,POLICY_NUMBER,EFFECTIVE_DATE ," +
  " INS_EXPIRATION_DATE,CARRIER_NAME " +
  " FROM EMS_OPS_PROVIDER WHERE CERTIFICATION_NUMBER=? ";
     public final static String UPDATE_PROVIDER =
    " UPDATE ems_ops_provider SET APPLICATION_REVIEWED=to_date(?,'mm/dd/yyyy'),APPLICATION_APPROVED=to_date(?,'mm/dd/yyyy'),AUDIT_DATE=to_date(?,'mm/dd/yyyy'),BUSINESS_TELEPHONE=?,CERTIFICATION_NUMBER=?,COUNTY=?, " +
    " CERTIFICATION_REQUIRED=?,MEETING_DATE=to_date(?,'mm/dd/yyyy'),CERTIFICATION_DATE=to_date(?,'mm/dd/yyyy'),EXPIRATION_DATE=to_date(?,'mm/dd/yyyy'),PROVIDE_NAME=?," +
    " PROVIDER_ADDRESS1=?,PROVIDER_ADDRESS2=?,PROVIDER_CITY=?,PROVIDER_STATE=?,PROVIDER_ZIP=?,PROVIDER_ZIP2 =?," +
    " REAPPLY_DATE=to_date(?,'mm/dd/yyyy'),VISIT_DATE=to_date(?,'mm/dd/yyyy'),REAPPLY_EXPIRE_DATE=to_date(?,'mm/dd/yyyy'),TYPE_CODE=null,DISTRICT=?,EMERGENCY_TELEPHONE  =?," +
    " APPLICATION_REC_DATE=to_date(?,'mm/dd/yyyy'),PROVIDER_TYPE=?,EMAIL=?,FAX=?,COMMENTS=?,INITIAL_AEMT=to_date(?,'mm/dd/yyyy'),INITIAL_DFBR=to_date(?,'mm/dd/yyyy'),INITIAL_PARAM =to_date(?,'mm/dd/yyyy')," +
    " INITIAL_CERTIFICATION=to_date(?,'mm/dd/yyyy'),CERTIFICATION_REQUESTED=to_date(?,'mm/dd/yyyy'),DATA_COLLECTION=?,TACTICAL_EXP_DATE=to_date(?,'mm/dd/yyyy'),IHERN_EXP_DATE =to_date(?,'mm/dd/yyyy')," +
    " TERMINATION_DATE=to_date(?,'mm/dd/yyyy'),UPDATED_DATE=to_date(?,'mm/dd/yyyy'),CONTACT_TELEPHONE=null,POLICY_NUMBER=?,EFFECTIVE_DATE =to_date(?,'mm/dd/yyyy')," +
    " INS_EXPIRATION_DATE=to_date(?,'mm/dd/yyyy'),CARRIER_NAME=? " +
    "  WHERE PROVIDER_ID=? ";
    
     public final static String SELECT_PERSON_LIST =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2 ,PR.EOP_PROVIDER_ID,PR.PERSON_TYPE  " +
    " FROM EMS_MEDICAL_PERSONNEL P, EMS_PERSONNEL_PROVIDER PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND PR.EOP_PROVIDER_ID=? order by 3";
    public final static String SELECT_PERSON_PROVIDER =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2,PR.PERSON_TYPE,PR.TITLE,PR.END_DATE   " +
    " FROM EMS_MEDICAL_PERSONNEL P,EMS_PERSONNEL_PROVIDER PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND P.EMS_PERSON_ID=? order by 3";
     public final static String SELECT_PERSON_HOSPITAL =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2,PR.PERSON_TYPE,PR.TITLE,PR.END_DATE   " +
    " FROM EMS_MEDICAL_PERSONNEL P,EMS_PERSONNEL_HOSPITAL PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND P.EMS_PERSON_ID=? order by 3";
     public final static String SELECT_PERSON_INSTITUTION =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2,PR.PERSON_TYPE,PR.TITLE,PR.END_DATE   " +
    " FROM EMS_MEDICAL_PERSONNEL P,EMS_PERSONNEL_INSTITUTION PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND P.EMS_PERSON_ID=? order by 3";
     public final static String UPDATE_PERSON =
    " UPDATE EMS_MEDICAL_PERSONNEL SET FIRST_NAME=?,LAST_NAME=?,MI=?,EMAIL=?,PHONE=?,FAX =?," +
    " ADDRESS1=?,ADDRESS2=?,CITY=?,STATE=?,ZIP=?,ZIP2=? " +
    " WHERE EMS_PERSON_ID=?";
    public final static String SELECT_VEHICLE_LIST =
    " SELECT VEHICLE_ID,CERTIFICATION_NUMBER,INSPECTION_DATE,EXPIRATION_DATE, " +
    "FILE_UPDATION_DATE,CERTIFICATE_ISSUED,CERTIFICATE_REQUESTED,MODEL_YEAR , " +
    "MODEL_MAKE,MODEL_VIN,MODEL_TYPE ,VEHICLE_TYPE,COLOR_SCHEME,CORRECTION_LETTER_DATE, " +
     "MILEAGE,COMMENTS,TEMP_MAKE,TEMP_VIN,TEMP_EXPIRATION, " +
     "EOP_PROVIDER_ID,DISTRICT,CONVERSION_BY,TEMP_MODEL_TYPE,PLATE,TEMP_YEAR,LOCATION,FUEL_TYPE,PREV_CERT_NUMBER,OLD_NEW  " +
     " FROM OPS_VEHICLE WHERE EOP_PROVIDER_ID=? AND nvl(STATUS,'Active')='Active'";
      public final static String SELECT_VEHICLE =
    " SELECT VEHICLE_ID,CERTIFICATION_NUMBER,INSPECTION_DATE,EXPIRATION_DATE, " +
    "FILE_UPDATION_DATE,CERTIFICATE_ISSUED,CERTIFICATE_REQUESTED,MODEL_YEAR , " +
    "MODEL_MAKE,MODEL_VIN,MODEL_TYPE ,VEHICLE_TYPE,COLOR_SCHEME,CORRECTION_LETTER_DATE, " +
     "MILEAGE,COMMENTS,TEMP_MAKE,TEMP_VIN,TEMP_EXPIRATION, " +
     "EOP_PROVIDER_ID,DISTRICT,CONVERSION_BY,TEMP_MODEL_TYPE,PLATE,TEMP_YEAR,LOCATION,FUEL_TYPE,PREV_CERT_NUMBER,OLD_NEW  " +
     " FROM OPS_VEHICLE WHERE VEHICLE_ID=?";
     public final static String UPDATE_VEHICLE =
    " UPDATE OPS_VEHICLE SET CERTIFICATION_NUMBER=?,INSPECTION_DATE=TO_DATE(?,'MM/DD/YYYY'),EXPIRATION_DATE=TO_DATE(?,'MM/DD/YYYY'), " +
    " FILE_UPDATION_DATE=TO_DATE(?,'MM/DD/YYYY'),CERTIFICATE_ISSUED=TO_DATE(?,'MM/DD/YYYY'),CERTIFICATE_REQUESTED=TO_DATE(?,'MM/DD/YYYY'),MODEL_YEAR =?, " +
    " MODEL_MAKE=?,MODEL_VIN=?,MODEL_TYPE =?,VEHICLE_TYPE=?,COLOR_SCHEME=?,CORRECTION_LETTER_DATE=TO_DATE(?,'MM/DD/YYYY'), " +
     " MILEAGE=?,PLATE=?,COMMENTS=?,TEMP_YEAR=?,TEMP_MAKE=?,TEMP_VIN=?,TEMP_EXPIRATION=TO_DATE(?,'MM/DD/YYYY'), " +
     " DISTRICT=?,CONVERSION_BY=?,TEMP_MODEL_TYPE=?  ,LOCATION=?,FUEL_TYPE=?,PREV_CERT_NUMBER=?,OLD_NEW=?" +
     " WHERE VEHICLE_ID=?"; 
      public final static String INSERT_VEHICLE =
    " INSERT INTO OPS_VEHICLE(VEHICLE_ID ,OV_NUMBER ,OWNER_FIRST_NAME ,OWNER_LAST_NAME,OWNER_INITIAL,CERTIFICATION_NUMBER ," +  
    " INSPECTION_DATE,EXPIRATION_DATE,FILE_UPDATION_DATE,CERTIFICATE_ISSUED,CERTIFICATE_REQUESTED,MODEL_YEAR,MODEL_MAKE,MODEL_VIN, "+              
    " MODEL_TYPE,VEHICLE_TYPE,COLOR_SCHEME,CORRECTION_LETTER_DATE,MILEAGE,PLATE,COMMENTS,TEMP_YEAR,TEMP_MAKE,TEMP_VIN,TEMP_EXPIRATION ,"+
    " EOP_PROVIDER_ID,DISTRICT,CONVERSION_BY ,TEMP_MODEL_TYPE,LOCATION,STATUS,FUEL_TYPE,PREV_CERT_NUMBER,OLD_NEW ) "+
    " VALUES(ops_vehicle_id.nextval,NULL,NULL,NULL,NULL,?,TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'), " +
    " TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),?, " +
    " ?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'), " +
     " ?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'), " +
     " ?,?,?,? ,?,'Active',?,?,?) " ; 
      public final static String UPDATE_VEHICLE_LOCATION =
    " UPDATE OPS_VEHICLE SET LOCATION=?" +
     " WHERE VEHICLE_ID=?"; 
       public final static String INACTIVATE_VEHICLE =
    " UPDATE OPS_VEHICLE SET STATUS='Inactive'" +
     " WHERE CERTIFICATION_NUMBER=?"; 
      public final static String UPDATE_VEHICLE_RENEWAL =
    " UPDATE OPS_VEHICLE SET LOCATION=?, MILEAGE=?,PLATE=?,COMMENTS=?,CERTIFICATE_REQUESTED=sysdate" +
     " WHERE VEHICLE_ID=?"; 
         public final static String SELECT_VEHICLE_TYPE_OPTIONS=
     "SELECT description,description FROM dfbs_code where division=9 and canned_code='VEHICLE TYPE'";
       public final static String SELECT_CURRENT_VEHICLES_PROVIDER=
     "SELECT CERTIFICATION_NUMBER,CERTIFICATION_NUMBER FROM OPS_VEHICLE WHERE EOP_PROVIDER_ID=? AND nvl(STATUS,'Active')='Active' order by 1";
     public final static String INSERT_LEVEL =
     " INSERT INTO EMS_LEVEL (OPS_LEVEL_ID,LEVEL_NAME,EOP_PROVIDER_ID,OH_HOSPITAL_ID, " +
     " NSTTTNS_INSTITUTION_ID,LEVEL_NUM) VALUES(ems_ops_level_id.nextval,?,?,NULL,NULL,NULL)";
     public final static String UPDATE_LEVEL =
     " UPDATE EMS_LEVEL SET LEVEL_NAME=? WHERE OPS_LEVEL_ID=?";
       public final static String SELECT_LEVEL_LIST =
     " SELECT OPS_LEVEL_ID,LEVEL_NAME,EOP_PROVIDER_ID,OH_HOSPITAL_ID, " +
     " NSTTTNS_INSTITUTION_ID,LEVEL_NUM FROM EMS_LEVEL WHERE " +
     " EOP_PROVIDER_ID=? order by 2";
       public final static String SELECT_LEVEL =
     " SELECT OPS_LEVEL_ID,LEVEL_NAME,EOP_PROVIDER_ID,OH_HOSPITAL_ID, " +
     " NSTTTNS_INSTITUTION_ID,LEVEL_NUM FROM EMS_LEVEL WHERE " +
     " OPS_LEVEL_ID=?";
      public final static String SELECT_WAIVER_LIST =
     " SELECT WAIVER_ID,WAIVER_CODE,WAIVER_DATE,EL_OPS_LEVEL_ID FROM EMS_OPS_WAIVER WHERE " +
     " EL_OPS_LEVEL_ID=? order by 2";
     public final static String SELECT_WAIVER =
     " SELECT WAIVER_ID,WAIVER_CODE,WAIVER_DATE,EL_OPS_LEVEL_ID FROM EMS_OPS_WAIVER WHERE " +
     " WAIVER_ID=?";
       public final static String INSERT_WAIVER =
     " insert into EMS_OPS_WAIVER " +
     " VALUES(ems_waiver_id.nextval,?,TO_DATE(?,'MM/DD/YYYY'),?)";
      public final static String UPDATE_WAIVER =
     " UPDATE EMS_OPS_WAIVER SET WAIVER_CODE=?,WAIVER_DATE=TO_DATE(?,'MM/DD/YYYY')" +
     " WHERE WAIVER_ID=?";
     public final static String SELECT_HOSPITAL_LIST =
     " SELECT HOSPITAL_ID,HOSPITAL_NAME,ADDRESS1,ADDRESS2,COUNTY,CITY,REAPPLICATION_DATE ," +
     " STATE,ZIP,ZIP2,CERTIFICATION_NUMBER,DISTRICT,INITIAL_CERTIFICATION_DATE,EXPIRATION_DATE ," +
     " TERMINATION,UPDATED,VISIT_DATE,REVIEWED_DATE,APPROVED_DATE,AUDIT_DATE,COMMENTS ," +
     " CERTIFICATE_REQUESTED_DATE  FROM OPS_HOSPITAL order by 2";
     public final static String SELECT_HOSPITAL =
     " SELECT HOSPITAL_ID,HOSPITAL_NAME,ADDRESS1,ADDRESS2,COUNTY,CITY,REAPPLICATION_DATE ," +
     " STATE,ZIP,ZIP2,CERTIFICATION_NUMBER,DISTRICT,INITIAL_CERTIFICATION_DATE,EXPIRATION_DATE ," +
     " TERMINATION,UPDATED,VISIT_DATE,REVIEWED_DATE,APPROVED_DATE,AUDIT_DATE,COMMENTS ," +
     " CERTIFICATE_REQUESTED_DATE  FROM OPS_HOSPITAL WHERE HOSPITAL_ID=? ";
     
      public final static String SELECT_FIRST_LETTER_OPTIONS_HOSP =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select UPPER(substr(hospital_name,1,1)) \"LETTER\" " +
    " FROM ops_hospital " +
    " )group by LETTER  order by LETTER ";  
    
     public final static String SELECT_CITIES_OPTIONS_HOSP =
    "select CITY,CITY || ' (' || CITY_COUNT || ')' from ( " +
    "select distinct CITY \"CITY\", count(*) \"CITY_COUNT\" " +
    " FROM  ops_hospital " + 
    " group by CITY ) " +
    " order by city"; 
     public final static String SELECT_COUNTY_OPTIONS_HOSP =
    "select COUNTY,COUNTY || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct COUNTY \"COUNTY\", count(*) \"COUNTY_COUNT\" " +
    " FROM  ops_hospital " + 
    " group by COUNTY ) " +
    " order by COUNTY"; 
    
     public final static String SELECT_HOSPITAL_BY_CITY =
    " SELECT HOSPITAL_ID,HOSPITAL_NAME,ADDRESS1,ADDRESS2,COUNTY,CITY,STATE,ZIP,ZIP2 " +
    "  FROM OPS_HOSPITAL WHERE CITY = ? order by 2 " ;
     public final static String SELECT_HOSPITAL_BY_COUNTY =
     " SELECT HOSPITAL_ID,HOSPITAL_NAME,ADDRESS1,ADDRESS2,COUNTY,CITY,STATE,ZIP,ZIP2 " +
    "  FROM OPS_HOSPITAL WHERE COUNTY = ?  order by 2" ;
     public final static String SELECT_HOSPITAL_BY_LETTER =
    " SELECT HOSPITAL_ID,HOSPITAL_NAME,ADDRESS1,ADDRESS2,COUNTY,CITY,STATE,ZIP,ZIP2 " +
    "  FROM OPS_HOSPITAL WHERE SUBSTR(HOSPITAL_NAME,1,1) = ? order by 2 " ;
    
     public final static String SELECT_INSTITUTION_LIST =
     " SELECT INSTITUTION_ID,INSTITUTION_NAME,CERTIFICATION_NUMBER,CERTIFICATION_DATE,EXPIRATION_DATE ," +
     " INSTITUTION_ADDRESS1,INSTITUTION_ADDRESS2,INSTITUTION_CITY,INSTITUTION_COUNTY,INSTITUTION_STATE, " +
     " INSTITUTION_ZIP,INSTITUTION_ZIP2,INSTITUTION_TELEPHONE,INSTITUTION_FAX,INSTITUTION_EMAIL ," +
     "  INSTITUTION_TYPE ,RECEIVED_DATE,REVIEWED_DATE,CERTIFICATE_REQUEST_DATE FROM EMS_INSTITUTION  WHERE EXPIRATION_DATE >=SYSDATE order by 2";
     public final static String SELECT_INSTITUTION =
     " SELECT INSTITUTION_ID,INSTITUTION_NAME,CERTIFICATION_NUMBER,CERTIFICATION_DATE,EXPIRATION_DATE ," +
     " INSTITUTION_ADDRESS1,INSTITUTION_ADDRESS2,INSTITUTION_CITY,INSTITUTION_COUNTY,INSTITUTION_STATE, " +
     " INSTITUTION_ZIP,INSTITUTION_ZIP2,INSTITUTION_TELEPHONE,INSTITUTION_FAX,INSTITUTION_EMAIL ," +
     "  INSTITUTION_TYPE ,RECEIVED_DATE,REVIEWED_DATE,CERTIFICATE_REQUEST_DATE " +
     " FROM EMS_INSTITUTION WHERE INSTITUTION_ID=? ";
     
      public final static String SELECT_FIRST_LETTER_OPTIONS_INST =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select UPPER(substr(institution_name,1,1)) \"LETTER\" " +
    " FROM ems_institution WHERE EXPIRATION_DATE >=SYSDATE " +
    " )group by LETTER  order by LETTER ";  
    
     public final static String SELECT_CITIES_OPTIONS_INST =
    "select CITY,CITY || ' (' || CITY_COUNT || ')' from ( " +
    "select distinct INSTITUTION_CITY \"CITY\", count(*) \"CITY_COUNT\" " +
    " FROM  ems_institution WHERE EXPIRATION_DATE >=SYSDATE " + 
    " group by INSTITUTION_CITY ) " +
    " order by city"; 
     public final static String SELECT_COUNTY_OPTIONS_INST =
    "select COUNTY,COUNTY || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct INSTITUTION_COUNTY \"COUNTY\", count(*) \"COUNTY_COUNT\" " +
    " FROM  ems_institution WHERE EXPIRATION_DATE >=SYSDATE " + 
    " group by INSTITUTION_COUNTY ) " +
    " order by COUNTY"; 
    
     public final static String SELECT_INSTITUTION_BY_CITY =
    " SELECT INSTITUTION_ID,INSTITUTION_NAME," +
     " INSTITUTION_ADDRESS1,INSTITUTION_ADDRESS2,INSTITUTION_CITY,INSTITUTION_COUNTY,INSTITUTION_STATE, " +
     " INSTITUTION_ZIP,INSTITUTION_ZIP2 FROM EMS_INSTITUTION WHERE INSTITUTION_CITY = ? AND EXPIRATION_DATE >=SYSDATE " ;
     public final static String SELECT_INSTITUTION_BY_COUNTY =
     " SELECT INSTITUTION_ID,INSTITUTION_NAME," +
     " INSTITUTION_ADDRESS1,INSTITUTION_ADDRESS2,INSTITUTION_CITY,INSTITUTION_COUNTY,INSTITUTION_STATE, " +
     " INSTITUTION_ZIP,INSTITUTION_ZIP2 FROM EMS_INSTITUTION WHERE INSTITUTION_COUNTY = ?  AND EXPIRATION_DATE >=SYSDATE " ;
     public final static String SELECT_INSTITUTION_BY_LETTER =
    " SELECT INSTITUTION_ID,INSTITUTION_NAME," +
     " INSTITUTION_ADDRESS1,INSTITUTION_ADDRESS2,INSTITUTION_CITY,INSTITUTION_COUNTY,INSTITUTION_STATE, " +
     " INSTITUTION_ZIP,INSTITUTION_ZIP2  FROM EMS_INSTITUTION WHERE SUBSTR(INSTITUTION_NAME,1,1) = ? AND EXPIRATION_DATE >=SYSDATE " ;
     
      public final static String SELECT_PERSON_LIST_INST =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2 ,PR.NSTTTNS_INSTITUTION_ID,PR.PERSON_TYPE  " +
    " FROM EMS_MEDICAL_PERSONNEL P, EMS_PERSONNEL_INSTITUTION PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND PR.NSTTTNS_INSTITUTION_ID=? order by 3";
     public final static String SELECT_PERSON_LIST_INST_EMAILS =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,nvl(P.EMAIL,'No Email in Database'),P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2 ,PR.NSTTTNS_INSTITUTION_ID,PR.PERSON_TYPE  " +
    " FROM EMS_MEDICAL_PERSONNEL P, EMS_PERSONNEL_INSTITUTION PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND PR.NSTTTNS_INSTITUTION_ID=? " +
    " AND PR.PERSON_TYPE IN('Medical Director','Training Institution Official') order by 3";
    
    
     public final static String SELECT_PERSON_EMAIL_COUNT =
    " SELECT count(*)  " +
    " FROM EMS_MEDICAL_PERSONNEL P, EMS_PERSONNEL_INSTITUTION PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND PR.NSTTTNS_INSTITUTION_ID=? " +
    " AND PR.PERSON_TYPE IN('Training Institution Official') and P.EMAIL IS NOT NULL ";
     public final static String SELECT_PERSON_LIST_HOSP =
    " SELECT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2 ,PR.OH_HOSPITAL_ID ,PR.PERSON_TYPE " +
    " FROM EMS_MEDICAL_PERSONNEL P, EMS_PERSONNEL_HOSPITAL PR " +
    " WHERE P.EMS_PERSON_ID=PR.EMS_PERSON_ID AND PR.END_DATE IS NULL AND PR.OH_HOSPITAL_ID=? order by 3";
      public final static String SELECT_REPORT_GROUP =
      "SELECT DISTINCT PARAMETER1 FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=99 ";
      
    public final static String SELECT_REPORT_NAMES =
      "SELECT DISTINCT REPORT_NAME,REPORT_CODE FROM DFBS_REPORT_FORMAT " +
      " WHERE DIVISION=99 AND PARAMETER1=?";
      
    public final static String SELECT_PERSON_LIST_LOOKUP =
    " SELECT DISTINCT P.EMS_PERSON_ID,P.FIRST_NAME,P.LAST_NAME,P.MI,P.EMAIL,P.PHONE,P.FAX ," +
    " P.ADDRESS1,P.ADDRESS2,P.CITY,P.STATE,P.ZIP,P.ZIP2  " +
    " FROM EMS_MEDICAL_PERSONNEL P " + 
    " WHERE  UPPER(P.LAST_NAME)=upper(?) order by 4,3";
    public final static String INSERT_PERSON_HOSPITAL =
    " INSERT INTO EMS_PERSONNEL_HOSPITAL VALUES (ems_per_hosp_id.nextval,?,?,sysdate,null,?,?)";
    public final static String INSERT_PERSON_PROVIDER =
    " INSERT INTO EMS_PERSONNEL_PROVIDER VALUES (ems_per_prov_id.nextval,?,?,sysdate,null,?,?)";
    public final static String INSERT_PERSON_INSTITUTION =
    " INSERT INTO EMS_PERSONNEL_INSTITUTION VALUES (ems_per_inst_id.nextval,?,?,sysdate,null,?,?)";
    public final static String UPDATE_PERSON_INSTITUTION =
    " UPDATE EMS_PERSONNEL_INSTITUTION SET person_type=?,title=? ,end_date=to_date(?,'mm/dd/yyyy')" +
    " WHERE ems_person_id=? AND nstttns_institution_id=?" ;
     public final static String UPDATE_PERSON_PROVIDER =
    " UPDATE EMS_PERSONNEL_PROVIDER SET person_type=?,title=? ,end_date=to_date(?,'mm/dd/yyyy')" +
    " WHERE ems_person_id=? AND eop_provider_id=?"; 
     public final static String UPDATE_PERSON_HOSPITAL =
    " UPDATE EMS_PERSONNEL_HOSPITAL SET person_type=?,title=? ,end_date=to_date(?,'mm/dd/yyyy')" +
    " WHERE ems_person_id=? AND oh_hospital_id=?" ;
     public final static String UPDATE_PERSON_EMAIL =
    " UPDATE EMS_MEDICAL_PERSONNEL SET EMAIL=?" +
    " WHERE ems_person_id=? " ;
     public final static String INSERT_PERSON =
    " INSERT INTO EMS_MEDICAL_PERSONNEL VALUES(?,?,?,?,?,?,?," +
    " ?,?,?,?,?,? )" ;
     public final static String SELECT_NEXT_PERSON_ID =
    "select ems_person_id.nextval from dual" ;
    public final static String SELECT_INST_AGMTS_LIST =
    " SELECT AGREEMENT_ID,NSTTTNS_INSTITUTION_ID,EFFECTIVE_DATE,EXPIRATION_DATE,NAME,REMARKS " +
    " FROM EMS_OPS_CLI_SITE_AGMTS WHERE NSTTTNS_INSTITUTION_ID=?";
     public final static String SELECT_INST_AGMTS =
    " SELECT AGREEMENT_ID,NSTTTNS_INSTITUTION_ID,EFFECTIVE_DATE,EXPIRATION_DATE,NAME,REMARKS " +
    " FROM EMS_OPS_CLI_SITE_AGMTS WHERE AGREEMENT_ID=?";
     public final static String UPDATE_INST_AGMTS =
    " UPDATE EMS_OPS_CLI_SITE_AGMTS SET EFFECTIVE_DATE=TO_DATE(?,'MM/DD/YYYY'), " +
    " EXPIRATION_DATE=TO_DATE(?,'MM/DD/YYYY'),NAME=?,REMARKS=? " +
    " WHERE AGREEMENT_ID=?";
     public final static String INSERT_INST_AGMTS =
    " INSERT INTO EMS_OPS_CLI_SITE_AGMTS VALUES(agreement_id.nextval,?,TO_DATE(?,'MM/DD/YYYY'), " +
    " TO_DATE(?,'MM/DD/YYYY'),?,?) " ;
    
     public final static String SELECT_COURSE_LIST =
    " SELECT EMS_COURSE_ID,DAY_CLASS_MEET,DAY_CLASS_MEET_TIME,COURSE_NUMBER,STARTING_DATE, " +          
    " COMPLETION_DATE,LENGTH_COURSE,CRSS_COURSE_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL, " +
    " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,COUNTY_NAME,EMAIL,DATE_RECEIVED, " +         
    " CLASSROOM_LOCATION,TELEPHONE,PPROVED_DATE,PENDING_ITEMS,COMPLETE_REPORT_RECEIVED,RECORD_CREATED_BY " +
     " FROM  EMS_COURSE_DETAIL WHERE  NSTTTNS_INSTITUTION_ID=? AND STARTING_DATE>SYSDATE-365 order by STARTING_DATE desc";
  public final static String SELECT_COURSE_LIST_ADMIN =
  " SELECT EMS_COURSE_ID,DAY_CLASS_MEET,DAY_CLASS_MEET_TIME,COURSE_NUMBER,STARTING_DATE, " +
  " COMPLETION_DATE,LENGTH_COURSE,CRSS_COURSE_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL, " +
  " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,COUNTY_NAME,EMAIL,DATE_RECEIVED, " +
  " CLASSROOM_LOCATION,TELEPHONE,PPROVED_DATE,PENDING_ITEMS,COMPLETE_REPORT_RECEIVED,RECORD_CREATED_BY " +
  " FROM  EMS_COURSE_DETAIL WHERE  STARTING_DATE > SYSDATE-30 order by STARTING_DATE desc ";
      public final static String SELECT_COURSE =
    " SELECT EMS_COURSE_ID,DAY_CLASS_MEET,DAY_CLASS_MEET_TIME,COURSE_NUMBER,STARTING_DATE, " +          
    " COMPLETION_DATE,LENGTH_COURSE,CRSS_COURSE_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL, " +
    " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,COUNTY_NAME,EMAIL,DATE_RECEIVED, " +         
    " CLASSROOM_LOCATION,TELEPHONE ,NSTTTNS_INSTITUTION_ID,PPROVED_DATE,PENDING_ITEMS,COMPLETE_REPORT_RECEIVED,RECORD_CREATED_BY " +
     " FROM  EMS_COURSE_DETAIL WHERE  EMS_COURSE_ID=?";
      public final static String SELECT_COURSE_BY_NUMBER =
    " SELECT EMS_COURSE_ID,DAY_CLASS_MEET,DAY_CLASS_MEET_TIME,COURSE_NUMBER,STARTING_DATE, " +          
    " COMPLETION_DATE,LENGTH_COURSE,CRSS_COURSE_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL, " +
    " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,COUNTY_NAME,EMAIL,DATE_RECEIVED, " +         
    " CLASSROOM_LOCATION,TELEPHONE ,NSTTTNS_INSTITUTION_ID,PPROVED_DATE,PENDING_ITEMS,COMPLETE_REPORT_RECEIVED,RECORD_CREATED_BY " +
     " FROM  EMS_COURSE_DETAIL WHERE  COURSE_NUMBER=?";
     public final static String UPDATE_COURSE =
    " UPDATE EMS_COURSE_DETAIL SET TELEPHONE =?,DAY_CLASS_MEET=?,DAY_CLASS_MEET_TIME=?,COURSE_NUMBER=?,STARTING_DATE=TO_DATE(?,'MM/DD/YYYY'), " +          
    " COMPLETION_DATE=TO_DATE(?,'MM/DD/YYYY'),LENGTH_COURSE=?,CRSS_COURSE_ID=?,FIRST_NAME=?,LAST_NAME=?,MIDDLE_INITIAL=?, " +
    " ADDRESS1=?,ADDRESS2=?,CITY=?,STATE=?,ZIP=?,ZIP2=?,COUNTY_NAME=?,EMAIL=?,DATE_RECEIVED=TO_DATE(?,'MM/DD/YYYY'), " +         
    " CLASSROOM_LOCATION=? ,PPROVED_DATE=TO_DATE(?,'MM/DD/YYYY'),PENDING_ITEMS=?,COMPLETE_REPORT_RECEIVED=?,RECORD_CREATED_BY=?  " +
     " WHERE EMS_COURSE_ID=?"; 
      public final static String UPDATE_COURSE_APPROVAL =
    " UPDATE EMS_COURSE_DETAIL SET PPROVED_DATE=SYSDATE " +
     " WHERE EMS_COURSE_ID=?"; 
      public final static String INSERT_COURSE =
    " INSERT INTO EMS_COURSE_DETAIL(DAY_CLASS_MEET,DAY_CLASS_MEET_TIME,COURSE_NUMBER,STARTING_DATE, " +          
    " COMPLETION_DATE,LENGTH_COURSE,CRSS_COURSE_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL, " +
    " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,COUNTY_NAME,EMAIL,DATE_RECEIVED, " +         
    " CLASSROOM_LOCATION,TELEPHONE ,NSTTTNS_INSTITUTION_ID,EMS_COURSE_ID,PPROVED_DATE,PENDING_ITEMS,COMPLETE_REPORT_RECEIVED,RECORD_CREATED_BY) " +
    " VALUES(?,?,?,TO_DATE(?,'MM/DD/YYYY'), " +
    " TO_DATE(?,'MM/DD/YYYY'),?,?,?,?,?, " +
    " ?,?,?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'), " +
     " ?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?) " ; 
     
      public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
  
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,?)";
    public final static String SELECT_DOCUMENT_NAMES_ALL_BLASTER =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY , DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Blaster/Operator' order by 3";
     public final static String SELECT_DOCUMENT_NAMES_USER_BLASTER =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY ,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='Blaster/Operator' AND DOCUMENT_UPLOADED_BY=? order by 3";
  public final static String SELECT_DOCUMENT_NAMES_ALL =
      " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY , DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? order by 3";
       public final static String SELECT_DOCUMENT_NAMES_USER =
      " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY ,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? AND DOCUMENT_UPLOADED_BY=? order by 3";
       public final static String SELECT_DOCUMENT =
      " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE from DFBS_DOCUMENTS where DOCUMENT_ID=?";
   
   public final static String SELECT_COURSE_LIST_OPTIONS =
   "  SELECT course_id ,course_name" +
   " FROM dfbs_course where division=9 and course_id in(150012,150013,150015,150019) order by course_name";
    public final static String SELECT_COURSE_NAME =
   "  SELECT course_name" +
   " FROM dfbs_course where division=9 and course_id=?";
   
    public final static String SELECT_STATE_LIST_OPTIONS =
   "  SELECT state_initial,state_initial" +
   " FROM dfbs_state  order by 1";
    public final static String SELECT_COUNTY_LIST_OPTIONS =
   "  SELECT county_name,county_name" +
   " FROM dfbs_county  order by 1";
    public final static String SELECT_COURSE_PERSON_TYPE_OPTIONS =
   "  SELECT abbreviation ,description" +
   " FROM dfbs_code where division=9 and canned_code='EMS_COURSE_PERSON_TYPE' order by 1";
   public final static String   INSERT_EMS_INSRTUCTOR =
   " INSERT INTO ems_ops_course_instructor VALUES(instructor_id.nextval,?,?,?,?,?)";
    public final static String   REMOVE_EMS_INSRTUCTOR =
   " DELETE FROM ems_ops_course_instructor WHERE instructor_id=? and course_id=? ";
    public final static String   SELECT_COURSE_INSTRUCTOR =
  " SELECT distinct ad.instructor_id,upper(ad.inst_first_name), upper(ad.inst_last_name),ad.instructor_type,ad.inst_psid " +
    " from ems_ops_course_instructor ad  " +
     " WHERE ad.instructor_id=? and ad.course_id=? ";
    
     public final static String   SELECT_COURSE_INSTRUCTOR_LIST =
   " SELECT distinct ad.instructor_id,upper(ad.inst_first_name), upper(ad.inst_last_name),ad.instructor_type,ad.inst_psid " +
  " from ems_ops_course_instructor ad  " +
   " WHERE  ad.course_id=? ";
      public final static String   SELECT_INST_ORGANIZATION_LIST =
  " SELECT distinct ad.instructor_id,upper(ad.inst_first_name), upper(ad.inst_last_name),ad.instructor_type,ad.inst_psid " +
    " from ems_ops_course_instructor ad, ems_course_detail ec " +
    " where ad.course_id=ec.ems_course_id and ec.nstttns_institution_id=? order by 3,2"; 
   
    
    
     public final static String SELECT_COUNTY_CODE = " SELECT COUNTY_CODE FROM DFBS_COUNTY WHERE COUNTY_NAME=?";
      public final static String SELECT_COURSE_TYPE = " SELECT COURSE_ABBREVIATION FROM DFBS_COURSE WHERE COURSE_ID=?";
      
       public final static String SELECT_COURSE_LIST_SEARCH =
    " SELECT EMS_COURSE_ID,DAY_CLASS_MEET,DAY_CLASS_MEET_TIME,COURSE_NUMBER,STARTING_DATE, " +          
    " COMPLETION_DATE,LENGTH_COURSE,CRSS_COURSE_ID,FIRST_NAME,LAST_NAME,MIDDLE_INITIAL, " +
    " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,COUNTY_NAME,EMAIL,DATE_RECEIVED, " +         
    " CLASSROOM_LOCATION,TELEPHONE,PPROVED_DATE,PENDING_ITEMS,COMPLETE_REPORT_RECEIVED,RECORD_CREATED_BY,INSTITUTION_NAME,INSTITUTION_ID " +
     " FROM  EMS_COURSE_DETAIL,EMS_INSTITUTION WHERE  NSTTTNS_INSTITUTION_ID=INSTITUTION_ID AND COMPLETION_DATE <=SYSDATE";
      public final static String SELECT_INERMEDIATE_NEXT_ID =
      " SELECT  ops_intermediate_id.nextval FROM DUAL";
      
      
        public final static String DELETE_INERMEDIATE =
      " UPDATE OPS_INTERMEDIATE SET EXPIRATION_DATE=SYSDATE WHERE  EOP_PROVIDER_ID =?,OH_HOSPITAL_ID=?";
        public final static String INSERT_INERMEDIATE =
      " INSERT INTO OPS_INTERMEDIATE VALUES(?,?,?,?,sysdate)";
      
      public final static String UPDATE_INSTITUTION =
      "UPDATE EMS_INSTITUTION SET CERTIFICATE_REQUEST_DATE=TO_DATE(?,'MM/DD/YYYY'),INSTITUTION_NAME=?,CERTIFICATION_NUMBER=?,CERTIFICATION_DATE=TO_DATE(?,'MM/DD/YYYY'),EXPIRATION_DATE =TO_DATE(?,'MM/DD/YYYY')," +
     " INSTITUTION_ADDRESS1=?,INSTITUTION_ADDRESS2=?,INSTITUTION_CITY=?,INSTITUTION_COUNTY=?,INSTITUTION_STATE=?, " +
     " INSTITUTION_ZIP=?,INSTITUTION_ZIP2=?,INSTITUTION_TELEPHONE=?,INSTITUTION_FAX=?,INSTITUTION_EMAIL =?," +
     "  INSTITUTION_TYPE =?,RECEIVED_DATE=TO_DATE(?,'MM/DD/YYYY'),REVIEWED_DATE=TO_DATE(?,'MM/DD/YYYY') " +
     "  WHERE INSTITUTION_ID=?  ";    
     
      public final static String UPDATE_HOSPITAL =
      "UPDATE  OPS_HOSPITAL SET CERTIFICATE_REQUESTED_DATE=TO_DATE(?,'MM/DD/YYYY'),HOSPITAL_NAME=?,ADDRESS1=?,ADDRESS2=?,COUNTY=?,CITY=?,REAPPLICATION_DATE =TO_DATE(?,'MM/DD/YYYY')," +
     " STATE=?,ZIP=?,ZIP2=?,CERTIFICATION_NUMBER=?,DISTRICT=?,INITIAL_CERTIFICATION_DATE=TO_DATE(?,'MM/DD/YYYY'),EXPIRATION_DATE =TO_DATE(?,'MM/DD/YYYY')," +
     " TERMINATION=TO_DATE(?,'MM/DD/YYYY'),UPDATED=TO_DATE(?,'MM/DD/YYYY'),VISIT_DATE=TO_DATE(?,'MM/DD/YYYY'),REVIEWED_DATE=TO_DATE(?,'MM/DD/YYYY'),APPROVED_DATE=TO_DATE(?,'MM/DD/YYYY'),AUDIT_DATE=TO_DATE(?,'MM/DD/YYYY'),COMMENTS =?" +
     "  WHERE HOSPITAL_ID=? ";
     public final static String INSERT_DHS_CALENDER =
     " INSERT INTO HS_OWNER.HS_TRAINING_CALENDAR (CALENDAR_ID,CALENDAR_TYPE,COURSE_ID,CALENDAR_TITLE,LOCATION,CITY_COUNTY, " +
     " START_DATE,END_DATE,TEST_DATE,START_TIME,END_TIME,MAX_STUDENTS,COURSE_MANAGER,CONTACT_NUMBER,CONTACT_EMAIL,LEAD_INSTRUCTOR," +
     " LEAD_EVALUATOR,PROCTOR,NOTES,FORM_FILENAME,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,STATUS,DOC_FILENAME )" +
     "VALUES (HS_OWNER.HS_TRAINING_CALENDAR_SEQ.NEXTVAL,'TRAIN_CAL_08',?,?,?,?," +
     " TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),null,?,null,null,?,?,?,null," +
     "null,null,?,null,?,SYSDATE,null,null,'A',null)";

     public final static String SELECT_NEXT_SEC_DETAIL_ID= 
     " select ems_sec_detail_id.nextval from dual";
    
     public final static String  SELECT_COUNT_SEC_DETAIL =
     " select count(*) from dfbs_other_users_group_det where ops_id=? and ops_type =? and user_id=?";
     public final static String  SELECT_COURSE_DATE =
     " select TO_DATE(?,'MM/DD/YYYY')-SYSDATE FROM DUAL";
      public final static String  SELECT_COUNT_COURSE =
      " SELECT COUNT(*) FROM EMS_COURSE_DETAIL WHERE COURSE_NUMBER LIKE ?||'%' AND TO_CHAR(STARTING_DATE,'YYYY')=? ";
     
     public final static String  SELECT_RENEWAL_LIST = 
     " SELECT renewal_id ,eop_provider_id , "+
     " tactical_expiration_date , ihern_expiration_date ,uhf_expiration_date , " +
     " other_expiration_date , other_details , " +
     " uhf_cellular ,uhf_400 ,uhf_800 , " +
     " uhf_other ,dispatch_central ,dispatch_provider ,dispatch_other ,dispatch_other_details , " +
     " ems_247 , ems_247_details ,organization_staffing , " +
     " service_area_chage ,records_location ,waivers_list , " +
     " ems_data_registry , software_used , " +
     " training_daily ,trainig_monthly ,training_quarterly , " + 
     " training_other  , trainig_other_details , " +
     " traing_held ,audit_md ,audit_hosp_committee , " +
     " audit_prov_committee , " +
     " audit_session_monthly ,audit_session_quartrly ,audit_session_other , " +
     " audit_session_other_details,audit_records_with , vehicle_check_daily , " +
     " vehicle_check_weekly ,vehicle_check_monthly , " +
     " vehicle_check_other_details , equipmnt_maintenance , " +
     " veh_int_check_daily ,veh_int_check_weekly ,veh_int_check_monthly , " +
     " veh_int_check_other_details ,vehicle_maintenance, " +
     " VEHICLE_CHECK_OTHER , " +
     " VEH_INT_CHECK_OTHER,renewal_request_date FROM ems_provider_renewal WHERE eop_provider_id=? ";
      public final static String  SELECT_RENEWAL_BY_ID = 
     " SELECT renewal_id ,eop_provider_id , "+
     " tactical_expiration_date , ihern_expiration_date ,uhf_expiration_date , " +
     " other_expiration_date , other_details , " +
     " uhf_cellular ,uhf_400 ,uhf_800 , " +
     " uhf_other ,dispatch_central ,dispatch_provider ,dispatch_other ,dispatch_other_details , " +
     " ems_247 , ems_247_details ,organization_staffing , " +
     " service_area_chage ,records_location ,waivers_list , " +
     " ems_data_registry , software_used , " +
     " training_daily ,trainig_monthly ,training_quarterly , " + 
     " training_other  , trainig_other_details , " +
     " traing_held ,audit_md ,audit_hosp_committee , " +
     " audit_prov_committee , " +
     " audit_session_monthly ,audit_session_quartrly ,audit_session_other , " +
     " audit_session_other_details,audit_records_with , vehicle_check_daily , " +
     " vehicle_check_weekly ,vehicle_check_monthly , " +
     " vehicle_check_other_details , equipmnt_maintenance , " +
     " veh_int_check_daily ,veh_int_check_weekly ,veh_int_check_monthly , " +
     " veh_int_check_other_details ,vehicle_maintenance, " +
     " VEHICLE_CHECK_OTHER , " +
     " VEH_INT_CHECK_OTHER,renewal_request_date FROM ems_provider_renewal WHERE renewal_id=? ";
     
      public final static String  UPDATE_RENEWAL = 
     " UPDATE ems_provider_renewal SET VEH_INT_CHECK_OTHER=? ,eop_provider_id =?, "+
     " tactical_expiration_date =TO_DATE(?,'MM/DD/YYYY'), ihern_expiration_date =TO_DATE(?,'MM/DD/YYYY'),uhf_expiration_date =TO_DATE(?,'MM/DD/YYYY'), " +
     " other_expiration_date =TO_DATE(?,'MM/DD/YYYY'), other_details =?, " +
     " uhf_cellular =?,uhf_400 =?,uhf_800 =?, " +
     " uhf_other =?,dispatch_central =?,dispatch_provider =?,dispatch_other =?,dispatch_other_details =?, " +
     " ems_247 =?, ems_247_details =?,organization_staffing =?, " +
     " service_area_chage =?,records_location =?,waivers_list =?, " +
     " ems_data_registry =?, software_used =?, " +
     " training_daily =?,trainig_monthly =?,training_quarterly =?, " + 
     " training_other  =?, trainig_other_details =?, " +
     " traing_held =?,audit_md =?,audit_hosp_committee =?, " +
     " audit_prov_committee =?, " +
     " audit_session_monthly =?,audit_session_quartrly =?,audit_session_other =?, " +
     " audit_session_other_details=?,audit_records_with =?, vehicle_check_daily =?, " +
     " vehicle_check_weekly =?,vehicle_check_monthly =?, " +
     " vehicle_check_other_details =?, equipmnt_maintenance =?, " +
     " veh_int_check_daily =?,veh_int_check_weekly =?,veh_int_check_monthly =?, " +
     " veh_int_check_other_details =?,vehicle_maintenance=?, " +
     " VEHICLE_CHECK_OTHER =? " +
     "   WHERE renewal_id=? ";
     
      public final static String  INSERT_RENEWAL = 
     " INSERT INTO ems_provider_renewal(renewal_id ,eop_provider_id , "+
     " tactical_expiration_date , ihern_expiration_date ,uhf_expiration_date , " +
     " other_expiration_date , other_details , " +
     " uhf_cellular ,uhf_400 ,uhf_800 , " +
     " uhf_other ,dispatch_central ,dispatch_provider ,dispatch_other ,dispatch_other_details , " +
     " ems_247 , ems_247_details ,organization_staffing , " +
     " service_area_chage ,records_location ,waivers_list , " +
     " ems_data_registry , software_used , " +
     " training_daily ,trainig_monthly ,training_quarterly , " + 
     " training_other  , trainig_other_details , " +
     " traing_held ,audit_md ,audit_hosp_committee , " +
     " audit_prov_committee , " +
     " audit_session_monthly ,audit_session_quartrly ,audit_session_other , " +
     " audit_session_other_details,audit_records_with , vehicle_check_daily , " +
     " vehicle_check_weekly ,vehicle_check_monthly , " +
     " vehicle_check_other_details , equipmnt_maintenance , " +
     " veh_int_check_daily ,veh_int_check_weekly ,veh_int_check_monthly , " +
     " veh_int_check_other_details ,vehicle_maintenance, " +
     " VEHICLE_CHECK_OTHER , VEH_INT_CHECK_OTHER,renewal_request_date ) " +
     " values(?,?, " +
     " TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),?," +
     " ?,?,?,?,?,?,?,?," +
     " ?,?,?,?,?,?,?,?," +
     " ?,?,?,?,?,?,?,?," +
     " ?,?,?,?,?,?,?," +
     " ?,?,?,?,?,?,?," +
     " ?,?,?,?,sysdate)" ;
      public final static String SELECT_RENEWAL_YN_OPTIONS =
   "  SELECT abbreviation,description" +
   " FROM dfbs_code  where canned_code='PROVIDER_RENEWAL_YN' and division=9";
   
    public final static String SELECT_ORGANIZATION_BY_LETTER =
    " SELECT distinct ORGANIZATION FROM ACADIS_DATA " +
    " WHERE UPPER(SUBSTR(ORGANIZATION,1,1)) = UPPER(?) order by 1 " ;
     public final static String SELECT_FIRST_LETTER_OPTIONS_ORG =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select UPPER(substr(ORGANIZATION,1,1)) \"LETTER\" " +
    " FROM ACADIS_DATA " +
    " )group by LETTER  order by LETTER ";  
    public final static String SELECT_TI_MD_EMAIL=
    "SELECT EMAIL FROM EMS_MEDICAL_PERSONNEL WHERE " +
    "EMS_PERSON_ID IN (SELECT EMS_PERSON_ID FROM  EMS_PERSONNEL_INSTITUTION "+
    " WHERE NSTTTNS_INSTITUTION_ID=? AND PERSON_TYPE='Medical Director' AND END_DATE IS NULL AND ROWNUM<2)";
    public final static String SELECT_TI_OFFICIAL_EMAIL=
    "SELECT EMAIL FROM EMS_MEDICAL_PERSONNEL WHERE " +
    "EMS_PERSON_ID IN (SELECT EMS_PERSON_ID FROM  EMS_PERSONNEL_INSTITUTION "+
    " WHERE NSTTTNS_INSTITUTION_ID=? AND PERSON_TYPE='Training Institution Official' AND END_DATE IS NULL AND ROWNUM<2)";
    public final static String SELECT_PERSON_TYPE_OPTIONS=
     "SELECT description,description FROM dfbs_code where division=9 and canned_code='PERSON_TYPE'";
      
 
}

