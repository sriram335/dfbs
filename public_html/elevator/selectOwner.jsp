<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Select Owner" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">

<div id="sideContentFluid">

           
    
                  
   <jsp:include page="shoppingCart.jsp"/>
   
</div>


<div id="mainContentFluid" align="left">

<h2>Elevator owners list</h2>

 <html:link page="/application.do?method=startOverForm">[delete and start over]</html:link>
<div class="messageBox">

  <p>
  Select your owner name to continue the application process.
  </p>

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
<c:url var="url" value="/elevator/owner.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="owner" items="${DFBS_OWNER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/owner.do?method=view" paramId="ownerId" paramName="owner" paramProperty="ownerId">
    <c:out value="${owner.ownerDBA}" />
    </html:link>
    
  </h3>
  <p class="listingInfo">
    <c:out value="${owner.ownerAddress1}" /><br />
    <c:if test="${owner.ownerAddress2 != null && owner.ownerAddress2 != ''}">
    <c:out value="${owner.ownerAddress2}" /><br />
    </c:if>
    <c:out value="${owner.ownerCity}" />, <c:out value="${owner.ownerState}" /> <c:out value="${owner.ownerZip}" /><br />
    <br />
  </p>
  <a   href="/dfbs/elevator/owner.do?method=view&ownerId=<c:out value="${owner.ownerId}"/>">
             [start application for this owner]</a>
   <a   href="/dfbs/elevator/elevator.do?method=feesByOwnerId&ownerId=<c:out value="${owner.ownerId}"/>">
             [pay fees for this owner]</a>           
    
  </div>
  
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
