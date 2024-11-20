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

public class insuranceAction  extends ControlAction
{
 
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        insuranceForm insForm = (insuranceForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        amuseOwnerDAO oDAO = ( amuseOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_OWNER);
        amuseRideDAO rDAO = (amuseRideDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_RIDE);
        insuranceDAO iDAO = (insuranceDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_INSURANCE);  
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts = sDAO.setContacts("AMUSE_CONTACT1","AMUSE_CONTACT2");
        DfbsOwner owner = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          
        if (method.equals("updateInsurance"))
           {
            String insAppKey= request.getParameter("insAppKey");
            String ownerKey= request.getParameter("ownerKey");
            DfbsOwner cartOwner= cart.getOwner(ownerKey); 
            cartOwner.removeIns(insAppKey);
            insurance ins =iDAO.selectInsurance(Integer.parseInt(insAppKey));
            insForm.setProperties(ins);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("insurance");
           } 
        
        if (method.equals("addNewInsurance"))
           {session.setAttribute("RIDE_INSURANCE_SELECTED",null);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("insurance");
           }
        if (method.equals("noInsChange"))
           {session.setAttribute("RIDE_INSURANCE_SELECTED",null);
             session.setAttribute("RIDE_INSURANCE_STATUS","Y");
             return mapping.findForward("insuranceList");
           }
        if (method.equals("saveInsurance"))
        {
           insurance ins = insForm.getInsurance();
            insuranceForm errorForm = new insuranceForm();
         if (validate(ins,errorForm) ) {
           session.setAttribute("RIDE_INSURANCE_SELECTED",ins);
              Map ownerMap = cart.getOwnerMap();
              Set ownerkeys = ownerMap.keySet();
              Iterator i = ownerkeys.iterator();
             while(i.hasNext())
             {
              String key = (String) i.next();
              DfbsOwner cartOwner= (DfbsOwner) ownerMap.get(key); 
              cartOwner.addIns(ins);  
              owner.addIns(ins);  
             }           
           session.setAttribute("RIDE_INSURANCE_STATUS","Y");
           return mapping.findForward("insuranceList");
         }
          else {
            request.setAttribute("RIDE_INSURANCE_ERROR",errorForm); 
             setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("insuranceList");
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
     List states = uDAO.getOptions(amuseRideSQL.SELECT_STATE_OPTIONS3);
     request.setAttribute("DFBS_STATE_OPTIONS",states);
     
     
  } 
  protected static boolean validate( insurance ins,insuranceForm errorForm ) 
   {
     boolean noError = true;
   
     if (ins.getAmountCovered() == 0)
     {       errorForm.setAmountCovered(0);
         noError = false;
     }
     else
     {
     errorForm.setAmountCovered(ins.getAmountCovered());
     }
     
     if(ins.getInsName() == null || ins.getInsName().trim().equals("") ) 
     {
       errorForm.setInsName("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setInsName("");
     }
      
     if(ins.getPolicyNumber() == null || ins.getPolicyNumber().trim().equals("") ) 
     {
       errorForm.setPolicyNumber("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setPolicyNumber("");
     }
     
     if(ins.getEffDateString() == null || ins.getEffDateString().trim().equals("") ) 
     {
       errorForm.setEffDate("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setEffDate("");
     }
    
     
     if(ins.getExpDateString() == null || ins.getExpDateString().trim().equals("") ) 
     {
       errorForm.setExpDate("ERROR");
       noError = false;
     } 
     else 
     {
       errorForm.setExpDate("");
     }
    
     
     return noError;
   }
   
}
