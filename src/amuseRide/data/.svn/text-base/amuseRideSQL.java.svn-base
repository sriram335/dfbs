package amuseRide.data;

public class amuseRideSQL {
  public final static String SELECT_NEXT_OWNER_ID =
      "select OWNER_ID.NEXTVAL from dual";
      
      public final static String SELECT_NEXT_ADDRESS_ID =
      "select ADDRESS_ID.NEXTVAL from dual";
      public final static String SELECT_NEXT_APP_ID =
      "select eapplicant_id.nextval from dual"; 
    public final static String SELECT_NEXT_INS_ID = 
    "  SELECT insurance_id.nextval from DUAL ";
      public final static String SELECT_NEXT_TRANSACTION_ID =
      "select TRANSACTION_ID.NEXTVAL from dual";
      public final static String SELECT_ANNUAL_FEE =  
      " SELECT amount from dfbs_fee_structure " +
      " where  device_type='ANNUAL_PERMIT' and division=1" ;
       public final static String SELECT_NEXT_PERSON_ID =
      "select PERSON_ID.NEXTVAL from dual";
     
      
      /* OPTIONS SQL */
      public final static String SELECT_STATE_OPTIONS =
       "select state_id,state_name from dfbs_state order by state_name";
       
      public final static String SELECT_STATE_OPTIONS2 =
      "select state_initial,state_name from dfbs_state order by state_name";
  public final static String SELECT_STATE_OPTIONS3 =
  "select state_initial,state_initial from dfbs_state order by state_name";
       
       public final static String SELECT_COUNTY_LIST_OPTIONS =
       "select county_code,county_name from dfbs_county order by 2";
       
        public final static String SELECT_DEVICE_LIST_OPTIONS =
        "select DEVICE_ID,DEVICE_DESCRIPTION from dfbs_DEVICE WHERE DIVISION=1 AND DEVICE_ID >0 order by 2"; 
        
        public final static String SELECT_COUNTY_CODE =
       "select county_code from dfbs_county where county_name=?";
        
  public final static String SELECT_OWNERS_PREFIX =
  "select distinct a.owner_id,a.doing_business_as, " +
  "a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
  " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
  " a.telephone,b.fax,b.E_MAIL,b.STTS_STATE_ID ";
  
  
  public final static String SELECT_OWNERS_FROM =
  " from dfbs_owner a , dfbs_address b ,amuse_owner_ride x,dfbs_state c ,amuse_ride y " +
  " where a.owner_id=b.wnrs_owner_id  " +
  " and  b.STTS_STATE_ID = c.STATE_ID " +
  " and a.owner_id = x.WNRS_OWNER_ID  and x.amurds_serial_number=y.serial_number  "+
  " and y.ride_status='Active' and x.owner_end_date is null "; 
  
  
  public final static String SELECT_OWNERS_SUFFIX =
  "order by upper(a.doing_business_as) ";
  
  public final static String SELECT_OWNER =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
  "and a.OWNER_ID=? ";

  public final static String SELECT_OWNERS_ALL =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + SELECT_OWNERS_SUFFIX;  
  
  public final static String SELECT_OWNERS_BY_LETTER =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
  " AND upper(substr(a.doing_business_as,1,1)) = ? " +
  SELECT_OWNERS_SUFFIX;  
  
    public final static String SELECT_OWNERS_LIKE =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND upper(a.doing_business_as) LIKE  upper('%'||?||'%') " +
    SELECT_OWNERS_SUFFIX;  
    public final static String SELECT_OWNERS_ADDRESS_LIKE =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND upper(b.ADDRESS1) LIKE upper(?||'%') " +
    SELECT_OWNERS_SUFFIX;  
    public final static String SELECT_OWNERS_STATE_NUMBER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND x.state_number= ? " +
    SELECT_OWNERS_SUFFIX; 
    public final static String SELECT_OWNERS_ELEV_STREET_NUMBER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
    " AND upper(x. LOCATION_ADDRESS1) LIKE upper(?||'%') " +
    SELECT_OWNERS_SUFFIX; 
  public final static String SELECT_OWNERS_BY_CITY =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
  "AND x.CITY = ?  " +
  SELECT_OWNERS_SUFFIX;  
  
  public final static String SELECT_OWNERS_BY_COUNTY =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM + 
  "AND x.county = ? " +
  SELECT_OWNERS_SUFFIX;  
  
  public final static String SELECT_FIRST_LETTER_OPTIONS =
  "select distinct LETTER, count(*) \"COUNT\"  from ( " +
  "select substr(a.doing_business_as,1,1) \"LETTER\" " +
  SELECT_OWNERS_FROM +
  ") group by LETTER order by LETTER ";
  
  public final static String SELECT_CITIES_OPTIONS =
  "select  CITY,LOCATION_CITY || ' (' || CITY_COUNT || ')' from ( " +
  "select distinct x.LOCATION_CITY \"CITY\", count(*) \"CITY_COUNT\" " +
  SELECT_OWNERS_FROM +
  "group by x.LOCATION_CITY ) " +
  "order by LOCATION_CITY";
    
  public final static String SELECT_COUNTY_OPTIONS =
  "select COUNTY ,COUNTY_NAME || ' (' || COUNTY_COUNT || ')' from ( " +
  "select distinct fm.county \"COUNTY\",dc.COUNTY_NAME \"COUNTY_NAME\", count(*) \"COUNTY_COUNT\" " +
  " from fire_display fm,dfbs_county dc where fm.county=dc.county_code " +
  "group by dc.COUNTY_NAME ,fm.county) " +
  "order by county_name"; 

  
  
  public final static String INSERT_OWNER =
  "insert into DFBS_OWNER " +
  "(OWNER_ID,LAST_NAME,FIRST_NAME,MIDDLE_INITIAL,DOING_BUSINESS_AS,TELEPHONE,DIVISION) " +
  "values(?,?,?,?,?,?,1)";
  
  public final static String UPDATE_OWNER =
  "update DFBS_OWNER " +
  "set DOING_BUSINESS_AS=?,FIRST_NAME=?,MIDDLE_INITIAL=?,LAST_NAME=?,TELEPHONE=? " +
  "where OWNER_ID=?";
  
  public final static String INSERT_OWNER_ADDRESS =
  "insert into DFBS_ADDRESS " +
  "(ADDRESS_ID,WNRS_OWNER_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
  "values(?,?,?,?,?,?,?,?,?,1)";
  public final static String SELECT_FEES_ELEVATOR =
  "select " +
  "DEVICE_TYPE,DESCRIPTION,  " +
  "AMOUNT " +
  "from DFBS_FEE_STRUCTURE " +
  "where DIVISION=1" +
  " union select 0,0,0 from dual" ;
  
  public final static String INSERT_PERMIT_TRANSACTION =
  "insert into DFBS_FEE " +
  "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
  "OWNER_ID,UNIQUE_NUMBER,DOING_BUSINESS_AS,POST_DATE,DIVISION,FIRST_NAME,LAST_NAME) " +
  "values(?,0,?,UPPER(?),?,?,?,SYSDATE,1,?,?) ";
  
  public final static String SELECT_OWNERS_BY_STREET =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
  " AND upper(x.location_address1) like  upper(?||'%') " +
  SELECT_OWNERS_SUFFIX;
   public final static String SELECT_OWNERS_BY_PERMIT =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
  " AND x.state_number = ? " +
  SELECT_OWNERS_SUFFIX;
    public final static String SELECT_OWNERS_BY_STATUS =
  SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
  " AND x.total_risk =? " +
  SELECT_OWNERS_SUFFIX;
  public final static String SELECT_DOCUMENT_ID =
   "select DOCUMENT_ID.NEXTVAL from dual";
  
  public final static String UPLOAD_DOCUMENT =
     " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
     " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
     " values(?,?,?,?,SYSDATE,?,?,?)";
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
     " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE='AmuseRide' and DOCUMENT_UPLOAD_DATE >sysdate-2";
  public final static String UPDATE_OWNER_ADDRESS =
  "update DFBS_ADDRESS " +
  "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
  "where WNRS_OWNER_ID=?";
  public final static String SELECT_AMUSE_RIDES =
  " SELECT SERIAL_NUMBER ,DEVICE_NAME,PERMIT_NUMBER ,MANUFACTURER_NAME,NSRNCS_INSURANCE_ID,ADDRESS1,ADDRESS2 ,"+                      
  " CITY ,STATE,ZIP ,ZIP2,PHONE,CAPACITY,SPEED,EXPIRATION_DATE,APPLICATION_RECEIVED_DATE,NDT_DATE,null ,"+
  " ASSIGNED_INSPECTOR,SCHEDULED_INSPECTION_DATE,LOCATION ,DVCS_DEVICE_ID,RIDE_STATUS,ACCIDENT_DATE,NUMBER_ACCIDENT  "+
  " FROM AMUSE_RIDE,AMUSE_OWNER_RIDE WHERE AMURDS_SERIAL_NUMBER=SERIAL_NUMBER AND OWNER_END_DATE IS NULL AND WNRS_OWNER_ID=?";
  public final static String SELECT_AMUSE_RIDE =
  " SELECT SERIAL_NUMBER ,DEVICE_NAME,PERMIT_NUMBER ,MANUFACTURER_NAME,NSRNCS_INSURANCE_ID,ADDRESS1,ADDRESS2 ,"+                      
  " CITY ,STATE,ZIP ,ZIP2,PHONE,CAPACITY,SPEED,EXPIRATION_DATE,APPLICATION_RECEIVED_DATE,NDT_DATE,null ,"+
  " ASSIGNED_INSPECTOR,SCHEDULED_INSPECTION_DATE,LOCATION ,DVCS_DEVICE_ID,RIDE_STATUS,ACCIDENT_DATE,NUMBER_ACCIDENT  "+
  " FROM AMUSE_RIDE WHERE SERIAL_NUMBER=?";
  public final static String INSERT_AMUSE_RIDE =
  " INSERT INTO AMUSE_RIDE values(?,?,?,?,?,?,?, "+
   "?,?,?,?,?,?,?,to_date(?,'mm/dd/yyyy'),to_date(?,'mm/dd/yyyy'),to_date(?,'mm/dd/yyyy'),?," +
   "?,to_date(?,'mm/dd/yyyy'),?,?,?,to_date(?,'mm/dd/yyyy'),?)";
  
  public final static String UPDATE_AMUSE_RIDE =
  " UPDATE   AMUSE_RIDE SET NUMBER_ACCIDENT=?,DEVICE_NAME=?,PERMIT_NUMBER =?,MANUFACTURER_NAME=?,NSRNCS_INSURANCE_ID=?,ADDRESS1=?,ADDRESS2 =?,"+                      
  " CITY =?,STATE=?,ZIP =?,ZIP2=?,PHONE=?,CAPACITY=?,SPEED=?,EXPIRATION_DATE=to_date(?,'mm/dd/yyyy'),"+
  " APPLICATION_RECEIVED_DATE=sysdate,NDT_DATE=to_date(?,'mm/dd/yyyy'),OTHER_DOCUMENTS =?,"+
  " ASSIGNED_INSPECTOR=?,SCHEDULED_INSPECTION_DATE=to_date(?,'mm/dd/yyyy'),LOCATION =?,DVCS_DEVICE_ID=?,RIDE_STATUS=?  "+
  "  WHERE SERIAL_NUMBER=?";   
  
  public final static String INSERT_AMUSE_OWNER_RIDE =
      " INSERT INTO AMUSE_OWNER_RIDE VALUES(?,?,?,SYSDATE,NULL)";
  public final static String INSERT_AMUSE_RIDE_INSPECTION_FEE =
      " INSERT INTO AMUSE_RIDE_INSPECTION_FEE VALUES(?,SYSDATE,?,'ANNUAL')";
   public final static String SELECT_AMUSE_OWNER_ID =" SELECT amuse_owner_ride_id.nextval FROM DUAL";
   
    public final static String SELECT_AMUSE_FEE =
    " SELECT dfs.amount FROM dfbs_fee_structure dfs,dfbs_device dd "+
    "  WHERE dfs.description = 'Annual Inspection Fees' "+
      "  AND dfs.device_type = dd.device_description and dd.device_id=?";
  public final static String SELECT_DEVICE_OPTIONS =
   "SELECT DISTINCT  DEVICE_ID,DEVICE_DESCRIPTION FROM  DFBS_DEVICE WHERE DIVISION=1 order by 2";
  
  public final static String SELECT_INSURANCE_BY_ID=
    "SELECT INSURANCE_ID ,CARRIER_NAME,CARRIER_OTHER,POLICY_NUMBER,EFFECTIVE_DATE,EXPIRATION_DATE,AMOUNT_COVERAGE,SIGNED_DATE, "+
    " BRANCH_OFFICE,AUTHORIZED_REPRESENTATIVE,ADDRESS1,ADDRESS2,CITY,ZIP,ZIP2,STATE,TELEPHONE,FAX,DIVISION ,WNRS_OWNER_ID "+
    " FROM DFBS_INSURANCE WHERE INSURANCE_ID=?";
  public final static String SELECT_INSURANCE_BY_OWNER=
    " SELECT INSURANCE_ID ,CARRIER_NAME,CARRIER_OTHER,POLICY_NUMBER,EFFECTIVE_DATE,EXPIRATION_DATE,AMOUNT_COVERAGE,SIGNED_DATE, "+
    " BRANCH_OFFICE,AUTHORIZED_REPRESENTATIVE,ADDRESS1,ADDRESS2,CITY,ZIP,ZIP2,STATE,TELEPHONE,FAX,DIVISION ,WNRS_OWNER_ID "+
    " FROM DFBS_INSURANCE  WHERE WNRS_OWNER_ID=? ";
  public final static String INSERT_INSURANCE =
  " INSERT INTO DFBS_INSURANCE VALUES ( ?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),?,TO_DATE(?,'MM/DD/YYYY'),"+
      " ?,?,?,?,?,?,?,?,?,?,1,?)";
  public final static String UPDATE_INSURANCE=
    " UPDATE DFBS_INSURANCE SET WNRS_OWNER_ID =?,CARRIER_NAME=?,CARRIER_OTHER=?,POLICY_NUMBER=?,EFFECTIVE_DATE=TO_DATE(?,'MM/DD/YYYY'),"+
    " EXPIRATION_DATE=TO_DATE(?,'MM/DD/YYYY'),AMOUNT_COVERAGE=?,SIGNED_DATE=TO_DATE(?,'MM/DD/YYYY') ,"+
    " BRANCH_OFFICE=?,AUTHORIZED_REPRESENTATIVE=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,ZIP2=?,STATE=?,TELEPHONE=?," +
    " FAX=?,DIVISION =1"+
    " WHERE INSURANCE_ID=?";
}
