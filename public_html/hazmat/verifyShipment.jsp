<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - HAZMAT DIVISION"/>
<c:set var="title" scope="request" value="Verify Shipment Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Verify Permit Authenticity Information</h2> 
<a   href="/dfbs/hazmat/hazmat.do?method=backToHazmatApp">
              [Cancel]</a>
  <div id="mainContentFluid" align="left">
 <html:form action="/shipment" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="verifyPermit"/> 
      <html:hidden property="shipmentId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     
     <tr>
      <th scope="row"  class="required"    > * permit number:</th>
      <td>
       <html:text property="shipPermitNumber" size="20" maxlength="30"/>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * permit valid from date:</th>
      <td>
       <html:text property="shipIssueDate" size="10" maxlength="30"/>mm/dd/yyyy format
     </td>
    </tr>
    
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="verify" styleClass="button" />
     
     </td>
     </tr>
   
  <tr>
  <th scope="row"     > </th>
    <td>
   <c:if test="${sessionScope.HAZMAT_ORGANIZATION_VERIFY.orgName == 'NO DATA' }"> 
   <p class="error">
   No matching data found, please verify the permit number and permit valid from date are entered correctly.
   If information entered is correct, then permit is <b>NOT</b> valid contact <A title=mailto:PMellencamp@dhs.in.gov href="mailto:PMellencamp@dhs.in.gov">Phillip Mellencamp </A> or <A title=mailto:kstuder@dhs.in.gov href="mailto:kstuder@dhs.in.gov">Kaci Studer </A> with details
   </p>
   </c:if>
   <c:if test="${sessionScope.HAZMAT_ORGANIZATION_VERIFY.orgName != null && sessionScope.HAZMAT_ORGANIZATION_VERIFY.orgName != 'NO DATA' }"> 
   <p class="message">
   <b><u> Permit is Valid </u></b></br>
   Organization name: <c:out value="${sessionScope.HAZMAT_ORGANIZATION_VERIFY.orgName}"/></br>
   Carrier name:<c:out value="${sessionScope.HAZMAT_CARRIER_VERIFY.carrierName}"/></br>
   valid  between dates  <c:out value="${sessionScope.HAZMAT_SHIPMENT_VERIFY.shipIssueDateString}"/> and <c:out value="${sessionScope.HAZMAT_SHIPMENT_VERIFY.shipExpDateString}"/>
  </p>
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