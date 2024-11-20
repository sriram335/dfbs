<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Idhs Inspections" />
<c:set var="title" scope="request" value="Update Inspection" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<h2>Approve AE Permits</h2> 
<div id="sideContentFluid">
<table cellspacing="0" style="width:25%;" summary="sales" >
 

  <tr>
    <th >Approve Permits</th>
  </tr>
  <tbody>
   <c:forEach var="result" items="${AE_APPROVAL_LIST.list}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
            <c:out value="${result.resultId}"/> 
             <c:out value="${result.resultAddress1}" />,
            <c:out value="${result.resultAddress2}" /><br/>

            <c:if test="${result.resultType == 'AE' && result.resultCity =='APPROVED'}">
            <c:forEach var="inspDate" items="${INSPECTION_LIST_AE_APPROVAL.list}" varStatus="i">
            <a   href="/dfbs/idhsInspections/AEPermitApproval.do?method=approve&inspFacId=<c:out value="${result.resultId}"/>&inspDate=<c:out value="${inspDate.inspDateString}"/>"> Approve with <c:out value="${inspDate.inspDateString}"/> </a>
             </c:forEach>
              <c:if test="${INSPECTION_SELECTED.inspDate != null && result.resultCity =='APPROVED'}">
             <a   href="/dfbs/idhsInspections/AEPermitApproval.do?method=approve&inspFacId=<c:out value="${result.resultId}"/>&inspDate=<c:out value="${INSPECTION_SELECTED.inspDateString}"/>"> Approve with <c:out value="${INSPECTION_SELECTED.inspDateString}"/> </a>
            </c:if>
             </c:if>
             <c:if test="${result.resultType != 'AE' && result.resultCity =='APPROVED'}">
             (<c:out value="${INSPECTION_SELECTED.inspFacId}"/>) Speicial
             <a   href="/dfbs/idhsInspections/AEPermitApproval.do?method=approve&inspFacId=<c:out value="${result.resultId}"/>"> Approve </a>
            </c:if>
             <c:if test="${result.resultCity == null || result.resultCity =='NEW'}">
             <c:out value="${result.resultAddress1}" />
             Contact FM office, FM office approval pending!
              </c:if>
         </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
</div> 
<div id="leftContentFluid">
<a   href="/dfbs/idhsInspections/inspection.do?method=inspectionStart&facilityId=<c:out value="${INSPECTION_SEARCH_ID}"/>">
              [back to inspection list]</a></br>
 <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnpdf&report=fire_entertainment_permit_new.rdf&p_id=<c:out value="${sessionScope.INSPECTION_SELECTED.inspFacId}"/>">
             [view & print  permit( works after approval)]</a> 
  <div class="styledBox"> 
 <html:form action="/AEPermitApproval" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveFacUse"/> 
      <html:hidden property="inspFacId"/> 
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
          <th scope="row"    > * facility ID:</th>
          <td>
          <c:out value="${INSPECTION_SELECTED.inspFacId}"/>&nbsp;
            </td>
           </tr>
      <tr>
          <th scope="row"    > * facility use Bar/Night Club:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facBar != 'Y'}">
               <input type="checkbox" name="facBar"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facBar == 'Y'}">
              <input type="checkbox" name="facBar"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>
        <tr>
          <th scope="row"    > * facility Casinos / Off track betting / other Gambling venues:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facCasino != 'Y'}">
               <input type="checkbox" name="facCasino"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facCasino == 'Y'}">
              <input type="checkbox" name="facCasino"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>
      <tr>
          <th scope="row"    > * facility Haunted Houses:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facHaunted != 'Y'}">
               <input type="checkbox" name="facHaunted"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facHaunted == 'Y'}">
              <input type="checkbox" name="facHaunted"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>
        <tr>
          <th scope="row"    > * facility Arena / stadium facilities:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.fac_arena != 'Y'}">
               <input type="checkbox" name="fac_arena"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.fac_arena == 'Y'}">
              <input type="checkbox" name="fac_arena"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>
         <tr>
          <th scope="row"    > * facility Theatres:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.fac_thetre != 'Y'}">
               <input type="checkbox" name="fac_thetre"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.fac_thetre == 'Y'}">
              <input type="checkbox" name="fac_thetre"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>
         <tr>
          <th scope="row"    > * facility Schools:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.fac_school != 'Y'}">
               <input type="checkbox" name="fac_school"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.fac_school == 'Y'}">
              <input type="checkbox" name="fac_school"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
        <tr>
          <th scope="row"    > * facility Swimming Pools:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facSwimPool != 'Y'}">
               <input type="checkbox" name="facSwimPool"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facSwimPool == 'Y'}">
              <input type="checkbox" name="facSwimPool"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
        <tr>
          <th scope="row"    > * facility Banquet Facilities:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facBanquet != 'Y'}">
               <input type="checkbox" name="facBanquet"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facBanquet == 'Y'}">
              <input type="checkbox" name="facBanquet"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
        <tr>
          <th scope="row"    > * facility Bowling Alleys:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facBowling != 'Y'}">
               <input type="checkbox" name="facBowling"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facBowling == 'Y'}">
              <input type="checkbox" name="facBowling"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
    <tr>
          <th scope="row"    > * facility Pool Halls:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facBilliardPool != 'Y'}">
               <input type="checkbox" name="facBilliardPool"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facBilliardPool == 'Y'}">
              <input type="checkbox" name="facBilliardPool"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>  
     <tr>
          <th scope="row"    > * facility Restaurant:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facRestaurant != 'Y'}">
               <input type="checkbox" name="facRestaurant"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facRestaurant == 'Y'}">
              <input type="checkbox" name="facRestaurant"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
     <tr>
          <th scope="row"    > * facility Racetracks:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facRacTrack != 'Y'}">
               <input type="checkbox" name="facRacTrack"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facRacTrack == 'Y'}">
              <input type="checkbox" name="facRacTrack"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
     <tr>
          <th scope="row"    > * facility VFW / American Legion:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facAmLegion != 'Y'}">
               <input type="checkbox" name="facAmLegion"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facAmLegion == 'Y'}">
              <input type="checkbox" name="facAmLegion"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
     <tr>
          <th scope="row"    > * facility Private Clubs:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facPrivClub != 'Y'}">
               <input type="checkbox" name="facPrivClub"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facPrivClub == 'Y'}">
              <input type="checkbox" name="facPrivClub"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr> 
       <tr>
          <th scope="row"    > * facility Fair / Festival:</th>
          <td>
               <c:if test="${AE_FACILITY_USE.facFairFestival != 'Y'}">
               <input type="checkbox" name="facFairFestival"  class="switch"  value="Y"/>
               </c:if>
              <c:if test="${AE_FACILITY_USE.facFairFestival == 'Y'}">
              <input type="checkbox" name="facFairFestival"  class="switch"  value="Y" checked/>
              </c:if>
            </td>
           </tr>   
     <tr>
          <th scope="row"    > * facility other / describe:</th>
          <td>
               <html:text property="facOther" size="50" maxlength="100"/>
            </td>
           </tr>   
   
  </tbody>
        </table>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">        
      
        <p>
        <c:if test="${sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId }">
          <html:submit value=" Save " styleClass="button"/>
        </c:if>
        </p>
       
  </c:if>
    </html:form>   
</div>             
              
</div>

 