<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Level's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox" align="left">



<div id="mainContentFluid" align="left">
<a href="/dfbs/ems/ems/main.do?method=authUser">
            [back to main menu]  </a> 
     <a   href="/dfbs/ems/ems/provider.do?method=providerDetail&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to provider]  </a>    

<h2>Current Contact levels for the provider</h2>


<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
<html:form action="/level" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addNewLevel"/> 
<p>
   
          <html:submit value="Add Level" styleClass="button" />
 </p>
</html:form> 
</c:if>


  <c:forEach var="level" items="${LEVEL_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
    
  </h3>
  <p class="listingInfo">
     <html:link page="/level.do?method=levelDetail" paramId="levelId" paramName="level" paramProperty="levelId">
    <c:out value="${level.levelName}" /><br />
    </html:link>
    
    
  </p>
  </div>
 
  </c:forEach>


 
</div>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
