<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/elevator/elevTests.do?method=addTestsList&inspectionId=<c:out value="${ELEVATOR_INSPECTION_SELECTED.inspectionId}"/>">
              [back to tests list]</a></br>  
  <h2 style="font-weight:bold;">Update Elevator Test Information  </h2>
 
 
<html:form action="/elevTests" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveTest"/>
      <html:hidden property="testId"/> 
  Test:<c:out value="${UPDATE_TEST_SELECTED.testName}"/> 
  Status:  <html:text property="testStatus" rows="15" cols="15"/>
  Value:  <html:text property="testValue" rows="30" cols="30"/>
 
   <p>
           <html:submit value="Save" styleClass="button" />
    </p>
  </html:form>