<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Vehicle's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">

     

     
<div id="mainContentFluid" align="left">

<h2>Current Contact vehicles for the provider</h2>

<a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ back to provider ]</a> 
     <a   href="/dfbs/ems/provider.do?method=providerList">
              [ back to provider list ]</a> 
     <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
<html:form action="/vehicle" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addNewVehicle"/> 

<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Apply for new ambulance permit" styleClass="button" />
 </p>
</c:if>
</html:form>
  <c:forEach var="vehicle" items="${VEHICLE_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
    <html:link page="/vehicle.do?method=vehicleDetail" paramId="vehicleId" paramName="vehicle" paramProperty="vehicleId">
   Ambulance Certification Number: <c:out value="${vehicle.certNumber}" /><br />
    </html:link>
   </h3>
  <p class="listingInfo">
    expiration date:<c:out value="${vehicle.expDateString}" /><br />
    vin:<c:out value="${vehicle.modelVin}" /><br />
    make:<c:out value="${vehicle.modelMake}" /><br />
    year:<c:out value="${vehicle.modelYear}" /><br />
    type:<c:out value="${vehicle.modelType}" /><br />
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
     <a   href="/dfbs/ems/vehicle.do?method=renewVehicle&vehicleId=<c:out value="${vehicle.vehicleId}" />">
              [ renew ambulance permit ]</a> 
    </c:if>
  </p>
  </div>
 
  </c:forEach>

</div>

<div class="clearer">&nbsp;</div>

</div>
<jsp:include page="/app/common/hsPageFooter.jsp" />