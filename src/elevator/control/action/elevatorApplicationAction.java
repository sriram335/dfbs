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

import hs.report.pdf.*;

import main.to.ApplicationContacts;
import main.to.ApplicationSecurity;
public class elevatorApplicationAction extends ControlAction{    
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
        try {
          
          ServletContext context = 
          this.getServlet().getServletConfig().getServletContext();
         
          
          DfbsDataMap dmap2 = (DfbsDataMap)  context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
          elevApplicationForm elevAppForm = (elevApplicationForm) form;
          elevatorOwnerDAO oDAO = ( elevatorOwnerDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_OWNER);
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          elevatorDAO eDAO = (elevatorDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR);
          elevApplicationDAO aDAO = (elevApplicationDAO) dmapNew.getHsDAO(DfbsDataMap2.ELEVATOR_APPLICATION);  
          String method = request.getParameter("method");
          
          HttpSession session = request.getSession();
          HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");
            DfbsOwner owner = (DfbsOwner) session.getAttribute("OWNER_SELECTED");
          elevator elev =(elevator) session.getAttribute("ELEVATOR_SELECTED");  
          ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
         if (method.equals("elevApplications"))
            { List elevAppList= aDAO.selectElevatorApplications(elev.getStateNumber());
               session.setAttribute("ELEV_UPDATE_TESTS",new HsListWrapper(elevAppList));
               return mapping.findForward("applicationList");     
             }
          
          else if (method.equals("applySaveAlteration")) 
                   {  
                        elevatorApplication elevApp =elevAppForm.getElevatorApplication();
                       elevApplicationForm errorForm = new elevApplicationForm();
                         if (validate(elevApp,errorForm) ) 
                       {
                       request.setAttribute("ELEV_APP_AGREE","Agreed");
                       elevApp.setStateNumber(elev.getStateNumber());
                       elevApp.setApplicationType("ALTERATION");
                       DfbsOwner existingOwner= cart.getOwner(Integer.toString(owner.getOwnerId()));
                       if (existingOwner.getOwnerId() !=0) { 
                         cart.setAppRunningCount(cart.getAppRunningCount()+1);
                         existingOwner.addElevatorApp(elevApp,elev,cart);
                         existingOwner.addElevator(elev);
                         elev.addElevatorApp(elevApp,cart);
                       }
                        else
                       { 
                          cart.setAppRunningCount(cart.getAppRunningCount()+1);
                         owner.addElevatorApp(elevApp,elev,cart);
                          owner.addElevator(elev);
                         elev.addElevatorApp(elevApp,cart);
                         cart.addOwner(owner);
                       }
                       oDAO.calculateElevAppFee(elev,elevApp,cart);
                       return mapping.findForward("search"); 
                       }
                       else {
                         request.setAttribute("ELEVATOR_APP_ERROR",errorForm); ;
                               return mapping.findForward("preAlteration"); 
                       }
                    
                    
                   }
          else if (method.equals("applySaveInstallation")) 
                   {   
                        elevatorApplication elevApp =elevAppForm.getElevatorApplication();
                       elevApplicationForm errorForm = new elevApplicationForm();
                         if (validate(elevApp,errorForm) ) 
                       {
                      elevApp.setStateNumber(elev.getStateNumber());
                     elevApp.setApplicationType("INSTALL");
                     DfbsOwner existingOwner= cart.getOwner(Integer.toString(owner.getOwnerId()));
                     if (existingOwner.getOwnerId() !=0) { 
                      cart.setAppRunningCount(cart.getAppRunningCount()+1);
                       existingOwner.addElevatorApp(elevApp,elev,cart);
                       existingOwner.addElevator(elev);
                       elev.addElevatorApp(elevApp,cart);
                     }
                      else
                     {   cart.setAppRunningCount(cart.getAppRunningCount()+1);
                         owner.addElevatorApp(elevApp,elev,cart);
                         owner.addElevator(elev);
                       elev.addElevatorApp(elevApp,cart);
                        cart.addOwner(owner);
                     }
                     oDAO.calculateElevAppFee(elev,elevApp,cart);
                     return mapping.findForward("search");  
                     }
                     else {
                       request.setAttribute("ELEVATOR_APP_ERROR",errorForm); ;
                             return mapping.findForward("preInstallation"); 
                     }
                    
                   }
          else if (method.equals("updateApplication")) 
                   {  int appId= Integer.parseInt(request.getParameter("applicationId"));
                     elevatorApplication elevApp =aDAO.selectElevatorApplication(appId);
                     request.setAttribute("ELEV_APP_SELECTED",elevApp);
                     elevAppForm.setProperties(elevApp);
                     return mapping.findForward("updateApplication");                 
                   }
          else if (method.equals("updateSaveApplication")) 
                   {  
                     elevatorApplication elevApp =elevAppForm.getElevatorApplication();
                     aDAO.updateElevatorApplication(elevApp);
                     request.setAttribute("ELEV_APP_SELECTED",elevApp);
                     elevAppForm.setProperties(elevApp);
                     String inspEmail=aDAO.findInspectorEmail(elev.getLocCounty(),elev.getLocZip());
                     sendEmailInsp(elevApp,owner,inspEmail,security);
                     return mapping.findForward("updateApplication");                 
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
    
      protected static boolean validate(elevatorApplication elevApp,elevApplicationForm errorForm) throws Exception
       {
         boolean noError = true;
         
        
        if(elevApp.getOwnerBy()== null || elevApp.getOwnerBy().trim().equals("")) 
        { 
          errorForm.setOwnerBy("ERROR");
          noError = false;
        } 
        else 
        {
          errorForm.setOwnerBy("");
        }
        
        
         if(elevApp.getOwnerEmail()== null || elevApp.getOwnerEmail().trim().equals("")) 
         { 
           errorForm.setOwnerEmail("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setOwnerEmail("");
         }
         if(elevApp.getAppliedBy()== null || elevApp.getAppliedBy().trim().equals("")) 
         { 
           errorForm.setAppliedBy("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setAppliedBy("");
         }
         if(elevApp.getAppliedEmail()== null || elevApp.getAppliedEmail().trim().equals("")) 
         { 
           errorForm.setAppliedEmail("ERROR");
           noError = false;
         } 
         else 
         {
           errorForm.setAppliedEmail("");
         }
      return noError;
       }
      private static void sendEmailInsp( elevatorApplication app,
                                DfbsOwner owner, String inspEmail,ApplicationSecurity security) throws Exception
        {
                try {
                   
                  HsMailer mail = new HsMailer();
                  String[] emailTo = {inspEmail};
                  String[] bccTo = {security.getCurrentUser()+"@dhs.in.gov"};
                  StringBuffer sb = new StringBuffer();
                  sb.append(owner.getOwnerDBA()+ ": submitted following online applications.").append("\n\r");  
                  sb.append(app.getStateNumber() + ": applied for alteration/installation permit.").append("\n\r");
                  sb.append("Use this link to view information:").append("\n\r");
                  sb.append("https://oas.dhs.in.gov/dfbs/main/main.do").append("\n\r");
                  sb.append("Your dfbs user name password will work on this application").append("\n\r");  
                       StringBuffer sub = new StringBuffer();
                  sub.append(" From IDHS  Elevator online transaction alert, New online system test !!IGNORE!! ");
                  
                  mail.createMail("elevators_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
                 mail.sendAndClose();
                  } 
                  catch(Exception e) 
                  {
                    HsMailer mail = new HsMailer();
                  mail.createMail("elevators_online@dhs.in.gov","nnimmagadda@dhs.in.gov","nnimmagadda@dhs.in.gov","error email elevator check out"+app.getStateNumber());
                  mail.sendAndClose();
                  }
        }
      
    }

