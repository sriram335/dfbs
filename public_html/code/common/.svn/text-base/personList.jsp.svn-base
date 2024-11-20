<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th>Manufacturer's contact Person(s)</th>
    <th>Details</th>
  </tr>
  <tbody>
  <c:forEach var="mapItem" items="${sessionScope.MANUFACTURER.personMap}" varStatus="i">
              <c:set scope="request" var="person" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
          <c:out value="${person.codePersonLastName}"/>&nbsp;
          <c:out value="${person.codePersonFirstName}"/>&nbsp;<br/>
         Title: <c:out value="${person.codePersonTitle}"/></br> 
           <a   href="/dfbs/code/editPerson.do?method=editPerson&personType=<c:out value="${person.codePersonType}"/>&facilityId=<c:out value="${person.codePersonCompanyId}"/>&applicationKey=<c:out value="${person.applicationKey}"/>">
         [ Edit ]</a>
        </td>
        <td>
        
         Phone: <c:out value="${person.codePersonTelephone}"/></br>
         Fax: <c:out value="${person.codePersonFax}"/></br>
         Email:<c:out value="${person.codePersonEmail}"/>&nbsp;<br>
        
        </td>
       
        
      </tr>
     
    </c:forEach>
  </tbody>
</table>