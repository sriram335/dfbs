 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Idhs Inspections" />
<c:set var="title" scope="request" value="Violation Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Update Violation information</h2> 
<div id="sideContentFluid">
<%--   <c:forEach var="violation" items="${sessionScope.INSPECTION_SELECTED.violations}" varStatus="i">
      <c:if test="${sessionScope.INSPECTOR_CURRENT == INSPECTION_SELECTED.inspInspectorId }"> 
       <a   href="/dfbs/idhsInspections/idhsViolation.do?method=updateIdhsViolation&violationId=<c:out value="${violation.vioId}"/>">
             [Edit:<c:out value="${violation.vioOrder}"/>]</a> &nbsp;
        </c:if>
     </c:forEach> --%>
</div>
<div id="leftContentFluid">
<a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
              [back to inspection]</a>
  <div class="styledBox">
 <html:form action="/idhsViolation" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveAllViolations"/>
       <html:hidden property="inspectionId"/>
      <html:hidden property="vioId"/>
 <table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
    <tr>
      <th style="font-size:medium;font-weight:bold" scope="row" style="width : 25%" > </th>
      <td style="font-size:medium;font-weight:bold">
       
      </td>
    </tr>
    
     <tr>
          <th scope="row"  class="required"    > * vio. number(print order):</th>
          <td>
           <html:text property="vioOrder" size="3" maxlength="3"/>
            </td>
      </tr>
      <tr>
      <th scope="row"   class="required"     >  * vio. code(max 100 characters):</th>
          <td>
            <html:textarea property="vioCode" cols="70"/>
                 <c:if test="${VIOLATION_ERROR.vioCode == 'ERROR'}">
              <br/><span class="error">* please enter code  </span> 
            </c:if>
            </td>
      </tr> 
     
    <tr>
      <th scope="row"   class="required"     >  *vio. description:</th>
          <td>
            <html:textarea property="vioDescription" rows="10" cols="70" />
                 <c:if test="${VIOLATION_ERROR.vioDescription == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
     <tr>
      <th scope="row"        >  vio. remarks:</th>
          <td>
            <html:textarea property="vioRemarks" rows="10" cols="70"/>
                
            </td>
      </tr> 
     
      
       <tr>
          <th scope="row"  class="required"    > * vio. to be corrected by Date:</th>
          <td>
           <html:text property="vioDate" size="10" maxlength="10"/>mm/dd/yyyy<a href="javascript:NewCal('vioDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
            <c:if test="${VIOLATION_ERROR.vioDate == 'ERROR'}">
              <br/><span class="error">* please enter violation notice date  </span> 
            </c:if>
            </td>
      </tr>
      <tr>
          <th scope="row"  class="required"    > * vio. complied on Date:</th>
          <td>
           <html:text property="vioCompDate" size="10" maxlength="10"/>mm/dd/yyyy<a href="javascript:NewCal('vioCompDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
           
            </td>
      </tr>
  </tbody>
        </table>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTION_SELECTED.inspInspectorId ||sessionScope.INSPECTOR_CURRENT == sessionScope.INSPECTOR_SUPERVISOR }"> 
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
        <p class="error">
        <c:out value="${requestScope.VIO_NAVIGATION}"/></br>
          <html:submit value="Previous" styleClass="button"
                       property="vioType"/>
          <html:submit value="Next" styleClass="button"
                       property="vioType"/>
          <html:submit value="Save" styleClass="button"
                       property="vioType"/>
           <html:submit value="Back To Inspection" styleClass="button"
                       property="vioType"/>
        </p>
      </c:if>
       </c:if>
      </html:form>   
      
</div>
 

