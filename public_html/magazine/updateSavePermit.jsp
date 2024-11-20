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
<div id="sideContentFluid">
<c:forEach var="magazine" items="${OTHER_PERMITS_LIST.list}" varStatus="i" >
          Identification Number:   <c:out value="${magazine.magIdNumber}"/></span><br/>
        Type: <c:out value="${magazine.magMagType}"/>;Expiration Date: <c:out value="${magazine.magExpDateString}"/><br/>
       <c:if test="${ sessionScope.DFBS_SECURITY.groupLevelAll =='DBA' || sessionScope.DFBS_SECURITY.groupLevelFire != null}">
      <a   href="/dfbs/magazine/permit.do?method=editInformation&permitKey=<c:out value="${magazine.magIdNumber}"/>&ownerKey=<c:out value="${magazine.magOwnerId}"/>">
                 [ edit permit info]</a>
      </c:if></br>
</c:forEach>
  </div>
<h2>Update magazine Information</h2> 
<a   href="/dfbs/magazine/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/magazine/start.do?method=renewBy">
              [back to search]</a>
<a   href="/dfbs/magazine/start.do?method=backToIdNumber&magIdNumber=<c:out value="${PERMIT_UPDATE.magIdNumber}"/>">
              [back to owner]</a>

<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="updateSavePermit"/> 
      <html:hidden property="magIdNumber"/> 
      <html:hidden property="magPermitNumber"/> 
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
    <tr>
      <th style="font-size:medium;font-weight:bold" scope="row" > permit number:</th>
      <td style="font-size:medium;font-weight:bold">
       <c:out value="${PERMIT_UPDATE.magIdNumber}"/>
      </td>
    </tr>
    <tr>
          <th scope="row"  class="required"    > * permit year:</th>
          <td>
           <html:text property="magPermitYear" size="4" maxlength="4"/>(YYYY)
                 <c:if test="${PERMIT_ERROR.magPermitYear == 'ERROR'}">
              <br/><span class="error">* please enter permit year  </span> 
            </c:if>
            </td>
          </th>
        </tr> 
     <tr>
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
        <tr>
        <th scope="row" >magazine application received date:</th>
        <td>
          <html:text property="magSignedDate" size="10" maxlength="10"/>(mm/dd/yyyy)
         </td>
    </tr>
    <tr>
        <th scope="row" >magazine issue date:</th>
        <td>
        <html:text property="magIssueDate" size="10" maxlength="10"/>(mm/dd/yyyy)
         </td>
    </tr>
    <tr>
        <th scope="row" >magazine expiration date:</th>
        <td>
        <html:text property="magExpDate" size="10" maxlength="10"/>(mm/dd/yyyy)
          </td>
    </tr>
     <tr>
          <th scope="row"  class="required"    > * street address1:</th>
          <td>
           <html:text property="magAddress1" size="30" maxlength="30"/>
                 <c:if test="${PERMIT_ERROR.magAddress1 == 'ERROR'}">
              <br/><span class="error">* please enter address1  </span> 
            </c:if>
            </td>
        </tr>
     <tr>
          <th scope="row"     >  street address2:</th>
          <td>
           <html:text property="magAddress2" size="30" maxlength="30"/>
             
            </td>
        </tr>
        
      <tr>
          <th scope="row"  class="required"    > * city:</th>
          <td>
           <html:text property="magCity" size="30" maxlength="30"/>
                 <c:if test="${PERMIT_ERROR.magCity == 'ERROR'}">
              <br/><span class="error">* please enter city  </span> 
            </c:if>
            </td>
        </tr>
        
   <tr>
      <th scope="row"   class="required"     >  * state:</th>
      <td>
          <html:select property="magState" styleId="SELECT_INITIAL" onchange="setSelectValue('SELECT_INITIAL','STATE_INTITIAL')">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="MAGAZINE_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.magState == 'ERROR'}">
              <br/><span class="error">* please enter state  </span> 
            </c:if>
       </td>
    </tr> 
        <tr>
          <th scope="row"  class="required"    > * zip:</th>
          <td>
           <html:text property="magZip" size="5" maxlength="5"/>
                 <c:if test="${PERMIT_ERROR.magZip == 'ERROR'}">
              <br/><span class="error">* please enter zip  </span> 
            </c:if>
            </td>
         </tr>
    <tr>
      <th scope="row"   class="required"     >  * county:</th>
      <td>
          <html:select property="magCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_CODE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="MAGAZINE_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.magCounty == 'ERROR'}">
              <br/><span class="error">* please enter county  </span> 
            </c:if>
       </td>
    </tr>
        
          <tr>
      <th scope="row"   class="required"     >  * magazine type:</th>
      <td>
          <html:select property="magMagType" styleId="SELECT_DESC" onchange="setSelectValue('SELECT_DESC','DESCRIPTION')">
          <html:option value="">Please Select</html:option>
          <html:options collection="MAGAZINE_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${PERMIT_ERROR.magMagType == 'ERROR'}">
              <br/><span class="error">* please enter magazine type  </span> 
            </c:if>
       </td>
    </tr>
        <tr>
          <th scope="row"  class="required"    > * type of explosive stored:</th>
          <td>
           <html:text property="magExpType" size="30" maxlength="30"/>
                 <c:if test="${PERMIT_ERROR.magExpType == 'ERROR'}">
              <br/><span class="error">* please enter type of exposive stored  </span> 
            </c:if>
            </td>
         </tr>
          <tr>
          <th scope="row"  class="required"    > *  max. explosive quantity stored:</th>
          <td>
           <html:text property="magMinQuantity" size="12" maxlength="12"/></br> numbers only, write details in comments
                 <c:if test="${PERMIT_ERROR.magMinQuantity == 0}">
              <br/><span class="error">* please enter magazine max quantity  </span> 
            </c:if>
            </td>
        </tr>
          <tr>
          <th scope="row"      > * magazine construction type:(max 255 characters)</th>
          <td>
           <html:textarea property="magConstruction" rows="10" cols="70"/>
            </td>
         </tr>
        
   <tr>
        <th scope="row" >magazine contact person:</th>
        <td>
          <html:text property="magContactPerson" size="45" maxlength="45"/>
         </td>
    </tr>
   
   
    <tr>
        <th scope="row"  class="required" >* phone:</th>
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
           <html:text property="magEmail" size="50" maxlength="50"/>
        </td>
    </tr>
    
    
    <tr>
      <th scope="row"   class="required"     >  * fire department:</th>
      <td>
          <html:select property="magFd" styleId="SELECT_ID" onchange="setSelectValue('SELECT_ID','DEPARTMENT_ID')">
          <html:option value="">Please Select</html:option>
          <html:options collection="FIRE_DEPT_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.magFd == 'ERROR'}">
              <br/><span class="error">* please enter fire department  </span> 
            </c:if>
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
        <th scope="row" >miles from nearest highway:</th>
        <td>
            <html:text property="magNearHighway" size="30" maxlength="30"/>
        </td>
    </tr>
    <tr>
        <th scope="row" >miles from nearest rRailway:</th>
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
     <tr>
      <th scope="row" >online status:</th>
      <td >
      <html:select property="magOnlineStatus" >
          <html:option value="">Please Select</html:option>
          <html:option value="New">New</html:option>
          <html:option value="Approved">Approved</html:option>
          <html:option value="Denied">Denied</html:option>
          <html:option value="Pending">Pending</html:option>
        </html:select>  
      </td>
    </tr>    
  </tbody>
        </table>
        <p>
          <html:submit value=" Save " styleClass="button"/>
        
        </p>
     </html:form> 
        <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="emailInspector"/>
            <p>
              <input type="submit" value="Email Inspector" class="button"/></br></br>
            </p>
        </html:form>
        <html:form action="/permit" method="post">
        <input type="hidden" name="method" id="METHOD_KEY" value="processFee"/>
            <p>
              <input type="submit" value="send fee to accounting" class="button"/></br></br>
            </p>
        </html:form>
</div>

 </div>
