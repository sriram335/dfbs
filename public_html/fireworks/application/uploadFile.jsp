 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>


<hs:control/>

<c:set var="module" scope="request" value="Fireworks Permits" />
<c:set var="title" scope="request" value="Fireworks Permits Application" />



<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/fireworks/application.do?method=step2">
             [back to application]</a>
<h2>  Upload File</h2>

<div id="leftContentFluid">
<html:form action="/permit" method="post" enctype="multipart/form-data">

  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="applicationKey"/>
   <p class="message" size="125">
      <b><u> To upload file</u></b> </br> 
      <b>1. click "Browse" below and select file and then click Upload select button.</br> 2.Only files with these extensions are accepted </br>
      BMP,DOC,DWF,GIF,HTM,JPEG,JPG,PDF,PPT,RTF,TIF,TIFF,TXT.Make sure that the file name <U>does not</U> have punctuations like "," or "." or special characters like '&' or '@' etc..  </br>
       3.<u> Also make sure file name and file path can not be more than 99 characters</u></b> </p>
      <c:if test="${sessionScope.FILE_EXTENSION == 'ERROR'}"> 
      <span class="error">ERROR !. Try to upload only acceptable files</span>
      </c:if>     
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="100"   />   </td> 
        <input type="submit" value="Upload select" class="button"/>
      
</html:form>

<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >file(s) uploaded</th>
   
  </tr>
  <tbody>
Permit:
<c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelFire != null  }">  
       <a   href="/dfbs/fireworks/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
        </c:if>
          </td>
        <td>
</tr>
</c:forEach>

  </tbody>
</table> 
</div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
