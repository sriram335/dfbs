<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Magazine Permits" />
<c:set var="title" scope="request" value="Permit Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Verify and update magazine Information</h2> 
<a   href="/dfbs/magazine/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/magazine/start.do?method=renewBy">
              [back to search]</a>
<a   href="/dfbs/magazine/ownerListDisplay.do?method=renew&ownerId=<c:out value="${OWNER_SELECTED.ownerId}"/>">
              [back to owner]</a>
<a   href="/dfbs/magazine/ownerListDisplay.do?method=renewPermit">
              [back to owner contact]</a>
<a   href="/dfbs/magazine/permit.do?method=goToUpload">
              [Upload Magazine Floor Plan]</a>
  <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
  </div>
<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="checkOut"/> 
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
        <html:hidden property="magOwnerId"/>
        <html:hidden property="magPermitNumber"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
     <tr> 
      <th scope="row"style="width : 50%">magazine id/permit number :</th>
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
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null || sessionScope.MAGAZINE_USER != null}">  
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
          <html:hidden property="magTownship"/>
      </td>
   </tr>
   <tr>
      <th scope="row" >magazine type:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magMagType}"/>
         
      </td>
   </tr>
   <tr>
      <th scope="row" >type of explosive stored:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magExpType}"/>
          
      </td>
   </tr>
   <tr>
      <th scope="row" >*  max. explosive quantity stored:</th>
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
      <th scope="row" >permit application date:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.magSignedDateString}"/>
          <html:hidden property="magSignedDate"/>
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
          <html:text property="magContactPerson" size="30" maxlength="45"/>
         </td>
    </tr>
    <tr>
        <th scope="row" class="required" >* phone:</th>
        <td>
          <html:text property="magPhone" size="10" maxlength="10"/>
            <c:if test="${PERMIT_ERROR.magPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
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
           <html:text property="magEmail" size="30" maxlength="50"/>
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
        <th scope="row" >miles from nearest building:</th>
        <td>
            <html:text property="magNearBldg" size="30" maxlength="30"/>
           
        </td>
    </tr>
    
    <tr>
        <th scope="row" >miles from nearest highway:</th>
        <td>
            <html:text property="magNearHighway" size="30" maxlength="30"/>
        </td>
    </tr>
    <tr>
        <th scope="row" >miles from nearest railway:</th>
        <td>
            <html:text property="magNearRailway" size="30" maxlength="30"/>
        </td>
    </tr>
     <tr>
        <th scope="row" >miles from nearest factory:</th>
        <td>
            <html:text property="magNearFactory" size="30" maxlength="30"/>
        </td>
    </tr>
     <tr>
        <th scope="row" >comments:(max 255 characters)</th>
        <td>
            <html:textarea property="magComments" rows="4" cols="70"/>
        </td>
    </tr>
     
  </tbody>
        </table>
        <p>
          <html:submit value="Renew and Add to the shopping cart" styleClass="button"/>
        
        </p>
    </html:form> 
   <b><u> Other Permits you may want to Renew </u></b>
<table cellspacing="0" style="width:75%;" summary="sales">
  <tr>
    <th >permit number</th>
  </tr>
  <tbody>
    <c:forEach var="magazine" items="${PERMIT_RENEW_LIST.list}" varStatus="i" >
       <c:if test="${(magazine.magExpStatus =='R' || magazine.magExpDays >-90 ) && magazine.magIssueDate != null}"> 
          <tr class="row<c:out value='${(i.index % 2) + 1}' />">
        <td><span class="message" style="font-weight:font-size:small;">
             Permit Number:  <c:out value="${magazine.magPermitNumber}"/>&nbsp; <br/> 
          Identification Number:   <c:out value="${magazine.magIdNumber}"/>
          <a   href="/dfbs/magazine/permit.do?method=renew&permitNumber=<c:out value="${magazine.magIdNumber}"/>">
             [ Renew this magazine ]</a> 
        </span><br/>
       </td>
      </tr>
      </c:if>
     </c:forEach>
  </tbody>
</table>
    <%--<c:if test="${PERMIT_SELECTED.magOnlineStatus == 'New'}">
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null}">   
    <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="approve"/> 
       <p>
          <html:submit value="Approve and Email inspector " styleClass="button"/>
        
        </p>
    </html:form>  
    </c:if>

    </c:if> --%>
</div>
 </div>
