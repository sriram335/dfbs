package Variance.control.action;
import Variance.control.form.*;

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
import Variance.data.*;

import Variance.to.DfbsOwner;
import Variance.to.VarCodeDetail;
import Variance.to.varDesigner;
import Variance.to.varianceApplication;

import util.logging.DHSLogLevel;
import util.logging.Log;

import main.data.*;
import main.to.*;
public class varCheckoutAction  extends ControlAction
{
 private static final String CLASS_NAME="varCheckoutAction";
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        String METHOD_NAME="executeControlCheckout"; 
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
         DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
        
        varianceApplicationForm varForm = (varianceApplicationForm) form;
        
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = ( DfbsOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_OWNER);
        ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        varianceApplicationDAO aDAO = (varianceApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_APPLICATION); 
        varDesignerDAO dDAO = ( varDesignerDAO) dmapNew.getHsDAO(DfbsDataMap2.VARIANCE_DESIGNER);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        ApplicationContacts contacts = sDAO.setContacts("VARIANCE_CONTACT1","VARIANCE_CONTACT2");
        session.setAttribute("APPLICATION_CONTACTS",contacts);
        varianceApplication varApp = (varianceApplication) session.getAttribute("VARIANCE_APPLICATION_SELECTED");
        DfbsOwner owner = (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
        varDesigner des = (varDesigner) session.getAttribute("VARIANCE_DESIGNER_SELECTED");  
        String checkAddVarApp = (String) session.getAttribute("ADD_VARAPP_SECURITY");
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "1-method.:"+method);  
    if (method == null) {
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.getAmount().:"+varApp.getAmount());
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.getCodeMap().size().:"+varApp.getCodeMap().size());  
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varForm.getVarAppHistAffPhy().:"+varForm.getVarAppHistAffPhy());  
        
        if(varApp.getAmount() ==0 &&varApp.getCodeMap().size()==0) 
        {
          throw new Exception("Variance Codes not added");
        }
        else if (varApp.getAmount() > 0 && varForm.getVarAppHistAffPhy()!=null && varForm.getVarAppHistAffPhy().equals("Y"))
       // else if (varApp.getAmount() == 0 && varForm.getVarAppHistAffPhy()!=null && varForm.getVarAppHistAffPhy().equals("Y"))//Testing
         {
             Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-checkAddVarApp.:"+checkAddVarApp);
          if (checkAddVarApp !=null && checkAddVarApp.equals("Y")) {
            varApp.setVarAppDesSign(null);
            varApp.setVarAppOwnerSign(null);  
          }
          processCheckout(context,request,response,varApp);
          return null;
         }
        else if(varApp.getAmount() == 0 && checkAddVarApp !=null && checkAddVarApp.equals("Y"))
        //  else if(varApp.getAmount() > 0)// && checkAddVarApp !=null && checkAddVarApp.equals("Y"))//Testing
          { 
            varApp.setVarAppDesSign(null);
            varApp.setVarAppOwnerSign(null);
            applicationSpecificProcessing(aDAO,oDAO,varApp,dDAO,0,session,request,contacts,owner,des);
            return mapping.findForward("start");
        }else{ 
           return mapping.findForward("affirmation");
         }
    }else if(method.equals("check")){  
        String receiptId  = request.getParameter("receiptId");
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-receiptId.:"+receiptId); 
         applicationSpecificProcessing(aDAO,oDAO,varApp,dDAO,Integer.parseInt(receiptId),session,request,contacts,owner,des);
         session.setAttribute("VARIANCE_APPLICATION_SELECTED",null);
         session.setAttribute("VARIANCE_OWNER_SELECTED",null);
         session.setAttribute("VARIANCE_DESIGNER_SELECTED",null);
    } 
       return mapping.findForward("start");
    } catch (Exception e){
       saveError(request,e);
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
       return mapping.findForward("error");
    }
  }
  
  private static void applicationSpecificProcessing(varianceApplicationDAO aDAO, DfbsOwnerDAO oDAO,
       varianceApplication varApp,varDesignerDAO dDAO, int receiptId,HttpSession session,HttpServletRequest request,
       ApplicationContacts contacts,DfbsOwner owner,varDesigner des) throws Exception 
       {
        String METHOD_NAME="applicationSpecificProcessing"; 
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-owner.getOwnerId().:"+owner.getOwnerId());
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.getVarId().:"+varApp.getVarId());
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.getVarProjId().:"+varApp.getVarProjId());
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
        Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.getVarProjId().:"+code.getVarCodeId());
      if (code.getVarCodeId() >0) {
        aDAO.updateVarCode(code); 
      }
      else
      {
          Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.insertVarCode.bef:");
      aDAO.insertVarCode(code,varApp); 
          Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-varApp.insertVarCode.aft:");
      }
    }
    if(varApp.getAmount() >0)
    {
    aDAO.insertPermitTransaction(varApp, owner,receiptId);
    }
      if (varApp.getVarAppEmail() !=null)
      {
      sendEmailCheckOut(varApp,contacts,session,varApp.getVarAppEmail(),owner);
      }
      else
      {
      sendEmailCheckOut(varApp,contacts,session,owner.getOwnerEmail(),owner);
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
  
 private static void processCheckout(ServletContext context,
  HttpServletRequest request, HttpServletResponse response,
        varianceApplication varApp) throws Exception
 {
 try {
     String METHOD_NAME="processCheckout"; 
	HttpSession session = request.getSession();
	 OrderInfo cart = new OrderInfo();
   ItemInfo[] itemsInfo = new ItemInfo[1];
         int j = -1;
     Address userAddress = new Address();
     userAddress.setAddress1(varApp.getVarAppAddress1());
     userAddress.setAddress2(varApp.getVarAppAddress2());
     userAddress.setCity(varApp.getVarAppCity());
     userAddress.setState(varApp.getVarAppState());
     userAddress.setZipCode(varApp.getVarAppZip());
      StringBuffer sku = new StringBuffer("sku:");
   ItemInfo item = new ItemInfo();
        sku.append("Variance Application");
         item.setSku(sku.toString());
         StringBuffer sb1 = new StringBuffer();
         sb1.append(" Variance Application");
         sb1.append(varApp.getVarId());
         item.setDescription(sb1.toString());
         item.setQuantity(new Integer(1));
         item.setUnitCost(new BigDecimal(varApp.getAmount()));
         itemsInfo[0] = item;
   
	cart.setItemsInfo(itemsInfo);
     Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-cart.getItemsInfo.:"+cart.getItemsInfo());
 StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
   redirectUrl.append(request.getContextPath()).append("/variance/completeCheckout.do");
   cart.setRedirectUrl(redirectUrl.toString());
   
    cart.setApplicationName(context.getInitParameter("application_name_variance"));
     Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-cart..:"+cart);

	 CheckoutService2_PortType checkoutService = (CheckoutService2_PortType)new CheckoutService2_ServiceLocator().getCheckoutService2();
	 ((Stub)checkoutService)._setProperty(Stub.USERNAME_PROPERTY, context.getInitParameter("checkout_username"));
	 ((Stub)checkoutService)._setProperty(Stub.PASSWORD_PROPERTY, context.getInitParameter("checkout_password"));
	 ((CheckoutService2SoapBindingStub)checkoutService).setTimeout(0);
   String token = checkoutService.createOrder(cart);
     Log.log("ACTION_LAYER", DHSLogLevel.INFO, CLASS_NAME, METHOD_NAME, "2-token..:"+token);
	 session.setAttribute("VARIANCE_CHECKOUT_TOKEN", token);
   varApp.setCheckoutId(token);
  URL endpointUrl = new URL((String)((Stub)checkoutService)._getProperty(Stub.ENDPOINT_ADDRESS_PROPERTY));

   StringBuffer sb = new StringBuffer();
	 if(endpointUrl.getPort() != -1) {
      sb.append(endpointUrl.getProtocol()).append("://").append(endpointUrl.getHost());
      sb.append(":").append(endpointUrl.getPort());
      sb.append("/apps/kwikekard/checkout/servlet/beginSession?token=");
      sb.append(token);
			response.sendRedirect(response.encodeRedirectURL(sb.toString()));
		  return ;
		} else {
      sb.append(endpointUrl.getProtocol()).append("://").append(endpointUrl.getHost());
      sb.append("/apps/kwikekard/checkout/servlet/beginSession?token=");
      sb.append(token);
			response.sendRedirect(response.encodeRedirectURL(sb.toString()));
		  return;
		}
 }
   finally 
      {
       try {
          
        } catch (Exception e) { }
      }
      
 }
 
  private static void sendEmailCheckOut(varianceApplication varApp,ApplicationContacts contacts,HttpSession session,
                                        String varEmail,DfbsOwner owner) throws Exception
  {
          try {
              HsMailer mail = new HsMailer();
               
                String[] emailTo  = {varEmail};
                String[] bccTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov"};
            StringBuffer sb = new StringBuffer("Online Variance Application has been received for project:"+varApp.getVarProjName());
            sb.append(" Thank you for using Commission's online variance application." );
            sb.append("\n You have successfully submitted your variance application.Commission staff will contact you by email once they take action on your application. ");
            sb.append("Your online application reference id is: "+varApp.getVarId()+". If you have any questions or concerns about this process ");
              sb.append(" contact "+contacts.getContact2Email());
              sb.append("\n \n To view the variance information submitted in this application click the link provided below.(USE IE Browser)");
              sb.append("\n \n https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n Your application password is: "+owner.getOwnerId());  
              sb.append("\n \n  If link above does not work use the pdf version below.");
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
          try {
            HsMailer mail = new HsMailer();
               DfbsOwner owner= (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
            String[] bccTo = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov",contacts.getContact1Email(),"nnimmagadda@dhs.in.gov"};
            String[] emailTo  = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov",owner.getOwnerEmail()};
            StringBuffer sb = new StringBuffer("\r Online Variance Application affirmation needed for project: "+varApp.getVarProjName()+".");
            sb.append(" The Commission received a variance application from "+varApp.getVarAppFirstName()+" " +varApp.getVarAppLastName()+" with email address of "+varApp.getVarAppEmail()+".");
            sb.append("\n To complete the application process as the owners you need to click on the following link and submit their affirmation as an electronic signature.");
            sb.append("\n \n https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirmOwner&ownerKey="+owner.getOwnerEmail()+"&varKey="+varApp.getVarId()).append("\n\r");
            sb.append("\n \n Your affirmation password is: "+owner.getOwnerId());  
            sb.append("\n \n Your online application reference id is: "+varApp.getVarId()+". If you have any questions or concerns about this process");
            sb.append(" contact "+contacts.getContact2Email());
            sb.append("\n You can check status of your application(s) at https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
            StringBuffer sub = new StringBuffer();
            sub.append("Online Variance Application("+varApp.getVarId()+") affirmation needed for project: "+varApp.getVarProjName());
          
              mail.createMail("variance_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
              mail.sendAndClose();
             
            } 
            catch(Exception e) 
            {
            HsMailer mail1 = new HsMailer();
             DfbsOwner owner= (DfbsOwner) session.getAttribute("VARIANCE_OWNER_SELECTED"); 
            mail1.createMail("variance_online@dhs.in.gov","dfitzpatrick@dhs.in.gov"," Variance Online Transaction error  ",owner.getOwnerEmail() +":owner:dhs:"+contacts.getContact1Email());
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
             String[] emailTo  = {"nnimmagadda@dhs.in.gov","dfitzpatrick@dhs.in.gov","khyten@dhs.in.gov",des.getDesEmail()};
            StringBuffer sb = new StringBuffer("\r Online Variance Application affirmation needed for project: "+varApp.getVarProjName()+".");
            sb.append(" The Commission received a variance application from "+varApp.getVarAppFirstName()+" " +varApp.getVarAppLastName()+" with email address of "+varApp.getVarAppEmail()+".");
            sb.append("\n To complete the application process as the designers you need to click on the following link and submit the affirmation as an electronic signature. ");
              sb.append("\n \n https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirmDesigner&desKey="+des.getDesEmail()+"&varKey="+varApp.getVarId()).append("\n\r");
              sb.append("\n \n Your affirmation password is: "+owner.getOwnerId()+".");   
              sb.append("\n \n Your online application reference id is: "+varApp.getVarId()+". If you have any questions or concerns about this process");
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
            mail1.createMail("variance_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Variance Online Transaction error  ",des.getDesEmail() +":owner:dhs:"+contacts.getContact1Email());
            mail1.sendAndClose();    
           }
  }
  private static void sendEmailCheckOutLboLfo(varianceApplication varApp,ApplicationContacts contacts,String lboLfo,String emailContact,DfbsOwner owner) throws Exception
  {
          try {
              HsMailer mail = new HsMailer();
             
             
              String[] emailTo  = {emailContact};
              String[] bccTo  = {contacts.getContact1Email()};
              StringBuffer sb = new StringBuffer("Online Variance Application has been received for project:"+varApp.getVarProjName()+".");
              sb.append("\n This is inform you that Variance Commission has received a variance application. ");
              sb.append("The online application reference id is: "+varApp.getVarId()+". This variance for the project is filed under the name of the county for your jurisdiction. If you have any questions or concerns about this process");
              sb.append(" contact "+contacts.getContact2Email());
              sb.append("\n \n To view the variance information submitted in this application click the link provided below.(USE IE Browser)");
              sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n  If link above does not work use the pdf version below.");
              sb.append("\n  https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=variance_view_application.rdf&p_var_id="+varApp.getVarId());
              sb.append("\n \n To Search,view or Print all variance information click the link provided below.(USE IE Browser)");
              sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=viewApplications");
              sb.append("\n \n Please Click the link below to confirm, that you have received this variance notification.");
              sb.append("\n  https://oas.dhs.in.gov/dfbs/variance/start.do?method=affirm"+lboLfo+"&varKey="+varApp.getVarId()+"&pkey="+owner.getOwnerId()).append("\n\r");
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
  




