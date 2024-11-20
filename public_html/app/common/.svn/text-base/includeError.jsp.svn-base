<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<div class="messageBox error">
  <h3 id="title" class="error">Error:</h3>
  <c:choose>
    <c:when test="${HS_ERROR == 'HS_ERROR_FORM_RESUBMIT'}">
      <p class="error">Resubmission error!</p>
    </c:when>
    <c:when test="${HS_ERROR == 'HS_ERROR_METHOD_INVALID'}">
      <p class="error">Invalid method request!</p>
    </c:when>
    <c:when test="${HS_ERROR == 'HS_ERROR_NO_AUTHORIZATION'}">
      <p class="error">You are not authorized to access this page!</p>
    </c:when>
    <c:when test="${HS_ERROR == 'HS_ERROR_SAVE'}">
      <p>Unable to save!</p>
    </c:when>
    <c:when test="${HS_ERROR == 'HS_ERROR_SAVE_PARTIAL'}">
      <p>Not all data was saved!</p>
    </c:when>
    <c:otherwise>
      <p>
        <Strong>ERROR:</Strong> 
        <c:out value="${HS_ERROR}"/>
      </p>
    </c:otherwise>
  </c:choose>
</div>
