<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Code Enforcement"/>
<c:set var="title" scope="request" value="Application for Industrial / Manufactured Housing"/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<div id="sideContentFluid">
     <a   href="/dfbs/code/viewFacility.do?method=backtoview">
              [ Cancel ]</a>               
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">Buy Seals</h2>
 <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">   
    <html:form action="/viewFacility" focus="sealType" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="postSeals"/>
      <html:hidden property="facilityId"/>
     
            
      <h3>  Seals</h3>
       <tr>
      <th scope="row" class="required">*M seals quantity:</th>
      <td>
        <html:text property="mseals" size="10"/>
        <c:if test="${CODE_FACILITY_ERROR.sealType == 'ERROR'}">
          <br/>
          <span class="error">* please number of  M type seals you want to buy</span> 
        </c:if>
        </br>
      </td>
    </tr>
     <tr>
      <th scope="row" class="required">*p seals quantity:</th>
      <td>
        <html:text property="pseals" size="10"/>
        <c:if test="${CODE_FACILITY_ERROR.sealQuantity == 'ERROR'}">
          <br/>
          <span class="error">* please number of P type seals you want to buy</span> 
        </c:if>
        <br/>
      </td>
    </tr>

</div>
 <tr>
              <th colspan="2">&nbsp;</th>
</tr>
          </tbody>
        </table>
     
 <p class="messageBox">
        <html:submit value="purchase seals" styleClass="button"/></br>
By clicking the "purchase seals" button you are declaring and agreeing to the following:</br>
I do hereby Certify:</br>
1. That each seal will be affixed to only the unit to which it is assigned and to which the Office of the State Building Commissioner has issued a design release;</br>
2.That all applicable rules of the Indiana Fire Prevention and Building Safety Commission have adhered to and that units are built to the appropriate codes and released design documents;</br>
3. That seals will be affixed to units and the office of the State Building Commissioner will be notified within thirty(30) days there after, untill all seals are accounted for by unit serial number and system release number.</br>
</p>
 
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
</html:form>
