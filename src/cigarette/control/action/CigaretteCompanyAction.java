package cigarette.control.action;

import cigarette.control.form.*;
import cigarette.to.*;
import cigarette.data.*;
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
import util.logging.Log;

public class CigaretteCompanyAction extends ControlAction
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
        
        
        CigaretteCompanyForm companyForm = (CigaretteCompanyForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CigaretteCompanyDAO cDAO = (CigaretteCompanyDAO) dmap2.getHsDAO(DfbsDataMap.CIG_COMPANY);
        CigaretteUsersDAO uDAO = (CigaretteUsersDAO) dmap2.getHsDAO(DfbsDataMap.CIG_USER);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          ApplicationContacts contacts = sDAO.setContacts("CIGARETTE_CONTACT1","CIGARETTE_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts); 
         if (method == null || method.equals("")) 
        { 
          String conType ="CIGARETTE_CONTACT";
          session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
          String appLocation ="/dfbs/cigarette/cigarette.do";
          session.setAttribute("APPLICATION_LOCATION",appLocation); 
          String appHeading ="FSC Cigarette Certification Version 2";
          session.setAttribute("APPLICATION_HEADING",appHeading); 
             
          session.setAttribute("CIGARETTE_COMPANY",new CigaretteCompany());
          session.setAttribute("CIGARETTE_APPLICATION",null);
          session.setAttribute("CIGARETTE_BRAND",null);
          session.setAttribute("COMPANY_TYPE",null);
          session.setAttribute("UNIQUE_BRANDS",null);
           FileNames names= sDAO.selectAppStatus("CIG_PERMITS_MAINT_FLAG");
            session.setAttribute("CIG_PERMITS_APP_STATUS",names.getFileName());
            session.setAttribute("CIG_PERMITS_APP_MESSAGE",names.getFileType());
           return mapping.findForward("cigaretteApp");
        
        } 
        if (method.equals("startOver")) 
        {  session.setAttribute("APPLICATION_TYPE",null);
          session.setAttribute("CIGARETTE_COMPANY",new CigaretteCompany());
          session.setAttribute("CIGARETTE_APPLICATION",null);
          session.setAttribute("CIGARETTE_BRAND",null);
          session.setAttribute("COMPANY_TYPE",null);
           session.setAttribute("UNIQUE_BRANDS",null);
          return mapping.findForward("startOver");
        } 
        if (method.equals("cigaretteApp")) 
        {
          return mapping.findForward("cigaretteApp");
        } 
        
        
        else if (method.equals("addCompany")) 
        { 
          
         //  setOptions(request,dfbsUtilityDAO);
         // return mapping.findForward("addCompany");
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=addSupApp");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
      
        
         else if (method.equals("newAppType")) 
        { 
           
           String appType="Initial";
           session.setAttribute("APPLICATION_TYPE",appType);
          return mapping.findForward("cigaretteApp");
        } 
         else if (method.equals("supAppType")) 
        { 
           String appType="Supplemental";
           session.setAttribute("APPLICATION_TYPE",appType);
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=addSupApp");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
          else if (method.equals("3yearRenewal")) 
        {

           String appType="3YearRenewal";
           session.setAttribute("APPLICATION_TYPE",appType);
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=addSupApp");
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
        else if (method.equals("editCompany")) 
        {
          CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
          CigaretteAgent agent = (CigaretteAgent) session.getAttribute("CIGARETTE_AGENT");
           companyForm.setProperties(company);
           companyForm.setAgentProperties(agent);
          return mapping.findForward("updateCompanyAgent");
        } 
       
         else if (method.equals("backToCigaretteApp")) 
        {
           return mapping.findForward("cigaretteApp");
        } 
         else if(method.equals("saveChangeCompany")) 
        {  setOptions(request,dfbsUtilityDAO);
           CigaretteCompany company = companyForm.getCigaretteCompany();
           if(company.getCompanyId() ==0)
           {
            cDAO.insertCompany(company);
           }
           else
           {
              cDAO.updateChangeCompany(company);
           }
             
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=addSupApp");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
  
        }  
         else if(method.equals("saveChangeAgent")) 
        {  setOptions(request,dfbsUtilityDAO);
           CigaretteAgent agent = companyForm.getCigaretteAgent();
           if(agent.getAgentId() ==0)
           { CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
             cDAO.insertAgent(agent,company);
           }
           else
           {
              cDAO.updateChangeAgent(agent);
           }
             
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=addSupApp");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
 
        }  
         else if(method.equals("updateCompany")) 
        { 
           CigaretteCompany company = companyForm.getCigaretteCompany();
           CigaretteAgent agent = companyForm.getCigaretteAgent();
           CigaretteUsers cigUser = (CigaretteUsers) session.getAttribute("CIGARETTE_USER");
           if( cigUser ==null)
           {
              companyForm.setProperties(company);
              companyForm.setAgentProperties(agent);
               session.setAttribute("CIGARETTE_COMPANY",company);
               session.setAttribute("CIGARETTE_AGENT",agent);
              
           }
           else {
             cDAO.updateChangeAgent(agent);
              cDAO.updateChangeCompany(company);
                }
           return mapping.findForward("cigaretteApp");
        }  
        else if(method.equals("saveCompany")) 
        {  
           CigaretteCompany updatedCompany = companyForm.getCigaretteCompany();
           CigaretteCompanyForm errorForm = new CigaretteCompanyForm();
           if (validate(updatedCompany,errorForm) ) {
          
              CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
              companyForm.setUpdatedProperties(updatedCompany,company);
               return mapping.findForward("cigaretteApp");
           } 
           else 
           {  setOptions(request,dfbsUtilityDAO);
             request.setAttribute("CIGARETTE_COMPANY_ERROR",errorForm);
             return mapping.findForward("changeCompany");
           }
        }  
         else if (method.equals("addSupApp")) 
        {
       /*  changed to go to login screen */
         int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany company = cDAO.selectCompany(companyId);
          CigaretteAgent agent = cDAO.selectAgent(companyId);
          session.setAttribute("CIGARETTE_COMPANY",company);
          session.setAttribute("CIGARETTE_AGENT",agent);
          company.setUsers(uDAO.selectUserList(company.getCompanyId()));
          int appCount =cDAO.selectCompanyAppCount(companyId);
          if (appCount >0)
          {
             return mapping.findForward("loginUser");
          }
          else
          {session.setAttribute("APPLICATION_ERROR","You can not add supplemental application, add intial application");
             return mapping.findForward("errorPage");
          }
         /*   companyForm.setProperties(company);
          companyForm.setAgentProperties(agent);
        return mapping.findForward("updateCompanyAgent"); 
          return mapping.findForward("loginUser");*/
        } 
        
         else if (method.equals("add3YearRenewal")) 
        {
       /*  changed to go to login screen */
         int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany company = cDAO.selectCompany(companyId);
          CigaretteAgent agent = cDAO.selectAgent(companyId);
          session.setAttribute("CIGARETTE_COMPANY",company);
          session.setAttribute("CIGARETTE_AGENT",agent);
          company.setUsers(uDAO.selectUserList(company.getCompanyId()));
          int appCount =cDAO.selectCompanyAppCount(companyId);
          if (appCount >0)
          {
             return mapping.findForward("loginUser");
          }
          else
          {session.setAttribute("APPLICATION_ERROR","You can not add 3 year renewal application, add intial application");
             return mapping.findForward("errorPage");
          }
         
        } 
         else if (method.equals("addInitial")) 
        {
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany company = cDAO.selectCompany(companyId);
          CigaretteAgent agent = cDAO.selectAgent(companyId);
          session.setAttribute("CIGARETTE_COMPANY",company);
          session.setAttribute("CIGARETTE_AGENT",agent);
          companyForm.setProperties(company);
          companyForm.setAgentProperties(agent);
           int appCount =cDAO.selectCompanyAppCount(companyId);
          if (appCount == 0)
          {
             return mapping.findForward("updateCompanyAgent");
          }
          else
          {  session.setAttribute("APPLICATION_ERROR","You can not add another initial application, add supplemental application");
             return mapping.findForward("errorPage");
          }
          
        } 
         else if (method.equals("changeCompany")) 
        { 
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany company = cDAO.selectCompany(companyId);
          CigaretteAgent agent = cDAO.selectAgent(companyId);
          session.setAttribute("CIGARETTE_COMPANY",company);
          session.setAttribute("CIGARETTE_AGENT",agent);
          companyForm.setProperties(company);
          companyForm.setAgentProperties(agent);
          return mapping.findForward("changeCompany");
        }
         else if (method.equals("addNewCompany")) 
        {
          
          return mapping.findForward("changeCompany");
        }
        
         else if (method.equals("changeAgent")) 
        {
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany company = cDAO.selectCompany(companyId);
          CigaretteAgent agent = cDAO.selectAgent(companyId);
          session.setAttribute("CIGARETTE_COMPANY",company);
          session.setAttribute("CIGARETTE_AGENT",agent);
          companyForm.setProperties(company);
          companyForm.setAgentProperties(agent);
          return mapping.findForward("changeAgent");
        } 
        else if (method.equals("sendEmail")) 
        {
          sendEmail(request,contacts);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          return mapping.findForward("sendEmailForm");
        }
        else if (method.equals("sendEmailForm")) 
        {
          return mapping.findForward("sendEmailForm");
        }
       else if (method.equals("printApplication")) 
        {CigaretteApplication application = (CigaretteApplication) session.getAttribute("CIGARETTE_APPLICATION");
          String redirectUrl = "https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_application.rdf&p_application_id=" + Integer.toString(application.getAppId())+"&target=_blank";
          response.sendRedirect(response.encodeRedirectURL(redirectUrl));
          return null;
 
        }
           else if (method.equals("logOn")) 
        {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/main/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        }
         else if (method.equals("updateCompanyAgentPhones")) 
        {
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany company = cDAO.selectCompany(companyId);
          CigaretteAgent agent = cDAO.selectAgent(companyId);
          session.setAttribute("CIGARETTE_COMPANY",company);
          session.setAttribute("CIGARETTE_AGENT",agent);
          companyForm.setProperties(company);
          companyForm.setAgentProperties(agent);
        
             return mapping.findForward("updateCompanyAgent");
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
 

 private void sendEmail(HttpServletRequest request,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
           String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
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
            sub.append(" From Cigarette Permit Online  - ").append(subject);
          
          
          
            mail.createMail("cigarettte_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From error email ","email information");
            mail1.sendAndClose();
            }
  }
  
  protected static boolean validate(CigaretteCompany company,CigaretteCompanyForm errorForm) throws Exception
  {
    boolean noError = true;
   
   
    if(company.getCompanyName() == null || company.getCompanyName().trim().equals("")  ) 
    { 
      errorForm.setCompanyName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyName("");
    }
    if(company.getCompanyAddress1() == null || company.getCompanyAddress1().trim().equals("") ) 
    { 
      errorForm.setCompanyAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyAddress1("");
    }
     if(company.getCompanyCity() == null || company.getCompanyCity().trim().equals("") ) 
    { 
      errorForm.setCompanyCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyCity("");
    }
   
    
    if(company.getCompanyState() == null || company.getCompanyState().trim().equals("00") ) 
    { 
      errorForm.setCompanyState("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setCompanyState("");
    }
     
    if(company.getCompanyZip() == null || company.getCompanyZip().trim().equals("") ) 
    { 
      errorForm.setCompanyZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyZip("");
    }
    
    if ((company.getCompanyPhone() == null || company.getCompanyPhone().trim().equals("") ||
       company.getCompanyPhone().indexOf('-') > 0 || company.getCompanyPhone().indexOf(')') > 0 ||
       company.getCompanyPhone().indexOf('(') > 0) )
    { 
      errorForm.setCompanyPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyPhone("");
    } 
    
    if ((company.getCompanyFax().indexOf('-') > 0 || company.getCompanyFax().indexOf(')') > 0 ||
       company.getCompanyFax().indexOf('(') > 0))
    { 
      errorForm.setCompanyFax("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyFax("");
    }
    return noError;
  }
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(CigarettePermitSQL.SELECT_STATE_OPTIONS);
    request.setAttribute("COMPANY_STATE_OPTIONS",states);
     List companies = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_COMPANY_OPTIONS);
    request.setAttribute("CIG_COMPANY_OPTIONS",companies);
 
   
 }
  
 
 
 
}
