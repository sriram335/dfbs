package elevator.control.action;




import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import hs.control.form.*;
import elevator.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import main.data.*;
import elevator.data.*;

import elevator.to.*;

import hs.data.system.*;
import main.data.*;
import hs.report.pdf.*;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;

import planReview.data.PlanReviewDAO;

import planReview.to.PlanReview;

public class elevatorAction extends ControlAction {
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
           
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
         
          
          DfbsDataMap dmap2 = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
         elevatorForm elevForm = (elevatorForm) form;
         
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
          elevApplicationDAO aDAO = (elevApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_APPLICATION);  
          PlanReviewDAO pDAO = (PlanReviewDAO) dmapNew.getHsDAO(DfbsDataMap2.PLAN_REVIEW_UPLOAD);
          elevatorOwnerDAO oDAO = ( elevatorOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_OWNER);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            DfbsOwner owner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
         if (method.equals("elevatorAction"))
            {
              String elevAction = request.getParameter("elevAction");
               String elevStateNumber = request.getParameter("elevStateNumber");
              elevator elev = eDAO.selectElevator(elevStateNumber);
              session.setAttribute("ELEVATOR_SELECTED",elev);
               if (elevAction.equals("Apply for Alteration Permit")) 
              {   owner.addElevator(elev);
                elevForm.setProperties(elev);
                session.setAttribute("ELEVATOR_APP_ACTION","Alteration");
                    return mapping.findForward("preAlteration"); 
                
              }
               if (elevAction.equals("Submit /Print inspection"))
                  {
                    session.setAttribute("ELEVATOR_APP_ACTION","Inspection");
                    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                    redirectUrl.append(request.getContextPath()).append("/elevator/inspection.do?method=inspectionList");
                    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null;
                  }
                    
             }
          else if (method.equals("goToUpload")) 
                   { String elevStateNumber = request.getParameter("stateNumber");
                      if (elevStateNumber!=null && elevStateNumber.length()>4) {
                      elevator elev = eDAO.selectElevator(elevStateNumber);
                       elev.setFileList(eDAO.selectFileList(elev.getStateNumber()));
                      session.setAttribute("ELEVATOR_SELECTED",elev);
                      session.setAttribute("ELEVATOR_UPLOAD_ACTION","Y");
                      return mapping.findForward("upload");   
                     }
                    else {  session.setAttribute("ELEVATOR_UPLOAD_ACTION","N");
                       return mapping.findForward("start");  
                     }
                   }
          else if (method.equals("submitTests")) 
                   { String elevStateNumber = request.getParameter("stateNumber");
                      if (elevStateNumber!=null && elevStateNumber.length()>4) {
                      elevator elev = eDAO.selectElevator(elevStateNumber);
                       elev.setFileList(eDAO.selectFileList(elev.getStateNumber()));
                      session.setAttribute("ELEVATOR_SELECTED",elev);
                      session.setAttribute("ELEVATOR_UPLOAD_TEST_ACTION","Y");
                      return mapping.findForward("uploadTest");   
                     }
                    else {  session.setAttribute("ELEVATOR_UPLOAD_TEST_ACTION","N");
                       return mapping.findForward("start");  
                     }
                   }
           else if (method.equals("goToUploadInternal")) 
           { 
             String stateNumber = request.getParameter("stateNumber"); 
             String dfbsSessionId = request.getParameter("dfbsSessionId"); 
             if (security == null ){
                      security =new ApplicationSecurity ();
                      session.setAttribute("DFBS_SECURITY",security); 
                    }
             pDAO.getConnectionInfo(security,dfbsSessionId);
             String userCheck = sDAO.getSecurity(security,security.getUserId(),security.getUserPassword());
             pDAO.resetConnectionInfo(dfbsSessionId); 
                   setOptions(request,dfbsUtilityDAO);
             if (security.getUserId() !=null )
             {    elevator elev = eDAO.selectElevator(stateNumber); 
                       elev.setFileList(eDAO.selectFileList(elev.getStateNumber()));
                      session.setAttribute("ELEVATOR_SELECTED",elev);
                     return mapping.findForward("upload");
             }
             else
             {session.setAttribute("DFBS_SECURITY",null);
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                    redirectUrl.append(request.getContextPath()).append("/main/main.do");
                    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null;
             }
           } 
          else if (method.equals("uploadFile")) 
                  { 
                  
                  final FormFile oneFile = elevForm.getCaseFile();
                    if(oneFile != null && oneFile.getFileSize()>0 )
                     {    elevator elev =  (elevator) session.getAttribute("ELEVATOR_SELECTED");
                            String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                          if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                              fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                               fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                               fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                               fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                           {
                           eDAO.uploadFile(oneFile,elev.getStateNumber(),null,null);  
                           elev.setFileList(eDAO.selectFileList(elev.getStateNumber()));
                           return mapping.findForward("upload");
                         }
                         else
                         {
                         session.setAttribute("FILE_EXTENSION","ERROR");
                         return mapping.findForward("upload");
                         }
                     }
                    return mapping.findForward("goToUpload");
                  } 

        
          else if (method.equals("uploadTestFile")) 
          {
          
          final FormFile oneFile = elevForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {    elevator elev =  (elevator) session.getAttribute("ELEVATOR_SELECTED");
                
                   String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf(".")); 
                 if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {
                  String stateNumber = request.getParameter("stateNumber"); 
                  String userEmail = request.getParameter("locUserEmail"); 
                 if (stateNumber == null || stateNumber.equals("")) {
                   session.setAttribute("STATE_NUMBER_ERROR","ERROR");
                   session.setAttribute("USER_EMAIL_ERROR","");
                   return mapping.findForward("uploadTest"); 
                 }
                  if (userEmail == null || userEmail.equals("")) {
                    session.setAttribute("USER_EMAIL_ERROR","ERROR");
                    session.setAttribute("STATE_NUMBER_ERROR","");
                    return mapping.findForward("uploadTest"); 
                  }
                  eDAO.uploadFile(oneFile,elev.getStateNumber(),userEmail,null);  
                  elev.setFileList(eDAO.selectFileList(elev.getStateNumber()));
                  session.setAttribute("USER_EMAIL_ERROR","");
                  session.setAttribute("STATE_NUMBER_ERROR","");
                  session.setAttribute("FILE_EXTENSION","");
                  return mapping.findForward("uploadTest");
                }
                else
                {
                session.setAttribute("FILE_EXTENSION","ERROR");
                return mapping.findForward("upload");
                }
            }
           return mapping.findForward("goToUpload");
          }
            else if (method.equals("downLoadFile")) 
         { 
          String fileType = request.getParameter("fileType");
          int fileId = Integer.parseInt(request.getParameter("fileId"));
          String fileName = request.getParameter("fileName");
          doGetFile(request,response,eDAO,fileName,fileType,fileId);
           return null;
         }
          else if (method.equals("updateUserInfo")) 
                   { elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");
                     elev.setLocUserName(elevForm.getLocUserName());
                     elev.setLocUserPhone(elevForm.getLocUserPhone());
                     elev.setLocUserEmail(elevForm.getLocUserEmail());
                     session.setAttribute("ELEVATOR_APP_ACTION","updateUserInfo");
                return mapping.findForward("preAlteration");                     
                   }
          else if (method.equals("newElevator")) 
                   { 
                     session.setAttribute("ELEVATOR_APP_ACTION","Installtion");
                    setOptions(request,dfbsUtilityDAO);
                    return mapping.findForward("newElevator");
                     
                   }
         else if (method.equals("saveNewElevator")) 
                   { elevator elev =elevForm.getElev();
                     elev.setStateNumber("New");
                     setOptions(request,dfbsUtilityDAO);
                     elevatorForm errorForm = new elevatorForm();
                     session.setAttribute("ELEVATOR_SELECTED",elev);
                     if (validate(elev,errorForm) ) 
                     {
                     owner.addElevator(elev);
                     return mapping.findForward("preInstallation");
                     }
                     else
                     {   request.setAttribute("ELEVATOR_ERROR",errorForm);            
                     return mapping.findForward("newElevator");  
                     }
                   }
          else if (method.equals("fineSubsequent")) 
                   { elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");
                     aDAO.insertElevFineTransaction(elev,"Subsequent",1000,owner);
                     StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                     redirectUrl.append(request.getContextPath()).append("/elevator/inspection.do?method=inspectionList");
                     response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;
                     
                   }
          else if (method.equals("fineFollowUp")) 
                   { elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");
                     aDAO.insertElevFineTransaction(elev,"Follow Up",750,owner);
                     StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                     redirectUrl.append(request.getContextPath()).append("/elevator/inspection.do?method=inspectionList");
                     response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;
                     
                   }
          else if (method.equals("feesByStateNumber")) 
          { String elevStateNumber = request.getParameter("stateNumber");
            elevator feeElev = eDAO.selectElevator(elevStateNumber);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/idhsFeesFines/start.do?method=searchByIdElevator&stateNumber="+feeElev.getStateNumber()+"&ownerId=1");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          else if (method.equals("feesByOwnerId")) 
          {
            String ownerId = request.getParameter("ownerId");
            DfbsOwner feeOwner = oDAO.selectOwner(Integer.parseInt(ownerId));
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append("/dfbs/idhsFeesFines/start.do?method=searchByIdElevator&stateNumber=1&ownerId="+feeOwner.getOwnerId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          else if (method.equals("updateElevator")) 
          {
            String elevStateNumber = request.getParameter("stateNumber");
            elevator elev = eDAO.selectElevator(elevStateNumber);
            elevForm.setProperties(elev);
            setOptions(request,dfbsUtilityDAO);   
            List elevAppList= aDAO.selectElevatorApplications(elev.getStateNumber());
            elev.setElevAppList(elevAppList);
             elev.setFileList(eDAO.selectFileList(elev.getStateNumber()));
            session.setAttribute("ELEVATOR_SELECTED",elev); 
            return mapping.findForward("updateElevator"); 
          }
          else if (method.equals("saveUpdateElevator")) 
          {
            elevator elev = elevForm.getElev();
            elevatorForm errorForm = new elevatorForm();
            session.setAttribute("ELEVATOR_SELECTED",elev);
            if (validate(elev,errorForm) ) 
            {
            eDAO.updateElevator(elev);
            session.setAttribute("ELEVATOR_SELECTED",elev);
            }
            else {session.setAttribute("ELEVATOR_SELECTED",elev);
              request.setAttribute("ELEVATOR_ERROR",errorForm); 
            }
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("updateElevator"); 
          }
             else if (method.equals("removeElevator")) 
          {
            String elevAppKey = request.getParameter("elevAppKey");
            String ownerKey = request.getParameter("ownerKey");
            ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
            DfbsOwner ownerDelete= cart.getOwner(ownerKey);
           ownerDelete.removeElevatorApp(elevAppKey);
            return mapping.findForward("search"); 
          }
          else if (method.equals("processTests")) 
          {   
             List  fileList=eDAO.selectFileListTests();
            request.setAttribute("TEST_FILE_LIST",new HsListWrapper(fileList));                                          
           return mapping.findForward("processFiles");
          }
          else if (method.equals("approveFile")) 
          {    int fileId = Integer.parseInt(request.getParameter("fileId"));
            String stateNumber = request.getParameter("stateNumber");
            String uploadDate = request.getParameter("uploadDate");
            String userEmail = request.getParameter("userEmail");
            eDAO.updateFileStatus(fileId);
            this.sendEmailUser(request,userEmail,stateNumber,uploadDate);
             List  fileList=eDAO.selectFileListTests();
            request.setAttribute("TEST_FILE_LIST",new HsListWrapper(fileList));                                          
           return mapping.findForward("processFiles");
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
      protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
      {   
         List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS);
        List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS);
          List counties = uDAO.getOptions(elevatorSQL.SELECT_COUNTY_LIST_OPTIONS);
        List inspContractors = uDAO.getOptions(elevatorSQL.ELEV_CONTACTORS_OPTIONS);
        List devices = uDAO.getOptions(elevatorSQL.SELECT_DEVICE_LIST_OPTIONS);
        List occCodes = uDAO.getOptions(elevatorSQL.ELEV_OCCUPANCY_CODE);
         request.setAttribute("DFBS_FIRST_LETTERS_OPTIONS",charNav);
         request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
        request.setAttribute("DFBS_STATE_OPTIONS",states);
        request.setAttribute("ELEV_CON_COMPANY_OPTIONS",inspContractors );
        request.setAttribute("ELEV_DEV_TYPE_OPTIONS",devices );
        request.setAttribute("ELEV_OCC_CODES",occCodes );
      }
      
      protected static boolean validate(elevator elev,elevatorForm errorForm) throws Exception
       {
         boolean noError = true;
         
        if(elev.getDeviceId()==0) 
        { 
          errorForm.setDeviceId(99999);
          noError = false;
        } 
        else 
        {
          errorForm.setDeviceId(elev.getContractorId());
        } 
        
        if(elev.getDeviceCapacity()== null || elev.getDeviceCapacity().trim().equals("")) 
        { 
          errorForm.setDeviceCapacity("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setDeviceCapacity("");
        }
        //
        if(elev.getContactSpeed()==null || elev.getContactSpeed().trim().equals("")) 
        { 
          errorForm.setContactSpeed("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setContactSpeed("");
        } 
        //
        if(elev.getNumberOpenings()==null || elev.getNumberOpenings().trim().equals(""))  
        { 
          errorForm.setNumberOpenings("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setNumberOpenings("");
        } 
        //
        if(elev.getTotalTravel()==null || elev.getTotalTravel().trim().equals(""))  
        { 
          errorForm.setTotalTravel("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setTotalTravel("");
        } 
        //
        if(elev.getPlatformSize()==null || elev.getPlatformSize().trim().equals(""))  
        { 
          errorForm.setPlatformSize("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setPlatformSize("");
        }
        //
        if(elev.getContractNumber()==null || elev.getContractNumber().trim().equals(""))  
        { 
          errorForm.setContractNumber("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setContractNumber("");
        } 
        //
        if(elev.getTypeControl()==null || elev.getTypeControl().trim().equals(""))  
        { 
          errorForm.setTypeControl("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setTypeControl("");
        } 
        //
        if(elev.getFloors()==null || elev.getFloors().trim().equals(""))  
        { 
          errorForm.setFloors("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setFloors("");
        } 
        //
        if(elev.getLocUserName()==null || elev.getLocUserName().trim().equals(""))  
        { 
          errorForm.setLocUserName("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocUserName("");
        } 
        //
        if(elev.getLocAddress1()==null || elev.getLocAddress1().trim().equals(""))  
        { 
          errorForm.setLocAddress1("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocAddress1("");
        } 
        //
        if(elev.getLocCity()==null || elev.getLocCity().trim().equals(""))  
        { 
          errorForm.setLocCity("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocCity("");
        } 
        //
        if(elev.getLocState()==null || elev.getLocState().trim().equals(""))  
        { 
          errorForm.setLocState("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocState("");
        } 
        //
        if(elev.getLocZip()==null || elev.getLocZip().trim().equals(""))  
        { 
          errorForm.setLocZip("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocZip("");
        } 
        //
        if(elev.getLocCounty()==null || elev.getLocCounty().trim().equals(""))  
        { 
          errorForm.setLocCounty("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocCounty("");
        } 
        //
        if(elev.getLocUserPhone()==null || elev.getLocUserPhone().trim().equals(""))  
        { 
          errorForm.setLocUserPhone("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocUserPhone("");
        } 
        //
        if(elev.getLocUserEmail()==null || elev.getLocUserEmail().trim().equals(""))  
        { 
          errorForm.setLocUserEmail("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setLocUserEmail("");
        } 
         if(elev.getContractorId()==0) 
         { 
           errorForm.setContractorId(99999);
           noError = false;
         } 
         else 
         {
           errorForm.setContractorId(elev.getContractorId());
         } 
         
         return noError;
        
      }
       public void doGetFile(HttpServletRequest request, HttpServletResponse response,elevatorDAO eDAO,String fileName,String fileType,int fileId) throws Exception 
 {  
   try {response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
    java.io.OutputStream out = response.getOutputStream(); 
    int lenFile = eDAO.downLoad(out,fileId);
     out.flush(); 
    out.close();
   } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
            }
    
} 
       
      private static void sendEmailUser(HttpServletRequest request, String userEmail,String stateNumber, String uploadDate) throws Exception
        {
                try {
                   
                  HsMailer mail = new HsMailer();
                  String[] emailTo = {userEmail};
                //  String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
                  String[] bccTo = {"ghadnott@dhs.in.gov","nnimmagadda@dhs.in.gov"};
                 
                  StringBuffer sb = new StringBuffer();
                  sb.append("The Test report for elevator state number "+stateNumber+", you have submitted on "+uploadDate+" is approved.").append("\n\r");  
                  sb.append("If you have any questions on this email please contact Gregg Hadnott at ghadnott@dhs.in.gov").append("\n\r");
                       StringBuffer sub = new StringBuffer();
                  sub.append(" From IDHS  elevator test report approved ");
                  
                  mail.createMail("elevators_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                 mail.sendAndClose();
                  } 
                  catch(Exception e) 
                  {
                    HsMailer mail = new HsMailer();
                  mail.createMail("elevators_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email elevator test approval");
                  mail.sendAndClose();
                  }
        }
    }
