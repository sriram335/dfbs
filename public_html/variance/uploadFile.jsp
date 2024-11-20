<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<hs:control/>

<c:set var="module" scope="request" value="IDHS -Variance Application Online" />
<c:set var="title" scope="request" value="Variance Application" />



<jsp:include page="/app/common/hsPageHeader.jsp" />
<a   href="/dfbs/variance/start.do?method=backToApplication">
             [back to application]</a>
<h2>  Upload File</h2>

<div id="leftContentFluid">
<html:form action="/start" method="post" enctype="multipart/form-data">

  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="idNumber"/>
   <html:hidden property="idType"/>
  <p class="messageBox" size="125">
      <b><u> To upload files or Documents or Photos</u></b> </br> 
      <b>1. click "Browse" below and select file and then click Upload select button.</br> 2.Only files with these extensions are accepted </br>
      BMP,DOC,DWF,GIF,HTM,JPEG,JPG,PDF,PPT,RTF,TIF,TIFF,TXT.Make sure that the file name <U>does not</U> have punctuations like "," or "." or special characters like '&' or '@' etc..  </br>
       3.<u> Also make sure file name and file path can not be more than 99 characters</u></b> </p>
      <c:if test="${sessionScope.FILE_EXTENSION == 'ERROR'}"> 
      <span class="error">ERROR !. Try to upload only acceptable files</span>
      </c:if>    
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="70"   />   </td> </br></br>
        <input type="submit" value="Upload selected" class="button"/>
      
</html:form></br>

<html:form action="/start" method="post" >
<input type="hidden" name="method" id="METHOD_KEY" value="backToApplication"/>
 <input type="submit" value="Upload Complete" class="button"/>
 </html:form>
<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >file(s) uploaded</th>
  </tr>
  <tbody>

<c:forEach var="file" items="${sessionScope.VARIANCE_APPLICATION_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      Name <c:out value="${file.fileName}"/>&nbsp; Date:<c:out value="${file.fileDateString}"/><br/> 
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
