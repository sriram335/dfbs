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
 <c:if test="${sessionScope.START_STATUS !='COUNTY'}">
 <h2>Request New User Acccount</h2> 
<b><u> If you have account</u></b>
  <html:form action="/otherUser" method="post">
      <html:hidden property="userId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   
    <tr>
     <th scope="row"     > </th>
    <td>
    <html:submit value="Go to log in" styleClass="button" />
      </td>
     </tr>
   </tbody>
   </table>
   </html:form></br></br>
  <p align="left">
    <b><u> If you do not have a IDHS account start here </u></b>
 <html:form action="/otherUser" method="post" >
      <input type="hidden" name="method" id="METHOD_KEY2" value="selectSaveCounty"/> 
      <html:hidden property="userId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;" align="left">   
   <tbody class="rowHeader" >
   
     <tr align="left">
      <th scope="row" class="required"> select the county you work for:</th>
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
     <th scope="row"     > </th>
    <td>
    <html:submit value="Next" styleClass="button" />
      </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   
   </p>
   </c:if>
   <c:if test="${sessionScope.START_STATUS =='COUNTY'}">
 <html:form action="/otherUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY2" value="validateUserApp"/> 
      <html:hidden property="userId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    <td>
    
      </td>
    </tr>
     <tr>
      <th scope="row" class="required">Select Fire Department:</th>
      <td>
        <html:select property="FDId" styleId="SELECT_FD_ID" onchange="setSelectValue('SELECT_FD_ID','FD_ID')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_FD_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${DFBS_PERMIT_ERROR.countyId == 'ERROR'}">
          <br/>
          <span class="error">* please specify fire department</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    >  fire chief email  :</th>
      <td>
     <html:text property="userLoginId" size="50" maxlength="50"/>
      <c:if test="${CIGARETTE_USER_ERROR.userLoginId == 'ERROR'}">
          <br/>
          <span class="error">* please specify fire chief email</span> 
        </c:if> 
        </td>
    </tr>
   
     <tr>
      <th scope="row"      > security key :</th>
      <td>
       <html:text property="userLastName" size="30" maxlength="30"/>
       </td>
    </tr>
     
     <tr>
     <th scope="row"     > </th>
    <td>
  <html:submit value="Validate request" styleClass="button" />
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </c:if>
   </div>
 
 </div>  
  <div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>


