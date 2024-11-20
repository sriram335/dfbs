<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<table cellspacing="0" style="width: 50%;" summary="sales">
  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
  <c:forEach var="mapItem" items="${sessionScope.MANUFACTURER.facilityMap}" varStatus="i">
              <c:set scope="request" var="facility" value="${mapItem.value}"/>
     
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
        <c:out value="${facility.facilityName}"/> 
     <%--  <c:if test="${facility.sealQuantity > 0}"> --%>
            
         M Type seals: <c:out value="${facility.mseals}"/></br>
         P Type seals: <c:out value="${facility.pseals}"/></br>
         Cost $$ :<c:out value="${facility.amount}"/>0&nbsp;<br>
        
        </td>
       
        
      </tr>
  </c:forEach>
  </tbody>
</table>
<table cellspacing="0" style="width: 50%;" summary="sales">
  <tbody>
  <c:forEach var="mapItem" items="${sessionScope.MANUFACTURER.designReleaseMap}" varStatus="i">
              <c:set scope="request" var="release" value="${mapItem.value}"/>
     
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
          
         Release Type: <c:out value="${release.systemType}"/></br>
         Occupancy: <c:out value="${release.occupancy}"/></br>
         Cost $$ :<c:out value="${release.releaseFee}"/>0&nbsp;<br>
        <html:link page="/newRelease.do?method=editRelease" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[edit]</span> 
        </html:link>
        <html:link page="/newRelease.do?method=removeRelease" paramId="key" paramName="mapItem" paramProperty="key">
                      <span style="font-size:x-small">[remove] 
        </html:link>
           <a href="/dfbs/code/newRelease.do?method=goToUpload&idNumber=<c:out value="${release.applicationKey}"/>&idType=CodeRelease">
                    <span style="font-size:x-small">[upload code design release plan(s)]</a> </br></span>
         <c:if test="${release.fileStatus=='NO' }">
                     <br/><span class="error">* you have not uploaded the plans for this release</span> 
          </c:if>
        </td>
       
        
      </tr>
  </c:forEach>
  </tbody>
</table>
Total for Releases:<c:out value="${sessionScope.MANUFACTURER.totalAmountReleases}"/>0</br>
Total for Seals   :<c:out value="${sessionScope.MANUFACTURER.totalAmountSeals}"/>0</br>
Total in the cart :<c:out value="${sessionScope.MANUFACTURER.grandTotal}"/>0</br>
Design Releases Applied:<c:out value="${sessionScope.MANUFACTURER.designReleaseMapCount}"/> 
<c:if test="${sessionScope.MANUFACTURER.fileStatus=='NO' }">
                     <br/><span class="error">* you have not uploaded the plans for each release, verify each release</span> 
</c:if>
<c:if test="${sessionScope.MANUFACTURER.fileStatus=='CHECKED OUT' }">
                     <br/><span class="error">* Check posted and data posted.</span> 
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.userId == null}">   
 <html:form action="/checkout" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  
      </p>
      
</html:form>
</c:if>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }">     
 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      <p>
        <input type="submit" value="Received Check / Save " class="button"/>
      </p>
       Check Number:<html:text property="checkoutId" size="30" maxlength="30"/>
      Transaction Id / Fee Id:<c:out value="${sessionScope.MANUFACTURER.manufacturerNameId}"/>0</br>
</html:form>
<html:form action="/seal" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="processSeals"/>
      <p>
        <input type="submit" value="Process Order " class="button"/>
      </p>
       order Id:<html:text property="orderId" size="30" maxlength="30"/>
     
</html:form>

</c:if>  
<jsp:include page="feeDetails.jsp"/> 
 