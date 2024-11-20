<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Code Education Online Application"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">

 
 If you reach this page, Sorry the class is full and currently does not 
 accept registrations. You can come back later and see if any cancellations open up the registration.


 <html:form action="/user" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="backToManageUser"/>
      <p>
        <input type="submit" value="Register for more courses" class="button"/>
      </p>
      
</html:form>


<h2>Contact Us</h2>
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
          
