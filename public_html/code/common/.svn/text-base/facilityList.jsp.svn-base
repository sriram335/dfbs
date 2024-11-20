<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >Facilities</th>
    <th>Address </th>
  </tr>
  <tbody>
    <c:forEach var="mapItem" items="${sessionScope.MANUFACTURER.facilityMap}" varStatus="i">
              <c:set scope="request" var="facility" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
     
          <c:out value="${facility.facilityName}"/>&nbsp; <br/> </span>
           <a   href="/dfbs/code/viewFacility.do?method=viewFacility&facilityId=<c:out value="${facility.facilityId}"/>">
             [ Edit facility contact information ]</a><br/>
           <a   href="/dfbs/code/viewFacility.do?method=buySeals&facilityId=<c:out value="${facility.facilityId}"/>">
             [ buy seals for this facility ]</a>
          <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }"> 
           <a   href="/dfbs/code/viewFacility.do?method=sealOrders&facilityId=<c:out value="${facility.facilityId}"/>">
             [ view seal orders ]</a>
          </c:if>
          </td>
          <td>
          <c:out value="${facility.facilityAddress1}"/>
          <br/>
          <c:if test="${facility.facilityAddress2 != null && facility.facilityAddress2 != ''}">
            <c:out value="${facility.facilityAddress2}"/>
            <br/>
          </c:if>
          <c:out value="${facility.facilityCity}"/>,
          <c:out value="${facility.facilityState}"/>
          <c:out value="${facility.facilityZip}"/>&nbsp;
            
         
        </td>
      </tr>
  <%--      <tr>
        <td>
      <c:forEach var="mapItem" items="${facility.personMap}" varStatus="i">
          <c:set scope="request" var="person" value="${mapItem.value}"/>
          <jsp:include page="personsListFacility.jsp"/>    
         </c:forEach > 
         </td>
        </tr> --%>
    </c:forEach>
  </tbody>
</table>
