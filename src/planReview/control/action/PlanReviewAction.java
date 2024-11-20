package planReview.control.action;



import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import main.to.*;
import planReview.to.*;
import otherUsers.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import main.data.*;
import org.apache.struts.upload.FormFile;
import planReview.control.form.*;
import planReview.data.*;

public class PlanReviewAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           
           ServletContext context = 
           this.getServlet().getServletConfig().getServletContext();
          
           
           DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
           
           PlanReviewForm planReviewForm = (PlanReviewForm) form;
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           PlanReviewDAO aDAO = (PlanReviewDAO) dmapNew.getHsDAO(DfbsDataMap2.PLAN_REVIEW_UPLOAD);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
             PlanLBOUsersDAO pDAO = ( PlanLBOUsersDAO) dmapNew.getHsDAO(DfbsDataMap2.PLAN_LBO_USER);
           String method = request.getParameter("method");
            HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
           FileNames names= sDAO.selectAppStatus("PLAN_REVIEW_MAINT_FLAG");
            session.setAttribute("PLAN_REVIEW_APP_STATUS",names.getFileName());
            session.setAttribute("PLAN_REVIEW_APP_MESSAGE",names.getFileType());
            if (method == null) 
         
           {
             return mapping.findForward("start");
           } 
            
            else if (method.equals("goToUploadInternal")) 
           { 
             String idNumber = request.getParameter("idNumber"); 
             String dfbsSessionId = request.getParameter("dfbsSessionId"); 
             session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
             ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
             aDAO.getConnectionInfo(security,dfbsSessionId);
             String userCheck = sDAO.getSecurity(security,security.getUserId(),security.getUserPassword());
             aDAO.resetConnectionInfo(dfbsSessionId);
                   setOptions(request,dfbsUtilityDAO);
             if (security !=null && security.getUserId() !=null )
             {   session.setAttribute("PLAN_REVIEW_UPLOAD",new PlanReview());
              PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_UPLOAD");
              planReviewForm.setIdNumber(idNumber);
              planReviewForm.setIdType("planReview");
              planReviewForm.setSubmittedBy(security.getUserId());
              review.setIdNumber(idNumber);
              review.setIdType("planReview");
              review.setFileList(aDAO.selectFileList(idNumber,"planReview",0)); 
            return mapping.findForward("goToUpload");
             }
             else
             {session.setAttribute("DFBS_SECURITY",null);
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                    redirectUrl.append(request.getContextPath()).append("/main/main.do");
                    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                      return null;  
             }
           } 
              else if (method.equals("reviewMarkups")) 
           { 
             int fileId = Integer.parseInt(request.getParameter("fileId")); 
             String sudoPassword = request.getParameter("connString"); 
              int correctionId = aDAO.checkPlanMarkups(fileId,sudoPassword);
             if (correctionId >0) {
                  session.setAttribute("PLAN_REVIEW_MARKUPS",new PlanReview());
                  PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_MARKUPS");
                  review.setFileList(aDAO.selectFileListMarkups(correctionId)); 
                  session.setAttribute("PLAN_REVIEW_MARKUPS_SIZE",review.getFileList().size());
                 }
            return mapping.findForward("reviewMarkups");
           } 
                else if (method.equals("plansForLBO")) 
           { 
             String idNumber = request.getParameter("idNumber"); 
              List planList=aDAO.selectFileList(idNumber,"planReview",0) ;
              session.setAttribute("PLAN_REVIEW_LBO_PROJECT_PLANS",planList);
            return mapping.findForward("viewPlans");
           } 
                 else if (method.equals("viewPlansForOthers")) 
           { 
             String idNumber = request.getParameter("idNumber"); 
             otherUsers otherUser = (otherUsers) session.getAttribute("OTHER_USER");
             List planList=null;
             if (otherUser.getFDId() !=null && otherUser.getFDId().length()>3)
             {
              planList=aDAO.selectFileList(idNumber,"planReview",1) ;
             }
             else {
               planList=aDAO.selectFileList(idNumber,"planReview",0) ;
             }
              session.setAttribute("PLAN_REVIEW_OTHER_PROJECT_PLANS",planList);
            return mapping.findForward("othersViewPlans");
           } 
                  else if (method.equals("plansForOthers")) 
           { 
             String projStreetNumber = request.getParameter("projStreetNumber");
             String projCity = request.getParameter("projCity");
             String projectNumber = request.getParameter("idNumber");
             otherUsers otherUser = (otherUsers) session.getAttribute("OTHER_USER");
             String county = otherUser.getUserCounty(); 
              List projectList =pDAO.selectProjectListOthers(county, projectNumber,projStreetNumber,projCity);
              session.setAttribute("PLAN_REVIEW_OTHER_PLANS",projectList);
              return mapping.findForward("othersViewPlans"); 
           } 
         /*           else if (method.equals("searchOtherProject")) 
           {  
             otherUsers otherUser = (otherUsers) session.getAttribute("OTHER_USER");
             String idNumber = request.getParameter("idNumber"); 
             String county = otherUser.getUserCounty(); 
              List projectList =pDAO.selectProjectListOthers(county, Integer.parseInt(idNumber));
              session.setAttribute("PLAN_REVIEW_LBO_PLANS",projectList);
              return mapping.findForward("viewPlans"); 
           } */
             else if (method.equals("deleteFile")) 
              { 
               int fileId = Integer.parseInt(request.getParameter("fileId"));
                String idNumber = request.getParameter("idNumber"); 
              aDAO.deleteFile(fileId);
               setOptions(request,dfbsUtilityDAO);
                PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_UPLOAD");
                ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
              planReviewForm.setIdNumber(idNumber);
              planReviewForm.setIdType("planReview");
              planReviewForm.setSubmittedBy(security.getUserId());
              review.setIdNumber(idNumber);
              review.setIdType("planReview");
              review.setFileList(aDAO.selectFileList(idNumber,"planReview",0)); 
            return mapping.findForward("goToUpload");
              } 
               else if (method.equals("goToUpload")) 
           { 
             String idNumber = request.getParameter("idNumber"); 
             String planPath = request.getParameter("planPath");
             session.setAttribute("PLAN_REVIEW_UPLOAD_PATH",planPath);
             if (idNumber==null || idNumber.equals("")) {
               return mapping.findForward("start");
             }
              session.setAttribute("SBC_PLAN_NUMBER",idNumber);
            return mapping.findForward("preUpload");
           }  
                 else if (method.equals("goToUploadContinue")) 
           { 
             String idNumber = (String) session.getAttribute("SBC_PLAN_NUMBER"); 
              setOptions(request,dfbsUtilityDAO);
              session.setAttribute("PLAN_REVIEW_UPLOAD",new PlanReview());
              PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_UPLOAD");
              planReviewForm.setIdNumber(idNumber);
              planReviewForm.setIdType("planReview");
              review.setIdNumber(idNumber);
              review.setIdType("planReview");
              review.setFileList(aDAO.selectFileList(idNumber,"planReview",0)); 
            return mapping.findForward("goToUpload");
           }  
           else if (method.equals("downLoadFileGis")) 
           { String fileName = request.getParameter("fileName");
           String fileType = request.getParameter("fileType");
           int fileId = Integer.parseInt(request.getParameter("fileId"));
           doGetFile(request,response,aDAO,fileName,fileType,fileId);
           return null;
               }
            /*
             else if (method.equals("goToUpload")) 
           { 
             String idNumber = request.getParameter("idNumber"); 
             String planPath = request.getParameter("planPath");
             session.setAttribute("PLAN_REVIEW_UPLOAD_PATH",planPath);
             if (idNumber==null || idNumber.equals("")) {
               return mapping.findForward("start");
             }
              setOptions(request,dfbsUtilityDAO);
              session.setAttribute("SBC_PLAN_NUMBER",idNumber);
              session.setAttribute("PLAN_REVIEW_UPLOAD",new PlanReview());
              PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_UPLOAD");
              planReviewForm.setIdNumber(idNumber);
              planReviewForm.setIdType("planReview");
              review.setIdNumber(idNumber);
              review.setIdType("planReview");
              review.setFileList(aDAO.selectFileList(idNumber,"planReview")); 
            return mapping.findForward("goToUpload");
           }  */
           
            // file upload download feature
             else if (method.equals("downLoadFile")) 
            { ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
              // System.out.println(method);
              
             String fileName = request.getParameter("fileName");
             String fileType = request.getParameter("fileType");
             int fileId = Integer.parseInt(request.getParameter("fileId"));
              PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_UPLOAD");
             if (security !=null)
             { 
             if (security.getUserId().equals("PILLINGWORTH")||security.getUserId().equals("PROBISON")) {
               int priCheck=aDAO.checkPrivateSecurity(security.getUserId(),Integer.parseInt(review.getIdNumber()));
              
               if (priCheck>0) {
                 doGetFile(request,response,aDAO,fileName,fileType,fileId);
                 return null;
               }
               else {             setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("goToUpload");}
             }
             else
             {
             doGetFile(request,response,aDAO,fileName,fileType,fileId);
             return null;
             }
             }
             else
             {
             setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("goToUpload");
             }
            }
               else if (method.equals("downLoadFileLBO")) 
            { PlanLBOUsers planUser= (PlanLBOUsers) session.getAttribute("PLAN_LBO_USER");
              otherUsers otherUser = (otherUsers) session.getAttribute("OTHER_USER");
             String fileName = request.getParameter("fileName");
             String fileType = request.getParameter("fileType");
             int fileId = Integer.parseInt(request.getParameter("fileId"));
             if (planUser !=null || otherUser!=null)
             {
            doGetFile(request,response,aDAO,fileName,fileType,fileId);
            return null;
             }
             else
             {
             setOptions(request,dfbsUtilityDAO);
               return mapping.findForward("goToUpload");
             }
            }
           else if (method.equals("viewCheckList")) 
           { 
           String fileName = request.getParameter("fileName");
           String fileType = request.getParameter("fileType");
           int fileId = Integer.parseInt(request.getParameter("fileId"));
           int checkListCheck=aDAO.checkListValidation(fileId);
           if (checkListCheck >0)
           {
           doGetFile(request,response,aDAO,fileName,fileType,fileId);
           return null;
           }
           else
           {
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("goToUpload");
           }
           }
             else if (method.equals("downLoadFileMarkup")) 
            { 
             String fileNameMarkup = request.getParameter("fileNameMarkup");
             String fileTypeMarkup = request.getParameter("fileTypeMarkup");
             int fileIdMarkup = Integer.parseInt(request.getParameter("fileIdMarkup"));
             doGetFile(request,response,aDAO,fileNameMarkup,fileTypeMarkup,fileIdMarkup);
             setOptions(request,dfbsUtilityDAO);
              return null;
            }
             // file upload upload feature
             else if (method.equals("uploadFile")) 
            { 
            final FormFile oneFile = planReviewForm.getCaseFile();
              if(oneFile != null && oneFile.getFileSize()>0 )
                  { String idNumber = request.getParameter("idNumber"); 
                    String idType = request.getParameter("idType"); 
                    String submittedBy = request.getParameter("submittedBy"); 
                    String planType = request.getParameter("planType"); 
                    String planPath= (String) session.getAttribute("PLAN_REVIEW_UPLOAD_PATH");
                    session.setAttribute("PLAN_FILE_SUBMITTED_ERROR","");
                    session.setAttribute("PLAN_FILE_PLAN_TYPE_ERROR","");
                    session.setAttribute("PLAN_FILE_EXTENSION_ERROR","");
                    String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                      PlanReview review = (PlanReview) session.getAttribute("PLAN_REVIEW_UPLOAD");
                      ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
                      if(submittedBy ==null || submittedBy.equals("")) {
                        planReviewForm.setPlanType(planType);
                        planReviewForm.setCaseFile(oneFile);
                        session.setAttribute("PLAN_FILE_SUBMITTED_ERROR","ERROR");
                      }
                      else
                      { 
                            if (planType ==null || planType.equals("XX")) {
                               planReviewForm.setCaseFile(oneFile);
                               planReviewForm.setSubmittedBy(submittedBy);
                              session.setAttribute("PLAN_FILE_PLAN_TYPE_ERROR","ERROR");
                            }
                            else
                            {
                                if (fileExtension.substring(1,4).toUpperCase().equals("PDF")||fileExtension.substring(1,4).toUpperCase().equals("DWF") )
                                { 
                                 if (security ==null)
                                       {
                                        int planCount = aDAO.selectPlanCount(Integer.parseInt(idNumber));
                                         int formCount =aDAO.selectFormFiledCount(Integer.parseInt(idNumber));
                                        if(planCount==0 && formCount ==0){
                                         if(planPath !=null &&( planPath.equals("new") || planPath.equals("par")||
                                            planPath.equals("tank")||planPath.equals("pool")||planPath.equals("fire"))) {
                                            String corr="N";
                                            }
                                         else {
                                            aDAO.insertPlanFormFiled(Integer.parseInt(idNumber)); 
                                         }
                                         List emailList=aDAO.selectReviewerEmails(Integer.parseInt(idNumber));
                                         Iterator i = emailList.iterator();
                                          while(i.hasNext())
                                          {
                                              String emailReviewer =(String) i.next();
                                              sendEmail(emailReviewer,idNumber);
                                          }
                                        }
                                       aDAO.uploadFile(oneFile,idNumber,submittedBy,planType,planPath);  
                                       }
                                     else {
                                        aDAO.uploadFile(oneFile,idNumber,security.getUserId(),planType,planPath);  
                                        }
                                     review.setFileList(aDAO.selectFileList(idNumber,idType,0));
                                     session.setAttribute("PLAN_FILE_EXTENSION_ERROR","");
                                }
                                else { planReviewForm.setPlanType(planType);
                                    planReviewForm.setSubmittedBy(submittedBy);
                                  session.setAttribute("PLAN_FILE_EXTENSION_ERROR","ERROR");
                                }
                                session.setAttribute("PLAN_FILE_PLAN_TYPE_ERROR","");
                            }
                          planReviewForm.setSubmittedBy(submittedBy);
                          session.setAttribute("PLAN_FILE_SUBMITTED_ERROR","");
                      }
               }
               setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("goToUpload");
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
      
    
     private static void sendEmail(String email, String projectNumber) throws Exception
     {
             try {
               HsMailer mail = new HsMailer();
               
               String[] emailTo = {email};
            //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
           //  String[] bccTo = {"nnimmagadda@dhs.in.gov"};
                String[] bccTo = {email};
               StringBuffer sb = new StringBuffer("\r\n This is  system generated email alert."+"New plans submitted for project "+ projectNumber+".");
               sb.append(" You can log into dfbs application to view the plans.");
               StringBuffer sub = new StringBuffer();
               sub.append("New plans submitted for project "+ projectNumber);
               mail.createMail("planReview_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
               mail.sendAndClose();
               } 
               catch(Exception e) 
               {
                 throw new Exception("ERROR_EMAIL_FAILED");
               }
     }
     
    
     
      public void doGetFile( HttpServletRequest request,HttpServletResponse response,PlanReviewDAO eDAO,String fileName,String fileType,int fileId) throws Exception 
    { try {
       response.setContentType(fileType); 
       response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
       if(fileId >0 & fileName !=null) {
         java.io.OutputStream out = response.getOutputStream(); 
       int lenFile = eDAO.downLoad(out,fileId);
       out.flush(); 
       out.close();
       return; 
       }
      else {
         response.sendRedirect("goToUpload");
         return ;
       }
      } 
               catch(Exception e) 
               {
                 throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
               }
    }
    
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List planTypes = uDAO.getOptions(PlanReviewSQL.SELECT_PLAN_TYPE);
    request.setAttribute("PLAN_TYPE_OPTIONS",planTypes);
 
 }  
    
    }