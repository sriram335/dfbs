<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <c:if test="${VIEW_USER !=null}">
  <html:link page="/user.do?method=changePassword">[change password]</html:link>
  <html:link page="/user.do?method=logOut">[log out]</html:link>
  </c:if>
<c:if test="${DFBS_SECURITY.groupLevelAll == 'DBA' || DFBS_SECURITY.groupLevelFireBldEducation == 'USER'}">
      <a href="/dfbs/fireBldEducation/course.do?method=addNewCourse">
            [add new course]  </a> 
     <a href="/dfbs/fireBldEducation/user.do?method=userList">
              [back to user list ]  </a> </br>
   <a href="/dfbs/fireBldEducation/course.do?method=courseList">
              [back to course list ]  </a> </br>
   </c:if>
 <c:if test="${VIEW_USER !=null}">
 <a href="/dfbs/fireBldEducation/start.do">
              [back to main menu]  </a> </br>
<a href="/dfbs/fireBldEducation/user.do?method=manageUser">
              [back to user]  </a> </br>
</c:if>
<div id="mainBox">

<h2>Current Fire and Building Education courses </h2>
<c:if test="${REGISTERED_PREVIOUSLY == 'Registered'}">
<font color="#ff0000">
You are already registered!</font>
</c:if>
<c:if test="${REGISTERED_SUCCESSFULLY == 'Y'}">
<font color="#ff0000">
You are registered for course successfully. You will receive a confirmation email shortly.</font>
</c:if>
<%--
<div class="charNav">
<ul>
<c:forEach var="c" items="${COURSE_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${COURSE_LIST_FILTER.type == 'byLetter' && COURSE_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/fireBldEducation/course.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> users" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div> --%>
<div id="leftContentFluid">
  <c:forEach var="course" items="${COURSE_LIST.list}" varStatus="i" >
  
  <div class="listing" align="left">
 
   <c:if test="${DFBS_SECURITY.groupLevelAll == 'DBA' || DFBS_SECURITY.groupLevelFireBldEducation == 'USER'}"> 
    <h3 style="margin-bottom:5px;">
    <html:link page="/course.do?method=updateCourse" paramId="courseId" paramName="course" paramProperty="courseId">
    <c:out value="${course.courseName}" /><br />
     </html:link> </h3>
  </c:if>
  <c:if test="${DFBS_SECURITY.groupLevelAll == null || DFBS_SECURITY.groupLevelFireBldEducation == null}"> 
   <h3 style="margin-bottom:5px;">
    <c:out value="${course.courseName}" /><br />
      </h3>
  </c:if>
  <%-- <html:link page="/course.do?method=insertCalandar" paramId="courseId" paramName="course" paramProperty="courseId">
    Insert Calandar<br />
     </html:link> </h3>  --%>
  <p class="listingInfo">
    credits :<c:out value="${course.county}" /><br />
    class date:<c:out value="${course.startDateString}" /><br />
    class duration:<c:out value="${course.courseLength}" /><br />
    <%-- class size:<c:out value="${course.classSize}" /><br />--%>
    class location:<c:out value="${course.classLocation}" /><br />
     class Address:<c:out value="${course.address1}" />,<c:out value="${course.address2}" /><br />
                 <c:out value="${course.city}" />,&nbsp;IN&nbsp;<c:out value="${course.zip}" /><br />
    
    <font color="#ff0000">open vacancies for registration:<c:out value="${course.classVacancy}" /></font><br />
    Course Description:<c:out value="${course.courseDescription}" />
      <br/>
   </p>
   <c:if test="${course.classVacancy > 0}">
  <a href="/dfbs/fireBldEducation/course.do?method=registerCourse&courseId=<c:out value="${course.courseId}" />">
              [Register for this class]  </a> </br>
 </c:if>
   <c:if test="${course.classVacancy <= 0}">
   <p class="error">
   Class Full, You can come back later to see if any cancellations or new openings are available. </br>
   </p>
  </c:if>
 <c:if test="${DFBS_SECURITY.groupLevelAll == 'DBA' || DFBS_SECURITY.groupLevelFireBldEducation == 'USER'}">
     <a href="/dfbs/fireBldEducation/course.do?method=courseRoster&courseId=<c:out value="${course.courseId}" />">
              [class roster]  </a> </br>
    <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_bldg_code_edu_class_roster&p_course_id=<c:out value="${course.courseId}" />">
              [print class roster]  </a> </br>
  </c:if>
  
  </div>
 
  </c:forEach>
</div>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>