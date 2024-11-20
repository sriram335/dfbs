package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import main.to.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import main.data.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;

import otherUsers.to.otherUsers;


public class EmsProvRenewalAction extends ControlAction
{ public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        EmsProvRenewalForm renewalForm = (EmsProvRenewalForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsProvRenewalDAO rDAO = (EmsProvRenewalDAO) dmapNew.getHsDAO(DfbsDataMap2.PROVIDER_RENEWAL);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        EmsVehicleDAO vDAO = (EmsVehicleDAO) dmapNew.getHsDAO(DfbsDataMap2.VEHICLE);        

        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
        EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
         if (method.equals("renewalList"))
        { 
            setList(request,rDAO,provider.getProviderId());
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("renewalList");
        } 
        
        if (method.equals("goToRenewal"))
        { session.setAttribute("RENEW_PROVIDER","In Process");
                session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER",provider.getProviderName());
                session.setAttribute("RENEW_PROVIDER_UPDATE","Completed");
                 session.setAttribute("RENEW_PROVIDER_RENEW_INFORMATION","In Process");
          session.setAttribute("RENEWAL",null);
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("renewalDetail");
        } 
        
        
       if (method.equals("saveRenewalVehicleList"))
        {   
          List renVehicles = vDAO.selectVehicleList(EmsSQL.SELECT_VEHICLE_LIST,provider.getProviderId());
           Iterator i = renVehicles.iterator();
        
        while(i.hasNext())
        {
         EmsVehicle vehicle = (EmsVehicle)  i.next();
         StringBuffer sb = new StringBuffer("");
          sb.append(Integer.toString(vehicle.getVehicleId()));
          String vehLocation = request.getParameter(sb.toString());
          if(vehLocation != null) 
          {
            vDAO.updateVehicleLocation(vehicle.getVehicleId(),vehLocation);
          }
          
          } 
           session.setAttribute("RENEW_PROVIDER","In Process");
                session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER",provider.getProviderName());
                session.setAttribute("RENEW_PROVIDER_UPDATE","Completed");
                 session.setAttribute("RENEW_PROVIDER_RENEW_INFORMATION","Complete");
                  session.setAttribute("RENEW_PROVIDER_VEHICLE_INFORMATION","Complete");
                 session.setAttribute("RENEW_PROVIDER_FILE_UPLOAD","In Process"); 
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/ems/main.do?method=goToUpload&idNumber="+provider.getProviderId()+"&idType=Provider");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewalDetail")) 
        {
            int id = Integer.parseInt(request.getParameter("renewalId"));
             setOptions(request,dfbsUtilityDAO);
            this.setView(request,rDAO,renewalForm,id);
             EmsProviderRenewal renewal = (EmsProviderRenewal) session.getAttribute("RENEWAL");
              if (otherUser !=null)
            {
             
            int provSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),id,"Provider");
            if ((provSecurityCheck >0 && otherUser.getUserGroup().equals("USER")) ||otherUser.getUserGroup().equals("ADMINISTRATOR"))
            {
              session.setAttribute("PROVIDER_SECURITY_FLAG","Y");
            }
            else
            {
               session.setAttribute("PROVIDER_SECURITY_FLAG","N");
            }
            }
            
            return mapping.findForward("renewalDetail");
          }
        
                 
         if(method.equals("saveProvRenewal")) 
         
        {  
           
           int provSecurityCheck= eDAO.checkProviderSecurity(otherUser.getUserId(),provider.getProviderId(),"Provider");
             if ((provSecurityCheck >0))
             {
              EmsProviderRenewal renewal =  renewalForm.getEmsProviderRenewal();
              EmsProvRenewalForm errorForm = new EmsProvRenewalForm();
               if (validateProviderRenewal(renewal,errorForm))
              {  session.setAttribute("RENEWAL",renewal);
                 if (renewal.getRenewalId() == 0 )
                {
                 rDAO.insertRenewal(renewal,provider);
                 providerRenewalRequestReceived(provider.getProviderName() +":"+provider.getProviderCertNumber(),otherUser);
                }
                else
                {
                  rDAO.updateRenewal(renewal);
                }
                
                List list = null;
               list = vDAO.selectVehicleList(EmsSQL.SELECT_VEHICLE_LIST,provider.getProviderId());
               request.setAttribute("VEHICLE_RENEWAL_LIST",new HsListWrapper(list));
               session.setAttribute("RENEW_PROVIDER","In Process");
                session.setAttribute("RENEW_PROVIDER_SELECT_PROVIDER","Selected Provider:"+provider.getProviderName());
                session.setAttribute("RENEW_PROVIDER_UPDATE","Completed");
                 session.setAttribute("RENEW_PROVIDER_RENEW_INFORMATION","Complete");
                  session.setAttribute("RENEW_PROVIDER_VEHICLE_INFORMATION","In Process");
                return mapping.findForward("renewalVehicleList");
              }
              else
              { request.setAttribute("USER_ERROR",errorForm);
               setOptions(request,dfbsUtilityDAO);
               return mapping.findForward("renewalDetail");
              }
            }
            else
            {
              throw new Exception("UNAUTHORIZED_ACCESS");
            }
        
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
   
    List county = uDAO.getOptions(EmsSQL.SELECT_COUNTY_OPTIONS_PROV);
    List cities = uDAO.getOptions(EmsSQL.SELECT_CITIES_OPTIONS_PROV);
    List levels = uDAO.getOptions(EmsSQL.SELECT_LEVELS_OPTIONS_PROV);
    
    
    request.setAttribute("PROVIDER_COUNTY_OPTIONS",county);
    request.setAttribute("PROVIDER_CITIES_OPTIONS",cities);
    request.setAttribute("PROVIDER_LEVELS_OPTIONS",levels);
   
 }
 
  
 
  protected static EmsProviderRenewal setView(HttpServletRequest request,
    EmsProvRenewalDAO rDAO,
      EmsProvRenewalForm renewalForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsProviderRenewal renewal = rDAO.selectRenewal(id);
    
    session.setAttribute("RENEWAL",renewal);
    
    renewalForm.setProperties(renewal);
    return renewal; 
   
  }
  

protected static boolean validateProviderRenewal(EmsProviderRenewal renewal ,EmsProvRenewalForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(renewal.getEquipMaintenance() == null || renewal.getEquipMaintenance().trim().equals("")  )
    {
    errorForm.setEquipMaintenance("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setEquipMaintenance("");
       
    }
    
   System.out.println("in validate renewal");
    return noError;
  }
    
 
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
     List provYesNo = uDAO.getOptions(EmsSQL.SELECT_RENEWAL_YN_OPTIONS);
   
    request.setAttribute("RENEWAL_YN_OPTIONS",provYesNo);
 } 
 private static void providerRenewalRequestReceived(String providerName,otherUsers user) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            
          String[] emailTo = {"chilton@dhs.in.gov","rstump@dhs.in.gov",user.getUserLoginId()};
          // String[] emailTo = {"rstump@dhs.in.gov","ssteinhilber@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
          
            StringBuffer sb = new StringBuffer("\r\n EMS provider renewal requested for:");
            sb.append("\r\n provider name="+ providerName);
           
            
            StringBuffer sub = new StringBuffer();
            sub.append("EMS provider renewal requested ");
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  }
  
  private static void setList(HttpServletRequest request,EmsProvRenewalDAO rDAO,int providerId) throws Exception
  {
  
   
     List list = null;
     
    
        list = rDAO.selectRenewalList(providerId);
    
    
     request.setAttribute("RENEWAL_LIST",new HsListWrapper(list));
     
  }
}