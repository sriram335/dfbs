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

import org.apache.struts.upload.FormFile;

public class DfbsOwnerAction extends ControlAction
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
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        amuseOwnerDAO oDAO = ( amuseOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_OWNER);
        amuseRideDAO rDAO = (amuseRideDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_RIDE);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        insuranceDAO iDAO = (insuranceDAO) dmapNew.getHsDAO(DfbsDataMap2.AMUSE_INSURANCE); 
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts = sDAO.setContacts("AMUSE_CONTACT1","AMUSE_CONTACT2");
        session.setAttribute("APPLICATION_CONTACTS",contacts);
        if (method == null) 
        { 
          ShoppingCart cart = new ShoppingCart(); 
          session.setAttribute("SHOPPING_CART",cart);
          session.setAttribute("AMUSE_ONLINE_APP_STATUS",null);
           FileNames names= sDAO.selectAppStatus("AMUSE_PERMITS_MAINT_FLAG");
          session.setAttribute("AMUSE_PERMITS_APP_STATUS",names.getFileName());
          session.setAttribute("AMUSE_PERMITS_APP_MESSAGE",names.getFileType());
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("start");
        } 
             
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("start");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,ownerForm);
          setList(request,oDAO,ownerForm);
           setFilterOptions(request,dfbsUtilityDAO);
           return mapping.findForward("start");
        } 
        else if (method.equals("view")) 
        {String ownerId = request.getParameter("ownerId");
          DfbsOwner owner = oDAO.selectOwner(Integer.parseInt(ownerId));
          ownerForm.setProperties(owner);
          owner.setFileList(oDAO.selectFileList(ownerId,"AmuseRide")); 
            setOptions(request,dfbsUtilityDAO);
            setFilterOptions(request,dfbsUtilityDAO);
          session.setAttribute("AMUSE_OWNER_SELECTED",owner);
            return mapping.findForward("viewOwner");
        }   
         else if (method.equals("editOwner")) 
        {  String ownerId = request.getParameter("key");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
           DfbsOwner inMapOwner =cart.getOwner(ownerId);
          session.setAttribute("AMUSE_OWNER_SELECTED",inMapOwner);
          ownerForm.setProperties(inMapOwner);
           setOptions(request,dfbsUtilityDAO);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editOwner");
        } 
        
        else if (method.equals("newOwner")) 
        {  setFilterOptions(request,dfbsUtilityDAO);
           setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("newOwner");
        }
        else if (method.equals("saveNewOwner")) 
        { DfbsOwner newOwner = ownerForm.getDfbsOwner();
         DfbsOwnerForm errorForm = new DfbsOwnerForm();
         if (validate(newOwner,errorForm,session) ) {
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          session.setAttribute("RIDE_OWNER_STATUS","Y");
          if (cart.getOwnerMapCount() >0 ) {
            DfbsOwner oldOwner =cart.getOwner(newOwner.getOwnerKey());
            ownerForm.setUpdatedProperties(newOwner, oldOwner);
          }
          else
          {  DfbsOwner ownerCheck = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
            if (ownerCheck !=null && ownerCheck.getOwnerId()>0) {
              newOwner.setRides(ownerCheck.getRides());
            }
          cart.addOwner(newOwner);
          }
          session.setAttribute("AMUSE_OWNER_SELECTED",newOwner);
          if(newOwner.getOwnerId()==0)
          {
         StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
         redirectUrl.append(request.getContextPath()).append("/amuseRide/ride.do?method=addNewRide");
         response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
          }
          else { String insCheck= (String) session.getAttribute("VERIFY_APPLICATION");
              if (insCheck !=null && insCheck.equals("Y"))
              {
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/amuseRide/owner.do?method=preCheckout");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                  return null;                             
              }
            else
            {
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/amuseRide/owner.do?method=view&ownerId="+newOwner.getOwnerId());
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
            }
          }
        } 
         else 
         {setOptions(request,dfbsUtilityDAO);
           setFilterOptions(request,dfbsUtilityDAO);
          request.setAttribute("OWNER_ERROR",errorForm);
          session.setAttribute("AMUSE_OWNER_SELECTED",newOwner);
          return mapping.findForward("newOwner");
         }
         } 
           else if (method.equals("removeOwner")) 
        {  String ownerKey= request.getParameter("ownerKey");
           ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
          cart.removeOwner(ownerKey);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/amuseRide/owner.do");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;

        } 
        else if (method.equals("preCheckout")) 
        {  DfbsOwner owner = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
          session.setAttribute("VERIFY_APPLICATION", "Y");
           List insList =iDAO.selectInsuranceList(owner.getOwnerId());
           owner.setInsList(insList);
          return mapping.findForward("insuranceList");
        }
        else if (method.equals("editInsurance")) 
        {  DfbsOwner owner = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
          session.setAttribute("VERIFY_APPLICATION", "Y");
           List insList =iDAO.selectInsuranceList(owner.getOwnerId());
           owner.setInsList(insList);
          return mapping.findForward("insuranceList");
        }
        else if (method.equals("goToUpload")) 
        { String idNumber = request.getParameter("idNumber"); 
          String idType = request.getParameter("idType"); 
          DfbsOwner owner = (DfbsOwner) session.getAttribute("AMUSE_OWNER_SELECTED");
          ownerForm.setOwnerId(Integer.parseInt(idNumber));
           owner.setFileList(oDAO.selectFileList(idNumber,"AmuseRide")); 
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
        }
        else if (method.equals("downLoadFile")) 
        {
        String fileType = request.getParameter("fileType");
        int fileId = Integer.parseInt(request.getParameter("fileId"));
        String fileName = request.getParameter("fileName");
        doGetFile(response,oDAO,fileName,fileType,fileId);
        return null;
        }
        
        else if (method.equals("uploadFile"))
        {
        
        final FormFile oneFile = ownerForm.getCaseFile();
        if(oneFile != null && oneFile.getFileSize()>0 )
         {    String ownerId = request.getParameter("ownerId"); 
              String idType = "AmuseRide"; 
              String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (oneFile.getFileName().length() <=100 && (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                  fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                   fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                   fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                   fileExtension.substring(1,4).toUpperCase().equals("DWF")||fileExtension.substring(1,4).toUpperCase().equals("XLS")))
               {
               oDAO.uploadFile(oneFile,ownerId,idType);  
             }
             else
             {
             session.setAttribute("FILE_EXTENSION","ERROR");
             return mapping.findForward("goToUpload");
             }
         }
          return mapping.findForward("insuranceList");
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
    List charNav = uDAO.getCharacterNavs(amuseRideSQL.SELECT_FIRST_LETTER_OPTIONS);
   List states = uDAO.getOptions(amuseRideSQL.SELECT_STATE_OPTIONS);
    request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
   request.setAttribute("DFBS_STATE_OPTIONS",states);
    
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
     else if(filter.equals("byCounty"))  
    { 
       filterSession.setValue(filterForm.getCounty());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(filterForm.getCity());
     }
     session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,amuseOwnerDAO  fwDAO,DfbsOwnerForm ownerForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("DFBS_OWNER_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("DFBS_OWNER_LIST_FILTER",filterSession);
        list = fwDAO.selectOwnersList(amuseRideSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOwnersList(amuseRideSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue());
     } 
      request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
     
  }
  
  
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"cclouse@dhs.in.gov"};
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
            sub.append(" From Fire display Online enquiry - ").append(subject);
          
          
          
            mail.createMail("fire_display_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("display_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Fire display error emailOnline enquiry error ","DfbsOwnerAction");
            mail1.sendAndClose();             }
  }
  
 protected static boolean validate(DfbsOwner owner,DfbsOwnerForm errorForm,HttpSession session) 
  {
    boolean noError = true;
     ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
  
  if (owner.getOwnerId() == 0)
  {
    
    if(owner.getOwnerDBA() == null || owner.getOwnerDBA().trim().equals("") ) 
    {
      errorForm.setOwnerDBA("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerDBA("");
    }
     
    if (security !=null && (security.getGroupLevelAll()!=null ||security.getGroupLevelFire()!=null))
    { 
    if(owner.getOwnerAddress1() == null || owner.getOwnerAddress1().trim().equals("") ) 
    {
      errorForm.setOwnerAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerAddress1("");
    }
    
    if(owner.getOwnerCity() == null || owner.getOwnerCity().trim().equals("") ) 
    {
      errorForm.setOwnerCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerCity("");
    }
    if(owner.getOwnerStateId() ==  0  ) 
    {
      errorForm.setOwnerStateId(0);
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerState("");
    }
    
    if(owner.getOwnerZip() == null || owner.getOwnerZip().trim().equals("") ) 
    {
      errorForm.setOwnerZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerZip("");
    }
    }
    
  } 
   if(owner.getOwnerEmail() == null || owner.getOwnerEmail().trim().equals("") ||
       owner.getOwnerEmail().indexOf('@') <=0 || owner.getOwnerEmail().indexOf('.') <= 0 ) 
    {
      errorForm.setOwnerEmail("ERROR");
      noError = false;
    } 
    else 
    { 
      errorForm.setOwnerEmail("");
    }
    
    if(owner.getOwnerPhone() == null || owner.getOwnerPhone().trim().equals("") ||
       owner.getOwnerPhone().indexOf('-') > 0 ||owner.getOwnerPhone().indexOf(')') > 0 ||
       owner.getOwnerPhone().indexOf('(') > 0) 
    {
      errorForm.setOwnerPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOwnerPhone("");
    }
   
   owner.setOwnerError(noError);
    
    return noError;
  }
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(amuseRideSQL.SELECT_STATE_OPTIONS);
    List charNav = uDAO.getCharacterNavs(amuseRideSQL.SELECT_FIRST_LETTER_OPTIONS);
    
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
   request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    
    
 } 
  public void doGetFile( HttpServletResponse response,amuseOwnerDAO oDAO,String fileName,String fileType,int fileId) throws Exception 
  {
   try {response.setContentType(fileType); 
   response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\""); 
    java.io.OutputStream out = response.getOutputStream(); 
    int lenFile = oDAO.downLoad(out,fileId,fileName);
     
     out.flush(); 
    out.close();
   } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
            }
  }
 
}