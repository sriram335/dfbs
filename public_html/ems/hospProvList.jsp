<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Hospital attached Provider's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox" align="left">


<div id="leftContentFluid" align="left">
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
<a   href="/dfbs/ems/hospital.do?method=hospitalList">
              [ back to hospital list ]</a>             
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
 <a   href="/dfbs/ems/provider.do?method=providerList&hospAction=addNew">
              [ add new providers ]</a> 
</c:if>

<h2>Current EMS service providers attached to the Hospital</h2>

  <c:forEach var="provider" items="${PROVIDER_LIST.list}" varStatus="i" >
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
  <a   href="/dfbs/ems/institution.do?method=deleteProvider&providerId=<c:out value="${provider.providerId}" />">
              [ delete this provider to hospital ]</a> 
  </c:if>
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/provider.do?method=providerDetail" paramId="providerId" paramName="provider" paramProperty="providerId">
    <c:out value="${provider.providerName}" />
    </html:link>
  </h3>
  <p class="listingInfo">
    <c:out value="${provider.providerAddress1}" /><br />
    <c:if test="${provider.providerAddress2 != null && provider.providerAddress2 != ''}">
    <c:out value="${provider.providerAddress2}" /><br />
    </c:if>
    <c:out value="${provider.providerCity}" />, <c:out value="${provider.providerState}" /> <c:out value="${provider.providerZip}" /><br />
    <c:out value="${provider.providerCounty}" />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
