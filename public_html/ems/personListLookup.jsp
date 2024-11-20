<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Indiana Homeland Security / Ems Operations" />
<c:set var="title" scope="request" value="Person's List" />


<div id="mainBox">
<div id="sideContentFluid">

               
</div>
    
<div id="mainContentFluid">

<h2>Current Contact persons </h2>


<c:if test="${EMS_PERSON_LOOKUP == 'Y' }"> 
<html:form action="/person" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="addPerson"/>  
<p>
   
          <html:submit value="Not in list" styleClass="button" />
</p>      
</html:form>   
</c:if>    


  <c:forEach var="person" items="${PERSON_LIST_LOOKUP.list}" varStatus="i" >
  
  <div class="listing">
  <h3 style="margin-bottom:5px;">
   <c:out value="${person.emsPersonId}" />
   <c:out value="${person.personFirstName}" />  <c:out value="${person.personLastName}" /></a>    
   </br> <c:out value="${person.personTitle}" /><br />
  </h3>
  <%--
  <input type="checkbox" name="<c:out value="${person.emsPersonId}"/>" class="switch"/> --%>
  <p class="listingInfo">
    <c:out value="${person.personAddress1}" /><br />
    <c:if test="${person.personAddress2 != null && person.personAddress2 != ''}">
    <c:out value="${person.personAddress2}" /><br />
    </c:if>
    <c:out value="${person.personCity}" />, <c:out value="${person.personState}" /> <c:out value="${person.personZip}" /><br />
    <br />
  </p>
  <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || EMS_SECURITY.currentUserLevel == 'USER'}">
   <a href="/dfbs/ems/person.do?method=addNewContact&personId=<c:out value="${person.emsPersonId}"/>">
    [add this contact]  </a> 
   
  </c:if>
  
  </div>
   
 
  </c:forEach>

</div>


<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
