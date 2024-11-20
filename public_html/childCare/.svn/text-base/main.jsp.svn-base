<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Daycare Facilities" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">



<div id="sideContentFluid">

  
    
    <h2>Advanced Search Options</h2>
             
          <p>
          <html:form action="/childcare" method="get" styleId="filterByCity">
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
          <html:form action="/childcare" method="get" styleId="filterByCounty">
          <input type="hidden"  name="method" value="filter" />
          <input type="hidden"  name="filter" value="byCounty" />
         by Indiana county: <html:select property="county" onchange="submitForm('filterByCounty')">
           <html:option value="">-----</html:option>
            <html:options 
              collection="DFBS_COUNTY_OPTIONS" 
              property="value" 
              labelProperty="label" />
          </html:select>
          <input type="submit" value="get" class="smallButton" />
          </html:form>
          </p>   
           <html:form action="/start" method="post">
          <input type="hidden" name="method" id="METHOD_KEY" value="renewByStreetNumber"/>by Indiana Street Address: 
          
            <html:text property="streetNumber" size="10" maxlength="10"/>
         
          <input type="submit" value="Select the Child Care Facility by street address" class="button"/></br>*Note: for 302 West Washington St, enter 302 in the box, for Rural Road 400 enter Rural
             <c:if test="${START_FORM_ERROR.streetNumber == 0}">
             <span class="error">
             * please enter street number</span> 
            </c:if></br></br></br>
           </html:form>
           <html:form action="/start" method="post">
        <input type="hidden" name="method" id="METHOD_KEY1" value="renewByPermitNumber"/>
            <p>
            Child Care Facility Id:
            
              <html:text property="permitNumber" size="12" maxlength="12"/>
           
            </br>
              <input type="submit" value="Select the Child Care Facility by identification number" class="button"/>
            </p>
          
          <c:if test="${START_FORM_ERROR.permitNumber =='ERROR'}">
          <span class="error">* please enter identification number</span>
        </c:if> </br></br></br></br>
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
             
             
           
                  
        
   
</div>


<div id="mainContentFluid" align="left">

<h2><c:out value="${sessionScope.STRUCTURE_TYPE}" /> in the database</h2> 

 <html:link page="/start.do">[start over]</html:link>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
 <html:link page="/application.do?method=startApplication">[Add New Owner and New Facility]</html:link>
</c:if>

 <c:if test="${sessionScope.STRUCTURE_TYPE == 'Child Care Ministries'}">
       <a   href="/dfbs/childCare/childcare.do?method=downLoadFile&fileName=Child Care Room List Sample.pdf&fileId=1131&fileType=MASTER" target="_blank">
             [sample room list ]</a></br>
 </c:if></br></br>



<div class="charNav">
<ul>
<c:forEach var="c" items="${DFBS_CHILDCARE_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${DFBS_CHILDCARE_LIST_FILTER.type == 'byLetter' && DFBS_CHILDCARE_LIST_FILTER.value == c.character}">
  <li class="selected" >
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/childCare/childcare.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="permit" items="${DFBS_CHILDCARE_LIST.list}" varStatus="i" >
  <hr/>
 
  <h3 style="margin-bottom:5px;">
     <c:out value="${permit.daycareName}" />
    
  </h3>
        Permit Number:  <c:out value="${permit.permitNumber}"/>&nbsp; </br>
        Inspector Assigned:<c:out value="${permit.inspectorName}"/>&nbsp;</br> 
        Facility Type: <c:out value="${permit.structureType}"/>&nbsp;</br> 
 <span class="error">      IDHS Application Received Date: <c:out value="${permit.receivedDateString}"/>&nbsp;</br> </span>
        
    Address:
    <c:out value="${permit.address1}" /><br />
    <c:if test="${permit.address2 != null && permit.address2 != ''}">
    <c:out value="${permit.address2}" /><br />
    </c:if>
    <c:out value="${permit.city}" />, <c:out value="${permit.state}" /> <c:out value="${permit.zip}" /></br>
    County:<c:out value="${permit.county}" />
    <br />
   <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${permit.permitNumber}"/>">
               [ Inspections List for this permit ]</a>
         
          <c:if test="${permit.structureType =='Child Care Ministries'}">
        <a   href="/dfbs/childCare/childcare.do?method=renewPermit&permitKey=<c:out value="${permit.permitNumber}"/>&ownerKey=<c:out value="${permit.ownerId}" />">
             [apply for renewal and pay inspection fee ]</a>   
        </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
  <a   href="/dfbs/childCare/childcare.do?method=updatePermit&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [update permit]</a>
       <c:if test="${permit.substrPermitNumber == 'NEW'}">
          <a   href="/dfbs/childCare/childcare.do?method=reAssignId&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [ re-assign ID ]</a>
          </c:if>
    </c:if>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th ><c:out value="${sessionScope.STRUCTURE_TYPE}" /> Facility - Tracking information</th>
  </tr>
  <tbody>
      <tr class="row<c:out value='${(j.index % 2) + 1}' />">
      <td>
        FSSA Registration Expiration Date:  <c:out value="${permit.fssaExpDateString}"/>&nbsp; </br>
        FSSA Status of the facility: <c:out value="${permit.fssaStatus}"/></br>
        Facility last inspected by IDHS:<c:out value="${permit.lastInspDateString}"/>&nbsp;</br> 
        Last compliance inspection date :<c:out value="${permit.lastInspCompDateString}"/>&nbsp;</br> 
         </td>
         
      </tr>
    
  </tbody>
</table>
 </c:forEach>
 


 </div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
