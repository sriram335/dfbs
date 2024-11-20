package magazine.control.action;
import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.to.*;
import main.data.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
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
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
         String conType ="MAGAZINE_CONTACT";
       session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
        String appLocation ="/dfbs/magazine/start.do";
         session.setAttribute("APPLICATION_LOCATION",appLocation); 
        if (method == null) 
        {   ShoppingCart cart = new ShoppingCart(); 
            session.setAttribute("SHOPPING_CART",cart);
              ApplicationContacts contacts = sDAO.setContacts("MAGAZINE_CONTACT1","MAGAZINE_CONTACT2");
            session.setAttribute("APPLICATION_CONTACTS",contacts);
            String appHeading ="IDHS Fire Explosive Magazine Permit(s) Online Application";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
          FileNames names= sDAO.selectAppStatus("MAG_PERMITS_MAINT_FLAG");
            session.setAttribute("MAG_PERMITS_APP_STATUS",names.getFileName());
            session.setAttribute("MAG_PERMITS_APP_MESSAGE",names.getFileType());
            return mapping.findForward("start");
        } 
        else if (method.equals("backToStart")) 
        {
          return mapping.findForward("start");
        } 
        
       
        else if (method.equals("renewBy")) 
        {
        
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
          redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
        else if (method.equals("renewByOwner")) 
        {
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do");
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
            return mapping.findForward("renewBy");
          }
          else
          {
           List list = null;
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_STREET,streetNumber,"byStreet",streetNumber,pDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
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
            return mapping.findForward("renewBy");
          }
          else
          {
          List list = null;
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_PERMIT,permitNumber,"byPermit",permitNumber,pDAO);
              setOptions(request,dfbsUtilityDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          }
        } 
        else if (method.equals("renewByIdNumber")) 
        { String permitNumber=startForm.getIdNumber();
          if (permitNumber.trim().equals(""))
          {
            DfbsStartForm errorForm = new DfbsStartForm();
            errorForm.setIdNumber("ERROR");
            request.setAttribute("START_FORM_ERROR",errorForm);
            return mapping.findForward("renewBy");
          }
          else
          {
          List list = null;
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_ID,permitNumber,"byMagId",permitNumber,pDAO);
              setOptions(request,dfbsUtilityDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          }
        } 
        else if (method.equals("backToIdNumber")) 
        { String magIdNumber = request.getParameter("magIdNumber");
          
          List list = null;
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_ID,magIdNumber,"byMagId",magIdNumber,pDAO);
          setOptions(request,dfbsUtilityDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          
        } 
         else if (method.equals("showAllPermits")) 
        { String ownerId = request.getParameter("ownerId");
          List list = null;
          setOptions(request,dfbsUtilityDAO);
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_OWNER_ID,ownerId,"All",ownerId,pDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByPermitNumber");
          
        } 
             else if (method.equals("viewFees")) 
        { String magIdNumber = request.getParameter("magIdNumber");
          DfbsPermit permit=new DfbsPermit();
          permit.setMagIdNumber(magIdNumber);
          permit.setPermitFees(pDAO.selectPermitFees(permit.getMagIdNumber()));
          session.setAttribute("PERMIT_FEE",permit);
           List list = null;
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_ID,permit.getMagIdNumber(),"byMagId",permit.getMagIdNumber(),pDAO);
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
          list = oDAO.selectOwnersList(DfbsSQL.SELECT_OWNERS_BY_STATUS,"NEW","byStatus","xx",pDAO);
          request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
          return mapping.findForward("renewByStreetNumber");
         
        } 
         else if (method.equals("lookupNewPermits")) 
        {
          List list = null;
          list = oDAO.selectNewOwnersList(pDAO);
          request.setAttribute("NEW_PERMITS_LIST",new HsListWrapper(list));
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("newPermitList");
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
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 

  
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov"};
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
            sub.append(" From Magazine Online  - ").append(subject);
          
          
          
            mail.createMail("fire_magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Magazine Online  - alert error  ","DfbsStartAction");
            mail1.sendAndClose();            
          }
  }
  
protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS);
     request.setAttribute("OWNER_STATE_OPTIONS",states);
 List charNav = uDAO.getCharacterNavs(DfbsSQL.SELECT_FIRST_LETTER_OPTIONS);
    List counties = uDAO.getOptions(DfbsSQL.SELECT_COUNTY_OPTIONS);
    List cities = uDAO.getOptions(DfbsSQL.SELECT_CITIES_OPTIONS);
    request.setAttribute("OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("OWNER_COUNTY_OPTIONS",counties);
    request.setAttribute("OWNER_CITIES_OPTIONS",cities);
    List states2 = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS2);
    List mags =  uDAO.getOptions(DfbsSQL.SELECT_MAGAZINE_TYPE_OPTIONS);
     List fds =  uDAO.getOptions(DfbsSQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("MAGAZINE_STATE_OPTIONS",states2);
     request.setAttribute("MAGAZINE_TYPE_OPTIONS",mags);
     request.setAttribute("MAGAZINE_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
   
 }
 
}
