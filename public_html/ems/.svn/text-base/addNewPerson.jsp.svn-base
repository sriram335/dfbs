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
    <jsp:include page="personListLookup.jsp"/>
  </div>  
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">Add new contact person  </h2>
 
 <html:form action="/person" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="personListLookup"/>  
  Look up by last name   <html:text property="personLastName" size="30" maxlength="30"/>
  
   <p>
   
          <html:submit value="Look up persons" styleClass="button" />
   </p>
   
</html:form>

    
  </div>
 
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>