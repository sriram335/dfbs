 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>

<hs:control />

<c:set var="module" scope="request" value="Fireworks" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<jsp:include page="module/breadcrumbs.jsp" />

<c:if test="${FIREWORKS_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.FIREWORKS_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${FIREWORKS_APP_STATUS != 'I'}">
<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2> Advance Search Options</h2>
             
          <p>
          <html:form action="/main" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          by Owner Indiana city: <html:select property="city" onchange="submitForm('filterByCity')">
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
         fireworks facility county: <html:select property="county" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="DFBS_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   </br>
            
          <p>
          <html:form action="/main" method="get" styleId="filterByOtherStates">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byOtherState" />
          by other state: <html:select property="state" onchange="submitForm('filterByOtherStates')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="DFBS_OTHER_STATES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>  </br> 
          <html:form action="/main" method="post">
           <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byFacAddress" />
           Owner Street Address: 
                  <html:text property="street1" size="10" maxlength="10"/>
            </br>
            <input type="submit" value="Select the owner by street address" class="button"/></br>
               <c:if test="${SEARCH_STREET1 == 'ERROR'}">
               <span class="error">
               * please enter street number (enter at least 3 characters/ numbers)</span> </br>
              </c:if>*Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural</br></br></br>
          </html:form>
          <html:form action="/main" method="post">
         <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byPermit" />
            <p>
            Permit Number:
               <html:text property="street2" size="12" maxlength="12"/>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/>
            </p>
          
          <c:if test="${SEARCH_PERMIT =='ERROR'}">
          <span class="error">* please enter permit number</span>
        </c:if> </br></br></br></br>
      </html:form>
          
           <h2>Contact Us</h2>
          <p>
           You can contact us via email or by phone (click the name to launch email)
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/> </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>
             
             
           
                  
        
   
</div>


<div id="mainContentFluid" align="left">
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null  }">  
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=How to add a Inspection.doc&fileId=284&fileType=MASTER" target="_blank">
             [Help file on how to add a inspection]</a></br>
</c:if>
<h2>Authorized Fireworks Sellers</h2></br>
<p class="messageBox">
If you sell Fireworks in the State of Indiana, it is required that you register for the Public Safety Fee by calling 317-615-2544 and select option 2.
</p>
<div class="messageBox">
<c:choose>
<c:when test="${sessionScope.DFBS_OWNER_APPLICATION == null}">

<strong>Are you a new fireworks seller?</strong>
  <span class="control2">
    <html:link page="/application.do?method=startApplication">Yes, start my permits application... </html:link>
  </span>
  
  <p>
  If not, select your company name and click "Start Permits Application" (Click on the first letter of company name to select your company)
  </p>

</c:when>
<c:otherwise>
  <p>
 <span class="control2">
  <html:link page="/main.do?method=view" paramId="ownerId" paramName="DFBS_OWNER" paramProperty="ownerId">
          resume session for <c:out value="${DFBS_OWNER.ownerName}" />"
        </html:link> </span></br>
 <span class="control2">
    <html:link page="/start.do"> Or click here to Start Over </html:link>
  </span>
  </p>
</c:otherwise>
</c:choose>
<p>
<font color="red">For Stand/Sec8a Permits Contact Chris Clouse@317-232-1407</font>
    </p>
</div>





<div class="charNav" align="left">
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
<c:url var="url" value="/fireworks/main.do?method=filter&filter=byLetter&letter=${c.character}" />
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
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
