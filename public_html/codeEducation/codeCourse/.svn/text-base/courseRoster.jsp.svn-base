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
<a href="/dfbs/codeEducation/user.do?method=manageUser">
              [back to user]  </a> </br>

<div id="mainContentFluid">

<h2>Course Roster for <c:out value="${COURSE_SELECTED.courseName}" /></h2>


 <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateRoster"/> 
  <c:forEach var="user" items="${COURSE_ROSTER.list}" varStatus="i"  >
   
  <div class="listing" align="left">
 

  <p class="listingInfo">
  Name:
    <c:out value="${user.userFirstName}" /> <c:out value="${user.userLastName}" /><br />
   Phone: <c:out value="${user.userPhone}" /><br />
   Email: <c:out value="${user.userId}" /><br />
   <c:if test="${user.userStatus =='SHOW'}">
   No show: <input type="checkbox" name="<c:out value='${user.userPersonId}' />" class="switch"/>
   </c:if>
   <c:if test="${user.userStatus =='NO_SHOW'}">
   No show: <input type="checkbox" name="<c:out value='${user.userPersonId}' />" class="switch" checked/>
   </c:if>
    <br />
  </p>

 
  </div>
 
  </c:forEach>
   <td>
              <html:submit value="Update Roster" styleClass="button" />
             
     </td>
 </html:form>
</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
