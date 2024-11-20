<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <c:if test="${VIEW_USER !=null}">
 <a href="/dfbs/codeEducation/start.do">
              [back to main menu]  </a> </br>
<a href="/dfbs/codeEducation/user.do?method=manageUser">
              [back to user ]  </a> </br>
</c:if>
<c:if test="${DFBS_SECURITY.groupLevelAll == 'DBA' || DFBS_SECURITY.groupLevelCodeEducation == 'SUPERVISOR'}">
<a href="/dfbs/codeEducation/start.do">
              [back to main menu]  </a> </br>
 <a href="/dfbs/codeEducation/user.do?method=userList">
              [back to user list ]  </a> </br>
   <a href="/dfbs/codeEducation/course.do?method=courseList">
              [back to course list ]  </a> </br>
</c:if>
<div id="mainBox">

<h2>User Education courses </h2>


<div id="leftContentFluid">
  <c:forEach var="course" items="${CLASS_LIST.list}" varStatus="i" >
  
  <div class="listing" align="left">
   
    
  <p class="listingInfo">
   Name:<c:out value="${course.courseName}" /><br />
    class location:<c:out value="${course.classLocation}" /><br />
    
   </p>
 
  </div>
 
  </c:forEach>
</div>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>