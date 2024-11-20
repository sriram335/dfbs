<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<table cellspacing="0" style="width: 100%;" summary="sales">
<th scope="row" <c:out value="${requestScope.rowStyle}" escapeXml="false" />>special endorsement details #              </th>
  <tbody>
 <tr <c:out value="${rowStyle}" escapeXml="false" />>
                <td colspan="3" style="border-bottom: solid #E2C400 1px">&nbsp;</td>
 </tr> 
  <tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
  <td colspan="4">
      Event Name:          <c:out value="${special.eventName}"/></br>
      Event Date:          <c:out value="${special.eventDateString}"/></br>
      Event Time:          <c:out value="${special.eventTime}"/> </br>
      Maximum Occupancy :     <c:out value="${special.maximumOccupancy}"/> </br>
      Contact Person :     <c:out value="${special.contactPerson}"/> </br>
      Contact Phone :     <c:out value="${special.contactPhone}"/> </br>
      Fee Exempt :     <c:out value="${special.feeExempt}"/> </br>
      File Status:
      <c:forEach var="file" items="${special.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
      </c:forEach>
      <c:if test="${special.noMap == 'ERROR'}">
      <br/><span class="error">* Please upload floor plan for AE permit (step 2)</span> 
      </c:if>
        </td>    
        </tr>
    </tbody>
  </table>
