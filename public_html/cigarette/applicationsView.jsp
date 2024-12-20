<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION" />
<c:set var="title" scope="request" value="Cigarette Companies" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">


 <a   href="/dfbs/cigarette/cigarette.do">
              [back to start]</a>  

<div id="mainContentFluid" align="left">

<h2>Company and Application Details</h2>
<c:if test="${sessionScope.APPLICATION_TYPE == 'Supplemental' }">
 <p class="error">
1.Select the company Name.</br>
2. Click the "Add Supplemental Application" link to add Supplemental Application.</br>
</p>
</c:if>
<c:if test="${sessionScope.APPLICATION_TYPE == 'Initial' }">
 <p class="error">
1.Select the Manufacture Company Name.(Click on the first letter of the company name in the list)</br>
2. Click the "Add Initial Application" link to add Initial Application.</br>
</p>
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'}">   
</c:if>


<div class="charNav">
<ul>
<c:forEach var="c" items="${COMPANY_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${COMPANY_LIST_FILTER.type == 'byLetter' && COMPANY_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/cigarette/applicationsView.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="company" items="${COMPANY_LIST.list}" varStatus="i" >
   
  <div class="listing">
  <h3 style="margin-bottom:5px;">
  Company Name:<c:out value="${company.companyName}" />
  </h3>
  <c:if test="${sessionScope.APPLICATION_TYPE == 'Initial' }">
<a   href="/dfbs/cigarette/cigarette.do?method=addInitial&companyId=<c:out value="${company.companyId}"/>">[Add Initial Application]</a>
</c:if>
 <c:if test="${sessionScope.APPLICATION_TYPE == 'Supplemental' }">
<a   href="/dfbs/cigarette/cigarette.do?method=addSupApp&companyId=<c:out value="${company.companyId}"/>">[Add Supplemental Application]</a>
</c:if>
<c:if test="${sessionScope.CIGARETTE_USER !=null }">
<a   href="/dfbs/cigarette/brand.do?method=addNewBrand&companyId=<c:out value="${company.companyId}"/>">[Add New Brand Family]</a> </br>
<a   href="/dfbs/cigarette/cigarette.do?method=updateCompanyAgentPhones&companyId=<c:out value="${company.companyId}"/>">[Edit contact phones]</a>
</c:if>
 <c:if test="${sessionScope.APPLICATION_TYPE == '3YearRenewal' }">
<a   href="/dfbs/cigarette/cigarette.do?method=add3YearRenewal&companyId=<c:out value="${company.companyId}"/>">[Add 3 Year Renewal Application]</a>
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'}">   
</c:if>


  <p class="listingInfo">
  Address:
    <c:out value="${company.companyAddress1}" /><br />
    <c:if test="${company.companyAddress2 != null && company.companyAddress2 != ''}">
    <c:out value="${company.companyAddress2}" /><br />
    </c:if>
    <c:out value="${company.companyCity}" />, <c:out value="${company.companyState}" /> <c:out value="${company.companyZip}" /><br />
    <br />
  </p>

 <c:forEach var="application" items="${company.applications}" varStatus="i" >
<b> Application Date: </b>
<a   href="/dfbs/cigarette/application.do?method=approveApplication&appId=<c:out value="${application.appId}" />"><c:out value="${application.appDateString}" /> </a>
<b> Application Type: </b><c:out value="${application.appType}" /><b>
 <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_application.rdf&p_application_id=<c:out value="${application.appId}"/>" target="_blank" >[View Application]</a></br>
Certification Status: </b><c:out value="${application.appStatus}" />
 <c:if test="${application.appStatus == 'Approved' && application.feesPending == 0}">
 <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=cigarette_permit.rdf&p_application_id=<c:out value="${application.appId}"/>" target="_blank">[View and Print Certification]</a></br>
</c:if>

</br>
 </c:forEach>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
