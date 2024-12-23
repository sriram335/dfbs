<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Organization's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

          <h2>Contact Us</h2>
          <p>
          You contact us via <a href="/dfbs/ems/main.do?method=sendEmailForm">email</a> or by phone
          </p>
          <p>
          Robin Stump : <strong>317-753-3750</strong>
          </p>
         
    
</div>


<div id="mainContentFluid">

<h2>Current Organizations registered with state of IDHS </h2>
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br></br>

<div class="charNav">
<ul>
<c:forEach var="c" items="${ORGANIZATION_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${ORGANIZATION_LIST_FILTER.type == 'byLetter' && ORGANIZATION_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/ems/organization.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</c:forEach>
</ul>
</div>


  <c:forEach var="orgName" items="${ORGANIZATION_LIST.list}" varStatus="i" >
 
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
    <c:out value="${orgName}" />
   <a href="/dfbs/ems/instructor.do?method=instructorListOrganization&orgName=<c:out value="${orgName}"/>">
    Look up persons list for this organization </a> </br></p>
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=acadis_person_detail.rdf&p_orgName=<c:out value="${orgName}"/>"> View all person's certificates for this organization</a>
  </h3>
  
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
