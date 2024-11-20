<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 back to LEPC</a></br>
 <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a>  
<div id="mainBox">
  <h2>Add / Update <c:out value='${sessionScope.LEPC_EXERCISE.reportType}'/> </h2>

<div id="leftContentFluid">
<c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Exercise'}">
<p class="messageBox" size="200" align="left"><u><b> FINAL EXERCISE REPORT</b></u></br>
>>	DO submit report within 30 days after exercise completion</br>
>>	DO input information per instructions</br>
>>	DO report ONLY EHS- or CERCLA-defined chemicals used in your exercise</br> 
>><font color=red>DO remember that all exercises should include HSEEP information as applicable</font>
</p>
</c:if>
<c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Proposal'}">
<p class="messageBox" size="200" align="left"><u><b> EXERCISE PROPOSAL</b></u></br>
>>	DO submit proposal at least 30 days before exercise start date</br>
>>	DO input information per instructions</br>
>>	DO use ONLY EHS- or CERCLA-defined chemicals in your exercise (this system will only accept these chemicals for your proposal)</br>
>><font color=red>DO remember that all exercises should include HSEEP information as applicable</font>
</p>
</c:if>
<table cellspacing="0"  summary="sales"   frame="box">
      <tbody class="rowHeader">  
<html:form action="/exercise" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="saveExercise"/>
   <html:hidden property="lepcId"/> 
    <html:hidden property="exerciseId"/> 
     <html:hidden property="exerciseStatus"/> 
     <html:hidden property="reportDate"/>
      <html:hidden property="reportBy"/>
      <html:hidden property="reportType"/>
    <tr>
    <th>____________________________</th>
    <td>
    <b><u>Values</u></b>
    </td>
    </tr>
    
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Type:</th>
      <td>
      <html:select property="exerciseType"  onchange="setSelectValue('SELECT_DESCRIPTION','DESCRIPTION')">
          <html:option value="">-----</html:option>
          <c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Exercise'}">
          <html:options collection="LEPC_EXER_TYPE" property="value" labelProperty="label"/>
          </c:if>
          <c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Proposal'}">
          <html:options collection="LEPC_PROP_TYPE" property="value" labelProperty="label"/>
          </c:if>
        </html:select>
        
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > HSEEP Approval:</th> 
      <td>
       <html:text property="exerciseNumber" size="20" maxlength="20"/> 
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Start Date:</th> 
      <td>
       <html:text property="exerciseStartDate" size="10" maxlength="10"/> MM/DD/YYYY
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Start Time:</th>
      <td>
       <html:text property="exerciseStartTime" size="10" maxlength="10"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >Exercise End Date:</th> 
      <td>
       <html:text property="exerciseEndDate" size="10" maxlength="10"/> MM/DD/YYYY
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise End Time:</th>
      <td>
       <html:text property="exerciseEndTime" size="10" maxlength="10"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Location:</th>
      <td>
       <html:text property="exerciseLocation" size="100" maxlength="100"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > City:</th>
      <td>
       <html:text property="exerciseCity" size="50" maxlength="50"/>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > County:</th>
      <td>
       <html:select property="exerciseCounty"  onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       </td>     
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  > Scenario:</th>
      <td>
       <html:textarea property="exerciseScenario" cols="70"/>
       </td>     
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  > Exercise Incident Type:</th>
      <td>
      <html:select property="exerciseIncidentType"  onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="LEPC_INCIDENT_TYPE" property="value" labelProperty="label"/>
        </html:select>
        </td>     
    </tr>
    
<tr>
<th> </th>
<td>
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null)
    && (sessionScope.LEPC_EXERCISE.exerciseStatus ==null || sessionScope.LEPC_EXERCISE.exerciseStatus == 'Pending')}"> 
  <html:submit value="Save Exercise" styleClass="button" />
  <c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Proposal'}">
  <p class="error">Enter Exercise information above then Click "Save Exercise"  to reach options for Adding Agencies and Chemicals</p>
  </c:if>
</c:if>
</td>
</tr>
</html:form>
</tbody>
</table> <br/>  <br/> 

<c:if test="${sessionScope.LEPC_EXERCISE.exerciseId > 0 }">
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null)
    && (sessionScope.LEPC_EXERCISE.exerciseStatus ==null || sessionScope.LEPC_EXERCISE.exerciseStatus == 'Pending')}"> 
<a  href="/dfbs/lepc/agency.do?method=addAgency&exerciseId=<c:out value='${sessionScope.LEPC_EXERCISE.exerciseId}'/>">
                  Add Agency</a><br/> 
</c:if>
<b><u>Agencies Details</u></b><br/>  <br/> 
<c:forEach var="agency" items="${sessionScope.AGENCY_LIST}" varStatus="i">
         Name: <b> <c:out value="${agency.agencyName}"/>.&nbsp;</b>Agency Type: <b><c:out value="${agency.agencyType}"/></b> <br/> 
         Agency on Scene: <b><c:out value="${agency.agencyPresence}"/></b>.&nbsp;
         <c:if test="${sessionScope.LEPC_EXERCISE.reportType == 'Exercise'}">
         Agency Debriefing: <b><c:out value="${agency.agencyDebrief}"/></b>.&nbsp;
         </c:if><br/> 
  <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null)
    && (sessionScope.LEPC_EXERCISE.exerciseStatus ==null || sessionScope.LEPC_EXERCISE.exerciseStatus == 'Pending')}">        
         <a  href="/dfbs/lepc/agency.do?method=updateAgency&agencyId=<c:out value='${agency.agencyId}'/>">
                  Edit</a><br/>
    </c:if>
          <br/>  <br/> 
</c:forEach>
</c:if> 
<c:if test="${sessionScope.LEPC_EXERCISE.exerciseId > 0 }">
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null)
    && (sessionScope.LEPC_EXERCISE.exerciseStatus ==null || sessionScope.LEPC_EXERCISE.exerciseStatus == 'Pending')}"> 
<a  href="/dfbs/lepc/chemical.do?method=addChemical&exerciseId=<c:out value='${sessionScope.LEPC_EXERCISE.exerciseId}'/>">
                  Add Chemical</a><br/> 
</c:if>
<b><u>Chemicals Used </u></b><br/>  <br/> 
<c:forEach var="chemical" items="${sessionScope.CHEMICAL_LIST}" varStatus="i">
         Is Chemical EHS: <b> <c:out value="${chemical.isChemEHS}"/>.&nbsp;</b> 
         <br/> Is Chemical CERCLA: <b><c:out value="${chemical.isChemCercla}"/></b> <br/> 
         Chemical Name and CAS Number: <b><c:out value="${chemical.chemName}"/></b> <br/>
         RQ for Chemical: <b><c:out value="${chemical.rqForChem}"/></b>.&nbsp;<br/> 
         Amount of Chemical Released: <b><c:out value="${chemical.quantityReleased}"/></b>.&nbsp;<br/> 
         <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null)
    && (sessionScope.LEPC_EXERCISE.exerciseStatus ==null || sessionScope.LEPC_EXERCISE.exerciseStatus == 'Pending')}"> 
         <a  href="/dfbs/lepc/chemical.do?method=updateChemical&chemicalId=<c:out value='${chemical.chemicalId}'/>">
                  Edit</a>&nbsp;&nbsp;
           <a  href="/dfbs/lepc/chemical.do?method=deleteChemical&chemicalId=<c:out value='${chemical.chemicalId}'/>">
                 Delete</a><br/>
              </c:if>
          <br/>  <br/> 
</c:forEach>
</c:if> 
</div>
  <div class="clearer">&nbsp;</div>
 </div> 