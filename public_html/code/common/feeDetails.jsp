<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
 <c:forEach var="fee" items="${sessionScope.MANUFACTURER.manufacturerFees}" varStatus="i">
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>Receipt #</th>
    <th> Fee Due</th>
    <th>Fee Paid</th>
    <th>Post Date</th>
    <th>Description</th>
  </tr>
  <tbody>
   
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message">
          <c:out value="${fee.receiptNumber}"/>
          
        </td>
      
        <td><span class="message" >
          <c:out value="${fee.due}"/>
          
        </td>
      
        <td><span class="message" >
          <c:out value="${fee.amountPaid}"/>
          
        </td>
       <td><span class="error">
          <c:out value="${fee.postDate}"/>
          
        </td>
        <td><span class="message" >
          <c:out value="${fee.description}"/>
          
        </td>
      </tr>
   
  </tbody>
</table>
 </c:forEach>

