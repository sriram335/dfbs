<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="New User Registration"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Apply for registration</h2>
 <div id="sideContentFluid">
     <a   href="/dfbs/ems/main.do?method=verifySecurity">
              [ Cancel ]</a>     
 <c:if test="${EMS_NEW_USER.userId != null}">
  <h2>User requests authorization to work on</h2>
  <c:forEach var="detail" items="${EMS_NEW_USER.detailList}" varStatus="i" >
  <tr>
  <th scope="row"> </th>
  <td>
  <b><c:out value="${detail.providerType}"/></b> :
  <c:out value="${detail.providerName}"/></th></br>
   </td>
  </tr>
   </c:forEach>
  </c:if>
  </div>
 <div id="leftContentFluid">
  <p class="messageBox" size="150">
 ** Steps 1.Fill up the registration information and submit request for new account. </br>
          2.Request edit privileges to hospital/provider/institution.</br>
    Note: privileges request does not grant automatic edit rights, your 
    request will be processed and rights will be granted by IDHS-EMS division.
</p>
 </br> 

<c:if test="${EMS_NEW_USER.userId != null}">
  <c:if test="${sessionScope.NEW_USER_ACCOUNT_TYPE == 'USER' }">
  <a   href="/dfbs/ems/main.do?method=goProviderUser">
              [ request providers edit privileges ]</a>
     <a   href="/dfbs/ems/main.do?method=goHospitalUser">
              [ request  hospitals edit privileges ]</a> 
  </c:if>             
    <a   href="/dfbs/ems/main.do?method=goInstitutionUser">
              [ request institutions edit privileges ]</a> 

</c:if>
<c:if test="${EMS_NEW_USER.userId != null}">
  <c:if test="${sessionScope.NEW_USER_ACCOUNT_TYPE == 'COURSE_USER'}">
     
    <a   href="/dfbs/ems/main.do?method=goInstitutionUser">
              [ request institutions adding course privileges ]</a> 

</c:if>
</c:if>
 <html:form action="/main" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addNewUser"/>   
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
       <html:text property="userPhone" size="30" maxlength="30"/>(enter just number  no'-' or '()')
        <c:if test="${USER_ERROR.userPhone == 'ERROR'}">
          <br/>
          <span class="error">* please enter user telephone</span> 
        </c:if>
      </td>
     
    </tr>  
   
  <tr>
      <th scope="row"  style= "width:50%"   > comments for this account request:</th>
      <td>
       <html:textarea property="comments" />
        <c:if test="${USER_ERROR.comments == 'ERROR'}">
          <br/>
          <span class="error">* please enter comments</span> 
        </c:if>
      </td>
    </tr>
 <tr>
 <td>
<p>
          <html:submit value="Submit request for new account" styleClass="button" />
 </p>
 </td>
</tr> 



  </html:form>  
   </tbody>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>
