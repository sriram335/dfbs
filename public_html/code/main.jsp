<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control />

<c:set var="module" scope="request" value="Code Enforcement" />
<c:set var="title" scope="request" value="Application for Industrialized Building Systems/ Mobile Structures" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<c:if test="${CODE_CDR_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.CODE_CDR_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${CODE_CDR_APP_STATUS != 'I'}">

<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/main" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          by Indiana city: <html:select property="manufacturerCity" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="MANUFACTURER_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
          
      
        
           
              
          <p>
          <html:form action="/main" method="get" styleId="filterByOtherStates">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byOtherState" />
          by other state: <html:select property="manufacturerState" onchange="submitForm('filterByOtherStates')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="MANUFACTURER_OTHER_STATES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
          
          
        <h2>Contact Us</h2>
          <p>
          You can contact us via email or by phone
          </p>
          <p>
         <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>
             
   <c:if test="${sessionScope.DFBS_SECURITY == null}">  
<html:form action="/main" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="logOn"/>
<input type="submit" value="For IDHS Employees" class="button"/>          
 </html:form>
 </c:if>
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Current Registered Industrialized Building Systems/ Mobile Structures  "Manufacturers" with State of Indiana</h2>


<div class="messageBox">
<c:choose>
<c:when test="${sessionScope.MANUFACTURER_APPLICATION == null}">
 
  <p>
  HELP:<br/>
  Select your company name from the list to start the process ( use the first letter in company name to filter the list)
  </p>

</c:when>

</c:choose>
</div>





<div class="charNav">
<ul>
<c:forEach var="c" items="${MANUFACTURER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${MANUFACTURER_LIST_FILTER.type == 'byLetter' && MANUFACTURER_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/code/main.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="manufacturer" items="${MANUFACTURER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/main.do?method=view" paramId="manufacturerNameId" paramName="manufacturer" paramProperty="manufacturerNameId">
    <c:out value="${manufacturer.manufacturerName}" />
    </html:link>
    
  </h3>
  <p class="listingInfo">
    <c:out value="${manufacturer.manufacturerAddress1}" /><br />
    <c:if test="${manufacturer.manufacturerAddress2 != null && manufacturer.manufacturerAddress2 != ''}">
    <c:out value="${manufacturer.manufacturerAddress2}" /><br />
    </c:if>
    <c:out value="${manufacturer.manufacturerCity}" />, <c:out value="${manufacturer.manufacturerState}" /> <c:out value="${manufacturer.manufacturerZip}" /><br />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
