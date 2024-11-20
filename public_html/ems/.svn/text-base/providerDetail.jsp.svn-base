<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Update Provider"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:forEach var="file" items="${sessionScope.PROVIDER.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>
<c:if test="${sessionScope.RENEW_PROVIDER  != null }"> 
  <jsp:include page="/ems/renewalProviderStatus.jsp"/>
  </c:if>  
</div>
<div id="mainBox">
  <h2>Update User Information</h2>
 
  <a   href="/dfbs/ems/person.do?method=personList&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ contact list ]</a>  &nbsp;
   <a   href="/dfbs/ems/vehicle.do?method=vehicleList&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ ambulances list ]</a>     &nbsp;      
  <a   href="/dfbs/ems/level.do?method=levelList&providerId=<c:out value="${sessionScope.PROVIDER.providerId}"/>">
              [ level list ]</a>              
   <a   href="/dfbs/ems/provider.do?method=providerList">
              [ back to provider list ]</a>  &nbsp;
   <a   href="/dfbs/ems/providerRenewal.do?method=renewalList">
              [ renewal list ]</a> 
  <c:if test="${EMS_SECURITY.currentUserLevel == 'ADMINISTRATOR' || (EMS_SECURITY.currentUserLevel == 'USER' && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}">
   <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.PROVIDER.providerId}"/>&idType=Provider">
            [upload provider files]  </a>  &nbsp;
  </c:if>
     <a  href="/dfbs/otherUsers/otherUser.do?method=addNewEmsUser&detNumber=<c:out value="${sessionScope.PROVIDER.providerId}"/>&personType=Provider">
            [apply for new user account]  </a> &nbsp;        
  <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
  
  <div id="leftContentFluid">

 <html:form action="/provider" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveProvider"/> 
       <html:hidden property="providerId"/> 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* provider name:</th>
      <td>
       <html:text property="providerName" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerName == 'ERROR'}">
          <br/>
          <span class="error">* please enter provider name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* provider address1:</th>
      <td>
       <html:text property="providerAddress1" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter provider address1</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* provider address2:</th>
      <td>
       <html:text property="providerAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* provider city:</th>
      <td>
       <html:text property="providerCity" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter provider city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* provider state:</th>
      <td>
       <html:select property="providerState" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INTIAL')">
          <html:option value="">Please Select</html:option>
          <html:options collection="PROVIDER_STATE_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* provider zip:</th>
      <td>
       <html:text property="providerZip" size="5" maxlength="5"/>
        <c:if test="${PROVIDER_ERROR.providerZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter provider zip</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* provider zip2:</th>
      <td>
       <html:text property="providerZip2" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerZip2 == 'ERROR'}">
          <br/>
          <span class="error">* please enter provider zip2</span> 
        </c:if>
      </td>
    </tr>
   <tr>
      <th scope="row"  style= "width:50%" class="required"   >* provider Type:</th>
      <td>
       <html:text property="providerType" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerType == 'ERROR'}">
          <br/>
          <span class="error">* please enter provider Type</span> 
        </c:if>
      </td>
    </tr>
  <tr>
      <th scope="row"  style= "width:50%"  class="required"   >* provider certification number:</th>
      <td>
       <html:text property="providerCertNumber" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerCertNumber == 'ERROR'}">
          <br/>
          <span class="error">* please enter certification number</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* provider district:</th>
      <td>
       <html:text property="providerDistrict" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerDistrict == 'ERROR'}">
          <br/>
          <span class="error">* please enter district</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* provider county:</th>
      <td>
       <html:select property="providerCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="PROVIDER_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${PROVIDER_ERROR.providerCounty == 'ERROR'}">
          <br/>
          <span class="error">* please enter county</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"   > provider email:</th>
      <td>
       <html:text property="providerEmail" size="50" maxlength="50"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > provider fax:</th>
      <td>
       <html:text property="providerFax" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > provider phone:</th>
      <td>
       <html:text property="providerPhone" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* provider emergency phone:</th>
      <td>
       <html:text property="providerEmergencyPhone" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerEmergencyPhone == 'ERROR'}">
          <br/>
          <span class="error">* please enter emergency phone</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* provider insurance carrier:</th>
      <td>
       <html:text property="providerInsCarrier" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerInsCarrier == 'ERROR'}">
          <br/>
          <span class="error">* please provider insurance carrier</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* provider insurance policy number:</th>
      <td>
       <html:text property="providerInsPolNumber" size="30" maxlength="30"/>
        <c:if test="${PROVIDER_ERROR.providerInsPolNumber == 'ERROR'}">
          <br/>
          <span class="error">* please provider insurance policy number</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"  height =20 >comments(max 1000 characters):</th>
      <td>
       <html:textarea property="providerComments"  />
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  height =20 >provider certification required:</th>
      <td>
       <html:text property="providerCertRequired" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
     <th  style="color:green"><b><u>PROVIDER DATES</b></u></th>
     </tr>
     </tbody>
     </table>
     <%-- dates table --%>
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
   <tbody class="rowHeader" >
   <tr>
    <th colspan="4" style="color:white" >_____________________________</th>
   <td>
    <th colspan="4" style="color:white" >_____________________________</th>
   </td>
   </tr>
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null }"> 
   <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >insurance effective date:</th>
      <td>
       <html:text property="providerInsEffDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >insurance expiration date:</th>
      <td>
       <html:text property="providerInsExpDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th   colspan="4" scope="row"  style= "width:25%"   >application date:</th>
      <td>
       <html:text property="providerApplicationDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >provider review date:</th>
      <td>
       <html:text property="providerReviewDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >approved date:</th>
      <td>
       <html:text property="providerApprovedDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >update date:</th>
      <td>
       <html:text property="providerUpdateDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >certification date:</th>
      <td>
       <html:text property="providerCertDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >certification request date:</th>
      <td>
       <html:text property="providerCertReqDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >certification re-apply date:</th>
      <td>
       <html:text property="providerReapplyDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >visit date:</th>
      <td>
       <html:text property="providerVisitDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >audit date:</th>
      <td>
       <html:text property="providerAuditDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >initial certificate date:</th>
      <td>
       <html:text property="providerIniCertDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >initial Defibrilation date:</th>
      <td>
       <html:text property="providerIniDfbrDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >initial meeting date:</th>
      <td>
       <html:text property="providerIniMeetingDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >initial intermediate date:</th>
      <td>
       <html:text property="providerIniIntDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >initial AEMT date:</th>
      <td>
       <html:text property="providerIniAemtDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >initial paramedic date:</th>
      <td>
       <html:text property="providerIniParaDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >termination date:</th>
      <td>
       <html:text property="providerTerminationDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >expiration re-apply date:</th>
      <td>
       <html:text property="providerReapplyExpDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >tactical expiration date:</th>
      <td>
       <html:text property="providerTactExpDate" size="10" maxlength="10"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >IHERN expiration date:</th>
      <td>
       <html:text property="providerIhernExpDate" size="10" maxlength="10"/>
      </td>
    </tr>
    </c:if>
    <c:if test="${EMS_SECURITY.currentUserLevel ==  'USER'}">
      <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >insurance effective date:</th>
      <td>
       <c:out value="${PROVIDER.providerInsEffDateString}" />
        <html:hidden property="providerInsEffDate"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >insurance expiration date:</th>
      <td>
       <c:out value="${PROVIDER.providerInsExpDateString}" />
        <html:hidden property="providerInsExpDate"/>
       </td>
    </tr>
    <tr>
     <th   colspan="4" scope="row"  style= "width:25%"   >application date:</th>
      <td>
        <c:out value="${PROVIDER.providerApplicationDateString}" />
        <html:hidden property="providerApplicationDate"/>
       </td>
       <th colspan="4" scope="row"  style= "width:25%"   >provider review date:</th>
      <td>
	<c:out value="${PROVIDER.providerReviewDateString}" />
        <html:hidden property="providerReviewDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >approved date:</th>
      <td>
	<c:out value="${PROVIDER.providerApprovedDateString}" />
        <html:hidden property="providerApprovedDate"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >update date:</th>
      <td>
	<c:out value="${PROVIDER.providerUpdateDateString}" />
        <html:hidden property="providerUpdateDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >certification date:</th>
      <td>
	<c:out value="${PROVIDER.providerCertDateString}" />
        <html:hidden property="providerCertDate"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >certification request date:</th>
      <td>
	<c:out value="${PROVIDER.providerCertReqDateString}" />
        <html:hidden property="providerCertReqDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >certification re-apply date:</th>
      <td>
	<c:out value="${PROVIDER.providerReapplyDateString}" />
        <html:hidden property="providerReapplyDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >visit date:</th>
      <td>
	<c:out value="${PROVIDER.providerVisitDateString}" />
        <html:hidden property="providerVisitDate"/>
     </td>
	<th colspan="4" scope="row"  style= "width:25%"   >audit date:</th>
      <td>
	<c:out value="${PROVIDER.providerAuditDateString}" />
        <html:hidden property="providerAuditDate"/>
      </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >initial certificate date:</th>
      <td>
	<c:out value="${PROVIDER.providerIniCertDateString}" />
        <html:hidden property="providerIniCertDate"/>
       </td>
       <th colspan="4" scope="row"  style= "width:25%"   >initial Defibrilation date:</th>
      <td>
	<c:out value="${PROVIDER.providerIniDfbrDateString}" />
        <html:hidden property="providerIniDfbrDate"/>
        </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >initial meeting date:</th>
      <td>
	<c:out value="${PROVIDER.providerIniMeetingDateString}" />
        <html:hidden property="providerIniMeetingDate"/>
       </td>
       <th colspan="4" scope="row"  style= "width:25%"   >initial intermediate date:</th>
      <td>
	<c:out value="${PROVIDER.providerIniIntDateString}" />
        <html:hidden property="providerIniIntDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >initial AEMT date:</th>
      <td>
	<c:out value="${PROVIDER.providerIniAemtDateString}" />
        <html:hidden property="providerIniAemtDate"/>
       </td>
       <th colspan="4" scope="row"  style= "width:25%"   >initial paramedic date:</th>
      <td>
	<c:out value="${PROVIDER.providerIniParaDateString}" />
        <html:hidden property="providerIniParaDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >termination date:</th>
      <td>
	<c:out value="${PROVIDER.providerTerminationDateString}" />
        <html:hidden property="providerTerminationDate"/>
       </td>
       <th colspan="4" scope="row"  style= "width:25%"   >expiration re-apply date:</th>
      <td>
	<c:out value="${PROVIDER.providerReapplyExpDateString}" />
        <html:hidden property="providerReapplyExpDate"/>
       </td>
    </tr>
    <tr>
     <th colspan="4" scope="row"  style= "width:25%"   >tactical expiration date:</th>
      <td>
	<c:out value="${PROVIDER.providerTactExpDateString}" />
        <html:hidden property="providerTactExpDate"/>
      </td>
       <th colspan="4" scope="row"  style= "width:25%"   >IHERN expiration date:</th>
      <td>
	<c:out value="${PROVIDER.providerIhernExpDateString}" />
        <html:hidden property="providerIhernExpDate"/>
       </td>
    </tr>
   </c:if>
<tr>
<td>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
  <p>
     
            <html:submit value="Update changes" styleClass="button" />
   </p>
   </c:if>
   
    <c:if test="${sessionScope.DFBS_SECURITY ==null &&OTHER_USER == null && sessionScope.PROVIDER_SECURITY_FLAG!='Y'}"> 
  <p>
     
       You do not have clearence to apply for renewal for this provider, contact rstump@dhs.in.gov.
   </p>
   </c:if>
   </td>
 </tr>


   </tbody>
   </table>
   </html:form>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
  <html:form action="/providerRenewal" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="goToRenewal"/> 
       
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" > 
   <tr>
    <td>
  <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.PROVIDER_SECURITY_FLAG=='Y')}"> 
    <p>
       
              <html:submit value="Request Renewal" styleClass="button" />
     </p>
     </c:if> 
     </td>
 </tr>
   </tbody>
   </table>
   </html:form>
   </c:if> 
  </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>

