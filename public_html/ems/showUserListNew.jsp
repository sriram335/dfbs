<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security" />
<c:set var="title" scope="request" value="Ems Online Application Users List" />
<c:set var="level" scope="request" value="1" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">





<div id="mainContentFluid">

<h2>Current Registered Active Users List</h2>

<a   href="/dfbs/ems/main.do?method=showNewUserList">
              [ View New Users ]</a>    
 <a   href="/dfbs/ems/main.do?method=showAllUserList">
              [ View All users ]</a>  
<div class="charNav">
<ul>
<c:forEach var="c" items="${USER_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${USER_LIST_FILTER.type == 'byLetter' && USER_LIST_FILTER.value == c.character}">
<li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/ems/main.do?method=filterNew&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> users" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</li>
</c:forEach>
</ul>
</div>


  <c:forEach var="security" items="${USER_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/main.do?method=showUser" paramId="securityId" paramName="security" paramProperty="securityId">
    <c:out value="${security.userId}" />
    </html:link>
  </h3>
  <p class="listingInfo">
    <c:out value="${security.userLastName}" /><br />
    <c:out value="${security.userFirstName}" /><br />
    <c:out value="${security.userTitle}" /><br />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
