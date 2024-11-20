<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>

 
<table cellspacing="0" style="width: 75%;" summary="sales">
  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
  <tr>
  <td>
  <h2>Organization:<c:out value="${sessionScope.HAZMAT_ORG.orgName}"/></h2></br>
  Total Shipments for the Organization:<c:out value="${sessionScope.HAZMAT_ORG.totalShipments}"/></br>
  Fees for this Organization in this session:<c:out value="${sessionScope.HAZMAT_ORG.amount}"/>0
</td>
</tr>
  <c:forEach var="mapItem" items="${sessionScope.HAZMAT_ORG.carriersMap}" varStatus="i">
              <c:set scope="request" var="carrier" value="${mapItem.value}"/>
     
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
       <h2>Carrier: <c:out value="${carrier.carrierName}"/></h2> 
       <c:if test="${sessionScope.HAZMAT_ORG.receiptId == '0'}"> 
        <html:link page="/carrier.do?method=editCarrier" paramId="key" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[edit carrier]</span>  </br>                
         </html:link>
         <html:link page="/carrier.do?method=removeCarrier" paramId="key" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[remove carrier]</span> 
          </html:link> </br> 
          </c:if>
          Total Shipments for this carrier:<c:out value="${carrier.shipmentsMapCount}"/></br>
          Fees for this carrier:<c:out value="${carrier.amount}"/>0
           <c:forEach var="mapItem" items="${carrier.shipmentsMap}" varStatus="j">
                      <c:set scope="request" var="shipment" value="${mapItem.value}"/>
             
              <tr >
                <td>
               Shipment Origin: <c:out value="${shipment.shipOrigin}"/></br>
               Shipment Destination: <c:out value="${shipment.shipDestination}"/> </br>
               Shipment Radiation Level: <c:out value="${shipment.shipRadLevel}"/> </br> 
               Shipment Type: <c:out value="${shipment.shipmentType}"/> </br> 
               Shipment Number of Casks: <c:out value="${shipment.numberOfCasks}"/> </br> 
               Shipment Permit Fee: <c:out value="${shipment.amount}"/>0 </br>
                 
                </td>
                </tr>
              </c:forEach>
        </td>
      </tr>
  </c:forEach>
  </tbody>
</table>


<c:if test="${sessionScope.DFBS_SECURITY.userId == null}">   
<c:if test="${sessionScope.HAZMAT_ORG.receiptId == 0}">
 <html:form action="/checkout" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  If you do not wish to pay the additional fee click on the paper
      application link below and fill out the paper application for each shipment and mail it along with a check.
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=rad-waste permit application.pdf&fileType=.pdf&fileId=406" target="_blank">
          [paper application]</a> 
      </p>
     
</html:form>
</c:if>

</c:if>  
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null || sessionScope.DFBS_SECURITY.groupLevelHazmat == 'HAZMAT_SUP' }">     
 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      <p>
        <input type="submit" value="Received Check" class="button"/>
      </p>
       Check Number:<html:text property="receiptId" size="30" maxlength="30"/>
      
</html:form>
<%--
 <html:form action="/orgShipmentView" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="hazmatPrivate"/>
      <p>
        <input type="submit" value="IDHS Use" class="button"/>
      </p>
      
</html:form> --%>

</c:if> 
 <c:if test="${sessionScope.HAZMAT_ORG.receiptId != null  && sessionScope.HAZMAT_ORG.paymentType  == 'CC' }">     
 <html:form action="/hazmat" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="printAllPermits"/>
     <p>
        <input type="submit" value="Print Permits" class="button"/>
      </p>
  </html:form>
</c:if> 
<h2>Contact Us</h2>
          <p>
          You can contact us via email or by phone
          </p>
          <p>
         <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>             
  <c:if test="${sessionScope.DFBS_SECURITY == null}">  
<html:form action="/hazmat" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="logOn"/>
<input type="submit" value="For IDHS Employees" class="button"/>          
 </html:form>
 </c:if>          
<%-- </br><c:if test="${sessionScope.HAZMAT_ORG.receiptId > 0 }">
              <span class="message" style="font-weight:bold;font-size:medium;">****=> </span>
                 <a   href="/dfbs/hazmat/hazmat.do?method=printAllPermits&receiptId=<c:out value="${sessionScope.HAZMAT_ORG.receiptId}"/>">
                [ Print all permits in this session]</a>
                <span class="message" style="font-weight:bold;font-size:medium;"><=**** </span>
         </c:if>--%>