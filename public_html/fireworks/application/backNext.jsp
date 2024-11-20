 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<div style="font-weight:bold;font-size:large;">
  <c:choose>
    <c:when test="${step == 0}">
      <p style="float:right">
        <html:link page="/application.do?method=step1">proceed to step 1 &gt;</html:link>
      </p>
    </c:when>
    <c:when test="${step == 1}">
      <p style="float:left">
        <html:link page="/application.do?method=intro">&lt; back</html:link>
      </p>
      <p style="float:right">
        <html:link page="/application.do?method=step2">proceed to step 2 &gt;</html:link>
      </p>
    </c:when>
    <c:when test="${step == 2}">
      <p style="float:left">
        <html:link page="/application.do?method=step1">&lt; back</html:link>
      </p>
      <p style="float:right">
        <html:link page="/application.do?method=step3">proceed to step 3 &gt;</html:link>
      </p>
    </c:when>
    <c:when test="${step == 3}">
      <p style="float:left">
        <html:link page="/application.do?method=step2">&lt; back</html:link>
      </p>
    </c:when>
  </c:choose>
  <div class="clearer">&nbsp;</div>
</div>
