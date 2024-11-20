package aepermits.control.action;
import aepermits.control.*;
import aepermits.control.form.*;
import aepermits.to.*;
import aepermits.data.*;
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
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import main.data.*;
import main.to.*;

public class DfbsPermitSpecialAction extends ControlAction
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
        
        DfbsSpecialForm specialForm = (DfbsSpecialForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsEntrOwnerDAO oDAO = (DfbsEntrOwnerDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_OWNER);
        DfbsEntrPermitDAO pDAO = (DfbsEntrPermitDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_PERMIT);
        DfbsEntrSpecialDAO sDAO = (DfbsEntrSpecialDAO) dmap2.getHsDAO(DfbsDataMap.AEPERMITS_SPECIAL_PERMIT);
        
        String method = request.getParameter("method");
         HttpSession session = request.getSession();
        
        //at this point owner must be in session
        DfbsOwner owner = (DfbsOwner) session.getAttribute("DFBS_OWNER");
        DfbsOwner ownerApplication = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
         ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
        String key = request.getParameter("key");
        
          
          
         if((owner == null || ownerApplication == null)&& method==null) 
        {
         
          throw new Exception("NO_OWNER_IN_SESSION");
        
        }
       
       if (method.equals("addSpecialForm")) 
        { 
          return mapping.findForward("addSpecialForm");
        } 
        if (method.equals("addSpecialFormDirect")) 
         { 
           session.setAttribute("DFBS_OWNER_APPLICATION",owner);
            DfbsEntrPermit permit = pDAO.selectPermitUpdate(key);
           session.setAttribute("PERMIT_FOR_SPECIAL",permit);
           DfbsOwner ownerApp = (DfbsOwner) session.getAttribute("DFBS_OWNER_APPLICATION");
           ownerApp.addPermit(permit);
           return mapping.findForward("addSpecialForm");
         } 
        
        else if (method.equals("editSpecial")) 
        { 
           DfbsEntrPermit permit = ownerApplication.getPermit(key);    
        String keySpecial = request.getParameter("specialNumber");
          DfbsEntrSpecial special = permit.getSpecial(keySpecial);
          specialForm.setProperties(special);
          session.setAttribute("EDIT_SPECIAL_SELECTED",special);
          permit.removeSpecial(keySpecial);
          return mapping.findForward("editSpecial");
        } 
        else if (method.equals("saveSpecial")) 
        {   DfbsEntrPermit permit=null;
            if (key !=null)
            {
             permit = ownerApplication.getPermit(key); 
            }
            else
            {
             permit = (DfbsEntrPermit) session.getAttribute("PERMIT_FOR_SPECIAL");
            }
         DfbsEntrSpecial special = specialForm.getDfbsEntrSpecial();
          DfbsSpecialForm errorForm = new DfbsSpecialForm();
          if (validate(special,errorForm) ) { 
            special.setNoMap("No file uploaded");
            permit.addSpecial(special);
            session.setAttribute("SPECIAL_SELECTED",special);
            setOptions(request,dfbsUtilityDAO);
            StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
            redirectUrl.append(request.getContextPath()).append("/aepermits/special.do?method=uploadFile&annualNumber="+permit.getPermitNumber()+"&specialNumber="+special.getApplicationKey());
            response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
            return null;
          } 
          else 
          {
            request.setAttribute("DFBS_PERMIT_SPECIAL_ERROR",errorForm);
            return mapping.findForward("addSpecialForm");
          }
       
        } 
        
        else if (method.equals("removeSpecial")) 
        { String permitNumber = request.getParameter("permitNumber");
          DfbsEntrPermit permitremove = ownerApplication.getPermit(permitNumber);
          String keySpecial = request.getParameter("key");
          permitremove.removeSpecial(keySpecial);
          return mapping.findForward("step2");
        } 
        else if (method.equals("uploadFile")) 
        { 
              String annualNumber = request.getParameter("annualNumber");
              DfbsEntrPermit permitFile = ownerApplication.getPermit(annualNumber);
              String specialNumber = request.getParameter("specialNumber");
              DfbsEntrSpecial specialFile = permitFile.getSpecial(specialNumber);
               session.setAttribute("SPECIAL_SELECTED",specialFile);
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append(request.getContextPath()).append("/aepermits/main.do?method=goToUpload&idNumber="+specialFile.getApplicationKey()+"&idType=AESpecial");
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
        }
         else if (method.equals("viewSpecial")) 
        { 
         DfbsEntrSpecial special =sDAO.selectSpecial(request.getParameter("specialId"));
         special.setFileList(oDAO.viewFileList(request.getParameter("specialId"),"AESpecial"));
          specialForm.setProperties(special);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("VIEW_SPECIAL_SELECTED",special);
          return mapping.findForward("viewSpecial");
        }  
         else if (method.equals("updateSpecial")) 
        { 
         DfbsEntrSpecial special =sDAO.selectSpecial(request.getParameter("specialId"));
         special.setFileList(oDAO.viewFileList(request.getParameter("specialId"),"AESpecial"));
          specialForm.setProperties(special);
           setOptions(request,dfbsUtilityDAO);
           session.setAttribute("UPDATE_SPECIAL_SELECTED",special);
           session.setAttribute("SPECIAL_INSP_EMAIL","N");
          return mapping.findForward("updateSpecial");
        } 
         else if (method.equals("saveUpdateSpecial")) 
        { 
        DfbsEntrSpecial special = specialForm.getDfbsEntrSpecial();
         sDAO.updateSpecial(special);
           session.setAttribute("UPDATE_SPECIAL_SELECTED",special);
          setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("updateSpecial");
        } 
         else if (method.equals("emailInspector")) 
        { 
        DfbsEntrSpecial special =  (DfbsEntrSpecial) session.getAttribute("UPDATE_SPECIAL_SELECTED");
        DfbsEntrPermit permit =pDAO.selectPermitUpdate(special.getPermitNumber());
         String inspEmail=pDAO.findEmail(permit.getCounty(),permit.getZip());
        
        if (inspEmail.trim().length() > 5)
        {special.setOnlineStatus("APPROVED");
         sDAO.updateSpecial(special);
         sendEmailInspSpecial(request,inspEmail,special,contacts,pDAO);
        }
        specialForm.setProperties(special);
           setOptions(request,dfbsUtilityDAO);
            session.setAttribute("SPECIAL_INSP_EMAIL","Y");
        return mapping.findForward("updateSpecial");
        }
         else if (method.equals("emailPerson")) 
        { 
         DfbsEntrSpecial special =  (DfbsEntrSpecial) session.getAttribute("UPDATE_SPECIAL_SELECTED");
        String emailTo = request.getParameter("emailTo");
        if (emailTo.trim().length() > 5)
        {
         sendEmailInspSpecial(request,emailTo,special,contacts,pDAO);
         }specialForm.setProperties(special);
         setOptions(request,dfbsUtilityDAO);
         session.setAttribute("SPECIAL_EMAIL_SENT","Y");
       return mapping.findForward("updateSpecial");
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
  
   protected static boolean validate(DfbsEntrSpecial special,DfbsSpecialForm errorForm) throws Exception
  {
    boolean noError = true;
    
    
   /* if(special.getContactPerson() == null || special.getContactPerson().trim().equals("") ) 
    {
      errorForm.setContactPerson("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContactPerson("");
    }
    if(special.getPhoneNumber() == null || special.getPhoneNumber().trim().equals("") ) 
    { 
      errorForm.setPhoneNumber("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setPhoneNumber("");
    } */
   
     
    if(special.getEventName() == null || special.getEventName().trim().equals("") ) 
    {
      errorForm.setEventName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setEventName("");
    }
     if(special.getEventTime() == null || special.getEventTime().trim().equals("") ) 
    {
      errorForm.setEventTime("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setEventTime("");
    }
     if(special.getEventDate() == null || special.getEventDateString().trim().equals("") ) 
    {
      errorForm.setEventDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setEventDate(""); 
    } 
    
    
    
   
    if(special.getMaximumOccupancy() == null || special.getMaximumOccupancy().trim().equals("") ) 
    {
      errorForm.setMaximumOccupancy("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setMaximumOccupancy("");
    }
   
   
     if(special.getFeeExempt() == null || special.getFeeExempt().trim().equals("") ) 
    { 
      errorForm.setFeeExempt("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setFeeExempt("");
    }
    if(special.getContactPerson() == null || special.getContactPerson().trim().equals("") ) 
    { 
      errorForm.setContactPerson("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContactPerson("");
    }
    if(special.getContactPhone() == null || special.getContactPhone().trim().equals("")
    || special.getContactPhone().indexOf('-') > 0 || special.getContactPhone().indexOf(')') > 0 ||
       special.getContactPhone().indexOf('(') > 0)   
    { 
      errorForm.setContactPhone("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setContactPhone("");
    }
    return noError;
  }
  
 
  
/*  private static void addSpecialsMap(HttpServletRequest request,DfbsEntrPermit permit) throws Exception
  {
    List specials = permit.getSpecials();
    Iterator i = specials.iterator();
    
    while(i.hasNext())
    {DfbsEntrSpecial special = (DfbsEntrSpecial) i.next();
      StringBuffer sb = new StringBuffer("SPECIAL");
      sb.append(special.getPermitNumber());
      String param = request.getParameter(sb.toString());
      if(param != null) 
      { permit.addSpecial(special);
      }
    }
    
  }*/
  
 
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List states = uDAO.getOptions(DfbsEntrSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("DFBS_STATE_OPTIONS",states);
 
 
    List counties = uDAO.getOptions(DfbsEntrSQL.SELECT_COUNTY_OPTIONS);
    request.setAttribute("DFBS_COUNTY_OPTIONS",counties);
    
    List occupancy = uDAO.getOptions(DfbsEntrSQL.SELECT_OCCUPANCY_OPTIONS);
    request.setAttribute("DFBS_OCCUPANCY_OPTIONS",occupancy);
 }
 
  private static void sendEmailInspSpecial(HttpServletRequest request,String inspEmail,DfbsEntrSpecial special,
                          ApplicationContacts contacts,DfbsEntrPermitDAO pDAO) throws Exception
  {
          try {
             
            HsMailer mail = new HsMailer();
              String inspSupEmail =pDAO.findInspSupEmail(inspEmail);
              String[] emailTo = {inspEmail,inspSupEmail,contacts.getContact1Email(),contacts.getContact2Email()};
           // String[] emailTo = {"nnimmagadda@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
       //    String currentYear = pDAO.selectCurrentYear();
         //  int charPosition =inspEmail.indexOf("@");
         //  String userId= inspEmail.substring(0,charPosition);
         //  int inspectorId =pDAO.selectInspector(inspEmail);
           
          
            StringBuffer sb = new StringBuffer();
            sb.append(special.getPermitNumber() +" : " +special.getEventName() +": is ready for inspection").append("\n\r");
            sb.append("Use this link to add inspections:").append("\n\r");
            sb.append("https://oas.dhs.in.gov/dfbs/aepermits/start.do").append("\n\r");
            sb.append("your dfbs username and password will work with login to the new system.").append("\n\r");
            sb.append("payment made by check, may take couple of business days for posting.").append("\n\r"); 
              sb.append("payment made by check, may take couple of business days for posting.").append("\n\r"); 
            sb.append("Use this link to get details from black berry").append("\n\r");
            sb.append("https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=fire_entertainment_list_tobe_inspected.rdf&p_id="+special.getPermitNumber()).append("\n\r");
             StringBuffer sub = new StringBuffer();
            sub.append(" From fire entertainment inspection ready alert ");
           
            mail.createMail("aepermits_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
            } 
            catch(Exception e) 
            {
 HsMailer mail1 = new HsMailer();
            mail1.createMail("aepermits_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," From fire entertainment inspection ready alert error email ","HsFireworksCheckoutAction "+inspEmail);
           mail1.sendAndClose();     
           }
  }


 
  }