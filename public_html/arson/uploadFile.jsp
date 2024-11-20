<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<hs:control/>

<c:set var="module" scope="request" value="Arson File(s) upload" />
<c:set var="title" scope="request" value="File Upload(s)" />



<jsp:include page="/app/common/hsPageHeader.jsp" />

<div id="leftContentFluid"> 
<h2>  Upload File</h2>
</br>For   <c:out value="${sessionScope.DFBS_ARSON.idType}"/>: <c:out value="${sessionScope.DFBS_ARSON.idNumber}"/> 


<html:form action="/main" method="post" enctype="multipart/form-data">
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
   <html:hidden property="idNumber" />
   <html:hidden property="idType" />
   
  <p class="messageBox" size="100">
       to upload file </br> 
      <b> click "Browse" below and select file and then click Upload select button</b> </p>
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="70"   />   </td> </br>
        <input type="submit" value="Upload select" class="button"/>
      
</html:form></br>
<u>Uploaded File(s)</u>
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
    
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.DFBS_ARSON.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <a   href="/dfbs/arson/main.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileType=<c:out value="${file.fileType}"/>&fileId=<c:out value="${file.fileId}"/>" target="_blank">
             [download this file]</a> File date:<c:out value="${file.fileDateString}"/>File sent by:<c:out value="${file.fileLoadedBy}"/>
          </td>
        
      </tr>
</c:forEach>

  </tbody>
</table> 
</div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />
