 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Fireworks"/>
<c:set var="title" scope="request" value="Application for Sale of Fireworks"/>
<c:set var="step" scope="request" value="1"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="rightContentFluid">
    <jsp:include page="application.jsp"/>
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">Add / Update Company Information</h2>
  
    
    
    <html:form action="/application" focus="ownerName" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveOwner"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="addressId"/>
      <html:hidden property="contactId"/>
      <html:hidden property="state" styleId="state" />
      
      
      
      
      <h3>Company</h3>
      <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">
        <tbody class="rowHeader">
          <tr>
            <th scope="row" style="width:30%;" class="required">*company:</th>
            <c:choose>
            <c:when test="${DFBS_OWNER_APPLICATION.new}">
            <td>
              <html:text property="ownerName" size="30" maxlength="60"/>
              <c:if test="${DFBS_OWNER_ERROR.ownerName == 'ERROR'}">
                <br/>
                <span class="error">* please specify company name</span> 
              </c:if>
              </td>
              </c:when>
              <c:otherwise>
              <td style="font-size:large;font-weight:bold;">
              <html:hidden property="ownerName" />
              <c:out value="${DFBS_OWNER_APPLICATION.ownerName}" />
              </td>
              </c:otherwise>
            
            </c:choose>
          </tr>
          <tr>
            <th scope="row" class="required">*street 1:</th>
            <td>
              <html:text property="street1" size="30" maxlength="30"/>
              <c:if test="${DFBS_OWNER_ERROR.street1 == 'ERROR'}">
                <br/>
                <span class="error">* please specify street address</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row">street 2:</th>
            <td>
              <html:text property="street2" size="30" maxlength="30"/>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*city:</th>
            <td>
              <html:text property="city" size="20" maxlength="30"/>
              <c:if test="${DFBS_OWNER_ERROR.city == 'ERROR'}">
                <br/>
                <span class="error">* please specify city</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*state:</th>
            <td>
              <html:select  styleId="stateId" property="stateId" onchange="setSelectValue('stateId','state')">
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
            <th scope="row" class="required">*zip:</th>
            <td>
              <html:text property="zip" size="5" maxlength="5" />
              <c:if test="${DFBS_OWNER_ERROR.zip == 'ERROR'}">
                <br/>
                <span class="error">* please specify zip code</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">*phone:</th>
            <td>
              <html:text property="phoneNumber" size="10" maxlength="10" /> (type the numbers only)

              <c:if test="${DFBS_OWNER_ERROR.phoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify a phone number</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row">fax:</th>
            <td>
              <html:text property="faxNumber" size="10" maxlength="10" />
            </td>
          </tr>
          <tr>
            <th scope="row" class="required">email:</th>
            <td>
            <html:text property="ownerEmail" size="30" maxlength="70"/>
              <c:if test="${DFBS_OWNER_ERROR.ownerEmail == 'ERROR'}">
               <br/>
                <span class="error">* please enter correct Email</span> 
              </c:if>
            </td>
          </tr>
          
        </tbody>
      </table>
      <h3>Contact</h3>
      <table cellspacing="0" class="noBorder" summary="CONTACT FORM" style="width:100%;">
        <tbody class="rowHeader">
          <tr>
            <th scope="row" style="width:30%;" class="required">*first name:</th>
            <td>
              <html:text property="firstName" size="20" maxlength="30" />
              <c:if test="${DFBS_OWNER_ERROR.firstName == 'ERROR'}">
                <br/>
                <span class="error">* please specify firstname of contact</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" class="required">*last name:</th>
            <td>
              <html:text property="lastName" size="20"  maxlength="30" />
              <c:if test="${DFBS_OWNER_ERROR.lastName == 'ERROR'}">
                <br/>
                <span class="error">* please specify lastname of contact</span> 
              </c:if>
            </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" class="required">*phone:</th>
            <td>
              <html:text property="contactPhoneNumber" size="10" maxlength="10" /> (type the numbers only)

              <c:if test="${DFBS_OWNER_ERROR.contactPhoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify contact phone number</span> 
              </c:if>
            </td>
          </tr>
        </tbody>
      </table>
      <p>
        <html:submit value="Next" styleClass="button"/>
      </p>
    </html:form>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
