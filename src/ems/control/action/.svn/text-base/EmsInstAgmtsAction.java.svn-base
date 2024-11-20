package ems.control.action;
import main.data.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
import main.to.*;
import org.apache.struts.action.*;
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
import hs.data.*;
import hs.data.system.*;
import otherUsers.to.*;
import oracle.jdbc.*; 
import java.util.Date;


public class EmsInstAgmtsAction extends ControlAction
{
   public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW); 
       EmsInstAgmtsForm agmtsForm = (EmsInstAgmtsForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsInstAgmtsDAO aDAO = (EmsInstAgmtsDAO) dmapNew.getHsDAO(DfbsDataMap2.AGMTS);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
         if (method.equals("agmtsList"))
        {  int institutionId = Integer.parseInt(request.getParameter("institutionId"));
          setList(request,aDAO,agmtsForm,institutionId);
           return mapping.findForward("agmtsList");
        } 
       
      
        else if (method.equals("agmtsDetail")) 
        {
          int id = Integer.parseInt(request.getParameter("agreementId"));
          this.setView(request,aDAO,agmtsForm,id);
          return mapping.findForward("agmtsDetail");
        }
        
        
                 
         if(method.equals("saveAgmts")) 
         
       { 
          EmsInstAgmts agmts =  agmtsForm.getEmsInstAgmts();
          EmsInstAgmtsForm errorForm = new EmsInstAgmtsForm();
           if (validateAgmts(agmts,errorForm))
          {  if (agmts.getAgreementId()==0)
             {
             EmsInstitution institution = (EmsInstitution)session.getAttribute("INSTITUTION");
             int institutionId = institution.getInstitutionId();
             aDAO.insertAgmts(agmts,institutionId);
             setList(request,aDAO,agmtsForm,institutionId );
             return mapping.findForward("agmtsList");
             }
             else
             {
             aDAO.updateAgmts(agmts);
             int institutionId = agmts.getInstitutionId();
             setList(request,aDAO,agmtsForm,institutionId );
             return mapping.findForward("agmtsList");
             }
          }
          else
          { request.setAttribute("AGREEMENT_ERROR",errorForm);
           return mapping.findForward("agmtsDetail");
          }
        }
                    
         if(method.equals("addNewAgmts")) 
         
       {          
           return mapping.findForward("agmtsDetail");
                    
        } 
        
          
       
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
 
 

  
 private static void setList(HttpServletRequest request,EmsInstAgmtsDAO eaDAO,EmsInstAgmtsForm agmtsForm, int institutionId) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     list = eaDAO.selectAgmtsList(EmsSQL.SELECT_INST_AGMTS_LIST,institutionId);
     request.setAttribute("AGMTS_LIST",new HsListWrapper(list));
     
  }
  protected static EmsInstAgmts setView(HttpServletRequest request,
    EmsInstAgmtsDAO rDAO,
      EmsInstAgmtsForm agmtsForm, int agreementId) throws Exception
  { 
    HttpSession session = request.getSession();
    HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
    EmsInstAgmts agmts = rDAO.selectAgmts(agreementId);
    
    session.setAttribute("AGREEMENT",agmts);
    
    agmtsForm.setProperties(agmts);
     return agmts; 
   
  }
  
 private void sendEmail(HttpServletRequest request) throws Exception
  {
          try {
            HsMailer mail = new HsMailer();
              String[] emailTo = {"abiggs@dhs.in.gov","rstump@dhs.in.gov"};
            String[] bccTo = {"nnimmagadda@dhs.in.gov"};
          
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String msg = request.getParameter("msg");
          
            StringBuffer sb = new StringBuffer();
            sb.append("name: ").append(name).append("\n\r");
            sb.append("email: ").append(email).append("\n\r");
          
            sb.append(msg).append("\n\r");
          
            StringBuffer sub = new StringBuffer();
            sub.append("* From Fireworks Online * - ").append(subject);
          
          
          
            mail.createMail("codeEnforcement_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
            } 
            catch(Exception e) 
            {
              throw new Exception("ERROR_EMAIL_FAILED");
            }
  }
protected static boolean validateAgmts(EmsInstAgmts agmts ,EmsInstAgmtsForm errorForm) throws Exception
  {
    boolean noError = true;
    
    if(agmts.getName() == null   )
    {
    errorForm.setName("ERROR");
     noError = false;
      }
    else
    {  
        errorForm.setName("");
       
    }
    
 
    if(agmts.getEffectiveDate() == null  ) 
    { 
      errorForm.setEffectiveDate("ERROR");
      noError = false;
    } 
     else
    {
      errorForm.setEffectiveDate("");
    }
    
   return noError;
  }
    
 
 
 
}