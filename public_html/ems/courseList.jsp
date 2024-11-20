<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Course's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">

     

     
<div id="leftContentFluid" align="left">

<h2>Current Contact courses for the institution</h2>

    <a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ back to institution ]</a> 
     <a   href="/dfbs/ems/institution.do?method=institutionList">
              [ back to institution list ]</a> 
     <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
    <a href="/dfbs/ems/course.do?method=addNewCourse">
            [add new course]  </a> </br>
   </c:if>

  <c:forEach var="course" items="${COURSE_LIST.list}" varStatus="i" >
  
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">
    
    <html:link page="/course.do?method=courseDetail" paramId="courseId" paramName="course" paramProperty="courseId">
    <c:if test="${course.approvedDate !=null}">
    <c:out value="${course.courseNumber}" /><br />
    </c:if>
     <c:if test="${course.approvedDate ==null}">
     New Course Id: <c:out value="${course.courseId}" /><br />
     </c:if>
    </html:link>
   </h3>
  <p class="listingInfo">
    start date:<c:out value="${course.startDateString}" /><br />
    completion date:<c:out value="${course.completionDateString}" /><br />
    class location:<c:out value="${course.classLocation}" /><br />
    <a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${course.institutionId}"/>">
              <c:out value="${course.institutionName}" /></a>     
   
  </p>
  </div>
 
  </c:forEach>

</div>
 
<div class="clearer">&nbsp;</div>

</div>
<jsp:include page="/app/common/hsPageFooter.jsp" />