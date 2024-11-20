<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Update Hospital"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:forEach var="file" items="${sessionScope.HOSPITAL.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>

</div>
<div id="mainBox">
  <h2>Update Hospital Information</h2>
  
  <a   href="/dfbs/ems/person.do?method=personHospList&hospitalId=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>">
              [ contact list ]</a> &nbsp;
  <a   href="/dfbs/ems/provider.do?method=hospProvList&hospitalId=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>">
              [ attached provider list ]</a> &nbsp;
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.HOSPITAL_SECURITY_FLAG=='Y')}"> 
   <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>&idType=Hospital">
            [upload hospital files]  </a> &nbsp;
  </c:if>
  <a  href="/dfbs/otherUsers/otherUser.do?method=addNewEmsUser&detNumber=<c:out value="${sessionScope.HOSPITAL.hospitalId}"/>&personType=Hospital">
            [apply for new user account]  </a> &nbsp;
 <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>
  <div id="leftContentFluid">

 <html:form action="/hospital" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveHospital"/> 
       <html:hidden property="hospitalId"/> 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* hospital name:</th>
      <td>
       <html:text property="hospitalName" size="30" maxlength="30"/>
        <c:if test="${HOSPITAL_ERROR.hospitalName == 'ERROR'}">
          <br/>
          <span class="error">* please enter hospital name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* hospital address1:</th>
      <td>
       <html:text property="address1" size="30" maxlength="30"/>
        <c:if test="${HOSPITAL_ERROR.Address1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter hospital address1</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* hospital address2:</th>
      <td>
       <html:text property="address2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* hospital city:</th>
      <td>
       <html:text property="city" size="30" maxlength="30"/>
        <c:if test="${HOSPITAL_ERROR.city == 'ERROR'}">
          <br/>
          <span class="error">* please enter hospital city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* hospital state:</th>
      <td>
      <html:select property="state" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INTIAL')">
          <html:option value="">Please Select</html:option>
          <html:options collection="HOSPITAL_STATE_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* hospital zip:</th>
      <td>
       <html:text property="zip" size="5" maxlength="5"/>
        <c:if test="${HOSPITAL_ERROR.hospitalZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter hospital zip</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* hospital zip2:</th>
      <td>
       <html:text property="zip2" size="30" maxlength="30"/>
        <c:if test="${HOSPITAL_ERROR.hospitalZip2 == 'ERROR'}">
          <br/>
          <span class="error">* please enter hospital zip2</span> 
        </c:if>
      </td>
    </tr>
   
  <tr>
      <th scope="row"  style= "width:50%"  class="required"   >* hospital certification number:</th>
      <td>
       <html:text property="certNumber" size="30" maxlength="30"/>
        <c:if test="${HOSPITAL_ERROR.certNumber == 'ERROR'}">
          <br/>
          <span class="error">* please enter certification number</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* hospital district:</th>
      <td>
       <html:text property="distirict" size="30" maxlength="30"/>
        <c:if test="${HOSPITAL_ERROR.district == 'ERROR'}">
          <br/>
          <span class="error">* please enter district</span> 
        </c:if>
      </td>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* hospital county:</th>
      <td>
       <html:select property="county" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="HOSPITAL_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${HOSPITAL_ERROR.county == 'ERROR'}">
          <br/>
          <span class="error">* please enter county</span> 
        </c:if>
      </td>
    </tr>
  
    <tr>
      <th scope="row"  style= "width:50%"  height =20 >comments:</th>
      <td>
       <html:text property="comments" size="255" maxlength="225"/>
      </td>
    </tr>
    
       <th  style="color:green"><b><u>HOSPITAL DATES</b></u></th>
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
     <th  colspan="4" scope="row"  style= "width:25%"   >initial certification date:</th>
      <td>
       <html:text property="iniCertDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >visit date:</th>
      <td>
       <html:text property="visitDate" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >expiration date:</th>
      <td>
       <html:text property="expDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >audit date:</th>
      <td>
       <html:text property="auditDate" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >application received date:</th>
      <td>
       <html:text property="appRecivedDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >approved date:</th>
      <td>
       <html:text property="approvedDate" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >re-application date:</th>
      <td>
       <html:text property="reapplyDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >certtificate requested date:</th>
      <td>
       <html:text property="certRequestedDate" size="10" maxlength="10"/>
      </td>
    </tr>
    <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >review date:</th>
      <td>
       <html:text property="reviewedDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >updated date:</th>
      <td>
       <html:text property="updateDate" size="10" maxlength="10"/>
      </td>
    </tr>
</c:if>
<c:if test="${ EMS_SECURITY.currentUserLevel == 'USER'}">
<tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >initial certification date:</th>
      <td>
	<c:out value="${HOSPITAL.iniCertDateString}" />
        <html:hidden property="iniCertDate"/>
        </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >visit date:</th>
      <td>
	<c:out value="${HOSPITAL.visitDateString}" />
        <html:hidden property="visitDate"/>
       </td>
    </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >expiration date:</th>
      <td>
	<c:out value="${HOSPITAL.expDateString}" />
        <html:hidden property="expDate"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >audit date:</th>
      <td>
	<c:out value="${HOSPITAL.auditDateString}" />
        <html:hidden property="auditDate"/>
        </td>
    </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >application received date:</th>
      <td>
	<c:out value="${HOSPITAL.appRecivedDateString}" />
        <html:hidden property="appRecivedDate"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >approved date:</th>
      <td>
	<c:out value="${HOSPITAL.approvedDateString}" />
        <html:hidden property="approvedDate"/>
      </td>
    </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >re-application date:</th>
      <td>
	<c:out value="${HOSPITAL.reapplyDateString}" />
        <html:hidden property="reapplyDate"/>
       </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >certtificate requested date:</th>
      <td>
	<c:out value="${HOSPITAL.certRequestedDateString}" />
        <html:hidden property="certRequestedDate"/>
      </td>
    </tr>
    <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >review date:</th>
      <td>
	<c:out value="${HOSPITAL.reviewedDateString}" />
        <html:hidden property="reviewedDate"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >updated date:</th>
      <td>
	<c:out value="${HOSPITAL.updateDateString}" />
        <html:hidden property="updateDate"/>
      </td>
    </tr>
</c:if>


    <tr>
<td>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.HOSPITAL_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Update changes" styleClass="button" />
 </p>
 </c:if>
 </td>
 </tr>
   </tbody>
   </table>
   </html:form>
    <html:form action="/hospital" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="requestRenewal"/> 
       <html:hidden property="hospitalId"/> 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" > 
   <tr>
    <td>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.HOSPITAL_SECURITY_FLAG=='Y')}"> 
    <p>
       
              <html:submit value="Request Renewal" styleClass="button" />
     </p>
     </c:if>
     </td>
 </tr>
   </tbody>
   </table>
   </html:form>
   </div>
  <div class="clearer">&nbsp;</div>
 </div>  
 
<jsp:include page="/app/common/hsPageFooter.jsp"/>

