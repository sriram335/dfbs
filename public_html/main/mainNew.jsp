<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Application List"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<div id="mainBox">
  <div id="leftContentFluid">
  <h2><font color="blue"><input type="image" src="../images/firemarshal.gif" />DFBS/IDHS Applications</font></h2>
<table width="70%" align="center" background="grey" border="0">
<tr><td colspan=2>
<p class="message">Welcome <c:out value="${DFBS_SECURITY.userId}"/>, </p>
 <c:if test="${ sessionScope.DFBS_SECURITY.userId != 'PILLINGWORTH' && sessionScope.DFBS_SECURITY.userId != 'PROBISON'}"> 
</td></tr>
 <tr><td colspan=2>&nbsp;</td></tr>
 <tr><td colspan=2>
<p class="message"> <u><b>Permits:</b></u></p>
</td></tr>
<tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelFire !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="aepermits"/>
  <li><a href="/dfbs/aepermits/start.do">AE</a></li>
 </html:form>
 </td></tr>
  <tr><td>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="fireworks"/>
  <li> <a href="/dfbs/fireworks/start.do">Fireworks</a></li>
 </html:form>
 </td></tr>
 <tr><td>
 <html:form action="/application" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="display"/>
  <li> <a href="/dfbs/display/start.do">Fireworks Public Display</a></li>
 </html:form>
</c:if>
</td></tr>
 <tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelCigarette !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="cigarettes"/>
    <li><a   href="/dfbs/cigarette/cigarette.do">Cigarette</a></li>
</html:form>
</c:if>
</td></tr>
 <tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelHazmat !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="orgShipmentView"/>
    <li><a   href="/dfbs/hazmat/orgShipmentView.do">Hazmat</a></li>
 </html:form>
 </td></tr>
 <tr><td>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="magazines"/>
   <li><a   href="/dfbs/main/main.do?method=magazines">Magazine</a></li>
 </html:form>
</c:if>
</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
 <tr><td colspan=2>
 <p class="message"> <u><b>Inspections:</b></u></p>
 </td></tr>
 <tr><td>
<c:if test="${DFBS_SECURITY != null && DFBS_SECURITY.groupLevelVar ==null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="idhsInspections"/>
   <li><a   href="/dfbs/idhsInspections/search.do">IDHS Inspections</a></li>
</html:form>
</c:if>
</td></tr>
 <tr><td>
 <c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelAmuse !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="amuseRide"/>
   <li><a   href="/dfbs/amuseRide/owner.do">Amusement Rides</a></li>
</html:form>
</c:if>
</td></tr>
<tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelFire !=null}">
<html:form action="/application" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="childcare"/>
    <li><a   href="/dfbs/childCare/start.do">Registered Ministry (Child care facilities)</a></li>
 </html:form>
 </c:if>
</td></tr>
 <tr><td>
 <c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelElev !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="elevator"/>
   <li><a   href="/dfbs/elevator/start.do">Elevators</a></li>
</html:form>
</c:if>
 </td></tr>
 <tr><td colspan=2>&nbsp;</td></tr>
<tr><td colspan=2>
 <p class="message"> <u><b>Training:</b></u></p>
 </td></tr>
 <tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelFireBldEducation !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="fireBldEducation"/>
    <li><a   href="/dfbs/fireBldEducation/start.do">Fire and Bldg Education</a></li>
</html:form>
</c:if>
</td></tr>
 <tr><td>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="ust"/>
    <li><a   href="/dfbs/ust/ust.do">UST Certification</a></li>
</html:form>
</td></tr>
 <tr><td colspan=2>&nbsp;</td></tr>
<tr><td colspan=2>
 <p class="message"> <u><b>Buildings & Codes:</b></u></p>
 </td></tr>
<tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelPlan !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="plan"/>
   <li><a   href="/dfbs/otherUsers/otherUser.do?method=userListPlan">Plan Review</a></li>
</html:form>
</c:if>
</td></tr>
 <tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelVar !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="variance"/>
   <li><a   href="/dfbs/variance/start.do">Variance Application(s)</a></li>
 </html:form>
</c:if>
</td></tr>
<tr><td>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="idhsFeesFines"/>
  <li><a   href="/dfbs/idhsFeesFines/start.do">IDHS Fees / Fines Portal</a></li>
 </html:form>
</td></tr>
 <tr><td>
 <c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelCode !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="code"/>
   <li><a   href="/dfbs/code/main.do">Industrial and Mobile Structures (Seals and CDR's)</a></li>
</html:form>
</c:if>
</td></tr>
<tr><td colspan=2>&nbsp;</td></tr>
<tr><td colspan=2>
 <p class="message"> <u><b>Misc:</b></u></p>
 </td></tr>
<tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelLepc !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="lepc"/>
   <li><a   href="/dfbs/lepc/lepc.do?method=start">LEPC</a></li>
</html:form>
</c:if>
</td></tr>
 <tr><td>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelEms !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="emsOps"/>
   <li><a   href="/dfbs/ems/main.do">EMS Operations</a></li>
</html:form>
</c:if>
</td></tr>
<tr><td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelHipaa != null}">  
   <li><a   href="/dfbs/fireHouse/fireHouse.do">EMS FIRE HOUSE </a></li>
</c:if>
  </td><td>
 <c:if test="${DFBS_SECURITY != null && DFBS_SECURITY.groupLevelVar ==null}">
  <tr><td colspan=2>
<html:form action="/reports" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="reports"/>
    <li><a   href="/dfbs/main/reports.do?method=reports">DFBS Reports</a></li>
</html:form>
  </td></tr> 
  <tr><td colspan=2>&nbsp;</td></tr>
</table>
<table width="70%" align="center" border="0">
 <tr><td colspan=2>&nbsp;</td></tr>
<tr><td colspan=4><p class="message">
<b><u>!!!YEARLY REPORTS!!!</u></b></p>
</td></tr>
<tr><td colspan=2>
 <li><a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=accounting_yearly.rdf">
             Accounting </a></li>
</td></tr>
<tr><td colspan=2>			 
 <li><a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=plan_project_counts_yearly.rdf">
             Plan Review </a></li>
</td></tr>
<tr><td colspan=2>			 
<li><a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=acadis_cert_counts.rdf">
             EMS and Fire Certifications </a></li>
</td></tr>
<tr><td colspan=2>
<li><a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepypdf&report=fire_marshal_report.rdf">
             Fire Marshal Weekly </a></li>
</td></tr>
<tr><td colspan=2>			 
<li><a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=idhs_inspection_count.rdf">
             IDHS Fire and Code Inspections (Facility counts)</a></li>
</td></tr>
<tr><td colspan=2>			 
<li><a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=fire_facility_count.rdf">
             IDHS Fire and Code Facility Count</a></li>
</td></tr>

</c:if>
</table>
</c:if>
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  </br>
