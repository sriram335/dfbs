<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION" />
<c:set var="title" scope="request" value="User List" />


<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">


 <a   href="/dfbs/magazine/magazine.do">
              [back to start]</a>  
<div id="mainContentFluid" align="left">

<h2>User's List</h2>



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
<c:url var="url" value="/magazine/magazineUser.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> users" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>

</c:forEach>
</ul>
</div>


  <c:forEach var="user" items="${USER_LIST.list}" varStatus="i" >
   
  <div class="listing">
  <h3 style="margin-bottom:5px;">
  User Id:<c:out value="${user.userLoginId}" />
  </h3>
 
<c:if test="${ sessionScope.DFBS_SECURITY.groupLevelAll =='DBA' || sessionScope.DFBS_SECURITY.groupLevelFire != null}">
  <a   href="/dfbs/magazine/magazineUser.do?method=updateUser&userId=<c:out value="${user.userId}"/>">[Edit User Information]</a>
<a   href="/dfbs/magazine/magazineUser.do?method=emailUserInfo&userId=<c:out value="${user.userId}"/>&ownerId=<c:out value="${user.ownerId}"/>">[Email New User Information]</a>
</c:if>


  <p class="listingInfo">
   
  Name:
    <c:out value="${user.userLastName}" /><br />
  Password good untill:  <c:out value="${user.userPasswordExpiresDateString}" /><br />
   Phone: <c:out value="${user.userTelephone}" /><br />
    <br />
    
  </p>

 
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
