package aepermits.control.action;

import aepermits.control.*;
import aepermits.control.form.*;
import aepermits.to.*;
import aepermits.data.*;


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
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts.upload.FormFile;
import main.data.*;
import main.to.*;
public class DfbsEntrAction extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        
        
        DfbsOwnerForm ownerForm = (DfbsOwnerForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsEntrOwnerDAO oDAO = (DfbsEntrOwnerDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_OWNER);
        DfbsEntrPermitDAO pDAO = (DfbsEntrPermitDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_PERMIT);
        DfbsEntrSpecialDAO sDAO = (DfbsEntrSpecialDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_SPECIAL_PERMIT);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        if (method == null) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO,session);
          return mapping.findForward("main");
        } 
        
        else if (method.equals("refresh")) 
        {
          setList(request,oDAO,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO,session);
          return mapping.findForward("main");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,ownerForm);
          setFilterOptions(request,dfbsUtilityDAO,session);
          setList(request,oDAO,ownerForm);
          return mapping.findForward("main");
        } 
        else if (method.equals("refreshView")) 
        {
          String id = (String )request.getAttribute("DFBS_OWNER_REFRESH");
          this.setView(request,oDAO,ownerForm,id,session);
          return mapping.findForward("view");
        }
        else if (method.equals("view")) 
        {
          String id = request.getParameter("ownerId");
          this.setView(request,oDAO,ownerForm,id,session);
          return mapping.findForward("view");
        }
        else if (method.equals("viewOwner")) 
        { DfbsEntrPermit permit =pDAO.selectPermit(request.getParameter("permitNumber"));
          this.setView(request,oDAO,ownerForm,Integer.toString(permit.getOwnerId()),session);
          return mapping.findForward("view");
        }
        else if(method.equals("updateOwner")) 
        { String ownerId = request.getParameter("ownerId");
          DfbsOwner updatedOwner = oDAO.selectOwnerForUpdate(ownerId);
           ownerForm.setProperties(updatedOwner);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateOwner");
        } 
        else if(method.equals("saveUpdateOwner")) 
        { DfbsOwner updatedOwner = ownerForm.getDfbsOwner();
          oDAO.updateOwnerInternal(updatedOwner);
          ownerForm.setProperties(updatedOwner);
          this.setView(request,oDAO,ownerForm,Integer.toString(updatedOwner.getOwnerId()),session);
          return mapping.findForward("view");
        }         
         else if (method.equals("viewFees")) 
        { String permitNumber = request.getParameter("permitNumber");
          DfbsEntrPermit permit=new DfbsEntrPermit();
          permit.setPermitNumber(permitNumber);
          permit.setPermitFees(pDAO.selectPermitFees(permit.getPermitNumber()));
          session.setAttribute("PERMIT_FEE",permit);
           return mapping.findForward("view");
        }
        
        else if (method.equals("sendEmail")) 
        {
          sendEmail(request,oDAO);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          return mapping.findForward("sendEmailForm");
        }
        else if (method.equals("sendEmailForm")) 
        {
          return mapping.findForward("sendEmailForm");
        }
       
          // file upload download feature
         else if (method.equals("goToUpload")) 
         { String idNumber = request.getParameter("idNumber"); 
           String idType = request.getParameter("idType"); 
            DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
           DfbsEntrPermit permit = ownerApplication.getPermit(idNumber);
            if (idType.equals("AEPermit"))
                 { 
                 permit.setFileList(oDAO.selectFileList(idNumber,"AEPermit")); 
                 }
                 session.setAttribute("PERMIT_SELECTED",permit);
                  if (idType.equals("AESpecial"))
                 { DfbsEntrSpecial special = (DfbsEntrSpecial) session.getAttribute("SPECIAL_SELECTED");
                 special.setFileList(oDAO.selectFileList(idNumber,"AESpecial")); 
                 } 
                  session.setAttribute("FILE_EXTENSION","");
          return mapping.findForward("goToUpload");
         }
         else if (method.equals("IDHSgoToUpload")) 
         { String idNumber = request.getParameter("idNumber"); 
           String idType = request.getParameter("idType"); 
           DfbsEntrPermit permit = pDAO.selectPermit(idNumber);
            if (idType.equals("AEPermit"))
                 { 
                 permit.setFileList(oDAO.selectFileList(idNumber,"AEPermit")); 
                 }
                 session.setAttribute("PERMIT_SELECTED",permit);
                  if (idType.equals("AESpecial"))
                 { DfbsEntrSpecial special = sDAO.selectSpecial(idNumber);
                 special.setFileList(oDAO.selectFileList(idNumber,"AESpecial")); 
                 } 
                  session.setAttribute("FILE_EXTENSION","");
          return mapping.findForward("goToUpload");
         }
         
           else if (method.equals("downLoadFile")) 
         { 
          String fileType = request.getParameter("fileType");
          int fileId = Integer.parseInt(request.getParameter("fileId"));
          String fileName = request.getParameter("fileName");
          doGetFile(request,response,oDAO,fileName,fileType,fileId);
           return null;
         }
           
          else if (method.equals("uploadFile")) 
         { 
         
         final FormFile oneFile = ownerForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {    String idNumber = request.getParameter("idNumber"); 
                 String idType = request.getParameter("idType"); 
                 String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                   if (oneFile.getFileName().length() <=100 && (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF")||fileExtension.substring(1,4).toUpperCase().equals("XLS")))
                  {
                  oDAO.uploadFile(oneFile,idNumber,idType);  
                  
                   DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
                  DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
                  if ( owner !=null && ownerApplication !=null)
                  {
                  pDAO.computeFees(owner,ownerApplication);
                 
                 if (idType.equals("AEPermit"))
                 {DfbsEntrPermit permit = ownerApplication.getPermit(idNumber);
                 permit.setNoMap("uploaded:"+oneFile.getFileName());
                 permit.setFileList(oDAO.selectFileList(idNumber,"AEPermit"));
                 session.setAttribute("PERMIT_SELECTED",permit);
                 }
                 
                  if (idType.equals("AESpecial"))
                 { 
                 DfbsEntrSpecial special = (DfbsEntrSpecial) session.getAttribute("SPECIAL_SELECTED");
                 special.setNoMap("uploaded:"+oneFile.getFileName());
                 special.setFileList(oDAO.selectFileList(idNumber,"AESpecial")); 
                 } 
                  }
                }
                else
                {
                session.setAttribute("FILE_EXTENSION","ERROR");
                return mapping.findForward("goToUpload");
                }
            }
           return mapping.findForward("step2");
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
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO,HttpSession session) throws Exception 
 {
      List charNav = uDAO.getCharacterNavs(DfbsEntrSQL.SELECT_FIRST_LETTER_OPTIONS);
    List otherCounties = uDAO.getOptions(DfbsEntrSQL.SELECT_COUNTIES_OPTIONS);
    List cities = uDAO.getOptions(DfbsEntrSQL.SELECT_CITIES_OPTIONS);
    
    
    request.setAttribute("DFBS_OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
    request.setAttribute("DFBS_CITIES_OPTIONS",cities);
   
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
  
 private static void setList(HttpServletRequest request,DfbsEntrOwnerDAO fwDAO,DfbsOwnerForm ownerForm) throws Exception
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
        list = fwDAO.selectOwnersList(DfbsEntrSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = fwDAO.selectOwnersList(DfbsEntrSQL.SELECT_OWNERS_BY_LETTER,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCounty")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsEntrSQL.SELECT_OWNERS_BY_COUNTY,filterSession.getValue());
        ownerForm.setState(filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byCity")) 
     {
        
        list = fwDAO.selectOwnersList(DfbsEntrSQL.SELECT_OWNERS_BY_CITY,filterSession.getValue());
        ownerForm.setCity(filterSession.getValue());
     } 
    
    
     request.setAttribute("DFBS_OWNER_LIST",new HsListWrapper(list));
     
  }
  protected static DfbsOwner setView(HttpServletRequest request,
    DfbsEntrOwnerDAO fwDAO,
      DfbsOwnerForm ownerForm,String id,HttpSession session) throws Exception
  {
   
    DfbsOwner owner = fwDAO.selectOwner(id);
    session.setAttribute("DFBS_OWNER",owner);
    ownerForm.setProperties(owner);
    session.setAttribute("PERMIT_FEE",null);         
    
    return owner;
  }
  
 private void sendEmail(HttpServletRequest request,DfbsEntrOwnerDAO oDAO) throws Exception
  {
          try {
           
           
            HsMailer mail = new HsMailer();
            String[] emailTo = {"cclouse@dhs.in.gov"};
          //  String[] emailTo = {"nnimmaga"};
           String[] bccTo = {"nnimmagadda"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append(" From Entertainment Online enquiry - ").append(subject);
          
          
          
            mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From Entertainment Online enquiry error email ",name+":"+email);
            mail1.sendAndClose();
          
            }
  }
  
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,DfbsEntrOwnerDAO oDAO,String fileName,String fileType,int fileId) throws Exception 
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

  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
  {
     List states = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS);
     
     request.setAttribute("DFBS_STATE_OPTIONS",states);
  
 
}
}