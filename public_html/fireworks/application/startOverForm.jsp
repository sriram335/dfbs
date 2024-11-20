 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Delete Application"/>
<c:set var="step" scope="request" value="2"/>
<c:set var="nav" scope="request" value="false"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <h2>Delete Application</h2>
  <div id="rightContentFluid">
    <p class="control2">
      <html:link page="/application.do?method=step1">cancel</html:link>
    </p>
  </div>
  <div id="leftContentFluid">
    <html:form action="/application" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="startOver"/>
      
        <p style="font-weight:bold;">Are you sure you want to delete the application in session?
        </p>
          <button type="submit" class="button">
            delete
          </button>
        </p>
      
    </html:form>
  </div>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
