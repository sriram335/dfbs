<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>
 <b><u>Files Uploaded:</u></b></br>
<c:forEach var="file" items="${sessionScope.ELEVATOR_SELECTED.fileList}" varStatus="i">
<table cellspacing="0" style="width: 75%;" summary="sales">
<tr>
    <th>Files </th>
  </tr>
  <tbody>
     <tr class="row<c:out value='${(i.index % 2) + 1}' />">
      <td>
       <c:out value="${file.fileName}"/>&nbsp; <br/> 
       <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelElev != null}">  
       <a   href="/dfbs/elevator/elevator.do?method=downLoadFile&fileName=<c:out value="${file.fileName}"/>&fileId=<c:out value="${file.fileId}"/>&fileType=<c:out value="${file.fileType}"/>" target="_blank">
             [download this file]</a></br>
        </c:if>
          </td>
        <td>
</tr>
</tbody>
</table>
</c:forEach>

<u>Other Elevators for the same owner</u></br>
 <c:forEach var="elevator" items="${sessionScope.OWNER_SELECTED.elevators}" varStatus="i" >
    <a   href="/dfbs/elevator/elevator.do?method=updateElevator&stateNumber=<c:out value="${elevator.stateNumber}"/>"><b><c:out value="${elevator.stateNumber}"/></b></a> </br>
     </c:forEach>

<u>Elevator Applications</u></br>
 <c:forEach var="appl" items="${sessionScope.ELEVATOR_SELECTED.elevAppList}" varStatus="j" >
    <a   href="/dfbs/elevator/application.do?method=updateApplication&applicationId=<c:out value="${appl.applicationId}"/>"><b><c:out value="${appl.applicationDateString}"/></b></a> Status:<c:out value="${appl.appStatus}"/>
  </br>
     </c:forEach>