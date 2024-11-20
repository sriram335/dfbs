 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Fireworks Seller"/>
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
          Start Permits Application for... "<c:out value="${DFBS_OWNER.ownerName}" />"
        </html:link>
        </p>
        <html:form action="/application" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="startApplication"/> 
         <input type="hidden" id="ownerId" name="ownerId" value="${DFBS_OWNER.ownerId}"/> 
        <input type="submit" value="Start Application" class="button"/></br></br>
         </html:form>
      </c:when>
      <c:otherwise>
        <p>
          <span class="control2">
            <html:link page="/application.do?method=intro">Continue your application in session for... <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.ownerName}" /></html:link>
          </span>
        </p>
      </c:otherwise>
    </c:choose>
    </div>
    
    <jsp:include page="common/ownerDisplay.jsp"/>
    <c:choose>
      <c:when test="${DFBS_OWNER.permitsCount == 0}">
        <p class="messageBox">This seller does not have any permit record in our database.</p>
      </c:when>
      <c:otherwise>
        <jsp:include page="common/permitsList.jsp"/>
      </c:otherwise>
    </c:choose>
    <jsp:include page="module/exit.jsp?id=2"/>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
