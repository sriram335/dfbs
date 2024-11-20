 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

<div class="messageBox">
 <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId">
          Back to start application for <c:out value="${DFBS_OWNER.ownerName}" />
        </html:link>
  <h2 style="font-weight:bold;">Permits Application Cart</h2>
  <p style="font-size:x-small;font-weight:normal">
   <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="arrow" />
    <html:link page="/start.do">[delete and start over]</html:link>
  </p>
  
  <c:if test="${DFBS_APPLICATION_ERROR != null}">
    <h3>Please fix the application errors:</h3>
    <dl>
    <c:if test="${DFBS_OWNER_APPLICATION.permitsCount == 0}">
      <dt class="error" style="font-weight:bold;">* please add or select permits to renew </dt>
    </c:if>
    <c:if test="${DFBS_APPLICATION_ERROR.wholesalePermitCount == -1}">
      <dt class="error" style="font-weight:bold;">* multiple wholesale permit is not allowed</dt>
      <dd class="error">
      It seems you already have a valid or pending wholesale permit or this application
      have multiple wholesale permits. You are only allowed to have at most 1 wholesale permit per year.
      </dd>
    </c:if>
    </dl>
  </c:if>
 
  
  <jsp:include page="ownerDisplay.jsp"/>
  <h3>permits (<c:out value="${DFBS_OWNER_APPLICATION.permitsCount}"/>)</h3>
  
  
  
  <c:choose>
    <c:when test="${sessionScope.DFBS_OWNER_APPLICATION.permitsCount > 0}">
      <ol>
        <table class="noBorder" style="border: solid #E2C400 1px;width:100%;" cellspacing="0">
          <tbody class="rowHeader">
            <c:forEach var="p" items="${sessionScope.DFBS_OWNER_APPLICATION.permits}" varStatus="i">
              <c:set scope="request" var="permit" value="${p}"/>
              <c:set scope="request" var="permitError" value="${ DFBS_APPLICATION_ERROR.permitsMap[p.applicationKey] }"/>
              
              
              <c:choose>
                <c:when test="${(i.index % 2) == 0}">
                  <c:set scope="request" var="rowStyle" value="style='background: #FFFAA2'"/>
                </c:when>
                <c:otherwise>
                  <c:set scope="request" var="rowStyle" value=""/>
                </c:otherwise>
              </c:choose>
              <tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />>
                <th scope="row" <c:out value="${rowStyle}" escapeXml="false" />>permit:</th>
                <td>
                  <c:choose>
                    <c:when test="${permit.new}">NEW</c:when>
                    <c:otherwise>
                      <c:out value="${permit.permitNumber}"/>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td>
                  <c:out value="${permit.amountString}"/>
                </td>
              </tr>
              <jsp:include page="permitsDisplay.jsp"/>
              <tr <c:out value="${rowStyle}" escapeXml="false" />>
                <td colspan="3" style="border-bottom: solid #E2C400 1px">&nbsp;</td>
              </tr>
            </c:forEach>
            <tr>
              <th scope="row" colspan="2">total amount:</th>
              <td style="font-size: large;font-weight:bold;">
                <c:out value="${sessionScope.DFBS_OWNER_APPLICATION.totalPermitsAmountString}"/>
              </td>
            </tr>
          </tbody>
        </table>
      </ol>
    </c:when>
    <c:otherwise>-----</c:otherwise>
  </c:choose>
</div>
