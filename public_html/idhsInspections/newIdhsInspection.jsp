<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>


<hs:control/>
<c:set var="module" scope="request" value="Idhs Inspections" />
<c:set var="title" scope="request" value="Add new Inspection" />

<html:xhtml/>

  
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="leftContentFluid">
<c:forEach var="facility" items="${CODE_MAN_FACILITIES.list}" varStatus="i">
     Name: <c:out value="${alert}"/>&nbsp; <br/> 
     Address: <c:out value="${alert}"/>&nbsp; <br/>
      <c:out value="${alert}"/>&nbsp;  <c:out value="${alert}"/>&nbsp; <c:out value="${alert}"/>&nbsp; <br/>
</c:forEach>
</div>

<h2>Add New Inspection</h2> 
<c:if test="${SEARCH_INACTIVE == 'Y'}">   
<span class="error">You are entering a inspection for inactive facility, activate it after inspection is entred.  </span>
</c:if>
<div id="leftContentFluid">
<a   href="/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId=<c:out value="${INSPECTION_SEARCH_ID}"/>">
              [back to inspection list]</a></br>
<b><u>Inspection Alerts: </u></b><p class="error">
 <c:forEach var="alert" items="${ALERT_LIST.list}" varStatus="i">
      <c:out value="${alert}"/>&nbsp; <br/> 
    </c:forEach></p>
  <div class="styledBox">
 <html:form action="/idhsInspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInsertIdhsInspection"/> 
      <html:hidden property="inspId"/> 
      <html:hidden property="recCreatedDate"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
     <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   <tr>
      <th style="font-size:medium;width : 50%" scope="row" > </th>
      <td >
     <b>Create New </b> <input type="checkbox" name="createNewAE"  class="switch"  value="Y"/> AE Number
       <input type="checkbox" name="createNewBU"  class="switch"  value="Y"/> Business(BU) Number
       <input type="checkbox" name="createNewJP"  class="switch"  value="Y"/> Jail(JP) Number
       <input type="checkbox" name="createNewHM"  class="switch"  value="Y"/>Hotel/Motel(HM) Number
      </td>
    </tr>
  <%--  <tr>
      <th > </th>
      <td style="color:red">
     <b>Check here if it is a Super Bowl 2012 related inspection ,Check here!</b> 
      <input type="checkbox" name="inspOtherActivity"  class="switch"  value="Y"/> 
      </td>
    </tr> --%>
    <tr>
          <th scope="row"    > * facility ID:</th>
          <td>
          <html:text property="inspFacId" size="30" maxlength="30"/> 
            </td>
           </tr>
     <tr>
          <th scope="row"     > * facility name:</th>
          <td>
          <html:text property="facilityName" size="30" maxlength="80"/>
            </td>
           </tr>
      <tr>
          <th scope="row"    > * facility street number:</th>
          <td>
          <html:text property="facilitySteetNumber" size="6" maxlength="6"/>
            </td>
           </tr>
    <tr>
          <th scope="row"    > * facility street prefix:</th>
          <td>
           <html:select property="facilityStreetPrefix" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_ST_PREFIX_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
    <tr>
          <th scope="row"    > * facility street name:</th>
          <td>
          <html:text property="facilityStreetName" size="30" maxlength="30"/>
            </td>
           </tr>
     <tr>
          <th scope="row"    > * facility street suffix:</th>
          <td>
          <html:select property="facilityStreetSuffix" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_ST_SUFFIX_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    >  facility address2:</th>
          <td>
          <html:text property="facilityAddress2" size="15" maxlength="15"/>
            </td>
           </tr>
    <tr>
          <th scope="row"    > * facility city:</th>
          <td>
          <html:text property="facilityCity" size="30" maxlength="30"/>
            </td>
           </tr>
    <tr>
          <th scope="row"    > * facility state:</th>
          <td>
           <html:text property="facilityState" size="2" maxlength="2"/>
            </td>
           </tr>
      <tr>
          <th scope="row"    > * facility zip:</th>
          <td>
          <html:text property="facilityZip" size="5" maxlength="5"/>
            </td>
           </tr>
    <tr>
          <th scope="row"  class="required"  > * facility county:</th>
          <td>
           <html:select property="facilityCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_COUNTY_OPTIONS2" property="value" labelProperty="label"/>
        </html:select>
         <c:if test="${INSPECTION_ERROR.facilityCounty == 'ERROR'}">
              <br/><span class="error">* please inspection date  </span> 
            </c:if> 
            </td>
           </tr>
   
    <tr>
          <th scope="row"    > * facility contact:</th>
          <td>
          <html:text property="facilityContact" size="30" maxlength="80"/>
            </td>
           </tr>
    <tr>
          <th scope="row"    > * facility telephone:</th>
          <td>
          <html:text property="facilityPhone" size="10" maxlength="10"/>
            </td>
           </tr>
    <tr>
          <th scope="row"     > * facility Email:</th>
          <td>
          <html:text property="facilityEmail" size="30" maxlength="100"/>
            </td>
           </tr>
    <tr>
          <th scope="row"    >  Foundation/Footer status:</th>
          <td>
           <html:select property="inspConstFoundation" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
    <tr>
          <th scope="row"    > Concrete Slab status:</th>
          <td>
          <html:select property="inspConstSlab" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
      <tr>
          <th scope="row"    > Framing Status:</th>
          <td>
          <html:select property="inspConstFraming" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > Plumbing status:</th>
          <td>
          <html:select property="inspConstPlumbing" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
      <tr>
          <th scope="row"    > Mechanical status:</th>
          <td>
          <html:select property="inspConstMech" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > Electrical status:</th>
          <td>
          <html:select property="inspConstElec" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
      <tr>
          <th scope="row"    > Energy status:</th>
          <td>
          <html:select property="inspConstEnergy" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > Sprinkler status:</th>
          <td>
          <html:select property="inspConstSprinkler" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > alarm status:</th>
          <td>
          <html:select property="inspAlarm" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    >sensitivity current :</th>
          <td>
          <html:select property="inspSensitivityCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
      <tr>
          <th scope="row"    > Interior status:</th>
          <td>
          <html:select property="inspConstInterior" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > Pool status:</th>
          <td>
          <html:select property="inspConstPool" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > Egress status:</th>
          <td>
          <html:select property="inspConstEgress" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > suppression status:</th>
          <td>
          <html:select property="inspConstSuppression" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
       <tr>
          <th scope="row"    > temporary indoor stage:</th>
          <td>
          <html:select property="inspSprinklerCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
     </tr>
   
           
     <tr>
          <th scope="row"    > temporary outdoor stage:</th>
          <td>
          <html:select property="inspAlarmCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    >outdoor stage equipment :</th>
          <td>
          <html:select property="inspHoodCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
    <tr>
          <th scope="row"    >canopy:</th>
          <td>
          <html:select property="inspFireDrillCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
           
     <tr>
          <th scope="row"    > tent :</th>
          <td>
          <html:select property="inspFireEvacCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
       <tr>
          <th scope="row"    > ust installation:</th>
          <td>
          <html:select property="inspExtLightCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
           
         <tr>
          <th scope="row"    > ust removal:</th>
          <td>
          <html:select property="inspEmpTrainCurrent" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
<%--
    <tr>
          <th scope="row"    > fire extinguishers status:</th>
          <td>
          <html:select property="inspConstFireExt" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr> --%>
        
     <tr>
          <th scope="row"    >  project Type(select All that apply):</th>
          <td>
          New:<input type="checkbox" name="inspProjNew"  class="switch"  value="Y"/>
          Addition:<input type="checkbox" name="inspProjAddition"  class="switch"  value="Y"/>
          Remodel:<input type="checkbox" name="inspProjRemodel"  class="switch"  value="Y"/>
          Occ.Change:<input type="checkbox" name="inspProjOccChange"  class="switch"  value="Y"/>
          Existing:<input type="checkbox" name="inspProjExisting"  class="switch"  value="Y"/>
            </td>
           </tr>
           
    <tr>
          <th scope="row"    > * inspection Category:</th>
          <td>
          <html:select property="inspMaintType" styleId="SELECT_INSP_EXCEL" onchange="setSelectValue('SELECT_INSP_EXCEL','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_MAINT_INSP_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
 <%--    <tr>
          <th scope="row"    > inspection activity type:</th>
          <td>
           <html:select property="inspOtherActivity" styleId="SELECT_INSP_ACT" onchange="setSelectValue('SELECT_INSP_ACT','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_INSP_ACTIVITY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr> --%>
     <tr>
          <th scope="row"  class="required"  > * inspection  type:</th>
          <td>
          <html:select property="inspType" styleId="INSPECTION_SWITCH" onchange="setSelectValue('SELECT_INITIAL','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_INSP_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         <c:if test="${INSPECTION_ERROR.inspType == 'ERROR'}">
              <br/><span class="error">* please inspection date  </span> 
            </c:if>  
            </td>
           </tr>
   <%--  <tr>
          <th scope="row"    > sprinkler status:</th>
          <td>
           <html:select property="inspSprinkler" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>  below changed on 01/02/2013 as per Randy--%>
           
    
     <%--      
    <tr>
          <th scope="row"    > backflow preventor status:</th>
          <td>
          <html:select property="inspConstBackFlow" styleId="SELECT_INSP" onchange="setSelectValue('SELECT_INSP','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_RG_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
             </td>
           </tr> --%>
           
    
   
           
    
     
    <tr>
          <th scope="row"   class="required" > * inspection status:</th>
          <td>
           <html:select property="inspViolationStatus" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_INSP','INSP_STATUS')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_INSP_STATUS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
         <c:if test="${INSPECTION_ERROR.inspViolationStatus == 'ERROR'}">
              <br/><span class="error">* please inspection status  </span> 
            </c:if>  
            </td>
           </tr>
     <tr>
          <th scope="row"  class="required"    > * inspection Date:</th>
    <td>
          <html:text id="inspDate" property="inspDate" size="10" maxlength="10"/>(mm/dd/yyyy) <a href="javascript:NewCal('inspDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
           <c:if test="${INSPECTION_ERROR.inspDate == 'ERROR'}">
              <br/><span class="error">* please inspection date  </span> 
             </c:if>     
            </td>
      </tr>
     <tr>
          <th scope="row"      >  violation to be corrected by Date:</th>
          <td>
           <html:text property="nextInspDate" size="10" maxlength="10"/>(mm/dd/yyyy)<a href="javascript:NewCal('nextInspDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
                 
            </td>
      </tr>
 <%--    <tr>
          <th scope="row"     > violation letter Date:</th>
          <td>
           <html:text property="inspVioPrintDate" size="10" maxlength="10"/>(mm/dd/yyyy)
                 
            </td>
      </tr> --%>
        <tr>
      <th scope="row"   class="required"     >  * inspector:</th>
      <td>
          <html:select property="inspInspectorId" styleId="SELECT_INSPECTOR_ID" onchange="setSelectValue('SELECT_INSPECTOR_ID','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSPECTORS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspInspectorId == 999999}">
              <br/><span class="error">* please enter inspector  </span> 
            </c:if>
       </td>
      </tr> 
      <tr>
      <th scope="row"        >  notes:</th>
          <td>
            <html:textarea property="notes" rows="10" cols="70"/>
                
            </td>
      </tr> 
   
  </tbody>
        </table>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">        
      
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
     
  </c:if>
    </html:form>   
</div>             
              
</div>
<table cellspacing="0" style="width:100%;" summary="sales" >
 

  <tr>
    <th >code</th>
    <th style="width:35%;">description</th>
    <th style="width:35%;">remarks</th>
    <th >violation to be corrected by date</th>
    <th >violation complied date</th> 
  </tr>
  <tbody>
  
   <c:forEach var="violation" items="${sessionScope.INSPECTION_SELECTED.violations}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
       <c:out value="${violation.vioCode}"/>&nbsp; <br/> 
          </td>
        <td>
        <c:out value="${violation.vioDescription}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioRemarks}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioDateString}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioCompDateString}"/>&nbsp; <br/> 
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
 