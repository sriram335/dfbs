<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/elevator/inspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${ELEVATOR_INSPECTION_SELECTED.inspectionId}"/>">
              [back to inspection]</a>
  <h2 style="font-weight:bold;">Update Elevator Tests Information  </h2>
 
 
<html:form action="/elevTests" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveTestsList"/>
 
 <c:forEach var="test" items="${sessionScope.ELEV_UPDATE_TESTS.list}" varStatus="i" >
 
  <div class="listing" align="left">
   Test Name: <c:out value="${test.testName}" />
  Status: <c:out value="${test.testStatus}" />
   Value:<c:out value="${test.testValue}" /> 
  <a   href="/dfbs/elevator/elevTests.do?method=updateTest&testId=<c:out value="${test.testId}"/>">
              [edit]</a></br>   
  </div>
 
  </c:forEach>
   <p>
           <html:submit value="Save" styleClass="button" />
    </p>
  </html:form>