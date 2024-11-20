package hazmat.data;
import hazmat.to.*;
import hazmat.data.*;

public class HazmatPermitSQL 
{
   public final static String SELECT_NEXT_ORG_ID =
    "select organization_id.NEXTVAL from dual";
    
   public final static String INSERT_ORGANIZATION =
   " INSERT INTO hazmat_transport_permit(ORGANIZATION_ID,ORGANIZATION_NAME,ORGANIZATION_CONTACT,ORG_CONTACT_TITLE, " +
   " ADDRESS1,ADDRESS2,CITY,STATE,ZIP,ZIP2,PHONE,FAX,EMAIL,COUNTY )   "   +
   " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
   
   public final static String SELECT_NEXT_CAR_ID =
    "select carrier_id.NEXTVAL from dual";
   
    public final static String INSERT_CARRIER =
   " INSERT INTO hazmat_carrier(CARRIER_ID,CARRIER_NAME,CARRIER_CONTACT,CAR_CONTACT_TITLE, " +
   " PHONE ,FAX ,EMAIL,ORGANIZATION_ID         )   "   +
   " VALUES(?,?,?,?,?,?,?,?)" ;  
   
    public final static String SELECT_NEXT_SHIP_ID =
    "select shipment_id.NEXTVAL from dual";
   
    public final static String INSERT_SHIPMENT =
   " INSERT INTO hazmat_shipment(SHIPMENT_ID,SHIPMENT_DATE,SHIPMENT_DESTINATION, " +
   " AMOUNT_SHIPPED,MATERIAL_TYPE,ISOTOPES,ACTIVITY_ISOTOPE,VICVSA_YES_NO,VICVSA_STATE, " +
   " COMMENTS ,ROUTE_DESCRIPTION,RADIO_ACTIVE_LEVEL,FEE_ID,APPLICATION_DATE, " +
   " CARRIER_ID,SHIPMENT_ORIGIN,PERMIT_NUMBER,ISSUE_DATE, EXPIRATION_DATE ,AMOUNT_SHIPPED_WEIGHT,METHOD_OF_SHIPMENT,NUM_OF_RAIL_CARS      )   "   +
   " VALUES(?,TO_DATE(?,'MM/DD/YYYY'),?," +
   "?,?,?,?,?,?," +
   "?,?,?,?,SYSDATE," +
   " ?,?,?,TO_DATE(?,'MM/DD/YYYY')-4,TO_DATE(?,'MM/DD/YYYY')+4,?,?,?)" ;  
   
    public final static String SELECT_FEES_HAZMAT =
    "select " +
    "decode ( " +
    "DESCRIPTION, " +
    "'LOW',1, " +
    "'HIGH',2, " +
    "'HIGH_RAIL',3, " +
    "'HIGH_RAIL_ADD_CASK',4, " +
    "'HRCQ',5, " +
    "'HRCQ_RAIL',6, " +
    "'HRCQ_RAIL_ADD_CASK',7, " +
    "'0' " +
    ") PERMIT_REQUEST_TYPE,  " +
    "AMOUNT " +
    "from DFBS_FEE_STRUCTURE " +
    "where DEVICE_TYPE IN ('HAZMAT_RAD_WASTE_FEE')" +
    " union select 0 ,0 from dual" ;
    
      public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION,SSN) " +
    "values(?,0,?,UPPER(?),?,?,?,SYSDATE,15,?) ";
    
     public final static String SELECT_NEXT_TRANSACTION_ID =
    "select TRANSACTION_ID.NEXTVAL from dual";
   
    
    public final static String SELECT_PERMITS_TO_PRINT =
    " select distinct htp.organization_name,htp.address1,htp.address2,htp.city,htp.state,htp.zip, " +
    " hc.carrier_name,hs.amount_shipped,shipment_origin,hs.shipment_destination, " +
    " hs.radio_active_level,hs.shipment_date,hs.permit_number,hs.issue_date,hs.expiration_date,hs.method_of_shipment,hs.num_of_rail_cars " +
    " from hazmat_transport_permit htp,hazmat_carrier hc, hazmat_shipment hs, dfbs_fee df " +
    " where htp.organization_id=hc.organization_id and " +
    " hc.carrier_id=hs.carrier_id and hs.fee_id=df.owner_id and hs.fee_id=? and df.division=15 and  htp.email=? " +
    " and df.ssn='CC'" +
    " union " +
     " select htp.organization_name,htp.address1,htp.address2,htp.city,htp.state,htp.zip, " +
    " hc.carrier_name,hs.amount_shipped,shipment_origin,hs.shipment_destination, " +
    " hs.radio_active_level,hs.shipment_date,hs.permit_number,hs.issue_date,hs.expiration_date,hs.method_of_shipment,hs.num_of_rail_cars " +
    " from hazmat_transport_permit htp,hazmat_carrier hc, hazmat_shipment hs, dfbs_fee df " +
    " where htp.organization_id=hc.organization_id and " +
    " hc.carrier_id=hs.carrier_id and hs.permit_number=df.unique_number and df.owner_id=? " +
    " and df.ssn='check'  and  htp.email=?" ; 
    
    
     public final static String SELECT_PERMIT_STRING = 
    " select TO_CHAR(sysdate,'MMYY') from dual";
    
     public final static String SELECT_FIRST_LETTER_OPTIONS =
     "select distinct LETTER, count(*) \"COUNT\"  from ( " +
     " select substr(organization_name,1,1) \"LETTER\" " +
     " from hazmat_transport_permit " +
     ") group by LETTER order by LETTER ";
     
     
     public final static String SELECT_TIME_PERIOD_OPTIONS =
    "select  MONTH_YEAR,MONTH_YEAR || ' (' || MY_COUNT || ')' from ( " +
    "select distinct to_char(hs.shipment_date,'mmyyyy') \"MONTH_YEAR\", count(*) \"MY_COUNT\" " +
    " from hazmat_transport_permit htp,hazmat_carrier hc, hazmat_shipment hs " +
    " where htp.organization_id=hc.organization_id and " +
    " hc.carrier_id=hs.carrier_id " +
    " group by to_char(hs.shipment_date,'mmyyyy') ) " +
    " order by MONTH_YEAR";
    
    
    public final static String SELECT_ORG_BY_LETTER =
    " select htp.organization_name,htp.address1,htp.address2,htp.city,htp.state,htp.zip, " +
    " hc.carrier_name,hs.amount_shipped,hs.shipment_origin,hs.shipment_destination, " +
    " hs.radio_active_level,hs.shipment_date,hs.permit_number,hs.issue_date,hs.expiration_date, " +
    " htp.organization_id,hc.carrier_id,hs.shipment_id,hs.fee_id ,htp.email" +
    " from hazmat_transport_permit htp,hazmat_carrier hc, hazmat_shipment hs " +
    " where htp.organization_id=hc.organization_id and " +
    " hc.carrier_id=hs.carrier_id and substr(htp.organization_name,1,1)=? order by hs.shipment_date desc";
    
     public final static String SELECT_ORG_BY_TIME_PERIOD =
    " select htp.organization_name,htp.address1,htp.address2,htp.city,htp.state,htp.zip, " +
    " hc.carrier_name,hs.amount_shipped,shipment_origin,hs.shipment_destination, " +
    " hs.radio_active_level,hs.shipment_date,hs.permit_number,hs.issue_date,hs.expiration_date, " +
    " htp.organization_id,hc.carrier_id,hs.shipment_id ,hs.fee_id" +
    " from hazmat_transport_permit htp,hazmat_carrier hc, hazmat_shipment hs " +
    " where htp.organization_id=hc.organization_id and " +
    " hc.carrier_id=hs.carrier_id and to_char(hs.shipment_date,'mmyyyy')=? order by hs.shipment_date desc";
   
    public final static String SELECT_ORG_BY_ID =
    " SELECT ORGANIZATION_NAME,ORGANIZATION_CONTACT,ORG_CONTACT_TITLE,ADDRESS1 ," +
    " ADDRESS2,CITY,STATE,ZIP,ZIP2,PHONE,FAX,EMAIL  FROM HAZMAT_TRANSPORT_PERMIT WHERE ORGANIZATION_ID =?  "; 
    
    public final static String SELECT_CARRIER_BY_ID =
    " SELECT CARRIER_NAME,CARRIER_CONTACT ,CAR_CONTACT_TITLE,PHONE,FAX, " +
    " EMAIL  FROM HAZMAT_CARRIER WHERE CARRIER_ID=?   ";
    
    public final static String SELECT_SHIPMENT_BY_ID =
    " SELECT SHIPMENT_DATE,SHIPMENT_ORIGIN ,SHIPMENT_DESTINATION,AMOUNT_SHIPPED,MATERIAL_TYPE, " +
    " ISOTOPES,ACTIVITY_ISOTOPE,VICVSA_YES_NO,VICVSA_STATE,COMMENTS,ROUTE_DESCRIPTION ," +
    " RADIO_ACTIVE_LEVEL,APPLICATION_DATE,ISSUE_DATE,EXPIRATION_DATE, " +
    " PERMIT_NUMBER,AMOUNT_SHIPPED_WEIGHT,METHOD_OF_SHIPMENT,NUM_OF_RAIL_CARS  FROM HAZMAT_SHIPMENT WHERE SHIPMENT_ID  =? ";    
    
     public final static String SELECT_SHIPMENT_BY_PERMIT =
    " select htp.organization_name , hc.carrier_name, " +
    " hs.issue_date,hs.expiration_date " +
    " from hazmat_transport_permit htp,hazmat_carrier hc, hazmat_shipment hs " +
    " where htp.organization_id=hc.organization_id and " +
    " hc.carrier_id=hs.carrier_id and to_char(hs.issue_date,'mm/dd/yyyy')=? and hs.permit_number=? ";    
    
     public final static String SELECT_STATE_OPTIONS =
    "select state_initial,state_name from dfbs_state where state_id<=51 order by state_name ";
    
     public final static String SELECT_RAD_LEVEL_OPTIONS =
    "select description,description  from dfbs_code  where canned_code = 'HAZMAT_RAD_LEVEL'" +
    "  and division = 15" ;
  public final static String SELECT_SHIPMENT_TYPE_OPTIONS =
  "select description,description  from dfbs_code  where canned_code = 'HAZMAT_SHIPMENT_TYPE'" +
  "  and division = 15" ;
   
    
      public final static String SHIPMENT_CVSA_YN_OPTIONS =
   "select description,description  from dfbs_code  where canned_code = 'HAZMAT_CVSA_YN'" +
    "  and division = 15" ;
}
