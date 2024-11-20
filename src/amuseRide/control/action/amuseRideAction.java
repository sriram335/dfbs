package amuseRide.control.action;

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
import amuseRide.data.*;
import amuseRide.control.form.*;

import amuseRide.to.*;


import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;
import main.to.FileNames;

public class amuseRideAction extends ControlAction
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
        
        amuseRideForm rideForm = (amuseRideForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        amuseOwnerDAO oDAO = ( amuseOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_OWNER);
        amuseRideDAO rDAO = (amuseRideDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_RIDE);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        insuranceDAO iDAO = (insuranceDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_INSURANCE); 
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts = sDAO.setContacts("AMUSE_CONTACT1","AMUSE_CONTACT2");
        DfbsOwner owner = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          if (method.equals("rideAction"))
             {
               String rideAction = request.getParameter("rideAction");
                String serialNumber = request.getParameter("serialNumber");
               amuseRide ride = rDAO.selectRide(serialNumber);
               session.setAttribute("RIDE_SELECTED",ride);
                if (rideAction.equals("Apply for Renewal")) 
               {    owner.addRide(ride);
                   rideForm.setProperties(ride);
                   if (cart.getOwnerCount()==0) {
                     cart.addOwner(owner);
                     oDAO.calculateAmuseFee(cart);
                     session.setAttribute("AMUSE_OWNER_SELECTED",owner);
                   }
                   else {
                      Map ownerMap = cart.getOwnerMap();
                      Set ownerkeys = ownerMap.keySet();
                      Iterator i = ownerkeys.iterator();
                     while(i.hasNext())
                     {
                      String key = (String) i.next();
                      DfbsOwner cartOwner= (DfbsOwner) ownerMap.get(key); 
                      cartOwner.addRide(ride);  
                       oDAO.calculateAmuseFee(cart);
                     }
                   }
                 StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                 redirectUrl.append(request.getContextPath()).append("/amuseRide/owner.do?method=view&ownerId="+owner.getOwnerId());
                 response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                   return null;
               }
                if (rideAction.equals("Submit /Print inspection"))
                   {
                     StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                     redirectUrl.append(request.getContextPath()).append("/amuseRide/inspection.do?method=inspectionList");
                     response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                       return null;
                   }
              }
          
        if (method.equals("removeRide"))
           {
             Map ownerMap = cart.getOwnerMap();
             Set ownerkeys = ownerMap.keySet();
             Iterator i = ownerkeys.iterator();
             while(i.hasNext())
             {String rideAppKey= request.getParameter("rideAppKey");
             String key = (String) i.next();
             DfbsOwner cartOwner= (DfbsOwner) ownerMap.get(key); 
             cartOwner.removeRide(rideAppKey);
             }
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/amuseRide/owner.do?method=view&ownerId="+owner.getOwnerId());
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
               return null;
           }
        if (method.equals("addNewRide"))
           {session.setAttribute("AMUSE_RIDE_SELECTED",null);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("newRide");
           }
        if (method.equals("saveNewRide"))
        {
           amuseRide ride = rideForm.getAmuseRide();
            amuseRideForm errorForm = new amuseRideForm();
         if (validate(ride,errorForm,session) ) {
           session.setAttribute("AMUSE_RIDE_SELECTED",ride);
           if (cart.getOwnerMapCount()==0) {
             owner.addRide(ride);
             cart.addOwner(owner);
             oDAO.calculateAmuseFee(cart);
           }
           else {
              Map ownerMap = cart.getOwnerMap();
              Set ownerkeys = ownerMap.keySet();
              Iterator i = ownerkeys.iterator();
             while(i.hasNext())
             {
              String key = (String) i.next();
              DfbsOwner cartOwner= (DfbsOwner) ownerMap.get(key); 
              cartOwner.addRide(ride);  
               oDAO.calculateAmuseFee(cart);
                owner.addRide(ride);
             }
           }
          return mapping.findForward("insuranceList");
         }
          else {
            request.setAttribute("AMUSE_RIDE_ERROR",errorForm);
             setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("newRide");
          }
             
           }
       
        else if(method.equals("updateRide")) 
        { String serialNumber = request.getParameter("serialNumber"); 
          amuseRide ride = rDAO.selectRide(serialNumber);
          session.setAttribute("AMUSE_RIDE_SELECTED",ride);
           rideForm.setProperties(ride);
          setOptions(request,dfbsUtilityDAO); 
          return mapping.findForward("updateRide");
        } 
        else if(method.equals("updateSaveRide")) 
        {  amuseRide ride = rideForm.getAmuseRide();
           rDAO.updateRide(ride);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateRide");
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
     List states = uDAO.getOptions(amuseRideSQL.SELECT_STATE_OPTIONS3);
     List devices = uDAO.getOptions(amuseRideSQL.SELECT_DEVICE_OPTIONS);
     request.setAttribute("DFBS_STATE_OPTIONS",states);
    request.setAttribute("DFBS_DEVICE_OPTIONS",devices);
     
     
  } 
  protected static boolean validate(amuseRide ride,amuseRideForm errorForm,HttpSession session) 
   {
     boolean noError = true;
   
       if (ride.getDeviceId() == 0)
       {       errorForm.setDeviceId(0);
           noError = false;
       }
     else 
     {
       errorForm.setDeviceId(ride.getDeviceId());
     }
     
     if(ride.getDeviceName() == null || ride.getDeviceName().trim().equals("") ) 
     {
       errorForm.setDeviceName("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setDeviceName("");
     }
      
     if(ride.getManName() == null || ride.getManName().trim().equals("") ) 
     {
       errorForm.setManName("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setManName("");
     }
     
     if(ride.getSpeed() == null || ride.getSpeed().trim().equals("") ) 
     {
       errorForm.setSpeed("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setSpeed("");
     }
    
     
     if(ride.getCapacity() == null || ride.getCapacity().trim().equals("") ) 
     {
       errorForm.setCapacity("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setCapacity("");
     }
    
     
     return noError;
   }
   
}
