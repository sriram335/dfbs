<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Fire and Building Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <a href="/dfbs/fireBldEducation/start.do">
              [back to main menu]  </a> </br>
    <a href="/dfbs/fireBldEducation/course.do?method=courseList">
              [back to course list ]  </a> </br>

 
<div id="mainBox">
<h2>Update New Course Information</h2>

  <div id="leftContentFluid">
   <html:form action="/course" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveCourse"/> 
      <html:hidden property="courseId"/> 
<table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required" > *course name:</th>
      <td>
      <html:text property="courseName" size="60" maxlength="100"/>(will be used in certificate)
       </td>
    </tr>
    <tr>
      <th scope="row" class="required" >* course credits</th>
      <td>
         <html:text property="county" size="30" maxlength="30"/>(will be used in certificate)
      </td>
    </tr>
     <tr>
      <th scope="row" class="required" >* course hours</th>
      <td>
         <html:text property="courseHours" size="2" maxlength="2"/>(will be used in certificate)
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > *start date:</th>
      <td>
      <html:text property="startDate" size="10" maxlength="10"/>(mm/dd/yyyy)<a href="javascript:NewCal('inspStartDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
       </td>
    </tr> 
   <tr>
      <th scope="row"  style= "width:50%"   > class duration(Timings):</th>
      <td>
      <html:text property="courseLength" size="30" maxlength="30"/>
       </td>
    </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   > class size:</th>
      <td>
      <html:text property="classSize" size="4" maxlength="4"/>
       </td>
    </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   > class location:</th>
      <td>
      <html:text property="classLocation" size="50" maxlength="50"/>
       </td>
    </tr> 
    </tr> 
      
    <tr>
      <th scope="row"  style= "width:50%"   > address1:</th>
      <td>
      <html:text property="address1" size="30" maxlength="30"/>
       </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   > address2:</th>
      <td>
      <html:text property="address2" size="30" maxlength="30"/>
       </td>
    </tr> 
  
     <tr>
      <th scope="row"  style= "width:50%"  class="required" > * city:</th>
      <td>
      <html:text property="city" size="30" maxlength="30"/>(will be used in certificate)
       </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   > zip:</th>
      <td>
      <html:text property="zip" size="5" maxlength="5"/>
       </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   > zip2:</th>
      <td>
      <html:text property="zip2" size="4" maxlength="4"/>
       </td>
    </tr> 
   
    <tr>
      <th scope="row" >state:</th>
      <td>
        <html:select property="state">
          <html:option value="IN">INDIANA</html:option>
         </html:select>
      </td>
    </tr>
       <tr>
      <th scope="row"     > course description:</th>
      <td>
      <html:textarea property="courseDescription" rows="10" cols="70" />
       </td>
    </tr> 
   <tr>
   <th scope="row" ></th>
<td>
<c:if test="${SECURITY_SUPER_USER == 'YES'}">  
 
          <html:submit value="Update" styleClass="button" /></br></br>
 </c:if>
 </td>
 </tr>
    </tbody>
   </table>
   </html:form>
   <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="userToRegister"/>  
  Last name (required)  <html:text property="userLastName" size="30" maxlength="30" disabled="false"/></br>
    <p>
    <c:if test="${USER_TO_REGISTER_ERROR == 'ERROR'}">
     <span class="error"> last name needed for search</span> 
    </c:if>
          <html:submit value="Look up User Id" styleClass="button" />
   </p>

</html:form>
 <c:forEach var="user" items="${USER_TO_REGISTER_LIST.list}" varStatus="i"  >
   
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">
  User Id:<c:out value="${user.userId}" /> 
 </h3>
 <a href="/dfbs/fireBldEducation/user.do?method=registerCourse&userId=<c:out value="${user.userId}"/>">
              [Register this user]  </a> </br>
  </div>
  </c:forEach>
  <%--
    <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="registerCourse"/> 
      <c:if test="${SECURITY_SUPER_USER == 'YES'}">  
      <b>Special Registration:</b>
       user Id / Email: <html:text property="userId" size="80" maxlength="80"/>
          <html:submit value="Register" styleClass="button" />
       </c:if>
      </html:form>  --%>
  </div>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>