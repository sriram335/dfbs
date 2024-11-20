<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>location name:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.actualLocation}"/>
    <c:if test="${requestScope.permitError.actualLocation == 'ERROR'}">
      <br/><span class="error">* please location name (step 2)</span> 
    </c:if>
  </td>
  </tr> 

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
      <br/><span class="error">* please specify street address (step 2)</span> 
    </c:if>
    <c:if test="${requestScope.permitError.city == 'ERROR'}">
      <br/><span class="error">* please specify city (step 2)</span> 
    </c:if>
    <c:if test="${requestScope.permitError.state == 'ERROR'}">
      <br/><span class="error">* please specify state (step 2)</span> 
    </c:if>
    <c:if test="${requestScope.permitError.state == 'ERROR2'}">
      <br/><span class="error">* please select "INDIANA" as state (step 2)</span> 
    </c:if>
    <c:if test="${requestScope.permitError.zip == 'ERROR'}">
      <br/><span class="error">* please specify zip (step 2)</span> 
    </c:if>
    
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>county:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.county}"/>
    <c:if test="${requestScope.permitError.county == 'ERROR'}">
      <br/><span class="error">* please specify county (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>contact:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.contactPerson}"/>
    <c:if test="${requestScope.permitError.contactPerson == 'ERROR' }">
      <br/><span class="error">* please specify contact person (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>phone:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.phoneNumber}"/>
    <c:if test="${requestScope.permitError.phoneNumber == 'ERROR' }">
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

<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>facility description:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.facilityDescription}"/>
    <c:if test="${requestScope.permitError.facilityDescription == 'ERROR'}">
      <br/><span class="error">* please specify facility description (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>event date:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.eventDateString}"/>
    <c:if test="${requestScope.permitError.eventDate == 'ERROR'}">
      <br/><span class="error">* please specify event date (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>event time:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.eventTime}"/>
    <c:if test="${requestScope.permitError.eventTime == 'ERROR'}">
      <br/><span class="error">* please specify event time (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>event name:</th>
  <td colspan="2">
    <c:out value="${requestScope.permit.eventName}"/>
    <c:if test="${requestScope.permitError.eventName == 'ERROR'}">
      <br/><span class="error">* please specify event name (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>upload map status:</th>
  <td colspan="2">
     <c:forEach var="file" items="${requestScope.permit.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
      </c:forEach>
    <c:if test="${requestScope.permit.noMap == 'ERROR'}">
      <br/><span class="error">* please upload floor plan of ae permit (step 2)</span> 
    </c:if>
  </td>
</tr>
<tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
<c:forEach var="mapItem" items="${requestScope.permit.specialMap}" varStatus="j">
<c:set scope="request" var="special" value="${mapItem.value}"/>
 <td colspan="4">   

<span style="font-size:x-small">
  <a   href="/dfbs/aepermits/special.do?method=editSpecial&key=<c:out value="${permit.applicationKey}"/>&specialNumber=<c:out value="${mapItem.key}"/>">[ Edit ]</a>
  <a   href="/dfbs/aepermits/special.do?method=removeSpecial&permitNumber=<c:out value="${permit.applicationKey}"/>&key=<c:out value="${mapItem.key}"/>">[ remove ]</a>

                    <a href="/dfbs/aepermits/special.do?method=uploadFile&annualNumber=<c:out value="${permit.applicationKey}"/>&specialNumber=<c:out value="${mapItem.key}"/>">
                    [upload AE Special floor plan]</a> </br></span>

<jsp:include page="specialListSelect.jsp"/>
</td>
</tr>
</c>
</c:forEach>
</tr> 