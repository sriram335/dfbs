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

import idhsInspections.to.searchResults;

import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;
import main.to.FileNames;

import org.apache.struts.upload.FormFile;

import util.logging.DHSLogLevel;
import util.logging.Log;

public class varCodeAction  extends ControlAction
{
    private static final String CLASS_NAME="varCodeAction";
 
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          String METHOD_NAME="executeControl"; 
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        varCodeDetailForm codeForm = (varCodeDetailForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = ( DfbsOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_OWNER);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        varianceApplicationDAO aDAO = (varianceApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_APPLICATION); 
        varDesignerDAO dDAO = ( varDesignerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_DESIGNER);
        String method = request.getParameter("method");
        METHOD_NAME=method; 
        HttpSession session = request.getSession();
        ApplicationContacts contacts = sDAO.setContacts("VARIANCE_CONTACT1","VARIANCE_CONTACT2");
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
        session.setAttribute("APPLICATION_CONTACTS",contacts);
         if (method.equals("newCode")) 
        {    setOptions(request,dfbsUtilityDAO);
             List varCodes =aDAO.selectStdVarCodeList();
          session.setAttribute("VARIANCE_CODE_STD_LIST",varCodes); 
           session.setAttribute("NEW_VARIANCE_STATUS", "START_CODE"); 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          String checkAddVarApp = (String) session.getAttribute("ADD_VARAPP_SECURITY");
            if (varApp.getVarCodesList().size() >0 && checkAddVarApp !=null && checkAddVarApp.equals("Y")) {
                       Iterator j = varApp.getVarCodesList().iterator();
                        while(j.hasNext())
                        { 
                          VarCodeDetail code = (VarCodeDetail) j.next();
                          varApp.addCode(code);
                          codeForm.setProperties(code);  
                          session.setAttribute("VARIANCE_CODE_SELECTED",code);
                        }
                      }
            return mapping.findForward("newCode");
        }
        if (method.equals("addStdCode")) 
        {    setOptions(request,dfbsUtilityDAO);
         String varStdCode= request.getParameter("varStdCode");
          codeForm.setVarCode(varStdCode);
           return mapping.findForward("newCode");
        }
        else if (method.equals("codeDetail")) 
        {      
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                      redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=codeDetail");
                      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;
        }
        else if (method.equals("viewCode")) 
        {      
          int varCodeId = Integer.parseInt(request.getParameter("varCodeId"));
          VarCodeDetail code = aDAO.selectVarCode(varCodeId);
          codeForm.setProperties(code); 
          session.setAttribute("VARIANCE_VIEW_CODE",code);
          setOptions(request,dfbsUtilityDAO);
          varianceApplication varApp = aDAO.selectApp(code.getVarId());
          varDesigner designer =dDAO.selectDes(varApp.getVarProjId());
          DfbsOwner owner =oDAO.selectOwner(varApp.getVarProjOwnerId());
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          if (security!=null)
          {
          String emailList="";
          if(owner!=null & owner.getOwnerEmail()!=null) {
            emailList=emailList+owner.getOwnerEmail()+",";
          }
          if(designer!=null& designer.getDesEmail()!=null) {
            emailList=emailList+designer.getDesEmail()+",";
          }
          if(varApp.getVarAppEmail()!=null) {
            emailList=emailList+varApp.getVarAppEmail()+",";
          }
          emailList=emailList+security.getUserId()+"@dhs.in.gov";
          session.setAttribute("VARIANCE_APP_EMAIL_LIST",emailList);
          }                                                 
          String procCheck = (String) session.getAttribute("PROCESS_APPLICATION_LIST");
          
          if (procCheck.equals("Post"))
          {
          List varAppList =aDAO.selectAppList("Post",null,null);
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
          }
          else  {
            List varAppList =aDAO.selectAppList("commDate",null,null);
            request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
          }
            return mapping.findForward("processCompApplications");
        }
        else if (method.equals("viewCodeDetail")) 
        {      
          int varId = Integer.parseInt(request.getParameter("varId"));
          varianceApplication varApp = aDAO.selectApp(varId);
          varDesigner designer =dDAO.selectDes(varApp.getVarProjId());
          DfbsOwner owner =oDAO.selectOwner(varApp.getVarProjOwnerId());
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          if (security!=null)
          {
          String emailList="";
          if(owner!=null & owner.getOwnerEmail()!=null) {
            emailList=emailList+owner.getOwnerEmail()+",";
          }
          if(designer!=null& designer.getDesEmail()!=null) {
            emailList=emailList+designer.getDesEmail()+",";
          }
          if(varApp.getVarAppEmail()!=null) {
            emailList=emailList+varApp.getVarAppEmail()+",";
          }
          emailList=emailList+security.getUserId()+"@dhs.in.gov";
          session.setAttribute("VARIANCE_APP_EMAIL_LIST",emailList);
          }                                                         
          if (request.getParameter("varCodeId")!=null)
          {
          int varCodeId = Integer.parseInt(request.getParameter("varCodeId"));
          VarCodeDetail code = aDAO.selectVarCode(varCodeId);
         codeForm.setProperties(code);
            session.setAttribute("VARIANCE_VIEW_CODE",code);
          }
          searchResults result = new searchResults();
          aDAO.selectNextVarianceList(result);
          session.setAttribute("VARIANCE_NUM_LIST",result);
          setOptions(request,dfbsUtilityDAO);
          Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "1.:");
             return mapping.findForward("updateCode");
        }
        else if (method.equals("updateVarNum")) 
        { 
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "1.:"); 
          int varId = Integer.parseInt(request.getParameter("varId"));
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "varId.:"+varId); 
          String varNumber=request.getParameter("varNumber");
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "varNumber.:"+varNumber); 
          if (varNumber.length()==6) {
                    varNumber=varNumber+"01" ;
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "varNumber.inside len6:"+varNumber); 
             }
             varianceApplication varApp = aDAO.selectApp(varId);
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "varNumber.set::"+varNumber); 
            varApp.setVarAppVarNumber(varNumber);
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "varApp::"+varApp); 
            aDAO.updateApp(varApp);
            Log.log("UserId:"+security.getUserId()+":", DHSLogLevel.INFO, CLASS_NAME, method, "varNumber::"+varNumber); 
            aDAO.updatePermitTransaction(request.getParameter("varId"), varNumber);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
           redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=viewApplications");
        // redirectUrl.append(context.getInitParameter("contextPath")).append("/variance/start.do?method=viewApplications");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }
        else if (method.equals("updateCode")) 
        {      
          VarCodeDetail code =codeForm.getVarCodeDetail();
          aDAO.updateVarCode(code);
          codeForm.setProperties(code);
          setOptions(request,dfbsUtilityDAO);
          //Added below session value to get get the commission date reqd to run report which sends out an email. Dev 03/01/2015
          session.setAttribute("VARIANCE_VIEW_CODE",code);
          List varAppList =aDAO.selectAppList("Post",null,null);
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
            return mapping.findForward("processCompApplications");
        }
        else if (method.equals("updateCodeDetail")) 
        {      
          VarCodeDetail code =codeForm.getVarCodeDetail();
           aDAO.updateVarCode(code); 
          codeForm.setProperties(code);
          setOptions(request,dfbsUtilityDAO);
          session.setAttribute("VARIANCE_VIEW_CODE",code);
          return mapping.findForward("updateCode");
        }
     /*   else if (method.equals("saveCode")) 
        {      
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          VarCodeDetail code =codeForm.getVarCodeDetail();
          varApp.addCode(code);
          setOptions(request,dfbsUtilityDAO);
                     return mapping.findForward("newCode");
        }*/
        else if (method.equals("removeCode")) 
        {    
          String codeKey= request.getParameter("codeKey");
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          varApp.removeCode(codeKey);
          varApp.setAmount(aDAO.selectFeesMapping(varApp.getCodeMapCount()-1,varApp.getVarAppVarNumber(),varApp.getVarId()));
          session.setAttribute("VARIANCE_CODE_SELECTED",null); 
          setOptions(request,dfbsUtilityDAO);
                     return mapping.findForward("newCode");
        }
        else if (method.equals("editCode")) 
        {    
          String codeKey= request.getParameter("codeKey");
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          VarCodeDetail code = varApp.getCode(codeKey);
          session.setAttribute("VARIANCE_CODE_SELECTED",code); 
           codeForm.setProperties(code); 
          varApp.removeCode(codeKey);
           setOptions(request,dfbsUtilityDAO);
                     return mapping.findForward("newCode");
        }
        else if (method.equals("backToApplication")) 
        {   StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=backToCompCodeDetail");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
        }
        else if (method.equals("compCodeDetail")) 
        {  
          String appAction = request.getParameter("completeApplication");
          setOptions(request,dfbsUtilityDAO); 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          varApp.setVarProjState("IN");
          if (appAction.equals("Complete Application"))
          {     if (varApp.getCodeMapCount() >0 )
                {
                session.setAttribute("NEW_VARIANCE_CODE_COUNT","Y"); 
              String currentDate =sDAO.selectCurrentDate();
                session.setAttribute("CURRENT_DATE",currentDate);
              String url = request.getRemoteAddr();
              session.setAttribute("CURRENT_IP",url);
                return mapping.findForward("affirmation");
              }
              else {
                session.setAttribute("NEW_VARIANCE_CODE_COUNT","N"); 
                return mapping.findForward("newCode");
              }
          }
          else if (appAction.equals("Add Another Variance")){
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/variance/code.do?method=newCode");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          else if (appAction.equals("Upload File(s)")){
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=goToUpload");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          else if (appAction.equals("Save Variance")) 
          {     
            VarCodeDetail code =codeForm.getVarCodeDetail();
            varApp.addCode(code);
            varApp.setVarAppNumCode(varApp.getCodeMapCount());
            varApp.setAmount(aDAO.selectFeesMapping(varApp.getCodeMapCount()-1,varApp.getVarAppVarNumber(),varApp.getVarId()));
            setOptions(request,dfbsUtilityDAO);
            VarCodeDetail newCode =new VarCodeDetail();
            codeForm.setProperties(newCode);
            session.setAttribute("NEW_VARIANCE_STATUS", "END_CODE");
            return mapping.findForward("codeListApplications");
        /*    searchResults results = new searchResults();
            aDAO.selectLboDetails(results, varApp.getVarProjCity(), varApp.getVarProjCounty(), varApp.getVarAppFDName(), varApp.getVarAppBDName(), varApp.getVarProjZip());
            varApp.setVarAppLboEmail(results.getResultType());
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/variance/start.do?method=codeListApplication");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;*/
          }
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
    List states = uDAO.getOptions(varianceSQL.SELECT_STATE_OPTIONS);
   List states2 = uDAO.getOptions(varianceSQL.SELECT_STATE_OPTIONS2);
   List counties = uDAO.getOptions(varianceSQL.SELECT_COUNTY_OPTIONS);
    
   List vioBy = uDAO.getOptions(varianceSQL.VAR_VIOLATIONS_BY_OPTIONS);
   List yesNo = uDAO.getOptions(varianceSQL.VAR_YES_NO_OPTIONS);
   List varProjType = uDAO.getOptions(varianceSQL.VAR_PROJ_TYPE_OPTIONS);
   List varCommAction = uDAO.getOptions(varianceSQL.VAR_COMM_ACTION_OPTIONS);
   List varStaffCommType = uDAO.getOptions(varianceSQL.VAR_STAFF_COMM_BY_OPTIONS);
   request.setAttribute("VAR_PROJ_OPTIONS",varProjType);
   request.setAttribute("VIO_BY_OPTIONS",vioBy);
   request.setAttribute("YES_NO_OPTIONS",yesNo);
   request.setAttribute("OWNER_STATE_OPTIONS",states);
   request.setAttribute("VAR_STATE_OPTIONS",states2);
   request.setAttribute("VAR_COMM_ACTION_OPTIONS",varCommAction);
   request.setAttribute("VAR_COUNTY_OPTIONS",counties);
   request.setAttribute("VAR_STAFF_COMM_OPTIONS",varStaffCommType);
   
 } 
}