package hazmat.control.action;
import hazmat.control.form.*;
import hazmat.to.*;
import hazmat.data.*;
import hazmat.report.*;
import java.io.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Blob;
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
import main.to.*;
import main.data.*;
import util.logging.DHSLogLevel;
import util.logging.Log;

public class HazmatPermitAction extends ControlAction
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


            HazmatPermitForm permitForm = (HazmatPermitForm) form;

            HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
            HazmatPermitDAO oDAO = (HazmatPermitDAO) dmap2.getHsDAO(DfbsDataMap.HAZMAT_ORG);
            ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
            HazmatImageDAO iDAO= (HazmatImageDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_IMAGES);
            String method = request.getParameter("method");

            HttpSession session = request.getSession();
            System.out.println("Printing line 49"+ session.toString());
            HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
            String realPath =  context.getInitParameter("realPath");
            if (method == null || method.equals(""))
            {  String conType ="HAZMAT_CONTACT";
                session.setAttribute("APPLICATION_CONTACT_TYPE",conType);
                String appLocation ="/dfbs/hazmat/hazmat.do";
                session.setAttribute("APPLICATION_LOCATION",appLocation);
                ApplicationContacts contacts = sDAO.setContacts("HAZMAT_CONTACT1","HAZMAT_CONTACT2");
                session.setAttribute("APPLICATION_CONTACTS",contacts);
                String appHeading ="IDHS Hazmat Permit(s) Online Application";
                session.setAttribute("APPLICATION_HEADING",appHeading);
                session.setAttribute("HAZMAT_ORG",new HazmatPermit());
                session.setAttribute("HAZMAT_CARRIER",null);
                session.setAttribute("HAZMAT_SHIPMENT",null);
                FileNames names= sDAO.selectAppStatus("HAZ_PERMITS_MAINT_FLAG");
                session.setAttribute("HAZ_PERMITS_APP_STATUS",names.getFileName());
                session.setAttribute("HAZ_PERMITS_APP_MESSAGE",names.getFileType());
                return mapping.findForward("hazmatApp");

            }
            if (method.equals("startOver"))
            {
                session.setAttribute("HAZMAT_ORG",new HazmatPermit());
                session.setAttribute("HAZMAT_CARRIER",null);
                session.setAttribute("HAZMAT_SHIPMENT",null);
                return mapping.findForward("startOver");
            }
            if (method.equals("hazmatApp"))
            {
                return mapping.findForward("hazmatApp");
            }


            else if (method.equals("addOrg"))
            {
                setOptions(request,dfbsUtilityDAO);
                return mapping.findForward("addOrg");
            }
            else if (method.equals("editOrg"))
            {
                HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
                setOptions(request,dfbsUtilityDAO);
                permitForm.setProperties(permit);
                return mapping.findForward("editOrg");
            }
            else if (method.equals("backToHazmatApp"))
            {
                return mapping.findForward("hazmatApp");
            }
            else if(method.equals("savePermit"))
            {  setOptions(request,dfbsUtilityDAO);
                HazmatPermit permit = permitForm.getHazmatPermit();
                HazmatPermitForm errorForm = new HazmatPermitForm();
                if (validate(permit,errorForm) ) {
                    permitForm.setProperties(permit);
                    session.setAttribute("HAZMAT_ORG",permit);

                    return mapping.findForward("hazmatApp");
                }
                else
                {  setOptions(request,dfbsUtilityDAO);
                    request.setAttribute("HAZMAT_ORG_ERROR",errorForm);
                    return mapping.findForward("addOrg");
                }
            }
            else if(method.equals("saveEditedPermit"))
            {
                HazmatPermit updatedPermit = permitForm.getHazmatPermit();
                HazmatPermitForm errorForm = new HazmatPermitForm();
                if (validate(updatedPermit,errorForm) ) {
                    HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
                    permitForm.setUpdatedProperties(updatedPermit,permit);
                    return mapping.findForward("hazmatApp");
                }
                else
                {  setOptions(request,dfbsUtilityDAO);
                    request.setAttribute("HAZMAT_ORG_ERROR",errorForm);
                    return mapping.findForward("editOrg");
                }
            }


            else if (method.equals("sendEmail"))
            {
                sendEmail(request);
                request.setAttribute("DFBS_EMAIL_SENT","TRUE");
                return mapping.findForward("sendEmailForm");
            }
            else if (method.equals("sendEmailForm"))
            {
                return mapping.findForward("sendEmailForm");
            }
            // print permits
            else if(method.equals("printPermits"))
            {
                int receiptId =  Integer.parseInt(request.getParameter("receiptId"));
                String orgEmail =request.getParameter("orgEmail");
                List list = new ArrayList();
                if(receiptId != 0) {
                    list = oDAO.selectPermitToPrint(receiptId,orgEmail);
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                HazmatPermitPdf pdf =
                        new HazmatPermitPdf(
                                list,baos,
                                iDAO.getImageById("dhs_logo.jpg"),
                                iDAO.getImageById("dhs_logo2.jpg"),
                                iDAO.getImageById("fireMarshalSignature.gif"),
                                iDAO.getImageById("insealcl.jpg"));

        /*
         HazmatPermitPdf pdf =
           new HazmatPermitPdf(
             list,baos,context.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                 context.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                 context.getRealPath("/WEB-INF/img/fireMarshalSignature.gif"),
                 context.getRealPath("/WEB-INF/img/insealcl.jpg"));
         */
                request.setAttribute("HS_PDF",pdf);
                return mapping.findForward("pdf");
            }
            else if(method.equals("printAllPermits"))
            {

//                String deploymentPath1 = context.getRealPath("/");
//                String deploymentPath2 = context.getResource("/").getPath();
//
//                Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HazmatPermitAction", "printAllPermits",
//                        " Dynamic Deployment paths - "+ deploymentPath1 + " - war path - " +deploymentPath2);

//                Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HazmatPermitAction", "printAllPermits", "1-method.:"+ iDAO.getImageById(1).getImage());
                System.out.println("Printing line 49"+ session.getServletContext().getContextPath());
//                System.out.println(iDAO.getImageById(1).getImage());
                HazmatPermit permit = (HazmatPermit) session.getAttribute("HAZMAT_ORG");
                List list = new ArrayList();
                if (permit !=null && permit.getReceiptId()>0 )
                {
                    int receiptId =  permit.getReceiptId();
                    list = oDAO.selectPermitToPrint(receiptId,permit.getOrgEmail());
                }
                else
                {
                    int receiptId =  Integer.parseInt(request.getParameter("receiptId"));
                    String orgEmail =request.getParameter("orgEmail");
                    list = oDAO.selectPermitToPrint(receiptId,orgEmail);
                    if (list.size() ==0)
                    {
                        String remoteIp = request.getRemoteAddr();
                        HsMailer mail1 = new HsMailer();
                        mail1.createMail("hazmat_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Hazardous Material Transport permit Online hacker attack? "+remoteIp,"hazmatPermitPrint");
                        mail1.sendAndClose();

                    }
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                System.out.println("context1..."+context );
                System.out.println("context2..."+context.getContextPath() );
                System.out.println("context3..."+context.getResourcePaths("/") );
                System.out.println("context4..."+context.getRealPath("/dfbs/img/dhs_logo.jpg" ));

                System.out.println("Printing line 49"+ session.getServletContext().getContextPath());
//                System.out.println(iDAO.getImageById(1).getImage());
                String path =  context.getContextPath()+"/img/dhs_logo.jpg";
                System.out.println("path..."+path );
          /*
           HazmatPermitPdf pdf =
            new HazmatPermitPdf(
             /*list,baos,context.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                context.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                  context.getRealPath("/WEB-INF/img/fireMarshalSignature.gif"),
                  context.getRealPath("/WEB-INF/img/insealcl.jpg"));
             list,baos,context.getContextPath()+"/img/dhs_logo.jpg",
               context.getContextPath()+"/img/dhs_logo2.jpg",
                 context.getContextPath()+"/img/fireMarshalSignature.gif",
                 context.getContextPath()+"/img/insealcl.jpg");
            */
                HazmatPermitPdf pdf =
                        new HazmatPermitPdf(list,baos,
                                iDAO.getImageById("dhs_logo.jpg"),
                                iDAO.getImageById("dhs_logo2.jpg"),
                                iDAO.getImageById("fireMarshalSignature.gif"),
                                iDAO.getImageById("insealcl.jpg"));
                if(pdf.signatureImg!=null) {
                    Log.log("ACTION_LAYER", DHSLogLevel.INFO, "HazmatPermitAction", "printAllPermits",
                            " SIGNATURE IMAGE IS:    - "+ pdf.signatureImg.toString() + "    This image is NOT NULL!!!");
                }
             /*   list,baos,context.getRealPath("/WEB-INF/img/dhs_logo.jpg"),
                    context.getRealPath("/WEB-INF/img/dhs_logo2.jpg"),
                    context.getRealPath("/WEB-INF/img/fireMarshalSignature.gif"),
                    context.getRealPath("/WEB-INF/img/insealcl.jpg"));*/
                request.setAttribute("HS_PDF",pdf);
                return mapping.findForward("pdf");
            }
            else if (method.equals("logOn"))
            {
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append("/dfbs/main/main.do");
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


    private void sendEmail(HttpServletRequest request) throws Exception
    {
        try {
            HsMailer mail = new HsMailer();
            String[] emailTo = {"nnimmagadda@dhs.in.gov","cclouse@dhs.in.gov"};
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
            sub.append(" From Hazardous Material Transport Permit Online  - ").append(subject);



            mail.createMail("hazmat_online@dhs.in.gov",emailTo,bccTo,sub.toString(),sb.toString());
            mail.sendAndClose();
        }
        catch(Exception e)
        {
            HsMailer mail1 = new HsMailer();
            mail1.createMail("hazmat_online@dhs.in.gov","nnimmagadda@dhs.in.gov"," Hazardous Material Transport permit Online Transaction error email ","hazmatPermitAction");
            mail1.sendAndClose();            }
    }

    protected static boolean validate(HazmatPermit permit,HazmatPermitForm errorForm) throws Exception
    {
        boolean noError = true;


        if(permit.getOrgName() == null || permit.getOrgName().trim().equals("")  )
        {
            errorForm.setOrgName("ERROR");
            noError = false;
        }
        else
        {
            errorForm.setOrgName("");
        }
        if(permit.getOrgAddress1() == null || permit.getOrgAddress1().trim().equals("") )
        {
            errorForm.setOrgAddress1("ERROR");
            noError = false;
        }
        else
        {
            errorForm.setOrgAddress1("");
        }
        if(permit.getOrgCity() == null || permit.getOrgCity().trim().equals("") )
        {
            errorForm.setOrgCity("ERROR");
            noError = false;
        }
        else
        {
            errorForm.setOrgCity("");
        }
   /* if(permit.getOrgState() == null || permit.getOrgState().trim().equals("00") ) 
    { 
      errorForm.setOrgState("ERROR");
      noError = false;
    }
    
    else 
    {
      errorForm.setOrgState("");
    } */
        if(permit.getOrgContact() == null || permit.getOrgContact().trim().equals("") )
        {
            errorForm.setOrgContact("ERROR");
            noError = false;
        }

        else
        {
            errorForm.setOrgContact("");
        }
        if(permit.getOrgPhone() == null || permit.getOrgPhone().trim().equals("") )
        {
            errorForm.setOrgPhone("ERROR");
            noError = false;
        }

        else
        {
            errorForm.setOrgPhone("");
        }
        if(permit.getOrgEmail() == null || permit.getOrgEmail().trim().equals("") )
        {
            errorForm.setOrgEmail("ERROR");
            noError = false;
        }

        else
        {
            errorForm.setOrgEmail("");
        }

        if(permit.getOrgZip() == null || permit.getOrgZip().trim().equals("") )
        {
            errorForm.setOrgZip("ERROR");
            noError = false;
        }
        else
        {
            errorForm.setOrgZip("");
        }

        if ((permit.getOrgPhone() == null || permit.getOrgPhone().trim().equals("") ||
                permit.getOrgPhone().indexOf('-') > 0 || permit.getOrgPhone().indexOf(')') > 0 ||
                permit.getOrgPhone().indexOf('(') > 0) )
        {
            errorForm.setOrgPhone("ERROR");
            noError = false;
        }
        else
        {
            errorForm.setOrgPhone("");
        }

        if ((permit.getOrgFax().indexOf('-') > 0 || permit.getOrgFax().indexOf(')') > 0 ||
                permit.getOrgFax().indexOf('(') > 0))
        {
            errorForm.setOrgFax("ERROR");
            noError = false;
        }
        else
        {
            errorForm.setOrgFax("");
        }

        return noError;
    }

    protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception
    {
        List states = uDAO.getOptions(HazmatPermitSQL.SELECT_STATE_OPTIONS);
        request.setAttribute("ORG_STATE_OPTIONS",states);



    }

}
