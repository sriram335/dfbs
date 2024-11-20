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
              [ vehicle list ]</a> 
     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ back to provider ]</a> 
     <a   href="/dfbs/ems/provider.do?method=providerList">
              [ back to provider list ]</a> 
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
     <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.VEHICLE.vehicleId}"/>&idType=Vehicle">
            [upload vehicle files]  </a>
      </c:if>
     <a href="/dfbs/ems/main.do?method=authUser">
              [back to main menu]  </a> </br>
  <h2>Add/ Update Vehicle Information</h2>

             

  <div id="leftContentFluid">

 <html:form action="/vehicle" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveVehicle"/> 
      <html:hidden property="vehicleId"/> 
      <html:hidden property="providerId"/> 
      
     
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > vehicle certification number( will be assigned by IDHS):</th>
      <td>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
       <html:text property="certNumber" size="30" maxlength="30"/>
       </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == null   &&  sessionScope.DFBS_SECURITY.groupLevelEms == null}">
        <c:out value="${VEHICLE.certNumber}"/>
        <html:hidden property="certNumber"/> 
       </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > vehicle inspection date( will be assigned by IDHS):</th>
      <td>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }">
         <html:text property="inspectionDate" size="10" maxlength="10"/>
         </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == null   &&  sessionScope.DFBS_SECURITY.groupLevelEms == null }">
       <c:out value="${VEHICLE.inspectionDateString}"/>
       <html:hidden property="inspectionDate"/> 
       </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > vehicle expiration date( will be assigned by IDHS):</th>
      <td>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
       <html:text property="expDate" size="30" maxlength="30"/>
        </c:if>
        <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == null   &&  sessionScope.DFBS_SECURITY.groupLevelEms == null}">
       <c:out value="${VEHICLE.expDateString}"/>
       <html:hidden property="expDate"/> 
       </c:if>
      </td>
    </tr>
    <tr>
    </tr>
    <tr>
      <th scope="row" > vehicle file update date:</th>
      <td>
     <c:out value="${VEHICLE.fileUpdateDate}"/>
      </td>
    </tr>
     
      <th scope="row"  style= "width:50%"   > certificate issue date( will be assigned by IDHS):</th>
      <td>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
       <html:text property="certIssueDate" size="30" maxlength="30"/>
        </c:if>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == null   &&  sessionScope.DFBS_SECURITY.groupLevelEms == null}">
       <c:out value="${VEHICLE.certIssueDateString}"/>
       <html:hidden property="certIssueDate"/> 
       </c:if> 
      </td>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%"   > certificate requested date:</th>
      <td>
       <html:text property="certReqDate" size="10" maxlength="10"/>
      </td>
    </tr>
    </tr>
      <tr>
      <th scope="row"  style= "width:50%" class="required"  > * model year:</th>
      <td>
       <html:text property="modelYear" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > * model make:</th>
      <td>
       <html:text property="modelMake" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* model vin:</th>
      <td>
       <html:text property="modelVin" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* model type:</th>
      <td>
       <html:text property="modelType" size="30" maxlength="30"/>
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
      <th scope="row"  style= "width:50%"  class="required" > * color scheme:</th>
      <td>
       <html:text property="colorScheme" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > correction letter date:</th>
      <td>
       <html:text property="corrLetterDate" size="10" maxlength="10"/>
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
    <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
    <tr>
      <th scope="row"  style= "width:50%"   > temporary year:</th>
      <td>
       <html:text property="tempYear" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > temporary make:</th>
      <td>
       <html:text property="tempMake" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > temporary vin:</th>
      <td>
       <html:text property="tempVin" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > temporary model type:</th>
      <td>
       <html:text property="tempModelType" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > temporary expiration date:</th>
      <td>
       <html:text property="tempExpDate" size="10" maxlength="10"/>
      </td>
    </tr>
    </c:if>
    <tr>
      <th scope="row"  style= "width:50%"   > district:</th>
      <td>
       <html:text property="district" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > conversion by:</th>
      <td>
       <html:text property="conversionBy" size="30" maxlength="30"/>
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
       <html:text property="fuelType" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > previous certification number:</th>
      <td>
       <html:text property="prevCertNumber" size="30" maxlength="30"/>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > vehicle purchase status(New /Old):</th>
      <td>
       <html:select property="vehOldNew" >
          <html:option value="New">New</html:option>
          <html:option value="Old">Old</html:option>
          </html:select>
      </td>
    </tr>
     <tr>
     <tr>
      <th scope="row"  style= "width:50%"   >  New vehicle replaces vehilcle with certification number:</th>
      <td>
       <html:select property="ovNumber" styleId="SELECT_CERT" onchange="setSelectValue('SELECT_CERT','CERTIFICATION_NUMBER')">
          <html:option value="">Please Select</html:option>
          <html:options collection="CURRENT_VEHICLES" property="value" labelProperty="label"/>
        </html:select>
        </td>
    </tr>
   <tr>
<td>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
<p>
   
          <html:submit value="Save changes" styleClass="button" />
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


