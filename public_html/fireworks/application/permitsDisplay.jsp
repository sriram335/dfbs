 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>address:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.street1}"/>
    <br/>
    <c:if test="${requestScope.permit.street2 != null && requestScope.permit.street2 != ''}">
      <c:out value="${requestScope.permit.street2}"/>
      <br/>
    </c:if>
    <c:out value="${requestScope.permit.city}"/>,
    <c:out value="${requestScope.permit.state}"/>
    <c:out value="${requestScope.permit.zip}"/>
    
    <c:if test="${requestScope.permitError.street1 == 'ERROR'}">
      <br/><span class="error">* please specify street address </span> 
    </c:if>
    <c:if test="${requestScope.permitError.city == 'ERROR'}">
      <br/><span class="error">* please specify city </span> 
    </c:if>
    <c:if test="${requestScope.permitError.state == 'ERROR'}">
      <br/><span class="error">* please specify state </span> 
    </c:if>
    <c:if test="${requestScope.permitError.state == 'ERROR2'}">
      <br/><span class="error">* please select "INDIANA" as state </span> 
    </c:if>
    <c:if test="${requestScope.permitError.zip == 'ERROR'}">
      <br/><span class="error">* please specify zip </span> 
    </c:if>
    
  </td>
</tr>
<%--
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>county:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.county}"/>
    <c:if test="${requestScope.permitError.countyId == 'ERROR'}">
      <br/><span class="error">* please specify county (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>type:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.permitTypeString}"/>
  </td>
</tr>
<c:if test="${requestScope.permit.permitType == 1}">
  <tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
    <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>category:</th>
    <td colspan="2">
      <c:out value="${requestScope.permit.categoryString}"/>
      <c:if test="${requestScope.permitError.categoryId == -1}">
      <br/><span class="error">* please specify a category (step 2)</span> 
      </c:if>
    </td>
  </tr>
</c:if>

<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>merchant #:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.merchantNumber}"/>
    <c:if test="${requestScope.permitError.merchantNumber == 'ERROR'}">
      <br/><span class="error">* please specify merchant number  (step 2)</span> 
      </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>contact:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.contactPerson}"/>
    <c:if test="${requestScope.permitError.contactPerson == 'ERROR'}">
      <br/><span class="error">* please specify contact person (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>phone:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.phoneNumber}"/>
    <c:if test="${requestScope.permitError.phoneNumber == 'ERROR'}">
      <br/><span class="error">* please specify phone number (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>fax:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.faxNumber}"/>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>email:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.emailAddress}"/>
  </td>
</tr>
<c:if test="${requestScope.permit.permitType != 1}">
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>date to inspect:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.inspectDateString}"/>
    <c:if test="${requestScope.permitError.inspectDate == 'ERROR'}">
    <br/>
    <span class="error">* please specify date to inspect (step 2)</span> 
  </c:if>
  </td>
</tr>
</c:if>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>open date:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.openDateString}"/>
    <c:if test="${requestScope.permitError.openDate == 'ERROR'}">
    <br/>
    <span class="error">* please specify open date (step 2)</span> 
  </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>hours of operation:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.hoursOfOperation}"/>
    <c:if test="${requestScope.permitError.hoursOfOperation == 'ERROR'}">
    <br/>
    <span class="error">* please specify hours of operation (step 2)</span> 
  </c:if>
  </td>
</tr>
--%>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>file upload:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.noMap}"/>
    <c:if test="${requestScope.permitError.noMap == 'ERROR'}">
    <br/>
    <span class="error">* please upload floor plan</span> 
  </c:if>
  </td>
</tr>
