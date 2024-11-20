 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<h2>Search Results</h2>
<c:if test="${RESULTS_FLAG == 'ELEVATORS'}">
<b><u>Elevators:</u></b></br>
  <c:forEach var="elevator" items="${ELEVATOR_SEARCH_LIST.list}" varStatus="i"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Elevator State Number:<c:out value="${elevator.resultId}"/>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
            <c:out value="${elevator.facilityName}" /><br/>
            <c:out value="${elevator.resultAddress1}" />,
            <c:out value="${elevator.resultAddress2}" /></br>
            <c:out value="${elevator.resultCity}" />,<c:out value="${elevator.resultZip}" />,<c:out value="${elevator.resultCounty}" />
            <br/>
          </p>
   
<%--  <c:if test="${elevator.searchSequence == null}">
 <a   href="/dfbs/idhsInspections/search.do?method=link&linkId=<c:out value="${elevator.resultId}"/>">Link </a>&nbsp;
 </c:if>
 <c:if test="${elevator.searchSequence != null}">
 <a   href="/dfbs/idhsInspections/search.do?method=link&linkId=<c:out value="${elevator.resultId}"/>">De-Link </a>&nbsp;
 <p class="message">
 Verified Result! </p>
 --for later use
 <a   href="/dfbs/bpv/bpv.do?method=viewBpv=<c:out value="${bpv.resultId}"/>"> <c:out value="${bpv.resultId}"/></a>
 </c:if> --%>
 <a   href="/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId=<c:out value="${elevator.resultId}"/>"> Go to Inspection(s) </a>
    </div>
  </c:forEach>
 </c:if> 
 <c:if test="${RESULTS_FLAG == 'BPV'}">
  <b><u>Boilers and Pressure Vessels:</u></b><br/>
  <c:forEach var="bpv" items="${BPV_SEARCH_LIST.list}" varStatus="i"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Boiler State Number:<c:out value="${bpv.resultId}"/> 
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${bpv.facilityName}" /><br/>
            <c:out value="${bpv.resultAddress1}" />,
            <c:out value="${bpv.resultAddress2}" /><br/>
            <c:out value="${bpv.resultCity}" />,<c:out value="${bpv.resultZip}" />,<c:out value="${bpv.resultCounty}" />
            </br>
          </p>
           <a   href="/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId=<c:out value="${bpv.resultId}"/>"> Go to Inspection(s) </a>
      </div>
 
  </c:forEach>
</c:if>
<c:if test="${RESULTS_FLAG == 'AEPERMITS'}">
  <b><u>AE Permits:</u></b></br>
   <c:forEach var="aePermits" items="${AE_SEARCH_LIST.list}" varStatus="a"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          AE ID Number:<a   href="/dfbs/aepermits/permit.do?method=updatePermit&permitNumber=<c:out value="${aePermits.resultId}"/>"><c:out value="${aePermits.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:           <br/>
           <c:out value="${aePermits.facilityName}" /><br/>
            <c:out value="${aePermits.resultAddress1}" />,
            <c:out value="${aePermits.resultAddress2}" /></br>
            <c:out value="${aePermits.resultCity}" />,<c:out value="${aePermits.resultZip}" />,<c:out value="${aePermits.resultCounty}" />
            </br>
          </p>
          <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${aePermits.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
           <a   href="/dfbs/idhsInspections/search.do?method=inactivateFacility&idNumber=<c:out value="${aePermits.resultId}"/>"> Inactivate</a>&nbsp;&nbsp;
            <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${aePermits.resultId}"/>">
             [view & print  permit]</a> 
           </div>
           
  </c:forEach>
 </c:if>
 <c:if test="${RESULTS_FLAG == 'DAYCARE'}">
  <b><u>Child Care(s):</u></b></br>
   <c:forEach var="dayCare" items="${DAYCARE_SEARCH_LIST.list}" varStatus="d"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          <c:out value="${dayCare.resultType}" /> ID:<a   href="https://oas.dhs.in.gov/dfbs/childCare/childcare.do?method=updatePermit&permitNumber=<c:out value="${dayCare.resultId}"/>"><c:out value="${dayCare.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address: <br/>
           <c:out value="${dayCare.facilityName}" /><br/>
            <c:out value="${dayCare.resultAddress1}" />,
            <c:out value="${dayCare.resultAddress2}" /></br>
            <c:out value="${dayCare.resultCity}" />,<c:out value="${dayCare.resultZip}" />,<c:out value="${dayCare.resultCounty}" />
            </br>
          </p>
      <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${dayCare.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
           <a   href="/dfbs/idhsInspections/search.do?method=inactivateFacility&idNumber=<c:out value="${dayCare.resultId}"/>"> Inactivate</a>&nbsp;&nbsp;
     </div>
 
  </c:forEach>
</c:if>
<c:if test="${RESULTS_FLAG == 'SCHOOL'}">
  <b><u>Schools:</u></b></br>
  <c:forEach var="school" items="${SCHOOL_SEARCH_LIST.list}" varStatus="s"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          School ID Number:<c:out value="${school.resultId}"/> 
          <c:out value="${school.facilityName}" />
        </h3>
          <p class="listingInfo">
          Location Address:
           <c:out value="${school.facilityName}" /></br>
            <c:out value="${school.resultAddress1}" />,
            <c:out value="${school.resultAddress2}" /></br>
            <c:out value="${school.resultCity}" />,<c:out value="${school.resultZip}" />,<c:out value="${school.resultCounty}" />
            </br>
          </p>
          <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${school.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
    </div>
 
  </c:forEach>
</c:if>
<c:if test="${RESULTS_FLAG == 'HOSPITAL'}">
<b><u>Health Care(s):</u></b></br>
<c:forEach var="hospital" items="${HOSPITAL_SEARCH_LIST.list}" varStatus="h"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          <c:out value="${hospital.resultType}" /> ID Number:<c:out value="${hospital.resultId}"/> 
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${hospital.facilityName}" /><br/>
            <c:out value="${hospital.resultAddress1}" /></br>
            <c:out value="${hospital.resultAddress2}" /></br>
            <c:out value="${hospital.resultCity}" />,<c:out value="${hospital.resultZip}" />,<c:out value="${hospital.resultCounty}" />
            </br>
          </p>
       <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${hospital.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
    </div>
 
  </c:forEach> 
  </c:if>
  <c:if test="${RESULTS_FLAG == 'OTHER'}">
  <b><u>Others:</u></b></br>
<c:forEach var="other" items="${OTHER_SEARCH_LIST.list}" varStatus="o"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Other ID Number:<c:out value="${other.resultId}"/> 
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${other.facilityName}" /><br/>
            <c:out value="${other.resultAddress1}" /></br>
            <c:out value="${other.resultAddress2}" /></br>
            <c:out value="${other.resultCity}" />,<c:out value="${other.resultZip}" />,<c:out value="${other.resultCounty}" />
            </br>
          </p>
       <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${other.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
    <a   href="/dfbs/idhsInspections/search.do?method=inactivateOther&idNumber=<c:out value="${other.resultId}"/>"> Inactivate</a>&nbsp;&nbsp;
   </div>
  </c:forEach>
  </c:if>
  <c:if test="${RESULTS_FLAG == 'PLAN'}">
  <b><u>Plan Review:</u></b></br>
  <c:forEach var="plan" items="${PLAN_SEARCH_LIST.list}" varStatus="j"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          SBC Project Number:<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=plan_design_release_for_web.rdf&p_sbc_number=<c:out value="${plan.resultId}"/>"><c:out value="${plan.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${plan.facilityName}" /><br/>
            <c:out value="${plan.resultAddress1}" />,
            <c:out value="${plan.resultAddress2}" /></br>
            <c:out value="${plan.resultCity}" />,<c:out value="${plan.resultZip}" />,<c:out value="${plan.resultCounty}" />
            </br>
          </p>
     <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${plan.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
  </br>
     <a   href="/dfbs/idhsInspections/search.do?method=FinalInspectionDone&projectNumber=<c:out value="${plan.resultId}"/>"> Final Inspection Done</a>&nbsp;&nbsp;
      
     </div>
    
  </c:forEach>
</c:if>
 <c:if test="${RESULTS_FLAG == 'FIREWORKS_RETAIL'}">
  <b><u>Fireworks Retail:</u></b></br>
  <c:forEach var="fireworksRetail" items="${FIREWORKS_RETAIL_SEARCH_LIST.list}" varStatus="j"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Identification Number:<a   href="/dfbs/fireworks/permit.do?method=viewPermit&permitNumber=<c:out value="${fireworksRetail.resultId}"/>&permitType=Consumer"><c:out value="${fireworksRetail.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${fireworksRetail.facilityName}" /><br/>
            <c:out value="${fireworksRetail.resultAddress1}" />,
            <c:out value="${fireworksRetail.resultAddress2}" /></br>
            <c:out value="${fireworksRetail.resultCity}" />,<c:out value="${fireworksRetail.resultZip}" />,<c:out value="${fireworksRetail.resultCounty}" />
            </br>
      <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${fireworksRetail.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
 <a   href="/dfbs/fireworks/main.do?method=consumerToPrint&permitNumber=<c:out value="${fireworksRetail.resultId}"/>">
             [view & print  permit]</a> 
  </div>
  </c:forEach>
</c:if>
 <c:if test="${RESULTS_FLAG == 'FIREWORKS_WHOLESALE'}">
  <b><u>Fireworks Wholesale:</u></b></br>
  <c:forEach var="fireworksWholesale" items="${FIREWORKS_WHOLESALE_SEARCH_LIST.list}" varStatus="j"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Identification Number:<a   href="/dfbs/fireworks/permit.do?method=viewPermit&permitNumber=<c:out value="${fireworksWholesale.resultId}"/>&permitType=WholeSale"><c:out value="${fireworksWholesale.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${fireworksWholesale.facilityName}" /><br/>
            <c:out value="${fireworksWholesale.resultAddress1}" />,
            <c:out value="${fireworksWholesale.resultAddress2}" /></br>
            <c:out value="${fireworksWholesale.resultCity}" />,<c:out value="${fireworksWholesale.resultZip}" />,<c:out value="${fireworksWholesale.resultCounty}" />
            </br>
          </p>      <a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${fireworksWholesale.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
       <a   href="/dfbs/fireworks/main.do?method=wholesaleToPrint&permitNumber=<c:out value="${fireworksWholesale.resultId}"/>">
             [view & print  permit]</a> 
  </div>
  </c:forEach>
</c:if>
<c:if test="${RESULTS_FLAG == 'UST'}">
  <b><u>UST:</u></b></br>
  <c:forEach var="ust" items="${UST_SEARCH_LIST.list}" varStatus="j"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Identification Number:<a   href="/dfbs/plan/plan.do?method=viewPlan=<c:out value="${ust.resultId}"/>"><c:out value="${ust.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${ust.facilityName}" /><br/>
            <c:out value="${ust.resultAddress1}" />,
            <c:out value="${ust.resultAddress2}" /></br>
            <c:out value="${ust.resultCity}" />,<c:out value="${ust.resultZip}" />,<c:out value="${ust.resultCounty}" />
            </br>
          </p><a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${ust.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
 </div>
  </c:forEach>
</c:if>
<c:if test="${RESULTS_FLAG == 'NEW_INSPECTIONS'}">
  <b><u>Complaint /Unmatched Facility inspections:</u></b></br>
  <c:forEach var="newInsp" items="${NEW_INSP_SEARCH_LIST.list}" varStatus="j"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Identification Number:<a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${newInsp.resultId}"/>"><c:out value="${newInsp.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${newInsp.facilityName}" /><br/>
            <c:out value="${newInsp.resultAddress1}" />,
            <c:out value="${newInsp.resultAddress2}" /></br>
            <c:out value="${newInsp.resultCity}" />,<c:out value="${newInsp.resultZip}" />,<c:out value="${newInsp.resultCounty}" /></br>
            </br>
          </p><a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${newInsp.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
  </div>
  </c:forEach>
</c:if>
<c:if test="${RESULTS_FLAG == 'NEW_INSPECTIONS_IND_MOBILE'}">
  <b><u>Industrial/Mobile structures inspections:</u></b></br>
  <c:forEach var="newInsp" items="${NEW_INSP_IND_MOBILE_SEARCH_LIST.list}" varStatus="j"  >
   
      <div class="listing" align="left">
          <h3 style="margin-bottom:5px;">
          Identification Number:<a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${newInsp.resultId}"/>"><c:out value="${newInsp.resultId}"/> </a>
        </h3>
          <p class="listingInfo">
          Location Address:<br/>
           <c:out value="${newInsp.facilityName}" /><br/>
            <c:out value="${newInsp.resultAddress1}" />,
            <c:out value="${newInsp.resultAddress2}" /></br>
            <c:out value="${newInsp.resultCity}" />,<c:out value="${newInsp.resultZip}" />,<c:out value="${newInsp.resultCounty}" /></br>
            </br>
          </p><a   href="/dfbs/idhsInspections/search.do?method=searchById&idNumber=<c:out value="${newInsp.resultId}"/>"> Go to Inspections</a>&nbsp;&nbsp;
  </div>
  </c:forEach>
</c:if>