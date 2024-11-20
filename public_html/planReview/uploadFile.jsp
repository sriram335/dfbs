<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %><%@  
taglib uri="/WEB-INF/hs.tld" prefix="hs"%><hs:control/><html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Plan Review Upload Files"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${PLAN_REVIEW_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.PLAN_REVIEW_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${PLAN_REVIEW_APP_STATUS != 'I'}">
<h2>  Upload File</h2>
</br>For   <c:out value="${sessionScope.PLAN_REVIEW_UPLOAD.idType}"/>: <c:out value="${sessionScope.PLAN_REVIEW_UPLOAD.idNumber}"/> 
<html:form action="/upload" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="idNumber" />
   <html:hidden property="idType" />
  <p class="messageBox" size="200">
       to upload file </br> 
      <b> click "Browse" below and select file and then click Upload select button</b> </p>
  <p class="error" size="200" align="left">
  <b><u>IMPORTANT NOTES:</u></b> 1. Make sure that all files uploaded are in pdf format. We are trying to do "mark ups" for pdf files during review process.</br>
  2.Please do not use specials characters, in file names.
  Example don't use & or # or . symbols in the file names. If you use them
  our review of your project will be delayed. (Oracle/DFBS program uses these
  symbols in their programs for special purpose)  </br>
   4.<u> Also make sure file name and file path can not be more than 99 characters</u>
  5. All data a) Plan Type b) File Location c) submitted are mandatory fields on the screen.</br>
 <b> 6. Current <u>recommended</u> maximum single file size you can upload is 80MB.If you have a bigger file and have a good internet connection
 you can try to upload the file. If the Oracle Application Server does not time out you will be able to upload the file.</b>
 7. Once you have uploaded and verified the file upload list at the bottom, you can close the browser.
  </p>   </br>    </br> 
      Select Plan Type:  <html:select property="planType">
          <html:option value="XX">Please Select</html:option>
          <html:options collection="PLAN_TYPE_OPTIONS" property="value" labelProperty="label"/>
        </html:select>
        <c:if test="${sessionScope.PLAN_FILE_PLAN_TYPE_ERROR == 'ERROR'}">
          <span class="error"><h2>* please select plan Type</h2></span> 
        </c:if> </br></br>
	    File Location: <html:file property="caseFile" size="50" maxlength="99" />   </br></br>
        <c:if test="${sessionScope.PLAN_FILE_EXTENSION_ERROR == 'ERROR'}"> 
      <span class="error"> <h2>ERROR !.There are "." in file name (rename the file without special characters)
          or the file extension is NOT a pdf, Only pdf files are accepted</h2></span>
      </c:if>  
      Submitted By(Please enter Individual Name,Firm Name): <html:text property="submittedBy" size="30" maxlength="30"/>
       <c:if test="${sessionScope.PLAN_FILE_SUBMITTED_ERROR == 'ERROR'}"> 
      <span class="error"><h2>ERROR !. submitted by can not be null</h2></span>
      </c:if>  
         </br> </br>
        <input type="submit" value="Upload selected" class="button"/>
</html:form></br>
<font color="#009933" size ="2"> 1.Please note that if you see your file name in the list below, your file is uploaded correctly. You <u>do not</u> have to call IDHS to confirm upload of file.</br>
2.If you do not get your project filed -confirmation email, Please visit this link 
<a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipnhtml&report=plan_design_release_for_web.rdf&p_sbc_number=<c:out value="${sessionScope.PLAN_REVIEW_UPLOAD.idNumber}"/>">[Click Here] </a> to see status of your project. Some times 
confirmation emails may not be generated due to email server errors. If you see your project status details when you click the link, you <u>do not</u> have to call IDHS office
to verify if we have received the project.
</font></br>
<u>Uploaded File(s)(These are the list of files received by IDHS)</u>
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.PLAN_REVIEW_UPLOAD.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
         <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelPlan != null }"> 
 <%--      <a   href="/dfbs/planReview/upload.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> --%>
        <html:form action="/upload" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View Plan" class="button"/>
         </html:form>
        <a   href="/dfbs/planReview/fileNameChange.do?method=editFileName&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>" >
             [edit file name]</a><br/> 
         </c:if> 
           File date:<c:out value="${file.fileDateString}"/>&nbsp; File sent by:<c:out value="${file.fileLoadedBy}"/>;&nbsp;File Type:<c:out value="${file.filePlanType}"/></br>
           <c:if test="${(sessionScope.DFBS_SECURITY.userId =='DMOSES'   ||  sessionScope.DFBS_SECURITY.userId =='BROBISON' || sessionScope.DFBS_SECURITY.groupLevelAll != null)}">  
           <a href="/dfbs/planReview/upload.do?method=deleteFile&fileId=<c:out value="${file.fileId}"/>&idNumber=<c:out value="${sessionScope.PLAN_REVIEW_UPLOAD.idNumber}"/>"><FONT color="#ff0000">[delete this file, can not be recovered if deleted]</FONT></a> </br>
           </c:if>
          </td>
      </tr>
</c:forEach>

  </tbody>
</table> 
<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
