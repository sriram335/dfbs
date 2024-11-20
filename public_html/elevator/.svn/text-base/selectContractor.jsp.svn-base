<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Select contractor" />
<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">

<div id="sideContentFluid">
   <jsp:include page="shoppingCartContractor.jsp"/>
</div>


<div id="mainContentFluid" align="left">
<div class="messageBox">
<p>
  New Application, start click button below
  </p>
<html:form action="/servContractor" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="newContractor"/> 
      
   <p class="message">
          <html:submit value="Start New Application " styleClass="button"/>
        </p>
   </html:form>   
</div>
<h2>Elevator Service Contractor List</h2>
<div class="messageBox">

  <p>
  For Renewal Select contractor name to continue the application process.
  </p>

</div>

<div class="charNav">
<ul>
<c:forEach var="c" items="${CONTRACTOR_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${CONTRACTOR_LIST_FILTER.type == 'byLetter' && CONTRACTOR_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/elevator/servContractor.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> conts" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="cont" items="${ELEV_SERV_CONTRACTOR_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/servContractor.do?method=renew" paramId="contId" paramName="cont" paramProperty="contId">
    <c:out value="${cont.contLastName}" />&nbsp;&nbsp; <c:out value="${cont.contFirstName}" />
    </html:link>
    
  </h3>
  <p class="listingInfo">
    <c:out value="${cont.contAddress1}" /><br />
    <c:if test="${cont.contAddress2 != null && cont.contAddress2 != ''}">
    <c:out value="${cont.contAddress2}" /><br />
    </c:if>
    <c:out value="${cont.contCity}" />, <c:out value="${cont.contState}" /> <c:out value="${cont.contZip}" />
  </p>
  <a   href="/dfbs/elevator/servContractor.do?method=renew&contId=<c:out value="${cont.contId}"/>">
             [Renew this contractor]</a>
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null }"> 
   <a   href="/dfbs/elevator/servContractor.do?method=update&contId=<c:out value="${cont.contId}"/>">
             [update contractor]</a>
    </c:if>
   <a   href="/dfbs/elevator/elevator.do?method=feesBycontId&contId=<c:out value="${cont.contId}"/>">
             [pay fees for this contractor]</a>           
    
  </div>
  
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
