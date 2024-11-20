<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<div id="leftContentFluid"> 
  <h2 style="font-weight:bold;">Update Rule 13 information  </h2>
 <a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
              [back to inspection]</a>
 
<html:form action="/rule13" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveList"/>
 <h2 style="font-weight:bold;">Rule 13 disposition  </h2>
 
 <c:forEach var="rule13" items="${sessionScope.RULE13_UPDATE.list}" varStatus="i" >
  <tr>
  <td width="50">
  <div class="listing">
   <c:out value="${rule13.rule13SeqId}" /> 
   <c:out value="${rule13.rule13Description}" />
   <c:out value="${rule13.rule13Remarks}" />
    <a   href="/dfbs/idhsInspections/rule13.do?method=editRule13&responseId=<c:out value="${rule13.rule13Id}" />">
              [edit]</a></br>
  </div>
 </td>
 </tr>
  </c:forEach>
   <p>
   
          <html:submit value="Save" styleClass="button" />
   </p>
  </html:form>
  </div>
 
