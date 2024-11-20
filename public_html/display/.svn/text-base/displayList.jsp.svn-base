<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >Other permit(s) for the same owner</th>
   </tr>
  <tbody>
    <c:forEach var="display" items="${sessionScope.OWNER_DIAPLY_LIST.permits}" varStatus="i" >
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><b>
     
        Permit Number:  <c:out value="${display.displayIdNumber}"/>&nbsp; <br/> </b>
        location:   <c:out value="${display.displayAddress1}"/>,<c:out value="${display.displayCity}"/><br/>
       <c:if test="${display.displayOnlineStatus == 'NEW'}">
      <p class="message">Application in Process.</p>
      </c:if>
       <c:if test="${display.displayOnlineStatus != 'NEW'}">
      <a   href="/dfbs/display/display.do?method=renewPermit&ownerKey=<c:out value="${owner.ownerKey}"/>&displayKey=<c:out value="${display.displayIdNumber}"/>">
              <span style="font-size:x-small">[apply for permit at this location]</span> </a>  
       </c:if> 
         </td>
         </tr>
    </c:forEach>
  </tbody>
</table>
 

</div>
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
