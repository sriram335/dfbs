<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<span class="required" >
<a   href="/dfbs/idhsFeesFines/start.do">
              [Clear all and start over]</a>
              </span>
<c:if test="${sessionScope.SHOPPING_CART_FEE.feeIdCounter > 0}">  
<html:form action="/start" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="checkout"/>
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout. (It will be 2% of the total fee for credit card fees + $1 for portal fee.)
      </p>
</html:form>
</c:if>
<%--
 <html:form action="/start" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="otherFees"/>
      <p>
        <input type="submit" value="Search Other Fees" class="button"/>
      </p>
</html:form> --%>
 <table cellspacing="0" style="width: 75%;" summary="sales">

  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
  <tr>
  <td>
 <span class="required" >
 <U><b>In this session</b></U></br>
  Total transactions:<c:out value="${sessionScope.SHOPPING_CART_FEE.feeIdCounter}"/></br> 
  Total Fees:<c:out value="${sessionScope.SHOPPING_CART_FEE.amount}"/>0</span>
</td>
</tr>
  <c:forEach var="mapItem" items="${sessionScope.SHOPPING_CART_FEE.feeIdMap}" varStatus="i">
              <c:set scope="request" var="transaction" value="${mapItem.value}"/>
     
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
       State Number: <c:out value="${transaction.uniqueNumber}"/>;
          Post Date:<c:out value="${transaction.postDate}"/></br>
          Fee Due:<c:out value="${transaction.due}"/>
              <a   href="/dfbs/idhsFeesFines/start.do?method=removeTransaction&transactionId=<c:out value="${transaction.transactionId}"/>">
              <span style="font-size:x-small">[remove transaction] </span></a></br> 
            </td>
      </tr>
  </c:forEach>
  </tbody>
</table>




<h2>Contact Us</h2>
 <!--
          For Amusement Rides questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.ridesContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.ridesContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.ridesContactName}"/>  </A>
        </br> For Elevator questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.elevContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.elevContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.elevContactName}"/>  </A>
   -->    </br> For Blaster Certificate questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.blasterContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.blasterContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.blasterContactName}"/>  </A>
        </br> For Boiler and Pressure Vessels questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.bpvContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.bpvContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.bpvContactName}"/>  </A>
          </br> For Industrial Building CDR, Purchase Seals, and Plan Review questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.codeContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.codeContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.codeContactName}"/>  </A>
        </br> For Variance Application questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.varContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.varContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.varContactName}"/>  </A>
         </br> For all other questions contact: <A title="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.fireContactEmail}"/>"  href="mailto:<c:out value="${sessionScope.FEE_APP_CONTACTS.fireContactEmail}"/>"> <c:out value="${sessionScope.FEE_APP_CONTACTS.fireContactName}"/>  </A>
      
          </br>To help us address your questions/concerns please add State Number, Transaction ID and posting date details in your email.
          
