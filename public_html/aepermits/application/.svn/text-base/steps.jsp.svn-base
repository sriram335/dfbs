<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>


<div class="charNav">

  

  <ul>
    <c:choose>
      <c:when test="${step == 0}">
        <li class="selected">
          <html:link page="/application.do?method=intro" title="Instructions">*</html:link>          
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <html:link page="/application.do?method=intro" title="Instructions">*</html:link>
        </li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${step == 1}">
        <li class="selected">
          <html:link page="/application.do?method=step1" title="Company Information">1</html:link>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <html:link page="/application.do?method=step1" title="Company Information">1</html:link>
        </li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${step == 2}">
        <li class="selected">
          <html:link page="/application.do?method=step2" title="Permits">2</html:link>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <html:link page="/application.do?method=step2" title="Permits">2</html:link>
        </li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${step == 3}">
        <li class="selected">
          <html:link page="/application.do?method=step3" title="Review Your Application">3</html:link>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <html:link page="/application.do?method=step3" title="Review Your Application">3</html:link>
        </li>
      </c:otherwise>
    </c:choose>
  </ul>
  
</div>
