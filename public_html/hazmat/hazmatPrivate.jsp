<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - HAZMAT DIVISION"/>
<c:set var="title" scope="request" value="Apply for Hazardous Material Transport Permit"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  
  <div id="mainContentFluid" align="left">
     
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == 'HAZMAT_SUP' || sessionScope.DFBS_SECURITY.groupLevelHazmat == 'HAZMAT_SUP'}">    
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=hazmat_permit_counts.rdf">[view permit counts ]</a>
 <%--  
 <html:form action="/logOn" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="newApplication"/>
      <p>
        <input type="submit" value="Create new permit with  check" class="button"/>
      </p>
     
</html:form> --%>
 <html:form action="/orgShipmentView" method="post">
      <p>
        <input type="submit" value="View shipment details" class="button"/>
      </p>
      
</html:form>
</c:if> 
 </div>    
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
