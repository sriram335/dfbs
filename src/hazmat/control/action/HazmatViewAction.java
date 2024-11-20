package hazmat.control.action;
import hazmat.control.form.*;
import hazmat.to.*;
import hazmat.data.*;
import hazmat.report.*;
import main.to.*;
import main.data.*;
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
import util.logging.DHSLogLevel;
import util.logging.Log;
import hs.report.pdf.*;




public class HazmatViewAction extends ControlAction
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
        
        
        HazmatPermitViewForm orgForm = (HazmatPermitViewForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
        HazmatCarrierDAO cDAO = (HazmatCarrierDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_CARRIER);
        HazmatShipmentDAO sDAO = (HazmatShipmentDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_SHIPMENT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
        String url = request.getRemoteAddr();
        
     /* if(url != null && (url.substring(0,9).equals("127.0.0.1") || url.substring(0,9).equals("10.90.29."))) 
      
      {*/
       if (security != null &&((security.getGroupLevelHazmat() !=null &&security.getGroupLevelHazmat().equals("HAZMAT_SUP")) || (security.getGroupLevelAll() !=null && security.getGroupLevelAll().equals("DBA"))))
          {
              Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HAZMAT VIEW ACTION", "SEARCHSHIPMENTS", "Shipment ID: " + "IN LINE 65");
        if (method == null) 
        {Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HAZMAT VIEW ACTION", "SEARCHSHIPMENTS", "Shipment ID: " + "IN LINE 67");
          setList(request,oDAO,orgForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("orgShipmentView");
        } 
        else if (method.equals("orgShipmentView")) 
        {Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HAZMAT VIEW ACTION", "SEARCHSHIPMENTS", "Shipment ID: " + "IN LINE 73");

          setList(request,oDAO,orgForm);
           setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("orgShipmentView");
        }
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,orgForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("orgShipmentView");
        } 
        else if (method.equals("filter")) 
        {
            Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HAZMAT VIEW ACTION", "SEARCHSHIPMENTS", "Shipment ID: " + "IN LINE 105");
            setFilterSession(request,orgForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,oDAO,orgForm);
           return mapping.findForward("orgShipmentView");
        } 
         else if (method.equals("orgView")) 
        { 
          String orgId = request.getParameter("orgId");
          HazmatPermit permit =  oDAO.selectOrg(orgId);
          session.setAttribute("ORG_VIEW",permit);
          String carrierId = request.getParameter("carrierId");
          HazmatCarrier carrier =  cDAO.selectCarrier(carrierId);
          session.setAttribute("CARRIER_VIEW",carrier);
          String shipmentId = request.getParameter("shipmentId");
          HazmatShipment shipment =  sDAO.selectShipment(shipmentId);
          session.setAttribute("SHIPMENT_VIEW",shipment);
          return mapping.findForward("viewDetails");
        }
       
          else if (method.equals("backToView")) 
        { 
         return mapping.findForward("orgShipmentView");
        }
          else if (method.equals("hazmatPrivate")) 
        { 
         return mapping.findForward("hazmatPrivate");
        }
      }
      
     else
         {StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/main/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;

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
    List charNav = uDAO.getCharacterNavs(HazmatPermitSQL.SELECT_FIRST_LETTER_OPTIONS);
    List timePeriod = uDAO.getOptions(HazmatPermitSQL.SELECT_TIME_PERIOD_OPTIONS);
    
    
    request.setAttribute("ORG_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("ORG_TIME_PERIOD_OPTIONS",timePeriod);
   
   
 }
 protected static void setFilterSession(HttpServletRequest request,HazmatPermitViewForm filterForm) throws Exception
  {
    HttpSession session = request.getSession();
    String filter = request.getParameter("filter");
    
    HsFilter filterSession = new HsFilter();
    filterSession.setType(filter);
    
     if(filter == null ) {
      filterSession.setType("byLetter");
      filterSession.setValue("A");
      //also send result based on permi number. if no permit number is given send all records.
    } 
    else if(filter.equals("byLetter"))  
    { 
       String letter = request.getParameter("letter");
       filterSession.setValue(letter);
     }
     else if(filter.equals("byTimePeriod"))  
    {  
       filterSession.setValue(filterForm.getMonthYear());
    }
     
     session.setAttribute("ORG_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,HazmatPermitDAO fwDAO,HazmatPermitViewForm orgForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("ORG_LIST_FILTER");
   
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("ORG_LIST_FILTER",filterSession);
        list = fwDAO.selectOrgList(HazmatPermitSQL.SELECT_ORG_BY_LETTER ,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOrgList(HazmatPermitSQL.SELECT_ORG_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byTimePeriod")) 
     {
        
        list = fwDAO.selectOrgList(HazmatPermitSQL.SELECT_ORG_BY_TIME_PERIOD,filterSession.getValue());
       //orgForm.setState(filterSession.getValue());
     } 
    
   
    
     request.setAttribute("ORG_LIST",new HsListWrapper(list));
     
  }
 /* protected static HazmatPermit setView(HttpServletRequest request,
    HazmatPermitDAO fwDAO,
      HazmatPermitForm orgForm,String id) throws Exception
  {
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
   
    HazmatPermit org = fwDAO.selectOrganization(id);
   
    
    session.setAttribute("ORG_CARRIER_SHIPMENT_VIEW",org);
    
    orgForm.setProperties(org);
   
    
    return org;
  } */
  
 
  
  
 
 
}

