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


public class CigaretteBrandAction  extends ControlAction
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
        
        
        CigaretteBrandForm brandForm = (CigaretteBrandForm) form;
       
        HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY);
        CigaretteApplicationDAO aDAO = (CigaretteApplicationDAO) dmap2.getHsDAO(DfbsDataMap.CIG_APPLICATION);
        CigaretteCompanyDAO cDAO = (CigaretteCompanyDAO) dmap2.getHsDAO(DfbsDataMap.CIG_COMPANY);
        CigaretteBrandDAO bDAO = (CigaretteBrandDAO) dmap2.getHsDAO(DfbsDataMap.CIG_BRAND);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
        HsUser user = (HsUser) session.getAttribute(HsConstant.USER_KEY);
              CigaretteApplication application = (CigaretteApplication) session.getAttribute("CIGARETTE_APPLICATION");
              CigaretteCompany company = (CigaretteCompany) session.getAttribute("CIGARETTE_COMPANY");
         
        if (method == null ) 
        {
          
          return mapping.findForward("brand");
        } 
        
         else if(method.equals("saveBrand")) 
        { 
           CigaretteBrand brand = brandForm.getCigaretteBrandForm();
           CigaretteBrandForm errorForm = new CigaretteBrandForm();
           if (validate(brand,errorForm,session) ) {
              session.setAttribute("CIGARETTE_BRAND",brand);
               application.addBrand(brand);
              bDAO.computeFees(company);
              Map uniqueBrands =application.getMapUniqueBrand();
              session.setAttribute("UNIQUE_BRANDS",uniqueBrands);
              return mapping.findForward("cigaretteApp");
           } 
           else 
           {  setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
             request.setAttribute("CIGARETTE_BRAND_ERROR",errorForm);
             return mapping.findForward("addBrand");
           }
        }  
        else if (method.equals("addBrand")) 
        {
        session.setAttribute("CIGARETTE_BRAND",null);
         setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
          return mapping.findForward("addBrand");
        } 
      
         else if (method.equals("selectBrand")) 
        { String cigaretteBrand=request.getParameter("cigaretteBrand");
       setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
       CigaretteBrand brand =new CigaretteBrand();
          session.setAttribute("CIGARETTE_BRAND",brand);
         if (cigaretteBrand.equals("New Brand"))
         {
           brand.setCigaretteBrand("");
          brandForm.setCigaretteBrand("");
         }
         else
         {
          brand.setCigaretteBrand(cigaretteBrand);
          brandForm.setCigaretteBrand(cigaretteBrand);
         }
          return mapping.findForward("addBrand");
        } 
        else if (method.equals("editBrand")) 
        {   setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
          String key = request.getParameter("key");
           CigaretteBrand brand = application.getBrand(key);
          brandForm.setProperties(brand);
          application.removeBrand(key);
          return mapping.findForward("editBrand");
        } 
          else if (method.equals("addBrand3YearRenewal")) 
        {  setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
            List brandStyleList=(List) session.getAttribute("CIGARETTE_BRAND_RENEWAL_LIST");
             Iterator i = brandStyleList.iterator();
            bDAO.deleteBrandsRenewal();
            while(i.hasNext())
            {   CigaretteBrand brand = (CigaretteBrand) i.next();
               bDAO.insertBrandRenewal(brand);
            }
          return mapping.findForward("addBrandRenewal");
        } 
          else if (method.equals("saveBrand3YearRenewal")) 
        {  CigaretteBrand brand = brandForm.getCigaretteBrandForm();
           CigaretteBrandForm errorForm = new CigaretteBrandForm();
            if (validate(brand,errorForm,session) ) {
               bDAO.insertBrandRenewal(brand);
               List brandStyleList = bDAO.SelectBrandStyleList3YearRenewal();
               session.setAttribute("CIGARETTE_BRAND_RENEWAL_LIST", brandStyleList);
                   return mapping.findForward("3yearRenewal");
           } 
           else 
           {  setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
             request.setAttribute("CIGARETTE_BRAND_ERROR",errorForm);
             return mapping.findForward("addBrandRenewal");
           }
        }  
         else if (method.equals("editBrand3YearRenewal")) 
        {   setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
            List brandStyleList=(List) session.getAttribute("CIGARETTE_BRAND_RENEWAL_LIST");
            String brandId = request.getParameter("brandId");
            Iterator i = brandStyleList.iterator();
            bDAO.deleteBrandsRenewal();
            while(i.hasNext())
            {   CigaretteBrand brand = (CigaretteBrand) i.next();
              if (brand.getCigaretteId()!= Integer.parseInt(brandId))
              {
               bDAO.insertBrandRenewal(brand);
              }
            }
            CigaretteBrand brand = bDAO.selectBrand(brandId);
           brandForm.setProperties(brand);
          return mapping.findForward("update3Year");
        }  
           else if (method.equals("saveUpdatedBrand3Year")) 
        {  setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
          CigaretteBrand brand = brandForm.getCigaretteBrandForm();
           CigaretteBrandForm errorForm = new CigaretteBrandForm();
           if (validate(brand,errorForm,session) ) {
               bDAO.insertBrandRenewal(brand);
               List brandStyleList = bDAO.SelectBrandStyleList3YearRenewal();
               session.setAttribute("CIGARETTE_BRAND_RENEWAL_LIST", brandStyleList);
                   return mapping.findForward("3yearRenewal");
           } 
           else 
           {    setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
             request.setAttribute("CIGARETTE_BRAND_ERROR",errorForm);
             return mapping.findForward("update3Year");
           }
        } 
        else if (method.equals("updateBrand")) 
        { 
           setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
          String cigId = request.getParameter("cigaretteId");
           CigaretteBrand brand = bDAO.selectBrand(cigId);
          brandForm.setProperties(brand);
          return mapping.findForward("updateBrand");
        } 
          else if (method.equals("saveUpdatedBrand")) 
        {  setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
          CigaretteBrand brand = brandForm.getCigaretteBrandForm();
           CigaretteBrandForm errorForm = new CigaretteBrandForm();
           if (validate(brand,errorForm,session) ) {
               bDAO.updateBrand(brand);
               StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=viewDetails&appId="+ brand.getApplicationId());
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
             return null;
           } 
           else 
           {    setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
             request.setAttribute("CIGARETTE_BRAND_ERROR",errorForm);
             return mapping.findForward("updateBrand");
           }
        } 
        else if (method.equals("removeBrand")) 
        { 
          String key = request.getParameter("key");
           CigaretteBrand brand = application.getBrand(key);
          application.removeBrand(key);
           bDAO.computeFees(company);
          return mapping.findForward("cigaretteApp");
        }     
        
         else if (method.equals("addSameBrand")) 
        {
          String brandName = request.getParameter("brandName");
           setOptions(request,dfbsUtilityDAO,bDAO,Integer.toString(company.getCompanyId()));
          CigaretteBrand brand =new CigaretteBrand();
         brand.setCigaretteBrand( brandName);
         session.setAttribute("CIGARETTE_BRAND",brand);
         brandForm.setCigaretteBrand( brandName);
          return mapping.findForward("addBrand");
        }     
        else if (method.equals("addNewBrand")) 
        {
          int companyId = Integer.parseInt(request.getParameter("companyId"));
          CigaretteCompany cigCompany = cDAO.selectCompany(companyId);
          session.setAttribute("CIGARETTE_COMPANY",cigCompany);
          return mapping.findForward("addNewBrand");
        }
         else if (method.equals("saveNewBrand")) 
        {
          String brandName = request.getParameter("cigaretteBrand");
          bDAO.insertNewBrand(brandName,Integer.toString(company.getCompanyId()));
          session.setAttribute("CIGARETTE_COMPANY",company);
          StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
          redirectUrl.append(request.getContextPath()).append("/cigarette/applicationsView.do?method=addSupApp");
          response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
          return null;
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
 


  
  protected static boolean validate(CigaretteBrand brand,CigaretteBrandForm errorForm,HttpSession session) throws Exception
  {
    boolean noError = true;
   
   
    if(brand.getCigaretteBrand() == null || brand.getCigaretteBrand().trim().equals("")   ) 
    { 
      errorForm.setCigaretteBrand("ERROR");
      noError = false;
    } 
    else 
    
    {  
      errorForm.setCigaretteBrand("");
      
    }
    if(brand.getCigaretteStyle() == null || brand.getCigaretteStyle().trim().equals("")  ) 
    { 
      errorForm.setCigaretteStyle("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCigaretteStyle("");
    }
     if(brand.getCigaretteFilter() == null || brand.getCigaretteFilter().trim().equals("")  ) 
    { 
      errorForm.setCigaretteFilter("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCigaretteFilter("");
    }
    if(brand.getCigaretteFlavor() == null || brand.getCigaretteFlavor().trim().equals("")  ) 
    { 
      errorForm.setCigaretteFlavor("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCigaretteFlavor("");
    }
     if(brand.getCigaretteMarking() == null || brand.getCigaretteMarking().trim().equals("")  ) 
    { 
      errorForm.setCigaretteMarking("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCigaretteMarking("");
    }
    
     if(brand.getCigarettePackage() == null || brand.getCigarettePackage().trim().equals("")  ) 
    { 
      errorForm.setCigarettePackage("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setCigarettePackage("");
    }
     
      if(brand.getCigaretteLength() == 0   ) 
    { 
      errorForm.setCigaretteLength(1);
      noError = false;
    } 
    else 
    {
      errorForm.setCigaretteLength(0);
    }
       if(brand.getCigaretteCircumference() == 0   ) 
    { 
      errorForm.setCigaretteCircumference(1);
      noError = false;
    } 
    else 
    {
      errorForm.setCigaretteCircumference(0);
    }
  /*  if(brand.getTestLabName() == null || brand.getTestLabName().trim().equals("")  ) 
    { 
      errorForm.setTestLabName("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setTestLabName("");
    }
     if(brand.getTestDateString() == null || brand.getTestDateString().trim().equals("")  ) 
    { 
      errorForm.setTestDate("ERROR");
      noError = false;
    } 
    else 
    {
      errorForm.setTestDate("");
    } */
    return noError;
  }
   
 
  
  protected static void setOptions(HttpServletRequest request,HsUtilityDAO uDAO,CigaretteBrandDAO bDAO,String companyId) throws Exception 
 {
    
    List states = uDAO.getOptions(CigarettePermitSQL.SELECT_STATE_OPTIONS);
    request.setAttribute("BRAND_STATE_OPTIONS",states);
    List flavors = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_FLAVOR_OPTIONS);
    request.setAttribute("CIG_FLAVOR_OPTIONS",flavors);
    List filters = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_FILTER_OPTIONS);
    request.setAttribute("CIG_FILTER_OPTIONS",filters);
    List packages = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_PACKAGE_OPTIONS);
    request.setAttribute("CIG_PACKAGE_OPTIONS",packages);
     List companies = uDAO.getOptions(CigarettePermitSQL.SELECT_CIG_COMPANY_OPTIONS);
    request.setAttribute("CIG_COMPANY_OPTIONS",companies);
    if (companyId !=null  && companyId != "")
    {
     List brands = bDAO.selectBrands(companyId);
     request.setAttribute("CIG_BRANDS_OPTIONS",brands);
    }
   
 }
 
 
}
