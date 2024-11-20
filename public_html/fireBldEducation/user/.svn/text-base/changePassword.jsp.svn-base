<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Password Recovery"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Password request</h2>
<a   href="/dfbs/fireBldEducation/start.do">
  [ back to start ]</a>               
  <div id="leftContentFluid">
 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveChangedPassword"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    <tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user old password:</th>
      <td>
        <input type="password" name="userPassword" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userPassword == 'ERROR_SAMEPASSWORD'}">
          <br/>
          <span class="error">* you can not use the same password as old</span> 
        </c:if>
         <c:if test="${USER_ERROR.userPassword == 'ERROR_NOTOLDPASSWORD'}">
          <br/>
          <span class="error">* old password not correct</span> 
        </c:if>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%" class="required"  >*type new password( min 6 characters):</th>
      <td>
        <input type="password" name="newPassword" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.newPassword == 'ERROR_LENGTH'}">
          <br/>
          <span class="error">*new password length min of 6 characters</span> 
        </c:if>
      </td>
     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >*retype new password:</th>
      <td>
        <input type="password" name="retypeNewPassword" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.retypeNewPassword == 'ERROR_NOTEQUAL'}">
          <br/>
          <span class="error">* new password retype does not match new password</span> 
        </c:if>
      </td>
     
    </tr>
<tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Change Password" styleClass="button" />
</td>
</tr> 
<tr>
<th scope="row"  style= "width:100%" class="required"  ></th>
<td>
 <c:if test="${EMS_SECURITY.userId == 'ERROR'}">
          <br/>
          <span class="error">* user email does not match with data in database contact rstump@dhs.in.gov with details</span> 
</c:if>
 </td>
</tr> 
  </tbody>
  </table>
 </html:form>  
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>