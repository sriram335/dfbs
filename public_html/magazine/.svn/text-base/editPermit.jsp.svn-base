<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Edit magazine Information</h2> 
<a   href="/dfbs/magazine/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/magazine/start.do?method=renewBy">
              [back to search]</a>
<a   href="/dfbs/magazine/ownerListDisplay.do?method=renew&ownerId=<c:out value="${OWNER_SELECTED.ownerId}"/>&idNumber=<c:out value="${PERMIT_SELECTED.magIdNumber}"/>">
              [back to owner]</a>
<div id="sideContentFluid">
            
  <jsp:include page="shoppingCart.jsp"/>
  </div>
<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="checkOut"/> 
      <html:hidden property="magIdNumber"/> 
      <html:hidden property="magIdNumber"/> 
       <html:hidden property="magAddress1"/>
       <html:hidden property="magAddress2"/>
       <html:hidden property="magCity"/>
       <html:hidden property="magState"/>
       <html:hidden property="magZip"/>
       <html:hidden property="magCounty"/> 
       <html:hidden property="magExpType"/>
        <html:hidden property="magMinQuantity"/>
         <html:hidden property="magMagType"/>
         <html:hidden property="magDirections"/>
         <html:hidden property="magPermitNumber"/>
         <html:hidden property="magSignedDate"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th scope="row" style="width : 50%"> magazine id :</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${PERMIT_SELECTED.magIdNumber}"/>
         <html:hidden property="magIdNumber"/>
       </td>
      </tr>
      <tr>
          <th scope="row"      > magazine number/id :</th>
          <td>
           <html:text property="magSiteName" size="30" maxlength="30"/> (as used by your firm /company)
            </td>
        </tr>
        <tr>
          <th scope="row"  class="required"    > * ATF License:</th>
          <td>
           <html:text property="magAtfLicense" size="30" maxlength="30"/>
                 <c:if test="${PERMIT_ERROR.magAtfLicense == 'ERROR'}">
              <br/><span class="error">* please ATF License  </span> 
            </c:if>
            </td>
        </tr>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
   
    <tr>
      <th scope="row">street 1:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.magAddress1}"/>
        
       </td>
      </tr>
    <tr>
      <th scope="row">street 2:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.magAddress2}"/>
        
      </td>
    </tr>
    <tr>
      <th scope="row" >city:</th>
      <td>
        <c:out value="${PERMIT_SELECTED.magCity}"/>
         
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magState}"/>
         
       </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
       <c:out value="${PERMIT_SELECTED.magZip}"/>
       
      </td>
    </tr>
    <tr>
      <th scope="row" >county:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magCounty}"/>
         
      </td>
   </tr>
   
    <tr>
      <th scope="row" >township:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magTownship}"/>
          
      </td>
   </tr>
   <tr>
      <th scope="row" >magazine type:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magMagType}"/>
         
      </td>
   </tr>
   <tr>
      <th scope="row" >magazine exposive type:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magExpType}"/>
         
      </td>
   </tr>
   <tr>
      <th scope="row" >magazine min quantity:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magMinQuantity}"/>
         
      </td>
   </tr>
   
   </c:if>
    <tr>
      <th scope="row" >magazine construction type:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magConstruction}"/>
          <html:hidden property="magConstruction"/>
      </td>
   </tr>
   
   <tr>
      <th scope="row" >permit issue date:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magIssueDateString}"/>
          <html:hidden property="magIssueDate"/>
      </td>
   </tr>
   <tr>
      <th scope="row" >permit expiration date:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magExpDateString}"/>
          <html:hidden property="magExpDate"/>
      </td>
   </tr>
   <tr>
        <th scope="row" >magazine contact person:</th>
        <td>
          <html:text property="magContactPerson" size="30" maxlength="30"/>
         </td>
    </tr>
    <tr>
        <th scope="row" class="required" >* phone:</th>
        <td>
          <html:text property="magPhone" size="10" maxlength="10"/>
         </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
           <html:text property="magFax" size="10" maxlength="10"/>
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="magEmail" size="50" maxlength="50"/>
        </td>
    </tr>
    
    
       <tr>
      <th scope="row"    >   fire department:</th>
      <td>
          <html:select property="magFd" styleId="SELECT_FD" onchange="setSelectValue('SELECT_FD','DEPARTMENT_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="FIRE_DEPT_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       
       </td>
    </tr>
    <tr>
        <th scope="row" >directions to magazine site (max 255 characters):</th>
        <td>
            <html:textarea property="magDirections"  rows="10" cols="70"/>
        </td>
    </tr>
    <tr>
        <th scope="row" >miles from  nearest building:</th>
        <td>
            <html:text property="magNearBldg" size="30" maxlength="30"/>
        </td>
    </tr>
    
    <tr>
        <th scope="row" >miles from  nearest highway:</th>
        <td>
            <html:text property="magNearHighway" size="30" maxlength="30"/>
        </td>
    </tr>
    <tr>
        <th scope="row" >miles from  nearest Railway:</th>
        <td>
            <html:text property="magNearRailway" size="30" maxlength="30"/>
        </td>
    </tr>
     <tr>
        <th scope="row" >miles from  nearest Factory:</th>
        <td>
            <html:text property="magNearFactory" size="30" maxlength="30"/>
        </td>
    </tr>
      <tr>
        <th scope="row" >comments: (max 255 characters)</th>
        <td>
            <html:textarea property="magComments"  rows="10" cols="70"/>
        </td>
    </tr>
  </tbody>
        </table>
        <p>
          <html:submit value="Save " styleClass="button"/>
        
        </p>
    </html:form>   
</div>
 

