<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
<c:forEach var="magazine" items="${OTHER_PERMITS_LIST.list}" varStatus="i" >
          Identification Number:   <c:out value="${magazine.magIdNumber}"/></span><br/>
        Type: <c:out value="${magazine.magMagType}"/>;Expiration Date: <c:out value="${magazine.magExpDateString}"/><br/>
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null || sessionScope.MAGAZINE_USER != null}">   
       <a   href="/dfbs/magazine/permit.do?method=updatePermit&permitKey=<c:out value="${magazine.magIdNumber}"/>&ownerKey=<c:out value="${magazine.magOwnerId}"/>">
                [ update permit]</a>
      </c:if></br>
</c:forEach>
  </div>
<h2>Update magazine Information</h2> 
<a   href="/dfbs/magazine/start.do?method=backToStart">
              [back to start]</a>
<a   href="/dfbs/magazine/start.do?method=renewBy">
              [back to search]</a>
<a   href="/dfbs/magazine/ownerListDisplay.do?method=renew&ownerId=<c:out value="${OWNER_SELECTED.ownerId}"/>&idNumber=<c:out value="${PERMIT_UPDATE.magIdNumber}"/>">
              [back to owner]</a></br>
  <a   href="/dfbs/magazine/permit.do?method=goToUpload">
              [Upload Magazine Floor Plan]</a></br>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">
<c:forEach var="file" items="${sessionScope.PERMIT_UPDATE.fileList}" varStatus="i">
<tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp;,Upload Date:<c:out value="${file.fileDateString}"/> 
       <a   href="/dfbs/magazine/permit.do?method=downLoadFile&fileId=<c:out value="${file.fileId}"/>&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a><br/>
         <c:if test="${(sessionScope.DFBS_SECURITY.userId =='DTOLAN'   || sessionScope.DFBS_SECURITY.userId =='CCLOUSE' || sessionScope.DFBS_SECURITY.groupLevelAll != null)}">  
       <a href="/dfbs/magazine/permit.do?method=deleteFile&fileId=<c:out value="${file.fileId}"/>&permitKey=<c:out value="${PERMIT_UPDATE.magIdNumber}"/>&ownerKey=<c:out value="${PERMIT_UPDATE.magOwnerId}"/>"><FONT color="#ff0000">[delete this file, can not be recovered if deleted]</FONT></a> </br>
        </c:if>
          </td>
        <td>
</tr>
</c:forEach>
</c:if>
<div id="leftContentFluid">
  <div class="styledBox">
 <html:form action="/permit" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdatedPermit"/> 
      <html:hidden property="magIdNumber"/> 
      <html:hidden property="magIdNumber"/> 
       <html:hidden property="magExpType"/>
        <html:hidden property="magMinQuantity"/>
         <html:hidden property="magMagType"/>
         <html:hidden property="magSignedDate"/>
         <html:hidden property="magExpStatus"/>
         <html:hidden property="magPermitNumber"/>
<table cellspacing="0" class="noBorder" summary="OWNER DATA">
      <tbody class="rowHeader">
      <tr>
    <th style="color:rgb(198,198,198)" >__________________________</th>
    </tr>
   

    <tr>
      <th scope="row" style="width : 50%">magazine id / permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${PERMIT_UPDATE.magIdNumber}"/>
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
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null || sessionScope.MAGAZINE_USER != null }">  
   
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
      <th scope="row" >magazine type:</th>
      <td>
         <c:out value="${PERMIT_UPDATE.magMagType}"/>
         
      </td>
   </tr>
   <tr>
      <th scope="row" >type of explosive stored:</th>
      <td>
         <c:out value="${PERMIT_UPDATE.magExpType}"/>
         
      </td>
   </tr>
   <tr>
      <th scope="row" >*  max. explosive quantity stored:</th>
      <td>
         <c:out value="${PERMIT_UPDATE.magMinQuantity}"/>
         
      </td>
   </tr>
    
     <tr>
        <th scope="row" >magazine application received date:</th>
        <td>
          <c:out value="${PERMIT_UPDATE.magSignedDateString}"/>
          <html:hidden property="magSignedDate"/>
         </td>
    </tr>
    <tr>
        <th scope="row" >magazine issue date:</th>
        <td>
        <c:out value="${PERMIT_UPDATE.magIssueDateString}"/>
         <html:hidden property="magIssueDate"/>
         </td>
    </tr>
    <tr>
        <th scope="row" >magazine expiration date:</th>
        <td>
        <c:out value="${PERMIT_UPDATE.magExpDateString}"/>
        <html:hidden property="magExpDate"/>
         </td>
    </tr>
   </c:if>
    <tr>
      <th scope="row" >magazine construction type:</th>
      <td>
         <c:out value="${PERMIT_UPDATE.magConstruction}"/>
          <html:hidden property="magConstruction"/>
      </td>
   </tr>
   
   
   <tr>
        <th scope="row" >magazine contact person:</th>
        <td>
          <html:text property="magContactPerson" size="30" maxlength="30"/>
         </td>
    </tr>
    <tr>
        <th scope="row" class="required" > * phone:</th>
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
        <th scope="row" >directions to magazine site:</th>
        <td>
         <html:textarea property="magDirections" rows="10" cols="70"/>
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
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null  || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
      <tr>
        <th scope="row" >comments: (max 255 characters)</th>
        <td>
            <html:textarea property="magComments" rows="4" cols="70"/>
        </td>
    </tr>
     </c:if>
      <tr>
      <th scope="row"    >   online Status:</th>
      <td>
          <c:out value="${PERMIT_UPDATE.magOnlineStatus}"/>
          <html:hidden property="magOnlineStatus"/>
       
       </td>
    </tr>
   
  </tbody>
        </table>
        <p>
          <html:submit value="update " styleClass="button"/>
        
        </p>
    </html:form>   
</div>
 

