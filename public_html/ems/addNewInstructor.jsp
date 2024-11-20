<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="Ems operations"/>
<c:set var="title" scope="request" value=" Add Instructors"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<div id="sideContentFluid">
<c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>
   </div>  
  <div id="leftContentFluid"> 
  <c:if test="${COURSE.instructorCount > 0}"> 
    </br><a   href="/dfbs/ems/instructor.do?method=goToNewInstructor">
              [ add other staff ]</a> 
   </c:if>
  <h2 style="font-weight:bold;">Add Instructor  </h2>
 
 <html:form action="/instructor" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInstructor"/>  
          <html:hidden property="personId"/> 
<table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required" > * last name:</th>
      <td>
      <html:text property="personLastName" size="30" maxlength="30"/>
        <c:if test="${INSTRUCTOR.personLastName =='ERROR'}">
      <span class="error">* please select enter last name</span> 
       </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" >* first name:</th>
      <td>
       <html:text property="personFirstName" size="30" maxlength="30"/>
        <c:if test="${INSTRUCTOR.personFirstName =='ERROR'}">
      <span class="error">* please select enter first name</span> 
       </c:if> 
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" >* psid:</th>
      <td>
       <html:text property="psId" size="10" maxlength="10"/>
         <c:if test="${INSTRUCTOR.psId == 'ERROR'}">
          <br/>
          <span class="error">* please select class start time</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > * instructor type:</th>
      <td>
       <html:select property="personType" styleId="SELECT_PERSON_TYPE" onchange="setSelectValue('SELECT_PERSON_TYPE','DESCRIPTION')">
          <html:option value="">Select Instructor Type</html:option>
          <html:options collection="INSTRUCTOR_PERSON_TYPE" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${INSTRUCTOR.personType == 'ERROR'}">
          <br/>
          <span class="error">* please specify peson type</span> 
        </c:if> 
      </td>
    <tr>
     <th scope="row"  style= "width:50%"  class="required" ></th>
<td>

 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Save" styleClass="button" />
 </p>
 </c:if>
 
 </td>
 </tr>
  
   </tbody>
   </table>
</html:form>

  </div>
 
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>