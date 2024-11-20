<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">


  <div class="styledBox">
 <div id="mainContentFluid" align="left">
 <c:if test="${NEW_USER == 'Y'}">
  <h2>Request New User Acccount</h2> 
 <html:form action="/otherUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY2" value="saveNewUser"/> 
      <html:hidden property="userId"/> 
      <html:hidden property="userCounty"/> 
      <html:hidden property="FDId"/>
      <html:hidden property="FDName"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    <td>
    
      </td>
    </tr>
    
    <tr>
      <th scope="row"  class="required"    > * user login id(your official email)  :</th>
      <td>
     <html:text property="userLoginId" size="50" maxlength="50"/>
      <c:if test="${CIGARETTE_USER_ERROR.userLoginId == 'ERROR'}">
          <br/>
          <span class="error">* please specify login id</span> 
        </c:if> 
        </td>
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
      <th scope="row"      > phone :</th>
      <td>
       <html:text property="userPhone" size="10" maxlength="10"/>
       </td>
    </tr> 
  <%--    <tr>
      <th scope="row" class="required">*county:</th>
      <td>
        <html:select property="userCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.countyId == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if>
      </td>
    </tr>
    --%>
     
     <tr>
     <th scope="row"     > </th>
    <td>
      <c:if test="${INSERT_OTHER_USER_STATUS != 'Y'}">
      <html:submit value="Submit New User Request" styleClass="button" />
       </c:if>
     <c:if test="${INSERT_OTHER_USER_STATUS == 'Y' && INSERT_OTHER_USER_WARNING != 'Y'}">
     <p class="messageBox">Your NEW user request is submitted. IDHS will contact you about your request by tomorrow.If you do not hear back from IDHS please contact acronnon@dhs.in.gov</p>
     
     </c:if>
      <c:if test="${INSERT_OTHER_USER_WARNING == 'Y'}">
     <p class="messageBox"><font color="red"> Fire Department has two authorized users(given below). For security we can not add more than 2 users. 
     If you want this user to be added, Local Fire Chief is requested to send a email to acronnon@dhs.in.gov with information about
     which user(listed below) has to be inactivated. Only when we inactivate one user, we will activate this new user take in-activated user place.</font> </p>
     </c:if>
       <c:if test="${INSERT_OTHER_USER_WARNING == 'E'}">
     <p class="messageBox"><font color="red"> This user exists in database. Please contact acronnon@dhs.in.gov with user name, login id details to resolve the issue.</font> </p>
     </c:if>
     </td>
     </tr>
     </tbody>
   </table>
   </html:form>
   </c:if>
   <b><u> Authorized Users</u></b>
   <c:forEach var="user" items="${AUTH_USER_LIST}" varStatus="i" >
  <div class="listing">
  <h3 style="margin-bottom:5px;">
  User Id:<c:out value="${user.userLoginId}" />
  </h3>
  <p class="listingInfo">
  Name:
    <c:out value="${user.userLastName}" />,<c:out value="${user.userFirstName}" /><br />
  Password good untill:  <c:out value="${user.userPasswordExpiresDateString}" /><br />
   Phone: <c:out value="${user.userPhone}" /><br />
    <br />
  </p>
</div>
  </c:forEach>
   </div>
   </div>
 
 </div>  
  <div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>


