<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Fire Display  Permits" />
<c:set var="title" scope="request" value="Owner List" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/display /start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/display /start.do?method=renewBy">
              [back to search]</a>
<div id="mainBox">



<div id="sideContentFluid">
 

  
    
    <h2>More Filter Options</h2>
             
          <p>
          <html:form action="/ownerListDisplay" method="get" styleId="filterByCity">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCity" />
          display city: <html:select property="city" onchange="submitForm('filterByCity')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="OWNER_CITIES_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>    
               
          <p>
          <html:form action="/ownerListDisplay" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
         display county: <html:select property="county" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="OWNER_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
           <html:form action="/ownerListDisplay" method="post">
           <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="renewByStreetNumber" />Owner Street Address: 
                  <html:text property="ownerAddress1" size="10" maxlength="10"/>
            </br>
            <input type="submit" value="Select the permit by street address" class="button"/></br>*Note: for 302 West Washington ST, enter 302 in the box, Rural Road 400 enter Rural
               <c:if test="${START_FORM_ERROR.streetNumber == 0}">
               <span class="error">
               * please enter street number</span> 
              </c:if></br></br></br>
          </html:form>
          <html:form action="/ownerListDisplay" method="post">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="renewByPermitNumber" />
           <p>
            Permit Number:
               <html:text property="ownerAddress2" size="12" maxlength="12"/>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter permit number</span>
        </c:if> </br></br></br></br>
      </html:form>
          <html:form action="/ownerListDisplay" method="post">
           <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="renewByOwnerName" />
            <p>
            Owner Name like:
               <html:text property="ownerDBA" size="12" maxlength="12"/>
            </br>
              <input type="submit" value="Select the permit by permit number" class="button"/></br>*Note: For all variations of Eli Lilly enter Lilly in the box
               <c:if test="${START_FORM_ERROR.ownerDBA =='ERROR'}">
               <span class="error">
               * please enter owner doing business as</span> 
              </c:if></br></br></br>
      </html:form> 
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
             
  <jsp:include page="feeDetails.jsp"/>
           
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2>Fire Display  Owner List </h2>

 <c:out value="${DFBS_SECURITY.groupLevelAll}"/>






<div class="charNav">
<ul>
<c:forEach var="c" items="${OWNER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${OWNER_LIST_FILTER.type == 'byLetter' && OWNER_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
  <li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/display/ownerListDisplay.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="owner" items="${DFBS_OWNER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <c:out value="${owner.ownerLastName}" />
  </h3>
   <p class="listingInfo">
    <c:out value="${owner.ownerAddress1}" /><br />
    <c:if test="${owner.ownerAddress2 != null && owner.ownerAddress2 != ''}">
    <c:out value="${owner.ownerAddress2}" /><br />
    </c:if>
      <c:out value="${owner.ownerCity}" />, <c:out value="${owner.ownerState}" /> <c:out value="${owner.ownerZip}" /><br />
    <br />
 </p>
   
  
<%-- <a   href="/dfbs/display/display.do?method=addPermit&ownerKey=<c:out value="${owner.ownerId}"/>">
             [Add new display to this owner]</a>  --%>
 <a   href="/dfbs/display/ownerListDisplay.do?method=renew&idNumber=0&ownerId=<c:out value="${owner.ownerId}"/>">
             [ Add new display to this owner ]</a>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >permit number</th>
    <th>Address </th>
  </tr>
  <tbody>
    <c:forEach var="display" items="${owner.permits}" varStatus="i" >
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:small;">
     
        Permit Number:   <c:out value="${display.displayIdNumber}"/></span><br/>
        Issue Date:  <c:out value="${display.displayIssueDateString}"/>&nbsp; <br/>
        <c:out value="${display.displayCountyName}"/> County; 
       <span class="error"> Status:<c:out value="${display.displayOnlineStatus}"/>  </span>
      <c:if test="${display.displayOnlineStatus == 'New'}">
      <p class="message">Application in Process.</p>
      </c:if>
        <c:if test="${display.displayOnlineStatus != 'New'}">
     </br>  <a   href="/dfbs/display/ownerListDisplay.do?method=renew&idNumber=<c:out value="${display.displayIdNumber}"/>&ownerId=<c:out value="${owner.ownerId}"/>">
             [ apply for permit at this location ]</a>
       </c:if>
        <c:if test="${display.displayValid >= 0 && display.displayOnlineStatus != 'New'}">
       <a   href="/dfbs/display/display.do?method=printPermit&displayPermitNumber=<c:out value="${display.displayIdNumber}" />">
                [ view permit]</a><br/>
      </c:if>
       <a   href="/dfbs/display/display.do?method=viewPermit&displayKey=<c:out value="${display.displayIdNumber}" />">
                [ view permit information]</a><br/>
         <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
          <a   href="/dfbs/display/display.do?method=update&displayKey=<c:out value="${display.displayIdNumber}" />">
                [ update permit]</a><br/>
        
            <a   href="/dfbs/display/display.do?method=goToUploadInternal&displayKey=<c:out value="${display.displayIdNumber}"/>">
              <span style="font-size:x-small">[Upload file]</span></a>
          </c:if> 
       </td>
       <td>
          <c:out value="${display.displayAddress1}"/>
          <br/>
          <c:if test="${display.displayAddress2 != null && display.displayAddress2 != ''}">
            <c:out value="${display.displayAddress2}"/>
            <br/>
          </c:if>
          <c:out value="${display.displayCity}"/>,
          <c:out value="${display.displayState}"/>
          <c:out value="${display.displayZip}"/>&nbsp;</br>
            
         
        </td>
      </tr>
     </c:forEach>
  </tbody>
</table>
 
  </div>
  </c:forEach>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
</div>