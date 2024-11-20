<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<c:set var="module" scope="request" value="Ems operations"/>
<c:set var="title" scope="request" value=" reports"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<div id="mainBox">
  <div id="rightContentFluid">
    <jsp:include page="reportNames.jsp"/>
  </div> 
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">select report group  </h2>
<html:form action="/reports" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="selectDivision"/>
<html:select property="division"  onchange="setSelectValue('SELECT_REPORT','DIVISION_NAME')">
          <html:option value="">Select Division</html:option>
          <html:options collection="DFBS_DIVISION_OPTIONS" property="value" labelProperty="label"/>
</html:select>   
<html:submit value="Select Division" styleClass="button"/>
</html:form>

<c:forEach var="report" items="${REPORT_GROUP.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/reports.do?method=reportNames" paramId="reportGroup" paramName="report" paramProperty="reportGroup">
    <c:out value="${report.reportGroup}" />
    </html:link>
  
  </div>
 
  </c:forEach>
      
    
    
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
