<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<c:if test="${nav != 'false'}">
  <jsp:include page="steps.jsp"/>
</c:if>
<div class="messageBox">
  <c:if test="${nav != 'false'}">
    <jsp:include page="backNext.jsp"/>
  </c:if>
  <h2 style="font-weight:bold;">Permits Application Cart</h2>
  <p>
    <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="arrow" />
    <html:link page="/application.do?method=startOverForm">[delete and start over]</html:link>
  </p>
  <jsp:include page="ownerDisplay.jsp"/>
  <h3>permits (<c:out value="${DFBS_OWNER_APPLICATION.permitsMapCount}"/>)</h3>
 <c:choose>
    <c:when test="${sessionScope.DFBS_OWNER_APPLICATION.permitsMapCount > 0}">
      <ol>
        <table class="noBorder" style="border: solid #E2C400 1px;width:100%;" cellspacing="0">
          <tbody class="rowHeader">
            <c:forEach var="mapItem" items="${sessionScope.DFBS_OWNER_APPLICATION.permitsMap}" varStatus="i">
              <c:set scope="request" var="permit" value="${mapItem.value}"/>
              <c:choose>
                <c:when test="${(i.index % 2) == 0}">
                  <c:set scope="request" var="rowStyle" value="style='background: #FFFAA2'"/>
                </c:when>
                <c:otherwise>
                  <c:set scope="request" var="rowStyle" value=""/>
                </c:otherwise>
              </c:choose>
              <tr <c:out value="${rowStyle}" escapeXml="false" />>
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
                
                  <c:if test="${nav != 'false'}">
                  <c:if test="${permit.status == 'VALID' || permit.renewStatus == 'Renewed' || permit.new || permit.status == 'PENDING'}" >
                    <html:link page="/special.do?method=addSpecialForm" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[apply for special]</span> 
                    </html:link>
                    </c:if>
                    <c:if test="${permit.status == 'EXPIRED' ||permit.status == 'PENDING' ||permit.status == 'EXPIRES SOON' }">
                    <html:link page="/permit.do?method=editPermitForm" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[edit for renewal]</span>                  
                    </html:link>
                    </c:if>
                    <html:link page="/permit.do?method=removePermit" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[remove]</span> 
                    </html:link>
                    <span style="font-size:x-small">
                    <a href="/dfbs/aepermits/main.do?method=goToUpload&idNumber=<c:out value="${permit.applicationKey}"/>&idType=AEPermit">
                    [upload AE Annual facility floor plan]</a> </br></span>
                  </c:if>&nbsp;
                </td>
              </tr>
             <jsp:include page="permitsDisplay.jsp"/> 
              <tr <c:out value="${rowStyle}" escapeXml="false" />>
                <td colspan="3" style="border-bottom: solid #E2C400 1px">&nbsp;</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </ol>
    </c:when>
    <c:otherwise>-----</c:otherwise>
  </c:choose>
</div>
