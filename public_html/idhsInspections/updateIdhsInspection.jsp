<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>


<hs:control/>
<c:set var="module" scope="request" value="Idhs Inspections" />
<c:set var="title" scope="request" value="Update Inspection" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Update Inspection</h2> 
<div id="sideContentFluid">
<%--<span class="message" style="font-weight:bold;font-size:small;">Print violation report at Fire Marshal Office </span> 
     <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbspipypdf&report=idhs_fire_code_insp_report.rdf&desname=FireLaser&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[print]</a></br>
   --%>  
   <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbspipyhtml&report=idhs_fire_code_insp_report.rdf&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[Open Report in Chrome Browser]</a></br>
       <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null) }">        
         <html:form action="/inspection" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="fineAction"/>
        <p>
        <b><u>(Deafults to inspection date if no date is entered)</u></b></br>
        Fine Start Date<html:text property="fineStartDate" size="10" maxlength="10"/> </br>
        Fine End Date<html:text property="fineEndDate" size="10" maxlength="10"/> </br>
          <html:submit value="Start Fine" styleClass="button" property="inspectionAction"/></br>
          <html:submit value="Stop Fine" styleClass="button" property="inspectionAction"/></br>
        </p>
        <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=fm_notice_of_order_fine.rdf&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[print notice of fine]</a></br>
        <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=fm_initial_fine_letter.rdf&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[print policy overview letter]</a></br>
        <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=fm_notice_of_fine.rdf&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[print fine end letter]</a></br>
         </html:form>
        </c:if>
     <c:forEach var="fine" items="${sessionScope.FINE_LIST}" varStatus="i">
<table cellspacing="0" style="width: 100%;" summary="sales">
  <tr>
    <th>Receipt Number #</th>
    <th>Due</th>
    <th>Fine Start Date</th>
    <th>Fine End Date</th>
    <th>Fine created by</th>
  </tr>
  <tbody>
   
      <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message">
          <c:out value="${fine.receiptNumber}"/></span>
        </td>
      
        <td><span class="message" >
          <c:out value="${fine.fine}"/></span>
          
        </td>
      
        <td><span class="message" >
          <c:out value="${fine.fineStartDateString}"/></span>
          <c:if test="${sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTOR_SUPERVISOR || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR'}">  
  
          <a   href="/dfbs/idhsInspections/inspection.do?method=deleteFine&facilityId=<c:out value="${fine.facilityId}"/>&fineEndDate=<c:out value="${fine.fineEndDateString}"/>">
              [delete fine]</a>
          </c:if>
        </td>
       <td><span class="message">
          <c:out value="${fine.fineEndDateString}"/></span>
          
        </td>
        <td><span class="message" >
          <c:out value="${fine.fineCreatedBy}"/></span>
          
        </td>
      </tr>
   
  </tbody>
</table>
 </c:forEach>
  </div>
<div id="leftContentFluid">
<a   href="/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>">
              [back to inspection list]</a>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll == 'DBA'|| sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR' || (sessionScope.DFBS_SECURITY.userEmail == 'CWAHL@DHS.IN.GOV' ||sessionScope.DFBS_SECURITY.userEmail == 'PHUTZEL@DHS.IN.GOV')}">
<a   href="/dfbs/idhsInspections/AEPermitApproval.do?method=updateFacUse&inspFacId=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>">
              [Approve AE permit(s)]</a>
  <c:if test="${sessionScope.RULE13_FLAG =='INSERT'}">  
  <a   href="/dfbs/idhsInspections/rule13.do?method=addRuleList">
              [Rule13 Add]</a>
  </c:if>
   <c:if test="${sessionScope.RULE13_FLAG =='UPDATE'}">  
  <a   href="/dfbs/idhsInspections/rule13.do?method=updateRuleList">
              [Rule13 view/update]</a>
  </c:if>
   <a   href="/dfbs/idhsInspections/updateOwner.do?method=addNewOwner">
              [Add New Owner and facility]</a>
   (To update owner's please use other applications Example for AE's use AE application)
   </c:if>  
      </br>  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=idhs_fire_code_insp_report.rdf&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[view and Print Report (No Notes)]</a></br>
   <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=idhs_fire_code_insp_report.rdf&p_notes=Yes&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[view and Print Report with Notes]</a></br>
  
  <div class="styledBox">
 <html:form action="/idhsInspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdateIdhsInspection"/> 
      <html:hidden property="inspId"/> 
      <html:hidden property="recCreatedDate"/>
      
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
  <%--  <tr>
      <th > </th>
      <td style="color:red">
     <b>Super Bowl 2012 related inspection </b> 
     <c:if test="${INSPECTION_SELECTED.inspOtherActivity != 'Y'}">
           <input type="checkbox" name="inspOtherActivity"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${INSPECTION_SELECTED.inspOtherActivity == 'Y'}">
              <input type="checkbox" name="inspOtherActivity"  class="switch"  value="Y" checked/>
       </c:if>
      </td>
    </tr>  --%>
    <tr>
          <th scope="row"    >  Record Created Date:</th>
          <td>
          <c:out value="${INSPECTION_SELECTED.recCreatedDateString}"/> 
            </td>
           </tr>
    <tr>
          <th scope="row"    > * facility ID:</th>
          <td>
          <html:text property="inspFacId" size="30" maxlength="30"/> 
            </td>
           </tr>
     <tr>
          <th scope="row"    > * facility name:</th>
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
          <th scope="row"    >  facility state:</th>
          <td>
          IN
            </td>
           </tr>
     <tr>
          <th scope="row"    > * facility zip:</th>
          <td>
          <html:text property="facilityZip" size="5" maxlength="5"/>
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
          <th scope="row"    > * facility Email:</th>
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
          <th scope="row"    > Suppression status:</th>
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
     <tr>
          <th scope="row"    >  project Type(select ll that apply):</th>
          <td>
           <c:if test="${INSPECTION_SELECTED.inspProjNew !='Y'}">
            New:<input type="checkbox" name="inspProjNew"  class="switch"  value="Y"/>
          </c:if>
          <c:if test="${INSPECTION_SELECTED.inspProjNew =='Y'}">
            New:<input type="checkbox" name="inspProjNew"  class="switch"  value="Y" checked/>
          </c:if>
           <c:if test="${INSPECTION_SELECTED.inspProjAddition !='Y'}">
           Addition:<input type="checkbox" name="inspProjAddition"  class="switch"  value="Y"/>
          </c:if>
          <c:if test="${INSPECTION_SELECTED.inspProjAddition =='Y'}">
           Addition:<input type="checkbox" name="inspProjAddition"  class="switch"  value="Y" checked/>
          </c:if>
           <c:if test="${INSPECTION_SELECTED.inspProjRemodel !='Y'}">
          Remodel:<input type="checkbox" name="inspProjRemodel"  class="switch"  value="Y"/>
          </c:if>
          <c:if test="${INSPECTION_SELECTED.inspProjRemodel =='Y'}">
          Remodel:<input type="checkbox" name="inspProjRemodel"  class="switch"  value="Y" checked/>
          </c:if>
           <c:if test="${INSPECTION_SELECTED.inspProjOccChange !='Y'}">
          Occ.Change:<input type="checkbox" name="inspProjOccChange"  class="switch"  value="Y"/>
          </c:if>
          <c:if test="${INSPECTION_SELECTED.inspProjOccChange =='Y'}">
          Occ.Change:<input type="checkbox" name="inspProjOccChange"  class="switch"  value="Y" checked/>
          </c:if>
           <c:if test="${INSPECTION_SELECTED.inspProjExisting !='Y'}">
          Existing:<input type="checkbox" name="inspProjExisting"  class="switch"  value="Y"/>
          </c:if>
          <c:if test="${INSPECTION_SELECTED.inspProjExisting =='Y'}">
          Existing:<input type="checkbox" name="inspProjExisting"  class="switch"  value="Y" checked/>
          </c:if>
            </td>
           </tr> 
           
    <tr>
          <th scope="row"    > inspection Category:</th>
          <td>
          <html:select property="inspMaintType" styleId="SELECT_INSP_EXCEL" onchange="setSelectValue('SELECT_INSP_EXCEL','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_MAINT_INSP_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
 
     <tr>
          <th scope="row"  class="required"  > inspection  type:</th>
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
  
    <tr>
          <th scope="row"  class="required"  > inspection status:</th>
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
          <th scope="row"  class="required"    >  inspection Date:</th>
          <td>
           <html:text property="inspDate" size="10" maxlength="10"/>(mm/dd/yyyy)<a href="javascript:NewCal('inspDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
              <c:if test="${INSPECTION_ERROR.inspDate == 'ERROR'}">
              <br/><span class="error">* please inspection date  </span> 
             </c:if>      
            </td>
           
      </tr>
     <tr>
          <th scope="row"     > violation to be corrected by Date:</th>
          <td>
           <html:text property="nextInspDate" size="10" maxlength="10"/>(mm/dd/yyyy)<a href="javascript:NewCal('nextInspDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
                 
            </td>
      </tr>
  
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
      <tr>
          <th scope="row"    > Facility Match?:</th>
          <td>
           <html:text property="inspUploadError" size="50" maxlength="100"/>(Type in Facility Matched, when you match a complaint inspection with facility.)
                 
            </td>
      </tr> 
  </tbody>
        </table>
     
   <c:if test="${(sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId &&sessionScope.INSPECTION_SELECTED.editFlag <14) ||sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTOR_SUPERVISOR || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR'}"> 
      
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
        <c:if test="${INSPECTION_SELECTED.inspId > 0}">
     
       </br>  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=idhs_fire_code_insp_report.rdf&p_inspection_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspId}"/>" target="_blank" >[view and Print Report]</a></br>
   </c:if> 
  
 </c:if> 
    </html:form>
 
   <c:if test="${(sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId &&sessionScope.INSPECTION_SELECTED.editFlag <14) ||sessionScope.DFBS_SECURITY.userId =='RPULIKAL'   ||  sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTOR_SUPERVISOR || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR'}">  
   <html:form action="/idhsInspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="approveFireworks"/> 
  <p>
 <c:if test="${(sessionScope.PRINT_FAC_TYPE=='FWT' || sessionScope.PRINT_FAC_TYPE=='FWR'||sessionScope.PRINT_FAC_TYPE=='FW')}"> 
   <html:submit value=" Fireworks Approve with this date" styleClass="button"/>  
  </c:if>
  </p>
</html:form>   
    <a   href="/dfbs/idhsInspections/idhsInspection.do?method=deleteIdhsInspection">
              [delete this inspection]</a>
     </c:if>
    <c:if test="${(sessionScope.PRINT_FAC_TYPE=='AE') }">  
    <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>">
             [view & print  annual permit]</a> 
    <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsmailipdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>&desname=<c:out value="${sessionScope.DFBS_SECURITY.userId}"/>@dhs.in.gov&from=<c:out value="${sessionScope.DFBS_SECURITY.userId}"/>@dhs.in.gov">
             [Email permit]</a> 
             
      <c:forEach var="special" items="${sessionScope.SPECIALS_READY_TO_PRINT}" varStatus="i">
      <br/> <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>&p_special_date=<c:out value="${special.resultCity}"/>">
       [ view & print special permit for event date <c:out value="${special.resultAddress2}"/>]</a> 
        <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsmailipdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>&p_special_date=<c:out value="${special.resultCity}"/>&desname=<c:out value="${sessionScope.DFBS_SECURITY.userId}"/>@dhs.in.gov&from=<c:out value="${sessionScope.DFBS_SECURITY.userId}"/>@dhs.in.gov">
       [ Email special permit for event date <c:out value="${special.resultAddress2}"/>]</a> 
      </c:forEach>
     </c:if>
     <c:if test="${(sessionScope.PRINT_FAC_TYPE=='FW') }">  
      <a   href="/dfbs/fireworks/main.do?method=wholesaleToPrint&permitNumber=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>">
             [view & print  permit]</a> 
     </c:if>
      <c:if test="${(sessionScope.PRINT_FAC_TYPE=='FWT' || sessionScope.PRINT_FAC_TYPE=='FWR')}">  
      <a   href="/dfbs/fireworks/main.do?method=consumerToPrint&permitNumber=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>">
             [view & print  permit]</a> 
     </c:if>
</div>             
</div>              

<div id="mainContentFluid">
<html:form action="/idhsInspection" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteSelectedViolations"/> 
            
<table cellspacing="0" style="width:100%;" summary="sales" >
 

  <tr>
    <th >print order</th>
    <th >code</th>
    <th style="width:30%;">description</th>
    <th style="width:30%;">remarks</th>
    <th >violation to be corrected by date</th>
    <th >violation complied date</th> 
  </tr>
  <tbody>
  
   <c:forEach var="violation" items="${sessionScope.INSPECTION_SELECTED.violations}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${violation.vioOrder}"/>&nbsp; <br/> 
      </td>
        <td>
       <c:out value="${violation.vioCode}"/>&nbsp; <br/> 
         </td>
        <td>
        <c:out value="${violation.vioDescription}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioRemarks}"/>&nbsp; <br/> 
        <c:if test="${sessionScope.INSPECTOR_CURRENT == INSPECTION_SELECTED.inspInspectorId&&INSPECTION_SELECTED.editFlag <14||sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTOR_SUPERVISOR || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR' }"> 
       <a   href="/dfbs/idhsInspections/idhsViolation.do?method=updateIdhsViolation&violationId=<c:out value="${violation.vioId}"/>">
             [Edit this violation]</a> </br>
         <b>Delete:</b><input type="checkbox" name="<c:out value='${violation.vioId}' />" class="switch"
                   value="Y"/>
       <%--  <a   href="/dfbs/idhsInspections/idhsViolation.do?method=deleteIdhsViolation&violationId=<c:out value="${violation.vioId}"/>">
             [delete this violation]</a> --%>
       </c:if>
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
<c:if test="${(sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId   ||  sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTOR_SUPERVISOR || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR')}">  
             <input type="submit" value="Delete Selected Violations" class="button" />  </br> 
            </c:if>
</html:form>
<c:if test="${( sessionScope.DFBS_SECURITY.groupLevelFire !=null)}"> 
<html:form action="/idhsInspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="emailReport"/> 
  <p>
  To: <html:text property="emailTo" size="50" maxlength="200"/></br>
  (seperate multiple emails by ,)</br>
   <html:submit value=" Email Report " styleClass="button"/>     
  </p>

</html:form>
<html:form action="/idhsViolation" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="newIdhsViolation"/> 
  <p>
  Model Year Code: <input type="text" name="year" size="10" maxlength="4" value="" onBlur="return yearCheck(this)"/> 
 
   <html:submit value=" Copy and Paste Violations " styleClass="button"/>     
  </p>

</html:form>

<jsp:include page="/idhsInspections/lookUpStdViolation.jsp"/>
</c:if>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/> 