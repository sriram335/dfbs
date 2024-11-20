package fireHouse.control.action;



import ems.data.EmsProviderDAO;

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
import fireHouse.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import main.data.*;
import fireHouse.data.*;
import ems.to.*;


import hs.data.system.*;
import main.data.*;
import hs.report.pdf.*;

import main.to.*;




public class fireHouseAction extends ControlAction {
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
           
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
         
          
          DfbsDataMap dmap2 = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
         fireHouseForm fireForm = (fireHouseForm) form;
         
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          fireHouseDAO fDAO = ( fireHouseDAO) dmapNew.getHsDAO(DfbsDataMap2.FIRE_HOUSE);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          EmsProviderDAO pDAO = (EmsProviderDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_PROVIDER);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
           if  (method ==null) 
                   { 
                       return mapping.findForward("start");  
                    
                   }
          else if  (method.equals("goToUpload")) 
                  { 
                    String certNumber = request.getParameter("certNumber"); 
                    EmsProvider provider =pDAO.selectProviderByCert(certNumber);
                    if (provider.getProviderCertNumber() == null)
                    { session.setAttribute("PROVIDER_CERT_ERROR","ERROR");
                      return mapping.findForward("start"); 
                    }
                    else {
                      session.setAttribute("PROVIDER_CERT_ERROR","");
                      provider.setFileList(fDAO.selectFileList(certNumber));
                      session.setAttribute("PROVIDER_SELECTED",provider);
                        fireForm.setCertNumber(certNumber);
                      return mapping.findForward("uploadEms"); 
                    }
                   
                  }
          else if  (method.equals("goToProvider")) 
                  { 
                    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                     redirectUrl.append(request.getContextPath()).append("/ems/provider.do?method=providerList");
                     response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                     return null;
                  }
         
           else if (method.equals("uploadFile")) 
          {
          
          final FormFile oneFile = fireForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {   EmsProvider provider =  (EmsProvider) session.getAttribute("PROVIDER_SELECTED");
                  String certNumber = request.getParameter("certNumber"); 
                  String userEmail = request.getParameter("email"); 
                 if (certNumber == null || certNumber.equals("")) {
                   session.setAttribute("CERT_NUMBER_ERROR","ERROR");
                   session.setAttribute("USER_EMAIL_ERROR","");
                   return mapping.findForward("uploadEms"); 
                 }
                  if (userEmail == null || userEmail.equals("")) {
                    session.setAttribute("USER_EMAIL_ERROR","ERROR");
                    session.setAttribute("CERT_NUMBER_ERROR","");
                    return mapping.findForward("uploadEms"); 
                  }
                  fDAO.uploadFile(oneFile,certNumber,userEmail);  
                  provider.setFileList(fDAO.selectFileList(certNumber));
                  sendEmailUser(userEmail,certNumber,provider);
                  session.setAttribute("USER_EMAIL_ERROR","");
                  session.setAttribute("CERT_NUMBER_ERROR","");
                  session.setAttribute("FILE_EXTENSION","");
                  return mapping.findForward("uploadEms");
                
            }
           return mapping.findForward("uploadEms");
          }
          else if  (method.equals("sendErrorEmail")) 
                   { 
                     String comments = fireForm.getComments(); 
                     String fileName = request.getParameter("fileName"); 
                     String userEmail = request.getParameter("userEmail"); 
                     String certNumber = request.getParameter("certNumber"); 
                     EmsProvider provider =pDAO.selectProviderByCert(certNumber);
                     sendErrorEmailUser(userEmail,certNumber,comments,fileName,provider);
                     return mapping.findForward("start");  
                   }
         else if  (method.equals("processNewFiles")) 
                  { 
                    String startDate = request.getParameter("startDate"); 
                    String endDate = request.getParameter("endDate"); 
                    List fileList = fDAO.selectFileListAdmin(startDate,endDate);
                    request.setAttribute("FIRE_HOUSE_FILE_LIST",new HsListWrapper(fileList));
                    return mapping.findForward("start");  
                  }
          
           else if (method.equals("downLoadFile")) 
         { 
              if (security.getGroupLevelHipaa()!=null ||security.getGroupLevelAll()!=null)
              {
          String fileType = request.getParameter("fileType");
          int fileId = Integer.parseInt(request.getParameter("fileId"));
          String fileName = request.getParameter("fileName");
          doGetFile(request,response,fDAO,fileName,fileType,fileId);
           return null;
              }
              else {
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                 redirectUrl.append(request.getContextPath()).append("/main/main.do");
                 response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;
              }
         }
        
          else if (method.equals("approveFile")) 
          {    int fileId = Integer.parseInt(request.getParameter("fileId"));
                  fDAO.updateFileStatus(fileId);
            List fileList = fDAO.selectFileListAdmin(null,null);
            request.setAttribute("FIRE_HOUSE_FILE_LIST",new HsListWrapper(fileList));
            return mapping.findForward("start");  
          } 
          else if (method.equals("deleteFile")) 
             { 
              int fileId = Integer.parseInt(request.getParameter("fileId"));
               fDAO.deleteFile(fileId);
               List fileList = fDAO.selectFileListAdmin("xx","xx");
               request.setAttribute("FIRE_HOUSE_FILE_LIST",new HsListWrapper(fileList));
               return mapping.findForward("start"); 
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
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,fireHouseDAO fDAO,String fileName,String fileType,int fileId) throws Exception 
  {
  try {response.setContentType(fileType);
  response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\"");
  java.io.OutputStream out = response.getOutputStream();
  int lenFile = fDAO.downLoad(out,fileId);
  out.flush();
  out.close();
  }
       catch(Exception e) 
       {
         throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
       }
  
  }
  
  private static void sendEmailUser( String userEmail,String certNumber,EmsProvider provider) throws Exception
   {
           try {
              
             HsMailer mail = new HsMailer();
             String[] emailTo = {userEmail};
           //  String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
             String[] bccTo = {"abiggs@dhs.in.gov"};
            
             StringBuffer sb = new StringBuffer();
             sb.append("This is to confirm that we have received a data file from provider:"+provider.getProviderName()+" with certification number "+certNumber+". This file will be used to transfer data to Fire House Database.").append("\n\r"); 
             sb.append("If you have any questions on this email please contact Angie Biggs at abiggs@dhs.in.gov").append("\n\r");
                  StringBuffer sub = new StringBuffer();
             sub.append(" From IDHS EMS  Data file received confirmation");
             
             mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
             } 
             catch(Exception e) 
             {
               HsMailer mail = new HsMailer();
             mail.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email fire house file upload");
             mail.sendAndClose();
             }
   }
  private static void sendErrorEmailUser( String userEmail,String certNumber,String error,String fileName,EmsProvider provider) throws Exception
   {
           try {
              
             HsMailer mail = new HsMailer();
             String[] emailTo = {userEmail};
           //  String[] emailTo =  {"nnimmagadda@dhs.in.gov"};
             String[] bccTo = {"abiggs@dhs.in.gov"};
            
             StringBuffer sb = new StringBuffer();
             sb.append(" We have received a data file:"+fileName+" for "+provider.getProviderName()+". During our data transfer, we have received the following error with the file: "+fileName+" you have submitted.").append("\n\r"); 
               sb.append("''"+error +"'' You are advised to send a new data file for processing.").append("\n\r"); 
             sb.append("If you have any questions on this email please contact Angie Biggs at abiggs@dhs.in.gov").append("\n\r");
                  StringBuffer sub = new StringBuffer();
             sub.append(" From IDHS EMS  Data file error information for "+provider.getProviderName());
             
             mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
             } 
             catch(Exception e) 
             {
               HsMailer mail = new HsMailer();
             mail.createMail("ems_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email fire house error email");
             mail.sendAndClose();
             }
   }
}
