<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<c:set var="module" scope="request" value="Entertainment Permtis"/>
<c:set var="title" scope="request" value="Entertainment Permits"/>
<c:set var="level" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<jsp:include page="module/breadcrumbs.jsp"/>
<div id="mainBox">
  <div id="sideContentFluid">
  <jsp:include page="application/feeDetails.jsp"/>
  </div>
  <div id="mainContentFluid">
    <jsp:include page="module/exit.jsp?id=2"/>
    <div class="messageBox">
    <c:choose>
      <c:when test="${sessionScope.DFBS_OWNER_APPLICATION == null}">
        <p class="control2">
        <html:link page="/application.do?method=startApplication" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId">
          Start Permits Application (apply for a new permit not listed below) for : "<c:out value="${DFBS_OWNER.lastName}" />"
        </html:link>
        </p>
      </c:when>
      <c:otherwise>
        <p>
          <span class="control2">
            <html:link page="/application.do?method=intro">Continue your application in session for... <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.lastName}" /></html:link>
          </span>
        </p>
      </c:otherwise>
    </c:choose>
    </div>
    
    <jsp:include page="common/ownerDisplay.jsp"/>
        
      <c:if test="${DFBS_OWNER.permitsCount == 0}">
        <p class="messageBox">This person does not have any annual permit records for renewal in our database.
        
      </c:if>
        <jsp:include page="common/permitsList.jsp"/>
        <p class="messageBox"><span class="error">Note: special endorsement for annual permits can be applied only when you have a valid annual permit for the facility. </span></p>
     
   
    <jsp:include page="module/exit.jsp?id=2"/>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
