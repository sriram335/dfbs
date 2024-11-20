<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Log in"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<div id="mainBox">
 

  <div id="leftContentFluid">
   <h2>Log in to DFBS System</h2>
 <html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="loginSystem"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    <tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user id:</th>
      <td>
       <html:text property="userId" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userId == 'ERROR'}">
          <br/>
          <span class="error">* please enter user email</span> 
        </c:if>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user password:</th>
      <td>
     <%--   <html:text property="userPassword" size="30" maxlength="30"> --%>
          <input type="password" name="userPassword" size="30" maxlength="30"/>
      <%--  </html:text> --%>
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
          <br/>
    <c:if test="${DFBS_LOGIN_ERROR!=null && DFBS_LOGIN_ERROR== 'ERROR'}">
          <span class="error">* user name and/or password does not match with data in database</span> 
    </c:if>
  <html:link page="/logOn.do?method=emailPasswordPage">[Forgot User Name /Password]</html:link>
   <c:if test="${DFBS_LOGIN_ERROR == 'EMAIL_SENT'}">
          <br/>
          <span class="error">* email sent to the user address</span> 
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
