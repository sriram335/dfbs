  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<c:set var="module" scope="request" value="Entertainment Permits" />
<c:set var="title" scope="request" value="Violation Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Add New violation</h2> 
<div id="sideContentFluid">
 <b><u>Click code to add:</u></b>
 <c:forEach var="violation" items="${VIO_CODE_LIST.list}" varStatus="i"  >
      <div class="listing" align="left">
           <a   href="/dfbs/idhsInspections/idhsViolation.do?method=addVioCode&vioCode=<c:out value="${violation.vioCode}" />">
              <c:out value="${violation.vioCode}" /></a><br/>
           </div>
  </c:forEach>
</div>
<div id="leftContentFluid">
<a   href="/dfbs/idhsInspections/idhsInspection.do?method=updateIdhsInspection&inspectionId=<c:out value="${INSPECTION_SELECTED.inspId}"/>">
              [back to inspection]</a>
  <div class="styledBox">
 <html:form action="/idhsViolation" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveIdhsViolation"/> 
      <html:hidden property="inspectionId"/>
      
<table cellspacing="0" class="noBorder" style="width :100%">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >_____________________</th>
    <td style="font-size:medium;font-weight:bold;color:white">
       ________________________________________________________________
      </td>
    </tr>
    <tr>
      <th style="font-size:medium;font-weight:bold" scope="row" > </th>
      <td style="font-size:medium;font-weight:bold">
       
      </td>
    </tr>
    
     <tr>
          <th scope="row"  class="required"    > * vio. number(print order):</th>
          <td>
           <html:text property="vioOrder" size="3" maxlength="3"/>
                
            </td>
          </th>
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
            <html:textarea property="vioDescription" rows="10" cols="70"/>
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
          </th>
      </tr>
      <tr>
          <th scope="row"     >  vio.complied on Date:</th>
          <td>
           <html:text property="vioCompDate" size="10" maxlength="10"/>mm/dd/yyyy<a href="javascript:NewCal('vioCompDate','MMDDYYYY')"><img src="../img/clearbits/cal.gif" width="16" height="16" border="1" alt="Pick a date"></a>
            
            </td>
          </th>
      </tr>
  </tbody>
        </table>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
      </c:if>
    </html:form>   
</div>
 

