 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>


  <h2 style="font-weight:bold;">Look up Standard Violations  </h2>
 <font color=red>(Please enter Model Year Code and one of the Look up criteria)</font><br>
 <html:form action="/idhsInspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="lookUpStdViolation"/> 
  Model Year Code: <html:text property="vioYear" size="4" maxlength="4"/></br>
  Look up violation code number like   <html:text property="vioCode" size="20" maxlength="20"/></br>
  Look up description like   <html:text property="vioDescription" size="20" maxlength="20"/></br>
  Look up violation category like   <html:text property="vioType" size="20" maxlength="20"/></br>
   <p>
   
          <html:submit value="Look up standard violation" styleClass="button" />
   </p>
   
</html:form>
<%--<c:if test="${sessionScope.STANDARD_VIOLATION.list. >0}"> --%>
<html:form action="/idhsInspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addStdViolationList"/>
 <h2 style="font-weight:bold;">Standard Violation List  </h2>
 <p>
   
          <html:submit value="Add selected violation(s)" styleClass="button" />
   </p>
 <c:forEach var="violation" items="${sessionScope.STANDARD_VIOLATION.list}" varStatus="i" >
  <tr>
  <td width="200">
  <div class="listing">
   <b><c:out value="${violation.vioCode}" /> </b><br />
    <c:out value="${violation.vioDescription}" />
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">
   add --><input type="checkbox" name="<c:out value='${violation.vioId}' />" class="switch"/><--
  <%-- <a href="/dfbs/idhsInspections/idhsInspection.do?method=addStdViolation&vioId=<c:out value="${violation.vioId}" />">
    [add this violation]  </a>  --%>
  </c:if> 
  </div>
 </td>
 </tr>
  </c:forEach>
   <p>
   
          <html:submit value="Add selected violation(s)" styleClass="button" />
   </p>
  </html:form>
 
 
 
