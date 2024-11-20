package lepc.control.action;
import lepc.control.form.*;
import lepc.to.*;
import lepc.data.*;
import main.data.*;
import main.to.*;
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

import org.apache.struts.upload.FormFile;

import otherUsers.to.otherUsers;
public class fiscalReportAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      fiscalReportForm fiscalForm = (fiscalReportForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
      fiscalReportDAO fDAO = (fiscalReportDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_FISCAL_REPORT);
      String method = request.getParameter("method");
      HttpSession session = request.getSession();
      ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
      ApplicationContacts contacts = sDAO.setContacts("LEPC_CONTACT1","LEPC_CONTACT2");      
      otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");   
        if (method == null ||((otherUser ==null || otherUser.getUserLoginId().length() < 3 )&&
        (security ==null || security.getUserId().length() < 3)))
      {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
     
     
      else if (method.equals("addFiscalReport")) 
      {    
            fiscalReport checkBalance = new fiscalReport();
            session.setAttribute("FISCAL_CURRENT_ACCOUNT_BALANCE",null); 
            session.setAttribute("FISCAL_CURRENT_ACCOUNT_EXPENDITURE",null);
            session.setAttribute("LEPC_FISCAL_EXP_LIST",null);
            String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
            LepcYear lYear = lDAO.selectLepcYear(Integer.parseInt(lepcId));
            fDAO.FindFiscalBalance(checkBalance, lYear.getLepcYearString(),lYear.getLepcCounty());
            if (checkBalance.getAcctBalance()==0 && checkBalance.getReportBy().equals("Y")) {
              session.setAttribute("FISCAL_ACCOUNT_BALANCE_FLAG","N");
            }
            else {
              fiscalForm.setAcctBalance(checkBalance.getAcctBalance());
              session.setAttribute("LEPC_FISCAL_REPORT",checkBalance); 
              session.setAttribute("FISCAL_ACCOUNT_BALANCE",checkBalance.getAcctBalance());
              session.setAttribute("FISCAL_ACCOUNT_BALANCE_FLAG","Y");
            }
            return mapping.findForward("fiscalReport"); 
      }   
      else if (method.equals("saveFiscalReport")) 
      { 
        fiscalReport fisReport= fiscalForm.getFiscalReport();
          String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID");
          fisReport.setLepcId(Integer.parseInt(lepcId));
          String reportUser = "";
          if (otherUser==null) {
                 reportUser = security.getCurrentUser()   ;        }
          else {
            reportUser =otherUser.getUserLoginId();
          }
          if (fisReport.getFiscalId()== 0) {
            fDAO.insertFiscalReport(fisReport,reportUser);
            session.setAttribute("LEPC_FISCAL_REPORT",fisReport);
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/lepc/fiscalReport.do?method=updateFiscalReport&fiscalId="+fisReport.getFiscalId());
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null; 
          }
          else {
            fDAO.updateFiscalReport(fisReport);
          }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/lepc.do?method=viewLepcYear&lepcId="+lepcId);
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null; 
      }   
      else if (method.equals("updateFiscalReport")) 
      {   String fiscalId =request.getParameter("fiscalId");
        session.setAttribute("FISCAL_CURRENT_ACCOUNT_BALANCE",null); 
        session.setAttribute("FISCAL_CURRENT_ACCOUNT_EXPENDITURE",null);
        session.setAttribute("LEPC_FISCAL_EXP_LIST",null);
        fiscalReport fisReport= fDAO.selectFiscalReport(Integer.parseInt(fiscalId));
        fiscalForm.setProperties(fisReport);
         session.setAttribute("LEPC_FISCAL_REPORT",fisReport);
        String lepcId =(String) session.getAttribute("LEPC_SELECTED_ID"); 
        LepcYear lYear = lDAO.selectLepcYear(Integer.parseInt(lepcId));
        List expList = fDAO.selectExpenditureList(Integer.parseInt(fiscalId)); 
        session.setAttribute("LEPC_FISCAL_EXP_LIST",expList);
        fiscalReport checkBalance = new fiscalReport();
        fiscalReport currentBalance = new fiscalReport();
        fDAO.FindFiscalBalance(checkBalance, lYear.getLepcYearString(),lYear.getLepcCounty()); 
        double currentExpenditure = fDAO.FindFiscalBalanceCurrentYear(currentBalance, fisReport.getFiscalId()); 
        session.setAttribute("FISCAL_CURRENT_ACCOUNT_BALANCE",currentBalance.getAcctBalance()); 
        session.setAttribute("FISCAL_CURRENT_ACCOUNT_EXPENDITURE",currentExpenditure);
        if (checkBalance.getAcctBalance()==0 && checkBalance.getReportBy().equals("Y")) {
          session.setAttribute("FISCAL_ACCOUNT_BALANCE_FLAG","N");
        }
        else {
          fiscalForm.setAcctBalance(checkBalance.getAcctBalance());
          session.setAttribute("FISCAL_ACCOUNT_BALANCE",checkBalance.getAcctBalance());
          session.setAttribute("FISCAL_ACCOUNT_BALANCE_FLAG","Y");
        }
        return mapping.findForward("fiscalReport");
      }
          
      
      else if (method.equals("addDisbursement")) 
      {    
            fiscalReport expenditure = new fiscalReport();
            fiscalReport fisReport =(fiscalReport) session.getAttribute("LEPC_FISCAL_REPORT");
            fiscalForm.setFiscalId(fisReport.getFiscalId());
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("expenditure"); 
      }   
      else if (method.equals("updateDisbursement")) 
      {    
            String expId =request.getParameter("expId");
            fiscalReport expenditure = fDAO.selectFiscalExpenditure(Integer.parseInt(expId));
            fiscalForm.setProperties(expenditure);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("expenditure"); 
      }   
      else if (method.equals("saveDisbursement")) 
      {    
            fiscalReport expenditure = fiscalForm.getFiscalReport();
            fiscalReport fisReport =(fiscalReport) session.getAttribute("LEPC_FISCAL_REPORT");
            if (expenditure.getLepcId()==0)
             
            {   expenditure.setFiscalId(fisReport.getFiscalId());
                fDAO.insertFiscalExpenditure(expenditure);
            }
            else {
              fDAO.updateFiscalExpenditure(expenditure);
            }
            
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/lepc/fiscalReport.do?method=updateFiscalReport&fiscalId="+expenditure.getFiscalId());
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null;
      }   
      throw new Exception("LEPC Start Action"); 
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
   List planTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_DOC_TYPE_LIST);
   request.setAttribute("LEPC_DOC_TYPE_OPTIONS",planTypes);
    List expTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_EXPENDITURE_TYPE_LIST );
    request.setAttribute("LEPC_EXPENDITURE_TYPE_OPTIONS",expTypes);
  }
  // end
}