<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Client Site Agreement's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox" align="left">
<html:form action="/agmts" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addNewAgmts"/> 
      


<div id="mainContentFluid" align="left">

<h2>Current agreements for the institution</h2>
 
    
     <a   href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ back to institution ]</a> 
     <a href="/dfbs/ems/main.do?method=authUser">
             [back to main menu]  </a> </br>




  <c:forEach var="agmts" items="${AGMTS_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
    
  </h3>
  <p class="listingInfo">
    <html:link page="/agmts.do?method=agmtsDetail" paramId="agreementId" paramName="agmts" paramProperty="agreementId">
   <c:out value="${agmts.name}" /><br />
    </html:link>
    <c:out value="${agmts.effectiveDateString}" /><br />
    <c:out value="${agmts.remarks}" /><br />
  </p>
  </div>
 
  </c:forEach>

</div>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
<p>
   
          <html:submit value="Add Agreement" styleClass="button" />
 </p>
</c:if>
</html:form >
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />

