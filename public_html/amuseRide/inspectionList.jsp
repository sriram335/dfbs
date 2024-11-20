<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">

<html:form action="/inspection" method="post">
     <input type="hidden" name="method" id="METHOD_KEY" value="newElevInspection"/>
       <html:submit value="Submit New Inspection" styleClass="button" />
</html:form></br>
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbs&report=elev_report_of_tests_blank.rdf&p_state_number=<c:out value="${ELEVATOR_SELECTED.stateNumber}"/>">Pre-Inspection Report (Blank) </a> 
<p class="messageBox" size="100"> 
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }"> 
<html:form action="/elevator" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="fineSubsequent"/>
      <input type="submit" value="Add Subsequent Fine" class="button"/>
   
</html:form></br>
<html:form action="/elevator" method="post">
<input type="hidden" name="method" id="METHOD_KEY" value="fineFollowUp"/>
      <input type="submit" value="Add Follow Up Fine" class="button"/>
</html:form>
 </c:if>

   <a   href="/dfbs/idhsInspections/inspection.do?method=IDHSgoToUpload&idNumber=<c:out value="${INSPECTION_SEARCH_ID}"/>&idType=idhsInspection">
             [Upload file /plans]</a>
  </p>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th>Inspection Details for  State Number :<b><c:out value="${ELEVATOR_SELECTED.stateNumber}"/></b> 
   </th>
  </tr>
  <tbody>
   <c:forEach var="inspection" items="${ELEVATOR_SELECTED.elevInspections}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
         Inspection Date:  <c:out value="${inspection.inspectionDateString}"/>&nbsp;Inspection Type: <c:out value="${inspection.inspectionType}"/>&nbsp;;Inspection Category: <c:out value="${inspection.inspCategory}"/> <br/> 
        Inspector Id:   <c:out value="${inspection.inspectorId}"/>&nbsp;;Remarks: <c:out value="${inspection.remarks}"/>
        <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelElev != null) || (inspection.comments !=null &&inspection.comments == sessionScope.SESSION_ID)}">  
        <a   href="/dfbs/elevator/inspection.do?method=elevUpdateInspection&inspectionId=<c:out value="${inspection.inspectionId}"/>">
             [update / view this inspection]</a>
        </c:if>
        <a   href="/dfbs/elevator/inspection.do?method=elevUpdateInspection&inspectionId=<c:out value="${inspection.inspectionId}"/>">
             [update / view this inspection]</a>
        <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbs&report=elev_report_of_tests.rdf&p_insp_id=<c:out value="${inspection.inspectionId}"/>">[View Inspection Report] </a> 

        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
</div>