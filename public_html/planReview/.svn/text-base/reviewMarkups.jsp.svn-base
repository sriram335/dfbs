<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - FIRE AND BUILDING SERVICES DIVISION"/>
<c:set var="title" scope="request" value="Plan Review Upload Files"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<c:if test="${PLAN_REVIEW_APP_STATUS == 'I'}">
<p class="error">
 <c:out value="${sessionScope.PLAN_REVIEW_APP_MESSAGE}" />
</p>
</c:if>
<c:if test="${PLAN_REVIEW_APP_STATUS != 'I'}">
<div id="leftContentFluid"> 
<h2>  Markup File(s)</h2>
 
<table cellspacing="0" style="width:50%;" summary="sales" >
  <tr>
    
  </tr>
  <tbody>
<c:forEach var="file" items="${sessionScope.PLAN_REVIEW_MARKUPS.fileList}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
      <html:form action="/upload" method="post" >
    <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFileMarkup"/> 
    <input type="hidden" name="fileIdMarkup"   value ="${file.fileId}"/> 
    <input type="hidden" name="fileNameMarkup"  value="${file.fileName}"/> 
    <input type="hidden" name="fileTypeMarkup"   value="${file.fileType}"/> 
    
       <c:out value="${file.fileName}"/>&nbsp;For Project Number: <c:out value="${file.fileConnector}"/> &nbsp;File Type:<c:out value="${file.filePlanType}"/><br/> 
       File date:<c:out value="${file.fileDateString}"/>&nbsp;,File sent by:<c:out value="${file.fileLoadedBy}"/></br>
         <input type="submit" value="View Plan" class="button"/>
         </html:form>
          </td>
      </tr>
</c:forEach>
<c:if test="${sessionScope.PLAN_REVIEW_MARKUPS_SIZE == null || sessionScope.PLAN_REVIEW_MARKUPS_SIZE == 0}"> 
<p class="error">
Plans review time is over, contact the Plan Reviewer for additional review time.
</p>
</c:if> 

  </tbody>
</table> 
</div>

<div class="clearer">&nbsp;</div>
</div>
</c:if>
<jsp:include page="/app/common/hsPageFooter.jsp" />
