<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Entertainment Permits"/>
<c:set var="title" scope="request" value="Entertainment Permits"/>
<c:set var="step" scope="request" value="2"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
 <c:out value="${DFBS_PERMIT.permitNumber}"/>
  <h2>Add Special Endorsement to Annual Permit</h2>
  <div id="rightContentFluid">
    <p class="control2">
      <html:link page="/application.do?method=step2">cancel</html:link>
    </p>
  </div>
  <div id="leftContentFluid">
 <html:form action="/special" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveSpecial"/>
       <input type="hidden" name="key"  value="<c:out value='${param.key}' />" />
       <input type="hidden" name="permitNumber"  value="<c:out value='${permit.permitNumber}' />" />
      <html:hidden property="permitNumber"/> 
      
      
  <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">  
          
    <tr>
      <td style="font-size:medium;font-weight:bold">NEW</td>
    </tr>
     <tr>
      <th >  <c:if test="${EDIT_SPECIAL_SELECTED.permitType != 'Y' && UPDATE_SPECIAL_SELECTED.permitType != 'SU'}">
           <input type="checkbox" name="permitType"  class="switch"  value="Y"/> 
      </c:if>
       <c:if test="${EDIT_SPECIAL_SELECTED.permitType == 'Y' || UPDATE_SPECIAL_SELECTED.permitType == 'SU'}">
              <input type="checkbox" name="permitType"  class="switch"  value="Y" checked/>
       </c:if>
     </th>
      <td style="color:red">
     <b>Is any stage to be used</b> 
      </td>
    </tr> 
    <tr>
     <th scope="row" class="required">*event date:</th>
      <td>
        <html:text property="eventDate" size="10" maxlength="10"/>(mm/dd/yyyy)
       <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.eventDate == 'ERROR'}">
          <br/>
          <span class="error">* please specify event date</span> 
        </c:if> 
      </td>
    </tr>

    <tr>
      <th scope="row" class="required">*event time:</th>
      <td>
        <html:text property="eventTime" size="15" maxlength="15"/>
        <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.eventTime == 'ERROR'}">
          <br/>
          <span class="error">* please specify event time</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*event name:</th>
      <td>
        <html:text property="eventName" size="30" maxlength="30"/>
       <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.eventName == 'ERROR'}">
          <br/>
          <span class="error">* please specify event name</span> 
        </c:if> 
      </td>
    </tr>
    <tr>
      <th scope="row"  class="required" >*maximum occupancy:</th>
      <td>
        <html:text property="maximumOccupancy" size="30" maxlength="30"/>
         <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.eventName == 'ERROR'}">
          <br/>
          <span class="error">* please specify maximum occupancy</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required" >*contact person:</th>
      <td>
        <html:text property="contactPerson" size="30" maxlength="30"/>
         <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.contactPerson == 'ERROR'}">
          <br/>
          <span class="error">*  please specify contact person</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"  class="required" >*contact phone:</th>
      <td>
        <html:text property="contactPhone" size="10" maxlength="10"/>
         <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.contactPhone == 'ERROR'}">
          <br/>
          <span class="error">* please specify contact phone in correct format</span> 
        </c:if> 
       </td>
    </tr>
    <tr>
      <th scope="row"   >contact fax:</th>
      <td>
        <html:text property="contactFax" size="10" maxlength="10"/>
        </td>
    </tr>
     <tr>
      <th scope="row"   >contact email:</th>
      <td>
        <html:text property="contactEmail" size="30" maxlength="30"/>
        </td>
    </tr>
     <tr>
              <th scope="row" class="required" style="width:30%">*fee Status:</th>
              <td>
                <html:radio property="feeExempt" value="E" styleClass="switch" />Fee Exempt ( Needs IRS Letter)
                <br/>
                <html:radio property="feeExempt" value="N" styleClass="switch" />Non Exempt
                <br/>
                 <c:if test="${DFBS_PERMIT_SPECIAL_ERROR.feeExempt == 'ERROR'}">
                 <br/>
                <span class="error">* please specify fee exempt</span> 
                </c:if>
                 <br/>
              </td>
    </tr>
    </tr>
     <tr>
      <th scope="row"   >comments:</th>
      <td>
        <html:text property="comments" size="100" maxlength="100"/>
        </td>
    </tr>
    
 </tbody>
 
</table>

 <p>
          <html:submit value="save to your application" styleClass="button" />
 </p>
 
 </html:form>
    
  </div>
  <div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp"/>

