<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="IDHS Child Care Facility Permits" />
<c:set var="title" scope="request" value="Contacts Information" />
<jsp:include page="/app/common/hsPageHeader.jsp" />



<div id="mainBox">

<div id="mainContentFluid">

<h2>Childcare Contacts List</h2>
 <a   href="/dfbs/childCare/contact.do?method=newContact">
             [ add new contact ]</a></br></br>
 <html:link page="/main.do">[back]</html:link>
<html:form action="/contact" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="editContactsComplete"/> 
<c:forEach var="mapItem" items="${sessionScope.OWNER_SELECTED.contactsMap}" varStatus="i">
              <c:set scope="request" var="contact" value="${mapItem.value}"/>
  <hr/>
 
  <h3 style="margin-bottom:5px;">
  Name:   <c:out value="${contact.personLastName}" />, <c:out value="${contact.personFirstName}" /><br />
   </h3>
    Phone: <c:out value="${contact.personPhone}" /><br />
    Fax:<c:out value="${contact.personFax}" /><br />
    Email:<c:out value="${contact.personEmail}" /><br />
    <br />
<%--  <c:if test="${sessionScope.RENEW_RM == 'true' }">   --%>
  <a   href="/dfbs/childCare/contact.do?method=editContact&contactKey=<c:out value="${contact.contactKey}" />">
             [ edit this contact information ]</a></br></br>
<%-- </c:if> --%>
  </c:forEach>


 </div>
 <p>
          <html:submit value=" Add / Editing contacts complete " styleClass="button"/>
        
 </p>
 </html:form>  
<div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp" />
