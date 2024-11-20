package idhsInspections.control.action;
import main.data.*;
import main.to.*;
import idhsInspections.to.*;
import idhsInspections.data.*;
import idhsInspections.control.form.*;
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

import otherUsers.to.otherUsers;


public class InspectionSearchAction extends ControlAction{
    public ActionForward executeControl(ActionMapping mapping,
    ActionForm form,HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
      try {
        
        
        ServletContext context = 
        this.getServlet().getServletConfig().getServletContext();
          
        DfbsDataMap dmap2 = (DfbsDataMap) context.getAttribute(HsConstant.DFBS_DATA_MAP_KEY);
          HsUtilityDAO dfbsUtilityDAO = (HsUtilityDAO) dmap2.getHsDAO(DfbsDataMap.UTILITY); 
          idhsInspectionsDAO idhsDAO = (idhsInspectionsDAO) dmap2.getHsDAO(DfbsDataMap.IDHS_INSPECTION_SEARCH);
          fireInspectionDAO iDAO = (fireInspectionDAO) dmap2.getHsDAO(DfbsDataMap.FIRE_INSPECTION);
          ApplicationSecurityDAO sDAO = (ApplicationSecurityDAO) dmap2.getHsDAO(DfbsDataMap.DFBS_SECURITY);
        String method = request.getParameter("method");
        
        HttpSession session = request.getSession();
         
           ApplicationContacts contacts = sDAO.setContacts("IDHS_INSP_CONTACT1","IDHS_INSP_CONTACT2");
          session.setAttribute("APPLICATION_CONTACTS",contacts);
          
        if (method == null ) 
        {   String conType ="IDHS_INSP_CONTACT";
            session.setAttribute("APPLICATION_CONTACT_TYPE",conType); 
            String appLocation ="/dfbs/idhsInspections/start.do";
             session.setAttribute("APPLICATION_LOCATION",appLocation); 
             String appHeading ="IDHS INSPECTIONS (2010.1 version)";
         session.setAttribute("APPLICATION_HEADING",appHeading); 
            ApplicationSecurity security = (ApplicationSecurity) session.getAttribute("DFBS_SECURITY"); 
            String inspIdString=Integer.toString(iDAO.selectInspector(security.getUserId()+"@dhs.in.gov"));
            session.setAttribute("INSPECTOR_CURRENT",inspIdString); 
            setFilterOptions(request,dfbsUtilityDAO,session);
             return mapping.findForward("start");
        } 
          else if (method.equals("searchFssa")) 
        {   
            String appHeading ="IDHS INSPECTIONS (2010.1 version)";
             session.setAttribute("APPLICATION_HEADING",appHeading); 
            otherUsers otherUser = (otherUsers)  session.getAttribute("OTHER_USER");
            setFilterOptions(request,dfbsUtilityDAO,session);
             return mapping.findForward("fssaStart");
        } 
       
          else if (method.equals("search")) 
          { 
            String streetNumber = request.getParameter("streetNumber");
              String city = request.getParameter("city");
              String streetName = request.getParameter("streetName");
              String facName = request.getParameter("facilityName");
              String county = request.getParameter("county");
              String searchFor = request.getParameter("searchFor");
              String searchFacType = request.getParameter("searchFacType");
              String searchZip = request.getParameter("zip");
              String searchActive= request.getParameter("searchActive");
              session.setAttribute("SEARCH_STREET",streetNumber);
              session.setAttribute("SEARCH_ST_NAME",streetName);
              session.setAttribute("SEARCH_CITY",city);
              session.setAttribute("SEARCH_COUNTY",county);
              session.setAttribute("SEARCH_FAC_NAME",facName);
              session.setAttribute("SEARCH_FOR",searchFor);
              session.setAttribute("SEARCH_FAC_TYPE",searchFacType);
              session.setAttribute("SEARCH_ZIP",searchZip);
              session.setAttribute("SEARCH_ACTIVE",searchActive);
              setFilterOptions(request,dfbsUtilityDAO,session);
              inspectionSearch search = new inspectionSearch();
              if (streetNumber.length() >0 && streetName.length() >0) {
                  streetNumber= streetNumber+"%"+streetName;
              }
              session.setAttribute("CURRENT_SEARCH",search);
              if ((city.length() >0 && streetNumber.length() >0) ||(county.length() >0 && streetNumber.length() >0)||facName.length() >0)
              {
              if (searchFacType.equals("Elevators") ||searchFacType.equals("ALL"))
                  {
                  List elevList = idhsDAO.selectElevators(facName,streetNumber,city,county,search,searchZip,searchActive);
                  request.setAttribute("ELEVATOR_SEARCH_LIST",new HsListWrapper(elevList));
                  search.setElevList(elevList);
                  }
              
              if (searchFacType.equals("BPV") ||searchFacType.equals("ALL"))
                  {
                  List bpvList = idhsDAO.selectBpv(facName,streetNumber,city,county,search,searchZip,searchActive);
                  request.setAttribute("BPV_SEARCH_LIST",new HsListWrapper(bpvList));
                  search.setBpvList(bpvList);
                  }
              if (searchFacType.equals("AE Permits") ||searchFacType.equals("ALL"))
                  { 
                  List aeList = idhsDAO.selectAepermits(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
                  request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
                  search.setAeList(aeList);
                  }
              if (searchFacType.equals("Child Cares") ||searchFacType.equals("ALL"))
                  {
                  List dayCareList = idhsDAO.selectDaycares(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
                  request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
                  search.setDayCareList(dayCareList);
                  }
              if (searchFacType.equals("Schools") ||searchFacType.equals("ALL"))
                  { 
                  List schoolList = idhsDAO.selectSchools(facName,streetNumber,city,county,search,null,searchZip,searchActive);
                  request.setAttribute("SCHOOL_SEARCH_LIST",new HsListWrapper(schoolList));  
                  search.setSchoolList(schoolList);
                  }
              if (searchFacType.equals("Others") ||searchFacType.equals("ALL"))
                  { 
                  List othersList = idhsDAO.selectOthers(facName,streetNumber,city,county,search,null,searchZip,searchActive);
                  request.setAttribute("OTHER_SEARCH_LIST",new HsListWrapper(othersList));  
                  search.setOtherList(othersList);
                  }
              if (searchFacType.equals("Health Cares") ||searchFacType.equals("ALL"))
                  {
                  List hospitalsList = idhsDAO.selectHospitals(facName,streetNumber,city,county,search,null,searchZip,searchActive);
                  request.setAttribute("HOSPITAL_SEARCH_LIST",new HsListWrapper(hospitalsList)); 
                  search.setHospitalList(hospitalsList);
                  }
              if (searchFacType.equals("Plans") ||searchFacType.equals("ALL"))
                  { 
                  List plansList = idhsDAO.selectPlans(facName,streetNumber,city,county,search,null,searchZip,searchActive);
                  request.setAttribute("PLAN_SEARCH_LIST",new HsListWrapper(plansList));
                  search.setPlanList(plansList);
                  }
              if (searchFacType.equals("Fireworks") ||searchFacType.equals("ALL"))
                  { 
                  List fireworksRetailList = idhsDAO.selectFireworksRetail(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
                  request.setAttribute("FIREWORKS_RETAIL_SEARCH_LIST",new HsListWrapper(fireworksRetailList)); 
                  search.setFireworksRetailList(fireworksRetailList);
                  List fireworksWholesaleList = idhsDAO.selectFireworksWholesale(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
                  request.setAttribute("FIREWORKS_WHOLESALE_SEARCH_LIST",new HsListWrapper(fireworksWholesaleList)); 
                  search.setFireworksWholesaleList(fireworksWholesaleList);
                  }
              if (searchFacType.equals("USTs") ||searchFacType.equals("ALL"))
                  { 
                  List ustList = idhsDAO.selectUSTs(facName,streetNumber,city,county,search,null,searchZip,searchActive);
                  request.setAttribute("UST_SEARCH_LIST",new HsListWrapper(ustList)); 
                  search.setUstList(ustList);
                  }
                
              List newInspList = idhsDAO.selectNewInspections(facName,streetNumber,city,county,search,searchZip);
              request.setAttribute("NEW_INSP_SEARCH_LIST",new HsListWrapper(newInspList)); 
              search.setNewInspList(newInspList);
              List newInspIndMobileList = idhsDAO.selectNewInspectionsIndMobile(facName,streetNumber,city,county,search,searchZip,null);
              request.setAttribute("NEW_INSP_IND_MOBILE_SEARCH_LIST",new HsListWrapper(newInspIndMobileList)); 
              search.setIndMobileList(newInspIndMobileList);
                  session.setAttribute("SEARCH_INCORRECT","N"); 
                 // session.setAttribute("RESULTS_FLAG",""); 
              }
              else {
                  session.setAttribute("SEARCH_INCORRECT","Y"); 
              }
             
             return mapping.findForward("start");
            
          }
      // id number search start
          else if (method.equals("searchById")) 
          {   String facilityId = request.getParameter("idNumber");
              String streetNumber ="";
              String city ="";
              String county ="";
              String facName="";
              String streetName="";
             inspectionSearch search = new inspectionSearch();
              session.setAttribute("CURRENT_SEARCH",search);
              session.setAttribute("SEARCH_ID_FOUND","N");
            session.setAttribute("ACTIVITIES_SELECTED",null);
              session.setAttribute("INSPECTION_SEARCH_ID",facilityId);
              session.setAttribute("RESULTS_FLAG_ID",facilityId.substring(0,2).toUpperCase());  
              if(facilityId.substring(0,2).toUpperCase().equals("AE")) {
                  List aeList = idhsDAO.selectAepermits(null,null,null,null,search,facilityId,null,null,null); 
                  Iterator i = aeList.iterator();
                  while(i.hasNext())
                  { 
                      searchResults result = (searchResults) i.next();
                      session.setAttribute("SEARCH_ID_FOUND","Y"); 
                    int streetLength =0;
                    if (result.getResultAddress1().length() <=3) {
                      streetLength = result.getResultAddress1().length();
                    }
                    else {
                      streetLength =3;
                    }
                      streetNumber=result.getResultAddress1().substring(0,streetLength);
                      city=result.getResultCity();
                      county=iDAO.selectCountyCode(result.getResultCounty());
                     
                  }
                  session.setAttribute("RESULTS_FLAG","AEPERMITS");  
              }
                if(facilityId.substring(0,2).toUpperCase().equals("GH")||
                    facilityId.substring(0,2).toUpperCase().equals("RM")||
                    facilityId.substring(0,2).toUpperCase().equals("DC")||
                    facilityId.substring(0,2).toUpperCase().equals("MD")||
                    facilityId.substring(0,2).toUpperCase().equals("CI")||
                    facilityId.substring(0,2).toUpperCase().equals("SC")) {
                    List dayCareList = idhsDAO.selectDaycares(null,null,null,null,search,facilityId,null,null,null); 
                    Iterator i = dayCareList.iterator();
                    while(i.hasNext())
                    {searchResults result = (searchResults) i.next();
                      session.setAttribute("SEARCH_ID_FOUND","Y"); 
                      int streetLength =0;
                      if (result.getResultAddress1().length() <=3) {
                        streetLength = result.getResultAddress1().length();
                      }
                      else {
                        streetLength =3;
                      }
                      streetNumber=result.getResultAddress1().substring(0,streetLength);
                      city=result.getResultCity();
                      county=iDAO.selectCountyCode(result.getResultCounty());
                    } session.setAttribute("RESULTS_FLAG","DAYCARE"); 
                }
                if(facilityId.substring(0,2).toUpperCase().equals("SC")) {
                     List schoolList = idhsDAO.selectSchools(null,null,null,null,search,facilityId,null,null); 
                     Iterator i = schoolList.iterator();
                     while(i.hasNext())
                     {searchResults result = (searchResults) i.next();
                       session.setAttribute("SEARCH_ID_FOUND","Y"); 
                       int streetLength =0;
                       if (result.getResultAddress1().length() <=3) {
                         streetLength = result.getResultAddress1().length();
                       }
                       else {
                         streetLength =3;
                       }
                       streetNumber=result.getResultAddress1().substring(0,streetLength);
                       city=result.getResultCity();
                         county=iDAO.selectCountyCode(result.getResultCounty());
                     } session.setAttribute("RESULTS_FLAG","SCHOOL"); 
                 }
                if(facilityId.substring(0,2).toUpperCase().equals("UO")) {
                    List ustList = idhsDAO.selectUSTs(null,null,null,null,search,facilityId,null,null);
                    Iterator i = ustList.iterator();
                    while(i.hasNext())
                    {searchResults result = (searchResults) i.next();
                      session.setAttribute("SEARCH_ID_FOUND","Y"); 
                      int streetLength =0;
                      if (result.getResultAddress1().length() <=3) {
                        streetLength = result.getResultAddress1().length();
                      }
                      else {
                        streetLength =3;
                      }
                      streetNumber=result.getResultAddress1().substring(0,streetLength);
                      city=result.getResultCity();
                        county=iDAO.selectCountyCode(result.getResultCounty());
                    } session.setAttribute("RESULTS_FLAG","UST"); 
                }
                if(facilityId.substring(0,2).toUpperCase().equals("FW"))
                {
                if(facilityId.substring(0,3).toUpperCase().equals("FWR")||
                    facilityId.substring(0,3).toUpperCase().equals("FWT"))
                     {
                    List fireworksRetailList = idhsDAO.selectFireworksRetail(null,null,null,null,search,facilityId,null,null,null); 
                    Iterator i = fireworksRetailList.iterator();
                    while(i.hasNext())
                    {searchResults result = (searchResults) i.next();
                      session.setAttribute("SEARCH_ID_FOUND","Y"); 
                       int streetLength =0;
                       if (result.getResultAddress1().length() <=3) {
                         streetLength = result.getResultAddress1().length();
                       }
                       else {
                         streetLength =3;
                       }
                      streetNumber=result.getResultAddress1().substring(0,streetLength);
                      city=result.getResultCity();
                     county=iDAO.selectCountyCode(result.getResultCounty());
                     } session.setAttribute("RESULTS_FLAG","FIREWORKS_RETAIL"); }
                else
                    {
                        List fireworksWholesaleList = idhsDAO.selectFireworksWholesale(null,null,null,null,search,facilityId,null,null,null);
                         search.setFireworksWholesaleList(fireworksWholesaleList);
                        Iterator i = fireworksWholesaleList.iterator();
                        while(i.hasNext())
                        {searchResults result = (searchResults) i.next();
                          session.setAttribute("SEARCH_ID_FOUND","Y"); 
                          int streetLength =0;
                          if (result.getResultAddress1().length() <=3) {
                            streetLength = result.getResultAddress1().length();
                          }
                          else {
                            streetLength =3;
                          }
                          streetNumber=result.getResultAddress1().substring(0,streetLength);
                          city=result.getResultCity();
                            
                        }session.setAttribute("RESULTS_FLAG","FIREWORKS_WHOLESALE"); 
                    }
                }
               
                if(facilityId.substring(0,2).toUpperCase().equals("DD")||
                    facilityId.substring(0,2).toUpperCase().equals("SG")||
                    facilityId.substring(0,2).toUpperCase().equals("AM")||
                    facilityId.substring(0,2).toUpperCase().equals("SA")||
                    facilityId.substring(0,2).toUpperCase().equals("SH")||
                    facilityId.substring(0,2).toUpperCase().equals("ES")||
                    facilityId.substring(0,2).toUpperCase().equals("PS")||
                    facilityId.substring(0,2).toUpperCase().equals("LO")||
                    facilityId.substring(0,2).toUpperCase().equals("LT")||
                    facilityId.substring(0,2).toUpperCase().equals("HO")||
                    facilityId.substring(0,2).toUpperCase().equals("AS")||
                    facilityId.substring(0,2).toUpperCase().equals("RA")||
                    facilityId.substring(0,2).toUpperCase().equals("TR")||
                    facilityId.substring(0,2).toUpperCase().equals("DS")){
                    List hospitalsList = idhsDAO.selectHospitals(null,null,null,null,search,facilityId,null,null);
                    Iterator i = hospitalsList.iterator();
                    while(i.hasNext())
                    {searchResults result = (searchResults) i.next();
                      session.setAttribute("SEARCH_ID_FOUND","Y"); 
                      int streetLength =0;
                      if (result.getResultAddress1().length() <=3) {
                        streetLength = result.getResultAddress1().length();
                      }
                      else {
                        streetLength =3;
                      }
                      streetNumber=result.getResultAddress1().substring(0,streetLength);
                      city=result.getResultCity();
                        county=iDAO.selectCountyCode(result.getResultCounty());
                    }session.setAttribute("RESULTS_FLAG","HOSPITAL"); 
                }
                if(facilityId.substring(0,2).toUpperCase().equals("HM")||
                    facilityId.substring(0,2).toUpperCase().equals("JP")||
                    facilityId.substring(0,2).toUpperCase().equals("CS")||
                    facilityId.substring(0,2).toUpperCase().equals("BU")) {
                    List othersList = idhsDAO.selectOthers(null,null,null,null,search,facilityId,null,null);
                    Iterator i = othersList.iterator();
                    while(i.hasNext())
                    {searchResults result = (searchResults) i.next();
                      session.setAttribute("SEARCH_ID_FOUND","Y"); 
                      int streetLength =0;
                      if (result.getResultAddress1().length() <=3) {
                        streetLength = result.getResultAddress1().length();
                      }
                      else {
                        streetLength =3;
                      }
                      streetNumber=result.getResultAddress1().substring(0,streetLength);
                      city=result.getResultCity();
                        county=iDAO.selectCountyCode(result.getResultCounty());
                    }session.setAttribute("RESULTS_FLAG","OTHER"); 
                }
              if(facilityId.substring(0,1).toUpperCase().equals("9")||
                  facilityId.substring(0,1).toUpperCase().equals("2")||
                  facilityId.substring(0,1).toUpperCase().equals("3")||
                  facilityId.substring(0,1).toUpperCase().equals("4")) {
                  List plansList = idhsDAO.selectPlans(null,null,null,null,search,facilityId,null,null);
                  Iterator i = plansList.iterator();
                  while(i.hasNext())
                  {searchResults result = (searchResults) i.next();
                    session.setAttribute("SEARCH_ID_FOUND","Y"); 
                    int streetLength =0;
                    if (result.getResultAddress1().length() <=3) {
                      streetLength = result.getResultAddress1().length();
                    }
                    else {
                      streetLength =3;
                    }
                    streetNumber=result.getResultAddress1().substring(0,streetLength);
                    city=result.getResultCity();
                      county=iDAO.selectCountyCode(result.getResultCounty());
                  }session.setAttribute("RESULTS_FLAG","PLAN");  
              }
            if(facilityId.substring(0,1).toUpperCase().equals("P")|| (facilityId.substring(0,1).toUpperCase().equals("M")&&
                facilityId.substring(0,2).toUpperCase().compareTo("MA") !=0)) {
                  List newInspIndMobileList = idhsDAO.selectNewInspectionsIndMobile(null,null,null,null,search,null,facilityId);
                  request.setAttribute("NEW_INSP_IND_MOBILE_SEARCH_LIST",new HsListWrapper(newInspIndMobileList)); 
                  search.setIndMobileList(newInspIndMobileList);
                   session.setAttribute("SEARCH_ID_FOUND","N");  
                   session.setAttribute("RESULTS_FLAG","NEW_INSPECTIONS_IND_MOBILE");
                }
               setFilterOptions(request,dfbsUtilityDAO,session);
              String searchSuccess = (String) session.getAttribute("SEARCH_ID_FOUND"); 
              if (searchSuccess.equals("Y") )
              {   
                  List elevList = idhsDAO.selectElevators(facName,streetNumber,city,county,search,null,null);
                  request.setAttribute("ELEVATOR_SEARCH_LIST",new HsListWrapper(elevList));
                  search.setElevList(elevList);
                  List bpvList = idhsDAO.selectBpv(facName,streetNumber,city,county,search,null,null);
                  request.setAttribute("BPV_SEARCH_LIST",new HsListWrapper(bpvList));
                  search.setBpvList(bpvList);
                  List aeList = idhsDAO.selectAepermits(facName,streetNumber,city,county,search,null,null,null,null);
                  request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
                  search.setAeList(aeList);
                  List dayCareList = idhsDAO.selectDaycares(facName,streetNumber,city,county,search,null,null,null,null);
                  request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
                  search.setDayCareList(dayCareList);
                  List schoolList = idhsDAO.selectSchools(facName,streetNumber,city,county,search,null,null,null);
                  request.setAttribute("SCHOOL_SEARCH_LIST",new HsListWrapper(schoolList));  
                  search.setSchoolList(schoolList);
                  List othersList = idhsDAO.selectOthers(facName,streetNumber,city,county,search,null,null,null);
                  request.setAttribute("OTHER_SEARCH_LIST",new HsListWrapper(othersList));  
                  search.setOtherList(othersList);
                  List hospitalsList = idhsDAO.selectHospitals(facName,streetNumber,city,county,search,null,null,null);
                  request.setAttribute("HOSPITAL_SEARCH_LIST",new HsListWrapper(hospitalsList)); 
                  search.setHospitalList(hospitalsList);
                  List plansList = idhsDAO.selectPlans(facName,streetNumber,city,county,search,null,null,null);
                  request.setAttribute("PLAN_SEARCH_LIST",new HsListWrapper(plansList));
                  search.setPlanList(plansList);
                  List fireworksRetailList = idhsDAO.selectFireworksRetail(facName,streetNumber,city,county,search,null,null,null,null);
                  request.setAttribute("FIREWORKS_RETAIL_SEARCH_LIST",new HsListWrapper(fireworksRetailList)); 
                  search.setFireworksRetailList(fireworksRetailList);
                  List fireworksWholesaleList = idhsDAO.selectFireworksWholesale(facName,streetNumber,city,county,search,null,null,null,null);
                  request.setAttribute("FIREWORKS_WHOLESALE_SEARCH_LIST",new HsListWrapper(fireworksWholesaleList)); 
                  search.setFireworksWholesaleList(fireworksWholesaleList);
                  List ustList = idhsDAO.selectUSTs(facName,streetNumber,city,county,search,null,null,null);
                  request.setAttribute("UST_SEARCH_LIST",new HsListWrapper(ustList)); 
                  search.setUstList(ustList);
                  List newInspList = idhsDAO.selectNewInspections(facName,streetNumber,city,county,search,null);
                  request.setAttribute("NEW_INSP_SEARCH_LIST",new HsListWrapper(newInspList)); 
                  search.setNewInspList(newInspList);
                 List newInspIndMobileList = idhsDAO.selectNewInspectionsIndMobile(facName,streetNumber,city,county,search,null,null);
                 request.setAttribute("NEW_INSP_IND_MOBILE_SEARCH_LIST",new HsListWrapper(newInspList)); 
                 search.setIndMobileList(newInspIndMobileList);
               }
              session.setAttribute("SEARCH_STREET",streetNumber);
              session.setAttribute("SEARCH_ST_NAME",streetName);
              session.setAttribute("SEARCH_CITY",city);
              session.setAttribute("SEARCH_COUNTY",county);
              session.setAttribute("SEARCH_FAC_NAME",facName);
              StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
              redirectUrl.append("/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId="+facilityId);
              response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
              return null;
          }
      // id number search end
          
          else if (method.equals("elevators")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              List elevList = idhsDAO.selectElevators(facName,streetNumber,city,county,search,searchZip,null);
              request.setAttribute("ELEVATOR_SEARCH_LIST",new HsListWrapper(elevList));
              session.setAttribute("RESULTS_FLAG","ELEVATORS");   
              return mapping.findForward("start");
          }
          else if (method.equals("bpv")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List bpvList = idhsDAO.selectBpv(facName,streetNumber,city,county,search,searchZip,null);
              request.setAttribute("BPV_SEARCH_LIST",new HsListWrapper(bpvList));
              session.setAttribute("RESULTS_FLAG","BPV");   
              return mapping.findForward("start");
          }
          else if (method.equals("plan")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List plansList = idhsDAO.selectPlans(facName,streetNumber,city,county,search,null,searchZip,searchActive);
              request.setAttribute("PLAN_SEARCH_LIST",new HsListWrapper(plansList));
              session.setAttribute("RESULTS_FLAG","PLAN"); 
              return mapping.findForward("start");
          }
        
          else if (method.equals("aepermits")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List aeList = idhsDAO.selectAepermits(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
              request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
              session.setAttribute("RESULTS_FLAG","AEPERMITS");  
              return mapping.findForward("start");
          }
          else if (method.equals("daycare")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List dayCareList = idhsDAO.selectDaycares(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
              request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
              session.setAttribute("RESULTS_FLAG","DAYCARE"); 
              return mapping.findForward("start");
          }
        else if (method.equals("daycareFssa")) {
            System.out.println("test");
            setFilterOptions(request,dfbsUtilityDAO,session);
            inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
            String streetNumber=(String) session.getAttribute("SEARCH_STREET");
            String city=(String) session.getAttribute("SEARCH_CITY");
            String county=(String) session.getAttribute("SEARCH_COUNTY");
            String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
            String searchZip=(String) session.getAttribute("SEARCH_ZIP");
           List dayCareList = idhsDAO.selectDaycaresFssa(facName,streetNumber,city,county,search,null,null,searchZip,null);
            request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
            session.setAttribute("RESULTS_FLAG","DAYCARE"); 
            return mapping.findForward("fssaStart");
        }
          else if (method.equals("school")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List schoolList = idhsDAO.selectSchools(facName,streetNumber,city,county,search,null,searchZip,searchActive);
              request.setAttribute("SCHOOL_SEARCH_LIST",new HsListWrapper(schoolList));  
              session.setAttribute("RESULTS_FLAG","SCHOOL");   
              return mapping.findForward("start");
          }
          else if (method.equals("hospital")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List hospitalsList = idhsDAO.selectHospitals(facName,streetNumber,city,county,search,null,searchZip,searchActive);
              request.setAttribute("HOSPITAL_SEARCH_LIST",new HsListWrapper(hospitalsList)); 
              session.setAttribute("RESULTS_FLAG","HOSPITAL"); 
              return mapping.findForward("start");
          }
          else if (method.equals("other")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List othersList = idhsDAO.selectOthers(facName,streetNumber,city,county,search,null,searchZip,searchActive);
              request.setAttribute("OTHER_SEARCH_LIST",new HsListWrapper(othersList));
              session.setAttribute("RESULTS_FLAG","OTHER");   
              return mapping.findForward("start");
          }
          else if (method.equals("fireworksRetail")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List fireorksRetailList = idhsDAO.selectFireworksRetail(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
              request.setAttribute("FIREWORKS_RETAIL_SEARCH_LIST",new HsListWrapper(fireorksRetailList));
              session.setAttribute("RESULTS_FLAG","FIREWORKS_RETAIL");   
              return mapping.findForward("start");
          }
          else if (method.equals("fireworksWholesale")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List fireorksWholesaleList = idhsDAO.selectFireworksWholesale(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
              request.setAttribute("FIREWORKS_WHOLESALE_SEARCH_LIST",new HsListWrapper(fireorksWholesaleList));
              session.setAttribute("RESULTS_FLAG","FIREWORKS_WHOLESALE");   
              return mapping.findForward("start");
          }
          else if (method.equals("ust")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
              List ustList = idhsDAO.selectUSTs(facName,streetNumber,city,county,search,null,searchZip,searchActive);
              request.setAttribute("UST_SEARCH_LIST",new HsListWrapper(ustList));
              session.setAttribute("RESULTS_FLAG","UST");   
              return mapping.findForward("start");
          }
          else if (method.equals("newInspections")) {
              setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              List newInspList = idhsDAO.selectNewInspections(facName,streetNumber,city,county,search,searchZip);
              request.setAttribute("NEW_INSP_SEARCH_LIST",new HsListWrapper(newInspList));
              session.setAttribute("RESULTS_FLAG","NEW_INSPECTIONS");   
              return mapping.findForward("start");
          }
        else if (method.equals("indMobileInspections")) {
            setFilterOptions(request,dfbsUtilityDAO,session);
            String streetNumber=(String) session.getAttribute("SEARCH_STREET");
            String city=(String) session.getAttribute("SEARCH_CITY");
            String county=(String) session.getAttribute("SEARCH_COUNTY");
            inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
            String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
            String searchFor=(String) session.getAttribute("SEARCH_FOR");
            String searchZip=(String) session.getAttribute("SEARCH_ZIP");
            List newInspList = idhsDAO.selectNewInspectionsIndMobile(facName,streetNumber,city,county,search,searchZip,null);
            request.setAttribute("NEW_INSP_IND_MOBILE_SEARCH_LIST",new HsListWrapper(newInspList));
            session.setAttribute("RESULTS_FLAG","NEW_INSPECTIONS_IND_MOBILE");   
            return mapping.findForward("start");
        }
          else if (method.equals("start")) 
          {   setFilterOptions(request,dfbsUtilityDAO,session);
              String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
           return mapping.findForward("start");
            
          }
        else if (method.equals("FinalInspectionDone")) 
          {  String projectNumber = request.getParameter("projectNumber");
               iDAO.updateInactivatePlanProject(projectNumber); 
              setFilterOptions(request,dfbsUtilityDAO,session);
           /*   String streetNumber=(String) session.getAttribute("SEARCH_STREET");
              String city=(String) session.getAttribute("SEARCH_CITY");
              String county=(String) session.getAttribute("SEARCH_COUNTY");
              inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
              String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
              String searchFor=(String) session.getAttribute("SEARCH_FOR");
              String searchZip=(String) session.getAttribute("SEARCH_ZIP");
              List plansList = idhsDAO.selectPlans(facName,streetNumber,city,county,search,null,searchZip,null);
              request.setAttribute("PLAN_SEARCH_LIST",new HsListWrapper(plansList));
              session.setAttribute("RESULTS_FLAG","PLAN"); */
              return mapping.findForward("start");
            
          }
          else if (method.equals("inactivateFacility")) 
          {  String facilityId = request.getParameter("idNumber");
              fireInspection inspection= new fireInspection();
              inspection.setInspFacId(facilityId);
              session.setAttribute("INACTIVATE_FACILITY",inspection);
           return mapping.findForward("inactivateFacility");
            
          }
        else if (method.equals("inactivateOther")) 
        {  String facilityId = request.getParameter("idNumber");
          iDAO.updateInactivateFireOther(facilityId);
          setFilterOptions(request,dfbsUtilityDAO,session);
          return mapping.findForward("start");
        }
          else if (method.equals("saveInactivateFacility")) 
          { fireInspection inspection=  (fireInspection)session.getAttribute("INACTIVATE_FACILITY");
          StringBuffer sb = new StringBuffer("");
          sb.append(inspection.getInspFacId());
          String param = request.getParameter(sb.toString());
          if(param != null ) 
          {  
          if(inspection.getInspFacId().substring(0,2).toUpperCase().equals("AE"))
              {
              iDAO.updateInactivateFireEntr(param,inspection.getInspFacId());
                  setFilterOptions(request,dfbsUtilityDAO,session);
                  String streetNumber=(String) session.getAttribute("SEARCH_STREET");
                  String city=(String) session.getAttribute("SEARCH_CITY");
                  String county=(String) session.getAttribute("SEARCH_COUNTY");
                  inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
                  String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
                  String searchFor=(String) session.getAttribute("SEARCH_FOR");
                  String searchZip=(String) session.getAttribute("SEARCH_ZIP");
                  String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
                  List aeList = idhsDAO.selectAepermits(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
                  request.setAttribute("AE_SEARCH_LIST",new HsListWrapper(aeList));
                  session.setAttribute("RESULTS_FLAG","AEPERMITS");  
                  return mapping.findForward("start");
              }
              else {
                  iDAO.updateInactivateDayCare(param,inspection.getInspFacId());
                  setFilterOptions(request,dfbsUtilityDAO,session);
                  String streetNumber=(String) session.getAttribute("SEARCH_STREET");
                  String city=(String) session.getAttribute("SEARCH_CITY");
                  String county=(String) session.getAttribute("SEARCH_COUNTY");
                  inspectionSearch search=(inspectionSearch) session.getAttribute("CURRENT_SEARCH");
                  String facName=(String) session.getAttribute("SEARCH_FAC_NAME");
                  String searchFor=(String) session.getAttribute("SEARCH_FOR");
                  String searchZip=(String) session.getAttribute("SEARCH_ZIP");
                  String searchActive=(String) session.getAttribute("SEARCH_ACTIVE");
                  List dayCareList = idhsDAO.selectDaycares(facName,streetNumber,city,county,search,null,searchFor,searchZip,searchActive);
                  request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
                  session.setAttribute("RESULTS_FLAG","DAYCARE"); 
                  return mapping.findForward("start");
              }
          }
          return mapping.findForward("inactivateFacility");
           
          }
        // fssa id number search start
            else if (method.equals("searchByIdFssa")) 
            {   String facilityId = request.getParameter("idNumber");
                String streetNumber ="";
                String city ="";
                String county ="";
                String facName="";
                String streetName="";
               inspectionSearch search = new inspectionSearch();
                session.setAttribute("CURRENT_SEARCH",search);
                session.setAttribute("SEARCH_ID_FOUND","N");
              session.setAttribute("ACTIVITIES_SELECTED",null);
                session.setAttribute("INSPECTION_SEARCH_ID",facilityId);
                session.setAttribute("RESULTS_FLAG_ID",facilityId.substring(0,2).toUpperCase());  
                
                  if(facilityId.substring(0,2).toUpperCase().equals("GH")||
                      facilityId.substring(0,2).toUpperCase().equals("RM")||
                      facilityId.substring(0,2).toUpperCase().equals("DC")||
                      facilityId.substring(0,2).toUpperCase().equals("MD")||
                      facilityId.substring(0,2).toUpperCase().equals("CI")||
                      facilityId.substring(0,2).toUpperCase().equals("SC")) {
                      List dayCareList = idhsDAO.selectDaycares(null,null,null,null,search,facilityId,null,null,null); 
                      Iterator i = dayCareList.iterator();
                      while(i.hasNext())
                      {searchResults result = (searchResults) i.next();
                        session.setAttribute("SEARCH_ID_FOUND","Y"); 
                        int streetLength =0;
                        if (result.getResultAddress1().length() <=3) {
                          streetLength = result.getResultAddress1().length();
                        }
                        else {
                          streetLength =3;
                        }
                        streetNumber=result.getResultAddress1().substring(0,streetLength);
                        city=result.getResultCity();
                        county=iDAO.selectCountyCode(result.getResultCounty());
                      } session.setAttribute("RESULTS_FLAG","DAYCARE"); 
                  }
                  setFilterOptions(request,dfbsUtilityDAO,session);
                String searchSuccess = (String) session.getAttribute("SEARCH_ID_FOUND"); 
                if (searchSuccess.equals("Y") )
                {   
                    List dayCareList = idhsDAO.selectDaycares(facName,streetNumber,city,county,search,null,null,null,null);
                    request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
                    search.setDayCareList(dayCareList);
                   }
                session.setAttribute("SEARCH_STREET",streetNumber);
                session.setAttribute("SEARCH_ST_NAME",streetName);
                session.setAttribute("SEARCH_CITY",city);
                session.setAttribute("SEARCH_COUNTY",county);
                session.setAttribute("SEARCH_FAC_NAME",facName);
                StringBuffer redirectUrl = new StringBuffer(context.getInitParameter("app_server"));
                redirectUrl.append("/dfbs/idhsInspections/inspection.do?method=inspStartFssa&facilityId="+facilityId);
                response.sendRedirect(response.encodeRedirectURL(redirectUrl.toString()));
                return null;
            }
        // fssa id number search end
        // search for FSSA
        else if (method.equals("searchPatternFssa")) 
        { 
          String streetNumber = request.getParameter("streetNumber");
            String city = request.getParameter("city");
            String streetName = request.getParameter("streetName");
            String facName = request.getParameter("facilityName");
            String county = request.getParameter("county");
            String searchZip = request.getParameter("zip");
            String inspStartDate = request.getParameter("inspStartDate");
            String inspEndDate = request.getParameter("inspEndDate");
            session.setAttribute("SEARCH_STREET",streetNumber);
            session.setAttribute("SEARCH_ST_NAME",streetName);
            session.setAttribute("SEARCH_CITY",city);
            session.setAttribute("SEARCH_COUNTY",county);
            session.setAttribute("SEARCH_FAC_NAME",facName);
             session.setAttribute("SEARCH_ZIP",searchZip);
            setFilterOptions(request,dfbsUtilityDAO,session);
            inspectionSearch search = new inspectionSearch();
            if (streetNumber.length() >0 && streetName.length() >0) {
                streetNumber= streetNumber+"%"+streetName;
            }
            session.setAttribute("CURRENT_SEARCH",search);
            if ((city.length() >0 && streetNumber.length() >0) ||(county.length() >0 && streetNumber.length() >0)||facName.length() >0)
            {
               List dayCareList = idhsDAO.selectDaycaresFssa(facName,streetNumber,city,county,search,null,inspStartDate,searchZip,inspEndDate);
                request.setAttribute("DAYCARE_SEARCH_LIST",new HsListWrapper(dayCareList));  
                search.setDayCareList(dayCareList);
               session.setAttribute("SEARCH_INCORRECT","N"); 
               // session.setAttribute("RESULTS_FLAG",""); 
            }
            else {
                session.setAttribute("SEARCH_INCORRECT","Y"); 
            }
           
           return mapping.findForward("fssaStart");
          
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
      protected static void setFilterOptions(HttpServletRequest request,HsUtilityDAO uDAO,HttpSession session) throws Exception
      {
      
      List otherCounties = uDAO.getOptions(InspectionsSQL.SELECT_COUNTY_OPTIONS);
      
      request.setAttribute("DFBS_COUNTY_OPTIONS",otherCounties);
      
      List inspFor = uDAO.getOptions(InspectionsSQL.SELECT_SEARCH_FOR_OPTIONS);
      
      request.setAttribute("SEARCH_FOR_OPTIONS",inspFor);
        List inspectors = uDAO.getOptions(InspectionsSQL.SELECT_INSPECTORS);
        
        request.setAttribute("INSPECTORS_OPTIONS",inspectors);
      }
      
        
      }
