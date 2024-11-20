package fireworks.control.action;

import fireworks.control.form.*;
import fireworks.to.*;
import otherUsers.to.*;
import fireworks.data.*;
import fireworks.report.*;


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
import main.to.*;
import hs.util.*;
import main.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import util.logging.DHSLogLevel;
import util.logging.Log;

public class FireworksAction extends ControlAction
{
    private static final String CLASS_NAME="FireworksAction";
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)
        context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
        DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        String letter = request.getParameter("letter");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
         
         ApplicationContacts contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS"); 
         // contacts = sDAO.setContacts("FIREWORKS_CONTACT1","FIREWORKS_CONTACT2");
        //  session.setAttribute("APPLICATION_CONTACTS",contacts);
        //  contacts = (ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        String realPath =  context.getInitParameter("realPath");
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, "FireworksAction", "consumerToPrint", "realPath.:"+realPath); 
        String dhsLogo = realPath+"dhs_logo.jpg";
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, "FireworksAction", "consumerToPrint", "dhsLogo.:"+dhsLogo); 
        String dhsLogo2 = realPath+"dhs_logo2.jpg";
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, "FireworksAction", "consumerToPrint", "dhsLogo2.:"+dhsLogo2); 
        String firemarshalSign = realPath+"fireMarshalSignature.gif";
        // firemarshalSign = "D:/Apps/DHS/JavaApps/dfbs/WEB-INF/img/fireMarshalSignature.gif";
        System.out.print("Context2"+firemarshalSign);
         Log.log("ACTION_LAYER", DHSLogLevel.INFO, "FireworksAction", "consumerToPrint", "firemarshalSign.:"+firemarshalSign); 
        if (method == null) 
        {   
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
        
           return mapping.findForward("main");
        } 
        else if (method.equals("refresh")) 
        { 
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        } 
        else if (method.equals("filter")) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        } 
        else if (method.equals("refreshView")) 
        {
          String id = (String )request.getAttribute("DFBS_OWNER_REFRESH");
          this.setView(request,oDAO,ownerForm,id);
          return mapping.findForward("view");
        }
        else if (method.equals("view")) 
        {
          String id = request.getParameter("ownerId");
          this.setView(request,oDAO,ownerForm,id);
          return mapping.findForward("view");
        }
         else if (method.equals("showCounty")) 
        {
         otherUsers otherUser = (otherUsers) session.getAttribute("OTHER_USER");
         String recFlag = request.getParameter("recFlag");
          String county = otherUser.getUserCounty();
           session.setAttribute("OTHER_COUNTY",county);
           session.setAttribute("FIREWORKS_COUNTY_FLAG",recFlag);
          this.setViewCounty(request,oDAO,ownerForm,county,recFlag);
          return mapping.findForward("viewCounty");
        }
        else if(method.equals("consumerToPrint")) 
        {
          String permitNumber = request.getParameter("permitNumber");
          List list = new ArrayList();
          if(permitNumber == null) {
            String url = request.getRemoteAddr();
          if(url != null && url.substring(0,8).equals("10.90.29")) {
              list = pDAO.selectPermitToPrint(true);
            } 
            else 
            {
               throw new Exception("UNAUTHORIZED_ACCESS");  
            }
          } 
          else 
          {
            list = pDAO.selectPermitToPrint(true,permitNumber);
          } 
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          
         
            FireworksConsumerCompliancePdf pdf = 
                        new FireworksConsumerCompliancePdf(
                          list,baos,dhsLogo,dhsLogo2,firemarshalSign);
        /*   FireworksConsumerCompliancePdf pdf = 
            new FireworksConsumerCompliancePdf(
              list,baos,context.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                context.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                  context.getRealPath("/WEB-INF/img/fireMarshalSignature.jpg")); */

          request.setAttribute("HS_PDF",pdf);
          return mapping.findForward("pdf");
        } 
        else if(method.equals("wholesaleToPrint")) 
        {String permitNumber = request.getParameter("permitNumber");
          List list = new ArrayList();
          if(permitNumber == null) {
            String url = request.getRemoteAddr();
            if(url != null && url.substring(0,8).equals("10.90.29")) {
              list = pDAO.selectPermitToPrint(false);
            } 
            else 
            {
              throw new Exception("UNAUTHORIZED_ACCESS");  
            }
             } 
          else 
          {
            list = pDAO.selectPermitToPrint(false,permitNumber);
          } 
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          FireworksWholesalerCompliancePdf pdf = 
            new FireworksWholesalerCompliancePdf(
            list,baos,dhsLogo,dhsLogo2,firemarshalSign);
           /*   list,baos,context.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                context.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                  context.getRealPath("/WEB-INF/img/fireMarshalSignature.jpg"));  */         
          request.setAttribute("HS_PDF",pdf);
          return mapping.findForward("pdf");
        } 
        else if (method.equals("sendEmail")) 
        {
          sendEmail(request);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          return mapping.findForward("sendEmailForm");
        }
         else if (method.equals("viewFees")) 
        { String permitNumber = request.getParameter("permitNumber");
          DfbsFireworksPermit permit=new DfbsFireworksPermit();
          permit.setPermitNumber(permitNumber);
          permit.setPermitFees(pDAO.selectPermitFees(permit.getPermitNumber()));
          session.setAttribute("PERMIT_FEE",permit);
           return mapping.findForward("view");
        }
        else if (method.equals("sendEmailForm")) 
        {
          return mapping.findForward("sendEmailForm");
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
    List charNav = uDAO.getCharacterNavs(DfbsFireworksSQL.SELECT_FIRST_LETTER_OPTIONS);
    List otherStates = uDAO.getOptions(DfbsFireworksSQL.SELECT_OTHER_STATES_OPTIONS);
    List cities = uDAO.getOptions(DfbsFireworksSQL.SELECT_CITIES_OPTIONS);
     List otherCounties = uDAO.getOptions(DfbsFireworksSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("DFBS_OTHER_STATES_OPTIONS",otherStates);
    request.setAttribute("DFBS_CITIES_OPTIONS",cities);
    request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
    
 }
 protected static void setFilterSession(HttpServletRequest request,DfbsOwnerForm filterForm) throws Exception
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
     else if(filter.equals("byOtherState"))  
    { 
       filterSession.setValue(filterForm.getState());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(filterForm.getCity());
     }
      else if(filter.equals("byCounty"))  
    { 
       filterSession.setValue(filterForm.getCounty());
     }
      else if(filter.equals("byFacAddress"))  
    { 
       filterSession.setValue(filterForm.getStreet1());
     }
      else if(filter.equals("byPermit"))  
    { 
       filterSession.setValue(filterForm.getStreet2());
     }
     session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,DfbsFireworksOwnerDAO fwDAO,DfbsOwnerForm ownerForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
      setFilterSession(request,ownerForm);
     HsFilter filterSession = (HsFilter) session.getAttribute("DFBS_OWNER_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
        list = fwDAO.selectOwnersList(DfbsFireworksSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOwnersList(DfbsFireworksSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue());
        session.setAttribute("SEARCH_PERMIT","");
        session.setAttribute("SEARCH_STREET1","");
     } 
     else if(filterSession.getType().equals("byOtherState")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsFireworksSQL.SELECT_OWNERS_BY_OTHER_STATE,filterSession.getValue());
        ownerForm.setState(filterSession.getValue());
        session.setAttribute("SEARCH_PERMIT","");
        session.setAttribute("SEARCH_STREET1","");
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsFireworksSQL.SELECT_OWNERS_BY_CITY,filterSession.getValue());
        ownerForm.setCity(filterSession.getValue());
        session.setAttribute("SEARCH_PERMIT","");
        session.setAttribute("SEARCH_STREET1","");
     } 
      else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = fwDAO.selectOwnersList("byCounty",filterSession.getValue());
        ownerForm.setCounty(filterSession.getValue());
        session.setAttribute("SEARCH_PERMIT","");
        session.setAttribute("SEARCH_STREET1","");
     } 
    else if(filterSession.getType().equals("byFacAddress")) 
     {
         if (filterSession.getValue() !=null && filterSession.getValue().length() >=3)
        {
        list = fwDAO.selectOwnersList(DfbsFireworksSQL.SELECT_OWNERS_BY_STREET1+" AND b.ADDRESS1 LIKE upper('%"+filterSession.getValue()+"%')",null);
        session.setAttribute("SEARCH_PERMIT","");
        session.setAttribute("SEARCH_STREET1","");
        }
        else
        {
          session.setAttribute("SEARCH_STREET1","ERROR");
        }
        ownerForm.setStreet1(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byPermit")) 
     {
         
         if (filterSession.getValue() !=null && filterSession.getValue().length() >3)
        {
        list = fwDAO.selectOwnersList("byPermit",filterSession.getValue());
        ownerForm.setStreet2(filterSession.getValue());
        session.setAttribute("SEARCH_PERMIT","");
        session.setAttribute("SEARCH_STREET1","");
        }
        else
        {
          session.setAttribute("SEARCH_PERMIT","ERROR");
        }
        
     } 
     request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
     
  }
  protected static DfbsOwner setView(HttpServletRequest request,
    DfbsFireworksOwnerDAO fwDAO,
      DfbsOwnerForm ownerForm,String id) throws Exception
  {
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    String permitYear =(String) session.getAttribute("PERMIT_YEAR");
    String renewYear =(String) session.getAttribute("RENEW_YEAR"); 
    
    DfbsOwner owner = fwDAO.selectOwner(id,permitYear,renewYear);
   
    session.setAttribute("DFBS_OWNER",owner);
    session.setAttribute("DFBS_OWNER_APPLICATION",null);
    ownerForm.setProperties(owner);
    
    return owner;
  }
   protected static  void setViewCounty(HttpServletRequest request,
    DfbsFireworksOwnerDAO fwDAO,
      DfbsOwnerForm ownerForm,String county, String recFlag) throws Exception
  {
    List ownerList = fwDAO.selectOwnerCounty(county,recFlag);
     request.setAttribute("DFBS_OWNER_COUNTY_LIST",new HsListWrapper(ownerList));
     
  }
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            //String[] emailTo = {"rpickard@dhs.in.gov","cclouse@dhs.in.gov"};
            //String[] bccTo =   {"nnimmagadda@dhs.in.gov"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
            sb.append("ip: ").append(request.getRemoteAddr()).append("\n\r");
          
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append("* From Fireworks Online * - ").append(subject);
          
          
          
          //  mail.createMail("fireworks_online@dhs.in.gov",emailTo,null,sub.toString(),sb.toString());
           // mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  }
  
  
 
 
}