package cigarette.control.action;

import cigarette.control.form.*;
import cigarette.to.*;
import cigarette.data.*;

import code.to.CodeFacility;

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

public class CigaretteApplicationAction  extends ControlAction

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
        
        
        CigaretteApplicationForm applicationForm = (CigaretteApplicationForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CigaretteApplicationDAO aDAO = (CigaretteApplicationDAO) dmap2.getHsDAO(DfbsDataMap.CIG_APPLICATION);
        CigaretteCompanyDAO cDAO = (CigaretteCompanyDAO) dmap2.getHsDAO(DfbsDataMap.CIG_COMPANY);
         CigaretteBrandDAO bDAO = (CigaretteBrandDAO) dmap2.getHsDAO(DfbsDataMap.CIG_BRAND);
         
          
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          ApplicationContacts contacts = sDAO.setContacts("CIGARETTE_CONTACT1","CIGARETTE_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
        if (method == null ) 
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("application");
        } 
        
         else if(method.equals("saveApplication")) 
        {
           CigaretteApplication application = applicationForm.getCigaretteApplication();
           
           CigaretteApplicationForm errorForm = new CigaretteApplicationForm();
           if (validate(application,errorForm) ) 
             {
             CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
             String appType= (String) session.getAttribute("APPLICATION_TYPE");
             application.setAppType(appType);
              company.addApplication(application);
             
                 if (appType !=null && appType.equals("3YearRenewal"))
                 {
                 List brandStyleList = bDAO.SelectBrandStyleList3Year(Integer.toString(company.getCompanyId()));
                 session.setAttribute("CIGARETTE_BRAND_RENEWAL_LIST", brandStyleList);
                  session.setAttribute("CIGARETTE_RENEW_APPLICATION",application);
                   return mapping.findForward("3yearRenewal");
                 }
                 else { session.setAttribute("CIGARETTE_APPLICATION",application);
                     return mapping.findForward("cigaretteApp");
                 }
             } 
             else 
             { 
             setOptions(request,dfbsUtilityDAO);
               request.setAttribute("CIGARETTE_APPLICATION_ERROR",errorForm);
               return mapping.findForward("addApplication");
             }
        }  
        
         else if(method.equals("saveEditedApplication")) 
        {
           CigaretteApplication updatedApplication = applicationForm.getCigaretteApplication();
           
           CigaretteApplicationForm errorForm = new CigaretteApplicationForm();
           if (validate(updatedApplication,errorForm) ) 
             {
              CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
              CigaretteApplication application = (CigaretteApplication) session.getAttribute("CIGARETTE_EDIT_APPLICATION");
               applicationForm.setUpdatedProperties(updatedApplication,application);
              session.setAttribute("CIGARETTE_APPLICATION",application);
               session.setAttribute("CIGARETTE_EDIT_APPLICATION",null);
               return mapping.findForward("cigaretteApp");
             } 
             else 
             {
               request.setAttribute("CIGARETTE_APPLICATION_ERROR",errorForm);
               return mapping.findForward("editApplication");
             }
        }  
        else if (method.equals("addApplication")) 
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("addApplication");
        } 
        
        else if (method.equals("editApplication")) 
        {
          String key = request.getParameter("key");
          CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
          CigaretteApplication application = company.getApplication(key);
          session.setAttribute("CIGARETTE_EDIT_APPLICATION",application);
          applicationForm.setProperties(application);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editApplication");
        } 
         else if (method.equals("approveApplication")) 
        { 
          String appId = request.getParameter("appId");
          CigaretteApplication application = aDAO.selectApplication(appId);
          session.setAttribute("CIGARETTE_APPROVE_APPLICATION",application);
          application.setAppFees(cDAO.selectPermitFees(Integer.toString(application.getAppId())));
          int feePending =cDAO.selectFeesPending(Integer.toString(application.getAppId()));
          List brandExpList = cDAO.selectBrandsExpDateList(application.getCompanyId(),application.getAppId());
          request.setAttribute("BRAND_EXP_DATE_LIST",brandExpList);
          application.setFeesPending(feePending);
          applicationForm.setProperties(application);
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("approveApplication");
        } 
        else if (method.equals("removeApplication")) 
        { 
          String key = request.getParameter("key");
           CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
           CigaretteApplication application = company.getApplication(key);
          company.removeApplication(key);
           bDAO.computeFees(company);
           session.setAttribute("CIGARETTE_APPLICATION",null);
          return mapping.findForward("cigaretteApp");
        }     
        
        else if(method.equals("updateApplication")) 
        {
           CigaretteApplication updatedApplication = applicationForm.getCigaretteApplication();
           
           CigaretteApplicationForm errorForm = new CigaretteApplicationForm();
              aDAO.updateApplication(updatedApplication);
              if (updatedApplication.getAppStatus().equals("Approved"))
              {
              sendEmailApproval(updatedApplication.getEmail(),session,updatedApplication,contacts);
              }
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }  
        else if (method.equals("add3YearRenewal")) 
        {CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
         int appId= aDAO.selectMaxAppId(company.getCompanyId());
          CigaretteApplication application = aDAO.selectApplication(Integer.toString(appId));
          applicationForm.setProperties(application);
          applicationForm.setAppId(0) ;
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("addApplication");
        } 
        
       else if(method.equals("renewAll")) {
                session.setAttribute("CIGARETTE_RENEW_ALL","Y");
                     return mapping.findForward("3yearRenewal");
                  }
         else if(method.equals("addBrandsFor3YearRenewal")) { 
            session.setAttribute("CIGARETTE_RENEW_ALL","");
            CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
            CigaretteApplication renewApplication = (CigaretteApplication) session.getAttribute("CIGARETTE_RENEW_APPLICATION");
            CigaretteApplication application = company.getApplication(renewApplication.getApplicationKey());
            session.setAttribute("CIGARETTE_APPLICATION",application);
            List brandStyleList=(List) session.getAttribute("CIGARETTE_BRAND_RENEWAL_LIST");
            Iterator i = brandStyleList.iterator();
            while(i.hasNext())
            {
              CigaretteBrand brand = (CigaretteBrand) i.next();
              StringBuffer sb = new StringBuffer("");
              sb.append(Integer.toString(brand.getCigaretteId()));
              String param = request.getParameter(Integer.toString(brand.getCigaretteId()));
              if(param != null) 
              {
                application.addBrand(brand);
              }
            }
            bDAO.computeFees3YearRenewal(company);
            return mapping.findForward("cigaretteApp");
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
 


  protected static boolean validate(CigaretteApplication application,CigaretteApplicationForm errorForm) throws Exception
  {
    boolean noError = true;
   
   
   
   
     if(application.getContact() == null || application.getContact().trim().equals("")  ) 
    { 
      errorForm.setContact("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContact("");
    }
     if(application.getTitle() == null || application.getTitle().trim().equals("")  ) 
    { 
      errorForm.setTitle("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setTitle("");
    }
   
     if(application.getPhone() == null || application.getPhone().trim().equals("")  ) 
    { 
      errorForm.setPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPhone("");
    }
      if(application.getEmail() == null || application.getEmail().trim().equals("")  ) 
    { 
      errorForm.setEmail("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setEmail("");
    }
    
    return noError;
  }
  
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
    List apps = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_APP_OPTIONS);
    request.setAttribute("CIG_APP_OPTIONS",apps);
     List appStatus = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_APP_STATUS_OPTIONS);
    request.setAttribute("CIG_APP_STATUS_OPTIONS",appStatus);
    
 }
  
 
  private static void sendEmailApproval( String contactEmail, HttpSession session,CigaretteApplication application,ApplicationContacts contacts) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
                String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),application.getEmail()};
                //String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("Cigarette company certification Approval notice #");
            sb.append(" Thank you for using IDHS online application.Your application number is: "+application.getAppId() );
            sb.append("\n\r");
            sb.append(" Your application for cigarete certification is approved, you can click the link below to print it.");
            sb.append(" https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_permit.rdf&p_application_id=" +application.getAppId());
            
            
            
            
            StringBuffer sub = new StringBuffer();
            sub.append(" Your Cigarette certification with State of Indiana is approved  ");
          
          
          
            mail.createMail("cigarette_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("cigarette_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From cigarette approval error email ","cigarette approval in application action");
            mail1.sendAndClose();
            }
  }
 
}
