<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>User and Class Registration</h2>
   <div id="sideContentFluid" align="left">
   <a  href="/dfbs/fireBldEducation/user.do?method=classList&userPersonId=<c:out value="${VIEW_USER.userPersonId}"/>">[Show Registered Class List / Print Certificate(s)] </a>
  <br/>Courses Available: <br/>
   <c:forEach var="course" items="${COURSE_LIST_REGISTER.list}" varStatus="i" >
   Course Name:<c:out value="${course.courseName}" /><br/>
   Class date:<c:out value="${course.startDateString}" /><br/>
   <c:if test="${course.classVacancy > 0}">
   <a href="/dfbs/fireBldEducation/course.do?method=registerCourse&courseId=<c:out value="${course.courseId}" />">
              [Register for this class]  </a> </br>
   </c:if>
   </c:forEach>
   </div>
<div id="mainBox">
 <c:if test="${VIEW_USER !=null}">
  <html:link page="/user.do?method=changePassword">[change password]</html:link>
  <html:link page="/user.do?method=logOut">[log out]</html:link>
  </c:if>
  <div id="leftContentFluid">
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
  <tbody class="rowHeader" >
  <html:form action="/user" method="post">
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
    <th > user type :</th>
    <td>
    <c:out value="${sessionScope.VIEW_USER.userType}"/>
    </td>
    </tr>
     <tr>
      <th  > user id :</th>
      <td>
      <c:out value="${sessionScope.VIEW_USER.userId}"/>
      </td>
    </tr>
    <tr>
      <th  > user organization / company name :</th>
      <td>
      <c:out value="${sessionScope.VIEW_USER.userPsid}"/>
      </td>
    </tr>
 <tr>
      <th  > user first name:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userFirstName}"/>
      </td>
    </tr>
 <tr>
      <th > user last name:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userLastName}"/>
      </td>
    </tr>
   <tr>
      <th  > mi:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userMI}"/>
      </td>
    </tr>
     <tr>
      <th  > address1:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userAddress1}"/>
      </td>
    </tr>
     <tr>
      <th  > address2:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userAddress2}"/>
      </td>
    </tr>
   <tr>
      <th  > city:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userCity}"/>
      </td>
    </tr>
    <tr>
      <th  > state:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userState}"/>
      </td>
    </tr>
    <tr>
      <th  > county:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userCounty}"/>
      </td>
    </tr>
    <tr>
      <th  > zip:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userZip}"/>
      </td>
    </tr>
     <tr>
      <th  > zip2:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userZip2}"/>
      </td>
    </tr>
    <tr>
      <th  > telephone:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userPhone}"/>
      </td>
    </tr>
    <tr>
      <th  > fax:</th>
      <td>
       <c:out value="${sessionScope.VIEW_USER.userFax}"/>
      </td>
    </tr>
     </html:form>
      <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateUser"/>   
       <html:hidden property="userPersonId" value="${sessionScope.VIEW_USER.userPersonId}" />
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="View User Details" styleClass="button" />
     </td>
     </tr> 
     </html:form>
      
      <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="courseListUser"/>  
      <html:hidden property="userPersonId" value="${sessionScope.VIEW_USER.userPersonId}" />
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="Register for Class" styleClass="button" />
                            
     </td>
     </tr> 
      </html:form>
       <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="cancelCourse"/>   
      <html:hidden property="userPersonId" value="${sessionScope.VIEW_USER.userPersonId}" />
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="View / Cancel my class" styleClass="button" />
             
     </td>
     </tr> 
      </html:form>
   
</tbody>
    </table>
     
    </div>
   
    </div>
    <jsp:include page="/app/common/hsPageFooter.jsp"/>