package arson.control.action;

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
import arson.control.form.*;
import hs.to.*;
import arson.to.*;
import hs.util.*;
import hs.data.*;
import main.to.*;
import main.data.*;
import arson.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
public class ArsonAction extends ControlAction
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
        
        
        ArsonUploadForm arsonForm = (ArsonUploadForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        ArsonUploadDAO aDAO = (ArsonUploadDAO) dmap2.getHsDAO(DfbsDataMap.ARSON_UPLOAD);
       ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
         HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        
         if (method == null) 
      
        {
         throw new Exception("HS_ERROR_METHOD_INVALID");
        } 
         else if (method.equals("goToUpload")) 
        { 
          String idNumber = request.getParameter("idNumber"); 
          String idType = request.getParameter("idType"); 
          
          session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
          ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
          aDAO.getConnectionInfo(security,idNumber,idType);
          String userCheck = sDAO.getSecurity(security,security.getUserId(),security.getUserPassword());
          aDAO.resetConnectionInfo(idNumber,idType);
         
           if (userCheck  != "" )
          {   session.setAttribute("DFBS_ARSON",new Arson ());
           Arson arson = (Arson) session.getAttribute("DFBS_ARSON");
           arsonForm.setIdNumber(idNumber);
           arsonForm.setIdType(idType);
           arson.setIdNumber(idNumber);
           arson.setIdType(idType);
           arson.setFileList(aDAO.selectFileList(idNumber,idType)); 
                  
         return mapping.findForward("goToUpload");
           
          }
          else
          {security.setUserId("ERROR");
            return mapping.findForward("error");
          }
        } 
         
        
        // file upload download feature
          else if (method.equals("downLoadFile")) 
         { 
          String fileName = request.getParameter("fileName");
          String fileType = request.getParameter("fileType");
          int fileId = Integer.parseInt(request.getParameter("fileId"));
          doGetFile(request,response,aDAO,fileName,fileType,fileId);
           return null;
         }
          // file upload upload feature
          else if (method.equals("uploadFile")) 
         { 
         
         final FormFile oneFile = arsonForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
               { String idNumber = request.getParameter("idNumber"); 
                 String idType = request.getParameter("idType"); 
                  Arson arson = (Arson) session.getAttribute("DFBS_ARSON");
                  ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
                  aDAO.uploadFile(oneFile,idNumber,security.getUserId(),idType);   
                  arson.setFileList(aDAO.selectFileList(idNumber,idType)); 
            }
            
           return mapping.findForward("goToUpload");
         }
        
         // password change
         
        
         // email forgotten password
         
   
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
    catch (Exception e) 
    {
       saveError(request,e);
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
       return mapping.findForward("error");
      
    }
        
      
        
      
  }
   
 
  private static void sendEmail(String email,String passWord) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
            String[] emailTo = {email};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS Password Recovery");
            sb.append("\r\n user id=");
            sb.append(email);
            sb.append("\r\n password= ");
            sb.append(passWord);
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS password recovery service ");
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  }
  
 
  
   public void doGetFile(HttpServletRequest request, HttpServletResponse response,ArsonUploadDAO eDAO,String fileName,String fileType,int fileId) throws Exception 
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
 
  
 
}
