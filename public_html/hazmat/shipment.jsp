<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - HAZMAT DIVISION"/>
<c:set var="title" scope="request" value="Shipment Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add / Edit Shipment Information</h2> 
<a   href="/dfbs/hazmat/hazmat.do?method=backToHazmatApp">
              [Cancel]</a>
  <div class="styledBox">
 <html:form action="/shipment" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveShipment"/> 
      <html:hidden property="shipmentId"/> 
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * Low / High level waste:</th>
      <td>
       <html:select property="shipRadLevel" styleId="SELECT_RAD_LEVEL" onchange="setSelectValue('SELECT_RAD_LEVEL','LEVEL')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SHIPMENT_RAD_LEVEL_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipRadLevel == 'ERROR'}">
          <br/>
          <span class="error">* please specify radiation level</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * shipment by:</th>
      <td>
       <html:select property="shipmentType" styleId="SELECT_SHIPMENT" onchange="setSelectValue('SELECT_SHIPMENT','TYPE')">
         <html:option value="">Please Select</html:option>
          <html:options collection="SHIPMENT_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipmentType == 'ERROR'}">
          <br/>
          <span class="error">* please specify shipment type</span> 
        </c:if> 
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * total number of casks on the train:</th>
      <td>
       <html:text property="numberOfCasks" size="2" maxlength="2"/>(applicable to Rail Shipments only) Enter numbers only
       <c:if test="${HAZMAT_SHIPMENT_ERROR.numberOfCasks == -1}">
          <br/>
          <span class="error">* please enter number of casks</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * shipment origin:</th>
      <td>
       <html:text property="shipOrigin" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipOrigin == 'ERROR'}">
          <br/>
          <span class="error">* please enter shipment origin</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * shipment destination:</th>
      <td>
       <html:text property="shipDestination" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipDestination == 'ERROR'}">
          <br/>
          <span class="error">* please enter shipment destination</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * shipment date:</th>
      <td>
       <html:text property="shipDate" size="10" maxlength="10"/>mm/dd/yyyy
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipDate == 'ERROR'}">
          <br/>
          <span class="error">* please enter shipment date</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * amount shipped:</th>
      <td>
       <html:text property="shipAmount" size="10" maxlength="10"/>Enter numbers only
       <html:select property="shipWeightType" >
          <html:option value="Lbs">Lbs</html:option>
          <html:option value="Tons">Tons</html:option>
        </html:select>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipAmount == 'ERROR'}">
          <br/>
          <span class="error">* please enter amount shipped</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * shipment material type:</th>
      <td>
       <html:text property="shipMaterialType" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipMaterialType == 'ERROR'}">
          <br/>
          <span class="error">* please enter material type</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * shipment isotope:</th>
      <td>
       <html:text property="shipIsotope" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipIsotope == 'ERROR'}">
          <br/>
          <span class="error">* please enter isotope</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required"    > * shipment activity by isotope:</th>
      <td>
       <html:text property="shipActIsotope" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipActIsotope == 'ERROR'}">
          <br/>
          <span class="error">* please enter activity by isotope</span> 
        </c:if>
       </td>
    </tr>
   
     <tr>
      <th scope="row"  class="required"    > * Has a CVSA level VI inspection been scheduled:</th>
      <td>
        <html:select property="shipVicvsa" styleId="SELECT_YN" onchange="setSelectValue('SELECT_YN','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SHIPMENT_CVSA_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       if "Yes" state which state:
      <html:select property="shipVicvsaState" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="SHIPMENT_CVSA_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipVicvsa == 'ERROR'}">
          <br/>
          <span class="error">* please enter CVSA inspection planned</span> 
        </c:if>
      
       </td>
    </tr>
     <tr>
      <th scope="row"   class="required"   > * shipment route:</th>
      <td>
       <html:text property="shipRoute" size="50" maxlength="50"/>
       <c:if test="${HAZMAT_SHIPMENT_ERROR.shipRoute == 'ERROR'}">
          <br/>
          <span class="error">* please enter shipment route</span> 
         </c:if> 
       </td>
    </tr>
     <tr>
      <th scope="row"     > comments:</th>
      <td>
       <html:text property="shipComments" size="100" maxlength="100"/>
       </td>
    </tr>
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save Shipment" styleClass="button" />
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>