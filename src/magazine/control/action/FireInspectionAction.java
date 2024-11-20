package magazine.control.action;
import magazine.control.form.*;
import magazine.to.*;
import magazine.data.*;
import magazine.report.*;
import java.io.*;
import org.apache.struts.action.*;
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



public class FireInspectionAction extends ControlAction
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
        
        
        FireInspectionForm inspectionForm = (FireInspectionForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsOwnerDAO oDAO = (DfbsOwnerDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_OWNER);
        DfbsPermitDAO pDAO = (DfbsPermitDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_PERMIT);
       /* FireInspectionDAO iDAO = (FireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_MAG_INSPECTION);
        FireViolationDAO vDAO = (FireViolationDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_MAG_VIOLATION);
        DfbsContactDAO cDAO = (DfbsContactDAO) dmap2.getHsDAO(DfbsDataMap.MAGAZINE_CONTACT);*/
        String method = request.getParameter("method");
         HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        if (method.equals("inspectionList")) 
        {  
           String permitNumber = request.getParameter("permitNumber");
           DfbsPermit permit = pDAO.selectPermit(permitNumber,"New");
           session.setAttribute("PERMIT_SELECTED",permit);
           permit.setInspections(oDAO.selectInspections(permit.getMagIdNumber()));
           session.setAttribute("PERMIT_INSPECTIONS",permit);
           return mapping.findForward("inspectionList");
        }
        else if (method.equals("updateInspection")) 
        {int inspectionId = Integer.parseInt(request.getParameter("inspectionId"));
          FireInspection inspection=oDAO.selectInspection(inspectionId);
          inspection.setViolations(oDAO.selectViolations(inspection.getInspId()));
          inspectionForm.setProperties(inspection);
          session.setAttribute("INSPECTION_SELECTED",inspection);
           setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("updateInspection");
        }
         else if (method.equals("newInspection")) 
        { setOptions(request,dfbsUtilityDAO); 
         String permitNumber = request.getParameter("idNumber");
         int feeDue= pDAO.selectPermitFeeDue(permitNumber);
         if (feeDue >0)
         {
           session.setAttribute("FEE_STATUS","DUE");
         }
         else
         {
           session.setAttribute("FEE_STATUS","NO DUES");
         }
          inspectionForm.setInspFacId(permitNumber);
         return mapping.findForward("newInspection");
        }
        else if (method.equals("saveInspection")) 
        { 
          FireInspection inspection = inspectionForm.getFireInspection();
          FireInspectionForm errorForm = new FireInspectionForm();
          if (validate(inspection,errorForm) ) 
          {
          if (inspection.isNew())
          {setOptions(request,dfbsUtilityDAO);
           oDAO.insertInspection(inspection);
           if (inspection.getInspCompliance().trim().equals("Approved"))
           {
           oDAO.updateIssueDate(inspection.getInspFacId(),inspection.getInspDateString(),"xx");
           this.sendEmailPermitReady(inspection.getInspFacId(),pDAO,oDAO,session);
           }
           session.setAttribute("INSPECTION_SELECTED",inspection);
           return mapping.findForward("newInspection");
          }
          else
          {setOptions(request,dfbsUtilityDAO);
            oDAO.updateInspection(inspection);
           return mapping.findForward("updateInspection");
          }
          } 
          else 
          {setOptions(request,dfbsUtilityDAO); 
           request.setAttribute("INSPECTION_ERROR",errorForm);
            inspectionForm.setProperties(inspection);
            if (inspection.isNew())
            {
             return mapping.findForward("newInspection");
            }
            else
            {
              return mapping.findForward("updateInspection");
            }
           
          }
        
        } 
      
       else if (method.equals("backToInspection")) 
        { FireInspection inspection= (FireInspection)session.getAttribute("INSPECTION_SELECTED");
          inspection.setViolations(oDAO.selectViolations(inspection.getInspId()));
          inspectionForm.setProperties(inspection);
          setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("updateInspection");
        }
        
        
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 

 
 protected static boolean validate(FireInspection inspection,FireInspectionForm errorForm) throws Exception
  {
    boolean noError = true;
  
   
    if(inspection.getInspDateString() == null || inspection.getInspDateString().trim().equals("")  ) 
    { 
      errorForm.setInspDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setInspDate("");
    }
    if( inspection.getInspInspectorId()!=0 ) 
    { 
      errorForm.setInspInspectorId(inspection.getInspInspectorId());
     
    } 
    else 
    {  noError = false;
      errorForm.setInspInspectorId(0);
    }
    
    if(inspection.getInspCompliance() == null || inspection.getInspCompliance().trim().equals("") ) 
    { 
      errorForm.setInspCompliance("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setInspCompliance("");
    }
    
    
     
    return noError;
  } 
   protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List inspType = uDAO.getOptions(DfbsSQL.SELECT_INSPECTION_TYPE);
    List inspectors = uDAO.getOptions(DfbsSQL.SELECT_INSPECTORS);
    List inspOptions = uDAO.getOptions(DfbsSQL.SELECT_INSP_OPTIONS);
    List alaramOptions = uDAO.getOptions(DfbsSQL.SELECT_ALARAM_OPTIONS);
    request.setAttribute("INSPECTION_TYPE_OPTIONS",inspType);
    request.setAttribute("INSPECTORS_OPTIONS",inspectors);
    request.setAttribute("ALARAM_OPTIONS",alaramOptions);
    request.setAttribute("COMPLIED_OPTIONS",inspOptions);
     
    
    
 } 
  private static void sendEmailPermitReady(String inspFacId,DfbsPermitDAO pDAO,DfbsOwnerDAO oDAO,HttpSession session) throws Exception
  {
          try {
          DfbsPermit permit = pDAO.selectPermit(inspFacId,"New");
          String inspEmail=pDAO.findEmail(permit.getMagCounty());
          DfbsContact contact =oDAO.selectContact(permit.getMagOwnerId());
          String contactEmail = contact.getPersonEmail();
          ApplicationContacts contacts =(ApplicationContacts) session.getAttribute("APPLICATION_CONTACTS");
          String permitKey=pDAO.getPrintKey(permit.getMagIdNumber());
           pDAO.updatePermitPrintKey(permit.getMagIdNumber(),permitKey,"Permit");
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
            sb.append(" For all other concerns email "+ contacts.getContact2Email()+" .");
            sb.append("\n\r");
            sb.append(" Due to the nature of the permit, permits will be e-mailed to the ");
            sb.append(" contact address or hand delivered by our inspectors. ");
            sb.append("\n\r");
             sb.append(" https://oas.dhs.in.gov//dfbs/magazine/permit.do?method=printPermit&key1=" + permit.getMagIdNumber()+"&key2="+"Permit"+"&key3="+permitKey);
            StringBuffer sub = new StringBuffer();
            sub.append(" Explosive Magazine permit  ");
          
          
          
            mail.createMail("explosive_magazine_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              HsMailer mail1 = new HsMailer();
            mail1.createMail("explosive_magazine_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," permit ready error email ","FireInspectionAction");
            mail1.sendAndClose();
            }
  }
  
  
}
