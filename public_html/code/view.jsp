<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Code Enforcement"/>
<c:set var="title" scope="request" value="Application for Industrialized Building Systems/ Mobile Structures"/>
<c:set var="level" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<jsp:include page="module/breadcrumbs.jsp"/>
<div id="mainBox">
  <div id="sideContentFluid">
    <jsp:include page="common/shoppingCart.jsp"/>
  </div>
<div id="mainBox">
  <div id="mainContentFluid" align="left">
   
    
   <jsp:include page="common/manufacturerDisplay.jsp"/>
   <jsp:include page="common/personList.jsp"/>
    <jsp:include page="common/facilityList.jsp"/>
     
 </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
