<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Entertainment Permits" />
<c:set var="title" scope="request" value="Violation Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">

<jsp:include page="/code/common/lookUpStdCode.jsp"/>
</div>
<h2>Add New Conditions</h2> 
<div id="leftContentFluid">
 <a   href="/dfbs/code/newRelease.do?method=addReleaseDate&systemId=<c:out value="${sessionScope.UPDATE_RELEASE.systemId}"/>"> 
              [back to release]</a>
  <div class="styledBox">
 <html:form action="/newRelease" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveStdCode"/> 
      
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >_______</th>
    </tr>
    <tr>
      <th style="font-size:medium;font-weight:bold" scope="row" > </th>
      <td style="font-size:medium;font-weight:bold">
       
      </td>
    </tr>
    
    
      <th scope="row"   class="required"    >  *  code:</th>
          <td>
           <html:textarea property="cannedCode" />
            
            </td>
      </tr> 
     
    
  </tbody>
        </table>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }">  
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
      </c:if>
     
    </html:form>   
  Added Conditions:
  <jsp:include page="/code/common/cannedCodes.jsp"/>
 
</div>
 

