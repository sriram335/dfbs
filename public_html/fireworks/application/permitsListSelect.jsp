 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<p class="error">
 Note: Important!! Product must be in the facility before an inspection will occur! </br>
 </p>
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>permit #</th>
    <th>address</th>
    <th>type</th>
    <th>*</th>
  </tr>
  <tbody>
    <c:forEach var="permit" items="${sessionScope.DFBS_OWNER.permits}" varStatus="i">
    <%-- disable after may31  && (permit.permitType != 2)--%>
      <c:if test="${ ( (permit.status == 'EXPIRED') && sessionScope.DFBS_OWNER_APPLICATION.permitsMap[permit.permitNumber] == null  )   }">
              <tr class="row<c:out value='${(i.index % 2) + 1}' />">
          
          <td>
            <c:out value="${permit.permitNumber}"/>&nbsp;
          </td>
          <td>
            <c:out value="${permit.street1}"/>
            <br/>
            <c:if test="${permit.street2 != null && permit.street2 != ''}">
              <c:out value="${permit.street2}"/>
              <br/>
            </c:if>
            <c:out value="${permit.city}"/>,
            <c:out value="${permit.state}"/>
            <c:out value="${permit.zip}"/>&nbsp;
          </td>
          <td>
            <c:out value="${permit.permitTypeString}"/>
          </td>
          <td>
          <html:form action="/permit" method="post">
          <input type="hidden" name="method" id="METHOD_KEY" value="addRenewalPermits"/>
           <input type="hidden" name="permitNumber" id="METHOD_KEY" value="${permit.permitNumber}"/>
           <%-- <input type="checkbox" name="PERMIT<c:out value='${permit.permitNumber}' />" class="switch"/>&nbsp;--%>
            <input type="submit" value="Renew" class="button"/></br></br>
          </html:form>
          </td>
        </tr>
      </c:if>
    </c:forEach>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
  </tbody>
</table>
