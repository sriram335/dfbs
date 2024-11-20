<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >Shipment details</th>
  </tr>
  <tbody>
    <c:forEach var="mapItem" items="${sessionScope.HAZMAT_CARRIER.shipmentsMap}" varStatus="i">
              <c:set scope="request" var="shipment" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
          Permit Number:<c:out value="${shipment.shipPermitNumber}"/>&nbsp; <br/> 
          Date:<c:out value="${shipment.shipDateString}"/>&nbsp; <br/> 
          Origin:<c:out value="${shipment.shipOrigin}"/>&nbsp; <br/> 
          Destination:<c:out value="${shipment.shipDestination}"/>&nbsp; <br/> 
          Radio Active Level:<c:out value="${shipment.shipRadLevel}"/>&nbsp; <br/> 
          Material Type:<c:out value="${shipment.shipMaterialType}"/>&nbsp;</br>
        <c:if test="${sessionScope.HAZMAT_ORG.receiptId == '0'}"> 
        <html:link page="/shipment.do?method=editShipment" paramId="key" paramName="mapItem" paramProperty="key">
                 <span >[edit shipment]</span> </br>                 
                 </html:link>
         <html:link page="/shipment.do?method=removeShipment" paramId="key" paramName="mapItem" paramProperty="key">
                 <span >[remove shipment]</span> </br>
         </html:link>     
         </c:if> 
        </td>
      </tr>
  
    </c:forEach>
  </tbody>
</table>