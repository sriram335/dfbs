<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Code Enforcement"/>
<c:set var="title" scope="request" value="Application for Industrialized Building Systems/ Mobile Structures"/>
<c:set var="step" scope="request" value="2"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
      <a   href="/dfbs/code/newRelease.do?method=backtoview" >
              [ Cancel ]</a> </br>     
     
  </div>
<div id="mainBox">
  <h2>Apply for CDR for Industrialized Building Systems/ Mobile Structures</h2>
 
  <div id="leftContentFluid">
 <html:form action="/newRelease" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveRelease"/>
      <html:hidden property="applicationKey"/>
       <jsp:include page="release.jsp"/>
 
<p>
   
          <html:submit value="save to your application" styleClass="button" />
 </p>
 
 </html:form>
  
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>

