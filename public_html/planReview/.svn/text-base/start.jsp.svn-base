<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Apply for FSC Cigarette Certification"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<html:form action="/upload" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="goToUpload"/> 
 <b>Enter the project number and click "Go to Upload Plans" to start the process</b>
  <p align="left">
          SBC Project Number:<html:text property="idNumber" size="12" maxlength="12"/> </br></br>
          
          <input type="submit" value="Go to Upload Plans" class="button"/>
        </br>        
</p>
</html:form>
<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 