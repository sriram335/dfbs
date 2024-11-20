package childcare.control.action;

import childcare.control.form.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import childcare.data.*;
import childcare.to.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;

import main.data.*;
import main.to.*;

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
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts = sDAO.setContacts("CHILDCARE_CONTACT1","CHILDCARE_CONTACT2");
      
       session.setAttribute("APPLICATION_CONTACTS",contacts); 
       String conType ="CHILDCARE_CONTACT";
       session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
       String structureType = (String) session.getAttribute("STRUCTURE_TYPE");
        String appLocation ="/dfbs/childCare/start.do";
         session.setAttribute("APPLICATION_LOCATION",appLocation); 
        String appHeading ="Unlicensed Registered Child Care Ministry Online Application For Fire and Building Safety Division";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
        if (method == null) 
        {   ShoppingCart cart = new ShoppingCart(); 
            session.setAttribute("SHOPPING_CART",cart);
            FileNames names= sDAO.selectAppStatus("REG_MINI_MAINT_FLAG");
            session.setAttribute("REG_MINI_APP_STATUS",names.getFileName());
            session.setAttribute("REG_MINI_APP_MESSAGE",names.getFileType());
            return mapping.findForward("start");
        } 
        else if (method.equals("backToStart")) 
        { 
           return mapping.findForward("start");
        } 
        
        else if (method.equals("renewBy")) 
        {
        
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/childCare/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
         else if (method.equals("newPermitNewOwner")) 
        {
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("newOwner");
        } 
         else if (method.equals("newPermitOldOwner")) 
        {
        
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/childCare/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewByOwner")) 
        {session.setAttribute("PERMIT_FEE",null);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/childCare/main.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        } 
        else if (method.equals("renewByStreetNumber")) 
        {
        String streetNumber=startForm.getStreetNumber();
         if (streetNumber.equals(""))
          {setFilterOptions(request,dfbsUtilityDAO);
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setStreetNumber("");
            request.setAttribute("START_FORM_ERROR",errorForm);
            return mapping.findForward("renewByStreetNumber");
          }
          else
          {
           List list = null;
           setFilterOptions(request,dfbsUtilityDAO);
           list = pDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_ADDRESS,streetNumber,structureType);
          request.setAttribute("DFBS_CHILDCARE_LIST",new HsListWrapper(list));
           return mapping.findForward("renewByStreetNumber");
          }
        } 
        
        else if (method.equals("renewByPermitNumber")) 
        { String permitNumber=startForm.getPermitNumber();
          if (permitNumber.trim().equals(""))
          {setFilterOptions(request,dfbsUtilityDAO);
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setPermitNumber("ERROR");
            request.setAttribute("START_FORM_ERROR",errorForm);
            return mapping.findForward("renewByPermitNumber");
          }
          
          else
          {
         List list = null;
           setFilterOptions(request,dfbsUtilityDAO);
           list = pDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_ID,permitNumber,structureType);
           
          request.setAttribute("DFBS_CHILDCARE_LIST",new HsListWrapper(list));
           return mapping.findForward("renewByPermitNumber");
          }
        } 
        
         else if (method.equals("backToPermit")) 
        { 
         String permitNumber = request.getParameter("permitNumber");
           List list = null;
           setFilterOptions(request,dfbsUtilityDAO);
           list = pDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_ID,permitNumber,structureType);
          request.setAttribute("DFBS_CHILDCARE_LIST",new HsListWrapper(list));
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
       //   list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_STATUS,"NEW","byStatus","xx");
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
          
          
          
            mail.createMail("fire_childCare_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("childCare_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Entertainment Online * - error email ","DfbsStartAction");
            mail1.sendAndClose();            }
  }
  
protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsChildcareSQL.SELECT_STATE_OPTIONS);
     request.setAttribute("OWNER_STATE_OPTIONS",states);
 
 
   
 }
  protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List charNav = uDAO.getCharacterNavs(DfbsChildcareSQL.SELECT_FIRST_LETTER_OPTIONS);
    List otherCounties = uDAO.getOptions(DfbsChildcareSQL.SELECT_COUNTIES_OPTIONS);
    List cities = uDAO.getOptions(DfbsChildcareSQL.SELECT_CITIES_OPTIONS);
    
    request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
    request.setAttribute("DFBS_CITIES_OPTIONS",cities);
   
 }
}