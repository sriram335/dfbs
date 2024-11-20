<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Fireworks Seller"/>
<c:set var="level" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="mainContentFluid">
   <a   href="/dfbs/fireworks/main.do?method=showCounty&county=<c:out value="${OTHER_COUNTY}"/>&recFlag=PENDING">
           <b>Show Pending</b></a>&nbsp;&nbsp;
    <a   href="/dfbs/fireworks/main.do?method=showCounty&county=<c:out value="${OTHER_COUNTY}"/>&recFlag=APPROVED">
           <b>Show Approved</b></a>&nbsp;&nbsp;
    <a   href="/dfbs/fireworks/main.do?method=showCounty&county=<c:out value="${OTHER_COUNTY}"/>&recFlag=DENIED">
           <b>Show Denied</b></a>&nbsp;&nbsp;
     <a   href="/dfbs/fireworks/main.do?method=showCounty&county=<c:out value="${OTHER_COUNTY}"/>&recFlag=ALL">
           <b>Show All</b></a>
    <div class="messageBox">
    <c:out value="${OTHER_COUNTY}" /> Fireworks Pending Approval
    </div>
    
    <c:forEach var="owner" items="${DFBS_OWNER_COUNTY_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <c:out value="${owner.ownerName}" />
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
 <table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>permit #</th>
    <th>address</th>
    <th>type</th>
    <th>status</th>
  </tr>
  <tbody>
    <c:forEach var="permit" items="${owner.permits}" varStatus="i">
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
         <a   href="/dfbs/fireworks/permit.do?method=viewPermit&permitNumber=<c:out value="${permit.permitNumber}"/>&permitType=Consumer">
           <b><c:out value="${permit.permitNumber}"/></b></a> ,
          Inspector Assigned:<c:out value="${permit.inspectorName}"/>&nbsp;</br>
          Comments: <c:out value="${permit.permitComments}"/>
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
          <c:out value="${permit.zip}"/>&nbsp;
        </td>
        <td>
          <c:out value="${permit.permitTypeString}"/>
         </td>
         
        <td>
          <c:if test="${FIREWORKS_COUNTY_FLAG=='PENDING'}">
        </br>
          <html:form action="/permit" method="post">
          <input type="hidden" name="method" id="METHOD_KEY" value="countyApproved"/> 
           <html:hidden property="permitNumber" value="${permit.permitNumber}"/>
                <input type="submit" value="Approved" class="button"/></br></br>
        </html:form>
          <html:form action="/permit" method="post">
            <input type="hidden" name="method" id="METHOD_KEY" value="countyDenied"/> 
            <html:hidden property="permitNumber"value="${permit.permitNumber}"/>
                  <input type="submit" value="Denied" class="button"/></br></br>
          </html:form>
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
  </c:forEach>
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
