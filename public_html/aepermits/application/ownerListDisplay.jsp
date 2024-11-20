<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<hs:control/>

<c:set var="module" scope="request" value="Entertainment Permits" />
<c:set var="title" scope="request" value="Owner List" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/aepermits/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/aepermits/start.do?method=renewBy">
              [back to search]</a>
<div id="mainBox">
 <div id="sideContentFluid">
  <jsp:include page="feeDetails.jsp"/>
  </div>


<div id="sideContentFluid">

          
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
             
      <c:if test="${sessionScope.DFBS_SECURITY != null}">  
       <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY"  value="showNewPermits"/>
            <p>
              <input type="submit" value="Show New Permits" class="button"/></br></br></br>
            </p>
            
        </html:form>
      </c:if>       
           
                  
        
   
</div>


<div id="mainContentFluid">

<h2>Annual Entertainment Permit Holders from Previous Years</h2>


 <html:link page="/application.do?method=startOverForm">[delete and start over]</html:link>
<c:forEach var="owner" items="${DFBS_OWNER_LIST_BY_STREET.list}" varStatus="i" >
  <div class="listing">
   <h3 style="margin-bottom:5px;">
   <c:out value="${owner.ownerName}" /></h3>
  <p class="listingInfo">
    <c:out value="${owner.street1}" /><br />
    <c:if test="${owner.street2 != null && owner.street2 != ''}">
    <c:out value="${owner.street2}" /><br />
    </c:if>
    <c:out value="${owner.city}" />, <c:out value="${owner.state}" /> <c:out value="${owner.zip}" /><br />
    <br />
  </p>
  <h3 style="margin-bottom:5px;">
    <a   href="/dfbs/aepermits/main.do?method=view&ownerId=<c:out value="${owner.ownerId}"/>">
             [ Process this owner permits ]</a></br>
  
   </h3>
 <table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >permit number</th>
    <th>Address </th>
  </tr>
  <tbody>
    <c:forEach var="permit" items="${owner.permits}" varStatus="i" >
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
     
        Permit Number:  <c:out value="${permit.permitNumber}"/>&nbsp; </span>
        Inspector Assigned:<c:out value="${permit.inspectorName}"/>&nbsp;</br> 
        <a   href="/dfbs/aepermits/inspection.do?method=inspectionList&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [ Inspections List for this permit ]</a>
        <a   href="/dfbs/aepermits/permit.do?method=viewPermit&permitNumber=<c:out value="${permit.permitNumber}"/>">
             [view this permit ]</a>   
       
          </td>
          <td>
          <c:out value="${permit.street1}"/>
          <br/>
          <c:if test="${permit.street2 != null && permit.street2 != ''}">
            <c:out value="${permit.street2}"/>
            <br/>
          </c:if>
          <c:out value="${permit.city}"/>,
          <c:out value="${permit.state}"/>
          <c:out value="${permit.zip}"/>&nbsp;</br>
          <c:out value="${permit.county}"/> County;
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
 </c:forEach>
 </div></br>
</div>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
