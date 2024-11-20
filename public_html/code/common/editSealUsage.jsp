<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Code Enforcement"/>
<c:set var="title" scope="request" value="Application for Industrialized Building Systems/ Mobile Structures"/>
<c:set var="step" scope="request" value="2"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
      <a   href="/dfbs/code/seal.do?method=processSeals&orderId=" >
              [ Cancel ]</a> </br>     
     
  </div>
<div id="mainBox">
  <h2>Edit Seal Usage</h2>
 
  <div id="leftContentFluid">
 <html:form action="/sealUsage" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSealUsage"/>
      <html:hidden property="orderId"/>
  

  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
    <th scope="row"   >seal number:</th>
     <td>
    <c:out value="${SEAL_USAGE.sealNumber}" />
    <html:hidden property="sealNumber"/>
     </td>
     </tr>
     <tr>
    <th scope="row"   >inspector:</th>
     <td>
    <c:out value="${SEAL_USAGE.inspectorName}" />
     </td>
     </tr>
     <tr>
    <th scope="row"   >inspecting company:</th>
     <td>
    <c:out value="${SEAL_USAGE.companyName}" />
     </td>
     </tr>
     <tr>
      <th scope="row"   >unit number:</th>   
   <td>
   <html:text property="sealUnitNumber" size="20" maxlength="20"/>
   </td>
   </tr>
   <tr>
      <th scope="row"   >release number:</th>   
   <td>
   <html:text property="sealRelNumber" size="20" maxlength="20"/>
   </td>
   </tr>
    <tr>
      <th scope="row"   >seal update date:</th>   
   <td>
   <html:text property="sealUpdateDate" size="10" maxlength="10"/>(mm/dd/yyyy)
   </td>
   </tr>
     <tr>
      <th scope="row"   >seal used date:</th>   
   <td>
   <html:text property="sealUsedDate" size="10" maxlength="10"/>(mm/dd/yyyy)
   </td>
   </tr>
    <tr>
      <th scope="row"   >seal inspected date:</th>   
   <td>
   <html:text property="sealInspDate" size="10" maxlength="10"/>(mm/dd/yyyy)
   </td>
   </tr>
  
<tr>
<th scope="row"   ></th>  
<td>
 <p>
   
          <html:submit value="Update Seal information" styleClass="button" />
 </p>
 </td>
 </tr>
  </tbody>
 </html:form>
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
<jsp:include page="/app/common/hsPageFooter.jsp"/>
