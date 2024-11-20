<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Institution's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/institution" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          by Indiana city: <html:select property="institutionCity" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="INSTITUTION_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
          
      
        
           
              
          <p>
          <html:form action="/institution" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
          by indiana county: <html:select property="institutionCounty" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="INSTITUTION_COUNTY_OPTIONS" 
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
         
   <c:if test="${sessionScope.NEW_COURSE  != null }"> 
  <jsp:include page="/ems/newCourseStatus.jsp"/>
  </c:if>          
             
           
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Current EMS service institutions registered with state of Indiana</h2>
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br></br>

<div class="charNav">
<ul>
<c:forEach var="c" items="${INSTITUTION_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${INSTITUTION_LIST_FILTER.type == 'byLetter' && INSTITUTION_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/ems/institution.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</c:forEach>
</ul>
</div>


  <c:forEach var="institution" items="${INSTITUTION_LIST.list}" varStatus="i" >
    <c:if test="${sessionScope.USER_SEC_FLAG == 'Yes'}">
  <a   href="/dfbs/ems/main.do?method=addInstitutionUser&institutionId=<c:out value="${institution.institutionId}" />">
              [ add institution to user ]</a> 
  </c:if>
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/institution.do?method=institutionDetail" paramId="institutionId" paramName="institution" paramProperty="institutionId">
    <c:out value="${institution.institutionName}" />
    </html:link>
   </h3>
    <p class="listingInfo">
     
    <c:out value="${institution.institutionAddress1}" />
 
    <c:if test="${institution.institutionAddress2 != null && institution.institutionAddress2 != ''}">
    <c:out value="${institution.institutionAddress2}" /><br />
    </c:if>
    <c:out value="${institution.institutionCity}" />, <c:out value="${institution.institutionState}" /> <c:out value="${institution.institutionZip}" /><br />
    <c:out value="${institution.institutionCounty}" />
    <br />
  </p>
 <a href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${institution.institutionId}"/>">
            [Inst.Detail (create course,update contacts if you are an authorized user)]  </a> 
<br />
  </div>
         
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
