<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>


<c:forEach var="mapItem" items="${sessionScope.PERMIT_SELECTED.displayDatesMap}" varStatus="i">
 <c:set scope="request" var="displayDate" value="${mapItem.value}"/>
 Date: <c:out value="${displayDate.displayDateString}"/> Time: <c:out value="${displayDate.displayTime}"/></br>
 <a   href="/dfbs/display/displayDate.do?method=editDate&applicationKey=<c:out value="${displayDate.applicationKey}"/>">
              [Edit Date]</a>
 <a   href="/dfbs/display/displayDate.do?method=removeDate&applicationKey=<c:out value="${displayDate.applicationKey}"/>">
              [Remove Date]</a></br>
   </c:forEach>