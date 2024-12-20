<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Display Permits" />
<c:set var="title" scope="request" value="Display Permits Application" />



<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${FIRE_DISPLAY_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.FIRE_DISPLAY_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${FIRE_DISPLAY_APP_STATUS != 'I'}">
<h2>Start Application</h2>

<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/display/display/permit.do?method=downLoadFile&fileName=How to add a Inspection.doc&fileId=284&fileType=MASTER" target="_blank">
             [Help file on how to add a inspection]</a></br>
        </c:if>
 <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
</div>
<div id="leftContentFluid">
 <p class="error" size="100">
 <u><b>IMPORTANT NOTE:<b></u> Please contact the Fire Department having jurisdiction before you shoot, for "site approval" and to verify the physical address
  of the Fire Department and correct email address in order to correctly process this application. If you do not have this information,
  <u><b>STOP!</b></u> Obtain this information before starting the application process. After the permit
 is processed, it will be emailed to the responding Fire Chief on the application.</br>
 <u><b>Other information needed to complete the application:<b></u> </br>1.Electronic copy of Display Shooter's resume for document upload is required.</br>
 2. Credit card information to pay for the online permit.
 </p>
 <c:if test="${sessionScope.DFBS_SECURITY != null}">  
<p class="message">
Welcome <c:out value="${sessionScope.DFBS_SECURITY.userId}"/>, 
</p>
</c:if>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="renewBy"/>
  <p class="messageBox" size="100">
       If you have a previous display permit from the State of Indiana and would like to apply&nbsp;for a new one at&nbsp;the&nbsp;same&nbsp;location / view it.</br> 
      <b> click below</b> </p>
        <input type="submit" value="Old location new permit or View information" class="button"/>
      
      
</html:form>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="newPermitNewOwner"/>
     <p class="messageBox" size="100">
       If you never had a display permit from the State of Indiana and would like to apply for display permit with new owner name and address </br> 
      <b> click below</b> </p>
      <p>
        <input type="submit" value="New owner & New permit" class="button"/>
      </p>
      
</html:form>

<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="newPermitOldOwner"/>
      <p class="messageBox" size="100">
       If you have a previous display permit from the State of Indiana and would like to add a new display permit with same owner name and address </br> 
      <b> click below</b> </p>
      <p>
        <input type="submit" value="Existing Owner & New Permit" class="button"/>
      </p>
       
</html:form>


</div>

<div class="clearer">&nbsp;</div>
</c:if>

<jsp:include page="/app/common/hsPageFooter.jsp" /> 
<%--
 This application is under maintenance. come back later.--%>