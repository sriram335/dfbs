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
  <div id="rightContentFluid">
    <jsp:include page="application.jsp"/>
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">2) Renew/Add Permits </h2>
   
    <p class="control">
      <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/> <html:link page="/permit.do?method=addPermitForm">add new annual permit</html:link>
    </p>
    <p class="messageBox"><span class="error">Note: you can apply for special endorsements, when you have a valid annual permit. If annual is expired, first renew the permit and then apply for special endorsement(s).</span></p>
      <p style="font-size:x-large;font-weight:bold;">OR</p>
      <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="addSelectedPermits"/>
        <p style="font-size:medium;font-weight:bold;">
          <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow"/> click the check box(s) to select permits in the list for "Renew Annual" or "Add a Special" and click add to cart to process the permits:
        </p>
        
        <p>
          <html:submit value="add to cart" styleClass="button"/>
        </p>
        <jsp:include page="/aepermits/application/permitsListSelect.jsp"/>
        <p>
          <html:submit value="add to cart" styleClass="button"/>
        </p>
          <p class="messageBox"><span class="error"> After adding permits to the cart use the link(s) "edit for renewal" / "apply for special" to add appropriate information.</span></p>
  
      </html:form>
   
    
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
