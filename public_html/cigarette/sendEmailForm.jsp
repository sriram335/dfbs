<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Apply for Cigarette Certification"/>
<c:set var="level" scope="request" value="1"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="sideContentFluid">
  
  <html:link page="/cigarette.do" >back to home</html:link>
  
  </div>
  <div id="mainContentFluid" align="left">
    <h2 style="margin-top:0px;">Contact Us</h2>
    <c:if test="${DFBS_EMAIL_SENT == 'TRUE'}">
      <p class="messageBox">Your email has been successfully sent.</p>
    </c:if>
    <form method="post" action="/dfbs/cigarette/cigarette.do">
      <p>
        <input type="hidden" name="method" value="sendEmail"/>your name:
        <br/>
        <input type="text" name="name" maxlength="100" size="30" value="" class="inputText"/>
        <br/>your email:
        <br/>
        <input type="text" name="email" maxlength="100" size="30" value="" class="inputText"/>
        <br/>subject:
        <br/>
        <input type="text" name="subject" maxlength="100" size="30" value="" class="inputText"/>
        <br/>message:
        <br/>
        <textarea name="msg" cols="40" rows="10" class="inputText"></textarea>
        <br/>
        <br/>
        <input type="submit" value="send email" class="button"/>
      </p>
    </form>
  </div>
</div>
<div class="clearer">&nbsp;</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
