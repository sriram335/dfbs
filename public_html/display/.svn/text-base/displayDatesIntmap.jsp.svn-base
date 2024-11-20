<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
<a   href="/dfbs/display/displayDate.do?method=addIntDate">
              [Add New Date]</a></br></br>
</c:if>
 <c:forEach var="displayDate" items="${sessionScope.PERMIT_SELECTED.displayDatesList}" varStatus="i" >
 Date: <c:out value="${displayDate.displayDateString}"/> Time: <c:out value="${displayDate.displayTime}"/></br>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
 <a   href="/dfbs/display/displayDate.do?method=editIntDate&displayDateId=<c:out value="${displayDate.displayDateId}"/>">
              [Edit Date]</a>
 <a   href="/dfbs/display/displayDate.do?method=removeIntDate&displayDateId=<c:out value="${displayDate.displayDateId}"/>">
              [Remove Date]</a></br>
  </c:if>
   </c:forEach>
