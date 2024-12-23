 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control />


  
  <table cellspacing="0" style="width: 100%;"  summary="sales">
  <tr>
   <th>permit #</th>
   <th>address</th>
   <th>type</th>
   <th>status</th>
  </tr>
  <tbody>
  <c:forEach var="permit" items="${sessionScope.DFBS_OWNER_APPLICATION.permits}" varStatus="i" >
 <c:if test="${permit.renew}">
 <tr class="row<c:out value='${(i.index % 2) + 1}' />">
    <td>
    <c:out value="${permit.permitNumber}" /> &nbsp;
    </td>
 <td>
  <c:out value="${permit.street1}" /><br />
  <c:if test="${permit.street2 != null && permit.street2 != ''}">
  <c:out value="${permit.street2}" /><br />
  </c:if>
  <c:out value="${permit.city}" />, 
  <c:out value="${permit.state}" /> 
  <c:out value="${permit.zip}" />&nbsp;
 </td>
 <td>
  <c:out value="${permit.permitTypeString}" />
 </td>
 
 
 <td>
    <c:choose >
    <c:when test="${permit.status == 'EXPIRED'}">
    <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="expired"  />
    <span class="error" style="font-weight:bold;font-size:medium;">expired </span>
    </c:when>
    <c:when test="${permit.status == 'PENDING'}">
    <img class="iconNotice" src="/dfbs/img/clearbits/alert.gif" alt="expired"  />
    <span class="notice" style="font-weight:bold;font-size:medium;">pending </span>
    </c:when>
    <c:when test="${permit.status == 'VALID'}">
    <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" alt="valid"  />
    <span class="error" style="font-weight:bold;font-size:medium;">valid </span>
     <html:link page="/main.do?method=consumerToPrint" paramId="permitNumber" paramName="permit" paramProperty="permitNumber">
    pdf
    </html:link>
    </c:when>
    <c:otherwise>
     <img class="iconFalse" src="/dfbs/img/clearbits/check.gif" alt="valid"  />
   
    </c:otherwise>
    </c:choose>
    
 </td>
 
 
 </tr>
 </c:if>
 
 
</c:forEach>

</tbody>
</table>

