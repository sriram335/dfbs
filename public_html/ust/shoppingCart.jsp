<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<span class="required" >
<a   href="/dfbs/ust/ust.do">
              [Clear all and start over]</a>
              </span>
<c:if test="${sessionScope.SHOPPING_CART_UST.amount > 0 && sessionScope.UST_APPLICANT.fileListCount >0}">  
<html:form action="/checkOut" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
      <p class="messageBox">
      Please note the added transaction fee during checkout. (It will be 2% of the total fee for credit card fees + $1 for portal fee.)
      <b> Use Update link below to upload any supporting documents before proceeding to checkout </b>
      </p>
</html:form>
</c:if>
<c:if test="${sessionScope.SHOPPING_CART_UST.amount > 0 && sessionScope.UST_APPLICANT.fileListCount == 0}">  
    <p class="error">
      You need to upload supporting documents!
      <b> Use Upload link below to upload any supporting documents before proceeding to checkout </b>
      </p>

</c:if>
<a   href="/dfbs/ust/ust.do?method=goToUpload">
              [upload supporting documents]</a><%--
  <a   href="/dfbs/ust/completeCheckOut.do?method=check">
              [test]</a>           

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
  Renew Application for:<c:out value="${sessionScope.UST_APPLICANT.personLastName}"/></br> 
  Total Fees:<c:out value="${sessionScope.SHOPPING_CART_UST.amount}"/>0</span>
  <a   href="/dfbs/ust/ust.do?method=goToUpload">
              [upload supporting documents]</a>
</td>
</tr>
<c:forEach var="file" items="${sessionScope.UST_APPLICANT.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
             File date:<c:out value="${file.fileDateString}"/>&nbsp; File sent by:<c:out value="${file.fileLoadedBy}"/>;&nbsp;File Type:<c:out value="${file.filePlanType}"/></br>
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
