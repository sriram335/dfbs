package elevator.control.action;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import hs.control.*;
import hs.control.form.*;
import elevator.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import main.data.*;
import elevator.data.*;

import elevator.to.*;

import hs.data.system.*;


import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;

import org.apache.struts.upload.FormFile;

import ust.to.ustApplicant;

public class elevServContractorAction extends ControlAction{    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context =     this.getServlet().getServletConfig().getServletContext();
         
          DfbsDataMap dmap = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap.getHsDAO(DfbsDataMap.DFBS_SECURITY);
          elevServiceContractorForm contForm = (elevServiceContractorForm) form;
         
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap.getHsDAO(DfbsDataMap.UTILITY);
          elevServContractorDAO cDAO = (elevServContractorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_SERVICE_CONTRACTOR);
          elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
            ApplicationContacts contacts = sDAO.setContacts("ELEVATOR_CONTACT1","ELEVATOR_CONTACT2");
            session.setAttribute("APPLICATION_CONTACTS",contacts);
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            if (method == null) 
            { 
                        setList(request,cDAO);
                        setFilterOptions(request,dfbsUtilityDAO);
                        setOptions(request,dfbsUtilityDAO);
                        return mapping.findForward("selectContractor");
            } 
            else if (method.equals("refresh")) 
            {
              setList(request,cDAO);
              setFilterOptions(request,dfbsUtilityDAO);
              return mapping.findForward("selectContractor");
            } 
            else if (method.equals("filter")) 
            {
              setFilterSession(request);
              setList(request,cDAO);
               setFilterOptions(request,dfbsUtilityDAO);
               return mapping.findForward("selectContractor");
            }
            else if (method.equals("emailLicence")) 
            {    elevServiceContractor cont = (elevServiceContractor) session.getAttribute("SERVICE_CONTRACTOR_SELECTED");
                 this.emailLicence(cont.getLicNumber(),cont.getContEmail(),contacts);
                  StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                  redirectUrl.append("/dfbs/elevator/servContractor.do");
                  response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                  return null;
             }
            else if (method.equals("renew")) 
            {String contId = request.getParameter("contId");
              setFilterOptions(request,dfbsUtilityDAO);
              elevServiceContractor cont = cDAO.selectElevContractor(Integer.parseInt(contId)); 
              contForm.setProperties(cont);
              cont.setFileList(cDAO.selectFileList(Integer.toString(cont.getContId()))); 
              session.setAttribute("SERVICE_CONTRACTOR_SELECTED",cont);
             return mapping.findForward("renewContractor");
            }   
            else if (method.equals("backToContractor")) 
            {elevServiceContractor cont = (elevServiceContractor) session.getAttribute("SERVICE_CONTRACTOR_SELECTED");
              setFilterOptions(request,dfbsUtilityDAO);
             setOptions(request,dfbsUtilityDAO);
              contForm.setProperties(cont); 
              if (cont.getContId()==0) {
                return mapping.findForward("newContractor");
              }
              else
              {
              return mapping.findForward("renewContractor");
              }
            }   
            else if (method.equals("newContractor")) 
            { setFilterOptions(request,dfbsUtilityDAO);
              setOptions(request,dfbsUtilityDAO);
              session.setAttribute("SERVICE_CONTRACTOR_SELECTED",null);
                return mapping.findForward("newContractor");
            }  
            else if (method.equals("saveNewContractor")) 
            { setFilterOptions(request,dfbsUtilityDAO);
              setOptions(request,dfbsUtilityDAO);
              elevServiceContractor cont = contForm.getContractor();
              elevServiceContractorForm errorForm = new elevServiceContractorForm();
              if ( validate(cont,errorForm,session)) 
              {
                cDAO.calculateElevServContFee(cont);
              }
              else
              {   request.setAttribute("SERVICE_CONTRACTOR_ERROR",errorForm);            
              }
             // cDAO.insertElevContractor(cont);
             cDAO.calculateElevServContFee(cont);
              contForm.setProperties(cont); 
              session.setAttribute("SERVICE_CONTRACTOR_SELECTED",cont);
                return mapping.findForward("newContractor");
            }   
            else if (method.equals("update")) 
            {String contId = request.getParameter("contId");
              setFilterOptions(request,dfbsUtilityDAO);
              setOptions(request,dfbsUtilityDAO);
              elevServiceContractor cont = cDAO.selectElevContractor(Integer.parseInt(contId)); 
              contForm.setProperties(cont); 
              cont.setFileList(cDAO.selectFileList(Integer.toString(cont.getContId())));
              session.setAttribute("SERVICE_CONTRACTOR_SELECTED",cont);
              return mapping.findForward("updateContractor");
            }   
            else if (method.equals("saveUpdate")) 
            {
              setFilterOptions(request,dfbsUtilityDAO);
              setOptions(request,dfbsUtilityDAO);
              elevServiceContractor cont = contForm.getContractor();
              cDAO.updateElevServiceContractor(cont);
              contForm.setProperties(cont); 
              session.setAttribute("SERVICE_CONTRACTOR_SELECTED",cont);
              return mapping.findForward("updateContractor");
            }   
            else if (method.equals("saveUpdateRenewal")) 
            {
              setFilterOptions(request,dfbsUtilityDAO);
              setOptions(request,dfbsUtilityDAO);
              elevServiceContractor cont = contForm.getContractor();
              elevServiceContractorForm errorForm = new elevServiceContractorForm();
              if ( validate(cont,errorForm,session)) 
              {
                cDAO.calculateElevServContFee(cont);
              }
              else
              {   request.setAttribute("SERVICE_CONTRACTOR_ERROR",errorForm);            
              }
             // cDAO.updateElevServiceContRenewal(cont);
              contForm.setProperties(cont); 
              session.setAttribute("SERVICE_CONTRACTOR_SELECTED",cont);
              return mapping.findForward("renewContractor");
            }   
            else if (method.equals("downLoadCont")) 
            { String fileName = request.getParameter("fileName");
            String fileType = request.getParameter("fileType");
            int fileId = Integer.parseInt(request.getParameter("fileId"));
            doGetFile(request,response,eDAO,fileName,fileType,fileId);
            return null;
                }
            else if (method.equals("goToUpload")) 
            {
            elevServiceContractor cont = (elevServiceContractor) session.getAttribute("SERVICE_CONTRACTOR_SELECTED");
            if (cont.getContId()==0) {
              contForm.setContId(100);
              cont.setFileList(cDAO.selectFileList("100"));  
            }
            else
            {
             contForm.setContId(cont.getContId());
             cont.setFileList(cDAO.selectFileList(Integer.toString(cont.getContId()))); 
            }
            return mapping.findForward("goToUpload");
            }
            else if (method.equals("uploadFile")) 
                    { 
                    
                    final FormFile oneFile = contForm.getCaseFile();
                      if(oneFile != null && oneFile.getFileSize()>0 )
                       {    elevServiceContractor cont = (elevServiceContractor) session.getAttribute("SERVICE_CONTRACTOR_SELECTED");
                              String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                            if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                                fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                                 fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                                 fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                                 fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                             {
                             eDAO.uploadFile(oneFile,Integer.toString(cont.getContId()),null,"ElevatorCont");  
                             cont.setFileList(cDAO.selectFileList(Integer.toString(cont.getContId())));
                             return mapping.findForward("uploadCont");
                           }
                           else
                           {
                           session.setAttribute("FILE_EXTENSION","ERROR");
                           return mapping.findForward("uploadCont");
                           }
                       }
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
         List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS_CONT);
        List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS2);
         request.setAttribute("CONTRACTOR_FIRST_LETTERS_OPTIONS",charNav);
        request.setAttribute("DFBS_STATE_OPTIONS",states);
         
      }
      protected static void setFilterSession(HttpServletRequest request) throws Exception
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
       
          session.setAttribute("CONTRACTOR_LIST_FILTER",filterSession);
         
       }
       
      private static void setList(HttpServletRequest request,elevServContractorDAO cDAO) throws Exception
       {
       
          HttpSession session = request.getSession();
          
          List list = null;
          
          HsFilter filterSession = (HsFilter) session.getAttribute("CONTRACTOR_LIST_FILTER");
          if(filterSession == null) {
             filterSession = new HsFilter();
             filterSession.setType("byLetter");
             filterSession.setValue("A");
             session.setAttribute("CONTRACTOR_LIST_FILTER",filterSession);
             list = cDAO.selectElevContractorList("A");
          } 
          else if(filterSession.getType().equals("byLetter")) 
          {
             list = cDAO.selectElevContractorList(filterSession.getValue());
          } 
         
          request.setAttribute("ELEV_SERV_CONTRACTOR_LIST",new HsListWrapper(list));
          
       }
                                                                     
      protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
      {
         List states = uDAO.getOptions(elevatorSQL.SELECT_STATE_OPTIONS);
         List charNav = uDAO.getCharacterNavs(elevatorSQL.SELECT_FIRST_LETTER_OPTIONS_CONT);
        List conType = uDAO.getOptions(elevatorSQL.SELECT_CON_TYPE_OPTIONS);
        List conParentList = uDAO.getOptions(elevatorSQL. ELEV_CONTACTORS_OPTIONS);
        List conStatusList = uDAO.getOptions(elevatorSQL. SELECT_CON_STATUS_OPTIONS);
         request.setAttribute("CONTRACTOR_STATE_OPTIONS",states);
        request.setAttribute("CONTRACTOR_FIRST_LETTERS_OPTIONS",charNav);
        request.setAttribute("CON_TYPE_OPTIONS",conType);
        request.setAttribute("CON_PARENT_OPTIONS",conParentList); 
        request.setAttribute("CON_STATUS_OPTIONS",conStatusList);  
      } 
      
      protected static boolean validate(elevServiceContractor cont,elevServiceContractorForm errorForm,HttpSession session) 
       {
         boolean noError = true;
          ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
       
       if (cont.getContId() == 0)
       {
         
         if(cont.getContLastName() == null || cont.getContLastName().trim().equals("") ) 
         {
           errorForm.setContLastName("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setContLastName("");
         }
         if(cont.getContFirstName() == null || cont.getContFirstName().trim().equals("") ) 
         {
           errorForm.setContFirstName("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setContFirstName("");
         }
         if(cont.getContType() == null || cont.getContType().trim().equals("") ) 
         {
           errorForm.setContType("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setContType("");
         }
       
       } 
         // renewal
         if(cont.getContAddress1() == null || cont.getContAddress1().trim().equals("") ) 
          {
            errorForm.setContAddress1("ERROR");
            noError = false;
          } 
          else 
          {
            errorForm.setContAddress1("");
          }
          
          if(cont.getContCity() == null || cont.getContCity().trim().equals("") ) 
          {
            errorForm.setContCity("ERROR");
            noError = false;
          } 
          else 
          {
            errorForm.setContCity("");
          }
          if(cont.getContState() == null || cont.getContState().trim().equals("")  ) 
          {
            errorForm.setContState("ERROR");
            noError = false;
          } 
          else 
          {
            errorForm.setContState("");
          }
          
          if(cont.getContZip() == null || cont.getContZip().trim().equals("") ) 
          {
            errorForm.setContZip("ERROR");
            noError = false;
          } 
          else 
          {
            errorForm.setContZip("");
          }
     
        if(cont.getContEmail() == null || cont.getContEmail().trim().equals("") ||
            cont.getContEmail().indexOf('@') <=0 || cont.getContEmail().indexOf('.') <= 0 ) 
         {
           errorForm.setContEmail("ERROR");
           noError = false;
         } 
         else 
         { 
           errorForm.setContEmail("");
         }
         
         if(cont.getContPhone() == null || cont.getContPhone().trim().equals("") ||
            cont.getContPhone().indexOf('-') > 0 ||cont.getContPhone().indexOf(')') > 0 ||
            cont.getContPhone().indexOf('(') > 0) 
         {
           errorForm.setContPhone("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setContPhone("");
         }
        
          
         return noError;
       }
      public void doGetFile(HttpServletRequest request, HttpServletResponse response,elevatorDAO eDAO,String fileName,String fileType,int fileId) throws Exception 
      {
      try {response.setContentType(fileType);
      response.setHeader("Content-Disposition","in-line;filename=\""+fileName+"\"");
      java.io.OutputStream out = response.getOutputStream();
      int lenFile = eDAO.downLoad(out,fileId);
      out.flush();
      out.close();
      }
           catch(Exception e) 
           {
             throw new Exception("ERROR_FILE_DOWNLOAD_FAILED");
           }
      
      }
      private static void emailLicence(String licNumber ,String userEmail, ApplicationContacts contacts) throws Exception
      {
             try {
                
               HsMailer mail = new HsMailer();
             //    String[] emailTo = {userEmail};
              String[] emailTo = {"nnimmagadda@dhs.in.gov"};
              String[] bccTo = {contacts.getContact1Email(),contacts.getContact2Email()};
               StringBuffer sb = new StringBuffer();
               sb.append("This is to inform you that after reviewing your application, we have approved your ").append("\n\r");
               sb.append("application for Elevator Service Contractor Licence for state of Indiana.").append("\n\r");
               sb.append("Use this link print certification.Use Internet Explorer as the browser.").append("\n\r");
               sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=elev_con_licence.rdf&p_lic_number="+licNumber).append("\n\r");
                 sb.append("Use this link to verify status or renew certification or print certification at any future date.").append("\n\r");
                 sb.append("https://oas.dhs.in.gov/dfbs/elevator/servContractor.do").append("\n\r"); 
               StringBuffer sub = new StringBuffer();
               sub.append(" Your Elevator Service Contractor Licence is approved by Fire Marshal office, IDHS, State of Indiana ");
              
               mail.createMail(contacts.getContact1Email(),emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
               } 
               catch(Exception e) 
               {
                 HsMailer mail1 = new HsMailer();
                 String[] emailTo = {contacts.getContact1Email(),contacts.getContact2Email()};
                 String[] bccTo = {"nnimmagadda@dhs.in.gov"};
                mail1.createMail(contacts.getContact1Email(),emailTo,bccTo," From ust certification "," ust email cert: "+licNumber);
              mail1.sendAndClose();     
              }
      }
    }

