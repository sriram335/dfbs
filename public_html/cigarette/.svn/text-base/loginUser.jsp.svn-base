<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="log in screen"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Log in to Online Cigarette Application System</h2>

  <div id="leftContentFluid">
 <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="loginSystem"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    <tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user id:</th>
      <td>
       <html:text property="userLoginId" size="50" maxlength="50"/>
       </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user password:</th>
      <td>
          <input type="password" name="userPassword" size="10" maxlength="10"/>
        <c:if test="${USER_ERROR != null}">
          <br/>
          <p class="error">
          <c:out value="${sessionScope.USER_ERROR}"/> </p>
        </c:if>
      </td>
     
    </tr>
 <tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Login to  account" styleClass="button" />
</td>
</tr> 

  </tbody>
  </table>

 </html:form>  
  <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="emailPassword"/>
 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
     <th style="color:rgb(198,198,198)" >_____________________________</th>
    <tr>
     <tr>
      <th scope="row"  ><b><u> Recover Password</b></u></th>
      <td>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user Login id:</th>
      <td>
       <html:text property="userLoginId" size="50" maxlength="50"/>
       </td>
     
    </tr>
     <tr>
 <th scope="row"  style= "width:100%"  ></th>
<td>
   
          <html:submit value="Email password" styleClass="button" />
</td>
</tr> 

  </tbody>
  </table>
 <b><u> Authorized users for this company</br></b></u>
  <c:forEach var="user" items="${CIGARETTE_COMPANY.users}" varStatus="i" >
  <c:out value="${user.userLoginId}" /><br />
  
  </c:forEach>
  <p class="message" >If you have forgotten your password enter your user id in user id box and click email password button.
  Your password will be mailed to email account.</p>
 </html:form>  
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>
