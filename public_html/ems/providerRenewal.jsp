<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Renew Provider"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<c:if test="${sessionScope.RENEW_PROVIDER  != null }"> 
  <jsp:include page="/ems/renewalProviderStatus.jsp"/>
  </c:if>  
</div>
<div id="mainBox">
  <h2>Add/ Update Provider Renewal Information</h2>
 
     <a   href="/dfbs/ems/provider.do?method=providerDetail&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ back to provider ]</a> 
                  

  <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
  
  <div id="leftContentFluid">

 <html:form action="/providerRenewal" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveProvRenewal"/> 
      <html:hidden property="renewalId"/>
      <html:hidden property="eopProviderId"/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
   </tr>
    <tr>
     <th  style="color:green"><b><u>List FCC Licence Dates</b></u></th>
     </tr>
     <tr>
      <th scope="row"  style= "width:50%"   >tactical expiration date:</th>
      <td>
       <html:text property="tactExpDate" size="10" maxlength="10"/>(mm/dd/yyyy)
     </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   >IHERN expiration date:</th>
      <td>
       <html:text property="ihernExpDate" size="10" maxlength="10"/>(mm/dd/yyyy)
     </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   >UHE expiration date:</th>
      <td>
       <html:text property="uhfExpDate" size="10" maxlength="10"/>(mm/dd/yyyy)
     </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   >other expiration date:</th>
      <td>
       <html:text property="otherExpDate" size="10" maxlength="10"/>(mm/dd/yyyy)
     </td>
    </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   >other details:</th>
      <td>
       <html:text property="otherDetails" size="30" maxlength="30"/>
     </td>
    </tr> 
    <tr>
     <th  style="color:green"><b><u>UHF method</b></u></th>
     </tr>
    <tr>
      <th scope="row"   >Cellular:</th>
      <td>
       <c:if test="${RENEWAL.uhfCellular == null ||RENEWAL.uhfCellular == 'off'}">
      <input type="checkbox" name="<c:out value='uhfCellular' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.uhfCellular == 'on'}"> 
         <input type="checkbox" name="<c:out value='uhfCellular' />"  class="switch" checked />
       </c:if>
      
      </td>
      </tr> 
      <tr>
      <th scope="row"   >400 MHz:</th>
      <td>
       <c:if test="${RENEWAL.uhf400 == null ||RENEWAL.uhf400 == 'off'}">
      <input type="checkbox" name="<c:out value='uhf400' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.uhf400 == 'on'}"> 
         <input type="checkbox" name="<c:out value='uhf400' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
      <tr>
      <th scope="row"   >800MHz:</th>
      <td>
       <c:if test="${RENEWAL.uhf800 == null ||RENEWAL.uhf800 == 'off'}">
      <input type="checkbox" name="<c:out value='uhf800' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.uhf800 == 'on'}"> 
         <input type="checkbox" name="<c:out value='uhf800' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   >other details:</th>
      <td>
      <html:text property="uhfOther" size="30" maxlength="30"/>
      </td>
      </tr> 
     <tr>
     <th  style="color:red">If operating on frequencies licenced by other organizations, attach / upload letters of authorizations from the licensed organization </th>
     </tr>
    <tr>
    <tr>
     <th  style="color:green"><b><u>Operational Information</b></u></th>
     </tr>
     <tr>
      <th scope="row"  style= "width:50%"   >Does Oraganization provide 24/7 ems service:</th>
      <td>
       <html:select property="ems247" styleId="SELECT_ABBREVIATION" onchange="setSelectValue('SELECT_ABBREVIATION','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="RENEWAL_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > 24/7 ems service details:</th>
      <td>
      <html:textarea property="ems247Details" />
      </td>
      </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   > explain organization staffing pattern details:</th>
      <td>
      <html:textarea property="orgStaffing" />
      </td>
      </tr> 
    
     <tr>
      <th scope="row"  style= "width:50%"   >Has service area changed since last application:</th>
      <td>
       <html:select property="serviceAreaChange" styleId="SELECT_EMS247" onchange="setSelectValue('SELECT_EMS247','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="RENEWAL_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   >Location whare the recorcords are kept:</th>
      <td>
      <html:text property="recordLocation" size="30" maxlength="30"/>
      </td>
      </tr> 
     <tr>
      <th scope="row"  style= "width:50%"   > List any waivers granted to the provider by EMS commission:</th>
      <td>
      <html:textarea property="waiverList" />
      </td>
      </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   >Does your organization file EMS Data Registry Report on a monthly basis?:</th>
      <td>
       <html:select property="emsDataRegistry" styleId="SELECT_EMS247" onchange="setSelectValue('SELECT_EMS247','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="RENEWAL_YN_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > Data Registry Details, software used etc.:</th>
      <td>
      <html:textarea property="softwareUsed" />
      </td>
      </tr> 
       <tr>
     <th  style="color:green"><b><u>Training Information</b></u></th>
     </tr>
       <tr>
     <th  style="color:red">How often are trainig sessions held</th>
     </tr>
       <tr>
      <th scope="row"   >daily:</th>
      <td>
       <c:if test="${RENEWAL.trainingDaily == null ||RENEWAL.trainingDaily == 'off'}">
      <input type="checkbox" name="<c:out value='trainingDaily' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.trainingDaily == 'on'}"> 
         <input type="checkbox" name="<c:out value='trainingDaily' />"  class="switch" checked />
       </c:if>
     </td>
      </tr> 
       <tr>
      <th scope="row"   >monthly:</th>
      <td>
       <c:if test="${RENEWAL.trainingMonthly == null ||RENEWAL.trainingMonthly == 'off'}">
      <input type="checkbox" name="<c:out value='trainingMonthly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.trainingMonthly == 'on'}"> 
         <input type="checkbox" name="<c:out value='trainingMonthly' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >quarterly:</th>
      <td>
       <c:if test="${RENEWAL.trainingQuarterly == null ||RENEWAL.trainingQuarterly == 'off'}">
      <input type="checkbox" name="<c:out value='trainingQuarterly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.trainingQuarterly == 'on'}"> 
         <input type="checkbox" name="<c:out value='trainingQuarterly' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >other:</th>
      <td>
       <c:if test="${RENEWAL.trainingOther == null ||RENEWAL.trainingOther == 'off'}">
      <input type="checkbox" name="<c:out value='trainingOther' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.trainingOther == 'on'}"> 
         <input type="checkbox" name="<c:out value='trainingOther' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"  style= "width:50%"   > training other details:</th>
      <td>
      <html:textarea property="trainingOtherDetails" />
      </td>
      </tr> 
       <tr>
      <th scope="row"  style= "width:50%"   > where are the training sessions held:</th>
      <td>
      <html:textarea property="trainingHeld" />
      </td>
      </tr> 
      <tr>
     <th  style="color:green"><b><u>Audit and Review</b></u></th>
     </tr>
       <tr>
     <th  style="color:red">Who is responsible for conducting audit</th>
     </tr>
       <tr>
      <th scope="row"   >Medical Director:</th>
      <td>
       <c:if test="${RENEWAL.auditMD == null ||RENEWAL.auditMD == 'off'}">
      <input type="checkbox" name="<c:out value='auditMD' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.auditMD == 'on'}"> 
         <input type="checkbox" name="<c:out value='auditMD' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >provider organization committee:</th>
      <td>
       <c:if test="${RENEWAL.auditProvCommittee == null ||RENEWAL.auditProvCommittee == 'off'}">
      <input type="checkbox" name="<c:out value='auditProvCommittee' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.auditProvCommittee == 'on'}"> 
         <input type="checkbox" name="<c:out value='auditProvCommittee' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >Hospital committee</th>
      <td>
       <c:if test="${RENEWAL.auditHospCommittee == null ||RENEWAL.auditHospCommittee == 'off'}">
      <input type="checkbox" name="<c:out value='auditHospCommittee' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.auditHospCommittee == 'on'}"> 
         <input type="checkbox" name="<c:out value='auditHospCommittee' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
      
        <tr>
     <th  style="color:red">how often are audit sessions  conducted</th>
     </tr>
       <tr>
      <th scope="row"   >monthly:</th>
      <td>
      <c:if test="${RENEWAL.auditSessionMonthly == null ||RENEWAL.auditSessionMonthly == 'off'}">
      <input type="checkbox" name="<c:out value='auditSessionMonthly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.auditSessionMonthly == 'on'}"> 
         <input type="checkbox" name="<c:out value='auditSessionMonthly' />"  class="switch" checked />
       </c:if>	
      </td>
      </tr> 
       <tr>
      <th scope="row"   >quarterly:</th>
      <td>
      <c:if test="${RENEWAL.auditSessionQuarterly == null ||RENEWAL.auditSessionQuarterly == 'off'}">
      <input type="checkbox" name="<c:out value='auditSessionQuarterly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.auditSessionQuarterly == 'on'}"> 
         <input type="checkbox" name="<c:out value='auditSessionQuarterly' />"  class="switch" checked />
       </c:if>	
      </td>
      </tr> 
       <tr>
      <th scope="row"   >other</th>
      <td>
      <c:if test="${RENEWAL.auditSessionOther == null ||RENEWAL.auditSessionOther == 'off'}">
      <input type="checkbox" name="<c:out value='auditSessionOther' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.auditSessionOther == 'on'}"> 
         <input type="checkbox" name="<c:out value='auditSessionOther' />"  class="switch" checked />
       </c:if>	
      </td>
      </tr> 
       <tr>
      <th scope="row"  style= "width:50%"   > where are the training sessions held:</th>
      <td>
      <html:textarea property="auditSessionOtherDetails" />
      </td>
      </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   >who is responsible for keeping audit records:</th>
      <td>
       <html:text property="auditRecordsWith" size="80" maxlength="80"/>
     </td>
    </tr> 
       <tr>
     <th  style="color:red">how often are vehicles checked for equipment inventory</th>
     </tr> 
      <tr>
      <th scope="row"   >daily:</th>
      <td>
       <c:if test="${RENEWAL.vehicleCheckDaily == null ||RENEWAL.vehicleCheckDaily == 'off'}">
      <input type="checkbox" name="<c:out value='vehicleCheckDaily' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehicleCheckDaily == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehicleCheckDaily' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >weekly:</th>
      <td>
       <c:if test="${RENEWAL.vehicleCheckWeekly == null ||RENEWAL.vehicleCheckWeekly == 'off'}">
      <input type="checkbox" name="<c:out value='vehicleCheckWeekly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehicleCheckWeekly == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehicleCheckWeekly' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >monthly:</th>
      <td>
       <c:if test="${RENEWAL.vehicleCheckMonthly == null ||RENEWAL.vehicleCheckMonthly == 'off'}">
      <input type="checkbox" name="<c:out value='vehicleCheckMonthly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehicleCheckMonthly == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehicleCheckMonthly' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >other:</th>
      <td>
      <c:if test="${RENEWAL.vehicleCheckOther == null ||RENEWAL.vehicleCheckOther == 'off'}">
      <input type="checkbox" name="<c:out value='vehicleCheckOther' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehicleCheckOther == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehicleCheckOther' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"  style= "width:50%"   > inventory other details:</th>
      <td>
      <html:textarea property="vehicleCheckOtherDetails" />
      </td>
      </tr> 
       <tr>
     <th  style="color:red">describe oraganization's equipment maintenance procedures</th>
     </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   > </th>
      <td>
      <html:textarea property="equipMaintenance" />
      </td>
      </tr> 
      <tr>
     <th  style="color:red"><b>THE following questions do not apply to BLS-Non Transport Organization</b></th>
     </tr> 
      <tr>
     <th  style="color:red">how often are vehicles checked for vehicle integrity</th>
     </tr> 
      <tr>
      <th scope="row"   >daily:</th>
      <td>
       <c:if test="${RENEWAL.vehIntCheckDaily == null ||RENEWAL.vehIntCheckDaily == 'off'}">
      <input type="checkbox" name="<c:out value='vehIntCheckDaily' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehIntCheckDaily == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehIntCheckDaily' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >weekly:</th>
      <td>
      <c:if test="${RENEWAL.vehIntCheckWeekly == null ||RENEWAL.vehIntCheckWeekly == 'off'}">
      <input type="checkbox" name="<c:out value='vehIntCheckWeekly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehIntCheckWeekly == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehIntCheckWeekly' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >monthly:</th>
      <td>
        <c:if test="${RENEWAL.vehIntCheckMonthly == null ||RENEWAL.vehIntCheckMonthly == 'off'}">
      <input type="checkbox" name="<c:out value='vehIntCheckMonthly' />"  class="switch"  />
       </c:if>
        <c:if test="${RENEWAL.vehIntCheckMonthly == 'on'}"> 
         <input type="checkbox" name="<c:out value='vehIntCheckMonthly' />"  class="switch" checked />
       </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >other:</th>
      <td>
         <c:if test="${RENEWAL.vehIntCheckOther == null ||RENEWAL.vehIntCheckOther == 'off'}">
      <input type="checkbox" name="<c:out value='vehIntCheckOther' />"  class="switch"  />
      </c:if>
        <c:if test="${RENEWAL.vehIntCheckOther == 'on'}"> 
      <input type="checkbox" name="<c:out value='vehIntCheckOther' />"  class="switch" checked />
      </c:if>
      </td>
      </tr> 
       <tr>
      <th scope="row"  style= "width:50%"   > inventory other details:</th>
      <td>
      <html:textarea property="vehIntCheckOtherDetails" />
      </td>
      </tr> 
       <tr>
     <th  style="color:red">describe oraganization's vehicle maintenance procedures</th>
     </tr> 
      <tr>
      <th scope="row"  style= "width:50%"   > </th>
      <td>
      <html:textarea property="vehicleMaintenance" />
      </td>
      </tr> 
      
   
  
   <tr>
    <td>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
    <p>
       
              <html:submit value="Save Renewal" styleClass="button" />
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

