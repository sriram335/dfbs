package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
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
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;

import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import otherUsers.to.otherUsers;

public class EmsProviderAction extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context =   this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        EmsProviderForm providerForm = (EmsProviderForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsProviderDAO pDAO = (EmsProviderDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_PROVIDER);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY); 
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
          if (method.equals("providerList"))
        { 
          setList(request,pDAO,providerForm);
          setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("providerListNew");
        } 
        if (method.equals("providerRenewal"))
        { 
          setList(request,pDAO,providerForm);
          setFilterOptions(request,dfbsUtilityDAO);
           session.setAttribute("RENEW_PROVIDER","In Process");
          session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER","");
           return mapping.findForward("providerListNew");
        } 
         if (method.equals("addFromProviderList"))
        {  String hospFlag="Yes";
          session.setAttribute("PROVIDER_HOSP_FLAG",hospFlag);
          setList(request,pDAO,providerForm);
          setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("providerListNew");
        } 
         if (method.equals("hospProvList"))
        {  int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
          setListHospProv(request,pDAO,providerForm,hospitalId);
          setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("hospProvList");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,pDAO,providerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("providerListNew");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,providerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,pDAO,providerForm);
          return mapping.findForward("providerListNew");
        } 
      
        else if (method.equals("providerDetail")) 
        {System.out.println("1");
          int id = Integer.parseInt(request.getParameter("providerId"));
           setOptions(request,dfbsUtilityDAO);
          this.setView(request,pDAO,providerForm,id);System.out.println("2");
           EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
            if (otherUser !=null)
          {System.out.println("3");
           provider.setFileList(eDAO.selectFileList(request.getParameter("providerId"),"Provider",null,null)); System.out.println("4");
          int provSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),id,"Provider");System.out.println(provSecurityCheck);
          if (provSecurityCheck >0)
          {
            session.setAttribute("PROVIDER_SECURITY_FLAG","Y");
          }
          else
          {
             session.setAttribute("PROVIDER_SECURITY_FLAG","N");
          }
          }
          return mapping.findForward("providerDetail");
        }
       
        
                 
         if(method.equals("saveProvider")) 
         
       { int provSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),providerForm.getProviderId(),"Provider");
         if (provSecurityCheck >0 ||otherUser.getUserGroup().equals("ADMINISTRATOR"))
         {
          EmsProvider provider =  providerForm.getEmsProvider();
          EmsProviderForm errorForm = new EmsProviderForm();
           if (validateProvider(provider,errorForm))
          { 
             pDAO.updateProvider(provider);
             setList(request,pDAO,providerForm);
             setFilterOptions(request,dfbsUtilityDAO);
             setOptions(request,dfbsUtilityDAO);
             String renewFlag = (String) session.getAttribute("RENEW_PROVIDER");
             if (renewFlag !=null)
             { session.setAttribute("RENEW_PROVIDER","In Process");
                session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER",provider.getProviderName());
                session.setAttribute("RENEW_PROVIDER_UPDATE","Completed");
                 session.setAttribute("RENEW_PROVIDER_RENEW_INFORMATION","In Process");
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/ems/providerRenewal.do?method=goToRenewal");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;
             }
             else
             {
             return mapping.findForward("providerListNew");
             }
          }
          else
          { request.setAttribute("USER_ERROR",errorForm);
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("providerDetail");
          }
        }
        else
        {
          throw new Exception("UNAUTHORIZED_ACCESS");
        }
        
       }
           if(method.equals("personList")) 
        {  
                   return mapping.findForward("personList");
         }    
          else if (method.equals("startOverForm")) 
        {
          return mapping.findForward("startOverForm");
        }
        else if (method.equals("startOver")) 
        {
          session.setAttribute("PROVIDER",null);
           setList(request,pDAO,providerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        }
         else if (method.equals("renewProvider")) 
        { int id = Integer.parseInt(request.getParameter("providerId"));
         //  sendEmailRequestRenewal(provider,security);
         setOptions(request,dfbsUtilityDAO);
          this.setView(request,pDAO,providerForm,id);
          EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
          session.setAttribute("RENEW_PROVIDER","In Process");
          session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER",provider.getProviderName());
          session.setAttribute("RENEW_PROVIDER_UPDATE","In Process");
            if (otherUser !=null)
          {
           provider.setFileList(eDAO.selectFileList(request.getParameter("providerId"),"Provider",null,null)); 
          
          int provSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),id,"Provider");
          if (provSecurityCheck >0)
          {
            session.setAttribute("PROVIDER_SECURITY_FLAG","Y");
          }
          else
          {
             session.setAttribute("PROVIDER_SECURITY_FLAG","N");
          }
           
          }
          return mapping.findForward("providerDetail");
        } 
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List charNav = uDAO.getCharacterNavs(EmsSQL.SELECT_FIRST_LETTER_OPTIONS_PROV);
    List county = uDAO.getOptions(EmsSQL.SELECT_COUNTY_OPTIONS_PROV);
    List cities = uDAO.getOptions(EmsSQL.SELECT_CITIES_OPTIONS_PROV);
    List levels = uDAO.getOptions(EmsSQL.SELECT_LEVELS_OPTIONS_PROV);
    
    request.setAttribute("PROVIDER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("PROVIDER_COUNTY_OPTIONS",county);
    request.setAttribute("PROVIDER_CITIES_OPTIONS",cities);
    request.setAttribute("PROVIDER_LEVELS_OPTIONS",levels);
   
 }
 protected static void setFilterSession(HttpServletRequest request,EmsProviderForm filterForm) throws Exception
  {
    HttpSession session = request.getSession();
    String filter = request.getParameter("filter");
    
    HsFilter filterSession = new HsFilter();
    filterSession.setType(filter);
    
     if(filter == null ) {
      filterSession.setType("byLetter");
      filterSession.setValue("A");
    } 
    else if(filter.equals("byLetter"))  
    { 
       String letter = request.getParameter("letter");
       filterSession.setValue(letter);
     }
     else if(filter.equals("byCounty"))  
    { 
       filterSession.setValue(filterForm.getProviderCounty());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(filterForm.getProviderCity());
     }
      else if(filter.equals("byLevel"))  
    { 
       filterSession.setValue(filterForm.getProviderLevel());
     }
     session.setAttribute("PROVIDER_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,EmsProviderDAO epDAO,EmsProviderForm providerForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("PROVIDER_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("PROVIDER_LIST_FILTER",filterSession);
        list = epDAO.selectProviderList(EmsSQL.SELECT_PROVIDER_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = epDAO.selectProviderList(EmsSQL.SELECT_PROVIDER_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = epDAO.selectProviderList(EmsSQL.SELECT_PROVIDER_BY_COUNTY,filterSession.getValue());
        providerForm.setProviderState(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = epDAO.selectProviderList(EmsSQL.SELECT_PROVIDER_BY_CITY,filterSession.getValue());
        providerForm.setProviderCity(filterSession.getValue());
     } 
    else if(filterSession.getType().equals("byLevel")) 
     {
        
        list = epDAO.selectProviderList(EmsSQL.SELECT_PROVIDER_BY_LEVEL,filterSession.getValue());
        providerForm.setProviderCity(filterSession.getValue());
     } 
    
     request.setAttribute("PROVIDER_LIST",new HsListWrapper(list));
     
  }
  protected static EmsProvider setView(HttpServletRequest request,
    EmsProviderDAO rDAO,
      EmsProviderForm providerForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
  //  HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
   System.out.println("in set view");
    EmsProvider provider = rDAO.selectProvider(id);
    
    session.setAttribute("PROVIDER",provider);
    
    providerForm.setProperties(provider);
     System.out.println("out set view");
    return provider; 
   
  }
  
 private void sendEmailRequestRenewal(EmsProvider provider,EmsSecurity security) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"rstump@dhs.in.gov","nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
         
            StringBuffer sb = new StringBuffer();
            sb.append(" renewal request for provider name: " +provider.getProviderName()).append("\n\r");
            sb.append("is submitted by : "+security.getCurrentUser()).append("\n\r");
          
           
            StringBuffer sub = new StringBuffer();
            sub.append("ems online services - ").append(provider.getProviderName());
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  }
protected static boolean validateProvider(EmsProvider provider ,EmsProviderForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(provider.getProviderName() == null || provider.getProviderName().trim().equals("")  )
    {
    errorForm.setProviderName("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setProviderName("");
       
    }
    
 
    if(provider.getProviderAddress1() == null || provider.getProviderAddress1().trim().equals("") ) 
    { 
      errorForm.setProviderAddress1("ERROR_NOTEQUAL");
      noError = false;
    } 
     else
    {
      errorForm.setProviderAddress1("");
    }
    if(provider.getProviderAddress1() == null || provider.getProviderAddress1().trim().equals("")) 
    { 
      errorForm.setProviderAddress1("ERROR_LENGTH");
      noError = false;
    } 
     else
    {
      errorForm.setProviderAddress1("");
    }
   System.out.println("in validate provider");
    return noError;
  }
    
 private static void setListHospProv(HttpServletRequest request,EmsProviderDAO epDAO,
          EmsProviderForm providerForm, int hospitalId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
        list = epDAO.selectProviderListHosp(EmsSQL.SELECT_PROVIDER_BY_HOSPITAL,hospitalId);
    
    
     request.setAttribute("PROVIDER_LIST",new HsListWrapper(list));
     
  }
 
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
    
     List stateList = uDAO.getOptions(EmsSQL.SELECT_STATE_LIST_OPTIONS);
     List countyList = uDAO.getOptions(EmsSQL.SELECT_COUNTY_LIST_OPTIONS);
     request.setAttribute("PROVIDER_STATE_LIST_OPTIONS",stateList);
     request.setAttribute("PROVIDER_COUNTY_LIST_OPTIONS",countyList);
    
 } 
}