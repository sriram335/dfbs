<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<h2><u> Provider Renewal Status</u></h2>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
<c:if test="${sessionScope.RENEW_PROVIDER  != null }">
  Renewal Application Status:<b>
  <FONT color="#ff6666">  <c:out value="${sessionScope.RENEW_PROVIDER}"/>
  </b></FONT></br>
Select provider:<b><c:out value="${sessionScope.RENEW_PROVIDER_SELECT_PROVIDER}" /></b></br>
Update Provider Information:<b><c:out value="${sessionScope.RENEW_PROVIDER_UPDATE}" /></b></br>
Provider Renewal Information:<b><c:out value="${sessionScope.RENEW_PROVIDER_RENEW_INFORMATION}" /></b></br>
Update Vehicle Location:<b><c:out value="${sessionScope.RENEW_PROVIDER_VEHICLE_INFORMATION}" /></b></br>
Files Upload:<b><c:out value="${sessionScope.RENEW_PROVIDER_FILE_UPLOAD}" /></b></br>
** verify that all the files listed in the page are uploaded even when status shows complete.(Status will show complete
after first file is uploaded)**

</c:if>
</c:if>
