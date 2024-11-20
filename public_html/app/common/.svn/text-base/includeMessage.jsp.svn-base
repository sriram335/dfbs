<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>

<hs:control />




<div class="messageBox message">
<h3 id="title" class="message">Message:</h3>
<c:choose>
  <c:when test="${HS_MESSAGE == 'HS_MESSAGE_PASSWORD_CHANGED'}">
    <p>Password successfully changed!</p>
  </c:when>
  <c:when test="${HS_MESSAGE == 'HS_MESSAGE_MAIL_SUCCESS'}">
    <p>Your message has been successfully sent.</p>
  </c:when>
  <c:when test="${HS_MESSAGE == 'HS_MESSAGE_SAVE_SUCCESS'}">
    <p>Your data has been sucessfully saved.</p>
  </c:when>
</c:choose>
</div>