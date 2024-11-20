<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="Entertainment Permits" />
<c:set var="title" scope="request" value="Entertainment Permits Application" />



<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/code/newRelease.do?method=backtoview">
             [back to application]</a>
<h2>  Upload File</h2>
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
 <b> 6. Current maximum single file size you can upload is 80MB.</b>
 7. Once you have uploaded and verified the file upload list at the bottom, you can close the browser.</p>
<div id="leftContentFluid">
<html:form action="/newRelease" method="post" enctype="multipart/form-data">

  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="idNumber"/>
   <html:hidden property="idType"/>
  <p class="messageBox" size="100">
       to upload file </br> 
      <b> click "Browse" below and select file and then click Upload select button.</br> Only files with these extensions are accepted </br>
      BMP,DOC,DWF,GIF,HTM,JPEG,JPG,PDF,PPT,RTF,TIF,TIFF,TXT</b> </p>
      <c:if test="${sessionScope.FILE_EXTENSION == 'ERROR'}"> 
      <span class="error">ERROR !. Try to upload only acceptable files</span>
      </c:if>     
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="70"   />   </td> 
        <input type="submit" value="Upload select" class="button"/>
      
</html:form>
<html:form action="/newRelease" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="backtoview"/>
 <input type="submit" value="Done Upload" class="button"/>
 </html:form>
<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >Release file(s) uploaded</th>
   
  </tr>
  <tbody>

<c:forEach var="file" items="${sessionScope.RELEASE_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelCode != null}">  
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
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
