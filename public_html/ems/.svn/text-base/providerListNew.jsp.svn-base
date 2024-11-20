<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Provider's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/provider" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          by Indiana city: <html:select property="providerCity" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="PROVIDER_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
          
      
        
           
              
          <p>
          <html:form action="/provider" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
          by indiana county: <html:select property="providerCounty" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="PROVIDER_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
          
          <p>
          <html:form action="/provider" method="get" styleId="filterByLevel">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byLevel" />
          by level: <html:select property="providerLevel" onchange="submitForm('filterByLevel')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="PROVIDER_LEVELS_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
          
          
          <h2>Contact Us</h2>
          <p>
          You contact by email emsdata@dhs.in.gov</a> or by phone
          </p>
          <p>
         Angie Biggs  : <strong>317-232-1404</strong>
          </p>
         
<c:if test="${sessionScope.RENEW_PROVIDER  != null }"> 
  <jsp:include page="/ems/renewalProviderStatus.jsp"/>
  </c:if>           
             
           
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Current EMS service providers registered with state of Indiana</h2>
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br></br>

<div class="charNav">
<ul>
<c:forEach var="c" items="${PROVIDER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${PROVIDER_LIST_FILTER.type == 'byLetter' && PROVIDER_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/ems/provider.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</c:forEach>
</ul>
</div>


  <c:forEach var="provider" items="${PROVIDER_LIST.list}" varStatus="i" >
  <c:if test="${sessionScope.PROVIDER_HOSP_FLAG == 'Yes'}">
  <a   href="/dfbs/ems/institution.do?method=addProvider&providerId=<c:out value="${provider.providerId}" />">
              [ add this provider to hospital ]</a> 
  </c:if>
   <c:if test="${sessionScope.USER_SEC_FLAG == 'Yes'}">
  <a   href="/dfbs/ems/main.do?method=addProviderUser&providerId=<c:out value="${provider.providerId}" />">
              [ add provider to user ]</a> 
  </c:if>
 
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/provider.do?method=providerDetail" paramId="providerId" paramName="provider" paramProperty="providerId">
    <c:out value="${provider.providerName}" />
    </html:link> </h3>
    
   <c:if test="${sessionScope.RENEW_PROVIDER  != null }"> 
 <a href="/dfbs/ems/provider.do?method=renewProvider&providerId=<c:out value="${provider.providerId}"/>">
            [Apply for License Renewal]  </a> 
 </c:if>   
 
  <p class="listingInfo">
    <c:out value="${provider.providerAddress1}" /><br />
    <c:if test="${provider.providerAddress2 != null && provider.providerAddress2 != ''}">
    <c:out value="${provider.providerAddress2}" /><br />
    </c:if>
    <c:out value="${provider.providerCity}" />, <c:out value="${provider.providerState}" /> <c:out value="${provider.providerZip}" /><br />
    <c:out value="${provider.providerCounty}" />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
