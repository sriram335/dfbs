 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

<p class="error"> Please note that deadline for Wholesale is June 15th, deadline for Sec8a is June 1st. If you submit renewal requests for these after deadlines IDHS will deny them. </p>
<table cellspacing="0" style="width: 100%;" summary="sales">

  <tr>
    <th>permit #</th>
    <th>address</th>
    <th>type</th>
    <th>status</th>
  </tr>
  <tbody>
    <c:forEach var="permit" items="${sessionScope.DFBS_OWNER.permits}" varStatus="i">
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
          <b><c:out value="${permit.permitNumber}"/>&nbsp;&nbsp;</b>
          Inspector Assigned:<c:out value="${permit.inspectorName}"/>&nbsp;</br>
             <c:if test="${permit.permitType == 1}">
             <a   href="/dfbs/fireworks/permit.do?method=viewPermit&permitNumber=<c:out value="${permit.permitNumber}"/>&permitType=WholeSale">
               [view this application ]</a> 
              </c:if>
            <c:if test="${permit.permitType != 1}">
             <a   href="/dfbs/fireworks/permit.do?method=viewPermit&permitNumber=<c:out value="${permit.permitNumber}"/>&permitType=Consumer">
               [view this application ]</a> 
             </c:if>
              <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${permit.permitNumber}"/>">
               [ Inspections List for this permit ]</a>
          
             <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null  }">  
             <a   href="/dfbs/fireworks/main.do?method=viewFees&permitNumber=<c:out value="${permit.permitNumber}"/>">
               [view fee details ]</a> 
              </c:if>

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
          <c:if test="${permit.permitType == 1}">
            <br/>(
            <c:out value="${permit.categoryString}"/>)
          </c:if>
        </td>
        <td>
          <c:choose>
            <c:when test="${permit.status == 'EXPIRED'}">
              <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="expired"/>
              <span class="error" style="font-weight:bold;font-size:medium;">expired </span>
            </c:when>
            <c:when test="${permit.status == 'PENDING'}">
              <img class="iconNotice" src="/dfbs/img/clearbits/alert.gif" alt="expired"/>
              <span class="notice" style="font-weight:bold;font-size:medium;">
              pending<br />
              (<c:out value="${permit.applicationDateString}" />)
              
              </span>
              
              
            </c:when>
            <c:when test="${permit.status == 'VALID'}">
              <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" alt="valid"/>
              <span class="message" style="font-weight:bold;font-size:medium;">valid </span>
              <c:choose>
                <c:when test="${permit.permitType == 1}">
                    <a   href="/dfbs/fireworks/main.do?method=wholesaleToPrint&permitNumber=<c:out value="${permit.permitNumber}"/>">[ permit ]</a>
               </c:when>
                <c:otherwise>
                  <a   href="/dfbs/fireworks/main.do?method=consumerToPrint&permitNumber=<c:out value="${permit.permitNumber}"/>">[ permit ]</a>
                   </c:otherwise>
              </c:choose>
            </c:when>
            <c:otherwise>
              <img class="iconFalse" src="/dfbs/img/clearbits/check.gif" alt="valid"/>
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
