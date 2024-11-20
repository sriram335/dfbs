<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<u>Release Conditions:</u><c:forEach var="code" items="${UPDATE_RELEASE_LIST.list}" varStatus="i" >
  
  <div class="listing">
 
  <p class="listingInfo">
    <c:out value="${code.cannedCode}" /><br />
   </p>
    <a   href="/dfbs/code/newRelease.do?method=editCannedCode&codeId=<c:out value="${code.codeId}"/>"> 
              [edit this condition]</a>
     <a   href="/dfbs/code/newRelease.do?method=removeCannedCode&codeId=<c:out value="${code.codeId}"/>"> 
              [remove this condition]</a>
  </div>
 
  </c:forEach>
  