<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Shipment Complete Details</h2> 
<div id="sideContentFluid">
<a   href="/dfbs/hazmat/orgShipmentView.do?&mehtod=backToView">
              [back]</a>
</div>
<div align="left">
<c:if test="${sessionScope.ORG_VIEW.orgId != null}">    
<u> Organization Details:</br></u>
 <h3 style="margin-bottom:5px;">Organization Name: <c:out value="${sessionScope.ORG_VIEW.orgName}"/></br></h3>
 Contact name: <c:out value="${sessionScope.ORG_VIEW.orgContact}"/></br>
 Contact Title: <c:out value="${sessionScope.ORG_VIEW.orgTitle}"/></br>
<u> Organization Address1:</u></br>
                      <c:out value="${sessionScope.ORG_VIEW.orgAddress1}"/></br>
  <c:if test="${sessionScope.ORG_VIEW.orgAddress2 != null}">                     
                       <c:out value="${sessionScope.ORG_VIEW.orgAddress2}"/></br>
  </c:if>
                       <c:out value="${sessionScope.ORG_VIEW.orgCity}"/>,<c:out value="${sessionScope.ORG_VIEW.orgState}"/> <c:out value="${sessionScope.ORG_VIEW.orgZip}"/> -<c:out value="${sessionScope.ORG_VIEW.orgZip2}"/></br> 
Phone:<c:out value="${sessionScope.ORG_VIEW.orgPhone}"/></br>
Fax  :<c:out value="${sessionScope.ORG_VIEW.orgFax}"/></br>
Email:<c:out value="${sessionScope.ORG_VIEW.orgEmail}"/></br></br></br>

</c:if>  
<c:if test="${sessionScope.CARRIER_VIEW.carrierId != null }">     
 <u>Carrier Details:</u></br></br>
  <h3 style="margin-bottom:5px;">Carrier Name:<c:out value="${sessionScope.CARRIER_VIEW.carrierName}"/></br></h3>
 Contact name: <c:out value="${sessionScope.CARRIER_VIEW.carrierContact}"/></br>
 Contact Title: <c:out value="${sessionScope.CARRIER_VIEW.carrierTitle}"/></br>
 Phone:<c:out value="${sessionScope.CARRIER_VIEW.carrierPhone}"/></br>
 Fax  :<c:out value="${sessionScope.CARRIER_VIEW.carrierFax}"/></br>
 Email:<c:out value="${sessionScope.CARRIER_VIEW.carrierEmail}"/></br></br></br>
</c:if> 
<c:if test="${sessionScope.SHIPMENT_VIEW.shipmentId != null }">     
 <u>Shipment Details:</u></br></br>
 <h3 style="margin-bottom:5px;">Radiation Level:<c:out value="${sessionScope.SHIPMENT_VIEW.shipRadLevel}"/></br>
 Permit Number:<c:out value="${sessionScope.SHIPMENT_VIEW.shipPermitNumber}"/></h3></br>
 Shipment Date:<c:out value="${sessionScope.SHIPMENT_VIEW.shipDateString}"/></br>
 Application date:<c:out value="${sessionScope.SHIPMENT_VIEW.shipAppDateString}"/></br>
 Issue Date:<c:out value="${sessionScope.SHIPMENT_VIEW.shipIssueDateString}"/></br>
 Expiration Date:<c:out value="${sessionScope.SHIPMENT_VIEW.shipExpDateString}"/></br>
 Origin:<c:out value="${sessionScope.SHIPMENT_VIEW.shipOrigin}"/></br>
 Destination:<c:out value="${sessionScope.SHIPMENT_VIEW.shipDestination}"/></br>
 Ampunt Shipped :<c:out value="${sessionScope.SHIPMENT_VIEW.shipAmount}"/></br>
 Material Type :<c:out value="${sessionScope.SHIPMENT_VIEW.shipMaterialType}"/></br>
 Isotope:<c:out value="${sessionScope.SHIPMENT_VIEW.shipIsotope}"/></br>
 Isotope Activity:<c:out value="${sessionScope.SHIPMENT_VIEW.shipActIsotope}"/></br>
 vicvsa Inspection:<c:out value="${sessionScope.SHIPMENT_VIEW.shipVicvsa}"/></br>
 vicvsa Inspection state:<c:out value="${sessionScope.SHIPMENT_VIEW.shipVicvsaState}"/></br>
 Comments:<c:out value="${sessionScope.SHIPMENT_VIEW.shipComments}"/></br>
 Shipment route:<c:out value="${sessionScope.SHIPMENT_VIEW.shipRoute}"/></br>
 
</c:if> 
</div>
