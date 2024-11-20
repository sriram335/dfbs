<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %><%@  
taglib uri="/WEB-INF/hs.tld" prefix="hs"%><hs:control/><html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Plan Review Upload Files"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>

<h2>  Upload File</h2>
</br>For   <c:out value="${sessionScope.PLAN_REVIEW_UPLOAD.idType}"/>: <c:out value="${sessionScope.PLAN_REVIEW_UPLOAD.idNumber}"/> 
<html:form action="/ust" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="ustId" />
   <p class="messageBox" size="200">
       to upload file </br> 
      <b> click "Browse" below and select file and then click Upload select button</b> </p>
  <p class="error" size="200" align="left">
  <b><u>IMPORTANT NOTES:</u></b> 1.Please do not use specials characters, in file names.
  Example don't use & or # or . symbols in the file names. If you use them
  our review of your application will be delayed. (Oracle/DFBS program uses these
  symbols in their programs for special purpose)  </br>
   2. Also make sure file name and file path can not be more than 99 characters
  </p>   </br> </br>
   File Location: <html:file property="caseFile" size="50" maxlength="99" />   </br></br>
        <input type="submit" value="Upload selected" class="button"/>
</html:form></br>
</br>
<u>Uploaded File(s)(These are the list of files received by IDHS)</u>
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.UST_APPLICANT.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
             File date:<c:out value="${file.fileDateString}"/>&nbsp; File sent by:<c:out value="${file.fileLoadedBy}"/>;&nbsp;File Type:<c:out value="${file.filePlanType}"/></br>
            </td>
      </tr>
</c:forEach>

  </tbody>
</table> 
<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
