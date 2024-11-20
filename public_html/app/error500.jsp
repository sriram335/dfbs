<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="DHS Web"/>
<c:set var="title" scope="request" value="Error 500 - Web Applicaton Error"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="mainContent">
    
    <c:choose>
      <c:when test="${ HS_ERROR == 'UNAUTHORIZED_ACCESS' }">
       <h2 style="font-weight:bold;"> <img class="iconFalse" src="/img/clearbits/close.gif" alt="arrow" />Error: Unauthorized access...</h2>
      
       <p>
       You were accessing a page with out proper authorization.
       </p>
      </c:when>
      
      <c:when test="${ HS_ERROR == 'NO_OWNER_IN_SESSION' }">
      <h2 style="font-weight:bold;"> <img class="iconFalse" src="/img/clearbits/close.gif" alt="arrow" /> Error: Application has expired...</h2>
       
       <p>
       Your application seems to have expired. This usually happens when you 
       are inactive for 30 minutes.
       </p>
      </c:when>
      
      <c:when test="${ HS_ERROR == 'ERROR_OWNER_CHECKOUT_TOKEN' }">
       <h2 style="font-weight:bold;"> <img class="iconFalse" src="/img/clearbits/close.gif" alt="arrow" />Error: Checkout session has expired...</h2>
      
       <p>
       Your checkout session seems to have expired. 
       </p>
      </c:when>
      
      <c:when test="${ HS_ERROR == 'HS_ERROR_METHOD_INVALID' }">
       <h2 style="font-weight:bold;"> <img class="iconFalse" src="/img/clearbits/close.gif" alt="arrow" />Error: Unable to handle your request...</h2>
       <p>
       You tried to access a page with out passing a valid parameter.
       </p>
      </c:when>
      
      <c:when test="${ HS_ERROR == 'ERROR_EMAIL_FAILED' }">
       <h2 style="font-weight:bold;"> <img class="iconFalse" src="/img/clearbits/close.gif" alt="arrow" />Error: Unable to send email...</h2>
       
      </c:when>
      
      <c:otherwise>
        <h2 style="font-weight:bold;"> <img class="iconFalse" src="/img/clearbits/close.gif" alt="arrow" /> Error: <c:out value="${DFBS_APPLICATION_ERROR}" /></h2>
        <p>
        You encountered an unknown <c:out value="${DFBS_APPLICATION_ERROR}" /> while accessing our pages. Please email/ call the following contacts.
       </p>
       <p>
       <strong>possible cause: "<c:out value="${HS_ERROR}" />"</strong>
       </p>
      </c:otherwise>
    </c:choose>
    
    <h3>You might want to...</h3>
    <div class="sideContentList">
    <ul>
    <li> <img class="icon" src="/img/clearbits/arrow2_e.gif" alt="arrow" /> go back to the <a href="/dfbs/main/main.do">home page</a></li>
    </ul>
    </div>
    
  </div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
