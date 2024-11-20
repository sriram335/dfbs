<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Elevator Safety Division"/>
<c:set var="title" scope="request" value="New Owner Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
</div>
<div id="mainBox">
  
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">Add / Update Insurance Information</h2>
  
    <html:form action="/insurance" focus="insName" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInsurance"/>
      <html:hidden property="insuranceId"/>
      <html:hidden property="ownerId"/>
      <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">
        <tbody class="rowHeader">
            <tr>
            <th scope="row" style="width:30%;" class="required">* Insurance Name:</th>
            <td>
              <html:text property="insName" size="30" maxlength="30"/>
              <c:if test="${AMUSE_INS_ERROR.insName == 'ERROR'}">
                <br/>
                <span class="error">* please specify insurance name</span> 
              </c:if>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" class="required">* Policy Number:</th>
            <td>
             <html:text property="policyNumber" size="30" maxlength="30"/>
             <c:if test="${AMUSE_INS_ERROR.policyNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify policy number</span> 
              </c:if>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" class="required">* Effective Date:</th>
            <td>
             <html:text property="effDate" size="10" maxlength="10"/> (mm/dd/yyyy)
             <c:if test="${AMUSE_INS_ERROR.effDate == 'ERROR'}">
                <br/>
                <span class="error">* please specify effective date</span> 
              </c:if>
              </td>
          </tr>
           <tr>
            <th scope="row" style="width:30%;" class="required">* Expiration Date:</th>
            <td>
             <html:text property="expDate" size="10" maxlength="10"/> (mm/dd/yyyy)
             <c:if test="${AMUSE_INS_ERROR.expDate == 'ERROR'}">
                <br/>
                <span class="error">* please specify effective date</span> 
              </c:if>
              </td>
          </tr>
         <tr>
            <th scope="row" style="width:30%;" class="required">* Amount Covered:</th>
            <td>
             <html:text property="amountCovered" size="10" maxlength="10"/> 
             <c:if test="${AMUSE_INS_ERROR.amountCovered == 0}">
                <br/>
                <span class="error">* please specify amount covered</span> 
              </c:if>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Signed Date:</th>
            <td>
             <html:text property="signedDate" size="10" maxlength="10"/> (mm/dd/yyyy)
             </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Branch Office:</th>
            <td>
             <html:text property="branchOffice" size="30" maxlength="30"/> 
             </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Authorized Representative:</th>
            <td>
             <html:text property="authRep" size="30" maxlength="30"/> 
             </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Address1:</th>
            <td>
             <html:text property="address1" size="30" maxlength="30"/>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Address2:</th>
            <td>
             <html:text property="address2" size="30" maxlength="30"/>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > city:</th>
            <td>
             <html:text property="city" size="30" maxlength="30"/>
              </td>
          </tr>
          <tr>
          <th scope="row" style="width:30%;" > State :</th>
          <td>
            <html:select property="state"  onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
              <html:option value="">Please Select</html:option>
              <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
            </html:select>
           </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > zip:</th>
            <td>
             <html:text property="zip" size="5" maxlength="5"/>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > zip2:</th>
            <td>
             <html:text property="zip" size="4" maxlength="5"/>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Phone:</th>
            <td>
             <html:text property="phone" size="10" maxlength="10"/>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Fax:</th>
            <td>
             <html:text property="fax" size="10" maxlength="10"/>
              </td>
          </tr>
      </tbody>
       </table>
      <p>
        <html:submit value="save Inurance Info" styleClass="button"/>
      </p>
    </html:form>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>