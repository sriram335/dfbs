package ems.control.action;
import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import otherUsers.to.*;
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

public class EmsVehicleAction extends ControlAction
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
        EmsVehicleForm vehicleForm = (EmsVehicleForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsVehicleDAO vDAO = (EmsVehicleDAO) dmapNew.getHsDAO(DfbsDataMap2.VEHICLE);
        EmsSecurityDAO eDAO = (EmsSecurityDAO) dmapNew.getHsDAO(DfbsDataMap2.EMS_SECURITY);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      if (method.equals("vehicleList"))
        {  int providerId = Integer.parseInt(request.getParameter("providerId"));
           setList(request,vDAO,vehicleForm,providerId);
           return mapping.findForward("vehicleList");
        } 
      else if (method.equals("vehicleDetail")) 
        {
          int id = Integer.parseInt(request.getParameter("vehicleId"));
          this.setView(request,vDAO,vehicleForm,id);
          setOptions(request,dfbsUtilityDAO,0);
           EmsVehicle vehicle = (EmsVehicle) session.getAttribute("VEHICLE");
            if (otherUser !=null)
          {
           vehicle.setFileList(eDAO.selectFileList(request.getParameter("vehicleId"),"Vehicle",null,null)); 
          }
          return mapping.findForward("vehicleDetail");
        }
        else if (method.equals("renewVehicle")) 
        {
          int id = Integer.parseInt(request.getParameter("vehicleId"));
          this.setView(request,vDAO,vehicleForm,id);
           EmsVehicle vehicle = (EmsVehicle) session.getAttribute("VEHICLE");
         setOptions(request,dfbsUtilityDAO,0);
          return mapping.findForward("renewVehicle");
        }
         else if (method.equals("saveRenewVehicle")) 
        {
          EmsVehicle vehicle =  vehicleForm.getEmsVehicle();
          EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
          vDAO.updateRenewVehicle(vehicle);
           setList(request,vDAO,vehicleForm,vehicle.getProviderId());
            sendEmailRequestRenewal(vehicle,otherUser,provider);
           return mapping.findForward("vehicleList");
         
        }
         else if (method.equals("addNewVehicle")) 
        {
           session.setAttribute("VEHICLE",null);
           EmsProvider provider = (EmsProvider) session.getAttribute("PROVIDER");
           setOptions(request,dfbsUtilityDAO,provider.getProviderId());
          return mapping.findForward("vehicleDetail");
        }        
        else if(method.equals("saveVehicle")) 
         
       { 
          EmsVehicle vehicle =  vehicleForm.getEmsVehicle();
          EmsVehicleForm errorForm = new EmsVehicleForm();
          EmsProvider provider = (EmsProvider)session.getAttribute("PROVIDER");
           if (validateVehicle(vehicle,errorForm))
          {  if (vehicle.getVehicleId()==0)
             {
             int providerId = provider.getProviderId();
               if(vehicle.getOvNumber().length() >2)
             {
               vDAO.inactivateVehicle(vehicle.getOvNumber());
               vehicle.setCertNumber(vehicle.getOvNumber());
             }
             vDAO.insertVehicle(vehicle,providerId);
             setList(request,vDAO,vehicleForm,providerId);
               sendEmailRequestRenewal(vehicle,otherUser,provider);
             return mapping.findForward("vehicleList");
             }
             else
             {
             vDAO.updateVehicle(vehicle);
             int providerId = Integer.parseInt(request.getParameter("providerId"));
             setList(request,vDAO,vehicleForm,providerId);
             return mapping.findForward("vehicleList");
             }
          }
          else
          { setOptions(request,dfbsUtilityDAO,0);
           request.setAttribute("VEHICLE_ERROR",errorForm);
           return mapping.findForward("vehicleDetail");
          }
        }
          else if (method.equals("requestRenewal")) 
        {  EmsVehicle vehicle = (EmsVehicle) session.getAttribute("VEHICLE");
          // sendEmailRequestRenewal(vehicle,security);
           this.setView(request,vDAO,vehicleForm,vehicle.getVehicleId());
           setOptions(request,dfbsUtilityDAO,0);
          return mapping.findForward("vehicleDetail");
        } 
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
  private static void setList(HttpServletRequest request,EmsVehicleDAO vDAO,EmsVehicleForm vehicleForm,int providerId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     
        list = vDAO.selectVehicleList(EmsSQL.SELECT_VEHICLE_LIST,providerId);
     
    
    
     request.setAttribute("VEHICLE_LIST",new HsListWrapper(list));
     
  }
  protected static EmsVehicle setView(HttpServletRequest request,
    EmsVehicleDAO rDAO,
      EmsVehicleForm vehicleForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsVehicle vehicle = rDAO.selectVehicle(id);
    
    session.setAttribute("VEHICLE",vehicle);
    
    vehicleForm.setProperties(vehicle);
    return vehicle; 
   
  }
  protected static boolean validateVehicle(EmsVehicle vehicle ,EmsVehicleForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(vehicle.getModelVin() == null || vehicle.getModelVin().trim().equals("")  )
    {
    errorForm.setModelVin("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setModelVin("");
       
    }
    return noError;
  }
   private void sendEmailRequestRenewal(EmsVehicle vehicle,otherUsers otherUser,EmsProvider provider) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
          //  String[] emailTo = {security.getCurrentUser(),"rstump@dhs.in.gov","abiggs@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
           String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sub = new StringBuffer();
            StringBuffer sb = new StringBuffer();
            if(vehicle.getVehicleId()==0)
            { sb.append("New amulance permit from provider: " +provider.getProviderName()).append("\n\r");
              sb.append(" vehicle model type: " +vehicle.getModelType()).append("\n\r");
               sb.append(" for details go to https://oas.dhs.in.gov/ems/vehicle.do?method=vehicleDetail&vehicleId="+vehicle.getVehicleId()).append("\n\r");
               sub.append("ems online services - new permit request for vecle with vin :").append(vehicle.getModelVin());
            }
            else
            {sb.append("Renewal amulance permit request from provider: " +provider.getProviderName()).append("\n\r");
            sb.append(" vehicle certification number: " +vehicle.getCertNumber()).append("\n\r");
            sb.append(" for details go to https://oas.dhs.in.gov/ems/vehicle.do?method=vehicleDetail&vehicleId="+vehicle.getVehicleId()).append("\n\r");
             sub.append("ems online services - renewal request for vehicle certification :").append(vehicle.getCertNumber());
            }
            sb.append("is submitted by : "+otherUser.getUserLoginId()).append("\n\r");
           
           
          
          
          
            mail.createMail("ems_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  }
    protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO,int providerId) throws Exception 
 {
    
    
     List vehicleType = uDAO.getOptions(EmsSQL.SELECT_VEHICLE_TYPE_OPTIONS);
     List currentVehicles = uDAO.getOptions(EmsSQL.SELECT_CURRENT_VEHICLES_PROVIDER,providerId);
     
    request.setAttribute("VEHICLE_TYPE_OPTIONS",vehicleType);
    request.setAttribute("CURRENT_VEHICLES",currentVehicles);
    
 } 
}