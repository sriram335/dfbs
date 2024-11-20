package Variance.control.action;
import hs.control.ControlAction;
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
import hs.util.*;
import hs.data.*;
import hs.data.system.*;

import hs.report.pdf.*;
import Variance.data.*;
import Variance.control.form.*;

import Variance.to.*;

import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;
import main.to.FileNames;

import org.apache.struts.upload.FormFile;
public class varDesignerAction extends ControlAction{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        varDesignerForm desForm = ( varDesignerForm ) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        varDesignerDAO dDAO = ( varDesignerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_DESIGNER);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        varianceApplicationDAO aDAO = (varianceApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_APPLICATION); 
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method.equals("newDesigner")) 
        { 
          varDesigner newDesigner = ( varDesigner) session.getAttribute("VARIANCE_DESIGNER_SELECTED"); 
          if (newDesigner !=null) {
            desForm.setProperties(newDesigner);
          }
          session.setAttribute("NEW_VARIANCE_STATUS", "START_DESIGNER"); 
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newDesigner");
        } 
       
        else if (method.equals("saveDesigner")) 
        {  
            varDesigner newDesigner = desForm.getVarDesigner();
         varDesignerForm errorForm = new varDesignerForm();
         if (validate(newDesigner,errorForm,session) ) {
            session.setAttribute("NEW_VARIANCE_STATUS", "END_DESIGNER"); 
            session.setAttribute("VARIANCE_DESIGNER_SELECTED", newDesigner); 
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=projectInfo");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
          }
          desForm.setProperties(newDesigner);
          request.setAttribute("DES_ERROR",errorForm);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newDesigner");
            
        }
        if (method.equals("viewDesigner")) 
        {  int desId = Integer.parseInt(request.getParameter("designerId"));
           varDesigner designer =dDAO.selectDes(desId);
          session.setAttribute("VIEW_DESIGNER_SELECTED",designer);
          desForm.setProperties(designer);
           setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateDesigner");
        } 
        if (method.equals("updateDesigner")) 
        {  varDesigner des = desForm.getVarDesigner();
           dDAO.updateDes(des);
           setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateDesigner");
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
  

  private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"cclouse@dhs.in.gov"};
           String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
          
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append(" From Fire display Online enquiry - ").append(subject);
          
          
          
            mail.createMail("fire_display_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("display_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Fire display error emailOnline enquiry error ","DfbsDesAction");
            mail1.sendAndClose();             }
  }
  

  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception
  {
    List states = uDAO.getOptions(varianceSQL.SELECT_STATE_OPTIONS);
   List states2 = uDAO.getOptions(varianceSQL.SELECT_STATE_OPTIONS2);
   List counties = uDAO.getOptions(varianceSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DESIGNER_STATE_OPTIONS",states2);
   request.setAttribute("VAR_STATE_OPTIONS",states2);
   request.setAttribute("VAR_COUNTY_OPTIONS",counties);
   
  }
    protected static boolean validate(varDesigner des,varDesignerForm errorForm,HttpSession session) 
     {
       boolean noError = true;
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
     
     if (des.getDesId() == 0)
     {
       
       if(des.getDesDBA() == null || des.getDesDBA().trim().equals("") ) 
       {
         errorForm.setDesDBA("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesDBA("");
       }
       if(des.getDesLastName() == null || des.getDesLastName().trim().equals("") ) 
       {
         errorForm.setDesLastName("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesLastName("");
       }
       if(des.getDesFirstName() == null || des.getDesFirstName().trim().equals("") ) 
       {
         errorForm.setDesFirstName("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesFirstName("");
       }
      
       if(des.getDesAddress1() == null || des.getDesAddress1().trim().equals("") ) 
       {
         errorForm.setDesAddress1("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesAddress1("");
       }
       
       if(des.getDesCity() == null || des.getDesCity().trim().equals("") ) 
       {
         errorForm.setDesCity("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesCity("");
       }
       
       if(des.getDesState() == null || des.getDesState().trim().equals("")  ) 
       {
         errorForm.setDesState("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesState("");
       }
       
       if(des.getDesZip() == null || des.getDesZip().trim().equals("") ) 
       {
         errorForm.setDesZip("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesZip("");
       }
      
       
     } 
      if(des.getDesEmail() == null || des.getDesEmail().trim().equals("") ||
          des.getDesEmail().indexOf('@') <=0 || des.getDesEmail().indexOf('.') <= 0 ) 
       {
         errorForm.setDesEmail("ERROR");
         noError = false;
       } 
       else 
       { 
         errorForm.setDesEmail("");
       }
       
       if(des.getDesPhone() == null || des.getDesPhone().trim().equals("") ||
          des.getDesPhone().indexOf('-') > 0 ||des.getDesPhone().indexOf(')') > 0 ||
          des.getDesPhone().indexOf('(') > 0) 
       {
         errorForm.setDesPhone("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setDesPhone("");
       }
      
      
       return noError;
     }

  }