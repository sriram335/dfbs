
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

import aepermits.to.DfbsEntrPermit;

import idhsInspections.to.searchResults;

import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;
import main.to.FileNames;

import org.apache.struts.upload.FormFile;

public class varianceStartAction  extends ControlAction
{
 
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
         DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        varianceApplicationForm varForm = (varianceApplicationForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = ( DfbsOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_OWNER);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        varianceApplicationDAO aDAO = (varianceApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_APPLICATION); 
        varDesignerDAO dDAO = ( varDesignerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_DESIGNER);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationContacts contacts = sDAO.setContacts("VARIANCE_CONTACT1","VARIANCE_CONTACT2");
        session.setAttribute("APPLICATION_CONTACTS",contacts);
        if (method == null) 
        { 
          ShoppingCart cart = new ShoppingCart(); 
          session.setAttribute("SHOPPING_CART",cart);
          session.setAttribute("VARIANCE_ONLINE_APP_STATUS",null);
           FileNames names= sDAO.selectAppStatus("VARIANCE_MAINT_FLAG");
          session.setAttribute("VARIANCE_APP_STATUS",names.getFileName());
          session.setAttribute("VARIANCE_APP_MESSAGE",names.getFileType());
          List commDates =aDAO.selectCommDates();
          request.setAttribute("VARIANCE_COMM_DATES",commDates);
          setOptions(request,dfbsUtilityDAO);
        //Added below line in reference to the Variance link in idhsInspections module Dev 06/13/2015
          session.removeAttribute("idhsInsp");
          return mapping.findForward("start");
        } 
        else if (method.equals("startApplication")) 
               {   varianceApplication varApp = new varianceApplication();
                   System.out.println("ProjNbr:"+varForm.getVarAppSBCNum());
                     if (varForm.getVarAppSBCNum() >0) {
                         varApp.setVarAppSBCNum(varForm.getVarAppSBCNum());
                         varApp.setVarProjStatus("F");
                       DfbsOwner newOwner =new DfbsOwner();
                       varDesigner newDes =new varDesigner();
                       aDAO.selectPlanData(varApp.getVarAppSBCNum(), newOwner, newDes, varApp);
                       session.setAttribute("VARIANCE_OWNER_SELECTED", newOwner); 
                       session.setAttribute("VARIANCE_DESIGNER_SELECTED", newDes);      
                     }else{
                         varForm.setVarAppSBCNum(null);
                     }
                 session.setAttribute("NEW_VARIANCE_STATUS", "START"); 
                 session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp); 
                     StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                     redirectUrl.append(request.getContextPath()).append("/variance/owner.do?method=addNewOwner");
                     response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;
               } 
        else if (method.equals("subQuestion")) 
          {    
        return mapping.findForward("subQuestion");
        }
        else if (method.equals("subQuestionY")) 
        {    varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
             if (varApp !=null) {
               varForm.setProperties(varApp);
             }
            session.setAttribute("NEW_VARIANCE_STATUS", "START_SUBMITTER"); 
            setOptions(request,dfbsUtilityDAO);    
          return mapping.findForward("submitter");
        }
        else if (method.equals("subQuestionN")) 
        {  return mapping.findForward("desQuestion");
        }
        
        else if (method.equals("saveSubmitter")) 
        {varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          varApp.setVarAppAddress1(varForm.getVarAppAddress1());
          varApp.setVarAppAddress2(varForm.getVarAppAddress2());
          varApp.setVarAppCity(varForm.getVarAppCity());
          varApp.setVarAppFirmName(varForm.getVarAppFirmName());
          varApp.setVarAppZip(varForm.getVarAppZip());
          varApp.setVarAppZip2(varForm.getVarAppZip2());
          varApp.setVarAppFirstName(varForm.getVarAppFirstName());
          varApp.setVarAppLastName(varForm.getVarAppLastName());
          varApp.setVarApppMI(varForm.getVarApppMI());
          varApp.setVarAppState(varForm.getVarAppState());
          varApp.setVarAppTelephone(varForm.getVarAppTelephone());
          varApp.setVarAppFax(varForm.getVarAppFax());
          varApp.setVarAppEmail(varForm.getVarAppEmail());
          varianceApplicationForm errorForm = new varianceApplicationForm();
          if (validate(varApp,errorForm,session,"SUBMITTER") ) { 
            session.setAttribute("NEW_VARIANCE_STATUS", "END_SUBMITTER"); 
            return mapping.findForward("desQuestion");
          }
          else { setOptions(request,dfbsUtilityDAO);
           varForm.setProperties(varApp);
           request.setAttribute("VARIANCE_APPLICATION_ERROR",errorForm);
           return mapping.findForward("submitter");
          }
          
        }
        else if (method.equals("viewSubmitter")) 
          { int varId = Integer.parseInt(request.getParameter("varId"));
            varianceApplication varApp = aDAO.selectApp(varId);
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          varForm.setProperties(varApp); 
            setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("updateSubmitter");
        }
        else if (method.equals(" updateSaveSubmitter")) 
          { varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          varApp.setVarAppAddress1(varForm.getVarAppAddress1());
          varApp.setVarAppAddress2(varForm.getVarAppAddress2());
          varApp.setVarAppCity(varForm.getVarAppCity());
          varApp.setVarAppFirmName(varForm.getVarAppFirmName());
          varApp.setVarAppZip(varForm.getVarAppZip());
          varApp.setVarAppZip2(varForm.getVarAppZip2());
          varApp.setVarAppFirstName(varForm.getVarAppFirstName());
          varApp.setVarAppLastName(varForm.getVarAppLastName());
          varApp.setVarApppMI(varForm.getVarApppMI());
          varApp.setVarAppState(varForm.getVarAppState());
          varApp.setVarAppTelephone(varForm.getVarAppTelephone());
          varApp.setVarAppFax(varForm.getVarAppFax());
          varApp.setVarAppEmail(varForm.getVarAppEmail());
          aDAO.updateApp(varApp);
          varForm.setProperties(varApp); 
            setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("updateSubmitter");
        }
       
        else if (method.equals("viewProjectInfo")) 
          { int varId = Integer.parseInt(request.getParameter("varId"));
            varianceApplication varApp = aDAO.selectApp(varId);
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          varForm.setProperties(varApp); 
            setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("updateProjectInfo");
        }
        else if (method.equals("projectInfo")) 
        {   session.setAttribute("NEW_VARIANCE_STATUS", "START_PROJECT"); 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          varForm.setProperties(varApp);   
            setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("projectInfo");
        }
        else if (method.equals("saveProjectInfo")) 
        {varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          varApp.setVarProjAddress1(varForm.getVarProjAddress1());
          varApp.setVarProjAddress2(varForm.getVarProjAddress2());
          varApp.setVarProjCity(varForm.getVarProjCity());
          varApp.setVarProjCounty(varForm.getVarProjCounty());
          varApp.setVarProjName(varForm.getVarProjName());
          varApp.setVarProjState("IN");
          varApp.setVarProjType(varForm.getVarProjType());
          varApp.setVarProjZip(varForm.getVarProjZip());
          varApp.setVarProjZip2(varForm.getVarProjZip2());
          varApp.setVarProjStatus(varForm.getVarProjStatus());
          varApp.setVarAppPlanCorrection(varForm.getVarAppPlanCorrection());
          varApp.setVarAppViolation(varForm.getVarAppViolation());          
          varApp.setVarAppVioIssBy(varForm.getVarAppVioIssBy());
          varApp.setVarAppPhAffirm(varForm.getVarAppPhAffirm());
          varApp.setVarAppPhAffirmComment(varForm.getVarAppPhAffirmComment());
          varApp.setVarAppHistAffExc(varForm.getVarAppHistAffExc());
          varApp.setVarAppHistAffMaj(varForm.getVarAppHistAffMaj());
          varApp.setVarAppHistAffPhy(varForm.getVarAppHistAffPhy());
          varApp.setVarAppBDName(varForm.getVarAppBDName());
          varApp.setVarAppFDName(varForm.getVarAppFDName());
          varApp.setVarAppLboName(varForm.getVarAppLboName());
          varianceApplicationForm errorForm = new varianceApplicationForm();
          
          if (validate(varApp,errorForm,session,"PROJECT") ) { 
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
            session.setAttribute("NEW_VARIANCE_STATUS", "END_PROJECT"); 
            searchResults results = new searchResults();
            aDAO.selectLboDetails(results, varApp.getVarProjCity(), varApp.getVarProjCounty(), varApp.getVarAppFDName(), varApp.getVarAppBDName(), varApp.getVarProjZip());
           // List emailList=aDAO.selectEmailList(varApp.getVarProjCounty()); 
           // session.setAttribute("VARIANCE_LFO_EMAIL_LIST",emailList);
            varForm.setVarAppLboEmail(results.getResultType());
             session.setAttribute("NEW_VARIANCE_STATUS", "END_PROJECT"); 
            return mapping.findForward("lboLfoSelect");
          }
          else { setOptions(request,dfbsUtilityDAO);
           varForm.setProperties(varApp);
           request.setAttribute("VARIANCE_APPLICATION_ERROR",errorForm);
           return mapping.findForward("projectInfo");
          }
        }
        else if (method.equals("saveLboLfo")) 
        {
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          if (varForm.getVarAppLboEmail() ==null || varForm.getVarAppLboEmail().equals("") ||
          varForm.getVarAppLfoEmail() ==null || varForm.getVarAppLfoEmail().equals("")) {
            session.setAttribute("VARIANCE_APP_LFO_LBO_ERROR","Y");
            return mapping.findForward("lboLfoSelect");  
          }else if (varForm.getVarAppLboPhone() ==null || varForm.getVarAppLboPhone().equals("") ||
              varForm.getVarAppLfoPhone() ==null || varForm.getVarAppLfoPhone().equals("")) {
              session.setAttribute("VARIANCE_APP_LFO_LBO_ERROR","Y");
              return mapping.findForward("lboLfoSelect");  
          }
          else
          {session.setAttribute("VARIANCE_APP_LFO_LBO_ERROR","N");
          varApp.setVarAppLboEmail(varForm.getVarAppLboEmail());
          varApp.setVarAppLfoEmail(varForm.getVarAppLfoEmail());
          varApp.setVarAppLboPhone(varForm.getVarAppLboPhone());
          varApp.setVarAppLfoPhone(varForm.getVarAppLboPhone());
          if (varApp.getVarAppLboEmail()!=null &&varApp.getVarAppLboEmail().length()>5)
          {
          varApp.setVarAppLboNotified("N");}
          if (varApp.getVarAppLfoEmail()!=null && varApp.getVarAppLfoEmail().length()>5)
          {
          varApp.setVarAppLfoNotified("N"); }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/variance/code.do?method=newCode");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
          }
        }
        else if (method.equals("processCompApplications")) 
        { session.setAttribute("PROCESS_APPLICATION_LIST","Post");
           List varAppList =aDAO.selectAppList("Post",null,null);
         request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);                                         
           setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("processCompApplications");
        }
        else if (method.equals("processComDateApplications")) 
        {  session.setAttribute("PROCESS_APPLICATION_LIST","Date");
           List varAppList =aDAO.selectAppList("commDate",null,null);
         request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);                                        
           setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("processCompApplications");
        }
         else if (method.equals("processPreApplications")) 
        {  
            List varAppList =aDAO.selectAppList("Pre",null,null);
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);                                         
            setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("processApplications");
        }
        else if (method.equals("viewApp")) 
        {  int varId = Integer.parseInt(request.getParameter("varId"));
           
          varianceApplication varApp = aDAO.selectApp(varId);
          varForm.setProperties(varApp); 
          varApp.setFileList(aDAO.selectFileList(Integer.toString(varApp.getVarId()),"Variance"));  
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);  
          List varAppList =aDAO.selectAppList("Pre",null,null); 
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
            setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("processApplications");
        }
        else if (method.equals("testEmailSubmission")) 
        {  int varId = Integer.parseInt(request.getParameter("varId"));
          varianceApplication varApp = aDAO.selectApp(varId);
          List emailList=aDAO.selectEmailList(varApp.getVarProjCounty()); 
          sendEmailCheckOut(varApp,contacts,session,emailList);
          return mapping.findForward("processApplications");
        }
        else if (method.equals("sendEmailSubmitter")) 
        {  varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");  
           String emailComment= request.getParameter("varAppHistAffPhyComment");
          varApp.setVarAppHistAffPhyComment(emailComment);
          aDAO.updateApp(varApp);
          DfbsOwner owner = oDAO.selectOwner(varApp.getVarProjOwnerId());
          sendEmailSubmitter(varApp,contacts,owner);
          varForm.setProperties(varApp); 
            setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateSubmitter");
        }
        else if (method.equals("updateApplication")) 
        {   
          varianceApplication varApp = varForm.getVarianceApplication();
          aDAO.updateApp(varApp);
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          List varAppList =aDAO.selectAppList("Pre",null,null);
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
       //   sendEmailVarApplication(varApp,contacts,session);
            setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("processApplications");
        }
   /*     else if (method.equals("processCompApplications")) 
        {  
            List varAppList =aDAO.selectAppList("Post",null,null);
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
            setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("processApplications");
        } */
        else if (method.equals("viewApplications")){
        
            String startDate ="";
            String endDate = "";
            String projCounty ="";
            String varNumber ="";
            String projName ="";
            String codeType ="";
            String addSql="";
          addSql=" and va.var_id is not null ";
          String varProjType =(request.getParameter("varProjType"));
          if (varProjType==null){varProjType="Pre";}
           startDate =(request.getParameter("varAppLfoName"));
           endDate =(request.getParameter("varAppLboName"));
           projCounty =(request.getParameter("varProjCounty"));
           varNumber =(request.getParameter("varAppVarNumber"));
           projName =(request.getParameter("varProjName"));
           codeType =(request.getParameter("varAppCodeType"));
          //Added below lines in reference to the Variance link in idhsInspections module Dev 06/13/2015
          String idhsInsp =(request.getParameter("from"));
          if (idhsInsp != null) session.setAttribute("idhsInsp",idhsInsp);
          if (varProjType !=null && varProjType.equals("Old") && (startDate ==null||endDate ==null ||projCounty==null) && varNumber==null && projName ==null) {
            session.setAttribute("VAR_APP_LIST_FILTER_ERROR","Y");
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("varListPublic");
          }
          else {
             //   if (varProjType !=null && varProjType.equals("Old") && startDate !=null && !startDate.equals("") ) {
             if (startDate !=null && !startDate.equals("") ) { 
                addSql =addSql+" and va.received_date >= to_date('"+startDate+"','mm/dd/yyyy')";
                session.setAttribute("VAR_APP_LIST_FILTER_ERROR","N");
              }
            if (endDate !=null && !endDate.equals("")) {
            addSql =addSql+" and va.received_date <=  to_date('"+endDate+"','mm/dd/yyyy')";
            session.setAttribute("VAR_APP_LIST_FILTER_ERROR","N");
            }
            if (projCounty!=null && !projCounty.equals("")) {
            addSql =addSql+" and vp.county='"+projCounty+"'";
            session.setAttribute("VAR_APP_LIST_FILTER_ERROR","N");
            }
            if (varNumber!=null && !varNumber.equals("")) {
           // addSql =addSql+" and va.variance_number='"+varNumber+"'";//Dev 06/06/2015
            addSql =addSql+" and substr(VA.VARIANCE_NUMBER,1,3)||substr(VA.VARIANCE_NUMBER,4,3)||lpad(substr(VA.VARIANCE_NUMBER,7,8),2,'0')= substr('"+varNumber+"',1,3)||substr('"+varNumber+"',4,3)||lpad(substr('"+varNumber+"',7,8),2,'0')";
            session.setAttribute("VAR_APP_LIST_FILTER_ERROR","N");
            }
            if (projName !=null && !projName.equals("")) {
            addSql =addSql+" and upper(vp.project_name) like upper('%"+projName+"%')";
            session.setAttribute("VAR_APP_LIST_FILTER_ERROR","N");
            }
            if (codeType !=null && !codeType.equals("")) {
            addSql =addSql+" and var_id  in (select va_var_id from VAR_SPECIFIC_CODE where upper(code_name) like upper('%"+codeType+"%'))";
            session.setAttribute("VAR_APP_LIST_FILTER_ERROR","N");
            }
              System.out.println("varProjType:"+varProjType);
              System.out.println("addSql:"+addSql);
            List varAppList =aDAO.selectAppList( varProjType,null,addSql);
            session.setAttribute("VAR_APP_LIST_FILTER",varProjType);
            session.setAttribute("VAR_APP_LIST_SORT_SQL",addSql);
            request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
          }
            setOptions(request,dfbsUtilityDAO);
            request.setAttribute("varProjCounty","");
          return mapping.findForward("varListPublic");
        }
        else if (method.equals("listSort")) 
        {  String varProjType = (String) session.getAttribute("VAR_APP_LIST_FILTER");
          String sortSql = (String) session.getAttribute("VAR_APP_LIST_SORT_SQL");
          String sortValue =(request.getParameter("checkoutId"));
          List varAppList =aDAO.selectAppList(varProjType,sortValue,sortSql);
          request.setAttribute("VARIANCE_APPLICATION_LIST",varAppList);
            setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("varListPublic");
        }
     
        else if (method.equals("desQuestionY")) 
        {      
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/variance/designer.do?method=newDesigner");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
        }
        else if (method.equals("desQuestionN")) 
        {  varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          varForm.setProperties(varApp);     
          setOptions(request,dfbsUtilityDAO);
          session.setAttribute("VARIANCE_DESIGNER_SELECTED",null); 
          return mapping.findForward("projectInfo");
        }
        else if (method.equals("fromDesignerApplication")) 
        {    setOptions(request,dfbsUtilityDAO); 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          varForm.setProperties(varApp); 
          session.setAttribute("NEW_VARIANCE_STATUS","AFTER_DESIGNER"); 
          return mapping.findForward("varCompApplication");
        }
        else if (method.equals("saveCompApplication")) 
        {   
         varianceApplicationForm errorForm = new varianceApplicationForm();
          varianceApplication varApp = varForm.getVarianceApplication();
          setOptions(request,dfbsUtilityDAO); 
          String varStatus = (String) session.getAttribute("NEW_VARIANCE_STATUS");
           if (validate(varApp,errorForm,session,varStatus) ) { 
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/variance/code.do?method=newCode");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          }
          else {
            varForm.setProperties(varApp);
            request.setAttribute("VARIANCE_APPLICATION_ERROR",errorForm);
            return mapping.findForward("varCompApplication");
          }
        }
      
        else if (method.equals("backToCompCodeDetail")) 
        {    setOptions(request,dfbsUtilityDAO); 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
         varForm.setProperties(varApp);
            if (varApp.getCodeMapCount() >0)
            {
          session.setAttribute("NEW_VARIANCE_STATUS","AFTER_CODE"); 
            session.setAttribute("NEW_VARIANCE_CODE_COUNT","Y"); 
          String currentDate =sDAO.selectCurrentDate();
            session.setAttribute("CURRENT_DATE",currentDate);
          String url = request.getRemoteAddr();
          session.setAttribute("CURRENT_IP",url);
            return mapping.findForward("varCodeDetail");
          }
          else {
            varForm.setProperties(varApp); 
            session.setAttribute("NEW_VARIANCE_CODE_COUNT","N"); 
            return mapping.findForward("varCodeDetail");
          }
        }
        else if (method.equals("submitApplication")) 
        {    setOptions(request,dfbsUtilityDAO); 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          varForm.setProperties(varApp); 
          DfbsOwner owner = (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
          varDesigner des = (varDesigner) session.getAttribute("VARIANCE_DESIGNER_SELECTED"); 
          oDAO.insertOwner(owner);
          varApp.setVarProjOwnerId(owner.getOwnerId());
         aDAO.insertApp(varApp,owner);
          des.setDesProjId(varApp.getVarProjId());
          dDAO.insertDes(des);
          Map mapCode = varApp.getCodeMap();
          Set codekeys = mapCode.keySet();
          Iterator i = codekeys.iterator();
          while(i.hasNext())
          {
           String key = (String) i.next();
           VarCodeDetail code =  (VarCodeDetail) mapCode.get(key); 
            code.setVarId(varApp.getVarId());
            aDAO.insertVarCode(code,varApp);
          }
          aDAO.insertPermitTransaction(varApp, owner,0);
          List emailList=aDAO.selectEmailList(varApp.getVarProjCounty());
          sendEmailCheckOut(varApp,contacts,session,emailList);
          session.setAttribute("NEW_VARIANCE_STATUS","COMPLETE"); 
          return mapping.findForward("affirmation");
        }
       
        else if (method.equals("goToUpload")) 
        { 
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          DfbsOwner owner = ( DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
          if (varApp.getVarAppVarNumber()==null)
          {
            varApp.setFileList(aDAO.selectFileList(owner.getOwnerPhone(),"Variance")); 
          }
          else {
            varApp.setFileList(aDAO.selectFileList(varApp.getVarAppVarNumber(),"Variance")); 
          }
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
        }
        else if (method.equals("goToUploadInternal")) 
        { 
          int varId = Integer.parseInt(request.getParameter("varId"));
          varianceApplication varApp = aDAO.selectApp(varId);
           session.setAttribute("VARIANCE_APPLICATION_SELECTED", varApp);
          DfbsOwner owner = ( DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
          if (varApp.getVarAppVarNumber()==null)
          {
            varApp.setFileList(aDAO.selectFileList(Integer.toString(varApp.getVarId()),"Variance")); 
          }
          else {
            varApp.setFileList(aDAO.selectFileList(varApp.getVarAppVarNumber(),"Variance")); 
          }
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
        }
        else if (method.equals("downLoadFile")) 
        {
        String fileType = request.getParameter("fileType");
        int fileId = Integer.parseInt(request.getParameter("fileId"));
        String fileName = request.getParameter("fileName");
        doGetFile(response,aDAO,fileName,fileType,fileId);
        return null;
        }
        
        else if (method.equals("uploadFile"))
        {
          varianceApplication app = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          DfbsOwner owner = ( DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
          final FormFile oneFile = varForm.getCaseFile();
        if(oneFile != null && oneFile.getFileSize()>0 )
         {    String idType = "Variance"; 
              String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (oneFile.getFileName().length() <=100 && (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                  fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                   fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                   fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                   fileExtension.substring(1,4).toUpperCase().equals("DWF")||fileExtension.substring(1,4).toUpperCase().equals("XLS")))
               {
                if (app.getVarAppVarNumber()!=null)
                {
               aDAO.uploadFile(oneFile,app.getVarAppVarNumber(),idType); 
                  app.setFileList(aDAO.selectFileList(app.getVarAppVarNumber(),"Variance"));
                }
                else {
                    if (app.getVarId()>0) {
                      aDAO.uploadFile(oneFile,Integer.toString(app.getVarId()),idType); 
                      app.setFileList(aDAO.selectFileList(Integer.toString(app.getVarId()),"Variance")); 
                    }
                  else
                    {
                  aDAO.uploadFile(oneFile,owner.getOwnerPhone(),idType); 
                  app.setFileList(aDAO.selectFileList(owner.getOwnerPhone(),"Variance")); 
                    }
                }
             }
             else
             {
             session.setAttribute("FILE_EXTENSION","ERROR");
             return mapping.findForward("goToUpload");
             }
         }
          
          return mapping.findForward("goToUpload");
            
        }
        else if (method.equals("backToApplication")) 
         {    setOptions(request,dfbsUtilityDAO);
           varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
            if (varApp !=null)
           {
           varForm.setProperties(varApp);
            return mapping.findForward("codeListApplications");
           }
           else {
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/variance/start.do");
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;
           }
         }
     /*   else if (method.equals("completeApplication")) 
        {
        String varNumber = request.getParameter("varAppVarNumber");
          varianceApplication varApp = aDAO.selectAppByVarNum(varNumber);
          varForm.setProperties(varApp);
          session.setAttribute("COMP_APPLICATION",varApp);  
          return mapping.findForward("compApplication");
        }
        else if (method.equals("payFees")) 
        {
          varianceApplication varApp =(varianceApplication)session.getAttribute("VARIANCE_APPLICATION_SELECTED");
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/idhsFeesFines/start.do?method=varianceCompApplication&stateNumber="+varApp.getVarId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
        }
        else if (method.equals("goToUploadComp")) 
        { varianceApplication varApp =(varianceApplication)session.getAttribute("VARIANCE_APPLICATION_SELECTED");
           varApp.setFileList(aDAO.selectFileList(varApp.getVarAppVarNumber(),"Variance")); 
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
        } */
        else if (method.equals("affirmOwner")) 
        {         String ownerKey = request.getParameter("ownerKey");
        int varId = Integer.parseInt(request.getParameter("varKey"));
          varianceApplication varApp = aDAO.selectApp(varId);
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          DfbsOwner owner =oDAO.selectOwner(varApp.getVarProjOwnerId());
          String currentDate =sDAO.selectCurrentDate();
            session.setAttribute("CURRENT_DATE",currentDate);
          String url = request.getRemoteAddr();
          session.setAttribute("CURRENT_IP",url);
          if (owner!=null && owner.getOwnerEmail().toUpperCase().equals(ownerKey.toUpperCase()))
          {session.setAttribute("OWNER_AFFIRMATION_ERROR","N");
          }
          else {
            session.setAttribute("OWNER_AFFIRMATION_ERROR","Y");
          }
         return mapping.findForward("ownerAffirmation");
        }
        else if (method.equals("ownerVerified")||method.equals("designerVerified")) 
        {         int ownerPassword = Integer.parseInt(request.getParameter("varAppSBCNum"));
          varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
          DfbsOwner owner =oDAO.selectOwner(varApp.getVarProjOwnerId());
          varDesigner designer =dDAO.selectDes((varApp.getVarProjId())); 
           if (owner.getOwnerId()==(ownerPassword))
          {session.setAttribute("VAR_AFFIRMATION_ERROR","N");
            sendEmailConfirmAffirmation(varApp,contacts,session,method,owner,designer);
            String currentDate =sDAO.selectCurrentDate();
            String url = request.getRemoteAddr();
            if  (method.equals("ownerVerified")) {
              varApp.setVarAppOwnerSign("Date:"+currentDate+"IP:"+url);
            }
            if  (method.equals("designerVerified")) {
              varApp.setVarAppDesSign("Date:"+currentDate+"IP:"+url);
            }
            aDAO.updateApp(varApp);
          }
          else {
            session.setAttribute("VAR_AFFIRMATION_ERROR","Y");
          }
         return mapping.findForward("affirmationComplete");
        }
        else if (method.equals("affirmDesigner")) 
        {         String desKey = request.getParameter("desKey");
        int varId = Integer.parseInt(request.getParameter("varKey"));
          varianceApplication varApp = aDAO.selectApp(varId);
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
          varDesigner designer =dDAO.selectDes((varApp.getVarProjId())); 
         String currentDate =sDAO.selectCurrentDate();
            session.setAttribute("CURRENT_DATE",currentDate); 
          String url = request.getRemoteAddr();
          session.setAttribute("CURRENT_IP",url);
          if (designer !=null && designer.getDesEmail().toUpperCase().equals(desKey.toUpperCase())) 
          {
              session.setAttribute("DESIGNER_AFFIRMATION_ERROR","N");
          }
          else {
            session.setAttribute("DESIGNER_AFFIRMATION_ERROR","Y");
          }
         return mapping.findForward("designerAffirmation");
        }
        else if (method.equals("updateVarApplication")) 
        {
           return mapping.findForward("updateVarApplication");
        }
        else if (method.equals("updateOptions")) 
        {
           return mapping.findForward("updateOptions");
        }
       
        else if (method.equals("varAppVerified")) 
        {
          String receiptId = request.getParameter("varAppSBCNum");
          String ownerEmail = request.getParameter("varProjAddress1");
          int password = Integer.parseInt(request.getParameter("varProjAddress2"));
          String varNumber = request.getParameter("varProjCity");
          int verifyApp= aDAO.selectVarAppVerify(ownerEmail, varNumber, receiptId, password);
          if (verifyApp >0 ) {
            session.setAttribute("ADD_VARAPP_ERROR","N");
            session.setAttribute("ADD_VARAPP_SECURITY","Y");
            DfbsOwner owner =oDAO.selectOwner(password);
            varianceApplication varApp = aDAO.selectApp(Integer.parseInt(varNumber));
            varDesigner designer =dDAO.selectDes(varApp.getVarProjId());
            session.setAttribute("VARIANCE_OWNER_SELECTED",owner);
            session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
            session.setAttribute("VARIANCE_DESIGNER_SELECTED", designer); 
            return mapping.findForward("updateOptions");
          }else if(verifyApp ==0 ) {
             session.setAttribute("ADD_VARAPP_ERROR","P");
           session.setAttribute("ADD_VARAPP_SECURITY","N");
                return mapping.findForward("updateVarApplication");
          }else{ 
           session.setAttribute("ADD_VARAPP_ERROR","Y");
           session.setAttribute("ADD_VARAPP_SECURITY","N");
            return mapping.findForward("updateVarApplication");
          }
        }
        else if (method.equals("updateLfoEmail")) 
        {
          varianceApplication app = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");   
          varForm.setVarAppLfoEmail(app.getVarAppLfoEmail());
          varForm.setVarAppLboEmail(app.getVarAppLboEmail());
          varForm.setVarAppLfoPhone(app.getVarAppLfoPhone());
          varForm.setVarAppLboPhone(app.getVarAppLboPhone());
          return mapping.findForward("updateLfoEmail");
        }
        else if (method.equals("updateSaveLfoEmail")) 
        {
        String varLfoEmail = varForm.getVarAppLfoEmail();
          String varLboEmail = varForm.getVarAppLboEmail();
          //  String varLboPhone = varForm.getVarAppLboPhone();
          //  String varLfoPhone = varForm.getVarAppLfoPhone();
          varianceApplication app = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
          DfbsOwner owner = (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
          aDAO.updateLfoEmail(varLfoEmail,varLboEmail, app.getVarId());
          app.setVarAppLfoEmail(varLfoEmail);
          app.setVarAppLboEmail(varLboEmail);
        //  app.setVarAppLfoPhone(varLfoPhone);
        //  app.setVarAppLboPhone(varLboPhone);
          sendEmailCheckOutLboLfo(app,contacts,"LFO",varLfoEmail,owner); 
          sendEmailCheckOutLboLfo(app,contacts,"LBO",varLboEmail,owner);
          return mapping.findForward("updateOptions");
        }
        else if (method.equals("updateLfoEmailInternal")) 
        {
          int varId = Integer.parseInt(request.getParameter("varId"));
          varianceApplication varApp = aDAO.selectApp(varId);
           session.setAttribute("VARIANCE_APPLICATION_SELECTED", varApp);  
          DfbsOwner owner =oDAO.selectOwner(varApp.getVarProjOwnerId());
          session.setAttribute("VARIANCE_OWNER_SELECTED",owner);
          varForm.setVarAppLfoEmail(varApp.getVarAppLfoEmail());
          searchResults results = new searchResults();
          if (varApp.getVarAppLboEmail() ==null) {
            aDAO.selectLboDetails(results, varApp.getVarProjCity(), varApp.getVarProjCounty(), varApp.getVarAppFDName(), varApp.getVarAppBDName(), varApp.getVarProjZip());
             varForm.setVarAppLboEmail(results.getResultType());
          }
          else
          {
          varForm.setVarAppLboEmail(varApp.getVarAppLboEmail());
          }
          return mapping.findForward("updateLfoEmail");
        }
       
        else if (method.equals("affirmLFO")||method.equals("affirmLBO")) 
        {        String varKey = request.getParameter("varKey");
        int ownerId = Integer.parseInt(request.getParameter("pkey"));
          varianceApplication varApp = aDAO.selectApp(Integer.parseInt(varKey));
          session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
           if (varApp.getVarProjOwnerId()==(ownerId))
          {session.setAttribute("VAR_AFFIRMATION_ERROR","N");
           if (method.equals("affirmLFO"))
           {
            aDAO.updateLfoLboNotified("LFO",Integer.parseInt(varKey));
           }
            if (method.equals("affirmLBO"))
            {
             aDAO.updateLfoLboNotified("LBO",Integer.parseInt(varKey));
            }
          }
          else {
            session.setAttribute("VAR_AFFIRMATION_ERROR","Y");
          }
         return mapping.findForward("affirmationComplete");
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
   request.setAttribute("VAR_PROJ_OPTIONS",varProjType);
   request.setAttribute("VIO_BY_OPTIONS",vioBy);
   request.setAttribute("YES_NO_OPTIONS",yesNo);
   request.setAttribute("OWNER_STATE_OPTIONS",states);
   request.setAttribute("VAR_STATE_OPTIONS",states2);
   request.setAttribute("VAR_COMM_ACTION_OPTIONS",varCommAction);
   request.setAttribute("VAR_COUNTY_OPTIONS",counties);
 } 
  public void doGetFile( HttpServletResponse response,varianceApplicationDAO aDAO,String fileName,String fileType,int fileId) throws Exception 
  {
   try {response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
    java.io.OutputStream out = response.getOutputStream(); 
    int lenFile = aDAO.downLoad(out,fileId,fileName);
     
     out.flush(); 
    out.close();
   } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
            }
  }
 
  protected static boolean validate(varianceApplication varApp ,varianceApplicationForm errorForm,HttpSession session,String varStatus) 
   {
     boolean noError = true;
     /* submitter*/
     if (varApp.getVarId() == 0 &&varStatus.equals("SUBMITTER"))
     { 
         if(varApp.getVarAppTelephone() == null || varApp.getVarAppTelephone().trim().equals("") ) 
         {
           errorForm.setVarAppTelephone("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setVarAppTelephone("");
         }
       if(varApp.getVarAppFirstName() == null || varApp.getVarAppFirstName().trim().equals("") ) 
       {
         errorForm.setVarAppFirstName("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppFirstName("");
       }
       if(varApp.getVarAppLastName() == null || varApp.getVarAppLastName().trim().equals("") ) 
       {
         errorForm.setVarAppLastName("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppLastName("");
       }
      /* if(varApp.getVarAppFirmName() == null || varApp.getVarAppFirmName().trim().equals("") ) 
       {
         errorForm.setVarAppFirmName("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppFirmName("");
       }*/
       if(varApp.getVarAppAddress1() == null || varApp.getVarAppAddress1().trim().equals("") ) 
       {
         errorForm.setVarAppAddress1("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppAddress1("");
       }
       if(varApp.getVarAppAddress1() == null || varApp.getVarAppAddress1().trim().equals("") ) 
       {
         errorForm.setVarAppAddress1("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppAddress1("");
       }
       if(varApp.getVarAppCity() == null || varApp.getVarAppCity().trim().equals("") ) 
       {
         errorForm.setVarAppCity("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppCity("");
       }
       if(varApp.getVarAppState() == null || varApp.getVarAppState().trim().equals("") ) 
       {
         errorForm.setVarAppState("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppState("");
       }
       if(varApp.getVarAppTelephone() == null || varApp.getVarAppTelephone().trim().equals("") ) 
       {
         errorForm.setVarAppTelephone("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarAppTelephone("");
       }
       if(varApp.getVarAppEmail() == null || varApp.getVarAppEmail().trim().equals("") ||
           varApp.getVarAppEmail().indexOf('@') <=0 || varApp.getVarAppEmail().indexOf('.') <= 0 ) 
        {
          errorForm.setVarAppEmail("ERROR");
          noError = false;
        } 
        else 
        { 
          errorForm.setVarAppEmail("");
        }
     }            
     /* project*/
     if (varApp.getVarId() == 0 &&varStatus.equals("PROJECT"))
     {
       if(varApp.getVarProjName() == null || varApp.getVarProjName().trim().equals("") ) 
       {
         errorForm.setVarProjName("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarProjName("");
       }
       if(varApp.getVarProjCounty() == null || varApp.getVarProjCounty().trim().equals("") ) 
       {
         errorForm.setVarProjCounty("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarProjCounty("");
       }
       if(varApp.getVarProjStatus() == null || varApp.getVarProjStatus().trim().equals("") ) 
       {
         errorForm.setVarProjStatus("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarProjStatus("");
       }
       if(varApp.getVarProjAddress1() == null || varApp.getVarProjAddress1().trim().equals("") ) 
       {
         errorForm.setVarProjAddress1("ERROR");
         noError = false;
       } 
       else 
       {
         errorForm.setVarProjAddress1("");
       }
       if(varApp.getVarProjCity() == null || varApp.getVarProjCity().trim().equals("") ) 
       {
         errorForm.setVarProjCity("ERROR");
         noError = false;
       } 
       else 
       {
          errorForm.setVarProjCity("");
       }
       if(varApp.getVarAppBDName() == null || varApp.getVarAppBDName().trim().equals("") ) 
       {
         errorForm.setVarAppBDName("ERROR");
         noError = false;
       } 
       else 
       {
          errorForm.setVarAppBDName("");
       }
       if(varApp.getVarAppFDName() == null || varApp.getVarAppFDName().trim().equals("") ) 
       {
         errorForm.setVarAppFDName("ERROR");
         noError = false;
       } 
       else 
       {
          errorForm.setVarAppFDName("");
       }
       if(varApp.getVarAppLboName() == null || varApp.getVarAppLboName().trim().equals("") ) 
       {
         errorForm.setVarAppLboName("ERROR");
         noError = false;
       } 
       else 
       {
          errorForm.setVarAppLboName("");
       }
       
     }
     return noError;
   } 

    private static void sendEmailSubmitter(varianceApplication varApp,ApplicationContacts contacts,DfbsOwner owner) throws Exception
    {
            try {
              HsMailer mail = new HsMailer();
              String submitterEmail =varApp.getVarAppEmail();
                String[] emailTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov"};
                if (varApp.getVarAppEmail()!=null)
                {
                String[] emailTo1 = {varApp.getVarAppEmail()};
                  emailTo=emailTo1;
                }
                else
                {
                String[] emailTo1 = {owner.getOwnerEmail()};
                  emailTo=emailTo1;
                }
               String[] bccTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov","DfbsDatabase@dhs.in.gov"};
              StringBuffer sb = new StringBuffer("\r Variance Online Application additional information request ");
              sb.append(" Thank you for using IDHS online variance application." );
              sb.append("During our application review we found the following issues with your application for project "+varApp.getVarProjName()+".");
              sb.append("\n\r " +varApp.getVarAppHistAffPhyComment());
                sb.append("\n\r If you have questions or concerns on this email contact "+contacts.getContact2Email());
               StringBuffer sub = new StringBuffer();
              sub.append(" Variance Online Application("+varApp.getVarId()+") additional information request. ");
            
                mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                mail.sendAndClose();
               
              } 
              catch(Exception e) 
              {
              HsMailer mail1 = new HsMailer();
               String lboEmail =varApp.getVarAppLboEmail();
               String lfoEmail =varApp.getVarAppLfoEmail();
              mail1.createMail("magazine_online@dhs.in.gov","dfitzpatrick@dhs.in.gov"," Variance Application email submitter error  ",lfoEmail +":owner:contact:"+lboEmail+":dhs:"+contacts.getContact1Email());
              mail1.sendAndClose();    
             }
    }
    
    private static void sendEmailConfirmAffirmation(varianceApplication varApp,ApplicationContacts contacts,HttpSession session,
                                                    String method,DfbsOwner owner,varDesigner des) throws Exception
    {
            try {
              HsMailer mail = new HsMailer();
              
                String verification="";
                String recEmail="";
                if  (method.equals("ownerVerified")) {
                       verification="Owner"  ;
                   recEmail=owner.getOwnerEmail();
                       }
                if  (method.equals("designerVerified")) {
                       verification="Designer"  ;
                      recEmail=des.getDesEmail();
                       }
              
                String[] bccTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov",contacts.getContact1Email(),"DfbsDatabase@dhs.in.gov"};
                String[] emailTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov",recEmail};
               
              StringBuffer sb = new StringBuffer("The Commission received a variance affirmation from "+verification+" for variance id: "+varApp.getVarId()+".");
              sb.append(" Variance online application reference id is: "+varApp.getVarId()+". If you have any questions or concerns about this process");
              sb.append(" contact "+contacts.getContact2Email()+".");
              sb.append("\n \n You can check status of your application(s) at https://oas.dhs.in.gov/dfbs/variance/start.do");
              StringBuffer sub = new StringBuffer();
              sub.append("Online Variance Application("+varApp.getVarId()+") affirmation received from "+verification+ " for project: "+varApp.getVarProjName());
            
                mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                mail.sendAndClose();
               
              } 
              catch(Exception e) 
              {
              HsMailer mail1 = new HsMailer();
              mail1.createMail("variance_online@dhs.in.gov","dfitzpatrick@dhs.in.gov"," Variance Online Transaction error  ",des.getDesEmail() +":owner:dhs:"+contacts.getContact1Email());
              mail1.sendAndClose();    
             }
    }
    private static void sendEmailCheckOut(varianceApplication varApp,ApplicationContacts contacts,HttpSession session,List emailList) throws Exception
    {
            try {
                HsMailer mail = new HsMailer();
                  Iterator i = emailList.iterator();
                String[] bccToPool ={"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov"};
                  int j=0;
                  while(i.hasNext())
                  { 
                    String emailContact = (String) i.next();
                    if (j==0) {
                      if (varApp.getVarAppEmail() !=null)
                      {
                      String[]  bccTo1 ={"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov","DfbsDatabase@dhs.in.gov",varApp.getVarAppEmail()};
                        bccToPool=bccTo1;
                      }
                     else
                      {
                      String[]  bccTo2 ={"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov","DfbsDatabase@dhs.in.gov"};
                      bccToPool=bccTo2;
                      }
                    }
                    else {
                        String[]  bccTo3 ={"DfbsDatabase@dhs.in.gov"};
                      bccToPool=bccTo3;
                    }
               
                  String[] emailTo  = {emailContact};
              StringBuffer sb = new StringBuffer("Online Variance Application has been received for project:"+varApp.getVarProjName()+".");
              sb.append("\n This is inform you that Variance Commission has received a variance application. ");
              sb.append("The online application reference id is: "+varApp.getVarId()+". This variance for the project is filed in your county jurisdiction. If you have any questions or concerns about this process ");
                sb.append(" contact "+contacts.getContact2Email());
                sb.append("\n To view the variance information submitted in this application click the link provided below.(USE IE Browser)");
                sb.append("\n \n https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
                    sb.append("\n \n  If link above does not work use the pdf version below.");
                    sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
                sb.append("\n \n To Search,view or Print all variance information click the link provided below.(USE IE Browser)");
                 sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
                
              StringBuffer sub = new StringBuffer();
              sub.append(" Online Variance Application("+varApp.getVarId()+") has been received  ");
            
              //  mail.createMail("variance_online@dhs.in.gov",emailTo,bccToPool,sub.toString(),sb.toString());
              //  mail.sendAndClose();
                    j=j+1;
                  }
              } 
              catch(Exception e) 
              {
              
              HsMailer mail1 = new HsMailer();
               
             mail1.createMail("variance_online@dhs.in.gov","DfbsDatabase@dhs.in.gov"," Variance Online Transaction error  ",":owner:contact:dhs:");
              mail1.sendAndClose();    
             }
    }
    private static void sendEmailCheckOutLboLfo(varianceApplication varApp,ApplicationContacts contacts,String lboLfo,String emailContact,DfbsOwner owner) throws Exception
    {
            try {
                HsMailer mail = new HsMailer();
               
                
                String[] emailTo  = {emailContact};
             // String[] emailTo  = {"DfbsDatabase@dhs.in.gov"};
                String[] bccTo  = {contacts.getContact1Email(),contacts.getContact2Email(),"DfbsDatabase@dhs.in.gov"};
                StringBuffer sb = new StringBuffer("Online Variance Application has been received for project:"+varApp.getVarProjName()+".");
                sb.append("\n This is inform you that Variance Commission has received a variance application. ");
                sb.append("The online application reference id is: "+varApp.getVarId()+". This variance for the project is filed under the name of the county for your jurisdiction. If you have any questions or concerns about this process");
                sb.append(" contact "+contacts.getContact2Email());
                sb.append("\n \n To view the variance information submitted in this application click the link provided below.(USE IE Browser)");
                sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
                sb.append("\n \n  If link above does not work use the pdf version below.");
                sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
                sb.append("\n \n To Search,view or Print all variance information click the link provided below.(USE IE Browser)");
                sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
                sb.append("\n \n Please Click the link below to confirm, that you have received this variance notification.");
                sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirm"+lboLfo+"&varKey="+varApp.getVarId()+"&pkey="+owner.getOwnerId()).append("\n\r");
             //   sb.append("\n \n If you are not the correct LFO for this variance, can you  a) forward this email to the correct LFO OR b) Click the link below to notify this error to IDHS.");
              //  sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=error"+lboLfo+"&varKey="+varApp.getVarId()+"&pkey="+owner.getOwnerId()).append("\n\r");
                StringBuffer sub = new StringBuffer();
                sub.append(" Online Variance Application("+varApp.getVarId()+") has been received for "+lboLfo);
            
                mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                mail.sendAndClose(); 
              } 
              catch(Exception e) 
              {
              
              HsMailer mail1 = new HsMailer();
               
             mail1.createMail("variance_online@dhs.in.gov","DfbsDatabase@dhs.in.gov"," Variance lbo lfo email error  ",lboLfo+emailContact+ " contact DBA ");
              mail1.sendAndClose();    
             }
    }
  }

  /*  else if (method.equals("preApplication")) 
    {   varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
        if (varApp!=null) {
          varForm.setProperties(varApp);
        }
        setOptions(request,dfbsUtilityDAO);
      session.setAttribute("NEW_VARIANCE_STATUS","COUNTY");
     return mapping.findForward("varApplication");
    } 
    else if (method.equals("nextApplication")) 
    {    setOptions(request,dfbsUtilityDAO);
      varianceApplication varApp = varForm.getVarianceApplication();
      varianceApplicationForm errorForm = new varianceApplicationForm();
      String varStatus = (String) session.getAttribute("NEW_VARIANCE_STATUS");
      if (validate(varApp,errorForm,session,varStatus) )
      {
      session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
      if (varApp.getVarAppSBCNum() >0) {
        DfbsOwner newOwner =new DfbsOwner();
        varDesigner newDes =new varDesigner();
        aDAO.selectPlanData(varApp.getVarAppSBCNum(), newOwner, newDes, varApp);
       session.setAttribute("VARIANCE_OWNER_SELECTED", newOwner); 
        session.setAttribute("VARIANCE_DESIGNER_SELECTED", newDes); 
      }
      StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
      redirectUrl.append(request.getContextPath()).append("/variance/owner.do?method=addNewOwner");
      response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;
      }
      else {
        varForm.setProperties(varApp);
        request.setAttribute("VAR_APPLICATION_ERROR",errorForm);
        return mapping.findForward("varApplication");
      } 
    }
    else if (method.equals("fromOwnerApplication")) 
    {    setOptions(request,dfbsUtilityDAO);
      varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
      varForm.setProperties(varApp);
      session.setAttribute("NEW_VARIANCE_STATUS","AFTER_OWNER"); 
      return mapping.findForward("varApplication");
    }
    else if (method.equals("savePreDesApplication")) 
    {      
      varianceApplication varApp = varForm.getVarianceApplication();
      session.setAttribute("VARIANCE_APPLICATION_SELECTED",varApp);
      return mapping.findForward("desQuestion");
    }*/
  /*  else if (method.equals("compCodeDetail")) 
    {  
      String appAction = request.getParameter("completeApplication");
      setOptions(request,dfbsUtilityDAO); 
      varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED"); 
      varApp.setVarAppHistAffExc(varForm.getVarAppHistAffExc());
      varApp.setVarAppHistAffHist(varForm.getVarAppHistAffHist());
      varApp.setVarAppHistAffMaj(varForm.getVarAppHistAffMaj());
      varApp.setVarAppHistAffPhy(varForm.getVarAppHistAffPhy());
      varApp.setVarAppHistAffPhyComment(varForm.getVarAppHistAffPhyComment());
      varApp.setVarAppPhAffirm(varForm.getVarAppPhAffirm());
      varApp.setVarAppPhAffirmComment(varForm.getVarAppPhAffirmComment()); 
      varApp.setVarAppNumCode(varApp.getCodeMapCount());
      varApp.setAmount(aDAO.selectFeesMapping(varApp.getCodeMapCount()-1));
      varApp.setVarProjState("IN");
      if (appAction.equals("Complete Application"))
      {     if (varApp.getCodeMapCount() >0)
            {
          session.setAttribute("NEW_VARIANCE_STATUS","AFTER_CODE"); 
            session.setAttribute("NEW_VARIANCE_CODE_COUNT","Y"); 
          String currentDate =sDAO.selectCurrentDate();
            session.setAttribute("CURRENT_DATE",currentDate);
          String url = request.getRemoteAddr();
          session.setAttribute("CURRENT_IP",url);
          return mapping.findForward("affirmation");
          }
          else {
            varForm.setProperties(varApp); 
            session.setAttribute("NEW_VARIANCE_CODE_COUNT","N"); 
            return mapping.findForward("varCodeDetail");
          }
      }
      else if (appAction.equals("Add New Code")){
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
    } */
  /*  if (varApp.getVarId() == 0 &&varStatus.equals("COUNTY"))
   {
    
     if(varApp.getVarAppLfoName() == null || varApp.getVarAppLfoName().trim().equals("") ) 
     {
       errorForm.setVarAppLfoName("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarAppLfoName("");
     }
     if(varApp.getVarAppLfoEmail() == null || varApp.getVarAppLfoEmail().trim().equals("") ) 
     {
       errorForm.setVarAppLfoEmail("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarAppLfoEmail("");
     }
     if(varApp.getVarAppLboName() == null || varApp.getVarAppLboName().trim().equals("") ) 
     {
       errorForm.setVarAppLboName("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarAppLboName("");
     }
     if(varApp.getVarAppLboEmail() == null || varApp.getVarAppLboEmail().trim().equals("") ) 
     {
       errorForm.setVarAppLboEmail("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarAppLboEmail("");
     }
     if(varApp.getVarProjCounty() == null || varApp.getVarProjCounty().trim().equals("") ) 
     {
       errorForm.setVarProjCounty("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarProjCounty("");
     }
     if(varApp.getVarAppLboNotified() == null || varApp.getVarAppLboNotified().trim().equals("") ) 
     {
       errorForm.setVarAppLboNotified("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarAppLboNotified("");
     }
     if(varApp.getVarAppLfoNotified() == null || varApp.getVarAppLfoNotified().trim().equals("") ) 
     {
       errorForm.setVarAppLfoNotified("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setVarAppLfoNotified("");
     }
   } */
