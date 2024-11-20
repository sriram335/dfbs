<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %><%@  
taglib uri="/WEB-INF/hs.tld" prefix="hs"%><hs:control/><html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="LEPC Upload Files"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a>
<c:if test="${LEPC_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.LEPC_REVIEW_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${LEPC_APP_STATUS != 'I'}">
<h2>  Upload Ethics File</br>
</br>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
<html:form action="/lepc" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFileEthics"/>
   <html:hidden property="lepcId" />
  
  <p class="error" size="200" align="left">
   
           </br></br>
	    File Location: <html:file property="caseFile" size="50" maxlength="99" />   </br></br>
        <input type="submit" value="Upload selected" class="button"/>
       
</html:form></br>
</c:if>
</h2>
</c:if>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
