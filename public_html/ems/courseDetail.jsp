<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Course Details"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:if test="${COURSE.crssCourseId==150012}">
 <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=Course Approval checklist--First Responder.doc&fileType=.doc&fileId=234" target="_blank">
             [view and print course agreement document]</a></br> 
</c:if>
<c:if test="${COURSE.crssCourseId==150013}">
 <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=Course Approval checklist--Paramedic.doc&fileType=.doc&fileId=232" target="_blank">
             [view and print course agreement document]</a></br> 
</c:if>
<c:if test="${COURSE.crssCourseId==150015}">
 <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=Course Approval checklist--Basic.doc&fileType=.doc&fileId=236" target="_blank">
             [view and print course agreement document]</a></br> 
</c:if>
<c:if test="${COURSE.crssCourseId==150019}">
 <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=Course Approval checklist--Basic Advanced.doc&fileType=.doc&fileId=237" target="_blank">
             [view and print course agreement document]</a></br> 
</c:if>
<c:forEach var="file" items="${sessionScope.COURSE.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>
 
     <h2>current staff for course</h2>
   <c:if test="${COURSE.instructorCount > 0}"> 
    </br><a   href="/dfbs/ems/instructor.do?method=otherStaff">
              [ add other staff ]</a> 
   </c:if>
   <c:forEach var="instructor" items="${COURSE.instructors}" varStatus="i" >
  <tr>
  <th scope="row"  style= "width:50%"   ></th>
  <td>
  <div class="listing">
  <b>
    
    <html:link page="/instructor.do?method=instructorDetail" paramId="personId" paramName="instructor" paramProperty="personId">
    <c:out value="${instructor.personLastName}" /> <c:out value="${instructor.personFirstName}" /><br />
    </html:link>
   </b>
     <c:out value="${instructor.personType}" />
    <c:out value="${instructor.personCity}" />
  </div>
 </td>
 </tr>
  </c:forEach>
  <c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>
</div>
<div id="mainBox">
 <c:if test="${COURSE.courseId !=0}">
<a   href="/dfbs/ems/course.do?method=courseList&institutionId=<c:out value="${sessionScope.COURSE.institutionId}"/>">
              [ course list ]</a> 
     <a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${sessionScope.COURSE.institutionId}"/>">
              [ back to institution ]</a> 
</c:if>
     <a   href="/dfbs/ems/institution.do?method=institutionList">
              [ back to institution list ]</a> 
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
    <c:if test="${COURSE.courseId !=0}">
    
      <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.COURSE.courseId}"/>&idType=Course">
            [upload course files]  </a> 
      </c:if>
      </c:if>
     <a href="/dfbs/ems/main.do?method=authUser">
              [back to main menu]  </a> </br>
 <%--  temporarily disabled <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="registerForCourse"/> 
     <p>
           <html:submit value="Register for this Course" styleClass="button" />
   </p>
 </html:form> --%>
  
  <h2>Add / update Course Information</h2>

             

  <div id="leftContentFluid">
  

 <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveCourse"/> 
      <html:hidden property="courseId"/> 
       <html:hidden property="institutionId"/> 
       <html:hidden property="crssCourseId"/>
       <html:hidden property="receivedDate"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > course number:</th>
      <td>
       <c:if test="${COURSE.approvedDate !=null}">
      <c:out value="${COURSE.courseNumber}" />
      <html:hidden property="courseNumber"/>
       </c:if> 
        <c:if test="${COURSE.approvedDate ==null}">
      New Course
       </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > *day class meets:</th>
      <td>
       <html:text property="dayClassMeet" size="20" maxlength="20"/>
         <c:if test="${COURSE_ERROR.dayClassMeet == 'ERROR'}">
          <br/>
          <span class="error">* please select day class meets</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" >* class time:</th>
      <td>
       <html:text property="dayClassTime" size="10" maxlength="10"/>
         <c:if test="${COURSE_ERROR.dayClassTime == 'ERROR'}">
          <br/>
          <span class="error">* please select class start time</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > * start date:</th>
      <td>
       <html:text property="startDate" size="10" maxlength="10"/>(mm/dd/yyyy)
        <c:if test="${COURSE_ERROR.startDate == 'ERROR'}">
          <br/>
          <span class="error">* please select start date, which is atleast one month into future</span> 
        </c:if> 
      </td>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  >* completion date:</th>
      <td>
       <html:text property="completionDate" size="10" maxlength="10"/>(mm/dd/yyyy)
        <c:if test="${COURSE_ERROR.completionDate == 'ERROR'}">
          <br/>
          <span class="error">* please select completion date , which is atleast one month into future</span> 
        </c:if> 
      </td>
    </tr>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > *course length:</th>
      <td>
       <html:text property="courseLength" size="10" maxlength="10"/>
        <c:if test="${COURSE_ERROR.courseLength == 'ERROR'}">
          <br/>
          <span class="error">* please select course length</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:100%" class="required"  > * course name:</th>
      <td>
       <html:select property="crssCourseId" styleId="SELECT_COURSE" onchange="setSelectValue('SELECT_COURSE','COURSE_ID')" disabled="true">
          <html:option value="">Please Select</html:option>
          <html:options collection="COURSE_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${COURSE_ERROR.crssCourseId == 0}">
          <br/>
          <span class="error">* please select course name</span> 
        </c:if> 
       </td>
    </tr>
    
    <%-- <tr>
      <th scope="row"  style= "width:50%" class="required"  > * received date:</th>
      <td>
       <html:text property="receivedDate" size="10" maxlength="10" disabled="true"/>
        <c:if test="${COURSE_ERROR.receivedDate  == 'ERROR'}">
          <br/>
          <span class="error">* please select received date</span> 
        </c:if> 
      </td>
    </tr> --%>
     <tr>
      <th scope="row"  style= "width:50%"  class="required" > * physical class room locaton:</th>
      <td>
       <html:text property="classLocation" size="30" maxlength="30"/>
        <c:if test="${COURSE_ERROR.classLocation == 'ERROR'}">
          <br/>
          <span class="error">* please enter class location</span> 
        </c:if> 
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > class address1:</th>
      <td>
       <html:text property="address1" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > class address2:</th>
      <td>
       <html:text property="address2" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  > class city:</th>
      <td>
       <html:text property="city" size="30" maxlength="30" />
       <c:if test="${COURSE_ERROR.city  == 'ERROR'}">
          <br/>
          <span class="error">* please enter city</span> 
        </c:if> 
      </td>
    </tr>
     <tr>
      <th scope="row" >state:</th>
      <td>
        <html:select property="state">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="COURSE_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
     
       
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > completed report:</th>
      <td>
       <html:text property="compReport" size="30" maxlength="30"/>
      </td>
    </tr>
    
    
    <tr>
      <th scope="row" class="required">*county:</th>
      <td>
        <html:select property="county" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="COURSE_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${COURSE_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   class="required">  instructor phone:</th>
      <td>
       <html:text property="telephone" size="10" maxlength="10"/>
       <c:if test="${COURSE_ERROR.telephone == 'ERROR'}">
          <br/>
          <span class="error">* please enter phone number</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required"  > instructor email:</th>
      <td>
       <html:text property="email" size="100" maxlength="100"/>
       <c:if test="${COURSE_ERROR.email == 'ERROR'}">
          <br/>
          <span class="error">* please enter email</span> 
        </c:if>
      </td>
    </tr>
   <c:if test="${EMS_SECURITY.currentUserLevel != 'ADMINISTRATOR'}">
   <tr>
      <th scope="row"  style= "width:50%"   > approved date:</th>
      <td>
       <c:out value="${COURSE.approvedDateString}" />
        <html:hidden property="approvedDate"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > pending items:(255 char max)</th>
      <td>
       <c:out value="${COURSE.pendingItems}" />
        <html:hidden property="pendingItems"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > completed report:</th>
      <td>
        <c:out value="${COURSE.compReport}" />
         <html:hidden property="compReport"/>
      </td>
    </tr>
    </c:if>
    <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR'}">
   <tr>
      <th scope="row"  style= "width:50%"   > approved date:</th>
      <td>
       <c:out value="${COURSE.approvedDateString}" />
        <html:hidden property="approvedDate"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > pending items:(255 char max)</th>
      <td>
       <html:textarea property="pendingItems" />
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > completed report:</th>
      <td>
       <html:text property="compReport" size="30" maxlength="30"/>
      </td>
    </tr>
    </c:if>
     <tr>
      <th scope="row"  style= "width:50%"   > course created by:</th>
      <td>
       <c:out value="${COURSE.recCreatedBy}" />
        <html:hidden property="recCreatedBy"/></br></br>
      </td>
    </tr>
   
    <tr>
     <th scope="row"  style= "width:50%"   > </th>
<td>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
<p>
          <html:submit value="Next" styleClass="button" />
 </p>
 </c:if>
 <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null)}">
<p>
   
          <html:submit value="Update" styleClass="button" /></br></br>
         
 </p>
 
 </c:if>
 </td>
 </tr>
  
   </tbody>
   </table>
   </html:form>
   
      <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="courseApprove"/> 
    <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null) && COURSE.approvedDate ==null && COURSE.courseId != 0}"> 
         <html:submit value="Approve this course" styleClass="button" /></br> </br>
       </c:if>
    
  </html:form>
  <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="courseCondApprove"/> 
    <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null) && COURSE.approvedDate ==null && COURSE.courseId != 0}">
         <html:submit value="Conditional Approval" styleClass="button" /></br> 
      </c:if>
  </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>


