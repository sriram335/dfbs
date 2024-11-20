<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Entertainmetnt Permits" />
<c:set var="title" scope="request" value="Entertainment (Annual/Special) Permits Application" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<jsp:include page="module/breadcrumbs.jsp" />


<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>Advance Search Options</h2>
             
          <p>
          <html:form action="/main" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          by Indiana city: <html:select property="city" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="DFBS_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
          
           <p>
          <html:form action="/main" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
         AE facility county: <html:select property="county" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="DFBS_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   </br>
           <html:form action="/start" method="post">
            <input type="hidden" name="method" id="METHOD_KEY" value="renewByStreetNumber"/>Facility Location Street Address: 
                  <html:text property="streetNumber" size="10" maxlength="10"/>
            </br>
            <input type="submit" value="Select the permit by street address" class="button"/></br>*Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural
               <c:if test="${START_FORM_ERROR.streetNumber == 0}">
               <span class="error">
               * please enter street number</span> 
              </c:if></br></br></br>
          </html:form>
          <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="renewByPermitNumber"/>
            <p>
            Permit Number:
               <html:text property="permitNumber" size="12" maxlength="12"/>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter permit number</span>
        </c:if> </br></br></br></br>
      </html:form>
      <c:if test="${sessionScope.DFBS_SECURITY != null}">  
       <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="showNewPermits"/>
            <p>
              <input type="submit" value="Show New Permits" class="button"/></br></br></br>
            </p>
            
        </html:form>
      </c:if>       
             
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
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Entertainment Permit Holders from Previous Years</h2>

 <html:link page="/application.do?method=startOverForm">[delete and start over]</html:link>
<div class="messageBox">
<c:choose>
<c:when test="${sessionScope.DFBS_OWNER_APPLICATION == null}">

<strong>Are you applying for this permit for the first time?</strong></br>
  <span class="control2">
    <html:link page="/application.do?method=startApplication">Yes, start my permits application... </html:link>
  </span>
  <p>
  If not, select your company name and click "Start Permits Application"
  </p>

</c:when>
<c:otherwise>
  <p>
 <span class="control2">
  <html:link page="/application.do?method=intro">Continue your application in session for... "<c:out value="${sessionScope.DFBS_OWNER_APPLICATION.ownerName}" />"</html:link>
 </span>
  </p>
</c:otherwise>
</c:choose>
</div>





<div class="charNav">
<ul>
<c:forEach var="c" items="${DFBS_OWNER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${DFBS_OWNER_LIST_FILTER.type == 'byLetter' && DFBS_OWNER_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/aepermits/main.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="owner" items="${DFBS_OWNER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/main.do?method=view" paramId="ownerId" paramName="owner" paramProperty="ownerId">
    <c:out value="${owner.ownerName}" />
    </html:link>
    
    <%--span class="controlSmall" style="font-weight:bold;"><html:link page="/application.do?method=startApplication" paramId="ownerId" paramName="owner" paramProperty="ownerId">[ Start Permits Application ]</html:link></span--%>
    
  </h3>
  <p class="listingInfo">
    <c:out value="${owner.street1}" /><br />
    <c:if test="${owner.street2 != null && owner.street2 != ''}">
    <c:out value="${owner.street2}" /><br />
    </c:if>
    <c:out value="${owner.city}" />, <c:out value="${owner.state}" /> <c:out value="${owner.zip}" /><br />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
