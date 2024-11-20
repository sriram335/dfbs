<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - HAZMAT DIVISION"/>
<c:set var="title" scope="request" value="Carrier Information"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Complete Carrier Information</h2> 
  <div class="styledBox">
 <html:form action="/carrier" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveCarrier"/> 
      <html:hidden property="carrierId"/> 
      <html:hidden property="applicationKey"/>
  <table cellspacing="0" class="noBorder" summary="OWNER FORM" style="width:100%;">   
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * carrier name:</th>
      <td>
       <html:text property="carrierName" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_CARRIER_ERROR.carrierName == 'ERROR'}">
          <br/>
          <span class="error">* please enter carrier name</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * carrier contact name:</th>
      <td>
       <html:text property="carrierContact" size="30" maxlength="30"/>
       <c:if test="${HAZMAT_CARRIER_ERROR.carrierContact == 'ERROR'}">
          <br/>
          <span class="error">* please enter carrier contact name</span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"   class="required"   > * carrier contact title:</th>
      <td>
       <html:text property="carrierTitle" size="50" maxlength="50"/>
       <c:if test="${HAZMAT_CARRIER_ERROR.carrierName == 'ERROR'}">
          <br/>
          <span class="error">* please enter carrier title </span> 
        </c:if>
       </td>
    </tr>
     <tr>
      <th scope="row"  class="required"    > * carrier contact phone:</th>
      <td>
       <html:text property="carrierPhone" size="10" maxlength="10"/>
       <c:if test="${HAZMAT_CARRIER_ERROR.carrierPhone == 'ERROR'}">
          <br/>
          <span class="error">* please enter carrier phone</span> 
        </c:if>
       </td>
    </tr>
    <tr>
      <th scope="row"     > carrier contact fax:</th>
      <td>
       <html:text property="carrierFax" size="10" maxlength="10"/>
       </td>
    </tr>
    <tr>
      <th scope="row"     > carrier contact email:</th>
      <td>
       <html:text property="carrierEmail" size="50" maxlength="50"/>
       </td>
    </tr>
     
    
     <tr>
     <th scope="row"     > </th>
    <td>

       
              <html:submit value="Save Carrier" styleClass="button" />
     
     </td>
     </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>