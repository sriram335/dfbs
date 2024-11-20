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
<a   href="/dfbs/otherUsers/otherUser.do?method=userListPlan">
              back</a></br>

  <div class="styledBox">
 <div id="mainContentFluid" align="left">
 
  <h2>Add / Update  Plan Review User Acccount for Fire / EMS access to Building Plans </h2> 
 <html:form action="/otherUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY2" value="savePlanUser"/> 
      <html:hidden property="userId"/> 
      <html:hidden property="userPasswordExpiresDate"/>
     
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
     
        </td>
    </tr>
   <%--  <tr>
      <th scope="row"      > password :</th>
      <td>
       <html:text property="userPassword" size="10" maxlength="10"/>
       </td>
    </tr> --%>
    <tr>
      <th scope="row"      > status :</th>
      <td>
      <html:select property="userStatus"  onchange="setSelectValue('SELECT_DESCRIPTION','DESCRIPTION')">
          <html:option value="A">Active</html:option>
          <html:option value="I">Inactive</html:option>
        </html:select>
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
    <tr>
      <th scope="row"      > status :</th>
      <td>
       <html:text property="userStatus" size="10" maxlength="10"/> put 'A' for active, 'I' for inactive
       </td>
    </tr> 
     <tr>
      <th scope="row"      > password :</th>
      <td>
       <html:text property="userPassword" size="10" maxlength="10"/> 
       </td>
    </tr> 
     <tr>
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
    <tr>
      <th scope="row" class="required">Select Fire Department:</th>
      <td>
        <html:select property="FDId" styleId="SELECT_FD_ID" onchange="setSelectValue('SELECT_FD_ID','FD_ID')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_FIRE_DEPT_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.countyId == 'ERROR'}">
          <br/>
          <span class="error">* please specify fire department</span> 
        </c:if>
      </td>
    </tr>
     <tr>
     <th scope="row"     > </th>
    <td>
    <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER'}"> 
      <html:submit value="Save" styleClass="button" />
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


