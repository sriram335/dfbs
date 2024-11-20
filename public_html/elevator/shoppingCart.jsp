<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<span class="required" >
<a   href="/dfbs/elevator/start.do?method=backToStart">
              [Clear all and start over]</a>
              </span>
<c:if test="${sessionScope.SHOPPING_CART.ownerMapCount > 0}">  
<c:if test="${sessionScope.DFBS_SECURITY.userId == null}">   
<c:if test="${sessionScope.SHOPPING_CART.receiptId == 0}">
 <html:form action="/checkout" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="checkout"/>
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  
      </p>
     
</html:form>
<%--
<html:form action="/completeCheckout" method="post">
 <input type="hidden" name="method" id="METHOD_KEY" value="completeCheckoutTest"/>
      <p>
        <input type="submit" value="test" class="button"/>
      </p>
     
</html:form> --%>

</c:if>
</c:if>
</c:if>  
 <html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="search"/>
      <p>
        <input type="submit" value="Renew/ Apply for more elevators Different Owner" class="button"/>
      </p>
      
</html:form>
<html:form action="/owner" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="view"/>
  <input type="hidden" name="ownerId"  value="${sessionScope.OWNER_SELECTED.ownerId}"/>
      <p>
        <input type="submit" value="Renew/ Apply for more elevators Same Owner" class="button"/>
      </p>
      
</html:form>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null }">     
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
       <h3>Owner: <c:out value="${owner.ownerLastName}"/>,<c:out value="${owner.ownerFirstName}"/></h3> 
       <c:if test="${sessionScope.SHOPPING_CART.receiptId == '0'}"> 
       <c:if test="${owner.ownerId == '0'}">
       <a   href="/dfbs/elevator/elevator.do?method=addElevator&ownerKey=<c:out value="${owner.ownerKey}"/>">
              <span style="font-size:x-small">[add new Installation]</span> </a></br>
       </c:if>
       <%-- <html:link page="/application.do?method=editOwner" paramId="key" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[edit owner]</span>                  
         </html:link> --%>
         <html:link page="/owner.do?method=removeOwner" paramId="ownerKey" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[remove owner]</span> 
          </html:link> </br> 
          </c:if>
          Total applications for this owner:<c:out value="${owner.ownerAppCount}"/></br>
          Total Fees for this owner:<c:out value="${owner.amount}"/>
           <c:forEach var="mapItem" items="${owner.elevatorAppMap}" varStatus="j">
                      <c:set scope="request" var="elevator" value="${mapItem.value}"/>
              <tr >
                <td>
              
               Elevator Number: <c:out value="${elevator.stateNumber}"/> </br>
               Elevator  Fee: <c:out value="${elevator.appFee}"/></br>
              <c:if test="${sessionScope.SHOPPING_CART.receiptId == '0'}">
              <a   href="/dfbs/elevator/elevator.do?method=removeElevator&ownerKey=<c:out value="${owner.ownerKey}"/>&elevAppKey=<c:out value="${elevator.elevAppKey}"/>">
              <span style="font-size:x-small">[remove elevator] </span></a></br> 
        <%--       <U>Files Uploaded:</U>
               <c:forEach var="file" items="${elevator.fileList}" varStatus="i">
                   
                     <c:out value="${file.fileName}"/>&nbsp; <br/> 
                     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
                     <a   href="/dfbs/elevator/elevator.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
                           [download this file]</a>
                      </c:if>
            
               <a href="/dfbs/elevator/elevator.do?method=goToUpload&idNumber=<c:out value="${elevator.elevAppKey}"/>&idType=Elevator">
                    [upload files]</a> </c:forEach> --%> </br>
              </c:if>
                </td>
                </tr>
              </c:forEach>
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
        
          