<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
 <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tr>
    <th >Designer Details</th>
   </tr>
  
   <tbody class="rowHeader" >
  
   <tr>
    <td>
       Firm Name: <html:text property="designerFirmName" size="50" maxlength="80"/>
        </td>
  </tr>
  
      <tr>
      <td>
         Designer First Name:  <html:text property="designerFirstName" size="30" maxlength="30"/></br>
     esi  Disgner Last Name:   <html:text property="designerLastName" size="30" maxlength="30"/></br>
         address1:            <html:text property="address1" size="30" maxlength="30"/></br>
         address2:            <html:text property="address2" size="30" maxlength="30"/></br>
         city:                <html:text property="city" size="30" maxlength="30"/></br>
         state:
                              <html:select property="state" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
                              <html:option value="">Please Select</html:option>
                              <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
                              </html:select></br>
         zip:                 <html:text property="zip" size="5" maxlength="5"/></br>
        designer indiana number:<html:text property="designerIndianaNumber" size="15" maxlength="15"/></br>
        designer type:<html:text property="designerType" size="30" maxlength="30"/></br>
        phone:<html:text property="telephone" size="10" maxlength="10"/></br>
        email:<html:text property="email" size="50" maxlength="80"/>
        </td>
      </tr> 
</tbody>
</table>
 <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tr>
    <th >System Type</th>
    <th >Lengths of Units produced</th>
     <th >Units contain</th>
   </tr>
  <tr>
  <td>
   <html:select property="systemType"  onchange="setSelectValue('SELECT_CDR_SYSTEM_TYPE','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="CODE_SYSTEM_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${RELEASE_ERROR.systemType == 'ERROR'}">
          <span class="error">* please system type</span> 
        </c:if>
  </td>
     <td>
        <html:text property="lengthsOfUnit" size="30" maxlength="30"/>
        <c:if test="${RELEASE_ERROR.lengthsOfUnit == 'ERROR'}">
          <br/>
          <span class="error">* please lengths of unit</span> 
        </c:if>
      </td>
      <td>
     <c:if test="${RELEASE_SELECTED.unitElectrical == 'Y' || RELEASE_SELECTED.unitElectrical == 'on'}">
      <input type="checkbox" name="<c:out value='unitElectrical' />" class="switch" checked="true" />Electrical</br> 
    </c:if>
     <c:if test="${RELEASE_SELECTED.unitElectrical == null}">
      <input type="checkbox" name="<c:out value='unitElectrical' />" class="switch" />Electrical</br> 
    </c:if>
    <c:if test="${RELEASE_SELECTED.unitFireAlaram == 'Y' ||RELEASE_SELECTED.unitFireAlaram == 'on'}">
      <input type="checkbox" name="<c:out value='unitFireAlaram' />" class="switch" checked="true"/>Fire Alarm</br>
    </c:if>
    <c:if test="${RELEASE_SELECTED.unitFireAlaram == null}">
      <input type="checkbox" name="<c:out value='unitFireAlaram' />" class="switch"/>Fire Alarm</br>
    </c:if>
    <c:if test="${RELEASE_SELECTED.unitHood == 'Y' ||RELEASE_SELECTED.unitHood == 'on'}">
      <input type="checkbox" name="<c:out value='unitHood' />" class="switch" checked="true" />Hood Suppression</br>
     </c:if> 
      <c:if test="${RELEASE_SELECTED.unitHood == null}">
      <input type="checkbox" name="<c:out value='unitHood' />" class="switch"/>Hood Suppression</br>
     </c:if> 
     <c:if test="${RELEASE_SELECTED.unitMechanical == 'Y' ||RELEASE_SELECTED.unitMechanical == 'on'}">
      <input type="checkbox" name="<c:out value='unitMechanical' />" class="switch" checked="true" />Mechanical</br>
      </c:if> 
      <c:if test="${RELEASE_SELECTED.unitMechanical == null}">
      <input type="checkbox" name="<c:out value='unitMechanical' />" class="switch"/>Mechanical</br>
      </c:if>
      <c:if test="${RELEASE_SELECTED.unitPlumbing == 'Y' || RELEASE_SELECTED.unitPlumbing == 'on'}">
      <input type="checkbox" name="<c:out value='unitPlumbing' />" class="switch" checked="true" />Plumbing</br>
      </c:if>
      <c:if test="${RELEASE_SELECTED.unitPlumbing == null}">
      <input type="checkbox" name="<c:out value='unitPlumbing' />" class="switch"/>Plumbing</br>
      </c:if>
      <c:if test="${RELEASE_SELECTED.unitSprinkler == 'Y' || RELEASE_SELECTED.unitSprinkler == 'on'}">
      <input type="checkbox" name="<c:out value='unitSprinkler' />" class="switch" checked="true" />Sprinkler</br>
      </c:if>
      <c:if test="${RELEASE_SELECTED.unitSprinkler == null}">
      <input type="checkbox" name="<c:out value='unitSprinkler' />" class="switch"/>Sprinkler</br>
      </c:if>
      <c:if test="${RELEASE_SELECTED.unitStructural == 'Y' ||RELEASE_SELECTED.unitStructural == 'on'}">
      <input type="checkbox" name="<c:out value='unitStructural' />" class="switch" checked="true"/>Structural</br>
      </c:if>
      <c:if test="${RELEASE_SELECTED.unitStructural == null}">
      <input type="checkbox" name="<c:out value='unitStructural' />" class="switch"/>Structural</br>
      </c:if>
      </td>
      </tr> 
    </tr>
   <tbody class="rowHeader" >
   <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tr>
    <th >CDR Type</th>
    <th >Building Type</th>
    <th >Filing Status</th>
    <th >completed Width</th>
   </tr>
   <tr>
      <td>
        <html:select property="releaseType"  onchange="setSelectValue('SELECT_SYSTEM','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_SYSTEM_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${RELEASE_ERROR.systemType == 'ERROR'}">
          <br/>
          <span class="error">* please specify cdr Type</span> 
        </c:if>
      </td>
      <td>
        <html:select property="buildType"  onchange="setSelectValue('SELECT_OCCUPANCY','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_OCCUPANCY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${RELEASE_ERROR.buildType == 'ERROR'}">
          <br/>
          <span class="error">* please specify building type</span> 
        </c:if>
      </td>
       <td>
        <html:select property="filingStatus"  onchange="setSelectValue('SELECT_FILING','DESCRIPTION')">
          <html:option value="">-----</html:option> 
          <html:options collection="DFBS_FILING_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       
         <c:if test="${RELEASE_ERROR.filingStatus == 'ERROR'}">
          <br/>
          <span class="error">* please specify filing status</span> 
        </c:if>
      </td>
       <td>
        <html:text property="completedWidth" size="10" maxlength="10"/>
         <c:if test="${RELEASE_ERROR.completedWidth == 'ERROR'}">
          <br/>
          <span class="error">* please specify completed width</span> 
        </c:if>
      </td>
    </tr>
   
 </tbody>
</table>  
(LATE = this plan is already in production assembly line)
 <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tr>
    
   </tr>
    <tr>
      <th scope="row"  >number of additional plans($$ extra fee need to be paid):</th>
      <td>
        <html:text property="addPlans" size="2" maxlength="2"/>Enter numbers only</br>
        (Note: 1 plan per filing are included in the  filing fee, mention only how many plans above 1)</br>
      </td>
    </tr>
    <tr>
      <th scope="row"  >number of additional modules($$ extra fee need to be paid):</th>
      <td>
        <html:text property="addStructures" size="2" maxlength="2"/>Enter numbers only</br>
        (Note: 2 modules per filing are included in the filing fee, mention only how many modules above 2)
        </td>
    </tr>
  </table> 
   <tbody class="rowHeader" >
   <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tr>
    <th >Number of Stories</th>
    <th >Number of units in Structure</th>
    <th >previous release number</th>
    </tr>
    <tr>
       <td>
        <html:text property="numberStories" size="2" maxlength="2"/>Enter numbers only</br>
      </td>
      <td>
        <html:text property="numberInstructure" size="2" maxlength="2"/>Enter numbers only</br>
        </td>
     <td>
        <html:text property="prevReleaseNumber" size="30" maxlength="30"/>
        </td>
     </tr>
      </tbody>
</table>  
 <tbody class="rowHeader" >
   <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tr>
    <th >plan building structure type and occupancy:</th>
    <th >number public:</th>
    <th >number private:</th>
    </tr>
     <tr>
      <td>
        <html:text property="occupancy" size="30" maxlength="30"/>
        </td>
         <td>
        <html:text property="numberPublic" size="3" maxlength="3"/>Enter numbers only
        </td>
        <td>
        <html:text property="numberPrivate" size="3" maxlength="3"/>Enter numbers only
        </td>
    </tr>
      </tbody>
</table>  
     <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
    <tr>
      <th scope="row"   >structure use(max 2000 characters):</th>
      <td>
      <html:textarea property="structureUse" />
        </td>
      </tr>
      <tr>
      <th scope="row"   >comments(max 2000 characters):</th>
      <td>
      <html:textarea property="comments" />
        </td>
      </tr> 
   
      </tr> 
     
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null }"> 
    <tr>
      <th scope="row"   >system release number:</th>
      <td>
      <html:text property="releaseNumber" size="20" maxlength="20"/>
      </td>
      </tr> 
     <tr>
      <th scope="row"   >addendum release number:</th>
      <td>
      <html:text property="addReleaseNumber" size="20" maxlength="20"/>
      </td>
      </tr>    
       <tr>
      <th scope="row"   >addendum sequence:</th>
      <td>
      <html:text property="addSequence" size="3" maxlength="3"/>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >release scope:</th>
      <td>
      <html:textarea property="releaseScope" />
      </td>
      </tr> 
       <tr>
      <th scope="row"   >construction code:</th>
      <td>
      <html:text property="constructionCode" size="30" maxlength="30"/>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >occupancy code:</th>
      <td>
      <html:text property="occCode" size="30" maxlength="30"/>
      </td>
      </tr> 
       <tr>
      <th scope="row"   > code reference:</th>
      <td>
      <html:text property="codeReference" size="30" maxlength="30"/>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >release date(mm/dd/yyyy):</th>
      <td>
      <html:text property="releaseDate" size="10" maxlength="10"/>
      </td>
      </tr> 
      <tr>
      <th scope="row"   class="required"  >*please specify reviewer:</th>
      <td>
        <html:select property="reviewerId"  onchange="setSelectValue('SELECT_REVIEWER','INSPECTOR_ID')">
          <html:option value="">-----</html:option>
          <html:options collection="CODE_REVIEWER_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       
      </td>
     
    </tr>
     </c:if>
     <tr>
      <th scope="row"     >facilty used for manufacture1:</th>
      <td>
        <html:select property="facilityId1"  onchange="setSelectValue('SELECT_FACILITY_ID','FACILITY_ID')">
          <html:option value= "0">-----</html:option>
          <html:options collection="CODE_FACILITIES" property="value" labelProperty="label"/>
        </html:select>
       
      </td>
     
    </tr>
     <tr>
      <th scope="row"     >facilty used for manufacture2:</th>
      <td>
        <html:select property="facilityId2"  onchange="setSelectValue('SELECT_FACILITY_ID','FACILITY_ID')">
          <html:option value= "0">-----</html:option>
          <html:options collection="CODE_FACILITIES" property="value" labelProperty="label"/>
        </html:select>
       
      </td>
     
    </tr>
     <tr>
      <th scope="row"     >facilty used for manufacture3:</th>
      <td>
        <html:select property="facilityId3"  onchange="setSelectValue('SELECT_FACILITY_ID','FACILITY_ID')">
          <html:option value= "0">-----</html:option>
          <html:options collection="CODE_FACILITIES" property="value" labelProperty="label"/>
        </html:select>
       
      </td>
     
    </tr>
     <tr>
      <th scope="row"     >facilty used for manufacture4:</th>
      <td>
        <html:select property="facilityId4"  onchange="setSelectValue('SELECT_FACILITY_ID','FACILITY_ID')">
          <html:option value= "0">-----</html:option>
          <html:options collection="CODE_FACILITIES" property="value" labelProperty="label"/>
        </html:select>
       
      </td>
     
    </tr>
   
 </tbody>
</table>  
<%--
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th style="width:75%;">Facility</th>
    <th>Release manufactured at these facilites</th>
  </tr>
  <tbody>
    <c:forEach var="mapItem" items="${sessionScope.MANUFACTURER.facilityMap}" varStatus="i">
              <c:set scope="request" var="facility" value="${mapItem.value}"/>
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:bold;font-size:medium;">
     
          <c:out value="${facility.facilityName}"/>&nbsp; <br/> </span>
           <c:out value="${facility.facilityAddress1}"/>
          <br/>
          <c:out value="${facility.facilityCity}"/>,
          <c:out value="${facility.facilityState}"/>
          <c:out value="${facility.facilityZip}"/>&nbsp;
          </td>
          <td>
          <br/>
          <input type="checkbox" name="<c:out value='${facility.facilityId}' />" class="switch"/>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table> --%>
