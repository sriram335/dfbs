<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Inspections Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<link rel="stylesheet" type="text/css" media="all" href="/dfbs/idhsInspections/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="/dfbs/idhsInspections/jsDatePick.min.1.3.js"></script>
<script type="text/javascript">
window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"inspStartDate",
			dateFormat:"%m/%d/%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"inspEndDate",
			dateFormat:"%m/%d/%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"inspGpStartDate",
			dateFormat:"%m/%d/%Y"
		});
		new JsDatePick({
			useMode:2,
			target:"inspGpEndDate",
			dateFormat:"%m/%d/%Y"
		});
	};
</script>
<div id="sideContentFluid">
<jsp:include page="/idhsInspections/searchResults.jsp"/>
</div> 
<div id="mainBox">
<a   href="/dfbs/idhsInspections/search.do?method=aepermits">
AE Permit(s)[<c:out value="${sessionScope.CURRENT_SEARCH.aeCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=bpv">
 BPV[<c:out value="${sessionScope.CURRENT_SEARCH.bpvCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=elevators">
 Elevator(s)[<c:out value="${sessionScope.CURRENT_SEARCH.elevCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=daycare">
 Child Care(s)[<c:out value="${sessionScope.CURRENT_SEARCH.daycareCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=fireworksRetail">
 Fireworks Retail[<c:out value="${sessionScope.CURRENT_SEARCH.fireworksRetailCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=fireworksWholesale">
 Fireworks Wholesale[<c:out value="${sessionScope.CURRENT_SEARCH.fireworksWholesaleCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=hospital">
 Health Care(s)[<c:out value="${sessionScope.CURRENT_SEARCH.hospitalCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=other">
 Other(s)[<c:out value="${sessionScope.CURRENT_SEARCH.otherCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=school">
 School(s)[<c:out value="${sessionScope.CURRENT_SEARCH.schoolCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=plan">
 Plan(s)[<c:out value="${sessionScope.CURRENT_SEARCH.planCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=ust">
 UST(s)[<c:out value="${sessionScope.CURRENT_SEARCH.ustCount}"/>] </a>&nbsp; 
<a   href="/dfbs/idhsInspections/search.do?method=newInspections">
 Complaint Inspections[<c:out value="${sessionScope.CURRENT_SEARCH.complaintCount}"/>] </a>&nbsp; 
<a   href="/dfbs/idhsInspections/search.do?method=indMobileInspections">
 Ind/Mobile Inspections[<c:out value="${sessionScope.CURRENT_SEARCH.indMobileCount}"/>] </a>&nbsp;  
<a   href="/dfbs/variance/start.do?method=viewApplications&from=idhsInsp">Variance </a>&nbsp; 
<a   href="/dfbs/idhsInspections/inspection.do"> 
       Go to Inspection(s) </a>&nbsp; 
<a   href="/dfbs/idhsInspections/inspectorActivity.do?method=newInspActivity"> 
       Add Inspector Activity </a>&nbsp; 
<a   href="/dfbs/idhsInspections/idhsInspection.do?method=startOver"> 
       Search Start Over </a>&nbsp;       
  
<%--
<a   href="/dfbs/idhsInspections//excelUpload.do?method=uploadExcelFile">
Read Files </a>&nbsp;   <c:out value="${sessionScope.DFBS_SECURITY.groupLevelFire}"/>--%>
<div id="leftContentFluid">

<b><u>Search by ID</u></b>
<html:form action="/search" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="searchById"/> 
 
  <p align="left">
                 Facility ID:<html:text property="idNumber" size="30" maxlength="30"/> </br>
          Search For <html:select property="searchFor" styleId="SELECT_INSP" >
          <html:option value="Fire">Fire</html:option>
          <html:option value="Elevators">Elevators</html:option>
          <html:option value="BPV">BPV</html:option>
        </html:select>
            <input type="submit" value="Search" class="button"/>
        </br>        
</p>
</html:form>

<b><u>Search by </u></b></br> needs Street Number and (City or County))
<html:form action="/search" method="post" >
            <input type="hidden" name="method" id="METHOD_KEY" value="search"/> 
   <p align="left">
                Facility Name&nbsp;:<html:text property="facilityName" size="30" maxlength="30"/> </br>
             <FONT color="#009933">     (To narrow down search for Cheese Cake Factory use Cheese and city or county combination)</br></FONT>
                Street Number:<html:text property="streetNumber" size="6" maxlength="6"/> </br>
                Street Name&nbsp;&nbsp;&nbsp;:<html:text property="streetName" size="20" maxlength="20"/> </br>
             <FONT color="#009933">   (To narrow down search for west washington st use wash)</br></FONT>
                Facility City&nbsp;&nbsp;&nbsp;&nbsp;:<html:text property="city" size="30" maxlength="30"/></br>
                Facility County:<html:select property="county" >
                <html:option value="">-----</html:option>
                <html:options 
                  collection="DFBS_COUNTY_OPTIONS" 
                  property="value" 
                  labelProperty="label" />
                </html:select>  
                Facility Zip:<html:text property="zip" size="5" maxlength="5"/></br>
                  Show Records&nbsp;:<html:select property="searchActive" >
                <html:option value="Active">Active</html:option>
                <html:option value="All">All</html:option>
                </html:select>   </br> 
                <u><b>Refine Search by:</b></u> </br>
                Facility Type&nbsp;&nbsp;&nbsp;:<html:select property="searchFacType" >
                <html:option value="ALL">-----</html:option>
                <html:option value="AE Permits">AE Permits</html:option>
                 <html:option value="BPV">BPV</html:option>
                 <html:option value="Child Cares">Child Cares</html:option>
                  <html:option value="Elevators">Elevators</html:option>
                    <html:option value="Fireworks">Fireworks</html:option>
                  <html:option value="Health Cares">Health Cares</html:option>
                  <html:option value="Others">Others</html:option> 
                   <html:option value="Plans">Plans</html:option>
                  <html:option value="Schools">Schools</html:option> 
                  <html:option value="USTs">USTs</html:option>
                </html:select>   </br>
                 Insp. Status&nbsp;&nbsp;&nbsp;:<html:select property="searchFor" >
                <html:option value="">-----</html:option>
                <html:option value="toBeInspected">To Be Inspected</html:option>
                 <html:option value="Complied">Complied</html:option>
                </html:select>   </br>
    
            <input type="submit" value="Search" class="button"/></br>
            <c:if test="${START_FORM_ERROR.streetNumber == 0 && START_FORM_ERROR.streetName == null}">
               <span class="error">
               * please enter street number and street name for search</span> 
              </c:if></br>
</p>
</html:form>
<div align="left">
<b><u>Search my Inspections </u></b></br>
<html:form action="/inspection" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="lookUpMyInspections"/> 
 <input type="text" size="12" name="inspStartDate" id="inspStartDate" /> (mm/dd/yyyy)
 <input type="text" size="12" name="inspEndDate" id="inspEndDate" /> (mm/dd/yyyy)
 <c:if test="${INSP_DATE_ERROR == 'Y'}">
          <br/>
          <span class="error">* please enter from and to dates for search</span> 
 </c:if>
  
            <input type="submit" value="Show My Inspections" class="button"/>  </br></br>          

</html:form>
<b><u>Re-Inspections Due</u></b></br>
 <a   href="/dfbs/idhsInspections/idhsInspection.do?method=dueInspections&p_days=30">
              [Due in 30 days]</a>
 <a   href="/dfbs/idhsInspections/idhsInspection.do?method=dueInspections&p_days=60">
              [Due in 60 days]</a>
  <a   href="/dfbs/idhsInspections/idhsInspection.do?method=dueInspections&p_days=90">
              [Due in 90 days]</a></br></br>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR'}"> 
              
<b><u>Search my groups Inspections(applicable only for Inspector Supervisors)</u></b></br>
<html:form action="/inspection" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="lookUpMyGroupInspections"/> 
<!--
<html:text property="inspGpStartDate" size="10" maxlength="10"/> (mm/dd/yyyy)<a href="javascript:NewCal('inspGpStartDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
  <html:text property="inspGpEndDate" size="10" maxlength="10"/> (mm/dd/yyyy)<a href="javascript:NewCal('inspGpEndDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a></br>
 -->
 <input type="text" size="12" name="inspGpStartDate" id="inspGpStartDate" /> (mm/dd/yyyy)
 <input type="text" size="12" name="inspGpEndDate" id="inspGpEndDate" /> (mm/dd/yyyy)
Select inspector for <html:select property="inspInspectorId" styleId="SELECT_INSPECTOR_ID" onchange="setSelectValue('SELECT_INSPECTOR_ID','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSPECTORS_OPTIONS" property="value" labelProperty="label"/>
 </html:select> </br> 
  <c:if test="${INSP_DATE_ERROR == 'Y'}">
          <br/>
          <span class="error">* please enter from and to dates for search</span> 
 </c:if>
  
            <input type="submit" value="Show My Group Inspections" class="button"/>  </br>          
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=inspector_insp_status_count_bymonth.rdf&p_insp_id=0&p_start_date=&p_end_date=&p_unit=">
              [Inspector Complied/Violation Counts]</a></br>   
 
</html:form>
</c:if>
</div>
</div>
<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 