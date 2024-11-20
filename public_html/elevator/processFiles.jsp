<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<hs:control/>
<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - ELEVATOR SERVICES DIVISION"/>
<jsp:include page="/app/common/hsPageHeader.jsp"/>
<div id="mainBox">
<h2>  Process Elevator Tests </h2>
<a   href="/dfbs/elevator/start.do">
              [back to start]</a></br>  
<div id="leftContentFluid">


<table cellspacing="0" style="width:100%;" summary="sales" >
  <tr>
    <th >Tests to be processed</th>
  </tr>
  <tbody>
<c:forEach var="file" items="${requestScope.TEST_FILE_LIST.list}" varStatus="i">
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> upload date:<c:out value="${file.fileDateString}"/>;File sent by:<c:out value="${file.fileLoadedBy}"/></br>
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   ||  sessionScope.DFBS_SECURITY.groupLevelElev != null}">  
        <a   href="/dfbs/elevator/elevator.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [view this file]</a></br>
       <a   href="/dfbs/elevator/elevator.do?method=approveFile&fileId=<c:out value="${file.fileId}"/>&stateNumber=<c:out value="${file.fileConnector}"/>&userEmail=<c:out value="${file.fileLoadedBy}"/>&uploadDate=<c:out value="${file.fileDateString}"/>">
             [Approve this file]</a>
        
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