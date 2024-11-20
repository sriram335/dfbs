<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Application List"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<div id="mainBox">
  <h2>Application List</h2>

  <div id="leftContentFluid">
<p class="message">
 Welcome <c:out value="${DFBS_SECURITY.userId}"/>, </p>
 <c:if test="${ sessionScope.DFBS_SECURITY.userId != 'PILLINGWORTH' && sessionScope.DFBS_SECURITY.userId != 'PROBISON'}"> 
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelFireBldEducation !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="fireBldEducation"/>
   <a   href="/dfbs/fireBldEducation/start.do">
             Fire and Bldg Education</a>
      <input type="image" src="../img/codeEducation.jpg" name="Fire and Building Eucation" value="fireBldEducation"/>
      
</html:form>
</c:if>
<c:if test="${DFBS_SECURITY != null && DFBS_SECURITY.groupLevelVar ==null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="idhsInspections"/>
   <a   href="/dfbs/idhsInspections/search.do">
              IDHS Inspections</a>
 
        <input type="image" src="../img/building inspection.jpg" name="idhsInspections" value="idhsInspections"/></br>
    
</html:form>
</c:if>


<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelFire !=null}">
<%--Removed as IOT developed PSP 01/04/2020
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="fireworks"/>
  <a   href="/dfbs/fireworks/start.do">
              Fireworks Permits</a>
 
 <input type="image" src="../img/FIREWORKS.jpg" name="Fireworks" value="fireworks"/>
      
</html:form>
--%>
<%--Removed as IOT developed PSP 01/04/2020
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="aepermits"/>
  <a   href="/dfbs/aepermits/start.do">
              AE Permits</a>

     <input type="image" src="../img/ENTERTAINMENT.jpg" name="AE Permits" value="aepermits"/>
</html:form>
--%>
<html:form action="/application" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="childcare"/>
   <a   href="/dfbs/childCare/start.do">
              Registered Ministry (Child care facilities)Online Application</a>
 
        <input type="image" src="../img/childcare.jpg" name="childcare" value="childcare"/>
      
</html:form>
<!--Removed cigarettes by skasinadhuni@dhs.in.gov
-->
<html:form action="/application" method="post">

 <input type="hidden" name="method" id="METHOD_KEY" value="display"/>
   <a   href="/dfbs/display/start.do">
              Supervised Fireworks Public Display Permit</a>
 
        <input type="image" src="../img/firedisplay.jpg" name="display" value="display"/>
      
</html:form>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="magazines"/>
   <a   href="/dfbs/main/main.do?method=magazines">
              Magazine Permits</a>
 
  <input type="image" src="../img/MAGAZINE.jpg" name="Magazine" value="magazines"/>     
      
</html:form>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="ust"/>
  <a   href="/dfbs/ust/ust.do">
              UST Certification</a>
 
 <input type="image" src="../img/ust.jpg" name="ust" value="ust"/>
      
</html:form>
</c:if>

<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelCode !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="code"/>
   <a   href="/dfbs/code/main.do">
              Industrial and Mobile Structures (Seals and CDR's)</a>
 
        <input type="image" src="../img/CODE.jpg" name="Code Enforcement" value="code"/>
      
</html:form>
</c:if>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelHazmat !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="orgShipmentView"/>
   <a   href="/dfbs/hazmat/orgShipmentView.do">
              Hazmat Permits</a>
 
        <input type="image" src="../img/hazmat.jpg" name="Hazmat" value="orgShipmentView"/>
      
</html:form>
</c:if></br>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelPlan !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="plan"/>
   <a   href="/dfbs/otherUsers/otherUser.do?method=userListPlan">
              Plan Review</a>
 
        <input type="image" src="../img/drafting2.gif" name="Plan" value="plan"/>
      
</html:form>
</c:if>
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="idhsFeesFines"/>
   <a   href="/dfbs/idhsFeesFines/start.do">
              IDHS Fees / Fines Portal</a>
 
        <input type="image" src="../img/dollar.jpg" name="IDHS Fees Fines" value="idhsFeesFines"/>
      
</html:form>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelLepc !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="lepc"/>
   <a   href="/dfbs/lepc/lepc.do?method=start">
              LEPC</a>
 
        <input type="image" src="../img/LEPC.JPG" name="LEPC'S" value="lepc"/>
      
</html:form></br></br>
</c:if>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelEms !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="emsOps"/>
   <a   href="/dfbs/ems/main.do">
              Ems Operations</a>
 
        <input type="image" src="../img/ems.jpg" name="Ems Operations" value="emsOps"/>
      
</html:form></br></br>
</c:if>
<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelVar !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="variance"/>
   <a   href="/dfbs/variance/start.do">
              Variance Application(s)</a>
        <input type="image" src="../img/variance.gif" name="Variance Application(s)" value="emsOps"/>
      
</html:form></br></br>
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelHipaa != null}">  

   <a   href="/dfbs/fireHouse/fireHouse.do">
              EMS FIRE HOUSE </a>
 
</c:if></br></br>
<%--

<c:if test="${DFBS_SECURITY.groupLevelAll !=null || DFBS_SECURITY.groupLevelEms !=null}">
<html:form action="/main" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="ems"/>
   <a   href="/dfbs/ems/main.do?method=publicAccess">
              IDHS EMS Online Application</a>
 
        <input type="image" src="../img/ems.jpg" name="Ems" value="ems"/>
      
</html:form>
</c:if>
 --%>
 <c:if test="${DFBS_SECURITY != null && DFBS_SECURITY.groupLevelVar ==null}">
<html:form action="/reports" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="reports"/>
   <a   href="/dfbs/main/reports.do?method=reports">
             DFBS Reports</a>
 
       <input type="image" src="../img/REPORT.jpg" name="Reports" value="reports"/>
      
</html:form></br>
<b>!!!YEARLY REPORTS!!!</b></br>
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=accounting_yearly.rdf">
             Accounting Yearly Report</a></br>
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=plan_project_counts_yearly.rdf">
             Plan Review yearly Report</a></br>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=acadis_cert_counts.rdf">
             EMS and Fire Certifications Yearly Report</a></br>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepypdf&report=fire_marshal_report.rdf">
             Fire Marshal Weekly Report</a></br>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=idhs_inspection_count.rdf">
             IDHS Fire and Code Inspections Yearly Report(Facility counts)</a></br>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnpdf&report=fire_facility_count.rdf">
             IDHS Fire and Code Facility Count</a></br> 
</c:if>
</c:if>
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  </br>
