<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Update User"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Update User Information</h2>
  <a   href="/dfbs/ems/main.do?method=showNewUserList">
              [ View New Users ]</a>       
  <a   href="/dfbs/ems/main.do?method=showAllUserList">
              [ View All users ]</a>    
   <a   href="/dfbs/ems/main.do?method=emailNewUser&userId=<c:out value="${EMS_EDIT_USER.userId}"/>&password=<c:out value="${EMS_EDIT_USER.userPassword}"/>">
              [ Email User Id and Password ]</a> 
    <a   href="/dfbs/ems/main.do?method=goProviderUser">
              [ add providers to this user ]</a>
     <a   href="/dfbs/ems/main.do?method=goHospitalUser">
              [ add hospitals to this user ]</a> 
     <a   href="/dfbs/ems/main.do?method=goInstitutionUser">
              [ add institutions to this user ]</a> 
 <div id="sideContentFluid">
     <a   href="/dfbs/ems/main.do?method=authUser">
              [ Cancel ]</a>   
  
  </div>
  <div id="leftContentFluid">

 <html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveApprovedUsers"/> 
       <html:hidden property="securityId"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* user id (office email address is only acceptable user Id):</th>
      <td>
       <html:text property="userId" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userId == 'ERROR'}">
          <br/>
          <span class="error">* please enter userId (official email)</span> 
        </c:if>
      </td>
     
    </tr>
  <tr>
      <th scope="row"  style= "width:50%"   > user password :</th>
      <td>
      <c:if test="${sessionScope.EMS_EDIT_USER.userLevel == null || sessionScope.EMS_EDIT_USER.userLevel == 'COURSE_USER' || sessionScope.EMS_EDIT_USER.userLevel == 'USER'}">
       <html:text property="userPassword" size="10" maxlength="10"/>
      </td>
     </c:if>
       <c:if test="${sessionScope.EMS_EDIT_USER.userLevel != 'COURSE_USER' && sessionScope.EMS_EDIT_USER.userLevel != 'USER'}">
         <html:hidden property="userPassword"/> Hidden
      </td>
     </c:if>
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user first name:</th>
      <td>
       <html:text property="userFirstName" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userFirstName == 'ERROR'}">
          <br/>
          <span class="error">* please enter user first name</span> 
        </c:if>
      </td>
     
    </tr>
 <tr>
      <th scope="row"  style= "width:50%" class="required"  >*user last name:</th>
      <td>
       <html:text property="userLastName" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userLastName == 'ERROR'}">
          <br/>
          <span class="error">* please enter user last name</span> 
        </c:if>
      </td>
     
    </tr>
   <tr>
      <th scope="row"  style= "width:50%" class="required"  >*title:</th>
      <td>
       <html:text property="userTitle" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userTitle == 'ERROR'}">
          <br/>
          <span class="error">* please enter user title</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >*office telephone(only accepted):</th>
      <td>
       <html:text property="userPhone" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userPhone == 'ERROR'}">
          <br/>
          <span class="error">* please enter user telephone</span> 
        </c:if>
      </td>
     
    </tr>  
   
 
 <%-- to get the additional fields for admininstrator --%>
   <c:if test="${sessionScope.EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR'}">
  <tr>
      <th scope="row"  style= "width:50%"   >*user level:</th>
      <td>
        <html:text property="userLevel" size="30" maxlength="30" readonly="true"/>
        <c:if test="${USER_ERROR.userLevel == 'ERROR'}">
          <br/>
          <span class="error">* please enter user level</span> 
  </c:if>
      </td>
     </tr>
      <tr>
      <th scope="row"  style= "width:50%"  class="required" >* user status:</th>
      <td>
       <html:text property="userStatus" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userStatus == 'ERROR'}">
          <br/>
          <span class="error">* please enter user Status</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" >* password expires date:</th>
      <td>
       <html:text property="passwordExpiresDate" size="30" maxlength="30"/>
        <c:if test="${USER_ERROR.userLevel == 'ERROR'}">
          <br/>
          <span class="error">* please enter expiration date</span> 
        </c:if>
      </td>
     </tr>
      <tr>
      <th scope="row">user creation date:</th>
      <td>
        <c:out value="${EMS_SECURITY.recordCreatedDate}"/>
      </td>
      </tr>
       <tr>
      <th scope="row">user created by:</th>
      <td>
        <c:out value="${EMS_SECURITY.recordCreatedBy}"/>
      </td>
      </tr>
 </c:if>
<%-- to get the additional fields for admininstrator --%>
<tr>
<td>
<p>
   
          <html:submit value="Save changes" styleClass="button" />
 </p>
 </td>
 </tr>
 
 <tr>
<td>

 <h2>User authorized to work on</h2>
  <c:forEach var="detail" items="${EMS_EDIT_USER.detailList}" varStatus="i" >
  <tr>
  <th scope="row"> </th>
  <td>
  <c:out value="${detail.providerType}"/>:
  <c:out value="${detail.providerName}"/></th>
   <a   href="/dfbs/ems/main.do?method=removeProviderUser&detailId=<c:out value="${detail.detailId}"/>">
              [ Remove this authorization ]</a>  
  </td>
  </tr>
   </c:forEach>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
