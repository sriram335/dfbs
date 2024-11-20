<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="update contact Email" />

<jsp:include page="/app/common/hsPageHeader.jsp" />
<div id="sideContentFluid">
 <c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>
</div>
<div id="mainBox">
<div id="mainContentFluid">

<h2>Current Contact persons </h2>
<html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateInstEmails"/> 
      <html:hidden property="courseId"/> 

  <c:forEach var="person" items="${PERSON_CONTACT_EMAIL.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">

    
    <c:out value="${person.personFirstName}" />, <c:out value="${person.personLastName}" /></a>    
    </br> <c:out value="${person.personTitle}" /><br />
  </h3>
  <p class="listingInfo">
    <c:out value="${person.personAddress1}" /><br />
    <c:if test="${person.personAddress2 != null && person.personAddress2 != ''}">
    <c:out value="${person.personAddress2}" /><br />
    </c:if>
    <c:out value="${person.personCity}" />, <c:out value="${person.personState}" /> <c:out value="${person.personZip}" /><br />
    <br />
  </p>
  Current Email:<c:out value="${person.personEmail}" /></br>
  <p class="message">
  New or Changed Email:<input type="text" name="<c:out value="${person.emsPersonId}" />" /></br>
  (Type the new Email if Email is not in database or changed)
  </p>
  </div>
 </c:forEach>
<c:if test="${(EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER' || EMS_SECURITY.currentUserLevel == 'COURSE_USER') && COURSE.courseNumber=='New'}">
<p>
   
          <html:submit value="Next" styleClass="button" />
 </p>
 </c:if>
 <c:if test="${(EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' ) && COURSE.courseNumber!='New'}">
<p>
   
          <html:submit value="Update" styleClass="button" /></br></br>
         
 </p>
 
 </c:if>
 </html:form>
</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
