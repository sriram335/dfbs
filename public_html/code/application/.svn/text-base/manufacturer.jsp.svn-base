<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Code Enforcement"/>
<c:set var="title" scope="request" value="Application for Industrialized Building Systems/ Mobile Structures"/>

<html:xhtml/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>

<div id="mainBox">

  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">Edit web page information</h2>
   <html:form action="/main" focus="manufacturerName" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveManufacturer"/>
      <html:hidden property="manufacturerNameId"/>
      <html:hidden property="manufacturerEntityId"/>
      <html:hidden property="manufacturerName"/>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
  <tbody class="rowHeader">   
       
      <h3>  Manufacturer Details</h3>
       <tr>
        <th scope="row">*Manufacturer Name: </th>
        <td>
             <c:out value="${MANUFACTURER.manufacturerName}" />
        </td>
        </tr>
        <tr>
          <th scope="row">address:</th></br>
          <td>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerAddress1}"/></br>
            <c:if test="${sessionScope.MANUFACTURER.manufacturerAddress2 != null && sessionScope.MANUFACTURER.manufacturerAddress2 != ''}">
              <c:out value="${sessionScope.MANUFACTURER.manufacturerAddress2}"/></br>
            </c:if>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerCity}"/>,
            <c:out value="${sessionScope.MANUFACTURER.manufacturerState}"/>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerZip}"/>
            <c:out value="${sessionScope.MANUFACTURER.manufacturerStatus}"/>
          </td>
        </tr> 
        <tr>
        <th scope="row" style="width:30%;" class="required">Web Page:</th></br>
        <td>
        <html:text property="manufacturerWebPage" size="50" maxlength="80"/>
        </td>
        </tr>
         </tbody>
        </table>
  <p>
        <html:submit value="save to your application" styleClass="button"/>
</p>
</div>
  <div class="clearer">&nbsp;</div>
</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
</html:form>