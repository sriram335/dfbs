<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Person's List" />


<div id="mainBox">
<div id="sideContentFluid">

               
</div>
    
<div id="mainContentFluid">
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
<h2>Persons available </h2>
</c:if>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
  <h2>Persons / Instructor available </h2>

</c:if>
 


  <c:forEach var="instructor" items="${INSTRUCTOR_LIST_LOOKUP.list}" varStatus="i" >
  
     
  <div class="listing">
  <h3 style="margin-bottom:5px;">
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=acadis_person_detail.rdf&p_personid=<c:out value="${instructor.personId}"/>" target="_blank" >[view details]</a>
  </br> <c:out value="${instructor.personFirstName}" />  <c:out value="${instructor.personLastName}" /></a>    
   </br> <c:out value="${instructor.personCity}" /></br>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
    <a href="/dfbs/ems/instructor.do?method=addNewInstructor&personId=<c:out value="${instructor.personId}"/>">
    [add this instructor]  </a> </br>
    <a href="/dfbs/ems/instructor.do?method=instructorDetail&personId=<c:out value="${instructor.personId}"/>">
    [look up details of this person]  </a>  
  </c:if> 
 
  </div>
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
