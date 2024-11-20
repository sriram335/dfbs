<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:xhtml/>

<jsp:include page="/app/common/hsPageHeader.jsp" />
<jsp:include page="module/breadcrumbs.jsp" />
<h2>
<p>
You have successfully submitted your application.
</p>
</h2>
     <p>
     You have applied for a fee exempt AE permit. </br>Your application will not be processed
untill we receive the copy of IRS fee exempt letter.</br> (If you have already submitted the required documentation</br>
     for the current year, you need not submit it again.)</br>You can mail the copy of IRS letter to the following
  address or you can fax it to 317-233-0307. Or if you have a electronic copy of the document you can
     email it to: <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/> at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Email}"/>
     or <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Name}"/> at <c:out value="${sessionScope.APPLICATION_CONTACTS.contact2Email}"/>. If we have any questions regarding the application
     we will contact you at the email / phone number provided by you in the application.</br></br>
     <u>Mailing Address</u></br>
          Department of Fire and Building Services</br>
          Permit Division, State Fire Marshal Office</br>
          302 West Washington St, # E241</br>
          Indianapolis, IN 46204</br>
          
     </p>
     
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
<h3>You might want to...</h3>
    <div class="sideContentList">
    <ul>
    <li> <img class="icon" src="/dfbs/img/clearbits/arrow2_e.gif" alt="arrow" /> go back to the <a href="/dfbs/aepermits/main.do">home page</a></li>
    </ul>
    </div>