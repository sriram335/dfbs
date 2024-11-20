<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<a   href="/dfbs/codeEducation/start.do">
              [back to start]</a>  
 <a href="/dfbs/codeEducation/user.do?method=userList">
              [back to user list ]  </a> </br>
   <a href="/dfbs/codeEducation/course.do?method=courseList">
              [back to course list ]  </a> </br>

<div id="mainContentFluid">

<h2>User's List</h2>



<div class="charNav">
<ul>
<c:forEach var="c" items="${USER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${USER_LIST_FILTER.type == 'byLetter' && USER_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/codeEducation/user.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> users" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="user" items="${USER_LIST.list}" varStatus="i"  >
   
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">
  User Id:<a   href="/dfbs/codeEducation/user.do?method=updateUser&userPersonId=<c:out value="${user.userPersonId}"/>"><c:out value="${user.userId}" /> </a>

  </h3>

  <p class="listingInfo">
  Name:
    <c:out value="${user.userFirstName}" /> <c:out value="${user.userLastName}" /><br />
   Phone: <c:out value="${user.userPhone}" /><br />
    <br />
  </p>
<c:if test="${SECURITY_SUPER_USER == 'YES' }">  
    <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=code_user_edu_plan&p_user_id=<c:out value="${user.userPersonId}" />">
              [print complete education plan]  </a> </br>
     <a   href="/dfbs/codeEducation/user.do?method=classList&userPersonId=<c:out value="${user.userPersonId}"/>">[Show Class List] </a>
  </c:if>
 
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
