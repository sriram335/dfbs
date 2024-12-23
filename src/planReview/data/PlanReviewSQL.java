package planReview.data;

public class PlanReviewSQL {
    public final static String SELECT_USER =
      " select ds.login_id,ds.password,ds.password_expires_date,dga.access_level,sysdate from dfbs_security ds, dfbs_group_access dga " +
      " where ds.login_id=dga.scrtys_login_id and dga.grps_group_id IN('ARSON','ALL') " +
      " and ds.status='A' and dga.access_level IN('SUPERVISOR','USER','DBA') and upper(ds.login_id)=upper(?) and upper(ds.password)=upper(?)";
       public final static String RECOVER_PASSWORD =
      " select ds.login_id,ds.password from dfbs_security ds " +
      " where ds.status='A' and UPPER(ds.e_mail)=UPPER(?)";
       public final static String CHANGE_PASSWORD =
      " update dfbs_security  set password=? password_expires_date=sysdate+90" +
      " where login_id=?";
        
         public final static String SELECT_DOCUMENT_ID =
       "select DOCUMENT_ID.NEXTVAL from dual";
     
       public final static String UPLOAD_DOCUMENT =
       " insert into DFBS_DOCUMENTS(DOCUMENT_ID,DOUCUMENT_NAME,DOCUMENT_TYPE,DOCUMENT_CONTENT, " +
       " DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_CONNECTOR,DOCUMENT_CONNECTOR_TYPE,DOCUMENT_PLAN_TYPE,GIS_FLAG)" +
       " values(?,?,?,?,SYSDATE,?,?,?,?,?)";
       public final static String SELECT_DOCUMENT_NAMES_ALL =
       " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID,DOCUMENT_CONNECTOR ,UPPER(DOCUMENT_PLAN_TYPE) from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? order by 3 desc";
         public final static String SELECT_DOCUMENT_NAMES_OTHERS =
       " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID,DOCUMENT_CONNECTOR ,"+
       " UPPER(DOCUMENT_PLAN_TYPE) from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? " +
       " AND GIS_FLAG IN('GIS','OTHER') order by 3 desc";
     
        public final static String SELECT_DOCUMENT_NAME =
       " SELECT DOUCUMENT_NAME , DOCUMENT_TYPE, DOCUMENT_UPLOAD_DATE,DOCUMENT_UPLOADED_BY,DOCUMENT_ID from DFBS_DOCUMENTS WHERE DOCUMENT_CONNECTOR=? AND DOCUMENT_CONNECTOR_TYPE=? order by 3";
  public final static String SELECT_CHECK_LIST_VALIDATION =
  " SELECT COUNT(*) from DFBS_DOCUMENTS WHERE DOCUMENT_ID=? AND UPPER(DOUCUMENT_NAME) LIKE '%CHECK%LIST%'";
        public final static String SELECT_DOCUMENT =
       " SELECT DOCUMENT_CONTENT,DOCUMENT_TYPE,DOCUMENT_ID from DFBS_DOCUMENTS where DOCUMENT_ID=?";
        public final static String SELECT_DFBS_CONNECT =
       " SELECT USER_NAME,PASSWORD from  PLAN_REVIEW_ONLINEPLANS where sudo_password=?";
         public final static String DELETE_DFBS_CONNECT =
       " DELETE from  PLAN_REVIEW_ONLINEPLANS   where sudo_password=?";
        public final static String SELECT_PLAN_REVIEWER_EMAIL =  
        " SELECT distinct  EMAIL FROM PLAN_REVIEW_OFFICIAL, PLAN_FORM_FILED WHERE PLNRVOFF_OFFICIAL_ID=OFFICIAL_ID AND " +
        " PRJCTS_SBC_PROJECT_NUMBER=? and  STATUS_FLAG='A'  AND EMAIL IS NOT NULL ";
        public final static String SELECT_PLAN_CORRECTION_EMAIL =  
        " SELECT distinct  HOLD_BY||'@DHS.IN.GOV'  FROM PLAN_PROJECT_STATUS , PLAN_FORM_FILED WHERE FRMFLDS_FORM_ID =FORM_ID AND " +
        " PRJCTS_SBC_PROJECT_NUMBER=? AND HOLD_END_DATE IS NULL ";
          public final static String SELECT_DOCUMENT_COUNT=
       " SELECT COUNT(*) from DFBS_DOCUMENTS where DOCUMENT_CONNECTOR=to_char(?) AND DOCUMENT_CONNECTOR_TYPE='planReview' "+
       " AND TO_CHAR(DOCUMENT_UPLOAD_DATE,'MMDDYYYY') =TO_CHAR(SYSDATE,'MMDDYYYY') and DOCUMENT_PLAN_TYPE is not null ";
        public final static String SELECT_FORM_FILED_COUNT=
        " SELECT COUNT(*) from PLAN_FORM_FILED where TO_CHAR(PRJCTS_SBC_PROJECT_NUMBER)=to_char(?) "+
        " AND TO_CHAR(APPLICATION_ENTERED_DATE,'MMDDYYYY') =TO_CHAR(SYSDATE,'MMDDYYYY')  "+
        " AND FRM_FORM_ID IN (806292,6,682432,682435,8,9,10,15)";  
        
         public final static String UPDATE_DOCUMENT_NAME= 
        " UPDATE DFBS_DOCUMENTS SET DOUCUMENT_NAME=? WHERE DOCUMENT_ID=? ";
        public final static  String SELECT_PLAN_TYPE =
        " select abbreviation,description from dfbs_code where canned_code='PLAN_TYPE_UPLOAD' and division=4 "+
            "  order by 2 asc ";
        public final static  String SELECT_PLANS_CHECK =
            " SELECT CORRECTION_ID from PLAN_RFI_MARKUPS WHERE DOCUMENT_ID=? AND SUDO_PASSWORD=? AND DOC_OPEN_TILL_DATE >=SYSDATE ";
        public final static  String SELECT_PLANS_FOR_MARKUPS =
            " SELECT A.DOUCUMENT_NAME , A.DOCUMENT_TYPE, A.DOCUMENT_UPLOAD_DATE,A.DOCUMENT_UPLOADED_BY,A.DOCUMENT_ID ,A.DOCUMENT_CONNECTOR ,UPPER(A.DOCUMENT_PLAN_TYPE)" +
            " FROM DFBS_DOCUMENTS A,PLAN_RFI_MARKUPS B WHERE A.DOCUMENT_ID=B.DOCUMENT_ID AND B.CORRECTION_ID=? AND B.DOC_OPEN_TILL_DATE >=SYSDATE ";
        
         public final static  String UPDATE_PLAN_LBO_USER_PASSWORD =
            " UPDATE dfbs_inspector SET INTEREST=? ,CERTIFICATION_DATE=SYSDATE+90 " +
             " WHERE INSPECTOR_ID=? AND DIVISION=4 AND INSPECTOR_TYPE='LBO' AND INSPECTOR_STATUS='A' ";
         
         public final static  String VERIFY_PLAN_LBO_USERS =
        " SELECT DISTINCT INSPECTOR_ID,FIRST_NAME,LAST_NAME,INTEREST,INSPECTOR_STATUS,CERTIFICATION_DATE, " +
        " E_MAIL,TELEPHONE_OFFICE,NULL,CERTIFICATION_DATE-SYSDATE FROM DFBS_INSPECTOR "+
        " WHERE UPPER(E_MAIL)=UPPER(?)  AND UPPER(INTEREST)=UPPER(?) AND DIVISION=4 AND INSPECTOR_TYPE='LBO' AND ROWNUM<2 ";
         
          public final static  String EMAIL_PLAN_LBO_USERS_PASSWORD =
        " SELECT DISTINCT INSPECTOR_ID,FIRST_NAME,LAST_NAME,INTEREST,INSPECTOR_STATUS,CERTIFICATION_DATE, " +
        " E_MAIL,TELEPHONE_OFFICE,NULL,CERTIFICATION_DATE-SYSDATE FROM DFBS_INSPECTOR "+
        " WHERE UPPER(E_MAIL)=UPPER(?)  AND DIVISION=4 AND INSPECTOR_TYPE='LBO' AND ROWNUM<2 ";
          
         public final static  String SELECT_PROJECTS_WITH_PLANS_LBO_COUNTY =
        " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PFF.APPLICATION_ENTERED_DATE >sysdate-? AND PP.CNTYS_COUNTY_CODE=? AND PP.STATE_OWNED <>'Yes'  "+
        " AND UPPER(PP.PROJECT_CITY) NOT IN (?) " +
         " UNION SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name  FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PFF.APPLICATION_ENTERED_DATE > sysdate-? AND PP.CNTYS_COUNTY_CODE=? AND PP.STATE_OWNED <>'Yes'  "+
        " AND PP.CITY_LIMITS='No' " ;
           public final static  String SELECT_PROJECTS_WITH_PLANS_DNR =
        " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PP.FLOOD_PLAN ='Y' " ;
        
             public final static  String SELECT_PROJECTS_WITH_PLANS_IDEM =
        " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC ,PLAN_AREA_COST PAC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER AND PP.SBC_PROJECT_NUMBER=PAC.PRJCTS_SBC_PROJECT_NUMBER "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview'  "+
        "  AND PAC.FACILITY_CATEGORY='F' AND PAC.FACILITY_NEW_NOS >0  " ;
        
              public final static  String SELECT_PROJECTS_WITH_PLANS_OTHER =
        " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC  ,DFBS_COUNTY DC" +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER AND PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PP.CNTYS_COUNTY_CODE =DC.COUNTY_CODE AND DC.COUNTY_NAME=? AND DOC.GIS_FLAG IN('GIS','OTHER')";
        public final static  String SELECT_PROJECTS_WITH_PLANS_ISDH =
        " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC  " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER AND PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' ";
                    
        public final static  String SELECT_PROJECTS_WITH_PLANS_LBO_CITY = 
             " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PFF.APPLICATION_ENTERED_DATE > sysdate-? AND PP.CNTYS_COUNTY_CODE=? AND PP.STATE_OWNED <>'Yes'  "+
        " AND UPPER(PP.PROJECT_CITY) = UPPER(?) " ;
        
         public final static  String SELECT_PROJECTS_WITH_PLANS_LBO_COUNTY_PROJECT =
        " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PFF.APPLICATION_ENTERED_DATE >sysdate-? AND PP.CNTYS_COUNTY_CODE=? AND PP.STATE_OWNED <>'Yes'  "+
        " AND UPPER(PP.PROJECT_CITY) NOT IN (?) " +
         " UNION SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PFF.PRJCTS_SBC_PROJECT_NUMBER=? AND PP.CNTYS_COUNTY_CODE=? AND PP.STATE_OWNED <>'Yes'  "+
        " AND PP.CITY_LIMITS='No' order by 1 " ;
         
        public final static  String SELECT_PROJECTS_WITH_PLANS_LBO_CITY_PROJECT = 
             " SELECT DISTINCT PFF.PRJCTS_SBC_PROJECT_NUMBER,PP.PROJECT_ADDRESS1||','||PP.PROJECT_ADDRESS2 ,PP.PROJECT_CITY,pp.project_name FROM PLAN_PROJECT PP,PLAN_FORM_FILED PFF ,"+
        " DFBS_DOCUMENTS DOC " +
        " WHERE PP.SBC_PROJECT_NUMBER=PFF.PRJCTS_SBC_PROJECT_NUMBER  "+
        " AND TO_CHAR(PP.SBC_PROJECT_NUMBER)= DOC.DOCUMENT_CONNECTOR AND DOC.DOCUMENT_CONNECTOR_TYPE='planReview' AND "+
        " PFF.PRJCTS_SBC_PROJECT_NUMBER=? AND PP.CNTYS_COUNTY_CODE=? AND PP.STATE_OWNED <>'Yes'  "+
        " AND UPPER(PP.PROJECT_CITY) = UPPER(nvl(?,PP.PROJECT_CITY)) order by 1 " ;
        
        public final static  String SELECT_LBO_AREA =
        " SELECT DC.COUNTY_CODE,CCS.COUNTY_NAME, CCS.CITY_NAME FROM  CODE_COUNTY_SCOPE CCS, DFBS_COUNTY DC,DFBS_INSPECTOR DI "+
        " WHERE DI.INSPECTOR_ID=CCS.NSPCTRS_INSPECTOR_ID AND UPPER(DI.E_MAIL)= UPPER(?) AND "+
        " CCS.COUNTY_NAME=DC.COUNTY_NAME ";
            
       public final static  String SELECT_LBO_COUNTY_CITY_NAMES = 
        "SELECT COUNTY_NAME,CITY_NAME FROM  CODE_COUNTY_SCOPE WHERE COUNTY_NAME=? ";
       
       public final static  String INSERT_PLAN_FORM_FILED =
        " INSERT INTO PLAN_FORM_FILED (PRJCTS_SBC_PROJECT_NUMBER,FRM_FORM_ID,FORM_ID,APPLICATION_ENTERED_DATE,PLNRVOFF_OFFICIAL_ID,  " +
        " NUMBER_OF_FILINGS) VALUES (?,675693,FORM_ID.NEXTVAL,SYSDATE,100,1)";
       public final static String DELETE_DOCUMENT =
      " DELETE FROM  DFBS_DOCUMENTS WHERE DOCUMENT_ID=?  ";      
}
