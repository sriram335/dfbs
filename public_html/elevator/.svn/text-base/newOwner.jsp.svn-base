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
<div id="mainBox">
  
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">Specify owner / company Information</h2>
  
    <html:form action="/owner" focus="ownerName" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveNewOwner"/>
      <html:hidden property="ownerId"/>
     
      <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">
        <tbody class="rowHeader">
            <tr>
            <th scope="row" style="width:30%;" class="required">*company:</th>
            
            <td>
              <html:text property="ownerDBA" size="50" maxlength="80"/>
              <c:if test="${DFBS_OWNER_ERROR.ownerName == 'ERROR'}">
                <br/>
                <span class="error">* please specify company name</span> 
              </c:if>
              </td>
          </tr>
          <tr>
            <th scope="row" class="required">*street 1:</th>
            <td>
              <html:text property="ownerAddress1" size="30" maxlength="30"/>
              <c:if test="${DFBS_OWNER_ERROR.street1 == 'ERROR'}">
                <br/>
                <span class="error">* please specify street address</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row">street 2:</th>
            <td>
              <html:text property="ownerAddress2" size="30" maxlength="30"/>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*city:</th>
            <td>
              <html:text property="ownerCity" size="30" maxlength="30"/>
              <c:if test="${DFBS_OWNER_ERROR.city == 'ERROR'}">
                <br/>
                <span class="error">* please specify city</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*state:</th>
            <td>
              <html:select  styleId="stateId" property="ownerStateId" onchange="setSelectValue('stateId','state')">
                <html:option value="">-----</html:option>
                <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
              </html:select>
              <c:if test="${DFBS_OWNER_ERROR.stateId == -1}">
                <br/>
                <span class="error">* please select a state</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*owner zip:</th>
            <td>
              <html:text property="ownerZip" size="5" maxlength="5" />
              <c:if test="${DFBS_OWNER_ERROR.zip == 'ERROR'}">
                <br/>
                <span class="error">* please specify zip code</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*owner phone:</th>
            <td>
              <html:text property="ownerPhone" size="10" maxlength="10" />
              <c:if test="${DFBS_OWNER_ERROR.phoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify a phone number  in the correct format</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row">fax:</th>
            <td>
              <html:text property="ownerFax" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*email:</th>
            <td>
              <html:text property="ownerEmail" size="50" maxlength="80"/>
              <c:if test="${DFBS_OWNER_ERROR.ownerEmail == 'ERROR'}">
                <br/>
                <span class="error">* please specify email address</span> 
              </c:if>
            </td>
          </tr>
          
        </tbody>
       </table>
      <p>
        <html:submit value="save owner" styleClass="button"/>
      </p>
    </html:form>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
