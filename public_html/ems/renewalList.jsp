<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="All Renewal List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">

     

     
<div id="mainContentFluid">

<h2>All the renewals received so far</h2>

     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${PROVIDER.providerId}"/>">
            [back to provider]  </a> &nbsp;   
     <a   href="dfbs/ems/provider.do?method=providerList">
              [ back to provider list ]</a> &nbsp;
     <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
  
  <c:forEach var="renewal" items="${RENEWAL_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    
   
    Renewal Request Date:  <c:out value="${renewal.renewalRequestDateString}" /><br />
     <a   href="/dfbs/ems/providerRenewal.do?method=renewalDetail&renewalId=<c:out value="${renewal.renewalId}"/>">
            [Edit/ View details]  </a>  
   </h3>
  
  </div>
 
  </c:forEach>

</div>
 
<div class="clearer">&nbsp;</div>

</div>
<jsp:include page="/app/common/hsPageFooter.jsp" />