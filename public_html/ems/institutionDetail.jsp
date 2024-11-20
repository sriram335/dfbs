<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - Ems Operations"/>
<c:set var="title" scope="request" value="Update Institution"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="sideContentFluid">
  <h2>Current available files</h2> 
<c:forEach var="file" items="${sessionScope.INSTITUTION.fileList}" varStatus="i">
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/ems/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a></br> File date:<c:out value="${file.fileDateString}"/></br> File sent by:<c:out value="${file.fileLoadedBy}"/>
</c:forEach>

</div>
<div id="mainBox">

  <h2>Update Institution Information</h2>
  
     <a   href="/dfbs/ems/person.do?method=personInstList&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ contact list ]</a> &nbsp;
     <a   href="/dfbs/ems/agmts.do?method=agmtsList&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [  site agreement(s) list ]</a> &nbsp;
     <a   href="/dfbs/ems/course.do?method=courseList&institutionId=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>">
              [ course list ]</a>    
     <a   href="/dfbs/ems/institution.do?method=institutionList">
              [ back to institution list ]</a> 
      <a  href="/dfbs/otherUsers/otherUser.do?method=addNewEmsUser&detNumber=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>&personType=Institution">
            [apply for new user account]  </a> &nbsp;
         <a href="/dfbs/ems/main.do?method=authUser">
            [back to main menu]  </a> </br>&nbsp;
    <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
    <a href="/dfbs/ems/main.do?method=goToUpload&idNumber=<c:out value="${sessionScope.INSTITUTION.institutionId}"/>&idType=Institution">
            [upload institution files]</a> </br>&nbsp;
    </c:if>
      <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null ||(OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
    <c:if test="${sessionScope.INSTITUTION.instPersonEmail >0 }"> 
     <a href="/dfbs/ems/course.do?method=addNewCourse">
                [add new course]  </a> &nbsp;
     </c:if> 
     <c:if test="${sessionScope.INSTITUTION.instPersonEmail == 0 }"> 
     <p class="error"> !! You can not add a new Course !!.We do not have Medical Director and Training Institution Official emails on file. This online application
     works exclusively with emails. Contact the Institution officials and submit the their email(s) to rachelmiller@dhs.in.gov or update the contact list </p>
     </c:if> 
      </c:if><br />

  <div id="leftContentFluid">

 <html:form action="/institution" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="saveInstitution"/> 
       <html:hidden property="institutionId"/> 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" >
   <tr>
    <th style="color:rgb(198,198,198)" >_____________________________</th>
    </tr>
     <tr>
      <th scope="row"  style= "width:50%" class="required"  >* institution name:</th>
      <td>
       <html:text property="institutionName" size="30" maxlength="30"/>
        <c:if test="${INSTITUTION_ERROR.institutionName == 'ERROR'}">
          <br/>
          <span class="error">* please enter institution name</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* institution address1:</th>
      <td>
       <html:text property="institutionAddress1" size="30" maxlength="30"/>
        <c:if test="${INSTITUTION_ERROR.institutionAddress1 == 'ERROR'}">
          <br/>
          <span class="error">* please enter institution address1</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* institution address2:</th>
      <td>
       <html:text property="institutionAddress2" size="30" maxlength="30"/>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* institution city:</th>
      <td>
       <html:text property="institutionCity" size="30" maxlength="30"/>
        <c:if test="${INSTITUTION_ERROR.institutionCity == 'ERROR'}">
          <br/>
          <span class="error">* please enter institution city</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* institution state:</th>
      <td>
      <html:select property="institutionState" styleId="SELECT_STATE" onchange="setSelectValue('SELECT_STATE','STATE_INTIAL')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSTITUTION_STATE_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%" class="required"  >* institution zip:</th>
      <td>
       <html:text property="institutionZip" size="5" maxlength="5"/>
        <c:if test="${INSTITUTION_ERROR.institutionZip == 'ERROR'}">
          <br/>
          <span class="error">* please enter institution zip</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   >* institution zip2:</th>
      <td>
       <html:text property="institutionZip2" size="30" maxlength="30"/>
        <c:if test="${INSTITUTION_ERROR.institutionZip2 == 'ERROR'}">
          <br/>
          <span class="error">* please enter institution zip2</span> 
        </c:if>
      </td>
    </tr>
   
  <tr>
      <th scope="row"  style= "width:50%"  class="required"   >* institution certification number:</th>
      <td>
       <html:text property="institutionCertNumber" size="30" maxlength="30"/>
        <c:if test="${INSTITUTION_ERROR.institutionCertNumber == 'ERROR'}">
          <br/>
          <span class="error">* please enter certification number</span> 
        </c:if>
      </td>
    </tr>
    
     <tr>
      <th scope="row"  style= "width:50%"  class="required"  >* institution county:</th>
      <td>
       <html:select property="institutionCounty" styleId="SELECT_COUNTY" onchange="setSelectValue('SELECT_COUNTY','COUNTY_NAME')">
          <html:option value="">Please Select</html:option>
          <html:options collection="INSTITUTION_COUNTY_LIST_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${INSTITUTION_ERROR.institutionCounty == 'ERROR'}">
          <br/>
          <span class="error">* please enter county</span> 
        </c:if>
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > institution phone:</th>
      <td>
       <html:text property="institutionPhone" size="10" maxlength="10"/>
        
      </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > institution fax:</th>
      <td>
       <html:text property="institutionFax" size="10" maxlength="10"/>
       </td>
    </tr>
    <tr>
      <th scope="row"  style= "width:50%"   > institution email:</th>
      <td>
       <html:text property="institutionEmail" size="80" maxlength="80"/>
      </td>
    </tr>
   
    
       <th  style="color:green"><b><u>INSTITUTION DATES</b></u></th>
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
     <th  colspan="4" scope="row"  style= "width:25%"   > certification date:</th>
      <td>
       <html:text property="institutionCertDate" size="10" maxlength="10"/>
      </td>
      <th  colspan="4" scope="row"  style= "width:25%"   >expiration date:</th>
      <td>
       <html:text property="institutionExpDate" size="10" maxlength="10"/>
      </td>
       </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >review date:</th>
      <td>
       <html:text property="institutionReviewedDate" size="10" maxlength="10"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >received date:</th>
      <td>
       <html:text property="institutionReceivedDate" size="10" maxlength="10"/>
      </td>
    </tr>
     <tr>
      <th  colspan="4" scope="row"  style= "width:25%"   >certificate requested date:</th>
      <td>
       <html:text property="institutionCertReQDate" size="10" maxlength="10"/>
      </td>
    </tr>
 </c:if>
  <c:if test="${EMS_SECURITY.currentUserLevel == 'USER' }">  
   <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   > certification date:</th>
      <td>
       <c:out value="${INSTITUTION.institutionCertDateString}" />
        <html:hidden property="institutionCertDate"/>
      </td>
      <th  colspan="4" scope="row"  style= "width:25%"   >expiration date:</th>
      <td>
	<c:out value="${INSTITUTION.institutionExpDateString}" />
        <html:hidden property="institutionExpDate"/>
      </td>
       </tr>
     <tr>
     <th  colspan="4" scope="row"  style= "width:25%"   >review date:</th>
      <td>
	<c:out value="${INSTITUTION.institutionReviewedDateString}" />
        <html:hidden property="institutionReviewedDate"/>
      </td>
       <th  colspan="4" scope="row"  style= "width:25%"   >received date:</th>
      <td>
	<c:out value="${INSTITUTION.institutionReceivedDateString}" />
        <html:hidden property="institutionReceivedDate"/>
      </td>
    </tr>
     <tr>
      <th  colspan="4" scope="row"  style= "width:25%"   >certificate requested date:</th>
      <td>
	<c:out value="${INSTITUTION.institutionCertReQDateString}" />
        <html:hidden property="institutionCertReQDate"/>
      </td>
    </tr>  
</c:if>
    <tr>
<td>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
<p>
   
          <html:submit value="Update changes" styleClass="button" />
 </p>
 </c:if>
 </td>
 </tr>
   </tbody>
   </table>
   </html:form>
    <html:form action="/institution" method="post">
      <input type="hidden" name="method" id="METHOD_KEY" value="requestRenewal"/> 
       <html:hidden property="institutionId"/> 
  <table cellspacing="0" class="noBorder"  summary="OWNER FORM">
  
  
   <tbody class="rowHeader" > 
   <tr>
    <td>
     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelEms != null || 
    (OTHER_USER !=null && sessionScope.INSTITUTION_SECURITY_FLAG=='Y')}"> 
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

