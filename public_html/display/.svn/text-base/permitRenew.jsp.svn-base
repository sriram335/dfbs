<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<c:set var="module" scope="request" value="Fire Display  Permits" />
<c:set var="title" scope="request" value="Permit Information" />
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>Verify and update display Information</h2> 
<a   href="/dfbs/display/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/display/start.do?method=renewBy">
              [back to search]</a>
<a   href="/dfbs/display/ownerListDisplay.do?method=renew&ownerId=<c:out value="${OWNER_SELECTED.ownerId}"/>">
              [back to owner]</a>
<a   href="/dfbs/display/ownerListDisplay.do?method=renewPermit">
              [back to owner contact]</a>

  <div id="sideContentFluid">
  <jsp:include page="shoppingCart.jsp"/>
  </div>
<div id="leftContentFluid" align="left">
<b><u>Steps:</u></b></br>
1.Add the display information.</br>
2.Add permit to Shopping cart</br>
3.Add display date(s).</br>
4.Upload Shooter Resume.</br>
5.Proceed to Check out.</br>
(repeat steps 1 to 5  for multiple displays).
  <div class="styledBox">
 <html:form action="/display" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="checkOut"/> 
       <html:hidden property="displayIdNumber"/> 
        <html:hidden property="displayOwnerId"/> 
     
      
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th scope="row" >permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        NEW</br>
        <html:hidden property="displayIdNumber"/>
      </td>
    </tr>
<%--<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
 
  <tr>
      <th scope="row"  > display start date :</th>
      <td>
       <html:text property="displayDateFrom" size="10" maxlength="10"/>(mm/dd/yyyy)
        <c:if test="${PERMIT_ERROR.displayDateFrom == 'ERROR'}">
              <br/><span class="error">* please enter display from date  </span> 
            </c:if> 
       </td>
      </tr>
    <tr>
      <th scope="row"   > display end date:</th>
      <td>
        <html:text property="displayDateTo" size="10" maxlength="10"/>(mm/dd/yyyy)
        <c:if test="${PERMIT_ERROR.displayDateTo == 'ERROR'}">
              <br/><span class="error">* please enter display to date  </span> 
            </c:if> 
       </td>
      </tr> 
      </c:if> --%>
         <tr>
      <th scope="row">display dates:</th>
      <td>
      Date1:<html:text property="displayDate1" size="10" maxlength="10"/> (mm/dd/yyyy);Time1:<html:text property="displayTime1" size="10" maxlength="10"/></br>
      Date2:<html:text property="displayDate2" size="10" maxlength="10"/> (mm/dd/yyyy);Time2:<html:text property="displayTime2" size="10" maxlength="10"/></br>
      Date3:<html:text property="displayDate3" size="10" maxlength="10"/> (mm/dd/yyyy);Time3:<html:text property="displayTime3" size="10" maxlength="10"/></br>
      Date4:<html:text property="displayDate4" size="10" maxlength="10"/> (mm/dd/yyyy);Time4:<html:text property="displayTime4" size="10" maxlength="10"/></br>
      Date5:<html:text property="displayDate5" size="10" maxlength="10"/> (mm/dd/yyyy);Time5:<html:text property="displayTime5" size="10" maxlength="10"/></br>
      Date6:<html:text property="displayDate6" size="10" maxlength="10"/> (mm/dd/yyyy);Time6:<html:text property="displayTime6" size="10" maxlength="10"/></br>
      Date7:<html:text property="displayDate7" size="10" maxlength="10"/> (mm/dd/yyyy);Time7:<html:text property="displayTime7" size="10" maxlength="10"/></br>
      Date8:<html:text property="displayDate8" size="10" maxlength="10"/> (mm/dd/yyyy);Time8:<html:text property="displayTime8" size="10" maxlength="10"/></br>
      Date9:<html:text property="displayDate9" size="10" maxlength="10"/> (mm/dd/yyyy);Time9:<html:text property="displayTime9" size="10" maxlength="10"/></br>
      Date10:<html:text property="displayDate10" size="10" maxlength="10"/> (mm/dd/yyyy);Time10:<html:text property="displayTime10" size="10" maxlength="10"/></br>
      ( If 10 dates are not enough enter additional dates in comments section.)
       </td>
      </tr>
     
    <tr>
      <th scope="row">address1 :</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayAddress1}"/>
        <html:hidden property="displayAddress1"/> 
      </td>
    </tr>
     <tr>
      <th scope="row">address1 :</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayAddress2}"/>
        <html:hidden property="displayAddress2"/> 
      </td>
    </tr>
     <tr>
      <th scope="row">city :</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayCity}"/>
        <html:hidden property="displayCity"/> 
      </td>
    </tr>
    <tr>
      <th scope="row" >state:</th>
      <td>
         <c:out value="${PERMIT_SELECTED.displayState}"/>
        <html:hidden property="displayState"/> 
       </td>
    </tr>
    <tr>
      <th scope="row" >zip:</th>
      <td>
       <c:out value="${PERMIT_SELECTED.displayZip}"/>
       <html:hidden property="displayZip"/> 
      </td>
    </tr>
    <tr>
      <th scope="row"   class="required"     >  * county:</th>
      <td>
          <html:select property="displayCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_CODE')" >
          <html:option value="">Please Select</html:option>
          <html:options collection="DISPLAY_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.displayCounty == 'ERROR'}">
              <br/><span class="error">* please enter county  </span> 
            </c:if>
       </td>
    </tr>
 
    <tr>
        <th scope="row" class="required" >* phone:</th>
        <td>
          <html:text property="displayPhone" size="10" maxlength="10"/>
            <c:if test="${PERMIT_ERROR.displayPhone == 'ERROR'}">
              <br/><span class="error">* please enter phone  </span> 
            </c:if>
         </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
           <html:text property="displayFax" size="10" maxlength="10"/>
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="displayEmail" size="30" maxlength="50"/>
        </td>
    </tr>
    
    
       <tr>
      <th scope="row"    >   fire department:</th>
      <td>
          <html:select property="displayFd" styleId="SELECT_FD" onchange="setSelectValue('SELECT_FD','DEPARTMENT_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="FIRE_DEPT_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       
       </td>
    </tr>
   <tr>
        <th scope="row" class="required" > * Fire department email:</th>
        <td>
           <html:text property="displayFdEmail" size="30" maxlength="50"/></br>
            <c:if test="${PERMIT_ERROR.displayFdEmail == 'ERROR'}">
              <br/><span class="error">* please enter fire department email  </span> 
            </c:if>
        </td>
    </tr>
   <tr>
        <th scope="row" class="required" > * shooter name:</th>
        <td>
           <html:text property="shooterName" size="30" maxlength="30"/></br>
          <c:if test="${PERMIT_ERROR.shooterName == 'ERROR'}">
              <br/><span class="error">* please enter shooter name  </span> 
            </c:if>
        </td>
    </tr>
     <tr>
        <th scope="row" class="required" > * shooting company name:</th>
        <td>
           <html:text property="companyName" size="30" maxlength="30"/></br>
          <c:if test="${PERMIT_ERROR.companyName == 'ERROR'}">
              <br/><span class="error">* please enter company name  </span> 
            </c:if>
        </td>
    </tr>
     <tr>
        <th scope="row" >comments:(max 255 characters)</th>
        <td>
            <html:textarea property="displayComments" rows="10" cols="70"/>
        </td>
    </tr>
     
  </tbody>
        </table>
     
        <p>
          <html:submit value="Save and Add to the shopping cart " styleClass="button"/></br>
        </p>
     
    </html:form>   
      
    <jsp:include page="displayList.jsp"/>
</div>
 </div>
