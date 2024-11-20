<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Code Education Student Information</h2>
 <div id="sideContentFluid">
     <a   href="/dfbs/fireBldEducation/start.do">
              [ back to start ]</a> 
<c:if test="${SECURITY_SUPER_USER == 'YES'}">  
   <a href="/dfbs/fireBldEducation/user.do?method=userList">
              [back to user list ]  </a> </br>
   <a href="/dfbs/fireBldEducation/course.do?method=courseList">
              [back to course list ]  </a> </br>
   <br/>Courses Available: <br/>
   <c:forEach var="course" items="${COURSE_LIST_REGISTER.list}" varStatus="i" >
   Course Name:<c:out value="${course.courseName}" /><br/>
   Class date:<c:out value="${course.startDateString}" /><br/>
   <c:if test="${course.classVacancy > 0}">
   <a href="/dfbs/fireBldEducation/course.do?method=registerCourse&courseId=<c:out value="${course.courseId}" />">
              [Register current user for this class]  </a> 
  <a href="/dfbs/fireBldEducation/course.do?method=cancelCourseSave&courseId=<c:out value="${course.courseId}"/>&personId=<c:out value="${VIEW_USER.userPersonId}"/>">
              [Cancel current user registration]  </a> </br>
   </c:if>
   </c:forEach>     
</c:if>
   </div>

 <div id="leftContentFluid">
 <c:if test="${SECURITY_SUPER_USER != 'YES'}">
 For updating user information like phone /fax/ address details, contact <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A>
</c:if>
 <c:if test="${CODE_EDU_USER_TYPE =='NEED_EDU_PLAN'}">
  <p class="messageBox" >

 ** Steps 1.Fill up the person information . </br>
          2.Complete Education Plan, enter at least one item for duites, training, education.</br>
          3.If you complete tha application, IDHS will look up your application and then, confirm your account.</br>
          
   
</p>
</c:if>
</br> 


 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveUser"/>   
       <html:hidden property="userPersonId"/>
       <html:hidden property="userPassword"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
   
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* user id :</th>
      <td>
       <c:out value="${VIEW_USER.userId}"/>
         <html:hidden property="userId"/>
      </td>
     </tr>
         <tr>
      <th scope="row"  style= "width:50%"   > jurisdiction / company name :</th>
      <td>
      <html:text property="userPsid" size="30" maxlength="30"/>
    </td>
     </tr>
      <tr>
      <th scope="row" >user type:</th>
      <td>
        <html:select property="userType" onchange="setSelectValue('SELECT_ABBREVIATION','ABBREVIATION')">
          <html:option value="">-----</html:option>
          <html:options collection="USER_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </td>
    </tr>
     <c:if test="${SECURITY_SUPER_USER == 'YES'}">
         
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user first name:</th>
      <td>
      <html:text property="userFirstName" size="30" maxlength="30"/>
       </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user last name:</th>
      <td>
      <html:text property="userLastName" size="30" maxlength="30"/>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%"   >*mi:</th>
      <td>
      <html:text property="userMI" size="1" maxlength="1"/>
       </td>
    </tr>
    </c:if>
    <c:if test="${SECURITY_SUPER_USER != 'YES'}">
 <tr> 
      <th scope="row"  style= "width:50%" class="required"  >*user first name:</th>
      <td>
       <c:out value="${VIEW_USER.userFirstName}"/>
         <html:hidden property="userFirstName"/>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user last name:</th>
      <td>
       <c:out value="${VIEW_USER.userLastName}"/>
         <html:hidden property="userLastName"/>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%"   >*mi:</th>
      <td>
       <c:out value="${VIEW_USER.userMI}"/>
         <html:hidden property="userMI"/>
      </td>
    </tr>
    </c:if>
     <tr>
      <th scope="row"  style= "width:50%" class="required" >*address1:</th>
      <td>
       <html:text property="userAddress1" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter user address1</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  >address2:</th>
      <td>
       <html:text property="userAddress2" size="30" maxlength="30"/>
    </td>
    </tr>
   
     <tr>
      <th scope="row"  style= "width:50%" class="required" >*user city:</th>
      <td>
       <html:text property="userCity" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter user city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
        <html:select property="userState">
          <html:option value="IN">INDIANA</html:option>
        </html:select>
      </td>
    </tr>
    <tr>
      <th scope="row" >county:</th>
      <td>
        <html:select property="userCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="USER_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${COURSE_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required" >zip:</th>
      <td>
       <html:text property="userZip" size="5" maxlength="5"/>-<html:text property="userZip2" size="4" maxlength="4"/>
       <c:if test="${USER_ERROR.userZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter user zip</span> 
        </c:if>
    </td>
    </tr>
     
    <tr>
      <th scope="row"  style= "width:50%"   >* telephone:</th>
      <td>
       <html:text property="userPhone" size="10" maxlength="10"/>
    </td>
    </tr>  
    <tr>
      <th scope="row"  style= "width:50%"  >fax:</th>
      <td>
       <html:text property="userFax" size="10" maxlength="10"/>
    </td>
    </tr>
    <c:if test="${SECURITY_SUPER_USER == 'YES'}">  
     <tr>
      <th scope="row"  style= "width:50%"  >status:</th>
      <td>
       <html:text property="userStatus" size="1" maxlength="1"/>
    </td>
    </tr>
     <tr>
     <th scope="row"  style= "width:50%"   ></th>
     <td>
              <html:submit value="update" styleClass="button" />
     </td>
     </tr> 
     </c:if>
     
</tbody>
</table>
  </html:form>  
   
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
