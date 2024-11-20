<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<div id="mainBox">
 
  <h2 style="font-weight:bold;">click on the report to run </h2>
   
<c:forEach var="report" items="${REPORT_NAMES.list}" varStatus="i" >
  
  <h3 style="margin-bottom:5px;">
   <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=<c:out value="${report.reportCode}"/> " target="_blank" >
               <c:out value="${report.reportName}"/>  </a> 
  
 </c:forEach>
      
    
    
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>