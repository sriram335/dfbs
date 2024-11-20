package Variance.control.action;

import Variance.control.form.DfbsOwnerForm;
import Variance.control.form.varianceApplicationForm;

import Variance.data.DfbsOwnerDAO;
import Variance.data.varDesignerDAO;
import Variance.data.varianceApplicationDAO;

import Variance.to.DfbsOwner;
import Variance.to.VarCodeDetail;
import Variance.to.varDesigner;
import Variance.to.varianceApplication;

import ai.kwikekard.checkout.service2.client.*;
import java.math.*;
import java.net.URL;
import javax.xml.rpc.*;
import Variance.to.*;


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

import util.logging.DHSLogLevel;
import util.logging.Log;

public class varCompleteCheckoutAction extends ControlAction
{
    private static final String CLASS_NAME="varCompleteCheckoutAction";
    
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      String METHOD_NAME="executeControl";  
      try {
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "1.:"+context);  
         DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2.:"+dmapNew);  
        varianceApplicationForm varForm = (varianceApplicationForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
		 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "3.:"+dfbsUtilityDAO); 
        DfbsOwnerDAO oDAO = ( DfbsOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_OWNER);
		 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "4.:"+oDAO);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
		 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "5.:"+sDAO);
       varianceApplicationDAO aDAO = (varianceApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_APPLICATION); 
	    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "6.:"+aDAO);
        varDesignerDAO dDAO = ( varDesignerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_DESIGNER);
		 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "7.:"+dDAO);
        String method = request.getParameter("method");
       Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "8.:"+method);
        HttpSession session = request.getSession();
       
        ApplicationContacts contacts = sDAO.setContacts("VARIANCE_CONTACT1","VARIANCE_CONTACT2");
		   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "9.:"+contacts);
        session.setAttribute("APPLICATION_CONTACTS",contacts); 
        varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
		  Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "10.:"+varApp);
        DfbsOwner owner = (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED");  
		 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "11.:"+owner);
        varDesigner des = (varDesigner) session.getAttribute("VARIANCE_DESIGNER_SELECTED"); 
 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "12.:"+des);		
        if(method !=null && method.equals("check"))
       {  
   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "13.:"+request);
         applicationSpecificProcessing(aDAO,oDAO,varApp,dDAO,100,session,request,contacts,owner,des);
		 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "14.:"+request);
         session.setAttribute("VARIANCE_APPLICATION_SELECTED",null);
         session.setAttribute("VARIANCE_OWNER_SELECTED",null);
         session.setAttribute("VARIANCE_DESIGNER_SELECTED",null);
         return null;
       } 
   /*    if(method !=null && method.equals("testEmail"))
       {
         sendEmailCheckOutLboLfo(varApp,contacts,"LFO",varApp.getVarAppLfoEmail(),owner);  
        return null;
       } */
        if(varApp == null) 
        {
			 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "15.:"+varApp);
          throw new Exception("NO_APPLICATION_IN_SESSION");
        } 
         if (varApp != null && varApp.getAmount() > 0 )
        { 
	 Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "16.:"+varApp.getAmount());
        completeCheckout(context,request,response,varApp,owner,des,contacts,session,aDAO,oDAO,dDAO);
        return null;
        }
        else
        {
          return null;
          }
     } 
    catch (Exception e) 
    { HttpSession session = request.getSession();
      String locError= (String) session.getAttribute("SESSION_ERROR_AT");
       saveError(request,"test"+e+":"+locError);
      request.setAttribute("DFBS_APPLICATION_ERROR",":test:"+locError+":"+e.toString());
       return mapping.findForward("error");
     }
        
  }
  
 private static void completeCheckout(ServletContext context,
    HttpServletRequest request,HttpServletResponse response,
        varianceApplication varApp,DfbsOwner owner,varDesigner des,ApplicationContacts contacts,HttpSession session,
     varianceApplicationDAO aDAO, DfbsOwnerDAO oDAO,varDesignerDAO dDAO) throws Exception
 {
 String METHOD_NAME="completeCheckout";	  
   String checkoutToken = (String)session.getAttribute("VARIANCE_CHECKOUT_TOKEN");
    Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "17.:"+checkoutToken);
   if(checkoutToken == null || ! checkoutToken.equals(varApp.getCheckoutId())) 
   {
     throw new Exception("ERROR_OWNER_CHECKOUT_TOKEN");
   }
	  
   
	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((Stub)checkoutService)._setProperty(Stub.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "18.:"+checkoutService);
	  OrderInfo orderInfo = checkoutService.getOrderInfo(checkoutToken);
	 Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "19.:"+orderInfo);
	 
   StringBuffer homeUrl = new StringBuffer(context.getInitParameter("app_server"));
  //  StringBuffer homeUrl = new StringBuffer("https://oasdev.dhs.in.gov");
   Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "20.:"+homeUrl);  
    
    
	  if(!orderInfo.isAuthorized()) {
    
      throw new ServletException(checkoutToken + " has not been authorized.");
	  
    } else if(!orderInfo.isCaptured()) {
	    session.setAttribute("SESSION_ERROR_AT","5");
		   Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "21.:");  
	     int receiptId = checkoutService.completeOrder(checkoutToken, generateReceipt(request,response));
		  Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "23:receiptId.:"+receiptId); 
	   applicationSpecificProcessing(aDAO,oDAO,varApp,dDAO,receiptId,session,request,contacts,owner,des);
	    Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "24:receiptId.:");  
      session.setAttribute("VARIANCE_APPLICATION_SELECTED",null);
      session.setAttribute("VARIANCE_OWNER_SELECTED",null);
	    session.setAttribute("VARIANCE_DESIGNER_SELECTED",null);
	  }

	  URL url = new URL((String)((Stub)checkoutService)._getProperty(Stub.ENDPOINT_ADDRESS_PROPERTY));
    StringBuffer sb = new StringBuffer();
    if(url.getPort() != -1) {
      sb.append(url.getProtocol()).append("://").append(url.getHost());
      sb.append(":").append(url.getPort());
      sb.append("/apps/kwikekard/checkout/servlet/receipt");
      response.sendRedirect(sb.toString());
		  return ;
		} else {
        sb.append(url.getProtocol()).append("://").append(url.getHost());
        sb.append("/apps/kwikekard/checkout/servlet/receipt");
        response.sendRedirect(sb.toString());
		  return ;
		}
      
	}

  private static void applicationSpecificProcessing(varianceApplicationDAO aDAO, DfbsOwnerDAO oDAO,
       varianceApplication varApp,varDesignerDAO dDAO, int receiptId,HttpSession session,HttpServletRequest request,
       ApplicationContacts contacts,DfbsOwner owner,varDesigner des) throws Exception 
       {
      if (owner.getOwnerId() >0 ) {
        oDAO.updateOwner(owner); 
      }
      else
      {
      oDAO.insertOwner(owner); 
      }
      if (des ==null)
      {
      varApp.setVarAppDesSign("Y");
      }
    varApp.setVarProjOwnerId(owner.getOwnerId());
    if(varApp.getVarId()>0) {
        Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, "checkOut:", "varApp::"+varApp); 
      aDAO.updateApp(varApp);  
    }
    else
    {
    aDAO.insertApp(varApp,owner); 
    }
    if (des !=null)
    {
    des.setDesProjId(varApp.getVarProjId());
    if (des.getDesId()>0) {
      dDAO.updateDes(des);
    }
    else
    {
    dDAO.insertDes(des);
    } 
    }
    Map mapCode = varApp.getCodeMap();
    Set codekeys = mapCode.keySet();
    Iterator i = codekeys.iterator();
    while(i.hasNext())
    {
     String key = (String) i.next();
     VarCodeDetail code =  (VarCodeDetail) mapCode.get(key); 
      code.setVarId(varApp.getVarId());
      if (code.getVarCodeId() >0) {
          Log.log("checkOut::", DHSLogLevel.INFO, CLASS_NAME, "checkOut:updateVarCode-", "code::"+code); 
        aDAO.updateVarCode(code); 
      }
      else
      {
      aDAO.insertVarCode(code,varApp); 
      }
    }
    if(varApp.getAmount() >0)
    {
    aDAO.insertPermitTransaction(varApp, owner,receiptId);
    }
      if (varApp.getVarAppEmail() !=null)
      {
      sendEmailCheckOut(varApp,contacts,session,varApp.getVarAppEmail(),owner,receiptId);
      }
      else
      {
      sendEmailCheckOut(varApp,contacts,session,owner.getOwnerEmail(),owner,receiptId);
      }
      if (varApp.getVarAppLboEmail()!=null  && varApp.getVarAppLboEmail().length()>5)
      {
        sendEmailCheckOutLboLfo(varApp,contacts,"LBO",varApp.getVarAppLboEmail(),owner); 
      }
      if (varApp.getVarAppLfoEmail()!=null  && varApp.getVarAppLfoEmail().length()>5)
      {
        sendEmailCheckOutLboLfo(varApp,contacts,"LFO",varApp.getVarAppLfoEmail(),owner); 
      }
    sendEmailOwnerAffirmation  (varApp,contacts,session); 
      if (des !=null)
      {
       sendEmailDesAffirmation  (varApp,contacts,session);
      }
    }  
    
  
  
  private static String generateReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String METHOD_NAME="generateReceipt";
    BufferedHttpServletResponse bufferedResponse = new BufferedHttpServletResponse(response);
      request.getRequestDispatcher("/variance/receipt.jsp").include(request,bufferedResponse);
	     Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "22.:"+bufferedResponse.getBuffer());  
	   return new String(bufferedResponse.getBuffer());
	}
  
  private static void sendEmailCheckOut(varianceApplication varApp,ApplicationContacts contacts,HttpSession session,
                                        String varEmail,DfbsOwner owner, int receiptId) throws Exception
  {
          try {
              HsMailer mail = new HsMailer();
                String[] emailTo  = {varEmail};
               String[] bccTo = {"dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov","nnimmagadda@dhs.in.gov"};
             // String[] bccTo = null;
              StringBuffer sb = new StringBuffer("Online Variance Application has been received for project: "+varApp.getVarProjName());
              sb.append("\n \nThank you for using the Commission's online variance application. " );
              sb.append("You have successfully submitted your variance application. Commission staff will contact you by email once they take action on your application. ");
              sb.append("If you have any questions or concerns about this process, ");
              sb.append(" contact "+contacts.getContact2Email()+".");
              sb.append(" To view the variance information submitted in this application click the link provided below.(USE IE Browser)");
              sb.append("\n \n Your online application reference ID is: "+varApp.getVarId()+".");
              sb.append("\n \n Your application password is: "+owner.getOwnerId()); 
              sb.append("\n \n Your application payment confirmation ID is: "+receiptId); 
              sb.append("\n \n View application link in html"); 
              sb.append("\n https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n  If link above does not work, use the pdf version below.");
              sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n To Search,view or Print all variance information click the link provided below.(USE IE Browser)");
               sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
              
            StringBuffer sub = new StringBuffer();
            sub.append(" Online Variance Application("+varApp.getVarId()+") has been received  ");
          
              mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
             
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
             
            mail1.createMail("variance_online@dhs.in.gov","dfitzpatrick@dhs.in.gov"," Variance Online Transaction error  ",":owner:contact:dhs:"+contacts.getContact1Email());
            mail1.sendAndClose();    
           }
  }
  private static void sendEmailOwnerAffirmation(varianceApplication varApp,ApplicationContacts contacts,HttpSession session) throws Exception
  {
      HsMailer mail = new HsMailer();
      DfbsOwner owner= (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
      String[] emailTo  = {owner.getOwnerEmail(),"dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov","nnimmagadda@dhs.in.gov"};
      String[] bccTo ={contacts.getContact1Email(),"nnimmagadda@dhs.in.gov"};
          try {
            StringBuffer sb = new StringBuffer("\rOnline Variance Application affirmation needed for project: "+varApp.getVarProjName()+".");
            if (varApp.getVarAppEmail()==null ||varApp.getVarAppEmail().length() <5) {
              sb.append(" The Commission received a variance application from "+owner.getOwnerFirstName()+" " +owner.getOwnerLastName()+" with an email address of "+owner.getOwnerEmail()+"."); 
            }
            else
            {
            sb.append(" The Commission received a variance application from "+varApp.getVarAppFirstName()+" " +varApp.getVarAppLastName()+" with an email address of "+varApp.getVarAppEmail()+".");
            }   
            sb.append("\n \n To complete the application process as the owners, you need to click on the following link and submit affirmation as an electronic signature.");
            sb.append("\n \n If clicking the link does not work, copy and paste the entire url/web address in a browser and run it.");  
            sb.append("\n https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirmOwner&ownerKey="+owner.getOwnerEmail()+"&varKey="+varApp.getVarId()).append("\n\r");
            sb.append("\n \n Your affirmation password is: "+owner.getOwnerId());  
            sb.append("\n \n Your online application reference id is: "+varApp.getVarId()+".");
            sb.append("\n \n If you have any questions or concerns about this process,");
            sb.append(" contact "+contacts.getContact2Email()+".");
            sb.append("\n  \n You can check status of your application(s) at https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
            StringBuffer sub = new StringBuffer();
            sub.append("Online Variance Application("+varApp.getVarId()+") affirmation needed for project: "+varApp.getVarProjName());
          
              mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
             
            } 
            catch(Exception e) 
            {
            HsMailer mail1 =  new HsMailer();
             owner = (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
            mail1.createMail("variance_online@dhs.in.gov", emailTo, bccTo," Variance Online owner affirmation error  ",owner.getOwnerEmail() +":owner:dhs:"+contacts.getContact1Email());
            mail1.sendAndClose();    
           }
  }
  private static void sendEmailDesAffirmation(varianceApplication varApp,ApplicationContacts contacts,HttpSession session) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
               varDesigner des= (varDesigner) session.getAttribute("VARIANCE_DESIGNER_SELECTED"); 
              DfbsOwner owner= (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
            String[] bccTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov",contacts.getContact1Email()};
             String[] emailTo  = {des.getDesEmail(),"dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("\r Online Variance Application affirmation needed for project: "+varApp.getVarProjName()+".");
              if (varApp.getVarAppEmail()==null ||varApp.getVarAppEmail().length() <5) {
                sb.append(" The Commission received a variance application from "+owner.getOwnerFirstName()+" " +owner.getOwnerLastName()+" with an email address of "+owner.getOwnerEmail()+"."); 
              }
              else
              {
              sb.append(" The Commission received a variance application from "+varApp.getVarAppFirstName()+" " +varApp.getVarAppLastName()+" with an email address of "+varApp.getVarAppEmail()+".");
              }   
            sb.append("\n \n To complete the application process as the designers, you need to click on the following link and submit the affirmation as an electronic signature. ");
            sb.append("\n If clicking the link does not work, copy and paste the entire url/web address in a browser and run it.");  
            sb.append("\n https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirmDesigner&desKey="+des.getDesEmail()+"&varKey="+varApp.getVarId()).append("\n\r");
            sb.append("\n Your affirmation password is: "+owner.getOwnerId()+".");   
            sb.append("\n Your online application reference id is: "+varApp.getVarId()+".");
            sb.append("\n  If you have any questions or concerns about this process,");
            sb.append(" contact "+contacts.getContact2Email()+".");
            sb.append("\n You can check status of your application(s) at https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
            StringBuffer sub = new StringBuffer();
            sub.append("Online Variance Application("+varApp.getVarId()+") affirmation needed for project: "+varApp.getVarProjName());
          
              mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
             
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
             varDesigner des= (varDesigner) session.getAttribute("VARIANCE_DESIGNER_SELECTED"); 
            mail1.createMail("variance_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Variance Online designer affirmation email  ",des.getDesEmail() +":owner:dhs:"+contacts.getContact1Email());
            mail1.sendAndClose();    
           }
  }
  private static void sendEmailCheckOutLboLfo(varianceApplication varApp,ApplicationContacts contacts,String lboLfo,String emailContact,DfbsOwner owner) throws Exception
  {
          try {
              HsMailer mail = new HsMailer();
             
             
             // String[] emailTo  = {emailContact};
             // String[] bccTo  = {contacts.getContact1Email()};
             String[] emailTo  = {"nnimmagadda@dhs.in.gov",contacts.getContact2Email(),emailContact,"dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov"};
              String[] bccTo  = null;
              StringBuffer sb = new StringBuffer("Online Variance Application has been received for the following project:");
              sb.append("\n \nProject Name:"+varApp.getVarProjName()+".");
              sb.append("\n \nApplication reference id:"+varApp.getVarId()+".");
              sb.append("\n \n !!!!!!!! IMPORTANT NOTE PLEASE READ !!!!!!!!!.");
              sb.append("\n \nPlease Click the link below to confirm that you have received this electronic variance notification. Without your acknowledgement IDHS will keep this application in pending status.");
              sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirm"+lboLfo+"&varKey="+varApp.getVarId()+"&pkey="+owner.getOwnerId()).append("\n\r");
              sb.append("\n \nThis email is to inform you that The Fire Prevention and Building Safety Commission has received a variance application. ");
              sb.append("The variance for the project is filed under the name of the county for your jurisdiction. If you have any questions or concerns about this process");
              sb.append(" contact "+contacts.getContact2Email());
              sb.append("\n \n To view the variance information submitted in this application click the link provided below.(USE IE Browser)");
              sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n If link above does not work use the pdf version below.");
              sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n To Search,view or Print all variance information click the link provided below.(USE IE Browser)");
              sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
              //   sb.append("\n \n If you are not the correct LFO for this variance, can you  a) forward this email to the correct LFO OR b) Click the link below to notify this error to IDHS.");
            //  sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=error"+lboLfo+"&varKey="+varApp.getVarId()+"&pkey="+owner.getOwnerId()).append("\n\r");
              StringBuffer sub = new StringBuffer();
              sub.append(" Online Variance Application("+varApp.getVarId()+") has been received "+lboLfo);
          
              mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
            } 
            catch(Exception e) 
            {
            
            HsMailer mail1 = new HsMailer();
             
           mail1.createMail("variance_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Variance lbo lfo email error  ",lboLfo+emailContact+ " contact DBA ");
            mail1.sendAndClose();    
           }
  }
 
  
}
