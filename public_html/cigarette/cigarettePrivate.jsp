<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Online Cigarette Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  
  <div id="mainContentFluid" align="left">
     
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCigarette == 'SUPERVISOR'}">    
 <html:form action="/logOn" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="newApplication"/>
      <p>
        <input type="submit" value="Create new certification with  check" class="button"/>
      </p>
     
</html:form>
 <html:form action="/applicationsView" method="post">
      <p>
        <input type="submit" value="View previous applications details" class="button"/>
      </p>
      
</html:form>
   <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=cigarettes_brand_status" target="_blank">[Application(s) Brand Status]</a></br>
  <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=cigarettes_brand_not_applied" target="_blank">[ Brand(s) not applied]</a></br>
</c:if> 
 </div>    
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
