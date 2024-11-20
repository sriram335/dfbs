<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="UST Certifications" />
<c:set var="title" scope="request" value="UST Certification(s)" />



<jsp:include page="/app/common/hsPageHeader.jsp" />
<c:if test="${UST_PERMITS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.MAG_PERMITS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${UST_PERMITS_APP_STATUS != 'I'}">

<h2>Start UST Application</h2>

<div id="leftContentFluid" align="left">

<c:if test="${sessionScope.DFBS_SECURITY !=null}">
<p class="message">
Welcome  <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
<html:form action="/ust" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="renewUst"/>
   <p class="messageBox" size="100">
   To Renew existing UST certification with IDHS</br></br>
         <input type="submit" value="Renew My UST Certification" class="button"/></br></br>
    </p>  
</html:form></br></br>
 <html:form action="/ust" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="newApp"/>
            <p class="messageBox" size="100">
            Apply for NEW UST certification with IDHS</br></br>
              <input type="submit" value="Start New Application" class="button"/></br></br>
            </p>
</html:form>
      
</div>


<div class="clearer">&nbsp;</div>

</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
