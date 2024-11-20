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
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import main.data.*;
import main.control.form.*;
import org.apache.struts.upload.FormFile;
import planReview.control.form.*;
import planReview.data.*;
public class PlanFileAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           
           ServletContext context = 
           this.getServlet().getServletConfig().getServletContext();
          
           
           DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
           
           FileNamesForm fileForm = (FileNamesForm) form;
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
           PlanReviewDAO aDAO = (PlanReviewDAO) dmapNew.getHsDAO(DfbsDataMap2.PLAN_REVIEW_UPLOAD);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
           String method = request.getParameter("method");
            HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
           // System.out.println(method);
            if (method == null) 
         
           {
             return mapping.findForward("start");
           } 
           
         
                else if (method.equals("editFileName")) 
           {  
             int fileId = Integer.parseInt(request.getParameter("fileId")); 
             String fileName = request.getParameter("fileName"); 
            // System.out.println(fileName);
             fileForm.setFileId(fileId);
             fileForm.setFileName(fileName);
            return mapping.findForward("editFileName");
           } 
                 else if (method.equals("saveFileName")) 
           { 
            int fileId=fileForm.getFileId();
            String fileName= fileForm.getFileName();
            aDAO.updateFileName(fileId,fileName);
             String  idNumber=(String)  session.getAttribute("SBC_PLAN_NUMBER");
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                    redirectUrl.append(request.getContextPath()).append("/planReview/upload.do?method=goToUpload&idNumber="+idNumber);
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
      
}
