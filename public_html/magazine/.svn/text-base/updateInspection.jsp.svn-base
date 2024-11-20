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
<h2>Update Inspection Information</h2> 

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
           <c:out value="${INSPECTION_SELECTED.inspFacId}"/>
            <html:hidden property="inspFacId"/> 
            </td>
          </th>
        </tr>
     <tr>
     <tr>
          <th scope="row"  class="required"    > * inspection Date:</th>
          <td>
           <html:text property="inspDate" size="10" maxlength="10"/>
                 <c:if test="${INSPECTION_ERROR.inspDate == 'ERROR'}">
              <br/><span class="error">* please enter inspection date  </span> 
            </c:if>
            </td>
          </th>
      </tr>
      <tr>
      <th scope="row"   class="required"     >  * inspection type:</th>
      <td>
          <html:select property="inspType" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSPECTION_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspType == 'ERROR'}">
              <br/><span class="error">* please enter inspection type </span> 
            </c:if>
       </td>
      </tr> 
     <tr>
          <th scope="row"  class="required"    > * inspection district:</th>
          <td>
           <html:text property="inspDistrict" size="12" maxlength="12"/>
                 <c:if test="${INSPECTION_ERROR.inspDistrict == 'ERROR'}">
              <br/><span class="error">* please enter inspection district  </span> 
            </c:if>
            </td>
          </th>
      </tr>
      <tr>
          <th scope="row"     > * occupant load:</th>
          <td>
           <html:text property="inspOccLoad" size="12" maxlength="12"/>
                
            </td>
          </th>
      </tr>  
  <%--    <tr>
      <th scope="row"   class="required"     >  * alaram:</th>
      <td>
          <html:select property="inspAlaram" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ALARAM_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspAlaram == 'ERROR'}">
              <br/><span class="error">* please enter alaram value  </span> 
            </c:if>
       </td>
      </tr> 
       <tr>
      <th scope="row"   class="required"     >  * sprinkler:</th>
      <td>
          <html:select property="inspSprinkler" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="ALARAM_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspSprinkler == 'ERROR'}">
              <br/><span class="error">* please enter sprinkler value  </span> 
            </c:if>
       </td>
      </tr> --%>
       <tr>
      <th scope="row"   class="required"     >  * complied violation:</th>
      <td>
          <html:select property="inspCompliance" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','OPTION_VALUE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="COMPLIED_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspCompliance == 'ERROR'}">
              <br/><span class="error">* please enter complied violation  </span> 
            </c:if>
       </td>
      </tr> 
        <tr>
      <th scope="row"   class="required"     >  * inspector:</th>
      <td>
          <html:select property="inspInspectorId" styleId="SELECT_INSPECTOR_ID" onchange="setSelectValue('SELECT_INSPECTOR_ID','INSPECTOR_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSPECTORS_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSPECTION_ERROR.inspInspectorId == 0}">
              <br/><span class="error">* please enter inspector  </span> 
            </c:if>
       </td>
      </tr> 
       <tr>
          <th scope="row"  class="required"    > * violation letter Date:</th>
          <td>
           <html:text property="inspVioPrintDate" size="10" maxlength="10"/>
            <c:if test="${INSPECTION_ERROR.inspVioPrintDate == 'ERROR'}">
              <br/><span class="error">* please enter violation print date  </span> 
            </c:if>
            </td>
          </th>
      </tr>
       <tr>
          <th scope="row"     >  inspector remarks:</th>
          <td>
            <html:textarea property="inspRemarks"  />
           
            </td>
          </th>
      </tr>
  </tbody>
 </table>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">   
   <a   href="/dfbs/magazine/violation.do?method=newViolation">
             [Add a new violation]</a>
</c:if>
<table cellspacing="0" style="width:100%;" summary="sales" >
 

  <tr>
    <th >code</th>
    <th style="width:75%;">description</th>
    <th >remarks</th>
    <th >violation to be corrected by date</th>
    <th >complied date</th> 
  </tr>
  <tbody>
  
   <c:forEach var="violation" items="${sessionScope.INSPECTION_SELECTED.violations}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td>
       <c:out value="${violation.vioCode}"/>&nbsp; <br/> 
       <a   href="/dfbs/magazine/violation.do?method=updateViolation&violationId=<c:out value="${violation.vioId}"/>">
             [Edit this violation]</a>
          </td>
        <td>
        <c:out value="${violation.vioDescription}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioRemarks}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioCorDateString}"/>&nbsp; <br/> 
        </td>
        <td>
        <c:out value="${violation.vioCompDateString}"/>&nbsp; <br/> 
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
</c:if>
    </html:form>   
</div>
 
