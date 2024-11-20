<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Elevator Permits" />
<c:set var="title" scope="request" value="Elevator Online Application" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${ELEV_PERMITS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.ELEV_PERMITS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${ELEV_PERMITS_APP_STATUS != 'I'}">
<h2>Start Application</h2>
elevator/servContractor.do

 
<div id="leftContentFluid" align="left">

 <c:if test="${sessionScope.DFBS_SECURITY != null}">  
<p class="message">
Welcome <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
<p class="messageBox" size="100">
  Note: You can use this application to:</br>
  1. Apply and Print for alteration,installation,temporary elevator permit applications.</br>
  2. Print current invoices and pay IDHS fees and fines for elevators.</br>
  3. Add and view inspection and safety tests information.</br>
  4. Check the current status of Elevator inspection tests.</br>
</p>
<p class="error" size="100">
  To serve you better</br>
  1. Please select the correct owner / elevator information and then proceed to complete the application/inspection information.</br>
  2. Only add new information if it is not existing in the database. This will help us avoding duplicate information in database and
  help us avoid delay in processing the information.
</p>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="search"/>
  
      <p class="messageBox" size="100"> 
       I am a user who has elevators in IDHS database.</br>
      <input type="submit" value="Proceed" class="button"/>
      </p>
</html:form>
<html:form action="/owner" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="newOwner"/>
         <p class="messageBox" size="100"> 
        <b> Use this option only if all the answer's for the questions is "Yes".</b></br>
         1.Are you a new user, never applied for any elevator permit from IDHS?</br>
         2.Are you are applying for elevator applications for a brand new facility?</br>
         (If you have purchased a business look for elevators under previous owners)</br>
         3.Are you starting a new business in state of Indiana?</br>
         <input type="submit" value="Proceed" class="button"/></p>
 </html:form>
<p class="messageBox" size="100">
  Complete the alteration/ installation application process by a)uploading Affirmation of Owner and Affirmation of Contractor;</br>
  b)Upload elevator /building alteration or installation plans</br>
 <html:form action="/elevator" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="goToUpload"/> 
 State Number:<html:text property="stateNumber" size="12" maxlength="12"/> 
  <c:if test="${sessionScope.ELEVATOR_UPLOAD_ACTION == 'N'}">
              <br/><span class="error">* please enter State Number  </span> 
            </c:if>
   <input type="submit" value="upload" class="button"/>
 </html:form>
</p>
<p class="messageBox" size="100">
  a)Submit Elevator Annual Test Reports</br>
 <html:form action="/elevator" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="submitTests"/> 
 State Number:<html:text property="stateNumber" size="12" maxlength="12"/> 
  <c:if test="${sessionScope.ELEVATOR_UPLOAD_TEST_ACTION == 'N'}">
              <br/><span class="error">* please enter State Number  </span> 
            </c:if>
   <input type="submit" value="Submit Test Reports" class="button"/>
 </html:form>
</p>
<c:if test="${sessionScope.DFBS_SECURITY != null}"> 
<html:form action="/elevator" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="processTests"/> 
  <input type="submit" value="Process Test Reports" class="button"/>
 </html:form></br></br>
 <html:form action="/servContractor" method="post">
  <input type="submit" value="Service Contractors" class="button"/>
 </html:form>
</c:if>
</div>

<div class="clearer">&nbsp;</div>
</c:if>

<jsp:include page="/app/common/hsPageFooter.jsp" /> 