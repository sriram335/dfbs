 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Inspections"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<a href="/dfbs/idhsInspections/search.do?method=aepermits">
AE Permit(s)[<c:out value="${sessionScope.CURRENT_SEARCH.aeCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=bpv">
 BPV[<c:out value="${sessionScope.CURRENT_SEARCH.bpvCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=elevators">
 Elevator(s)[<c:out value="${sessionScope.CURRENT_SEARCH.elevCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=daycare">
 Child Care(s)[<c:out value="${sessionScope.CURRENT_SEARCH.daycareCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=fireworksRetail">
 Fireworks Retail[<c:out value="${sessionScope.CURRENT_SEARCH.fireworksRetailCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=fireworksWholesale">
 Fireworks Wholesale[<c:out value="${sessionScope.CURRENT_SEARCH.fireworksWholesaleCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=hospital">
 Health Care(s)[<c:out value="${sessionScope.CURRENT_SEARCH.hospitalCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=other">
 Other(s)[<c:out value="${sessionScope.CURRENT_SEARCH.otherCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=school">
 School(s)[<c:out value="${sessionScope.CURRENT_SEARCH.schoolCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=plan">
 Plan(s)[<c:out value="${sessionScope.CURRENT_SEARCH.planCount}"/>] </a>&nbsp;
<a   href="/dfbs/idhsInspections/search.do?method=ust">
 UST(s)[<c:out value="${sessionScope.CURRENT_SEARCH.ustCount}"/>] </a>&nbsp; 
<a   href="/dfbs/idhsInspections/search.do?method=newInspections">
 Complaint Inspection(s)[<c:out value="${sessionScope.CURRENT_SEARCH.complaintCount}"/>] </a>&nbsp;  
</br></br>
<div id="sideContentFluid">
<b><u>Down Load Facility Files:</u></b><br/> 
<c:forEach var="file" items="${sessionScope.INSPECTION_FILE_LIST}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      <c:out value="${file.fileName}"/>,Upload Date:<c:out value="${file.fileDateString}"/></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/idhsInspections/inspection.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
         <c:if test="${(sessionScope.DFBS_SECURITY.userId =='DTOLAN'   || sessionScope.DFBS_SECURITY.userId =='CCLOUSE'   ||  sessionScope.DFBS_SECURITY.userId =='RCOOLEY' || sessionScope.DFBS_SECURITY.groupLevelAll != null) && sessionScope.RESULTS_FLAG !='PLAN'}">  
       <a href="/dfbs/idhsInspections/inspection.do?method=deleteFile&fileId=<c:out value="${file.fileId}"/>&idNumber=<c:out value="${INSPECTION_SEARCH_ID}"/>"><FONT color="#ff0000">[delete this file, can not be recovered if deleted]</FONT></a> </br>
        </c:if>
          <c:if test="${sessionScope.RESULTS_FLAG_ID =='RM' && (sessionScope.DFBS_SECURITY.userId =='JSHORT'   ||  sessionScope.DFBS_SECURITY.userId =='CWAHL' ||  sessionScope.DFBS_SECURITY.userId =='JRETHERFORD')&& sessionScope.RESULTS_FLAG !='PLAN'}">  
       <a href="/dfbs/idhsInspections/inspection.do?method=deleteFile&fileId=<c:out value="${file.fileId}"/>&idNumber=<c:out value="${INSPECTION_SEARCH_ID}"/>"><FONT color="#ff0000">[delete this file, can not be recovered if deleted]</FONT></a> </br>
        </c:if>
          </td>
        <td>
</tr><br/>
</c:forEach>
</div>
<p align="left" class="error">
<b><U> Inspections for: <c:out value="${INSPECTION_SEARCH_ID}"/></u></b> </p>


 <html:form action="/idhsInspection" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="newIdhsInspection"/> 
<p align="left">  
            <input type="submit" value="Add new inspection" class="button"/>            
</p>
</html:form>

 <html:form action="/idhsInspection" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="startOver"/> 

<p align="left">  
            <input type="submit" value="Start Over" class="button"/>            
</p>
</html:form>

<jsp:include page="/idhsInspections/inspectionList.jsp"/>
<div class="clearer">&nbsp;</div>
</div>
<p align="left" class="error">
<b><U>Inspector Activity:</u></b></p>
<table cellspacing="0" style="width:50%;" summary="sales" >
 

  <tr>
    <th >activity Type</th>
    <th >activity date</th>
    <th >activity duration</th>
    <th >contact</th>
    <th >location</th> 
    <th >remarks</th> 
    <th >Inspector</th>
  </tr>
  <tbody>
  
   <c:forEach var="activity" items="${sessionScope.ACTIVITIES_SELECTED}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
        <c:out value="${activity.activityType}"/>&nbsp; <br/>
        <c:if test="${sessionScope.INSPECTOR_CURRENT == activity.inspectorId || sessionScope.DFBS_SECURITY.groupLevelFire =='SUPERVISOR'}">
        <a   href="/dfbs/idhsInspections/inspectorActivity.do?method=updateInspActivity&inspActivityId=<c:out value="${activity.activityId}"/>">
             [Edit this activity]</a>
         <a   href="/dfbs/idhsInspections/inspectorActivity.do?method=deleteInspActivity&inspActivityId=<c:out value="${activity.activityId}"/>">
             [Delete this activity]</a>
        </c:if>
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
        <td>
        <c:out value="${activity.inspectorName}"/>&nbsp; <br/> 
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
 
<jsp:include page="/app/common/hsPageFooter.jsp"/> 