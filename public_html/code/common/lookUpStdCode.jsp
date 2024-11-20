<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>


<div id="leftContentFluid"> 
  <h2 style="font-weight:bold;">Look up Standard Code  </h2>
  <html:form action="/newRelease" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="codeLookup"/> 
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }">  
        <p>
          <html:submit value=" lookUp " styleClass="button"/>
        
        </p>
      </c:if>
      
    </html:form>   

 <h2 style="font-weight:bold;">Standard Code List  </h2>
  <c:forEach var="code" items="${STANDARD_CODE.list}" varStatus="i" >
  <tr>
  <td>
  <div class="listing">
    <c:out value="${code.cannedCode}" />
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null}">
   <a href="/dfbs/code/newRelease.do?method=addStdCode&codeId=<c:out value="${code.codeId}" />">
    [add this violation]  </a>
  </c:if> 
  
  </div>
 </td>
 </tr>
  </c:forEach>
  </div>
 
