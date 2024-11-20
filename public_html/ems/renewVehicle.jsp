<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Contact Details"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<a   href="/dfbs/ems/vehicle.do?method=vehicleList&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ back to vehicle list ]</a> 
     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ back to provider ]</a> 
     <a   href="/dfbs/ems/provider.do?method=providerList">
              [ back to provider list ]</a> 
     
     <a href="/dfbs/ems/main.do?method=authUser">
              [back to main menu]  </a> </br>
  <h2>Apply for Renewal of Vehicle( Make sure you update location,mileage,plate information) </h2>

             

  <div id="leftContentFluid">

 <html:form action="/vehicle" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveRenewVehicle"/> 
      <html:hidden property="vehicleId"/> 
      <html:hidden property="providerId"/> 
      <html:hidden property="tempYear"/> 
      <html:hidden property="tempMake"/> 
      <html:hidden property="tempVin"/> 
      <html:hidden property="tempExpDate"/> 
      <html:hidden property="tempModelType"/> 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > vehicle certification number:</th>
      <td>
         <c:out value="${VEHICLE.certNumber}"/>
        <html:hidden property="certNumber"/> 
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > vehicle inspection date:</th>
      <td>
        <c:out value="${VEHICLE.inspectionDateString}"/>
       <html:hidden property="inspectionDate"/> 
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > vehicle expiration date( will be assigned by IDHS):</th>
      <td>
       <c:out value="${VEHICLE.expDateString}"/>
       <html:hidden property="expDate"/> 
      </td>
    </tr>
    <tr>
    </tr>
    <tr>
      <th scope="row" > vehicle file update date:</th>
      <td>
     <c:out value="${VEHICLE.fileUpdateDateString}"/>
     <html:hidden property="fileUpdateDate"/>
      </td>
    </tr>
     
      <th scope="row"  style= "width:50%"   > certificate issue date( will be assigned by IDHS):</th>
      <td>
       <c:out value="${VEHICLE.certIssueDateString}"/>
       <html:hidden property="certIssueDate"/> 
       </td>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%"   > certificate requested date:</th>
      <td>
      <c:out value="${VEHICLE.certReqDateString}"/>
      <html:hidden property="certReqDate"/>
       </td>
    </tr>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%"   >  model year:</th>
      <td>
      <c:out value="${VEHICLE.modelYear}"/>
      <html:hidden property="modelYear"/>
     </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >  model make:</th>
      <td>
       <c:out value="${VEHICLE.modelMake}"/>
       <html:hidden property="modelMake"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > model vin:</th>
      <td>
      <c:out value="${VEHICLE.modelVin}"/>
      <html:hidden property="modelVin"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  > model type:</th>
      <td>
      <c:out value="${VEHICLE.modelType}"/>
      <html:hidden property="modelType"/>
      </td>
    </tr>
    <tr>
     <tr>
      <th scope="row"  style= "width:50%"   >  vehicle type:</th>
      <td>
      <html:select property="vehicleType" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_VEHICLE','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="VEHICLE_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >  color scheme:</th>
      <td>
       <c:out value="${VEHICLE.colorScheme}"/>
       <html:hidden property="colorScheme"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > correction letter date:</th>
      <td>
      <c:out value="${VEHICLE.corrLetterDateString}"/>
      <html:hidden property="corrLetterDate"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required" > * mileage:</th>
      <td>
       <html:text property="mileage" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required" > * plate:</th>
      <td>
       <html:text property="plate" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > comments:</th>
      <td>
       <html:text property="comments" size="30" maxlength="30"/>
      </td>
    </tr>
    
     <tr>
      <th scope="row"  style= "width:50%"   > district:</th>
      <td>
       <c:out value="${VEHICLE.district}"/>
       <html:hidden property="district"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > conversion by:</th>
      <td>
      <c:out value="${VEHICLE.conversionBy}"/>
      <html:hidden property="conversionBy"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required" > * vehicle location:</th>
      <td>
       <html:text property="vehicleLocation" size="100" maxlength="100"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > vehicle fuel Type(Diesel / Gasoline /):</th>
      <td>
       <c:out value="${VEHICLE.fuelType}"/>
       <html:hidden property="fuelType"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > previous certification number:</th>
      <td>
      <c:out value="${VEHICLE.prevCertNumber}"/>
      <html:hidden property="prevCertNumber"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > vehicle purchase status(New /Old):</th>
      <td>
      <c:out value="${VEHICLE.vehOldNew}"/>
      <html:hidden property="vehOldNew"/>
      </td>
    </tr>
   <tr>
    <th scope="row"  style= "width:50%"   ></th>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Renew Vehicle" styleClass="button" />
 </p>
</c:if>
 </td>
 </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>



