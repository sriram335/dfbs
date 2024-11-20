 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="IDHS Inspections"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">


<p align="left" class="error">
<b><U> Inspections for: <c:out value="${INSPECTION_SEARCH_ID}"/></u></b> </p>

 <html:form action="/search" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="searchFssa"/> 

<p align="left">  
            <input type="submit" value="Start Over" class="button"/>            
</p>
</html:form>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th>Inspection Details</th>
  </tr>
  <tbody>
   <c:forEach var="inspection" items="${INSPECTION_LIST.list}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
      <c:if test="${inspection.inspCompliance =='C' || inspection.inspCompliance =='COMPLIED'}"> 
      <span class="message" >
       <c:out value="${inspection.inspCompliance}"/>  Facility Id:<c:out value="${inspection.inspFacId}"/>&nbsp;Facility Name:<c:out value="${inspection.facilityName}"/><br/>
         Inspection Date:  <c:out value="${inspection.inspDateString}"/>&nbsp;Inspection Status: <c:out value="${inspection.inspCompliance}"/>&nbsp;;Inspection Type: <c:out value="${inspection.inspType}"/> <br/> 
        Inspector:   <c:out value="${inspection.inspectorName}"/>&nbsp;;Record Created Date:<c:out value="${inspection.recCreatedDateString}"/></span>
        Sprinkler: <c:out value="${inspection.inspSprinkler}"/>&nbsp; Alarm: <c:out value="${inspection.inspAlarm}"/>&nbsp;
        Remarks:<c:out value="${inspection.inspRemarks}"/>&nbsp;
        </c:if>
          <c:if test="${inspection.inspCompliance !='C' && inspection.inspCompliance!='COMPLIED'}">
       <span class="error" >
       Facility Id:<c:out value="${inspection.inspFacId}"/>&nbsp;Facility Name:<c:out value="${inspection.facilityName}"/><br/>
         Inspection Date:  <c:out value="${inspection.inspDateString}"/>&nbsp;Inspection Status: <c:out value="${inspection.inspCompliance}"/>&nbsp;;Inspection Type: <c:out value="${inspection.inspType}"/> <br/> 
        Inspector:   <c:out value="${inspection.inspectorName}"/>&nbsp;;Record Created Date:<c:out value="${inspection.recCreatedDateString}"/></span>
         Remarks:<c:out value="${inspection.inspRemarks}"/>&nbsp;
         </c:if>
        </br>
        <span class="message" ><c:out value="${inspection.inspUploadError}"/></span>
        <c:if test="${inspection.inspCompliance =='VIOLATION' ||inspection.inspCompliance =='COMPLIED'||inspection.inspCompliance =='ORDER'||inspection.inspCompliance =='NOTES'}">
         <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=idhs_fire_code_insp_report.rdf&p_inspection_id=<c:out value="${inspection.inspId}"/>">[view inspection report]</a>
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
          </c:if>
          
    </td>
         
      </tr>
    </c:forEach>
  </tbody>
</table>
</div>


<jsp:include page="/app/common/hsPageFooter.jsp"/> 