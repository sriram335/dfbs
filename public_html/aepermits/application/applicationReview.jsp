<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<jsp:include page="steps.jsp"/>
<div class="messageBox">
  <jsp:include page="backNext.jsp"/>
  <h2 style="font-weight:bold;">Permits Application Cart</h2>
  <p style="font-size:x-small;font-weight:normal">
    <img class="iconFalse" src="/dfbs/img/clearbits/close.gif" alt="arrow" />
    <html:link page="/application.do?method=startOverForm">[delete and start over]</html:link>
  </p>
  
  <c:if test="${DFBS_APPLICATION_ERROR != null}">
    <h3>Please fix the application errors:</h3>
    <dl>
    <c:if test="${DFBS_OWNER_APPLICATION.permitsCount == 0}">
      <dt class="error" style="font-weight:bold;">* please add or select permits to renew (step 2)</dt>
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
              <tr <c:out value="${requestScope.rowStyle}" escapeXml="false" />
               <th scope="row" <c:out value="${rowStyle}" escapeXml="false" />permit:</th>
                <td colspan="4">
                  <c:choose>
                    <c:when test="${permit.new}">NEW</c:when> 
                    <c:otherwise>
                      <c:out value="${permit.permitNumber}"/> 
                      <c:if test="${permit.specialsCount == 0 &&( permit.status == 'VALID' || permit.status == 'PENDING') }">
                     <br/><span class="error">* you have addded the permit to cart but not added special endorsments (step 2)</span> 
                     </c:if>
                    </c:otherwise>
                  </c:choose>  
                  </br>New permit / Renewal fee:<c:out value="${permit.amountString}"/> </br>
                  Special endorsement fee:<c:out value="${permit.permitSpecialAmountString}"/>  </br>
                  Number of Specials applied :<c:out value="${permit.specialsCount}"/>  </br>
                  Additional plans fee for permit:(<c:out value="${permit.addPlansFee}"/>0) </br>
                  
                </td>
                </tr>
                
              <jsp:include page="permitsDisplay.jsp"/>
              <tr <c:out value="${rowStyle}" escapeXml="false" />
                <td colspan="3" style="border-bottom: solid #E2C400 1px">&nbsp;</td>
              </tr>
            </c:forEach>
            <tr>
              <th scope="row" colspan="2">total additional plans fee:</th>
              <td style="font-size: large;font-weight:bold;">
                $<c:out value="${sessionScope.DFBS_OWNER_APPLICATION.addPlansFee}"/>0
              </td>
            </tr>
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
