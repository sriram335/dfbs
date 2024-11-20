  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Application for Sale of Fireworks"/>
<c:set var="step" scope="request" value="0"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2 style="font-weight:bold;">Instructions</h2>
  <div id="rightContentFluid">
    <c:choose>
      <c:when test="${requestScope.DFBS_APPLICATION == 'NEW'}">
        <div style="font-weight:bold;font-size:large;margin-top:10px;">
          <p>
            <html:link page="/application.do?method=step1">proceed to step 1 &gt;</html:link>
          </p>
          <p style="font-weight:normal">
          <html:link page="/application.do?method=startOver">[cancel]</html:link>
          </p>
        </div>
      </c:when>
      <c:when test="${requestScope.DFBS_APPLICATION == 'RENEW'}">
        <div style="font-weight:bold;font-size:large;margin-top:10px;">
          <p>
            <html:link page="/application.do?method=step1">proceed to step 1 &gt;</html:link>
          </p>
          <p style="font-weight:normal">
          <html:link page="/application.do?method=startOver">[cancel]</html:link>
          </p>
        </div>
      </c:when>
      <c:when test="${sessionScope.DFBS_OWNER_APPLICATION != null}">
        <jsp:include page="application.jsp"/>
      </c:when>
    </c:choose>
  </div>
  <div id="leftContentFluid">
    <c:choose>
      <c:when test="${requestScope.DFBS_APPLICATION == 'NEW'}">
        <div class="messageBox">
          <h3>Important:</h3>
          <p>You are a 
            <span style="font-size:x-large;font-weight:bold;">NEW</span> fireworks seller.
          </p>
          <p>Please make sure you are not in our  
            <html:link page="/application.do?method=startOver">database</html:link>  before you proceed...
          </p>
        </div>
      </c:when>
      <c:when test="${requestScope.DFBS_APPLICATION == 'RENEW'}">
        <div class="messageBox">
          <h3>Important:</h3>
          <p>You are filling out an application for:</p>
          <p style="font-size:x-large;font-weight:bold;">
           
              <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.ownerName}"/>
           
          </p>
        </div>
      </c:when>
    </c:choose>
    <h3>In just 3 steps...</h3>
    <dl class="modified">
      <dt>1) Specify company information</dt>
      <dd>enter or update your company imformation</dd>
      <dt>2) Renew/add permits</dt>
      <dd>select permits you want to renew or add new permits</dd>
      <dt>3) review application and fees</dt>
      <dd>Final step before checkout. You'll be able to review you application and permit fees.</dd>
    </dl>
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
