<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Elevator Permits" />
<c:set var="title" scope="request" value="Elevator Online Application" />

<jsp:include page="/app/common/hsPageHeader.jsp"/>

<h2>Start Installation Application</h2>


 <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
</div>
<div id="leftContentFluid" align="left">
 
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="installationPermitOldOwner"/>
      <p>   <input type="submit" value="Apply for Installation Permit Old owner" class="button"/>
      (I have applied for Elevator Installation Permit with IDHS or I do have other Elevators listed with IDHS)</p>
</html:form>
<html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="installationPermitNewOwner"/>
         <p>  <input type="submit" value="Apply for Installation Permit New Owner" class="button"/>
         (Never applied for Elevator Installtion Permit with IDHS or I do not own any Buildings in Indiana with Elevators)</p>
 </html:form>

</div>

<div class="clearer">&nbsp;</div>


<jsp:include page="/app/common/hsPageFooter.jsp" /> 