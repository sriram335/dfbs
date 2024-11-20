<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainmetnt Permits"/>
<c:set var="title" scope="request" value="Application for Annual / Special Entertainmetnt Permits"/>
<c:set var="step" scope="request" value="1"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
  <div id="rightContentFluid">
    <jsp:include page="application.jsp"/>
  </div>
  <div id="leftContentFluid">
  <h2 style="font-weight:bold;">1) Specify Company Information</h2>
  
    
    
    <html:form action="/application" focus="ownerName" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveOwner"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="addressId"/>
      <html:hidden property="contactId"/>
      <html:hidden property="state" styleId="state" />
  <%--    <html:hidden property="stateId" styleId="stateId" /> --%>
      
      <c:if test="${DFBS_SAVE == 'OWNER'}">
        <p class="messageBox message">
        <img class="iconTrue" src="/dfbs/img/clearbits/check.gif" alt="valid"  />
        <strong>Successfully saved to your permits application cart...</strong>
        </p>
      </c:if>
      
      <h3>Company</h3>
      <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">
        <tbody class="rowHeader">
          <c:choose>
          <c:when test="${DFBS_OWNER_APPLICATION.new}">
          <tr>
            <th scope="row" style="width:30%;" class="required">*company:</th>
            
            <td>
              <html:text property="ownerName" size="30" maxlength="60"/>
              <c:if test="${DFBS_OWNER_ERROR.ownerName == 'ERROR'}">
                <br/>
                <span class="error">* please specify company name</span> 
              </c:if>
              </td>
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
              <html:text property="city" size="30" maxlength="30"/>
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
            </c:when>     
            <c:otherwise>
            <tr>
            <th >name:</th>
              <td style="font-size:medium;font-weight:bold;">
              <html:hidden property="ownerName" />
              <c:out value="${DFBS_OWNER_APPLICATION.ownerName}" />
              </td>
              </tr>
              <tr>
              <th >street1:</th>
              <td style="font-size:medium;font-weight:bold;">
              <html:hidden property="street1" />
              <c:out value="${DFBS_OWNER_APPLICATION.street1}" />
              </td>
              </tr>
              <tr>
              <th >street2:</th>
              <td style="font-size:medium;font-weight:bold;">
              <html:hidden property="street2" />
              <c:out value="${DFBS_OWNER_APPLICATION.street2}" />
              </td>
              </tr>
              <tr>
              <th >city:</th>
              <td style="font-size:medium;font-weight:bold;">
              <html:hidden property="city" />
              <c:out value="${DFBS_OWNER_APPLICATION.city}" />
              </td>
              </tr>
              <tr>
              <th >state:</th>
              <td style="font-size:medium;font-weight:bold;">
              <html:hidden property="stateId" />
              <c:out value="${DFBS_OWNER_APPLICATION.state}" />
              </td>
              </tr>
              <tr>
              <th >zip:</th>
              <td style="font-size:medium;font-weight:bold;">
              <html:hidden property="zip" />
              <c:out value="${DFBS_OWNER_APPLICATION.zip}" />
              </td>
              </tr>
              </c:otherwise>
            
            </c:choose>
            <th scope="row" class="required">*phone:</th>
            <td>
              <html:text property="phoneNumber" size="10" maxlength="10" />
              <c:if test="${DFBS_OWNER_ERROR.phoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify a phone number  in the correct format</span> 
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
              <html:text property="contactPhoneNumber" size="10" maxlength="10" />
              <c:if test="${DFBS_OWNER_ERROR.contactPhoneNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify contact phone number in correct format</span> 
              </c:if>
            </td>
          </tr>
        </tbody>
      </table>
      <p>
        <html:submit value="save to your application" styleClass="button"/>
      </p>
    </html:form>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>
