<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<h2><u> New Course Status</u></h2>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
  <c:if test="${sessionScope.NEW_COURSE  != null }">
  New Course Application Status:<b>
  <FONT color="#ff6666">  <c:out value="${sessionScope.NEW_COURSE}"/>
  </b></FONT></br>
Select Course Type:<b><c:out value="${sessionScope.NEW_COURSE_TYPE}" /></b></br>
Select Institution:<b><c:out value="${sessionScope.INSTITUTION.institutionName}" /></b></br>
Course Information:<b><c:out value="${sessionScope.NEW_COURSE_INFORMATION}" /></b></br>
Instructor Selection:<b><c:out value="${sessionScope.NEW_COURSE_INSTRUCTOR}" /></b></br>
Course Agreement:<b><c:out value="${sessionScope.NEW_COURSE_AGREEMENT}" /></b></br>
Files Upload:<b><c:out value="${sessionScope.NEW_COURSE_FILE_UPLOAD}" /></b></br>

</c:if>
</c:if>
