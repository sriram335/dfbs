<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Cigarette Company Information"/>
<h2>Edit Company Information</h2> 
<div id="sideContentFluid">
<a   href="/dfbs/cigarette/applicationsView.do?method=addSupApp">
              [Cancel]</a>
</div>
  <div class="styledBox">
 <html:form action="/cigarette" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveChangeCompany"/> 
      <html:hidden property="companyId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * company name:</th>
      <td>
       <html:text property="companyName" size="80" maxlength="80"/>
       <c:if test="${CIGARETTE_COMPANY_ERROR.companyName == 'ERROR'}">
          <br/>
          <span class="error">* please enter company name</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * company address1:</th>
      <td>
       <html:text property="companyAddress1" size="100" maxlength="100"/>
       <c:if test="${CIGARETTE_COMPANY_ERROR.companyAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter company address1</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > company address2:</th>
      <td>
       <html:text property="companyAddress2" size="30" maxlength="30"/>
       </td>
    </tr>
    <tr>
      <th scope="row"    class="required" > * company city:</th>
      <td>
       <html:text property="companyCity" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_COMPANY_ERROR.companyCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter company city</span> 
        </c:if>
       </td>
    </tr>
  
    <tr>
      <th scope="row"    class="required" > * company state:</th>
      <td>
         <html:text property="companyState" size="30" maxlength="30"/>
       </td>
    </tr>
 
  
    <tr>
      <th scope="row"    class="required" > * company zip:</th>
      <td>
       <html:text property="companyZip" size="30" maxlength="30"/>
       <c:if test="${CIGARETTE_COMPANY_ERROR.companyZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter company zip</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"     > company zip2:</th>
      <td>
       <html:text property="companyZip2" size="4" maxlength="4"/>
       </td>
    </tr>
      <tr>
      <th scope="row"     > company province:</th>
      <td>
       <html:text property="companyProvince" size="30" maxlength="30"/>
       </td>
    </tr>
      <tr>
      <th scope="row"     > company country:</th>
      <td>
       <html:text property="companyCountry" size="30" maxlength="30"/>
       </td>
    </tr>
     <tr>
      <th scope="row"     class="required"> * company  phone:</th>
      <td>
       <html:text property="companyPhone" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_COMPANY_ERROR.companyPhone == 'ERROR'}">
          <br/>
          <span class="error">* phone format error</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"    > company contact fax:</th>
      <td>
       <html:text property="companyFax" size="10" maxlength="10"/>
       <c:if test="${CIGARETTE_COMPANY_ERROR.companyFax == 'ERROR'}">
          <br/>
          <span class="error">* fax format error</span> 
        </c:if>
       </td>
    </tr>
   
     
    
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'}">
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save" styleClass="button" />
     
     </td>
     </tr>
     </c:if>
   </tbody>
   </table>
   </html:form>
    </div>
  <div class="clearer">&nbsp;</div>
 </div>  
  </div> 
<jsp:include page="/app/common/hsPageFooter.jsp"/>