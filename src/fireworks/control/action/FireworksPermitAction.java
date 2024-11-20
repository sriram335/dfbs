package fireworks.control.action;

import fireworks.control.form.*;
import fireworks.to.*;
import fireworks.data.*;
import fireworks.report.*;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import hs.control.*;
import hs.control.form.*;
import main.to.*;
import hs.util.*;
import main.data.*;
import idhsInspections.data.*;
import hs.data.system.*;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
import otherUsers.to.*;

import java.util.*;
import java.text.*;

public class FireworksPermitAction extends ControlAction
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
        
        DfbsFireworksPermitForm permitForm = (DfbsFireworksPermitForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsFireworksOwnerDAO oDAO = (DfbsFireworksOwnerDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_OWNER);
        DfbsFireworksPermitDAO pDAO = (DfbsFireworksPermitDAO) dmap2.getHsDAO(DfbsDataMap.FIREWORKS_PERMIT);
         ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        //at this point owner must be in session
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
        if((owner == null || ownerApplication == null) && method == null) 
        {
          throw new Exception("NO_OWNER_IN_SESSION");
        }
       
       if (method.equals("addPermitForm")) 
        {
          permitForm.setPermitType(1);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("addPermitForm");
        } 
        else if (method.equals("editPermitForm")) 
        {
          String key = request.getParameter("key");
          DfbsFireworksPermit permit = ownerApplication.getPermit(key);
          permitForm.setProperties(permit);
          request.setAttribute("DFBS_PERMIT",permit);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("editPermitForm");
        } 
        else if (method.equals("addPermit")) 
        {
          DfbsFireworksPermit permit = permitForm.getDfbsFireworksPermit();
          DfbsFireworksPermitForm errorForm = new DfbsFireworksPermitForm();
          if (validate(permit,errorForm,pDAO,session) ) {
            ownerApplication.addPermit(permit);
            return mapping.findForward("step2");
          } 
          else 
          {
              
              
            request.setAttribute("DFBS_PERMIT_ERROR",errorForm);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("addPermitForm");
          }
          
        } 
        else if (method.equals("editPermit")) 
        {
          DfbsFireworksPermit updatedPermit = permitForm.getDfbsFireworksPermit();
          DfbsFireworksPermit permit = ownerApplication.getPermit(updatedPermit.getApplicationKey());
           DfbsFireworksPermitForm errorForm = new DfbsFireworksPermitForm();
          if (validate(updatedPermit,errorForm,pDAO,session) ) {
          permitForm.setUpdatedProperties(updatedPermit,permit);
           return mapping.findForward("step2");
          } 
          else 
          {
            request.setAttribute("DFBS_PERMIT_ERROR",errorForm);
            request.setAttribute("DFBS_PERMIT",permit);
            setOptions(request,dfbsUtilityDAO);
            return mapping.findForward("editPermitForm");
          }
          
        } 
        else if (method.equals("addSelectedPermits")) 
        {
          addPermitsToRenew(request,owner,ownerApplication);
          return mapping.findForward("step2");
        }
          else if (method.equals("addRenewalPermits")) 
          {
              String permitNumber = request.getParameter("permitNumber");
              addPermitsToRenewNew(request,owner,ownerApplication,permitNumber); 
              DfbsFireworksPermit permit = ownerApplication.getPermit(permitNumber);
              permitForm.setProperties(permit);
              request.setAttribute("DFBS_PERMIT",permit);
              setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("editPermitForm");
          }
        else if (method.equals("removePermit")) 
        {
          String key = request.getParameter("key");
          ownerApplication.removePermit(key);
          return mapping.findForward("step2");
        } 
     
       else if (method.equals("viewPermit")) 
        { 
           
          DfbsFireworksPermit permit =pDAO.selectPermit(request.getParameter("permitType"),request.getParameter("permitNumber"));
          permit.setFileList(pDAO.selectFileList(request.getParameter("permitNumber"),"Fireworks"));
          permitForm.setProperties(permit);
          setOptions(request,dfbsUtilityDAO);
          session.setAttribute("DFBS_VIEW_PERMIT",permit);
          return mapping.findForward("viewPermit");
        }    
           else if (method.equals("goToUpload")) 
         { String idNumber = request.getParameter("idNumber"); 
           String idType = "Fireworks"; 
           DfbsFireworksPermit permit = ownerApplication.getPermit(idNumber);
            permit.setFileList(pDAO.selectFileList(idNumber,"Fireworks")); 
            session.setAttribute("PERMIT_SELECTED",permit);
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
           else if (method.equals("viewFile")) 
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
            {   DfbsFireworksPermit selectedPermit = (DfbsFireworksPermit) session.getAttribute("PERMIT_SELECTED");
                DfbsFireworksPermit permit = ownerApplication.getPermit(selectedPermit.getApplicationKey());
                 String idType = "Fireworks"; 
                 String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                  if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {
                  pDAO.uploadFile(oneFile,permit.getApplicationKey(),idType);  
                 permit.setNoMap("uploaded:"+oneFile.getFileName());
                 permit.setFileList(pDAO.selectFileList(permit.getApplicationKey(),"Fireworks"));
                  return mapping.findForward("step2");
                }
                else
                {
                session.setAttribute("FILE_EXTENSION","ERROR");
                return mapping.findForward("goToUpload");
                }
            }
         }
       
             else if (method.equals("countyApproved")) 
         { 
          String permitNumber = request.getParameter("permitNumber");
           DfbsFireworksPermit permit =pDAO.selectPermit("Consumer",permitNumber);
           permitForm.setProperties(permit);
           session.setAttribute("FIREWORKS_PERMIT_APPROVE","Y");
          return mapping.findForward("countyApproval"); 
         }
            else if (method.equals("approveSaveCounty")) 
         { 
          String permitNumber = request.getParameter("permitNumber");
           DfbsFireworksPermit permit =pDAO.selectPermit("Consumer",permitNumber);
           String permitComments =permitForm.getPermitComments();
          if (permitComments.length() > 0)
          {session.setAttribute("FIREWORKS_PERMIT_APPROVE_ERROR","");
             otherUsers otherUser =(otherUsers)  session.getAttribute("OTHER_USER");
          pDAO.updatePermitCountyApproval(permitNumber,"APPROVED",permitComments+": permit approved by "+ otherUser.getUserLastName()+".");
          String inspEmail = pDAO.findEmail(permit.getCounty(), permit.getZip());
           ApplicationContacts contacts = sDAO.setContacts("FIREWORKS_CONTACT1","FIREWORKS_CONTACT2");
            sendEmailCountyApproval(permit,contacts,inspEmail,otherUser.getUserLoginId(),"Approved");
          String county =(String) session.getAttribute("OTHER_COUNTY");
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/fireworks/main.do?method=showCounty&county="+county+"&recFlag=PENDING");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null; 
          }
          else { session.setAttribute("FIREWORKS_PERMIT_APPROVE_ERROR","ERROR");
             return mapping.findForward("countyApproval");
          }
         }
              else if (method.equals("countyDenied")) 
         { 
          String permitNumber = request.getParameter("permitNumber");
           DfbsFireworksPermit permit =pDAO.selectPermit("Consumer",permitNumber);
           permitForm.setProperties(permit);
           session.setAttribute("FIREWORKS_PERMIT_APPROVE","N");
          return mapping.findForward("countyApproval"); 
         }
             else if (method.equals("denySaveCounty")) 
         { 
          String permitNumber = request.getParameter("permitNumber");
           DfbsFireworksPermit permit =pDAO.selectPermit("Consumer",permitNumber);
           String permitComments =permitForm.getPermitComments();
          if (permitComments.length() > 0)
          {session.setAttribute("FIREWORKS_PERMIT_APPROVE_ERROR","");
             otherUsers otherUser =(otherUsers)  session.getAttribute("OTHER_USER");
          pDAO.updatePermitCountyApproval(permitNumber,"DENIED",permitComments+": permit denied by "+ otherUser.getUserLastName()+".");
          String inspEmail = pDAO.findEmail(permit.getCounty(), permit.getZip());
           ApplicationContacts contacts = sDAO.setContacts("FIREWORKS_CONTACT1","FIREWORKS_CONTACT2");
            sendEmailCountyApproval(permit,contacts,inspEmail,otherUser.getUserLoginId(),"Denied");
          String county =(String) session.getAttribute("OTHER_COUNTY");
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append(request.getContextPath()).append("/fireworks/main.do?method=showCounty&county="+county+"&recFlag=PENDING");
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null; 
          }
          else { session.setAttribute("FIREWORKS_PERMIT_APPROVE_ERROR","ERROR");
             return mapping.findForward("countyApproval");
          }
         }
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
   protected static boolean validate(DfbsFireworksPermit permit,DfbsFireworksPermitForm errorForm, DfbsFireworksPermitDAO pDAO,HttpSession session) throws Exception
  {
    boolean noError = true;
    
    
    
    if(permit.getStreet1() == null || permit.getStreet1().trim().equals("") ) 
    {
      errorForm.setStreet1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setStreet1("");
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
    else  if(permit.getPermitType() != 1 && ! permit.getState().equals("IN")  ) 
    {
      errorForm.setState("ERROR2");
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
    if(permit.getPermitType() != 1 && (permit.getCountyId() == null || permit.getCountyId().trim().equals("")) ) 
    {
      errorForm.setCountyId("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCountyId("");
    }
    if(permit.getMerchantNumber() == null || permit.getMerchantNumber().trim().equals("") ) 
    {
      errorForm.setMerchantNumber("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMerchantNumber("");
    }
    
    if(permit.getContactPerson() == null || permit.getContactPerson().trim().equals("") ) 
    {
      errorForm.setContactPerson("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContactPerson("");
    }
    
    if(permit.getPhoneNumber() == null || permit.getPhoneNumber().trim().equals("") ) 
    {
      errorForm.setPhoneNumber("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPhoneNumber("");
    }
 



    if(permit.getPermitType() != 1 && (permit.getInspectDate() == null || (permit.getInspectDateString()).length() <10) ) 
    {
      errorForm.setInspectDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setInspectDate("");
    }
      //Added code to check if inspect is lesser that current date Dev 03/25/2015
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                       //get current date time with Date()
                       Date currDate = new Date();
                     String currDateStr = dateFormat.format(currDate);
                       currDate = dateFormat.parse(currDateStr);
      if(permit.getInspectDate() != null && permit.getInspectDate().before(currDate) ) 
      {
        errorForm.setInspectDate("ERROR");
        noError = false;
      } 
      else 
      {
        errorForm.setInspectDate("");
      }
    if(permit.getOpenDate() == null || (permit.getOpenDateString()).length() <10 ) 
    {
      errorForm.setOpenDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setOpenDate("");
    }
      if(permit.getOpenDate() != null && permit.getOpenDate().before(currDate) ) 
      {
        errorForm.setOpenDate("ERROR");
        noError = false;
      } 
      else 
      {
        errorForm.setInspectDate("");
      }
    if(permit.getHoursOfOperation() == null || permit.getHoursOfOperation().trim().equals("")) 
    {
      errorForm.setHoursOfOperation("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setHoursOfOperation("");
    }
    
    if(  permit.getPermitType() == 1 
      && ! ( permit.isManufacturer()
      || permit.isImporter() 
      || permit.isWholesaler() 
      || permit.isDistributor() ) ) 
    {
      errorForm.setCategoryId(-1);
      noError = false;
    } 
    else 
    {
      errorForm.setCategoryId(permit.getCategoryId());
    }
    
   // to verify file upload removed the file upload on 03/18/2010 as per Chris.
   session.setAttribute("FILE_UPLOAD","TRUE");   
   /*
     String fileName=pDAO.getUploadFileName(permit.getApplicationKey(),"Fireworks");
     if(fileName.equals("NoName") ) 
    { 
      errorForm.setNoMap("Upload");
      permit.setNoMap("Pending");
        session.setAttribute("FILE_UPLOAD","FALSE");
    } 
    else
    {
      errorForm.setNoMap("");
      session.setAttribute("FILE_UPLOAD","TRUE");   
      permit.setNoMap("uploaded:" + fileName);
    }
    */
    
    
    return noError;
  }
  
  
  private static void addPermitsToRenew(HttpServletRequest request,DfbsOwner owner,DfbsOwner ownerApplication) throws Exception
  {
    List permits = owner.getPermits();
    Iterator i = permits.iterator();
    
    while(i.hasNext())
    {
      DfbsFireworksPermit permit = (DfbsFireworksPermit) i.next();
      StringBuffer sb = new StringBuffer("PERMIT");
      sb.append(permit.getPermitNumber());
      String param = request.getParameter(sb.toString());
      if(param != null) 
      {
        ownerApplication.addPermit(permit);
      }
    }
    
  }
    private static void addPermitsToRenewNew(HttpServletRequest request,DfbsOwner owner,DfbsOwner ownerApplication,String permitNumber) throws Exception
    {
      List permits = owner.getPermits();
      Iterator i = permits.iterator();
      
      while(i.hasNext())
      {
        DfbsFireworksPermit permit = (DfbsFireworksPermit) i.next();
        
        if(permit.getPermitNumber().equals(permitNumber)) 
        {
          ownerApplication.addPermit(permit);
        }
      }
      
    }
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsFireworksSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("DFBS_STATE_OPTIONS",states);
 
 
    List counties = uDAO.getOptions(DfbsFireworksSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
 }
 
 
 
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,DfbsFireworksPermitDAO pDAO,String fileName,String fileType,int fileId) throws Exception 
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

 private static void sendEmailCountyApproval(DfbsFireworksPermit permit,
                          ApplicationContacts contacts,String inspEmail,String otherEmail,String statusFlag) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
           String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail,otherEmail,"fireworks@indy.org"};
       //  String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer();
             StringBuffer sub = new StringBuffer();
            if (statusFlag.equals("Approved"))
                   {
                   sub.append(" From fire fireworks inspection ready alert ");
                    sb.append(permit.getPermitNumber() + ": is ready for inspection,County has "+statusFlag+" the permit").append("\n\r");
                   }
              else {
                   sub.append(" Fireworks permission denied by the county ");
                    sb.append(permit.getPermitNumber() + ": County has "+statusFlag+" the permit").append("\n\r");
                    }
            sb.append("County :"+ permit.getCounty()).append("\n\r");
            sb.append("Contact Name :"+ permit.getContactPerson()+" Phone Number= " +permit.getPhoneNumber()).append("\n\r");
            sb.append("Ready for inspection date :"+ permit.getInspectDateString()+" Facility open date= " +permit.getOpenDateString()+" Hours of operation="+permit.getHoursOfOperation()).append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do").append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
           
            mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("fireworks_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire fireworks inspection ready alert error county approval email ",permit.getPermitNumber()+"HsFireworksCompleteCheckoutAction ");
            mail1.sendAndClose();             }
  }

 
}
  


