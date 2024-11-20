package idhsInspections.control.action;
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
public class fireAEPermitApprovalAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           
           ServletContext context = 
           this.getServlet().getServletConfig().getServletContext();
          
           
           DfbsDataMap dmap2 = (DfbsDataMap)
           context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           
           
           fireAEPermitApprovalForm facUseForm = (fireAEPermitApprovalForm) form;
          
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           fireInspectionDAO iDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
           idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION);
           fireViolationDAO vDAO = (fireViolationDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_VIOLATION);
           ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
             fireAEPermitApprovalDAO faDAO = (fireAEPermitApprovalDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_AE_FAC_USE);
           String method = request.getParameter("method");
           HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
              ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
             if (method==null) {return mapping.findForward("inspectionStart"); 
             }
             else if (method.equals("goToFacUse")) 
                     { return mapping.findForward("aeFacUse"); 
                         
                     }
           else if (method.equals("approve")) 
                   {   fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                       String inspFacId = request.getParameter("inspFacId");
                       String inspDate = request.getParameter("inspDate");
                       faDAO.updateAEPermitIssueDate(inspFacId,inspection.getInspDateString(),inspDate);
                       List inspList =  faDAO.selectPermitsForApproval(inspFacId);
                       request.setAttribute("AE_APPROVAL_LIST",new HsListWrapper(inspList)); 
                       fireAEPermitApproval facUse= (fireAEPermitApproval) session.getAttribute("AE_FACILITY_USE");
                       facUseForm.setProperties(facUse);
                   return mapping.findForward("updateFacUse"); 
                       
                   }
           else if (method.equals("updateFacUse")) 
                   {  
                     String inspFacId = request.getParameter("inspFacId");
                       List inspDateList =iDAO.selectInspectionsAeApproval(inspFacId);
                       session.setAttribute("INSPECTION_LIST_AE_APPROVAL",new HsListWrapper(inspDateList)); 
                       List inspList =  faDAO.selectPermitsForApproval(inspFacId);
                       request.setAttribute("AE_APPROVAL_LIST",new HsListWrapper(inspList)); 
                      fireAEPermitApproval facUse=faDAO.selectFacUse(inspFacId);
                       if (facUse.getInspFacId() ==null) { 
                          facUse = new   fireAEPermitApproval();
                      }
                       facUseForm.setProperties(facUse);
                      session.setAttribute("AE_FACILITY_USE",facUse);
                      setOptions(request,dfbsUtilityDAO);
                      return mapping.findForward("updateFacUse");
                   }
             else if (method.equals("saveFacUse")) 
                     { 
                        fireAEPermitApproval facUse=facUseForm.getFacUse();
                         session.setAttribute("AE_FACILITY_USE",facUse);
                         fireAEPermitApprovalForm errorForm = new fireAEPermitApprovalForm();
                         if (validate(facUse,errorForm) ) 
                         {if (facUse.getInspFacId()==null || facUse.getInspFacId().equals("") ) 
                         {
                             fireInspection inspection = (fireInspection) session.getAttribute("INSPECTION_SELECTED");
                             facUse.setInspFacId(inspection.getInspFacId());
                         faDAO.insertFacUse(facUse);
                         }
                         else  {
                                 faDAO.updateFacUse(facUse);
                             }
                         }  
                        return mapping.findForward("updateFacUse");
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
    

    
    protected static boolean validate(fireAEPermitApproval facUse,fireAEPermitApprovalForm errorForm) throws Exception
     {
       boolean noError = true;
           
       if(facUse.getFac_arena()== null && facUse.getFac_school()==null  && facUse.getFac_thetre()==null  && facUse.getFacAmLegion()== null &&
          facUse.getFacBanquet()==null && facUse.getFacBar()==null &&facUse.getFacBilliardPool()==null && facUse.getFacBowling() ==null &&
          facUse.getFacCasino() ==null && facUse.getFacHaunted()== null && facUse.getFacOther()==null && facUse.getFacPrivClub() == null &&
          facUse.getFacRacTrack() == null && facUse.getFacRestaurant()== null && facUse.getFacSwimPool() ==null) 
       { 
         errorForm.setFac_arena("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setFac_arena("");
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
               String[] bccTo = {"nnimmagadda@dhs.in.gov"};
             
             
                   StringBuffer sb = new StringBuffer("\r\n");
                   sb.append(" You have tagged this facility for re-inspection " +inspection.getInspFacId()+".");
                   sb.append(" The re-inspection is scheduled on date " +inspection.getNextInspDateString()+".");
                   sb.append(" Facility Address is: "+ inspection.getFacilityStreetPrefix()+" "+inspection.getFacilitySteetNumber()+
                   " "+inspection.getFacilityStreetName()+" "+inspection.getFacilityStreetSuffix()+". ");
                   sb.append(" \r\n " +inspection.getFacilityCity()+" "+"IN"+" "+inspection.getFacilityZip()+".");
                   sb.append(" \r\n Notes:" +inspection.getNotes()+".");
               
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
     
  /*  public void refineSearchList(inspectionSearch search, String streetNumber, String city,String county,
                         HttpServletRequest request,idhsInspectionsDAO idhsDAO,String searchZip) throws Exception 
    {
       
        List elevList = idhsDAO.selectElevators(null,streetNumber,city,county,search,searchZip);
        request.setAttribute("ELEVATOR_SEARCH_LIST",new HsListWrapper(elevList));
        search.setElevList(elevList);
        List bpvList = idhsDAO.selectBpv(null,streetNumber,city,county,search,searchZip);
        request.setAttribute("BPV_SEARCH_LIST",new HsListWrapper(bpvList));
        search.setBpvList(bpvList);
        List aeList = idhsDAO.selectAepermits(null,streetNumber,city,county,search,null,null,searchZip);
        request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
        search.setAeList(aeList);
        List dayCareList = idhsDAO.selectDaycares(null,streetNumber,city,county,search,null,searchZip);
        request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
        search.setDayCareList(dayCareList);
        List schoolList = idhsDAO.selectSchools(null,streetNumber,city,county,search,null,searchZip);
        request.setAttribute("SCHOOL_SEARCH_LIST",new HsListWrapper(schoolList));  
        search.setSchoolList(schoolList);
        List othersList = idhsDAO.selectOthers(null,streetNumber,city,county,search,null,searchZip);
        request.setAttribute("OTHER_SEARCH_LIST",new HsListWrapper(othersList));  
        search.setOtherList(othersList);
        List hospitalsList = idhsDAO.selectHospitals(null,streetNumber,city,county,search,null,searchZip);
        request.setAttribute("HOSPITAL_SEARCH_LIST",new HsListWrapper(hospitalsList)); 
        search.setHospitalList(hospitalsList);
        List plansList = idhsDAO.selectPlans(null,streetNumber,city,county,search,null,searchZip);
        request.setAttribute("PLAN_SEARCH_LIST",new HsListWrapper(plansList));
        search.setPlanList(plansList);
        List fireworksRetailList = idhsDAO.selectFireworksRetail(null,streetNumber,city,county,search,null,null,searchZip);
        request.setAttribute("FIREWORKS_RETAIL_SEARCH_LIST",new HsListWrapper(fireworksRetailList)); 
        search.setFireworksRetailList(fireworksRetailList);
        List fireworksWholesaleList = idhsDAO.selectFireworksRetail(null,streetNumber,city,county,search,null,null,searchZip);
        request.setAttribute("FIREWORKS_WHOLESALE_SEARCH_LIST",new HsListWrapper(fireworksWholesaleList)); 
        search.setFireworksWholesaleList(fireworksWholesaleList);
        List ustList = idhsDAO.selectUSTs(null,streetNumber,city,county,search,null,searchZip);
        request.setAttribute("UST_SEARCH_LIST",new HsListWrapper(ustList)); 
        search.setUstList(ustList);
        List newInspList = idhsDAO.selectNewInspections(null,streetNumber,city,county,search,searchZip);
        request.setAttribute("NEW_INSP_SEARCH_LIST",new HsListWrapper(newInspList)); 
        search.setNewInspList(newInspList);
    }
    */
}