 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:out value="${sessionScope.PERMIT_FEE.permitNumber}"/>
 <c:forEach var="fee" items="${sessionScope.PERMIT_FEE.permitFees}" varStatus="i">
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>Receipt Number #</th>
    <th>Due</th>
    <th>Amount Paid</th>
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
       <td><span class="message">
          <c:out value="${fee.postDate}"/>
          
        </td>
        <td><span class="message" >
          <c:out value="${fee.description}"/>
          
        </td>
      </tr>
   
  </tbody>
</table>
 </c:forEach>

