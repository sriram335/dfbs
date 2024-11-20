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
   <c:if test="${(sessionScope.DFBS_SECURITY.groupLevelLepc != null ||sessionScope.DFBS_SECURITY.groupLevelAll != null) }"> 
  <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=lepc_meeting_schedule">
              [meeting schedule for all counties]</a> &nbsp;&nbsp;    
   <a href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyopen&report=lepc_meeting_schedule&destype=mail&desformat=delimiteddata&FROM=ierc@dhs.in.gov&desname=iwusi@dhs.in.gov&subject=LEPC meeting schedule attached">[meeting schedule .csv for all counties]</a> &nbsp;&nbsp;
 </c:if>
<div id="sideContentFluid">
<c:forEach var="user" items="${COUNTY_USER_LIST}" varStatus="i" >
   
  <div class="listing">
 <b> User Id:<c:out value="${user.userLoginId}" /></b>
  <p class="listingInfo">
  Name:
    <c:out value="${user.userLastName}" />,<c:out value="${user.userFirstName}" /><br />
  Password good untill:  <c:out value="${user.userPasswordExpiresDateString}" /><br />
   Phone: <c:out value="${user.userPhone}" />&nbsp;&nbsp;County:<c:out value="${user.userCounty}" /><br />
   <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER'}">
    <a   href="/dfbs/otherUsers/otherUser.do?method=dropUserLEPC&userId=<c:out value="${user.userId}"/>&countyName=<c:out value="${user.userCounty}" />" >
              [Drop this User]</a> 
    </c:if>
    <br />
  </p>
 </div>
  </c:forEach>
  </div>
<div id="mainBox">
  <h2>List of LEPCs</h2>

<div id="leftContentFluid">
<a   href="/dfbs/otherUsers/otherUser.do?method=userListLepc">
              Go to LEPC User(s)</a></br>
 <c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER'}">
 <%-- <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyopen&report=lepc_roster_ethics.rdf&destype=mail&desformat=delimiteddata&FROM=ierc@dhs.in.gov&desname=bgavin@dhs.in.gov&subject=Ethics Roster"> Print Roster for Eithics for excel</a></br> --%>
  <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsepyopen&report=lepc_roster_ethics_contacts.rdf&destype=mail&desformat=delimiteddata&FROM=ierc@dhs.in.gov&desname=bgavin@dhs.in.gov&subject=Ethics contacts"> Eithics contacts report for excel</a></br>
  <a   href="/dfbs/lepc/lepc.do?method=goToUploadEthics">
             Upload Ethics File</a></br>
 </c:if>
<%-- <c:forEach var="file" items="${sessionScope.LEPC_DOC_LIST_ETHICS}" varStatus="i">
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
         <c:if test="${(file.fileLoadedBy == OTHER_USER.userLoginId && file.filePlanType != 'Approved')||(sessionScope.DFBS_SECURITY.groupLevelLepc != null && file.filePlanType != 'Approved') }"> 
          <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="deleteFileEthics"/> 
          <input type="hidden" name="fileId"   value ="${file.fileId}"/> 
          <input type="submit" value="Delete" class="button"/>
         </html:form>
         </c:if>
             
</c:forEach> <br/> --%>
<html:form action="/lepc" method="post">
  <input type="hidden" name="method" id="METHOD_KEY" value="viewTutorial"/>
      <input type="image" src="../img/lepc_overall.png" name="viewTutorial" value="viewTutorial"/>
</html:form> <br/>
<table cellspacing="0"  summary="sales"  style="border: solid #E2C400 1px;width:100%;" >
      <tbody class="rowHeader">  
<c:if test="${sessionScope.DFBS_SECURITY.groupLevelAll != null   || sessionScope.DFBS_SECURITY.groupLevelLepc == 'USER' ||sessionScope.OTHER_USER.userLoginId != null}"> 
<c:if test="${(sessionScope.DFBS_SECURITY.groupLevelLepc != null || sessionScope.DFBS_SECURITY.groupLevelAll != null) }"> 
      
          <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="viewAllCounties"/> 
          <input type="hidden" name="lepcId"   value ="${file.fileId}"/> 
          <input type="submit" value="View All County Information" class="button"/>
         </html:form>&nbsp; <br/>
         <a   href="https://oasdev.dhs.in.gov/reports/rwservlet?dfbsipypdf&report=lepc_roster.rdf">[ run roster report]</a></br>
</c:if> <br/>
<c:forEach var="county" items="${LEPC_COUNTY_LIST}" varStatus="i" >
<tr class="row<c:out value='${(i.index % 2) + 1}'/>">
<th> </th>
<td>

<a   href="/dfbs/lepc/lepc.do?method=goToCounty&countyName=<c:out value='${county}'/>"><h4 ><c:out value='${county}'/></h4></a> &nbsp;
     <%--    <html:form action="/lepc" method="post" >
          <input type="hidden" name="method" id="METHOD_KEY" value="goToCounty"/> 
          <input type="hidden" name="countyName"   value ="${county}"/> 
          <input type="submit" value="Go to LEPC(s)" class="button"/>
         </html:form>    --%>
 <a   href="/dfbs/lepc/lepc.do?method=goToCounty&countyName=<c:out value='${county}'/>">
              Go to this county LEPC(s)</a> &nbsp;      
<a   href="/dfbs/otherUsers/otherUser.do?method=userListLepcCounty&countyName=<c:out value='${county}'/>">
              Show contacts for this county</a></br>
</td>
</tr>
</c:forEach></br>
</c:if>
</tbody>
</table>
</div>
  <div class="clearer">&nbsp;</div>
 </div> 