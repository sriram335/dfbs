package Variance.data;

public class varianceSQL {
    public final static String SELECT_NEXT_OWNER_ID =
    "select OWNER_ID.NEXTVAL from dual";
    public final static String SELECT_NEXT_DESIGNER_ID =
    "select designer1_id.nextval from dual";
    public final static String SELECT_NEXT_ADDRESS_ID =
    "select ADDRESS_ID.NEXTVAL from dual";
        public final static String SELECT_NEXT_VAR_ID =
        " SELECT var_id.nextval from dual";
    
    public final static String SELECT_NEXT_TRANSACTION_ID =
    "select TRANSACTION_ID.NEXTVAL from dual";
    public final static String SELECT_NEXT_PROJECT_ID =
    " SELECT project_id.nextval FROM DUAL";
    
    public final static String SELECT_STATE_OPTIONS =
     "select state_id,state_name from dfbs_state order by state_name";
     
    public final static String SELECT_STATE_OPTIONS2 =
    "select state_initial,state_name from dfbs_state order by state_name";
     
     public final static String SELECT_COUNTY_OPTIONS =
     "select county_name,county_name from dfbs_county order by 1";
    public final static String VAR_VIOLATIONS_BY_OPTIONS =
        "select abbreviation,description from dfbs_code where canned_code='VIOLATIONS_BY' and division=8 order by 1"; 
    public final static String VAR_STAFF_COMM_BY_OPTIONS =
        "select code_id,description from dfbs_code where canned_code='STAFF_COMM_TYPE' and division=8 order by 1"; 
    public final static String VAR_CODE_TYPE_OPTIONS =
        "select description,description from dfbs_code where canned_code='VAR_CODE_TYPE' and division=8 order by 1"; 
    public final static String VAR_YES_NO_OPTIONS =
    "select 'yes' activity,'Yes' activity_status from dual union " +
    " select 'No' activity,'No' activity_status from dual  order by 1 desc" ; 
    public final static String VAR_PROJ_TYPE_OPTIONS =
        "select description,abbreviation from dfbs_code where canned_code='VAR_PROJ_TYPE' and division=8 order by 1"; 
    public final static String VAR_COMM_ACTION_OPTIONS =
        "select abbreviation,description from dfbs_code where canned_code='COM_ACTION' and division=8 order by 1"; 
    
      public final static String SELECT_COUNTY_CODE =
     "select county_code from dfbs_county where county_name=?";
      
    public final static String INSERT_OWNER =
    "insert into DFBS_OWNER " +
    "(OWNER_ID,LAST_NAME,FIRST_NAME,MIDDLE_INITIAL,DOING_BUSINESS_AS,TELEPHONE,DIVISION) " +
    "values(?,?,?,?,?,?,8)";
    
    public final static String UPDATE_OWNER =
     "update DFBS_OWNER " +
    "set LAST_NAME=?,FIRST_NAME=?,MIDDLE_INITIAL=?,DOING_BUSINESS_AS=?,TELEPHONE=? " +
    "where OWNER_ID=?";
    
    public final static String INSERT_OWNER_ADDRESS =
    "insert into DFBS_ADDRESS " +
    "(ADDRESS_ID,WNRS_OWNER_ID,STTS_STATE_ID,ADDRESS1,ADDRESS2,CITY,ZIP,E_MAIL,FAX,DIVISION) " +
    "values(?,?,?,upper(?),upper(?),upper(?),upper(?),upper(?),?,8)";
    
     public final static String UPDATE_OWNER_ADDRESS =
     "update DFBS_ADDRESS " +
    "set STTS_STATE_ID=?,ADDRESS1=?,ADDRESS2=?,CITY=?,ZIP=?,E_MAIL=?,FAX=? " +
    "where WNRS_OWNER_ID=?";
    public final static String SELECT_DOCUMENT_ID =
    "select DOCUMENT_ID.NEXTVAL from dual";
    
    public final static String UPLOAD_DOCUMENT =
    " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
    " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE)" +
    " values(?,?,?,?,SYSDATE,?,?,?)";
    public final static String SELECT_DOCUMENT_NAMES_ALL =
    " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID "+
    " from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? AND DOCUMENT_UPLOAD_DATE >SYSDATE-365 order by 3";
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
    public final static String SELECT_FEES_VARIANCE =
    " SELECT abbreviation,canned_code FROM dfbs_code "+
    " WHERE canned_code IN('VAR_FEE_AD','VAR_FEE_APP','VAR_FEE_SC') AND division=8";
    public final static String SELECT_DUES_VARIANCE_IN_ACCT =
    " SELECT SUM(DUE) FROM DFBS_FEE WHERE DIVISION=8 AND (UNIQUE_NUMBER =to_char(?) OR UNIQUE_NUMBER=?)";
    public final static String SELECT_VARIANCE_TYPE =
    " SELECT count(*) FROM DFBS_FEE WHERE DIVISION=8 AND (UNIQUE_NUMBER =to_char(?) OR UNIQUE_NUMBER=?) AND DESCRIPTION LIKE '%CONFIRM%'";
    public final static String INSERT_PERMIT_TRANSACTION =
    "insert into DFBS_FEE " +
    "(TRANSACTION_ID,AMOUNT_PAID,DUE,DESCRIPTION, " +
    "OWNER_ID,UNIQUE_NUMBER,LAST_NAME,POST_DATE,DIVISION) " +
    "values(?,0,?,UPPER(?),?,?,upper(?),SYSDATE,8) ";
    
    public final static String UPDATE_PERMIT_TRANSACTION =
    "UPDATE DFBS_FEE " +
    " SET UNIQUE_NUMBER=? ,DESCRIPTION=DESCRIPTION||':'||? WHERE UNIQUE_NUMBER=? AND DIVISION=8 ";
    
    public final static String SELECT_ACCT_DUES =
    "SELECT to_char(due),to_char(amount_paid),to_char(feespd_receipt_number),to_char(post_date,'mm/dd/yyyy'),description " +
    " FROM dfbs_fee WHERE unique_number=? and post_date >'15-DEC-08' ORDER BY POST_DATE ASC";
    
    public final static String SELECT_VARIANCE_APP_LIST =
    " SELECT DISTINCT VA.VAR_ID,VA.APPLICANT_FIRST_NAME,VA.APPLICANT_LAST_NAME,VA.APPLICANT_MIDDLE_INITIAL,VA.APPLICANT_ADDRESS1,VA.APPLICANT_ADDRESS2, " +                                                                                                                                                                                   
    " VA.CITY,VA.STATE,nvl(VA.ZIP,0),VA.ZIP2,VA.COMMISSION_CONDITIONS,VA.COMMISSION_STATUS,VA.EQUAL_ALTERNATIVE,VA.PLAN_CORRECTION,VA.RECEIVED_DATE,VA.TELEPHONE_NUMBER, " +                                                                                                                                                                                  
    " substr(VA.VARIANCE_NUMBER,1,3)||substr(VA.VARIANCE_NUMBER,4,3)||lpad(substr(VA.VARIANCE_NUMBER,7,8),2,'0') VAR_NUMBER,VA.VIOLATION,VA.VIOLATION_ISSUED_BY,VA.COMMISSION_ACTION_DATE,VA.OWNER_SIGNATURE,VA.DESIGNER_SIGNATURE,VA.LBO_NOTIFIED, " +                                                                                                                                                                              
    " VA.LFO_NOTIFIED,VA.PLANS_RECEIVED,nvl(VA.NUMBER_CODE,0),VA.CODE_TYPE,nvl(VA.VP_PROJECT_ID,0),nvl(VA.PRJCTS_SBC_PROJECT_NUMBER,0),VA.FIRM_NAME,VA.FAX ,VA.E_MAIL ,"+                                                                                                                                                                               
    " VA.APP_FD_NAME,VA.APP_BD_NAME,VA.APP_LBO_NAME,VA.APP_LFO_NAME,VA.APP_LBO_EMAIL,VA.APP_LFO_EMAIL," + 
    " VA.VAR_APP_PH_AFFIRM,VA.VAR_APP_PH_AFFIRM_COMMENT,VA.VAR_APP_HIST_AFF_PHY,VA.VAR_APP_HIST_AFF_PHY_COMMENT," + 
    " VA.VAR_APP_HIST_AFF_MAJ,VA.VAR_APP_HIST_AFF_EXC,VA.VAR_APP_HIST_AFF_HIST,VA.APP_LBO_PHONE,VA.APP_LFO_PHONE,VP.PROJECT_NAME,VP.COUNTY FROM VAR_APPLICATION VA, VAR_PLAN  VP" +
    " where VA.VP_PROJECT_ID=VP.PROJECT_ID "; 
    public final static String SELECT_VARIANCE_APP_LIST_CODE =
    " SELECT DISTINCT VA.VAR_ID,VA.APPLICANT_FIRST_NAME,VA.APPLICANT_LAST_NAME,VA.APPLICANT_MIDDLE_INITIAL,VA.APPLICANT_ADDRESS1,VA.APPLICANT_ADDRESS2, " +                                                                                                                                                                                   
    " VA.CITY,VA.STATE,nvl(VA.ZIP,0),VA.ZIP2,VA.COMMISSION_CONDITIONS,VA.COMMISSION_STATUS,VA.EQUAL_ALTERNATIVE,VA.PLAN_CORRECTION,VA.RECEIVED_DATE,VA.TELEPHONE_NUMBER, " +                                                                                                                                                                                  
    " substr(VA.VARIANCE_NUMBER,1,3)||substr(VA.VARIANCE_NUMBER,4,3)||lpad(substr(VA.VARIANCE_NUMBER,7,8),2,'0') VAR_NUMBER,VA.VIOLATION,VA.VIOLATION_ISSUED_BY,VA.COMMISSION_ACTION_DATE,VA.OWNER_SIGNATURE,VA.DESIGNER_SIGNATURE,VA.LBO_NOTIFIED, " +                                                                                                                                                                              
    " VA.LFO_NOTIFIED,VA.PLANS_RECEIVED,nvl(VA.NUMBER_CODE,0),VA.CODE_TYPE,nvl(VA.VP_PROJECT_ID,0),nvl(VA.PRJCTS_SBC_PROJECT_NUMBER,0),VA.FIRM_NAME,VA.FAX ,VA.E_MAIL ,"+                                                                                                                                                                               
    " VA.APP_FD_NAME,VA.APP_BD_NAME,VA.APP_LBO_NAME,VA.APP_LFO_NAME,VA.APP_LBO_EMAIL,VA.APP_LFO_EMAIL," + 
    " VA.VAR_APP_PH_AFFIRM,VA.VAR_APP_PH_AFFIRM_COMMENT,VA.VAR_APP_HIST_AFF_PHY,VA.VAR_APP_HIST_AFF_PHY_COMMENT," + 
     " VA.VAR_APP_HIST_AFF_MAJ,VA.VAR_APP_HIST_AFF_EXC,VA.VAR_APP_HIST_AFF_HIST,VA.APP_LBO_PHONE,VA.APP_LFO_PHONE,VP.PROJECT_NAME,VP.COUNTY ,VSC.CODE_NAME,VSC.SPECIFIC_CODE,VSC.VAR_COMMISSION_CONDITIONS "+
    " FROM VAR_APPLICATION VA, VAR_PLAN  VP , VAR_SPECIFIC_CODE VSC " +
      " where VA.VP_PROJECT_ID=VP.PROJECT_ID  AND VA.VAR_ID=VSC.VA_VAR_ID "; 
      
    public final static String SELECT_VARIANCE_APP =
    " SELECT VAR_ID,APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME,APPLICANT_MIDDLE_INITIAL,APPLICANT_ADDRESS1,APPLICANT_ADDRESS2, " +                                                                                                                                                                                   
    " CITY,STATE,nvl(ZIP,0),ZIP2,COMMISSION_CONDITIONS,COMMISSION_STATUS,EQUAL_ALTERNATIVE,PLAN_CORRECTION,RECEIVED_DATE,TELEPHONE_NUMBER, " +                                                                                                                                                                                  
    " substr(VA.VARIANCE_NUMBER,1,3)||substr(VA.VARIANCE_NUMBER,4,3)||lpad(substr(VA.VARIANCE_NUMBER,7,8),2,'0') VARIANCE_NUMBER,VIOLATION,VIOLATION_ISSUED_BY,COMMISSION_ACTION_DATE,OWNER_SIGNATURE,DESIGNER_SIGNATURE,LBO_NOTIFIED, " +                                                                                                                                                                              
    " LFO_NOTIFIED,PLANS_RECEIVED,nvl(NUMBER_CODE,0),CODE_TYPE,nvl(VP_PROJECT_ID,0),nvl(PRJCTS_SBC_PROJECT_NUMBER,0),FIRM_NAME,FAX ,E_MAIL ,"+                                                                                                                                                                               
    " APP_FD_NAME,APP_BD_NAME,APP_LBO_NAME,APP_LFO_NAME,APP_LBO_EMAIL,APP_LFO_EMAIL," + 
    " VAR_APP_PH_AFFIRM,VAR_APP_PH_AFFIRM_COMMENT,VAR_APP_HIST_AFF_PHY,VAR_APP_HIST_AFF_PHY_COMMENT," + 
    " VAR_APP_HIST_AFF_MAJ,VAR_APP_HIST_AFF_EXC,VAR_APP_HIST_AFF_HIST,VA.APP_LBO_PHONE,VA.APP_LFO_PHONE FROM VAR_APPLICATION VA WHERE VAR_ID=?"; 
    public final static String SELECT_VARIANCE_APP_BY_VAR =
    " SELECT VAR_ID,APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME,APPLICANT_MIDDLE_INITIAL,APPLICANT_ADDRESS1,APPLICANT_ADDRESS2, " +                                                                                                                                                                                   
    " CITY,STATE,nvl(ZIP,0),ZIP2,COMMISSION_CONDITIONS,COMMISSION_STATUS,EQUAL_ALTERNATIVE,PLAN_CORRECTION,RECEIVED_DATE,TELEPHONE_NUMBER, " +                                                                                                                                                                                  
    " substr(VA.VARIANCE_NUMBER,1,3)||substr(VA.VARIANCE_NUMBER,4,3)||lpad(substr(VA.VARIANCE_NUMBER,7,8),2,'0') VARIANCE_NUMBER,VIOLATION,VIOLATION_ISSUED_BY,COMMISSION_ACTION_DATE,OWNER_SIGNATURE,DESIGNER_SIGNATURE,LBO_NOTIFIED, " +                                                                                                                                                                              
    " LFO_NOTIFIED,PLANS_RECEIVED,nvl(NUMBER_CODE,0),CODE_TYPE,nvl(VP_PROJECT_ID,0),nvl(PRJCTS_SBC_PROJECT_NUMBER,0),FIRM_NAME,FAX ,E_MAIL ,"+                                                                                                                                                                               
    " APP_FD_NAME,APP_BD_NAME,APP_LBO_NAME,APP_LFO_NAME,APP_LBO_EMAIL,APP_LFO_EMAIL," + 
    " VAR_APP_PH_AFFIRM,VAR_APP_PH_AFFIRM_COMMENT,VAR_APP_HIST_AFF_PHY,VAR_APP_HIST_AFF_PHY_COMMENT," + 
    " VAR_APP_HIST_AFF_MAJ,VAR_APP_HIST_AFF_EXC,VAR_APP_HIST_AFF_HIST,VA.APP_LBO_PHONE,VA.APP_LFO_PHONE FROM VAR_APPLICATION VA WHERE VARIANCE_NUMBER=?"; 
    
    public final static String UPDATE_VARIANCE_APP =
    " UPDATE VAR_APPLICATION SET APP_LFO_EMAIL=?,APPLICANT_FIRST_NAME=?,APPLICANT_LAST_NAME=?,APPLICANT_MIDDLE_INITIAL=?,APPLICANT_ADDRESS1=?,APPLICANT_ADDRESS2=?, " +                                                                                                                                                                                   
    " CITY=?,STATE=?,ZIP=?,ZIP2=?,COMMISSION_CONDITIONS=?,COMMISSION_STATUS=?,EQUAL_ALTERNATIVE=?,PLAN_CORRECTION=?,RECEIVED_DATE=TO_DATE(?,'MM/DD/YYYY'),TELEPHONE_NUMBER=?, " +                                                                                                                                                                                  
    " VARIANCE_NUMBER=?,VIOLATION=?,VIOLATION_ISSUED_BY=?,COMMISSION_ACTION_DATE=?,OWNER_SIGNATURE=?,DESIGNER_SIGNATURE=?,LBO_NOTIFIED=?, " +                                                                                                                                                                              
    " LFO_NOTIFIED=?,PLANS_RECEIVED=?,NUMBER_CODE=?,CODE_TYPE=?,VP_PROJECT_ID=?,PRJCTS_SBC_PROJECT_NUMBER=?,FIRM_NAME=?,FAX =?,E_MAIL =?,"+                                                                                                                                                                               
    " APP_FD_NAME=?,APP_BD_NAME=?,APP_LBO_NAME=?,APP_LFO_NAME=?,APP_LBO_EMAIL=? ," + 
    " VAR_APP_PH_AFFIRM=? ,VAR_APP_PH_AFFIRM_COMMENT=? ,VAR_APP_HIST_AFF_PHY=? ,VAR_APP_HIST_AFF_PHY_COMMENT=? ," + 
    " VAR_APP_HIST_AFF_MAJ=? ,VAR_APP_HIST_AFF_EXC=? ,VAR_APP_HIST_AFF_HIST=?,APP_LBO_PHONE=?,APP_LFO_PHONE=? WHERE VAR_ID=?"; 
    
    public final static String INSERT_VARIANCE_APP =
    " INSERT INTO VAR_APPLICATION (" +
        " VAR_ID,APPLICANT_FIRST_NAME,APPLICANT_LAST_NAME,APPLICANT_MIDDLE_INITIAL,APPLICANT_ADDRESS1,APPLICANT_ADDRESS2, " + 
        " CITY,STATE,ZIP,ZIP2,COMMISSION_CONDITIONS,COMMISSION_STATUS,EQUAL_ALTERNATIVE,PLAN_CORRECTION,VAR_APP_HIST_AFF_EXC,TELEPHONE_NUMBER," + 
        " VARIANCE_NUMBER,VIOLATION,VIOLATION_ISSUED_BY,VAR_APP_HIST_AFF_HIST,OWNER_SIGNATURE,DESIGNER_SIGNATURE,LBO_NOTIFIED, " +  
        " LFO_NOTIFIED,PLANS_RECEIVED,NUMBER_CODE,CODE_TYPE,VP_PROJECT_ID,PRJCTS_SBC_PROJECT_NUMBER,FIRM_NAME,FAX ,E_MAIL ,"+ 
        " APP_FD_NAME,APP_BD_NAME,APP_LBO_NAME,APP_LFO_NAME,APP_LBO_EMAIL,APP_LBO_PHONE,APP_LFO_EMAIL,APP_LFO_PHONE," + 
        " VAR_APP_PH_AFFIRM,VAR_APP_PH_AFFIRM_COMMENT,VAR_APP_HIST_AFF_PHY,VAR_APP_HIST_AFF_PHY_COMMENT," + 
        " VAR_APP_HIST_AFF_MAJ,RECEIVED_DATE)" +
        " VALUES ("+
    "?,?,?,?,?,?,"+
    "?,?,?,?,?,?,?,?,?,?," +
    "?,?,?,?,?,?,?,"+
    "?,?,?,?,?,?,?,?,?,"+
    " ?,?,?,?,?,?,?,?,"+
    "?,?,?,?,"+
    "?,sysdate)"    ;
    
    public final static String SELECT_OWNERS_PREFIX =
    "select distinct a.owner_id,a.LAST_NAME, " +
    "a.FIRST_NAME,a.MIDDLE_INITIAL,a.DOING_BUSINESS_AS,b.ADDRESS_ID,b.ADDRESS1," +
    " b.ADDRESS2,b.CITY,c.STATE_INITIAL,b.ZIP,b.zip2, " +
    "a.telephone,b.fax,b.E_MAIL,b.STTS_STATE_ID ";
    
    public final static String SELECT_OWNERS_FROM =
    " from dfbs_owner a , dfbs_address b ,dfbs_state c  " +
    " where a.owner_id=b.wnrs_owner_id  " +
    " and  b.STTS_STATE_ID = c.STATE_ID  "; 
    
    
    public final static String SELECT_OWNERS_SUFFIX =
    "order by UPPER(a.last_name) ";
    
    public final static String SELECT_OWNER =
    SELECT_OWNERS_PREFIX + SELECT_OWNERS_FROM +
    "and a.OWNER_ID=? ";
    
    public final static String SELECT_VARIANCE_PROJ =
    " SELECT PROJECT_ID,PROJECT_NAME,PROJECT_STATUS,PROJECT_TYPE,ADDRESS_LINE1,ADDRESS_LINE2,CITY," +
    " COUNTY,STATE,ZIP,ZIP2,nvl(WNRS_OWNER_ID,0) FROM VAR_PLAN WHERE PROJECT_ID=?";
    
  public final static String UPDATE_VARIANCE_PROJ =
  " UPDATE VAR_PLAN SET WNRS_OWNER_ID=?,PROJECT_NAME=?,PROJECT_STATUS=?,PROJECT_TYPE=?,ADDRESS_LINE1=?,ADDRESS_LINE2=?,CITY=?," +
  " COUNTY=?,STATE=?,ZIP=?,ZIP2=? WHERE PROJECT_ID=?";  
  
  public final static String INSERT_VARIANCE_PROJ =
  " INSERT INTO VAR_PLAN (PROJECT_ID,PROJECT_NAME,PROJECT_STATUS,PROJECT_TYPE,ADDRESS_LINE1,ADDRESS_LINE2,CITY," + 
  " COUNTY,STATE,ZIP,ZIP2,WNRS_OWNER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";
    public final static String SELECT_VAR_DESIGNER=
  " SELECT PROFESSIONAL_ID,PROFESSIONAL_LAST_NAME,PROFESSIONAL_MIDDLE_INITIAL ,PROFESSIONAL_FIRST_NAME,ADDRESS_LINE1,"+
  " ADDRESS_LINE2,CITY,STATE,ZIP,ZIP2,TELEPHONE_NUMBER,VP_PROJECT_ID,FIRM_NAME,FAX,E_MAIL " +
  " FROM VAR_DESIGN_PROFESSIONAL WHERE VP_PROJECT_ID=?" ;
     public final static String INSERT_VAR_DESIGNER=
    " INSERT INTO VAR_DESIGN_PROFESSIONAL( PROFESSIONAL_ID,PROFESSIONAL_LAST_NAME,PROFESSIONAL_MIDDLE_INITIAL ,PROFESSIONAL_FIRST_NAME,ADDRESS_LINE1,"+
    " ADDRESS_LINE2,CITY,STATE,ZIP,ZIP2,TELEPHONE_NUMBER,VP_PROJECT_ID,FIRM_NAME,E_MAIL) " +
    " VALUES (?,?,?,?,?," +
    " ?,?,?,?,?,?,?,?,?) ";
    public final static String UPDATE_VAR_DESIGNER=
    " UPDATE VAR_DESIGN_PROFESSIONAL SET E_MAIL=?,PROFESSIONAL_LAST_NAME=?,PROFESSIONAL_MIDDLE_INITIAL =?,PROFESSIONAL_FIRST_NAME=?,ADDRESS_LINE1=?," + 
    " ADDRESS_LINE2=?,CITY=?,STATE=?,ZIP=?,ZIP2=?,TELEPHONE_NUMBER=?,VP_PROJECT_ID=?,FIRM_NAME=? " + 
    "  WHERE PROFESSIONAL_ID=? ";
      public final static String SELECT_VAR_CODE_LIST=
    " SELECT VAR_CODE_ID,SPECIFIC_CODE,CODE_NAME,NATURE_NON_COMPLIANCE,EDITION,VA_VAR_ID,COMMISSION_ACTION_DATE, " +
    " COMMISSION_STATUS,VAR_PRINT_DATE ,VAR_APP_PH_AFFIRM,VAR_APP_PH_AFFIRM_COMMENT,VAR_APP_HIST_AFF_PHY,VAR_APP_HIST_AFF_PHY_COMMENT, " + 
    " VAR_APP_HIST_AFF_MAJ,VAR_APP_HIST_AFF_EXC,VAR_APP_HIST_AFF_HIST,STAFF_COMMENTS,STAFF_COMMENTS_REC,VAR_RELEASE_FLAG,VAR_COMMISSION_CONDITIONS FROM VAR_SPECIFIC_CODE "+
    " WHERE   VA_VAR_ID=? AND (NVL(STAFF_COMMENTS_REC,'XX') != 'VOID' OR NVL(COMMISSION_STATUS,'XX') !='W')";
    public final static String SELECT_VAR_CODE=
    " SELECT VAR_CODE_ID,SPECIFIC_CODE,CODE_NAME,NATURE_NON_COMPLIANCE,EDITION,VA_VAR_ID,COMMISSION_ACTION_DATE, " +
    " COMMISSION_STATUS,VAR_PRINT_DATE,VAR_APP_PH_AFFIRM,VAR_APP_PH_AFFIRM_COMMENT,VAR_APP_HIST_AFF_PHY,VAR_APP_HIST_AFF_PHY_COMMENT, " + 
    " VAR_APP_HIST_AFF_MAJ,VAR_APP_HIST_AFF_EXC,VAR_APP_HIST_AFF_HIST,STAFF_COMMENTS ,STAFF_COMMENTS_REC,VAR_RELEASE_FLAG,VAR_COMMISSION_CONDITIONS FROM VAR_SPECIFIC_CODE WHERE   VAR_CODE_ID=?"; 
    public final static String INSERT_VAR_CODE=
    " INSERT INTO VAR_SPECIFIC_CODE( VAR_CODE_ID,SPECIFIC_CODE,CODE_NAME,NATURE_NON_COMPLIANCE,EDITION,VA_VAR_ID,COMMISSION_ACTION_DATE, " +
    " COMMISSION_STATUS,VAR_PRINT_DATE,VAR_APP_PH_AFFIRM,VAR_APP_PH_AFFIRM_COMMENT,VAR_APP_HIST_AFF_PHY,VAR_APP_HIST_AFF_PHY_COMMENT, " + 
    " VAR_APP_HIST_AFF_MAJ,VAR_APP_HIST_AFF_EXC,VAR_APP_HIST_AFF_HIST) VALUES(?,?,?,?,?,?,null,null,NULL,?,?,?,?,?,?,?) " ;
    public final static String UPDATE_VAR_CODE=
    " UPDATE  VAR_SPECIFIC_CODE SET COMMISSION_STATUS=?,SPECIFIC_CODE=?,CODE_NAME=?,NATURE_NON_COMPLIANCE=?,EDITION=?," +
        " COMMISSION_ACTION_DATE=TO_DATE(?,'MM/DD/YYYY'), VAR_PRINT_DATE=TO_DATE(?,'MM/DD/YYYY'),VAR_APP_PH_AFFIRM=?,"+
       " VAR_APP_PH_AFFIRM_COMMENT=?,VAR_APP_HIST_AFF_PHY=?,VAR_APP_HIST_AFF_PHY_COMMENT=?, " + 
        " VAR_APP_HIST_AFF_MAJ=?,VAR_APP_HIST_AFF_EXC=?,VAR_APP_HIST_AFF_HIST=? ,STAFF_COMMENTS=? ,STAFF_COMMENTS_REC=? ,VAR_RELEASE_FLAG =?, VAR_COMMISSION_CONDITIONS=? WHERE VAR_CODE_ID=?"; 
    public final static String UPDATE_VAR_CODE_FLAG=
    " UPDATE  VAR_SPECIFIC_CODE SET VAR_RELEASE_FLAG =? WHERE   VAR_CODE_ID=?"; 
    public final static String SELECT_PLAN_DATA=
    " SELECT PP.PROJECT_NAME,PP.PROJECT_ADDRESS1,PP.PROJECT_ADDRESS2,PP.PROJECT_CITY,PP.PROJECT_STATE,PP.PROJECT_ZIP,PP.PROJECT_ZIP2,"+
    " DO.DOING_BUSINESS_AS,DO.LAST_NAME,DO.FIRST_NAME,DO.MIDDLE_INITIAL,DA.ADDRESS1,DA.ADDRESS2,DA.CITY,DA.STTS_STATE_ID,DA.ZIP,DA.ZIP2,DO.TELEPHONE,DA.FAX,DA.E_MAIL," +
    " PD.FIRM_NAME,PD.FIRM_ADDRESS1,PD.FIRM_ADDRESS2,PD.FIRM_CITY,PD.FIRM_STATE,PD.FIRM_ZIP,PD.FIRM_ZIP2,PDR.LAST_NAME,PDR.FIRST_NAME,PDR.E_MAIL,PD.FIRM_TELEPHONE,PD.FAX , DC.COUNTY_NAME, "+
    " PP.CITY_LIMITS,PP.STATE_OWNED " +
    " FROM PLAN_PROJECT PP,PLAN_FORM_FILED,PLAN_DESIGN PD,PLAN_DESIGNER PDR,DFBS_OWNER DO,DFBS_ADDRESS DA, DFBS_COUNTY DC " +
    " WHERE SBC_PROJECT_NUMBER=PRJCTS_SBC_PROJECT_NUMBER AND PP.WNRS_OWNER_ID=DO.OWNER_ID AND DO.OWNER_ID=DA.WNRS_OWNER_ID "+
    " AND FRMFLDS_FORM_ID=FORM_ID AND DSGNRS_DESIGNER_ID=DESIGNER_ID AND PP.CNTYS_COUNTY_CODE=DC.COUNTY_CODE "+
    " AND FRM_FORM_ID=10     AND SBC_PROJECT_NUMBER=?";
    public final static String SELECT_VAR_COMM_DATES=
    " SELECT to_char(var_app_date,'mm/dd/yyyy'), to_char(var_comm_date,'mm/dd/yyyy') ,var_comm_time FROM var_commission_dates WHERE var_app_date>=sysdate order by var_app_date asc";
    public final static String SELECT_EMAIL_LIST=
    " SELECT DISTINCT EMAIL E_MAIL,FIRE_CHIEF_FIRST_NAME||' '||FIRE_CHIEF_LAST_NAME name, TELEPHONE_NUMBER FROM DFBS_FIRE_DEPARTMENT WHERE COUNTY=? AND EMAIL IS NOT NULL " +
    " UNION SELECT DISTINCT USER_LOGIN_ID E_MAIL,user_first_name||' '||user_last_name name,user_phone TELEPHONE_NUMBER FROM DFBS_OTHER_USERS WHERE COUNTY=? AND FIRE_DEPT_ID IS NOT NULL AND STATUS='A'" ;
    
    public final static String SELECT_VAR_APP_VERIFICATION=
     " SELECT count(*) FROM VAR_APPLICATION A, VAR_PLAN B  ,DFBS_ADDRESS C, DFBS_FEE D WHERE " +
        " A.VP_PROJECT_ID=B.PROJECT_ID AND B.WNRS_OWNER_ID=C.WNRS_OWNER_ID AND D.DESCRIPTION LIKE '%'||?||'%' "  +
        " AND A.VAR_ID=? AND upper(C.E_MAIL)=upper(?) AND D.OWNER_ID=? AND C.WNRS_OWNER_ID=? AND D.OWNER_ID > 0 AND A.VARIANCE_NUMBER IS NULL";
    public final static String SELECT_INSPECTOR_BY_ID =
         " SELECT FIRST_NAME,LAST_NAME,E_MAIL FROM DFBS_INSPECTOR WHERE DIVISION=4 AND INSPECTOR_STATUS='A' AND inspector_id=? ";     
    public final static String UPDATE_LBO_CONFIRMATION =        
        " UPDATE VAR_APPLICATION SET LBO_NOTIFIED=? WHERE VAR_ID=?";
public final static String UPDATE_LFO_CONFIRMATION =  
    " UPDATE VAR_APPLICATION SET LFO_NOTIFIED=? WHERE VAR_ID=?";
    public final static String UPDATE_LFO_EMAIL =    
    " UPDATE VAR_APPLICATION SET APP_LFO_EMAIL=?,LFO_NOTIFIED=? ,APP_LBO_EMAIL=?,LBO_NOTIFIED=? WHERE VAR_ID=?";
  }
