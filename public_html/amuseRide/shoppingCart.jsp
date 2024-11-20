<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<span class="required" >
<a   href="/dfbs/amuseRide/owner.do?method=backToStart">
              [Clear all and start over]</a>
              </span></br>
<c:if test="${sessionScope.RIDE_INSURANCE_STATUS != 'Y'}">
 <span class="error">* please update insurance information before checkout(use Edit/Add options below)</span> </br>
 </c:if>
  <c:if test="${sessionScope.RIDE_OWNER_STATUS != 'Y'}">
  <span class="error">* please update owner information before checkout(use Edit option below)</span></br> After editing check out option will be enabled. </span>
  </c:if>
   <c:if test="${sessionScope.AMUSE_OWNER_SELECTED.fileListCount ==0}">
  <span > please upload amuse ride itinarary /amuse ride schedule.(If your rides are fixed to a location, this is optional) </span></br>
  </c:if>
<c:if test="${sessionScope.SHOPPING_CART.ownerMapCount > 0}">  
<c:if test="${sessionScope.RIDE_INSURANCE_STATUS == 'Y' && sessionScope.RIDE_OWNER_STATUS == 'Y' }">   
 <html:form action="/checkout" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  
      </p>
     
</html:form>
</c:if>
</c:if>  
<c:if test="${sessionScope.RIDE_INSURANCE_STATUS !='Y' && sessionScope.RIDE_INSURANCE_STATUS !='Y' }">   
 <html:form action="/owner" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="preCheckout"/>
      <p>
        <input type="submit" value=" verify final application " class="button"/>
      </p>
</html:form>
</c:if>

 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelAmuse != null }">     
 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      
       Check Number:<html:text property="receiptId" size="30" maxlength="30"/>
      <p>
        <input type="submit" value="Received Check / Save " class="button"/>
      </p>
</html:form>
 
</c:if>  

<table cellspacing="0" style="width: 75%;" summary="sales">

  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
  <tr>
  <td>
  Step 1. Add all the rides for renewal to shopping cart.</br> 
  Step 2. Click Edit owner and verify the information.</br> 
  Step 3. Click Add/Edit Insurance and update the information.</br> 
  Step 4. Click Upload itenarary... link and upload all the documents</br> 
  Then proceed to check out.
  <tr>
  <td>
 <span class="required" >
 <U><b>In this session</b></U></br>
  Total Owners:<c:out value="${sessionScope.SHOPPING_CART.ownerMapCount}"/></br> 
 
  Fees:<c:out value="${sessionScope.SHOPPING_CART.amount}"/>0</span>
</td>
</tr>
  <c:forEach var="mapItem" items="${sessionScope.SHOPPING_CART.ownerMap}" varStatus="i">
              <c:set scope="request" var="owner" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
       <h3>Owner: <c:out value="${owner.ownerDBA}"/></h3> 
        <html:link page="/owner.do?method=editOwner" paramId="key" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[edit owner]</span>                  
         </html:link> 
         <html:link page="/owner.do?method=removeOwner" paramId="ownerKey" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[remove owner]</span> 
          </html:link> 
          <a   href="/dfbs/amuseRide/owner.do?method=view&ownerId=<c:out value="${owner.ownerKey}"/>">
            <span style="font-size:x-small"> [show rides list]</span></a></br> 
          <a   href="/dfbs/amuseRide/owner.do?method=goToUpload&idNumber=<c:out value="${owner.ownerKey}"/>">
              <span style="font-size:x-small">[Upload itenarary / Amuse Schedule files]</span></a>
          <a   href="/dfbs/amuseRide/owner.do?method=editInsurance&idNumber=<c:out value="${owner.ownerKey}"/>">
              <span style="font-size:x-small">[Add/Edit Insurance]</span></a></br>
            
           
          Total Fees for this owner:<c:out value="${owner.amount}"/>
          
           <c:forEach var="mapItem" items="${owner.ridesMap}" varStatus="j">
                      <c:set scope="request" var="ride" value="${mapItem.value}"/>
              <tr >
                <td>
               Ride Serial Number: <c:out value="${ride.serialNumber}"/> </br>
               Ride Fee: <c:out value="${ride.appFee}"/></br>
              <c:if test="${sessionScope.SHOPPING_CART.receiptId == '0'}">
              <a   href="/dfbs/amuseRide/ride.do?method=removeRide&ownerKey=<c:out value="${owner.ownerKey}"/>&rideAppKey=<c:out value="${ride.amuseApplicationKey}"/>">
              <span style="font-size:x-small">[remove ride] </span></a></br> 
              </br>
              </c:if>
                </td>
                </tr>
              </c:forEach>
               <c:forEach var="mapItem" items="${owner.insMap}" varStatus="j">
                      <c:set scope="request" var="ins" value="${mapItem.value}"/>
              <tr >
                <td>
               InuranceName: <c:out value="${ins.insName}"/> </br>
               Plocicy Number: <c:out value="${ins.policyNumber}"/></br>
                </td>
                </tr>
              </c:forEach>
        </td>
      </tr>
  </c:forEach>
 
  <c:forEach var="file" items="${sessionScope.AMUSE_OWNER_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
        File Loaded:<c:out value="${file.fileName}"/>&nbsp; File upload date:<c:out value="${file.fileDateString}"/></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
        </c:if>
          </td>        
    </tr>
</c:forEach>

  </tbody>
</table>




<h2>Contact Us</h2>
          <p>
          You can contact us via email or by phone
          </p>
          <p>
         <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"  href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/>  </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>"> <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>   </br>   </br>          
        
          
