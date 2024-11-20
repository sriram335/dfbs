package code.data;
import code.to.*;
import code.data.*;

public class CodeSQL 
{
 /* SEQUENCE SQL */
    public final static String SELECT_NEXT_ENTITY_ID =
    "select code_name_id.nextval from dual";
    
     public final static String SELECT_NEXT_TRANSACTION_ID =
    "select TRANSACTION_ID.NEXTVAL from dual";
    
     public final static String SELECT_NEXT_FACILITY_ID =
    "select code_facility_id.nextval from dual";
    
     public final static String SELECT_NEXT_PERSON_ID =
    "select code_person_id.nextval from dual";
    
     public final static String SELECT_NEXT_FACILITY_RELEASE_ID =
    "select code_fac_rel_id.nextval from dual" ;
     public final static String SELECT_NEXT_RELEASE_ID =
    "select code_system_id.nextval from dual" ;
      
     public final static String SELECT_NEXT_REL_DESIGNER_ID =
    "select code_rel_designer_id.nextval from dual" ;
    
    /* OPTIONS SQL */
    public final static String SELECT_STATE_OPTIONS =
     "select state_id,state_name from dfbs_state order by state_name";
     
    public final static String SELECT_STATE_OPTIONS2 =
    "select state_initial,state_name from dfbs_state order by state_name";
     
     public final static String SELECT_COUNTY_OPTIONS =
     "select county_code,county_name from dfbs_county order by county_name";
    
    public final static String SELECT_OCCUPANCY_OPTIONS =
     "select description,abbreviation from dfbs_code where canned_code='CDR_OCCUPANCY' and division=13";
     public final static String SELECT_SYSTEM_OPTIONS =
     "select abbreviation,description from dfbs_code where canned_code='CDR_TYPE' and division=13";
      public final static String SELECT_FILING_OPTIONS =
     "select abbreviation,description from dfbs_code where canned_code='CDR_FILING_STATUS' and division=13";
      public final static String SELECT_CDR_SYSTEM_TYPE_OPTIONS =
     "select abbreviation,description from dfbs_code where canned_code='CODE_SYSTEM_TYPE' and division=13";
     public final static String SELECT_CDR_REVIEWER_OPTIONS =
     " SELECT distinct  inspector_id, first_name||' '||last_name name " +
     " FROM dfbs_inspector    WHERE division=13 ORDER BY 1 ";
     /* OWNER SQL */
    public final static String SELECT_MANUFACTURER_PREFIX =
    "select b.MNFCTR_ENTITY_ID,a.MANUFACTURER_NAME, " +
    "a.MNFCTR_ADDRESS1,a.MNFCTR_ADDRESS2,a.MNFCTR_CITY,a.MNFCTR_STATE,a.MNFCTR_POSTAL_CODE,a.MNFCTR_COUNTY, " +
    "a.MNFCTR_COUNTRY,a.MNFCTR_NAME_STATUS,a.MNFCTR_WEB_PAGE,a.MNFCTR_NAME_ID ";
   
   
    public final static String SELECT_MANUFACTURER_FROM =
    "from code_mnfctr_name_addr a ,code_mnfctr_name_xref  b "; 
    
   
    public final static String SELECT_MANUFACTURER_SUFFIX =
    "order by upper(a.MANUFACTURER_NAME) ";
   
    public final static String SELECT_MANUFACTURER =
    SELECT_MANUFACTURER_PREFIX + SELECT_MANUFACTURER_FROM +
    "WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID  AND  a.MNFCTR_NAME_ID=? ";

    public final static String SELECT_MANUFACTURER_ALL =
    SELECT_MANUFACTURER_PREFIX + SELECT_MANUFACTURER_FROM + 
    " WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID " + SELECT_MANUFACTURER_SUFFIX;  
   
    public final static String SELECT_MANUFACTURER_BY_LETTER =
    SELECT_MANUFACTURER_PREFIX + SELECT_MANUFACTURER_FROM + 
    "WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID  AND substr(a.MANUFACTURER_NAME,1,1) = ? " +
    SELECT_MANUFACTURER_SUFFIX;  
   
    public final static String SELECT_MANUFACTURER_BY_CITY =
    SELECT_MANUFACTURER_PREFIX + SELECT_MANUFACTURER_FROM + 
    "WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID  AND a.MNFCTR_CITY = ?  " +
    SELECT_MANUFACTURER_SUFFIX;  
   
    public final static String SELECT_MANUFACTURER_BY_OTHER_STATE =
    SELECT_MANUFACTURER_PREFIX + SELECT_MANUFACTURER_FROM + 
    "WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID  AND a.MNFCTR_STATE = ? " +
    SELECT_MANUFACTURER_SUFFIX;  
   
    public final static String SELECT_FIRST_LETTER_OPTIONS =
    "select distinct LETTER, count(*) \"COUNT\"  from ( " +
    "select substr(a.MANUFACTURER_NAME,1,1) \"LETTER\" " +
    SELECT_MANUFACTURER_FROM + " WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID " +
    " )group by LETTER  order by LETTER ";
   
   public final static String SELECT_CITIES_OPTIONS =
    "select  CITY,CITY || ', ' || STATE_NAME || ' (' || STATE_COUNT || ')' from ( " +
    "select distinct a.MNFCTR_CITY \"CITY\",a.MNFCTR_STATE \"STATE_NAME\", count(*) \"STATE_COUNT\" " +
    SELECT_MANUFACTURER_FROM + " WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID " +
     "group by a.MNFCTR_CITY,a.MNFCTR_STATE ) " +
    "order by CITY";
      
    public final static String SELECT_OTHER_STATES_OPTIONS =
    "select STATE,STATE || ' (' || STATE_COUNT || ')' from ( " +
    "select distinct a.MNFCTR_STATE \"STATE\", count(*) \"STATE_COUNT\" " +
    SELECT_MANUFACTURER_FROM + 
    "WHERE a.MNFCTR_NAME_ID=b.MNFCTR_NAME_ID AND a.MNFCTR_STATE <> 'IN' " +
    "group by a.MNFCTR_STATE ) " +
    "order by state"; 

    public final static String SELECT_PERSON_MANUFACTURER =
    " SELECT " +
    " PERSON_ID,PERSON_TYPE,PERSON_LAST_NAME,PERSON_FIRST_NAME, " +
    " PERSON_MIDDLE_NAME,PERSON_TITLE,PERSON_TELEPHONE,PERSON_FAX,PERSON_E_MAIL " +
    " FROM CODE_PERSON WHERE PERSON_TYPE='M' AND COMPANY_ID=? ";
    
     public final static String SELECT_PERSON_FACILITY =
    " SELECT " +
    " PERSON_ID,PERSON_TYPE,PERSON_LAST_NAME,PERSON_FIRST_NAME, " +
    " PERSON_MIDDLE_NAME,PERSON_TITLE,PERSON_TELEPHONE,PERSON_FAX,PERSON_E_MAIL " +
    " FROM CODE_PERSON WHERE PERSON_TYPE='F' AND COMPANY_ID=? ";
    
    public final static String INSERT_PERSON =
    "insert into CODE_PERSON " +
    "(PERSON_ID,PERSON_TYPE,PERSON_LAST_NAME,PERSON_FIRST_NAME, " +
    " PERSON_MIDDLE_NAME,PERSON_TITLE,PERSON_TELEPHONE,PERSON_FAX,PERSON_E_MAIL,COMPANY_ID) " +
    "values (?,?,?,?,?,?,?,?,?,?)";
    
    public final static String UPDATE_PERSON =
    "UPDATE CODE_PERSON " +
    "set PERSON_TYPE=?,PERSON_LAST_NAME=?,PERSON_FIRST_NAME=?, " +
    "PERSON_MIDDLE_NAME=?,PERSON_TITLE=?,PERSON_TELEPHONE=?,PERSON_FAX=?,PERSON_E_MAIL=? " +
    "WHERE PERSON_ID=?";
  
     /* PERMIT SQL */

    public final static String SELECT_CONSUMER_PERMITS_TO_PRINT =
    "select " +
    "IDENTIFICATION_NUMBER, " +
    "ISSUE_DATE, " +
    "TO_CHAR(ISSUE_DATE,'yyyy'), " +
    "last_name || ' ' || first_name , " +
    "SALES_ADDRESS1,SALES_ADDRESS2,SALES_CITY,SALES_STATE,SALES_ZIP,a.WNRS_OWNER_ID " +
    "from FIRE_SALES  a,DFBS_OWNER b " +
    "where IDENTIFICATION_NUMBER IN ( " +
    "select FRSALES_IDENTIFICATION_NUMBER  " +
    "from DFBS.FIRE_INSPECTION  " +
    "where FRSALES_IDENTIFICATION_NUMBER is not null  " +
    "and type5 = 'Y') " +
    "and a.WNRS_OWNER_ID = b.OWNER_ID and a.ISSUE_DATE is not null  " +
    "and to_char(a.ISSUE_DATE,'yyyy') >= to_char(SYSDATE,'yyyy') ";
  
   public final static String SELECT_FACILITIES =
    "select  DISTINCT " +
    " FACILITY_ID  ,FACILITY_NAME||','|| "   +
    " FACILITY_ADDRESS1||','|| FACILITY_CITY||','||FACILITY_STATE ||','|| " +       
    " FACILITY_POSTAL_CODE " +
    " FROM CODE_FACILITY ORDER BY 2 ASC ";
   
    public final static String SELECT_FACILITY =
    "select  " +
    " FACILITY_ID  , MNFCTR_ENTITY_ID,INSPECTOR_ID, INSPCTN_COMPANY_ID ,FACILITY_NAME, "   +
    " FACILITY_ADDRESS1,FACILITY_ADDRESS2, FACILITY_CITY,FACILITY_STATE ,FACILITY_COUNTRY, " +       
    " FACILITY_POSTAL_CODE,FACILITY_COUNTY , FACILITY_STATUS ,SAA_AUDITOR_ID , FACILITY_TYPE_HUD, " +     
    " FACILITY_TYPE_MOC , FACILITY_TYPE_MOR ,FACILITY_TYPE_MOB ,FACILITY_TYPE_PAN ,SAA_AUDITOR_ID2 " +
    " FROM CODE_FACILITY WHERE  MNFCTR_ENTITY_ID=?";
    
     public final static String SELECT_FACILITY_BY_ID =
    "select  " +
    " FACILITY_ID  , MNFCTR_ENTITY_ID,INSPECTOR_ID, INSPCTN_COMPANY_ID ,FACILITY_NAME, "   +
    " FACILITY_ADDRESS1,FACILITY_ADDRESS2, FACILITY_CITY,FACILITY_STATE ,FACILITY_COUNTRY, " +       
    " FACILITY_POSTAL_CODE,FACILITY_COUNTY , FACILITY_STATUS ,SAA_AUDITOR_ID , FACILITY_TYPE_HUD, " +     
    " FACILITY_TYPE_MOC , FACILITY_TYPE_MOR ,FACILITY_TYPE_MOB ,FACILITY_TYPE_PAN ,SAA_AUDITOR_ID2 " +
    " FROM CODE_FACILITY WHERE  FACILITY_ID=?";

    public final static String SELECT_FEES =
    "select " +
    "decode ( " +
    "CANNED_CODE, " +
    "'CODE_SEAL_FEE',1, " +
    "'CODE_CDR_FEE_FILING',2, " +
    "'CODE_CDR_FEE_FILING_LATE',3, " +
    "'CODE_CDR_RES_FEE',4, " +
    "'CODE_CDR_RES_FEE_LATE',5, " +
    "'CODE_CDR_COM_FEE',6, " +
    "'CODE_CDR_COM_FEE_LATE',7, " +
    "'CODE_CDR_ADD_FEE',8, " +
    "'CODE_CDR_ADD_FEE_LATE',9, " +
    "'CODE_CDR_ADD_RES_FEE',10, " +
    "'CODE_CDR_ADD_RES_FEE_LATE',11, " +
    "'CODE_CDR_ADD_COM_FEE',12, " +
    "'CODE_CDR_ADD_COM_FEE_LATE',13, " +
    "'CODE_CDR_ADDN_PLAN_FEE',14, " +
    "'CODE_CDR_ADDN_MODULE_FEE',15, " +
    "'Other',0, " +
    "'0' " +
    ") \"FEE_TYPE\",  " +
    " ABBREVIATION \"AMOUNT\" " +
    "from DFBS_CODE " +
    "where (CANNED_CODE like 'CODE%FEE%') AND DIVISION=13 ";

    public final static String SELECT_SEAL_FEES =
    "select DESCRIPTION,ABBREVIATION " +
    "from DFBS_CODE " +
    "where (CANNED_CODE='CODE_SEAL_FEE')";

    public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION) " +
    "values(?,0,substr(?,1,255),UPPER(?),?,?,?,SYSDATE,13) ";

   public final static String INSERT_CDR = 
    "insert into CODE_PRODUCT " +
    "(SYSTEM_ID,MNFCTR_ENTITY_ID,SYSTEM_TYPE_CODE,UNIT_LENGTHS_COMMENT,BLDG_TYPE_OCPNCY_," +
    " NMBR_OF_STORIES,NMBR_UNITS_TOTAL,CMPLTD_UNIT_WIDTH,NMBR_PERSONS_PUBLIC," +
    " NMBR_PERSONS_EMPLOYED,GENERAL_COMMENTS, " +
    " SYSTEM_RELEASE_NMBR,ADD_RELEASE_NMBR,ADDENDUM_SEQUENCE,CODE_REFERENCE," +
    " CONSTRUCTION_TYPE,SYSTEM_RELEASE_DATE,REVIEWER_ID,LATE_FILING,STRUCTURE_USE_DESC," +
    " RELEASE_TYPE,BUILDING_TYPE,PREV_SYSTEM_RELEASE_NMBR,ADDITIONAL_PLANS,ADDITIONAL_MODULES," +
    " BUILDING_OCCUPANCY,SCOPE_OF_RELEASE,RELEASE_APPROVAL)" +
     " values (?,?,?,?,?,?,?,?,?,?,?," +
     " ?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,?,?,?,?,?,?,?,?,?,?)" ; 
     public final static String UPDATE_CDR = 
    "UPDATE CODE_PRODUCT SET " +
    " LATE_FILING=?,MNFCTR_ENTITY_ID=?,SYSTEM_TYPE_CODE=?,UNIT_LENGTHS_COMMENT=?,BLDG_TYPE_OCPNCY_=?," +
    " NMBR_OF_STORIES=?,NMBR_UNITS_TOTAL=?,CMPLTD_UNIT_WIDTH=?,NMBR_PERSONS_PUBLIC=?," +
    " NMBR_PERSONS_EMPLOYED=?,GENERAL_COMMENTS =?," +
    " SYSTEM_RELEASE_NMBR=?,ADD_RELEASE_NMBR=?,ADDENDUM_SEQUENCE=?,CODE_REFERENCE=?," +
    " CONSTRUCTION_TYPE=?,SYSTEM_RELEASE_DATE=TO_DATE(?,'MM/DD/YYYY'),REVIEWER_ID=?," +
    " STRUCTURE_USE_DESC=? ,RELEASE_TYPE=? ,BUILDING_OCCUPANCY=? ,BUILDING_TYPE=? ," +
    " PREV_SYSTEM_RELEASE_NMBR=? ,ADDITIONAL_PLANS=?,ADDITIONAL_MODULES=?,SCOPE_OF_RELEASE=? ,RELEASE_APPROVAL=? where SYSTEM_ID=?" ;  
     public final static String SELECT_CDR = 
    "SELECT " +
    " SYSTEM_ID,LATE_FILING,MNFCTR_ENTITY_ID,SYSTEM_TYPE_CODE,UNIT_LENGTHS_COMMENT,BLDG_TYPE_OCPNCY_," +
    " NMBR_OF_STORIES,NMBR_UNITS_TOTAL,CMPLTD_UNIT_WIDTH,NMBR_PERSONS_PUBLIC," +
    " NMBR_PERSONS_EMPLOYED,GENERAL_COMMENTS, " +
    " SYSTEM_RELEASE_NMBR,ADD_RELEASE_NMBR,ADDENDUM_SEQUENCE,CODE_REFERENCE," +
    " CONSTRUCTION_TYPE,SYSTEM_RELEASE_DATE,REVIEWER_ID,STRUCTURE_USE_DESC, RELEASE_TYPE," +
    " BUILDING_OCCUPANCY,BUILDING_TYPE,CODE_REFERENCE,ADDITIONAL_PLANS,ADDITIONAL_MODULES," +
    " PREV_SYSTEM_RELEASE_NMBR,SCOPE_OF_RELEASE,RELEASE_APPROVAL " +
    " FROM CODE_PRODUCT WHERE SYSTEM_ID=?" ;
  public final static String INSERT_CDR_UNIT_CONTAINS =
    " insert into CODE_PRDCT_SPCL_FEATURE " +
    "(SYSTEM_ID,SPECIAL_FEATURE) " +
    " values (?,?)" ;
   public final static String SELECT_CDR_UNIT_CONTAINS =
    " SELECT SYSTEM_ID,SPECIAL_FEATURE FROM CODE_PRDCT_SPCL_FEATURE WHERE SYSTEM_ID=? " ;
     public final static String SELECT_CDR_FACILITIES =
    " SELECT to_char(FACILITY_ID),SYSTEM_ID FROM CODE_FACILITY_RELEASE WHERE SYSTEM_ID=? " ;
  public final static String INSERT_CDR_CODE =
    " insert into CODE_SYSTEM_RLS_CNDTN " +
    "(SYSTEM_ID,CONDITION_ID,CONDITION_CODE) " +
    " values (?,code_condition_id.nextval,?)" ;
     public final static String INSERT_CDR_FACILITIES =
     "insert into CODE_FACILITY_RELEASE " +
     "(CODE_FAC_REL_ID,FACILITY_ID,SYSTEM_ID)" +
     " values(?,?,?)";
   public final static String SELECT_RELEASES =  
   " select SYSTEM_ID,SYSTEM_ID_XREF,SYSTEM_RELEASE_NMBR,ADD_RELEASE_NMBR,REVIEWER_ID, " +
   " MNFCTR_ENTITY_ID,SYSTEM_TYPE_CODE,UNIT_LENGTHS_COMMENT,BLDG_TYPE_OCPNCY_,NMBR_OF_STORIES, " +
   " NMBR_UNITS_TOTAL,CMPLTD_UNIT_WIDTH,STRUCTURE_USE_DESC,NMBR_PERSONS_PUBLIC, " +
   " NMBR_PERSONS_EMPLOYED,GENERAL_COMMENTS,SCOPE_OF_RELEASE,ADDENDUM_SEQUENCE,CODE_REFERENCE, " +
   " CONSTRUCTION_TYPE,BUILDING_OCCUPANCY,SYSTEM_RELEASE_DATE,LATE_FILING,RELEASE_TYPE, " +
   " RELEASE_APPROVAL  from code_product WHERE MNFCTR_ENTITY_ID=? order by SYSTEM_RELEASE_DATE desc" ;     
  
  
   public final static String UPDATE_MANUFACTURER =
  "  update code_mnfctr_name_addr SET MNFCTR_WEB_PAGE=? where MNFCTR_NAME_ID=? ";
  
   public final static String INSERT_FACILITY_SEALS =
   " insert into CODE_FACILITY_SEALS_SOLD " +
   " (FACILITY_ID,M_SEALS,P_SEALS,SOLD_DATE,ORDER_ID) " +
   " values(?,?,?,sysdate,?)";
   
    public final static String UPDATE_FACILITY_SEALS =
   " UPDATE CODE_FACILITY_SEALS_SOLD " +
   " set M_SEALS_SENT_FROM=?,M_SEALS_SENT_TO=?,P_SEALS_SENT_FROM=?,P_SEALS_SENT_TO=? " +
   " WHERE ORDER_ID=?";
   
   public final static String SELECT_FACILITY_SEALS =
   " SELECT  FACILITY_ID,M_SEALS,P_SEALS,SOLD_DATE,ORDER_ID,M_SEALS_SENT_FROM,M_SEALS_SENT_TO,P_SEALS_SENT_FROM,P_SEALS_SENT_TO " +
   " FROM CODE_FACILITY_SEALS_SOLD WHERE FACILITY_ID=?   ";
   
    public final static String SELECT_SEALS_BY_ORDER =
   " SELECT  FACILITY_ID,M_SEALS,P_SEALS,SOLD_DATE,ORDER_ID,M_SEALS_SENT_FROM,M_SEALS_SENT_TO,P_SEALS_SENT_FROM,P_SEALS_SENT_TO " +
   " FROM CODE_FACILITY_SEALS_SOLD WHERE ORDER_ID= ?";
   
   
    public final static String SELECT_CODE_CONTACTS =
   " SELECT canned_code,description,abbreviation,section from DFBS_CODE " +
    " WHERE canned_code in('MAGAZINE_CONTACT1','MAGAZINE_CONTACT2')";
    
    public final static String SELECT_RELEASE_DESIGNER =  
    " SELECT DESIGNER_ID,FIRM_NAME,DESIGNER_FIRST_NAME,DESIGNER_LAST_NAME,ADDRESS1, " +
    " ADDRESS2,CITY ,STATE,ZIP,DESIGNER_IND_NUMBER,DESIGNER_TYPE ,PHONE,EMAIL  " +
    " FROM CODE_RELEASE_DESIGNER WHERE SYSTEM_ID=?";
   
    public final static String UPDATE_RELEASE_DESIGNER =  
    " UPDATE CODE_RELEASE_DESIGNER SET FIRM_NAME=?,DESIGNER_FIRST_NAME=?,DESIGNER_LAST_NAME=?,ADDRESS1=?, " +
    " ADDRESS2=?,CITY =?,STATE=?,ZIP=?,DESIGNER_IND_NUMBER=?,DESIGNER_TYPE =?,PHONE=?,EMAIL =? " +
    " WHERE SYSTEM_ID=?";
    
     public final static String INSERT_RELEASE_DESIGNER =  
    " INSERT INTO CODE_RELEASE_DESIGNER (DESIGNER_ID,FIRM_NAME,DESIGNER_FIRST_NAME,DESIGNER_LAST_NAME,ADDRESS1, " +
    " ADDRESS2,CITY ,STATE,ZIP,DESIGNER_IND_NUMBER,DESIGNER_TYPE ,PHONE,EMAIL,SYSTEM_ID)  " +
    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
     public final static String SELECT_STANDARD_CODE =
     " SELECT  code_id,description,abbreviation FROM dfbs_code WHERE canned_code='CANNED_CODE' AND division=13 ";  
      public final static String SELECT_ADD_CODE =
     " SELECT  description,abbreviation FROM dfbs_code WHERE canned_code='CANNED_CODE' AND division=13 AND CODE_ID=?"; 
     
      public final static String SELECT_CDR_CODE =
    " SELECT SYSTEM_ID,CONDITION_ID,CONDITION_CODE FROM CODE_SYSTEM_RLS_CNDTN WHERE SYSTEM_ID=?" ;
    
      public final static String REMOVE_SAVED_CODE =
    " DELETE FROM CODE_SYSTEM_RLS_CNDTN WHERE CONDITION_ID=?" ;
    
       public final static String SELECT_SAVED_CODE =
    " SELECT SYSTEM_ID,CONDITION_ID,CONDITION_CODE FROM CODE_SYSTEM_RLS_CNDTN WHERE CONDITION_ID=?" ;
    
      public final static String SELECT_ACCT_DUES =
      "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description,unique_number,owner_id " +
      " FROM dfbs_fee WHERE unique_number=? and post_date >sysdate-365 AND DIVISION=13  ORDER BY POST_DATE desc";
      
      public final static String INSERT_CODE_SEAL =
      " INSERT into CODE_SEAL( SEAL_NMBR,MNFCTR_ENTITY_ID,FACILITY_ID,INSPECTOR_ID,INSPCTN_COMPANY_ID,MNFCTR_NAME_ID," +
      " SEAL_PURCHASE_DATE,UNIT_SERIAL_NMBR,RELEASE_NMBR,DATE_SEAL_USED,SEAL_UPDATE_DATE,SEAL_INSPECTED_DATE,ORDER_ID)" +
      " VALUES(?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),NULL,NULL,NULL,NULL,NULL,?)";
      
       public final static String SELECT_CODE_SEAL_BY_ORDER =
      " SELECT SEAL_NMBR,FACILITY_ID,INSPECTOR_ID,INSPCTN_COMPANY_ID,MNFCTR_NAME_ID," +
      " SEAL_PURCHASE_DATE,UNIT_SERIAL_NMBR,RELEASE_NMBR,DATE_SEAL_USED,SEAL_UPDATE_DATE,SEAL_INSPECTED_DATE,ORDER_ID " +
      " FROM CODE_SEAL WHERE ORDER_ID=? order by 1 ";
       public final static String SELECT_CODE_SEAL_ORDER_STATUS =
      " SELECT COUNT(*) " +
      " FROM CODE_SEAL WHERE ORDER_ID=? ";
       public final static String SELECT_CODE_SEAL_BY_NUMBER =
      " SELECT SEAL_NMBR,FACILITY_ID,INSPECTOR_ID,INSPCTN_COMPANY_ID,MNFCTR_NAME_ID," +
      " SEAL_PURCHASE_DATE,UNIT_SERIAL_NMBR,RELEASE_NMBR,DATE_SEAL_USED,SEAL_UPDATE_DATE,SEAL_INSPECTED_DATE,ORDER_ID " +
      " FROM CODE_SEAL WHERE SEAL_NMBR=? order by 1 ";
      public final static String SELECT_INSP_COMPANY_OPTIONS =
      " SELECT inspctn_company_id, inspctn_cmpny_name " +
      " FROM code_inspection_company   order by inspctn_cmpny_name";
      public final static String SELECT_INSPECTOR_OPTIONS =
      " SELECT inspector_id, last_name||', '||first_name||' '||middle_initial inspctr_name " +
      " FROM dfbs_inspector     WHERE division = 4       AND inspector_type = 'DBC-CON' " +
      " ORDER BY last_name, first_name, middle_initial"  ;
      
       public final static String SELECT_COMPANY_NAME =
      " SELECT  inspctn_cmpny_name " +
      " FROM code_inspection_company  WHERE inspctn_company_id=?";
      
        public final static String SELECT_INSPECTOR_NAME =
      " SELECT  last_name||', '||first_name||' '||middle_initial inspctr_name " +
      " FROM dfbs_inspector     WHERE division = 4       AND inspector_type = 'DBC-CON' " +
      " AND inspector_id=? " ;
      
        public final static String UPDATE_CODE_SEAL =
      " UPDATE CODE_SEAL SET UNIT_SERIAL_NMBR=?,RELEASE_NMBR=?,DATE_SEAL_USED=TO_DATE(?,'MM/DD/YYYY'),"+
      " SEAL_UPDATE_DATE=TO_DATE(?,'MM/DD/YYYY'),SEAL_INSPECTED_DATE=TO_DATE(?,'MM/DD/YYYY')" +
      "  WHERE SEAL_NMBR=?  ";
      
       public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,'CodeRelease')";
    public final static String SELECT_DOCUMENT_NAMES_ALL =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='CodeRelease' order by 3";
     public final static String SELECT_DOCUMENT_NAMES_USER =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='CodeRelease' AND DOCUMENT_UPLOADED_BY=? order by 3";
     public final static String SELECT_DOCUMENT =
    " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE,DOCUMENT_ID from DFBS_DOCUMENTS where DOCUMENT_ID=?";
    public final static String SELECT_DOCUMENT_NAME =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_ID " +
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=?  ";
    public final static String UPDATE_DOCUMENT_NAME =
    " UPDATE DFBS_DOCUMENTS set DOCUMENT_CONNECTOR=? where DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? ";
     public final static String SELECT_DOCUMENT_COUNT =
    " SELECT COUNT(*) " +
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? ";
}
