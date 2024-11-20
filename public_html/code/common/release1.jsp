<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
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
      <th scope="row"  style= "width:50%" class="required"  >*please specify cdr type:</th>
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
     
    </tr>
  <tr>
      <th scope="row" class="required">*please specify building type:</th>
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
    </tr>
    <tr>
    <tr>
      <th scope="row" class="required">*please specify system classification:</th>
      <td>
        <html:select property="systemType"  onchange="setSelectValue('SELECT_CDR_SYSTEM_TYPE','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <html:options collection="CODE_SYSTEM_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${RELEASE_ERROR.systemType == 'ERROR'}">
          <br/>
          <span class="error">* please system type</span> 
        </c:if>
      </td>
    </tr>
    
      <th scope="row" class="required">*filing status:</th> 
      <td>
        <html:select property="filingStatus"  onchange="setSelectValue('SELECT_FILING','DESCRIPTION')">
          <html:option value="">-----</html:option> 
          <html:options collection="DFBS_FILING_OPTIONS" property="value" labelProperty="label"/>
        </html:select> 
        (LATE = this plan is already in production assembly line)
        <c:if test="${RELEASE_ERROR.filingStatus == 'ERROR'}">
          <br/>
          <span class="error">* please specify filing status</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*lengths of unit:</th>
      <td>
        <html:text property="lengthsOfUnit" size="30" maxlength="30"/>
        <c:if test="${RELEASE_ERROR.lengthsOfUnit == 'ERROR'}">
          <br/>
          <span class="error">* please lengths of unit</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  class="required" >*completed width:</th>
      <td>
        <html:text property="completedWidth" size="30" maxlength="30"/>
         <c:if test="${RELEASE_ERROR.completedWidth == 'ERROR'}">
          <br/>
          <span class="error">* please completed width</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"  >number of additional plans($$ extra fee need to be paid):</th>
      <td>
        <html:text property="addPlans" size="5" maxlength="30"/></br>
        (Note: 1 plan per filing are included in the  filing fee, mention only how many plans above 1)</br>
      </td>
    </tr>
    <tr>
      <th scope="row"  >number of additional modules($$ extra fee need to be paid):</th>
      <td>
        <html:text property="addStructures" size="5" maxlength="5"/></br>
        (Note: 2 modules per filing are included in the filing fee, mention only how many modules above 2)
        </td>
    </tr> 
    <tr>
      <th scope="row"  >number of stories:</th>
      <td>
        <html:text property="numberStories" size="30" maxlength="30"/></br>
      </td>
    </tr>
    <tr>
      <th scope="row"  >number of units in structure:</th>
      <td>
        <html:text property="numberInstructure" size="30" maxlength="30"/></br>
        </td>
    </tr> 
    
    <tr>
      <th scope="row"   >previous release number:</th>
      <td>
        <html:text property="prevReleaseNumber" size="30" maxlength="30"/>
        </td>
    </tr>
     <tr>
      <th scope="row"   >plan building structure type and occupancy:</th>
      <td>
        <html:text property="occupancy" size="30" maxlength="30"/>
        </td>
    </tr>
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
    <tr>
      <th scope="row"   >number public:</th>
      <td>
        <html:text property="numberPublic" size="10" maxlength="10"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >number private:</th>
      <td>
        <html:text property="numberPrivate" size="10" maxlength="10"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >designer firm name:</th>
      <td>
        <html:text property="designerFirmName" size="80" maxlength="80"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >designer first name:</th>
      <td>
        <html:text property="designerFirstName" size="30" maxlength="30"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >designer last name:</th>
      <td>
        <html:text property="designerLastName" size="30" maxlength="30"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >address1:</th>
      <td>
        <html:text property="address1" size="30" maxlength="30"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >address2:</th>
      <td>
        <html:text property="address2" size="30" maxlength="30"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >city:</th>
      <td>
        <html:text property="city" size="30" maxlength="30"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"     >   state:</th>
      <td>
          <html:select property="state" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INITIAL')">
          <html:option value="">Please Select</html:option>
          <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       </td>
    </tr>
     
       <tr>
      <th scope="row"   >zip:</th>
      <td>
        <html:text property="zip" size="5" maxlength="5"/>
        </td>
      </tr> 
       <tr>
      <th scope="row"   >designer indiana number:</th>
      <td>
        <html:text property="designerIndianaNumber" size="15" maxlength="15"/>
        </td>
      </tr> 
       <tr>
      <th scope="row"   >designer type:</th>
      <td>
        <html:text property="designerType" size="30" maxlength="30"/>
        </td>
      </tr> 
      </tr> 
       <tr>
      <th scope="row"   >phone:</th>
      <td>
        <html:text property="telephone" size="10" maxlength="10"/>
        </td>
      </tr> 
       <tr>
      <th scope="row"   >email:</th>
      <td>
        <html:text property="email" size="80" maxlength="80"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >Release scope electrical:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitElectrical' />" class="switch"/>
      </td>
      </tr> 
       <tr>
       <th scope="row"   >Release scope fire alaram:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitFireAlaram' />" class="switch"/>
      </td>
      </tr> 
       <tr>
      <th scope="row"   >Release scope hood:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitHood' />" class="switch"/>
      </td>
      </tr>
      <tr>
      <th scope="row"   >Release scope mechanical:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitMechanical' />" class="switch"/>
      </td>
      </tr>
      <tr>
      <th scope="row"   >Release scope plumbing:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitPlumbing' />" class="switch"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >Release scope sprinkler:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitSprinkler' />" class="switch"/>
        </td>
      </tr> 
      <tr>
      <th scope="row"   >Release scope structural:</th>
      <td>
      <input type="checkbox" name="<c:out value='unitStructural' />" class="switch"/>
      </td>
      </tr> 
 
 </tbody>
 
</table>
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
</table>