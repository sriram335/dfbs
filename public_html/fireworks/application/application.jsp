 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

<div class="messageBox">
  <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId">
          Back to start application for <c:out value="${DFBS_OWNER.ownerName}" />
        </html:link>
  <h2 style="font-weight:bold;">Permits Application Cart</h2>
  <p>
    <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="arrow" />
    <html:link page="/start.do">[delete and start over]</html:link>
  </p>
  <jsp:include page="ownerDisplay.jsp"/>
  <h3>permits (<c:out value="${DFBS_OWNER_APPLICATION.permitsMapCount}"/>)</h3>
  <c:choose>
    <c:when test="${sessionScope.DFBS_OWNER_APPLICATION.permitsMapCount > 0}">
     <p class="control">
      <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/> <html:link page="/application.do?method=step3">Verify Application & Fee  and Proceed to Check Out</html:link>
      </p>  
    <p class="messageBox">
  <b><u><FONT color="#ff3333">Note for File Upload(Optional):</FONT></u></br> Upload the "fireworks layout floor plan" in the building  using "upload floor plan" link below for each permit.</b>
  </p>
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
                    <html:link page="/permit.do?method=editPermitForm" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[edit]</span> 
                    </html:link>
                    <html:link page="/permit.do?method=removePermit" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[remove]</span> 
                    </html:link>
                    <html:link page="/permit.do?method=goToUpload" paramId="idNumber" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[upload floor plan]</span> 
                    </html:link>
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
