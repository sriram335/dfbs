<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %><%@  
taglib uri="/WEB-INF/hs.tld" prefix="hs"%><hs:control/><html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="LEPC Upload Files"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<a   href="/dfbs/lepc/lepc.do?method=viewLepcYear&lepcId=<c:out value="${sessionScope.LEPC_SELECTED_ID}"/>">
                 [back to LEPC]</a>
<c:if test="${LEPC_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.LEPC_REVIEW_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${LEPC_APP_STATUS != 'I'}">
<h2>  Upload File</br>
</br>For   <c:out value='${sessionScope.LEPC_COUNTY_SELECTED}'/> County LEPC</h2> </br>
<c:if test="${sessionScope.LEPC_DOC_UPLOAD_TYPE == 'Legal Notice'}">
<p class="messageBox" size="200" align="left"><u><b> LEGAL NOTICE</b></u></br>
>>	DO upload ONLY newspaper clippings and/or legal affidavits </br>
>>	DO NOT submit media publication requests or member correspondence</br>
</p>
</c:if>
<c:if test="${sessionScope.LEPC_DOC_UPLOAD_TYPE == 'Fiscal Report'}">
<p class="messageBox" size="200" align="left"><u><b> FISCAL REPORT</b></u></br>
>>	DO upload ONLY county auditor's report on LEPC expenditure for prior year</br>
</p>
</c:if>
<c:if test="${sessionScope.LEPC_DOC_UPLOAD_TYPE == 'Bylaws'}">
<p class="messageBox" size="200" align="left"><u><b> BYLAWS</b></u></br>
>>	DO upload ONLY current updated, signed and dated document</br>
</p>
</c:if>
<c:if test="${sessionScope.LEPC_DOC_UPLOAD_TYPE == 'Plan Review'}">
<p class="messageBox" size="200" align="left"><u><b> PLAN UPDATES</b></u></br>
>>	DO upload entire plan or copies of actual pages with updates</br>
>>	DO update annually--at the very least--the 10 items denoted by asterisks in the <i>plan review document:</i></br>
<a   href="http://www.in.gov/dhs/files/LEPC_plan_evaluation_checklist.pdf">http://www.in.gov/dhs/files/LEPC_plan_evaluation_checklist.pdf</a></br>
>>	DO NOT upload a notice stating/implying that no changes or minimal changes have been made to the plan</br> 
>>	DO NOT upload a notice stating changes without providing copies of the changes within the plan itself</br>
>>	DO NOT upload documents or plans NOT addressing hazardous materials and/or the 9 planning elements identified in the <i>plan review document</i></br>
>>	DO NOT upload any plan review document with your updates/changes/comments on it
</br>
</p>
</c:if>
<c:if test="${sessionScope.LEPC_DOC_UPLOAD_TYPE == 'Meeting Minutes'}">
<p class="messageBox" size="200" align="left"><u><b> MEETING MINUTES</b></u></br>
>>	DO include attendee sign-in sheets with ALL meeting minutes </br>
>>	DO upload a minimum of 4 meeting minutes per year; 2 meetings required per every half year</br>
</p>
</c:if>
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
<html:form action="/lepc" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="lepcId" />
  
  <p class="error" size="200" align="left">
  <b><u>IMPORTANT NOTES:</u></b></br> 1)	Please do not use special characters in file names. </br> Example: don't use symbols like '&' or '#' or '.' If you use them, our review of your submission will be delayed. </br>
  2)	All data-LEPC File Type, File Location-are mandatory fields on this screen.   </br>
  <b> 3)	Current maximum single file size you can upload is 80MB.</b> </br>
   4)<u> Also make sure file name and file path can not be more than 99 characters</u>
  5) PLEASE UPLOAD ONLY NON-ALTERABLE FILES (e.g., PDF, JPEG, BMP etc.)
  </p>   </br>    </br> 
   
      <b> To upload file </br> 
       click "Choose File" below and select file,</br> then click "upload selected" button</b> </br></br>
      Select LEPC File Type:  <html:select property="lepcDocType">
          <html:option value="XX">Please Select</html:option>
          <html:options collection="LEPC_DOC_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${sessionScope.LEPC_DOC_TYPE_ERROR == 'ERROR'}">
          <span class="error"> <h2>* please select document Type</h2> </span> 
        </c:if> </br></br>
	    File Location: <html:file property="caseFile" size="50" maxlength="99" />   </br></br>
        <input type="submit" value="Upload selected" class="button"/>
       
</html:form></br>
</c:if>
<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
