<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Log in"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h3>Log in to Code Education System</h3>

  <div id="leftContentFluid">
 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="loginSystem"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    <tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user id:</th>
      <td>
       <html:text property="userId" size="50" maxlength="80"/>
        <c:if test="${USER_ERROR.userId == 'ERROR'}">
          <br/>
          <span class="error">* please enter user email</span> 
        </c:if>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user password:</th>
      <td>
          <input type="password" name="userPassword" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userPassword == 'ERROR'}">
          <br/>
          <span class="error">* please enter user pass word</span> 
        </c:if>
      </td>
     
    </tr>
 <tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Login to  account" styleClass="button" />
</td>
</tr> 


<tr>
<th scope="row"  style= "width:100%" class="required"  ></th>
<td>
     <c:if test="${PASSWORD_STATUS == 'EMAIL_SENT'}">
          <br/>
          <span class="message">* email sent to the user address</span> 
  </c:if>
          <span class="error">* If you forget password use the following link to recover password.</span> 
  <html:link page="/user.do?method=emailPasswordPage">[Forgot Password]</html:link></br>
          <span class="error">* If you forget user id use the following link to recover password.</span> 
  <html:link page="/user.do?method=forgotUserId">[Forgot userId]</html:link>
 </td>
</tr> 
  </tbody>
  </table>
 </html:form>  
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>
