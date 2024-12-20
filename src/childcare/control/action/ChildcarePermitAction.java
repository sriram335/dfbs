package childcare.control.action;

import childcare.control.form.*;

import ai.kwikekard.checkout.service2.client.*;
import childcare.data.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import childcare.to.*;


import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
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
import main.data.*;
import main.to.*;
public class ChildcarePermitAction extends ControlAction
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
        
        DfbsChildcarePermitForm permitForm = (DfbsChildcarePermitForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsChildcareOwnerDAO oDAO = (DfbsChildcareOwnerDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_OWNER);
        DfbsChildcarePermitDAO pDAO = (DfbsChildcarePermitDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_LICENSE);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.CHILDCARE_CONTACT);
        ApplicationSecurityDAO seDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        //at this point owner must be in session
         ApplicationContacts contacts = ( ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        
        if(method.equals("start") ) 
        { 
          String structureType = request.getParameter("structureType");
          
          session.setAttribute("STRUCTURE_TYPE",structureType);
          setList(request,pDAO,permitForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        }  
       else if (method.equals("refresh")) 
        {
          setList(request,pDAO,permitForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("main");
        } 
       
        else if (method.equals("filter")) 
        {
          setFilterSession(request,permitForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,pDAO,permitForm);
          return mapping.findForward("main");
        } 
        
       if (method.equals("addPermitForm")) 
        { 
          setOptions(request,dfbsUtilityDAO);
         
          return mapping.findForward("addPermitForm");
        } 
        else if (method.equals("editPermitForm")) 
        { String permitKey = (String) session.getAttribute("PERMIT_KEY");
          DfbsChildcarePermit permit = pDAO.selectPermit(permitKey);
          session.setAttribute("PERMIT_SELECTED",permit);
          permitForm.setProperties(permit);
          permitForm.setApplicationKey(permitKey);
          request.setAttribute("CHILDCARE_PERMIT",permit);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editPermitForm");
           
        } 
        else if (method.equals("renewPermitForm")) 
        { String permitKey = request.getParameter("permitKey");
          DfbsOwner selectOwner = (DfbsOwner) session.getAttribute("OWNER_RENEW");
          DfbsChildcarePermit permit = selectOwner.getPermit(permitKey);
          permit.setFileList(pDAO.selectFileList(permit.getPermitNumber()));
          session.setAttribute("PERMIT_SELECTED",permit);
          permitForm.setProperties(permit);
          permitForm.setApplicationKey(permitKey);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("renewPermitForm");
           
        } 
         else if (method.equals("editCartPermit")) 
        { String permitKey = request.getParameter("permitKey");
          String ownerKey = request.getParameter("ownerKey");
          DfbsOwner selectedOwner=cart.getOwner(ownerKey);
          DfbsChildcarePermit permit = selectedOwner.getPermit(permitKey);
          session.setAttribute("PERMIT_SELECTED",permit);
          permitForm.setProperties(permit);
          permitForm.setApplicationKey(permitKey);
          setOptions(request,dfbsUtilityDAO);
          selectedOwner.removePermit(permitKey);
          session.setAttribute("OWNER_RENEW",selectedOwner);
          return mapping.findForward("renewPermitForm");
        } 
 
        
        else if (method.equals("addPermit")) 
        { DfbsChildcarePermit permit = permitForm.getChildcare();
          DfbsChildcarePermitForm errorForm = new DfbsChildcarePermitForm();
          
          if (validate(permit,errorForm,oDAO,session) ) {
          DfbsOwner owner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsOwner selectedOwner = cart.getOwner(owner.getOwnerKey());
             selectedOwner.addPermit(permit);
             setOptions(request,dfbsUtilityDAO);
             pDAO.computeFees(cart);
             session.setAttribute("OWNER_SELECTED",selectedOwner);
            return mapping.findForward("addPermitForm");
          } 
          else 
          {
          
            request.setAttribute("CHILDCARE_PERMIT_ERROR",errorForm);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("addPermitForm");
          }
          
        } 
        else if (method.equals("editPermit")) 
        {
          DfbsChildcarePermit updatedPermit = permitForm.getChildcare();
          DfbsChildcarePermitForm errorForm = new DfbsChildcarePermitForm();
            if (validate(updatedPermit,errorForm,oDAO,session) ) {
            DfbsChildcarePermit permit = (DfbsChildcarePermit) session.getAttribute("PERMIT_SELECTED");
            permitForm.setUpdatedProperties(updatedPermit,permit);
            DfbsOwner selectedOwner = cart.getOwner(Integer.toString(updatedPermit.getOwnerId()));
            selectedOwner.addPermit(updatedPermit);
            pDAO.computeFees(cart);
            setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editPermitForm");
          } 
          else 
          {
           request.setAttribute("CHILDCARE_PERMIT_ERROR",errorForm);
            request.setAttribute("DFBS_PERMIT",updatedPermit);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("editPermitForm");
          }
          
        }
         if (method.equals("renewPermit")) 
        { String permitKey = request.getParameter("permitKey");
          String ownerKey = request.getParameter("ownerKey");
          DfbsOwner selectOwner = oDAO.selectOwner(Integer.parseInt(ownerKey));
           DfbsOwner ownerCheck =cart.getOwner(Integer.toString(selectOwner.getOwnerId()));
          if (ownerCheck != null && ownerCheck.getOwnerLastName().equals("xxx"))
          {
            selectOwner.setRenewStatus("N");
          }
          else
          {
            selectOwner.setRenewStatus("Y");
          }
          cDAO.selectContactMap(selectOwner.getOwnerId(),selectOwner);
          DfbsChildcarePermit permit =pDAO.selectPermit(permitKey);
           permit.setFileList(pDAO.selectFileList(permit.getPermitNumber()));
          session.setAttribute("PERMIT_SELECTED",permit);
          selectOwner.addPermit(permit);
          setOptions(request,dfbsUtilityDAO);
          session.setAttribute("OWNER_RENEW",selectOwner);
           session.setAttribute("UPLOAD_FLAG","N");
          return mapping.findForward("renewPermit");
        } 
         if (method.equals("backToRenewPermit")) 
        { 
          return mapping.findForward("renewPermit");
        } 
        else if (method.equals("renewPermitVerified")) 
        {
          DfbsChildcarePermit updatedPermit = permitForm.getChildcare();
          DfbsChildcarePermitForm errorForm = new DfbsChildcarePermitForm();
            if (validate(updatedPermit,errorForm,oDAO,session) ) {
            updatedPermit.setRenewStatus("Y");
            DfbsOwner selectOwner = (DfbsOwner) session.getAttribute("OWNER_RENEW");
            DfbsChildcarePermit permit = selectOwner.getPermit(updatedPermit.getApplicationKey());
            permitForm.setUpdatedProperties(updatedPermit,permit);
            session.setAttribute("PERMIT_SELECTED",updatedPermit);
            setOptions(request,dfbsUtilityDAO);
            String uploadFlag =oDAO.ownerRenewStatus(selectOwner);
          if (uploadFlag.equals("Y"))
          {
            session.setAttribute("UPLOAD_FLAG","Y");
          }
          return mapping.findForward("renewPermit");
          } 
          else 
          {
           request.setAttribute("CHILDCARE_PERMIT_ERROR",errorForm);
            request.setAttribute("DFBS_PERMIT",updatedPermit);
            permitForm.setProperties(updatedPermit);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("renewPermitForm");
          }
       
        } 
            else if (method.equals("addToCart")) 
        { DfbsOwner selectOwner = (DfbsOwner) session.getAttribute("OWNER_RENEW");
           if (cart.getOwnerMapCount() ==0)
           {
           cart.addOwner(selectOwner);
           }
           else
           {
           DfbsOwner ownerCheck =cart.getOwner(Integer.toString(selectOwner.getOwnerId()));
           if(ownerCheck.getOwnerLastName().equals("xxx"))
           {
             cart.addOwner(selectOwner);
           }
           else
           {
             DfbsChildcarePermit permit = (DfbsChildcarePermit) session.getAttribute("PERMIT_SELECTED");
             ownerCheck.addPermit(permit);
           }
           }
           pDAO.computeFees(cart);
          return mapping.findForward("renewPermit");
        } 
        else if (method.equals("removePermit")) 
        {  String permitKey = request.getParameter("permitKey");
          String ownerKey = request.getParameter("ownerKey");
          DfbsOwner selectedOwner=cart.getOwner(ownerKey);
          selectedOwner.removePermit(permitKey);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("renewPermit");
        } 
         else if (method.equals("viewPermit")) 
        { 
         DfbsChildcarePermit permit =pDAO.selectPermit(request.getParameter("permitNumber"));
         permit.setFileList(pDAO.selectFileList(request.getParameter("permitNumber")));
          permitForm.setProperties(permit);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("VIEW_PERMIT_SELECTED",permit);
          return mapping.findForward("viewPermit");
        } 
         else if (method.equals("reAssignId")) 
        { 
         String permitNumber=request.getParameter("permitNumber");
         permitForm.setPermitNumber(permitNumber);
          setOptions(request,dfbsUtilityDAO);
           session.setAttribute("PERMIT_OLD",permitNumber);
          return mapping.findForward("reAssignId");
        } 
          else if (method.equals("changeId")) 
        { 
         String newPermitNumber=request.getParameter("idNew");
         String oldPermitNumber= (String) session.getAttribute("PERMIT_OLD");
         pDAO.childCareReassignId(oldPermitNumber,newPermitNumber);
         session.setAttribute("PERMIT_NEW",newPermitNumber);
          return mapping.findForward("reAssignId");
        } 
             // file upload download feature
         else if (method.equals("goToUpload")) 
         { DfbsChildcarePermit permit = (DfbsChildcarePermit) session.getAttribute("PERMIT_SELECTED");
         String idNumber = permit.getPermitNumber(); 
         DfbsOwner owner = ( DfbsOwner) session.getAttribute("OWNER_RENEW");
         DfbsChildcarePermit uploadPermit = owner.getPermit(permit.getPermitNumber());
          uploadPermit.setFileList(pDAO.selectFileList(idNumber)); 
          session.setAttribute("PERMIT_SELECTED",uploadPermit);
           session.setAttribute("FILE_EXTENSION","");
          return mapping.findForward("goToUpload");
         }
         
        else if (method.equals("goToUploadDfbs")) 
        { 
        String idNumber = request.getParameter("idNumber"); 
        DfbsChildcarePermit uploadPermit = pDAO.selectPermit(idNumber);
         uploadPermit.setFileList(pDAO.selectFileList(idNumber)); 
         session.setAttribute("PERMIT_SELECTED",uploadPermit);
          session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
        }
           else if (method.equals("downLoadFile")) 
         { 
          String fileType = request.getParameter("fileType");
          int fileId = Integer.parseInt(request.getParameter("fileId"));
          String fileName = request.getParameter("fileName");
          doGetFile(request,response,pDAO,fileName,fileType,fileId);
           return null;
         }
          else if (method.equals("uploadFile")) 
         { 
         
         final FormFile oneFile = permitForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {    DfbsChildcarePermit  uploadPermit = (DfbsChildcarePermit) session.getAttribute("PERMIT_SELECTED");
                 String idNumber =  uploadPermit.getPermitNumber(); 
               //  DfbsOwner owner = ( DfbsOwner) session.getAttribute("OWNER_RENEW");
               //  DfbsChildcarePermit uploadPermit = owner.getPermit(permit.getPermitNumber());
                 String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                 if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {
                  pDAO.uploadFile(oneFile,idNumber);  
                  uploadPermit.setFileList(pDAO.selectFileList(idNumber));
                  uploadPermit.setUploadStatus("Y"); 
                 session.setAttribute("PERMIT_SELECTED",uploadPermit);
                 
                }
                else
                {
                session.setAttribute("FILE_EXTENSION","ERROR");
                return mapping.findForward("goToUpload");
                }
            }
           return mapping.findForward("goToUpload");
         }  
          else if (method.equals("updatePermit")) 
        {  String url = request.getRemoteAddr();
        if (contacts.getContact1IP()!=null && contacts.getContact1IP().equals(url) ||contacts.getContact2IP()!=null && contacts.getContact2IP().equals(url)||url.equals("10.90.27.52"))
          {   
             session.setAttribute("DFBS_SECURITY",new ApplicationSecurity ());
             ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
             String conType= (String) session.getAttribute("APPLICATION_CONTACT_TYPE");
             seDAO.findSecurityUser(url,security,conType);
             String userLevel = seDAO.getSecurity(security,security.getUserId(),security.getUserPassword());
           }
         DfbsChildcarePermit permit =pDAO.selectPermit(request.getParameter("permitNumber"));
         permit.setFileList(pDAO.selectFileList(request.getParameter("permitNumber")));
          permitForm.setProperties(permit);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("UPDATE_PERMIT_SELECTED",permit);
          return mapping.findForward("updatePermit");
        } 
          else if (method.equals("saveUpdatePermit")) 
        { 
         DfbsChildcarePermit updatedPermit = permitForm.getChildcare();
          DfbsChildcarePermitForm errorForm = new DfbsChildcarePermitForm();
            if (validate(updatedPermit,errorForm,oDAO,session) ) {
            pDAO.updatePermit(updatedPermit);
            }
            else 
          {
            request.setAttribute("CHILDCARE_PERMIT_ERROR",errorForm);
           }
             setOptions(request,dfbsUtilityDAO);
           session.setAttribute("UPDATE_PERMIT_SELECTED",updatedPermit);
          return mapping.findForward("updatePermit");
        } 
           else if (method.equals("emailInspector")) 
        { 
         DfbsChildcarePermit updatedPermit = (DfbsChildcarePermit) session.getAttribute("UPDATE_PERMIT_SELECTED");
         String inspEmail=pDAO.findEmail(updatedPermit.getCounty(),updatedPermit.getZip());
         sendEmailInsp(request,updatedPermit,contacts,session,inspEmail); 
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updatePermit");
        } 
        
        throw new Exception("HS_ERROR_METHOD_INVALID2");
             
      } 
    catch (Exception e) 
    {
       saveError(request,e);
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
       return mapping.findForward("error");
      
    }
        
      
        
      
  }
  
   protected static boolean validate(DfbsChildcarePermit permit,DfbsChildcarePermitForm errorForm,DfbsChildcareOwnerDAO oDAO,HttpSession session) throws Exception
  {
    boolean noError = true;
   
    if(permit.getAddress1() == null || permit.getAddress1().trim().equals("")  ) 
    { 
      errorForm.setAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setAddress1("");
    }
    if(permit.getCity() == null || permit.getCity().trim().equals("") ) 
    {
      errorForm.setCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCity("");
    }
    
    if(permit.getState() == null || permit.getState().trim().equals("") ) 
    { 
      errorForm.setState("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setState("");
    }
    
    
    
    if(permit.getZip() == null || permit.getZip().trim().equals("") ) 
    { 
      errorForm.setZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setZip("");
    }
    
    if((permit.getCounty() == null || permit.getCounty().trim().equals("") || permit.getCounty().trim().equals("-----")) ) 
    {
      errorForm.setCounty("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCounty("");
    }
    
    if (permit.getTelephone() == null || permit.getTelephone().trim().equals("") ||
       permit.getTelephone().indexOf('-') > 0 || permit.getTelephone().indexOf(')') > 0 ||
       permit.getTelephone().indexOf('(') > 0) 
    { 
      errorForm.setTelephone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setTelephone("");
    }
   
     
     if(permit.getDaycareName() == null || permit.getDaycareName().trim().equals("") ) 
    { 
      errorForm.setDaycareName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setDaycareName("");
    }
    
 /*    if(permit.getFdId()== null || permit.getFdId().trim().equals("")  ) 
    {
      errorForm.setFdId("");
      noError = false;
    } 
    else 
    {
      errorForm.setFdId("");;
    }
  
   
  
    
   // to verify file upload
     String fileName=oDAO.getUploadFileName(permit.getApplicationKey(),"ChildCare");
     if(fileName.equals("NoName") &&permit.getStatus().trim().equals("EXPIRED")) 
    { 
      errorForm.setNoMap("ERROR");
      permit.setNoMap("ERROR");
        session.setAttribute("FILE_UPLOAD","FALSE");
    } 
    else
    {
      errorForm.setNoMap("");
      session.setAttribute("FILE_UPLOAD","TRUE");   
      permit.setNoMap("uploaded:" + fileName);
    } */
    return noError;
  }
  
 
  
  
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsChildcareSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("DFBS_STATE_OPTIONS",states);
 
 
    List counties = uDAO.getOptions(DfbsChildcareSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
    
    List occupancy = uDAO.getOptions(DfbsChildcareSQL.SELECT_OCCUPANCY_OPTIONS);
    request.setAttribute("DFBS_OCCUPANCY_OPTIONS",occupancy);
    
    List structureType = uDAO.getOptions(DfbsChildcareSQL.SELECT_DAYCARE_STRUCTURE_OPTIONS);
    request.setAttribute("CHILDCARE_STRUCTURE_OPTIONS",structureType);
    
    List fds = uDAO.getOptions(DfbsChildcareSQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("DFBS_FD_OPTIONS",fds);
 }
 
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,DfbsChildcarePermitDAO pDAO,String fileName,String fileType,int fileId) throws Exception 
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
 
  protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {  
    HttpSession session = request.getSession();
    String structureType = (String) session.getAttribute("STRUCTURE_TYPE");
    List charNav = uDAO.getCharacterNavs(DfbsChildcareSQL.SELECT_FIRST_LETTER_OPTIONS_CHILDCARE,structureType);
     String lsql = "select COUNTY ,COUNTY_NAME || ' (' || COUNTY_COUNT || ')' from ( " +
    "select distinct fe.county \"COUNTY\",fe.county \"COUNTY_NAME\", count(*) \"COUNTY_COUNT\" " +
    " from fire_day_care fe where  active='A' " +" AND STRUCTURE_TYPE ='" +structureType +"'"+
    " group by fe.county) " +
    " order by county";  
     List otherCounties = uDAO.getOptions(lsql);
    lsql = "select  CITY,CITY  || ' (' || STATE_COUNT || ')' from ( " +
    "select distinct CITY \"CITY\", count(*) \"STATE_COUNT\" " +
     " from fire_day_care  where  active='A' " +" AND STRUCTURE_TYPE ='" +structureType +"'"+
    " group by CITY ) " +
    " order by CITY";
    List cities = uDAO.getOptions(lsql);
     request.setAttribute("DFBS_CHILDCARE_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
    request.setAttribute("DFBS_CITIES_OPTIONS",cities);
   
 }
 protected static void setFilterSession(HttpServletRequest request,DfbsChildcarePermitForm permitForm) throws Exception
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
       filterSession.setValue(permitForm.getCounty());
     }
     else if(filter.equals("byCity"))  
    { 
       filterSession.setValue(permitForm.getCity());
     }
     session.setAttribute("DFBS_CHILDCARE_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,DfbsChildcarePermitDAO fwDAO,DfbsChildcarePermitForm permitForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("DFBS_CHILDCARE_LIST_FILTER");
     String structureType = (String) session.getAttribute("STRUCTURE_TYPE");
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("A");
        session.setAttribute("DFBS_CHILDCARE_LIST_FILTER",filterSession);
        list = fwDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_FIRST_LETTER,filterSession.getValue(),structureType);
       
     } 
     else if(filterSession.getType().equals("byLetter")) 
     { 
        list = fwDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_FIRST_LETTER,filterSession.getValue(),structureType);
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {    list = fwDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_COUNTY,filterSession.getValue(),structureType);
        permitForm.setCounty(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectPermits(DfbsChildcareSQL.SELECT_CHILDCARE_PERMITS_BY_CITY,filterSession.getValue(),structureType);
        permitForm.setCity(filterSession.getValue());
     } 
    
    
     request.setAttribute("DFBS_CHILDCARE_LIST",new HsListWrapper(list));
     
  }

private static void sendEmailInsp(HttpServletRequest request,DfbsChildcarePermit permit,
                          ApplicationContacts contacts,HttpSession session, String inspEmail) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
       
            String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
         //   String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            
           
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getPermitNumber() + ": is ready for inspection").append("\n\r");
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Use this link to log in to the system").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/childCare/start.do").append("\n\r"); 
             sb.append("Use this link view details of the registered ministry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_day_care_tobe_inspected.rdf&p_id=" + permit.getPermitNumber()).append("\n\r");
            List files = permit.getFileList();
             Iterator i = files.iterator();
            while(i.hasNext())
              {
               FileNames file = (FileNames)  i.next();
                 sb.append("Room List").append("\n\r");
                 sb.append("https://oas.dhs.in.gov/dfbs/childCare/childcare.do?method=downLoadFile&fileName="+file.getFileName()+"&fileId="+file.getFileId()+"&fileType="+file.getFileType()).append("\n\r");
               } 
            StringBuffer sub = new StringBuffer();
            sub.append(" From childcare inspection ready alert ");
            
          mail.createMail("childCare_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail = new HsMailer();
            mail.createMail("childCare_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email inspector send");
            mail.sendAndClose();
            }
  }
 
 
}
  


