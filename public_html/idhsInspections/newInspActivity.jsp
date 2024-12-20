<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Idhs Inspections" />
<c:set var="title" scope="request" value="Inspector Activity" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<link rel="stylesheet" type="text/css" media="all" href="/dfbs/idhsInspections/jsDatePick_ltr.min.css" />
<script type="text/javascript" src="/dfbs/idhsInspections/jsDatePick.min.1.3.js"></script>
<script type="text/javascript">
window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"activityDate",
			dateFormat:"%m/%d/%Y"
		});
	};
</script>
<h2>Add New Inspector Activity</h2> 

<div id="leftContentFluid">
<a   href="/dfbs/idhsInspections/inspection.do"> 
       Go to Inspection(s) </a>&nbsp; 
  <div class="styledBox">
 <html:form action="/inspectorActivity" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInsertInspActivity"/> 
      <html:hidden property="inspectorId"/> 
      <html:hidden property="activityId"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th style="font-size:medium;font-weight:bold" scope="row" style="width : 50%"> </th>
      <td style="font-size:medium;font-weight:bold">
       
      </td>
    </tr>
   
    <tr>
          <th scope="row"    > * activity type:</th>
          <td>
           <html:select property="activityType" styleId="SELECT_INSP_ACT" onchange="setSelectValue('SELECT_INSP_ACT','ACTIVITY')">
          <html:option value="">Please Select</html:option>
          <html:options collection="IDHS_INSP_ACTIVITY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
            </td>
           </tr>
     <tr>
          <th scope="row"    > * activity date:</th>
          <td>
 		   <input type="text" size="10" maxlength="10" name="activityDate" id="activityDate" /> (mm/dd/yyyy)
            </td>
           </tr>
      <tr>
          <th scope="row"    > * activity duration:</th>
          <td>
          <html:text property="activityDuration" size="10" maxlength="10"/>
            </td>
           </tr>
       <tr>
          <th scope="row"    > * activity contact:</th>
          <td>
          <html:text property="activityContact" size="30" maxlength="30"/>
            </td>
           </tr>
         <tr>
          <th scope="row"    > * activity location:</th>
          <td>
          <html:text property="activityLocation" size="30" maxlength="100"/>
            </td>
           </tr>
            <tr>
          <th scope="row"    > * activity remarks(max 2000 characters):</th>
          <td>
          <html:textarea property="activityRemarks" rows="10" cols="70" />
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
    <th >activity Type</th>
    <th >activity date</th>
    <th >activity duration</th>
    <th >contact</th>
    <th >location</th> 
    <th >remarks</th> 
  </tr>
  <tbody>
  
   <c:forEach var="activity" items="${sessionScope.ACTIVITIES_SELECTED}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
        <c:out value="${activity.activityType}"/>&nbsp; <br/> 
        <a   href="/dfbs/idhsInspections/inspectorActivity.do?method=updateInspActivity&inspActivityId=<c:out value="${activity.activityId}"/>">
             [Edit this activity]</a>
        </td>
        <td>
        <c:out value="${activity.activityDateString}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${activity.activityDuration}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${activity.activityContact}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${activity.activityLocation}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${activity.activityRemarks}"/>&nbsp; <br/> 
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
 