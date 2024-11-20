<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
  <b><u>Applications to be processed</u></b></br>
<table cellspacing="0"  summary="sales">
  <tr>
    <th>Variance Details</th>
   
  </tr>
  <tbody>
       <c:forEach var="varApp" items="${requestScope.VARIANCE_APPLICATION_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
         <b> <c:out value="${varApp.varAppVarNumber}"/>&nbsp;</span></b></br>
         Project Name:<c:out value="${varApp.varProjName}"/>&nbsp;</br>
         Application Date:<c:out value="${varApp.varAppRecDateString}"/>&nbsp;</br>
          <c:forEach var="code" items="${varApp.varCodesList}" varStatus="i">
          Code Name:<c:out value="${code.varCode}"/>&nbsp;</br>
          Code Specific Section:<c:out value="${code.varCodeName}"/>&nbsp;</br>
             <a   href="/dfbs/variance/code.do?method=viewCode&varCodeId=<c:out value="${code.varCodeId}"/>">
             [view code Details]</a> 
          </c:forEach>
        </td>
      </tr>
       </c:forEach>
  </tbody>
</table>
 
  

 