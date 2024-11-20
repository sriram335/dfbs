 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Fireworks Seller"/>
<c:set var="step" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="rightContentFluid">
 
    <jsp:include page="application.jsp"/>
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">2) Renew/Add Permits </h2>
   
    <p class="control">
      <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/> <html:link page="/permit.do?method=addPermitForm">add new permit</html:link>
    </p>
    
    
    <c:if test="${DFBS_OWNER.permitsCount > 0}">
      <p style="font-size:x-large;font-weight:bold;">OR</p>
        <p style="font-size:medium;font-weight:bold;">
          <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/> click  renew to start renewal process :
        </p>
        <p>
        <jsp:include page="/fireworks/application/permitsListSelect.jsp"/>
      
    </c:if></br></br>
     <c:if test="${sessionScope.DFBS_OWNER_APPLICATION.permitsMapCount > 0}">
     <p class="control">
      <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/> <html:link page="/application.do?method=step3">Verify Application & Fee  and Proceed to Check Out</html:link>
      </p>
    </c:if>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
