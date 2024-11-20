<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
New Permits:

<table cellspacing="0" style="width: 30%;" summary="sales">
  
  <tbody>
    <c:forEach var="permit" items="${sessionScope.PERMITS_NEW}" varStatus="i">
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
           <a   href="/dfbs/aepermits/permit.do?method=updatePermit&permitNumber=<c:out value="${permit.permitNumber}"/>">
              <c:out value="${permit.permitNumber}"/></a> 
        </td>
        </tr>
      </c:forEach>
     </table>
  
     <p class="error" style="font-size:medium;font-weight:bold;padding: 12px;">
    Special  Permits
    </p> 
    <table cellspacing="0" style="width: 30%;" summary="sales">
    <tbody>
    <c:forEach var="special" items="${sessionScope.SPECIALS_NEW}" varStatus="i">
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
           <a   href="/dfbs/aepermits/special.do?method=updateSpecial&specialId=<c:out value="${special.specialId}"/>">
             <c:out value="${special.permitNumber}"/></a> 
        </td>
        </tr>
      </c:forEach>
     </table>
    
    
  
 