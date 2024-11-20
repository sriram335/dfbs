package ust.control.action;
import aepermits.data.DfbsEntrSQL;

import java.io.*;
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
import main.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import main.data.*;
import org.apache.struts.upload.FormFile;
import ust.to.*;
import ust.control.form.*;
import ust.data.*;
public class ustCertAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
     ActionForm form,HttpServletRequest request, HttpServletResponse response) 
     throws IOException, ServletException
     {
         try {
           
           ServletContext context =  this.getServlet().getServletConfig().getServletContext();
           DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
           DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
           ustCertificationForm certForm = (ustCertificationForm) form;
           HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
             ustDAO uDAO = ( ustDAO) dmapNew.getHsDAO(DfbsDataMap2.UST_APPLICANT);
           ustCertificationDAO certDAO = ( ustCertificationDAO) dmapNew.getHsDAO(DfbsDataMap2.UST_CERTIFICATION);
          
           String method = request.getParameter("method");
            HttpSession session = request.getSession();
           HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
           ApplicationContacts contacts = sDAO.setContacts("UST_CONTACT1","UST_CONTACT2");
           session.setAttribute("APPLICATION_CONTACTS",contacts);
            if (method == null) 
         
           {    setOptions(request,dfbsUtilityDAO);
             ustApplicant ust = (ustApplicant) session.getAttribute("UST_APPLICANT"); 
             if (ust.getUstId() >0)
             {
             ustCertification cert = certDAO.selectUstCert(ust.getUstId());
             certForm.setProperties(cert);
             session.setAttribute("UST_CERT",cert); 
             return mapping.findForward("ustCert");
             }
             else { ustCertification cert = (ustCertification) session.getAttribute("UST_CERT"); 
                 if (cert !=null) {
                   certForm.setProperties(cert);
                 }
               setOptions(request,dfbsUtilityDAO);
               return mapping.findForward("ustCertNew");
             }
           } 
           else if (method.equals("ustCert")) 
           {      setOptions(request,dfbsUtilityDAO);
                 return mapping.findForward("ustCert");
           }
           else if (method.equals("updateUstCert")) 
           { 
            int ustId = Integer.parseInt(request.getParameter("ustId"));; 
             ustCertification cert = certDAO.selectUstCert(ustId);
             ustApplicant ust = uDAO.selectust(ustId);
             certForm.setProperties(cert);
             session.setAttribute("UST_CERT",cert); 
             setOptions(request,dfbsUtilityDAO);
            List ustNew = uDAO.selectUstList(ustSQL.SELECT_UST_LIST_NEW,null);
             ust.setFileList(uDAO.selectFileList(Integer.toString(ust.getUstId())));
             request.setAttribute("UST_LIST_NEW",new HsListWrapper(ustNew));
             session.setAttribute("UST_OLD_APPLICANT",ust);
            return mapping.findForward("updateUstCert");
           }
           else if (method.equals("saveUpdateCert")) 
           { 
             ustCertification cert = certForm.getUstCertification();
             session.setAttribute("UST_CERT",cert); 
             certDAO.updateUst(cert);
             certForm.setProperties(cert);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("updateUstCert");
           }
           else if (method.equals("addToCart")) 
           { 
           int ustFee = certDAO.selectUstFee();
             ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART_UST");
            cart.setAmount(ustFee);
             session.setAttribute("SHOPPING_CART_UST",cart);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("ustCert");
           }
           else if (method.equals("saveCert")) 
           { 
           int ustFee = certDAO.selectUstFee();
             ShoppingCart cart = (ShoppingCart) session.getAttribute("SHOPPING_CART_UST");
            cart.setAmount(ustFee);
             session.setAttribute("SHOPPING_CART_UST",cart);
             ustCertification cert = certForm.getUstCertification();
             
              if (cert.getLicState().equals("0")) {
                      cert.setLicState("IN");
                    }
             session.setAttribute("UST_CERT",cert); 
             certForm.setProperties(cert);
             setOptions(request,dfbsUtilityDAO);
             return mapping.findForward("ustCertNew");
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
 
  
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
  {
       List states = uDAO.getOptions(ustSQL.SELECT_STATE_OPTIONS2);
    request.setAttribute("UST_STATE_OPTIONS",states);
    List certStatus = uDAO.getOptions(ustSQL.SELECT_CERT_STATUS_OPTIONS);
    request.setAttribute("UST_CERT_STATUS_OPTIONS",certStatus);
    
  }
}
