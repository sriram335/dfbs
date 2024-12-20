<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <div id="sideContentFluid">
 <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="emailCerts"/> 
      <td>
              <html:submit value="Email Certificates" styleClass="button" />
             
     </td>
 </html:form>
 <b><u>Please Verify the Certification Approval Email List</u></b>
    <c:forEach var="user" items="${USER_EMAIL_LIST.list}" varStatus="i"  >
    <p class="listingInfo">
  Name:
    <c:out value="${user.userFirstName}" />, <c:out value="${user.userLastName}" /><br />
        <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_bldg_class_certificate.rdf&p_course_Id=<c:out value="${COURSE_SELECTED.courseId}"/>&p_user_Id=<c:out value="${user.userPersonId}" />" target="_blank" color="blue"><b>[Print Certificate]</b></a></br>
    <br />
  </p>
 </c:forEach>
  </div>  
<div id="mainBox">
<a   href="/dfbs/fireBldEducation/start.do">
              [back to start]</a>  
<a href="/dfbs/fireBldEducation/user.do?method=manageUser">
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
   <a  href="/dfbs/fireBldEducation/course.do?method=emailCertSingle&userPersonId=<c:out value="${user.userPersonId}"/>">Email Certificate </a>
   </c:if>
   <c:if test="${user.userStatus =='NO_SHOW'}">
   No show: <input type="checkbox" name="<c:out value='${user.userPersonId}' />" class="switch" checked/>
   </c:if>
    <br />
  </p>

 
  </div>
 
  </c:forEach>
   <td>
              <html:submit value="Update Roster & View User Cert. List" styleClass="button" />
             
     </td>
 </html:form><br/><br/><br/>
 <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="emailClassCancel"/> 
      <html:hidden property="courseId"  value="${COURSE_SELECTED.courseId}" />
      <td>
      Class Cancel Reason: <html:textarea property="courseDescription" rows="10" cols="70" />
              <html:submit value="Cancel Class & Email Roster Persons" styleClass="button" />
             
     </td>
 </html:form>
</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
