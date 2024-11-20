package code.control.action;
import code.to.*;
import code.control.form.*;
import code.data.* ;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import main.data.* ;
import main.to.* ;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
public class CodeAction extends ControlAction
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
        
       CodeManufacturerForm manufacturerForm = (CodeManufacturerForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CodeManufacturerDAO oDAO = (CodeManufacturerDAO) dmap2.getHsDAO(DfbsDataMap.MANUFACTURER);
        CodeFacilityDAO pDAO = (CodeFacilityDAO) dmap2.getHsDAO(DfbsDataMap.FACILITY);
         ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
         HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        
         if (method == null) 
        {ApplicationContacts contacts = sDAO.setContacts("CODE_CONTACT1","CODE_CONTACT2");
       session.setAttribute("APPLICATION_CONTACTS",contacts);
       String conType ="CODE_CONTACT";
       session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
        String appLocation ="/dfbs/code/start.do";
         session.setAttribute("APPLICATION_LOCATION",appLocation); 
        String appHeading ="Industrialized Building Systems/ Mobile Structures - Purchase Seals / Apply for CDR(s) ";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
          setList(request,oDAO,manufacturerForm);
          setFilterOptions(request,dfbsUtilityDAO);
           FileNames names= sDAO.selectAppStatus("IND_HOUSING_MAINT_FLAG");
            session.setAttribute("CODE_CDR_APP_STATUS",names.getFileName());
            session.setAttribute("CODE_CDR_APP_MESSAGE",names.getFileType());
          return mapping.findForward("main");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,manufacturerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,manufacturerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,oDAO,manufacturerForm);
          return mapping.findForward("main");
        } 
      
        else if (method.equals("view")) 
        {
          int id = Integer.parseInt(request.getParameter("manufacturerNameId"));
          this.setView(request,oDAO,manufacturerForm,id,pDAO);
          return mapping.findForward("view");
        }
        if(method.equals("editManufacturer")) 
        
        {   return mapping.findForward("editManufacturer");
        } 
        
                 
         if(method.equals("saveManufacturer")) 
         
        {   CodeManufacturer manufacturer = (CodeManufacturer) session.getAttribute("MANUFACTURER"); 
            manufacturer.setManufacturerWebPage(manufacturerForm.getManufacturerWebPage());
            return mapping.findForward("view");
         } 
           if(method.equals("viewRelease")) 
        {   
                   return mapping.findForward("viewRelease");
         }    
          else if (method.equals("startOverForm")) 
        {
          return mapping.findForward("startOverForm");
        }
        else if (method.equals("startOver")) 
        {
          session.setAttribute("MANUFACTURER",null);
           setList(request,oDAO,manufacturerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
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
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List charNav = uDAO.getCharacterNavs(CodeSQL.SELECT_FIRST_LETTER_OPTIONS);
    List otherStates = uDAO.getOptions(CodeSQL.SELECT_OTHER_STATES_OPTIONS);
    List cities = uDAO.getOptions(CodeSQL.SELECT_CITIES_OPTIONS);
    
    request.setAttribute("MANUFACTURER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("MANUFACTURER_OTHER_STATES_OPTIONS",otherStates);
    request.setAttribute("MANUFACTURER_CITIES_OPTIONS",cities);
   
 }
 protected static void setFilterSession(HttpServletRequest request,CodeManufacturerForm filterForm) throws Exception
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
       filterSession.setValue(filterForm.getManufacturerState());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(filterForm.getManufacturerCity());
     }
     session.setAttribute("MANUFACTURER_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,CodeManufacturerDAO fwDAO,CodeManufacturerForm manufacturerForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("MANUFACTURER_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("MANUFACTURER_LIST_FILTER",filterSession);
        list = fwDAO.selectManufacturerList(CodeSQL.SELECT_MANUFACTURER_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectManufacturerList(CodeSQL.SELECT_MANUFACTURER_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byOtherState")) 
     {
        
        list = fwDAO.selectManufacturerList(CodeSQL.SELECT_MANUFACTURER_BY_OTHER_STATE,filterSession.getValue());
        manufacturerForm.setManufacturerState(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectManufacturerList(CodeSQL.SELECT_MANUFACTURER_BY_CITY,filterSession.getValue());
        manufacturerForm.setManufacturerCity(filterSession.getValue());
     } 
    
    
     request.setAttribute("MANUFACTURER_LIST",new HsListWrapper(list));
     
  }
  protected static CodeManufacturer setView(HttpServletRequest request,
    CodeManufacturerDAO mDAO,
      CodeManufacturerForm manufacturerForm, int id,CodeFacilityDAO pDAO) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
   
    CodeManufacturer manufacturer = mDAO.selectManufacturer(id,pDAO);
    
    session.setAttribute("MANUFACTURER",manufacturer);
    
    manufacturerForm.setProperties(manufacturer);
    
    return manufacturer;
  }
  
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"tbradshaw@dhs.in.gov","cclouse@dhs.in.gov"};
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
            sub.append(" From code enforcement Online  - ").append(subject);
          
          
          
            mail.createMail("codeEnforcement_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
           HsMailer mail1 = new HsMailer();
            mail1.createMail("codeEnforcement_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From code enforcement Online error email ","CodeAction");
            mail1.sendAndClose();    
           }
  }
  
 
 
 
}
