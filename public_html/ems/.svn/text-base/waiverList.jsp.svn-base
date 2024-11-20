<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Waiver's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">
<html:form action="/waiver" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addNewWaiver"/> 


<div id="mainContentFluid">
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 
     <a href="/dfbs/ems/level.do?method=levelDetail&levelId=<c:out value="${LEVEL.levelId}"/>">
            [back to level ]  </a>  
     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to provider]  </a>               
            
<h2>Current Contact waivers for the provider</h2>





  <c:forEach var="waiver" items="${WAIVER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
    
  </h3>
  <p class="listingInfo">
    <c:out value="${waiver.waiverCode}" /><br />
    <c:out value="${waiver.waiverDateString}" /><br />
  </p>
  </div>
 
  </c:forEach>

</div>
<c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' }">
<p>
   
          <html:submit value="Add Waiver" styleClass="button" />
 </p>
 </c:if>
</html:form>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
