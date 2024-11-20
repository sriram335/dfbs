package code.control.action;
import code.to.*;
import code.control.form.*;
import code.data.* ;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.data.* ;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
public class CodeReleaseAction extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        CodeReleaseForm releaseForm = (CodeReleaseForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CodeDesignReleaseDAO rDAO = (CodeDesignReleaseDAO) dmap2.getHsDAO(DfbsDataMap.RELEASE);
        CodeFacilityDAO fDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER");
         if  (method.equals("newRelease"))
          
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newRelease");
        }
        
         else if (method.equals("saveRelease")) 
       { 
           setOptions(request,dfbsUtilityDAO);
          CodeDesignRelease release =  releaseForm.getCodeDesignRelease();
          String param = request.getParameter("releaseApproval");
          if(param != null) 
          {
            release.setReleaseApproval("Y");;
          }
           setReleaseScope(request,release);
          session.setAttribute("RELEASE_SELECTED",release);
          CodeReleaseForm errorForm = new CodeReleaseForm();
          if (validate(release,errorForm) ) {
            manufacturer.addRelease(release);
           // addFacilitiesToRelease(request,manufacturer,release);
           String facility1 = request.getParameter("facilityId1");
           String facility2 = request.getParameter("facilityId2");
           String facility3 = request.getParameter("facilityId3");
           String facility4 = request.getParameter("facilityId4");
           if (Integer.parseInt(facility1) >0)
           { CodeFacility facility = fDAO.selectFacility(Integer.parseInt(facility1));
             release.addFacility(facility,release);
           }
            if (Integer.parseInt(facility2) >0)
           { CodeFacility facility = fDAO.selectFacility(Integer.parseInt(facility2));
             release.addFacility(facility,release);
           }
            if (Integer.parseInt(facility3) >0)
           { CodeFacility facility = fDAO.selectFacility(Integer.parseInt(facility3));
             release.addFacility(facility,release);
           }
            if (Integer.parseInt(facility4) >0)
           { CodeFacility facility = fDAO.selectFacility(Integer.parseInt(facility4));
             release.addFacility(facility,release);
           }
            rDAO.computeFees(manufacturer);
            return mapping.findForward("backtoview");
          } 
          else 
          {
          
            request.setAttribute("RELEASE_ERROR",errorForm);
            return mapping.findForward("newRelease");
          }
          
        } 
        else if (method.equals("backtoview")) 
        { setOptions(request,dfbsUtilityDAO);
          manufacturer.setReleaseList(rDAO.selectReleasesList(manufacturer));
          return mapping.findForward("backtoview");
        }
      
    
       else if (method.equals("editRelease")) 
        { setOptions(request,dfbsUtilityDAO);
         String key = request.getParameter("key");
          CodeDesignRelease release = manufacturer.getCodeRelease(key);
          releaseForm.setProperties(release);
          manufacturer.removeCodeDesignRelease(key);
          return mapping.findForward("editRelease");
        }
         else if (method.equals("sendApprovalEmail")) 
        { setOptions(request,dfbsUtilityDAO);
         CodeDesignRelease rel = (CodeDesignRelease) session.getAttribute("UPDATE_RELEASE");
         CodeDesignRelease release = rDAO.selectCDR(rel.getSystemId(),manufacturer);
         releaseForm.setProperties(release);
          sendEmailApproval(release.getSystemId(),manufacturer);
           return mapping.findForward("updateRelease");
        }
         else if (method.equals("addReleaseDate")) 
        {  setOptions(request,dfbsUtilityDAO);
         int systemId = Integer.parseInt(request.getParameter("systemId"));
          CodeDesignRelease release = rDAO.selectCDR(systemId,manufacturer);
         releaseForm.setProperties(release);
          session.setAttribute("UPDATE_RELEASE",release);
            session.setAttribute("UPDATE_RELEASE_LIST",new HsListWrapper(release.getCannedCodes()));
          
          return mapping.findForward("updateRelease");
        }
        else if (method.equals("updateRelease")) 
        { setOptions(request,dfbsUtilityDAO);
          CodeDesignRelease release =  releaseForm.getCodeDesignRelease();
           String relApproval = request.getParameter("releaseApproval");
          if(relApproval != null) 
          {
           release.setReleaseApproval("Y");;
          }
          rDAO.updateCDR(release,manufacturer);
          // facility update
              Map facilityMap = manufacturer.getFacilityMap();
             Set facilitykeys = facilityMap.keySet();
             Iterator j = facilitykeys.iterator();
             while(j.hasNext())
             {
             
             String keyf = (String) j.next();
             CodeFacility facility = (CodeFacility)  facilityMap.get(keyf);
              StringBuffer sb = new StringBuffer("");
             sb.append(Integer.toString(facility.getFacilityId()));
             String param = request.getParameter(sb.toString());
             rDAO.updateFacility (release.getSystemId(),facility.getFacilityId(),param);
            }
          releaseForm.setProperties(release);
          CodeDesignRelease updatedRelease = (CodeDesignRelease) session.getAttribute("UPDATE_RELEASE");
          release.setCannedCodes(updatedRelease.getCannedCodes());
           session.setAttribute("UPDATE_RELEASE",release);
           session.setAttribute("UPDATE_RELEASE_LIST",new HsListWrapper(release.getCannedCodes()));
          return mapping.findForward("addConditions");
        }
         else if (method.equals("removeRelease")) 
        { String key = request.getParameter("key");
          manufacturer.removeCodeDesignRelease(key);
          return mapping.findForward("backtoview");
        }
         else if (method.equals("addConditions")) 
        { 
        
          return mapping.findForward("addConditions");
        }
         else if (method.equals("codeLookup")) 
        {
         List list = rDAO.selectStdCodes();
         request.setAttribute("STANDARD_CODE",new HsListWrapper(list));
         return mapping.findForward("addConditions");
        }
        
        else if (method.equals("addStdCode")) 
        {int codeId = Integer.parseInt(request.getParameter("codeId"));
         String stdCode = rDAO.selectStdCode(codeId);
         releaseForm.setCannedCode(stdCode);
         CodeDesignRelease release =(CodeDesignRelease) session.getAttribute("UPDATE_RELEASE");
          session.setAttribute("UPDATE_RELEASE_LIST",new HsListWrapper(release.getCannedCodes()));
         return mapping.findForward("addConditions");
        }
          else if (method.equals("saveStdCode")) 
        {String cannedCode =(request.getParameter("cannedCode"));
          CodeDesignRelease release =(CodeDesignRelease) session.getAttribute("UPDATE_RELEASE");
         rDAO.addStdCode(cannedCode,release);
         rDAO.selectCannedCodes(release.getSystemId(),release);
         session.setAttribute("UPDATE_RELEASE_LIST",new HsListWrapper(release.getCannedCodes()));
         releaseForm.setCannedCode("");
         return mapping.findForward("addConditions");
        }
          else if (method.equals("editCannedCode")) 
        {int codeId = Integer.parseInt(request.getParameter("codeId"));
         String stdCode = rDAO.selectCannedCode(codeId);
         releaseForm.setCannedCode(stdCode);
         rDAO.removeCannedCode(codeId);
         CodeDesignRelease release =(CodeDesignRelease) session.getAttribute("UPDATE_RELEASE");
         session.setAttribute("UPDATE_RELEASE_LIST",new HsListWrapper(release.getCannedCodes()));
         return mapping.findForward("addConditions");
        }
        else if (method.equals("removeCannedCode")) 
        {int codeId = Integer.parseInt(request.getParameter("codeId"));
         rDAO.removeCannedCode(codeId);
         setOptions(request,dfbsUtilityDAO);
         CodeDesignRelease release =(CodeDesignRelease) session.getAttribute("UPDATE_RELEASE");
         releaseForm.setProperties(release);
         rDAO.selectCannedCodes(release.getSystemId(),release);
         session.setAttribute("UPDATE_RELEASE_LIST",new HsListWrapper(release.getCannedCodes()));
           String releaseUpdateFlag="Y";
           session.setAttribute("UPDATE_FLAG",releaseUpdateFlag);
          return mapping.findForward("updateRelease");
         
        }
         else if (method.equals("goToUpload")) 
         {  String idNumber = request.getParameter("idNumber");
              CodeDesignRelease release =  manufacturer.getCodeRelease(idNumber);
            session.setAttribute("RELEASE_SELECTED",release);
            release.setFileList(rDAO.selectFileList(release.getApplicationKey()));
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
         }
         
           else if (method.equals("downLoadFile")) 
         { 
          String fileName = request.getParameter("fileName");
          String fileType = request.getParameter("fileType");
           int fileId = Integer.parseInt(request.getParameter("fileId"));
          doGetFile(request,response,rDAO,fileName,fileType,fileId);
           return null;
         }
          else if (method.equals("uploadFile")) 
         { 
         
         final FormFile oneFile = releaseForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {   String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {
                  CodeDesignRelease release = (CodeDesignRelease) session.getAttribute("RELEASE_SELECTED");
                  rDAO.uploadFile(oneFile,release.getApplicationKey());         
                  release.setFileList(rDAO.selectFileList(release.getApplicationKey()));
                  release.setFileStatus("YES");
                  manufacturer.setFileStatus("YES");
                  }
                  else
                  {  session.setAttribute("FILE_EXTENSION","ERROR");
                     return mapping.findForward("goToUpload");
         
                  }
                 
            }
          
          return mapping.findForward("view");
          
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
    protected static boolean validate(CodeDesignRelease release,CodeReleaseForm errorForm) throws Exception
  {
    boolean noError = true;
    
   
   
    if(release.getLengthsOfUnit() == null || release.getLengthsOfUnit().trim().equals("") ) 
    { 
      errorForm.setLengthsOfUnit("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setLengthsOfUnit("");
    }
    
 
     if(release.getCompletedWidth() == null || release.getCompletedWidth().trim().equals("") ) 
    {
      errorForm.setCompletedWidth("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompletedWidth("");
    }
    
    if(release.getFilingStatus() == null || release.getFilingStatus().trim().equals("") ) 
    {
      errorForm.setFilingStatus("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setFilingStatus("");
    }
    if(release.getBuildType() == null || release.getBuildType().trim().equals("") ) 
    {
      errorForm.setBuildType("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setBuildType("");
    }
    if(release.getSystemType() == null || release.getSystemType().trim().equals("") ) 
    {
      errorForm.setSystemType("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setSystemType("");
    }
     if(release.getReleaseType() == null || release.getReleaseType().trim().equals("") ) 
    {
      errorForm.setReleaseType("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setReleaseType("");
    }
     
    return noError;
  }
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(CodeSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("DFBS_STATE_OPTIONS",states);
 
 
    List counties = uDAO.getOptions(CodeSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
    
    List system = uDAO.getOptions(CodeSQL.SELECT_SYSTEM_OPTIONS);
    request.setAttribute("DFBS_SYSTEM_OPTIONS",system);
    
     List occupancy = uDAO.getOptions(CodeSQL.SELECT_OCCUPANCY_OPTIONS);
    request.setAttribute("DFBS_OCCUPANCY_OPTIONS",occupancy);
    
     List filing = uDAO.getOptions(CodeSQL.SELECT_FILING_OPTIONS);
    request.setAttribute("DFBS_FILING_OPTIONS",filing);
    
    List systemType = uDAO.getOptions(CodeSQL.SELECT_CDR_SYSTEM_TYPE_OPTIONS);
    request.setAttribute("CODE_SYSTEM_TYPE_OPTIONS",systemType);
    List reviewers = uDAO.getOptions(CodeSQL.SELECT_CDR_REVIEWER_OPTIONS);
    request.setAttribute("CODE_REVIEWER_OPTIONS",reviewers); 
    
   List facilities = uDAO.getOptions(CodeSQL.SELECT_FACILITIES);
    request.setAttribute("CODE_FACILITIES",facilities);  
 }
/*   private static void addFacilitiesToRelease(HttpServletRequest request,CodeManufacturer manufacturer,CodeDesignRelease release) throws Exception
  {
    List facilities = manufacturer.getFacilityList();
    Iterator i = facilities.iterator();
    
    while(i.hasNext())
    {
      CodeFacility facility = (CodeFacility) i.next();
      StringBuffer sb = new StringBuffer("");
      sb.append(Integer.toString(facility.getFacilityId()));
      String param = request.getParameter(sb.toString());
      if(param != null) 
      {
        release.addFacility(facility,release);
      }
    }
    
  }*/
    private static void setReleaseScope(HttpServletRequest request,CodeDesignRelease release) throws Exception
  {
      StringBuffer sbe = new StringBuffer("electrical");
      String parame = request.getParameter(sbe.toString());
      if(parame != null) 
      {
        release.setUnitElectrical("Y");;
      }
      StringBuffer sbf = new StringBuffer("fireAlaram");
      String paramf = request.getParameter(sbf.toString());
      if(paramf != null) 
      {
        release.setUnitFireAlaram("Y");;
      }
      StringBuffer sbh = new StringBuffer("hood");
      String paramh = request.getParameter(sbh.toString());
      if(paramh != null) 
      {
        release.setUnitHood("Y");;
      }
       
       StringBuffer sbm = new StringBuffer("mechanical");
      String paramm = request.getParameter(sbm.toString());
      if(paramm != null) 
      {
        release.setUnitMechanical("Y");;
      }
      StringBuffer sbsp = new StringBuffer("sprinkler");
      String paramsp = request.getParameter(sbsp.toString());
      if(paramsp != null) 
      {
        release.setUnitSprinkler("Y");;
      }
      StringBuffer sbst = new StringBuffer("structural");
      String paramst = request.getParameter(sbst.toString());
      if(paramst != null) 
      {
        release.setUnitStructural("Y");;
      }
  }
    public void doGetFile(HttpServletRequest request, HttpServletResponse response,CodeDesignReleaseDAO rDAO,String fileName,String fileType,int fileId) throws Exception 
 {  
   try {response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
    java.io.OutputStream out = response.getOutputStream(); 
    int lenFile = rDAO.downLoad(out,fileId);
     out.flush(); 
    out.close();
   } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
            }
    

} 

 private static void sendEmailApproval(int systemId,CodeManufacturer manufacturer) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            List emailList = new ArrayList();
// to find emails address
 // manufacturer person  
      String[] emailTo =new String[20];
      int x =0;
       StringBuffer sbEmail = new StringBuffer();
       Map personManufacturer = manufacturer.getPersonMap();
       Set personkeys = personManufacturer.keySet();
       Iterator i = personkeys.iterator();
       while(i.hasNext()){
       String key = (String) i.next();
        CodePerson person = (CodePerson) personManufacturer.get(key); 
        if (person.getCodePersonEmail()!=null)
        {emailTo[x]=person.getCodePersonEmail();
          x=x+1;
        }
       
       }
    // facility    
       Map facilityMap = manufacturer.getFacilityMap();
       Set facilitykeys = facilityMap.keySet();
       Iterator j = facilitykeys.iterator();
       while(j.hasNext()){
       String keyf = (String) j.next();
       CodeFacility facility = (CodeFacility)  facilityMap.get(keyf);
     
       Map personFacility = facility.getPersonMap();
       Set personkeysf =personFacility.keySet();
       Iterator k = personkeysf.iterator();
       while(k.hasNext()){
       String keypf = (String) k.next();
        CodePerson person = (CodePerson) personFacility.get(keypf); 
         if (person.getCodePersonEmail()!=null && facility.getCdrFlag()=="true")
        {
           emailTo[x]=person.getCodePersonEmail();
          x=x+1;
        }
       
       }
       
       }
      emailTo[x]="tbradshaw@dhs.in.gov";
  
// end email address "mcarroll@dhs.in.gov","cclouse@dhs.in.gov"
          // String[] emailTotest = {"pulikal@hotmail.com"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("\r\n Design Release for INDUSTRIALIZED BUILDING SYSTEMS AND MOBILE STRUCTURES Approved.");
            sb.append("\r\n User:");
            sb.append(manufacturer.getManufacturerName()+".");
            sb.append("\r\n  to view details, click "); 
            sb.append(" \r\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=code_design_release&p_system_id=" + Integer.toString(systemId));
            StringBuffer sub = new StringBuffer();
            sub.append(" Code enforcement CDR approved ");
          
            mail.createMail("code_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
 HsMailer mail1 = new HsMailer();
            mail1.createMail("codeEnforcement_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Code enforcement CDR approval error email ","Code confirmation error email"+systemId);
            mail1.sendAndClose();     
           }
  } 
  
  
    
}
