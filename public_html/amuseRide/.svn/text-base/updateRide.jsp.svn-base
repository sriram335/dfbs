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
  <h2 style="font-weight:bold;">Add / Update Amusement Ride Information</h2>
  
    <html:form action="/ride" focus="serialNumber" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSaveRide"/>
     
      <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">
        <tbody class="rowHeader">
            <tr>
            <th scope="row" style="width:30%;" class="required">* Serial Number:</th>
            <td>
            <c:if test="${AMUSE_RIDE_SELECTED.serialNumber == null}">
              <html:text property="serialNumber" size="30" maxlength="30"/>
            </c:if>
              <c:if test="${AMUSE_RIDE_SELECTED.serialNumber != null}">
              <c:out value="${AMUSE_RIDE_SELECTED.serialNumber}" />
                <html:hidden property="serialNumber"/>
           </c:if>
              <c:if test="${AMUSE_RIDE_ERROR.serialNumber == 'ERROR'}">
                <br/>
                <span class="error">* please specify serial number</span> 
              </c:if>
              </td>
          </tr>
          <c:if test="${AMUSE_RIDE_SELECTED.serialNumber != null}">
          <tr>
            <th scope="row" style="width:30%;" >* Permit Number:</th>
            <td>
             <c:out value="${AMUSE_RIDE_SELECTED.serialNumber}" />
              </td>
          </tr>
          </c:if>
          <tr>
            <th scope="row" style="width:30%;" class="required">* Device Name:</th>
            <td>
             <html:text property="deviceName" size="30" maxlength="30"/>
             <c:if test="${AMUSE_RIDE_ERROR.deviceName == 'ERROR'}">
                <br/>
                <span class="error">* please specify device name</span> 
              </c:if>
              </td>
          </tr>
          <tr>
          <th scope="row" style="width:30%;" class="required">* Device Type :</th>
          <td>
            <html:select property="deviceId"  onchange="setSelectValue('SELECT_DEVICE_ID','DEVICE_ID')">
              <html:option value="">Please Select</html:option>
              <html:options collection="DFBS_DEVICE_OPTIONS" property="value" labelProperty="label"/>
            </html:select>
            <c:if test="${AMUSE_RIDE_ERROR.deviceId == 0}">
                <br/>
                <span class="error">* please specify device type</span> 
              </c:if>
           </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Accident Date:</th>
            <td>
             <html:text property="accDate" size="10" maxlength="10"/>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" > Accident Number:</th>
            <td>
             <html:text property="numberAccidents" size="10" maxlength="10"/>
              </td>
          </tr>
           <tr>
            <th scope="row" style="width:30%;" class="required"> Capacity:</th>
            <td>
             <html:text property="capacity" size="30" maxlength="30"/>
             <c:if test="${AMUSE_RIDE_ERROR.capacity == 'ERROR'}">
                <br/>
                <span class="error">* please specify device name</span> 
              </c:if>
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" class="required"> Speed:</th>
            <td>
             <html:text property="speed" size="30" maxlength="30"/>
             <c:if test="${AMUSE_RIDE_ERROR.speed == 'ERROR'}">
                <br/>
                <span class="error">* please specify device name</span> 
              </c:if>
              </td>
          </tr>
           <tr>
            <th scope="row" style="width:30%;" > NDT Date:</th>
            <td>
             <html:text property="ndtDate" size="10" maxlength="10"/>
              </td>
          </tr>
           <tr>
            <th scope="row" style="width:30%;" > Insurance Date:</th>
            <td>
             <html:text property="inspDate" size="10" maxlength="10"/>
              </td>
          </tr>
           <tr>
            <th scope="row" style="width:30%;" > Expiration Date:</th>
            <td>
             <c:out value="${AMUSE_RIDE_SELECTED.expDateString}" />
              </td>
          </tr>
          <tr>
            <th scope="row" style="width:30%;" class="required"> Manufacture Name:</th>
            <td>
             <html:text property="manName" size="30" maxlength="30"/>
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
      </tbody>
       </table>
      <p>
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelAmuse != null }">
        <html:submit value="save ride info" styleClass="button"/>
      </c:if>
      </p>
    </html:form>
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>