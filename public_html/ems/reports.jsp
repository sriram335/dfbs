<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="Ems operations"/>
<c:set var="title" scope="request" value=" reports"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="rightContentFluid">
    <jsp:include page="reportNames.jsp"/>
  </div> 
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">select report group  </h2>
   
<c:forEach var="report" items="${REPORT_GROUP.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/reports.do?method=reportNames" paramId="reportGroup" paramName="report" paramProperty="reportGroup">
    <c:out value="${report.reportGroup}" />
    </html:link> </h3>
  
  </div>
 
  </c:forEach>
      
    
    
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
