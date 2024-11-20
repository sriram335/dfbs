<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<hs:control/>

<c:set var="module" scope="request" value="Amuse Rides Permits" />
<c:set var="title" scope="request" value="Amuse Rides Application" />



<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/amuseRide/owner.do?method=view&ownerId=<c:out value="${AMUSE_OWNER_SELECTED.ownerId}"/>">
             [back to owner]</a>
<h2>  Upload File</h2>

<div id="leftContentFluid">
<html:form action="/owner" method="post" enctype="multipart/form-data">

  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="ownerId"/>
   
 <p class="message" size="125">
      <b><u> To upload file</u></b> </br> 
      <b>1. Click "Browse" below and select file and then click Upload select button.</br> </b></p>
     <p class="error" size="125"> <b>
      2. Only files with these extensions are accepted </br>
      BMP,DOC,DWF,GIF,HTM,JPEG,JPG,PDF,PPT,RTF,TIF,TIFF,TXT.Make sure that the file name <U>does not</U> have punctuations like "," or "." or special characters like '&' or '@' etc..  </br>
       3.<u> Also make sure file name and file path can not be more than 99 characters</u></b> </p>
      <c:if test="${sessionScope.FILE_EXTENSION == 'ERROR'}"> 
      <span class="error">ERROR !. Try to upload only acceptable file type and make sure file name does not have speicial characters</span>
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
<c:forEach var="file" items="${sessionScope.AMUSE_OWNER_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; File upload date:<c:out value="${file.fileDateString}"/></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelFire != null}">  
       <a   href="/dfbs/aepermits/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
        </c:if>
          </td>
        
    </tr>
</c:forEach>
  </tbody>
</table> 
</div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
