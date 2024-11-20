<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Magazine Permits" />
<c:set var="title" scope="request" value="Inspections Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add New Inspection</h2> 

<div id="leftContentFluid">
<a   href="/dfbs/magazine/inspection.do?method=inspectionList&permitNumber=<c:out value="${PERMIT_SELECTED.magIdNumber}"/>">
              [back to inspection list]</a>
  <div class="styledBox">
 <html:form action="/inspection" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInspection"/> 
      <html:hidden property="inspId"/> 
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
           <c:out value="${PERMIT_SELECTED.magIdNumber}"/>
            <html:hidden property="inspFacId"/> 
            </td>
         </tr>
     <tr>
     <tr>
          <th scope="row"  class="required"    > * Inspection Date:</th>
          <td>
           <html:text property="inspDate" size="10" maxlength="10"/>(mm/dd/yyyy)
                 <c:if test="${INSPECTION_ERROR.inspDate == 'ERROR'}">
              <br/><span class="error">* please enter inspection date  </span> 
            </c:if>
            </td>
      </tr>
      <c:if test="${FEE_STATUS == 'NO DUES'}">
      <tr>
      <th scope="row"   class="required"     >  * Permit Status: ( ensure that ATF permist is issued)</th>
      <td>
           <html:select property="inspCompliance" >
          <html:option value="">Please Select</html:option>
          <html:option value="Approved">Approved</html:option>
          <html:option value="Denied">Denied</html:option>
          <html:option value="No ATF permit">No ATF permit</html:option>
           </html:select>
        <c:if test="${INSPECTION_ERROR.inspCompliance == 'ERROR'}">
              <br/><span class="error">* please enter inspection type </span> 
            </c:if>
       </td>
      </tr> 
    </c:if>
    <c:if test="${FEE_STATUS == 'DUE'}">
    <tr>
      <th scope="row"        > Payment process pending </th>
      <td>
    Permit fee not received, you can not enter the inspection, or permit can not be printed. Contact <c:out value="${sessionScope.APPLICATION_CONTACTS.contact1Name}"/> for questions.
    </td>
      </tr> 
    </c:if>
        <tr>
      <th scope="row"   class="required"     >  * inspector:</th>
      <td>
          <html:select property="inspInspectorId" styleId="SELECT_INSPECTOR_ID" onchange="setSelectValue('SELECT_INSPECTOR_ID','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSPECTORS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspInspectorId == 0}">
              <br/><span class="error">* please enter inspector  </span> 
            </c:if>
       </td>
      </tr> 
      
       <tr>
          <th scope="row"     >  inspector remarks:</th>
          <td>
            <html:textarea property="inspRemarks" />
           
            </td>
       </tr>
      
  </tbody>
        </table>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">        
      <c:if test="${INSPECTION_SELECTED.inspId > 0}">
      <a   href="/dfbs/magazine/violation.do?method=newViolation&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
              [Add violations]</a>
              
     </c:if>
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
  </c:if>
 
    </html:form>   
</div>
 </div>

