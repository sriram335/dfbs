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
<div id="sideContentFluid">
<c:if test="${sessionScope.RENEW_PROVIDER  != null }"> 
  <jsp:include page="/ems/renewalProviderStatus.jsp"/>
  </c:if>  
</div>
<html:form action="/providerRenewal" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveRenewalVehicleList"/> 
     

     
<div id="mainContentFluid" align="left">

<h2>Current vehicles listed in our data base for the provider</h2>

 <p class="messageBox" size="100">
 Verify the list of vehicles, If you still have all the vehicles, update the Location of the vehicles.
 If any of the vehicles are inactive then write inactive in the location.
 
 </p>



  <c:forEach var="vehicle" items="${VEHICLE_RENEWAL_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
   certification Number: <c:out value="${vehicle.certNumber}" />,expiration date:<c:out value="${vehicle.expDateString}" /><br />
   </h3>
  <p class="listingInfo">
    vin:<c:out value="${vehicle.modelVin}" /><br />
    make:<c:out value="${vehicle.modelMake}" /><br />
    year:<c:out value="${vehicle.modelYear}" /><br />
    type:<c:out value="${vehicle.modelType}" /><br />
    Location: <input type= "text" name="<c:out value="${vehicle.vehicleId}" />"  size="100" maxlength="100"/>
    <br />
  </p>
  </div>
 
  </c:forEach>


<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Complete Renewal Process" styleClass="button" />
 </p>
</c:if>
</html:form>
</div>
<div class="clearer">&nbsp;</div>

</div>
<jsp:include page="/app/common/hsPageFooter.jsp" />