<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value=" Lookup User Id"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <a href="/dfbs/codeEducation/start.do">
              [back]  </a> </br>
<div id="sideContentFluid"> 
 <c:forEach var="user" items="${USER_VIEW_LIST.list}" varStatus="i"  >
   
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">
  User Id:<c:out value="${user.userId}" /> 
 </h3>
 <p class="listingInfo">
  Name: <c:out value="${user.userFirstName}" /> <c:out value="${user.userLastName}" /><br />
     </p>
  </div>
  </c:forEach>
</div>
<div id="mainBox">
<div id="leftContentFluid"> 
  <h2 style="font-weight:bold;">Lookup User Id  </h2>

 <html:form action="/user" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="userIdLookup"/>  
  Last name (required)  <html:text property="userLastName" size="30" maxlength="30" disabled="false"/></br>
  First name(required)   <html:text property="userFirstName" size="30" maxlength="30" disabled="false"/>
   <p>
    <c:if test="${USER_VIEW_LIST_ERROR == 'ERROR'}">
     <span class="error">Both first and last name needed for search</span> 
    </c:if>
          <html:submit value="Look up User Id" styleClass="button" />
   </p>

</html:form>

  </div>

 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>