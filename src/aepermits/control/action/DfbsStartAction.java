package aepermits.control.action;
import aepermits.control.*;
import aepermits.control.form.*;
import aepermits.to.*;
import aepermits.data.*;
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
import idhsInspections.to.*;
import idhsInspections.data.*;
import main.data.*;


public class DfbsStartAction extends ControlAction
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
        
        
        DfbsStartForm startForm = (DfbsStartForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsEntrOwnerDAO oDAO = (DfbsEntrOwnerDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_OWNER);
        DfbsEntrPermitDAO pDAO = (DfbsEntrPermitDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_PERMIT);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION_SEARCH);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
       
        if (method == null) 
        {   ShoppingCart cart = new ShoppingCart(); 
          String conType ="ENTERTAINMENT_CONTACT";
            session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
            String appLocation ="/dfbs/aepermits/start.do";
             session.setAttribute("APPLICATION_LOCATION",appLocation); 
             ApplicationContacts contacts = sDAO.setContacts("ENTERTAINMENT_CONTACT1","ENTERTAINMENT_CONTACT2");
             session.setAttribute("APPLICATION_CONTACTS",contacts);
            String appHeading ="Entertainment (Annual/Special) Permits Online Application";
            session.setAttribute("APPLICATION_HEADING",appHeading); 
            session.setAttribute("SHOPPING_CART",cart);
            FileNames names= sDAO.selectAppStatus("AE_PERMITS_MAINT_FLAG");
            session.setAttribute("AE_PERMITS_APP_STATUS",names.getFileName());
            session.setAttribute("AE_PERMITS_APP_MESSAGE",names.getFileType());
            return mapping.findForward("start");
        } 
        else if (method.equals("backToStart")) 
        { session.setAttribute("DFBS_OWNER",null);
          session.setAttribute("DFBS_OWNER_APPLICATION",null);
          return mapping.findForward("start");
        } 
        else if (method.equals("permitYear")) 
        { 
          session.setAttribute("SEARCH_AE",null);
          return mapping.findForward("start");
        } 
        
        else if (method.equals("search")) 
        { String stNumber = request.getParameter("streetNumber");
          String city = request.getParameter("city");
          String streetName = request.getParameter("streetName");
          if ((stNumber ==null || stNumber.equals("")) && (city ==null || city.equals(""))&& (streetName==null || streetName.equals(""))) {
            request.setAttribute("SEARCH_NULL","ERROR");
          }
          else
          {request.setAttribute("SEARCH","");
           String streetNumber =stNumber+"%"+streetName;
          List aeList = idhsDAO.selectAepermits(null,streetNumber,city,null,null,null,null,null,null);
          request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
          session.setAttribute("SEARCH_AE","SEARCH");
          setOptions(request,dfbsUtilityDAO);
          }
          return mapping.findForward("start");
          
        } 
        else if (method.equals("No Match")) 
        { session.setAttribute("SEARCH_AE","No Match");
          return mapping.findForward("start");
        } 
        else if (method.equals("renewBy")) 
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("renewBy");
        } 
         else if (method.equals("newPermitNewOwner")) 
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newOwner");
        } 
         else if (method.equals("newPermitOldOwner")) 
        {
        
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/aepermits/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewByOwner")) 
        {session.setAttribute("PERMIT_FEE",null);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/aepermits/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewByStreetNumber")) 
        {
        String streetNumber=startForm.getStreetNumber();
         if (streetNumber.equals(""))
          {
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setStreetNumber("");
            request.setAttribute("START_FORM_ERROR",errorForm);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("start");
          }
          else
          {
           List list = null;
          list = oDAO.selectOwnersListByStreet(DfbsEntrSQL.SELECT_OWNERS_BY_STREET,streetNumber,"byStreet");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          session.setAttribute("PERMIT_FEE",null);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("renewByStreetNumber");
          }
        } 
        
        else if (method.equals("renewByPermitNumber")) 
        { String permitNumber=startForm.getPermitNumber();
          if (permitNumber.trim().equals(""))
          {
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setPermitNumber("ERROR");
            request.setAttribute("START_FORM_ERROR",errorForm);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("start");
          }
          
          else
          {
          List list = null;
           list = oDAO.selectOwnersListByStreet(DfbsEntrSQL.SELECT_OWNERS_BY_PERMIT,permitNumber,"byPermit");
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          session.setAttribute("PERMIT_FEE",null);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("renewByPermitNumber");
          }
        } 
        
         else if (method.equals("backToPermit")) 
        { 
         String permitNumber = request.getParameter("permitNumber");
          List list = null;
          list = oDAO.selectOwnersListByStreet(DfbsEntrSQL.SELECT_OWNERS_BY_PERMIT,permitNumber,"byPermit");
          setOptions(request,dfbsUtilityDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          
        } 
        
        else if (method.equals("sendEmail")) 
        {
          sendEmail(request);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          return mapping.findForward("sendEmailForm");
        }
        else if (method.equals("sendEmailForm")) 
        {
          return mapping.findForward("sendEmailForm");
        }
        else if (method.equals("reset")) 
        {
          
           session.setAttribute("SHOPPING_CART",null);
          return mapping.findForward("start");
        }
         else if (method.equals("lookupNew")) 
        {
         List list = null;
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByStreetNumber");
         
        } 
           else if (method.equals("logOn")) 
        {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append("/dfbs/main/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
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
 

  
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"nnimmagadda@dhs.in.gov","cclouse@dhs.in.gov"};
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
            sub.append("* From Entertainment Online * - ").append(subject);
          
          
          
            mail.createMail("fire_aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Entertainment Online * - error email ","DfbsStartAction");
            mail1.sendAndClose(); 
           }
  }
  

  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("DFBS_STATE_OPTIONS",states);
 
     List states1 = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS);
     request.setAttribute("OWNER_STATE_OPTIONS",states1); 
     
    List counties = uDAO.getOptions(DfbsEntrSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
    
    List occupancy = uDAO.getOptions(DfbsEntrSQL.SELECT_OCCUPANCY_OPTIONS);
    request.setAttribute("DFBS_OCCUPANCY_OPTIONS",occupancy);
    
    List charNav = uDAO.getCharacterNavs(DfbsEntrSQL.SELECT_FIRST_LETTER_OPTIONS);
    List otherCounties = uDAO.getOptions(DfbsEntrSQL.SELECT_COUNTIES_OPTIONS);
    List cities = uDAO.getOptions(DfbsEntrSQL.SELECT_CITIES_OPTIONS);
    
    request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
    request.setAttribute("DFBS_CITIES_OPTIONS",cities);
 }
 
}