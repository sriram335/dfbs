<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainment Permits"/>
<c:set var="title" scope="request" value="Entertainment Permits"/>
<c:set var="step" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 
<div id="mainBox">
  <h2>Re-Assign ID</h2>
     <a   href="/dfbs/childCare/start.do?method=backToPermit&permitNumber=<c:out value="${sessionScope.PERMIT_NEW}"/>">
             [ go to new permit ]</a></br> 
    <a   href="/dfbs/childCare/main.do"/>
             [ back to start ]</a></br> 
  
  <div id="leftContentFluid">
    <html:form action="/childcare" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="changeId"/>
      <html:hidden property="permitNumber"/>
      Enter New ID<html:text property="idNew" size="12" maxlength="12"/>
          <html:submit value="Change Id" styleClass="button"/>
        </p>
        
    </html:form>
   
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
