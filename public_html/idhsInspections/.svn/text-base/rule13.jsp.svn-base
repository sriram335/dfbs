<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
              [back to inspection]</a>
<div id="leftContentFluid"> 
  <h2 style="font-weight:bold;">Add Rule 13 information  </h2>
 
 
<html:form action="/rule13" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveList"/>
 <h2 style="font-weight:bold;">Rule 13 disposition  </h2>
 
 <c:forEach var="rule13" items="${sessionScope.RULE13.list}" varStatus="i" >
  <tr>
  <td width="50">
  <div class="listing">
   <c:out value="${rule13.rule13SeqId}" /> 
   <c:out value="${rule13.rule13Description}" />
   <input type="text"   name="<c:out value='${rule13.rule13SeqId}' />" size="100" maxlength="200"/>
  </div>
 </td>
 </tr>
  </c:forEach>
   <p>
    <c:if test="${sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId }"> 
          <html:submit value="Save" styleClass="button" />
    </c:if>
   </p>
  </html:form>
  </div>
 
