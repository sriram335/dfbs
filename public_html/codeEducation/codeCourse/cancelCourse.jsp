<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${DFBS_SECURITY.groupLevelAll == 'DBA' || DFBS_SECURITY.groupLevelCodeEducation == 'SUPERVISOR'}">
      <a href="/dfbs/codeEducation/course.do?method=addNewCourse">
            [add new course]  </a> 
   </c:if>
<div id="sideContentFluid">
     <a   href="/dfbs/codeEducation/start.do">
              [ back to start ]</a> 
   <a href="/dfbs/codeEducation/user.do?method=manageUser">
              [back to user ]  </a> </br>
<div id="mainBox">

<h2>Current Registered Code Education courses </h2>

<div id="leftContentFluid">
  <c:forEach var="course" items="${CANCEL_COURSE_LIST.list}" varStatus="i" >
  
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">
    
    <html:link page="/course.do?method=updateCourse" paramId="courseId" paramName="course" paramProperty="courseId">
    <c:out value="${course.courseName}" /><br />
     </html:link> </h3>
    
  <p class="listingInfo">
    County :<c:out value="${course.county}" /><br />
    start date:<c:out value="${course.startDateString}" /><br />
    class size:<c:out value="${course.classSize}" /><br />
    class location:<c:out value="${course.classLocation}" /><br />
   
  </p>
   <a href="/dfbs/codeEducation/course.do?method=cancelCourseSave&courseId=<c:out value="${course.courseId}" />&personId=<c:out value="${CANCEL_COURSE_PERSON}" />">
              [Cancel for this class]  </a> </br>
  </div>
 
  </c:forEach>
</div>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>