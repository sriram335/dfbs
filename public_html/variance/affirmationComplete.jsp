<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/><c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Variance Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">

    
  <div id="leftContentFluid">
  <h1><b><u>Variance Application Process</u></b></h1></br> </br> 
  <h2>Affirmation Completed</h2> 
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepnhtml&report=variance_view_application.rdf&p_var_id=<c:out value="${VARIANCE_APPLICATION_SELECTED.varId}"/>">[ view application ]</a>
  <div class="styledBox">
 
  <c:if test="${DESIGNER_AFFIRMATION_ERROR =='N'}">
  Your Affirmation process is complete. If we need any additional information IDHS will contact you.
   </c:if>
   <c:if test="${VAR_AFFIRMATION_ERROR =='Y'}">
   We could not validate your password.Please contact DfbsDatabase@dhs.in.gov, with variance reference id and project name and password provided in the email.
   </c:if>
  
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 </div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
