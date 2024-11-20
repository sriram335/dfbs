package fireworks.control.action;

import fireworks.control.form.*;
import fireworks.to.*;
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
import hs.util.*;
import main.data.*;
import main.to.*;
import hs.data.system.*;
import hs.report.pdf.*;


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
        DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
        DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method == null) 
        {   
            String conType ="FIREWORKS_CONTACT";
       session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
        String appLocation ="/dfbs/fireworks/start.do";
         session.setAttribute("APPLICATION_LOCATION",appLocation); 
          ApplicationContacts contacts = sDAO.setContacts("FIREWORKS_CONTACT1","FIREWORKS_CONTACT2");
            session.setAttribute("APPLICATION_CONTACTS",contacts);
            String appHeading ="Fireworks permit Online Application";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
            ShoppingCart cart = new ShoppingCart(); 
            session.setAttribute("SHOPPING_CART",cart);
             setOptions(request,dfbsUtilityDAO);
             //Commented and changed PERMIT_YEAR from 2015 to 2016 and RENEW_YEAR from 2014 to 2015. Dev 12/27/2015
             //Commented and changed PERMIT_YEAR from 2016 to 2017 and RENEW_YEAR from 2015 to 2016. Dev 12/30/2016
             //Commented and changed PERMIT_YEAR from 2017 to 2018 and RENEW_YEAR from 2016 to 2017. Dev 12/20/2017
          //  session.setAttribute("PERMIT_YEAR","2017");
          //  session.setAttribute("RENEW_YEAR","2016");
          session.setAttribute("PERMIT_YEAR","2020");
          session.setAttribute("RENEW_YEAR","2019");
            session.setAttribute("DFBS_OWNER_LIST_FILTER",null);
            session.setAttribute("DFBS_OWNER_APPLICATION",null);
            //FileNames names= sDAO.selectAppStatus("FIREWORKS_MAINT_FLAG");
            //session.setAttribute("FIREWORKS_APP_STATUS",names.getFileName());
            //session.setAttribute("FIREWORKS_APP_MESSAGE",names.getFileType());
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/fireworks/main.do");
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

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
      
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
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
          
          
          
           // mail.createMail("fire_fireworks_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
           // mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
        //    mail1.createMail("fireworks_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Entertainment Online * - error email ","DfbsStartAction");
           // mail1.sendAndClose();   
           }
  }
  
protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsFireworksSQL.SELECT_STATE_OPTIONS);
     request.setAttribute("OWNER_STATE_OPTIONS",states);
    List years = uDAO.getOptions(DfbsFireworksSQL.SELECT_PERMIT_YEAR);
     request.setAttribute("PERMIT_YEAR_OPTIONS",years);
 
   
 }
 
}