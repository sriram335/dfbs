<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:out value="${sessionScope.IDHS_FEE_ID}"/>
 <html:form action="/start" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="addSelectToCart"/> 
            <input type="submit" value="Add to Cart" class="button" />  </br> 
         
 <c:forEach var="fee" items="${FEE_DUE_LIST}" varStatus="i">
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>State Number #(Transaction ID)</th>
    <th>Due</th>
    <th>Post Date</th>
    <th>    Description </th>
  </tr>
  <tbody>
   
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message">
          <c:out value="${fee.uniqueNumber}"/></span>(<c:out value="${fee.transactionId}"/>)
        </td>
      
        <td><span class="message" >
          <c:out value="${fee.due}"/></span>
          
        </td>
       <td><span class="message">
          <c:out value="${fee.postDate}"/></span>
          
        </td>
        <td><span class="message" >
          <c:out value="${fee.description}"/></span>
        </td>
        <td>
        Pay:<input type="checkbox" name="<c:out value='${fee.transactionId}' />" class="switch"
                   value="Y"/>
        </td>
    <%--    <td><span class="message" >
          <html:form action="/start" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="addToCart"/> 
            <html:hidden property="transactionId" value="${fee.transactionId}"/>
            <input type="submit" value="Pay" class="button" />  </br> 
          </html:form></span>
        </td> --%>
      </tr>
   
  </tbody>
</table>
 </c:forEach>
 </html:form>
