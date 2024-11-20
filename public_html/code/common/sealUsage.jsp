<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>


 <html:form action="/seal" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveRelease"/>

 <h2 style="font-weight:bold;">Seal Usage  </h2>
 <table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >Seal Number</th>
    <th>Seal used date</th>
     <th>Seal inspected date</th>
     <th>Seal release number </th> 
     <th>Seal update date  </th> 
      <th>unit serial number  </th> 
       <th>state inspector  </th> 
         <th>inspection company  </th> 
  </tr>
  <tbody>
  <c:forEach var="seal" items="${SEAL_USAGE.list}" varStatus="i" >
  <tr class="row<c:out value='${(i.index % 2) + 1}' />">
  <td>
    <c:out value="${seal.sealNumber}" />
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null}">
   <a href="/dfbs/code/sealUsage.do?method=editSealUsage&sealNumber=<c:out value="${seal.sealNumber}" />">
    [edit]  </a>
  </c:if> 
 </td>
 <td>
 <c:out value="${seal.sealUsedDateString}" />
 </td>
 <td>
 <c:out value="${seal.sealInspDateString}" />
 </td>
 <td>
 <c:out value="${seal.sealRelNumber}" />
 </td>
 <td>
 <c:out value="${seal.sealUpdateDateString}" />
 </td>
 <td>
 <c:out value="${seal.sealUnitNumber}" />
 </td>
  <td>
 <c:out value="${seal.inspectorName}" />
 </td>
 
  <td>
 <c:out value="${seal.companyName}" />
 </td>
 </tr>
  </c:forEach>
  </tbody>
  </table>
 </html:form>
