<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="Child Care Facility Renewal"/>
<c:set var="title" scope="request" value="Child Care Facility Information"/>
<c:set var="step" scope="request" value="2"/>
<c:set var="renew" scope="request" value="renewed"/>

<jsp:include page="/app/common/hsPageHeader.jsp"/>
 <div id="sideContentFluid">
<c:forEach var="file" items="${sessionScope.UPDATE_PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
       <a   href="/dfbs/childCare/childcare.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> 
        </c:if>
        </td>
        <td>
</tr>
</c:forEach>
</div>
<div id="mainBox">
 <h2>Update Permit</h2>
  
<div id="rightContentFluid">
 <p class="control2">
      <html:link page="/childcare.do?method=start&structureType=Child Care Ministries">cancel</html:link>
    </p>
</div>
  <div id="leftContentFluid">
    <html:form action="/childcare" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveUpdatePermit"/>
      <html:hidden property="ownerId"/>
      <html:hidden property="applicationKey"/>
       <html:hidden property="receivedDate"/>
       <html:hidden property="active"/>
      <html:hidden property="county" styleId="COUNTY_NAME"/>
      
         <table cellspacing="0" class="noBorder" summary="OWNER FORM">
          <tbody class="rowHeader">
            <tr>
      <th scope="row" style="width : 50%">permit number:</th>
      <td style="font-size:medium;font-weight:bold">
        <c:out value="${UPDATE_PERMIT_SELECTED.permitNumber}"/>
        <html:hidden property="permitNumber"/>
      </td>
    </tr>
      <th scope="row" class="required">*facility name:</th>
      <td>
        <html:text property="daycareName" size="30" maxlength="30"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.daycareName == 'ERROR'}">
          <br/>
          <span class="error">* please specify actual location</span> 
        </c:if>
      </td>
    </tr> 
    <tr>
      <th scope="row" class="required">*address 1:</th>
      <td>
        <html:text property="address1" size="30" maxlength="30"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.address1 == 'ERROR'}">
          <br/>
          <span class="error">* please specify address address</span> 
        </c:if>
        <br/>
      </td>
    </tr>
    <tr>
      <th scope="row">address 2:</th>
      <td>
        <html:text property="address2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*city:</th>
      <td>
        <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please specify city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*state:</th>
      <td>
        <html:select property="state">
          <html:option value="IN">INDIANA</html:option>
          <html:options collection="DFBS_STATE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${CHILDCARE_PERMIT_ERROR.state == 'ERROR'}">
          <br/>
          <span class="error">* please select a state</span> 
        </c:if>
        <c:if test="${CHILDCARE_PERMIT_ERROR.state == 'ERROR2'}">
          <br/>
          <span class="error">* please select &quot;INDIANA&quot; as state</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*zip:</th>
      <td>
        <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${CHILDCARE_PERMIT_ERROR.zip == 'ERROR'}">
          <br/>
          <span class="error">* please specify zip code</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row" class="required">*county:</th>
      <td>
        <html:select property="county" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">-----</html:option>
          <html:options collection="DFBS_COUNTY_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${CHILDCARE_PERMIT_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please specify county</span> 
        </c:if> 
      </td>
    </tr>
   <tr>
      <th scope="row" class="required">* day care structure type:</th>
      <td>
        <html:select property="structureType" styleId="SELECT_STRUCTURE" onchange="setSelectValue('SELECT_STRCURE','STRUCTURE_TYPE')">
          <html:option value="">-----</html:option>
          <html:options collection="CHILDCARE_STRUCTURE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
       <c:if test="${CHILDCARE_PERMIT_ERROR.structureType == 'ERROR'}">
          <br/>
          <span class="error">* please specify structure type</span> 
        </c:if> 
      </td>
    </tr>
        <tr>
      <th scope="row"   class="required"     >  * fire department:</th>
      <td>
          <html:select property="fdId" styleId="SELECT_ID" onchange="setSelectValue('SELECT_ID','STRUCTURE_TYPE')">
          <html:option value="">Please Select</html:option>
          <html:options collection="DFBS_FD_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PERMIT_ERROR.fdId == 'ERROR'}">
              <br/><span class="error">* please enter fire department  </span> 
            </c:if>
       </td>
    </tr> 
    <tr>
        <th scope="row"  class="required">phone:</th>
        <td>
           <html:text property="telephone" size="10" maxlength="10"/>
    <c:if test="${CHILDCARE_PERMIT_ERROR.telephone == 'ERROR'}">
      <br/>
      <span class="error">* please specify phone number in the correct format</span> 
    </c:if>
        </td>
    </tr>
    <tr>
        <th scope="row" >fax:</th>
        <td>
            <html:text property="fax" size="10" maxlength="10"/> 
        </td>
    </tr>
     <tr>
        <th scope="row" >email:</th>
        <td>
           <html:text property="email" size="60"/>
        </td>
    </tr>
    
     <tr>
        <th scope="row" >comments:</th>
        <td>
           <html:textarea property="notes" /> (max 255 characters)
        </td>
    </tr>     
          </tbody>
        </table>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
        <p>
          <html:submit value="update" styleClass="button"/>
        
        </p>
         </c:if>
        </html:form>
         <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null }">  
         <html:form action="/childcare" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="emailInspector"/>
      <p>
          <html:submit value="Email Inspector" styleClass="button"/>
        
        </p>
        
     </html:form>
    </c:if>

   
  </div>
  <div class="clearer">&nbsp;</div>
</div>
<jsp:include page="/app/common/hsPageFooter.jsp"/>

