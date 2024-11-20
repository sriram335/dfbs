package cigarette.control.action;

import cigarette.control.form.*;
import cigarette.to.*;
import cigarette.data.*;
import main.data.*;
import main.to.*;

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
import hs.report.pdf.*;


public class CigaretteViewAction extends ControlAction
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
        
        
        CigaretteCompanyForm companyForm = (CigaretteCompanyForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CigaretteApplicationDAO aDAO = (CigaretteApplicationDAO) dmap2.getHsDAO(DfbsDataMap.CIG_APPLICATION);
        CigaretteCompanyDAO cDAO = (CigaretteCompanyDAO) dmap2.getHsDAO(DfbsDataMap.CIG_COMPANY);
         CigaretteBrandDAO bDAO = (CigaretteBrandDAO) dmap2.getHsDAO(DfbsDataMap.CIG_BRAND);
        
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
        String url = request.getRemoteAddr();
     
      
        if (method == null) 
        {
          setList(request,cDAO,companyForm);
          setFilterOptions(request,dfbsUtilityDAO);
          session.setAttribute("APPLICATION_TYPE",null);
          return mapping.findForward("applicationsView");
        } 
        else if (method.equals("refresh")) 
        {
          setList(request,cDAO,companyForm);
          setFilterOptions(request,dfbsUtilityDAO);
          return mapping.findForward("applicationsView");
        } 
        else if (method.equals("filter")) 
        {
          setFilterSession(request,companyForm);
          setFilterOptions(request,dfbsUtilityDAO);
          setList(request,cDAO,companyForm);
          return mapping.findForward("applicationsView");
        } 
        else if (method.equals("addSupApp")) 
        {
          setList(request,cDAO,companyForm);
          setFilterOptions(request,dfbsUtilityDAO);
         return mapping.findForward("applicationsView");
        } 
         else if (method.equals("addSupApp")) 
        {
          setList(request,cDAO,companyForm);
          setFilterOptions(request,dfbsUtilityDAO);
         return mapping.findForward("applicationsView");
        } 
         else if (method.equals("viewDetails")) 
        { 
          int appId = Integer.parseInt(request.getParameter("appId"));
          List brandList=cDAO.selectBrandList(CigarettePermitSQL.SELECT_BRAND_BY_APP,appId);
           request.setAttribute("BRAND_LIST",new HsListWrapper(brandList));
          return mapping.findForward("viewDetails");
        }
         
          else if (method.equals("backToView")) 
        { 
         return mapping.findForward("applicationsView");
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
 
 protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List charNav = uDAO.getCharacterNavs(CigarettePermitSQL.SELECT_FIRST_LETTER_OPTIONS);
    
    
    request.setAttribute("COMPANY_FIRST_LETTERS_OPTIONS",charNav);
    
   
 }
 protected static void setFilterSession(HttpServletRequest request,CigaretteCompanyForm filterForm) throws Exception
  {
    HttpSession session = request.getSession();
    String filter = request.getParameter("filter");
    
    HsFilter filterSession = new HsFilter();
    filterSession.setType(filter);
    
     if(filter == null ) {
      filterSession.setType("byLetter");
      filterSession.setValue("A");
    } 
    else if(filter.equals("byLetter"))  
    { 
       String letter = request.getParameter("letter");
       filterSession.setValue(letter);
     }
    
     session.setAttribute("COMPANY_LIST_FILTER",filterSession);
    
  }
  
 private static void setList(HttpServletRequest request,CigaretteCompanyDAO cDAO,CigaretteCompanyForm companyForm) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
     
     HsFilter filterSession = (HsFilter) session.getAttribute("COMPANY_LIST_FILTER");
     
     
     if(filterSession == null) {
        filterSession = new HsFilter();
        filterSession.setType("byLetter");
        filterSession.setValue("R");
        session.setAttribute("COMPANY_LIST_FILTER",filterSession);
        list = cDAO.selectCompanyList(CigarettePermitSQL.SELECT_COMPANY_BY_LETTER ,filterSession.getValue());
     } 
     else if(filterSession.getType().equals("byLetter")) 
     {
        list = cDAO.selectCompanyList(CigarettePermitSQL.SELECT_COMPANY_BY_LETTER,filterSession.getValue());
     } 
    
    
    
    
     request.setAttribute("COMPANY_LIST",new HsListWrapper(list));
     
  }
 
  
 
  
  
 
 
}
