<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <c:if test="${VIEW_USER !=null}">
  <html:link page="/user.do?method=changePassword">[change password]</html:link>
  <html:link page="/user.do?method=logOut">[log out]</html:link>
  </c:if>
<div id="mainBox">

<c:if test="${sessionScope.VIEW_USER.userType != 'OTHER'}">
 
 Your Registration is complete. A confirmation email is sent to your email address.
<a href="/dfbs/codeEducation/user.do?method=manageUser">
              [back to user and registrations]  </a> </br>
</c:if>
<c:if test="${sessionScope.VIEW_USER.userType == 'OTHER'}">
<table cellspacing="0" style="width: 75%;" summary="sales">

  <tr>
    <th>Shopping Cart </th>
  </tr>
  <tbody>
 <tr> 
 <td>
 <span class="required" >
 <u><b>In this session</b></u> </br>
  Total courses registered:<c:out value="${sessionScope.VIEW_USER.coursesCount}"/> </br> 
  Course Registration Fees:<c:out value="${sessionScope.VIEW_USER.registrationFee}"/>0</span>
   <p class="messageBox">
      Please note the added transaction fee during checkout. No refunds on the fees paid. 
      </p>
</td>
</tr>
 
  </tbody>
</table>

<html:form action="/checkout" method="post">
      <p>
        <input type="submit" value="proceed to checkout" class="button"/>
      </p>
     
     
</html:form>

 <html:form action="/user" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="manageUser"/>
      <p>
        <input type="submit" value="Register for more courses" class="button"/>
      </p>
      
</html:form>

</c:if> 
          <p>
          <h2>Contact Us</h2>
          <p>
           You can contact us via email or by phone (click the name to launch email)
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/></A> : <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Phone}"/></strong>
          </p>
          <p>
          <A title="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>" href="mailto:<c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>">
          <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/> </A>: <strong><c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Phone}"/></strong>
          </p>
          
