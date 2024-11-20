<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainmetnt Permits"/>
<c:set var="title" scope="request" value="Entertainmetnt Permits Application"/>
<c:set var="step" scope="request" value="3"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  
  <div id="rightContentFluid">
    <jsp:include page="applicationReview.jsp"/>
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">3) Review Application and Fees</h2>
   <c:choose >
   
  <c:when test="${DFBS_APPLICATION_ERROR == null && sessionScope.FILE_UPLOAD =='TRUE'}">
  <html:form action="/checkout" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  
      </p>
   </html:form>
   </c:when>
   <c:otherwise>
   <h3 class="error">Errors found in your application.</h3>
   <p class="error" style="font-weight:bold;">
    Please fix the following errors in order to proceed to checkout.
    </p>
   </c:otherwise>
  

  </c:choose>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">     
 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      <p>
        <input type="submit" value="Received Check / Save " class="button"/>
      </p>
      Fee ID<c:out value="${DFBS_OWNER_APPLICATION.ownerId}"/>
      <html:text property="checkNumber" size="30" maxlength="30"/>
      
</html:form>
 
</c:if>  
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
