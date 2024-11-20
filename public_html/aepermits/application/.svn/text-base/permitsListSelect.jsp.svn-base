<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>*</th>
    <th>permit #</th>
    <th>address</th>
    
    
  </tr>
  <tbody>
    <c:forEach var="permit" items="${sessionScope.DFBS_OWNER.permits}" varStatus="i">
    <c:set scope="request" var="permit" value="${permit.permitNumber}"/>
     <c:if test="${ ( sessionScope.DFBS_OWNER_APPLICATION.permitsMap[permit.permitNumber] == null )  }">
        <tr class="row<c:out value='${(i.index % 2) + 1}' />">
           <c:if test="${  (permit.status == 'EXPIRED')   }">
           <td>
            <input type="checkbox" name="PERMIT<c:out value='${permit.permitNumber}' />" class="switch"/>Renew Annual&nbsp;
          </td>
           </c:if>
            <c:if test="${  (permit.status == 'EXPIRES SOON')  }">
           <td>
            <input type="checkbox" name="PERMIT<c:out value='${permit.permitNumber}' />" class="switch"/>Renew Annual or Add Special&nbsp;
          </td>
           </c:if>
           <c:if test="${  (permit.status == 'VALID' || permit.status == 'PENDING')    }">
           <td>
            <input type="checkbox" name="PERMIT<c:out value='${permit.permitNumber}' />" class="switch"/>Add a Special&nbsp;
          </td>
           </c:if>
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
         
        </tr>
      </c:if>
    </c:forEach>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
  </tbody>
</table>
