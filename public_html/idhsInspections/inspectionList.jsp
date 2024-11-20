<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>

<div id="mainBox">

   <a   href="/dfbs/idhsInspections/inspection.do?method=IDHSgoToUpload&idNumber=<c:out value="${INSPECTION_SEARCH_ID}"/>&idType=idhsInspection">
             [Upload file /photos]</a>

<table cellspacing="0" style="width:60%;" summary="sales">
  <tr>
    <th>Inspection Details</th>
  </tr>
  <tbody>
   <c:forEach var="inspection" items="${INSPECTION_LIST.list}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
      <c:if test="${inspection.inspCompliance =='C' || inspection.inspCompliance =='COMPLIED' || inspection.inspCompliance =='NOTES'}"> 
      <span class="message" >
       <c:out value="${inspection.inspCompliance}"/>  Facility Id:<c:out value="${inspection.inspFacId}"/>&nbsp;Facility Name:<c:out value="${inspection.facilityName}"/><br/>
         Inspection Date:  <c:out value="${inspection.inspDateString}"/>&nbsp;Inspection Status: <c:out value="${inspection.inspCompliance}"/>&nbsp;;Inspection Type: <c:out value="${inspection.inspType}"/> <br/> 
        Inspector:   <c:out value="${inspection.inspectorName}"/>&nbsp;;Record Created Date:<c:out value="${inspection.recCreatedDateString}"/></span>
        Sprinkler: <c:out value="${inspection.inspSprinkler}"/>&nbsp; Alarm: <c:out value="${inspection.inspAlarm}"/>&nbsp;
        Remarks:<c:out value="${inspection.inspRemarks}"/>&nbsp;
        </c:if>
          <c:if test="${inspection.inspCompliance !='C' && inspection.inspCompliance!='COMPLIED' && inspection.inspCompliance!='NOTES'}">
       <span class="error" >
       Facility Id:<c:out value="${inspection.inspFacId}"/>&nbsp;Facility Name:<c:out value="${inspection.facilityName}"/><br/>
         Inspection Date:  <c:out value="${inspection.inspDateString}"/>&nbsp;Inspection Status: <c:out value="${inspection.inspCompliance}"/>&nbsp;;Inspection Type: <c:out value="${inspection.inspType}"/> <br/> 
        Inspector:   <c:out value="${inspection.inspectorName}"/>&nbsp;;Record Created Date:<c:out value="${inspection.recCreatedDateString}"/></span>
         Remarks:<c:out value="${inspection.inspRemarks}"/>&nbsp;
         </c:if>
        </br>
        <span class="message" ><c:out value="${inspection.inspUploadError}"/></span>
        <c:if test="${inspection.inspCompliance =='VIOLATION' ||inspection.inspCompliance =='COMPLIED'||inspection.inspCompliance =='ORDER'||inspection.inspCompliance =='NOTES'}">
         <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=idhs_fire_code_insp_report.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">[view inspection report]</a>
         <a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${inspection.inspId}"/>">
             [update / view this inspection]</a>
          <a   href="/dfbs/idhsInspections/idhsInspection.do?method=ReIdhsInspection&inspectionId=<c:out value="${inspection.inspId}"/>">
             [add reinspection]</a>
         <c:if test="${sessionScope.RESULTS_FLAG =='AEPERMITS' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_entr_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
          <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='DC' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_child_care_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
            <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='GH' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_group_home_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
             <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='MD' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_mig_care_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
            <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='CI' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_daycare_inst_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
             <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='RM' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_daycaremin_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
          <c:if test="${sessionScope.RESULTS_FLAG =='SCHOOL' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_school_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
           <c:if test="${sessionScope.RESULTS_FLAG =='UST' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_ust_location_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
            <c:if test="${sessionScope.RESULTS_FLAG =='HOSPITAL' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_hospitals_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
             <c:if test="${sessionScope.RESULTS_FLAG =='OTHER' && inspection.inspCompliance =='VIOLATION'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_other_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
         </c:if>
          <c:if test="${inspection.inspId <145661}">
          <c:if test="${sessionScope.RESULTS_FLAG =='AEPERMITS' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_entr_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
          <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='DC' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_child_care_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
            <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='GH' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_group_home_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
             <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='MD' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_mig_care_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
            <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='CI' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_daycare_inst_letter_new.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
             <c:if test="${sessionScope.RESULTS_FLAG =='DAYCARE' && sessionScope.RESULTS_FLAG_ID =='RM'&& inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_daycaremin_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
          <c:if test="${sessionScope.RESULTS_FLAG =='SCHOOL' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_school_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
           <c:if test="${sessionScope.RESULTS_FLAG =='UST' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_ust_location_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
            <c:if test="${sessionScope.RESULTS_FLAG =='HOSPITAL' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_hospitals_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
             <c:if test="${sessionScope.RESULTS_FLAG =='OTHER' && inspection.inspCompliance =='V'}">
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_vio_other_letter.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">
         [violation order]</a>
          </c:if>
           </c:if>
          
    </td>
         
      </tr>
    </c:forEach>
  </tbody>
</table>
 
</div>

<div class="clearer">&nbsp;</div>

<jsp:include page="/app/common/hsPageFooter.jsp"/> 