<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Fire Display  Permits" />
<c:set var="title" scope="request" value="Fire Display  Permits Application" />



<jsp:include page="/app/common/hsPageHeader.jsp"/>
<h2>  Upload File</h2>
 <c:out value="${PERMIT_SELECTED.displayIdNumber}"/>
<a   href="/dfbs/display/display.do?method=backToPermit">
              [back to permit]</a>

<div id="leftContentFluid">
<html:form action="/display" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
  <p class="messageBox" size="100">
       to upload file </br> 
      <b> click "Browse" below and select file and then click Upload select button.</br> Only files with these extensions are accepted </br>
      BMP,DOC,DWF,GIF,HTM,JPEG,JPG,PDF,PPT,RTF,TIF,TIFF,TXT</b> </p>
      <c:if test="${sessionScope.FILE_EXTENSION == 'ERROR'}"> 
      <span class="error">ERROR !. Try to upload only acceptable files and make sure that the file name does not contain"."(period)</span>
      </c:if>     
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="70"   />   </td> 
        <input type="submit" value="Upload select" class="button"/>     
</html:form>
<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >file name</th>
   
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.PERMIT_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/display/display.do?method=downLoadFile&fileId=<c:out value="${file.fileId}"/>&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
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
