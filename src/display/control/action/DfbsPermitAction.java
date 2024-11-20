package display.control.action;

import display.control.*;
import display.to.*;
import display.data.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import display.control.form.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import main.to.*;
import main.data.*;
import hs.report.pdf.*;

public class DfbsPermitAction extends ControlAction

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
        
        
        DfbsDisplayForm displayForm = (DfbsDisplayForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_OWNER);
        DfbsDisplayDAO pDAO = (DfbsDisplayDAO) dmap2.getHsDAO(DfbsDataMap.DISPLAY_PERMIT);
         ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts = ( ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        if (method == null) 
        { DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          if (selectedOwner.getOwnerId() == 0)
          { setOptions(request,dfbsUtilityDAO); 
          session.setAttribute("PERMIT_SELECTED", new DfbsDisplay());
          session.setAttribute("DISPLAY_DATE_SELECTED","");
            return mapping.findForward("newPermit");
          }
          else
          {setOptions(request,dfbsUtilityDAO); 
          DfbsDisplay selectedPermit = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
          DfbsDisplay display = pDAO.selectPermit(selectedPermit.getDisplayIdNumber());
          if (display.getDisplayIdNumber() == null || display.getDisplayIdNumber().equals(""))
          { session.setAttribute("DISPLAY_DATE_SELECTED","");
          session.setAttribute("PERMIT_SELECTED", new DfbsDisplay());
            return mapping.findForward("newPermit");
          }
          else
          { 
            displayForm.setProperties(display);
            session.setAttribute("PERMIT_SELECTED",display);
             return mapping.findForward("renewPermit");
          
          }
          }
        } 
        else if (method.equals("update")) 
        {
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
        String url = request.getRemoteAddr();
       
        if(security !=null ) 
            {
              String displayKey= request.getParameter("displayKey");
                DfbsDisplay selectedPermit = pDAO.selectPermitUpdate(displayKey);
                request.setAttribute("county",selectedPermit.getDisplayCounty());
                setOptions(request,dfbsUtilityDAO);
                session.setAttribute("PERMIT_SELECTED",selectedPermit);
                session.setAttribute("DISPLAY_DATE_SELECTED",selectedPermit);
                 session.setAttribute("PERMIT_UPDATE","N");
                displayForm.setProperties(selectedPermit);
                 return mapping.findForward("updatePermit");          
            }
            else
           {
              return mapping.findForward("logOn"); 
            }
        }
         else if (method.equals("viewPermit")) 
       
        { String displayKey= request.getParameter("displayKey");
            DfbsDisplay selectedPermit = pDAO.selectPermitUpdate(displayKey);
           // request.setAttribute("county",selectedPermit.getDisplayCounty());
            setOptions(request,dfbsUtilityDAO);
            selectedPermit.setFileList(pDAO.selectFileList(request.getParameter("displayKey")));
            session.setAttribute("PERMIT_SELECTED",selectedPermit);
            session.setAttribute("DISPLAY_DATE_SELECTED",selectedPermit);
            displayForm.setProperties(selectedPermit);
             return mapping.findForward("viewPermit");          
        }
        else if (method.equals("saveUpdate")) 
        {
         DfbsDisplay display = displayForm.getDfbsDisplay();
         session.setAttribute("PERMIT_UPDATE","Y");
         pDAO.updatePermit(display);
         String FDEmail=display.getDisplayFdEmail();
         if (display.getDisplayOnlineStatus().equals("Approved"))
         {
         String contactEmail= pDAO.selectContactEmail(display.getDisplayOwnerId());
         
           if (FDEmail !=null && FDEmail.trim().length()  >5) 
           {
           sendEmailFd(request,FDEmail,display,contacts);
           }
         
          if (contactEmail !=null && contactEmail.trim().length()  >5) 
           {
           sendEmailFd(request,contactEmail,display,contacts);
           }  
         } 
           setOptions(request,dfbsUtilityDAO);
           displayForm.setProperties(display);
             return mapping.findForward("updatePermit");          
        
        }
        // need to set form properties here 
        else if (method.equals("checkOut")) 
        { setOptions(request,dfbsUtilityDAO); 
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsDisplay display = displayForm.getDfbsDisplay();
          DfbsDisplayForm errorForm = new DfbsDisplayForm();
          if (validate(display,errorForm,session,pDAO) )
          {
          session.setAttribute("PERMIT_SELECTED",display);
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           // to see if owner in cart
          String displayNew="";
          String ownerCartCheck ="N";
          Map mapOwner = cart.getOwnerMap();
          Set ownerkeys = mapOwner.keySet();
          Iterator i = ownerkeys.iterator();
           while(i.hasNext())
           { 
           String key = (String) i.next();
           DfbsOwner owner = (DfbsOwner) mapOwner.get(key); 
           if (owner.getOwnerId()==selectedOwner.getOwnerId())
           {
             session.setAttribute("IN_MAP_OWNER",owner);
             ownerCartCheck ="Y";
           }
           }
          if (ownerCartCheck =="Y")
          { 
            DfbsOwner inMapOwner = (DfbsOwner) session.getAttribute("IN_MAP_OWNER");
            inMapOwner.addPermit(display);
            if (display.getDisplayIdNumber().equals(""))
            { displayNew="Y";
            }
            else
            { displayNew="N";
            }
            pDAO.computeFees(cart,displayNew);
            setOptions(request,dfbsUtilityDAO); 
            displayForm.setProperties(display);
             if (display.getDisplayDatesMapCount() ==0)
            {
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/display/displayDate.do?method=addDate");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
            }
            else
            {
              return mapping.findForward("renewPermit");
            }
          }
          else
          {
          selectedOwner.addPermit(display);
          cart.addOwner(selectedOwner);
          if (display.getDisplayIdNumber().equals(""))
            { displayNew="Y";
            }
            else
            { displayNew="N";
            }
          pDAO.computeFees(cart,displayNew);
          setOptions(request,dfbsUtilityDAO); 
          displayForm.setProperties(display);
            if (display.getDisplayDatesMapCount()==0)
            {
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/display/displayDate.do?method=addDate");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
            }
            else
            {
              return mapping.findForward("renewPermit");
            }
          }
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERMIT_ERROR",errorForm);
            displayForm.setProperties(display);
           session.setAttribute("PERMIT_SELECTED",display);
           return mapping.findForward("renewPermit");
          }
        } 
        else if (method.equals("checkOutNew")) 
        { DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsDisplay display = displayForm.getDfbsDisplay();
          DfbsDisplayForm errorForm = new DfbsDisplayForm();
          if (validate(display,errorForm,session,pDAO) )
          {
          session.setAttribute("PERMIT_SELECTED",display);
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          setOptions(request,dfbsUtilityDAO); 
          displayForm.setProperties(display);
          selectedOwner.addPermit(display);
          if (selectedOwner.getOwnerKey() == null || selectedOwner.getOwnerKey().equals(""))
          {
          cart.addOwner(selectedOwner);
          }
          pDAO.computeFees(cart,"N");
          return mapping.findForward("newPermit");
           
          
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERMIT_ERROR",errorForm);
            displayForm.setProperties(display);
           session.setAttribute("PERMIT_SELECTED",display);
           return mapping.findForward("newPermit");
          }
        } 
      
        else if (method.equals("newPermit")) 
         {  setOptions(request,dfbsUtilityDAO); 
          session.setAttribute("DISPLAY_DATE_SELECTED","");
            return mapping.findForward("newPermit");
          }
            else if (method.equals("addPermit")) 
         {  String ownerKey= request.getParameter("ownerKey");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner selectedOwner = cart.getOwner(ownerKey);
           if (selectedOwner.getOwnerLastName()==null)
           {
           selectedOwner = oDAO.selectOwner(Integer.parseInt(ownerKey));
           }
           session.setAttribute("OWNER_SELECTED",selectedOwner);
            setOptions(request,dfbsUtilityDAO); 
            return mapping.findForward("newPermit");
          }
         else if (method.equals("permitInCart")) 
         {  setOptions(request,dfbsUtilityDAO);
            DfbsDisplay selectedPermit = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
            displayForm.setProperties(selectedPermit);
             return mapping.findForward("renewPermit");
          }  
          else if (method.equals("backToPermit")) 
         {  setOptions(request,dfbsUtilityDAO);
            DfbsDisplay selectedPermit = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
            displayForm.setProperties(selectedPermit);
             return mapping.findForward("renewPermit");
          }  
        else if (method.equals("renewPermit")) 
         {  String ownerKey= request.getParameter("ownerKey");
            String displayKey= request.getParameter("displayKey");
            setOptions(request,dfbsUtilityDAO);
            DfbsDisplay selectedPermit = pDAO.selectPermit(displayKey);
            session.setAttribute("PERMIT_SELECTED",selectedPermit);
            displayForm.setProperties(selectedPermit);
             return mapping.findForward("newPermit");
          }  
          else if (method.equals("editPermit")) 
        {  
           String ownerKey= request.getParameter("ownerKey");
           String displayKey= request.getParameter("displayKey");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner selectedOwner = cart.getOwner(ownerKey);
           session.setAttribute("OWNER_SELECTED",selectedOwner);
           DfbsDisplay editPermit = selectedOwner.getPermit(displayKey);
           session.setAttribute("PERMIT_SELECTED",editPermit);
           displayForm.setProperties(editPermit);
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("newPermit");
         
        } 
         else if (method.equals("saveEditPermit")) 
        { DfbsDisplay updatedPermit = displayForm.getDfbsDisplay();
          DfbsDisplayForm errorForm = new DfbsDisplayForm();
          if (validate(updatedPermit,errorForm,session,pDAO) ) {
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsDisplay display = selectedOwner.getPermit(updatedPermit.getDisplayPermitKey());
          displayForm.setUpdatedProperties(updatedPermit,display);
            session.setAttribute("PERMIT_SELECTED",display);
             displayForm.setProperties(display);
             return mapping.findForward("renewPermit");
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERSON_ERROR",errorForm);
           displayForm.setProperties(updatedPermit);
           return mapping.findForward("editPermit");
          }
          } 
           else if (method.equals("removePermit")) 
        {  String ownerKey= request.getParameter("ownerKey");
           String displayKey= request.getParameter("displayKey");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner selectedOwner = cart.getOwner(ownerKey);
           DfbsDisplay removePermit = selectedOwner.getPermit(displayKey);
           cart.setAmount(cart.getAmount()-removePermit.getAmount());
           cart.setTotalPermits(cart.getTotalPermits()-1);
          selectedOwner.removePermit(displayKey);
          setOptions(request,dfbsUtilityDAO); 
          DfbsDisplay selectedPermit = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
          displayForm.setProperties(selectedPermit);
             return mapping.findForward("renewPermit");
        } 
     /*   to finish later */
         else if(method.equals("printPermit")) 
        { String displayKey= request.getParameter("displayPermitNumber");
          StringBuffer redirectUrl = new StringBuffer("https://oasdev.dhs.in.gov/reports/rwservlet?");
          redirectUrl.append("dfbsipnpdf&report=fire_display_fireworks_permit.rdf&p_number="+displayKey);
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
         return null;
       }  
         else if (method.equals("goToUpload")) 
         { 
          String displayKey= request.getParameter("displayKey");
          
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
           DfbsDisplay display = selectedOwner.getPermit(displayKey);
             session.setAttribute("PERMIT_SELECTED",display);
           display.setFileList(pDAO.selectFileList(display.getDisplayIdNumber()));
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
         }
          else if (method.equals("goToUploadInternal")) 
         { 
          String displayKey= request.getParameter("displayKey");
            DfbsDisplay display = pDAO.selectPermit(displayKey);
            display.setDisplayPermitKey(display.getDisplayIdNumber());
           display.setFileList(pDAO.selectFileList(display.getDisplayIdNumber()));
           session.setAttribute("PERMIT_SELECTED",display);
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
         }
          else if (method.equals("uploadFile")) 
         { 
         setOptions(request,dfbsUtilityDAO); 
         final FormFile oneFile = displayForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {   String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {setOptions(request,dfbsUtilityDAO); 
                  DfbsDisplay display = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
                   pDAO.uploadFile(oneFile,display.getDisplayPermitKey());    
                  display.setDisplayUploadError(true);
                  display.setFileList(pDAO.selectFileList(display.getDisplayIdNumber()));  
                  }
                  else
                  {  session.setAttribute("FILE_EXTENSION","ERROR");
                     return mapping.findForward("goToUpload");
         
                  }
                  
            }
            DfbsDisplay selectedPermit = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
          displayForm.setProperties(selectedPermit);
          session.setAttribute("APPLICATION_ERROR","false");
           return mapping.findForward("renewPermit");
         }
         
           else if (method.equals("downLoadFile")) 
         { 
          String fileName = request.getParameter("fileName");
          String fileType = request.getParameter("fileType");
           int fileId = Integer.parseInt(request.getParameter("fileId"));
          doGetFile(request,response,pDAO,fileName,fileType,fileId);
           return null;
         }
          else if (method.equals("uploadFile")) 
         { 
         setOptions(request,dfbsUtilityDAO); 
         final FormFile oneFile = displayForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {   String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {setOptions(request,dfbsUtilityDAO); 
                  DfbsDisplay display = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
                  pDAO.uploadFile(oneFile,display.getDisplayPermitKey());    
                  display.setDisplayUploadError(true);
                  display.setFileList(pDAO.selectFileList(display.getDisplayIdNumber()));  
                  }
                  else
                  {  session.setAttribute("FILE_EXTENSION","ERROR");
                     return mapping.findForward("goToUpload");
         
                  }
                  
            }
            DfbsDisplay selectedPermit = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
          displayForm.setProperties(selectedPermit);
          session.setAttribute("APPLICATION_ERROR","false");
           return mapping.findForward("renewPermit");
         }
         
         else if (method.equals("approve")) 
         { DfbsDisplay display = (DfbsDisplay) session.getAttribute("PERMIT_SELECTED");
           display.setDisplayOnlineStatus("Approved");
           pDAO.updatePermit(display);
           String FDEmail=display.getDisplayFdEmail();
           String contactEmail= pDAO.selectContactEmail(display.getDisplayOwnerId());
           if (FDEmail.trim().length()  >5) 
           {
           sendEmailFd(request,FDEmail,display,contacts);
           }
         /*  if (contactEmail.trim().length()  >5) 
           {
           sendEmailFd(request,contactEmail,display,contacts);
           }      */     
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/display/start.do?method=lookupNew");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
          else if (method.equals("goToUploadInternal")) 
         { 
          String displayKey= request.getParameter("displayKey");
            DfbsDisplay display = pDAO.selectPermit(displayKey);
           display.setFileList(pDAO.selectFileList(display.getDisplayIdNumber()));
           session.setAttribute("PERMIT_SELECTED",display);
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
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
    List charNav = uDAO.getCharacterNavs(DfbsDisplaySQL.SELECT_FIRST_LETTER_OPTIONS);
    List counties = uDAO.getOptions(DfbsDisplaySQL.SELECT_COUNTY_OPTIONS);
    List cities = uDAO.getOptions(DfbsDisplaySQL.SELECT_CITIES_OPTIONS);
    request.setAttribute("OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("OWNER_COUNTY_OPTIONS",counties);
    request.setAttribute("OWNER_CITIES_OPTIONS",cities);
   
 }
 
 protected static boolean validate(DfbsDisplay display,DfbsDisplayForm errorForm,HttpSession session,DfbsDisplayDAO pDAO) throws Exception
  {
  
   ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
    boolean noError = true;
    display.setDisplayUploadError(true);
  display.setDisplayDateError(true);
    
   if(display.isNew() == true) {
    if(display.getShooterName() == null || display.getShooterName().trim().equals("")  ) 
    { 
      errorForm.setShooterName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setShooterName("");
    }
    if(display.getCompanyName() == null || display.getCompanyName().trim().equals("")  ) 
    { 
      errorForm.setCompanyName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCompanyName("");
    }
    
    if(display.getDisplayAddress1() == null || display.getDisplayAddress1().trim().equals("")  ) 
    { 
      errorForm.setDisplayAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDisplayAddress1("");
    }
    if(display.getDisplayCity() == null || display.getDisplayCity().trim().equals("") ) 
    { 
      errorForm.setDisplayCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDisplayCity("");
    }
    
    if(display.getDisplayState() == null || display.getDisplayState().trim().equals("") ) 
    { 
      errorForm.setDisplayState("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setDisplayState("");
    }
    
    if (security ==null)
    {
    if(display.getDisplayZip() == null || display.getDisplayZip().trim().equals("") ) 
    { 
      errorForm.setDisplayZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDisplayZip("");
    }
    
    }
    if((display.getDisplayCounty() == null || display.getDisplayCounty().trim().equals("") ) ) 
    {
      errorForm.setDisplayCounty("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDisplayCounty("");
    }
    
  }
  
   if ((display.getDisplayPhone() == null || display.getDisplayPhone().trim().equals("") ||
       display.getDisplayPhone().indexOf('-') > 0 || display.getDisplayPhone().indexOf(')') > 0 ||
       display.getDisplayPhone().indexOf('(') > 0))
    { 
      errorForm.setDisplayPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDisplayPhone("");
    }
    if(display.getDisplayFdEmail() == null || display.getDisplayFdEmail().trim().equals("") ||
       display.getDisplayFdEmail().indexOf('@') <=0 || display.getDisplayFdEmail().indexOf('.') <= 0 ) 
    {
      errorForm.setDisplayFdEmail("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDisplayFdEmail("");
    }
    int uploadCount = pDAO.CountFileList(display.getDisplayPermitKey());
    if (uploadCount == 0)
    {
      display.setDisplayUploadError(false);
    }
    else
    {
      display.setDisplayUploadError(true);
    }
    if( display.getDisplayDatesMapCount() ==0)
    {
     display.setDisplayDateError(false); 
    }
    else
    {
       display.setDisplayDateError(true); 
    }
    display.setDisplayError(noError);
    return noError;
  }
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsDisplaySQL.SELECT_STATE_OPTIONS);
    List states2 = uDAO.getOptions(DfbsDisplaySQL.SELECT_STATE_OPTIONS2);
     List counties =  uDAO.getOptions(DfbsDisplaySQL.SELECT_COUNTY_LIST_OPTIONS);
     String county = (String)request.getAttribute("county");
     List fds = null;
     if(county !=null)
      fds =  uDAO.getOptions(DfbsDisplaySQL.SELECT_FIRE_DEPT_OPTIONS_COUNTY,county);
     else
      fds =  uDAO.getOptions(DfbsDisplaySQL.SELECT_FIRE_DEPT_OPTIONS);  
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("DISPLAY_STATE_OPTIONS",states2);
    request.setAttribute("DISPLAY_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
     
     
    
    
 } 
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,DfbsDisplayDAO pDAO,String fileName,String fileType,int fileId) throws Exception 
 {  
   try {response.setContentType(fileType); 
     response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
    java.io.OutputStream out = response.getOutputStream(); 
    int lenFile = pDAO.downLoad(out,fileId);
    out.flush(); 
    out.close();
   } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
            }
    

} 
private static void sendEmailFd(HttpServletRequest request,String FdEmail,DfbsDisplay display,ApplicationContacts contacts) throws Exception
  {
          try {
            
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),display.getDisplayFdEmail(),contacts.getContact2Email()};
           // String[]   emailTo ={"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
           
          
            StringBuffer sb = new StringBuffer();
            sb.append( display.getDisplayIdNumber() + " is ready for inspection").append("\n\r");
            sb.append("Use this link to view display:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/display/display.do?method=viewPermit&displayKey="+display.getDisplayIdNumber()).append("\n\r");
            sb.append("Use this link to print display:").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_display_fireworks_permit.rdf&p_number="+display.getDisplayIdNumber()+".").append("\n\r");
             sb.append(" If for any reason, this shoot will not be approved by the local Fire Department, please contact this office immediately at " +
                       contacts.getContact1Phone());
             StringBuffer sub = new StringBuffer();
            sub.append(" From fire display inspection ready alert ");
        
          
            mail.createMail("fireDisplay_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e)
            {
               HsMailer mail1 = new HsMailer();
            mail1.createMail("fireDisplay_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire display inspection ready alert error  ","displayCompleteCheckoutAction");
            mail1.sendAndClose(); 
            }
  }
}
