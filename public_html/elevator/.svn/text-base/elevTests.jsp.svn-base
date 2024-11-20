<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html2"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${ELEVATOR_INSPECTION_SELECTED.inspectionId}"/>">
              [back to inspection]</a>
  <h2 style="font-weight:bold;">Add Elevator Tests Information  </h2>
 
 
<html:form action="/elevTests" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveTestsList"/>
 
 <c:forEach var="test" items="${sessionScope.ELEV_TESTS.list}" varStatus="i" >
 
  <div class="listing" align="left">

  <c:out value="${test.testName}" />
    Status: <select    name="<c:out value='${test.testId}' />" size="1" maxlength="10">
            <option value="N.A">Not Applicable</option>
            <option value="Pass">Pass</option> 
              </select>
  <%--  Status: <input type="text"   name="<c:out value='${test.testId}' />" size="10" maxlength="10"/> --%>
   Value:<input type="text"   name="<c:out value='${test.testValue}' />" size="10" maxlength="30"/> 
  </div>
 
  </c:forEach>
   <p>
           <html:submit value="Save" styleClass="button" />
    </p>
  </html:form>
 