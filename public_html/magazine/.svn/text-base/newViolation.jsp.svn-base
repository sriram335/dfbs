<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Entertainment Permits" />
<c:set var="title" scope="request" value="Violation Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<jsp:include page="/aepermits/application/lookUpStdViolation.jsp"/>
</div>
<h2>Add New violation</h2> 
<div id="leftContentFluid">
<a   href="/dfbs/aepermits/inspection.do?method=updateInspection&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
              [back to inspection]</a>
  <div class="styledBox">
 <html:form action="/violation" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveViolation"/> 
      <html:hidden property="inspectionId"/>
      
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
          <th scope="row"  class="required"    > * violation number(print order):</th>
          <td>
           <html:text property="vioOrder" size="3" maxlength="3"/>
                
            </td>
       </tr>
      <tr>
      <th scope="row"   class="required"     >  * violation code:</th>
          <td>
           <html:text property="vioCode" size="30" maxlength="30"/>
            <c:if test="${VIOLATION_ERROR.vioCode == 'ERROR'}">
             <span class="error">* please enter code  </span> 
            </c:if>
            </td>
      </tr> 
     
    <tr>
      <th scope="row"   class="required"     >  *violation description:</th>
          <td>
            <html:textarea property="vioDescription" />
                 <c:if test="${VIOLATION_ERROR.vioDescription == 'ERROR'}">
              <br/><span class="error">* please enter description  </span> 
            </c:if>
            </td>
      </tr> 
     <tr>
      <th scope="row"        >  violation remarks:</th>
          <td>
            <html:textarea property="vioRemarks" />
                
            </td>
      </tr> 
     
      
       <tr>
          <th scope="row"  class="required"    > * violation to be corrected by Date:</th>
          <td>
           <html:text property="vioCorDate" size="10" maxlength="10"/>mm/dd/yyyy
            <c:if test="${INSPECTION_ERROR.vioCorDate == 'ERROR'}">
              <br/><span class="error">* please enter violation notice date  </span> 
            </c:if>
            </td>
      </tr>
      <tr>
          <th scope="row"     >  violation  complied by Date:</th>
          <td>
           <html:text property="vioCompDate" size="10" maxlength="10"/>mm/dd/yyyy
            
            </td>
      </tr>
  </tbody>
        </table>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
      </c:if>
    </html:form>   
</div>
 </div>

