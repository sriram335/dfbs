<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Person's List" />

<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="mainBox" align="left">
<a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> &nbsp;

<c:if test="${EMS_PERSON_LIST_TYPE == 'H' }"> 
     <a href="/dfbs/ems/hospital.do?method=hospitalDetail&hospitalId=<c:out value="${EMS_PERSON_LIST_ID}"/>">
            [back to hospital]  </a>  &nbsp; 
    <a  href="/dfbs/otherUsers/otherUser.do?method=addNewEmsUser&detNumber=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>&personType=Hospital">
            [apply for new user account]  </a> &nbsp;
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.HOSPITAL_SECURITY_FLAG=='Y')}"> 
<a href="/dfbs/ems/person.do?method=addNewPerson&relationId=<c:out value="${EMS_PERSON_LIST_ID}"/>&personType=H">
            [add new contact]  </a>   &nbsp; 
</c:if>
</c:if>
<c:if test="${EMS_PERSON_LIST_TYPE == 'I' }"> 
     <a href="/dfbs/ems/institution.do?method=institutionDetail&institutionId=<c:out value="${EMS_PERSON_LIST_ID}"/>">
            [back to institution]  </a> &nbsp;
    <a  href="/dfbs/otherUsers/otherUser.do?method=addNewEmsUser&detNumber=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>&personType=Institution">
            [apply for new user account]  </a> &nbsp;
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
   <a  href="/dfbs/ems/person.do?method=addNewPerson&relationId=<c:out value="${EMS_PERSON_LIST_ID}"/>&personType=I">
            [add new contact]  </a> &nbsp;
  </c:if>
</c:if>
<c:if test="${EMS_PERSON_LIST_TYPE == 'P' }"> 
     <a href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${EMS_PERSON_LIST_ID}"/>">
            [back to provider]  </a>   &nbsp;
    <a  href="/dfbs/otherUsers/otherUser.do?method=addNewEmsUser&detNumber=<c:out value="${sessionScope.PROVIDER.providerId}"/>&personType=Provider">
            [apply for new user account]  </a> &nbsp;
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
     <a  href="/dfbs/ems/person.do?method=addNewPerson&relationId=<c:out value="${EMS_PERSON_LIST_ID}"/>&personType=P">
            [add new contact]  </a> &nbsp;
</c:if>
</c:if>
  </div>             
    
<div id="mainContentFluid" align="left">

<h2>Current Contact persons </h2>



  <c:forEach var="person" items="${PERSON_LIST.list}" varStatus="i" >
  
  <div class="listing" align="left">
  <h3 style="margin-bottom:5px;">

  <c:if test="${person.personInstId >0}"> 
          <a   href="/dfbs/ems/person.do?method=personDetail&emsPersonId=<c:out value="${person.emsPersonId}"/>&personInstId=<c:out value="${person.personInstId}"/>&personHospId=0&personProvId=0">
             <c:out value="${person.personFirstName}" />, <c:out value="${person.personLastName}" /></a>    
  </c:if>
  <c:if test="${person.personHospId >0}"> 
          <a   href="/dfbs/ems/person.do?method=personDetail&emsPersonId=<c:out value="${person.emsPersonId}"/>&personHospId=<c:out value="${person.personHospId}"/>&personInstId=0&personProvId=0">
             <c:out value="${person.personFirstName}" />, <c:out value="${person.personLastName}" /></a>    
  </c:if>
  <c:if test="${person.personProvId >0}"> 
          <a   href="/dfbs/ems/person.do?method=personDetail&emsPersonId=<c:out value="${person.emsPersonId}"/>&personProvId=<c:out value="${person.personProvId}"/>&personHospId=0&personInstId=0">
             <c:out value="${person.personFirstName}" />, <c:out value="${person.personLastName}" /></a>    
  </c:if>
  </br> <c:out value="${person.personTitle}" /><br />
  </h3>
  <p class="listingInfo">
    <c:out value="${person.personAddress1}" /><br />
    <c:if test="${person.personAddress2 != null && person.personAddress2 != ''}">
    <c:out value="${person.personAddress2}" /><br />
    </c:if>
    <c:out value="${person.personCity}" />, <c:out value="${person.personState}" /> <c:out value="${person.personZip}" /><br />
    <br />
  </p>
  </div>
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>


<jsp:include page="/app/common/hsPageFooter.jsp" />
