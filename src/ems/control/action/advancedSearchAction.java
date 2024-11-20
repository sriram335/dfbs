package ems.control.action;

import ems.data.EmsProviderDAO;

import ems.control.*;
import ems.control.form.*;
import ems.to.*;
import ems.data.*;
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

public class advancedSearchAction  extends ControlAction
{
  public ActionForward executeControl(ActionMapping mapping,
  ActionForm form,HttpServletRequest request, HttpServletResponse response) 
  throws IOException, ServletException
  {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
       
        
        DfbsDataMap dmap2 = (DfbsDataMap)   context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
        DfbsDataMap2 dmapNew = (DfbsDataMap2) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY_NEW);
       
        advancedSearchForm searchForm = (advancedSearchForm) form;
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        EmsCourseDAO cDAO = (EmsCourseDAO) dmapNew.getHsDAO(DfbsDataMap2.COURSE);
        EmsInstructorsDAO iDAO = (EmsInstructorsDAO) dmapNew.getHsDAO(DfbsDataMap2.INSTRUCTOR);
        EmsProviderDAO pDAO = (EmsProviderDAO) dmapNew.getHsDAO(DfbsDataMap2.PROVIDER);
        EmsInstitutionDAO IDAO = (EmsInstitutionDAO) dmapNew.getHsDAO(DfbsDataMap2.INSTITUTION);
        EmsHospitalDAO hDAO = (EmsHospitalDAO) dmapNew.getHsDAO(DfbsDataMap2.HOSPITAL);
        advancedSearchDAO sDAO = (advancedSearchDAO) dmapNew.getHsDAO(DfbsDataMap2.ADV_SEARCH);
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
         if (method.equals("courseList"))
        {   
           String county1 = request.getParameter("county1");
           String county2 = request.getParameter("county2");
           String county3 = request.getParameter("county3");
           String county4 = request.getParameter("county4");
         /*  String zip1 = request.getParameter("zip1");
           String zip2 = request.getParameter("zip2");
           String zip3 = request.getParameter("zip3");
           String zip4 = request.getParameter("zip4");
           String provLevel = request.getParameter("provLevel");*/
           String startDate = request.getParameter("startDate");
           String endDate = request.getParameter("endDate"); 
           String sqlSelectc ="";
           String sqlSelectz ="";
           if (county1.length() >3)
           {
             sqlSelectc = " in ('"+county1+"')";
             if (county2.length() >3)
             {sqlSelectc = " in ('"+county1+"','"+county2+"')";
                if (county3.length() >3)
                {sqlSelectc = " in ('"+county1+"','"+county2+"','"+county3+"')";
                   if (county4.length() >3)
                     {sqlSelectc = " in ('"+county1+"','"+county2+"','"+county3+"','"+county4+"')";
                     }
                }
             } 
           }
          /* if (zip1.length() >3)
           {
             sqlSelectz = " in (zip1)";
             if (zip2.length() >3)
             {sqlSelectz = " in (zip1,zip2)";
                if (zip3.length() >3)
                {sqlSelectz = " in (zip1,zip2,zip3)";
                   if (zip4.length() >3)
                     {sqlSelectz = " in (zip1,zip2,zip3,zip4)";
                     }
                }
             }
           }*/
      
          List list = sDAO.selectCourseSearch(sqlSelectc,sqlSelectz,startDate,endDate);
           request.setAttribute("COURSE_LIST",new HsListWrapper(list));
           return mapping.findForward("courseList");
        } 
       if (method.equals("goToSearch"))
        {  
          setOptions(request,dfbsUtilityDAO);
           return mapping.findForward("goToSearch");
        } 
        if (method.equals("instructorListLookup"))
        {   return mapping.findForward("addNewInstructor");
        } 
         if (method.equals("incident"))
        {   return mapping.findForward("incident");
        } 
         if (method.equals("addIncidentManager"))
        {   return mapping.findForward("addIncidentManager");
        } 
        if (method.equals("incidentDetail"))
        {   return mapping.findForward("incidentDetail");
        } 
        throw new Exception("HS_ERROR_METHOD_INVALID");
             
      } 
      catch (Exception e) 
      {
         saveError(request,e);
         return mapping.findForward("error");
        
      }
        
      
        
      
  }
    protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO) throws Exception 
 {
    
    
     List countyList = uDAO.getOptions(EmsSQL.SELECT_COUNTY_LIST_OPTIONS);
     request.setAttribute("SEARCH_COUNTY_LIST_OPTIONS",countyList);
    
 }  
  
}