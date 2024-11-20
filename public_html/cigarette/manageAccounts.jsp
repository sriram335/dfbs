<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="manage account screen"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Manage my account</h2>

  <div id="leftContentFluid">
 <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updatePassword"/>
 
   
          <html:submit value="Change Password" styleClass="button" />
 </html:form>  
 
  <html:form action="/cigUser" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="dataExtract"/>
 
      <b><u> Get my company data extract</b></u>
      
         <html:submit value="Email Data Extract" styleClass="button" />

  <p class="message" >You can get your company cigarette information in as txt file attachement which you can import into excel format and submit it for other states.</p>
 </html:form>  
 <html:form action="/applicationsView" method="post">
      <p>
        <input type="submit" value="View previously filed Applications" class="button"/>
      </p>
     
</html:form>
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>

