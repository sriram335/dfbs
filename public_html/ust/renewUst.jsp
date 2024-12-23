<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Ust Applicants List" />
<c:set var="title" scope="request" value="Renew UST Application" />
<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">
<div id="sideContentFluid">
   
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
</div>


<div id="mainContentFluid" align="left">

<h2>UST Certifications Listed by Name</h2>

<div class="charNav">
<ul>
<c:forEach var="c" items="${UST_FIRST_LETTERS_OPTIONS}" varStatus="i" >
<c:choose>
<c:when test="${UST_LIST_FILTER.type == 'byLetter' && UST_LIST_FILTER.value == c.character}">
  <li class="selected">
</c:when>
<c:otherwise>
<li>
</c:otherwise>
</c:choose>
<c:url var="url" value="/ust/ust.do?method=filter&filter=byLetter&letter=${c.character}" />
<a title="<c:out value='${c.count}' /> owners" href="<c:out value='${url}' />"><c:out value='${c.character}' /></a>
</c:forEach>
</ul>
</div>


  <c:forEach var="ust" items="${UST_LIST.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
    <html:link page="/ust.do?method=appRenewal" paramId="ustId" paramName="ust" paramProperty="ustId">
    <c:out value="${ust.personLastName}" />
    </html:link> 
     </h3>
  <p class="listingInfo">
    <c:out value="${ust.personAddress1}" /><br />
    <c:if test="${ust.personAddress2 != null && ust.personAddress2 != ''}">
    <c:out value="${ust.personAddress2}" /><br />
    </c:if>
    <c:out value="${ust.personCity}" />, <c:out value="${ust.personState}" /> <c:out value="${ust.personZip}" /><br />
    Issue Date :<c:out value="${ust.issueDate}" />; Expiration Date :<c:out value="${ust.expDate}" />;Status:<c:out value="${ust.status}" />;
    <br />
    <a   href="/dfbs/ust/ust.do?method=appRenewal&ustId=<c:out value="${ust.ustId}"/>">
             [ Renew Certification ]</a> 
     <c:if test="${ust.issueDate != null && ust.issueDate != ''}"> 
      <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=ust_certification_laser.rdf&p_ust_id=<c:out value="${ust.ustId}"/>"> [Print Certificate] </a></br>
    </c:if>
     <c:if test="${ust.issueDate == null }"> 
     Certificate In Process
      </c:if>
      <c:if test="${sessionScope.DFBS_SECURITY != null  }">
      <a   href="/dfbs/ust/ust.do?method=sendCertification&ustId=<c:out value="${ust.ustId}"/>&userEmail=<c:out value="${ust.personEmail}"/>">
             [ Email Certification ]</a> 
      <a   href="/dfbs/ust/ustCert.do?method=updateUstCert&ustId=<c:out value="${ust.ustId}"/>">
             [update Certification ]</a> 
      </c:if>
  </p>
  </div>
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
