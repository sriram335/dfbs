<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %> 
<%@ taglib uri="/WEB-INF/hs.tld" prefix="hs"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<hs:control/>

<html:xhtml/>
<c:set var="module" scope="request" value="IDHS - DFBS system"/>
<c:set var="title" scope="request" value="Indiana LEPC's"/>
<jsp:include page="/main/IdhsPageHeader.jsp"/></br>
 <a   href="/dfbs/otherUsers/otherUser.do?method=logOutLepc" >
              [log out LEPC]</a> 
   <a   href="/dfbs/lepc/lepc.do?method=start">
              [back to start]</a>
    
<div id="mainBox">
  <h2><c:out value='${sessionScope.LEPC_COUNTY_SELECTED}'/> County LEPC</h2>
<a   href="/dfbs/lepc/lepc.do?method=addLepcYear">
             Add Current LEPC Year</a></br></br>
 <%--<c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_ETHICS}" varStatus="i">
       <b> Ethics Status File:<c:out value="${file.fileName}"/></b> <br/>
       <c:if test="${file.filePlanType == 'Approved'}"> 
      <font color="green"> Status:<b><c:out value="${file.filePlanType}"/></b></font>
      </c:if>
           <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>
                     
</c:forEach> <br/></br> --%>
             
 <html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewTutorial"/>
      <input type="image" src="../img/lepc_overall.png" name="viewTutorial" value="viewTutorial"/>
</html:form>
<div id="leftContentFluid">
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<c:forEach var="lepc" items="${LEPC_YEAR_LIST}" varStatus="i" >
<tr>
<th>Year: </th>
<td>
<h3>
<c:out value='${lepc.lepcStatus}'/> &nbsp;&nbsp;
 <c:if test="${lepc.lepcApprovedBy == 'Approved'}">
     <font color="green"> Status: <c:out value='${lepc.lepcApprovedBy}'/> </font>
     </c:if>
       <c:if test="${lepc.lepcApprovedBy == 'Pending'}">
     <font color="red"> Status: <c:out value='${lepc.lepcApprovedBy}'/> </font></br>
      </c:if>
</h3>
         <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="viewLepcYear"/> 
          <input type="hidden" name="lepcId"   value ="${lepc.lepcId}"/> 
          <input type="submit" value="View LEPC" class="button"/>
         </html:form>&nbsp; <br/>
        

<c:forEach var="file" items="${lepc.lepcCountyPlan}" varStatus="i">
        <b> Tier II documents:&nbsp;<c:out value="${file.fileName}"/></b> <br/> 
     
         <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="downLoadFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="hidden" name="fileName"  value="${file.fileName}"/> 
          <input type="hidden" name="fileType"   value="${file.fileType}"/> 
           <input type="submit" value="View" class="button"/>
         </html:form>&nbsp;
        <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
           <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFile"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
  </c:forEach>
  </td>
  </tr>
</c:forEach>

</tbody>
</table>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 