<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>


<div id="leftContentFluid"> 
  <h2 style="font-weight:bold;">Look up Standard Violations  </h2>
 
 <html:form action="/violation" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="violationLookup"/>  
  Look up violation like(use code)   <html:text property="stdViolation" size="30" maxlength="30"/>
  
   <p>
   
          <html:submit value="Look up standard violation" styleClass="button" />
   </p>
   
</html:form>

 <h2 style="font-weight:bold;">Standard Violation List  </h2>
  <c:forEach var="violation" items="${STANDARD_VIOLATION.list}" varStatus="i" >
  <tr>
  <td>
  <div class="listing">
   <b><c:out value="${violation.vioCode}" /> </b><br />
    <c:out value="${violation.vioDescription}" />
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null}">
   <a href="/dfbs/magazine/violation.do?method=addStdViolation&stdVioCode=<c:out value="${violation.vioCode}" />">
    [add this violation]  </a> 
  </c:if> 
  </div>
 </td>
 </tr>
  </c:forEach>
  </div>
 
