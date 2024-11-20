<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - EMS OPS DIVISION"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<h2>  Upload File(s) to Database</h2>
 
<a   href="/dfbs/fireHouse/fireHouse.do">
              [back to start]</a></br>  
<div id="leftContentFluid">
<html:form action="/fireHouse" method="post" enctype="multipart/form-data">
 
  <input type="hidden" name="method" id="METHOD_KEY" value="uploadFile"/>
 <p class="messageBox" size="125" align="left">
      <b><u> To upload file</u></b> </br> 
      <b>1. click "Browse" below and select file and then click Upload select button.</br>2.Make sure that the file name <U>does not</U> have punctuations like "," or "." or special characters like '&' or '@' etc..  </br>
       3.<u> Also make sure file name and file path can not be more than 99 characters</u></b> </p>
     
   <p  align="left">
 <h3 style="margin-bottom:5px;">  Provider:&nbsp; <c:out value="${PROVIDER_SELECTED.providerName}"/>&nbsp;</h3> <br/>
  <b>Certification Number:&nbsp;</b><html:text property="certNumber" size="12" maxlength="12"/> </br></br>
  <b>User Email:&nbsp;</b><html:text property="email" size="50" maxlength="50"/> </br>
  (Errors in files will be communicated to this user by IDHS)</br></br>
   <c:if test="${sessionScope.CERT_NUMBER_ERROR == 'ERROR'}"> 
      <span class="error">ERROR !. Please Enter Cert Number</span>
      </c:if>   
     <c:if test="${sessionScope.USER_EMAIL_ERROR == 'ERROR'}"> 
      <span class="error">ERROR !.Please Enter User Email</span>
      </c:if> 
    </p>
       <td width="100%" align="left" valign="middle"> 
	     <html:file property="caseFile" size="100"   />   </td> 
        <input type="submit" value="Upload EMS file" class="button"/>
      
</html:form>

<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >file(s) uploaded</th>
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.PROVIDER_SELECTED.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> upload date:<c:out value="${file.fileDateString}"/>;File sent by:<c:out value="${file.fileLoadedBy}"/>
  <%--     <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelElev != null}">  
       <a   href="/dfbs/elevator/elevator.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a>
        </c:if> --%>
          </td>
        </tr>
</c:forEach>
  </tbody>
</table> 
</div>

<div class="clearer">&nbsp;</div>
</div>

<jsp:include page="/app/common/hsPageFooter.jsp" />