<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

 <jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="sideContentFluid">
     <a   href="/dfbs/code/viewFacility.do?method=backtoview">
              [ Cancel ]</a>               
  </div>
  <div id="mainContentFluid" align="left">
  <html:form action="/viewFacility" focus="codePersonLastname" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="backtoview"/>   
    <table cellspacing="0" class="noBorder" summary="MANUFACTURER DATA">
      <tbody class="rowHeader">
        <tr style= "width:150%;" >
          <th scope="row" colspan="2" style="font-size:large;font-weight:bold;">
    <c:out value="${sessionScope.FACILITY.facilityName}" />
     </th>
        </tr>
        <tr>
          <th scope="row">address:</th>
          <td>
         <c:out value="${sessionScope.FACILITY.facilityAddress1}"/>
          <br/>
          <c:if test="${sessionScope.FACILITY.facilityAddress2 != null && facility.facilityAddress2 != ''}">
            <c:out value="${sessionScope.FACILITY.facilityAddress2}"/>
            <br/>
          </c:if>
          <c:out value="${sessionScope.FACILITY.facilityCity}"/>,
          <c:out value="${sessionScope.FACILITY.facilityState}"/>
          <c:out value="${sessionScope.FACILITY.facilityZip}"/>&nbsp;
          </td>
         </tr>
         <tr>
         <td>
          <a   href="/dfbs/code/editPerson.do?method=newPerson&codePersonType=F&codePersonCompanyId=<c:out value="${sessionScope.FACILITY.facilityId}"/>">
              [ Add new contact ]</a>
          </td>
          </tr>
        <tr>
        <c:forEach var="mapItem" items="${sessionScope.FACILITY.personMap}" varStatus="i">
          <c:set scope="request" var="person" value="${mapItem.value}"/>
          <jsp:include page="personsListFacility.jsp"/>    
         </c:forEach >
        </tr>
                
      </tbody>
    </table>
   
        
       
      <p>
        <html:submit value="save information" styleClass="button"/>
</p>
    </div>
    </div>

  <div class="clearer">&nbsp;</div>
  
</html:form>