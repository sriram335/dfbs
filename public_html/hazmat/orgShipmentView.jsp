
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>


<hs:control/>

<c:set var="module" scope="request" value="IDHS - HAZMAT DIVISION" />
<c:set var="title" scope="request" value="Apply for Hazardous Material Transport Permit" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/orgShipmentView" method="get" styleId="filterByTimePeriod">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byTimePeriod" />
          permit month year: <html:select property="monthYear" onchange="submitForm('filterByTimePeriod')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="ORG_TIME_PERIOD_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
     
</div>

<a   href="/dfbs/hazmat/hazmat.do?">
              [Apply for new permit]</a>
 <a   href="/dfbs/hazmat/hazmat.do?method=hazmatApp">
              [back to application]</a>             
<div id="mainContentFluid" align="left">
<h2>Organizations with shipments</h2>


<div class="charNav">
<ul>
<c:forEach var="c" items="${ORG_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${ORG_LIST_FILTER.type == 'byLetter' && ORG_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/hazmat/orgShipmentView.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</c:forEach>

</ul>
</div>


  <c:forEach var="org" items="${ORG_LIST.list}" varStatus="i" >
  
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">
  Organization Name:
    <c:out value="${org.orgName}" />
  </br>
 <span class="error"> <c:out value="${org.shipPermitNumber}" /></span></br>
     <a   href="/dfbs/hazmat/hazmat.do?method=printAllPermits&receiptId=<c:out value="${org.feeId}" />&orgEmail=<c:out value="${org.orgEmail}" />">
                [ Print permit]</a></br>
    
   </a>            
    <%--span class="controlSmall" style="font-weight:bold;"><html:link page="/application.do?method=startApplication" paramId="ownerId" paramName="owner" paramProperty="ownerId">[ Start Permits Application ]</html:link></span--%>
    
  </h3>
  <p class="listingInfo">
  Address:
    <c:out value="${org.orgAddress1}" /><br />
    <c:if test="${org.orgAddress2 != null && org.orgAddress2 != ''}">
    <c:out value="${org.orgAddress2}" /><br />
    </c:if>
    <c:out value="${org.orgCity}" />, <c:out value="${org.orgState}" /> <c:out value="${org.orgZip}" />
      </p>
  <h3 style="margin-bottom:5px;">Carrier Name:<c:out value="${org.carrierName}" /><br /></h3>
   <a   href="/dfbs/hazmat/orgShipmentView.do?method=orgView&orgId=<c:out value="${org.orgId}" />&carrierId=<c:out value="${org.carrierId}" />&shipmentId=<c:out value="${org.shipmentId}" />">
    View All Details</br>
   </a> 
  Origin:<c:out value="${org.shipOrigin}" /><br />
  Destination:<c:out value="${org.shipDestination}" />
<h3 style="margin-bottom:5px;"> Shiment Date:<c:out value="${org.shipDateString}" /><br /></h3>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
