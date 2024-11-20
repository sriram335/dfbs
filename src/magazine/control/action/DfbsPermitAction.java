package magazine.control.action;
import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
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
        
        DfbsPermitForm permitForm = (DfbsPermitForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
         String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
        if (method == null) 
        { DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          if (selectedOwner.getOwnerId() == 0)
          { setOptions(request,dfbsUtilityDAO); 
            return mapping.findForward("newPermit");
          }
          else
          {setOptions(request,dfbsUtilityDAO); 
          DfbsPermit selectedPermit = (DfbsPermit) session.getAttribute("PERMIT_SELECTED");
          DfbsPermit permit = pDAO.selectPermit(selectedPermit.getMagIdNumber(),"Old");
          if (permit.getMagIdNumber() == null || permit.getMagIdNumber().equals(""))
          {
            return mapping.findForward("newPermit");
          }
          else
          { 
            permitForm.setProperties(permit);
            session.setAttribute("PERMIT_SELECTED",permit);
             return mapping.findForward("renewPermit");          
          }
          }
        } 
        // need to set form properties here 
        else if (method.equals("checkOut")) 
        { setOptions(request,dfbsUtilityDAO); 
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsPermit permit = permitForm.getDfbsPermit();
          DfbsPermitForm errorForm = new DfbsPermitForm();
          if (validate(permit,errorForm,session) ) {
          session.setAttribute("PERMIT_SELECTED",permit);
          // to see if owner in cart
          String permitNew="";
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
            inMapOwner.addPermit(permit);
            if (permit.getMagIdNumber().equals(""))
            { permitNew="Y";
            }
            else
            { permitNew="N";
            }
            pDAO.computeFees(cart,permitNew);
            setOptions(request,dfbsUtilityDAO); 
            permitForm.setProperties(permit);
            return mapping.findForward("renewPermit");
          }
          else
          {
          selectedOwner.addPermit(permit);
          cart.addOwner(selectedOwner);
          if (permit.getMagIdNumber().equals(""))
            { permitNew="Y";
            }
            else
            { permitNew="N";
            }
          pDAO.computeFees(cart,permitNew);
          setOptions(request,dfbsUtilityDAO); 
          permitForm.setProperties(permit);
          return mapping.findForward("renewPermit");
          }
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERMIT_ERROR",errorForm);
            permitForm.setProperties(permit);
           session.setAttribute("PERMIT_SELECTED",permit);
           return mapping.findForward("renewPermit");
          }
        } 
        else if (method.equals("checkOutNew")) 
        { DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsPermit permit = permitForm.getDfbsPermit();
          DfbsPermitForm errorForm = new DfbsPermitForm();
          if (validate(permit,errorForm,session) ) {
          session.setAttribute("PERMIT_SELECTED",permit);
          setOptions(request,dfbsUtilityDAO); 
          permitForm.setProperties(permit);
          selectedOwner.addPermit(permit);
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
            permitForm.setProperties(permit);
           session.setAttribute("PERMIT_SELECTED",permit);
           return mapping.findForward("newPermit");
          }
        } 
          else if (method.equals("renew")) 
           {  String permitNumber= request.getParameter("permitNumber");
             DfbsPermit permit = pDAO.selectPermit(permitNumber,"Old");
              setOptions(request,dfbsUtilityDAO); 
                permitForm.setProperties(permit);
                session.setAttribute("PERMIT_SELECTED",permit);
                 return mapping.findForward("renewPermit"); 
            }
        else if (method.equals("newPermit")) 
         {  setOptions(request,dfbsUtilityDAO); 
            return mapping.findForward("newPermit");
          }
            else if (method.equals("addPermit")) 
         {  String ownerKey= request.getParameter("ownerKey");
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
            DfbsPermit selectedPermit = (DfbsPermit) session.getAttribute("PERMIT_SELECTED");
            permitForm.setProperties(selectedPermit);
             return mapping.findForward("renewPermit");
          }  
          else if (method.equals("backToPermit")) 
         {  setOptions(request,dfbsUtilityDAO);
            DfbsPermit selectedPermit = (DfbsPermit) session.getAttribute("PERMIT_SELECTED");
            permitForm.setProperties(selectedPermit);
             return mapping.findForward("renewPermit");
          }  
       
          else if (method.equals("editPermit")) 
        {  
           String ownerKey= request.getParameter("ownerKey");
           String permitKey= request.getParameter("permitKey");
           DfbsOwner selectedOwner = cart.getOwner(ownerKey);
           session.setAttribute("OWNER_SELECTED",selectedOwner);
           DfbsPermit editPermit = selectedOwner.getPermit(permitKey);
           session.setAttribute("PERMIT_SELECTED",editPermit);
           permitForm.setProperties(editPermit);
           setOptions(request,dfbsUtilityDAO);
           if (editPermit.getMagIdNumber() == null || editPermit.getMagIdNumber().equals(""))
           {
          return mapping.findForward("newPermit");
           }
          else
          {
          return mapping.findForward("editPermit");
          }
        } 
            else if (method.equals("viewDetails")) 
        {  ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
          if (security !=null)
          {
           String permitKey= request.getParameter("permitKey");
           DfbsPermit permit = pDAO.selectPermit(permitKey,"New");
           permit.setFileList(pDAO.selectFileList(permit));
           session.setAttribute("PERMIT_SELECTED",permit);
           permitForm.setProperties(permit);
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("viewDetails");
          }
          else
          {
             return mapping.findForward("logOn");
          }
        } 
         else if (method.equals("saveEditPermit")) 
        { DfbsPermit updatedPermit = permitForm.getDfbsPermit();
          DfbsPermitForm errorForm = new DfbsPermitForm();
          if (validate(updatedPermit,errorForm,session) ) {
          DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          DfbsPermit permit = selectedOwner.getPermit(updatedPermit.getMagPermitKey());
          permitForm.setUpdatedProperties(updatedPermit,permit);
            session.setAttribute("PERMIT_SELECTED",permit);
             permitForm.setProperties(permit);
             return mapping.findForward("renewPermit");
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("PERSON_ERROR",errorForm);
           permitForm.setProperties(updatedPermit);
           return mapping.findForward("editPermit");
          }
          } 
           else if (method.equals("removePermit")) 
        {  String ownerKey= request.getParameter("ownerKey");
           String permitKey= request.getParameter("permitKey");
           DfbsOwner selectedOwner = cart.getOwner(ownerKey);
           DfbsPermit removePermit = selectedOwner.getPermit(permitKey);
          /* cart.setAmount(cart.getAmount()-removePermit.getAmount());
           cart.setTotalPermits(cart.getTotalPermits()-1); */
          selectedOwner.removePermit(permitKey);
           pDAO.computeFees(cart,"N");
          setOptions(request,dfbsUtilityDAO); 
          DfbsPermit selectedPermit = (DfbsPermit) session.getAttribute("PERMIT_SELECTED");
          permitForm.setProperties(selectedPermit);
             return mapping.findForward("newPermit");
        } 
        
         else if(method.equals("printPermit")) 
        {
          String key1 =  request.getParameter("key1");
          String key2 =  request.getParameter("key2");
          String key3 =  request.getParameter("key3");
           List list = new ArrayList();
           
           list = pDAO.selectPermitToPrint(key1,key2,key3); 
          
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
         
            String realPath =  context.getInitParameter("realPath");
          MagazinePermitPdf pdf = 
            new MagazinePermitPdf(list,baos,realPath+"dhs_logo.jpg",
                 realPath+"dhs_logo2.jpg",
                 realPath+"fireMarshalSignature.gif"); 
            /*
         MagazinePermitPdf pdf =
         new MagazinePermitPdf(list,baos,context.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
            context.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
              context.getRealPath("/WEB-INF/img/fireMarshalSignature.gif")); 
            */

          request.setAttribute("HS_PDF",pdf);
         
          return mapping.findForward("pdf");
        } 
         else if(method.equals("approvePermit")) 
        {
          String Id =  request.getParameter("key1");
           oDAO.updateIssueDate(Id,oDAO.selectCurrentDate(),"xx");
           DfbsPermit permit = pDAO.selectPermit(Id,"New");
           DfbsOwner owner = oDAO.selectOwner(permit.getMagOwnerId());
           String permitKey=pDAO.getPrintKey(permit.getMagIdNumber());
           pDAO.updatePermitPrintKey(permit.getMagIdNumber(),permitKey,"Permit");
           String printFlag ="Permit";
           this.sendEmailPermitReady(Id,pDAO,oDAO,session,owner,printFlag,permitKey);
            setOptions(request,dfbsUtilityDAO); 
          return mapping.findForward("ownerListDisplayAll");
        } 
          else if(method.equals("approveAllPermits")) 
        {
          String Id =  request.getParameter("key1");
          String appCounty =  request.getParameter("appCounty");
          String inspFacId =  request.getParameter("inspFacId");
           oDAO.updateIssueDate(Id,oDAO.selectCurrentDate(),appCounty);
            DfbsOwner owner = oDAO.selectOwner(Integer.parseInt(Id));
            String printFlag ="All";
            String permitKey=pDAO.getPrintKey(owner.getOwnerLastName().substring(1,4));
            pDAO.updatePermitPrintKey(Integer.toString(owner.getOwnerId()),permitKey,"All");
           this.sendEmailPermitReady(inspFacId,pDAO,oDAO,session,owner,printFlag,permitKey);
           setOptions(request,dfbsUtilityDAO); 
          return mapping.findForward("ownerListDisplayAll");
        } 
         else if (method.equals("goToUpload")) 
         { String ownerKey= request.getParameter("ownerKey");
           String permitKey= request.getParameter("permitKey");
           DfbsOwner selectedOwner = cart.getOwner(ownerKey);
           DfbsPermit selectedPermit = selectedOwner.getPermit(permitKey);
           selectedPermit.setFileList(pDAO.selectFileList(selectedPermit));
           session.setAttribute("PERMIT_SELECTED_UPLOAD",selectedPermit);
           session.setAttribute("OWNER_UPLOAD","N");
           session.setAttribute("FILE_EXTENSION","");
         return mapping.findForward("goToUpload");
         }
        else if (method.equals("goToUploadOwner")) 
        { String ownerKey= request.getParameter("ownerKey");
          session.setAttribute("PERMIT_SELECTED_UPLOAD",null);
          session.setAttribute("OWNER_UPLOAD",ownerKey);
          session.setAttribute("FILE_EXTENSION","");
        return mapping.findForward("goToUpload");
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
         
         final FormFile oneFile = permitForm.getCaseFile();
           if(oneFile != null && oneFile.getFileSize()>0 )
            {   String fileExtension = oneFile.getFileName().substring(oneFile.getFileName().indexOf("."));
                if (fileExtension.substring(1,4).toUpperCase().equals("DOC") || fileExtension.substring(1,4).toUpperCase().equals("JPG") || fileExtension.substring(1,4).toUpperCase().equals("PDF") ||
                     fileExtension.substring(1,4).toUpperCase().equals("BMP") || fileExtension.substring(1,4).toUpperCase().equals("JPE") || fileExtension.substring(1,4).toUpperCase().equals("TIF")||
                      fileExtension.substring(1,4).toUpperCase().equals("GIF")|| fileExtension.substring(1,4).toUpperCase().equals("TXT")||
                      fileExtension.substring(1,4).toUpperCase().equals("HTM")||fileExtension.substring(1,4).toUpperCase().equals("PPT")||fileExtension.substring(1,4).toUpperCase().equals("RTF")||
                      fileExtension.substring(1,4).toUpperCase().equals("DWF"))
                  {
                  DfbsPermit permit = (DfbsPermit) session.getAttribute("PERMIT_SELECTED_UPLOAD");
                  String ownUpload =(String) session.getAttribute("OWNER_UPLOAD");
                  if (ownUpload.equals("N")) {
                    pDAO.uploadFile(oneFile,permit.getMagPermitKey());   
                   }
                  else
                  {
                    pDAO.uploadFile(oneFile,ownUpload); 
                    DfbsOwner owner = cart.getOwner(ownUpload);
                    owner.setFileList(oDAO.selectFileListNew(owner));
                    session.setAttribute("FILE_UPLOAD_STATUS","Y");
                    StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                    redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do?method=filter&filter=byLetter&letter="+owner.getOwnerLastName().substring(0,1));
                    response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                    return null;
                  }
                  session.setAttribute("FILE_UPLOAD_STATUS","Y");
                  setOptions(request,dfbsUtilityDAO);
                  permit.setFileList(pDAO.selectFileList(permit));  
                  permitForm.setProperties(permit);
                  }
                  else
                  {  session.setAttribute("FILE_EXTENSION","ERROR");
                     return mapping.findForward("goToUpload");
                  }
                  
            }
           DfbsOwner owner =(DfbsOwner) session.getAttribute("OWNER_SELECTED");
           StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
           redirectUrl.append(request.getContextPath()).append("/magazine/ownerListDisplay.do?method=filter&filter=byLetter&letter="+owner.getOwnerLastName().substring(0,1));
           response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;
         }
         
         else if (method.equals("approve")) 
         { DfbsPermit permit = (DfbsPermit) session.getAttribute("PERMIT_SELECTED");
           permit.setMagOnlineStatus("Approved");
            pDAO.updatePermit(permit);
           String inspEmail=pDAO.findEmail(permit.getMagCounty());
           sendEmailInsp(request,inspEmail,permit,contacts);
          request.setAttribute("DFBS_EMAIL_SENT","TRUE");
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/magazine/start.do?method=lookupNew");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
           return null;

         }
           else if (method.equals("updatePermit")) 
        {  
           String permitKey= request.getParameter("permitKey");
           DfbsPermit updatePermit = pDAO.selectPermit(permitKey,"New");
          updatePermit.setFileList(pDAO.selectFileList(updatePermit));
           session.setAttribute("PERMIT_UPDATE",updatePermit);
           permitForm.setProperties(updatePermit);
           setOptions(request,dfbsUtilityDAO);
            String ownerKey= request.getParameter("ownerKey");
            DfbsOwner selectedOwner = oDAO.selectOwner(Integer.parseInt(ownerKey));
                List otherPermits = pDAO.selectPermits(selectedOwner.getOwnerId(),"All",null);
          request.setAttribute("OTHER_PERMITS_LIST",new HsListWrapper(otherPermits));
           session.setAttribute("OWNER_SELECTED",selectedOwner);
           return mapping.findForward("updatePermit");         
        } 
        else if (method.equals("deleteFile")) 
        {
        String permitKey= request.getParameter("permitKey");
        int fileId= Integer.parseInt(request.getParameter("fileId"));
        DfbsPermit updatePermit = pDAO.selectPermit(permitKey,"New");
          pDAO.deleteFile(fileId);
        updatePermit.setFileList(pDAO.selectFileList(updatePermit));
        session.setAttribute("PERMIT_UPDATE",updatePermit);
        permitForm.setProperties(updatePermit);
        setOptions(request,dfbsUtilityDAO);
         String ownerKey= request.getParameter("ownerKey");
         DfbsOwner selectedOwner = oDAO.selectOwner(Integer.parseInt(ownerKey));
             List otherPermits = pDAO.selectPermits(selectedOwner.getOwnerId(),"All",null);
        request.setAttribute("OTHER_PERMITS_LIST",new HsListWrapper(otherPermits));
        session.setAttribute("OWNER_SELECTED",selectedOwner);
        return mapping.findForward("updatePermit");         
        }
            else if (method.equals("saveUpdatedPermit")) 
        {  
           DfbsPermit updatedPermit = permitForm.getDfbsPermit();
         /*  String portalReceiptId = (String) session.getAttribute("PORTAL_RECEIPT");
           if (portalReceiptId !=null )
           {
            pDAO.updatePermit(updatedPermit,"Approved");
            List list = null;
            list = pDAO.selectPermitsList(portalReceiptId);
             request.setAttribute("NEW_PERMIT_LIST",new HsListWrapper(list));
             String inspEmail=pDAO.findEmail(updatedPermit.getMagCounty());
             sendEmailInsp(request,inspEmail,updatedPermit,contacts);
            return mapping.findForward("updatePermit");
           }
           else
           { */
             pDAO.updatePermit(updatedPermit);
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/magazine/start.do?method=backToIdNumber&magIdNumber="+updatedPermit.getMagIdNumber());
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;

           //}
         
        } 
            else if (method.equals("editInformation")) 
        {  
           String permitKey= request.getParameter("permitKey");
           DfbsPermit updatePermit = pDAO.selectPermit(permitKey,"New");
           session.setAttribute("PERMIT_UPDATE",updatePermit);
           permitForm.setProperties(updatePermit);
           setOptions(request,dfbsUtilityDAO);
            String ownerKey= request.getParameter("ownerKey");
            DfbsOwner selectedOwner = oDAO.selectOwner(Integer.parseInt(ownerKey));
            List otherPermits = pDAO.selectPermits(selectedOwner.getOwnerId(),"All",null);
          request.setAttribute("OTHER_PERMITS_LIST",new HsListWrapper(otherPermits));
           session.setAttribute("OWNER_SELECTED",selectedOwner);
           return mapping.findForward("updateSavePermit");
         
        } 
             else if (method.equals("updateSavePermit")) 
        {
              DfbsPermit updatedPermit = permitForm.getDfbsPermit();
              pDAO.updatePermit(updatedPermit);
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/magazine/start.do?method=backToIdNumber&magIdNumber="+updatedPermit.getMagIdNumber());
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;  
        }
        else if (method.equals("updateNewPermit")) 
        {
         DfbsPermit updatedPermit = permitForm.getDfbsPermit();
         pDAO.updatePermit(updatedPermit);
          String inspEmail=pDAO.findEmail(updatedPermit.getMagCounty());
         if(updatedPermit.getMagOnlineStatus().equals("Approved"))
         {
          sendEmailInsp(request,inspEmail,updatedPermit,contacts);
         }
          List  list = oDAO.selectNewOwnersList(pDAO);
          request.setAttribute("NEW_PERMITS_LIST",new HsListWrapper(list));
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("newPermitList");
        }
        else if (method.equals("editNewPermit")) 
        {
          String permitKey= request.getParameter("permitKey");
          DfbsPermit updatePermit = pDAO.selectPermit(permitKey,"New");
          session.setAttribute("PERMIT_UPDATE",updatePermit);
          permitForm.setProperties(updatePermit);
          List  list = oDAO.selectNewOwnersList(pDAO);
          request.setAttribute("NEW_PERMITS_LIST",new HsListWrapper(list));
           setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("newPermitList");
        }
             else if (method.equals("emailInspector")) 
        {
              DfbsPermit updatedPermit = (DfbsPermit) session.getAttribute("PERMIT_UPDATE");
              String inspEmail=pDAO.findEmail(updatedPermit.getMagCounty());
             sendEmailInsp(request,inspEmail,updatedPermit,contacts);
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/magazine/start.do?method=backToIdNumber&magIdNumber="+updatedPermit.getMagIdNumber());
             response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;  
        }  
          
             else if (method.equals("processFee")) 
        {     DfbsOwner selectedOwner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
              DfbsPermit updatedPermit = (DfbsPermit) session.getAttribute("PERMIT_UPDATE");
              pDAO.insertPermitTransaction(updatedPermit, selectedOwner);
             StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             redirectUrl.append(request.getContextPath()).append("/magazine/start.do?method=backToIdNumber&magIdNumber="+updatedPermit.getMagIdNumber());
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
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {   
    List charNav = uDAO.getCharacterNavs(DfbsSQL.SELECT_FIRST_LETTER_OPTIONS);
    List counties = uDAO.getOptions(DfbsSQL.SELECT_COUNTY_OPTIONS);
    List cities = uDAO.getOptions(DfbsSQL.SELECT_CITIES_OPTIONS);
    request.setAttribute("OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("OWNER_COUNTY_OPTIONS",counties);
    request.setAttribute("OWNER_CITIES_OPTIONS",cities);
   
 }
 
 protected static boolean validate(DfbsPermit permit,DfbsPermitForm errorForm,HttpSession session) throws Exception
  {
  
   ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
    boolean noError = true;
   if(permit.isNew() == false) {
    if ((permit.getMagPhone() == null || permit.getMagPhone().trim().equals("") ||
       permit.getMagPhone().indexOf('-') > 0 || permit.getMagPhone().indexOf(')') > 0 ||
       permit.getMagPhone().indexOf('(') > 0) )
    { 
      errorForm.setMagPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagPhone("");
    }
    return noError;
    }
   if ( permit.isNew()== true)
   {
    if(permit.getMagAddress1() == null || permit.getMagAddress1().trim().equals("")  ) 
    { 
      errorForm.setMagAddress1("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagAddress1("");
    }
    if(permit.getMagCity() == null || permit.getMagCity().trim().equals("") ) 
    { 
      errorForm.setMagCity("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagCity("");
    }
    
    if(permit.getMagState() == null || permit.getMagState().trim().equals("") ) 
    { 
      errorForm.setMagState("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setMagState("");
    }
    
     
    if(permit.getMagZip() == null || permit.getMagZip().trim().equals("") ) 
    { 
      errorForm.setMagZip("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagZip("");
    }
   
    if((permit.getMagCounty() == null || permit.getMagCounty().trim().equals("") ) ) 
    {
      errorForm.setMagCounty("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagCounty("");
    }
     if( permit.getMagMagType() == null || permit.getMagExpType().trim().equals("") )
    {
      errorForm.setMagMagType("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagExpType("");
    }
     if( permit.getMagExpType() == null || permit.getMagExpType().trim().equals("") )
    {
      errorForm.setMagExpType("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagExpType("");
    }
     if( permit.getMagAtfLicense() == null || permit.getMagAtfLicense().trim().equals("") )
    {
      errorForm.setMagAtfLicense("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagAtfLicense("");
    }
   
    if(permit.getMagMinQuantity() == 0  ) 
    {
      errorForm.setMagMinQuantity(0);
      noError = false;
    } 
    else 
    {
      errorForm.setMagMinQuantity(permit.getMagMinQuantity());
    }
    
   }
   if ((permit.getMagPhone() == null || permit.getMagPhone().trim().equals("") ||
       permit.getMagPhone().indexOf('-') > 0 || permit.getMagPhone().indexOf(')') > 0 ||
       permit.getMagPhone().indexOf('(') > 0))
    { 
      errorForm.setMagPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMagPhone("");
    }
    return noError;
  }
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS);
    List states2 = uDAO.getOptions(DfbsSQL.SELECT_STATE_OPTIONS2);
    List mags =  uDAO.getOptions(DfbsSQL.SELECT_MAGAZINE_TYPE_OPTIONS);
     List counties =  uDAO.getOptions(DfbsSQL.SELECT_COUNTY_LIST_OPTIONS);
     List fds =  uDAO.getOptions(DfbsSQL.SELECT_FIRE_DEPT_OPTIONS);
    request.setAttribute("OWNER_STATE_OPTIONS",states);
    request.setAttribute("PERSON_STATE_OPTIONS",states);
    request.setAttribute("MAGAZINE_STATE_OPTIONS",states2);
     request.setAttribute("MAGAZINE_TYPE_OPTIONS",mags);
     request.setAttribute("MAGAZINE_COUNTY_OPTIONS",counties); 
    request.setAttribute("FIRE_DEPT_OPTIONS",fds); 
     
    List charNav = uDAO.getCharacterNavs(DfbsSQL.SELECT_FIRST_LETTER_OPTIONS);
    List cities = uDAO.getOptions(DfbsSQL.SELECT_CITIES_OPTIONS);
    request.setAttribute("OWNER_FIRST_LETTERS_OPTIONS",charNav);
    request.setAttribute("OWNER_CITIES_OPTIONS",cities); 
     request.setAttribute("OWNER_COUNTY_OPTIONS",counties); 
    
 } 
  public void doGetFile(HttpServletRequest request, HttpServletResponse response,DfbsPermitDAO pDAO,String fileName,String fileType,int fileId) throws Exception 
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
private void sendEmailInsp(HttpServletRequest request,String inspEmail,DfbsPermit permit,ApplicationContacts contacts) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
            String[] emailTo = {contacts.getContact1Email(),inspEmail,contacts.getContact2Email()};
           // String[] emailTo ={"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
           
          
            StringBuffer sb = new StringBuffer();
            sb.append(permit.getMagIdNumber() + " is ready for inspection").append("\n\r");
            
          
            StringBuffer sub = new StringBuffer();
            sub.append(" From explosive magazine inspection ready alert ");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/main/main.do").append("\n\r");
             
            mail.createMail("explosive_magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From explosive magazine inspection ready alert error  ","DfbsPermitAction");
            mail1.sendAndClose();           
          }
  }
  private static void sendEmailPermitReady(String inspFacId,DfbsPermitDAO pDAO,DfbsOwnerDAO oDAO,HttpSession session,DfbsOwner owner,String printFlag,String permitKey) throws Exception
  {
          try {
          DfbsPermit permit = pDAO.selectPermit(inspFacId,"New");
          String inspEmail=pDAO.findEmail(permit.getMagCounty());
          DfbsContact contact =oDAO.selectContact(permit.getMagOwnerId());
          String contactEmail = null;
          contact.getPersonEmail();
          ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
            HsMailer mail = new HsMailer();
            String[] emailTo;
           if (contactEmail != null && contactEmail.length() >5)
           {
             String[] emailTo1 = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail,contactEmail};
             emailTo =emailTo1;
           }
           
           else
           {   
        
            String[] emailTo2 = {contacts.getContact1Email(),contacts.getContact2Email(),inspEmail};
            emailTo =emailTo2;
           } 
           
           // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("Explosive Magazine permit ready.");
            sb.append(" To print the permits use the email link given below to print them.  ");
            sb.append("\n\r");
            sb.append(" For all other concerns email "+ contacts.getContact1Email()+" .");
            sb.append("\n\r");
            sb.append(" Due to the nature of the permit, permits will be e-mailed to the ");
            sb.append(" contact address or hand delivered by our inspectors. ");
            sb.append("\n\r");
            if (printFlag.equals("All"))
            {
            sb.append(" https://oasdev.dhs.in.gov/dfbs/magazine/permit.do?method=printPermit&key1=" + owner.getOwnerId()+"&key2="+"All"+"&key3="+permitKey);
            }
            else
            {
             sb.append(" https://oasdev.dhs.in.gov/dfbs/magazine/permit.do?method=printPermit&key1=" + permit.getMagIdNumber()+"&key2="+"Permit"+"&key3="+permitKey); 
            }
            StringBuffer sub = new StringBuffer();
            sub.append(" Explosive Magazine permit approved  ");
            mail.createMail("explosive_magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail1 = new HsMailer();
               DfbsPermit permit = pDAO.selectPermit(inspFacId,"New");
            mail1.createMail("explosive_magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," permit ready error email ","dfbspermitAction"+permit.getMagIdNumber());
            mail1.sendAndClose();
            }
  }
}
