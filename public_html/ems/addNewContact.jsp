<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value=" Add New Contact" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox">
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> 

<c:if test="${EMS_PERSON_LIST_TYPE == 'H' }"> 
     <a href="/dfbs/ems/hospital.do?method=hospitalDetail&hospitalId=<c:out value="${EMS_PERSON_LIST_ID}"/>">
            [back to hospital]  </a>               
<a href="/dfbs/ems/person.do?method=addNewPerson&relationId=<c:out value="${EMS_PERSON_LIST_ID}"/>&personType=H">
            [add new contact]  </a>     
</c:if>
<c:if test="${EMS_PERSON_LIST_TYPE == 'I' }"> 
     <a href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${EMS_PERSON_LIST_ID}"/>">
            [back to institution]  </a>  
   <a  href="/dfbs/ems/person.do?method=addNewPerson&relationId=<c:out value="${EMS_PERSON_LIST_ID}"/>&personType=I">
            [add new contact]  </a>   
</c:if>
<c:if test="${EMS_PERSON_LIST_TYPE == 'P' }"> 
     <a href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${EMS_PERSON_LIST_ID}"/>">
            [back to provider]  </a>    
     <a  href="/dfbs/ems/person.do?method=addNewPerson&relationId=<c:out value="${EMS_PERSON_LIST_ID}"/>&personType=P">
            [add new contact]  </a>   
</c:if>
               
<html:form action="/person" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveNewContact"/>  
      <html:hidden property="emsPersonId"/> 
<div id="mainContentFluid">

 
  <div class="listing">
  <h3 style="margin-bottom:5px;">
            <c:out value="${EMS_PERSON_NEW_CONTACT.personFirstName}" />, <c:out value="${person.personLastName}" /></a>    
  </h3>
  <p class="listingInfo">
    <c:out value="${EMS_PERSON_NEW_CONTACT.personAddress1}" /><br />
    <c:if test="${EMS_PERSON_NEW_CONTACT.personAddress2 != null && EMS_PERSON_NEW_CONTACT.personAddress2 != ''}">
    <c:out value="${EMS_PERSON_NEW_CONTACT.personAddress2}" /><br />
    </c:if>
    <c:out value="${EMS_PERSON_NEW_CONTACT.personCity}" />, <c:out value="${EMS_PERSON_NEW_CONTACT.personState}" /> <c:out value="${EMS_PERSON_NEW_CONTACT.personZip}" /><br />
    <br />
     Contact person type: <html:text property="personType" size="30" maxlength="30"/>
     Contact person title: <html:text property="personTitle" size="30" maxlength="30"/>
  </p>
  <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
 <p>
   
          <html:submit value="Add this contact" styleClass="button" />
   </p>
   </c:if>
  </div>


</div>
 </html:form>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
