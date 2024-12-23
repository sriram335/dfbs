<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<c:set var="module" scope="request" value="Code Enforcement" />
<c:set var="title" scope="request" value="Seal Orders" />
 

<jsp:include page="/app/common/hsPageHeader.jsp" />
<h2>Seal Orders</h2>
<a   href="/dfbs/code/main.do?method=view&manufacturerNameId=<c:out value="${sessionScope.MANUFACTURER.manufacturerNameId}"/>"> 
              [back to facilites]</a>
<div id="mainBox">



<div id="leftContentFluid">
 <c:forEach var="order" items="${SEAL_ORDER_LIST.list}" varStatus="i">
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>Order Id #</th>
    <th>M seals applied</th>
    <th>P seals applied</th>
    <th>Order Date</th>
    <th>M seals details</th>
    <th>P seals details</th>
  </tr>
  <tbody>
   
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message">
          <c:out value="${order.orderId}"/></span>
        <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }"> 
        
          <a   href="/dfbs/code/seal.do?method=processSeals&orderId=<c:out value="${order.orderId}"/>">
             [ process order ]</a>
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=code_seal_online_order.rdf&p_order_id=<c:out value="${order.orderId}"/>">
         [Print Shipment]</a></br>
         </c:if>
        
        </td>
      
        <td><span class="message" >
          <c:out value="${order.mseals}"/></span>
          
        </td>
      
        <td><span class="message" >
          <c:out value="${order.pseals}"/></span>
          
        </td>
       <td><span class="message">
          <c:out value="${order.orderDateString}"/></span>
          
        </td>
        <td><span class="message">
         From: <c:out value="${order.msealsFrom}"/></br>
         To: <c:out value="${order.msealsTo}"/></span>
          
        </td>
         <td><span class="message">
           From: <c:out value="${order.psealsFrom}"/></br>
         To: <c:out value="${order.psealsTo}"/></span>
          
        </td>
   
  </tbody>
</table>
 </c:forEach>
</div>
</div>
