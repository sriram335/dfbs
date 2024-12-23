package idhsInspections.control.action;

import codeEducation.to.CodeEducationCourse;

import idhsInspections.control.*;
import idhsInspections.control.form.*;
import idhsInspections.to.*;
import idhsInspections.data.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import main.to.*;
import hs.util.*;
import hs.data.*;
import main.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import main.data.DfbsDataMap;

import org.apache.struts.upload.FormFile;

public class fireInspectionAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           
           ServletContext context = 
           this.getServlet().getServletConfig().getServletContext();
          
           
           DfbsDataMap dmap2 = (DfbsDataMap)
           context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           
           
           fireInspectionForm inspectionForm = (fireInspectionForm) form;
          
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           fireInspectionDAO iDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
           idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION);
           fireViolationDAO vDAO = (fireViolationDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_VIOLATION);
           ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
             fireInspActivityDAO faDAO = (fireInspActivityDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSP_ACTIVITY);
             rule13DAO rDAO = (rule13DAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION_RULE13);
           fireAEPermitApprovalDAO fsDAO = (fireAEPermitApprovalDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_AE_FAC_USE);
           String method = request.getParameter("method");
           HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
             ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
             if (method==null) {return mapping.findForward("inspectionStart"); 
             }
             if (method.equals("startOver")) {
                 session.setAttribute("INSPECTION_SEARCH_ID",null);
                 session.setAttribute("INSPECTION_SELECTED",null);
                 session.setAttribute("RESULTS_FLAG",null); 
                 session.setAttribute("CURRENT_SEARCH",null); 
                 StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                 redirectUrl.append("/dfbs/idhsInspections/search.do");
                 response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;

             }
           if (method.equals("inspectionStart")) { 
               String facilityId = request.getParameter("facilityId");
               List fileList=null ;
              
                String result_flag= (String) session.getAttribute("RESULTS_FLAG"); 
                String  filterSQL= InspectionsSQL.SELECT_INSPECTIONS; 
             if (result_flag !=null && result_flag.equals("AEPERMITS")) {  
                  fileList = idhsDAO.selectFileList(facilityId,"AEPermit");
                  filterSQL =filterSQL + " AND FRNTR_IDENTIFICATION_NUMBER=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
              } 
               if (result_flag !=null && result_flag.equals("SCHOOL")) { fileList = idhsDAO.selectFileList(facilityId,"School");
                   filterSQL =filterSQL + " AND FRSCHOL_FIRE_ID=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
               }
               if (result_flag !=null && result_flag.equals("FIREWORKS_RETAIL")) { fileList = idhsDAO.selectFileList(facilityId,"Fireworks");
                                 filterSQL =filterSQL + " AND FRSALES_IDENTIFICATION_NUMBER=? "+InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST ;
               }
                if (result_flag !=null && result_flag.equals("FIREWORKS_WHOLESALE")) {fileList = idhsDAO.selectFileList(facilityId,"Fireworks");
                   filterSQL =filterSQL + " AND FRCPL_IDENTIFICATION_NUMBER=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
               }
               
               if (result_flag !=null && result_flag.equals("DAYCARE")) {fileList = idhsDAO.selectFileList(facilityId,"ChildCare");
                   filterSQL =filterSQL + " AND FDC_FIRE_ID=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
               }
               if (result_flag !=null && result_flag.equals("HOSPITAL")) {
                   filterSQL =filterSQL + " AND FRHOSP_FIRE_ID=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
               }
               if (result_flag !=null && result_flag.equals("OTHER")) {fileList = idhsDAO.selectFileList(facilityId,"Hospital");
                   filterSQL =filterSQL + " AND FRTHR_IDENTIFICATION_NUMBER=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
               }
               if (result_flag !=null && result_flag.equals("NEW_INSPECTIONS")) {
                   filterSQL =InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST_NEW   ;
                 List inspList = iDAO.selectInspectionsNew(facilityId,filterSQL);
                   request.setAttribute("INSPECTION_LIST",new HsListWrapper(inspList)); 
                   return mapping.findForward("inspectionStart");  
               }
               if (result_flag !=null && result_flag.equals("PLAN")) {fileList = idhsDAO.selectFileList(facilityId,"planReview");
                   filterSQL =InspectionsSQL.SELECT_INSPECTIONS_CODE +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
               }
             if (result_flag !=null && result_flag.equals("NEW_INSPECTIONS_IND_MOBILE")) {
                 filterSQL =InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST_NEW   ;
               List inspList = iDAO.selectInspectionsNew(facilityId,filterSQL);
                 request.setAttribute("INSPECTION_LIST",new HsListWrapper(inspList)); 
                 return mapping.findForward("inspectionStart");  
             }
               session.setAttribute("INSPECTION_FILE_LIST",fileList);
               List inspList =null;
              
               if (result_flag ==null){    
                   inspList = (List)session.getAttribute("GROUP_INSPECTION_LIST");
                   session.setAttribute("INSPECTION_SEARCH_ID",null);
               }else{
                   session.setAttribute("INSPECTION_SEARCH_ID",facilityId);
                   inspList = iDAO.selectInspections(facilityId,filterSQL);
                                  if (result_flag !=null && result_flag.equals("SCHOOL") && inspList.size()==0) {
                       filterSQL =InspectionsSQL.SELECT_INSPECTIONS + " AND FDC_FIRE_ID=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
                      inspList = iDAO.selectInspections(facilityId,filterSQL);
                     }
               }
               request.setAttribute("INSPECTION_LIST",new HsListWrapper(inspList)); 
               return mapping.findForward("inspectionStart");  
           }
          // insp start fssa
          if (method.equals("inspStartFssa")) { 
              String facilityId = request.getParameter("facilityId");
              List fileList=null ;
              session.setAttribute("INSPECTION_SEARCH_ID",facilityId);
              String  filterSQL= InspectionsSQL.SELECT_INSPECTIONS;
               String result_flag= (String) session.getAttribute("RESULTS_FLAG"); 
             if (result_flag !=null && result_flag.equals("DAYCARE")) {fileList = idhsDAO.selectFileList(facilityId,"ChildCare");
                  filterSQL =filterSQL + " AND FDC_FIRE_ID=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
              }
              
              session.setAttribute("INSPECTION_FILE_LIST",fileList);
              List inspList =null;
              inspList = iDAO.selectInspections(facilityId,filterSQL);
                             if (result_flag !=null && result_flag.equals("SCHOOL") && inspList.size()==0) {
                  filterSQL =InspectionsSQL.SELECT_INSPECTIONS + " AND FDC_FIRE_ID=? " +InspectionsSQL.SELECT_IDHS_INSPECTIONS_LIST;
                 inspList = iDAO.selectInspections(facilityId,filterSQL);
                }
              request.setAttribute("INSPECTION_LIST",new HsListWrapper(inspList)); 
              return mapping.findForward("inspStartFssa");  
          }
          // end insp start fssa
             else if (method.equals("updateIdhsInspection")) 
             {int inspectionId = Integer.parseInt(request.getParameter("inspectionId"));
               fireInspection inspection=iDAO.selectInspectionNew(inspectionId);
               session.setAttribute("INSPECTION_SEARCH_ID",inspection.getInspFacId());
                  String currentSupervisor=iDAO.selectInspectionSupervisor(inspection.getInspInspectorId());
                 session.setAttribute("INSPECTOR_SUPERVISOR",currentSupervisor);
                inspection.setViolations(vDAO.selectIdhsViolations(inspection.getInspId()));
                inspectionForm.setProperties(inspection); 
               String facType="xx";
                if (inspection.getInspFacId() !=null)
                {
                 facType=inspection.getInspFacId().substring(0,2);
                if(facType.equals("FW")) {
                  String facTypeFireworks =inspection.getInspFacId().substring(0,3);
                   if (facTypeFireworks.equals("FWR")||facTypeFireworks.equals("FWT")) {
                    facType =facTypeFireworks;
                  }
                }
                }               
               session.setAttribute("PRINT_FAC_TYPE",facType);
               session.setAttribute("INSPECTION_SELECTED",inspection);
             List fineList  =iDAO.selectFines(inspection.getInspFacId());
               session.setAttribute("FINE_LIST",fineList);
                setOptions(request,dfbsUtilityDAO);
               int ruleCount =rDAO.countRule13s(inspection.getInspId());
               if (ruleCount ==0 ) {
                   session.setAttribute("RULE13_FLAG","INSERT");
               }
               else {
                   session.setAttribute("RULE13_FLAG","UPDATE");
               }
               if(inspection.getInspFacId().substring(0,2).toUpperCase().equals("AE")){
                    List spList = fsDAO.selectSpecialsforPrint(inspection.getInspFacId());
                    session.setAttribute("SPECIALS_READY_TO_PRINT",spList);
                  }
               else {
                 session.setAttribute("SPECIALS_READY_TO_PRINT",null);
               }
                 System.out.println("INSPECTOR_SUPERVISOR:"+currentSupervisor);
                 System.out.println("inspInspectorId:"+inspection.getInspInspectorId());
                 System.out.println("INSPECTOR_CURRENT:"+session.getAttribute("INSPECTOR_CURRENT"));
              System.out.println("getEditFlag:"+inspection.getEditFlag());
              /*  if(inspection.getEditFlag() > 15){
                   return mapping.findForward("viewIdhsInspection");    
               }else{
                   return mapping.findForward("updateIdhsInspection");
               }*/
                 return mapping.findForward("updateIdhsInspection");
             }
             else if (method.equals("saveUpdateIdhsInspection")) 
             { fireInspection inspection = inspectionForm.getFireInspection();
               inspection.setInspDateString(iDAO.formatDfbsDate(inspection.getInspDateString()));
                inspection.setNextInspDateString(iDAO.formatDfbsDate(inspection.getNextInspDateString()));
             fireInspectionForm errorForm = new fireInspectionForm();
             if (validate(inspection,errorForm) ) 
             {
             iDAO.updateInspectionNew(inspection);
             }  inspectionForm.setProperties(inspection);
                 inspection.setViolations(vDAO.selectIdhsViolations(inspection.getInspId()));
               session.setAttribute("INSPECTION_SELECTED",inspection);
               setOptions(request,dfbsUtilityDAO);
               if(inspection.getInspFacId().substring(0,2).toUpperCase().equals("AE")){
                    List spList = fsDAO.selectSpecialsforPrint(inspection.getInspFacId());
                    session.setAttribute("SPECIALS_READY_TO_PRINT",spList);
                  }
               else {
                 session.setAttribute("SPECIALS_READY_TO_PRINT",null);
               }
              return mapping.findForward("updateIdhsInspection");
             }
             else if (method.equals("saveInsertIdhsInspection")) 
             {    fireInspection inspection = inspectionForm.getFireInspection();
                  inspection.setInspDateString(iDAO.formatDfbsDate(inspection.getInspDateString()));
                   inspection.setNextInspDateString(iDAO.formatDfbsDate(inspection.getNextInspDateString()));
             fireInspectionForm errorForm = new fireInspectionForm();
             int checkNewFlag=0;
             if (validate(inspection,errorForm) ) 
             {if (inspectionForm.getCreateNewAE() !=null) {
                inspection.setInspFacId("AE");
                iDAO.createAEandBUnumbers(inspection);
                checkNewFlag=1;
              }
               if (inspectionForm.getCreateNewHM() !=null) {
                               inspection.setInspFacId("HM");
                               iDAO.createAEandBUnumbers(inspection);
                               checkNewFlag=1;
                             }
               if (inspectionForm.getCreateNewBU() !=null) {
                                inspection.setInspFacId("BU");
                               iDAO.createAEandBUnumbers(inspection);
                               checkNewFlag=1;
                             }
               if (inspectionForm.getCreateNewJP() !=null) {
                               inspection.setInspFacId("JP");
                               iDAO.createAEandBUnumbers(inspection);
                               checkNewFlag=1;
                             }
              iDAO.insertInspectionNew(inspection,security);
               fireInspection inspectionWithVio =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                
            if (inspection.getInspViolationStatus().equals("COMPLIED"))
            {inspection.setViolations(null);}
            else
            {
             if (inspectionWithVio !=null && inspectionWithVio.getViolations().size()>0) {
                   List violations = inspectionWithVio.getViolations();
                    Iterator i = violations.iterator();
                    while(i.hasNext())
                    {
                        fireViolation newViolation = (fireViolation) i.next();
                        vDAO.insertIdhsViolation(newViolation,inspection);
                        inspection.setViolations(vDAO.selectIdhsViolations(inspection.getInspId()));
                    }
               }
             }
                if(inspection.getNextInspDate() !=null ||inspection.getNextInspDateString().length()>3)
                {
                 sendEmailReInspection(security.getUserId()+"@dhs.in.gov",inspection);
                }
               inspectionForm.setProperties(inspection);
               session.setAttribute("INSPECTION_SELECTED",inspection);
               if(inspection.getInspFacId().substring(0,2).toUpperCase().equals("AE")){
                    List spList = fsDAO.selectSpecialsforPrint(inspection.getInspFacId());
                    session.setAttribute("SPECIALS_READY_TO_PRINT",spList);
                  }
               else {
                 session.setAttribute("SPECIALS_READY_TO_PRINT",null);
               }
               setOptions(request,dfbsUtilityDAO);
               if (checkNewFlag==1)
               {
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
               redirectUrl.append("/dfbs/idhsInspections/updateOwner.do?method=addNewOwner");
               response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;
               }
               else
               {
                return mapping.findForward("updateIdhsInspection");
               }
             }
             else { setOptions(request,dfbsUtilityDAO);
                 request.setAttribute("INSPECTION_ERROR",errorForm);
                 return mapping.findForward("newIdhsInspection");
             }
             }
// new inspection pre populate
            else if (method.equals("newIdhsInspection")) 
           { setOptions(request,dfbsUtilityDAO); 
               session.setAttribute("INSPECTION_SELECTED",null);
               String inspId= (String) session.getAttribute("INSPECTOR_CURRENT");
               inspectionForm.setInspInspectorId(Integer.parseInt(inspId));
               session.setAttribute("STANDARD_VIOLATION",null);
               String facilityId = request.getParameter("facilityId");
               if (facilityId ==null)
               {
               facilityId = (String) session.getAttribute("INSPECTION_SEARCH_ID");
               }
               if (facilityId !=null)
                { inspectionSearch search = (inspectionSearch) session.getAttribute("CURRENT_SEARCH");
                int maxInspId = iDAO.selectMaxInspection(facilityId);
                if ( maxInspId >0) {
                    fireInspection inspection=iDAO.selectInspectionNew(maxInspId);
                    inspectionForm.setNewInspProperties(inspection);
               }
                else {    
                  List popList=null;
                  if(facilityId.substring(0,2).toUpperCase().equals("AE")) {
                  popList =   idhsDAO.selectAepermits(null,null,null,null,search,facilityId,null,null,null);
                  }
                  if(facilityId.substring(0,2).toUpperCase().equals("GH")||
                  facilityId.substring(0,2).toUpperCase().equals("RM")||
                  facilityId.substring(0,2).toUpperCase().equals("DC")||
                  facilityId.substring(0,2).toUpperCase().equals("MD")||
                  facilityId.substring(0,2).toUpperCase().equals("CI")) {
                  popList  = idhsDAO.selectDaycares(null,null,null,null,search,facilityId,null,null,null);
                  }
                  if(facilityId.substring(0,2).toUpperCase().equals("SC")) {
                  popList = idhsDAO.selectSchools(null,null,null,null,search,facilityId,null,null);
                  if (popList.size()==0) {
                   popList  = idhsDAO.selectDaycares(null,null,null,null,search,facilityId,null,null,null); 
                  }
                  }
                  if(facilityId.substring(0,2).toUpperCase().equals("UO")) {
                  popList = idhsDAO.selectUSTs(null,null,null,null,search,facilityId,null,null);
                  }
                  if(facilityId.substring(0,2).toUpperCase().equals("FW"))
                  {
                  if(facilityId.substring(0,3).toUpperCase().equals("FWR")||
                  facilityId.substring(0,3).toUpperCase().equals("FWT"))
                   {
                  popList = idhsDAO.selectFireworksRetail(null,null,null,null,search,facilityId,null,null,null); }
                  else
                  {
                    popList = idhsDAO.selectFireworksWholesale(null,null,null,null,search,facilityId,null,null,null);
                  }
                  }
                  
                  if(facilityId.substring(0,2).toUpperCase().equals("DD")||
                  facilityId.substring(0,2).toUpperCase().equals("SG")||
                  facilityId.substring(0,2).toUpperCase().equals("AM")||
                  facilityId.substring(0,2).toUpperCase().equals("SA")||
                  facilityId.substring(0,2).toUpperCase().equals("SH")||
                  facilityId.substring(0,2).toUpperCase().equals("ES")||
                  facilityId.substring(0,2).toUpperCase().equals("PS")||
                  facilityId.substring(0,2).toUpperCase().equals("LO")||
                  facilityId.substring(0,2).toUpperCase().equals("LT")||
                  facilityId.substring(0,2).toUpperCase().equals("HO")||
                  facilityId.substring(0,2).toUpperCase().equals("AS")||
                  facilityId.substring(0,2).toUpperCase().equals("RA")||
                  facilityId.substring(0,2).toUpperCase().equals("TR")||
                  facilityId.substring(0,2).toUpperCase().equals("DS")){
                  popList = idhsDAO.selectHospitals(null,null,null,null,search,facilityId,null,null);
                  }
                  if(facilityId.substring(0,2).toUpperCase().equals("HM")||
                  facilityId.substring(0,2).toUpperCase().equals("JP")||
                  facilityId.substring(0,2).toUpperCase().equals("CS")||
                  facilityId.substring(0,2).toUpperCase().equals("BU")) {
                  popList = idhsDAO.selectOthers(null,null,null,null,search,facilityId,null,null);
                  }
                  if(facilityId.substring(0,1).toUpperCase().equals("9")||
                    facilityId.substring(0,1).toUpperCase().equals("2")||
                    facilityId.substring(0,1).toUpperCase().equals("3")||
                    facilityId.substring(0,1).toUpperCase().equals("4")) {
                   popList = idhsDAO.selectPlans(null,null,null,null,search,facilityId,null,null);
                  }
                  if(facilityId.substring(0,1).toUpperCase().equals("M")||
                    facilityId.substring(0,1).toUpperCase().equals("P")) {
                   popList = idhsDAO.selectFacilities(null,null,null,null,search,facilityId,null,null);
                  }
                  if (popList.size() >0)
                  {
                  Iterator i = popList.iterator();
                  while(i.hasNext())
                  { 
                  searchResults result = (searchResults) i.next();
                  session.setAttribute("SEARCH_ID_FOUND","Y");
                  if (result.getResultId().equals(facilityId))
                  {
                  inspectionForm.setFacilityAddress2(result.getResultAddress2());
                  inspectionForm.setFacilityStreetName(result.getResultAddress1());
                  inspectionForm.setFacilityCity(result.getResultCity());
                  inspectionForm.setInspFacId(facilityId);
                  inspectionForm.setFacilityZip(result.getResultZip());
                  inspectionForm.setFacilityName(result.getFacilityName());
                  inspectionForm.setFacilityState("IN");
                  inspectionForm.setFacilityContact(result.getResultType());
                  inspectionForm.setFacilityPhone(result.getSearchSequence());
                    if(facilityId.substring(0,3).toUpperCase().equals("FWR")||
                    facilityId.substring(0,3).toUpperCase().equals("FWT")||facilityId.substring(0,2).toUpperCase().equals("FW"))
                     {
                       inspectionForm.setFacilityCounty(iDAO.selectCountyName(result.getResultCounty()));
                     }
                    else
                    {
                     inspectionForm.setFacilityCounty(result.getResultCounty());
                    }
                  }
                  }
                  }
                }    
                    
                    inspectionForm.setFacilityState("IN");
                    inspectionForm.setInspInspectorId(Integer.parseInt(inspId));
                    
                    int streetLength =0;
                    if (inspectionForm.getFacilityStreetName()!=null && inspectionForm.getFacilityStreetName().length() <=3) {
                      streetLength = inspectionForm.getFacilityStreetName().length();
                    }
                    else {
                      streetLength =3;
                    }
                    if(inspectionForm.getFacilityStreetName()!=null)
                    { 
                    this.refineSearchList(search,inspectionForm.getFacilityStreetName().substring(0,streetLength),inspectionForm.getFacilityCity(),inspectionForm.getFacilityCounty(),request,idhsDAO,null) ;
                    List alertList =iDAO.setAlerts(search);
                    request.setAttribute("ALERT_LIST",new HsListWrapper(alertList));
                       request.setAttribute("SEARCH_INACTIVE","N");
                   }
                   else {
                         request.setAttribute("SEARCH_INACTIVE","Y");
                  }
                    return mapping.findForward("newIdhsInspection");
                }
                
            return mapping.findForward("newIdhsInspection");
           }
// new inspection prepopulate end
             else if (method.equals("IDHSgoToUpload")) 
                      { 
                         String idNumber = request.getParameter("idNumber"); 
                        String idType = request.getParameter("idType"); 
                          List fileList =(idhsDAO.selectFileList(idNumber));
                          session.setAttribute("INSPECTION_FILE_LIST",fileList);
                          session.setAttribute("FILE_EXTENSION","");
                        return mapping.findForward("goToUpload");
                      }
             else if (method.equals("downLoadFile")) 
                    { 
                     String fileType = request.getParameter("fileType");
                     int fileId = Integer.parseInt(request.getParameter("fileId"));
                     String fileName = request.getParameter("fileName");
                     if (security !=null)
                      {
                     doGetFile(request,response,idhsDAO,fileName,fileType,fileId);
                      }
                      return null;
                    }
                      
                     else if (method.equals("uploadFile")) 
                    { 
                    
                    final FormFile oneFile = inspectionForm.getCaseFile();
                      if(oneFile != null && oneFile.getFileSize()>0 )
                       {    String idNumber = request.getParameter("idNumber"); 
                            String idType = request.getParameter("idType"); 
                            String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                             if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                                fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                                 fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                                 fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                                 fileExtension.substring(1,4).toUpperCase().equals("DWF")||fileExtension.substring(1,4).toUpperCase().equals("XLS"))
                             {
                             idhsDAO.uploadFile(oneFile,idNumber,idType);  
                                 List fileList =(idhsDAO.selectFileList(idNumber));
                                 session.setAttribute("INSPECTION_FILE_LIST",fileList);
                                 return mapping.findForward("inspectionStart");  
                             }
                           }
                           else
                           {
                           session.setAttribute("FILE_EXTENSION","ERROR");
                           return mapping.findForward("goToUpload");
                           }
                       }
           else if (method.equals("deleteFile")) 
              { 
               int fileId = Integer.parseInt(request.getParameter("fileId"));
                String idNumber = request.getParameter("idNumber"); 
              iDAO.deleteFile(fileId);
                List fileList =(idhsDAO.selectFileList(idNumber));
                session.setAttribute("INSPECTION_FILE_LIST",fileList);
                return mapping.findForward("inspectionStart");  
              } 
             else if (method.equals("lookUpMyInspections")) 
                                     
              {
                String inspStartDate = request.getParameter("inspStartDate");
                  String inspEndDate = request.getParameter("inspEndDate");
                  inspEndDate =iDAO.formatDfbsDate(inspEndDate);
                inspStartDate =iDAO.formatDfbsDate(inspStartDate);
                 String inspId= (String) session.getAttribute("INSPECTOR_CURRENT"); 
                   if(inspStartDate.length()==10 && inspEndDate.length()==10)
                  { 
                  List inspList = iDAO.selectInspections(inspStartDate,inspEndDate,inspId);
                  request.setAttribute("INSPECTION_LIST",new HsListWrapper(inspList)); 
                      String currentDate= iDAO.selectCurrentDate();
                      List activityList =  faDAO.selectInspeActivities(Integer.parseInt(inspId),inspStartDate,inspEndDate);
                      session.setAttribute("ACTIVITIES_SELECTED",activityList);
                      session.setAttribute("INSP_DATE_ERROR","N");
                  }
                  else {
                      session.setAttribute("INSP_DATE_ERROR","Y");
                  }
                  session.setAttribute("INSPECTION_FILE_LIST",null);
               return mapping.findForward("inspectionStart");
              }
             else if (method.equals("lookUpMyGroupInspections")) 
                                     
              {
                String inspStartDate = request.getParameter("inspGpStartDate");
                String inspEndDate = request.getParameter("inspGpEndDate");
                String inspectorId = request.getParameter("inspInspectorId");
                inspEndDate =iDAO.formatDfbsDate(inspEndDate);
                inspStartDate =iDAO.formatDfbsDate(inspStartDate);
                 String inspId= (String) session.getAttribute("INSPECTOR_CURRENT"); 
                   if(inspStartDate.length()==10 && inspEndDate.length()==10)
                  { 
                  List inspList = iDAO.selectInspectionsGroup(inspStartDate,inspEndDate,inspId,inspectorId);
                  request.setAttribute("INSPECTION_LIST",new HsListWrapper(inspList)); 
                  //Added below session variable to display list when Back to Inspection List is selected Dev 05/23/2015
                  session.setAttribute("GROUP_INSPECTION_LIST",inspList); 
                      String currentDate= iDAO.selectCurrentDate();
                      List activityList =  faDAO.selectInspeActivitiesGroup(Integer.parseInt(inspId),inspStartDate,inspEndDate,inspectorId);
                      session.setAttribute("ACTIVITIES_SELECTED",activityList);
                      session.setAttribute("INSP_DATE_ERROR","N");
                  }
                  else {
                      session.setAttribute("INSP_DATE_ERROR","Y");
                  }
                  session.setAttribute("INSPECTION_FILE_LIST",null);
               return mapping.findForward("inspectionStart");
              }
             else if (method.equals("lookUpStdViolation")) 
              { fireInspection inspection= (fireInspection)session.getAttribute("INSPECTION_SELECTED");
                  inspectionForm.setProperties(inspection);
                  String vioYear = request.getParameter("vioYear");
                  String vioCode = request.getParameter("vioCode");
                  String vioType = request.getParameter("vioType");
                  String vioDescription = request.getParameter("vioDescription");
                  String lsql= InspectionsSQL.SELECT_STANDARD_VIOLATION_ALL;
                  List vioList =null;
                    if (vioCode !=null && vioCode.length()>2)
                    { lsql=InspectionsSQL.SELECT_STANDARD_VIOLATION_CODE_LIKE;
                    vioList = vDAO.selectStdViolations(vioCode,vioYear,"CODE",lsql);
                    }
                  if (vioType !=null && vioType.length()>2)
                  {lsql=InspectionsSQL.SELECT_STANDARD_VIOLATION_REM_LIKE;
                  vioList = vDAO.selectStdViolations(vioType,vioYear,"REM",lsql);
                  }
                  if (vioDescription !=null && vioDescription.length()>2)
                  {lsql=InspectionsSQL.SELECT_STANDARD_VIOLATION_DESC_LIKE;
                  vioList = vDAO.selectStdViolations(vioDescription,vioYear,"DESC",lsql);
                  }
                session.setAttribute("STANDARD_VIOLATION",new HsListWrapper(vioList));
                  fireInspection inspectionStdViolations =new fireInspection();
                  inspectionStdViolations.setViolations(vioList);
                  session.setAttribute("INSPECTION_STANDARD_VIOLATION",inspectionStdViolations);
                  setOptions(request,dfbsUtilityDAO);
               return mapping.findForward("updateIdhsInspection");
              }
             else if (method.equals("addStdViolation")) 
             {String stdVioId = request.getParameter("vioId");
                 fireViolation  newViolation = new fireViolation ();
              vDAO.selectStdVioDescription(stdVioId,newViolation);
              fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
              vDAO.insertIdhsViolation(newViolation,inspection);
                 List vioList=inspection.getViolations();
                 vioList.add(newViolation);
                  setOptions(request,dfbsUtilityDAO);
                 inspectionForm.setProperties(inspection);
              return mapping.findForward("updateIdhsInspection");
             }
             else if (method.equals("addStdViolationList")) 
             {   
                
              fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                 fireInspection inspectionStdViolations = (fireInspection) session.getAttribute("INSPECTION_STANDARD_VIOLATION");
               List stdViolations = inspectionStdViolations.getViolations();
                Iterator i = stdViolations.iterator();
                while(i.hasNext())
                {
                  fireViolation violation = (fireViolation) i.next();
                  StringBuffer sb = new StringBuffer("");
                  sb.append(Integer.toString(violation.getVioId()));
                  String param = request.getParameter(sb.toString());
                  if(param != null) 
                  { 
                    fireViolation  newViolation = new fireViolation ();
                    vDAO.selectStdVioDescription(Integer.toString(violation.getVioId()),newViolation);
                    vDAO.insertIdhsViolation(newViolation,inspection);
                      List vioList=inspection.getViolations();
                      vioList.add(newViolation);
                  }
                }
                   setOptions(request,dfbsUtilityDAO);
                 inspectionForm.setProperties(inspection);
              return mapping.findForward("updateIdhsInspection");
             }
          else if (method.equals("backToInspection")) 
           { fireInspection inspection= (fireInspection)session.getAttribute("INSPECTION_SELECTED");
              if (inspection==null ||inspection.getInspId()==0) {
                  return mapping.findForward("inspectionStart");   
              }
             inspection.setViolations(vDAO.selectIdhsViolations(inspection.getInspId()));
             inspectionForm.setProperties(inspection);
             setOptions(request,dfbsUtilityDAO);
             if(inspection.getInspFacId().substring(0,2).toUpperCase().equals("AE")){
                  List spList = fsDAO.selectSpecialsforPrint(inspection.getInspFacId());
                  session.setAttribute("SPECIALS_READY_TO_PRINT",spList);
                }
             else {
               session.setAttribute("SPECIALS_READY_TO_PRINT",null);
             }
            return mapping.findForward("updateIdhsInspection");
           }
             else if (method.equals("deleteIdhsInspection")) 
              { fireInspection inspection= (fireInspection)session.getAttribute("INSPECTION_SELECTED");
                  iDAO.deleteInspectionNew(inspection);
                  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                  redirectUrl.append("/dfbs/idhsInspections/search.do?method=searchById&idNumber="+inspection.getInspFacId());
                  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;

              }
           else if (method.equals("dueInspections")) 
            { String p_days = request.getParameter("p_days");
              String inspId= (String) session.getAttribute("INSPECTOR_CURRENT");
              System.out.println(inspId);
              iDAO.updateFireReportSecurity(inspId);
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append("/reports/rwservlet?dfbsipnpdf&report=inspector_insp_status_count.rdf&p_insp_id="+inspId+"&p_days="+p_days);
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;

            }
             else if (method.equals("inspectionCounts")) 
              { String p_days = request.getParameter("p_days");
                String inspId= (String) session.getAttribute("INSPECTOR_CURRENT");
                System.out.println(inspId);
                iDAO.updateFireReportSecurity(inspId);
                  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                  redirectUrl.append("/reports/rwservlet?dfbsipnpdf&report=inspector_insp_status_count.rdf&p_insp_id="+inspId+"&p_days="+p_days);
                  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;

              }
                       
        /*     else if (method.equals("ReIdhsInspection")) 
              { String facilityId= request.getParameter("facilityId");
                  session.setAttribute("STANDARD_VIOLATION",null);
               if (facilityId ==null)
               {
               facilityId = (String) session.getAttribute("INSPECTION_SEARCH_ID");
               }
                inspectionSearch search = (inspectionSearch) session.getAttribute("CURRENT_SEARCH");
                int maxInspId = iDAO.selectMaxInspection(facilityId);
                if (maxInspId >0) {
                    fireInspection inspection=iDAO.selectInspectionNew(maxInspId);
                    inspection.setViolations(vDAO.selectIdhsViolationsReInsp(inspection.getInspId()));
                    inspectionForm.setReInspectionProperties(inspection);
                    String inspId= (String) session.getAttribute("INSPECTOR_CURRENT");
                    inspectionForm.setInspInspectorId(Integer.parseInt(inspId));
                    session.setAttribute("INSPECTION_SELECTED",inspection);
                    setOptions(request,dfbsUtilityDAO);
                    return mapping.findForward("newIdhsInspection");
                }
                else
                {
                  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                  redirectUrl.append("/dfbs/idhsInspections/inspection.do?method=newIdhsInspection&facilityId="+facilityId);
                  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;

                }
                 
              } */
        else if (method.equals("ReIdhsInspection")) 
                    { String inspectionId= request.getParameter("inspectionId");
                        session.setAttribute("STANDARD_VIOLATION",null);
                         fireInspection inspection=iDAO.selectInspectionNew(Integer.parseInt(inspectionId));
                          inspection.setViolations(vDAO.selectIdhsViolationsReInsp(inspection.getInspId()));
                          inspectionForm.setReInspectionProperties(inspection);
                         // System.out.println(inspection.getFacilityId());
                          session.setAttribute("INSPECTION_SEARCH_ID",inspection.getInspFacId());
                          String inspId= (String) session.getAttribute("INSPECTOR_CURRENT");
                          inspectionForm.setInspInspectorId(Integer.parseInt(inspId));
                          session.setAttribute("INSPECTION_SELECTED",inspection);
                          setOptions(request,dfbsUtilityDAO);
                          return mapping.findForward("newIdhsInspection");
                      }
                      
                    
             else if (method.equals("copyViolations")) 
             {
              int inspectionId = Integer.parseInt(request.getParameter("inspectionId"));
              fireInspection inspection= (fireInspection)session.getAttribute("INSPECTION_SELECTED");
                 int maxInspId = iDAO.selectMaxInspectionForViolation(inspection.getInspFacId(),inspectionId);
                  List violations= vDAO.selectIdhsViolations(maxInspId);
                 inspection.setViolations(violations);
                Iterator i = violations.iterator();
                 while(i.hasNext())
                 { 
                   fireViolation  violation = (fireViolation) i.next();
                   violation.setVioId(0);
                   vDAO.insertIdhsViolation(violation,inspection);
                 }
               setOptions(request,dfbsUtilityDAO);
               return mapping.findForward("updateIdhsInspection");
             }
             else if (method.equals("emailReport")) 
                     { String emailTo=request.getParameter("emailTo");
                         fireInspection inspection= (fireInspection)session.getAttribute("INSPECTION_SELECTED");
                       String redirectUrl = "https://oasdev.dhs.in.gov/reports/rwservlet?dfbsmailipdf&report=idhs_fire_code_insp_report.rdf&from="+security.getUserId()+"@dhs.in.gov"+
                       "&p_inspection_id="+inspection.getInspId()+
                       "&desname="+emailTo+","+security.getUserId()+"@dhs.in.gov"+"&subject=Your Recent inspection report for " +inspection.getInspFacId()+"&target=_blank";
                         response.sendRedirect(response.encodeRedirectURL(redirectUrl));
                       return null;
  
                     }
          // for fines
          else if (method.equals("fineAction")) 
           {   String inspAction=request.getParameter("inspectionAction");
             String fineStartDate=request.getParameter("fineStartDate");
             String fineEndDate=request.getParameter("fineEndDate");
             fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
             inspectionForm.setProperties(inspection);
               if (inspAction.equals("Start Fine")) {
                 iDAO.startFine(inspection, security,fineStartDate);
               }
               else {
                
                 iDAO.EndFine(inspection, security,fineEndDate);
                 if(fineEndDate.length()==10)
                 {
                 iDAO.createIdhsFines(inspection.getInspFacId(),fineEndDate);
                 }
                  else
                 {
                 iDAO.createIdhsFines(inspection.getInspFacId(),inspection.getInspDateString());
                 }
                     
               }
               
             List fineList  =iDAO.selectFines(inspection.getInspFacId());
             session.setAttribute("FINE_LIST",fineList);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("updateIdhsInspection");
           }
           else if (method.equals("deleteFine")) 
                   {  String facilityId=request.getParameter("facilityId");
                      String fineEndDate=request.getParameter("fineEndDate");
                       iDAO.deleteIdhsFines(facilityId,fineEndDate);
                     List fineList  =iDAO.selectFines(facilityId);
                     session.setAttribute("FINE_LIST",fineList);
                       fireInspection inspection= (fireInspection)session.getAttribute("INSPECTION_SELECTED");
                     inspection.setViolations(vDAO.selectIdhsViolations(inspection.getInspId()));
                     inspectionForm.setProperties(inspection);
                     setOptions(request,dfbsUtilityDAO);
                     return mapping.findForward("updateIdhsInspection");           
                   }
           else if (method.equals("deleteSelectedViolations")) 
           { fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
             List violations = inspection.getViolations();
                 Iterator i = violations.iterator();
                 while(i.hasNext())
                 { fireViolation violation = (fireViolation) i.next();
                  String vioFlag = request.getParameter(Integer.toString(violation.getVioId()));
                 if (vioFlag !=null)  
                 {vDAO.deleteViolation(violation.getVioId());  
               } }
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append("/dfbs/idhsInspections/idhsInspection.do?method=backToInspection");
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;

           }
           
           else if (method.equals("approveFireworks")) 
           { 
               fireInspection inspection =  (fireInspection) session.getAttribute("INSPECTION_SELECTED");
             String fireworksType= (String) session.getAttribute("PRINT_FAC_TYPE");
             if (fireworksType.equals("FW")) {
               iDAO.updateIssueDateFireworks(inspection.getInspFacId(),inspection.getInspDateString(),"Wholesale");
             }
             if (fireworksType.equals("FWR")||fireworksType.equals("FWT")) {
               iDAO.updateIssueDateFireworks(inspection.getInspFacId(),inspection.getInspDateString(),"Consumer"); 
             }
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append("/dfbs/idhsInspections/idhsInspection.do?method=backToInspection");
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;

           }
           throw new Exception("HS_ERROR_METHOD_INVALID");
                
         } 
       catch (Exception e) 
       {
          saveError(request,e);
         request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
          return mapping.findForward("error");
         
       }
           
         
           
         
     }
    

    
    protected static boolean validate(fireInspection inspection,fireInspectionForm errorForm) throws Exception
     {
       boolean noError = true;
           
       if(inspection.getInspDateString() == null || inspection.getInspDateString().trim().equals("")  ) 
       { 
         errorForm.setInspDate("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setInspDate("");
       }
       if(inspection.getFacilityCounty() == null || inspection.getFacilityCounty().trim().equals("")  ) 
       { 
         errorForm.setFacilityCounty("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setFacilityCounty("");
       }
         if( inspection.getInspInspectorId()==0  ) 
         { 
           errorForm.setInspInspectorId(999999);
           noError = false;
         } 
         else 
         {
           errorForm.setInspDate("");
         }
       
       if(inspection.getInspViolationStatus() == null || inspection.getInspViolationStatus().trim().equals("")  ) 
       { 
         errorForm.setInspViolationStatus("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setInspViolationStatus("");
       }
      
       if(inspection.getInspType() == null || inspection.getInspType().trim().equals("")  ) 
       { 
         errorForm.setInspType("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setInspType("");
       }
       if(inspection.getInspInspectorId() ==0 ) 
       { 
         errorForm.setInspInspectorId(999999);
         noError = false;
       } 
       else 
       {
         errorForm.setInspInspectorId(inspection.getInspInspectorId());
       }
       return noError;
     } 
      protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
    {       
       List inspType = uDAO.getOptions(InspectionsSQL.SELECT_INSPECTION_TYPE);
       List inspectors = uDAO.getOptions(InspectionsSQL.SELECT_INSPECTORS);
       List inspOptions = uDAO.getOptions(InspectionsSQL.SELECT_INSP_OPTIONS);
       List alaramOptions = uDAO.getOptions(InspectionsSQL.SELECT_ALARAM_OPTIONS);
        
       List idhsMaintInspOptions = uDAO.getOptions(InspectionsSQL.IDHS_MAINT_INSP_EXCEL_TYPE_OPTIONS);
        List idhsInspOptions = uDAO.getOptions(InspectionsSQL.IDHS_EXCEL_INSP_TYPE_OPTIONS);
       List idhsInspActivityOptions = uDAO.getOptions(InspectionsSQL.IDHS_INSP_ACTIVITY_OPTIONS);
        List idhsStPrefixOptions = uDAO.getOptions(InspectionsSQL.IDHS_ST_PREFIX_OPTIONS);
        List idhsStSuffixOptions = uDAO.getOptions(InspectionsSQL.IDHS_ST_SUFFIX_OPTIONS);
        List idhsRGOptions = uDAO.getOptions(InspectionsSQL.IDHS_RED_GREEN_OPTIONS);
        List idhSYesNoptions = uDAO.getOptions(InspectionsSQL.IDHS_YES_NO_OPTIONS);
        List countyOptions2 = uDAO.getOptions(InspectionsSQL.SELECT_COUNTY_OPTIONS2);
        List inspStatusOptions = uDAO.getOptions(InspectionsSQL.IDHS_INSP_STATUS_OPTIONS);
        
       request.setAttribute("INSPECTION_TYPE_OPTIONS",inspType);
       request.setAttribute("INSPECTORS_OPTIONS",inspectors);
       request.setAttribute("ALARAM_OPTIONS",alaramOptions);
       request.setAttribute("COMPLIED_OPTIONS",inspOptions);
        request.setAttribute("IDHS_MAINT_INSP_TYPE_OPTIONS",idhsMaintInspOptions); 
        request.setAttribute("IDHS_INSP_TYPE_OPTIONS",idhsInspOptions); 
        request.setAttribute("IDHS_INSP_ACTIVITY_OPTIONS",idhsInspActivityOptions); 
        request.setAttribute("IDHS_ST_PREFIX_OPTIONS",idhsStPrefixOptions); 
        request.setAttribute("IDHS_ST_SUFFIX_OPTIONS",idhsStSuffixOptions); 
        request.setAttribute("IDHS_RG_OPTIONS",idhsRGOptions); 
         request.setAttribute("IDHS_YES_NO_OPTIONS",idhSYesNoptions); 
        request.setAttribute("IDHS_COUNTY_OPTIONS2",countyOptions2);
        request.setAttribute("IDHS_INSP_STATUS_OPTIONS",inspStatusOptions);
        
    } 
        public void doGetFile(HttpServletRequest request, HttpServletResponse response,idhsInspectionsDAO idhsDAO,String fileName,String fileType,int fileId) throws Exception 
        {  
          try {response.setContentType(fileType); 
          response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
           java.io.OutputStream out = response.getOutputStream(); 
           int lenFile = idhsDAO.downLoad(out,fileId);
            out.flush(); 
           out.close();
          } 
                   catch(Exception e) 
                   {
                     throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
                   }
           
    }
    
    private static void sendEmailReInspection(String userId,fireInspection inspection) throws Exception
     {
             try {
               HsMailer mail = new HsMailer();
               String[] emailTo = {userId};
               String[] bccTo = {userId};
             
             
                   StringBuffer sb = new StringBuffer("\r\n");
                   sb.append("You have tagged this facility for re-inspection " +inspection.getInspFacId()+".");
                   sb.append("\r\nThe re-inspection is scheduled on date " +inspection.getNextInspDateString()+".");
                   sb.append("\r\n Facility Address is: "+inspection.getFacilityName());
                   sb.append("\r\n Facility Address is: "+ inspection.getFacilityStreetPrefix()+" "+inspection.getFacilitySteetNumber()+
                   " "+inspection.getFacilityStreetName()+" "+inspection.getFacilityStreetSuffix()+". ");
                   sb.append("\r\n " +inspection.getFacilityCity()+" "+"IN"+" "+inspection.getFacilityZip()+".");
                   sb.append("\r\n Notes:" +inspection.getNotes()+".");
                   sb.append("\r\n You can drag and drop this email on to the calendar to create a reminder in out look.");
               StringBuffer sub = new StringBuffer();
               sub.append(" IDHS Fire and Code Re-inspection reminder  ");
             
             
             
               mail.createMail("IdhsInspections_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
               mail.sendAndClose();
               } 
               catch(Exception e) 
               {
                 HsMailer mail = new HsMailer();
               mail.createMail("IdhsInspections_online@dhs.in.gov","nnimmagadda@dhs.in.gov","sendEmailReInspection",userId);
               mail.sendAndClose();
               }
     }  
     
  public void refineSearchList(inspectionSearch search, String streetNumber, String city,String county,
                       HttpServletRequest request,idhsInspectionsDAO idhsDAO,String facilityId) throws Exception 
  {
      List elevList = idhsDAO.selectElevators(null,streetNumber,city,county,search,null,null);
      request.setAttribute("ELEVATOR_SEARCH_LIST",new HsListWrapper(elevList));
      search.setElevList(elevList);
      List bpvList = idhsDAO.selectBpv(null,streetNumber,city,county,search,null,null);
      request.setAttribute("BPV_SEARCH_LIST",new HsListWrapper(bpvList));
      search.setBpvList(bpvList);
      List aeList = idhsDAO.selectAepermits(null,streetNumber,city,county,search,facilityId,null,null,null);
      request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
      search.setAeList(aeList);
      List dayCareList = idhsDAO.selectDaycares(null,streetNumber,city,county,search,facilityId,null,null,null);
      request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
      search.setDayCareList(dayCareList);
      List schoolList = idhsDAO.selectSchools(null,streetNumber,city,county,search,facilityId,null,null);
      request.setAttribute("SCHOOL_SEARCH_LIST",new HsListWrapper(schoolList));  
      search.setSchoolList(schoolList);
      List othersList = idhsDAO.selectOthers(null,streetNumber,city,county,search,facilityId,null,null);
      request.setAttribute("OTHER_SEARCH_LIST",new HsListWrapper(othersList));  
      search.setOtherList(othersList);
       List hospitalsList = idhsDAO.selectHospitals(null,streetNumber,city,county,search,facilityId,null,null);
      request.setAttribute("HOSPITAL_SEARCH_LIST",new HsListWrapper(hospitalsList)); 
      search.setHospitalList(hospitalsList);
      List plansList = idhsDAO.selectPlans(null,streetNumber,city,county,search,null,null,null);
      request.setAttribute("PLAN_SEARCH_LIST",new HsListWrapper(plansList));
      search.setPlanList(plansList);
      List fireworksRetailList = idhsDAO.selectFireworksRetail(null,streetNumber,city,county,search,facilityId,null,null,null);
      request.setAttribute("FIREWORKS_RETAIL_SEARCH_LIST",new HsListWrapper(fireworksRetailList)); 
      search.setFireworksRetailList(fireworksRetailList);
      List fireworksWholesaleList = idhsDAO.selectFireworksRetail(null,streetNumber,city,county,search,facilityId,null,null,null);
      request.setAttribute("FIREWORKS_WHOLESALE_SEARCH_LIST",new HsListWrapper(fireworksWholesaleList)); 
      search.setFireworksWholesaleList(fireworksWholesaleList);
      List ustList = idhsDAO.selectUSTs(null,streetNumber,city,county,search,facilityId,null,null);
      request.setAttribute("UST_SEARCH_LIST",new HsListWrapper(ustList)); 
      search.setUstList(ustList);
      List newInspList = idhsDAO.selectNewInspections(null,streetNumber,city,county,search,null);
      request.setAttribute("NEW_INSP_SEARCH_LIST",new HsListWrapper(newInspList)); 
      search.setNewInspList(newInspList);
  }
    
}