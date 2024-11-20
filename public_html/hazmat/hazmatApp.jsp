<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - HAZMAT DIVISION"/>
<c:set var="title" scope="request" value="Apply for Hazardous Material Transport Permit"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${HAZ_PERMITS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.HAZ_PERMITS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${HAZ_PERMITS_APP_STATUS != 'I'}">
<div id="mainBox">
  <div id="sideContentFluid">
    <jsp:include page="shoppingCart.jsp"/>
  </div>
  <div id="mainContentFluid" align="left">
     <a   href="/dfbs/hazmat.do?method=startOver">
              [ Cancel and Start Over New ]</a>  
 
  <u><h2>STEPS TO COMPLETE THE APPLICATION</h2></u>
  <p class="error">
  1.Complete Organization information.</br>
  2.Complete Carrier information(more than one carrier can be added in a session).</br>
  3.Complete Shipment information(more than one shipment can be added in a session).</br>
  4.Pay the fees in the payment portal.</br>
  5.Return to the main application and print the permits.(use the link "Back to DHS Hazardous Material Transport Permit").</br>
  </p>
  <p class="messageBox" size=100>
  For <b>Indiana State Police</b>, use this feature to verify the authenticity of the permit.</br>
  You need permit number and valid from date to use this feature.
  Concerns? contact <A title=mailto:PMellencamp@dhs.in.gov href="mailto:PMellencamp@dhs.in.gov">Phillip Mellencamp </A> or <A title=mailto:kstuder@dhs.in.gov href="mailto:kstuder@dhs.in.gov">Kaci Studer </A>
  <html:form action="/shipment" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="verify"/>
     <input type="submit" value="verify permit Information" class="button"/>
      
  </html:form>
   </p >
  <h2>Organization Information</h2>  
  <jsp:include page="orgDisplay.jsp"/>
   <c:if test="${sessionScope.HAZMAT_ORG.orgName != null }">  
   <h2>Carrier Information</h2> 
     <c:if test="${sessionScope.HAZMAT_ORG.receiptId == '0'}"> 
        <a   href="/dfbs/hazmat/carrier.do?method=addCarrier">
                [ Add carrier information ]</a>
      </c:if>
       <jsp:include page="carrierDisplay.jsp"/>
       </c:if> 
     <c:if test="${sessionScope.HAZMAT_CARRIER.carrierName != null}">  
     <h2>Shipment(s) Information</h2> 
      <c:if test="${sessionScope.HAZMAT_ORG.receiptId == '0'}"> 
      <a   href="/dfbs/hazmat/shipment.do?method=addShipment">
            [ Add shipment information ]</a>
       </c:if>
       <jsp:include page="shipmentDisplay.jsp"/>
       </c:if> 
 </div>    
  <div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
