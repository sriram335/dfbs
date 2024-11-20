package main.control.action;
import main.data.*;
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
import main.control.form.*;
import hs.to.*;
import hs.util.*;
import hs.data.*;
import hs.data.system.*;
import hs.report.pdf.*;
import oracle.jdbc.*; 
import java.util.Date;


public class DfbsReportAction  extends ControlAction
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
        
         DfbsReportsForm reportsForm = (DfbsReportsForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        DfbsReportsDAO rDAO = (DfbsReportsDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_REPORTS);
        
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
         if (method.equals("reports"))
        { setOptions(request,dfbsUtilityDAO);
          return mapping.findForward("reports");
        } 
          if (method.equals("selectDivision"))
        { setOptions(request,dfbsUtilityDAO);
          String division = request.getParameter("division");
          session.setAttribute("REPORT_DIVISION",division);
          setReportsGroup(request,rDAO,reportsForm,division);
          return mapping.findForward("reports");
        } 
         
         if (method.equals("reportNames"))
        { 
          String division =(String) session.getAttribute("REPORT_DIVISION");
          setOptions(request,dfbsUtilityDAO);
          String repGroup = request.getParameter("reportGroup");
           setReportsGroup(request,rDAO,reportsForm,division);
           setReportNames(request,rDAO,reportsForm,repGroup,division);
           return mapping.findForward("reports");
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
 
 
 
 private static void setReportsGroup(HttpServletRequest request,DfbsReportsDAO epDAO,DfbsReportsForm reportsForm,String division) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
       list = epDAO.selectReportsGroup(DfbsReportsSQL.SELECT_REPORT_GROUP,division);
     
    
    
     request.setAttribute("REPORT_GROUP",new HsListWrapper(list));
     
  }
 
  private static void setReportNames(HttpServletRequest request,DfbsReportsDAO epDAO,DfbsReportsForm reportsForm, String repGroup,String division) throws Exception
  {
  
     HttpSession session = request.getSession();
     HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
     
     List list = null;
       list = epDAO.selectReportNames(DfbsReportsSQL.SELECT_REPORT_NAMES,repGroup,division);
      
    
     request.setAttribute("REPORT_NAMES",new HsListWrapper(list));
     
  }
 
  
 protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    List divisions = uDAO.getOptions(DfbsReportsSQL.SELECT_DFBS_DIVISION_OPTIONS);
    request.setAttribute("DFBS_DIVISION_OPTIONS",divisions);
 
 }

}