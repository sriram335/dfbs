<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Hospital's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/hospital" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          by Indiana city: <html:select property="city" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="HOSPITAL_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
          
      
        
           
              
          <p>
          <html:form action="/hospital" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
          by indiana county: <html:select property="county" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="HOSPITAL_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
          
          
          <h2>Contact Us</h2>
          <p>
          You contact us via <a href="/dfbs/ems/main.do?method=sendEmailForm">email</a> or by phone
          </p>
          <p>
          Angie Biggs  : <strong>317-232-1404</strong>
          </p>
         
             
             
           
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Current EMS supervising hospitals registered with state of Indiana</h2>
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br></br>

<div class="charNav">
<ul>
<c:forEach var="c" items="${HOSPITAL_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${HOSPITAL_LIST_FILTER.type == 'byLetter' && HOSPITAL_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/ems/hospital.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</c:forEach>
</ul>
</div>


  <c:forEach var="hospital" items="${HOSPITAL_LIST.list}" varStatus="i" >
   <c:if test="${sessionScope.USER_SEC_FLAG == 'Yes'}">
  <a   href="/dfbs/ems/main.do?method=addHospitalUser&hospitalId=<c:out value="${hospital.hospitalId}" />">
              [ add hospital to user ]</a> 
  </c:if>
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/hospital.do?method=hospitalDetail" paramId="hospitalId" paramName="hospital" paramProperty="hospitalId">
    <c:out value="${hospital.hospitalName}" />
    </html:link>
     
  </h3>
  <p class="listingInfo">
    <c:out value="${hospital.address1}" /><br />
    <c:if test="${hospital.address2 != null && hospital.address2 != ''}">
    <c:out value="${hospital.address2}" /><br />
    </c:if>
    <c:out value="${hospital.city}" />, <c:out value="${hospital.state}" /> <c:out value="${hospital.zip}" /><br />
    <c:out value="${hospital.county}" />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
