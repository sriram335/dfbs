package ems.control.action;


import childcare.to.DfbsChildcarePermit;

import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import main.to.*;
import main.data.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;
import hs.control.*;
import hs.control.form.*;
import hs.to.*;
import hs.util.*;
import otherUsers.to.*;
import hs.data.system.*;


public class EmsPersonAction extends ControlAction
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
        
        EmsPersonForm personForm = (EmsPersonForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsPersonDAO pDAO = (EmsPersonDAO) dmap2.getHsDAO(DfbsDataMap.EMS_PERSON);
         ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
      if (method.equals("personList"))
        {  
           int providerId = Integer.parseInt(request.getParameter("providerId"));
           setList(request,pDAO,personForm,providerId,"P");
           return mapping.findForward("personList");
        } 
         if (method.equals("personLookup"))
        {  String blasterFlag =request.getParameter("blasterFlag");
          session.setAttribute("EMS_PERSON_LOOKUP",blasterFlag);
           FileNames names= sDAO.selectAppStatus("EMS_CERT_MAINT_FLAG");
            session.setAttribute("EMS_CERT_APP_STATUS",names.getFileName());
            session.setAttribute("EMS_CERT_APP_MESSAGE",names.getFileType());
           return mapping.findForward("personLookup");
        } 
       
       
          if (method.equals("addNewPerson"))
        {   int relationId = Integer.parseInt(request.getParameter("relationId"));
           String personType=request.getParameter("personType");
          session.setAttribute("EMS_PERSON_LOOKUP","N");
           EmsPerson person =  new EmsPerson();
           
           if (personType.equals("I"))
           {
              person.setPersonInstId(relationId);
           }
            
           if (personType.equals("H"))
           {
              person.setPersonHospId(relationId);
           }
            
           if (personType.equals("P"))
           { 
              person.setPersonProvId(relationId);
           }
           
           return mapping.findForward("addNewPerson");
        } 
         if (method.equals("addPerson"))
         {
           return mapping.findForward("personDetail");
         }
        // inst persons
          if (method.equals("personInstList"))
        {  int institutionId = Integer.parseInt(request.getParameter("institutionId"));
           setList(request,pDAO,personForm,institutionId,"I");
           return mapping.findForward("personList");
        } 
        // hosp persons
         if (method.equals("personHospList"))
        {  int hospitalId = Integer.parseInt(request.getParameter("hospitalId"));
           setList(request,pDAO,personForm,hospitalId,"H");
           return mapping.findForward("addNewPerson");
        } 
          if (method.equals("personListLookup"))
        {    String lastName = personForm.getPersonLastName();
            setListLookup(request,pDAO,personForm,lastName);
            session.setAttribute("EMS_PERSON_LOOKUP","Y");
           return mapping.findForward("addNewPerson");
        } 
         if (method.equals("addNewContact"))
        {  int id = Integer.parseInt(request.getParameter("personId"));
           this.setViewNewContact(request,pDAO,personForm,id);
          
            return mapping.findForward("addNewContact");
        } 
          if (method.equals("saveNewContact"))
        {  int id = Integer.parseInt(request.getParameter("emsPersonId"));
           String personTitle = request.getParameter("personTitle");
           String personType = request.getParameter("personType");
           String relationType = (String)session.getAttribute("EMS_PERSON_LIST_TYPE");
           String relationId = (String)session.getAttribute("EMS_PERSON_LIST_ID");
           pDAO.addNewContact(id,Integer.parseInt(relationId),relationType,personTitle,personType);
           this.setViewNewContact(request,pDAO,personForm,id);
          
            return mapping.findForward("addNewContact");
        } 
      else if (method.equals("personDetail")) 
        {
           int id = Integer.parseInt(request.getParameter("emsPersonId"));
           int institutionId = Integer.parseInt(request.getParameter("personInstId"));
           int hospitalId = Integer.parseInt(request.getParameter("personHospId"));
           int providerId = Integer.parseInt(request.getParameter("personProvId"));
           setOptions(request,dfbsUtilityDAO);
           if (institutionId >0)
          {
          this.setView(request,pDAO,personForm,id,institutionId,"I");
          }
           if (hospitalId >0)
          {
          this.setView(request,pDAO,personForm,id,hospitalId,"H");
          }
           if (providerId >0)
          {
          this.setView(request,pDAO,personForm,id,providerId,"P");
          }
          return mapping.findForward("personDetail");
        }
                
        else if(method.equals("savePerson")) 
         
       { 
          EmsPerson person =  personForm.getEmsPerson();
          EmsPersonForm errorForm = new EmsPersonForm();
          String relationType = (String)session.getAttribute("EMS_PERSON_LIST_TYPE");
          int relationId = Integer.parseInt((String)session.getAttribute("EMS_PERSON_LIST_ID"));
           if (validatePerson(person,errorForm))
          {  if (person.getEmsPersonId()==0)
            {
              pDAO.insertPerson(person,relationType,relationId);
            }
            else
            {
             pDAO.updatePerson(person,relationType,relationId);
            }
            
            if (relationType == "P")
            {
             
             setList(request,pDAO,personForm,relationId,"P");
            }
            else if  (relationType == "H")
            {
             setList(request,pDAO,personForm,relationId,"H");
            }
             else if  (relationType == "I")
            {
              setList(request,pDAO,personForm,relationId,"I");
            }
             return mapping.findForward("personList");
          }
          else
          { request.setAttribute("USER_ERROR",errorForm);
           return mapping.findForward("personDetail");
          }
        }
        // file upload download feature
    /*    else if (method.equals("goToUpload"))
        { 
        String psid = request.getParameter("psid");
        EmsInstructors instructor = pDAO.selectPerson(personId, relationId, relationType));
        instructor.setFileList(pDAO.selectFileList(idNumber));
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
        
        return mapping.findForward("goToUpload");
        }
        else if (method.equals("uploadFile"))
        {
        
        final FormFile oneFile = personForm.getCaseFile();
        if(oneFile != null && oneFile.getFileSize()>0 )
        {    DfbsChildcarePermit permit = (DfbsChildcarePermit) session.getAttribute("PERMIT_SELECTED");
            String idNumber = permit.getPermitNumber(); 
            DfbsOwner owner = ( DfbsOwner) session.getAttribute("OWNER_RENEW");
            DfbsChildcarePermit uploadPermit = owner.getPermit(permit.getPermitNumber());
            String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
           // System.out.println( (fileExtension.substring(1,4)));
            if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                 fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                 fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                 fileExtension.substring(1,4).toUpperCase().equals("DWF"))
             {
             pDAO.uploadFile(oneFile,idNumber);  
             uploadPermit.setFileList(pDAO.selectFileList(idNumber));
            session.setAttribute("PERMIT_SELECTED",uploadPermit);
            uploadPermit.setUploadStatus("Y");  
           }
           else
           {
           session.setAttribute("FILE_EXTENSION","ERROR");
           return mapping.findForward("goToUpload");
           }
        }
        return mapping.findForward("goToUpload");
        } */
          
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
        request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
  
  private static void setList(HttpServletRequest request,EmsPersonDAO epDAO,EmsPersonForm personForm,int relationId, String personType) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
        if (personType == "P")
        {
        list = epDAO.selectPersonList(EmsSQL.SELECT_PERSON_LIST,relationId,personType);
        session.setAttribute("EMS_PERSON_LIST_TYPE","P");
        session.setAttribute("EMS_PERSON_LIST_ID",Integer.toString(relationId));
        }
        if (personType == "I")
        {
        list = epDAO.selectPersonList(EmsSQL.SELECT_PERSON_LIST_INST,relationId,personType);
         session.setAttribute("EMS_PERSON_LIST_TYPE","I");
         session.setAttribute("EMS_PERSON_LIST_ID",Integer.toString(relationId));
        }
        if (personType == "H")
        {
        list = epDAO.selectPersonList(EmsSQL.SELECT_PERSON_LIST_HOSP,relationId,personType);
         session.setAttribute("EMS_PERSON_LIST_TYPE","H");
         session.setAttribute("EMS_PERSON_LIST_ID",Integer.toString(relationId));
        }
    
     request.setAttribute("PERSON_LIST",new HsListWrapper(list));
     
  }
  protected static EmsPerson setView(HttpServletRequest request,
    EmsPersonDAO rDAO,
      EmsPersonForm personForm, int id, int relationId, String relationType) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsPerson person = rDAO.selectPerson(id,relationId,relationType);
    
    session.setAttribute("EMS_PERSON",person);
    
    personForm.setProperties(person);
    return person; 
   
  }
  protected static boolean validatePerson(EmsPerson person ,EmsPersonForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(person.getPersonFirstName() == null || person.getPersonFirstName().trim().equals("")  )
    {
    errorForm.setPersonFirstName("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setPersonFirstName("");
       
    }
     return noError;
  } 
  private static void setListLookup(HttpServletRequest request,EmsPersonDAO epDAO,
        EmsPersonForm personForm, String lastName) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
       
        list = epDAO.selectPersonListLookup(EmsSQL.SELECT_PERSON_LIST_LOOKUP,lastName);
       
        
    
     request.setAttribute("PERSON_LIST_LOOKUP",new HsListWrapper(list));
     
  }
  
   protected static EmsPerson setViewNewContact(HttpServletRequest request,
    EmsPersonDAO rDAO,
      EmsPersonForm personForm, int id) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    String relationType = (String)session.getAttribute("EMS_PERSON_LIST_TYPE");
    String relationId = (String)session.getAttribute("EMS_PERSON_LIST_ID");
    EmsPerson person = rDAO.selectPerson(id,Integer.parseInt(relationId),relationType);
    
    session.setAttribute("EMS_PERSON_NEW_CONTACT",person);
    
    personForm.setProperties(person);
    return person; 
   
  }
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
    
     List personType = uDAO.getOptions(EmsSQL.SELECT_PERSON_TYPE_OPTIONS);
   
    request.setAttribute("INST_PERSON_TYPE_OPTIONS",personType);
    
    
 } 
 
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,EmsPersonDAO pDAO,String fileName,String fileType,int fileId) throws Exception 
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
}
