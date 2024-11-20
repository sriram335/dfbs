package lepc.control.action;
import hs.control.ControlAction;

import hs.data.system.HsUtilityDAO;

import hs.util.HsConstant;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lepc.control.form.*;
import lepc.data.*;
import lepc.to.*;
import childcare.to.*;

import hs.util.HsMailer;

import main.data.ApplicationSecurityDAO;
import main.data.DfbsDataMap;
import main.data.DfbsDataMap2;

import main.to.ApplicationContacts;

import main.to.ApplicationSecurity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import otherUsers.to.otherUsers;

public class representativeAction extends ControlAction
{
 public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
          
      ServletContext context =   this.getServlet().getServletConfig().getServletContext();
      
      
      DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
      DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
      lepcRosterRepForm repForm = (lepcRosterRepForm) form;
      
      HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
      lepcDAO lDAO = (lepcDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_USER);
      lepcRosterDAO rDAO = (lepcRosterDAO) dmapNew.getHsDAO(DfbsDataMap2.LEPC_ROSTER);
      String method = request.getParameter("method");
      HttpSession session = request.getSession();
      ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
      ApplicationContacts contacts = sDAO.setContacts("LEPC_CONTACT1","LEPC_CONTACT2");      
        otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
        ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY");  
        LepcYear lYear = (LepcYear) session.getAttribute("LEPC_SELECTED");
        if (security !=null) {  
        String ethicsSecurity = rDAO.selectEthicsSecurity(security.getUserId());
        if (ethicsSecurity !=null) {
          session.setAttribute("ETHICS_SECURITY",ethicsSecurity);   
        }
        }
        if (method == null ||((otherUser ==null || otherUser.getUserLoginId().length() < 3 )&&
        (security ==null || security.getUserId().length() < 3))) 
      {
        StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
        redirectUrl.append(request.getContextPath()).append("/otherUsers/otherUser.do");
        response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
        return null; 
      }
      else if (method.equals("addRepresentative")) 
      { String rosterId = request.getParameter("rosterId");
        String personType = request.getParameter("personType");
        session.setAttribute("ROSTER_PERSON_UPDATE",null);
        if (personType !=null && personType.equals(""))
        {
        repForm.setRosterId(Integer.parseInt(rosterId)); 
          session.setAttribute("ROSTER_PERSON_TYPE","");
        }
        else {
          repForm.setRosterId(Integer.parseInt(rosterId)); 
          repForm.setPersonType(personType);
          session.setAttribute("ROSTER_PERSON_TYPE",personType);  
        }
        setOptions(request,dfbsUtilityDAO);
       return mapping.findForward("addRepresentative"); 
      } 
        else if (method.equals("saveRepresentative")) 
        {
           lepcRosterRep representative= repForm.getRosterRep();
          if (representative.getPersonId()== 0) {            
             int rosterSize= rDAO.checkRosterSize(representative);
              if (rosterSize <=9)
              {    session.setAttribute("LEPC_ROSTER_SIZE_ERROR","N");
                    int dupCount = rDAO.checkDuplicatePerson(representative);
                    if (dupCount ==0)
                    {session.setAttribute("LEPC_DUPLICATE_REP_ERROR","N");
                    rDAO.insertRepresentative(representative);
                         lepcRoster roster= rDAO.selectRoster(representative.getRosterId());
                         roster.setRosterStatus("Pending");
                         sendUpdateEmail("Roster added ",lYear.getLepcCounty());
                         rDAO.updateRoster(roster);
                       }
                    else {
                      session.setAttribute("LEPC_DUPLICATE_REP_ERROR","Y");
                      return mapping.findForward("addRepresentative"); 
                    }
              }
              else {
                session.setAttribute("LEPC_ROSTER_SIZE","Y");
                return mapping.findForward("addRepresentative"); 
              }
          }
          else {
            if (representative.getStatusDate()== null && representative.getPersonStatus() !=null && (representative.getPersonStatus().equals("Resigned")
                ||representative.getPersonStatus().equals("Proposed for removal"))) {
              String currentDate =sDAO.selectCurrentDate();
              representative.setStatusDateString(currentDate);
                 }
              /*
              if (representative.getAddDateString() ==null || representative.getAddDateString().length()==0) {
                String currentDate =sDAO.selectCurrentDate();
                representative.setAddDateString(currentDate); 
              } */
            lepcRosterRep repCheck= rDAO.selectRepresentative(representative.getPersonId());
            if (repCheck.getPersonStatus() != representative.getPersonStatus() || repCheck.getPersonType() != representative.getPersonType()) {
              lepcRoster roster= rDAO.selectRoster(representative.getRosterId());
              roster.setRosterStatus("Pending");
              sendUpdateEmail("Roster updated ",lYear.getLepcCounty());
              rDAO.updateRoster(roster);  
            }
            rDAO.updateRepresentative(representative);
            
             }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster&rosterId="+representative.getRosterId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
        } 
        else if (method.equals("updateRepresentative")) 
        { 
          String personId =request.getParameter("personId");
          lepcRosterRep rep= rDAO.selectRepresentative(Integer.parseInt(personId));
          repForm.setProperties(rep);
          setOptions(request,dfbsUtilityDAO);
           session.setAttribute("ROSTER_PERSON_UPDATE",rep);
          session.setAttribute("ROSTER_PERSON_TYPE",null);
         return mapping.findForward("addRepresentative"); 
        } 
        else if (method.equals("deleteRepresentative")) 
        { 
          String personId =request.getParameter("personId");
         rDAO.deletePerson(Integer.parseInt(personId), lepcSQL.DELETE_LEPC_ROSTER_PERSONS);
          lepcRoster roster = (lepcRoster) session.getAttribute("LEPC_DOC_ROSTER");
          roster.setRosterStatus("Pending");
          sendUpdateEmail("Roster deleted ",lYear.getLepcCounty());
          rDAO.updateRoster(roster);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster&rosterId="+roster.getRosterId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
        } 
        else if (method.equals("deleteProxy")) 
        { 
          String personId =request.getParameter("personId");
         rDAO.deletePerson(Integer.parseInt(personId), lepcSQL.DELETE_LEPC_ROSTER_PERSON_PROXY);
          lepcRoster roster = (lepcRoster) session.getAttribute("LEPC_DOC_ROSTER");
          roster.setRosterStatus("Pending");
          sendUpdateEmail("Roster proxy deleted ",lYear.getLepcCounty());
          rDAO.updateRoster(roster);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster&rosterId="+roster.getRosterId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
        } 
        else if (method.equals("proxyQuestion")) 
        {    session.setAttribute("PROXY_QUESTION", null);
            String personId = request.getParameter("personId");
            repForm.setRosterId(Integer.parseInt(personId));
            return mapping.findForward("addRepresentativeProxy"); 
        }
        
         else if (method.equals("proxyQuestionFirst"))  {
            int personId =  repForm.getRosterId();
            String proxyAction = request.getParameter("proxyAction");
            if (proxyAction.equals("Yes")) {
              session.setAttribute("PROXY_QUESTION", "FIRST");
              repForm.setRosterId(personId); 
              repForm.setPersonId(0);
              setOptions(request,dfbsUtilityDAO);
              return mapping.findForward("addRepresentativeProxy");
            }
            else { 
              session.setAttribute("PROXY_QUESTION", "ERROR");
              return mapping.findForward("addRepresentativeProxy");
             // lepcRoster roster = (lepcRoster) session.getAttribute("LEPC_DOC_ROSTER");
             // StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
             // redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster&rosterId="+roster.getRosterId());
             // response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             // return null;  
            }
            
          }
        else if (method.equals("proxyQuestionSecond"))  {
             session.setAttribute("PROXY_QUESTION", "SECOND");
           int personId =  repForm.getRosterId();
            lepcRosterRep rep= rDAO.selectRepresentative(personId);
           String personDept = request.getParameter("personDept");
           rep.setPersonDept(personDept);
           rDAO.updateRepresentative(rep);
           repForm.setRosterId(personId); 
           repForm.setPersonId(0);
           setOptions(request,dfbsUtilityDAO);
        return mapping.findForward("addRepresentativeProxy"); 
         }
        else if (method.equals("addRepresentativeProxy")) 
        { String personId = request.getParameter("personId");
          String personType = request.getParameter("personType");
          repForm.setRosterId(Integer.parseInt(personId)); 
          repForm.setPersonId(0);
          repForm.setPersonType(personType);
          setOptions(request,dfbsUtilityDAO);
         return mapping.findForward("addRepresentativeProxy"); 
        } 
        
        else if (method.equals("saveRepresentativeProxy")) 
        {
           lepcRosterRep representative= repForm.getRosterRep();
          if (representative.getPersonId()== 0) {            
              rDAO.insertRepresentativeProxy(representative);
                 lepcRoster roster= rDAO.selectRoster(representative.getRosterId());
                 roster.setRosterStatus("Pending");
                 sendUpdateEmail("Roster proxy added ",lYear.getLepcCounty());
                 rDAO.updateRoster(roster);
               }
            else
          {
              if (representative.getStatusDate()== null && representative.getPersonStatus() !=null && (representative.getPersonStatus().equals("Resigned")
                ||representative.getPersonStatus().equals("Proposed for removal"))) {
              String currentDate =(String) session.getAttribute("CURRENT_DATE");
              representative.setStatusDateString(currentDate);
                 }
            lepcRosterRep repCheck= rDAO.selectRepPersonProxy(representative.getPersonId());
            if (repCheck.getPersonStatus() != representative.getPersonStatus() || repCheck.getPersonType() != representative.getPersonType()) {
              lepcRoster roster= rDAO.selectRoster(representative.getRosterId());
              roster.setRosterStatus("Pending");
              sendUpdateEmail("Roster proxy updated ",lYear.getLepcCounty());
              rDAO.updateRoster(roster);  
            }
            rDAO.updateRepresentativeProxy(representative);
            
          }
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/lepc/roster.do?method=updateRoster&rosterId="+representative.getRosterId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;  
        } 
        
        else if (method.equals("updateRepresentativeProxy")) 
        { 
          String proxyId =request.getParameter("proxyId");
          lepcRosterRep rep= rDAO.selectRepPersonProxy(Integer.parseInt(proxyId));
          repForm.setProperties(rep);
          setOptions(request,dfbsUtilityDAO);
           session.setAttribute("ROSTER_PROXY_UPDATE",rep);
         return mapping.findForward("addRepresentativeProxy"); 
        } 
      throw new Exception("LEPC Start Action"); 
      }
      
      catch (Exception e)
      {
      saveError(request,e);
      request.setAttribute("DFBS_APPLICATION_ERROR",e.toString());
      return mapping.findForward("error");
      
      }
      }
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
  {
   List planTypes = uDAO.getOptions(lepcSQL.SELECT_LEPC_PERSON_TYPE_LIST);
   request.setAttribute("LEPC_PERSON_TYPE_OPTIONS",planTypes);
    List personStatus = uDAO.getOptions(lepcSQL.SELECT_LEPC_PERSON_STATUS);
    request.setAttribute("LEPC_PERSON_STATUS_OPTIONS",personStatus);
    List personTrainStatus = uDAO.getOptions(lepcSQL.SELECT_LEPC_PERSON_TRAIN_STATUS);
    request.setAttribute("LEPC_PERSON_TRAIN_OPTIONS",personTrainStatus);
  }
  private static void sendEthicsEmail(DfbsContact contact) throws Exception
  {
         try {
           HsMailer mail = new HsMailer();
           
           String[] emailTo = {contact.getPersonEmail(),"bgavin@dhs.in.gov"};
          // String[] emailTo = {};
           String[] bccTo = {"mroe@dhs.IN.gov","KBuster@dhs.in.gov","nnimmagadda@dhs.in.gov"};
           StringBuffer sb = new StringBuffer("\r\n This is a system generated alert about a new LEPC rep , who needs Ethics training.");
             sb.append("\n \n User last name=");
           sb.append(contact.getPersonLastName()+".");
            sb.append("\n \n User first name=");
            sb.append(contact.getPersonFirstName());
             sb.append("\n \n user Email= ");
            sb.append(contact.getPersonEmail());
             sb.append("\n \n Last Ethics Training= ");
             sb.append(contact.getPersonFax());
              sb.append("\n \n If you have questions or concerns about this email, please contact Ian Ewusi at iewusi@dhs.in.gov.");
            StringBuffer sub = new StringBuffer();
           sub.append("Ethics training needed ");
            mail.createMail("mroe@dhs.IN.gov",emailTo,bccTo,sub.toString(),sb.toString());
           mail.sendAndClose();
           } 
           catch(Exception e) 
           {
              HsMailer mail1 = new HsMailer();
           mail1.createMail("ierc_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," DFBS new user request error email ","OtherUserAction");
           mail1.sendAndClose();
           }
  }
    private static void sendUpdateEmail( String docName,String county) throws Exception
    {
           try {
             HsMailer mail = new HsMailer();
             
             String[] emailTo = {"mroe@dhs.IN.gov","KBuster@dhs.in.gov"};
            // String[] emailTo = {"iewusi@dhs.in.gov"};
             String[] bccTo = {"mroe@dhs.IN.gov"};
             StringBuffer sb = new StringBuffer("\r\n This is a system generated alert about LEPC update.");
               sb.append("\n \n This is to confirm that LEPC information for "+docName + " for county "+county+" is changed.");
              StringBuffer sub = new StringBuffer();
             sub.append(" LEPC update information ");
              mail.createMail("mroe@dhs.IN.gov",emailTo,null,sub.toString(),sb.toString());
             mail.sendAndClose();
             } 
             catch(Exception e) 
             {
                HsMailer mail1 = new HsMailer();
             mail1.createMail("ierd_online@dhs.in.gov","nnimmagadda@dhs.in.gov","LEPC update error email. ","OtherUserAction");
             mail1.sendAndClose();
             }
    }
  }

