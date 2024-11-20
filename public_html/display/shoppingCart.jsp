<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<span class="required" >
<a   href="/dfbs/display/start.do">
              [Clear all and start over]</a>
              </span>
<c:if test="${sessionScope.SHOPPING_CART.totalPermits > 0}">  
<c:if test="${sessionScope.DFBS_SECURITY == null}">   
<c:if test="${sessionScope.SHOPPING_CART.receiptId == 0}">
 <html:form action="/checkout" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout.  
      </p>
      <c:if test="${sessionScope.APPLICATION_ERROR == 'true'}"> 
           <b><p class="error"> Application errors! Verify red error messages in the shopping cart and fix them.After fixing them click again on proceed to check out</br></p></b>
      </c:if>
</html:form>

</c:if>
</c:if>
</c:if>  
<c:if test="${sessionScope.sessionScope.SHOPPING_CART.ownerMapCount > 0}"> 
 <html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="backToStart"/>
      <p>
        <input type="submit" value="Renew/ Apply permits for different owner" class="button"/>
      </p>
      
</html:form>
</c:if>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">     
 <html:form action="/checkout" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="check"/>
      <p>
        <input type="submit" value="Received Check / Save " class="button"/>
      </p>
       Check Number:<html:text property="receiptId" size="30" maxlength="30"/>
      
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
 <U><b>In this session</U></b></br>
  Total Owners:<c:out value="${sessionScope.SHOPPING_CART.ownerMapCount}"/></br> 
  Total Permits:<c:out value="${sessionScope.SHOPPING_CART.totalPermits}"/></br>
  Fees:<c:out value="${sessionScope.SHOPPING_CART.amount}"/>0</span>
</td>
</tr>
  <c:forEach var="mapItem" items="${sessionScope.SHOPPING_CART.ownerMap}" varStatus="i">
              <c:set scope="request" var="owner" value="${mapItem.value}"/>
     
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
       <b>Owner: <c:out value="${owner.ownerLastName}"/>,<c:out value="${owner.ownerFirstName}"/></b> </br>
        <c:if test="${owner.ownerError == 'false'}"> 
               <p class="error"> Mandatory owner information missing, edit owner.</br></p>
              </c:if>
       <c:if test="${sessionScope.SHOPPING_CART.receiptId == '0'}"> 
        <a   href="/dfbs/display/display.do?method=addPermit&ownerKey=<c:out value="${owner.ownerKey}"/>">
              <span style="font-size:x-small">[add new permit, same owner]</span> </a></br>
         <html:link page="/ownerListDisplay.do?method=editOwner" paramId="key" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[edit owner]</span>                  
         </html:link>
         <html:link page="/ownerListDisplay.do?method=removeOwner" paramId="key" paramName="mapItem" paramProperty="key">
         <span style="font-size:x-small">[remove owner]</span> 
          </html:link> </br> 
        </c:if>  
          Total Permits for this owner:<c:out value="${owner.permitsMapCount}"/></br>
     <%--      Total PermitsList:<c:out value="${owner.displayListCount}"/></br> --%>
          Fees for this owner:<c:out value="${owner.amount}"/>0
           <c:forEach var="mapItem" items="${owner.permitsMap}" varStatus="j">
                      <c:set scope="request" var="permit" value="${mapItem.value}"/>
              <tr >
                <td>
               <c:if test="${permit.displayError == 'false'}"> 
               <p class="error"> Mandatory display information missing, edit display.</br></p>
              </c:if>
              <c:if test="${permit.displayUploadError == 'false'}"> 
               <p class="error"> Display shooter resume needed.</br></p>
              </c:if>
               <c:if test="${permit.displayDateError == 'false'}"> 
               <p class="error"> Display dates needed.(use Add /Edit Display Date(s) option)</br></p>
              </c:if>
               Permit Number:New <c:out value="${permit.displayPermitKey}"/> </br>
               Permit Permit Fee: <c:out value="${permit.amount}"/>0</br>
              <c:if test="${sessionScope.SHOPPING_CART.receiptId == '0'}">
              <a   href="/dfbs/display/display.do?method=editPermit&ownerKey=<c:out value="${owner.ownerKey}"/>&displayKey=<c:out value="${permit.displayPermitKey}"/>">
              <span style="font-size:x-small">[edit permit]</span> </a>  
               <a   href="/dfbs/display/display.do?method=removePermit&ownerKey=<c:out value="${owner.ownerKey}"/>&displayKey=<c:out value="${permit.displayPermitKey}"/>">
              <span style="font-size:x-small">[remove permit]</span> </a>  </br>
              <a   href="/dfbs/display/display.do?method=goToUpload&displayKey=<c:out value="${permit.displayPermitKey}"/>">
              <span style="font-size:x-small">[Upload display shooter resume]</span></a>
             <%-- <a   href="/dfbs/display/displayDate.do?method=addDate&ownerKey=<c:out value="${owner.ownerKey}"/>&displayKey=<c:out value="${permit.displayPermitKey}"/>">
              <span style="font-size:x-small">[Add /Edit Display Date(s)]</span></a></br></br> --%>
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
          </p>              
          
