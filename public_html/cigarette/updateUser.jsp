<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Cigarette Brand Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">

<a   href="/dfbs/cigarette/cigUser.do?method=userList">
              [back to user list]</a>
  <div class="styledBox">
 <div id="mainContentFluid" align="left">

  
 <h2> Edit and Update Cigarette Comany Users</h2> 
 <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY2" value="saveUpdateUser"/> 
      <html:hidden property="userId"/> 
      
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    <td>
    
      </td>
    </tr>
    
    <tr>
      <th scope="row"  class="required"    > * user login id  :</th>
      <td>
     <html:text property="userLoginId" size="50" maxlength="50"/>
      <c:if test="${CIGARETTE_USER_ERROR.userLoginId == 'ERROR'}">
          <br/>
          <span class="error">* please specify login id</span> 
        </c:if> 
        </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > password :</th>
      <td>
       <html:text property="userPassword" size="10" maxlength="10"/>
       </td>
       <c:if test="${CIGARETTE_USER_ERROR.userPassword == 'ERROR'}">
          <br/>
          <span class="error">* please specify login id</span> 
        </c:if> 
    </tr>
    <tr>
      <th scope="row"      > password expiration date :</th>
      <td>
       <html:text property="userPasswordExpiresDate" size="10" maxlength="10"/>
       </td>
        <c:if test="${CIGARETTE_USER_ERROR.userPasswordExpiresDate == 'ERROR'}">
          <br/>
          <span class="error">* please specify login id</span> 
        </c:if> 
    </tr>
     <tr>
      <th scope="row"      > last name :</th>
      <td>
       <html:text property="userLastName" size="30" maxlength="30"/>
       </td>
    </tr>
     <tr>
      <th scope="row"      > first name :</th>
      <td>
       <html:text property="userFirstName" size="30" maxlength="30"/>
       </td>
    </tr>
    
     <tr>
      <th scope="row"      > status(enter I or A) :</th>
      <td>
       <html:text property="userStatus" size="2" maxlength="1"/>
       </td>
    </tr>
     
     <tr>
      <th scope="row"      > phone :</th>
      <td>
       <html:text property="userTelephone" size="10" maxlength="10"/>
       </td>
    </tr>
    
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save User" styleClass="button" />
      <c:if test="${CIGARETTE_UPDATE_USER_STATUS == 'Y'}">
      <p class="message"> User information updated </p>
      </c:if>
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
   </div>
 
 </div>  
  <div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>


